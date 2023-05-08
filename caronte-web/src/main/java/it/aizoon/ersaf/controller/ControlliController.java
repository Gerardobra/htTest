package it.aizoon.ersaf.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import it.aizoon.ersaf.bean.form.AllegatiControlliForm;
import it.aizoon.ersaf.business.IControlliEJB;
import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IDomandeEJB;
import it.aizoon.ersaf.business.IGeneriSpecieEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.AllegatoDTO;
import it.aizoon.ersaf.dto.CampioneDTO;
import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.ControlloDTO;
import it.aizoon.ersaf.dto.DomandaDto;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.IspettoreDTO;
import it.aizoon.ersaf.dto.MisuraDTO;
import it.aizoon.ersaf.dto.MisuraUfficialeDTO;
import it.aizoon.ersaf.dto.MonitCofinanziatoDTO;
import it.aizoon.ersaf.dto.OrgNocivoGenereSpecieDTO;
import it.aizoon.ersaf.dto.RequisitiProfessionaliDTO;
import it.aizoon.ersaf.dto.SementeDTO;
import it.aizoon.ersaf.dto.SitoProduzioneDTO;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.TipologiaAttMaterialeDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarDComune;
import it.aizoon.ersaf.dto.generati.CarDConoscenzaProfessionale;
import it.aizoon.ersaf.dto.generati.CarDMateriale;
import it.aizoon.ersaf.dto.generati.CarDMetodoProduzione;
import it.aizoon.ersaf.dto.generati.CarDNormaVerbale;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDProvincia;
import it.aizoon.ersaf.dto.generati.CarDRequisitoProfessionale;
import it.aizoon.ersaf.dto.generati.CarDStrutturaAttrezzatura;
import it.aizoon.ersaf.dto.generati.CarDTipoAttivita;
import it.aizoon.ersaf.dto.generati.CarDTipoIrrigazione;
import it.aizoon.ersaf.dto.generati.CarDTipoRespAzienda;
import it.aizoon.ersaf.dto.generati.CarDTipologiaControllo;
import it.aizoon.ersaf.dto.generati.CarDTipologiaMisura;
import it.aizoon.ersaf.dto.generati.CarDTipologiaSemente;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoOrgNoc;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoReqProf;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoSpecie;
import it.aizoon.ersaf.dto.generati.CarRControlloIspettore;
import it.aizoon.ersaf.dto.generati.CarRControlloNormaVerbale;
import it.aizoon.ersaf.dto.generati.CarRControlloTipologia;
import it.aizoon.ersaf.dto.generati.CarRMisuraIspettore;
import it.aizoon.ersaf.dto.generati.CarRMisuraOrgNoc;
import it.aizoon.ersaf.dto.generati.CarRMisuraTipologia;
import it.aizoon.ersaf.dto.generati.CarTAllegatoControllo;
import it.aizoon.ersaf.dto.generati.CarTCentroAziendale;
import it.aizoon.ersaf.dto.generati.CarTControllo;
import it.aizoon.ersaf.dto.generati.CarTControlloDocumentale;
import it.aizoon.ersaf.dto.generati.CarTControlloFisico;
import it.aizoon.ersaf.dto.generati.CarTControlloIdentita;
import it.aizoon.ersaf.dto.generati.CarTEsito;
import it.aizoon.ersaf.dto.generati.CarTMisuraUfficiale;
import it.aizoon.ersaf.dto.generati.CarTResponsabilePassaporto;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.ModaliForm;
import it.aizoon.ersaf.form.NuovaDomandaForm;
import it.aizoon.ersaf.form.NuovoControlloForm;
import it.aizoon.ersaf.form.RicercaOperatoreForm;
import it.aizoon.ersaf.security.SecurityUtils;
import it.aizoon.ersaf.stampe.StampeManager;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.validator.ControlloValidator;

@SuppressWarnings("unused")
@Controller

@SessionAttributes({ "nuovoControlloForm" })
@RequestMapping(value = { "/controlli" })

public class ControlliController extends BaseController {

	@Autowired
	private IControlliEJB controlliEJB = null;

	@Autowired
	private IDecodificheEJB decodificheEJB = null;

	@Autowired
	private IDomandeEJB domandeEJB = null;

	@Autowired
	private IUtenteEJB utenteEJB = null;

	@Autowired
	private IGeneriSpecieEJB generiSpecieEJB = null;

	@Autowired
	private ControlloValidator validator;

	@ModelAttribute("nuovoControlloForm")
	public NuovoControlloForm getNuovoControlloForm(HttpServletRequest request) {
		NuovoControlloForm form = new NuovoControlloForm();
		return form;
	}

	@PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = "/elenco")
	public String cerca(@ModelAttribute("ricercaOperatoreForm") @Valid RicercaOperatoreForm form, BindingResult result,
			Model model, SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN cerca");
		// Si resettano i form dei cu figli (nuova, modifica....)
		sessionStatus.setComplete();

		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();
			Long idAssociazioneSezione = getSezioneRequest(request);
			form.setIdAssociazioneSezione(idAssociazioneSezione);
			logger.debug("-- idAssociazioneSezione =" + idAssociazioneSezione);

			// Popolo le combo
			model.addAttribute("listaIspettori", decodificheEJB.getListaIspettori());
			model.addAttribute("listaProvince", decodificheEJB.getListaProvince());
			model.addAttribute("listaTipologieAttivita", decodificheEJB.getTipoAttivita());
			model.addAttribute("listaOrganismiNocivi", decodificheEJB.getListaOrganismiNocivi());

			// se il campo genere è stato valorizzato, cerco l'idGenere
			// associato
			if (form != null) {
				if (form.getGenere() != null) {
					Long idGenere = generiSpecieEJB.getIdGenereByDescr(form.getGenere());
					logger.debug("-- idGenere = " + idGenere);
					form.setIdGenere(idGenere);
				}
				if (form.getSpecie() != null && form.getSpecie().size() > 0) {
					List<Integer> specie = form.getSpecie().stream().map(Integer::parseInt)
							.collect(Collectors.toList());
					form.setIdSpecie(specie);
				}
			}

			/*
			 * Controllare il tipo di Utente autenticato : se non è un utente
			 * SuperUser o Amministratore, la ricerca deve restituire solo gli
			 * spedizionieri ai quali è legato l'utente per poter visualizzare i
			 * controlli effettuati presso la propria azienda
			 */
			if (!utente.isSuperUser()) {
				logger.debug("-- L'utente non è un Superuser o Amministratore, filtrare anche per idUtente ="
						+ utente.getId());
				form.setIdUtente(utente.getId());
			}
			/*
			 * Controllare i filtri di ricerca selezionati : - CASO 1
			 * (getElencoOperatoriAttivi) : NESSUN FILTRO O SOLO FILTRO SU :
			 * ISPETTORE, PROVINCIA SEDE LEGALE, RAGIONE SOCIALE, CUAA (non ci
			 * sono filtri su Domande Ruop o Comunicazioni Vivai) - CASI 2
			 * (getElencoOperatori) : ALMENO UN FILTRO TRA : TIPOLOGIA
			 * ATTIVITA', GENERE, SPECIE, ORGANISMO NOCIVO
			 */
			boolean filtriSuDomande = controllaFiltriRicercaOperatori(form);
			logger.debug("-- filtriSuDomande =" + filtriSuDomande);
			List<SpedizioniereDTO> elencoOperatori = null;
			if (filtriSuDomande) {
				elencoOperatori = controlliEJB.getElencoOperatori(form);
			} else {
				// -- lo spedizioniere deve avere almeno un centro aziendale
				// collegato, altrimenti non lo visualizziamo (i controlli
				// vengono collegati ai centri aziendali)
				elencoOperatori = controlliEJB.getElencoOperatoriAttivi(form);
			}
			model.addAttribute("elencoOperatori", elencoOperatori);
		} catch (BusinessException exc) {
			logger.error("BusinessException in cerca getElencoOperatori :" + exc.getMessage());
			addErrorMessage("Errore nella ricerca degli operatori");
		} finally {
			logger.debug("END cerca");
		}
		return "elenco";
	}

	private boolean controllaFiltriRicercaOperatori(RicercaOperatoreForm form) {
		logger.debug("BEGIN controllaFiltriRicercaOperatori");
		boolean filtriSuDomande = false;
		// Controllo se c'è almeno un filtro tra : TIPOLOGIA ATTIVITA', GENERE,
		// SPECIE, ORGANISMO NOCIVO
		if (form.getIdTipoAttivita() != null || form.getIdGenere() != null || form.getIdOrgNocivo() != null) {
			filtriSuDomande = true;
		}
		logger.debug("END controllaFiltriRicercaOperatori");
		return filtriSuDomande;
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = "/azienda/modifica/{id}-{idCa}")
	public String modificaDatiAzienda(@PathVariable Long id, @PathVariable Long idCa,
			@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model, HttpServletRequest request)
			throws BusinessException {
		logger.debug("-- BEGIN modificaDatiAzienda");
		String messaggioErrore = null;
		logger.debug("Setto nel form idCentroAziendale= " + idCa);
		form.setIdCentroAziendale(idCa);

		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();
			// TUTTI GLI SPEDIZIONIERI SONO MODIFICABILI? VERIFICARE SE INSERIRE
			// O MENO IL CONTROLLO
			// Boolean isUtenteAbilitato =
			// domandeEJB.isSpedizioniereModificabile(utente.getId(), id);

			// if (isUtenteAbilitato == null) {
			// messaggioErrore = "La domanda non può essere modificata
			// dall'utente corrente";
			// }
			// else if (!isUtenteAbilitato) {
			// L'utente non è abilitato a modificare i dati, ma può avanzare lo
			// stato della richiesta
			// logger.debug("-- L'utente non è abilitato a modificare i dati, ma
			// può avanzare lo stato della richiesta");
			// return getRedirect("comunicazioni/avanza/" + id, request);
			// }
			// else {
			logger.debug("-- Recupero i dati di dello spedizioniere con id =" + id);

			// --- Dati Tab Azienda
			logger.debug("-- Dati Tab Azienda");
			ControlloDTO domandaAzienda = controlliEJB.getDettaglioAziendaByIdSpedizioniere(id);
			logger.debug("domandaAzienda.getIdSpedizioniere() = " + domandaAzienda.getIdSpedizioniere());
			setDatiAzienda(form, utente, domandaAzienda);

		}
		// }
		catch (Exception e) {
			logger.error("-- Exception in modifica =" + e.getMessage());
			messaggioErrore = "Errore nella modifica dell' azienda";
			throw new BusinessException(e.getMessage());
		}

		if (messaggioErrore != null) {
			addErrorMessage(messaggioErrore);

			return getRedirect("controlli/elenco", request);
		}

		return datiAzienda(form, model, request);

	}

	private void setDatiAzienda(NuovoControlloForm form, UtenteDTO utente, ControlloDTO domandaAzienda)
			throws BusinessException {

		form.setIdSpedizioniere(domandaAzienda.getIdSpedizioniere());
		form.setIdTipoSpedizioniere(domandaAzienda.getIdTipoSpedizioniere());
		form.setCuaa(domandaAzienda.getCuaa()); // cuaa/codice fiscale
		form.setDenomSpedizioniere(domandaAzienda.getDenomSpedizioniere());
		form.setNome(domandaAzienda.getNome());
		form.setCognome(domandaAzienda.getCognome());
		form.setIdComune(domandaAzienda.getIdComune());
		if (domandaAzienda.getIdComune() != null)
			form.setIdProvincia(domandeEJB.getIdProvinciaByIdComune(domandaAzienda.getIdComune()));
		form.setCap(domandaAzienda.getCap());
		form.setIndirizzo(domandaAzienda.getIndirizzo());
		form.setTelefono(domandaAzienda.getTelefono());
		form.setCellulare(domandaAzienda.getCellulare());
		form.setFax(domandaAzienda.getFax());
		form.setEmail(domandaAzienda.getEmail());
		form.setPec(domandaAzienda.getPec());
		form.setPartitaIva(domandaAzienda.getPartitaIva());
		form.setTipoSpedizioniereAltro(domandaAzienda.getTipoSpedizioniereAltro());
		form.setTipologiaAttivita(domandaAzienda.getTipologiaAttivita());
		form.setCodiceFitok(domandaAzienda.getCodiceFitok());

	}

	// Tab Dati azienda
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/azienda/nuova", "/azienda/modifica" })

	public String datiAzienda(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model,
			HttpServletRequest request) throws BusinessException {

		model.addAttribute("azienda", controlliEJB.getDettaglioAziendaByIdSpedizioniere(form.getIdSpedizioniere()));
		model.addAttribute("listaTipiAzienda", decodificheEJB.getListaTipiSpedizioniere());
		model.addAttribute("listaProvinceSedeLegale", decodificheEJB.getListaProvince());
		model.addAttribute("listaCentriAziendali", domandeEJB.getCentriAziendaliByIdSpediz(form.getIdSpedizioniere()));

		if (form.getIdProvincia() != null) {

			model.addAttribute("listaComuniSedeLegale", decodificheEJB.getListaComuni(form.getIdProvincia()));
		}

		logger.debug("++-+ prima di getViewNuovaModifica /azienda/modifica");
		return getViewNuovaModifica("azienda", request);
	}

	// Nuovo controllo - Tab Dati Base -- NM
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "datiBase/nuova", "datiBase/modifica" })
	public String nuovoControllo(@ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm,
			Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN nuovoControllo - dati base");
		UtenteDTO utente = SecurityUtils.getUtenteLoggato();

		// controllo in quale versione siamo
		Long idVersioneControllo = controlliEJB.checkVersioneControllo(nuovoControlloForm.getIdControllo());
		logger.debug("-- idVersioneControllo = " + idVersioneControllo);
		nuovoControlloForm.setIdVersioneControllo(idVersioneControllo);

		Long idCentroAz = nuovoControlloForm.getIdCentroAziendale();

		Long idDomanda = controlliEJB.getMaxIdDomandaValidaByIdCentroAz(idCentroAz);
		/*
		 * if (idDomanda == null) { model.addAttribute("page_error",
		 * "Domanda valida non presente per il centro aziendale selezionato. Impossibile procedere."
		 * ); return "datiBase/domandaInesistente"; }
		 */

		// Recupero dati centro aziendale e spedizioniere
		logger.debug("-- idCentroAz =" + idCentroAz);
		CarTCentroAziendale centroAziendale = domandeEJB.getCentroAziendaleById(idCentroAz);
		SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniere(centroAziendale.getIdSpedizioniere());
		logger.debug("-- idSpedizioniere =" + centroAziendale.getIdSpedizioniere());
		model.addAttribute("centroAziendale", centroAziendale);
		model.addAttribute("spedizioniere", spedizioniere);

		if (centroAziendale.getIdComune() != null) {
			CarDComune comune = decodificheEJB.getComuneByPrimaryKey(centroAziendale.getIdComune());
			model.addAttribute("comuneCentroAziendale", comune.getDenomComune());
			CarDProvincia provincia = decodificheEJB.getProvinciaByIdProv(comune.getIdProvincia());
			model.addAttribute("provinciaCentroAziendale", provincia.getDenomProvincia());
		}
		if (spedizioniere.getIdComune() != null) {
			CarDComune comune = decodificheEJB.getComuneByPrimaryKey(spedizioniere.getIdComune());
			model.addAttribute("comuneSpedizioniere", comune.getDenomComune());
			CarDProvincia provincia = decodificheEJB.getProvinciaByIdProv(comune.getIdProvincia());
			model.addAttribute("provinciaSpedizioniere", provincia.getDenomProvincia());
		}

		List<DomandaDto> elencoIspettori = decodificheEJB.getListaIspettori();
		model.addAttribute("elencoIspettori", elencoIspettori);
		List<CarDNormaVerbale> elencoNorme = decodificheEJB.getListaNormeVerbale();
		model.addAttribute("elencoNorme", elencoNorme);
		List<CarDTipoRespAzienda> elencoTipiRespAzienda = decodificheEJB.getListaTipiRespAzienda();
		model.addAttribute("elencoTipiRespAzienda", elencoTipiRespAzienda);
		// Tipologie controlli di car_d_tipologia_controllo
		List<CarDTipologiaControllo> elencoTipologieControlli = decodificheEJB.getListTipologieControlli();
		model.addAttribute("elencoTipologieControlli", elencoTipologieControlli);

		if (idDomanda != null) {
			logger.debug("-- Cerco le Tipologie Attività e Materiali");
			List<TipologiaAttMaterialeDTO> tipologieAttMaterialidb = controlliEJB
					.getTipologieAttMaterialeDomandeRuop(idDomanda);
			model.addAttribute("tipologieAttDb", tipologieAttMaterialidb);
		}

		// inizio sementi
		// tipologia attivita sementiera
		List<CarDMateriale> elencoMaterialiSementi = decodificheEJB
				.getListaMaterialiByIdTipoAttivita(CaronteConstants.ID_TIPO_ATTIVITA_SEMENTIERA);
		model.addAttribute("elencoMaterialiSementi", elencoMaterialiSementi);
		// tipologia sementi lavorate
		List<CarDTipologiaSemente> elencoSementi = decodificheEJB
				.getListaTipologiaSementiByIdVersioneControllo(nuovoControlloForm.getIdVersioneControllo());
		model.addAttribute("elencoSementi", elencoSementi);

		/*
		 * Recupero le attivita(materiali) dei sementi dal controllo se sono
		 * state gia' inserite(caso modifica) altrimenti recupero quelle
		 * inserite in fase di domanda
		 */
		List<CarDMateriale> materialiSemente = null;
		if (nuovoControlloForm.getIdControllo() != null) {
			materialiSemente = controlliEJB.getAttivitaSementiByIdControllo(nuovoControlloForm.getIdControllo());
			logger.debug("-- cerco sul controllo materialiSemente.size() = " + materialiSemente.size());
		}
		if (materialiSemente == null && idDomanda != null) {
			materialiSemente = controlliEJB.getAttivitaSementiByIdDomanda(idDomanda);
			logger.debug("-- cerco sulla domanda materialiSemente.size() = " + materialiSemente.size());
		}
		if (materialiSemente != null && materialiSemente.size() > 0) {
			logger.debug("-- trovato materialiSemente.size() = " + materialiSemente.size());
			Long[] idMateriale = new Long[materialiSemente.size()];
			int i = 0;
			for (CarDMateriale materiale : materialiSemente) {
				idMateriale[i] = materiale.getIdMateriale();
				i++;
			}
			nuovoControlloForm.setIdsMaterialeSementi(idMateriale);
			// siccome ci sono sementi devo tenere aperto il collapsible
			model.addAttribute("hasSementi", true);

		}
		// List<SementeDTO> sementi =
		// controlliEJB.getSementiByIdControllo(nuovoControlloForm.getIdControllo());
		// nuovoControlloForm.setSementi(sementi);

		// Fine sementi

		// model.addAttribute("abilitaSementi",
		// controlliEJB.checkAbilitaSementi(idCentroAz));
		// logger.debug("+--+check abilita sementi= " +
		// controlliEJB.checkAbilitaSementi(idCentroAz));

		// Setto i campi nel form per sapere quali tab devono essere
		// visualizzati
		setTabDaVisualizzare(nuovoControlloForm);

		logger.debug("-- END nuovoControllo - dati base");
		return getViewNuovaModifica("datiBase", request);
	}

	private void setTabDaVisualizzare(NuovoControlloForm nuovoControlloForm) throws BusinessException {
		logger.debug("BEGIN setTabDaVisualizzare");

		if (nuovoControlloForm.getIdControllo() != null) {
			List<CarRControlloTipologia> tipologie = controlliEJB
					.getControlloTipologiaByIdControllo(nuovoControlloForm.getIdControllo());
			if (tipologie != null && tipologie.size() > 0) {
				logger.debug("-- numero di tipologie controlli presenti sul db =" + tipologie.size());
				boolean tabDocumentale = false;
				boolean tabIdentita = false;
				boolean tabFisico = false;
				for (CarRControlloTipologia tipologia : tipologie) {
					logger.debug("-- idTipologiaControllo =" + tipologia.getIdTipologiaControllo());
					Long idTipologia = tipologia.getIdTipologiaControllo();
					// In base agli inserimenti che vengono effetuati, setto nel
					// form i campi per visualizzare i tab corretti
					if (idTipologia == CaronteConstants.ID_TIPOLOGIA_CONTROLLO_DOCUMENTALE) {
						tabDocumentale = true;
					}
					if (idTipologia == CaronteConstants.ID_TIPOLOGIA_CONTROLLO_IDENTITA) {
						tabIdentita = true;
					}
					if (idTipologia == CaronteConstants.ID_TIPOLOGIA_FISICO) {
						tabFisico = true;
					}
				}
				nuovoControlloForm.setTabDocumentale(tabDocumentale);
				nuovoControlloForm.setTabIdentita(tabIdentita);
				nuovoControlloForm.setTabFisico(tabFisico);
			}
		}

		logger.debug("END setTabDaVisualizzare");
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = { "datiBase/nuova", "datiBase/modifica" })
	public String salvaNuovoControllo(
			@Valid @ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm,
			BindingResult bindResult, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN salvaNuovoControllo");

		Long idCentroAz = nuovoControlloForm.getIdCentroAziendale();

		/*
		 * Long idDomanda =
		 * controlliEJB.getMaxIdDomandaValidaByIdCentroAz(idCentroAz);
		 * 
		 * if (idDomanda == null) { model.addAttribute("page_error",
		 * "Domanda valida non presente per il centro aziendale selezionato. Impossibile procedere."
		 * ); return "datiBase/domandaInesistente"; }
		 */

		// nuovoControlloForm.setIdTipoComunicazione(domanda.getIdTipoComunicazione());
		nuovoControlloForm.setIdTipoComunicazione(CaronteConstants.ID_TIPO_COMUNICAZIONE_CONTROLLO);
		nuovoControlloForm.setIdStatoComunicazione(CaronteConstants.STATO_RICHIESTA_BOZZA);

		// logger.debug("-- attivita
		// nuovoControlloForm.getIdsMaterialeSementi().length =
		// "+nuovoControlloForm.getIdsMaterialeSementi().length);
		// logger.debug("-- sementi
		// nuovoControlloForm.getIdsTipologiaSementi().length =
		// "+nuovoControlloForm.getIdsTipologiaSementi().length);
		// logger.debug("-- quantita
		// nuovoControlloForm.getQuantitaSementi().length =
		// "+nuovoControlloForm.getQuantitaSementi().length);

		validator.validateDatiBaseControllo(nuovoControlloForm, bindResult);

		if (!bindResult.hasErrors()) {

			try {
				Long idUtente = SecurityUtils.getUtenteLoggato().getId();
				if (nuovoControlloForm.getIdControllo() == null) {
					controlliEJB.inserisciNuovoControllo(idCentroAz, nuovoControlloForm, idUtente);
				} else {
					controlliEJB.updateNuovoControllo(idCentroAz, nuovoControlloForm, idUtente);
				}
				addSuccessMessage("Dati generali salvati correttamente.");
				//return redirectPageSucc(nuovoControlloForm, request, CaronteConstants.TAB_DATI_GEBERALI);
			} catch (Exception ex) {
				addErrorMessage("Errore durante il salvataggio del controllo");
				logger.error("Errore durante il salvataggio del controllo " + ex.getMessage());
			}

		} else {
			logger.debug("-- errore di validazione sul campo: " + bindResult.getFieldError().toString());
			if (StringUtils.contains(bindResult.getFieldError().toString(), "sementi")) {
				model.addAttribute("hasErrorsSementi", true);
			}
		}

		logger.debug("-- END salvaNuovoControllo ");
		return nuovoControllo(nuovoControlloForm, model, request);
	}

	/*
	 * Controllo in qualeTtab devo andare, in base alla selezione dei check di
	 * Dati base (Documentale, Identità, Fisico) Se non è stato selezionato
	 * nessuno dei tre tab, si passa sul Tab Allegati
	 */
	private String redirectPageSucc(NuovoControlloForm nuovoControlloForm, HttpServletRequest request,
			String tabChiamante) throws Exception {
		logger.debug("BEGIN redirectPageSucc");

		String redirect = "";
		boolean sceltaTab = false;

		if (tabChiamante.equals(CaronteConstants.TAB_DATI_GEBERALI)) {
			if (nuovoControlloForm.isTabDocumentale()) {
				sceltaTab = true;
				if (isNuovoControllo(request)) {
					redirect = getRedirect("controllodocumentale/nuova", request);
					return redirect;
				} else {
					redirect = getRedirect("controllodocumentale/modifica", request);
					return redirect;
				}
			}
			if (nuovoControlloForm.isTabIdentita()) {
				sceltaTab = true;
				if (isNuovoControllo(request)) {
					redirect = getRedirect("controlloidentita/nuova", request);
					return redirect;
				} else {
					redirect = getRedirect("controlloidentita/modifica", request);
					return redirect;
				}
			}
			if (nuovoControlloForm.isTabFisico()) {
				sceltaTab = true;
				if (isNuovoControllo(request)) {
					redirect = getRedirect("controllofisico/nuova", request);
					return redirect;
				} else {
					redirect = getRedirect("controllofisico/modifica", request);
					return redirect;
				}
			}
		}
		if (tabChiamante.equals(CaronteConstants.TAB_DOCUMENTALE)) {
			if (nuovoControlloForm.isTabIdentita()) {
				sceltaTab = true;
				if (isNuovoControllo(request)) {
					redirect = getRedirect("controlloidentita/nuova", request);
					return redirect;
				} else {
					redirect = getRedirect("controlloidentita/modifica", request);
					return redirect;
				}
			}
			if (nuovoControlloForm.isTabFisico()) {
				sceltaTab = true;
				if (isNuovoControllo(request)) {
					redirect = getRedirect("controllofisico/nuova", request);
					return redirect;
				} else {
					redirect = getRedirect("controllofisico/modifica", request);
					return redirect;
				}
			}
		}
		if (tabChiamante.equals(CaronteConstants.TAB_IDENTITA)) {
			if (nuovoControlloForm.isTabFisico()) {
				sceltaTab = true;
				if (isNuovoControllo(request)) {
					redirect = getRedirect("controllofisico/nuova", request);
					return redirect;
				} else {
					redirect = getRedirect("controllofisico/modifica", request);
					return redirect;
				}
			}
		}

		if (!sceltaTab) {
			if (isNuovoControllo(request)) {
				redirect = getRedirect("allegati/nuova", request);
				return redirect;
			} else {
				redirect = getRedirect("allegati/modifica", request);
				return redirect;
			}
		}

		logger.debug("-- redirect =" + redirect);

		logger.debug("END redirectPageSucc");
		return redirect;

	}

	// Salva Dati Azienda
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = { "/azienda/nuova", "/azienda/modifica" })
	public String salvaDatiAzienda(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, BindingResult result,
			Model model, SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {
		logger.debug("-- Validazione dei dati dell'azienda");

		validator.validateDatiAzienda(form, result);

		if (result.getErrorCount() == 0) {
			logger.debug(
					"-- Non ci sono errori di validazione sui dati dell'azienda, posso procedere con il salvataggio");
			try {
				Long idUtente = SecurityUtils.getUtenteLoggato().getId();
				logger.debug("-- aggiornamento dati in CAR_T_SPEDIZIONIERE");
				logger.debug("--- idSpedizioniere =" + form.getIdSpedizioniere());
				controlliEJB.aggiornaDatiAzienda(form, idUtente);

			} catch (BusinessException be) {
				addErrorMessage("Errore nel salvataggio dei dati azienda");
			}

			logger.debug("Non sono stati superati i controlli di validazione dati azienda");
		}
		return datiAzienda(form, model, request);
	}

	// ----- Tab Controllo Documentale
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/controllodocumentale/nuova", "/controllodocumentale/modifica" })
	public String datiControlloDocumentale(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model,
			HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN datiControlloDocumentale");
		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();

			// TODO
			// DA RIMUOVERE RIGA SOTTO FORZATA PER TESTARE LA MODIFICA TAB
			// CONTROLLO DOCUMENTALE
			// form.setIdControllo(49L);

			if (form.getIdControllo() != null) {
				// Cerco i dati sul db relativi al Controllo Documentale
				CarTControlloDocumentale controlloDoc = controlliEJB
						.getControlloDocumentaleByIdControllo(form.getIdControllo());

				// --- Caso di modifica
				if (controlloDoc != null) {
					logger.debug("-- CASO di modifica Controllo Documentale con idControllo =" + form.getIdControllo());
					form.setIdControlloDocumentale(controlloDoc.getIdControlloDocumentale());
					logger.debug("-- idControlloDocumentale =" + controlloDoc.getIdControlloDocumentale());
					setDatiControlloDocumentale(form, controlloDoc);
				}
				// --- Caso di inserimento
				else {
					logger.debug("-- CASO di inserimento Nuovo Controllo Documentale");
					// Cercare se per l'id_centro_aziendale è presente
					// l'idDomanda
					logger.debug("Cercare se per l'id_centro_aziendale è presente l'idDomanda ="
							+ form.getIdCentroAziendale());
					Long idDomanda = controlliEJB.getMaxIdDomandaValidaByIdCentroAz(form.getIdCentroAziendale());
					// Settare ad 'S' la riga 3.1 e visualizzare il codice ruop
					// nella pagina
					if (idDomanda != null) {
						logger.debug("--- E' presente una domanda RUOP per idCentroAziendale = "
								+ form.getIdCentroAziendale());
						CarTSpedizioniere sped = utenteEJB.getCarTSpedizioniere(form.getIdSpedizioniere());
						if (sped != null) {
							logger.debug("-- Codice ruop =" + sped.getCodiceRuop());
							form.setDescControllo3x1(sped.getCodiceRuop().toUpperCase());
							form.setFlControllo3x1(CaronteConstants.FLAG_S);
						}
					} else {
						logger.debug("--- Non è presente una domanda RUOP per idCentroAziendale = "
								+ form.getIdCentroAziendale());
					}
				}
			} else {
				logger.debug("--- Manca idControllo, non sono stati salvati i dati di Base");
				addErrorMessage("Inserire prima i Dati Generali");
			}
		} catch (Exception exc) {
			addErrorMessage("Errore in fase di caricamento dati Tab Controllo Documentale");
		}
		return getViewNuovaModifica("controllodocumentale", request);
	}

	private void setDatiControlloDocumentale(NuovoControlloForm form, CarTControlloDocumentale controlloDoc)
			throws Exception {
		logger.debug("BEGIN setDatiControlloDocumentale");

		form.setFlControllo3x1(controlloDoc.getFlControllo3x1());
		form.setDescControllo3x1(controlloDoc.getDescControllo3x1());
		form.setFlControllo3x1x1(controlloDoc.getFlControllo3x1x1());
		form.setNoteControllo3x1x1(controlloDoc.getNoteControllo3x1x1());
		form.setFlControllo3x2(controlloDoc.getFlControllo3x2());
		form.setFlControllo3x3(controlloDoc.getFlControllo3x3());
		form.setFlControllo3x3x1(controlloDoc.getFlControllo3x3x1());
		form.setNoteControllo3x3x1(controlloDoc.getNoteControllo3x3x1());
		form.setFlControllo3x4(controlloDoc.getFlControllo3x4());
		form.setFlControllo3x5(controlloDoc.getFlControllo3x5());
		form.setFlControllo3x6(controlloDoc.getFlControllo3x6());
		form.setFlControllo3x7(controlloDoc.getFlControllo3x7());
		form.setFlControllo3x8(controlloDoc.getFlControllo3x8());
		form.setNoteControllo3x8(controlloDoc.getNoteControllo3x8());
		form.setFlControllo3x9(controlloDoc.getFlControllo3x9());
		form.setNoteControllo3x9(controlloDoc.getNoteControllo3x9());
		form.setFlControllo3x10(controlloDoc.getFlControllo3x10());
		form.setFlControllo3x11(controlloDoc.getFlControllo3x11());
		form.setNoteControllo3x11(controlloDoc.getNoteControllo3x11());
		form.setFlControllo3x12(controlloDoc.getFlControllo3x12());
		form.setNoteControllo3x12(controlloDoc.getNoteControllo3x12());
		form.setFlControllo3x13(controlloDoc.getFlControllo3x13());
		form.setNoteControllo3x13(controlloDoc.getNoteControllo3x13());
		form.setFlControllo3x14(controlloDoc.getFlControllo3x14());
		form.setFlControllo3x15(controlloDoc.getFlControllo3x15());
		form.setNoteControllo3x15(controlloDoc.getNoteControllo3x15());
		form.setFlControllo3x16(controlloDoc.getFlControllo3x16());

		logger.debug("END setDatiControlloDocumentale");
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = { "/controllodocumentale/nuova/salva", "/controllodocumentale/modifica/salva" })
	public String salvaDatiControlloDocumentale(
			@Valid @ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm,
			BindingResult bindResult, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN salvaDatiControlloDocumentale");

		Long idControllo = nuovoControlloForm.getIdControllo();
		logger.debug("-- Salvo CONTROLLO DOCUMENTALE per idControllo =" + idControllo);

		// Note : per ora non facciamo validazioni al salva
		try {
			if (nuovoControlloForm.getIdControllo() != null) {
				Long idUtente = SecurityUtils.getUtenteLoggato().getId();
				if (nuovoControlloForm.getIdControlloDocumentale() == null) {
					logger.debug("-- Caso di inserimento NUOVO CONTROLLO DOCUMENTALE");
					controlliEJB.inserisciControlloDocumentale(nuovoControlloForm, idUtente);
				} else {
					logger.debug("-- Caso di aggiornamento CONTROLLO DOCUMENTALE con id_controllo_documentale ="
							+ nuovoControlloForm.getIdControlloDocumentale());
					controlliEJB.updateControlloDocumentale(nuovoControlloForm, idUtente);
				}
				addSuccessMessage("Controllo documentale salvato correttamente.");
			} else {
				addErrorMessage("Inserire prima i Dati Generali");
			}
		//	return redirectPageSucc(nuovoControlloForm, request, "DOCUMENTALE");

		} catch (Exception ex) {
			addErrorMessage("Errore durante il salvataggio del controllo documentale");
			logger.error("Errore durante il salvataggio del controllo documentale" + ex.getMessage());
		} finally {
			logger.debug("-- END salvaDatiControlloDocumentale");
		}

		return datiControlloDocumentale(nuovoControlloForm, model, request);
	}

	// ----- INIZIO Tab Controllo Identità
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/controlloidentita/nuova", "/controlloidentita/modifica" })
	public String datiControlloIdentita(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model,
			HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN datiControlloIdentita");
		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();

			// TODO ALE
			// Rimuovere le 2 righe sotto : forzati valori per testare la
			// modifica tab Controllo Identità
			// form.setIdControllo(48L);
			// form.setIdCentroAziendale(14L);

			// Controllo se è stato salvato il record in CAR_T_CONTROLLO nel Tab
			// Dati Base
			if (form.getIdControllo() != null) {
				logger.debug("-- idControllo =" + form.getIdControllo());

				// --- Caso di modifica
				CarTControlloIdentita controlloIdentita = controlliEJB
						.getControlloIdentitaByIdControllo(form.getIdControllo());
				if (controlloIdentita != null) {
					logger.debug("-- idControlloIdentita =" + form.getIdControlloIdentita());
					form.setIdControlloIdentita(controlloIdentita.getIdControlloIdentita());
					logger.debug("--- CASO di modifica Controllo Identità");
					setDatiControlloIdentita(form, controlloIdentita);
				} else {
					logger.debug("--- CASO di inserimento Controllo Identità");
					// --- Caso di inserimento
					List<SitoProduzioneDTO> sitiProduzioneRuop = null;
					if (form.getIdCentroAziendale() != null) {
						logger.debug("--- E' presente una domanda RUOP per idCentroAziendale = "
								+ form.getIdCentroAziendale() + ", cerco i siti di produzione inseriti in domanda");
						sitiProduzioneRuop = domandeEJB.getSitiProduzioneCentroAz(form.getIdCentroAziendale());
						logger.debug("-- Salvo i siti di produzione della domanda ruop sul controllo");
					}
					// Salvo i siti di produzione della domanda ruop su
					// car_r_controllo_identita_sito, se non sono ancora stati
					// salvati
					if (sitiProduzioneRuop != null && sitiProduzioneRuop.size() > 0) {
						logger.debug(
								"-- Salvo i siti di produzione della domanda ruop su car_r_controllo_identita_sito");
						controlliEJB.inserisciElencoSitiProduzioneRuopControlloIdentita(form, utente.getId(),
								sitiProduzioneRuop);
						form.setListaSitiProdRuop(sitiProduzioneRuop);
					}
				}
			} else {
				logger.debug("--- Manca idControllo, non sono stati salvati i dati di Base");
				addErrorMessage("Inserire prima i Dati Generali");
			}

			// Recupero i Siti di produzione sul db
			logger.debug("-- Recupero i Siti di produzione sul db");
			List<SitoProduzioneDTO> sitiProduzioneControlloId = controlliEJB
					.getSitiProduzioneByIdControlloIdentita(form.getIdControlloIdentita());
			model.addAttribute("sitiProduzioneDb", sitiProduzioneControlloId);

			// Popolo combo Provincia e Comune
			logger.debug("-- Popolo combo Provincia e Comune");
			model.addAttribute("listaProvinceSitoProd", decodificheEJB.getListaProvince());
			if (form.getProvinciaSitoProd() != null)
				model.addAttribute("listaComuniSitoProd", decodificheEJB.getListaComuni(form.getProvinciaSitoProd()));
		} catch (Exception exc) {
			addErrorMessage("Errore in fase di caricamento dati Tab Controllo Identita");
		}
		return getViewNuovaModifica("controlloidentita", request);
	}

	private void setDatiControlloIdentita(NuovoControlloForm form, CarTControlloIdentita controlloId) throws Exception {
		logger.debug("BEGIN setDatiControlloIdentita");
		form.setFlControllo4x1(controlloId.getFlControllo4x1());
		form.setFlControllo4x2(controlloId.getFlControllo4x2());
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(params = "aggiungiSito", value = { "/controlloidentita/nuova", "/controlloidentita/modifica" })
	public String aggiungiSitoProduzione(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form,
			BindingResult result, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN aggiungiSitoProduzione");
		UtenteDTO utente = SecurityUtils.getUtenteLoggato();

		validator.validateAggiungiSitoProdControlloIdentita(form, result);

		if (result.getErrorCount() == 0) {
			model.addAttribute("hasErrorsSitoProdIdentita", false);
			logger.debug(
					"-- Non ci sono errori di validazione sui dati del Sito di produzione, posso procedere con il salvataggio");

			if (form.getIdControllo() != null) {
				logger.debug("--- ***  Salvo sul db i dati del Sito Produzione");
				controlliEJB.inserisciSitoProduzioneControlloIdentita(form, utente.getId());
				addSuccessMessage("Sito produzione aggiunto");
				// pulisco il form di tutti i dati relativi al centro aziendale
				logger.debug("-- Pulisco form sito produzione");
				puliscoFormSitoProduz(form);
			} else {
				logger.debug("--- Manca idControllo, non sono stati salvati i dati di Base");
				addErrorMessage("Inserire prima i Dati Generali");
			}

			// Popolo combo Provincia e Comune
			logger.debug("-- Popolo combo Provincia e Comune");
			model.addAttribute("listaProvinceSitoProd", decodificheEJB.getListaProvince());
			if (form.getProvinciaSitoProd() != null)
				model.addAttribute("listaComuniSitoProd", decodificheEJB.getListaComuni(form.getProvinciaSitoProd()));
		} else {
			// Note : variabile testata nella jsp per aprire il collapsible in
			// caso di errori di validazione
			model.addAttribute("hasErrorsSitoProdIdentita", true);
			logger.debug("-- Errore di validazione in aggiungiSitoProduzione");
		}

		// Ricerco i dati dei siti produzioneper idControlloIdentita
		logger.debug("-- Ricerco i dati dei siti produzioneper idControlloIdentita");
		List<SitoProduzioneDTO> sitiProduzioneControlloId = controlliEJB
				.getSitiProduzioneByIdControlloIdentita(form.getIdControlloIdentita());
		model.addAttribute("sitiProduzioneDb", sitiProduzioneControlloId);

		// Popolo combo Provincia e Comune
		logger.debug("-- Popolo combo Provincia e Comune");
		model.addAttribute("listaProvinceSitoProd", decodificheEJB.getListaProvince());
		if (form.getProvinciaSitoProd() != null)
			model.addAttribute("listaComuniSitoProd", decodificheEJB.getListaComuni(form.getProvinciaSitoProd()));

		logger.debug("END aggiungiSitoProduzione");
		return getViewNuovaModifica("controlloidentita", request);
	}

	private void puliscoFormSitoProduz(NuovoControlloForm form) {
		form.setDescSitoProduzione(null);
		form.setFoglio(null);
		form.setComuneSitoProd(null);
		form.setProvinciaSitoProd(null);
		form.setMappale(null);
		form.setSuperficie(null);
		form.setUbicazione(null);
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/controlloidentita/eliminaSitoProd/{id}",
			"/modifica/controlloidentita/eliminaSitoProd/{id}" })
	public String eliminaSitoProduz(@PathVariable Long id,
			@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model, HttpServletRequest request)
			throws BusinessException {
		logger.debug("BEGIN eliminaSitoProduz");
		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();

			logger.debug("-------- idControlloIdentita =" + form.getIdControlloIdentita());

			logger.debug("-- Elimino sito produzione con id =" + id);
			controlliEJB.eliminaSitoProduzione(id, form.getIdControlloIdentita(), utente.getId());
			addSuccessMessage("Sito Produzione eliminato");

			// Ricerco i dati dei siti produzioneper idControlloIdentita
			logger.debug("-- Ricerco i dati dei siti produzioneper idControlloIdentita");
			List<SitoProduzioneDTO> sitiProduzioneControlloId = controlliEJB
					.getSitiProduzioneByIdControlloIdentita(form.getIdControlloIdentita());
			model.addAttribute("sitiProduzioneDb", sitiProduzioneControlloId);

			// Popolo combo Provincia e Comune
			logger.debug("-- Popolo combo Provincia e Comune");
			model.addAttribute("listaProvinceSitoProd", decodificheEJB.getListaProvince());
			if (form.getProvinciaSitoProd() != null)
				model.addAttribute("listaComuniSitoProd", decodificheEJB.getListaComuni(form.getProvinciaSitoProd()));

		} catch (Exception e) {
			logger.error("-- Exception in eliminaSitoProduz =" + e.getMessage());
			addErrorMessage("Errore nell'eliminazione del Sito produzione");
		} finally {
			logger.debug("END eliminaSitoProduz");
		}
		return getViewNuovaModifica("controlloidentita", request);
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(params = "salvaControlloIdentita", value = { "/controlloidentita/nuova",
			"/controlloidentita/modifica" })
	public String salvaDatiControlloIdentita(
			@Valid @ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm,
			BindingResult bindResult, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN salvaDatiControlloIdentita");

		Long idControllo = nuovoControlloForm.getIdControllo();
		logger.debug("-- Salvo CONTROLLO IDENTITA per idControllo =" + idControllo);

		// Note : per ora non facciamo validazioni al salva
		try {
			if (nuovoControlloForm.getIdControllo() != null) {
				Long idUtente = SecurityUtils.getUtenteLoggato().getId();
				if (nuovoControlloForm.getIdControlloIdentita() == null) {
					logger.debug("-- Caso di inserimento NUOVO CONTROLLO IDENTITA");
					controlliEJB.inserisciControlloIdentita(nuovoControlloForm, idUtente);
				} else {
					logger.debug("-- Caso di aggiornamento CONTROLLO IDENTITA con id_controllo_identita ="
							+ nuovoControlloForm.getIdControlloIdentita());
					controlliEJB.updateControlloIdentita(nuovoControlloForm, idUtente);
				}
				addSuccessMessage("Controllo identità salvato correttamente.");
			} else {
				addErrorMessage("Inserire prima i Dati Generali");
				return getRedirect("controlloidentita/nuova", request);
			}

		//	return redirectPageSucc(nuovoControlloForm, request, CaronteConstants.TAB_IDENTITA);
		} catch (Exception ex) {
			addErrorMessage("Errore durante il salvataggio del controllo identita");
			logger.error("Errore durante il salvataggio del controllo identita" + ex.getMessage());
		} finally {
			logger.debug("-- END salvaDatiControlloIdentita");
		}

		return datiControlloIdentita(nuovoControlloForm, model, request);
	}

	// --- FINE TAB CONTROLLO IDENTITA'

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = "/centroaziendale/modifica/{id}")
	public String modificaCentroAziendale(@PathVariable Long id,
			@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model, HttpServletRequest request)
			throws BusinessException {

		logger.debug("-- BEGIN modificaCentroAziendale");

		UtenteDTO utente = SecurityUtils.getUtenteLoggato();

		logger.debug("-- Dati Tab Centro Aziendale");
		ControlloDTO centriAz = controlliEJB.getDettaglioCentroAzByIdCentroAz(id);
		logger.debug(" -- idCentroAziendale = " + centriAz.getIdCentroAziendale());

		Long idDomanda = controlliEJB.getMaxIdDomandaValidaByIdCentroAz(centriAz.getIdCentroAziendale());
		form.setIdDomanda(idDomanda);

		// Ottengo il max idDomanda in stato inoltrato o convalidato che ha
		// car_t_domanda.id_centro_aziendale_passaporto = idCentroAziendale
		Long idDomandaConPassporto = controlliEJB
				.getMaxIdDomandaByIdCentroAzPassaporto(centriAz.getIdCentroAziendale());
		logger.debug(" -- idDomandaConPassporto = " + idDomandaConPassporto);
		CarTResponsabilePassaporto respPassaporto = null;
		if (idDomandaConPassporto != null) {
			respPassaporto = controlliEJB.getResponsabileFitosanitarioByIdDomanda(idDomandaConPassporto);
			if (respPassaporto != null) {
				centriAz.setIdResponsabilePassaporto(respPassaporto.getIdResponsabilePassaporto());
				centriAz.setNomeRP(respPassaporto.getNome());
				centriAz.setCognomeRP(respPassaporto.getCognome());
				centriAz.setTelefonoRP(respPassaporto.getTelefono());
			}
		}

		setDatiCentroAz(form, respPassaporto, centriAz, model);

		return datiCentroAziendale(form, model, request);

	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/centroaziendale/nuova", "/centroaziendale/modifica" })
	public String datiCentroAziendale(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model,
			HttpServletRequest request) throws BusinessException {

		logger.debug("getIdCentroAziendale() /centroaziendale/modifica = " + form.getIdCentroAziendale());
		model.addAttribute("listaCentriAziendali", domandeEJB.getCentriAziendaliByIdSpediz(form.getIdSpedizioniere()));
		model.addAttribute("listaControlli", controlliEJB.getControlliByIdCentroAziendale(form.getIdCentroAziendale()));

		ControlloDTO centriAz = controlliEJB.getDettaglioCentroAzByIdCentroAz(form.getIdCentroAziendale());

		logger.debug(" -- idCentroAziendale = " + centriAz.getIdCentroAziendale());
		// Ottengo il max idDomanda in stato inoltrato o convalidato che ha
		// car_t_domanda.id_centro_aziendale_passaporto = idCentroAziendale
		Long idDomandaConPassporto = controlliEJB
				.getMaxIdDomandaByIdCentroAzPassaporto(centriAz.getIdCentroAziendale());
		logger.debug(" -- idDomandaConPassporto = " + idDomandaConPassporto);
		CarTResponsabilePassaporto respPassaporto = null;
		if (idDomandaConPassporto != null) {
			respPassaporto = controlliEJB.getResponsabileFitosanitarioByIdDomanda(idDomandaConPassporto);
			if (respPassaporto != null) {
				centriAz.setIdResponsabilePassaporto(respPassaporto.getIdResponsabilePassaporto());
				centriAz.setNomeRP(respPassaporto.getNome());
				centriAz.setCognomeRP(respPassaporto.getCognome());
				centriAz.setTelefonoRP(respPassaporto.getTelefono());
			}
		}
		model.addAttribute("centroAziendale", centriAz);
		setDatiCentroAz(form, respPassaporto, centriAz, model);

		logger.debug("++-+ prima di getViewNuovaModifica /centroaziendale/modifica");

		return getViewNuovaModifica("centroaziendale", request);
	}

	private void setDatiCentroAz(NuovoControlloForm form, CarTResponsabilePassaporto resp, ControlloDTO centriAz,
			Model model) throws BusinessException {
		logger.debug("BEGIN setDatiCentroAz");
		// Dati centro aziendale
		form.setIdCentroAziendale(centriAz.getIdCentroAziendale());
		form.setCodCentroAziendale(centriAz.getCodCentroAziendale());
		form.setIdComuneCa(centriAz.getIdComuneCa());
		form.setIdProvinciaCa(centriAz.getIdProvinciaCa());
		form.setCapCa(centriAz.getCapCa());
		form.setIndirizzoCa(centriAz.getIndirizzoCa());
		form.setFrazioneCa(centriAz.getFrazioneCa());
		form.setTelefonoCa(centriAz.getTelefonoCa());
		form.setCellulareCa(centriAz.getCellulareCa());
		form.setEmailCa(centriAz.getEmailCa());
		form.setPecCa(centriAz.getPecCa());
		form.setIdIspettore(centriAz.getIdIspettore());
		form.setIdTipologiaPassaporto(centriAz.getIdTipologiaPassaporto());
		form.setTariffa(centriAz.getTariffa());

		model.addAttribute("listaIspettori", decodificheEJB.getListaIspettori());
		model.addAttribute("listaTipologiaPassaporto", decodificheEJB.getListaTipologiaPassaporto());

		// Responsabile Passaporto
		if (resp != null) {
			logger.debug("--Esiste il responsabile Passaporto");
			form.setIdResponsabilePassaporto(resp.getIdResponsabilePassaporto());
			form.setCognomeRP(resp.getCognome());
			form.setNomeRP(resp.getNome());
			form.setTelefonoRP(resp.getTelefono());
		}
		logger.debug("END setDatiCentroAz");
	}

	// Salva Dati Centro Aziendale
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = { "/centroaziendale/nuova", "/centroaziendale/modifica" })
	public String salvaDatiCentroAziendale(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form,
			BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request)
			throws BusinessException {
		logger.debug("-- Validazione dei dati del centro aziendale");

		validator.validateDatiCentroAziendale(form, result);

		if (result.getErrorCount() == 0) {
			logger.debug(
					"-- Non ci sono errori di validazione sui dati del centro aziendale, posso procedere con il salvataggio");
			try {
				Long idUtente = SecurityUtils.getUtenteLoggato().getId();
				logger.debug("-- aggiornamento dati in CAR_T_CENTRO_AZIENDALE");
				logger.debug("--- IdCentroAziendale =" + form.getIdCentroAziendale());
				controlliEJB.aggiornaDatiCentroAziendale(form, idUtente);

			} catch (BusinessException be) {
				addErrorMessage("Errore nel salvataggio dei dati centro aziendale");
			}

			logger.debug("Non sono stati superati i controlli di validazione dati centro aziendale");
		}

		return datiCentroAziendale(form, model, request);
	}

	private void setDatiControlloFisico(NuovoControlloForm form, CarTControlloFisico controlloFisico)
			throws BusinessException {
		logger.debug("BEGIN setDatiControlloFisico");
		form.setFlControllo5x1(form.getFlControllo5x1() == null ? controlloFisico.getFlControllo5x1() : form.getFlControllo5x1());
		form.setFlControllo5x2(form.getFlControllo5x2() == null ? controlloFisico.getFlControllo5x2() : form.getFlControllo5x2());
		form.setFlControllo5x4(form.getFlControllo5x4() == null ? controlloFisico.getFlControllo5x4() : form.getFlControllo5x4());
		form.setFlControllo5x5(form.getFlControllo5x5() == null ? controlloFisico.getFlControllo5x5() : form.getFlControllo5x5());
		form.setFlControllo5x6(form.getFlControllo5x6() == null ? controlloFisico.getFlControllo5x6() : form.getFlControllo5x6());
		form.setFlControllo5x7(form.getFlControllo5x7() == null ? controlloFisico.getFlControllo5x7() : form.getFlControllo5x7());
		form.setFlControllo5x8(form.getFlControllo5x8() == null ? controlloFisico.getFlControllo5x8() : form.getFlControllo5x8());
		form.setFlControllo5x9(form.getFlControllo5x9() == null ? controlloFisico.getFlControllo5x9() : form.getFlControllo5x9());
		form.setFlControllo5x10(form.getFlControllo5x10() == null ? controlloFisico.getFlControllo5x10() : form.getFlControllo5x10());
		form.setFlControllo5x11(form.getFlControllo5x11() == null ? controlloFisico.getFlControllo5x11() : form.getFlControllo5x11());
		form.setNoteControllo5x11(form.getNoteControllo5x11() == null ? controlloFisico.getNoteControllo5x11() : form.getNoteControllo5x11()); 
		form.setFlControllo5x12(form.getFlControllo5x12() == null ? controlloFisico.getFlControllo5x12() : form.getFlControllo5x12());
		form.setFlControllo5x13(form.getFlControllo5x13() == null ? controlloFisico.getFlControllo5x13() : form.getFlControllo5x13());
		form.setFlControllo5x14(form.getFlControllo5x14() == null ? controlloFisico.getFlControllo5x14() : form.getFlControllo5x14());
		form.setFlControllo5x15(form.getFlControllo5x15() == null ? controlloFisico.getFlControllo5x15() : form.getFlControllo5x15());
		form.setFlControllo5x16(form.getFlControllo5x16() == null ? controlloFisico.getFlControllo5x16() : form.getFlControllo5x16());
		form.setFlControllo5x17(form.getFlControllo5x17() == null ? controlloFisico.getFlControllo5x17() : form.getFlControllo5x17());
		form.setFlControllo5x18(form.getFlControllo5x18() == null ? controlloFisico.getFlControllo5x18() : form.getFlControllo5x18());
		form.setFlControllo5x19(form.getFlControllo5x19() == null ? controlloFisico.getFlControllo5x19() : form.getFlControllo5x19());
		form.setFlControllo5x20(form.getFlControllo5x20() == null ? controlloFisico.getFlControllo5x20() : form.getFlControllo5x20());
		form.setFlControllo5x21(form.getFlControllo5x21() == null ? controlloFisico.getFlControllo5x21() : form.getFlControllo5x21());
		form.setFlControllo5x22(form.getFlControllo5x22() == null ? controlloFisico.getFlControllo5x22() : form.getFlControllo5x22());
		form.setNoteControllo5x22(form.getNoteControllo5x22() == null ? controlloFisico.getNoteControllo5x22() : form.getNoteControllo5x22());
		form.setFlControllo5x23(form.getFlControllo5x23() == null ? controlloFisico.getFlControllo5x23() : form.getFlControllo5x23());

		logger.debug("-- Recupero i dati salvati in car_r_controllo_fisico_specie");
		List<GenereSpecieDTO> listGenereSpecieDB = controlliEJB
				.getGenereSpecieByIdControlloFisisco(controlloFisico.getIdControlloFisico());
		form.setGenereSpecie(listGenereSpecieDB);

		logger.debug("END setDatiControlloFisico");
	}

	/* TAB CONTROLLO FISICO */

	// Gestione modale Modifica organismo nocivo
	@GetMapping(value = { "/controllofisico/editOrgNocFisico",
			"/controllofisico/editOrgNocFisico/{idControlloFisicoSpecie}" })
	public String modificaOrganismiNocivi(@PathVariable(required = false) Long idControlloFisicoSpecie, Model model,
			@ModelAttribute("modaliForm") ModaliForm form,
			@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm domandaForm, HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
		logger.debug("BEGIN modificaOrganismiNocivi");

		setDatiModaleModificaOrganismiNocivi(model, form, idControlloFisicoSpecie);

		logger.debug("END modificaOrganismiNocivi");
		return "controllofisico/editOrgNocFisico";
	}

	// Gestione salva modale organismo nocivo
	@PostMapping(value = "/controllofisico/editOrgNocFisico")
	public String salvaModificaOrganismiNocivi(Model model,
			@ModelAttribute("nuovoControlloForm") NuovoControlloForm controlloform,
			@ModelAttribute("modaliForm") ModaliForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
		logger.debug("BEGIN salvaModificaOrganismiNocivi");
		UtenteDTO utente = SecurityUtils.getUtenteLoggato();

		// Controllo che sia stata inserita la domanda (dati precendenti)
		if (controlloform.getIdControllo() == null) {
			logger.debug("-- Bloccare l'utente, deve prima inserire i dati del controllo");
			addErrorMessage("Attenzione : inserire prima i dati generali");
			return "controllofisico/editOrgNocFisico";
		}

		if (form.getIdControlloFisicoSpecie() != null) {
			logger.debug("-- elimina/inserisci su car_r_controllo_fisico_specie con IdControlloFisicoSpecie = "
					+ form.getIdControlloFisicoSpecie());
			controlliEJB.salvaModificaOrganismiNocivi(controlloform, form, utente.getId());
			puliscoFormModaleModificaOrganismiNocivi(form);
		} else {
			logger.debug("-- Bloccare l'utente, IdControlloFisicoSpecie non presente");
			return "controllofisico/editOrgNocFisico";
		}

		logger.debug("END salvaModificaOrganismiNocivi");
		return null;
	}

	private void puliscoFormModaleModificaOrganismiNocivi(ModaliForm modaliForm) {
		modaliForm.setIdOrgNociviFisico(null);
	}

	private void setDatiModaleModificaOrganismiNocivi(Model model, ModaliForm form, Long idControlloFisicoSpecie)
			throws BusinessException {
		logger.debug("BEGIN setDatiModaleModificaOrganismiNocivi");

		logger.debug("-- idControlloFisicoSpecie =" + idControlloFisicoSpecie);

		// popolo la combo degli organismi nocivi
		List<CarDOrgNocivo> organismiNocList = decodificheEJB.getListaOrganismiNocivi();
		model.addAttribute("listaOrganismiNociviModale", organismiNocList);

		// Setto hidden
		form.setIdControlloFisicoSpecie(idControlloFisicoSpecie);

		// Setto campi genere e specie in visualizzazione
		CarRControlloFisicoSpecie controlloFisicoSpecie = controlliEJB
				.getControlloFisicoSpecieByIdControlloFisicoSpecie(idControlloFisicoSpecie);
		if (controlloFisicoSpecie != null) {
			// Setto campi da visualizzare Genere e Specie (non modificabili)
			if (controlloFisicoSpecie.getIdGenere() != null) {
				logger.debug("-- controlloFisicoSpecie.getIdGenere() = " + controlloFisicoSpecie.getIdGenere());
				CodeDescriptionDTO codeDescr = generiSpecieEJB.getGenereByIdGenere(controlloFisicoSpecie.getIdGenere());
				form.setDenomGenereFisico(codeDescr.getDescr());
			}
			if (controlloFisicoSpecie.getIdSpecie() != null) {
				logger.debug("-- controlloFisicoSpecie.getIdSpecie() = " + controlloFisicoSpecie.getIdSpecie());
				List<Long> idSpecieList = new ArrayList<Long>();
				idSpecieList.add(controlloFisicoSpecie.getIdSpecie());
				List<SpecieDTO> specieList = generiSpecieEJB.getSpecieGenereByListIdSpecie(idSpecieList);
				String denomSpecieFisico = null;
				if (specieList != null) {
					denomSpecieFisico = specieList.get(0).getDenomSpecie();
				}
				form.setDenomSpecieFisico(denomSpecieFisico);
			}
		}

		logger.debug("BEGIN setDatiModaleModificaOrganismiNocivi");
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(params = "aggiungiSpecie", value = { "controllofisico/nuova/salva", "controllofisico/modifica/salva" })
	public String aggiungiSpecieControlloFisico(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form,
			BindingResult result, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN aggiungiSpecieControlloFisico");
		UtenteDTO utente = SecurityUtils.getUtenteLoggato();
		validator.validateSpecieControlloFisico(form, result);

		// mi recupero l'id del genere selezionato
		if (result.getErrorCount() == 0) {
			logger.debug("-- form.getGenereFisico() = " + form.getGenereFisico());
			if (form.getGenereFisico() != null) {
				List<GenereDTO> listaGeneri = generiSpecieEJB.getListaGeneri(form.getGenereFisico(),
						CaronteConstants.CODICE_LINGUA_LATINO);
				form.setIdGenereFisico(listaGeneri.get(0).getIdGenere());
			}
			controlliEJB.aggiungiGenereSpecieOrgNocFisico(form, utente.getId());
			puliscoFormSpecieFisico(form);
		} else {
			addErrorMessage("Indicare almeno una Specie");
		}
		
		
		if (form.getIdControllo() != null) {
			CarTControlloFisico controlloFisico = controlliEJB.getControlloFisicoByIdControllo(form.getIdControllo());
			if (controlloFisico != null) {
				setDatiControlloFisico(form, controlloFisico);
			}
		}

		if (isNuovoControllo(request)) {
			logger.debug("-- END aggiungiSpecieControlloFisico");
			return getRedirect("controllofisico/nuova", request);
		} else {
			logger.debug("-- END aggiungiSpecieControlloFisico");
			return getRedirect("controllofisico/modifica", request);
		}
		//return getViewNuovaModifica("controllofisico", request);

	}

	private void puliscoFormSpecieFisico(NuovoControlloForm form) {
		form.setSpecieFisico(null);
		form.setGenereFisico(null);
		form.setOrgNociviFisico(null);
		form.setIdGenereFisico(null);
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "controllofisico/nuova", "controllofisico/modifica" })
	public String controlloFisico(@ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm,
			Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN - controlloFisico");

		if (nuovoControlloForm.getIdControllo() != null) {

			List<CarDStrutturaAttrezzatura> listaStrutturaAttrezzatura = decodificheEJB.getListaStrutturaAttrezzatura();
			List<CarDMetodoProduzione> listaMetodiProduzione = decodificheEJB.getListaMetodiProduzione();
			List<CarDTipoIrrigazione> listaTipoIrrigazione = decodificheEJB.getListaTipoIrrigazione();
			List<CarDConoscenzaProfessionale> listaConoscenzaProfessionale = decodificheEJB
					.getListaConoscenzaProfessionale();
			List<CarDRequisitoProfessionale> listaRequisitoProfessionale = decodificheEJB
					.getListaRequisitoProfessionale();

			model.addAttribute("listaStrutturaAttrezzatura", listaStrutturaAttrezzatura);
			model.addAttribute("listaMetodiProduzione", listaMetodiProduzione);
			model.addAttribute("listaTipoIrrigazione", listaTipoIrrigazione);
			model.addAttribute("listaConoscenzaProfessionale", listaConoscenzaProfessionale);
			model.addAttribute("listaRequisitoProfessionale", listaRequisitoProfessionale);

			List<CarDOrgNocivo> organismiNocList = decodificheEJB.getListaOrganismiNocivi();
			// Carico la combo Organismo nocivo
			logger.debug("-- Carico la combo Organismi nocivi trovati");
			model.addAttribute("listaOrgNocGenereSpecie", organismiNocList);

			// Carico la combo Organismo nocivo da ricercare
			logger.debug("-- Carico la combo Organismo nocivo");
			model.addAttribute("listaOrgNoc", organismiNocList);

			model.addAttribute("abilitaSementi",
					controlliEJB.checkAbilitaSementi(nuovoControlloForm.getIdCentroAziendale()));
			logger.debug("+--+check abilita sementi= "
					+ controlliEJB.checkAbilitaSementi(nuovoControlloForm.getIdCentroAziendale()));

			/* parte per la modifica */
			CarTControlloFisico controlloFisico = controlliEJB
					.getControlloFisicoByIdControllo(nuovoControlloForm.getIdControllo());
			if (controlloFisico != null) {
				// sono in modifica, esiste gia un controllo fisico
				nuovoControlloForm.setIdControlloFisico(controlloFisico.getIdControlloFisico());
				logger.debug("-- siamo in modifica idControlloFisico: " + controlloFisico.getIdControlloFisico());
				setDatiControlloFisico(nuovoControlloForm, controlloFisico);
				logger.debug("-- Recupero i dati di struttura attrezzatura da DB");
				String[] idsStutturaAtt = controlliEJB
						.getStrutturaAttrByIdControlloFisico(controlloFisico.getIdControlloFisico());
				nuovoControlloForm.setIdsStrutturaAttrezzatura(idsStutturaAtt);
				String[] idsMetodiProd = controlliEJB
						.getMetodoProduzioneByIdControlloFisico(controlloFisico.getIdControlloFisico());
				nuovoControlloForm.setIdsMetodiProduzione(idsMetodiProd);
				String[] idsTipiIrriga = controlliEJB
						.getTipoIrrigazioneByIdControlloFisico(controlloFisico.getIdControlloFisico());
				nuovoControlloForm.setIdsTipiIrrigazione(idsTipiIrriga);
				String[] idsConProf = controlliEJB
						.getConoscenzaProfByIdControlloFisico(controlloFisico.getIdControlloFisico());
				nuovoControlloForm.setIdsConoscenzeProfessionali(idsConProf);

				List<CarRControlloFisicoReqProf> listaReqProf = controlliEJB
						.getRequisitoProfByIdControlloFisico(controlloFisico.getIdControlloFisico());
				if (listaReqProf != null) {
					String[] idsReqProf = new String[listaReqProf.size()];
					for (int i = 0; i < listaReqProf.size(); i++) {
						idsReqProf[i] = String.valueOf(listaReqProf.get(i).getIdRequisitoProfessionale());
						if (listaReqProf.get(i).getIdRequisitoProfessionale() == 1L) { // mettere
																						// costante
																						// per
																						// Titolo
																						// di
																						// studio
																						// in
																						// ambito
																						// agrario/forestale
							nuovoControlloForm.setDiploma(listaReqProf.get(i).getDescDiploma());
							nuovoControlloForm.setLaurea(listaReqProf.get(i).getDescLaurea());
						}
					}
					nuovoControlloForm.setIdsRequisitiProfessionali(idsReqProf);
				}

				// Recupero i dati salvati in car_r_controllo_fisico_specie
				logger.debug("-- Recupero i dati salvati in car_r_controllo_fisico_specie");
				List<GenereSpecieDTO> listGenereSpecieDB = controlliEJB
						.getGenereSpecieByIdControlloFisisco(controlloFisico.getIdControlloFisico());
				nuovoControlloForm.setGenereSpecie(listGenereSpecieDB);

				// car_r_controllo_fisico_org_noc (per il campo 5.11)
				List<CarRControlloFisicoOrgNoc> contrFisOrgNoc = controlliEJB
						.getOrgNocByIdControlloFisico(controlloFisico.getIdControlloFisico());
				Long[] idOrgNoc = new Long[contrFisOrgNoc.size()];
				int i = 0;
				for (CarRControlloFisicoOrgNoc orgNoc : contrFisOrgNoc) {
					idOrgNoc[i] = orgNoc.getIdOrgNocivo();
					i++;
				}
				nuovoControlloForm.setOrgNoc(idOrgNoc);

			} else {
				logger.debug(
						"-- Sono in NUOVA controllo fisico, al primo accesso : cerco i generi e specie di VIVAI, se non ci sono, quelli di DOMANDA RUOP. Inserisco il record in CAR_T_CONTROLLO_FISICO e li salvo su car_r_controllo_fisico_specie");
				List<GenereSpecieDTO> listaGenereSpecie = generiSpecieEJB
						.getListaGeneriSpecieComunicazione(nuovoControlloForm.getIdCentroAziendale());
				if (listaGenereSpecie == null || listaGenereSpecie.size() == 0) {
					if (nuovoControlloForm.getIdDomanda() != null) {
						logger.debug("-- Ricerca genere e specie Domanda Ruop");
						listaGenereSpecie = generiSpecieEJB
								.getListaGeneriSpecieDomandaRuop(nuovoControlloForm.getIdDomanda());
					}
				}
				Long idUtente = SecurityUtils.getUtenteLoggato().getId();
				// recupero l'idGenere
				if (listaGenereSpecie != null) {
					logger.debug("-- recupero l'idGenere");
					for (Iterator<GenereSpecieDTO> iterator = listaGenereSpecie.iterator(); iterator.hasNext();) {
						GenereSpecieDTO genereSpecieDTO = (GenereSpecieDTO) iterator.next();
						if (genereSpecieDTO != null && genereSpecieDTO.getDenomGenere() != null
								&& !genereSpecieDTO.getDenomGenere().trim().equals("")) {
							List<GenereDTO> listaGeneri = generiSpecieEJB.getListaGeneri(
									genereSpecieDTO.getDenomGenere(), CaronteConstants.CODICE_LINGUA_LATINO);
							genereSpecieDTO.setIdGenere(listaGeneri.get(0).getIdGenere());
							logger.debug("-- idGenere =" + genereSpecieDTO.getIdGenere());

						}
					}
				}
				controlliEJB.inserisciControlloFisicoSpecie(listaGenereSpecie, nuovoControlloForm, idUtente);
				List<GenereSpecieDTO> listGenereSpecieDB = controlliEJB
						.getGenereSpecieByIdControlloFisisco(nuovoControlloForm.getIdControlloFisico());
				nuovoControlloForm.setGenereSpecie(listGenereSpecieDB);
			}
		} else {
			logger.debug("--- Manca idControllo, non sono stati salvati i dati di Base");
			addErrorMessage("Inserire prima i Dati Generali");
		}

		logger.debug("-- END -controlloFisico");
		return getViewNuovaModifica("controllofisico", request);

	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = { "controllofisico/nuova/salva", "controllofisico/modifica/salva" })
	public String controlloFisicoPost(@ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm,
			BindingResult bindResult, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN nuovoControllo - controlloFisicoPost");
		
		if (!bindResult.hasErrors()) {
			try {

				// UPDATE SU car_r_controllo_fisico_specie PER NUM_PIANTE E
				// NUM_PIANTE_SINTOMATICHE
				String[] numeroPianteArr = nuovoControlloForm.getNumeroPiante();
				nuovoControlloForm.setNumeroPiante(numeroPianteArr);
				String[] numeroPianteSintomaticheArr = nuovoControlloForm.getNumPianteSintomatiche();
				nuovoControlloForm.setNumPianteSintomatiche(numeroPianteSintomaticheArr);

				Long idUtente = SecurityUtils.getUtenteLoggato().getId();
				if (nuovoControlloForm.getIdControlloFisico() == null) {
					logger.debug("CASO inserisciNuovoControlloFisico");
					controlliEJB.inserisciNuovoControlloFisico(nuovoControlloForm, idUtente);
				} else {
					logger.debug("CASO updateNuovoControlloFisico");
					controlliEJB.updateNuovoControlloFisico(nuovoControlloForm, idUtente);
				}
				addSuccessMessage("Controllo fisico salvato correttamente.");
				
				
				logger.debug("get id da eliminare:= " + nuovoControlloForm.getIdsDaEliminare() );
				if(nuovoControlloForm.getIdsDaEliminare() != null){
						logger.debug("-- Elimino record in car_r_controllo_fisico_specie");
						
						Long[] idsDaEliminare = nuovoControlloForm.getIdsDaEliminare();
						logger.debug("-- form.getIdsDaEliminare(): " + idsDaEliminare);
						
						for(Long id : idsDaEliminare){
							logger.debug("id da eliminare: " + id);
							controlliEJB.eliminaSpecieControlloFisico(id);
						}
						nuovoControlloForm.setIdsDaEliminare(null);
						addSuccessMessage("Specie eliminate");
					}	
/*
				if (isNuovoControllo(request)) {
					if ("S".equals(nuovoControlloForm.getFlControllo5x23()))
						return getRedirect("campioni/nuova", request);
					else
						return getRedirect("allegati/nuova", request);
				} else {
					if ("S".equals(nuovoControlloForm.getFlControllo5x23()))
						return getRedirect("campioni/modifica", request);
					else
						return getRedirect("allegati/modifica", request);
				}
*/
			} catch (Exception ex) {
				addErrorMessage("Errore durante il salvataggio del controllo");
				logger.error("Errore durante il salvataggio del controllo " + ex.getMessage());
			}
		} /*
			 * else { addErrorMessage("Errore di validazione");
			 * logger.error("Errore di validazione = "+
			 * bindResult.getFieldError()); }
			 */

		logger.debug("-- END nuovoControllo -controlloFisicoPost");
		return controlloFisico(nuovoControlloForm, model, request);

	}
	/* FINE TAB CONTROLLO FISICO */

	// ** TAB CAMPIONI
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/campioni/nuova", "/campioni/modifica" })
	public String datiCampione(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model,
			HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN datiCampione");
		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();

			// Carico la combo Tipi Campione
			logger.debug("-- Carico la combo Tipi Campione");
			List<CodeDescriptionDTO> tipiCampioneList = new ArrayList<CodeDescriptionDTO>();
			CodeDescriptionDTO cd = new CodeDescriptionDTO();
			cd.setCod(CaronteConstants.COD_SINTOMATICO);
			cd.setDescr(CaronteConstants.DESCR_SINTOMATICO);
			tipiCampioneList.add(cd);
			cd = new CodeDescriptionDTO();
			cd.setCod(CaronteConstants.COD_ASINTOMATICO);
			cd.setDescr(CaronteConstants.DESCR_ASINTOMATICO);
			tipiCampioneList.add(cd);
			cd = new CodeDescriptionDTO();
			cd.setCod(CaronteConstants.COD_NON_APPLICABILE);
			cd.setDescr(CaronteConstants.DESCR_NON_APPLICABILE);
			tipiCampioneList.add(cd);
			model.addAttribute("listaTipiCampione", tipiCampioneList);

			// Carico la combo Organismo nocivo da ricercare
			logger.debug("-- Carico la combo Organismo nocivo da ricercare");
			model.addAttribute("listaOrgNocDaRicercare", decodificheEJB.getListaOrganismiNocivi());

			// Carico la combo Tipologia Campione
			logger.debug("-- Carico la combo Tipologia Campione");
			model.addAttribute("listaTipologieCampione", decodificheEJB.getListaTipologiaCampione());

			// Carico Combo Esiti
			logger.debug("-- Carico la combo Esiti");
			List<CodeDescriptionDTO> esitiList = new ArrayList<CodeDescriptionDTO>();
			CodeDescriptionDTO esito = new CodeDescriptionDTO();
			esito.setCod(CaronteConstants.COD_ASSENTE);
			esito.setDescr(CaronteConstants.DESCR_ASSENTE);
			esitiList.add(esito);
			esito = new CodeDescriptionDTO();
			esito.setCod(CaronteConstants.COD_PRESENTE);
			esito.setDescr(CaronteConstants.DESCR_PRESENTE);
			esitiList.add(esito);
			model.addAttribute("listaEsitiRdp", esitiList);

			// Carico la combo Organismo nocivo accertato
			logger.debug("-- Carico la combo Organismo nocivo accertato");
			model.addAttribute("listaOrgNocAccertato", decodificheEJB.getListaOrganismiNocivi());

			// Ricerco i campioni da visualizzare nella tabella
			logger.debug("-- Ricerco i campioni da visualizzare nella tabella");
			List<CampioneDTO> campioniList = controlliEJB.getListaCampioniByIdControllo(form.getIdControllo());
			// Ciclo sui campioni e setto la stringa con le descrizioni degli
			// organismi nocivi da ricercare
			if (campioniList != null && campioniList.size() > 0) {
				logger.debug(
						"-- Ciclo sui campioni e setto la stringa con le descrizioni degli organismi nocivi da ricercare");
				for (Iterator<CampioneDTO> iterator = campioniList.iterator(); iterator.hasNext();) {
					CampioneDTO campioneDTO = (CampioneDTO) iterator.next();
					if (campioneDTO != null) {
						if (campioneDTO.getOrgNociviDaRicercareList() != null
								&& campioneDTO.getOrgNociviDaRicercareList().size() > 0) {
							String descrOrgNocDaRicercare = "";
							for (Iterator<CodeDescriptionDTO> iterator2 = campioneDTO.getOrgNociviDaRicercareList()
									.iterator(); iterator2.hasNext();) {
								CodeDescriptionDTO orgNoc = (CodeDescriptionDTO) iterator2.next();
								if (orgNoc != null) {
									descrOrgNocDaRicercare += orgNoc.getDescr();
									if (iterator2.hasNext()) {
										descrOrgNocDaRicercare += ", ";
									}
								}
							}
							logger.debug("-- descrOrgNocDaRicercare =" + descrOrgNocDaRicercare);
							campioneDTO.setDescrElencoOrgNocDaRicercare(descrOrgNocDaRicercare);
						}
					}
				}
				// setto i tre flag
				form.setFlAnalisi(campioniList.get(0).getFlAnalisi());
				form.setFlBanda(campioniList.get(0).getFlBanda());
				form.setFlSacchetti(campioniList.get(0).getFlSacchetti());
			}
			model.addAttribute("campioniDb", campioniList);

		} catch (Exception exc) {
			addErrorMessage("Errore in fase di caricamento dati Tab Campioni");
			logger.debug("-- Exception in datiCampione =" + exc.getMessage());
		} finally {
			logger.debug("END datiCampione");
		}
		return getViewNuovaModifica("campioni", request);
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(params = "aggiungiCampione", value = { "/campioni/nuova", "/campioni/modifica" })
	public String aggiungiCampione(@ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm,
			BindingResult result, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN aggiungiCampione");

		UtenteDTO utente = SecurityUtils.getUtenteLoggato();

		if (nuovoControlloForm.getIdControllo() != null) {
			logger.debug("Validazione campione da aggiungere");
			/*
			 * if(nuovoControlloForm.getIdGenereCampione() != null ||
			 * nuovoControlloForm.getSpecieCampione() != null ||
			 * nuovoControlloForm.getCodCampioneDal() != null ||
			 * nuovoControlloForm.getTipoCampione() != null ||
			 * nuovoControlloForm.getOrgNocDaRicercare().length > 0 ||
			 * nuovoControlloForm.getTipologiaCampione() != null ||
			 * nuovoControlloForm.getFlCofinanziato() != null ||
			 * nuovoControlloForm.getCodCampioneAl() != null ||
			 * nuovoControlloForm.getTempoImpiegato() != null ||
			 * nuovoControlloForm.getDataRdp() != null ||
			 * nuovoControlloForm.getEsitoRdp() != null ||
			 * nuovoControlloForm.getOrgNocAccertato().length > 0 ||
			 * nuovoControlloForm.getNumeroRdp() != null ||
			 * nuovoControlloForm.getNoteCampione() != null){
			 */

			validator.validateDatiCampione(nuovoControlloForm, result);
			// }

			if (result.getErrorCount() == 0) {
				// caso aggiunta nuovo campione
				// Salvo il campione su car_t_campione
				logger.debug("-- Salvo il campione su car_t_campione");
				controlliEJB.inserisciCampione(nuovoControlloForm, utente.getId());
				puliscoFormCampione(nuovoControlloForm);
				addSuccessMessage("Campione aggiunto");
				/*
				 * if(!controlliEJB.getListaCampioniByIdControllo(
				 * nuovoControlloForm.getIdControllo()).isEmpty()){
				 * controlliEJB.inserisciCheck(nuovoControlloForm,
				 * utente.getId()); } else{
				 * addErrorMessage("Inserire almeno un campione"); }
				 */
			} else {
				model.addAttribute("hasErrors", true);
				logger.debug("-- La validazione non è andata a buon fine");
			}
		} else {
			logger.debug("-- Manca il record su car_t_controllo, devono compilare i dati nel Tab Dati di base");
			addErrorMessage("Inserire prima i Dati Generali");
			return getRedirect("campioni/nuova", request);
		}

		datiCampione(nuovoControlloForm, model, request);

		logger.debug("END aggiungiCampione");
		return getViewNuovaModifica("campioni", request);
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(params = "salvaDatiCampione", value = { "/campioni/nuova", "/campioni/modifica" })
	public String salvaDatiCampione(@ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm,
			BindingResult result, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN salvaDatiCampione");

		UtenteDTO utente = SecurityUtils.getUtenteLoggato();

		if (!controlliEJB.getListaCampioniByIdControllo(nuovoControlloForm.getIdControllo()).isEmpty()) {
			controlliEJB.inserisciCheck(nuovoControlloForm, utente.getId());
			addSuccessMessage("Campioni salvati correttamente.");
		} else {
			addErrorMessage("Inserire almeno un campione");
		}

		datiCampione(nuovoControlloForm, model, request);

		logger.debug("END salvaDatiCampione");
		return getViewNuovaModifica("campioni", request);
	}

	private void puliscoFormCampione(NuovoControlloForm form) {
		logger.debug("BEGIN puliscoFormCampione");
		form.setIdGenereCampione(null);
		form.setGenereCampione(null);
		// form.setIdSpecie(null);
		form.setCodCampioneDal(null);
		form.setCodCampioneAl(null);
		form.setTipoCampione(null);
		form.setOrgNocDaRicercare(null);
		form.setOrgNocAccertato(null);
		form.setTipologiaCampione(null);
		form.setFlCofinanziato(null);
		form.setTempoImpiegato(null);
		form.setDataRdp(null);
		form.setEsitoRdp(null);
		form.setNumeroRdp(null);
		form.setNoteCampione(null);
		logger.debug("END puliscoFormCampione");
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/campioni/eliminaCampione/{id}", "/modifica/campioni/eliminaCampione/{id}" })
	public String eliminaCampione(@PathVariable Long id, @ModelAttribute("nuovoControlloForm") NuovoControlloForm form,
			Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN eliminaCampione");
		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();

			logger.debug("-- Elimino campione con id_campione =" + id);
			controlliEJB.eliminaCampione(id);
			addSuccessMessage("Campione eliminato");

			datiCampione(form, model, request);
		} catch (Exception e) {
			logger.error("-- Exception in eliminaCampione =" + e.getMessage());
			addErrorMessage("Errore nell'eliminazione del Campione");
		} finally {
			logger.debug("END eliminaCampione");
		}
		return getViewNuovaModifica("campioni", request);
	}

	// ** FINE TAB CAMPIONI

	/* TAB ESITO */
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "esito/nuova", "esito/modifica" })
	public String esito(@ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm, Model model,
			HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN nuovoControllo - esito");

		if (nuovoControlloForm.getIdControllo() == null) {
			logger.debug("--- Manca idControllo, non sono stati salvati i dati Generali");
			addErrorMessage("Inserire prima i Dati Generali");
		} else {
			popolaTabEsito(nuovoControlloForm); // valorizzo i campi in caso di
												// modifica
			boolean checkAbilitaStampaVerbaleIsp = controlliEJB
					.checkAbilitaStampaVerbaleIsp(nuovoControlloForm.getIdControllo());
			logger.debug("+--+checkAbilitaStampaVerbaleIsp= " + checkAbilitaStampaVerbaleIsp);
			model.addAttribute("abilitaStampa", checkAbilitaStampaVerbaleIsp);

			if (nuovoControlloForm.getDataChiusuraVerbale() == null) {
				nuovoControlloForm.setDataChiusuraVerbale(new Date());
			}
		}

		logger.debug("-- END nuovoControllo -esito");
		return getViewNuovaModifica("esito", request); // "esito/nuova";

	}

	private void popolaTabEsito(NuovoControlloForm nuovoControlloForm) throws BusinessException {
		logger.debug("-- BEGIN popolaTabEsito");
		CarTEsito esito = controlliEJB.getEsitoByIdControllo(nuovoControlloForm.getIdControllo());

		if (esito != null) {
			nuovoControlloForm.setIdEsito(esito.getIdEsito());
			nuovoControlloForm.setDataChiusuraVerbale(esito.getDataChiusuraVerbale());
			nuovoControlloForm.setCognomeResp(esito.getCognomeResp());
			nuovoControlloForm.setNomeResp(esito.getNomeResp());
			nuovoControlloForm.setNoteDichiarazione(esito.getNoteDichiarazione());
			nuovoControlloForm.setNote(esito.getNote());
			nuovoControlloForm.setFlIrregolarita(esito.getFlIrregolarita());
			nuovoControlloForm.setNoteIrregolarita(esito.getNoteIrregolarita());
			nuovoControlloForm.setFlEsito(esito.getFlEsito());
			nuovoControlloForm.setFlEsitoPassaporto(esito.getFlEsitoPassaporto());
			nuovoControlloForm.setEmailInvioVerbale(esito.getEmailInvioVerbale());
			// nuovi campi per id_versione = 2
			nuovoControlloForm.setNoteEsitoControllo(esito.getNoteEsitoControllo());
			nuovoControlloForm.setFlMisuraUfficiale(esito.getFlMisuraUfficiale());
			nuovoControlloForm.setNumMisuraUfficiale(esito.getNumMisuraUfficiale());
			nuovoControlloForm.setFlMotivoMisuraUfficiale(esito.getFlMotivoMisuraUfficiale());
			nuovoControlloForm.setNoteMotivoMisuraUfficiale(esito.getNoteMotivoMisuraUfficiale());
			nuovoControlloForm.setFlSanzioneAmministrativaEmessa(esito.getFlSanzioneAmministrativaEmessa());
			nuovoControlloForm.setNoteSanzioneAmministrativaEmessa(esito.getNoteSanzioneAmministrativaEmessa());
			nuovoControlloForm.setFlSanzioneAmministrativaProposta(esito.getFlSanzioneAmministrativaProposta());
			nuovoControlloForm.setNoteSanzioneAmministrativaProposta(esito.getNoteSanzioneAmministrativaProposta());
			nuovoControlloForm.setFlPrescrizioni(esito.getFlPrescrizioni());
			nuovoControlloForm.setNotePrescrizioni(esito.getNotePrescrizioni());

			CarTControllo controllo = controlliEJB.getControllo(nuovoControlloForm.getIdControllo());
			nuovoControlloForm.setMisuraUfficiale(controllo.getMisuraUfficiale());
		}

		logger.debug("-- END popolaTabEsito");

	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = { "esito/nuova", "esito/modifica" })
	public String salvaEsito(@ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm,
			BindingResult bindResult, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN nuovoControllo - esitoSalva");

		validator.validateEsito(nuovoControlloForm, bindResult);

		if (!bindResult.hasErrors()) {
			try {
				Long idUtente = SecurityUtils.getUtenteLoggato().getId();
				if (nuovoControlloForm.getIdEsito() == null) {
					controlliEJB.inserisciEsitoControllo(nuovoControlloForm, idUtente);
				} else {
					controlliEJB.updateEsitoControllo(nuovoControlloForm, idUtente);
				}
				addSuccessMessage("Esito salvato correttamente.");
				if (isNuovoControllo(request)) {
					return getRedirect("esito/nuova", request);
				} else {
					return getRedirect("esito/modifica", request);
				}

			} catch (Exception ex) {
				addErrorMessage("Errore durante il salvataggio dell'esito");
				logger.error("Errore durante il salvataggio dell'esito " + ex.getMessage());
			}
		}

		logger.debug("-- END nuovoControllo -esitoSalva");
		return esito(nuovoControlloForm, model, request);// "esito/nuova";

	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = "esito/inviaMail/{idEsito}/{idControllo}")
	@ResponseBody
	public ResponseEntity<String> inviaMailVerbale(@PathVariable Long idEsito, @PathVariable Long idControllo,
			Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN nuovoControllo - inviaMailVerbale");

		Long idUtente = SecurityUtils.getUtenteLoggato().getId();
		String indirizzoMail = request.getParameter("indirizzoMail");
		try {
			Long numeroVerbale = controlliEJB.getSalvaNumeroVerbale(idControllo, idUtente);
			byte[] pdf = generaPdfIspezioneVerbale(idControllo, model, request);
			controlliEJB.inviaVerbaleViaMail(idControllo, idEsito, indirizzoMail, pdf, idUtente, numeroVerbale);
		} catch (Exception e) {
			logger.debug("-- END Errore nuovoControllo - inviaMailVerbale");
			return new ResponseEntity<>("KO", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.debug("-- END nuovoControllo - inviaMailVerbale");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	public byte[] generaPdfIspezioneVerbale(Long id, Model model, HttpServletRequest request) {
		try {
			byte[] data = null;
			String filename = null;

			Long idAssociazioneSezione = getSezioneRequest(request);
			logger.debug("idAssociazioneSezione =" + idAssociazioneSezione);
			Long idVersioneControllo = controlliEJB.checkVersioneControllo(id);
			logger.debug("-- idVersioneControllo = " + idVersioneControllo);

			if (idAssociazioneSezione == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI) {

				if (idVersioneControllo.equals(1L)) {
					logger.debug("\n\n STAMPA generaPdfIspezioneVerbale PRIMA VERSIONE idControllo =" + id);
					List<byte[]> listTemplateVerbaleIspezione = new ArrayList<byte[]>();
					listTemplateVerbaleIspezione.add(controlliEJB.getTemplateTipoStampaControlli(id));
					listTemplateVerbaleIspezione.add(controlliEJB.getTemplateTipoStampaControlloById(
							CaronteConstants.ID_TIPO_STAMPA_SUBREP_SITI_PROD_CONTR));
					listTemplateVerbaleIspezione.add(controlliEJB
							.getTemplateTipoStampaControlloById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_ATTIVITA_CONTR));
					listTemplateVerbaleIspezione.add(controlliEJB.getTemplateTipoStampaControlloById(
							CaronteConstants.ID_TIPO_STAMPA_SUBREP_GENERI_SPECIE_CONTR));
					listTemplateVerbaleIspezione.add(controlliEJB
							.getTemplateTipoStampaControlloById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_CAMPIONI_CONTR));
					listTemplateVerbaleIspezione.add(controlliEJB
							.getTemplateTipoStampaControlloById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_ALLEGATI_CONTR));
					String[] listNomiIspezione = { "id_controllo", "subreportParameterSitoProduzione",
							"subreportParemeterAttivita", "subreportGeneriSpecie", "subreportCampioni",
							"subreportAllegati" };

					data = StampeManager.getPdfVerbaleIspezioneControlli(id, listTemplateVerbaleIspezione,
							listNomiIspezione);
				} else {
					logger.debug("\n\n STAMPA generaPdfIspezioneVerbale SECONDA VERSIONE idControllo =" + id);
					// Seconda versione checklist verbale isp
					List<byte[]> listTemplateVerbaleIspezione = new ArrayList<byte[]>();
					listTemplateVerbaleIspezione.add(controlliEJB.getTemplateTipoStampaControlloById(
							CaronteConstants.ID_TIPO_STAMPA_VERB_ISP_SECONDA_VERSIONE));
					listTemplateVerbaleIspezione.add(controlliEJB.getTemplateTipoStampaControlloById(
							CaronteConstants.ID_TIPO_STAMPA_SUBREP_SITI_PROD_CONTR));
					listTemplateVerbaleIspezione.add(controlliEJB
							.getTemplateTipoStampaControlloById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_ATTIVITA_CONTR));
					listTemplateVerbaleIspezione.add(controlliEJB.getTemplateTipoStampaControlloById(
							CaronteConstants.ID_TIPO_STAMPA_SUBREP_GENERI_SPECIE_CONTR));
					listTemplateVerbaleIspezione.add(controlliEJB
							.getTemplateTipoStampaControlloById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_CAMPIONI_CONTR));
					listTemplateVerbaleIspezione.add(controlliEJB
							.getTemplateTipoStampaControlloById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_ALLEGATI_CONTR));
					listTemplateVerbaleIspezione.add(controlliEJB.getTemplateTipoStampaControlloById(
							CaronteConstants.ID_TIPO_STAMPA_SUBREP_ORGANISMO_NOCIVO));
					String[] listNomiIspezione = { "id_controllo", "subreportParameterSitoProduzione",
							"subreportParemeterAttivita", "subreportGeneriSpecie", "subreportCampioni",
							"subreportAllegati", "subreportON" };

					data = StampeManager.getPdfVerbaleIspezioneControlli(id, listTemplateVerbaleIspezione,
							listNomiIspezione);

				}
			}

			return data;

		} catch (Exception e) {
			logger.error("Errore nella generazione del pdf :" + e.getMessage());
			addErrorMessage("Errore nella generazione del pdf");
		}
		return null;
	}

	@GetMapping(value = "esito/stampa/{idEsito}/{idControllo}", produces = "application/pdf")
	public void stampaVerbaleIspezione(@PathVariable Long idEsito, @PathVariable Long idControllo, Model model,
			HttpServletRequest request, HttpServletResponse response) throws BusinessException, IOException {
		logger.debug("-- BEGIN nuovoControllo - stampaVerbaleIspezione");

		Long idUtente = SecurityUtils.getUtenteLoggato().getId();
		CarTEsito esito = controlliEJB.getEsito(idEsito);
		CarTControllo controllo = controlliEJB.getControllo(idControllo);
		Long numeroVerbale = controlliEJB.getSalvaNumeroVerbale(idControllo, idUtente);
		byte[] pdf = generaPdfIspezioneVerbale(idControllo, model, request);
		String fileName = controlliEJB.salvaStampa(idEsito, pdf, idUtente, numeroVerbale);

		response.resetBuffer();
		response.setContentType("application/pdf");
		LocalDateTime dataStampa = LocalDateTime.now();
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
		response.setContentLength(pdf.length);
		response.getOutputStream().write(pdf);
		response.getOutputStream().flush();

	}
	/* FINE TAB ESITO */

	// Tab Allegati
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/allegati/nuova", "/allegati/modifica" })
	public String datiAllegati(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model,
			HttpServletRequest request) throws BusinessException {
		if (form.getIdControllo() != null) {
			try {
				setDatiAllegati(model, form);
			} catch (BusinessException exc) {
				addErrorMessage("Errore in fase di caricamento dati allegati");
			}
		} else {
			logger.debug("--- Manca idControllo, non sono stati salvati i dati di Base");
			addErrorMessage("Inserire prima i Dati Generali");
		}
		return getViewNuovaModifica("allegati", request);
	}

	private void setDatiAllegati(Model model, NuovoControlloForm form) throws BusinessException {

		model.addAttribute("listaAllegati", controlliEJB.getListaAllegatiControllo(form.getIdControllo()));

		// Se serve aggiungere i moduli si completa questa parte
		// model.addAttribute("listaModuli",
		// decodificheEJB.getListaModuliComunicazione(form.getIdDomanda()));

	}

	// Tab Allegati : salva allegati
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = { "/allegati/nuova", "/allegati/modifica" })
	public String salvaDatiAllegati(@ModelAttribute("allegatiForm") AllegatiControlliForm allegatiForm,
			@ModelAttribute("nuovoControlloForm") NuovoControlloForm controlloForm, Model model,
			HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN salvaDatiAllegati");
		try {
			Long idUtente = SecurityUtils.getUtenteLoggato().getId();

			/*
			 * PER LA GESTIONE DEI MODULI List<ModuloDTO> listaModuli =
			 * decodificheEJB.getListaModuliComunicazione(domandaForm.
			 * getIdDomanda());
			 * 
			 * if (listaModuli != null && listaModuli.size() > 0) {
			 * logger.debug("converti file moduli"); logger.debug("modulo[0] ="
			 * +listaModuli.get(0).getIdModulo());
			 * allegatiForm.convertiFileModuli(listaModuli); }
			 * domandeEJB.aggiornaDatModuli(domandaForm.getIdDomanda(),
			 * listaModuli, idUtente);
			 * logger.debug("aggiornamento dati moduli ok");
			 */

			List<AllegatoDTO> listaAllegati = allegatiForm.convertiFileAllegati();
			controlliEJB.aggiornaDatiAllegati(controlloForm.getIdControllo(), listaAllegati, idUtente);
			addSuccessMessage("Allegati salvati correttamente.");
			// addSuccessMessage("Allegati salvati correttamente");

		} catch (BusinessException be) {
			addErrorMessage("Errore nel salvataggio dei dati allegati");
		}

		setDatiAllegati(model, controlloForm);
		// return getViewNuovaModifica("allegati", request);
		/*if (isNuovoControllo(request)) {
			return getRedirect("esito/nuova", request);
		} else {
			return getRedirect("esito/modifica", request);
		}
		*/
		return datiAllegati(controlloForm, model, request);
	}

	// Tab Allegati : scarica allegato
	@PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = "/allegato/scarica/{idAllegatoControllo}", produces = { "application/octet-stream" })
	public void downloadAllegato(@PathVariable Long idAllegatoControllo, Model model, HttpServletResponse response) {
		logger.debug("BEGIN downloadAllegato");
		try {
			logger.debug("-- idAllegatoControllo da scaricare =" + idAllegatoControllo);
			CarTAllegatoControllo allegato = controlliEJB.getAllegatoControlloById(idAllegatoControllo);

			if (allegato != null && !StringUtils.isEmpty(allegato.getNomeFile())) {
				/*
				 * Il filename viene encodato per evitare vulnerabilità di
				 * response splitting
				 */
				scaricaFile("application/octet-stream",
						"attachment; filename=" + URLEncoder.encode(allegato.getNomeFile(), "UTF-8"),
						allegato.getAllegato(), response);
			}
		} catch (BusinessException | IOException exc) {
			logger.error("Errore nel reperimento dell'allegato", exc);
		}
	}

	// Tab Monitoraggio cofinanziato
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/monitoraggio/nuova", "/monitoraggio/modifica" })
	public String datiMonitoraggio(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model,
			HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN datiMonitoraggio");

		// Popolo combo Organismi nocivi
		model.addAttribute("listaMotiviMisura", decodificheEJB.getListaOrganismiNocivi());

		if (form.getIdControllo() == null) {
			addErrorMessage("Inserire prima i Dati Generali");
		}
		// Controllo se sono nel caso di modifica
		else {
			List<MonitCofinanziatoDTO> monitCofinanziatoList = controlliEJB
					.getMonitoraggiCofinanziato(form.getIdControllo());
			if (monitCofinanziatoList != null) {
				logger.debug("-- CASO di modifica Monitoraggio cofinanziato");
				setDatiMonitoraggioCofinanziato(form, monitCofinanziatoList);
			}
		}

		logger.debug("END datiMonitoraggio");
		return getViewNuovaModifica("monitoraggio", request);
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = { "/monitoraggio/nuova/salva", "/monitoraggio/modifica/salva" })
	public String salvaMonitCofinanziato(
			@Valid @ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm,
			BindingResult bindResult, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN salvaMonitCofinanziato");

		Long idControllo = nuovoControlloForm.getIdControllo();
		logger.debug("-- Salvo MONITORAGGIO COFINANZIATO per idControllo =" + idControllo);

		/*
		 * Recuperare i dati da salvare per Organismi nocivi, genere e specie 1)
		 * recupero gli idSpecie : recupero idGenere e idOrganismo nocivo e
		 * salvo dati (Casi di Genere non famiglia) 2) recupero gli idGenere e
		 * note e salvo (caso di Genere famiglia)
		 */

		String[] idsOrganismiNociviAggiunti = request.getParameterValues("idOrganismoNocivoMonit");
		String[] idsSpecieAggiunti = request.getParameterValues("idSpecieMonit");
		String[] idsGeneriAggiunti = request.getParameterValues("idGenereMonit");
		String[] noteAggiunte = request.getParameterValues("noteMonitH");

		String[] numeroPianteArr = nuovoControlloForm.getNumeroPianteMonit();
		String[] latitudineArr = nuovoControlloForm.getLatitudine();
		String[] longitudineArr = nuovoControlloForm.getLongitudine();
		String[] noteMonitArr = nuovoControlloForm.getNoteMonit();
		String[] oraInizioMonitArr = nuovoControlloForm.getOraInizioMonit();
		String[] oraFineMonitArr = nuovoControlloForm.getOraFineMonit();

		try {
			// Formatto in un oggetto i dati presenti in tabella
			List<MonitCofinanziatoDTO> listMonitCofinanziato = getDatiMonitoraggioCofinanziato(
					idsOrganismiNociviAggiunti, idsSpecieAggiunti, idsGeneriAggiunti, noteAggiunte, numeroPianteArr,
					latitudineArr, longitudineArr, noteMonitArr, oraInizioMonitArr, oraFineMonitArr);
			nuovoControlloForm.setMonitoraggiCofinanziati(listMonitCofinanziato);

			if (nuovoControlloForm.getIdControllo() != null) {
				// Controllo se hanno indicato almeno un organismo nocivo
				if (idsOrganismiNociviAggiunti == null || idsOrganismiNociviAggiunti.length == 0) {
					addErrorMessage("Inserire almeno un organismo nocivo");
					return datiMonitoraggio(nuovoControlloForm, model, request);
				}
				Long idUtente = SecurityUtils.getUtenteLoggato().getId();
				if (nuovoControlloForm.getIdMonitCofinanziato() == null) {
					logger.debug("-- Caso di inserimento NUOVO MONITORAGGIO COFINANZIATO");
					controlliEJB.inserisciMonitoraggioCofinanziato(nuovoControlloForm, idUtente);
				} else {
					logger.debug("-- Caso di aggiornamento MONITORAGGIO COFINANZIATOE con id_monit_cofinanziato ="
							+ nuovoControlloForm.getIdMonitCofinanziato());
					// TODO
					// controlliEJB.updateMisuraUfficiale(nuovoControlloForm,
					// idUtente);
				}
			} else {
				addErrorMessage("Inserire prima i Dati Generali");
				return datiMonitoraggio(nuovoControlloForm, model, request);
			}
			if (isNuovoControllo(request)) {
				return getRedirect("monitoraggio/nuova", request);
			} else {
				return getRedirect("monitoraggio/modifica", request);
			}

		} catch (Exception ex) {
			addErrorMessage("Errore durante il salvataggio del Monitoraggio cofinanziato");
			logger.error("Errore durante il salvataggio del Monitoraggio cofinanziato" + ex.getMessage());
		} finally {
			logger.debug("-- END salvaMonitCofinanziato");
		}

		return datiMonitoraggio(nuovoControlloForm, model, request);
	}

	private void setDatiMonitoraggioCofinanziato(NuovoControlloForm form,
			List<MonitCofinanziatoDTO> monitCofinanziatoList) throws BusinessException {
		logger.debug("BEGIN setDatiMonitoraggioCofinanziato");

		if (monitCofinanziatoList != null && monitCofinanziatoList.size() > 0) {
			// Settare le descrizioni di organismo nocivo, genere e specie
			logger.debug("-- Settare le descrizioni di organismo nocivo, genere e specie");
			for (Iterator iterator = monitCofinanziatoList.iterator(); iterator.hasNext();) {
				MonitCofinanziatoDTO dto = (MonitCofinanziatoDTO) iterator.next();

				// Cerco la descrizione genere per i casi con la specie indicata
				if (dto.getIdSpecie() != null) {
					logger.debug("-- Cerco la descrizione genere per i casi con la specie indicata");
					CodeDescriptionDTO genere = generiSpecieEJB.getGenereByIdSpecie(dto.getIdSpecie());
					if (genere != null) {
						dto.setDenomGenere(genere.getDescr());
					}
					// Cerco la descrizione della specie
					logger.debug("-- Cerco la descrizione della specie");
					List<Long> idSpecieList = new ArrayList<Long>();
					idSpecieList.add(dto.getIdSpecie());
					List<SpecieDTO> specieDto = generiSpecieEJB.getSpecieGenereByListIdSpecie(idSpecieList);
					if (specieDto != null) {
						dto.setDenomSpecie(specieDto.get(0).getDenomSpecie());
					}
				}
				// Cerco la descrizione genere per i casi con solo genere
				// specificato (famiglia)
				else {
					if (dto.getIdGenere() != null) {
						logger.debug(
								"-- Cerco la descrizione genere per i casi con solo genere specificato (famiglia)");
						CodeDescriptionDTO genere = generiSpecieEJB.getGenereByIdGenere(dto.getIdGenere());
						if (genere != null) {
							dto.setDenomGenere(genere.getDescr());
						}
					}
				}
				// Cerco la descrizione dell'organismo nocivo
				if (dto.getIdOrganismoNocivo() != null) {
					CodeDescriptionDTO orgNocivo = generiSpecieEJB
							.getOrgNocivoByIdOrgNocivo(dto.getIdOrganismoNocivo());
					dto.setDenomOrganismoNocivo(orgNocivo.getDescr());
				}
			}
			form.setMonitoraggiCofinanziati(monitCofinanziatoList);
		}
		logger.debug("END setDatiMonitoraggioCofinanziato");
	}

	private List<MonitCofinanziatoDTO> getDatiMonitoraggioCofinanziato(String[] idsOrganismiNocAggiunti,
			String[] idsSpecieAggiunti, String[] idsGeneriAggiunti, String[] noteAggiunte, String[] numeroPianteArr,
			String[] latitudineArr, String[] longitudineArr, String[] noteMonitArr, String[] oraInizioMonitArr,
			String[] oraFineMonitArr) throws BusinessException {
		logger.debug("BEGIN getDatiMonitoraggioCofinanziato");

		List<MonitCofinanziatoDTO> monitoraggiCof = new ArrayList<MonitCofinanziatoDTO>();

		if (idsOrganismiNocAggiunti != null && idsOrganismiNocAggiunti.length > 0) {
			logger.debug("-- righe presenti nella tabella =" + idsOrganismiNocAggiunti.length);
			for (int i = 0; i < idsOrganismiNocAggiunti.length; i++) {
				MonitCofinanziatoDTO orgNoc = new MonitCofinanziatoDTO();

				// Setto idOrganismoNocivo
				logger.debug("-- idsOrganismiNocAggiunti[i] =" + idsOrganismiNocAggiunti[i]);
				orgNoc.setIdOrganismoNocivo(new Long(idsOrganismiNocAggiunti[i]));

				// Setto idGenere
				logger.debug("-- idsGeneriAggiunti[i] =" + idsGeneriAggiunti[i]);
				if (!idsGeneriAggiunti[i].equals("")) {
					orgNoc.setIdGenere(new Long(idsGeneriAggiunti[i]));
				}
				// Cerco il genere relativo alla specie
				else {
					if (!idsSpecieAggiunti[i].equals("")) {
						logger.debug("-- Cerco il genere relativo alla specie");
						CodeDescriptionDTO codDescGen = generiSpecieEJB
								.getGenereByIdSpecie(new Long(idsSpecieAggiunti[i]));
						if (codDescGen != null) {
							logger.debug("-- idGenere =" + codDescGen.getId());
							orgNoc.setIdGenere(codDescGen.getId());
						}
					}
				}

				// Setto idSpecie
				logger.debug("-- idsSpecieAggiunti[i] =" + idsSpecieAggiunti[i]);
				if (!idsSpecieAggiunti[i].equals("")) {
					orgNoc.setIdSpecie(new Long(idsSpecieAggiunti[i]));
				}

				// Setto Note
				logger.debug("-- noteAggiunte[i] =" + noteAggiunte[i]);
				orgNoc.setNote(noteAggiunte[i]);

				// Setto Numero Piante
				logger.debug("-- numeroPianteArr[i] =" + numeroPianteArr[i]);
				if (numeroPianteArr[i] != null && !numeroPianteArr[i].equals(""))
					orgNoc.setNumeroPiante(new Long(numeroPianteArr[i]));

				// Setto Latitudine
				logger.debug("-- latitudineArr[i] =" + latitudineArr[i]);
				if (latitudineArr[i] != null && !latitudineArr[i].equals(""))
					orgNoc.setLatitudine(latitudineArr[i]);

				// Setto Longitudine
				logger.debug("-- longitudineArr[i] =" + longitudineArr[i]);
				if (longitudineArr[i] != null && !longitudineArr[i].equals(""))
					orgNoc.setLongitudine(longitudineArr[i]);

				// Setto Note
				logger.debug("-- noteMonitArr[i] =" + noteMonitArr[i]);
				if (noteMonitArr[i] != null && !noteMonitArr[i].equals(""))
					orgNoc.setNoteMonit(noteMonitArr[i]);

				// Setto Ora inizio (Tempo impiegato)
				logger.debug("-- oraInizioMonitArr[i] =" + oraInizioMonitArr[i]);
				if (oraInizioMonitArr[i] != null && !oraInizioMonitArr[i].equals(""))
					orgNoc.setOraInizioMonit(oraInizioMonitArr[i]);

				// Setto Ora fine (Tempo impiegato)
				logger.debug("-- oraFineMonitArr[i] =" + oraFineMonitArr[i]);
				if (oraFineMonitArr[i] != null && !oraFineMonitArr[i].equals(""))
					orgNoc.setOraFineMonit(oraFineMonitArr[i]);

				monitoraggiCof.add(orgNoc);
			}
		}

		logger.debug("---- Numero di Monitoraggi cofinanziati da inserire =" + monitoraggiCof.size());

		logger.debug("END getDatiMonitoraggioCofinanziato");
		return monitoraggiCof;
	}

	// Tab Misura ufficiale
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/misura/nuova", "/misura/modifica" })
	public String datiMisura(@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model,
			HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN datiMisura");

		// Popolo le combo
		model.addAttribute("elencoIspettoriMisura",
				decodificheEJB.getListaIspettoriByTipoIspettore(CaronteConstants.TIPO_ISPETTORE_ISPETTORE));
		model.addAttribute("listaMisure", decodificheEJB.getTipologieMisura());
		model.addAttribute("listaMotiviMisura", decodificheEJB.getListaOrganismiNocivi());

		model.addAttribute("listaProvinceCustode", decodificheEJB.getListaProvince());
		model.addAttribute("elencoTipiRespAzienda", decodificheEJB.getListaTipiRespAzienda());

		if (form.getIdControllo() == null) {
			addErrorMessage("Inserire prima i Dati Generali");
		}
		// Controllo se sono nel caso di modifica
		else {
			MisuraUfficialeDTO misuraUfficialeDto = controlliEJB.getMisuraUfficiale(form.getIdControllo());
			if (misuraUfficialeDto != null && misuraUfficialeDto.getMisuraUfficiale() != null) {
				logger.debug("-- CASO di modifica misura ufficiale");
				logger.debug("-- idMisuraUfficiale =" + misuraUfficialeDto.getMisuraUfficiale().getIdMisuraUfficiale());
				setDatiMisuraUfficiale(form, misuraUfficialeDto);
			}
			model.addAttribute("abilitaStampaDisposizione",
					controlliEJB.checkAbilitaStampaDisposizioneMisuraUff(form.getIdControllo()));
			logger.debug("+--+check abilita stampa Dispo= "
					+ controlliEJB.checkAbilitaStampaDisposizioneMisuraUff(form.getIdControllo()));
			model.addAttribute("abilitaStampaConstatazione",
					controlliEJB.checkAbilitaStampaConstatazioneMisuraUff(form.getIdControllo()));
			logger.debug("+--+check abilita stampa Dispo= "
					+ controlliEJB.checkAbilitaStampaConstatazioneMisuraUff(form.getIdControllo()));
		}

		if (form.getIdProvNascitaCustode() != null) {
			model.addAttribute("listaComuniNascitaCustode",
					decodificheEJB.getListaComuni(form.getIdProvNascitaCustode()));
		}

		model.addAttribute("listaProvinceCustode", decodificheEJB.getListaProvince());
		if (form.getIdProvResidCustode() != null) {
			model.addAttribute("listaComuniResidenzaCustode",
					decodificheEJB.getListaComuni(form.getIdProvResidCustode()));
		}

		logger.debug("END datiMisura");
		return getViewNuovaModifica("misura", request);
	}

	private void setDatiMisuraUfficiale(NuovoControlloForm form, MisuraUfficialeDTO misuraUfficialeDto)
			throws BusinessException {
		logger.debug("BEGIN setDatiMisuraUfficiale");

		CarTMisuraUfficiale misura = misuraUfficialeDto.getMisuraUfficiale();
		form.setIdMisuraUfficiale(misura.getIdMisuraUfficiale());
		form.setDataMisura(misura.getDataMisuraDisp());
		form.setOraMisura(misura.getOraMisuraDisp());
		form.setNumVerbMisuraUff(String.valueOf(misura.getNumeroVerbaleMf()));

		// Ispettori disposizione
		Long[] idsIspettoreMisura = null;
		if (misuraUfficialeDto.getIspettoriDisposizione() != null) {
			idsIspettoreMisura = new Long[misuraUfficialeDto.getIspettoriDisposizione().size()];
			int i = 0;
			for (Iterator<CarRMisuraIspettore> iterator = misuraUfficialeDto.getIspettoriDisposizione()
					.iterator(); iterator.hasNext();) {
				CarRMisuraIspettore isp = (CarRMisuraIspettore) iterator.next();
				idsIspettoreMisura[i] = isp.getIdIspettore();
				i++;
			}
			form.setIdsIspettoreMisura(idsIspettoreMisura);
		}

		form.setNoteNonConformita(misura.getNoteConformita());

		// Misure applicate
		if (misuraUfficialeDto.getMisureApplicate() != null) {
			List<MisuraDTO> misureList = new ArrayList<MisuraDTO>();
			List<CarDTipologiaMisura> tipologieMisure = decodificheEJB.getTipologieMisura();
			for (int i = 0; i < tipologieMisure.size(); i++) {
				Long idTipologiaMisura = tipologieMisure.get(i).getIdTipologiaMisura();
				MisuraDTO misuraAppl = new MisuraDTO();
				for (Iterator<CarRMisuraTipologia> iterator = misuraUfficialeDto.getMisureApplicate()
						.iterator(); iterator.hasNext();) {
					CarRMisuraTipologia misuraTip = (CarRMisuraTipologia) iterator.next();
					if (misuraTip.getIdTipologiaMisura() == idTipologiaMisura) {
						misuraAppl.setIdMisura(misuraTip.getIdTipologiaMisura());
						misuraAppl.setNote(misuraTip.getNoteTipologia());
						break;
					}
				}
				misureList.add(misuraAppl);
			}
			form.setMisure(misureList);
		}

		form.setLettereMisura(misura.getLettereMisura());
		form.setDataMisuraEntro(misura.getDataEntroMisura());
		form.setModalita(misura.getModalitaMisura());
		form.setNoteMisuraDisp(misura.getNoteMisuraDisp());

		// Organismi nocivi
		if (misuraUfficialeDto.getOrganismiNociviMisuraUfficiale() != null) {
			List<OrgNocivoGenereSpecieDTO> organismiNocMisura = new ArrayList<OrgNocivoGenereSpecieDTO>();
			String[] numeroPianteMisura = new String[misuraUfficialeDto.getOrganismiNociviMisuraUfficiale().size()];
			int i = 0;
			for (Iterator<CarRMisuraOrgNoc> iterator = misuraUfficialeDto.getOrganismiNociviMisuraUfficiale()
					.iterator(); iterator.hasNext();) {
				CarRMisuraOrgNoc orgNoc = (CarRMisuraOrgNoc) iterator.next();

				OrgNocivoGenereSpecieDTO orgNocDto = new OrgNocivoGenereSpecieDTO();
				orgNocDto.setIdGenere(orgNoc.getIdGenere());
				orgNocDto.setIdSpecie(orgNoc.getIdSpecie());
				orgNocDto.setIdSpecieMisura(orgNoc.getIdSpecie());
				orgNocDto.setIdOrganismoNocivo(orgNoc.getIdOrgNocivo());
				orgNocDto.setNote(orgNoc.getNote());
				orgNocDto.setNumeroPiante(orgNoc.getNumeroPiante());
				numeroPianteMisura[i] = String.valueOf(orgNoc.getNumeroPiante());
				// Cerco la descrizione genere per i casi con la specie indicata
				if (orgNoc.getIdSpecie() != null) {
					logger.debug("-- Cerco la descrizione genere per i casi con la specie indicata");
					CodeDescriptionDTO genere = generiSpecieEJB.getGenereByIdSpecie(orgNoc.getIdSpecie());
					if (genere != null) {
						orgNocDto.setDenomGenere(genere.getDescr());
					}
					// Cerco la descrizione della specie
					logger.debug("-- Cerco la descrizione della specie");
					List<Long> idSpecieList = new ArrayList<Long>();
					idSpecieList.add(orgNoc.getIdSpecie());
					List<SpecieDTO> specieDto = generiSpecieEJB.getSpecieGenereByListIdSpecie(idSpecieList);
					if (specieDto != null) {
						orgNocDto.setDenomSpecie(specieDto.get(0).getDenomSpecie());
					}
				}
				// Cerco la descrizione genere per i casi con solo genere
				// specificato (famiglia)
				else {
					if (orgNoc.getIdGenere() != null) {
						logger.debug(
								"-- Cerco la descrizione genere per i casi con solo genere specificato (famiglia)");
						CodeDescriptionDTO genere = generiSpecieEJB.getGenereByIdGenere(orgNoc.getIdGenere());
						if (genere != null) {
							orgNocDto.setDenomGenere(genere.getDescr());
						}
					}
				}
				// Cerco la descrizione dell'organismo nocivo
				if (orgNoc.getIdOrgNocivo() != null) {
					CodeDescriptionDTO orgNocivo = generiSpecieEJB.getOrgNocivoByIdOrgNocivo(orgNoc.getIdOrgNocivo());
					orgNocDto.setDenomOrganismoNocivo(orgNocivo.getDescr());
				}
				organismiNocMisura.add(orgNocDto);
				i++;
			}
			form.setOrgNocivoGenereSpecie(organismiNocMisura);
			form.setNumeroPianteMisura(numeroPianteMisura);
		}

		// Custode
		form.setCognomeCustode(misura.getCongnomeCustode());
		form.setNomeCustode(misura.getNomeCustode());
		if (misura.getIdComuneNascitaCustode() != null) {
			form.setIdComNascitaCustode(misura.getIdComuneNascitaCustode());
			form.setIdProvNascitaCustode(domandeEJB.getIdProvinciaByIdComune(misura.getIdComuneNascitaCustode()));
		}
		form.setDataNascitaCustode(misura.getDataNascitaCustode());
		if (misura.getIdComuneResidenzaCustode() != null) {
			form.setIdComResidCustode(misura.getIdComuneResidenzaCustode());
			form.setIdProvResidCustode(domandeEJB.getIdProvinciaByIdComune(misura.getIdComuneResidenzaCustode()));
		}
		form.setIndirResidCustode(misura.getIndirizzoCustode());
		form.setTipoDocIdentificazCustode(misura.getTipoDocumento());
		form.setNumDocIdentificazCustode(misura.getCodDocumento());
		form.setDataEmissioneDocumento(misura.getDataEmissioneDocumento());
		form.setOrgEmissioneDocumento(misura.getOrgEmissioneDocumento());
		form.setIdQualificaCustode(misura.getIdTipoRespCustode());
		form.setPrescrizioniCustode(misura.getNoteCustode());

		// Persona di riferimento
		form.setDataConsegnaDisp(misura.getDataConsegnaDisposizione());
		form.setPersonaRifVerbale(misura.getPersConsegnaDisposizione());
		form.setIdTipoRespConsegnaDisp(misura.getIdTipoRespConsegnaDisp());
		form.setDichPersRifVerbale(misura.getNoteDichiarazione());

		// ----- Constatazione ------
		if (misura.getFlEsitoConstatazione() != null) {
			logger.debug("-- Sono presenti i dati della Constatazione");
			form.setIdConstatazionePresente("1");

			form.setDataConstMisura(misura.getDataConstatazione());
			form.setOraConstMisura(misura.getOraConstatazione());
			form.setNumVerbConstatazMisuraUff(String.valueOf(misura.getNumeroVerbaleCo()));

			// Ispettori constatazione
			Long[] idsIspettoreConstMisura = null;
			if (misuraUfficialeDto.getIspettoriConstatazione() != null) {
				idsIspettoreConstMisura = new Long[misuraUfficialeDto.getIspettoriConstatazione().size()];
				int i = 0;
				for (Iterator<CarRMisuraIspettore> iterator = misuraUfficialeDto.getIspettoriConstatazione()
						.iterator(); iterator.hasNext();) {
					CarRMisuraIspettore isp = (CarRMisuraIspettore) iterator.next();
					idsIspettoreConstMisura[i] = isp.getIdIspettore();
					i++;
				}
				form.setIdsIspettoreConstMisura(idsIspettoreConstMisura);
			}

			form.setFlEsitoMisura(misura.getFlEsitoConstatazione());
			form.setNoteConstMisura(misura.getNoteEsitoConst());

			// Persona presente durante la Constatazione
			form.setPersonaPresenteConst(misura.getPersRiferimentoConst());
			form.setIdQualificaPersonaPresenteConst(misura.getIdTipoRespAziendaConst());

			// Persona di riferimento per il verbale della Constatazione della
			// Misura ufficiale
			form.setDataConsegnaConst(misura.getDataConsegnaConst());
			form.setPersonaRifVerbaleConst(misura.getPersConsegnaConst());
			form.setIdTipoRespConsegnaConst(misura.getIdTipoRespConsegnaConst());
			form.setDichPersRifVerbaleConst(misura.getNoteDichiarazioneConst());
			form.setNotePersRifVerbaleConst(misura.getNoteConstatazione());
		}
		logger.debug("END setDatiMisuraUfficiale");
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = { "/misura/nuova/salva", "/misura/modifica/salva" })
	public String salvaDatiMisuraUfficiale(
			@Valid @ModelAttribute("nuovoControlloForm") NuovoControlloForm nuovoControlloForm,
			BindingResult bindResult, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN salvaDatiMisuraUfficiale");

		Long idControllo = nuovoControlloForm.getIdControllo();
		logger.debug("-- Salvo MISURA UFFICIALE per idControllo =" + idControllo);

		// -------- Salvataggio dati Parte Misura ufficiale DISPOSIZIONE e dati
		// Parte Misura ufficiale CONSTATAZIONE----------
		// Note : la validazione viene già fatta sulla jsp ; se viene checkata
		// la voce : 'Compila Misura ufficiale - Constatazione, i campi della
		// Constatazione sono obbligatori

		/*
		 * Recuperare i dati da salvare per Organismi nocivi, genere e specie 1)
		 * recupero gli idSpecie : recupero idGenere e idOrganismo nocivo e
		 * salvo dati (Casi di Genere non famiglia) 2) recupero gli idGenere e
		 * note e salvo (caso di Genere famiglia)
		 */

		String[] idsOrganismiNociviAggiunti = request.getParameterValues("idOrganismoNocivoMisura");
		String[] idsSpecieAggiunti = request.getParameterValues("idSpecieMisura");
		String[] idsGeneriAggiunti = request.getParameterValues("idGenereMisura");
		String[] noteAggiunte = request.getParameterValues("noteMisuraH");
		String[] numeroPianteArr = nuovoControlloForm.getNumeroPianteMisura();

		validator.validateDatiMisura(nuovoControlloForm, bindResult);
		if (!bindResult.hasErrors()) {

			try {
				// Formatto in un oggetto i dati presenti in tabella Motivo
				// della misura (organismo nocivo)
				List<OrgNocivoGenereSpecieDTO> listOrgNociviMisuraUfficiale = getDatiOrganismiNociviMisuraUfficiale(
						idsOrganismiNociviAggiunti, idsSpecieAggiunti, idsGeneriAggiunti, noteAggiunte,
						numeroPianteArr);
				nuovoControlloForm.setOrgNocivoGenereSpecie(listOrgNociviMisuraUfficiale);

				if (nuovoControlloForm.getIdControllo() != null) {
					Long idUtente = SecurityUtils.getUtenteLoggato().getId();
					if (nuovoControlloForm.getIdMisuraUfficiale() == null) {
						logger.debug("-- Caso di inserimento NUOVA MISURA UFFICIALE");
						controlliEJB.inserisciMisuraUfficiale(nuovoControlloForm, idUtente);
					} else {
						logger.debug("-- Caso di aggiornamento MISURA UFFICIALE con id_misura_ufficiale ="
								+ nuovoControlloForm.getIdMisuraUfficiale());
						controlliEJB.updateMisuraUfficiale(nuovoControlloForm, idUtente);
					}
				} else {
					addErrorMessage("Inserire prima i Dati Generali");
					return datiMisura(nuovoControlloForm, model, request);
				}
				if (isNuovoControllo(request)) {
					return getRedirect("misura/nuova", request);
				} else {
					return getRedirect("misura/modifica", request);
				}
			} catch (Exception ex) {
				addErrorMessage("Errore durante il salvataggio della Misura Ufficiale");
				logger.error("Errore durante il salvataggio della Misura Ufficiale" + ex.getMessage());
			} finally {
				logger.debug("-- END salvaDatiMisuraUfficiale");
			}
		}

		return datiMisura(nuovoControlloForm, model, request);
	}

	private List<OrgNocivoGenereSpecieDTO> getDatiOrganismiNociviMisuraUfficiale(String[] idsOrganismiNocAggiunti,
			String[] idsSpecieAggiunti, String[] idsGeneriAggiunti, String[] noteAggiunte, String[] numeroPianteArr)
			throws Exception {
		logger.debug("BEGIN getDatiOrganismiNociviMisuraUfficiale");

		List<OrgNocivoGenereSpecieDTO> orgNociviMisuraUffList = new ArrayList<OrgNocivoGenereSpecieDTO>();

		if (idsOrganismiNocAggiunti != null && idsOrganismiNocAggiunti.length > 0) {
			logger.debug("-- righe presenti nella tabella =" + idsOrganismiNocAggiunti.length);
			for (int i = 0; i < idsOrganismiNocAggiunti.length; i++) {
				OrgNocivoGenereSpecieDTO orgNoc = new OrgNocivoGenereSpecieDTO();

				// Setto idOrganismoNocivo
				logger.debug("-- idsOrganismiNocAggiunti[i] =" + idsOrganismiNocAggiunti[i]);
				orgNoc.setIdOrganismoNocivo(new Long(idsOrganismiNocAggiunti[i]));

				// Setto idGenere
				logger.debug("-- idsGeneriAggiunti[i] =" + idsGeneriAggiunti[i]);
				if (!idsGeneriAggiunti[i].equals("")) {
					orgNoc.setIdGenere(new Long(idsGeneriAggiunti[i]));
				}
				// Cerco il genere relativo alla specie
				else {
					if (!idsSpecieAggiunti[i].equals("")) {
						logger.debug("-- Cerco il genere relativo alla specie");
						CodeDescriptionDTO codDescGen = generiSpecieEJB
								.getGenereByIdSpecie(new Long(idsSpecieAggiunti[i]));
						if (codDescGen != null) {
							logger.debug("-- idGenere =" + codDescGen.getId());
							orgNoc.setIdGenere(codDescGen.getId());
						}
					}
				}

				// Setto idSpecie
				logger.debug("-- idsSpecieAggiunti[i] =" + idsSpecieAggiunti[i]);
				if (!idsSpecieAggiunti[i].equals("")) {
					orgNoc.setIdSpecie(new Long(idsSpecieAggiunti[i]));
				}

				// Setto Note
				logger.debug("-- noteAggiunte[i] =" + noteAggiunte[i]);
				orgNoc.setNote(noteAggiunte[i]);

				// Setto Numero Piante
				logger.debug("-- numeroPianteArr[i] =" + numeroPianteArr[i]);
				if (numeroPianteArr[i] != null && !numeroPianteArr[i].equals(""))
					orgNoc.setNumeroPiante(new Long(numeroPianteArr[i]));

				orgNociviMisuraUffList.add(orgNoc);
			}
		}

		logger.debug("---- Numero di organismi nocivi MISURA UFFICIALE da inserire =" + orgNociviMisuraUffList.size());
		logger.debug("END getDatiOrganismiNociviMisuraUfficiale");
		return orgNociviMisuraUffList;
	}

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = "/datiBase/modifica/{id}")
	public String modificaDatiBase(@PathVariable Long id, @ModelAttribute("nuovoControlloForm") NuovoControlloForm form,
			Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- BEGIN modificaDatiBase");
		String messaggioErrore = null;

		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();
			logger.debug("-- idControllo =" + id);
			form.setIdControllo(id);
			CarTControllo controllo = controlliEJB.getControllo(id);
			form.setAltreNorme(controllo.getAltreNorme());
			form.setSoggettiSupportoSopralluogo(controllo.getAltriSoggetti());
			form.setDataControllo(controllo.getDataControllo());
			form.setIdCentroAziendale(controllo.getIdCentroAziendale());
			form.setIdQualifica(controllo.getIdTipoRespAzienda());
			form.setMonitoraggioCofinanziato(controllo.getMonitoraggioCofinanziato());
			form.setOraFineControllo(controllo.getOraFineControllo());
			form.setOraInizioControllo(controllo.getOraInizioControllo());
			form.setResponsabileAzienda(controllo.getResponsabileAzienda());
			form.setIdTipoComunicazione(controllo.getIdTipoComunicazione());
			form.setIdStatoComunicazione(controllo.getIdStatoComunicazione());

			form.setMisuraUfficiale(controllo.getMisuraUfficiale());

			Long idVersioneControllo = controlliEJB.checkVersioneControllo(form.getIdControllo());
			logger.debug("-- idVersioneControllo = " + idVersioneControllo);
			form.setIdVersioneControllo(idVersioneControllo);

			// Controllo se deve essere visualizzato il Tab Campione
			// Se nel Tab Fisico è stato settato a SI il controllo 5X23, deve
			// essere visualizzato il tab Campioni
			CarTControlloFisico controlloFisico = controlliEJB.getControlloFisicoByIdControllo(id);
			if (controlloFisico != null) {
				form.setFlControllo5x23(controlloFisico.getFlControllo5x23());
			}

			// car_r_controllo_ispettore
			List<CarRControlloIspettore> ispettori = controlliEJB.getIspettoriByIdControllo(id);
			String[] idIspettori = new String[ispettori.size()];
			int i = 0;
			for (CarRControlloIspettore ispettore : ispettori) {
				idIspettori[i] = ispettore.getIdIspettore().toString();
				i++;
			}
			form.setIdsIspettore(idIspettori);

			// car_r_controllo_norma_verbale
			List<CarRControlloNormaVerbale> normeVerbali = controlliEJB.getNormeVerbaliByIdControllo(id);
			String[] idNormeVerbali = new String[normeVerbali.size()];
			i = 0;
			for (CarRControlloNormaVerbale normaVerbale : normeVerbali) {
				idNormeVerbali[i] = normaVerbale.getIdNormaVerbale().toString();
				i++;
			}
			form.setIdsNormaVerbale(idNormeVerbali);

			// car_r_controllo_tipologia
			List<CarRControlloTipologia> tipologie = controlliEJB.getControlloTipologiaByIdControllo(id);
			String[] idTipologie = new String[tipologie.size()];
			i = 0;
			for (CarRControlloTipologia tipologia : tipologie) {
				if (tipologia.getIdTipologiaControllo() != 4L) {
					idTipologie[i] = tipologia.getIdTipologiaControllo().toString();
					i++;
				}
				if (tipologia.getNote() != null) {
					form.setAltroControllo(tipologia.getNote());
				}
			}
			form.setIdsTipologiaControllo(idTipologie);

			setTabDaVisualizzare(form);

			// car_r_controllo_tipo_semente
			// siccome usiamo l'index nel jsp, è necessario che la lista delle
			// sementi del db sia lunga come lista delle decodifiche
			List<CarDTipologiaSemente> elencoSementi = decodificheEJB
					.getListaTipologiaSementiByIdVersioneControllo(form.getIdVersioneControllo());
			List<SementeDTO> sementiDB = controlliEJB.getSementiByIdControllo(id);
			List<SementeDTO> sementi = new ArrayList<SementeDTO>();
			boolean trovato = false;
			for (CarDTipologiaSemente tipoSemente : elencoSementi) {
				logger.debug("-- semente.getIdTipologiaSemente() = " + tipoSemente.getIdTipologiaSemente());
				trovato = false;
				SementeDTO sem = new SementeDTO();
				for (SementeDTO semente : sementiDB) {
					if (tipoSemente.getIdTipologiaSemente().equals(semente.getIdTipologiaSemente())) {
						sem.setIdTipologiaSemente(semente.getIdTipologiaSemente());
						sem.setQuantita(semente.getQuantita());
						sem.setNote(semente.getNote() != null ? semente.getNote() : null);
						trovato = true;
						break;
					}
				}
				if (!trovato) {
					sem.setIdTipologiaSemente(null);
					sem.setQuantita(null);
					sem.setNote(null);
				}
				sementi.add(sem);
			}
			form.setSementi(sementi);
			logger.debug("-- sementi.size = " + sementi.size());

		} catch (Exception e) {
			logger.error("-- Exception in modificaDatiBase =" + e.getMessage());
			messaggioErrore = "Errore nella modifica della domanda";
			throw new BusinessException(e.getMessage());
		}

		if (messaggioErrore != null) {
			addErrorMessage(messaggioErrore);
			// return getRedirect("comunicazioni/elenco", request);
		}

		return nuovoControllo(form, model, request);
	}

	private boolean isNuovoControllo(HttpServletRequest request) {
		return request == null ? false : request.getRequestURI().contains("/nuova");
	}

	@GetMapping(value = "/getGenereByIdSpecie_{idSpecie}")
	@ResponseBody
	public String getGenereByIdSpecie(@PathVariable("idSpecie") Long idSpecie, Model model) throws BusinessException {
		logger.debug("BEGIN getGenereByIdSpecie");
		CodeDescriptionDTO genere = null;
		String descrGenere = "";
		if (idSpecie != null) {
			genere = generiSpecieEJB.getGenereByIdSpecie(idSpecie);
		}

		if (genere != null) {
			descrGenere = genere.getDescr();
		}
		logger.debug("END getGenereByIdSpecie");
		return descrGenere;
	}

	@GetMapping(value = "/isTipoGenereFamiglia_{descrGenere}")
	@ResponseBody
	public String isTipoGenereFamiglia(@PathVariable("descrGenere") String descrGenere, Model model)
			throws BusinessException {
		logger.debug("BEGIN isTipoGenereFamiglia");
		String isGenereFamiglia = "N";
		if (descrGenere != null && !descrGenere.trim().equals("")) {
			boolean isTipoGenereFamiglia = decodificheEJB.isTipoGenereFamiglia(descrGenere);
			logger.debug("-- isTipoGenereFamiglia =" + isTipoGenereFamiglia);
			if (isTipoGenereFamiglia) {
				isGenereFamiglia = "S";
			}
		}
		logger.debug("END isTipoGenereFamiglia");
		return isGenereFamiglia;
	}

	public byte[] generaPdfVerbaleDisposizioneMisuraUff(Long id, Model model, HttpServletRequest request) {
		try {
			byte[] data = null;
			String filename = null;

			Long idAssociazioneSezione = getSezioneRequest(request);
			logger.debug("idAssociazioneSezione =" + idAssociazioneSezione);

			if (idAssociazioneSezione == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI) {
				logger.debug("\n\n generaPdfVerbaleDisposizioneMisuraUff idControllo =" + id);

				List<byte[]> listTemplateVerbaleDisp = new ArrayList<byte[]>();
				listTemplateVerbaleDisp.add(controlliEJB
						.getTemplateTipoStampaControlloById(CaronteConstants.ID_TIPO_STAMPA_VERBALE_DISPOSIZIONE_MU));
				listTemplateVerbaleDisp.add(controlliEJB
						.getTemplateTipoStampaControlloById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_MISURA_APPL_DISP));
				listTemplateVerbaleDisp.add(controlliEJB
						.getTemplateTipoStampaControlloById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_GENERI_SPECIE_DISP));
				String[] listNomiMU = { "id_controllo", "subreportMisuraApplicata", "subreportGeneriSpecie" };

				data = StampeManager.getPdfVerbaleMisuraUff(id, listTemplateVerbaleDisp, listNomiMU);
			}

			return data;

		} catch (Exception e) {
			logger.error("Errore nella generazione del pdf :" + e.getMessage());
			addErrorMessage("Errore nella generazione del pdf");
		}
		return null;
	}

	@GetMapping(value = "misura/stampadisposizione/{idControllo}", produces = "application/pdf")
	public void stampaVerbaleDisposizioneMisuraUff(@PathVariable Long idControllo, Model model,
			HttpServletRequest request, HttpServletResponse response) throws BusinessException, IOException {
		logger.debug("-- BEGIN nuovoControllo - stampaVerbaleIspezione");

		Long idUtente = SecurityUtils.getUtenteLoggato().getId();
		MisuraUfficialeDTO misura = controlliEJB.getMisuraUfficiale(idControllo);
		Long idMisuraUff = misura.getMisuraUfficiale().getIdMisuraUfficiale();
		Long numeroVerbaleDisposizione = misura.getMisuraUfficiale().getNumeroVerbaleMf();
		byte[] pdf = generaPdfVerbaleDisposizioneMisuraUff(idControllo, model, request);
		String fileName = controlliEJB.salvaStampaDisposizione(idMisuraUff, pdf, idUtente, numeroVerbaleDisposizione);

		response.resetBuffer();
		response.setContentType("application/pdf");
		LocalDateTime dataStampa = LocalDateTime.now();
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
		response.setContentLength(pdf.length);
		response.getOutputStream().write(pdf);
		response.getOutputStream().flush();
	}

	public byte[] generaPdfVerbaleConstatazioneMisuraUff(Long id, Model model, HttpServletRequest request) {
		try {
			byte[] data = null;
			String filename = null;

			Long idAssociazioneSezione = getSezioneRequest(request);
			logger.debug("idAssociazioneSezione =" + idAssociazioneSezione);

			if (idAssociazioneSezione == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI) {
				logger.debug("\n\n generaPdfVerbaleConstatazioneMisuraUff idControllo =" + id);

				List<byte[]> listTemplateVerbaleConst = new ArrayList<byte[]>();
				listTemplateVerbaleConst.add(controlliEJB
						.getTemplateTipoStampaControlloById(CaronteConstants.ID_TIPO_STAMPA_VERBALE_CONSTATAZIONE_MU));
				String[] listNomiMU = { "id_controllo" };

				data = StampeManager.getPdfVerbaleMisuraUff(id, listTemplateVerbaleConst, listNomiMU);
			}

			return data;

		} catch (Exception e) {
			logger.error("Errore nella generazione del pdf :" + e.getMessage());
			addErrorMessage("Errore nella generazione del pdf");
		}
		return null;
	}

	@GetMapping(value = "misura/stampaconstatazione/{idControllo}", produces = "application/pdf")
	public void stampaVerbaleConstatazioneMisuraUff(@PathVariable Long idControllo, Model model,
			HttpServletRequest request, HttpServletResponse response) throws BusinessException, IOException {
		logger.debug("-- BEGIN nuovoControllo - stampaVerbaleIspezione");

		Long idUtente = SecurityUtils.getUtenteLoggato().getId();
		MisuraUfficialeDTO misura = controlliEJB.getMisuraUfficiale(idControllo);
		Long idMisuraUff = misura.getMisuraUfficiale().getIdMisuraUfficiale();
		Long numeroVerbaleConstatazione = misura.getMisuraUfficiale().getNumeroVerbaleCo();
		logger.debug("numeroVerbaleConstatazione: " + numeroVerbaleConstatazione);
		byte[] pdf = generaPdfVerbaleConstatazioneMisuraUff(idControllo, model, request);
		String fileName = controlliEJB.salvaStampaConstatazione(idMisuraUff, pdf, idUtente, numeroVerbaleConstatazione);

		response.resetBuffer();
		response.setContentType("application/pdf");
		LocalDateTime dataStampa = LocalDateTime.now();
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
		response.setContentLength(pdf.length);
		response.getOutputStream().write(pdf);
		response.getOutputStream().flush();
	}

	// Dettaglio controllo
	@PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = "/dettaglio/{id}")
	public String getDettaglioControllo(@PathVariable Long id,
			@ModelAttribute("nuovoControlloForm") NuovoControlloForm form, Model model, HttpServletRequest request)
			throws BusinessException {
		logger.debug("BEGIN getDettaglioControllo");
		try {
			form.setIdControllo(id);

			// Versione
			Long idVersioneControllo = controlliEJB.checkVersioneControllo(form.getIdControllo());
			logger.debug("-- idVersioneControllo = " + idVersioneControllo);
			form.setIdVersioneControllo(idVersioneControllo);

			// DATIBASE
			CarTControllo controllo = controlliEJB.getControllo(form.getIdControllo());
			model.addAttribute("controllo", controllo);
			logger.debug("-- *** idControllo = " + form.getIdControllo());

			// Setto quali sono i tab da visualizzare
			// Tab Misura ufficiale
			form.setMisuraUfficiale(controllo.getMisuraUfficiale());
			// Tab Documentale, Fisico, Identità
			setTabDaVisualizzare(form);

			CarTCentroAziendale centroAziendale = controlliEJB.getCentroAziendaleByIdControllo(form.getIdControllo());
			model.addAttribute("centroAziendale", centroAziendale);
			logger.debug("-- idCentroAz =" + centroAziendale.getIdCentroAziendale());

			List<IspettoreDTO> listaIspettoriDatiBase = controlliEJB
					.getIspettoriControlloByIdControllo(form.getIdControllo());
			model.addAttribute("listaIspettoriDatiBase", listaIspettoriDatiBase);

			SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniere(centroAziendale.getIdSpedizioniere());
			model.addAttribute("spedizioniere", spedizioniere);
			logger.debug("-- idSpedizioniere =" + spedizioniere.getIdSpedizioniere());
			logger.debug("++8++0++ tipologia attivita annotazione= " + spedizioniere.getTipologiaAttivita());
			if (centroAziendale.getIdComune() != null) {
				CarDComune comune = decodificheEJB.getComuneByPrimaryKey(centroAziendale.getIdComune());
				model.addAttribute("comuneCentroAziendale", comune.getDenomComune());
				CarDProvincia provincia = decodificheEJB.getProvinciaByIdProv(comune.getIdProvincia());
				model.addAttribute("provinciaCentroAziendale", provincia.getDenomProvincia());
			}
			if (spedizioniere.getIdComune() != null) {
				CarDComune comune = decodificheEJB.getComuneByPrimaryKey(spedizioniere.getIdComune());
				model.addAttribute("comuneSpedizioniere", comune.getDenomComune());
				CarDProvincia provincia = decodificheEJB.getProvinciaByIdProv(comune.getIdProvincia());
				model.addAttribute("provinciaSpedizioniere", provincia.getDenomProvincia());
			}

			List<CarDNormaVerbale> listaNorme = controlliEJB.getListaNormeVerbaliByIdControllo(form.getIdControllo());
			model.addAttribute("listaNorme", listaNorme);

			Long idDomanda = controlliEJB.getMaxIdDomandaValidaByIdCentroAz(centroAziendale.getIdCentroAziendale());
			List<TipologiaAttMaterialeDTO> tipologieAttMaterialidb = controlliEJB
					.getTipologieAttMaterialeDomandeRuop(idDomanda);
			model.addAttribute("tipologieAttDb", tipologieAttMaterialidb);

			model.addAttribute("abilitaSementi",
					controlliEJB.checkAbilitaSementi(centroAziendale.getIdCentroAziendale()));
			List<SementeDTO> listaSementi = controlliEJB.getListaSementiByIdControllo(form.getIdControllo());
			model.addAttribute("listaSementi", listaSementi);
			if (controllo.getIdTipoRespAzienda() != null) {
				model.addAttribute("qualificaResponsabile",
						decodificheEJB.getQualificaByIdTipoRespAzienda(controllo.getIdTipoRespAzienda()));
			}

			List<CarDTipologiaControllo> listaTipologiaControllo = controlliEJB
					.getListeTipologiaControlloByIdControllo(form.getIdControllo());
			model.addAttribute("listaTipologiaControllo", listaTipologiaControllo);

			// CONTROLLO DOCUMENTALE
			CarTControlloDocumentale controlloDocumentale = controlliEJB
					.getControlloDocumentaleByIdControllo(form.getIdControllo());
			model.addAttribute("controlloDocumentale", controlloDocumentale);

			// CONTROLLO IDENTITA
			CarTControlloIdentita controlloIdentita = controlliEJB
					.getControlloIdentitaByIdControllo(form.getIdControllo());
			model.addAttribute("controlloIdentita", controlloIdentita);
			if (controlloIdentita != null && controlloIdentita.getIdControlloIdentita() != null) {
				List<SitoProduzioneDTO> sitiProduzioneDb = controlliEJB
						.getSitiProduzioneByIdControlloIdentita(controlloIdentita.getIdControlloIdentita());
				model.addAttribute("sitiProduzioneDb", sitiProduzioneDb);
			}

			// CONTROLLO FISICO
			CarTControlloFisico controlloFisico = controlliEJB.getControlloFisicoByIdControllo(form.getIdControllo());
			if (controlloFisico != null) {
				form.setFlControllo5x23(controlloFisico.getFlControllo5x23());
			}
			model.addAttribute("controlloFisico", controlloFisico);
			// Carico la combo Organismo nocivo da ricercare
			logger.debug("-- Carico la combo Organismo nocivo");
			if (controlloFisico != null) {
				model.addAttribute("listaOrgNoc",
						controlliEJB.getListaOrgNocByIdControlloFisico(controlloFisico.getIdControlloFisico()));
			}

			logger.debug("-- Recupero i dati del controllo fisico");
			// potrebbe non essere stato compilato il controllo fisico
			if (controlloFisico != null) {
				logger.debug(
						"-- Controllo fisico presente IdControlloFisico= " + controlloFisico.getIdControlloFisico());
				List<CarDStrutturaAttrezzatura> listaStruttureAttrezzature = controlliEJB
						.getListaStruttureAttrezzatureByIdControlloFisico(controlloFisico.getIdControlloFisico());
				model.addAttribute("listaStruttureAttrezzature", listaStruttureAttrezzature);
				List<CarDMetodoProduzione> listaMetodiProduzione = controlliEJB
						.getMetodiProduzioneByIdControlloFisico(controlloFisico.getIdControlloFisico());
				model.addAttribute("listaMetodiProduzione", listaMetodiProduzione);
				List<CarDTipoIrrigazione> listaTipiIrrigazione = controlliEJB
						.getListaTipiIrrigazioneByIdControlloFisico(controlloFisico.getIdControlloFisico());
				model.addAttribute("listaTipiIrrigazione", listaTipiIrrigazione);
				model.addAttribute("abilitaSementi",
						controlliEJB.checkAbilitaSementi(centroAziendale.getIdCentroAziendale()));
				List<CarDConoscenzaProfessionale> listaConoscenzeProfessionali = controlliEJB
						.getListaConoscenzeProfessionaliByIdControlloFisico(controlloFisico.getIdControlloFisico());
				model.addAttribute("listaConoscenzeProfessionali", listaConoscenzeProfessionali);
				List<RequisitiProfessionaliDTO> listaRequisitiProfessionali = controlliEJB
						.getListaRequisitiProfessionaliByIdControlloFisico(controlloFisico.getIdControlloFisico());
				model.addAttribute("listaRequisitiProfessionali", listaRequisitiProfessionali);
				List<GenereSpecieDTO> listGenereSpecieDB = controlliEJB
						.getGenereSpecieByIdControlloFisisco(controlloFisico.getIdControlloFisico());
				model.addAttribute("listGenereSpecieDB", listGenereSpecieDB);

				// carico la lista organismi nocivi (PER IL CAMPO 5.11)
				model.addAttribute("listaON",
						controlliEJB.getListaOrgNocByIdControlloFisico(controlloFisico.getIdControlloFisico()));

			}
			// CAMPIONI
			logger.debug("-- Ricerco i campioni da visualizzare nella tabella");
			List<CampioneDTO> campioniList = controlliEJB.getListaCampioniByIdControllo(form.getIdControllo());
			// Ciclo sui campioni e setto la stringa con le descrizioni degli
			// organismi nocivi da ricercare
			if (campioniList != null && campioniList.size() > 0) {
				logger.debug(
						"-- Ciclo sui campioni e setto la stringa con le descrizioni degli organismi nocivi da ricercare");
				for (Iterator<CampioneDTO> iterator = campioniList.iterator(); iterator.hasNext();) {
					CampioneDTO campioneDTO = (CampioneDTO) iterator.next();
					if (campioneDTO != null) {
						if (campioneDTO.getOrgNociviDaRicercareList() != null
								&& campioneDTO.getOrgNociviDaRicercareList().size() > 0) {
							String descrOrgNocDaRicercare = "";
							for (Iterator<CodeDescriptionDTO> iterator2 = campioneDTO.getOrgNociviDaRicercareList()
									.iterator(); iterator2.hasNext();) {
								CodeDescriptionDTO orgNoc = (CodeDescriptionDTO) iterator2.next();
								if (orgNoc != null) {
									descrOrgNocDaRicercare += orgNoc.getDescr();
									if (iterator2.hasNext()) {
										descrOrgNocDaRicercare += ", ";
									}
								}
							}
							logger.debug("-- descrOrgNocDaRicercare =" + descrOrgNocDaRicercare);
							campioneDTO.setDescrElencoOrgNocDaRicercare(descrOrgNocDaRicercare);
						}
					}
				}
				model.addAttribute("campioniDb", campioniList);
				model.addAttribute("flAnalisi", campioniList.get(0).getFlAnalisi());
				model.addAttribute("flBanda", campioniList.get(0).getFlBanda());
				model.addAttribute("flSacchetti", campioniList.get(0).getFlSacchetti());
			}

			// ALLEGATI
			model.addAttribute("listaAllegati", controlliEJB.getListaAllegatiControllo(form.getIdControllo()));

			// MONITORAGGIO

			// MISURA
			MisuraUfficialeDTO misuraUfficiale = controlliEJB.getMisuraUfficiale(form.getIdControllo());
			if (misuraUfficiale != null) {
				model.addAttribute("misuraUfficiale", misuraUfficiale.getMisuraUfficiale());
				if (misuraUfficiale.getMisuraUfficiale().getIdComuneNascitaCustode() != null) {
					CarDComune comuneN = decodificheEJB
							.getComuneByPrimaryKey(misuraUfficiale.getMisuraUfficiale().getIdComuneNascitaCustode());
					model.addAttribute("comuneNascitaCustode", comuneN.getDenomComune());
					CarDProvincia provinciaN = decodificheEJB.getProvinciaByIdProv(comuneN.getIdProvincia());
					model.addAttribute("provinciaNascitaCustode", provinciaN.getDenomProvincia());

					CarDComune comuneR = decodificheEJB
							.getComuneByPrimaryKey(misuraUfficiale.getMisuraUfficiale().getIdComuneResidenzaCustode());
					model.addAttribute("comuneResidenzaCustode", comuneR.getDenomComune());
					CarDProvincia provinciaR = decodificheEJB.getProvinciaByIdProv(comuneR.getIdProvincia());
					model.addAttribute("provinciaResidenzaCustode", provinciaR.getDenomProvincia());
				}
				if (misuraUfficiale.getMisuraUfficiale().getIdTipoRespCustode() != null) {
					model.addAttribute("qualificaCustode", decodificheEJB.getQualificaByIdTipoRespAzienda(
							misuraUfficiale.getMisuraUfficiale().getIdTipoRespCustode()));
				}
				if (misuraUfficiale.getMisuraUfficiale().getIdTipoRespAziendaConst() != null) {
					model.addAttribute("qualificaPersPresConstataz", decodificheEJB.getQualificaByIdTipoRespAzienda(
							misuraUfficiale.getMisuraUfficiale().getIdTipoRespAziendaConst()));
				}
				if (misuraUfficiale.getMisuraUfficiale().getIdTipoRespConsegnaConst() != null) {
					model.addAttribute("tipoRespConsegnaConst", decodificheEJB.getQualificaByIdTipoRespAzienda(
							misuraUfficiale.getMisuraUfficiale().getIdTipoRespConsegnaConst()));
				}
				if (misuraUfficiale.getMisuraUfficiale().getIdTipoRespConsegnaDisp() != null) {
					model.addAttribute("tipoRespConsegnaDisp", decodificheEJB.getQualificaByIdTipoRespAzienda(
							misuraUfficiale.getMisuraUfficiale().getIdTipoRespConsegnaDisp()));
				}
				List<MisuraDTO> listaTipologieMisura = controlliEJB.getListaTipologieMisuraByIdMisuraUfficiale(
						misuraUfficiale.getMisuraUfficiale().getIdMisuraUfficiale());

				List<IspettoreDTO> listaIspettoriDisp = controlliEJB
						.getIspettoriDispByIdMisura(misuraUfficiale.getMisuraUfficiale().getIdMisuraUfficiale());
				model.addAttribute("listaIspettoriDisp", listaIspettoriDisp);
				List<IspettoreDTO> listaIspettoriConst = controlliEJB
						.getIspettoriConstByIdMisura(misuraUfficiale.getMisuraUfficiale().getIdMisuraUfficiale());
				model.addAttribute("listaIspettoriConst", listaIspettoriConst);
				model.addAttribute("listaTipologieMisura", listaTipologieMisura);
				setDatiMisuraUfficiale(form, misuraUfficiale);
			}
			// ESITO
			model.addAttribute("esito", controlliEJB.getEsitoByIdControllo(form.getIdControllo()));

		} catch (Exception exc) {
			logger.error("-- Exception in getDettaglioControllo =" + exc.getMessage());
			addErrorMessage("Errore nella ricerca della comunicazione");
			throw new BusinessException(exc.getMessage());
		}

		return "dettaglio";
	}

	@GetMapping(value = { "/campioni/editCampioni", "/campioni/editCampioni/{idCampione}" })
	public String modificaCampioniModale(@PathVariable(required = false) Long idCampione, Model model,
			@ModelAttribute("modaliForm") ModaliForm form,
			@ModelAttribute("nuovoControlloForm") NuovoControlloForm controlloform, HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {

		// Carico Combo Esiti
		logger.debug("-- Carico la combo Esiti");
		List<CodeDescriptionDTO> esitiList = new ArrayList<CodeDescriptionDTO>();
		CodeDescriptionDTO esito = new CodeDescriptionDTO();
		esito.setCod(CaronteConstants.COD_ASSENTE);
		esito.setDescr(CaronteConstants.DESCR_ASSENTE);
		esitiList.add(esito);
		esito = new CodeDescriptionDTO();
		esito.setCod(CaronteConstants.COD_PRESENTE);
		esito.setDescr(CaronteConstants.DESCR_PRESENTE);
		esitiList.add(esito);
		model.addAttribute("listaEsitiRdp", esitiList);

		// Carico la combo Organismo nocivo accertato
		logger.debug("-- Carico la combo Organismo nocivo accertato");
		model.addAttribute("listaOrgNocAccertato", decodificheEJB.getListaOrganismiNocivi());

		logger.debug("-- Ricerco i dati RDP e gli organismi nocivi accertati se erano già stati inseriti");
		List<CampioneDTO> campioniList = controlliEJB.getListaCampioniByIdControllo(controlloform.getIdControllo());
		if (campioniList != null && campioniList.size() > 0) {
			for (Iterator<CampioneDTO> iterator = campioniList.iterator(); iterator.hasNext();) {
				CampioneDTO campioneDTO = (CampioneDTO) iterator.next();
				if (campioneDTO != null && campioneDTO.getIdCampione().equals(idCampione)) {
					form.setDataRdp(campioneDTO.getDataRdp());
					form.setEsitoRdp(campioneDTO.getEsitoRdp());
					form.setNumeroRdp(campioneDTO.getNumRdp());

					form.setFlAnalisi(campioneDTO.getFlAnalisi());
					form.setFlBanda(campioneDTO.getFlBanda());
					form.setFlSacchetti(campioneDTO.getFlSacchetti());

					logger.debug("NON VEDO LE NOTEEEEEE: " + campioneDTO.getNote());
					form.setNoteCampione(campioneDTO.getNote());
					// recupero anche gli idOrgNocAccertati selezionati in
					// precedenza
					if (campioneDTO.getOrgNociviAccertatiList() != null
							&& campioneDTO.getOrgNociviAccertatiList().size() > 0) {
						int i = 0;
						Long[] idsOrgNocivoAcc = new Long[campioneDTO.getOrgNociviAccertatiList().size()];
						for (Iterator<CodeDescriptionDTO> iterator2 = campioneDTO.getOrgNociviAccertatiList()
								.iterator(); iterator2.hasNext();) {
							CodeDescriptionDTO orgNoc = (CodeDescriptionDTO) iterator2.next();
							if (orgNoc != null) {
								idsOrgNocivoAcc[i] = orgNoc.getId();
								i++;
							}
						}
						form.setOrgNocAccertato(idsOrgNocivoAcc);
					}
				}
			}
			model.addAttribute("campioniDb", campioniList);
		}

		return "campioni/editCampioni";
	}

	@PostMapping(value = "/campioni/editCampioni")
	public String salvaModificaCampione(Model model,
			@ModelAttribute("nuovoControlloForm") NuovoControlloForm controlloform,
			@ModelAttribute("modaliForm") ModaliForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
		UtenteDTO utente = SecurityUtils.getUtenteLoggato();

		// Controllo che sia stata inserita la domanda (dati precendenti)
		if (controlloform.getIdControllo() == null) {
			logger.debug("-- Bloccare l'utente, deve prima inserire i dati del controllo");
			addErrorMessage("Attenzione : inserire prima i dati generali");
			return "campioni/editCampioni";
		}

		if (form.getIdCampione() != null) {
			logger.debug("-- aggiorno il campione");
			controlliEJB.updateCampione(controlloform, form, utente.getId());
			puliscoFormCampione(controlloform);
		} else {
			logger.debug("-- Bloccare l'utente, id campione non presente");
			return "campioni/editCampioni";
		}

		logger.debug("-- Sono passato dal controller salvaModificaCampione");
		return null;
	}

	/*
	 * Per la cancellazione comunicazione all'interno di un flusso
	 */
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/elimina/{id}" })
	public String eliminaControllo(@PathVariable Long id, @ModelAttribute("nuovoControlloForm") NuovoControlloForm form,
			Model model, HttpServletRequest request) throws BusinessException {

		logger.debug("BEGIN eliminaControllo");
		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();
			logger.debug("---- idControllo =" + id);
			controlliEJB.eliminaControllo(id, utente.getId());

		} catch (Exception e) {
			logger.error("-- Exception in eliminaControllo =" + e.getMessage());
			addErrorMessage("Errore nell'eliminazione del controllo");
		} finally {
			logger.debug("END eliminaControllo");
		}

		return getRedirect("centroaziendale/modifica", request);
	}

}
