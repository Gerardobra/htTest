package it.aizoon.ersaf.dto;

import it.aizoon.ersaf.dto.generati.CarDTipoComunicazione;

@SuppressWarnings("serial")
public class TipoDomandaDTO extends CarDTipoComunicazione {

  private int numTipiSuperficie;
  private int numTipiDichiarazione;
  private int numTipiChecklist;
  private int numTipiAllegati;
  private int numTipiModuli;
  
  public int getNumTipiSuperficie() {
    return numTipiSuperficie;
  }
  public void setNumTipiSuperficie(int numTipiSuperficie) {
    this.numTipiSuperficie = numTipiSuperficie;
  }
  public int getNumTipiDichiarazione() {
    return numTipiDichiarazione;
  }
  public void setNumTipiDichiarazione(int numTipiDichiarazione) {
    this.numTipiDichiarazione = numTipiDichiarazione;
  }
  public int getNumTipiAllegati() {
    return numTipiAllegati;
  }
  public void setNumTipiAllegati(int numTipiAllegati) {
    this.numTipiAllegati = numTipiAllegati;
  }
  public int getNumTipiModuli() {
    return numTipiModuli;
  }
  public void setNumTipiModuli(int numTipiModuli) {
    this.numTipiModuli = numTipiModuli;
  }
  public int getNumTipiChecklist() {
    return numTipiChecklist;
  }
  public void setNumTipiChecklist(int numTipiChecklist) {
    this.numTipiChecklist = numTipiChecklist;
  }
  
}
