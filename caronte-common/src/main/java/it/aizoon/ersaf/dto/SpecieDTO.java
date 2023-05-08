package it.aizoon.ersaf.dto;

import java.util.Date;

public class SpecieDTO extends BaseDto {
	
	private static final long serialVersionUID = 1L;
	private Long idSpecie;
	private String codSpecie;
	private String denomSpecie;
	private String denomSpecieCommerciale;
	private String denomSpecieLocale;
	private Long idAutoreEppo;
	private String descAutoreEppo;
	private Long idNazione;
	private Long idUtenteInsert;
	private Date dataInsert;
	private Date dataUpdate;
	private boolean attivo;
	private boolean preferred;
	private String denomNazione;
	private String descLingua;
	private Long idLingua;
	private Long idLinguaLocale;
	private Long idGenere;
	private String denomGenere;
	private Long numeroPiante;
	
	public Long getNumeroPiante() {
		return numeroPiante;
	}
	public void setNumeroPiante(Long numeroPiante) {
		this.numeroPiante = numeroPiante;
	}
	public Long getIdSpecie() {
		return idSpecie;
	}
	public void setIdSpecie(Long idSpecie) {
		this.idSpecie = idSpecie;
	}
	public String getCodSpecie() {
		return codSpecie;
	}
	public void setCodSpecie(String codSpecie) {
		this.codSpecie = codSpecie;
	}
	public String getDenomSpecie() {
		return denomSpecie;
	}
	public void setDenomSpecie(String denomSpecie) {
		this.denomSpecie = denomSpecie;
	}
	public Long getIdAutoreEppo() {
		return idAutoreEppo;
	}
	public void setIdAutoreEppo(Long idAutoreEppo) {
		this.idAutoreEppo = idAutoreEppo;
	}
	public Long getIdNazione() {
		return idNazione;
	}
	public void setIdNazione(Long idNazione) {
		this.idNazione = idNazione;
	}
	public Long getIdUtenteInsert() {
    return idUtenteInsert;
  }
  public void setIdUtenteInsert(Long idUtenteInsert) {
    this.idUtenteInsert = idUtenteInsert;
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
	public String getDenomSpecieCommerciale() {
		return denomSpecieCommerciale;
	}
	public void setDenomSpecieCommerciale(String denomSpecieCommerciale) {
		this.denomSpecieCommerciale = denomSpecieCommerciale;
	}
	public String getDescAutoreEppo() {
		return descAutoreEppo;
	}
	public void setDescAutoreEppo(String descAutoreEppo) {
		this.descAutoreEppo = descAutoreEppo;
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
	public String getDescLingua() {
		return descLingua;
	}
	public void setDescLingua(String descLingua) {
		this.descLingua = descLingua;
	}
	public String getDenomSpecieLocale() {
		return denomSpecieLocale;
	}
	public void setDenomSpecieLocale(String denomSpecieLocale) {
		this.denomSpecieLocale = denomSpecieLocale;
	}
	public Long getIdLingua() {
		return idLingua;
	}
	public void setIdLingua(Long idLingua) {
		this.idLingua = idLingua;
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
		this.denomGenere = denomGenere;
	}
}
