package it.aizoon.ersaf.dto;

import java.io.Serializable;

public class OrgNocivoGenereSpecieDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Long idSpecieMisura;
	private Long idOrganismoNocivo;
	private String denomOrganismoNocivo;
	private Long idGenere;
	private String denomGenere;
	private String note;
	private Long idSpecie;
	private String denomSpecie;
	private Long numeroPiante;
	private String descEstesa;
	private String descBreveON;
	private String codiceEppo;
	private Long idTipoOrgNocivo;
	private String descBreveTipoON;
	private String descTipoProdotto;
	private Boolean attivo;
	private Long idZonaProtetta;
	private Long idTipoProdotto;
	private Long idGruppoZonaProtetta;

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
	public Long getNumeroPiante() {
		return numeroPiante;
	}
	public void setNumeroPiante(Long numeroPiante) {
		this.numeroPiante = numeroPiante;
	}
	public Long getIdOrganismoNocivo() {
		return idOrganismoNocivo;
	}
	public void setIdOrganismoNocivo(Long idOrganismoNocivo) {
		this.idOrganismoNocivo = idOrganismoNocivo;
	}
	public String getDenomOrganismoNocivo() {
		return denomOrganismoNocivo;
	}
	public void setDenomOrganismoNocivo(String denomOrganismoNocivo) {
		this.denomOrganismoNocivo = denomOrganismoNocivo;
	}
	public Long getIdSpecieMisura() {
		return idSpecieMisura;
	}
	public void setIdSpecieMisura(Long idSpecieMisura) {
		this.idSpecieMisura = idSpecieMisura;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the descEstesa
	 */
	public String getDescEstesa() {
		return descEstesa;
	}
	/**
	 * @param descEstesa the descEstesa to set
	 */
	public void setDescEstesa(String descEstesa) {
		this.descEstesa = descEstesa;
	}
	/**
	 * @return the descBreveON
	 */
	public String getDescBreveON() {
		return descBreveON;
	}
	/**
	 * @param descBreveON the descBreveON to set
	 */
	public void setDescBreveON(String descBreveON) {
		this.descBreveON = descBreveON;
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
		this.codiceEppo = codiceEppo;
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
	 * @return the descBreveTipoON
	 */
	public String getDescBreveTipoON() {
		return descBreveTipoON;
	}
	/**
	 * @param descBreveTipoON the descBreveTipoON to set
	 */
	public void setDescBreveTipoON(String descBreveTipoON) {
		this.descBreveTipoON = descBreveTipoON;
	}
	/**
	 * @return the descTipoProdotto
	 */
	public String getDescTipoProdotto() {
		return descTipoProdotto;
	}
	/**
	 * @param descTipoProdotto the descTipoProdotto to set
	 */
	public void setDescTipoProdotto(String descTipoProdotto) {
		this.descTipoProdotto = descTipoProdotto;
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
	 * @return the idZonaProtetta
	 */
	public Long getIdZonaProtetta() {
		return idZonaProtetta;
	}
	/**
	 * @param idZonaProtetta the idZonaProtetta to set
	 */
	public void setIdZonaProtetta(Long idZonaProtetta) {
		this.idZonaProtetta = idZonaProtetta;
	}
	public Long getIdTipoProdotto() {
		return idTipoProdotto;
	}
	public void setIdTipoProdotto(Long idTipoProdotto) {
		this.idTipoProdotto = idTipoProdotto;
	}
	public Long getIdGruppoZonaProtetta() {
		return idGruppoZonaProtetta;
	}
	public void setIdGruppoZonaProtetta(Long idGruppoZonaProtetta) {
		this.idGruppoZonaProtetta = idGruppoZonaProtetta;
	}

		
}	
