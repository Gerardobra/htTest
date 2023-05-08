package it.aizoon.ersaf.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.aizoon.ersaf.business.IAutenticazioneEJB;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.exception.BusinessException;

/**
 * @author ff
 */
@Component
public class ErsafAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private IAutenticazioneEJB autenticazioneEjb = null;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		UtenteDTO utente;
		try {
			
			utente = autenticazioneEjb.getUtenteByCredenzialiEnc(username);

			//check password
			if (utente != null && !passwordEncoder.matches(password, utente.getPassword())) {
				utente = null;
			}
			  
			if (utente != null) {
			  //check abilitazione
	      if (!utente.isAbilitato()) {
	        throw new DisabledException("L'utente non è abilitato ad operare nell'applicativo");
	      }
	      
	      //login successful - trace login informations
        autenticazioneEjb.insertTTraceLogin(utente.getId());
			}
						
		} catch (BusinessException e) {
			// autenticazione fallita
			throw new RuntimeException("Errore interno in fase di autenticazione", e);
		}

		if (utente == null) {
			throw new AuthenticationCredentialsNotFoundException("Credenziali fornite non valide");
		}

		List<SimpleGrantedAuthority> authoritiesList = utente.getGrant().stream()
				.map(grant -> new SimpleGrantedAuthority(grant.getCodice())).collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(utente, utente.getPassword(), authoritiesList);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
