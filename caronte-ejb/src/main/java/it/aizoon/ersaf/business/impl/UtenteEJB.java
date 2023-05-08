package it.aizoon.ersaf.business.impl;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.IspettoreDTO;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarDAssociazioneSezione;
import it.aizoon.ersaf.dto.generati.CarRSpedizAssocSezione;
import it.aizoon.ersaf.dto.generati.CarRSpedizAssocSezioneExample;
import it.aizoon.ersaf.dto.generati.CarRUtenteGruppoExample;
import it.aizoon.ersaf.dto.generati.CarRUtenteSpedizionieri;
import it.aizoon.ersaf.dto.generati.CarTCentroAziendale;
import it.aizoon.ersaf.dto.generati.CarTIspettore;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniereExample;
import it.aizoon.ersaf.dto.generati.CarTUtente;
import it.aizoon.ersaf.dto.generati.CarTUtenteExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.IspettoreForm;
import it.aizoon.ersaf.form.NuovaDomandaForm;
import it.aizoon.ersaf.form.SpedizioniereForm;
import it.aizoon.ersaf.form.UtenteForm;
import it.aizoon.ersaf.integration.UtenteDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.UtenteMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRDomandaCentroAzMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRSpedizAssocSezioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRUtenteGruppoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRUtenteSpedizionieriMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTCentroAziendaleMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTIspettoreMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTSpedizioniereMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTUtenteMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

@Stateless(name = "Utente"/* , mappedName = "java:app/Utente" */)
// @EJB(name = "java:app/Utente", beanInterface = IUtenteEJB.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
// @Interceptors(SpringBeanAutowiringInterceptor.class)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })

public class UtenteEJB extends AbstractEJB<UtenteDAO, UtenteMapper, BaseDto, GenericExample> implements IUtenteEJB {

	private final String THIS_CLASS = UtenteEJB.class.getCanonicalName();

	@Inject
	UtenteMapper utenteMapper;

	@Inject
	CarTSpedizioniereMapper spedizioniereMapper;

	@Inject
	CarTUtenteMapper carTUtenteMapper;

	@Inject
	CarTIspettoreMapper carTIspettoreMapper;

	@Inject
	CarRSpedizAssocSezioneMapper spedizioniereSezioneMapper;

	@Inject
	CarRUtenteSpedizionieriMapper utenteSpedizMapper;

	@Inject
	CarTCentroAziendaleMapper carTCentroAziendaleMapper;
	
	@Inject
	CarRDomandaCentroAzMapper carRDomandaCentroAzMapper;

	@Inject
	CarRSpedizAssocSezioneMapper carRSpedizAssocSezioneMapper;
	
	@Inject
	CarRUtenteGruppoMapper carRUtenteGruppoMapper;


	@Override
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public String getUtenteProva() throws BusinessException {
		return utenteMapper.getUtenteById(1l);
	}

	@Override
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public CarTSpedizioniere getCarTSpedizioniere(Long idSpedizioniere) throws BusinessException {
		return spedizioniereMapper.selectByPrimaryKey(idSpedizioniere);
	}

	@Override
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public List<UtenteDTO> getElencoUtenti(UtenteForm utenteForm) throws BusinessException {

		return utenteMapper.getElencoUtenti(utenteForm);
	}

	public Long insertUtenteIspettore(UtenteForm nuovoUtente) throws BusinessException {
		logger.debug("BEGIN insertUtenteIspettore");
		CarTUtente utente = new CarTUtente();

		if (null != nuovoUtente) {
			utente.setAbilitato(true); // di default perché inserito da un
										// amministratore
			utente.setNome(nuovoUtente.getNome());
			utente.setCognome(nuovoUtente.getCognome());
			utente.setDataInserimento(new Date());
			utente.setEmail(nuovoUtente.getEmail());
			utente.setCodiceFiscale(nuovoUtente.getCodiceFiscale());
			utente.setNote(nuovoUtente.getNote());
			utente.setModificatoAdmin(true);

			if (nuovoUtente.getDenomSpedizioniere() != null) {
				CarTSpedizioniere sped = getSpedizioniereByDenomSpedizioniere(nuovoUtente.getDenomSpedizioniere());
				utente.setIdSpedizioniere(sped.getIdSpedizioniere());
			}
		}

		carTUtenteMapper.insertSelective(utente);
		nuovoUtente.setIdUtente(utente.getIdUtente());
		logger.debug("-- inserito utente in CAR_T_UTENTE con idUtente =" + utente.getIdUtente());

		// se inserisco un ispettore devo modificare le associazioni sezione in
		// base
		// ai flag checkati dall'utente
		insertGruppiUtente(nuovoUtente);

		logger.debug("END insertUtenteIspettore");
		return utente.getIdUtente();
	}

	@Override
	public Long insertUtente(UtenteForm nuovoUtente) throws BusinessException {
		CarTUtente utente = new CarTUtente();

		if (null != nuovoUtente) {

			utente.setAbilitato(true); // di default perché inserito da un
										// amministratore
			utente.setRifiutato(false);
			utente.setNome(nuovoUtente.getNome());
			utente.setCognome(nuovoUtente.getCognome());
			utente.setDataInserimento(new Date());
			utente.setEmail(nuovoUtente.getEmail());
			utente.setCodiceFiscale(nuovoUtente.getCodiceFiscale());
			utente.setNote(nuovoUtente.getNote());
			utente.setModificatoAdmin(true);

			if (nuovoUtente.getDenomSpedizioniere() != null) {
				CarTSpedizioniere sped = getSpedizioniereByDenomSpedizioniere(nuovoUtente.getDenomSpedizioniere());
				logger.debug("sped.getIdSpedizioniere()= " + sped.getIdSpedizioniere());
				utente.setIdSpedizioniere(sped.getIdSpedizioniere());
			}
		}

		carTUtenteMapper.insertSelective(utente);
		nuovoUtente.setIdUtente(utente.getIdUtente());

		nuovoUtente.setSezioneImport(false);
		nuovoUtente.setSezioneExport(false);
		nuovoUtente.setSezioneVivai(false);
		nuovoUtente.setSezioneAutorizzazioni(false);
		nuovoUtente.setSezioneControlli(false);
		// inserimento utente -> eredita le associazioni sezione dallo
		// spedizioniere
		SpedizioniereDTO sp = getSpedizioniere(nuovoUtente.getIdSpedizioniere());
		if (sp != null && sp.getSezioni() != null)
			for (CarDAssociazioneSezione s : sp.getSezioni()) {
				if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT)
					nuovoUtente.setSezioneImport(true);
				if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT)
					nuovoUtente.setSezioneExport(true);
				if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI)
					nuovoUtente.setSezioneVivai(true);
				if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI)
					nuovoUtente.setSezioneAutorizzazioni(true);
				if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI)
					nuovoUtente.setSezioneControlli(true);
			}

		insertGruppiUtente(nuovoUtente);

		return utente.getIdUtente();
	}

	private void insertGruppiUtente(UtenteForm utenteForm) throws BusinessException {
		logger.debug("BEGIN insertGruppiUtente");
		if (utenteForm.getIdUtente() != null) {

			if (utenteForm.isSezioneImport()) {
				logger.debug("-- insertRUtenteGruppo IMPORT");
				utenteMapper.insertRUtenteGruppo(utenteForm, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT);
			}

			if (utenteForm.isSezioneExport()) {
				logger.debug("-- insertRUtenteGruppo EXPORT");
				utenteMapper.insertRUtenteGruppo(utenteForm, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT);
			}
			if (utenteForm.isSezioneVivai()) {
				logger.debug("-- insertRUtenteGruppo VIVAI");
				utenteMapper.insertRUtenteGruppo(utenteForm, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI);
			}
			if (utenteForm.isSezioneAutorizzazioni()) {
				logger.debug("-- insertRUtenteGruppo AUTORIZZAZIONI");
				utenteMapper.insertRUtenteGruppo(utenteForm, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI);
			}
			if (utenteForm.isSezioneControlli()) {
				logger.debug("-- insertRUtenteGruppo CONTROLLI");
				utenteMapper.insertRUtenteGruppo(utenteForm, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI);
			}
		}
		logger.debug("END insertGruppiUtente");
	}

	@Override
	public UtenteDTO getUtente(Long idUtente) throws BusinessException {
		return utenteMapper.getUtente(idUtente);
	}

	public void updateUtenteIspettore(UtenteForm nuovoUtente) throws BusinessException {
		logger.debug("BEGIN updateUtenteIspettore");
		CarTUtente utente = new CarTUtente();

		if (null != nuovoUtente) {
			utente.setAbilitato(nuovoUtente.isAbilitato());
			utente.setNome(nuovoUtente.getNome());
			utente.setCognome(nuovoUtente.getCognome());
			utente.setEmail(nuovoUtente.getEmail());
			utente.setCodiceFiscale(nuovoUtente.getCodiceFiscale());
			if (nuovoUtente.getNote() == null || "".equals(nuovoUtente.getNote()))
				nuovoUtente.setNote("");
			utente.setNote(nuovoUtente.getNote());
			utente.setIdUtente(nuovoUtente.getIdUtente());
			utente.setModificatoAdmin(true);
			utente.setDataUpdate(new Date());

			if (nuovoUtente.getDenomSpedizioniere() != null) {
				CarTSpedizioniere sped = getSpedizioniereByDenomSpedizioniere(nuovoUtente.getDenomSpedizioniere());
				utente.setIdSpedizioniere(sped.getIdSpedizioniere());
			}
		}
		logger.debug("-- aggiorno l'utente in car_t_utente con id_utente =" + utente.getIdUtente());
		carTUtenteMapper.updateByPrimaryKeySelective(utente);
		// cancello e ricreo il legame con il gruppo
		logger.debug("-- cancello e ricreo il legame con il gruppo");
		utenteMapper.deleteCarRUtenteGruppo(nuovoUtente);

		insertGruppiUtente(nuovoUtente);
		logger.debug("END updateUtenteIspettore");
	}

	@Override
	public void updateUtente(UtenteForm nuovoUtente) throws BusinessException {
		CarTUtente utente = new CarTUtente();

		if (null != nuovoUtente) {

			// Rileggo le info dell'utente corrente su db. Se prima non era
			// abilitato
			// ma ora lo è, gli invio la mail per informarlo
			// dell'avvenuta abilitazione. Solo se ha già la password, se no
			// devo
			// mandargli la mail di reimpostazine col pulsante apposito.
			CarTUtente getOldUtente = carTUtenteMapper.selectByPrimaryKey(nuovoUtente.getIdUtente());
			logger.debug("oldutente.getabilitato= " + getOldUtente.getAbilitato());
			if ((getOldUtente.getAbilitato() == null || !getOldUtente.getAbilitato()) && nuovoUtente.isAbilitato()
					&& getOldUtente.getPassword() != null)
				inviaMailRegistrazioneOCambioPassword(nuovoUtente.getEmail(),
						nuovoUtente.getCognome() + " " + nuovoUtente.getNome(), null, false);

			if ((getOldUtente.getRifiutato() == null || !getOldUtente.getRifiutato()) && nuovoUtente.isRifiutato()
					&& !StringUtils.isEmpty(nuovoUtente.getMotivoRifiuto())) {
				inviaMailRifiutoUtente(nuovoUtente.getEmail(), StringUtils.capitalize(nuovoUtente.getCognome()) + " "
						+ StringUtils.capitalize(nuovoUtente.getNome()), nuovoUtente.getMotivoRifiuto());
			}
			logger.debug("-- updateUtente utente.setAbilitato - getOldUtente.getAbilitato =" + nuovoUtente.isAbilitato()
					+ " - " + getOldUtente.getAbilitato());

			utente.setAbilitato(nuovoUtente.isAbilitato());
			utente.setRifiutato(nuovoUtente.isRifiutato());
			utente.setMotivoRifiuto(nuovoUtente.getMotivoRifiuto());
			utente.setNome(nuovoUtente.getNome());
			utente.setCognome(nuovoUtente.getCognome());
			utente.setEmail(nuovoUtente.getEmail());
			utente.setCodiceFiscale(nuovoUtente.getCodiceFiscale());
			if (nuovoUtente.getNote() == null || "".equals(nuovoUtente.getNote()))
				nuovoUtente.setNote("");
			utente.setNote(nuovoUtente.getNote());
			utente.setIdUtente(nuovoUtente.getIdUtente());
			utente.setModificatoAdmin(true);
			utente.setTelefono(nuovoUtente.getNumeroTelefonoUtente());
			utente.setCellulare(nuovoUtente.getNumeroCellUtente());
			utente.setDataUpdate(new Date());

			if (nuovoUtente.getDenomSpedizioniere() != null) {
				CarTSpedizioniere sped = getSpedizioniereByDenomSpedizioniere(nuovoUtente.getDenomSpedizioniere());
				utente.setIdSpedizioniere(sped.getIdSpedizioniere());
			}
		}
		carTUtenteMapper.updateByPrimaryKeySelective(utente);
		// cancello e ricreo il legame con il gruppo

		// modifica utente -> eredita le associazioni sezione dallo
		// spedizioniere

		SpedizioniereDTO sp = getSpedizioniere(nuovoUtente.getIdSpedizioniere());
		if (sp != null && sp.getSezioni() != null) {
			utenteMapper.deleteCarRUtenteGruppo(nuovoUtente);

			nuovoUtente.setSezioneImport(false);
			nuovoUtente.setSezioneExport(false);
			nuovoUtente.setSezioneVivai(false);
			nuovoUtente.setSezioneAutorizzazioni(false);
			nuovoUtente.setSezioneControlli(false);
			for (CarDAssociazioneSezione s : sp.getSezioni()) {
				if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT)
					nuovoUtente.setSezioneImport(true);
				if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT)
					nuovoUtente.setSezioneExport(true);
				if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI)
					nuovoUtente.setSezioneVivai(true);
				if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI)
					nuovoUtente.setSezioneAutorizzazioni(true);
				if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI)
					nuovoUtente.setSezioneControlli(true);
			}
		}

		insertGruppiUtente(nuovoUtente);
	}

	@Override
	public List<IspettoreDTO> getElencoIspettori(IspettoreForm ricercaIspettoreForm) throws BusinessException {
		return utenteMapper.getElencoIspettori(ricercaIspettoreForm);
	}

	@Override
	public void insertIspettore(IspettoreForm nuovoIspettore, Long idUtenteInsert) throws BusinessException {
		logger.debug("BEGIN insertIspettore");
		// create and insert utente in car_t_utente
		logger.debug("-- Ricerca se c'è gia' utente con email =" + nuovoIspettore.getEmail());
		Long idUtenteInserito = utenteMapper.getIdUtenteByEmail(nuovoIspettore.getEmail());
		logger.debug("-- idUtenteInserito =" + idUtenteInserito);

		if (idUtenteInserito == null) {
			idUtenteInserito = insertUtenteIspettore(nuovoIspettore);
		} else {
			nuovoIspettore.setIdUtente(idUtenteInserito);

			CarTUtente utenteEsistente = carTUtenteMapper.selectByPrimaryKey(idUtenteInserito);

			if (utenteEsistente == null) {
				nuovoIspettore.setAbilitato(true);
			} else {
				nuovoIspettore.setAbilitato(utenteEsistente.getAbilitato());
			}

			updateUtenteIspettore(nuovoIspettore);
		}

		// create ispettore in car_t_ispettore
		CarTIspettore ispettore = new CarTIspettore();

		if (null != nuovoIspettore) {

			ispettore.setIdNazioneNascita(nuovoIspettore.getIdNazioneNascita());
			ispettore.setIdComuneNascita(nuovoIspettore.getIdComuneNascita());

			Long idProvNascita = null;
			if (nuovoIspettore.getIdComuneNascita() != null)
				idProvNascita = utenteMapper.getIdProvinciaByIdComune(nuovoIspettore.getIdComuneNascita());
			if (idProvNascita != null) {
				Long idRegioneServizio = utenteMapper.getIdRegioneByIdProvincia(idProvNascita);
				ispettore.setIdRegioneServizio(idRegioneServizio);
			}

			ispettore.setIdComuneUfficio(nuovoIspettore.getIdComuneUfficio());
			ispettore.setCapUfficio(nuovoIspettore.getCapUfficio());
			ispettore.setDataInsert(new Date());
			ispettore.setIdUtenteInsert(idUtenteInsert);
			ispettore.setIdUtente(idUtenteInserito);
			ispettore.setSesso(nuovoIspettore.getSesso());
			ispettore.setNumeroTessera(nuovoIspettore.getNumeroTessera());
			ispettore.setTitoloStudio(nuovoIspettore.getTitoloStudio());
			ispettore.setIndirizzoUfficio(nuovoIspettore.getIndirizzoUfficio());
			ispettore.setDataNascita(nuovoIspettore.getDataNascita());
			ispettore.setAttivo(true);// di default è sempre true in inserimento
			ispettore.setDataInizio(new Date());// per cui c'è anche la data
												// inizio
			ispettore.setCittaNascita(nuovoIspettore.getCittaNascita());
			ispettore.setTipoIspettore("I");// di default passiamo I
		}

		Long idIspett = utenteMapper.getIdIspettoreByIdUtente(idUtenteInserito);
		logger.debug("--- idIspettore trovato =" + idIspett);
		if (idIspett == null) {
			logger.debug("--- Inserimento record in CAR_T_ISPETTORE");
			carTIspettoreMapper.insertSelective(ispettore);
		} else {
			ispettore.setIdIspettore(idIspett);
			logger.debug("--- Modifica record in CAR_T_ISPETTORE");
			carTIspettoreMapper.updateByPrimaryKeySelective(ispettore);
		}
		logger.debug("END insertIspettore");
	}

	@Override
	public void updateIspettore(IspettoreForm nuovoIspettore, Long idUtenteUpdate) throws BusinessException {
		logger.debug("BEGIN updateIspettore");

		updateUtenteIspettore(nuovoIspettore);

		IspettoreDTO oldIspettore = utenteMapper.getIspettore(nuovoIspettore.getIdIspettore());
		CarTIspettore ispettore = new CarTIspettore();
		ispettore.setDataInizio(oldIspettore.getDataInizio());
		ispettore.setDataInsert(oldIspettore.getDataInsert());
		ispettore.setDataUpdate(oldIspettore.getDataUpdate());
		ispettore.setIdUtenteInsert(oldIspettore.getIdUtenteInsert());
		ispettore.setTipoIspettore(oldIspettore.getTipoIspettore());

		if (null != nuovoIspettore) {

			ispettore.setIdUtente(nuovoIspettore.getIdUtente());
			ispettore.setIdNazioneNascita(nuovoIspettore.getIdNazioneNascita());

			ispettore.setIdComuneUfficio(nuovoIspettore.getIdComuneUfficio());
			Long idProvUFF = null;
			if (nuovoIspettore.getIdComuneUfficio() != null) {
				idProvUFF = utenteMapper.getIdProvinciaByIdComune(nuovoIspettore.getIdComuneUfficio());
			}
			if (idProvUFF != null) {
				Long idRegioneServizio = utenteMapper.getIdRegioneByIdProvincia(idProvUFF);
				ispettore.setIdRegioneServizio(idRegioneServizio);
			}
			ispettore.setCapUfficio(nuovoIspettore.getCapUfficio());
			ispettore.setDataUpdate(new Date());
			ispettore.setIdUtenteUpdate(idUtenteUpdate);
			ispettore.setSesso(nuovoIspettore.getSesso());
			ispettore.setTitoloStudio(nuovoIspettore.getTitoloStudio());
			ispettore.setIndirizzoUfficio(nuovoIspettore.getIndirizzoUfficio());
			ispettore.setDataNascita(nuovoIspettore.getDataNascita());
			ispettore.setIdIspettore(nuovoIspettore.getIdIspettore());
			ispettore.setNumeroTessera(nuovoIspettore.getNumeroTessera());
			ispettore.setAttivo(nuovoIspettore.isAttivo());

			if (nuovoIspettore.getIdNazioneNascita() != null && nuovoIspettore.getIdNazioneNascita() == 1) // ITA
			{
				ispettore.setCittaNascita(null);
				ispettore.setIdComuneNascita(nuovoIspettore.getIdComuneNascita());

			} else {
				ispettore.setCittaNascita(nuovoIspettore.getCittaNascita());
				ispettore.setIdComuneNascita(null);

			}

			logger.debug("\n\nNUOVO ISPETTORE ATTIVO? " + nuovoIspettore.isAttivo());
			logger.debug("\n\nVECCHIO ISPETTORE ATTIVO? " + oldIspettore.isAttivo());
			// se il flag è cambiato rispetto alla situazione precedente
			if (nuovoIspettore.isAttivo() != oldIspettore.isAttivo()) {
				if (nuovoIspettore.isAttivo()) // riattivato -> risetto la data
												// inizio e
												// metto a null la data fine
				{
					logger.debug("\n\nATTIVO ISPETTORE!!");
					ispettore.setDataInizio(new Date());
					ispettore.setDataFine(null);
				} else // disattivato -> setto la data fine
				{
					logger.debug("\n\nDISATTIVO ISPETTORE!!");
					ispettore.setDataFine(new Date());
				}
			}
		}

		carTIspettoreMapper.updateByPrimaryKey(ispettore);
		logger.debug("BEGIN updateIspettore");
	}

	@Override
	public IspettoreDTO getIspettore(Long idIspettore) throws BusinessException {
		logger.debug("BEGIN getIspettore");
		return utenteMapper.getIspettore(idIspettore);
	}

	@Override
	public List<SpedizioniereDTO> getElencoSpedizionieri(SpedizioniereForm ricercaSpedizioniereForm)
			throws BusinessException {
		List<SpedizioniereDTO> spedizionieri = utenteMapper.getElencoSpedizionieri(ricercaSpedizioniereForm);
		logger.debug("==.== inizio elenco spedizionieri");
		// gli spedizionieri di tipo DITTA INDIVIDUALE o UTENTE PRIVATO non
		// hanno
		// denominazione, quindi vado a leggere
		// nome e cognome dell'utente ad essi associato
		for (SpedizioniereDTO s : spedizionieri) {
			if (s.getDenomSpedizioniere() == null && (s.getIdTipoSpedizioniere() == CaronteConstants.DITTA_INDIVIDUALE
					|| s.getIdTipoSpedizioniere() == CaronteConstants.UTENTE_PRIVATO)) {
				UtenteDTO u = utenteMapper.getUtenteIndividuale(s.getIdSpedizioniere());
				if (u != null)
					s.setDenomSpedizioniere(u.getCognome() + " " + u.getNome());
			}
		}

		return spedizionieri;
	}

	@Override
	public Long insertSpedizioniere(SpedizioniereForm nuovoSpedizioniere, Long idUtenteInsert)
			throws BusinessException {

		CarTSpedizioniere spedizioniere = new CarTSpedizioniere();

		if (null != nuovoSpedizioniere) {
			spedizioniere.setAttivo(true); // di default perché inserito da un
											// amministratore
			spedizioniere.setIdStatoAzienda(CaronteConstants.ID_STATO_AZIENDA_ATTIVA); // valore
																						// di
																						// default
																						// :
																						// stato
																						// ATTIVA
			spedizioniere.setIdTipoSpedizioniere(nuovoSpedizioniere.getIdTipoSpedizioniere());

			if (nuovoSpedizioniere.getDenomSpedizioniere() != null) {
				spedizioniere.setDenomSpedizioniere(nuovoSpedizioniere.getDenomSpedizioniere());
			}
			if (nuovoSpedizioniere.getNomeDitta() != null)
				spedizioniere.setNome(nuovoSpedizioniere.getNomeDitta());
			if (nuovoSpedizioniere.getCognomeDitta() != null)
				spedizioniere.setCognome(nuovoSpedizioniere.getCognomeDitta());
			// else
			// spedizioniere.setDenomSpedizioniere(nuovoSpedizioniere.getNomeDitta()
			// +
			// " " + nuovoSpedizioniere.getCognomeDitta());;

			spedizioniere.setDataInsert(new Date());
			spedizioniere.setIdUtenteInsert(idUtenteInsert);
			spedizioniere.setCuaa(nuovoSpedizioniere.getCuaa());
			spedizioniere.setTelefono(nuovoSpedizioniere.getNumeroTelefono());
			spedizioniere.setCellulare(nuovoSpedizioniere.getNumeroCellulare());
			spedizioniere.setCap(nuovoSpedizioniere.getCapSedeSociale());
			spedizioniere.setIndirizzo(nuovoSpedizioniere.getIndirizzoSedeSociale());
			spedizioniere.setCodiceFiscale(nuovoSpedizioniere.getCodiceFiscale());
			spedizioniere.setIdComune(nuovoSpedizioniere.getIdComuneSedeSociale());
			spedizioniere.setCodiceRuop(nuovoSpedizioniere.getCodiceRUOP());
			spedizioniere.setDataRegistrazioneRuop(nuovoSpedizioniere.getDataRegistrazioneRuop());
			spedizioniere.setPec(nuovoSpedizioniere.getPec());
			spedizioniere.setEmail(nuovoSpedizioniere.getEmailSpedizioniere());
			spedizioniere.setModificatoAdmin(true);
			spedizioniere.setAutorizPagamPosticip(nuovoSpedizioniere.isAutorizPagamPosticip());
			
			// Nome e cognome devono solo essere salvati in caso di tipo spedizioniere = 5 (Azienda individuale) o = 1 (Altro)
			if(nuovoSpedizioniere.getIdTipoSpedizioniere() != null && 
				  (nuovoSpedizioniere.getIdTipoSpedizioniere() == CaronteConstants.DITTA_INDIVIDUALE || nuovoSpedizioniere.getIdTipoSpedizioniere() == CaronteConstants.UTENTE_PRIVATO) 
				){
				spedizioniere.setNome(nuovoSpedizioniere.getNomeDitta());
				spedizioniere.setCognome(nuovoSpedizioniere.getCognomeDitta());
			}
			else{
				spedizioniere.setNome(null);
				spedizioniere.setCognome(null);
			}
			
			spedizioniere.setPartitaIva(nuovoSpedizioniere.getPartitaIVA());
		}

		spedizioniereMapper.insertSelective(spedizioniere);

		nuovoSpedizioniere.setIdSpedizioniere(spedizioniere.getIdSpedizioniere());

		/*
		 * Inserisco l'associazione con le sezioni per lo spedizioniere appena
		 * creato
		 */
		logger.debug("-- Inserisco l'associazione con le sezioni per lo spedizioniere appena creato");
		insertSezioniSpedizioniere(nuovoSpedizioniere);

		logger.debug("-- tipoSpedizioniere =" + spedizioniere.getIdTipoSpedizioniere());
		// se devo inserisco anche l'utente (Caso di Tipo Spedizionere Altro o
		// Azienda individuale)
		if (spedizioniere.getIdTipoSpedizioniere() == CaronteConstants.DITTA_INDIVIDUALE
				|| spedizioniere.getIdTipoSpedizioniere() == CaronteConstants.UTENTE_PRIVATO) {
			logger.debug("-- Inserimento dell'utente");
			// insert utente
			CarTUtente utente = new CarTUtente();
			utente.setAbilitato(false);
			utente.setNome(nuovoSpedizioniere.getNomeDitta());
			utente.setCognome(nuovoSpedizioniere.getCognomeDitta());
			utente.setDataInserimento(new Date());
			utente.setEmail(nuovoSpedizioniere.getEmailSpedizioniere());
			utente.setCodiceFiscale(nuovoSpedizioniere.getCuaa());
			utente.setIdSpedizioniere(spedizioniere.getIdSpedizioniere());
			utente.setTelefono(nuovoSpedizioniere.getNumeroTelefono());
			utente.setModificatoAdmin(true);
			carTUtenteMapper.insertSelective(utente);

			nuovoSpedizioniere.setIdUtente(utente.getIdUtente());
			nuovoSpedizioniere.setIdRuolo(CaronteConstants.ID_TIPO_UTENTE);
			// insert relazione utente/gruppi
			logger.debug("-- insert relazione utente/gruppi con abilitazione alle sezioni selezionate");
			if (nuovoSpedizioniere.isSezioneExport())
				utenteMapper.insertRUtenteGruppo(nuovoSpedizioniere, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT);
			if (nuovoSpedizioniere.isSezioneImport())
				utenteMapper.insertRUtenteGruppo(nuovoSpedizioniere, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT);
			if (nuovoSpedizioniere.isSezioneVivai())
				utenteMapper.insertRUtenteGruppo(nuovoSpedizioniere, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI);
			if (nuovoSpedizioniere.isSezioneAutorizzazioni())
				utenteMapper.insertRUtenteGruppo(nuovoSpedizioniere,
						CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI);
			if (nuovoSpedizioniere.isSezioneControlli())
				utenteMapper.insertRUtenteGruppo(nuovoSpedizioniere,
						CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI);
		}

		return spedizioniere.getIdSpedizioniere();
	}

	private void insertSezioniSpedizioniere(SpedizioniereForm spedizioniereForm) {
		logger.debug("BEGIN insertSezioniSpedizioniere");
		if (spedizioniereForm.getIdSpedizioniere() != null) {
			CarRSpedizAssocSezione sezioneSpedizioniere = new CarRSpedizAssocSezione();
			sezioneSpedizioniere.setIdSpedizioniere(spedizioniereForm.getIdSpedizioniere());

			if (spedizioniereForm.isSezioneImport()) {
				sezioneSpedizioniere.setIdAssociazioneSezione(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT);
				logger.debug("-- INSERT SPEDIZIONIERE ABILITATO A IMPORT");
				spedizioniereSezioneMapper.insertSelective(sezioneSpedizioniere);
			}

			if (spedizioniereForm.isSezioneExport()) {
				sezioneSpedizioniere.setIdAssociazioneSezione(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT);
				logger.debug("-- INSERT SPEDIZIONIERE ABILITATO A EXPORT");
				spedizioniereSezioneMapper.insertSelective(sezioneSpedizioniere);
			}

			if (spedizioniereForm.isSezioneVivai()) {
				sezioneSpedizioniere.setIdAssociazioneSezione(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI);
				logger.debug("-- INSERT SPEDIZIONIERE ABILITATO A VIVAI");
				spedizioniereSezioneMapper.insertSelective(sezioneSpedizioniere);
			}

			if (spedizioniereForm.isSezioneAutorizzazioni()) {
				sezioneSpedizioniere.setIdAssociazioneSezione(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI);
				logger.debug("-- INSERT SPEDIZIONIERE ABILITATO A AUTORIZZAZIONI");
				spedizioniereSezioneMapper.insertSelective(sezioneSpedizioniere);
			}

			if (spedizioniereForm.isSezioneControlli()) {
				sezioneSpedizioniere.setIdAssociazioneSezione(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI);
				logger.debug("-- INSERT SPEDIZIONIERE ABILITATO A CONTROLLI");
				spedizioniereSezioneMapper.insertSelective(sezioneSpedizioniere);
			}

		}
		logger.debug("END insertSezioniSpedizioniere");
	}

	@Override
	public SpedizioniereDTO getSpedizioniere(Long idSpedizioniere) throws BusinessException {
		logger.debug("BEGIN getSpedizioniere");
		SpedizioniereDTO spedizioniere = utenteMapper.getSpedizioniere(idSpedizioniere);

		if (spedizioniere != null) {
			if (spedizioniere.getIdTipoSpedizioniere() == CaronteConstants.DITTA_INDIVIDUALE
					|| spedizioniere.getIdTipoSpedizioniere() == CaronteConstants.UTENTE_PRIVATO) {
				UtenteDTO utente = utenteMapper.getUtenteIndividuale(idSpedizioniere);
				if (utente != null) {
					spedizioniere.setNomeDitta(utente.getNome());
					spedizioniere.setCognomeDitta(utente.getCognome());
				}
			}
		}
		logger.debug("END getSpedizioniere");
		return spedizioniere;
	}

	@Override
	public SpedizioniereDTO getSpedizioniereByCuua(String cuaa) throws BusinessException {
		return utenteMapper.getSpedizioniereByCuua(cuaa);
	}

	@Override
	public void updateSpedizioniereDomanda(NuovaDomandaForm form, Long idUtente) throws BusinessException {
		CarTSpedizioniere spedizioniere = new CarTSpedizioniere();

		logger.debug("-- Ricerco dati mancanti dello spedizioniere su db - id_spedizioniere =" + form.getIdAzienda());
		CarTSpedizioniere spedizDb = getCarTSpedizioniere(form.getIdAzienda());

		if (null != form) {
			logger.debug("-- Setto i dati da aggiornare su car_t_spedizioniere");
			logger.debug("-- id_spedizioniere da aggiornare =" + form.getIdAzienda());
			logger.debug("caso update");
			spedizioniere.setIdSpedizioniere(form.getIdAzienda());
			spedizioniere.setIdTipoSpedizioniere(form.getIdTipoAzienda());
			spedizioniere.setDenomSpedizioniere(form.getDenomAzienda().toUpperCase());
			
			// Nome e cognome devono solo essere salvati in caso di tipo spedizioniere = 5 (Azienda individuale) o = 1 (Altro)
			if(form.getIdTipoAzienda() != null && 
				  (form.getIdTipoAzienda() == CaronteConstants.DITTA_INDIVIDUALE || form.getIdTipoAzienda() == CaronteConstants.UTENTE_PRIVATO) 
				){
				spedizioniere.setNome(form.getNomeAzienda());
				spedizioniere.setCognome(form.getCognomeAzienda());
			}
			else{
				spedizioniere.setNome(null);
				spedizioniere.setCognome(null);
			}
			
			
			spedizioniere.setDataUpdate(new Date());
			spedizioniere.setIdUtenteUpdate(idUtente);
			spedizioniere.setCuaa(form.getCodFiscaleAz());
			spedizioniere.setTelefono(form.getNumTelefonoSedeLegale());
			logger.debug("form.getNumTelefonoSedeLegale()= " + form.getNumTelefonoSedeLegale());
			spedizioniere.setCellulare(form.getNumCellulareSedeLegale());
			logger.debug("form.getNumCellulareSedeLegale()= " + form.getNumCellulareSedeLegale());
			spedizioniere.setFax(form.getFaxSedeLegale());
			logger.debug("form.getFaxSedeLegale()= " + form.getFaxSedeLegale());
			spedizioniere.setCap(form.getCapSedeLegale());
			spedizioniere.setIndirizzo(form.getIndirizzoSedeLegale());
			spedizioniere.setIdComune(form.getIdComuneSedeLegale());
			spedizioniere.setEmail(form.getEmailSedeLegale());
			spedizioniere.setPec(form.getPecSedeLegale());
			spedizioniere.setModificatoAdmin(false);
			spedizioniere.setPartitaIva(form.getPartitaIva());
			spedizioniere.setTipoSpedizioniereAltro(form.getTipoSpedizioniereAltro());

			// Dati non settati nella pagina (ripresi dal db)
			spedizioniere.setAttivo(spedizDb.getAttivo());
			spedizioniere.setIdStatoAzienda(spedizDb.getIdStatoAzienda());
			spedizioniere.setAutorizPagamPosticip(spedizDb.getAutorizPagamPosticip());
			spedizioniere.setCodiceRup(spedizDb.getCodiceRup());
			spedizioniere.setCodiceRuop(spedizDb.getCodiceRuop());
			spedizioniere.setDataRegistrazioneRuop(spedizDb.getDataRegistrazioneRuop());
			spedizioniere.setMotivoRichiesta(spedizDb.getMotivoRichiesta());
			spedizioniere.setDataInsert(spedizDb.getDataInsert());
			spedizioniere.setIdUtenteInsert(spedizDb.getIdUtenteInsert());
		}

		logger.debug("-- cuaa =" + form.getCodFiscaleAz());
		logger.debug("-- idTipoSpedizioniere =" + form.getIdTipoAzienda());

		// Controllare se ak_t_spedizioniere_01 (cuaa, id_tipo_spedizioniere,
		// attivo) esistono già sul db
		if ((spedizDb.getCuaa() != null && spedizDb.getCuaa().equalsIgnoreCase(form.getCodFiscaleAz()))
				&& (spedizDb.getIdTipoSpedizioniere() != null
						&& spedizDb.getIdTipoSpedizioniere() == form.getIdTipoAzienda())) {
			logger.debug("-- Cuaa e idTipoSpedizioniere non sono cambiati, devo fare updateSelective()");
			CarTSpedizioniereExample ex = new CarTSpedizioniereExample();
			ex.createCriteria().andIdSpedizioniereEqualTo(spedizioniere.getIdSpedizioniere());
			spedizioniere.setCuaa(null);
			spedizioniere.setIdTipoSpedizioniere(null);
			spedizioniereMapper.updateByExampleSelective(spedizioniere, ex);
		} else {
			logger.debug("-- Cuaa e idTipoSpedizioniere non sono cambiati, devo fare updateByPrimaryKey()");
			spedizioniereMapper.updateByPrimaryKey(spedizioniere);
		}
	}

	@Override
	public void updateSpedizioniereRegistrazione(SpedizioniereForm nuovoSpedizioniere, Long idUtenteUpdate)
			throws BusinessException {
		CarTSpedizioniere spedizioniere = new CarTSpedizioniere();

		if (null != nuovoSpedizioniere) {
			spedizioniere.setIdSpedizioniere(nuovoSpedizioniere.getIdSpedizioniere());
			spedizioniere.setIdStatoAzienda(nuovoSpedizioniere.getIdStatoAzienda());
			if (spedizioniere.getIdStatoAzienda() != null) {
				if (spedizioniere.getIdStatoAzienda() == CaronteConstants.ID_STATO_AZIENDA_CANCELLATA) {
					spedizioniere.setAttivo(false);
				} else {
					spedizioniere.setAttivo(true);
				}
			} else {
				spedizioniere.setIdStatoAzienda(CaronteConstants.ID_STATO_AZIENDA_ATTIVA);
				spedizioniere.setAttivo(true);
			}
			spedizioniere.setIdTipoSpedizioniere(nuovoSpedizioniere.getIdTipoSpedizioniere());
			spedizioniere.setDenomSpedizioniere(nuovoSpedizioniere.getDenomSpedizioniere());
			
			// Nome e cognome devono solo essere salvati in caso di tipo spedizioniere = 5 (Azienda individuale) o = 1 (Altro)
			if(nuovoSpedizioniere.getIdTipoSpedizioniere() != null && 
						  (nuovoSpedizioniere.getIdTipoSpedizioniere() == CaronteConstants.DITTA_INDIVIDUALE || nuovoSpedizioniere.getIdTipoSpedizioniere() == CaronteConstants.UTENTE_PRIVATO) 
				){
					spedizioniere.setNome(nuovoSpedizioniere.getNomeDitta());
					spedizioniere.setCognome(nuovoSpedizioniere.getCognomeDitta());
			}
			else{
				spedizioniere.setNome(null);
				spedizioniere.setCognome(null);
			}
			
			spedizioniere.setDataUpdate(new Date());
			spedizioniere.setIdUtenteUpdate(idUtenteUpdate);
			spedizioniere.setCuaa(nuovoSpedizioniere.getCuaa());
			spedizioniere.setTelefono(nuovoSpedizioniere.getNumeroTelefono());
			spedizioniere.setCellulare(nuovoSpedizioniere.getNumeroCellulare());
			spedizioniere.setCap(nuovoSpedizioniere.getCapSedeSociale());
			spedizioniere.setIndirizzo(nuovoSpedizioniere.getIndirizzoSedeSociale());
			spedizioniere.setIdComune(nuovoSpedizioniere.getIdComuneSedeSociale());
			spedizioniere.setCodiceRuop(nuovoSpedizioniere.getCodiceRUOP());
			spedizioniere.setDataRegistrazioneRuop(nuovoSpedizioniere.getDataRegistrazioneRuop());
			spedizioniere.setEmail(nuovoSpedizioniere.getEmailSpedizioniere());
			spedizioniere.setPec(nuovoSpedizioniere.getPec());
			spedizioniere.setModificatoAdmin(true);
			spedizioniere.setAutorizPagamPosticip(nuovoSpedizioniere.isAutorizPagamPosticip());
			spedizioniere.setPartitaIva(nuovoSpedizioniere.getPartitaIVA());
			spedizioniere.setTipoSpedizioniereAltro(nuovoSpedizioniere.getTipoSpedizioniereAltro());

		}

		spedizioniereMapper.updateByPrimaryKeySelective(spedizioniere);
	}

	@Override
	public void updateSpedizioniere(SpedizioniereForm nuovoSpedizioniere, Long idUtenteUpdate)
			throws BusinessException {
		CarTSpedizioniere spedizioniere = new CarTSpedizioniere();

		if (null != nuovoSpedizioniere) {
			spedizioniere.setIdSpedizioniere(nuovoSpedizioniere.getIdSpedizioniere());
			spedizioniere.setIdStatoAzienda(nuovoSpedizioniere.getIdStatoAzienda());
			if (spedizioniere.getIdStatoAzienda() == CaronteConstants.ID_STATO_AZIENDA_CANCELLATA) {
				spedizioniere.setAttivo(false);
			} else {
				spedizioniere.setAttivo(true);
			}
			spedizioniere.setIdTipoSpedizioniere(nuovoSpedizioniere.getIdTipoSpedizioniere());
			spedizioniere.setDenomSpedizioniere(nuovoSpedizioniere.getDenomSpedizioniere());
			
			
			// Nome e cognome devono solo essere salvati in caso di tipo spedizioniere = 5 (Azienda individuale) o = 1 (Altro)
			if(nuovoSpedizioniere.getIdTipoSpedizioniere() != null && 
				  (nuovoSpedizioniere.getIdTipoSpedizioniere() == CaronteConstants.DITTA_INDIVIDUALE || nuovoSpedizioniere.getIdTipoSpedizioniere() == CaronteConstants.UTENTE_PRIVATO) 
				){
				spedizioniere.setNome(nuovoSpedizioniere.getNome());
				spedizioniere.setCognome(nuovoSpedizioniere.getCognome());
			}
			else{
				spedizioniere.setNome(null);
				spedizioniere.setCognome(null);
			}
			
			
			spedizioniere.setDataUpdate(new Date());
			spedizioniere.setIdUtenteUpdate(idUtenteUpdate);
			spedizioniere.setCuaa(nuovoSpedizioniere.getCuaa());
			if (nuovoSpedizioniere.getNumeroTelefono() == null) {
				spedizioniere.setTelefono("");
			} else {
				spedizioniere.setTelefono(nuovoSpedizioniere.getNumeroTelefono());
			}

			spedizioniere.setCellulare(nuovoSpedizioniere.getNumeroCellulare());
			spedizioniere.setCap(nuovoSpedizioniere.getCapSedeSociale());
			spedizioniere.setIndirizzo(nuovoSpedizioniere.getIndirizzoSedeSociale());
			spedizioniere.setIdComune(nuovoSpedizioniere.getIdComuneSedeSociale());
			spedizioniere.setCodiceRuop(nuovoSpedizioniere.getCodiceRUOP());
			spedizioniere.setDataRegistrazioneRuop(nuovoSpedizioniere.getDataRegistrazioneRuop());
			spedizioniere.setEmail(nuovoSpedizioniere.getEmailSpedizioniere());
			spedizioniere.setPec(nuovoSpedizioniere.getPec());
			spedizioniere.setModificatoAdmin(true);
			spedizioniere.setAutorizPagamPosticip(nuovoSpedizioniere.isAutorizPagamPosticip());
			spedizioniere.setPartitaIva(nuovoSpedizioniere.getPartitaIVA());
			spedizioniere.setTipoSpedizioniereAltro(nuovoSpedizioniere.getTipoSpedizioniereAltro());
		}

		logger.debug("-- Modifica ai dati su car_t_spedizioniere");
		spedizioniereMapper.updateByPrimaryKeySelective(spedizioniere);

		/*
		 * Si cancella e ricrea l'associazione con le sezioni per lo
		 * spedizioniere
		 */
		logger.debug("-- Si cancella e ricrea l'associazione con le sezioni per lo spedizioniere");
		CarRSpedizAssocSezioneExample filtri = new CarRSpedizAssocSezioneExample();
		filtri.createCriteria().andIdSpedizioniereEqualTo(nuovoSpedizioniere.getIdSpedizioniere());

		spedizioniereSezioneMapper.deleteByExample(filtri);

		insertSezioniSpedizioniere(nuovoSpedizioniere);

		// se lo spedizioniere è disabilitato disabilito tutti i suoi utenti
		logger.debug("-- se lo spedizioniere è disabilitato disabilito tutti i suoi utenti");
		if (!spedizioniere.getAttivo()) {
			logger.debug("-- spedizioniere.getAttivo() =" + spedizioniere.getAttivo());
			CarTUtenteExample ex = new CarTUtenteExample();
			ex.createCriteria().andIdSpedizioniereEqualTo(spedizioniere.getIdSpedizioniere());
			CarTUtente u = new CarTUtente();
			u.setAbilitato(false);
			u.setDataUpdate(new Date());
			carTUtenteMapper.updateByExampleSelective(u, ex);
		}

		// Aggiorno car_r_utente_gruppo per tutti gli utenti (rimuovo quelli
		// precedenti e aggiungo quelli nuovi) - ECCETTO QUELLI LEGATI
		// ALL'ISPETTORE
		logger.debug(
				"---- Aggiorno car_r_utente_gruppo per tutti gli utenti (rimuovo quelli precedenti e aggiungo quelli nuovi) ECCETTO QUELLI LEGATI ALL'ISPETTORE");
		UtenteForm utenteForm = new UtenteForm();
		utenteForm.setIdSpedizioniere(spedizioniere.getIdSpedizioniere());
		List<UtenteDTO> utenti = utenteMapper.getElencoUtentiNonIspettori(utenteForm);
		if (utenti != null) {
			logger.debug("---- *** Numero di utenti abilitati per id_spedizioniere = "
					+ spedizioniere.getIdSpedizioniere() + " = " + utenti.size());
			for (UtenteDTO u : utenti) {
				logger.debug("-- id_utente =" + u.getIdUtente());
				logger.debug("-- id_ruolo =" + u.getIdRuolo());
				utenteForm.setIdRuolo(u.getIdRuolo());
				utenteForm.setIdUtente(u.getIdUtente());
				logger.debug("---- delete su CAR_R_UTENTE_GRUPPO con id_utente =" + u.getIdUtente());
				utenteMapper.deleteCarRUtenteGruppo(utenteForm);

				utenteForm.setSezioneImport(false);
				utenteForm.setSezioneExport(false);
				utenteForm.setSezioneVivai(false);
				utenteForm.setSezioneAutorizzazioni(false);
				utenteForm.setSezioneControlli(false);
				// inserimento utente -> eredita le associazioni sezione dallo
				// spedizioniere
				logger.debug("--- insert in CAR_R_UTENTE_GRUPPO : eredita le associazioni sezione dello spedizioniere");
				SpedizioniereDTO sp = getSpedizioniere(spedizioniere.getIdSpedizioniere());
				if (sp != null && sp.getSezioni() != null)
					for (CarDAssociazioneSezione s : sp.getSezioni()) {
						if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT)
							utenteForm.setSezioneImport(true);
						if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT)
							utenteForm.setSezioneExport(true);
						if (s.getIdAssociazioneSezione().longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI)
							utenteForm.setSezioneVivai(true);
						if (s.getIdAssociazioneSezione()
								.longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI)
							utenteForm.setSezioneAutorizzazioni(true);
						if (s.getIdAssociazioneSezione()
								.longValue() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI)
							utenteForm.setSezioneControlli(true);
					}

				insertGruppiUtente(utenteForm);
			}
		}

	}

	@Override
	public List<IspettoreDTO> getListaIspettoriRichiesta() throws BusinessException {
		return utenteMapper.getListaIspettoriRichiesta();
	}

	@Override
	public boolean isMailUnivocal(UtenteForm form) throws BusinessException {

		if (utenteMapper.countMail(form) == 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isCFUnivocal(UtenteForm form) throws BusinessException {

		if (utenteMapper.countCF(form) == 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isDenomUnivocal(SpedizioniereForm form) throws BusinessException {
		if (utenteMapper.countDenomSpedizioniere(form) == 0)
			return true;
		return false;
	}

	@Override
	public Long registraUtente(SpedizioniereForm form, Long idUtenteInsert) throws BusinessException {

		SpedizioniereDTO spedizioniere = getSpedizioniereByCuua(form.getCuaa());
		Long idSpedizioniere = null;
		if (null == spedizioniere) {
			idSpedizioniere = insertSpedizioniere(form, idUtenteInsert);
		} else {
			idSpedizioniere = null;
		}

		form.setIdSpedizioniere(idSpedizioniere);

		return insertUtente(form);
	}

	@Override
	public Long insertUtenteRegistrazione(SpedizioniereForm spedizioniereForm, PasswordEncoder passwordEncoder)
			throws BusinessException {

		CarTUtente utente = new CarTUtente();
		// Attenzione : ogni utente che si registra verrà subito abilitato alla
		// sezione 'Autorizzazione'
		utente.setAbilitato(true);
		utente.setRifiutato(false);
		utente.setNome(spedizioniereForm.getNome());
		utente.setCognome(spedizioniereForm.getCognome());
		utente.setDataInserimento(new Date());
		utente.setEmail(spedizioniereForm.getEmail());
		utente.setCodiceFiscale(spedizioniereForm.getCodiceFiscale());
		utente.setNote(spedizioniereForm.getNote());
		utente.setIdSpedizioniere(spedizioniereForm.getIdSpedizioniere());
		utente.setTelefono(spedizioniereForm.getNumeroTelefonoUtente());
		utente.setCellulare(spedizioniereForm.getNumeroCellUtente());
		utente.setModificatoAdmin(false);
		utente.setDataNascita(spedizioniereForm.getDataNascita());
		String password = spedizioniereForm.getPassword();
		// encrypt password
		String hashedPassword = passwordEncoder.encode(password);
		utente.setPassword(hashedPassword);
		utente.setDataAccettazionePrivacy(spedizioniereForm.getDataAccettazionePrivacy());

		carTUtenteMapper.insertSelective(utente);

		spedizioniereForm.setIdUtente(utente.getIdUtente());
		spedizioniereForm.setIdRuolo(CaronteConstants.ID_TIPO_UTENTE); // lo
																		// inserisco
																		// come
																		// UTENTE

		insertGruppiUtente(spedizioniereForm);

		return utente.getIdUtente();
	}

	public Long insertSpedizioniereDomanda(NuovaDomandaForm form, Long idUtente) throws BusinessException {
		CarTSpedizioniere spedizioniere = new CarTSpedizioniere();

		if (null != form) {
			logger.debug("-- Setto i campi da inserire in car_t_spedizioniere");
			logger.debug("caso insert");
			spedizioniere.setAttivo(false); // di default
			spedizioniere.setIdStatoAzienda(CaronteConstants.ID_STATO_AZIENDA_ATTIVA); // di
																						// default
																						// setto
																						// stato
																						// attivo
																						// (1)
			spedizioniere.setIdTipoSpedizioniere(form.getIdTipoAzienda());
			spedizioniere.setDenomSpedizioniere(form.getDenomAzienda().toUpperCase());
			
			// Nome e cognome devono solo essere salvati in caso di tipo spedizioniere = 5 (Azienda individuale) o = 1 (Altro)
			if(form.getIdTipoAzienda() != null && 
				  (form.getIdTipoAzienda() == CaronteConstants.DITTA_INDIVIDUALE || form.getIdTipoAzienda() == CaronteConstants.UTENTE_PRIVATO) 
				){
				spedizioniere.setNome(form.getNome());
				spedizioniere.setCognome(form.getCognome());
			}
			else{
				spedizioniere.setNome(null);
				spedizioniere.setCognome(null);
			}
			
			
			spedizioniere.setDataInsert(new Date());
			spedizioniere.setCuaa(form.getCodFiscaleAz());
			spedizioniere.setTelefono(form.getNumTelefonoSedeLegale());
			logger.debug("form.getNumTelefonoSedeLegale()= " + form.getNumTelefonoSedeLegale());

			spedizioniere.setCellulare(form.getNumCellulareSedeLegale());
			logger.debug("form.getNumCellulareSedeLegale()= " + form.getNumCellulareSedeLegale());

			spedizioniere.setFax(form.getFaxSedeLegale());
			logger.debug("form.getF= axSedeLegale()" + form.getFaxSedeLegale());

			spedizioniere.setCap(form.getCapSedeLegale());
			spedizioniere.setIndirizzo(form.getIndirizzoSedeLegale());
			spedizioniere.setIdComune(form.getIdComuneSedeLegale());
			spedizioniere.setEmail(form.getEmailSedeLegale());
			spedizioniere.setPec(form.getPecSedeLegale());
			spedizioniere.setModificatoAdmin(false);
			spedizioniere.setPartitaIva(form.getPartitaIva());
			spedizioniere.setTipoSpedizioniereAltro(form.getTipoSpedizioniereAltro());
		}

		/*
		 * L'utente ha sempre un id_spedizioniere già legato : lo inserisco come
		 * spedizioniere secondario in car_r_utente_spedizionieri
		 */
		spedizioniereMapper.insertSelective(spedizioniere);
		Long idSpedizioniereInserito = spedizioniere.getIdSpedizioniere();
		logger.debug("-- idSpedizioniereInserito =" + idSpedizioniereInserito);

		// Recupero l'idUtente del Tab Dati Anagrafici
		Long idUtenteTabDatiAnag = form.getIdUtente();
		logger.debug("-- idUtenteTabDatiAnag =" + idUtenteTabDatiAnag);

		CarRUtenteSpedizionieri utenteSped = new CarRUtenteSpedizionieri();
		utenteSped.setIdSpedizioniere(idSpedizioniereInserito);
		utenteSped.setIdUtente(idUtenteTabDatiAnag);
		utenteSped.setInizioValidita(new Date());

		utenteSpedizMapper.insert(utenteSped);
		logger.debug("-- inserito record in car_r_utente_spedizionieri");

		return idSpedizioniereInserito;
	}

	@Override
	public Long insertSpedizioniereRegistrazione(SpedizioniereForm nuovoSpedizioniere, Long idUtente)
			throws BusinessException {

		CarTSpedizioniere spedizioniere = new CarTSpedizioniere();

		if (null != nuovoSpedizioniere) {
			spedizioniere.setAttivo(false); // di default
			spedizioniere.setIdStatoAzienda(CaronteConstants.ID_STATO_AZIENDA_ATTIVA); // valore
																						// di
																						// default
																						// stato
																						// ATTIVA
			spedizioniere.setIdTipoSpedizioniere(nuovoSpedizioniere.getIdTipoSpedizioniere());
			spedizioniere.setDenomSpedizioniere(nuovoSpedizioniere.getDenomSpedizioniere());
			
			// Nome e cognome devono solo essere salvati in caso di tipo spedizioniere = 5 (Azienda individuale) o = 1 (Altro)
			if(nuovoSpedizioniere.getIdTipoSpedizioniere() != null && 
				  (nuovoSpedizioniere.getIdTipoSpedizioniere() == CaronteConstants.DITTA_INDIVIDUALE || nuovoSpedizioniere.getIdTipoSpedizioniere() == CaronteConstants.UTENTE_PRIVATO) 
				){
				spedizioniere.setNome(nuovoSpedizioniere.getNome());
				spedizioniere.setCognome(nuovoSpedizioniere.getCognome());
			}
			else{
				spedizioniere.setNome(null);
				spedizioniere.setCognome(null);
			}
			
			
			spedizioniere.setDataInsert(new Date());
			spedizioniere.setCuaa(nuovoSpedizioniere.getCuaa());
			spedizioniere.setTelefono(nuovoSpedizioniere.getNumeroTelefono());
			spedizioniere.setCellulare(nuovoSpedizioniere.getNumeroCellulare());
			spedizioniere.setCap(nuovoSpedizioniere.getCapSedeSociale());
			spedizioniere.setIndirizzo(nuovoSpedizioniere.getIndirizzoSedeSociale());
			spedizioniere.setCodiceRup(nuovoSpedizioniere.getCodiceRUP());
			spedizioniere.setCodiceRuop(nuovoSpedizioniere.getCodiceRUOP());
			spedizioniere.setDataRegistrazioneRuop(nuovoSpedizioniere.getDataRegistrazioneRuop());
			spedizioniere.setIdComune(nuovoSpedizioniere.getIdComuneSedeSociale());
			spedizioniere.setEmail(nuovoSpedizioniere.getEmailSpedizioniere());
			spedizioniere.setMotivoRichiesta(nuovoSpedizioniere.getMotivoRichiesta());
			spedizioniere.setPec(nuovoSpedizioniere.getPec());
			spedizioniere.setPartitaIva(nuovoSpedizioniere.getPartitaIVA());
			spedizioniere.setModificatoAdmin(false);
			spedizioniere.setTipoSpedizioniereAltro(nuovoSpedizioniere.getTipoSpedizioniereAltro());
		}

		spedizioniereMapper.insertSelective(spedizioniere);

		nuovoSpedizioniere.setIdSpedizioniere(spedizioniere.getIdSpedizioniere());

		/*
		 * Inserisco l'associazione con le sezioni per lo spedizioniere appena
		 * creato
		 */
		insertSezioniSpedizioniere(nuovoSpedizioniere);

		return spedizioniere.getIdSpedizioniere();
	}

	@Override
	public void updateIdUtenteInsertDelloSpedizioniere(SpedizioniereForm spedizioniereForm) throws BusinessException {
		utenteMapper.updateIdUtenteInsertDelloSpedizioniere(spedizioniereForm);

	}

	@Override
	public void registraUtenteESpedizioniere(SpedizioniereForm spedizioniereForm, PasswordEncoder passwordEncoder)
			throws BusinessException {
		Long idSpedizAggiunto = insertSpedizioniereRegistrazione(spedizioniereForm, null); // inserisco
																							// lo
																							// spedizioniere
		logger.debug("-- idSpedizAggiunto =" + idSpedizAggiunto);
		spedizioniereForm.setIdSpedizioniere(idSpedizAggiunto);
		Long idUtente = insertUtenteRegistrazione(spedizioniereForm, passwordEncoder); // inserisco
																						// l'utente
																						// con
																						// id_spedizioniere
																						// inserito
		logger.debug("-- idUtente =" + idUtente);
		spedizioniereForm.setIdUtente(new Long(idUtente));
		updateIdUtenteInsertDelloSpedizioniere(spedizioniereForm);
	}

	@Override
	public void updatePasswordUtente(UtenteForm utenteForm, PasswordEncoder passwordEncoder) throws BusinessException {

		UtenteDTO u = utenteMapper.getUtenteByToken(utenteForm.getToken());

		CarTUtente utente = new CarTUtente();
		utente.setIdUtente(u.getIdUtente());
		String password = utenteForm.getPassword();
		// encrypt password
		String hashedPassword = passwordEncoder.encode(password);
		utente.setPassword(hashedPassword);
		utente.setToken(""); // in questo modo annullo il token utilizzato
		carTUtenteMapper.updateByPrimaryKeySelective(utente);

	}

	@Override
	public void updateTokenUtente(UtenteDTO utenteDTO) throws BusinessException {

		CarTUtente utente = new CarTUtente();
		logger.debug("-- idUtente =" + utenteDTO.getIdUtente());
		utente.setIdUtente(utenteDTO.getIdUtente());
		logger.debug("-- token =" + utenteDTO.getToken());
		utente.setToken(utenteDTO.getToken());
		utente.setDataToken(utenteDTO.getDataToken());

		carTUtenteMapper.updateByPrimaryKeySelective(utente);

	}

	@Override
	public UtenteDTO getUtenteByToken(UtenteDTO utenteDTO) throws BusinessException {
		return utenteMapper.getUtenteByToken(utenteDTO.getToken());
	}

	@Override
	public void inviaMailRegistrazioneOCambioPassword(String emailDestinatario, String nomeUtente, String token,
			boolean primoAccesso) throws BusinessException {
		logger.debug("BEGIN inviaMailRegistrazioneOCambioPassword");
		String title = null;
		String message = null;
		String url = null;

		Properties caronteProperties = CaronteConstants.getProperties();

		if (caronteProperties != null) {
			url = (String) CaronteConstants.getProperties().get(CaronteConstants.APPLICATION_URL_PROPERTY);
		}

		if (url == null) {
			url = CaronteConstants.URL_ACCESSO_APPLICATIVO;
		}

		if (!url.endsWith("/")) {
			url += "/";
		}

		if (token == null) {
			message = "<html><body><p>Gentile :USERNAME,"
					+ "<br/>ti confermiamo che l'attivazione dell'account <strong>:EMAIL</strong> &egrave; avvenuta con successo."
					+ "<br />Da questo momento puoi accedere all'applicativo tramite il link:"
					+ "<br /><a href=':URL'>:URL</a>"
					+ "<br /><br />Questa mail &egrave; stata generata in automatico, si prega di non rispondere a questo indirizzo, grazie."
					+ "<br/></p></body></html>\n";
			title = "Attivazione account";
		} else {
			url += "registrazione/riassegnaPassword_" + token;
			if (primoAccesso) {
				message = "<html><body><p>Gentile :USERNAME,"
						+ "<br/>ti confermiamo che l'attivazione dell'account <strong>:EMAIL</strong> &egrave; avvenuta con successo."
						+ "<br />Per accedere all'applicativo devi prima impostare la tua password tramite il link:"
						+ "<br /><a href=':URL'>:URL</a>"
						+ "<br><br>NB: Questo link rester&agrave; valido per le prossime 72 ore"
						+ "<br /><br />Questa mail &egrave; stata generata in automatico, si prega di non rispondere a questo indirizzo, grazie."
						+ "<br/></p></body></html>\n";
				title = "Attivazione account";
			} else {
				message = "<html><body><p>Gentile :USERNAME,"
						+ "<br/>&egrave; stata richiesta la modifica della password  per l'account <strong>:EMAIL</strong>"
						+ "<br />Per procedere con l'operazione utilizzare il link:" + "<br /><a href=':URL'>:URL</a>"
						+ "<br><br>NB: Questo link rester&agrave; valido per le prossime 72 ore"
						+ "<br /><br />Questa mail &egrave; stata generata in automatico, si prega di non rispondere a questo indirizzo, grazie."
						+ "<br/></p></body></html>\n";
				title = "Modifica password";
			}
		}
		message = message.replaceAll(":URL", url).replaceAll(":USERNAME", nomeUtente).replaceAll(":EMAIL",
				emailDestinatario);
		postMailWithoutAttachments(CaronteConstants.INDIRIZZO_MITTENTE_REGISTRAZIONE_UTENTE,
				new String[] { emailDestinatario }, null, title, message);

		logger.debug("END inviaMailRegistrazioneOCambioPassword");
	}

	@Override
	public void inviaMailRifiutoUtente(String emailDestinatario, String nomeUtente, String motivoRifiuto)
			throws BusinessException {
		String title = null;
		String message = null;
		String url = null;

		Properties caronteProperties = CaronteConstants.getProperties();

		if (caronteProperties != null) {
			url = (String) CaronteConstants.getProperties().get(CaronteConstants.APPLICATION_URL_PROPERTY);
		}

		if (url == null) {
			url = CaronteConstants.URL_ACCESSO_APPLICATIVO;
		}

		if (!url.endsWith("/")) {
			url += "/";
		}

		message = "<html><body><p>Gentile :USERNAME,"
				+ "<br/>ti avvisiamo che l'attivazione dell'account <strong>:EMAIL</strong> &egrave; stata rifiutata per il seguente motivo:"
				+ "<br />:MOTIVO_RIFIUTO"
				+ "<br /><br />Questa mail &egrave; stata generata in automatico, si prega di non rispondere a questo indirizzo, grazie."
				+ "<br/></p></body></html>\n";
		title = "Attivazione account";
		message = message.replaceAll(":URL", url).replaceAll(":USERNAME", nomeUtente)
				.replaceAll(":EMAIL", emailDestinatario.toLowerCase()).replaceAll(":MOTIVO_RIFIUTO", motivoRifiuto);
		postMailWithoutAttachments(CaronteConstants.INDIRIZZO_MITTENTE_REGISTRAZIONE_UTENTE,
				new String[] { emailDestinatario }, null, title, message);
	}

	protected void postMailWithoutAttachments(String from, String[] to, String[] ccn, String subject, String message)
			throws BusinessException {
		try {
			logger.debug("BEGIN postMailWithoutAttachments");
			CaronteUtils.postMailWithoutAttachments(from, to, ccn, subject, message);
		} catch (Exception ex) {
			logger.error("-- Exception in postMailWithoutAttachments =" + ex.getMessage());
			throw new BusinessException(ex.getMessage());
		} finally {
			logger.debug("END postMailWithoutAttachments");
		}
	}

	@Override
	public List<IspettoreDTO> getElencoIspettoriFornituraDati(List<Long> ids) throws BusinessException {
		return utenteMapper.getElencoIspettoriFornituraDati(ids);
	}

	@Override
	public Long getUtenteByEmail(String email) throws BusinessException {
		return utenteMapper.getIdUtenteByEmail(email);
	}

	@Override
	public CarTUtente getUtenteByCodiceFiscale(String codiceFiscale) throws BusinessException {
		CarTUtenteExample example = new CarTUtenteExample();
		example.createCriteria().andCodiceFiscaleEqualTo(codiceFiscale.trim().toUpperCase());
		List<CarTUtente> utenti = carTUtenteMapper.selectByExample(example);
		CarTUtente utente = null;
		if (utenti != null && utenti.size() > 0) {
			logger.debug("-- E' stato trovato l'utente con codice fiscale =" + codiceFiscale);
			utente = utenti.get(0);
		}
		return utente;
	}

	@Override
	public SpedizioniereDTO getSpedizioniereByPartitaIva(String partitaIva) throws BusinessException {
		return utenteMapper.getSpedizioniereByPartitaIva(partitaIva);
	}

	@Override
	public void updateStatoCentroAziendale(Long idCentroAziendale, Long idNuovoStato) throws BusinessException {
		CarTCentroAziendale centroAz = new CarTCentroAziendale();
		centroAz.setIdCentroAziendale(idCentroAziendale);
		centroAz.setIdStatoAzienda(idNuovoStato);
		carTCentroAziendaleMapper.updateByPrimaryKeySelective(centroAz);
	}

	@Override
	public Date getDataAccettazionePrivacyUtente(Long idUtente) throws BusinessException {
		Date dataAccettazionePrivacy = null;
		CarTUtente utente = carTUtenteMapper.selectByPrimaryKey(idUtente);
		if (utente != null) {
			dataAccettazionePrivacy = utente.getDataAccettazionePrivacy();
			logger.debug("-- dataAccettazionePrivacy =" + dataAccettazionePrivacy);
		}
		return dataAccettazionePrivacy;
	}

	@Override
	public void setDataAccettazionePrivacy(Long idUtente) throws BusinessException {

		CarTUtente utente = new CarTUtente();
		utente.setIdUtente(idUtente);
		utente.setIdUtenteUpdate(idUtente);
		utente.setDataUpdate(new Date());
		utente.setDataAccettazionePrivacy(new Date());
		carTUtenteMapper.updateByPrimaryKeySelective(utente);

	}

	@Override
	public boolean checkIfDenomSpedizioniereExist(String denomSpedizioniere) throws BusinessException {

		logger.debug("-- Controllo se lo spedizioniere esiste già a sistema");

		CarTSpedizioniereExample example = new CarTSpedizioniereExample();
		example.createCriteria().andDenomSpedizioniereEqualTo(denomSpedizioniere);
		return !spedizioniereMapper.selectByExample(example).isEmpty();

	}

	@Override
	public CarTSpedizioniere getSpedizioniereByDenomSpedizioniere(String denomSpedizioniere) throws BusinessException {

		CarTSpedizioniereExample example = new CarTSpedizioniereExample();
		example.createCriteria().andDenomSpedizioniereEqualTo(denomSpedizioniere);
		return spedizioniereMapper.selectByExample(example).get(0);

	}
	
	@Override
	public CarTUtente resetPasswordUtente(UtenteForm utenteForm, PasswordEncoder passwordEncoder) throws BusinessException {
		logger.debug("BEGIN resetPasswordUtente");
		
		CarTUtente utente = carTUtenteMapper.selectByPrimaryKey(utenteForm.getIdUtente());				 		 
		
		String password = utenteForm.getPassword();
		// encrypt password
		String hashedPassword = passwordEncoder.encode(password);
		utente.setPassword(hashedPassword);
		utente.setToken(""); // in questo modo annullo il token utilizzato
		utente.setDataUpdate(new Date());
		carTUtenteMapper.updateByPrimaryKeySelective(utente);

		// risetto la password non criptata per la visualizzazione sul layout
		utente.setPassword(password);
		logger.debug("END resetPasswordUtente");
		return utente;
	}

	@Override
	public void deleteCentroAziendaleByIdCentroAziendale(Long idCentroAziendale) throws BusinessException {
		carTCentroAziendaleMapper.deleteByPrimaryKey(idCentroAziendale);
	}

	@Override
	public void deleteSpedizioniereByIdSpedizioniere(Long idSpedizioniere) throws BusinessException {
		CarRSpedizAssocSezioneExample example = new CarRSpedizAssocSezioneExample();
		example.createCriteria().andIdSpedizioniereEqualTo(idSpedizioniere);
		carRSpedizAssocSezioneMapper.deleteByExample(example);
		
		spedizioniereMapper.deleteByPrimaryKey(idSpedizioniere);	
	}

	@Override
	public void deleteUtenteByIdUtente(Long idUtente) throws BusinessException {
		
		CarRUtenteGruppoExample example = new CarRUtenteGruppoExample();
		example.createCriteria().andIdUtenteEqualTo(idUtente);
		carRUtenteGruppoMapper.deleteByExample(example);
		
		//carTUtenteMapper.deleteByPrimaryKey(idUtente);
		
	}

}