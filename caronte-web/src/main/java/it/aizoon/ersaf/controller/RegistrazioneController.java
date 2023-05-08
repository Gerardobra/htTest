package it.aizoon.ersaf.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarDComune;
import it.aizoon.ersaf.dto.generati.CarDProvincia;
import it.aizoon.ersaf.dto.generati.CarTUtente;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.SpedizioniereForm;
import it.aizoon.ersaf.form.UtenteForm;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.validator.UtenteValidator;

/**
 * @author Nicolò Mandrile
 */

@Controller
@RequestMapping(value = { "/registrazione" })
public class RegistrazioneController extends BaseController {

  @Autowired
  private IUtenteEJB utenteEJB = null;

  @Autowired
  private IDecodificheEJB decodificheEJB = null;

  @Autowired
  private UtenteValidator validator;

  @GetMapping(value = "/registrazione")
  public String login(@ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm, Model model,
      HttpServletRequest request) throws BusinessException {

    prepareModelData(model, spedizioniereForm);
    return "registrazione/registrazione";
  }

  private void prepareModelData(Model model, SpedizioniereForm spedizioniereForm) throws BusinessException {
    model.addAttribute("tipiProfilo", decodificheEJB.getTipiRuolo());
    model.addAttribute("listaProvince", decodificheEJB.getListaProvince());
    model.addAttribute("listaTipiSpedizionieri", decodificheEJB.getListaTipiSpedizioniere());
    model.addAttribute("listaSezioni", decodificheEJB.getListaSezioni());

    if (spedizioniereForm != null && spedizioniereForm.getIdProvinciaSedeSociale() != null) {
      model.addAttribute("listaComuni", decodificheEJB.getListaComuni(spedizioniereForm.getIdProvinciaSedeSociale()));
    } else {
      model.addAttribute("listaComuni", new ArrayList<CarDComune>());
    }
  }

  @PostMapping(params = "datiSpedizioniere", value = "/registrazione")
  public String salvaDatiNuovo(@ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm,
      BindingResult result, Model model, SessionStatus sessionStatus) throws BusinessException {

	// Validazione di dati dell'utente e dello spedizioniere
	logger.debug("-- Validazione di dati dell'utente e dello spedizioniere");
    validator.validateDatiRegistrazioneUtente(spedizioniereForm, result);
    
    // Se la provincia della sede legale è fuori regione Lombardia : validare anche i campi 'codice Ruop' e 'Data registrazione Ruop'
    if(spedizioniereForm.getIdProvinciaSedeSociale() != null){
    	CarDProvincia prov = decodificheEJB.getProvinciaByIdProv(spedizioniereForm.getIdProvinciaSedeSociale());
    	if(prov.getIdRegione() != CaronteConstants.ID_REGIONE_LOMBARDIA){
    		logger.debug("-- Effettuare validazione sui campi codice Ruop e Data registrazione Ruop");
    		validator.validateDatiSedeFuoriRegione(spedizioniereForm, result);
    	}
    }

    if (result.getErrorCount() == 0) {
      logger.debug("-- Le validazioni sono andate a buon fine, si puo' procedere con il salvataggio dei dati");
      try {
    	  
    	  /*
    	   *  Se ho passato i controlli di campi obbligatori, l'utente avrà anche selezionato il check di accettazione trattamento dati personali, quindi
    	      setto con la data di oggi il campo dataAccettazionePrivacy 
    	  */
    	  logger.debug("-- setto dataAccettazionePrivacy");
    	  spedizioniereForm.setDataAccettazionePrivacy(new Date());

    	/*
    	 * Possibili casi :
    	 * 1) utente presente su db : l'utente non viene fatto proseguire in fase di validazione dati, quindi non arriva in questo ramo       	
    	 * 2) utente non è presente su db e spedizioniere presente su db : insert utente e update spedizionere
    	 * 3) utente e spedizioniere non presenti su db : insert utente e spedizioniere    
    	 * 
    	 *   Attenzione : in qualsiasi caso il nuovo utente verrà subito abilitato alla sezione 'Autorizzazioni'
    	 */    	
        SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniereByCuua(spedizioniereForm.getCuaa());                
        /*
         * CASO SPEDIZIONIERE presente su db :
         * aggiorno i dati dello spedizioniere
         */
        if (spedizioniere != null) {
          logger.debug("-- CASO SPEDIZIONIERE presente su db");
          logger.debug("-- idSpedizioniere ="+spedizioniere.getIdSpedizioniere());
          spedizioniereForm.setIdSpedizioniere(spedizioniere.getIdSpedizioniere());
          spedizioniereForm.setSezioneExport(spedizioniere.isSezioneExport());
          spedizioniereForm.setSezioneVivai(spedizioniere.isSezioneVivai());
          spedizioniereForm.setSezioneControlli(spedizioniere.isSezioneControlli());
          spedizioniereForm.setSezioneAutorizzazioni(true);          
          logger.debug("-- update spedizioniere e insert utente");
          utenteEJB.updateSpedizioniereRegistrazione(spedizioniereForm, null);
          Long idUtente = utenteEJB.insertUtenteRegistrazione(spedizioniereForm, passwordEncoder);
          logger.debug("-- idUtente ="+idUtente);
        } 
        /*
         * CASO SPEDIZIONIERE NON presente su db :
         * inserisco i dati dello spedizioniere
         */
        else{
          /*if (spedizioniereForm.getIdAssociazioneSezione() != null) {
            spedizioniereForm.setSezioneImport((spedizioniereForm.getIdAssociazioneSezione().intValue() & 1) > 0);
            spedizioniereForm.setSezioneExport((spedizioniereForm.getIdAssociazioneSezione().intValue() & 2) > 0);      
            spedizioniereForm.setSezioneVivai((spedizioniereForm.getIdAssociazioneSezione().intValue() & 4) > 0); 
          }*/
          logger.debug("-- CASO SPEDIZIONIERE non presente su db");
          logger.debug("-- insert spedizioniere e insert utente");
          // qualsiasi utente deve essere abilitato subito alla sezione 'Autorizzazioni'
          spedizioniereForm.setSezioneAutorizzazioni(true);
          utenteEJB.registraUtenteESpedizioniere(spedizioniereForm, passwordEncoder);
        }

      } 
      catch (BusinessException be) {
        model.addAttribute("page_error", "Errore nei dati inseriti");
        logger.error("BusinessException in salvaDatiNuovo ="+be.getMessage());
        prepareModelData(model, spedizioniereForm);

        return "registrazione/registrazione";
      }

      return "redirect:./registrazioneOK";
    }
    else{
    	logger.debug("-- Le validazioni NON sono andate a buon fine"+result.getFieldError());
    }

    prepareModelData(model, spedizioniereForm);

    return "registrazione/registrazione";
  }
  
  
  @GetMapping(value = "/checkIfcheckIfUtenteExists_{codiceFiscale}")
  @ResponseBody
  public String checkIfcheckIfUtenteExists(@PathVariable("codiceFiscale") String codiceFiscale, Model model, HttpServletRequest request)
      throws BusinessException {

    CarTUtente utente = utenteEJB.getUtenteByCodiceFiscale(codiceFiscale);

    if (utente == null) {
      return "NOTEXISTS";
    } 
    else {     
      return 
    		 utente.getIdUtente()+ "&&&" +
    		 utente.getCodiceFiscale() + "&&&" + 
    		 utente.getCognome() + "&&&" +
    		 utente.getEmail() + "&&&" +
    		 utente.getNome() + "&&&" + 
    		 utente.getNote() + "&&&" + 
    		 utente.getPassword() + "&&&" + 
    		 utente.getTelefono()+ "&&&" +
    		 utente.getCellulare();   
    }
  }

  @GetMapping(value = "/checkIfSpedizioniereExists_{cuaa}")
  @ResponseBody
  public String checkIfSpedizioniereExists(@PathVariable("cuaa") String cuaa, Model model, HttpServletRequest request)
      throws BusinessException {

    SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniereByCuua(cuaa);

    if (spedizioniere == null) {
      return "NOTEXISTS";
    } else {
      StringBuilder sb = new StringBuilder("<option value=\"\">Selezionare</option>");

      if (spedizioniere.getIdComuneSedeSociale() != null) {
        CarDComune comune = decodificheEJB.getComuneByPrimaryKey(spedizioniere.getIdComuneSedeSociale());
        spedizioniere.setIdProvinciaSedeSociale(comune.getIdProvincia());
        List<CarDComune> listaComuni = new ArrayList<>();

        listaComuni = decodificheEJB.getListaComuni(comune.getIdProvincia());
        for (CarDComune com : listaComuni) {
          sb.append("<option value=\"" + com.getIdComune() + "\">" + com.getDenomComune() + "</option>");
        }
      }

      return spedizioniere.getIdTipoSpedizioniere() + "&&&" + spedizioniere.getDenomSpedizioniere() + "&&&"
          + spedizioniere.getIdProvinciaSedeSociale() + "&&&" + spedizioniere.getIdComuneSedeSociale() + "&&&"
          + spedizioniere.getDenomComuneSedeSociale() + "&&&" + spedizioniere.getIndirizzoSedeSociale() + "&&&"
          + spedizioniere.getEmailSpedizioniere() + "&&&" + spedizioniere.getNumeroTelefono() + "&&&"
          + spedizioniere.getCodiceRUOP() + "&&&" + spedizioniere.getPec() + "&&&" + spedizioniere.getCapSedeSociale()
          + "&&&" + spedizioniere.getPartitaIVA() + "&&&" + spedizioniere.isSezioneImport() + "&&&"
          + spedizioniere.isSezioneExport() + "&&&"  + spedizioniere.isSezioneVivai() + "&&&"
          + spedizioniere.getNumeroCellulare() + "&&&" + sb.toString() + "&&&" 
          + spedizioniere.getTipoSpedizioniereAltro();
    }
  }

  @GetMapping(value = "/getListaComuni_{idProvincia}")
  @ResponseBody
  public String getComuni(@PathVariable("idProvincia") Long idProvincia, Model model) throws BusinessException {

    List<CarDComune> listaComuni = new ArrayList<>();
    StringBuilder sb = new StringBuilder("<option value=\"\">Selezionare</option>");

    if (idProvincia != null) {
      listaComuni = decodificheEJB.getListaComuni(idProvincia);
    }

    for (CarDComune comune : listaComuni) {
      sb.append("<option value=\"" + comune.getIdComune() + "\">" + comune.getDenomComune() + "</option>");
    }

    return sb.toString();
  }

  @GetMapping(value = "/registrazioneOK")
  public String registrazioneOK(Model model, HttpSession session) throws BusinessException {
	logger.debug("La registrazione è andata a buon fine");      
	session.setAttribute("registrazioneOK", "La registrazione è stata effettuata con successo.");        
    return getRedirect("login", null);
    
  }

  @GetMapping(value = "/riassegnaPassword_{token}")
  public String riassegnaPassword(@ModelAttribute("utenteForm") UtenteForm utenteForm, Model model,
      @PathVariable("token") String token, HttpServletRequest request, HttpSession session) throws BusinessException {

    /*
     * l'utente atterra qua dopo aver cliccato sul token della mail di
     * attivazione o reimpostazione psw. leggo il token da db e vedere se è
     * ancora valido!!
     */
    UtenteDTO utente = new UtenteDTO();
    utente = utenteEJB.getUtenteByToken(utente);

    if (utente == null) {
      model.addAttribute("error", "Il link utilizzato è scaduto.");
    } else {

      if (utente.getDataToken() == null) {
        model.addAttribute("page_error", "Il link utilizzato è scaduto.");
      } else {

        LocalDate dateToken = utente.getDataToken().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateTokenFineValidita = dateToken.plusDays(3); // 72 ore di
                                                                 // validita
        LocalDate now = LocalDate.now();
        // controllo che la data del token non sia scaduta
        if (!now.isBefore(dateTokenFineValidita)) {
          model.addAttribute("page_error",
              "Richiesta scaduta. E' passato troppo tempo dalla generazione dell'email per la riassegnazione della password. E' necessario effettuare nuovamente la richiesta.");
        } else {
          model.addAttribute("token", utente.getToken());
        }
      }

    }
    return "registrazione/riassegnaPassword";
  }

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping(value = "/riassegnaPassword")
  public String riassegnaPasswordPost(@ModelAttribute("utenteForm") UtenteForm utenteForm, BindingResult result,
      Model model, HttpServletRequest request, HttpSession session) throws BusinessException {

    // leggo il token da db e vedere se è ancora valido!!
    String token = request.getParameter("token");
    UtenteDTO utente = new UtenteDTO();
    utente.setToken(token);
    utente = utenteEJB.getUtenteByToken(utente);

    if (utente == null) {
      model.addAttribute("page_error", "Il link utilizzato è scaduto.");
      return "registrazione/riassegnaPassword";
    } else {

      if (utente.getDataToken() == null) {
        model.addAttribute("page_error", "Il link utilizzato è scaduto.");
        return "registrazione/riassegnaPassword";
      } else {

        LocalDate dateToken = utente.getDataToken().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateTokenFineValidita = dateToken.plusDays(3); // 72 ore di
                                                                 // validita
        LocalDate now = LocalDate.now();
        // controllo che la data del token non sia scaduta
        if (!now.isBefore(dateTokenFineValidita)) {
          model.addAttribute("page_error",
              "Richiesta scaduta. E' passato troppo tempo dalla generazione dell'email per la riassegnazione della password. E' necessario effettuare nuovamente la richiesta.");
          return "registrazione/riassegnaPassword";

        } else {
          model.addAttribute("token", utente.getToken());
        }
      }
    }

    validator.validatePasswordUtente(utenteForm, result);

    if (result.getErrorCount() == 0) {
      try {

        utenteEJB.updatePasswordUtente(utenteForm, passwordEncoder);

      } catch (BusinessException be) {
        model.addAttribute("page_error", "Errore nella modifica della password.");
        model.addAttribute("riassegnaPsw", true);
        return "registrazione/riassegnaPassword";
      }

      if (session.getAttribute("idUtenteReimpostazionePsw") != null)
        session.removeAttribute("idUtenteReimpostazionePsw");

      return "redirect:./riassegnazionePswOK";

    }

    return "registrazione/riassegnaPassword";

  }

  @GetMapping(value = "/riassegnazionePswOK")
  public String riassegnazionePswOK(Model model, HttpSession session) throws BusinessException {

    session.setAttribute("riassegnazionePswOK", true);
    return getRedirect("login", null);
  }

  @GetMapping(value = "/forgotPassword")
  public String forgotPassword(@ModelAttribute("utenteForm") UtenteForm utenteForm, Model model, HttpSession session)
      throws BusinessException {

    return "registrazione/forgotPassword";
  }

  @PostMapping(value = "/forgotPassword")
  public String forgotPasswordPost(@ModelAttribute("utenteForm") UtenteForm utenteForm, Model model,
      HttpSession session, HttpServletRequest request) throws BusinessException {

    String email = utenteForm.getEmail();
    Long idUtente = null;
    if (email != null) {
      idUtente = utenteEJB.getUtenteByEmail(email);
      logger.debug("-- idUtente ="+idUtente);
    }

    if (idUtente != null) {
      logger.debug("-- CASO idUtente != null"); 
      // invio mail
      UtenteDTO utente = utenteEJB.getUtente(idUtente);

      String token = "";
      boolean flagAttivazione = false;
      String nomeCognome = utente.getCognome() + " " + utente.getNome();

      /* try { */
      // scrivo il token nel db con la data di validita SYSDATE
      logger.debug("-- scrivo il token nel db con la data di validita SYSDATE");
      token = generateToken();
      utente.setDataToken(new Date());
      utente.setToken(token);
      utenteEJB.updateTokenUtente(utente);
      utenteEJB.inviaMailRegistrazioneOCambioPassword(email, nomeCognome, token, flagAttivazione);

      addAlertSuccessMessage(
          "Una email con le istruzioni per reimpostare la password è stata inviata all'indirizzo inserito");
      return getRedirect("login", request);

      /*
       * } catch (BusinessException be) { model.addAttribute("err",
       * "Errore applicativo, contattare l'assistenza"); }
       */
    } else {
      model.addAttribute("err", "L'indirizzo email inserito non appartiene ad un utente registrato.");
      logger.debug("-- L'indirizzo email inserito non appartiene ad un utente registrato");
    }

    return "registrazione/forgotPassword";
  }
  
  @GetMapping(value = "/isProvFuoriRegLombardia_{idProvincia}")
  @ResponseBody
  public String isProvFuoriRegLombardia(@PathVariable("idProvincia") Long idProvincia, Model model) throws BusinessException {
	logger.debug("BEGIN isProvFuoriRegLombardia");  
    String provFuoriRegLombardia = "N";
    
    CarDProvincia prov = decodificheEJB.getProvinciaByIdProv(idProvincia);
    if(prov != null && prov.getIdRegione() != CaronteConstants.ID_REGIONE_LOMBARDIA){
    	logger.debug("-- idRegione fuori Lombardia ="+prov.getIdRegione());
    	provFuoriRegLombardia = "S";
    }
    logger.debug("END isProvFuoriRegLombardia");
	return provFuoriRegLombardia;
  }
  
  
  
  
  
}
