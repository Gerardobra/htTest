package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.generati.CarDAutoreEppo;

@Mapper
public interface AutoreEppoMapper extends IBaseDao<BaseDto, GenericExample>{
	
	List<CarDAutoreEppo> getListaAutoriEppoDenominazione(String denomAutore);
	
	CarDAutoreEppo getDatiDenomAutoreEppo(String denomAutore);
	
	
}