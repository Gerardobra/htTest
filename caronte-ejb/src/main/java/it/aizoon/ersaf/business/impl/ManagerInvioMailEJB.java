package it.aizoon.ersaf.business.impl;


import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.aizoon.ersaf.business.IAsyncInvioMailEJB;
import it.aizoon.ersaf.business.IManagerInvioMailEJB;
import it.aizoon.ersaf.dto.generati.CarTInvioMailUtente;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.integration.mybatis.mapper.ComunicazioneMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;



@Stateless(name = "ManagerInvioMail")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class ManagerInvioMailEJB implements IManagerInvioMailEJB {

	protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".business");

	
	@Inject
    private IAsyncInvioMailEJB asyncInvioMailEJB;
	
	@Inject
	private ComunicazioneMapper comunicazioneMapper;


	@Override
	public boolean invioMailMassiva() throws BusinessException {				
		try{
		 /*  Ricerca delle mail da inviare :
		  *  su car_t_invio_mail_utente ricerco chi non ha ancora effettuato l'invio mail o 
		  *  l'ha effettuata e non è andata a buon fine
		 */			  		
		  logger.debug("-- Ricerco a chi dobbiamo inviare la mail");
		  List<CarTInvioMailUtente> invioMailUtenteList = comunicazioneMapper.getElencoMailInvioMassivo();
		  if(null != invioMailUtenteList){
			 logger.debug("-- NUMERO DI MAIL DA INVIARE ="+invioMailUtenteList.size()); 
			 for (Iterator<CarTInvioMailUtente> iterator = invioMailUtenteList.iterator(); iterator.hasNext();) {
				CarTInvioMailUtente carTInvioMailUtente = (CarTInvioMailUtente) iterator.next();
				logger.debug("-- IdInvioMailUtente ="+carTInvioMailUtente.getIdInvioMailUtente()); 		
				// Chiamata al metodo asincrono che invia la mail e traccia il risultato dell'invio mail
				asyncInvioMailEJB.invioMailMassiva(carTInvioMailUtente);				
			}			  
		  }
		  
		 
		}		
		catch(Exception e){
		  logger.error("Exception in invioMailMassiva ="+e.getMessage(),e);
		  return false;
		}		
		return true;
	
	}
	
	
	
  


	
	
}
