package it.aizoon.ersaf.dto;


public class CodeDescriptionDTO extends BaseDto {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long id;
	private String descr;
	private String cod;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}



}
