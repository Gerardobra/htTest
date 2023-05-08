package it.aizoon.ersaf.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.IspettoreDTO;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarTUtente;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.IspettoreForm;
import it.aizoon.ersaf.form.NuovaDomandaForm;
import it.aizoon.ersaf.form.SpedizioniereForm;
import it.aizoon.ersaf.form.UtenteForm;

@Component
@Local
public interface IUtenteEJB extends IAbstractEJB<BaseDto, GenericExample> {

	public String getUtenteProva() throws BusinessException;

	public Long registraUtente(SpedizioniereForm form, Long idUtenteInsert) throws BusinessException;

	// UTENTI
	public List<UtenteDTO> getElencoUtenti(UtenteForm ricercaUtenteForm) throws BusinessException;

	public Long insertUtente(UtenteForm nuovoUtenteForm) throws BusinessException;

	public UtenteDTO getUtente(Long idUtente) throws BusinessException;

	public void updateUtente(UtenteForm nuovoUtenteForm) throws BusinessException;
	
	public CarTUtente getUtenteByCodiceFiscale(String codiceFiscale) throws BusinessException;
	
	public Date getDataAccettazionePrivacyUtente(Long idUtente) throws BusinessException;
	
	public CarTUtente resetPasswordUtente(UtenteForm utenteForm, PasswordEncoder passwordEncoder) throws BusinessException;

	// ISPETTORI
	public List<IspettoreDTO> getElencoIspettori(IspettoreForm ricercaIspettoreForm) throws BusinessException;

	public void insertIspettore(IspettoreForm nuovoIspettoreForm, Long idUtenteInsert) throws BusinessException;

	public IspettoreDTO getIspettore(Long idIspettore) throws BusinessException;

	public void updateIspettore(IspettoreForm nuovoIspettoreForm, Long idUtenteUpdate) throws BusinessException;

	public List<IspettoreDTO> getListaIspettoriRichiesta() throws BusinessException;

	// SPEDIZIONIERI
	public List<SpedizioniereDTO> getElencoSpedizionieri(SpedizioniereForm ricercaSpedizioniereForm)
			throws BusinessException;

	public Long insertSpedizioniere(SpedizioniereForm nuovoSpedizioniereForm, Long idUtenteInsert)
			throws BusinessException;

	public SpedizioniereDTO getSpedizioniere(Long idSpedizioniere) throws BusinessException;

	public SpedizioniereDTO getSpedizioniereByCuua(String cuaa) throws BusinessException;

	public void updateSpedizioniere(SpedizioniereForm nuovoIspettoreForm, Long idUtenteUpdate) throws BusinessException;

	public void updateSpedizioniereRegistrazione(SpedizioniereForm nuovoIspettoreForm, Long idUtenteUpdate)
			throws BusinessException;

	public boolean isMailUnivocal(UtenteForm form) throws BusinessException;

	public boolean isCFUnivocal(UtenteForm form) throws BusinessException;

	public boolean isDenomUnivocal(SpedizioniereForm form) throws BusinessException;

	public CarTSpedizioniere getCarTSpedizioniere(Long idSpedizioniere) throws BusinessException;

	public Long insertUtenteRegistrazione(SpedizioniereForm spedizioniereForm, PasswordEncoder passwordEncoder)
			throws BusinessException;

	public Long insertSpedizioniereRegistrazione(SpedizioniereForm spedizioniereForm, Long idUtente)
			throws BusinessException;

	public void updateIdUtenteInsertDelloSpedizioniere(SpedizioniereForm spedizioniereForm) throws BusinessException;

	public void registraUtenteESpedizioniere(SpedizioniereForm spedizioniereForm, PasswordEncoder passwordEncoder)
			throws BusinessException;

	public void updatePasswordUtente(UtenteForm utenteForm, PasswordEncoder passwordEncoder) throws BusinessException;

	public void updateTokenUtente(UtenteDTO utente) throws BusinessException;

	public UtenteDTO getUtenteByToken(UtenteDTO utenteDTO) throws BusinessException;

	public void inviaMailRegistrazioneOCambioPassword(String emailDestinatario, String nomeUtente, String token,
			boolean primoAccesso) throws BusinessException;

	public List<IspettoreDTO> getElencoIspettoriFornituraDati(List<Long> ids) throws BusinessException;

	public Long getUtenteByEmail(String email) throws BusinessException;

	void inviaMailRifiutoUtente(String emailDestinatario, String nomeUtente, String motivoRifiuto)
			throws BusinessException;
	
	public Long insertSpedizioniereDomanda(NuovaDomandaForm form, Long idUtente) throws BusinessException;
	
	public void updateSpedizioniereDomanda(NuovaDomandaForm form, Long idUtente) throws BusinessException;
	
	public SpedizioniereDTO getSpedizioniereByPartitaIva(String partitaIva) throws BusinessException;
	
	public void updateStatoCentroAziendale(Long idCentroAziendale, Long idNuovoStato) throws BusinessException;

	public void setDataAccettazionePrivacy(Long idUtente) throws BusinessException;

	public boolean checkIfDenomSpedizioniereExist(String denomSpedizioniere) throws BusinessException;
	
	public CarTSpedizioniere getSpedizioniereByDenomSpedizioniere(String denomSpedizioniere) throws BusinessException;

	public void deleteCentroAziendaleByIdCentroAziendale(Long idCentroAziendale) throws BusinessException;

	public void deleteSpedizioniereByIdSpedizioniere(Long idSpedizioniere) throws BusinessException;

	public void deleteUtenteByIdUtente(Long idUtente) throws BusinessException;

}