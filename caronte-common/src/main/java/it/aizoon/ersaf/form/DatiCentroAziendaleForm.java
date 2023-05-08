package it.aizoon.ersaf.form;


import java.util.List;

import it.aizoon.ersaf.dto.SitoProduzioneDTO;
import it.aizoon.ersaf.dto.TipologiaProdSpecieDTO;



public class DatiCentroAziendaleForm extends BaseForm {

	private Long idDomanda;
	private Long idCentroAziendale;
	private String ragioneSociale;
	
	private List<TipologiaProdSpecieDTO> tipologieList;
	private List<SitoProduzioneDTO> sitiProduzioneList;
	
	
	public Long getIdDomanda() {
		return idDomanda;
	}
	public void setIdDomanda(Long idDomanda) {
		this.idDomanda = idDomanda;
	}
	public Long getIdCentroAziendale() {
		return idCentroAziendale;
	}
	public void setIdCentroAziendale(Long idCentroAziendale) {
		this.idCentroAziendale = idCentroAziendale;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
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


}
