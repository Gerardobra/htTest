package it.aizoon.ersaf.controller;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarTUtente;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.SpedizioniereForm;
import it.aizoon.ersaf.form.UtenteForm;
import it.aizoon.ersaf.validator.UtenteValidator;

/**
 * @author Nicolò Mandrile
 */

@Controller
@RequestMapping(value = { "/admin/utenti/profili"})
public class ProfiliController extends BaseController {

	@Autowired
	private IDecodificheEJB decodificheEJB = null;

	@Autowired
	private IUtenteEJB utenteEJB = null;

	@Autowired
	private UtenteValidator validator;

	private static final String TIPOLOGIA_ATTORE = "profili";
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/elenco")
	public String cerca(@ModelAttribute("utenteForm") @Valid UtenteForm ricercaUtenteForm, BindingResult result,
			Model model, SessionStatus sessionStatus) throws BusinessException {

		sessionStatus.setComplete();
		model.addAttribute("secondaryActiveLink", TIPOLOGIA_ATTORE);

		try {

			addModelData(model);
			//Non voglio più la combo ma un campo libero
			/*
			List<CarTSpedizioniere> spedizionieri = decodificheEJB.getListaSpedizionieri();
			//non mostro gli spedizionieri di tipo: "utente privato" e "ditta individuale"
			spedizionieri=spedizionieri.stream().filter(s -> s.getIdTipoSpedizioniere()!=CaronteConstants.DITTA_INDIVIDUALE && s.getIdTipoSpedizioniere()!=CaronteConstants.UTENTE_PRIVATO).collect(Collectors.toList());
			model.addAttribute("listaSpedizionieri", spedizionieri);
			*/
			List<UtenteDTO> utenti = utenteEJB.getElencoUtenti(ricercaUtenteForm);
			model.addAttribute("elencoUtenti", utenti);

		} catch (BusinessException exc) {
			model.addAttribute("page_error", "Errore nella ricerca degli utenti");
		}

		return "utenti/" + TIPOLOGIA_ATTORE + "/elenco";
	}

	private void addModelData(Model model) throws BusinessException {
		
		//Non mostro più la select ma un campo di testo
		/*
		List<CarTSpedizioniere> spedizionieri = decodificheEJB.getListaSpedizionieri();
		//non mostro gli spedizionieri di tipo: "utente privato" e "ditta individuale"
		spedizionieri=spedizionieri.stream().filter(s -> s.getIdTipoSpedizioniere()!=CaronteConstants.DITTA_INDIVIDUALE && s.getIdTipoSpedizioniere()!=CaronteConstants.UTENTE_PRIVATO).collect(Collectors.toList());
		model.addAttribute("listaSpedizionieri", spedizionieri);
		*/
		model.addAttribute("tipiProfilo", decodificheEJB.getTipiRuolo());
		model.addAttribute("listaSezioni", decodificheEJB.getListaSezioni());

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/nuovo")
	public String nuovoProfilo(@ModelAttribute("utenteForm") UtenteForm nuovoUtenteForm, Model model)
			throws BusinessException {

		addModelData(model);
		return "utenti/" + TIPOLOGIA_ATTORE + "/nuovo";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(params = "datiUtente", value = "/nuovo")
	public String salvaDatiNuovo(@ModelAttribute("utenteForm") UtenteForm nuovoUtenteForm, BindingResult result,
			Model model, SessionStatus sessionStatus) throws BusinessException {

		validator.validateDatiUtente(nuovoUtenteForm, result);

		if (result.getErrorCount() == 0) {
			try {

				utenteEJB.insertUtente(nuovoUtenteForm);

			} catch (BusinessException be) {
				model.addAttribute("page_error", "Errore nel salvataggio dell'utente");
				return nuovoProfilo(nuovoUtenteForm, model);
			}

			return "redirect:./elenco";
		}

		return nuovoProfilo(nuovoUtenteForm, model);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/modifica_{idUtente}")
	public String modificaProfilo(@ModelAttribute("utenteForm") UtenteForm nuovoUtenteForm,
			@PathVariable("idUtente") Long idUtente, Model model) throws BusinessException {

		addModelData(model);
		UtenteDTO utente = utenteEJB.getUtente(idUtente);
		model.addAttribute("utente", utente);
		model.addAttribute("utenteForm", utente);

		return "utenti/" + TIPOLOGIA_ATTORE + "/modifica";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(params = "datiUtente", value = "/modifica_{idUtente}")
	public String salvaDatiModificaProfilo(@PathVariable("idUtente") Long idUtente,
			@ModelAttribute("utenteForm") UtenteForm nuovoUtenteForm, BindingResult result, Model model,
			SessionStatus sessionStatus) throws BusinessException {

		nuovoUtenteForm.setIdUtente(idUtente);
		UtenteDTO utente2 = utenteEJB.getUtente(idUtente);
		nuovoUtenteForm.setIdTipoSpedizioniere(utente2.getIdTipoSpedizioniere());
		validator.validateDatiUtente(nuovoUtenteForm, result);

		if (result.getErrorCount() == 0) {
			try {

				utenteEJB.updateUtente(nuovoUtenteForm);
				
			} catch (BusinessException be) {
				model.addAttribute("page_error", "Errore nella modifica dell'utente");
				addModelData(model);
				model.addAttribute("utente", utenteEJB.getUtente(idUtente));
				return "utenti/" + TIPOLOGIA_ATTORE + "/modifica";
			}

			addSuccessMessage("Salvataggio effettuato con successo");
			
			return "redirect:./elenco";
		}

		addModelData(model);
		model.addAttribute("utente", utenteEJB.getUtente(idUtente));
		
		return "utenti/" + TIPOLOGIA_ATTORE + "/modifica";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/dettaglio_{idUtente}")
	public String dettaglioProfilo(@PathVariable("idUtente") Long idUtente, Model model) throws BusinessException {

		addModelData(model);
		model.addAttribute("utente", utenteEJB.getUtente(idUtente));

		return "utenti/" + TIPOLOGIA_ATTORE + "/dettaglio";
	}

	@GetMapping(value = "/inviaMail_{idUtente}")
	@ResponseBody
	public String inviaMail(@PathVariable("idUtente") Long idUtente, Model model) throws BusinessException {

		UtenteDTO utente = utenteEJB.getUtente(idUtente);

		// scrivo il token nel db con la data di validita SYSDATE ma solo se l'utente
		// non ha ancora la psw
		// se no vuol dire che si è autoregistrato e lo avviso solamente

		String token = "";
		boolean flagAttivazione = false;
		String email = utente.getEmail();
		String nomeCognome = utente.getCognome() + " " + utente.getNome();
		try {

			if (utente.getPassword() == null) {
				// l'utente è stato registrato dall'amministratore - viene attivato e viene
				// inviata la mail col token tramite
				// il quale andrà a impostare la psw per la prima volta
				flagAttivazione = true;

				token = generateToken();
				utente.setDataToken(new Date());
				utente.setToken(token);
				utenteEJB.updateTokenUtente(utente);
			} else {
				// l'utente si è autoregistrato. Ha già una password. Lo informo solamente che è
				// stato attivato. Non deve impostare/modificare la psw, ma solo fare il login.
				token = null;
				flagAttivazione = false;
			}

			utenteEJB.inviaMailRegistrazioneOCambioPassword(email, nomeCognome, token, flagAttivazione);
		} catch (BusinessException be) {
			model.addAttribute("page_error", "Errore");
			return "ERROR";
		}

		return "SUCCESS";
	}

	@GetMapping(value = "/inviaMailReimpostaPsw_{idUtente}")
	@ResponseBody
	public String inviaMailReimpostaPsw(@PathVariable("idUtente") Long idUtente, Model model) throws BusinessException {

		UtenteDTO utente = utenteEJB.getUtente(idUtente);

		String token = "";
		boolean flagAttivazione = false;
		String email = utente.getEmail();
		String nomeCognome = utente.getCognome() + " " + utente.getNome();

		try {
			// scrivo il token nel db con la data di validita SYSDATE
			token = generateToken();
			utente.setDataToken(new Date());
			utente.setToken(token);
			utenteEJB.updateTokenUtente(utente);
			utenteEJB.inviaMailRegistrazioneOCambioPassword(email, nomeCognome, token, flagAttivazione);

		} catch (BusinessException be) {
			model.addAttribute("page_error", "Errore");
			return "ERROR";
		}

		return "SUCCESS";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/resetpassword_{idUtente}")
	public String resetPassword(@ModelAttribute("utenteForm") UtenteForm nuovoUtenteForm, @PathVariable("idUtente") Long idUtente, Model model) throws BusinessException {
		logger.debug("BEGIN resetPassword");
		
		UtenteDTO utente = utenteEJB.getUtente(idUtente);
		model.addAttribute("utente", utente);
		model.addAttribute("utenteForm", utente);

		logger.debug("END resetPassword");
		return "utenti/" + TIPOLOGIA_ATTORE + "/resetpassword";
	}
	
	
		
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(params = "generaNuovaPassword", value = "/resetpassword_{idUtente}")
	public String generaNuovaPassword(@PathVariable("idUtente") Long idUtente, @ModelAttribute("utenteForm") UtenteForm utenteForm, BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN generaNuovaPassword");
		
		logger.debug("--- reset Password per l'idUtente ="+idUtente);
		utenteForm.setIdUtente(idUtente);
		
		// Genero una nuova password di default
		// Regola : almeno 1 minuscola, 1 maiuscola, 1 carattere speciale, 1 numero e che sia di almeno 8 caratteri
		UtenteDTO ut = utenteEJB.getUtente(idUtente);
		char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!#$%&?")).toCharArray();
		String randomStr = RandomStringUtils.random( 8, 0, possibleCharacters.length-1, true, true, possibleCharacters, new SecureRandom() );
		randomStr+="A1!"; // aggiungo sempre in fondo : una lettera maiuscola, un carattere speciale e un numero per essere sicura che rispetti le regole di Caronte
		logger.debug("---- pasword generata random ="+randomStr); 
		utenteForm.setPassword(randomStr);
		
		try {
			CarTUtente utente = utenteEJB.resetPasswordUtente(utenteForm, passwordEncoder);	
			logger.debug("--- password salvata sul db");			
		} 
		catch (BusinessException be) {
			model.addAttribute("page_error", "Errore nella modifica dell'utente");
			addModelData(model);
			model.addAttribute("utente", utenteEJB.getUtente(idUtente));
			return "utenti/" + TIPOLOGIA_ATTORE + "/modifica";
		}

		addSuccessMessage("Password resettata con successo");
			
		UtenteDTO utDTO = utenteEJB.getUtente(idUtente);
		// setto la password da visualizzare nella pagina
		utDTO.setPassword(randomStr);

		model.addAttribute("utente", utDTO);
		model.addAttribute("utenteForm", utDTO);
		
		logger.debug("END generaNuovaPassword");
		return "utenti/" + TIPOLOGIA_ATTORE + "/resetpassword";
		//return getRedirect("admin/utenti/" + TIPOLOGIA_ATTORE + "/resetpassword_"+idUtente, request);
		//return "utenti/" + TIPOLOGIA_ATTORE + "/resetpassword_"+idUtente;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/eliminaUtente_{idUtente}")
	public String eliminaUtente(@ModelAttribute("utenteForm") UtenteForm utenteForm,
			@PathVariable("idUtente") Long idUtente, Model model, HttpSession session) throws BusinessException {
		logger.debug("BEGIN eliminaUtente");
		logger.debug("-- idUtente =" + idUtente);

		// Elimino l'utente
		logger.debug("-- Elimino l'utente");
		utenteEJB.deleteUtenteByIdUtente(idUtente);

		addSuccessMessage("Utente eliminato");
		
		try {

			addModelData(model);
			List<UtenteDTO> utenti = utenteEJB.getElencoUtenti(utenteForm);
			model.addAttribute("elencoUtenti", utenti);

		} catch (BusinessException exc) {
			model.addAttribute("page_error", "Errore nella ricerca degli utenti");
		}

		return "utenti/profili/elenco";

	}

}