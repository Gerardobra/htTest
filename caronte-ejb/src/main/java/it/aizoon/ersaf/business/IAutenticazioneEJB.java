package it.aizoon.ersaf.business;

import javax.ejb.Local;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import it.aizoon.ersaf.dto.LoginTokenDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.exception.BusinessException;

/**
 * @author ff
 */
@Component
@Local
public interface IAutenticazioneEJB {

	UtenteDTO getUtenteByCredenziali(String username, String password) throws BusinessException;
	
	UtenteDTO getUtenteByUsername(String username) throws BusinessException;
	
	LoginTokenDTO getTokenBySeries(@Param("series") String series) throws BusinessException;

	int insertToken(LoginTokenDTO token) throws BusinessException;

	int updateTokenBySeries(LoginTokenDTO token) throws BusinessException;

	int removeTokenByUsername(@Param("username") String username) throws BusinessException;

	UtenteDTO getUtenteByCredenzialiEnc(String username) throws BusinessException;

	void insertTTraceLogin(Long id)throws BusinessException;
}
