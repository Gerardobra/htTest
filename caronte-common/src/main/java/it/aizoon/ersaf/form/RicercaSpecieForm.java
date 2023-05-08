package it.aizoon.ersaf.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.util.CaronteUtils;

public class RicercaSpecieForm extends BaseForm{
	
	@NumberFormat(style= Style.NUMBER)
	public Long idNazione;
	
	private String codSpecie;
	
	private String denomSpecie;
	
	private String denomSpecieCommerciale;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataInsert;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataUpdate;
	
	private Boolean attivo;
	
	private boolean preferred;
	
	private String denomNazione;
	
	private String descLingua;
	
	private String codLingua;
	
	private String denomSpecieLocale;
	
	private String denomGenere;

	private Long idSpecie;

	public Long getIdNazione() {
		return idNazione;
	}

	public void setIdNazione(Long idNazione) {
		this.idNazione = idNazione;
	}

	public String getCodSpecie() {
		return codSpecie;
	}

	public void setCodSpecie(String codSpecie) {
		this.codSpecie = CaronteUtils.convertToUpperCase(codSpecie);
	}

	public String getDenomSpecie() {
		return denomSpecie;
	}

	public void setDenomSpecie(String denomSpecie) {
		this.denomSpecie = CaronteUtils.convertToUpperCase(denomSpecie);
	}

	public String getDenomSpecieCommerciale() {
		return denomSpecieCommerciale;
	}

	public void setDenomSpecieCommerciale(String denomSpecieCommerciale) {
		this.denomSpecieCommerciale = CaronteUtils.convertToUpperCase(denomSpecieCommerciale);
	}

	public Date getDataInsert() {
		return dataInsert;
	}

	public void setDataInsert(Date dataInsert) {
		this.dataInsert = dataInsert;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public Boolean getAttivo() {
		return attivo;
	}
	
	public void setAttivo(Boolean attivo){
		this.attivo = attivo;
	}

	public Boolean isPreferred() {
		return preferred;
	}

	public void setPreferred(boolean preferred) {
		this.preferred = preferred;
	}

	public String getDenomNazione() {
		return denomNazione;
	}

	public void setDenomNazione(String denomNazione) {
		this.denomNazione = denomNazione;
	}

	public String getDescLingua() {
		return descLingua;
	}

	public void setDescLingua(String descLingua) {
		this.descLingua = CaronteUtils.convertToUpperCase(descLingua);
	}

	public String getCodLingua() {
		return codLingua;
	}

	public void setCodLingua(String codLingua) {
		this.codLingua = CaronteUtils.convertToUpperCase(codLingua);
	}

	public String getDenomSpecieLocale() {
		return denomSpecieLocale;
	}

	public void setDenomSpecieLocale(String denomSpecieLocale) {
		this.denomSpecieLocale = CaronteUtils.convertToUpperCase(denomSpecieLocale);
	}

	public Long getIdSpecie() {
		return idSpecie;
	}

	public void setIdSpecie(Long idSpecie) {
		this.idSpecie = idSpecie;
	}

	public String getDenomGenere() {
		return denomGenere;
	}

	public void setDenomGenere(String denomGenere) {
		this.denomGenere = CaronteUtils.convertToUpperCase(denomGenere);
	}

}
