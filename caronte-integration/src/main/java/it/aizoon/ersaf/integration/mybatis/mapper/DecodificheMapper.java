package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.CentroAziendaleDto;
import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.DomandaDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.ModuloDTO;
import it.aizoon.ersaf.dto.TipoDomandaDTO;
import it.aizoon.ersaf.dto.generati.CarDGruppoZonaProtetta;
import it.aizoon.ersaf.dto.generati.CarDMateriale;
import it.aizoon.ersaf.dto.generati.CarDNormaVerbale;
import it.aizoon.ersaf.dto.generati.CarDTipoRespAzienda;
import it.aizoon.ersaf.dto.generati.CarDTipologiaControllo;
import it.aizoon.ersaf.dto.generati.CarDTipologiaSemente;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoStrAtt;
import it.aizoon.ersaf.exception.BusinessException;

@Mapper
public interface DecodificheMapper extends IBaseDao<BaseDto, GenericExample> {

  TipoDomandaDTO getDatiTipoDomanda(@Param("idTipoDomanda") Long idTipoDomanda);  
  
  List<CarDGruppoZonaProtetta> getZoneProtette();  
  
  List<CarDMateriale> getListaMaterialiByIdTipoAttivita(Long idTipoAttivita);
  
  String isTipoGenereFamiglia(String denomGenere);

  List<DomandaDto> getListaIspettori();
  
  List<CentroAziendaleDto> getListaCentriAziendali(@Param("idSpedizioniere") Long idSpedizioniere);

  List<ModuloDTO> getListaModuliComunicazione(@Param("idDomanda") Long idDomanda);

  List<CarDNormaVerbale> getListaNormeVerbale();

  List<CarDTipologiaSemente> getListaTipologiaSementi();

  List<CarDTipoRespAzienda> getListaTipiRespAzienda();

  List<CarDTipologiaControllo> getListTipologieControlli();
  
  List<CodeDescriptionDTO> getGeneriByIdOrgNocivo(@Param("idOrganismoNocivo") Long idOrganismoNocivo);
 
  List<CodeDescriptionDTO> getSpecieByIdGeneri(List<Long> idGenereList);

  List<CarRControlloFisicoStrAtt> getListaStruttureAttrezzatureByIdControlloFisico(Long idControlloFisico);
  
  List<CodeDescriptionDTO> getListaIspettoriByTipoIspettore(@Param("tipoIspettore") String tipoIspettore);
  
  List<CarDTipologiaSemente> getListaTipologiaSementiByIdVersioneControllo(@Param("idVersioneControllo") Long idVersioneControllo);


}