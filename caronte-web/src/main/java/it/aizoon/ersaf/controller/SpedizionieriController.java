package it.aizoon.ersaf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.CentroAziendaleDto;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.generati.CarDComune;
import it.aizoon.ersaf.dto.generati.CarDTipoSpedizioniere;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.SpedizioniereForm;
import it.aizoon.ersaf.security.SecurityUtils;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.validator.UtenteValidator;

/**
 * @author Nicolò Mandrile
 */

@Controller
@SessionAttributes("nuovaRichiestaForm")
@RequestMapping(value = { "/admin/utenti/spedizionieri" })
public class SpedizionieriController extends BaseController {

  @Autowired
  private IDecodificheEJB decodificheEJB = null;

  @Autowired
  private IUtenteEJB utenteEJB = null;

  @Autowired
  private UtenteValidator validator;

  private static final String TIPOLOGIA_ATTORI = "spedizionieri";

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/elenco")
  public String cerca(@ModelAttribute("spedizioniereForm") @Valid SpedizioniereForm spedizioniereForm,
      BindingResult result, Model model, SessionStatus sessionStatus) throws BusinessException {

    sessionStatus.setComplete();
    model.addAttribute("secondaryActiveLink", TIPOLOGIA_ATTORI);

    try {
      model.addAttribute("listaTipiSpedizionieri", decodificheEJB.getListaTipiSpedizioniere());
      model.addAttribute("tipiProfilo", decodificheEJB.getTipiRuolo());      
      model.addAttribute("elencoSpedizionieri", utenteEJB.getElencoSpedizionieri(spedizioniereForm));   
      model.addAttribute("listaStatiAzienda", decodificheEJB.getListaStatiAzienda());

    } catch (BusinessException exc) {
      model.addAttribute("page_error", "Errore nella ricerca.");
    }

    return "utenti/" + TIPOLOGIA_ATTORI + "/elenco";
  }

  private void addModelData(Model model, SpedizioniereForm spedizioniereForm) throws BusinessException {
    addModelData(model, spedizioniereForm, null);
  }
  
  private void addModelData(Model model, SpedizioniereForm spedizioniereForm, SpedizioniereDTO datiSpedizioniere) throws BusinessException {
    Long idProvinciaSedeSociale = null;
    
    model.addAttribute("listaTipiSpedizionieri", decodificheEJB.getListaTipiSpedizioniere());
    model.addAttribute("tipiProfilo", decodificheEJB.getTipiRuolo());
    model.addAttribute("listaSezioni", decodificheEJB.getListaSezioni());
    model.addAttribute("listaProvince", decodificheEJB.getListaProvince());
    model.addAttribute("listaStatiAzienda", decodificheEJB.getListaStatiAzienda());
    model.addAttribute("statiAzienda", decodificheEJB.getListaStatiAzienda());  
    
    
    if (spedizioniereForm != null) {
      idProvinciaSedeSociale = spedizioniereForm.getIdProvinciaSedeSociale();
      
      List<CentroAziendaleDto> listaCentriAz = decodificheEJB.getListaCentriAziendali(spedizioniereForm.getIdSpedizioniere());
      model.addAttribute("listaCentriAziendali", listaCentriAz);   
    }else if (datiSpedizioniere != null) {
      idProvinciaSedeSociale = datiSpedizioniere.getIdProvinciaSedeSociale();
      
      List<CentroAziendaleDto> listaCentriAz = decodificheEJB.getListaCentriAziendali(datiSpedizioniere.getIdSpedizioniere());
      model.addAttribute("listaCentriAziendali", listaCentriAz);   
    }
    
    if (idProvinciaSedeSociale != null) {
      model.addAttribute("listaComuni", decodificheEJB.getListaComuni(idProvinciaSedeSociale));
    }else {
      model.addAttribute("listaComuni", new ArrayList<CarDComune>());
    }
    
	  	
	
    
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/nuovo")
  public String nuovoSpedizioniere(@ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm,
      Model model) throws BusinessException {

    addModelData(model, spedizioniereForm);
    return "utenti/" + TIPOLOGIA_ATTORI + "/nuovo";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(params = "datiSpedizioniere", value = "/nuovo")
  public String salvaDatiNuovoSpedizioniere(@ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm,
      BindingResult result, Model model, SessionStatus sessionStatus) throws BusinessException {

    validator.validateDatiSpedizioniere(spedizioniereForm, result);

    if (result.getErrorCount() == 0) {
      try {

        Long idUtenteInsert = SecurityUtils.getUtenteLoggato().getId();
        utenteEJB.insertSpedizioniere(spedizioniereForm, idUtenteInsert);

      } catch (BusinessException be) {
        model.addAttribute("page_error", "Errore nel salvataggio dello spedizioniere");
        return nuovoSpedizioniere(spedizioniereForm, model);
      }
      return "redirect:./elenco";
    }

    return nuovoSpedizioniere(spedizioniereForm, model);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/modifica_{idSpedizioniere}")
  public String modificaSpedizioniere(@ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm,
      @PathVariable("idSpedizioniere") Long idSpedizioniere, Model model , HttpSession session) throws BusinessException {

	logger.debug("BEGIN modificaSpedizioniere");  
	logger.debug("-- idSpedizioniere ="+idSpedizioniere);
    SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniere(idSpedizioniere);
    session.setAttribute("idSpedizioniere", idSpedizioniere);

    logger.debug("-- Ricerco l'elenco dei centri aziendali collegati all'idSpedizioniere ="+spedizioniere.getIdSpedizioniere());
    List<CentroAziendaleDto> listaCentriAz = decodificheEJB.getListaCentriAziendali(spedizioniere.getIdSpedizioniere());
    model.addAttribute("listaCentriAziendali", listaCentriAz);        
    
    model.addAttribute("spedizioniere", spedizioniere);
    model.addAttribute("spedizioniereForm", spedizioniere);
    
    addModelData(model, null, spedizioniere);
    
    if (spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO
        && spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE) {

      List<CarDTipoSpedizioniere> lista = decodificheEJB.getListaTipiSpedizioniere();
      lista = lista.stream().filter(l -> l.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE
          && l.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO).collect(Collectors.toList());
      model.addAttribute("listaTipiSpedizionieri", lista);
    }

    return "utenti/" + TIPOLOGIA_ATTORI + "/modifica";
  }

  @PreAuthorize("hasRole('ADMIN')")
 // @PostMapping(params = "datiSpedizioniere", value = "/modifica_{idSpedizioniere}")
  @PostMapping(params = "datiSpedizioniere", value = "/salva")
  public String salvaDati(//@PathVariable("idSpedizioniere") Long idSpedizioniere,
      @ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm, BindingResult result, Model model,
      SessionStatus sessionStatus, HttpSession session) throws BusinessException {

	Long idSpedizioniere = (Long)session.getAttribute("idSpedizioniere");  
	logger.debug("-- idSpedizioniere ="+idSpedizioniere);
    spedizioniereForm.setIdSpedizioniere(idSpedizioniere);
    validator.validateDatiSpedizioniere(spedizioniereForm, result);
    logger.debug("errore " + result.getFieldError("numeroCellulare"));

    if (result.getErrorCount() == 0) {
      try {

        spedizioniereForm.setIdSpedizioniere(idSpedizioniere);
        Long idUtenteUpdate = SecurityUtils.getUtenteLoggato().getId();
        utenteEJB.updateSpedizioniere(spedizioniereForm, idUtenteUpdate);

      } catch (BusinessException be) {

        model.addAttribute("page_error", "Errore nella modifica dello spedizioniere");
        
        SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniere(idSpedizioniere);
        model.addAttribute("spedizioniere", spedizioniere);
        
        addModelData(model, null, spedizioniere);
        
        if (spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO
            && spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE) {

          List<CarDTipoSpedizioniere> lista = decodificheEJB.getListaTipiSpedizioniere();
          lista = lista.stream().filter(l -> l.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE
              && l.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO).collect(Collectors.toList());
          model.addAttribute("listaTipiSpedizionieri", lista);

        }
        
        return "utenti/" + TIPOLOGIA_ATTORI + "/modifica";
      }

      return "redirect:./elenco";
    }

    addModelData(model, spedizioniereForm);
    
    SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniere(idSpedizioniere);
    model.addAttribute("spedizioniere", spedizioniere);
    
    if (spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO
        && spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE) {

      List<CarDTipoSpedizioniere> lista = decodificheEJB.getListaTipiSpedizioniere();
      lista = lista.stream().filter(l -> l.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE
          && l.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO).collect(Collectors.toList());
      model.addAttribute("listaTipiSpedizionieri", lista);
    }
    
    return "utenti/" + TIPOLOGIA_ATTORI + "/modifica";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/dettaglio_{idSpedizioniere}")
  public String dettaglioSpedizioniere(@PathVariable("idSpedizioniere") Long idSpedizioniere, Model model)
      throws BusinessException {
    
    SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniere(idSpedizioniere);
    model.addAttribute("spedizioniere", spedizioniere);
    
    addModelData(model, null, spedizioniere);
    
    logger.debug("-- Ricerco l'elenco dei centri aziendali collegati all'idSpedizioniere ="+idSpedizioniere);
    List<CentroAziendaleDto> listaCentriAz = decodificheEJB.getListaCentriAziendali(idSpedizioniere);
    model.addAttribute("listaCentriAziendali", listaCentriAz);        
    
    
    return "utenti/" + TIPOLOGIA_ATTORI + "/dettaglio";
  }

  @GetMapping(value = "/getListaComuni_{idProvincia}")
  @ResponseBody
  public String getComuni(@PathVariable("idProvincia") Long idProvincia, Model model) throws BusinessException {

    List<CarDComune> listaComuni = new ArrayList<>();
    StringBuilder sb = new StringBuilder("<option value=\"\">Selezionare</option>");
    listaComuni = decodificheEJB.getListaComuni(idProvincia);
    for (CarDComune comune : listaComuni) {
      sb.append("<option value=\"" + comune.getIdComune() + "\">" + comune.getDenomComune() + "</option>");
    }

    return sb.toString();
  }
  
  
  /*
   * Gestione modifica stato centro aziendale
   */
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/attivaCentroAz_{idCentroAziendale}")
  public String attivaCentroAziendale(@ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm, 
		  @PathVariable("idCentroAziendale") Long idCentroAziendale,
		  Model model, HttpSession session) throws BusinessException {
	  logger.debug("BEGIN attivaCentroAziendale");
	  logger.debug("-- idCentroAziendale ="+idCentroAziendale);
	  Long idSpedizioniere = (Long)session.getAttribute("idSpedizioniere");
	  logger.debug("-- idSpedizioniere ="+idSpedizioniere);

	  // Modifico lo stato del centro aziendale
	  logger.debug("-- Modifico lo stato del centro aziendale");
	  utenteEJB.updateStatoCentroAziendale(idCentroAziendale, CaronteConstants.ID_STATO_AZIENDA_ATTIVA);

	  // Setto i dati nel model
	  logger.debug("-- Setto i dati nel model");
	  SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniere(idSpedizioniere);

	  logger.debug("-- Ricerco l'elenco dei centri aziendali collegati all'idSpedizioniere ="+spedizioniere.getIdSpedizioniere());
	  List<CentroAziendaleDto> listaCentriAz = decodificheEJB.getListaCentriAziendali(spedizioniere.getIdSpedizioniere());
	  model.addAttribute("listaCentriAziendali", listaCentriAz);        

	  model.addAttribute("spedizioniere", spedizioniere);
	  model.addAttribute("spedizioniereForm", spedizioniere);

	  addModelData(model, null, spedizioniere);

	  if (spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO
			  && spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE) {

		  List<CarDTipoSpedizioniere> lista = decodificheEJB.getListaTipiSpedizioniere();
		  lista = lista.stream().filter(l -> l.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE
				  && l.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO).collect(Collectors.toList());
		  model.addAttribute("listaTipiSpedizionieri", lista);
	  }
	  return "utenti/" + TIPOLOGIA_ATTORI + "/modifica";	
  }
  
  
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/sospendiCentroAz_{idCentroAziendale}")
  public String sospendiCentroAziendale(@ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm, 
		  @PathVariable("idCentroAziendale") Long idCentroAziendale, 
		  Model model, HttpSession session) throws BusinessException {
	  logger.debug("BEGIN sospendiCentroAziendale");
	  logger.debug("-- idCentroAziendale ="+idCentroAziendale);
	  Long idSpedizioniere = (Long)session.getAttribute("idSpedizioniere");
	  logger.debug("-- idSpedizioniere ="+idSpedizioniere);

	  // Modifico lo stato del centro aziendale
	  logger.debug("-- Modifico lo stato del centro aziendale");
	  utenteEJB.updateStatoCentroAziendale(idCentroAziendale, CaronteConstants.ID_STATO_AZIENDA_SOSPESA);
	  
	  // Setto i dati nel model
	  logger.debug("-- Setto i dati nel model");
	  SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniere(idSpedizioniere);

	  logger.debug("-- Ricerco l'elenco dei centri aziendali collegati all'idSpedizioniere ="+idSpedizioniere);
	  List<CentroAziendaleDto> listaCentriAz = decodificheEJB.getListaCentriAziendali(idSpedizioniere);
	  model.addAttribute("listaCentriAziendali", listaCentriAz);        

	  model.addAttribute("spedizioniere", spedizioniere);
	  model.addAttribute("spedizioniereForm", spedizioniere);

	  addModelData(model, null, spedizioniere);

	  if (spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO
			  && spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE) {

		  List<CarDTipoSpedizioniere> lista = decodificheEJB.getListaTipiSpedizioniere();
		  lista = lista.stream().filter(l -> l.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE
				  && l.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO).collect(Collectors.toList());
		  model.addAttribute("listaTipiSpedizionieri", lista);
	  }
	  return "utenti/" + TIPOLOGIA_ATTORI + "/modifica";
  }
  
  
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/revocaCentroAz_{idCentroAziendale}")
  public String revocaCentroAziendale(@ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm, 
		  @PathVariable("idCentroAziendale") Long idCentroAziendale,
		  Model model, HttpSession session) throws BusinessException {
	  logger.debug("BEGIN revocaCentroAziendale");
	  logger.debug("-- idCentroAziendale ="+idCentroAziendale);
	  Long idSpedizioniere = (Long)session.getAttribute("idSpedizioniere");
	  logger.debug("-- idSpedizioniere ="+idSpedizioniere);

	  // Modifico lo stato del centro aziendale
	  logger.debug("-- Modifico lo stato del centro aziendale");
	  utenteEJB.updateStatoCentroAziendale(idCentroAziendale, CaronteConstants.ID_STATO_AZIENDA_REVOCATA);

	  // Setto i dati nel model
	  logger.debug("-- Setto i dati nel model");	 
	  SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniere(idSpedizioniere);

	  logger.debug("-- Ricerco l'elenco dei centri aziendali collegati all'idSpedizioniere ="+spedizioniere.getIdSpedizioniere());
	  List<CentroAziendaleDto> listaCentriAz = decodificheEJB.getListaCentriAziendali(spedizioniere.getIdSpedizioniere());
	  model.addAttribute("listaCentriAziendali", listaCentriAz);        

	  model.addAttribute("spedizioniere", spedizioniere);
	  model.addAttribute("spedizioniereForm", spedizioniere);

	  addModelData(model, null, spedizioniere);

	  if (spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO
			  && spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE) {

		  List<CarDTipoSpedizioniere> lista = decodificheEJB.getListaTipiSpedizioniere();
		  lista = lista.stream().filter(l -> l.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE
				  && l.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO).collect(Collectors.toList());
		  model.addAttribute("listaTipiSpedizionieri", lista);
	  }
	  return "utenti/" + TIPOLOGIA_ATTORI + "/modifica";
  }
  
  
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/cancellaCentroAz_{idCentroAziendale}")
  public String cancellaCentroAziendale(@ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm,
		  @PathVariable("idCentroAziendale") Long idCentroAziendale,
		  Model model, HttpSession session) throws BusinessException {
	  logger.debug("BEGIN cancellaCentroAziendale");
	  logger.debug("-- idCentroAziendale ="+idCentroAziendale);
	  Long idSpedizioniere = (Long)session.getAttribute("idSpedizioniere");
	  logger.debug("-- idSpedizioniere ="+idSpedizioniere);

	  // Modifico lo stato del centro aziendale
	  logger.debug("-- Modifico lo stato del centro aziendale");
	  utenteEJB.updateStatoCentroAziendale(idCentroAziendale, CaronteConstants.ID_STATO_AZIENDA_CANCELLATA);

	  // Setto i dati nel model
	  logger.debug("-- Setto i dati nel model");
	  SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniere(idSpedizioniere);

	  logger.debug("-- Ricerco l'elenco dei centri aziendali collegati all'idSpedizioniere ="+spedizioniere.getIdSpedizioniere());
	  List<CentroAziendaleDto> listaCentriAz = decodificheEJB.getListaCentriAziendali(spedizioniere.getIdSpedizioniere());
	  model.addAttribute("listaCentriAziendali", listaCentriAz);        

	  model.addAttribute("spedizioniere", spedizioniere);
	  model.addAttribute("spedizioniereForm", spedizioniere);

	  addModelData(model, null, spedizioniere);

	  if (spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO
			  && spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE) {

		  List<CarDTipoSpedizioniere> lista = decodificheEJB.getListaTipiSpedizioniere();
		  lista = lista.stream().filter(l -> l.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE
				  && l.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO).collect(Collectors.toList());
		  model.addAttribute("listaTipiSpedizionieri", lista);
	  }
	  return "utenti/" + TIPOLOGIA_ATTORI + "/modifica";
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/eliminaCentroAz_{idCentroAziendale}")
  public String eliminaCentroAziendale(@ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm,
		  @PathVariable("idCentroAziendale") Long idCentroAziendale,
		  Model model, HttpSession session) throws BusinessException {
	  logger.debug("BEGIN eliminaCentroAziendale");
	  logger.debug("-- idCentroAziendale ="+idCentroAziendale);
	  Long idSpedizioniere = (Long)session.getAttribute("idSpedizioniere");
	  logger.debug("-- idSpedizioniere ="+idSpedizioniere);

	  // Elimino il centro aziendale
	  logger.debug("-- Elimino il centro aziendale");
	  utenteEJB.deleteCentroAziendaleByIdCentroAziendale(idCentroAziendale);

	  // Setto i dati nel model
	  logger.debug("-- Setto i dati nel model");
	  SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniere(idSpedizioniere);

	  logger.debug("-- Ricerco l'elenco dei centri aziendali collegati all'idSpedizioniere ="+spedizioniere.getIdSpedizioniere());
	  List<CentroAziendaleDto> listaCentriAz = decodificheEJB.getListaCentriAziendali(spedizioniere.getIdSpedizioniere());
	  model.addAttribute("listaCentriAziendali", listaCentriAz);        

	  model.addAttribute("spedizioniere", spedizioniere);
	  model.addAttribute("spedizioniereForm", spedizioniere);

	  addModelData(model, null, spedizioniere);

	  if (spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO
			  && spedizioniere.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE) {

		  List<CarDTipoSpedizioniere> lista = decodificheEJB.getListaTipiSpedizioniere();
		  lista = lista.stream().filter(l -> l.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE
				  && l.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO).collect(Collectors.toList());
		  model.addAttribute("listaTipiSpedizionieri", lista);
	  }
	  addSuccessMessage("Centro aziendale eliminato");
	  return "utenti/" + TIPOLOGIA_ATTORI + "/modifica";
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/eliminaSpedizioniere_{idSpedizioniere}")
  public String eliminaSpedizioniere(@ModelAttribute("spedizioniereForm") SpedizioniereForm spedizioniereForm,
		  @PathVariable("idSpedizioniere") Long idSpedizioniere, Model model, HttpSession session) throws BusinessException {
	  logger.debug("BEGIN eliminaSpedizioniere");
	  logger.debug("-- idSpedizioniere =" + idSpedizioniere);

	  // Elimino lo spedizioniere
	  logger.debug("-- Elimino lo spedizioniere");
	  utenteEJB.deleteSpedizioniereByIdSpedizioniere(idSpedizioniere);
	  
	  try {
	      model.addAttribute("listaTipiSpedizionieri", decodificheEJB.getListaTipiSpedizioniere());
	      model.addAttribute("tipiProfilo", decodificheEJB.getTipiRuolo());      
	      model.addAttribute("elencoSpedizionieri", utenteEJB.getElencoSpedizionieri(spedizioniereForm));   
	      model.addAttribute("listaStatiAzienda", decodificheEJB.getListaStatiAzienda());

	    } catch (BusinessException exc) {
	      model.addAttribute("page_error", "Errore nella ricerca.");
	    }    
	  addSuccessMessage("Spedizioniere eliminato");
	  return "utenti/" + TIPOLOGIA_ATTORI + "/elenco";
 
  }



}
