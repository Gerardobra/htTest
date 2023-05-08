package it.aizoon.ersaf.dto;

import it.aizoon.ersaf.dto.generati.CarTRichiesta;

@SuppressWarnings("serial")
public class DettaglioRichiestaDto extends CarTRichiesta {

  private String utenteNome;
  private String utenteCognome;
  private String utenteEmail;
  private String descStatoRichiesta;
  private String denomNazioneMittente;
  private String denomComuneIta;
  private String denomNazioneDestinatario;
  private String denomNazioneRupDestinatario;
  private String descNazioneProtVegDestinat;
  private String dogana;
  private String descUfficioDoganaleUtilizzato;
  private String descModoTrasporto;
  private String descModoTrasportoExp;
  private String descTipoCertificato;
  private Long idSpedizioniere;
  private String denomSpedizioniere;
  private String numeroCertificato;
  private Long idTipoSpedizioniere;
  private String denomTipoSpedizioniere;
  private String descMezzoTrasporto;
  private Long idProvincia;
  private String denomProvincia;
  private String descRegioneRupDestinatario;
  private int numeroMerci;
  private Long idPagamento;
  private String descTipoRichiesta;
  private boolean autorizPagamPosticip;

  public String getUtenteNome() {
    return utenteNome;
  }

  public void setUtenteNome(String utenteNome) {
    this.utenteNome = utenteNome;
  }

  public String getUtenteCognome() {
    return utenteCognome;
  }

  public void setUtenteCognome(String utenteCognome) {
    this.utenteCognome = utenteCognome;
  }

  public String getUtenteEmail() {
    return utenteEmail;
  }

  public void setUtenteEmail(String utenteEmail) {
    this.utenteEmail = utenteEmail;
  }

  public String getDescStatoRichiesta() {
    return descStatoRichiesta;
  }

  public void setDescStatoRichiesta(String descStatoRichiesta) {
    this.descStatoRichiesta = descStatoRichiesta;
  }

  public String getDenomNazioneMittente() {
    return denomNazioneMittente;
  }

  public void setDenomNazioneMittente(String denomNazioneMittente) {
    this.denomNazioneMittente = denomNazioneMittente;
  }

  public String getDenomComuneIta() {
    return denomComuneIta;
  }

  public void setDenomComuneIta(String denomComuneIta) {
    this.denomComuneIta = denomComuneIta;
  }

  public String getDenomNazioneDestinatario() {
    return denomNazioneDestinatario;
  }

  public void setDenomNazioneDestinatario(String denomNazioneDestinatario) {
    this.denomNazioneDestinatario = denomNazioneDestinatario;
  }

  public String getDenomNazioneRupDestinatario() {
    return denomNazioneRupDestinatario;
  }

  public void setDenomNazioneRupDestinatario(String denomNazioneRupDestinatario) {
    this.denomNazioneRupDestinatario = denomNazioneRupDestinatario;
  }

  public String getDescNazioneProtVegDestinat() {
    return descNazioneProtVegDestinat;
  }

  public void setDescNazioneProtVegDestinat(String descNazioneProtVegDestinat) {
    this.descNazioneProtVegDestinat = descNazioneProtVegDestinat;
  }

  public String getDogana() {
    return dogana;
  }

  public void setDogana(String dogana) {
    this.dogana = dogana;
  }

  public String getDescUfficioDoganaleUtilizzato() {
    return descUfficioDoganaleUtilizzato;
  }

  public void setDescUfficioDoganaleUtilizzato(String descUfficioDoganaleUtilizzato) {
    this.descUfficioDoganaleUtilizzato = descUfficioDoganaleUtilizzato;
  }

  public String getDescModoTrasporto() {
    return descModoTrasporto;
  }

  public void setDescModoTrasporto(String descModoTrasporto) {
    this.descModoTrasporto = descModoTrasporto;
  }

  public String getDescModoTrasportoExp() {
    return descModoTrasportoExp;
  }

  public void setDescModoTrasportoExp(String descModoTrasportoExp) {
    this.descModoTrasportoExp = descModoTrasportoExp;
  }

  public String getDescTipoCertificato() {
    return descTipoCertificato;
  }

  public void setDescTipoCertificato(String descTipoCertificato) {
    this.descTipoCertificato = descTipoCertificato;
  }

  public Long getIdSpedizioniere() {
    return idSpedizioniere;
  }

  public void setIdSpedizioniere(Long idSpedizioniere) {
    this.idSpedizioniere = idSpedizioniere;
  }

  public String getDenomSpedizioniere() {
    return denomSpedizioniere;
  }

  public void setDenomSpedizioniere(String denomSpedizioniere) {
    this.denomSpedizioniere = denomSpedizioniere;
  }

  public String getNumeroCertificato() {
    return numeroCertificato;
  }

  public void setNumeroCertificato(String numeroCertificato) {
    this.numeroCertificato = numeroCertificato;
  }

  public Long getIdTipoSpedizioniere() {
    return idTipoSpedizioniere;
  }

  public void setIdTipoSpedizioniere(Long idTipoSpedizioniere) {
    this.idTipoSpedizioniere = idTipoSpedizioniere;
  }

  public String getDenomTipoSpedizioniere() {
    return denomTipoSpedizioniere;
  }

  public void setDenomTipoSpedizioniere(String denomTipoSpedizioniere) {
    this.denomTipoSpedizioniere = denomTipoSpedizioniere;
  }

  public String getDescMezzoTrasporto() {
  	return descMezzoTrasporto;
  }
  
  public void setDescMezzoTrasporto(String descMezzoTrasporto) {
  	this.descMezzoTrasporto = descMezzoTrasporto;
  }
  
  public Long getIdProvincia() {
    return idProvincia;
  }

  public void setIdProvincia(Long idProvincia) {
    this.idProvincia = idProvincia;
  }

  public String getDenomProvincia() {
  	return denomProvincia;
  }
  
  public void setDenomProvincia(String denomProvincia) {
  	this.denomProvincia = denomProvincia;
  }

  public String getDescRegioneRupDestinatario() {
    return descRegioneRupDestinatario;
  }

  public void setDescRegioneRupDestinatario(String descRegioneRupDestinatario) {
    this.descRegioneRupDestinatario = descRegioneRupDestinatario;
  }

  public int getNumeroMerci() {
    return numeroMerci;
  }

  public void setNumeroMerci(int numeroMerci) {
    this.numeroMerci = numeroMerci;
  }

  public Long getIdPagamento() {
    return idPagamento;
  }

  public void setIdPagamento(Long idPagamento) {
    this.idPagamento = idPagamento;
  }

  public String getDescTipoRichiesta() {
    return descTipoRichiesta;
  }

  public void setDescTipoRichiesta(String descTipoRichiesta) {
    this.descTipoRichiesta = descTipoRichiesta;
  }

  public boolean isAutorizPagamPosticip() {
    return autorizPagamPosticip;
  }

  public void setAutorizPagamPosticip(boolean autorizPagamPosticip) {
    this.autorizPagamPosticip = autorizPagamPosticip;
  }

}