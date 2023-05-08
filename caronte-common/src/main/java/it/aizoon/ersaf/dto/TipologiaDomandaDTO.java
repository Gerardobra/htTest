package it.aizoon.ersaf.dto;


import java.util.List;

import it.aizoon.ersaf.dto.generati.CarRDomandaTipologia;



@SuppressWarnings("serial")
public class TipologiaDomandaDTO {

	// *** TAB TIPOLOGIA
	
	List<CarRDomandaTipologia> tipologieDomandaList;

	public List<CarRDomandaTipologia> getTipologieDomandaList() {
		return tipologieDomandaList;
	}

	public void setTipologieDomandaList(List<CarRDomandaTipologia> tipologieDomandaList) {
		this.tipologieDomandaList = tipologieDomandaList;
	}
	
}
