package it.aizoon.ersaf.dto;

import java.math.BigDecimal;
import java.util.List;

import it.aizoon.ersaf.dto.generati.CarDRangeTariffa;
import it.aizoon.ersaf.dto.generati.CarDTariffa;

/**
 * @author Ivan Morra
 */
@SuppressWarnings("serial")
public class TariffaDto extends CarDTariffa {

  private String descTipoProdotto;
  private Long idUnitaMisura;
  private String descUnitaMisura;
  private BigDecimal tariffa = BigDecimal.ZERO;
  private Long quantitaLimite = 0L;
  private List<CarDRangeTariffa> listaRange;
  
  public String getDescTipoProdotto() {
    return descTipoProdotto;
  }

  public void setDescTipoProdotto(String descTipoProdotto) {
    this.descTipoProdotto = descTipoProdotto;
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

  public BigDecimal getTariffa() {
    return tariffa;
  }

  public void setTariffa(BigDecimal tariffa) {
    this.tariffa = tariffa;
  }

  public Long getQuantitaLimite() {
    return quantitaLimite;
  }

  public void setQuantitaLimite(Long quantitaLimite) {
    this.quantitaLimite = quantitaLimite;
  }

  public List<CarDRangeTariffa> getListaRange() {
    return listaRange;
  }

  public void setListaRange(List<CarDRangeTariffa> listaRange) {
    this.listaRange = listaRange;
  }
  
}
