package it.aizoon.ersaf.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import it.aizoon.ersaf.dto.UtenteDTO;

public abstract class SecurityUtils {

	public static UtenteDTO getUtenteLoggato() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			UtenteDTO currentUser = (UtenteDTO)authentication.getPrincipal();
		  return currentUser;
		} else {
			return null;
		}
	}
	
	public static boolean hasUserRole(String role) {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  
	  if (!StringUtils.isEmpty(role) && authentication.getAuthorities() != null) {
	    for (GrantedAuthority authority : authentication.getAuthorities()) {
	      if (authority.getAuthority().toUpperCase().contains(role.toUpperCase())) {
	        return true;
	      }
	    }
	  }
	  
	  return false;
	}
	
	
}
