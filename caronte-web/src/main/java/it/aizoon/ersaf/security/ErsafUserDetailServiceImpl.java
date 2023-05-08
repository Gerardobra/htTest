package it.aizoon.ersaf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import it.aizoon.ersaf.business.IAutenticazioneEJB;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.exception.BusinessException;

/**
 * @author ff
 */
public class ErsafUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private IAutenticazioneEJB autenticazioneEjb = null;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UtenteDTO utenteDTO;
		
		try {
			utenteDTO = autenticazioneEjb.getUtenteByUsername(username);
		} catch (BusinessException e) {
			throw new RuntimeException("errore nella verifica del token", e);
		}
		
		ErsafRememberMeUserDetails u = new ErsafRememberMeUserDetails();
		if (utenteDTO!=null) {
			u.setCognome(utenteDTO.getCognome());
			u.setGrant(utenteDTO.getGrant());
			u.setId(utenteDTO.getId());
			u.setNome(utenteDTO.getNome());
			u.setUsername(username);
			u.setIdSpedizioniere(utenteDTO.getIdSpedizioniere());
			u.setSuperUser(utenteDTO.isSuperUser());
		}
		
		return u;
	}

}
