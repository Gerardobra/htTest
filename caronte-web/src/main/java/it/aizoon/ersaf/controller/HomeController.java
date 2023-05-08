package it.aizoon.ersaf.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.aizoon.ersaf.business.IComunicazioniEJB;
import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IDomandeEJB;
import it.aizoon.ersaf.business.IRichiesteEJB;
import it.aizoon.ersaf.business.IServiziEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.DomandaDto;
import it.aizoon.ersaf.dto.ProfiloNonAutorizzatoDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarDCostante;
import it.aizoon.ersaf.dto.generati.CarTComunicazione;
import it.aizoon.ersaf.dto.generati.CarTNotifica;
import it.aizoon.ersaf.dto.generati.CarTRichiesta;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.UtenteForm;
import it.aizoon.ersaf.security.SecurityUtils;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;
import it.aizoon.ersaf.validator.UtenteValidator;

@Controller
public class HomeController extends BaseController {

  @Autowired
  private IUtenteEJB utenteEjb = null;

  @Autowired
  private IRichiesteEJB richiesteEJB = null;

  @Autowired
  private IComunicazioniEJB comunicazioniEJB = null;
  
  @Autowired
  private IDomandeEJB domandeEJB = null;
  
  @Autowired
  private IUtenteEJB utenteEJB;

  @Autowired
  private IServiziEJB serviziEJB;
  
  @Autowired
  private IDecodificheEJB decodificheEJB;

  public IUtenteEJB getUtenteEjb() {
    return utenteEjb;
  }

  public IRichiesteEJB getRichiesteEJB() {
    return richiesteEJB;
  }

  @GetMapping(value = "/login")
  public String login(Model model, HttpServletRequest request, HttpSession session) throws BusinessException {
    logger.debug("login()");

    if (session.getAttribute("registrazioneOK") != null) {
      session.removeAttribute("registrazioneOK");
      model.addAttribute("registrazioneOK",
          "La registrazione dell'utente è avvenuta con successo. Attendere una mail di conferma prima di poter effettuare il login.");
    }
    if (session.getAttribute("riassegnazionePswOK") != null) {
      session.removeAttribute("riassegnazionePswOK");

      model.addAttribute("riassegnazionePswOK", "Password impostata con successo! E' necessario effettuare il login");
    }

    return "login";
  }

  @GetMapping(value = "/login-required")
  public String loginRequired(Model model, HttpServletRequest request) throws BusinessException {
    logger.debug("loginRequired()");
    model.addAttribute("requested", true);
    return "login";
  }

  @GetMapping(value = "/login-failed")
  public String loginFailed(Model model, HttpServletRequest request) throws BusinessException {
    logger.debug("loginFailed()");
    
    String messaggioErrore = null;
    Object eccezioneLogin = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
    
    if (eccezioneLogin instanceof Exception) {
      if (eccezioneLogin.getClass().isAssignableFrom(BadCredentialsException.class)) {
        messaggioErrore = "Credenziali fornite non valide";
      }else {
        messaggioErrore = ((Exception)eccezioneLogin).getMessage();
      }
    }
    
    if (StringUtils.isEmpty(messaggioErrore)) {
      messaggioErrore = "Login non riuscito";
    }
    
    model.addAttribute("error", messaggioErrore);
    return "login";
  }

  @GetMapping(value = "/")
  public String root(Model model, HttpSession session) throws BusinessException {
    logger.debug("root()");
    return home(model, session);
  }

  @GetMapping(value = "/accesso-negato")
  public String accessoNegato(Model model) throws BusinessException {
    logger.debug("accessoNegato()");
    return "accesso-negato";
  }

  @GetMapping(value = "/home")
  public String home(Model model, HttpSession session) throws BusinessException {

	  UtenteDTO utente = SecurityUtils.getUtenteLoggato();

		if (utente != null && utente.getId() != null) {

			return "redirect:/dashboard";

		}

    return "homepage";
  }

  @GetMapping(value = "/dashboard")
  public String dashboard(Model model) throws BusinessException {
	  logger.debug("BEGIN dashboard");
	  UtenteDTO utente = SecurityUtils.getUtenteLoggato();
	  Date dataLimite = new DateTime(new Date()).minusDays(CaronteConstants.LIMITE_GIORNI_NOTIFICHE).toDate();
	  
		List<CarTNotifica> listaMessaggi = serviziEJB.getElencoMessaggi().stream()
				.filter(notifica -> (notifica.getFineValidita() == null
						|| !notifica.getFineValidita().before(DateUtils.truncate(new Date(), Calendar.DATE)))
						&& (notifica.getInizioValidita() == null
								|| !notifica.getInizioValidita().after(DateUtils.truncate(new Date(), Calendar.DATE))))
				.filter(notifica -> notifica.getDataInsert().after(dataLimite))
				.collect(Collectors.toList());

		model.addAttribute("listaMessaggi", listaMessaggi);
		
		
		// UTENTE AMMINISTRATORE
		if (CaronteConstants.ID_TIPO_UTENTE_ADMIN.equals(utente.getIdRuolo())) {
			List<ProfiloNonAutorizzatoDTO> elenco = serviziEJB.getUtentiDaAutorizzare().stream().filter(profilo->profilo.getDataInsert().after(dataLimite)).collect(Collectors.toList());
			model.addAttribute("ispettoriDaAutorizzare", elenco.stream()
					.filter(profilo -> !profilo.getIdIspettore().equals(0L)).collect(Collectors.toList()));
			model.addAttribute("spedizionenieriDaAutorizzare", elenco.stream()
					.filter(profilo -> !profilo.getIdSpedizioniere().equals(0L)).collect(Collectors.toList()));
			model.addAttribute("utentiDaAutorizzare",
					elenco.stream().filter(profilo -> !profilo.getIdUtente().equals(0L)).collect(Collectors.toList()));
			model.addAttribute("listaRichiesteRespinte", new ArrayList<CarTRichiesta>());
			model.addAttribute("listaComunicazioniRespinte", new ArrayList<CarTComunicazione>());
			model.addAttribute("listaDomandeRespinte", new ArrayList<DomandaDto>());
			// tutte le domande in stato inoltrata, senza filtrare per idUtente
			model.addAttribute("listaDomandeInoltrate", domandeEJB.getDomandeInoltrate(null));		
		} 
		// ALTRI UTENTI
		else {
			model.addAttribute("ispettoriDaAutorizzare", new ArrayList<ProfiloNonAutorizzatoDTO>());
			model.addAttribute("spedizionenieriDaAutorizzare", new ArrayList<ProfiloNonAutorizzatoDTO>());
			model.addAttribute("utentiDaAutorizzare", new ArrayList<ProfiloNonAutorizzatoDTO>());
			model.addAttribute("listaRichiesteRespinte", richiesteEJB.getRichiesteRespinte(utente.getId()));
			model.addAttribute("listaComunicazioniRespinte", comunicazioniEJB.getComunicazioniRespinte(utente.getId()));
			// le domande respinte dell'utente base
			model.addAttribute("listaDomandeRespinte", domandeEJB.getDomandeRespinte(utente.getId()));
			model.addAttribute("listaDomandeInoltrate", new ArrayList<DomandaDto>());
		}
		
		/*
		 * Controllo per accettazione Policy Privacy :
		 *  Se l'utente non ha ancora accettato la policy (se car_t_utente.data_accettazione_privacy è null)
		 *  o se l'utente ha accettato, ma la data e ora di accettazione è precedente alla data e ora della pubblicazione della policy 
		 *   (car_t_utente.data_accettazione_privacy < car_d_costante.valore_costante con cod_costante = DATA_PUBBL_PRIV)
		 *   -> far comparire una modale che obbliga l'utente ad accettare prima di proseguire
		 */
		boolean accettazRichiesta = false;
		logger.debug("--  Controllo per accettazione Policy Privacy");
		Date dataAccettazPrivacyUtente = utenteEJB.getDataAccettazionePrivacyUtente(utente.getId());
		model.addAttribute("idUtente",utente.getId());
		logger.debug("-- dataAccettazPrivacyUtente ="+dataAccettazPrivacyUtente);
		if(dataAccettazPrivacyUtente == null){
			accettazRichiesta = true;
		}
		else{
			CarDCostante costante = decodificheEJB.getCostante(CaronteConstants.DATA_PUBBL_PRIV);
			if(costante != null){
				String dataPubblicazionePolicy = costante.getValoreCostante();
				logger.debug("-- dataPubblicazionePolicy ="+dataPubblicazionePolicy);
				SimpleDateFormat fromCostant = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					String reformattedStr = myFormat.format(fromCostant.parse(dataPubblicazionePolicy));
					Date dataPubblicazionePolicyDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(reformattedStr);
					logger.debug("-- dataPubblicazionePolicyDate ="+dataPubblicazionePolicyDate);
					if(dataAccettazPrivacyUtente.before(dataPubblicazionePolicyDate)){
						logger.debug("-- dataAccettazPrivacyUtente < dataPubblicazionePolicyDate");
						accettazRichiesta = true;
					}
				} 
				catch (ParseException e) {
					logger.error("-- ParseException ="+e.getMessage());
					return "dashboard";
				}
			}
		}
		logger.debug("-- accettazRichiesta ="+accettazRichiesta);
		if(accettazRichiesta){
			model.addAttribute("obbligoAccettaPolicy","S");
			// Setto il testo della policy privacy da visualizzare nella modale
			String testoPolicy ="";
			logger.debug("-- Setto il testo della policy privacy da visualizzare nella modale");
			CarDCostante costante = decodificheEJB.getCostante(CaronteConstants.TESTO_PRIVACY);
			if(costante != null){
				testoPolicy = costante.getValoreCostante();			
			}
			model.addAttribute("testoPolicy",testoPolicy);
		}
		else{
			model.addAttribute("obbligoAccettaPolicy","N");	
		}		

    return "dashboard";
  }

  @GetMapping(value = "/informazioniProfiloUtente")
  public String informazioniProfiloUtente(Model model) throws BusinessException {

    Long idUtenteCorrente = SecurityUtils.getUtenteLoggato().getId();
    UtenteDTO utente = utenteEJB.getUtente(idUtenteCorrente);
    model.addAttribute("dettaglioUtente", utente);
    return "profilo/dettaglioProfilo";
  }
  
  @GetMapping(value = "/privacy")
  public String privacy(@ModelAttribute("utenteForm") @Valid UtenteForm utenteForm) throws BusinessException {
	Long idUtenteCorrente = SecurityUtils.getUtenteLoggato().getId();
	logger.debug(" -- idUtenteCorrente "+idUtenteCorrente);
	utenteForm.setIdUtente(idUtenteCorrente);
	Date dataAccettazionePrivacy = utenteEJB.getDataAccettazionePrivacyUtente(idUtenteCorrente);		
	logger.debug(" -- dataAccettazionePrivacy "+dataAccettazionePrivacy);
	utenteForm.setDataAccettazionePrivacy(dataAccettazionePrivacy);
	/*if(dataAccettazionePrivacy != null){
		utenteForm.setCheckAccettazionePrivacy("S");		
	}	
	else{
		utenteForm.setCheckAccettazionePrivacy("");
	}
	*/  
    return "privacy/privacyPolicy";
  }
  

  @Autowired
  private UtenteValidator validator;

  @GetMapping(value = "/modificaProfiloUtente")
  public String modificaInformazioniProfiloUtenteGet(@ModelAttribute("utenteForm") @Valid UtenteForm utenteForm,
      BindingResult result, Model model) throws BusinessException {

    prepareModel(model, utenteForm);

    return "profilo/modificaProfilo";
  }

  private void prepareModel(Model model, UtenteForm utenteForm) throws BusinessException {

    Long idUtenteCorrente = SecurityUtils.getUtenteLoggato().getId();

    UtenteDTO utente = utenteEJB.getUtente(idUtenteCorrente);
    utenteForm.setNome(utente.getNome());
    utenteForm.setCognome(utente.getCognome());
    utenteForm.setNumeroTelefonoUtente(utente.getTelefono());
    utenteForm.setCodiceFiscale(utente.getCodiceFiscale());
    utenteForm.setNote(utente.getNote());
    utenteForm.setNumeroCellUtente(utente.getCellulare());
    utenteForm.setEmail(utente.getEmail());

    model.addAttribute("utenteForm", utenteForm);
  }

  @PostMapping(value = "/modificaProfiloUtente")
  public String modificaInformazioniProfiloUtente(@ModelAttribute("utenteForm") @Valid UtenteForm utenteForm,
      BindingResult result, Model model) throws BusinessException {

    Long idUtenteCorrente = SecurityUtils.getUtenteLoggato().getId();

    validator.validaDatiDettaglioUtente(utenteForm, result);

    if (result.getErrorCount() == 0) {
      try {

        utenteForm.setIdUtente(idUtenteCorrente);
        utenteForm.setAbilitato(SecurityUtils.getUtenteLoggato().isAbilitato());
        utenteForm.setRifiutato(SecurityUtils.getUtenteLoggato().isRifiutato());
        
        utenteEJB.updateUtente(utenteForm);

      } catch (BusinessException be) {
        model.addAttribute("page_error", "Errore nella modifica dell'utente");
        prepareModel(model, utenteForm);
        return "profilo/modificaProfilo";
      }

      return informazioniProfiloUtente(model);
    }

    UtenteDTO utente = utenteEJB.getUtente(idUtenteCorrente);
    model.addAttribute("dettaglioUtente", utente);
    return "profilo/modificaProfilo";
  }

}
