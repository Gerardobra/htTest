package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.IspettoreDTO;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.IspettoreForm;
import it.aizoon.ersaf.form.SpedizioniereForm;
import it.aizoon.ersaf.form.UtenteForm;

@Mapper
public interface UtenteMapper extends IBaseDao<BaseDto, GenericExample>{
  
	String getUtenteById(@Param("idUtente") Long idUtente);
  
	List<UtenteDTO> getElencoUtenti(@Param("utenteForm") UtenteForm utenteForm);

	int insertRUtenteGruppo(@Param("utente") UtenteForm nuovoUtente, @Param("idSezione") Long idSezione);

	UtenteDTO getUtente(Long idUtente);

	void deleteCarRUtenteGruppo(UtenteForm nuovoUtente);

	String getUtenteById(long l);

	//ISPETTORI
	List<IspettoreDTO> getElencoIspettori(@Param("ispettoreForm") IspettoreForm ispettoreForm);

	IspettoreDTO getIspettore(Long idIspettore);
	
	List<IspettoreDTO> getListaIspettoriRichiesta();
	
	//SPEDIZIONIERI
	List<SpedizioniereDTO> getElencoSpedizionieri(@Param("spedizioniereForm") SpedizioniereForm spedizioniereForm);

	SpedizioniereDTO getSpedizioniere(Long idSpedizioniere);
	
	SpedizioniereDTO getSpedizioniereByCuua(String cuaa);

	//validate
	int countMail(UtenteForm form);
	
	int countCF(UtenteForm form);
	
	int countDenomSpedizioniere(SpedizioniereForm form);

	Long insertSpedizioniereRegistrazione(SpedizioniereForm spedizioniereForm);

	void updateIdUtenteInsertDelloSpedizioniere(SpedizioniereForm spedizioniereForm);

	UtenteDTO getUtenteByToken(String token);

	UtenteDTO getUtenteIndividuale(Long idSpedizioniere);

	List<CarTSpedizioniere> getListaSpedizionieri();

	List<IspettoreDTO> getElencoIspettoriFornituraDati(List<Long> ids);

	Long getIdProvinciaByIdComune(Long idComuneNascita);

	Long getIdRegioneByIdProvincia(Long idProvNascita);

	Long getIdUtenteByEmail(String email);

	List<UtenteDTO> getElencoUtentiNonIspettori(UtenteForm utenteForm);

	Long getIdIspettoreByIdUtente(Long idUtente);
	
	SpedizioniereDTO getSpedizioniereByPartitaIva(String partitaIva);
	
	Long getIdRuoloByIdUtente(@Param("idUtente") Long idUtente);

}