package it.aizoon.ersaf.dto;

import java.math.BigDecimal;

import it.aizoon.ersaf.dto.generati.CarTSitoProduzione;

@SuppressWarnings("serial")
public class SitoProduzioneDTO extends CarTSitoProduzione {
  
  private String denomComune;
  private Long idProvincia;
  private String siglaProvincia;
  private BigDecimal superficie;
  
  
	public String getDenomComune() {
		return denomComune;
	}
	public void setDenomComune(String denomComune) {
		this.denomComune = denomComune;
	}
	public Long getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getSiglaProvincia() {
		return siglaProvincia;
	}
	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

  
  
}
