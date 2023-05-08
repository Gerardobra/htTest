package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.RicercaSpecieForm;

/**
 * @author Ivan Morra
 */
@Mapper
public interface SpecieMapper extends IBaseDao<BaseDto, GenericExample> {

  List<SpecieDTO> ricercaListaSpecie(RicercaSpecieForm ricercaSpecieForm);

  SpecieDTO getDettaglioSpecie(@Param("id") Long id, @Param("codiceLingua") String codiceLingua);

  List<SpecieDTO> getListaSpecieGenere(@Param("idGenere") Long idGenere, @Param("codLingua") String codiceLingua);

  List<SpecieDTO> getListaSpecieDenomGenere(@Param("denomGenere") String denomGenere,
      @Param("codLingua") String codiceLingua, @Param("idSpecieList") List<Integer> idSpecieList);

  Boolean isSpecieEliminabile(Long idSpecie);
  
  List<SpecieDTO> getListaSpecieByIdVoce(@Param("idVoce") Long idVoce);
  
  List<SpecieDTO> getListaSpecieByIdGruppoZPIdGenere(@Param("idGruppoZonaProtetta") Long idGruppoZonaProtetta, @Param("idGenere") Long idGenere);

}
