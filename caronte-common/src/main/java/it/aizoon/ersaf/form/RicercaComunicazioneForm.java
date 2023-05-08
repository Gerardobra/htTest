package it.aizoon.ersaf.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.util.CaronteUtils;

/**
 * @author Ivan Morra
 *
 */
public class RicercaComunicazioneForm extends BaseForm {

  private String spedizioniere;
  @NumberFormat(style = Style.NUMBER)
  private Long idTipoComunicazione;
  @NumberFormat(style = Style.NUMBER)
  private Long idStatoComunicazione; 
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date dataCreazione;
  @NumberFormat(style = Style.NUMBER)
  private Long idUtenteInsert;
  
  public String getSpedizioniere() {
    return spedizioniere;
  }
  public void setSpedizioniere(String spedizioniere) {
    this.spedizioniere = CaronteUtils.convertToUpperCase(spedizioniere);
  }
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
  
}
