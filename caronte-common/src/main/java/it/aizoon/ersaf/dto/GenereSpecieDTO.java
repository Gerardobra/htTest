package it.aizoon.ersaf.dto;

import java.io.Serializable;
import java.util.List;

public class GenereSpecieDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idGenere;
	private String denomGenere;
	private Long idSpecie;
	private String denomSpecie;
	private Long numeroPiante;
	private Long numPianteSintomatiche;
	private List<Long> idOrgNocivoList;
	private String descBreveOn;
	
	private Long idControlloFisicoSpecie;
	private Long idOrgNocivo;


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

	public Long getNumPianteSintomatiche() {
		return numPianteSintomatiche;
	}

	public void setNumPianteSintomatiche(Long numeroPianteSintomatiche) {
		this.numPianteSintomatiche = numeroPianteSintomatiche;
	}

	public String getDescBreveOn() {
		return descBreveOn;
	}

	public void setDescBreveOn(String descBreveOn) {
		this.descBreveOn = descBreveOn;
	}
	public List<Long> getIdOrgNocivoList() {
		return idOrgNocivoList;
	}
	public void setIdOrgNocivoList(List<Long> idOrgNocivoList) {
		this.idOrgNocivoList = idOrgNocivoList;
	}
	public Long getIdControlloFisicoSpecie() {
		return idControlloFisicoSpecie;
	}
	public void setIdControlloFisicoSpecie(Long idControlloFisicoSpecie) {
		this.idControlloFisicoSpecie = idControlloFisicoSpecie;
	}
	public Long getIdOrgNocivo() {
		return idOrgNocivo;
	}
	public void setIdOrgNocivo(Long idOrgNocivo) {
		this.idOrgNocivo = idOrgNocivo;
	}


		
}	
