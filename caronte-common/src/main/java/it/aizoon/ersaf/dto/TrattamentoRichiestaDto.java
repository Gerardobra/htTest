package it.aizoon.ersaf.dto;

import java.util.Date;

import it.aizoon.ersaf.dto.generati.CarRTrattamentoRichiesta;

public class TrattamentoRichiestaDto extends CarRTrattamentoRichiesta {

	private String descTrattamento;
	
	public String getDescTrattamento() {
		return descTrattamento;
	}

	public void setDescTrattamento(String descTrattamento) {
		this.descTrattamento = descTrattamento;
	}


}
