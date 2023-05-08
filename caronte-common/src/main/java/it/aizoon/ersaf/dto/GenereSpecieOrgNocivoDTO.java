package it.aizoon.ersaf.dto;

import java.io.Serializable;
import java.util.List;

public class GenereSpecieOrgNocivoDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idZonaProtetta;
	private Long idTipoOrgNocivo;
	private String descTipoOrgNocivo;
	private Long idTipoProdotto;
	private String descTipoProdotto;
	private Long idGenere;
	private String denomGenere;
	private Long idSpecie;
	private String denomSpecie;
	private String codiceEppo;
	private String descrOrgNocivo;
	private Long idGruppoZonaProtetta;
	
	public Long getIdZonaProtetta() {
		return idZonaProtetta;
	}
	public void setIdZonaProtetta(Long idZonaProtetta) {
		this.idZonaProtetta = idZonaProtetta;
	}
	public Long getIdTipoProdotto() {
		return idTipoProdotto;
	}
	public void setIdTipoProdotto(Long idTipoProdotto) {
		this.idTipoProdotto = idTipoProdotto;
	}
	public String getDescTipoProdotto() {
		return descTipoProdotto;
	}
	public void setDescTipoProdotto(String descTipoProdotto) {
		this.descTipoProdotto = descTipoProdotto;
	}
	public Long getIdGenere() {
		return idGenere;
	}
	public void setIdGenere(Long idGenere) {
		this.idGenere = idGenere;
	}
	public String getDenomGenere() {
		return denomGenere;
	}
	public void setDenomGenere(String denomGenere) {
		this.denomGenere = denomGenere;
	}
	public Long getIdSpecie() {
		return idSpecie;
	}
	public void setIdSpecie(Long idSpecie) {
		this.idSpecie = idSpecie;
	}
	public String getDenomSpecie() {
		return denomSpecie;
	}
	public void setDenomSpecie(String denomSpecie) {
		this.denomSpecie = denomSpecie;
	}
	public Long getIdTipoOrgNocivo() {
		return idTipoOrgNocivo;
	}
	public void setIdTipoOrgNocivo(Long idTipoOrgNocivo) {
		this.idTipoOrgNocivo = idTipoOrgNocivo;
	}
	public String getDescTipoOrgNocivo() {
		return descTipoOrgNocivo;
	}
	public void setDescTipoOrgNocivo(String descTipoOrgNocivo) {
		this.descTipoOrgNocivo = descTipoOrgNocivo;
	}
	public String getCodiceEppo() {
		return codiceEppo;
	}
	public void setCodiceEppo(String codiceEppo) {
		this.codiceEppo = codiceEppo;
	}
	public String getDescrOrgNocivo() {
		return descrOrgNocivo;
	}
	public void setDescrOrgNocivo(String descrOrgNocivo) {
		this.descrOrgNocivo = descrOrgNocivo;
	}
	public Long getIdGruppoZonaProtetta() {
		return idGruppoZonaProtetta;
	}
	public void setIdGruppoZonaProtetta(Long idGruppoZonaProtetta) {
		this.idGruppoZonaProtetta = idGruppoZonaProtetta;
	}
}	
