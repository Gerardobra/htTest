package it.aizoon.ersaf.dto;


public class MisuraDTO {

	private Long idMisura;
	private String note;
	private String descEstesa;
		
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Long getIdMisura() {
		return idMisura;
	}
	public void setIdMisura(Long idMisura) {
		this.idMisura = idMisura;
	}
	public String getDescEstesa() {
		return descEstesa;
	}
	public void setDescEstesa(String descEstesa) {
		this.descEstesa = descEstesa;
	}

}
