package it.aizoon.ersaf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.IspettoreDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarDComune;
import it.aizoon.ersaf.dto.generati.CarDProvincia;
import it.aizoon.ersaf.dto.generati.CarDRuolo;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.IspettoreForm;
import it.aizoon.ersaf.security.SecurityUtils;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.validator.UtenteValidator;

/**
 * @author Nicolò Mandrile
 */

@Controller
@RequestMapping(value = { "/admin/utenti/ispettori" })
public class IspettoriController extends BaseController {

	@Autowired
	private IDecodificheEJB decodificheEJB = null;

	@Autowired
	private IUtenteEJB utenteEJB = null;

	@Autowired
	private UtenteValidator validator;

	private static final String TIPOLOGIA_ATTORI = "ispettori";

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/elenco")
	public String cerca(@ModelAttribute("ispettoreForm") @Valid IspettoreForm ricercaIspettoreForm,
			BindingResult result, Model model, SessionStatus sessionStatus) throws BusinessException {

		sessionStatus.setComplete();
		try {

			addModelData(model);
			model.addAttribute("elencoIspettori", utenteEJB.getElencoIspettori(ricercaIspettoreForm));

		} catch (BusinessException exc) {
			model.addAttribute("page_error", "Errore nella ricerca");
		}

		return "utenti/" + TIPOLOGIA_ATTORI + "/elenco";
	}

	private void addModelData(Model model) throws BusinessException {

		model.addAttribute("secondaryActiveLink", TIPOLOGIA_ATTORI);
		/*
		List<CarTSpedizioniere> spedizionieri = decodificheEJB.getListaSpedizionieri();
		//non mostro gli spedizionieri di tipo: "utente privato" e "ditta individuale"
		
		spedizionieri=spedizionieri.stream().filter(s -> s.getIdTipoSpedizioniere()!=CaronteConstants.DITTA_INDIVIDUALE && s.getIdTipoSpedizioniere()!=CaronteConstants.UTENTE_PRIVATO).collect(Collectors.toList());
		model.addAttribute("listaSpedizionieri", spedizionieri);
		*/
		List<CarDRuolo> tipiRuolo = decodificheEJB.getTipiRuolo();
		// tengo in lista solo SUPERVISORE e AMMINISTRATORE
		tipiRuolo = tipiRuolo.stream().filter(r -> r.getSuperuser()).collect(Collectors.toList());
		model.addAttribute("tipiProfilo", tipiRuolo);
		model.addAttribute("listaSezioni", decodificheEJB.getListaSezioni());
		model.addAttribute("listaNazioni", decodificheEJB.getListaNazioni(false));
		model.addAttribute("listaComuniUfficio", decodificheEJB.getListaComuni());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/nuovo")
	public String nuovoIspettore(@ModelAttribute("ispettoreForm") IspettoreForm nuovoIspettoreForm, Model model)
			throws BusinessException {

		addModelData(model);
		return "utenti/" + TIPOLOGIA_ATTORI + "/nuovo";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(params = "datiIspettore", value = "/nuovo")
	public String salvaDatiNuovoIspettore(@ModelAttribute("ispettoreForm") IspettoreForm nuovoIspettoreForm,
			BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request)
			throws BusinessException {

		validator.validateDatiIspettore(nuovoIspettoreForm, result, decodificheEJB, request, false);

		if (result.getErrorCount() == 0) {
			try {

				Long idUtenteInsert = SecurityUtils.getUtenteLoggato().getId();
				utenteEJB.insertIspettore(nuovoIspettoreForm, idUtenteInsert);

			} catch (BusinessException be) {
				model.addAttribute("page_error", "Errore nel salvataggio dell'ispettore");
				return nuovoIspettore(nuovoIspettoreForm, model);
			}

			return "redirect:./elenco";
		}

		return nuovoIspettore(nuovoIspettoreForm, model);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/modifica_{idIspettore}")
	public String modificaIspettore(@ModelAttribute("ispettoreForm") IspettoreForm nuovoIspettoreForm,
			@PathVariable("idIspettore") Long idIspettore, Model model) throws BusinessException {

		IspettoreDTO ispettore = utenteEJB.getIspettore(idIspettore);
		model.addAttribute("ispettore", ispettore);
		model.addAttribute("ispettoreForm", ispettore);
		addModelData(model);

		return "utenti/" + TIPOLOGIA_ATTORI + "/modifica";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(params = "datiIspettore", value = "/modifica_{idIspettore}")
	public String salvaDatiModificaIspettore(@PathVariable("idIspettore") Long idIspettore,
			@ModelAttribute("ispettoreForm") IspettoreForm nuovoIspettoreForm, BindingResult result, Model model,
			SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {

		nuovoIspettoreForm.setIdUtente(utenteEJB.getIspettore(idIspettore).getIdUtente());
		validator.validateDatiIspettore(nuovoIspettoreForm, result, decodificheEJB, request, true);

		if (result.getErrorCount() == 0) {
			try {

				Long idUtenteUpdate = SecurityUtils.getUtenteLoggato().getId();
				nuovoIspettoreForm.setIdIspettore(idIspettore);
				// se almeno una sezione è attiva allora l'utente rimane abilitato
				if (nuovoIspettoreForm.isSezioneImport() || nuovoIspettoreForm.isSezioneExport() 
						|| nuovoIspettoreForm.isSezioneVivai() || nuovoIspettoreForm.isSezioneAutorizzazioni() || nuovoIspettoreForm.isSezioneControlli()){
					nuovoIspettoreForm.setAbilitato(true);
				}				
				utenteEJB.updateIspettore(nuovoIspettoreForm, idUtenteUpdate);

			} catch (BusinessException be) {
				model.addAttribute("page_error", "Errore nella modifica dell'ispettore");
				addModelData(model);
				model.addAttribute("ispettore", utenteEJB.getIspettore(idIspettore));
				return "utenti/" + TIPOLOGIA_ATTORI + "/modifica";
			}

			addSuccessMessage("I dati sono stati modificati con successo");
			//return "redirect:./elenco";
			return "redirect:./dettaglio_" + idIspettore;
		}

		model.addAttribute("ispettore", utenteEJB.getIspettore(idIspettore));
		addModelData(model);

		return "utenti/" + TIPOLOGIA_ATTORI + "/modifica";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/dettaglio_{idIspettore}")
	public String dettaglioIspettore(@PathVariable("idIspettore") Long idIspettore, Model model)
			throws BusinessException {

		model.addAttribute("ispettore", utenteEJB.getIspettore(idIspettore));
		addModelData(model);
		return "utenti/" + TIPOLOGIA_ATTORI + "/dettaglio";
	}

	/// Metodo per popolare le AUTOCOMPLETE per i comuni
	@PreAuthorize("hasRole('READ')")
	@ResponseBody
	@GetMapping(value = "/getListaComuni")
	public String getComuni(Model model) throws BusinessException {

		List<CarDProvincia> listaProvince = decodificheEJB.getListaProvince();
		List<CarDComune> listaComuni = new ArrayList<>();
		StringBuilder sb = new StringBuilder("[");

		for (CarDProvincia provincia : listaProvince) {
			listaComuni = decodificheEJB.getListaComuni(provincia.getIdProvincia());
			for (CarDComune comune : listaComuni)
				sb.append(
						"{ \"name\" : \"" + comune.getDenomComune() + "\", \"id\": \"" + comune.getIdComune() + "\"},");
		}
		sb.deleteCharAt(sb.length() - 1);// rimuovo ultima virgola
		sb.append("]");

		return sb.toString();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/esportaDatiIspettori")
	public ModelAndView esportaDatiIspettori(@ModelAttribute("ispettoreForm") @Valid IspettoreForm ricercaIspettoreForm,
			ModelMap model, BindingResult result, HttpSession session, HttpServletRequest request)
			throws BusinessException {
		List<IspettoreDTO> elencoIspettori = null;
		List<IspettoreDTO> elencoIspettoriExcel = null;
		List<Long> ids = new ArrayList<>();
		if (result.getErrorCount() == 0) {
			try {
				elencoIspettori =  utenteEJB.getElencoIspettori(ricercaIspettoreForm);
				if(elencoIspettori!=null)
					{
						elencoIspettori.forEach(i -> ids.add(i.getIdIspettore()) );
						elencoIspettoriExcel = utenteEJB.getElencoIspettoriFornituraDati(ids);
					}
								
			} catch (Exception exc) {
				addErrorMessage("Errore nell'esportazione dati richieste");
			}
			
			ModelAndView modelAndView = new ModelAndView("excelDatiIspettoriView", "elenco", elencoIspettoriExcel != null ? elencoIspettoriExcel : new ArrayList<IspettoreDTO>());
			modelAndView.addObject("codTipoRichiesta", getTipoRichiesta(request));
			return modelAndView;
		}

		// Se ci sono stati errori di validazione.. tornero' alla pagina dell'elenco
		return new ModelAndView("utenti/ispettori/elenco");
	}

	@GetMapping(value = "/checkIfUtenteExists_{email}")
	@ResponseBody
	public String checkIfUtenteExists(@PathVariable("email") String email, Model model, HttpServletRequest request){

		try {
			Long idUtente = utenteEJB.getUtenteByEmail(email.replaceAll("&&&", "."));
			UtenteDTO utente = null;
			if(idUtente!=null)
				utente = utenteEJB.getUtente(idUtente);
			if (utente == null) {
				return "NOTEXISTS";
			}else {
				return utente.getNome() + "&&&" + utente.getCognome() + "&&&" + utente.getCodiceFiscale() + 
						"&&&" + utente.getIdRuolo() + "&&&" + utente.getIdSpedizioniere() +
						"&&&" + utente.isSezioneImport()+ "&&&" + utente.isSezioneExport();
			}
		}
		catch (Exception e) {
			return "NOTEXISTS";
		}
	}

}
