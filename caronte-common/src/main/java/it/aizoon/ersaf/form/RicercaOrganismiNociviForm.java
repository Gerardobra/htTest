package it.aizoon.ersaf.form;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.util.CaronteUtils;

public class RicercaOrganismiNociviForm extends BaseForm{
	
	
	@NumberFormat(style= Style.NUMBER)
	public Long idOrgNocivo;
	
	private Boolean attivo;
	
	@NumberFormat(style = Style.NUMBER)
	private Long idTipoOrgNocivo;	
	
	private String codiceEppo;
	private String descOn;
	
	
	public Long getIdOrgNocivo() {
		return idOrgNocivo;
	}
	
	public void setIdOrgNocivo(Long idOrgNocivo) {
		this.idOrgNocivo = idOrgNocivo;
	}

	/**
	 * @return the attivo
	 */
	public Boolean getAttivo() {
		return attivo;
	}

	/**
	 * @param attivo the attivo to set
	 */
	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	/**
	 * @return the idTipoOrgNocivo
	 */
	public Long getIdTipoOrgNocivo() {
		return idTipoOrgNocivo;
	}

	/**
	 * @param idTipoOrgNocivo the idTipoOrgNocivo to set
	 */
	public void setIdTipoOrgNocivo(Long idTipoOrgNocivo) {
		this.idTipoOrgNocivo = idTipoOrgNocivo;
	}

	/**
	 * @return the codiceEppo
	 */
	public String getCodiceEppo() {
		return codiceEppo;
	}

	/**
	 * @param codiceEppo the codiceEppo to set
	 */
	public void setCodiceEppo(String codiceEppo) {
		this.codiceEppo = CaronteUtils.convertToUpperCase(codiceEppo);
	}

	/**
	 * @return the descOn
	 */
	public String getDescOn() {
		return descOn;
	}

	/**
	 * @param descOn the descOn to set
	 */
	public void setDescOn(String descOn) {
		this.descOn = CaronteUtils.convertToUpperCase(descOn);
	}

	
	

}
