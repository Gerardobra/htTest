package it.aizoon.ersaf.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.util.CaronteUtils;


public class RicercaDomandaForm extends BaseForm {

  private String azienda;
  private String nomeOperatore;
  private String cognomeOperatore;
  private String codiceDomanda;
  
  @NumberFormat(style = Style.NUMBER)
  private Long idTipoComunicazione;
  
  @NumberFormat(style = Style.NUMBER)
  private Long idStatoComunicazione; 
  
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date dataCreazione;
  
  @NumberFormat(style = Style.NUMBER)
  private Long idUtenteInsert;
  
  private Long idAssociazioneSezione;
  
  private Long idUtenteLoggato; 
  
 
  public Long getIdTipoComunicazione() {
    return idTipoComunicazione;
  }
  public void setIdTipoComunicazione(Long idTipoComunicazione) {
    this.idTipoComunicazione = idTipoComunicazione;
  }
  public Long getIdStatoComunicazione() {
    return idStatoComunicazione;
  }
  public void setIdStatoComunicazione(Long idStatoComunicazione) {
    this.idStatoComunicazione = idStatoComunicazione;
  }
  public Date getDataCreazione() {
    return dataCreazione;
  }
  public void setDataCreazione(Date dataCreazione) {
    this.dataCreazione = dataCreazione;
  }
  public Long getIdUtenteInsert() {
    return idUtenteInsert;
  }
  public void setIdUtenteInsert(Long idUtenteInsert) {
    this.idUtenteInsert = idUtenteInsert;
  }
public String getAzienda() {
	return azienda;
}
public void setAzienda(String azienda) {
	this.azienda = CaronteUtils.convertToUpperCase(azienda);
}
public String getCodiceDomanda() {
	return codiceDomanda;
}
public void setCodiceDomanda(String codiceDomanda) {
	this.codiceDomanda = CaronteUtils.convertToUpperCase(codiceDomanda);
}
public String getNomeOperatore() {
	return nomeOperatore;
}
public void setNomeOperatore(String nomeOperatore) {
	this.nomeOperatore = CaronteUtils.convertToUpperCase(nomeOperatore);
}
public String getCognomeOperatore() {
	return cognomeOperatore;
}
public void setCognomeOperatore(String cognomeOperatore) {
	this.cognomeOperatore = CaronteUtils.convertToUpperCase(cognomeOperatore);
}
public Long getIdAssociazioneSezione() {
	return idAssociazioneSezione;
}
public void setIdAssociazioneSezione(Long idAssociazioneSezione) {
	this.idAssociazioneSezione = idAssociazioneSezione;
}
public Long getIdUtenteLoggato() {
	return idUtenteLoggato;
}
public void setIdUtenteLoggato(Long idUtenteLoggato) {
	this.idUtenteLoggato = idUtenteLoggato;
}
  
}
