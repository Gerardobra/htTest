package it.aizoon.ersaf.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.aizoon.ersaf.dto.GrantDTO;
import it.aizoon.ersaf.dto.UtenteDTO;

public class ErsafRememberMeUserDetails extends UtenteDTO implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// TODO questa roba andrebbe cachata
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantDTO> raw = super.getGrant();
		if (raw == null) return null;
		return raw.stream().map(o -> new SimpleGrantedAuthority(o.getCodice())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return "not-allowed";
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}