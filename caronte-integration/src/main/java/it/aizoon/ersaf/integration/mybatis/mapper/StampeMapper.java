package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.stampe.EverdeDTO;
import it.aizoon.ersaf.dto.stampe.FitosanitarioExportDTO;
import it.aizoon.ersaf.dto.stampe.FitosanitarioRiexportDTO;
import it.aizoon.ersaf.dto.stampe.NullaOstaDTO;

@Mapper
public interface StampeMapper extends IBaseDao<BaseDto, GenericExample>{
  
	NullaOstaDTO getPdfNullaOsta(@Param("idRichiesta") long idRichiesta);
	
	EverdeDTO getPdfEverde(@Param("idRichiesta") long idRichiesta);

	List<FitosanitarioExportDTO> getPdfCertificatoExport(@Param("idRichiesta") long idRichiesta);
	
	List<FitosanitarioRiexportDTO> getPdfCertificatoRiexport(@Param("idRichiesta") long idRichiesta);

}