package it.aizoon.ersaf.dto;

import it.aizoon.ersaf.dto.internal.ILoggable;

public class DecodificaDTO<T> implements ILoggable {

  private static final long serialVersionUID = 1L;
  private T id;
  private String infoAggiuntiva;
  private String descrizione;
  private String codice;

  public DecodificaDTO() {}

  public DecodificaDTO(T id) {
    this.id = id;
  }

  public DecodificaDTO(T id, String descrizione) {
    this.id = id;
    this.descrizione = descrizione;
  }

  public DecodificaDTO(T id, String codice, String descrizione) {
    this.id = id;
    this.descrizione = descrizione;
    this.codice = codice;
  }

  public T getId() {
    return id;
  }

  public void setId(T id) {
    this.id = id;
  }

  public String getInfoAggiuntiva() {
    return infoAggiuntiva;
  }

  public void setInfoAggiuntiva(String infoAggiuntiva) {
    this.infoAggiuntiva = infoAggiuntiva;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public String getCodice() {
    return codice;
  }

  public void setCodice(String codice) {
    this.codice = codice;
  }

  public String getDescrizioneEstesa() {
    return descrizione + " - " + infoAggiuntiva;
  }

}
