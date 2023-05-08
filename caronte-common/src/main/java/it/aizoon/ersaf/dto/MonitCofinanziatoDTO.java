package it.aizoon.ersaf.dto;

import java.io.Serializable;

public class MonitCofinanziatoDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idSpecie;
	private Long idOrganismoNocivo;
	private String denomOrganismoNocivo;
	private Long idGenere;
	private String denomGenere;
	private String note;
	private String denomSpecie;
	private Long numeroPiante;
	private String oraInizioMonit;
	private String oraFineMonit;
	private String latitudine;
	private String longitudine;
	private String noteMonit;

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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getOraInizioMonit() {
		return oraInizioMonit;
	}
	public void setOraInizioMonit(String oraInizioMonit) {
		this.oraInizioMonit = oraInizioMonit;
	}
	public String getOraFineMonit() {
		return oraFineMonit;
	}
	public void setOraFineMonit(String oraFineMonit) {
		this.oraFineMonit = oraFineMonit;
	}
	public String getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}
	public String getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}
	public String getNoteMonit() {
		return noteMonit;
	}
	public void setNoteMonit(String noteMonit) {
		this.noteMonit = noteMonit;
	}

		
}	
