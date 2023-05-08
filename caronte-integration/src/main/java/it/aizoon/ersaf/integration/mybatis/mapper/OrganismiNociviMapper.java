package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenereSpecieOrgNocivoDTO;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.OrgNocivoGenereSpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDTipoOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDTipoProdotto;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.RicercaOrganismiNociviForm;


@Mapper
public interface OrganismiNociviMapper extends IBaseDao<BaseDto, GenericExample> {

	public List<OrgNocivoGenereSpecieDTO> getElencoOrganismiNocivi(RicercaOrganismiNociviForm ricercaOrganismiNocivi);
	public List<CarDOrgNocivo> getOrganismiNocivi();
	public List<CarDTipoOrgNocivo> getTipiOrganismiNocivi();
	public List<CarDTipoProdotto> getTipiProdotto(@Param("idTipoProdotto") Long idTipoProdotto);
	public OrgNocivoGenereSpecieDTO getDatiOrganismoNocivoByIdOrgNoc(@Param("idOrganismoNocivo") Long idOrganismoNocivo);
	public List<GenereSpecieOrgNocivoDTO> getGenereSpecieOrgNocivoByIdOrgNoc(@Param("idOrganismoNocivo") Long idOrganismoNocivo);
	public CarDOrgNocivo getOrganismoNocivoByCodiceEppoDescrOrgNoc(@Param("codiceEppo") String codiceEppo, @Param("descrOrgNoc") String descrOrgNoc);
	 
}
