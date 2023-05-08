package it.aizoon.ersaf.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.util.CaronteUtils;

public class NuovaSpecieForm extends BaseForm {

	@NumberFormat(style= Style.NUMBER)
	public Long idNazione;

	@NumberFormat(style = Style.NUMBER)	
	private Long idSpecie;
	
	@NotNull(message="Campo obbligatorio")
	@Size(min=1, max=10)
	private String codSpecie;
	
	@NotNull(message="Campo obbligatorio")
	@Size(min=1, max=50)
	private String denomSpecie;
	
	private String denomSpecieCommerciale;
	
	@NumberFormat(style = Style.NUMBER)	
	private Long idAutoreEppo;
	
	private String descAutoreEppo;
	
	@NotNull(message="Campo obbligatorio")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataInsert;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataUpdate;
	
	private boolean attivo = true;
	
	private boolean preferred = true;
	
	private String denomNazione;
	
	@NumberFormat(style = Style.NUMBER)
	private Long idLingua;
	
	private String denomSpecieLocale;
	
	private Long idLinguaLocale;
	
	@NotNull(message="Campo obbligatorio")
	private Long idGenere;
	
	@NotNull(message="Campo obbligatorio")
  @Size(min=1, max=200)
	private String denomGenere;

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
		this.denomNazione = denomNazione;
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

	public Long getIdLinguaLocale() {
		return idLinguaLocale;
	}

	public void setIdLinguaLocale(Long idLinguaLocale) {
		this.idLinguaLocale = idLinguaLocale;
	}

	public Long getIdGenere() {
		return idGenere;
	}

	public void setIdGenere(Long idGenere) {
		this.idGenere = idGenere;
	}

	public String getDenomGenere() {
		return denomGenere;
	}

	public void setDenomGenere(String denomGenere) {
		this.denomGenere = CaronteUtils.convertToUpperCase(denomGenere);
	}

	public void setDatiFromDTO(SpecieDTO specie) {
		setIdSpecie(specie.getIdSpecie());
		setAttivo(specie.isAttivo());
		setCodSpecie(specie.getCodSpecie());
		setDataInsert(specie.getDataInsert());
		setDataUpdate(specie.getDataUpdate());
		setDenomSpecie(specie.getDenomSpecie());
		setDenomSpecieCommerciale(specie.getDenomSpecieCommerciale());
		setDenomSpecieLocale(specie.getDenomSpecieLocale());
		setDenomNazione(specie.getDenomNazione());
		setDescAutoreEppo(specie.getDescAutoreEppo());
		setIdAutoreEppo(specie.getIdAutoreEppo());
		setIdLingua(specie.getIdLingua());
		setIdNazione(specie.getIdNazione());
		setPreferred(specie.isPreferred());
		setIdLinguaLocale(specie.getIdLinguaLocale());
		setIdGenere(specie.getIdGenere());
		setDenomGenere(specie.getDenomGenere());
	}

}
