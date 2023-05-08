package it.aizoon.ersaf.dto;

import java.io.Serializable;
import java.util.List;

import it.aizoon.ersaf.dto.generati.CarDMateriale;

public class TipologiaAttMaterialeDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Long idAttivitaMaterialeUtente;
	
	Long idTipologia;	
	String denomTipologia;
	String denomTipologiaEstesa;
	
	Long idMateriale;
	String descBreve;
	String descEstesa;
	
	String note;
	Long idDomanda;
	String richiestaPassaporto;
		
	
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
	public String getDenomTipologiaEstesa() {
		return denomTipologiaEstesa;
	}
	public void setDenomTipologiaEstesa(String denomTipologiaEstesa) {
		this.denomTipologiaEstesa = denomTipologiaEstesa;
	}
	public Long getIdAttivitaMaterialeUtente() {
		return idAttivitaMaterialeUtente;
	}
	public void setIdAttivitaMaterialeUtente(Long idAttivitaMaterialeUtente) {
		this.idAttivitaMaterialeUtente = idAttivitaMaterialeUtente;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Long getIdDomanda() {
		return idDomanda;
	}
	public void setIdDomanda(Long idDomanda) {
		this.idDomanda = idDomanda;
	}
	public Long getIdMateriale() {
		return idMateriale;
	}
	public void setIdMateriale(Long idMateriale) {
		this.idMateriale = idMateriale;
	}
	public String getDescBreve() {
		return descBreve;
	}
	public void setDescBreve(String descBreve) {
		this.descBreve = descBreve;
	}
	public String getDescEstesa() {
		return descEstesa;
	}
	public void setDescEstesa(String descEstesa) {
		this.descEstesa = descEstesa;
	}
	public String getRichiestaPassaporto() {
		return richiestaPassaporto;
	}
	public void setRichiestaPassaporto(String richiestaPassaporto) {
		this.richiestaPassaporto = richiestaPassaporto;
	}
	
	
	
		
}	
