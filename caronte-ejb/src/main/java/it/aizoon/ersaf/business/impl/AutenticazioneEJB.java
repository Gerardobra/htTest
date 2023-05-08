package it.aizoon.ersaf.business.impl;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.springframework.util.StringUtils;

import it.aizoon.ersaf.business.IAutenticazioneEJB;
import it.aizoon.ersaf.dto.GrantDTO;
import it.aizoon.ersaf.dto.LoginTokenDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarTTraceLogin;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.integration.mybatis.mapper.AutenticazioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTTraceLoginMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;

/**
 * @author ff
 */
@Stateless(name = "Autenticazione")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class AutenticazioneEJB implements IAutenticazioneEJB {

	private final String THIS_CLASS = AutenticazioneEJB.class.getCanonicalName();

	@Inject
	AutenticazioneMapper autenticazioneMapper;

	@Inject
	CarTTraceLoginMapper traceLoginMapper;
	
	@Override
	public UtenteDTO getUtenteByUsername(String username) throws BusinessException {
		if (StringUtils.isEmpty(username)) {
			throw new InvalidParameterException();
		}
		
		UtenteDTO utente = autenticazioneMapper.getUtenteByUsername(username);
		if (utente != null) {
			utente.getGrant().addAll(getGrantByIdUtente(utente.getId()));
		}

		return utente;
	}
	
	@Override
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public UtenteDTO getUtenteByCredenziali(String username, String password) throws BusinessException {
		if (StringUtils.isEmpty(username)) {
			throw new InvalidParameterException();
		}
		if (StringUtils.isEmpty(password)) {
			throw new InvalidParameterException();
		}

		UtenteDTO utente = autenticazioneMapper.getUtenteByCredenziali(username, password);
		if (utente != null) {
			return getUtenteByUsername(utente.getUsername());
		} else {
			return null;
		}
	}

	private List<GrantDTO> getGrantByIdUtente(Long idUtente) {
		if (null == idUtente || idUtente <= 0L) {
			throw new InvalidParameterException();
		}

		return autenticazioneMapper.getGrantByIdUtente(idUtente);
	}

	private List<GrantDTO> getGrantByIdUtenteAndType(Long idUtente, String type) {
		if (StringUtils.isEmpty(type)) {
			throw new InvalidParameterException();
		}

		return getGrantByIdUtente(idUtente).stream().filter(candidate -> candidate.getGrantType().equals(type))
				.collect(Collectors.toList());
	}

	@Override
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public LoginTokenDTO getTokenBySeries(String series) throws BusinessException {
		return autenticazioneMapper.getTokenBySeries(series);
	}

	@Override
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public int insertToken(LoginTokenDTO token) throws BusinessException {
		return autenticazioneMapper.insertToken(token);
	}

	@Override
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public int updateTokenBySeries(LoginTokenDTO token) throws BusinessException {
		return autenticazioneMapper.updateTokenBySeries(token);
	}

	@Override
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public int removeTokenByUsername(String username) throws BusinessException {
		return autenticazioneMapper.removeTokenByUsername(username);
	}

	@Override
	public UtenteDTO getUtenteByCredenzialiEnc(String username) throws BusinessException {
		UtenteDTO utente =  autenticazioneMapper.getUtenteByCredenzialiEnc(username);
		
		if (utente != null) {
			utente.getGrant().addAll(getGrantByIdUtente(utente.getId()));
		}
		
		return utente;
	}

	@Override
	public void insertTTraceLogin(Long idUtente) throws BusinessException {
			
		CarTTraceLogin record = new CarTTraceLogin();
		record.setIdUtente(idUtente);
		record.setDataInsert(new Date());
		traceLoginMapper.insert(record);		
	}

}
