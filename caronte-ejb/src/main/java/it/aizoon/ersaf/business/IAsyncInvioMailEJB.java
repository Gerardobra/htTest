package it.aizoon.ersaf.business;

import javax.ejb.Local;

import org.springframework.stereotype.Component;

import it.aizoon.ersaf.dto.generati.CarTInvioMailUtente;
import it.aizoon.ersaf.exception.BusinessException;


public interface IAsyncInvioMailEJB {
		
  public void invioMailMassiva(CarTInvioMailUtente invioMailUtente) throws BusinessException;		
		
}
