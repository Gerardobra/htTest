package it.aizoon.ersaf.business;

import javax.ejb.Local;

import org.springframework.stereotype.Component;

import it.aizoon.ersaf.dto.generati.CarTInvioMailUtente;
import it.aizoon.ersaf.exception.BusinessException;

@Component
@Local
public interface ITraceIEJB { 
  
  public void updateInvioMailUtente(CarTInvioMailUtente invioMailUtente) throws BusinessException;
  	
		
}