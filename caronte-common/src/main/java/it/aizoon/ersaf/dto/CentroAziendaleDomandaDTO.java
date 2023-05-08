package it.aizoon.ersaf.dto;

import java.util.List;

import it.aizoon.ersaf.dto.generati.CarTCentroAziendale;
import it.aizoon.ersaf.dto.generati.CarTSitoProduzione;


@SuppressWarnings("serial")
public class CentroAziendaleDomandaDTO extends CarTCentroAziendale {

  private List<TipologiaProdSpecieDTO> tipologieList;
  private List<SitoProduzioneDTO> sitiProduzioneList;
  
  private String denomComune;
  private String siglaProvincia;
  
	public List<TipologiaProdSpecieDTO> getTipologieList() {
		return tipologieList;
	}
	public void setTipologieList(List<TipologiaProdSpecieDTO> tipologieList) {
		this.tipologieList = tipologieList;
	}
	public List<SitoProduzioneDTO> getSitiProduzioneList() {
		return sitiProduzioneList;
	}
	public void setSitiProduzioneList(List<SitoProduzioneDTO> sitiProduzioneList) {
		this.sitiProduzioneList = sitiProduzioneList;
	}		
	public String getDenomComune() {
		return denomComune;
	}
	public void setDenomComune(String denomComune) {
		this.denomComune = denomComune;
	}
	public String getSiglaProvincia() {
		return siglaProvincia;
	}
	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

 
}
