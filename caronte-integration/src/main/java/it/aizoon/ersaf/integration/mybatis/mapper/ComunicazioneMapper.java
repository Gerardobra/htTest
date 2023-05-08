package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.CentroAziendaleDto;
import it.aizoon.ersaf.dto.ComunicazioneDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazione;
import it.aizoon.ersaf.dto.generati.CarTInvioMailUtente;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.RicercaComunicazioneForm;

@Mapper
public interface ComunicazioneMapper extends IBaseDao<BaseDto, GenericExample> {

  List<ComunicazioneDto> getElencoComunicazioniRespinte(@Param("idUtente") Long idUtente,
      @Param("dataLimite") Date dataLimite, @Param("listaStati") List<Long> listaStati);

  List<ComunicazioneDto> getElencoComunicazioni(RicercaComunicazioneForm ricercaComunicazione);

  List<CentroAziendaleDto> getElencoCentriAziendali(Long idSpedizioniere);

  Boolean isUtenteAbilitatoModificaComunicazione(@Param("idUtente") Long idUtente,
      @Param("idComunicazione") Long idComunicazione);
  
  ComunicazioneDto getDettaglioComunicazione(Long idComunicazione);
  
  List<CarDStatoComunicazione> getListaStatiComunicazioneSuccessivi(@Param("idUtente") Long idUtente,
      @Param("idComunicazione") Long idComunicazione);
  
  Long lockComunicazione(Long idComunicazione);
  
  List<CarTInvioMailUtente> getElencoMailInvioMassivo();
  
  List<CarDTipoComunicazione> getTipiComunicazioneByIdUtente(@Param("idUtente") Long idUtente,
	      @Param("idAssociazioneSezione") Long idAssociazioneSezione);
  
  List<CarTSpedizioniere> getListaSpedizionieriByDenomRuop(@Param("spedizioniereRuop") String spedizioniereRuop, 
		  @Param("idAssociazioneSezione") Long idAssociazioneSezione);
  
  List<CarTSpedizioniere> getListaSpedizionieriByIdUtente(@Param("idUtente") Long idUtente);
  
}
