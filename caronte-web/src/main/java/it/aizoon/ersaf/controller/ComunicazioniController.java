package it.aizoon.ersaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.aizoon.ersaf.business.IComunicazioniEJB;
import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IGeneriSpecieEJB;
import it.aizoon.ersaf.business.IManagerInvioMailEJB;
import it.aizoon.ersaf.business.impl.GeneriSpecieEJB;
import it.aizoon.ersaf.dto.ComunicazioneDto;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazione;
import it.aizoon.ersaf.dto.generati.CarTInvioMailUtente;
import it.aizoon.ersaf.excel.ComunicazioneSpecieExcelBuilder;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaComunicazioneForm;
import it.aizoon.ersaf.form.RicercaComunicazioneForm;
import it.aizoon.ersaf.security.SecurityUtils;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.validator.ComunicazioneValidator;

/**
 * @author Ivan Morra
 *
 */

@Controller
@SessionAttributes({ "nuovaComunicazioneForm" })
@RequestMapping(value = { "/vivai/comunicazioni"})
public class ComunicazioniController extends BaseController {

  @Autowired
  private IComunicazioniEJB comunicazioniEJB = null;

  @Autowired
  private IDecodificheEJB decodificheEJB = null;

  @Autowired
  private ComunicazioneValidator validator;
  
  @Autowired
  private IManagerInvioMailEJB managerInvioMailEJB = null;
  
  @Autowired
  private IGeneriSpecieEJB generiSpecieEJB = null;
  
  
  
  @GetMapping(value = "/inviomailmassiva")
  public void invioMailMassiva() throws BusinessException{
	  logger.debug("********************* INIZIO INVIO MAIL MASSIVA CONTROLLER ****************");
	    
	  boolean invio = managerInvioMailEJB.invioMailMassiva();
	  
	  logger.debug("--------- RETURN invioMailMassiva ="+invio);
	  
	  logger.debug("********************* FINE INVIO MAIL MASSIVA ************************");
  }
  

  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/elenco")
  public String cerca(@ModelAttribute("ricercaComunicazioneForm") @Valid RicercaComunicazioneForm form,
      BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request)
      throws BusinessException {

    // Si resettano i form dei cu figli (nuova, modifica....)
    sessionStatus.setComplete();

    try {
      UtenteDTO utente = SecurityUtils.getUtenteLoggato();
      Long idAssociazioneSezione = getSezioneRequest(request);
      
      model.addAttribute("tipiComunicazione", comunicazioniEJB.getTipiComunicazioneByIdUtente(utente.getId(),idAssociazioneSezione));
      model.addAttribute("statiComunicazione", decodificheEJB.getListaStatiComunicazione());      

      if (utente.isSuperUser()) {
        /*
         * Se è un super user, al primo caricamento della pagina verrà proposto
         * "Inoltrata" come stato selezionato della comunicazione
         */
        if (request.getParameter("idStatoComunicazione") == null) {
          form.setIdStatoComunicazione(CaronteConstants.STATO_COMUNICAZIONE_INOLTRATA);
        }
      } else {
        /*
         * Se è un utente normale, può consultare solo le comunicazioni create
         * da lui o da altri utenti dello stesso spedizioniere
         */
        form.setIdUtenteInsert(utente.getId());
      }

      logger.debug("-- Ricerca delle comunicazioni di vivai");
      List<ComunicazioneDto> elencoComunicazioni = comunicazioniEJB.getElencoComunicazioni(form);
      logger.debug("-- Dopo ricerca delle comunicazioni di vivai");
      model.addAttribute("elencoComunicazioni", elencoComunicazioni);

    } catch (BusinessException exc) {
      addErrorMessage("Errore nella ricerca delle comunicazioni");
    }

    return "comunicazioni/elenco";
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/anagrafica/nuova/{id}")
  public String nuova(@PathVariable Long id, @ModelAttribute("nuovaComunicazioneForm") NuovaComunicazioneForm form, Model model,
      HttpServletRequest request) throws BusinessException {
	
	logger.debug("-- idTipoComunicazione selezionato =" + id);
    setDatiNuovaComunicazione(model, form);
    
    //form.setIdTipoComunicazione(CaronteConstants.ID_TIPO_COMUNICAZIONE_SPECIE_ANNUALE);    
    form.setIdTipoComunicazione(id);

    return "comunicazioni/anagrafica/nuova";
  }

  private void setDatiNuovaComunicazione(Model model, NuovaComunicazioneForm form) throws BusinessException {
    UtenteDTO utente = SecurityUtils.getUtenteLoggato();

    if (form.getIdComunicazione() != null) {
      logger.debug("-- form.getIdComunicazione() ="+form.getIdComunicazione());
      ComunicazioneDto comunicazione = comunicazioniEJB.getDettaglioComunicazione(form.getIdComunicazione());

      form.setDatiComunicazione(comunicazione);
    }

    // Se l'utente è un utente Superuser vedrà tutti gli spedizionieri di vivai, altrimenti vedrà solo il suo/suoi spedizionieri
    if (utente.isSuperUser()) {
      logger.debug("-- Caso SuperUser : caricare tutti gli spedizionieri di vivai");  	
      model.addAttribute("listaSpedizionieri",
          decodificheEJB.getListaSpedizionieri(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI));
    }
    else{
    	model.addAttribute("listaSpedizionieri", comunicazioniEJB.getListaSpedizionieriByIdUtente(utente.getId()));
    }

    if (form.getIdSpedizioniere() != null) {
      model.addAttribute("listaCentriAziendali", comunicazioniEJB.getListaCentriAziendali(form.getIdSpedizioniere()));
    }
    
   // form.setDescAutocertificazione(decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_AUTOCERTIFICAZIONE_VIVAI).getValoreCostante());
    
  }

  @ModelAttribute("nuovaComunicazioneForm")
  public NuovaComunicazioneForm getNuovaComunicazioneForm(HttpServletRequest request) {
    NuovaComunicazioneForm form = new NuovaComunicazioneForm();
    UtenteDTO utente = SecurityUtils.getUtenteLoggato();

    if (utente != null) {
      if (!utente.isSuperUser()) {
        form.setIdSpedizioniere(utente.getIdSpedizioniere());
      }
    }
    
    /*try {
        form.setDescAutocertificazione(decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_AUTOCERTIFICAZIONE_VIVAI).getValoreCostante());
      } catch (BusinessException exc) {
        logger.error("Errore nel reperimento dei dati del destinatario pagamento", exc);
      }*/

    return form;
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(value = { "/anagrafica/nuova", "/anagrafica/modifica" })
  public String salvaDatiComunicazione(@ModelAttribute("nuovaComunicazioneForm") NuovaComunicazioneForm form,
      BindingResult result, Model model, HttpServletRequest request, final RedirectAttributes redirectAttrs)
      throws BusinessException {

    validator.validateDatiSpecie(form, result);  
    //validator.validateAutocertificazione(form.isAutocertificazione(), result);

    if (result.getErrorCount() == 0) {    
      if (form.getDenomGenere() == null) {
        addErrorMessage("Selezionare almeno un genere e una specie");
      } 
      else {
        try {
          Long idUtente = SecurityUtils.getUtenteLoggato().getId();
          
          // Gestione dati : specie, numero piante indicate e genere
          String[] idSpecieArr = null;
          if (form.getIdSpecie() != null) {
        	  idSpecieArr = form.getIdSpecie();
        	  logger.debug("-- Numero di specie indicate = "+idSpecieArr.length);
          }          
          String[] numeroPianteArr =  form.getNumeroPiante();
          String[] denomGenereArr = form.getDenomGenere();
          logger.debug("-- Numero di piante indicate = "+numeroPianteArr.length);
          logger.debug("-- Numero di generi indicati = "+denomGenereArr.length); 
          
          List<SpecieDTO> specieList = new ArrayList<SpecieDTO>();  
          for (int i = 0; i < denomGenereArr.length; i++) {
        	  SpecieDTO specie = new SpecieDTO();
        	  List<GenereDTO> listaGeneri = generiSpecieEJB.getListaGeneri(denomGenereArr[i], CaronteConstants.CODICE_LINGUA_LATINO);
		      specie.setIdGenere(listaGeneri.get(0).getIdGenere());
		      logger.debug("-- idGenere ="+specie.getIdGenere());
		      
		      if (idSpecieArr[i] != null) { 
		    	  specie.setIdSpecie(Long. parseLong(idSpecieArr[i]));
		    	  logger.debug("-- idSpecie ="+specie.getIdSpecie());
		      } else {
		    	  specie.setIdSpecie(null);
		      }
		      if(numeroPianteArr[i] != null) {
		    	  specie.setNumeroPiante(Long.parseLong(numeroPianteArr[i]));
		      }
		      logger.debug("-- numeroPiante ="+specie.getNumeroPiante());
			
		      specieList.add(specie);
          }  
            form.setSpecieList(specieList);
          
          
          // Caso di INSERIMENTO
          if (form.getIdComunicazione() == null) {
        	logger.debug("-- Inserimento NUOVA COMUNICAZIONE VIVAI");
            form.setIdComunicazione(comunicazioniEJB.inserisciComunicazione(form, idUtente));

            // addSuccessMessage("Comunicazione creata");
            redirectAttrs.addFlashAttribute("flagNuovaComunicazione", "S");

            return getRedirect("comunicazioni/anagrafica/modifica/" + form.getIdComunicazione(), request);
          } 
          // Caso di MODIFICA
          else {
            if (!checkModificaComunicazione(idUtente, form)) {
              return getRedirect("comunicazioni/elenco", request);
            }

            logger.debug("-- Modifica COMUNICAZIONE VIVAI");
            comunicazioniEJB.aggiornaComunicazione(form, idUtente);

            addSuccessMessage("Comunicazione modificata");
          }

        }
        catch (BusinessException be) {
          addErrorMessage("Errore nel salvataggio della comunicazione");
        }
      }
    }

    setDatiNuovaComunicazione(model, form);

    return getViewNuovaModifica(request);
  }

  private boolean checkModificaComunicazione(Long idUtente, NuovaComunicazioneForm form) throws BusinessException {
    Boolean isUtenteAbilitato = comunicazioniEJB.isComunicazioneModificabile(idUtente, form.getIdComunicazione());

    if (isUtenteAbilitato == null || !isUtenteAbilitato) {
      addErrorMessage("La comunicazione non può essere modificata dall'utente corrente");
      return false;
    }

    return true;
  }

  private String getViewNuovaModifica(HttpServletRequest request) {
    String requestURI = request == null ? "" : request.getRequestURI();

    if (requestURI.contains("/modifica")) {
      return "comunicazioni/anagrafica/modifica";    	
    } else {
      return "comunicazioni/anagrafica/nuova";
    }
  }

  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/dettaglio/{id}")
  public String getDettaglioComunicazione(@PathVariable Long id, Model model, HttpServletRequest request)
      throws BusinessException {
    try {
      ComunicazioneDto comunicazione = comunicazioniEJB.getDettaglioComunicazione(id);

      //comunicazione.setDescAutocertificazione(decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_AUTOCERTIFICAZIONE_VIVAI).getValoreCostante());

      model.addAttribute("dettaglioComunicazione", comunicazione);

    } catch (BusinessException be) {
      addErrorMessage("Errore nel reperimento del dettaglio della comunicazione");
    }

    return "comunicazioni/dettaglio";
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/elimina/{id}")
  public String eliminaComunicazione(@PathVariable Long id, Model model, HttpServletRequest request)
      throws BusinessException {
    try {
      ComunicazioneDto comunicazione = comunicazioniEJB.getDettaglioComunicazione(id);
      String messaggioErrore = null;

      if (!CaronteConstants.STATO_COMUNICAZIONE_BOZZA.equals(comunicazione.getIdStatoComunicazione())) {
        messaggioErrore = "Solo una comunicazione in stato In Bozza può essere eliminata";
      } else {
        UtenteDTO utente = SecurityUtils.getUtenteLoggato();

        if (!utente.isSuperUser() && !utente.getId().equals(comunicazione.getIdUtenteInserimento())) {
          messaggioErrore = "La comunicazione non può essere cancellata dall'utente corrente";
        }
      }

      if (messaggioErrore == null) {
        comunicazioniEJB.cancellaComunicazione(id);
        addSuccessMessage("La comunicazione è stata eliminata");
      } else {
        addErrorMessage(messaggioErrore);
      }

    } catch (BusinessException be) {
      addErrorMessage("Errore nell'eliminazione della comunicazione");
    }

    return getRedirect("comunicazioni/elenco", request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/anagrafica/modifica/{id}")
  public String modifica(@PathVariable Long id, @ModelAttribute("nuovaComunicazioneForm") NuovaComunicazioneForm form,
      Model model, HttpServletRequest request, @ModelAttribute("flagNuovaComunicazione") String flagNuovaComunicazione)
      throws BusinessException {
    String messaggioErrore = null;

    try {
      UtenteDTO utente = SecurityUtils.getUtenteLoggato();

      Boolean isUtenteAbilitato = comunicazioniEJB.isComunicazioneModificabile(utente.getId(), id);

      if (isUtenteAbilitato == null) {
        messaggioErrore = "La comunicazione non può essere modificata dall'utente corrente";
      } else if (!isUtenteAbilitato) {
        /*
         * L'utente non è abilitato a modificare i dati, ma può avanzare lo
         * stato della comunicazione
         */    	 
        return getRedirect("comunicazioni/avanza/" + id, request);
      } else {

        setDatiModificaComunicazione(model, form, id);
        form.setAbilitaInoltra(checkAbilitaStato(utente.getId(), CaronteConstants.STATO_COMUNICAZIONE_INOLTRATA, id));
        form.setAbilitaAnnulla(checkAbilitaStato(utente.getId(), CaronteConstants.STATO_COMUNICAZIONE_ANNULLATA, id));
        form.setAbilitaAutorizza(checkAbilitaStato(utente.getId(), CaronteConstants.STATO_COMUNICAZIONE_CONVALIDATA, id));
        form.setAbilitaRespingi(checkAbilitaStato(utente.getId(), CaronteConstants.STATO_COMUNICAZIONE_RESPINTA, id));

        if (!StringUtils.isEmpty(flagNuovaComunicazione) && form.isAbilitaInoltra()) {
          model.addAttribute("visualizzaModalInoltra", "S");
        }

      }
    } catch (BusinessException be) {
      messaggioErrore = "Errore nella modifica della comunicazione";
    }

    if (messaggioErrore != null) {
      addErrorMessage(messaggioErrore);

      return getRedirect("comunicazioni/elenco", request);
    }

    return "comunicazioni/anagrafica/modifica";
  }

  private void setDatiModificaComunicazione(Model model, NuovaComunicazioneForm form, Long idComunicazione)
      throws BusinessException {

    form.setIdComunicazione(idComunicazione);

    setDatiNuovaComunicazione(model, form);
  }

  private boolean checkAbilitaStato(Long idUtente, Long idStato, Long idComunicazione) throws BusinessException {

    if (idStato == null || idUtente == null || idComunicazione == null) {
      return false;
    }

    List<CarDStatoComunicazione> listaStatiSuccessivi = comunicazioniEJB.getListaStatiComunicazioneSuccessivi(idUtente,
        idComunicazione);
    
    for (CarDStatoComunicazione stato : listaStatiSuccessivi) {
      if (idStato.equals(stato.getIdStatoComunicazione())) {
        return true;
      }
    }

    return false;
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/inoltra/{id}")
  public String inoltraComunicazione(@PathVariable Long id, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_COMUNICAZIONE_INOLTRATA,
        CaronteConstants.DESC_STATO_COMUNICAZIONE_INOLTRATA.toLowerCase());

    return getRedirect("comunicazioni/elenco", request);
  }

  private boolean avanza(Long idComunicazione, Long idStatoSuccessivo, String descStato) {
    return avanza(idComunicazione, idStatoSuccessivo, descStato, null);
  }

  private boolean avanza(Long idComunicazione, Long idStatoSuccessivo, String descStato, String motivazione) {
    String messaggioErrore = null;

    try {
      UtenteDTO utente = SecurityUtils.getUtenteLoggato();

      if (comunicazioniEJB.avanzaStatoComunicazione(idComunicazione, idStatoSuccessivo, utente.getId(), motivazione)) {
        addSuccessMessage("La comunicazione è stata " + descStato);
      } else {
        messaggioErrore = "La comunicazione non può essere " + descStato + " dall'utente corrente";
      }
    } catch (BusinessException be) {
      messaggioErrore = "Errore nell'avanzamento dello stato della comunicazione";
    }

    if (messaggioErrore != null) {
      addErrorMessage(messaggioErrore);
    }

    return messaggioErrore == null;
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/avanza/{id}")
  public String avanzaStatoRichiesta(@PathVariable Long id, Model model, HttpServletRequest request)
      throws BusinessException {
    String messaggioErrore = null;

    try {
      UtenteDTO utente = SecurityUtils.getUtenteLoggato();

      Boolean isUtenteAbilitato = comunicazioniEJB.isComunicazioneModificabile(utente.getId(), id);

      if (isUtenteAbilitato == null) {
        messaggioErrore = "La comunicazione non può essere modificata dall'utente corrente";
      } else {
        boolean abilitaAutorizza = false;
        boolean abilitaRespingi = false;
        boolean abilitaAnnulla = false;

        ComunicazioneDto dettaglioComunicazione = comunicazioniEJB.getDettaglioComunicazione(id);
        //dettaglioComunicazione.setDescAutocertificazione(decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_AUTOCERTIFICAZIONE_VIVAI).getValoreCostante());

        List<CarDStatoComunicazione> listaStatiSuccessivi = comunicazioniEJB
            .getListaStatiComunicazioneSuccessivi(utente.getId(), id);

        for (CarDStatoComunicazione stato : listaStatiSuccessivi) {
          if (CaronteConstants.STATO_COMUNICAZIONE_CONVALIDATA.equals(stato.getIdStatoComunicazione())) {
            abilitaAutorizza = true;
          } else if (CaronteConstants.STATO_COMUNICAZIONE_RESPINTA.equals(stato.getIdStatoComunicazione())) {
            abilitaRespingi = true;
          } else if (CaronteConstants.STATO_COMUNICAZIONE_ANNULLATA.equals(stato.getIdStatoComunicazione())) {
            abilitaAnnulla = true;
          }
        }

        model.addAttribute("abilitaAutorizza", abilitaAutorizza);
        model.addAttribute("abilitaRespingi", abilitaRespingi);
        model.addAttribute("abilitaAnnulla", abilitaAnnulla);

        model.addAttribute("dettaglioComunicazione", dettaglioComunicazione);
      }
    } catch (BusinessException be) {
      messaggioErrore = "Errore nella modifica della comunicazione";
    }

    if (messaggioErrore != null) {
      addErrorMessage(messaggioErrore);
      return getRedirect("comunicazioni/elenco", request);
    }

    return "comunicazioni/avanza";
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/autorizza/{id}")
  public String autorizzaComunicazione(@PathVariable Long id, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_COMUNICAZIONE_CONVALIDATA,
        CaronteConstants.DESC_STATO_COMUNICAZIONE_CONVALIDATA.toLowerCase());

    return getRedirect("comunicazioni/elenco", request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/respingi/{id}")
  public String respingiComunicazione(@PathVariable Long id, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_COMUNICAZIONE_RESPINTA,
        CaronteConstants.DESC_STATO_COMUNICAZIONE_RESPINTA.toLowerCase());

    return getRedirect("comunicazioni/elenco", request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(value = "/respingi/{id}")
  public String respingiComunicazione(@PathVariable Long id,
      @RequestParam(value = "motivazione", required = false) String motivazione, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_COMUNICAZIONE_RESPINTA,
        CaronteConstants.DESC_STATO_COMUNICAZIONE_RESPINTA.toLowerCase(), motivazione);

    return getRedirect("comunicazioni/elenco", request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/annulla/{id}")
  public String annullaComunicazione(@PathVariable Long id, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_COMUNICAZIONE_ANNULLATA,
        CaronteConstants.DESC_STATO_COMUNICAZIONE_ANNULLATA.toLowerCase());

    return getRedirect("comunicazioni/elenco", request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(value = "/annulla/{id}")
  public String annullaComunicazione(@PathVariable Long id,
      @RequestParam(value = "motivazione", required = false) String motivazione, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_COMUNICAZIONE_ANNULLATA,
        CaronteConstants.DESC_STATO_COMUNICAZIONE_ANNULLATA.toLowerCase(), motivazione);

    return getRedirect("comunicazioni/elenco", request);
  }
  
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/copia/{id}")
  public String copiaComunicazione(@PathVariable Long id, Model model, HttpServletRequest request)
      throws BusinessException {
    Long idNuovaComunicazione = null;

    try {
      ComunicazioneDto comunicazione = comunicazioniEJB.getDettaglioComunicazione(id);
      String messaggioErrore = null;

      UtenteDTO utente = SecurityUtils.getUtenteLoggato();

      if (!utente.isSuperUser() && !utente.getId().equals(comunicazione.getIdUtenteInserimento())) {
        messaggioErrore = "La comunicazione non può essere copiata dall'utente corrente";
      }

      if (messaggioErrore == null) {
        idNuovaComunicazione = comunicazioniEJB.copiaComunicazione(id, utente.getId());
        addSuccessMessage("La copia della comunicazione è stata creata");
      } else {
        addErrorMessage(messaggioErrore);
      }

    } catch (BusinessException be) {
      addErrorMessage("Errore nella copia della comunicazione");
    }

    return getRedirect("comunicazioni/anagrafica/modifica/" + idNuovaComunicazione, request);
  }
  
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/excel/{id}")
  public ModelAndView excelComunicazione(@PathVariable Long id, ModelMap model,
      HttpServletRequest request) throws BusinessException {
    try {
      ComunicazioneDto comunicazione = comunicazioniEJB.getDettaglioComunicazione(id);
      logger.debug("-- test Vale");
      if (CaronteConstants.ID_TIPO_COMUNICAZIONE_SPECIE_ANNUALE.equals(comunicazione.getIdTipoComunicazione())) {
        return new ModelAndView("excelComunicazioneSpecieView", "datiComunicazione",
            comunicazione);
      }
    } catch (BusinessException be) {
      addErrorMessage("Errore nello scarico excel della comunicazione");
    }
        
    return new ModelAndView(getRedirect("comunicazioni/elenco", request));
  }
  
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = "/nuova")
	public String nuova(Model model, HttpServletRequest request) throws BusinessException {
		UtenteDTO utente = SecurityUtils.getUtenteLoggato();
		Long idAssociazioneSezione = getSezioneRequest(request);
		logger.debug("-- idAssociazioneSezione =" + idAssociazioneSezione);
		model.addAttribute("listaTipiComunicazioni", comunicazioniEJB.getTipiComunicazioneByIdUtente(utente.getId(), idAssociazioneSezione));

		return "comunicazioni/nuova";
	}

}
