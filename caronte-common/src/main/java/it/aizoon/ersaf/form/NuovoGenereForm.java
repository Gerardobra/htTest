package it.aizoon.ersaf.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.util.CaronteUtils;

public class NuovoGenereForm extends BaseForm{
	
	@NumberFormat(style= Style.NUMBER)
	public Long idNazione;

	@NumberFormat(style = Style.NUMBER)	
	private Long idGenere;
	
	@NotNull(message="Campo obbligatorio")
	@Size(min=1, max=10)
	private String codGenere;
	
	@NotNull(message="Campo obbligatorio")
	@Size(min=1, max=200)
	private String denomGenere;
	
	private String denomGenereCommerciale;
	
	@NumberFormat(style = Style.NUMBER)	
	private Long idAutoreEppo;
	
	private String descAutoreEppo;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataInsert;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataUpdate;
	
	private boolean attivo = true;
	
	private boolean preferred = true;
	
	private String denomNazione;
	
	@NumberFormat(style = Style.NUMBER)
	private Long idLingua;
	
	private String denomGenereLocale;
	
	private Long idLinguaLocale;

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

	public String getDescAutoreEppo() {
		return descAutoreEppo;
	}

	public void setDescAutoreEppo(String descAutoreEppo) {
		this.descAutoreEppo = CaronteUtils.convertToUpperCase(descAutoreEppo);
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

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
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

	public Long getIdAutoreEppo() {
		return idAutoreEppo;
	}

	public void setIdAutoreEppo(Long idAutoreEppo) {
		this.idAutoreEppo = idAutoreEppo;
	}

	public Long getIdLingua() {
		return idLingua;
	}

	public void setIdLingua(Long idLingua) {
		this.idLingua = idLingua;
	}

	public String getDenomGenereLocale() {
		return denomGenereLocale;
	}

	public void setDenomGenereLocale(String denomGenereLocale) {
		this.denomGenereLocale = CaronteUtils.convertToUpperCase(denomGenereLocale);
	}
	
	public Long getIdGenere() {
		return idGenere;
	}

	public void setIdGenere(Long idGenere) {
		this.idGenere = idGenere;
	}

	public Long getIdLinguaLocale() {
		return idLinguaLocale;
	}

	public void setIdLinguaLocale(Long idLinguaLocale) {
		this.idLinguaLocale = idLinguaLocale;
	}

	public void setDatiFromDTO(GenereDTO genere){
		setIdGenere(genere.getIdGenere());
		setAttivo(genere.isAttivo());
		setCodGenere(genere.getCodGenere());
		setDataInsert(genere.getDataInsert());
		setDataUpdate(genere.getDataUpdate());
		setDenomGenere(genere.getDenomGenere());
		setDenomGenereCommerciale(genere.getDenomGenereCommerciale());
		setDenomGenereLocale(genere.getDenomGenereLocale());
		setDenomNazione(genere.getDenomNazione());
		setDescAutoreEppo(genere.getDescAutoreEppo());
		setIdAutoreEppo(genere.getIdAutoreEppo());
		setIdLingua(genere.getIdLingua());
		setIdNazione(genere.getIdNazione());
		setPreferred(genere.isPreferred());
		setIdLinguaLocale(genere.getIdLinguaLocale());
	}

}
