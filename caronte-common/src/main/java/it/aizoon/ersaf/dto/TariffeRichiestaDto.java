package it.aizoon.ersaf.dto;

import java.math.BigDecimal;

/**
 * @author Ivan Morra
 */
@SuppressWarnings("serial")
public class TariffeRichiestaDto extends BaseDto {

  private BigDecimal totaleDocumentale;
  private BigDecimal totaleIdentita;
  private BigDecimal totaleFitosanitario;
  private BigDecimal massimaleTariffa;
  private Long numeroCertificati;
  private BigDecimal totaleTariffe;
  
  public BigDecimal getTotaleDocumentale() {
    return totaleDocumentale;
  }
  public void setTotaleDocumentale(BigDecimal totaleDocumentale) {
    this.totaleDocumentale = totaleDocumentale;
  }
  public BigDecimal getTotaleIdentita() {
    return totaleIdentita;
  }
  public void setTotaleIdentita(BigDecimal totaleIdentita) {
    this.totaleIdentita = totaleIdentita;
  }
  public BigDecimal getTotaleFitosanitario() {
    return totaleFitosanitario;
  }
  public void setTotaleFitosanitario(BigDecimal totaleFitosanitario) {
    this.totaleFitosanitario = totaleFitosanitario;
  }
  public BigDecimal getMassimaleTariffa() {
    return massimaleTariffa;
  }
  public void setMassimaleTariffa(BigDecimal massimaleTariffa) {
    this.massimaleTariffa = massimaleTariffa;
  }
  public Long getNumeroCertificati() {
    return numeroCertificati;
  }
  public void setNumeroCertificati(Long numeroCertificati) {
    this.numeroCertificati = numeroCertificati;
  }
  public BigDecimal getTotaleTariffe() {
    return totaleTariffe;
  }
  public void setTotaleTariffe(BigDecimal totaleTariffe) {
    this.totaleTariffe = totaleTariffe;
  }
  
}
