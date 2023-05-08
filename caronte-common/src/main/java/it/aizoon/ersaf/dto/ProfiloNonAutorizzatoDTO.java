package it.aizoon.ersaf.dto;

import java.util.Date;

public class ProfiloNonAutorizzatoDTO extends UtenteDTO {

  private static final long serialVersionUID = -4696129013380951948L;

  private String denominazione;
  private Long idUtente;
  private Long idSpedizioniere;
  private Long idIspettore;
  private Date dataInsert;

  public String getDenominazione() {
    return denominazione;
  }

  public void setDenominazione(String denominazione) {
    this.denominazione = denominazione;
  }

  public Long getIdUtente() {
    return idUtente;
  }

  public void setIdUtente(Long idUtente) {
    this.idUtente = idUtente;
  }

  public Long getIdSpedizioniere() {
    return idSpedizioniere;
  }

  public void setIdSpedizioniere(Long idSpedizioniere) {
    this.idSpedizioniere = idSpedizioniere;
  }

  public Long getIdIspettore() {
    return idIspettore;
  }

  public void setIdIspettore(Long idIspettore) {
    this.idIspettore = idIspettore;
  }

  public Date getDataInsert() {
    return dataInsert;
  }

  public void setDataInsert(Date dataInsert) {
    this.dataInsert = dataInsert;
  }

}