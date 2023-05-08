package it.aizoon.ersaf.form;


import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class DatiSitoProduzioneForm extends BaseForm {

  private Long idSitoProduzione;
  private Long idDomanda;
  
  private String descSitoProduzione;
  private String ubicazione;
  @NumberFormat(style = Style.NUMBER)
  private Long provinciaSitoProd;
  @NumberFormat(style = Style.NUMBER)
  private Long comuneSitoProd;
  @NumberFormat(style = Style.NUMBER)
  private String foglio;
  @NumberFormat(style = Style.NUMBER)
  private String mappale;
  @NumberFormat(style = Style.NUMBER)
  private String superficie;
  
  private String[] idComSitoProduzione;
  private String[] idProvSitoProduzione;
  
public Long getIdSitoProduzione() {
	return idSitoProduzione;
}
public void setIdSitoProduzione(Long idSitoProduzione) {
	this.idSitoProduzione = idSitoProduzione;
}
public Long getIdDomanda() {
	return idDomanda;
}
public void setIdDomanda(Long idDomanda) {
	this.idDomanda = idDomanda;
}
public String getDescSitoProduzione() {
	return descSitoProduzione;
}
public void setDescSitoProduzione(String descSitoProduzione) {
	this.descSitoProduzione = descSitoProduzione;
}
public String getUbicazione() {
	return ubicazione;
}
public void setUbicazione(String ubicazione) {
	this.ubicazione = ubicazione;
}
public String getFoglio() {
	return foglio;
}
public void setFoglio(String foglio) {
	this.foglio = foglio;
}
public String getMappale() {
	return mappale;
}
public void setMappale(String mappale) {
	this.mappale = mappale;
}
public String getSuperficie() {
	return superficie;
}
public void setSuperficie(String superficie) {
	this.superficie = superficie;
}
public Long getProvinciaSitoProd() {
	return provinciaSitoProd;
}
public void setProvinciaSitoProd(Long provinciaSitoProd) {
	this.provinciaSitoProd = provinciaSitoProd;
}
public Long getComuneSitoProd() {
	return comuneSitoProd;
}
public void setComuneSitoProd(Long comuneSitoProd) {
	this.comuneSitoProd = comuneSitoProd;
}
public String[] getIdComSitoProduzione() {
	return idComSitoProduzione;
}
public void setIdComSitoProduzione(String[] idComSitoProduzione) {
	this.idComSitoProduzione = idComSitoProduzione;
}
public String[] getIdProvSitoProduzione() {
	return idProvSitoProduzione;
}
public void setIdProvSitoProduzione(String[] idProvSitoProduzione) {
	this.idProvSitoProduzione = idProvSitoProduzione;
}
  
  }
