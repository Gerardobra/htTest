package it.aizoon.ersaf.dto;

import org.springframework.util.StringUtils;

import it.aizoon.ersaf.dto.generati.CarTCentroAziendale;

/**
 * @author Ivan Morra
 */
@SuppressWarnings("serial")
public class CentroAziendaleDto extends CarTCentroAziendale {

  private String denomComune;
  private String siglaProvincia;
  private String descrStatoAzienda;
  private String cap;
  private Boolean flEliminabile;

  public String getDenomComune() {
    return denomComune;
  }

  public void setDenomComune(String denomComune) {
    this.denomComune = denomComune;
  }

  public String getSiglaProvincia() {
    return siglaProvincia;
  }

  public void setSiglaProvincia(String siglaProvincia) {
    this.siglaProvincia = siglaProvincia;
  }

  public String getNomeIndirizzo() {
    StringBuilder result = new StringBuilder(getCodCentroAziendale());

    if (!StringUtils.isEmpty(getIndirizzo())) {
      result.append(" - ").append(getIndirizzo());
    }

    if (!StringUtils.isEmpty(getFrazione())) {
      result.append(" ").append(getFrazione());
    }

    if (!StringUtils.isEmpty(getDenomComune())) {
      result.append(" ").append(getDenomComune()).append(" (").append(getSiglaProvincia()).append(")");
    }

    return result.toString();
  }
  
  public String getNomeElenco(){
	  
	  if (this.getDenominazione() != null && this.getCodCentroAziendale() != null){
		  return this.getCodCentroAziendale() + " - " + this.getDenominazione() ; 
	  }
	  if (this.getDenominazione() == null && this.getCodCentroAziendale() != null) {
		 return this.getCodCentroAziendale();
	  }
	  if (this.getDenominazione() != null && this.getCodCentroAziendale() == null) {
			 return this.getDenominazione();
		  }
	  return null;
  }

public String getDescrStatoAzienda() {
	return descrStatoAzienda;
}

public void setDescrStatoAzienda(String descrStatoAzienda) {
	this.descrStatoAzienda = descrStatoAzienda;
}

/**
 * @return the flEliminabile
 */
public Boolean getFlEliminabile() {
	return flEliminabile;
}

/**
 * @param flEliminabile the flEliminabile to set
 */
public void setFlEliminabile(Boolean flEliminabile) {
	this.flEliminabile = flEliminabile;
}

public String getCap() {
	return cap;
}

public void setCap(String cap) {
	this.cap = cap;
}




}
