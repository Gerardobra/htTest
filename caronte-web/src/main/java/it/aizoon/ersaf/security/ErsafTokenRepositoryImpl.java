package it.aizoon.ersaf.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import it.aizoon.ersaf.business.IAutenticazioneEJB;
import it.aizoon.ersaf.dto.LoginTokenDTO;
import it.aizoon.ersaf.exception.BusinessException;

/**
 * @author ff
 */
public class ErsafTokenRepositoryImpl implements PersistentTokenRepository {

	@Autowired
	private IAutenticazioneEJB autenticazioneEjb = null;

	protected void initDao() {
		// NOP
	}

	private PersistentRememberMeToken map(LoginTokenDTO dto) {
		PersistentRememberMeToken token = new PersistentRememberMeToken(dto.getUsername(), dto.getSeries(),
				dto.getToken(), dto.getLastUsed());

		return token;
	}

	private LoginTokenDTO map(PersistentRememberMeToken token) {
		LoginTokenDTO tokenDTO = new LoginTokenDTO();
		tokenDTO.setUsername(token.getUsername());
		tokenDTO.setSeries(token.getSeries());
		tokenDTO.setToken(token.getTokenValue());
		tokenDTO.setLastUsed(token.getDate());
		return tokenDTO;
	}

	private LoginTokenDTO map(String series, String tokenValue, Date lastUsed) {
		LoginTokenDTO tokenDTO = new LoginTokenDTO();
		tokenDTO.setSeries(series);
		tokenDTO.setToken(tokenValue);
		tokenDTO.setLastUsed(lastUsed);
		return tokenDTO;
	}

	@Override
  public void createNewToken(PersistentRememberMeToken token) {
		LoginTokenDTO tokenDTO = map(token);
		try {
			autenticazioneEjb.insertToken(tokenDTO);
		} catch (BusinessException e) {
			throw new RuntimeException("errore nella creazione del nuovo token", e);
		}
	}

	@Override
  public void updateToken(String series, String tokenValue, Date lastUsed) {
		LoginTokenDTO tokenDTO = map(series, tokenValue, lastUsed);
		try {
			autenticazioneEjb.updateTokenBySeries(tokenDTO);
		} catch (BusinessException e) {
			throw new RuntimeException("errore nell'aggiornamento del token", e);
		}
	}

	@Override
  public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		LoginTokenDTO tokenDTO;
		try {
			tokenDTO = autenticazioneEjb.getTokenBySeries(seriesId);
		} catch (BusinessException e) {
			throw new RuntimeException("errore nella ricerca del token", e);
		}
		
		if (tokenDTO != null) {
			PersistentRememberMeToken token = map(tokenDTO);
			return token;
		}
		return null;
	}

	@Override
  public void removeUserTokens(String username) {
		try {
			autenticazioneEjb.removeTokenByUsername(username);
		} catch (BusinessException e) {
			throw new RuntimeException("errore nella rimozione del token", e);
		}
	}

}
