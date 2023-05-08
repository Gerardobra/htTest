package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.GrantDTO;
import it.aizoon.ersaf.dto.LoginTokenDTO;
import it.aizoon.ersaf.dto.UtenteDTO;

/**
 * @author ff
 */
@Mapper
public interface AutenticazioneMapper {

	UtenteDTO getUtenteByCredenziali(@Param("username") String username, @Param("password") String password);

	UtenteDTO getUtenteByUsername(@Param("username") String username);

	List<GrantDTO> getGrantByIdUtente(@Param("idUtente") Long idUtente);

	LoginTokenDTO getTokenBySeries(@Param("series") String series);

	int insertToken(LoginTokenDTO token);

	int updateTokenBySeries(LoginTokenDTO token);

	int removeTokenByUsername(@Param("username") String username);

	UtenteDTO getUtenteByCredenzialiEnc(String username);

}
