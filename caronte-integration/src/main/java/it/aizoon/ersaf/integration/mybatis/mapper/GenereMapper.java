package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.RicercaGeneriForm;

/**
 * @author Ivan Morra
 */
@Mapper
public interface GenereMapper extends IBaseDao<BaseDto, GenericExample> {

  List<GenereDTO> ricercaListaGeneri(RicercaGeneriForm ricercaGeneriForm);
  
  List<GenereDTO> getListaGeneri(String codiceLingua);
  
  List<GenereDTO> getListaGeneriDenominazione(@Param("denominazioneLike") String denominazioneLike, @Param("codiceLingua") String codiceLingua);

  GenereDTO getDettaglioGenere(@Param("idGenere") Long idGenere, @Param("codiceLingua") String codiceLingua);
  
  Boolean isGenereEliminabile(Long idGenere);
  
  GenereDTO getGenereDenominazione(String denomGenere);
  
  List<GenereDTO> getListaGenereByIdGruppoZonaProtetta(Long idGruppoZonaProtetta);

  List<GenereSpecieDTO> getListaGeneriSpecieComunicazione(Long idCentroAziendale);

  List<GenereSpecieDTO> getListaGeneriSpecieDomandaRuop(Long idDomanda);
  
  CodeDescriptionDTO getGenereByIdSpecie(@Param("idSpecie") Long idSpecie);
  
  List<SpecieDTO> getSpecieGenereByListIdSpecie(@Param("idSpecieList") List<Long> idSpecieList);
  
  CarDOrgNocivo getOrgNocByIdGenere(@Param("idGenere") Long idGenere);
  
  CodeDescriptionDTO getGenereByIdGenere(@Param("idGenere") Long idGenere);
  
  CodeDescriptionDTO getOrgNocivoByIdOrgNocivo(@Param("idOrgNocivo") Long idOrgNocivo);
  
}
