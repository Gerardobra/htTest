package it.aizoon.ersaf.business.impl;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.aizoon.ersaf.business.ITraceIEJB;
import it.aizoon.ersaf.dto.generati.CarTInvioMailUtente;
import it.aizoon.ersaf.dto.generati.CarTInvioMailUtenteExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTInvioMailUtenteMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;

@Stateless(name = "Trace")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class TraceIEJB implements ITraceIEJB{
		
	protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".business");
	
	@Inject
	CarTInvioMailUtenteMapper invioMailMapper;

	@Override
	@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
	public void updateInvioMailUtente(CarTInvioMailUtente invioMailUtente) throws BusinessException {
		// update data_invio_mail
		CarTInvioMailUtenteExample example = new CarTInvioMailUtenteExample();		
		example.createCriteria().andIdInvioMailUtenteEqualTo(invioMailUtente.getIdInvioMailUtente());
		logger.debug("--- UPDATE data_invio_mail con id_utente_invio_mail ="+invioMailUtente.getIdInvioMailUtente());
		invioMailMapper.updateByExample(invioMailUtente, example);	
	}
	
	
	
		
	
}
