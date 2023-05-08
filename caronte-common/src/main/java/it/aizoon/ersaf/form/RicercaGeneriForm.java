package it.aizoon.ersaf.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.util.CaronteUtils;

public class RicercaGeneriForm extends BaseForm{
	
	@NumberFormat(style= Style.NUMBER)
	public Long idNazione;
	
	private String codGenere;
	
	private String denomGenere;
	
	private String denomGenereCommerciale;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataInsert;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataUpdate;
	
	private Boolean attivo;
	
	private boolean preferred;
	
	private String denomNazione;
	
	private String descLingua;
	
	private String codLingua;
	
	private String denomGenereLocale;

	public Long getIdNazione() {
		return idNazione;
	}
	

	public void setIdNazione(Long idNazione) {
		this.idNazione = idNazione;
	}

	public String getCodGenere() {
		return codGenere;
	}

	public void setCodGenere(String codGenere) {
		this.codGenere = CaronteUtils.convertToUpperCase(codGenere);
	}

	public String getDenomGenere() {
		return denomGenere;
	}

	public void setDenomGenere(String denomGenere) {
		this.denomGenere = CaronteUtils.convertToUpperCase(denomGenere);
	}

	public String getDenomGenereCommerciale() {
		return denomGenereCommerciale;
	}

	public void setDenomGenereCommerciale(String denomGenereCommerciale) {
		this.denomGenereCommerciale = CaronteUtils.convertToUpperCase(denomGenereCommerciale);
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

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public boolean isPreferred() {
		return preferred;
	}

	public void setPreferred(boolean preferred) {
		this.preferred = preferred;
	}

	public String getDenomNazione() {
		return denomNazione;
	}

	public void setDenomNazione(String denomNazione) {
		this.denomNazione = CaronteUtils.convertToUpperCase(denomNazione);
	}


	public String getDescLingua() {
		return descLingua;
	}

	public void setDescLingua(String descLingua) {
		this.descLingua = CaronteUtils.convertToUpperCase(descLingua);
	}


	public String getDenomGenereLocale() {
		return denomGenereLocale;
	}


	public void setDenomGenereLocale(String denomGenereLocale) {
		this.denomGenereLocale = CaronteUtils.convertToUpperCase(denomGenereLocale);
	}


	public String getCodLingua() {
		return codLingua;
	}


	public void setCodLingua(String codLingua) {
		this.codLingua = CaronteUtils.convertToUpperCase(codLingua);
	}

}
