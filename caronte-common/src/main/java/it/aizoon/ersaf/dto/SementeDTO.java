package it.aizoon.ersaf.dto;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class SementeDTO {

	private Long idTipologiaSemente;
	@NumberFormat(style= Style.CURRENCY)
	private BigDecimal quantita;
	private String note;
	private String descEstesa;
	
	public Long getIdTipologiaSemente() {
		return idTipologiaSemente;
	}
	public void setIdTipologiaSemente(Long idTipologiaSemente) {
		this.idTipologiaSemente = idTipologiaSemente;
	}
	public BigDecimal getQuantita() {
		return quantita;
	}
	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDescEstesa() {
		return descEstesa;
	}
	public void setDescEstesa(String descEstesa) {
		this.descEstesa = descEstesa;
	}

}
