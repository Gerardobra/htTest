package it.aizoon.ersaf.dto;

import it.aizoon.ersaf.dto.generati.CarTCertificato;

@SuppressWarnings("serial")
public class CertificatoRichiestaDto extends CarTCertificato {
	
  private String descTipoCertificato;
	private String luogoEsecuzione;
	private String comuneEsecuzione;
	private Long idProvinciaEsecuzione;
	private String provinciaEsecuzione;
	private String siglaProvinciaEsecuzione;
	private String nomeIspettore;
	private String cognomeIspettore;
	private String numeroTesseraIspettore;
	private Long idIspettoreDocumentale;
	private String nomeIspettoreDocumentale;
  private String cognomeIspettoreDocumentale;
  private String numeroTesseraIspettoreDocumentale;
  private Long idIspettoreIdentita;
  private String nomeIspettoreIdentita;
  private String cognomeIspettoreIdentita;
  private String numeroTesseraIspettoreIdentita;
  private Long idIspettoreFitosanitario;
  private String nomeIspettoreFitosanitario;
  private String cognomeIspettoreFitosanitario;
  private String numeroTesseraIspettoreFitosanitario;
	private String codiceEnte;
	private String descTipoImballaggio;
	private String descCopiaConforme;
	
	public String getDescTipoCertificato() {
    return descTipoCertificato;
  }
  public void setDescTipoCertificato(String descTipoCertificato) {
    this.descTipoCertificato = descTipoCertificato;
  }
  public String getLuogoEsecuzione() {
		return luogoEsecuzione;
	}
	public void setLuogoEsecuzione(String luogoEsecuzione) {
		this.luogoEsecuzione = luogoEsecuzione;
	}
	public String getComuneEsecuzione() {
    return comuneEsecuzione;
  }
  public void setComuneEsecuzione(String comuneEsecuzione) {
    this.comuneEsecuzione = comuneEsecuzione;
  }
  public Long getIdProvinciaEsecuzione() {
    return idProvinciaEsecuzione;
  }
  public void setIdProvinciaEsecuzione(Long idProvinciaEsecuzione) {
    this.idProvinciaEsecuzione = idProvinciaEsecuzione;
  }
  public String getProvinciaEsecuzione() {
    return provinciaEsecuzione;
  }
  public void setProvinciaEsecuzione(String provinciaEsecuzione) {
    this.provinciaEsecuzione = provinciaEsecuzione;
  }
  public String getSiglaProvinciaEsecuzione() {
    return siglaProvinciaEsecuzione;
  }
  public void setSiglaProvinciaEsecuzione(String siglaProvinciaEsecuzione) {
    this.siglaProvinciaEsecuzione = siglaProvinciaEsecuzione;
  }
  public String getNomeIspettore() {
    return nomeIspettore;
  }
  public void setNomeIspettore(String nomeIspettore) {
    this.nomeIspettore = nomeIspettore;
  }
  public String getCognomeIspettore() {
    return cognomeIspettore;
  }
  public void setCognomeIspettore(String cognomeIspettore) {
    this.cognomeIspettore = cognomeIspettore;
  }
  public String getNumeroTesseraIspettore() {
    return numeroTesseraIspettore;
  }
  public void setNumeroTesseraIspettore(String numeroTesseraIspettore) {
    this.numeroTesseraIspettore = numeroTesseraIspettore;
  }
  public Long getIdIspettoreDocumentale() {
    return idIspettoreDocumentale;
  }
  public void setIdIspettoreDocumentale(Long idIspettoreDocumentale) {
    this.idIspettoreDocumentale = idIspettoreDocumentale;
  }
  public String getNomeIspettoreDocumentale() {
    return nomeIspettoreDocumentale;
  }
  public void setNomeIspettoreDocumentale(String nomeIspettoreDocumentale) {
    this.nomeIspettoreDocumentale = nomeIspettoreDocumentale;
  }
  public String getCognomeIspettoreDocumentale() {
    return cognomeIspettoreDocumentale;
  }
  public void setCognomeIspettoreDocumentale(String cognomeIspettoreDocumentale) {
    this.cognomeIspettoreDocumentale = cognomeIspettoreDocumentale;
  }
  public String getNumeroTesseraIspettoreDocumentale() {
    return numeroTesseraIspettoreDocumentale;
  }
  public void setNumeroTesseraIspettoreDocumentale(String numeroTesseraIspettoreDocumentale) {
    this.numeroTesseraIspettoreDocumentale = numeroTesseraIspettoreDocumentale;
  }
  public Long getIdIspettoreIdentita() {
    return idIspettoreIdentita;
  }
  public void setIdIspettoreIdentita(Long idIspettoreIdentita) {
    this.idIspettoreIdentita = idIspettoreIdentita;
  }
  public String getNomeIspettoreIdentita() {
    return nomeIspettoreIdentita;
  }
  public void setNomeIspettoreIdentita(String nomeIspettoreIdentita) {
    this.nomeIspettoreIdentita = nomeIspettoreIdentita;
  }
  public String getCognomeIspettoreIdentita() {
    return cognomeIspettoreIdentita;
  }
  public void setCognomeIspettoreIdentita(String cognomeIspettoreIdentita) {
    this.cognomeIspettoreIdentita = cognomeIspettoreIdentita;
  }
  public String getNumeroTesseraIspettoreIdentita() {
    return numeroTesseraIspettoreIdentita;
  }
  public void setNumeroTesseraIspettoreIdentita(String numeroTesseraIspettoreIdentita) {
    this.numeroTesseraIspettoreIdentita = numeroTesseraIspettoreIdentita;
  }
  public Long getIdIspettoreFitosanitario() {
    return idIspettoreFitosanitario;
  }
  public void setIdIspettoreFitosanitario(Long idIspettoreFitosanitario) {
    this.idIspettoreFitosanitario = idIspettoreFitosanitario;
  }
  public String getNomeIspettoreFitosanitario() {
    return nomeIspettoreFitosanitario;
  }
  public void setNomeIspettoreFitosanitario(String nomeIspettoreFitosanitario) {
    this.nomeIspettoreFitosanitario = nomeIspettoreFitosanitario;
  }
  public String getCognomeIspettoreFitosanitario() {
    return cognomeIspettoreFitosanitario;
  }
  public void setCognomeIspettoreFitosanitario(String cognomeIspettoreFitosanitario) {
    this.cognomeIspettoreFitosanitario = cognomeIspettoreFitosanitario;
  }
  public String getNumeroTesseraIspettoreFitosanitario() {
    return numeroTesseraIspettoreFitosanitario;
  }
  public void setNumeroTesseraIspettoreFitosanitario(String numeroTesseraIspettoreFitosanitario) {
    this.numeroTesseraIspettoreFitosanitario = numeroTesseraIspettoreFitosanitario;
  }
  public String getCodiceEnte() {
		return codiceEnte;
	}
	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}
	
	public String getDenominazioneIspettore() {
	  return getNumeroTesseraIspettore() + " (" + getNomeIspettore() + " " + getCognomeIspettore() + ")";
	}
	
	public String getDenominazioneIspettoreDocumentale() {
    return getNumeroTesseraIspettoreDocumentale() + " (" + getNomeIspettoreDocumentale() + " " + getCognomeIspettoreDocumentale() + ")";
  }
	
	public String getDenominazioneIspettoreIdentita() {
    return getNumeroTesseraIspettoreIdentita() + " (" + getNomeIspettoreIdentita() + " " + getCognomeIspettoreIdentita() + ")";
  }
	
	public String getDenominazioneIspettoreFitosanitario() {
    return getNumeroTesseraIspettoreFitosanitario() + " (" + getNomeIspettoreFitosanitario() + " " + getCognomeIspettoreFitosanitario() + ")";
  }
  public String getDescTipoImballaggio() {
    return descTipoImballaggio;
  }
  public void setDescTipoImballaggio(String descTipoImballaggio) {
    this.descTipoImballaggio = descTipoImballaggio;
  }
  public String getDescCopiaConforme() {
    return descCopiaConforme;
  }
  public void setDescCopiaConforme(String descCopiaConforme) {
    this.descCopiaConforme = descCopiaConforme;
  }
	
}
