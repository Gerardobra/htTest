package it.aizoon.ersaf.dto;

import java.math.BigDecimal;

import it.aizoon.ersaf.dto.generati.CarRMerceRichiesta;

@SuppressWarnings("serial")
public class MerceRichiestaDto extends CarRMerceRichiesta {

  private String descNazioneOrigine;
  private String codNazioneOrigine;
  private String descTipoProdotto;
  private String descGenere;
  private String descSpecie;
  private Long idUnitaMisura;
  private String descUnitaMisura;
  private String codUnitaMisura;
  private BigDecimal tariffaTeorica;
  // campi aggiunti per export
  private String descNaturaCollo;
  private String descProdotto;
  private String idClasseProdotto;
  private String descClasseProdotto;

  public String getDescNazioneOrigine() {
    return descNazioneOrigine;
  }

  public void setDescNazioneOrigine(String descNazioneOrigine) {
    this.descNazioneOrigine = descNazioneOrigine;
  }

  public String getDescTipoProdotto() {
    return descTipoProdotto;
  }

  public void setDescTipoProdotto(String descTipoProdotto) {
    this.descTipoProdotto = descTipoProdotto;
  }

  public String getDescGenere() {
    return descGenere;
  }

  public void setDescGenere(String descGenere) {
    this.descGenere = descGenere;
  }

  public String getDescSpecie() {
    return descSpecie;
  }

  public void setDescSpecie(String descSpecie) {
    this.descSpecie = descSpecie;
  }

  public Long getIdUnitaMisura() {
    return idUnitaMisura;
  }

  public void setIdUnitaMisura(Long idUnitaMisura) {
    this.idUnitaMisura = idUnitaMisura;
  }

  public String getDescUnitaMisura() {
    return descUnitaMisura;
  }

  public void setDescUnitaMisura(String descUnitaMisura) {
    this.descUnitaMisura = descUnitaMisura;
  }

  public String getCodUnitaMisura() {
    return codUnitaMisura;
  }

  public void setCodUnitaMisura(String codUnitaMisura) {
    this.codUnitaMisura = codUnitaMisura;
  }

  public BigDecimal getTariffaTeorica() {
    return tariffaTeorica;
  }

  public void setTariffaTeorica(BigDecimal tariffaTeorica) {
    this.tariffaTeorica = tariffaTeorica;
  }

  public String getDescNaturaCollo() {
    return descNaturaCollo;
  }

  public void setDescNaturaCollo(String descNaturaCollo) {
    this.descNaturaCollo = descNaturaCollo;
  }

  public String getDescProdotto() {
    return descProdotto;
  }

  public void setDescProdotto(String descProdotto) {
    this.descProdotto = descProdotto;
  }

  public String getIdClasseProdotto() {
    return idClasseProdotto;
  }

  public void setIdClasseProdotto(String idClasseProdotto) {
    this.idClasseProdotto = idClasseProdotto;
  }

  public String getDescClasseProdotto() {
    return descClasseProdotto;
  }

  public void setDescClasseProdotto(String descClasseProdotto) {
    this.descClasseProdotto = descClasseProdotto;
  }

  public String getCodNazioneOrigine() {
    return codNazioneOrigine;
  }

  public void setCodNazioneOrigine(String codNazioneOrigine) {
    this.codNazioneOrigine = codNazioneOrigine;
  }

}
