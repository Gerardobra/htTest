package it.aizoon.ersaf.dto;

import java.io.Serializable;
import java.util.List;

public class TipologiaProdSpecieDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Long idTipologia;	
	Long idVoce;
	String denomTipologia;
	String denomTipologiaEstesa;
	String note;
	Long idGenere;
	List<GenereSpecieDTO> specieList;
		
	
	public Long getIdTipologia() {
		return idTipologia;
	}
	public void setIdTipologia(Long idTipologia) {
		this.idTipologia = idTipologia;
	}
	public String getDenomTipologia() {
		return denomTipologia;
	}
	public void setDenomTipologia(String denomTipologia) {
		this.denomTipologia = denomTipologia;
	}
	public List<GenereSpecieDTO> getSpecieList() {
		return specieList;
	}
	public void setSpecieList(List<GenereSpecieDTO> specieList) {
		this.specieList = specieList;
	}
	public String getDenomTipologiaEstesa() {
		return denomTipologiaEstesa;
	}
	public void setDenomTipologiaEstesa(String denomTipologiaEstesa) {
		this.denomTipologiaEstesa = denomTipologiaEstesa;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Long getIdGenere() {
		return idGenere;
	}
	public void setIdGenere(Long idGenere) {
		this.idGenere = idGenere;
	}
	public Long getIdVoce() {
		return idVoce;
	}
	public void setIdVoce(Long idVoce) {
		this.idVoce = idVoce;
	}
	
	
	
		
}	
