package it.aizoon.ersaf.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import it.aizoon.ersaf.business.IOrganismiNociviEJB;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.GenereSpecieOrgNocivoDTO;
import it.aizoon.ersaf.dto.OrgNocivoGenereSpecieDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaDomandaForm;
import it.aizoon.ersaf.form.NuovoOrganismoNocivoForm;
import it.aizoon.ersaf.form.RicercaOrganismiNociviForm;
import it.aizoon.ersaf.security.SecurityUtils;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.validator.OrganismiNociviValidator;


@Controller
@SessionAttributes({ "nuovoOrganismoNocivoForm" })
@RequestMapping(value = { "/admin/organisminocivi" })
public class OrganismiNociviController extends BaseController {
	
  @Autowired
  private OrganismiNociviValidator validator;

  @Autowired
  private IOrganismiNociviEJB organismiNociviEJB = null;

  @ModelAttribute("nuovoOrganismoNocivoForm")
  public NuovoOrganismoNocivoForm getNuovoOrganismoNocivoForm(HttpServletRequest request) {
	return new NuovoOrganismoNocivoForm();		
  }
  
  @ModelAttribute("ricercaOrganismiNociviForm")
  public RicercaOrganismiNociviForm getRicercaGeneriForm() {
    return new RicercaOrganismiNociviForm();
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/elenco")
  public String cercaON(@ModelAttribute("ricercaOrganismiNociviForm") @Valid RicercaOrganismiNociviForm ricercaOrganismiNociviForm,
	      BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request)throws BusinessException {
    logger.debug("BEGIN cercaON");
    
    // resetto le variabili di sessione
    sessionStatus.setComplete();
    try {
      
    	//logger.debug("ricercaOrganismiNociviForm: " + ricercaOrganismiNociviForm);
    	model.addAttribute("elencoON", organismiNociviEJB.getElencoOrganismiNocivi(ricercaOrganismiNociviForm));
    	
    	model.addAttribute("selectOrganismiNocivi", organismiNociviEJB.getOrganismiNocivi());
    	model.addAttribute("selectTipiON", organismiNociviEJB.getTipiOrganismiNocivi());
    	
    	/*Long idTipoProdotto = 16L; // tipi prodotti che non devono essere quelli di Expor (>=16)
    	model.addAttribute("selectTipiProdotto", organismiNociviEJB.getTipiProdotto(idTipoProdotto));*/  
    	
    } 
    catch (Exception exc) {
      logger.error("-- Exception in cercaON ="+exc.getMessage());
      addErrorMessage("Errore nella ricerca degli organismi nocivi");
      //model.addAttribute("page_error", "Errore nella ricerca degli organismi nocivi");
    }
    finally {
    	logger.debug("END cercaON");
    }
    // Il path della JSP
    return "servizi/organisminocivi/elenco";
  }
  
  
  
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/nuovo")
  public String nuovoOrganismoNocivo(@ModelAttribute("nuovoOrganismoNocivoForm") NuovoOrganismoNocivoForm form, Model model)
      throws BusinessException {
	  form = new NuovoOrganismoNocivoForm();
	 	  
  		model.addAttribute("selectTipiON", organismiNociviEJB.getTipiOrganismiNocivi());
  		model.addAttribute("selectTipiProdotto", organismiNociviEJB.getTipiProdotto(16L));
  		model.addAttribute("selectGenere", organismiNociviEJB.getGeneri());

    return "servizi/organisminocivi/nuovo";
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(params = "datiNuovoOrganismoNocivo", value = "/nuovo")
  public String salvaNuovoOrganismoNocivo(@ModelAttribute("nuovoOrganismoNocivoForm") NuovoOrganismoNocivoForm form, 
	BindingResult result, Model model, HttpServletRequest request) throws BusinessException {

		  /* 
		   *  ********* VALIDAZIONE e INSERIMENTO DATI SUL DB ***********		 
		   *  - i CAMPI SONO TUTTI OBBLIGATORI a parte la Specie
		   *  - controllare che su CAR_D_ZONA_PROTETTA non ci sia ancora un record con i valori selezionati in tutte le combo della pagina
		   *  - controllare che su CAR_D_ORG_NOCIVO non ci sia il codice_eppo e la descrizione indicata indicato
		   *  
		   *  Inserimenti lato db su :
		   *  	INSERT INTO CAR_D_ORG_NOCIVO
		   *  
		   *  - Possibilità di legare l'organismo nocivo ad una zona protetta o no: 
		   *  - se checkano il campo 'Organismo nocivo in zona protetta' : INSERT INTO CAR_D_GRUPPO_ZONA_PROTETTA
		   *    altrimenti in CAR_D_ZONA_PROTETTA lo lego con id_gruppo_zona_protetta = 32
		   *        
		   *	INSERT INTO CAR_D_ZONA_PROTETTA  		  
		   */
		  validator.validateDatiNuovoOrgNocivo(form, result);
		  if (result.getErrorCount() == 0) {
		      try {
		    	  organismiNociviEJB.inserisciNuovoOrganismoNocivo(form);
		    	  addSuccessMessage("Salvataggio dell' organismo nocivo avvenuto con successo");
		      } 
		      catch (BusinessException be) {	    	  
		          addErrorMessage(be.getMessage());
		          logger.error("Errore durante il salvataggio dell' organismo nocivo" + be.getMessage());
		      }
		      catch (Exception ex) {
		          addErrorMessage("Errore durante il salvataggio dell' organismo nocivo");
		          logger.error("Errore durante il salvataggio dell' organismo nocivo" + ex.getMessage());
		      }
		      return getRedirect("admin/organisminocivi/elenco");
		  }
		  else{	
			  addErrorMessage(result.getAllErrors().get(0).getCode());
			  logger.debug("-- NON sono stati superati i controlli di validazione dei dati dell'organismo nocivo :"+result.getFieldError());
			  return getRedirect("admin/organisminocivi/nuovo");
		  }	   
  }
  
  
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/modifica/idOrganismoNocivo/{idOrganismoNocivo}")
  public String modificaOrganismoNocivo(@ModelAttribute("nuovoOrganismoNocivoForm") NuovoOrganismoNocivoForm form, 
		  @PathVariable Long idOrganismoNocivo,  Model model , HttpSession session) throws BusinessException {
	logger.debug("BEGIN modificaOrganismoNocivo");
	
	// Popolo le combo
	model.addAttribute("selectTipiON", organismiNociviEJB.getTipiOrganismiNocivi());
	model.addAttribute("selectTipiProdotto", organismiNociviEJB.getTipiProdotto(16L));
	model.addAttribute("selectGenere", organismiNociviEJB.getGeneri());
	
	// Setto i dati dell'organismo nocivo selezionato
	logger.debug("-- idOrganismoNocivo da modificare ="+idOrganismoNocivo);
	form.setIdOrgNocivo(idOrganismoNocivo);	
	
	//OrgNocivoGenereSpecieDTO datiOrgNoc = organismiNociviEJB.getDatiOrganismoNocivoByIdOrgNoc(idOrganismoNocivo);
	List<GenereSpecieOrgNocivoDTO> elencoGeneriSpeciOrgNocList = organismiNociviEJB.getGenereSpecieOrgNocivoByIdOrgNoc(idOrganismoNocivo);
	
	if (elencoGeneriSpeciOrgNocList.size() > 0) {
		form.setNuovoCodiceEppo(elencoGeneriSpeciOrgNocList.get(0).getCodiceEppo());
		form.setDescrizioneOrganismoNocivo(elencoGeneriSpeciOrgNocList.get(0).getDescrOrgNocivo());
		// se id_gruppo_zona_protetta != 32 (forzato a GENERIC), significa
		// che l'organismo nocivo è legato ad una zona protetta
		if (elencoGeneriSpeciOrgNocList.get(0).getIdGruppoZonaProtetta() != null) {
			if (elencoGeneriSpeciOrgNocList.get(0).getIdGruppoZonaProtetta().longValue() != CaronteConstants.ID_GRUPPO_ZONA_PROTETTA_GENERIC
					.longValue()) {
				logger.debug("-- l'organismo nocivo è legato ad una zona protetta");
				form.setOrgNocInZonaProtetta("S");
			} else {
				logger.debug("-- l'organismo nocivo NON è legato ad una zona protetta");
				form.setOrgNocInZonaProtetta(null);
			}
		}
	}else{
		  CarDOrgNocivo orgNoc = organismiNociviEJB.getOrganismoNocivoByIdOrgNoc(idOrganismoNocivo);
		  form.setNuovoCodiceEppo(orgNoc.getCodiceEppo());
	    form.setDescrizioneOrganismoNocivo(orgNoc.getDescBreve());
		  form.setOrgNocInZonaProtetta(null);
		}
	
	// Elenco dei generi e delle specie legati all'organismo nocivo selezionato (da visualizzare nella tabellina)	
	model.addAttribute("orgNociviList", elencoGeneriSpeciOrgNocList);
    form.setGeneriSpecieOrgNoc(elencoGeneriSpeciOrgNocList);


	logger.debug("END modificaOrganismoNocivo");
    return "servizi/organisminocivi/modifica";
    
  }
  
  @PreAuthorize("hasRole('ADMIN')") 
  @PostMapping(params = "aggiungiGenere", value = "/modifica" )
  public String aggiungiGenereSpecie(@ModelAttribute("nuovoOrganismoNocivoForm") NuovoOrganismoNocivoForm form, 
		  BindingResult result, Model model, HttpServletRequest request) throws BusinessException {
  	
	 	validator.validateAggiungiGenereSpecie(form, result);	 	
 	 	
	 	    if (result.getErrorCount() == 0) {
				if (form.getIdOrgNocivo() != null && form.getIdTipoProdotto() != null && form.getIdGenere() != null) {
					try {
						organismiNociviEJB.aggiunugiGenereSpecieOrgNocivo(form);
						logger.debug("-- idZonaProtetta inserita");
						organismiNociviEJB.updateFineValiditaOrgNocivo(form.getIdOrgNocivo(), null);
						logger.debug("-- fineValidita settato a null");
					} catch (BusinessException be) {
						addErrorMessage(be.getMessage());
						logger.error("Errore durante il salvataggio della modifica organismo nocivo" + be.getMessage());
						return getRedirect("admin/organisminocivi/modifica/idOrganismoNocivo/"+form.getIdOrgNocivo());
					}
				}	
	 	    } else {
	 	    	logger.debug("-- errore di validazione in aggiungi genere specie ");
	 	    	addErrorMessage(result.getAllErrors().get(0).getCode());
	 	    	logger.debug("-- NON sono stati superati i controlli di validazione dei dati dell'organismo nocivo :"+result.getFieldError());	 	   
	 	    	return getRedirect("admin/organisminocivi/modifica/idOrganismoNocivo/"+form.getIdOrgNocivo());
	 	    }
 	 	
 	    
 	    model.addAttribute("selectTipiON", organismiNociviEJB.getTipiOrganismiNocivi());
 		model.addAttribute("selectTipiProdotto", organismiNociviEJB.getTipiProdotto(16L));
 		model.addAttribute("selectGenere", organismiNociviEJB.getGeneri());
 	    
 	    List<GenereSpecieOrgNocivoDTO> elencoGeneriSpeciOrgNocList = organismiNociviEJB.getGenereSpecieOrgNocivoByIdOrgNoc(form.getIdOrgNocivo());
 		model.addAttribute("orgNociviList", elencoGeneriSpeciOrgNocList);
 	    form.setGeneriSpecieOrgNoc(elencoGeneriSpeciOrgNocList);

 	 	puliscoFormOrgNocivoGenereSpecie(form);

 	 	return getRedirect("admin/organisminocivi/modifica/idOrganismoNocivo/"+form.getIdOrgNocivo());
  }	
  
	private void puliscoFormOrgNocivoGenereSpecie(NuovoOrganismoNocivoForm form) {
		form.setIdTipoOrgNocivo(null);
		form.setIdTipoProdotto(null);
		form.setIdGenere(null);
		form.setSpecie(null);
	}
  
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(params = "datiOrganismoNocivo", value = "/modifica")
	public String salvaModificaOrganismoNocivo(
			@ModelAttribute("nuovoOrganismoNocivoForm") NuovoOrganismoNocivoForm form, BindingResult result,
			Model model, HttpServletRequest request) throws BusinessException {

		logger.debug("-- ID_ORG_NOCIVO = " + form.getIdOrgNocivo());
		validator.validateModificaNuovoOrgNocivo(form, result);
		try {
			if (result.getErrorCount() == 0) {
				logger.debug("-- ID_ORG_NOCIVO 2  = " + form.getIdOrgNocivo());
				organismiNociviEJB.salvaModificaOrgNocivo(form);
				logger.debug("-- Modifica Organismo nocivo salvata");
			} else {
				logger.debug("-- errore di validazione in modifica Org nocivo ");
				addErrorMessage(result.getAllErrors().get(0).getCode());
				logger.debug("-- NON sono stati superati i controlli di validazione dei dati dell'organismo nocivo :"
						+ result.getFieldError());
			}
		} catch (BusinessException be) {
			addErrorMessage(be.getMessage());
			logger.error("Errore durante il salvataggio della modifica organismo nocivo" + be.getMessage());
		}

		return getRedirect("admin/organisminocivi/elenco");
	}
  

	  @PreAuthorize("hasRole('ADMIN')")
	  @GetMapping(value = {"/elimina/{id}"})
	  public String cancellaON(@PathVariable Long id, @ModelAttribute("nuovoOrganismoNocivoForm") @Valid NuovoOrganismoNocivoForm form, Model model,
	      SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {

	    logger.debug("BEGIN cancellaON"); 
	    
	    try {
	        logger.debug("sono nel try"); 
	        //Elimino l' organismo nocivo dalla singola zona protetta. 
	        organismiNociviEJB.cancellaFisicoZonaProtettaByIdZonaProtetta(id);
	       //se l' on non ha più generi e specie, setto data_fine a null
	        if (organismiNociviEJB.getGenereSpecieOrgNocivoByIdOrgNoc(form.getIdOrgNocivo()).size() == 0) {
	          organismiNociviEJB.updateFineValiditaOrgNocivo(form.getIdOrgNocivo(), new Date());
	          logger.debug("-- fineValidita settato a " + new Date());
	        }
	      } catch (Exception exc) {
	        model.addAttribute("page_error", "Errore durante l' eliminazione dell'organismo nocivo selezionato.");
	      }
	    // Il path della JSP
	    //return nuovoOrganismoNocivo(form, model);
	    return getRedirect("admin/organisminocivi/modifica/idOrganismoNocivo/" + form.getIdOrgNocivo());
	  }
	  
 
  
  
}