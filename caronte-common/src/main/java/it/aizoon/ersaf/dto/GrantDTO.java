package it.aizoon.ersaf.dto;

import java.io.Serializable;

/**
 * @author ff
 */
public class GrantDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String grantType;
	private Long id;
	private String codice;
	private String descrizione;

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
