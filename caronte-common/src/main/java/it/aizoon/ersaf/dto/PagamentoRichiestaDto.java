package it.aizoon.ersaf.dto;

import it.aizoon.ersaf.dto.generati.CarTPagamento;

public class PagamentoRichiestaDto extends CarTPagamento {
	
	private String tipoPagamento;

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
}
