package it.aizoon.ersaf.dto;

import java.io.Serializable;
import java.util.List;

public class ZonaProtettaSpecieDTO implements Serializable {

	
	/**
	 * Arcarese Valentino
	 */
	private static final long serialVersionUID = 1L;
	
	Long idGruppoZonaProtetta;	
	String denomZonaProtetta;
	List<GenereSpecieDTO> specieList;
	
	
		
	public Long getIdGruppoZonaProtetta() {
		return idGruppoZonaProtetta;
	}
	public void setIdGruppoZonaProtetta(Long idGruppoZonaProtetta) {
		this.idGruppoZonaProtetta = idGruppoZonaProtetta;
	}
	public String getDenomZonaProtetta() {
		return denomZonaProtetta;
	}
	public void setDenomZonaProtetta(String denomZonaProtetta) {
		this.denomZonaProtetta = denomZonaProtetta;
	}
	public List<GenereSpecieDTO> getSpecieList() {
		return specieList;
	}
	public void setSpecieList(List<GenereSpecieDTO> specieList) {
		this.specieList = specieList;
	}	
	
		
}	
