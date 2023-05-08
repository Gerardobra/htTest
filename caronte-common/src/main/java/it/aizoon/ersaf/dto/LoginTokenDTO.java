package it.aizoon.ersaf.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ff
 */
public class LoginTokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String series;
	private String token;
	private Date lastUsed;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

}
