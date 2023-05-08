package it.aizoon.ersaf.dto;

import java.util.List;

import it.aizoon.ersaf.dto.generati.CarTComunicazione;

/**
 * @author Ivan Morra
 *
 */

@SuppressWarnings("serial")
public class ComunicazioneDto extends CarTComunicazione {

  private String descTipoComunicazione;
  private String descStatoComunicazione;
  private Long idCentroAziendale;
  private String descCentroAziendale;
  private CentroAziendaleDto centroAziendale;
  private Long idSpedizioniere;
  private String denomSpedizioniere;
  private String utenteCognome;
  private String utenteNome;
  private String utenteEmail;
  private Long numeroPiante;
  private String codiceRup;
  private String codiceRuop;
  
  private String codCentroAziendale;
  
  
  public Long getNumeroPiante() {
	  return numeroPiante;
  }

  public void setNumeroPiante(Long numeroPiante) {
	  this.numeroPiante = numeroPiante;
  }

private List<GenereSpecieDTO> listaGeneri;

  public String getDescTipoComunicazione() {
    return descTipoComunicazione;
  }

  public void setDescTipoComunicazione(String descTipoComunicazione) {
    this.descTipoComunicazione = descTipoComunicazione;
  }

  public String getDescStatoComunicazione() {
    return descStatoComunicazione;
  }

  public void setDescStatoComunicazione(String descStatoComunicazione) {
    this.descStatoComunicazione = descStatoComunicazione;
  }

  public Long getIdCentroAziendale() {
    return idCentroAziendale;
  }

  public void setIdCentroAziendale(Long idCentroAziendale) {
    this.idCentroAziendale = idCentroAziendale;
  }

  public String getDescCentroAziendale() {
    return descCentroAziendale;
  }

  public void setDescCentroAziendale(String descCentroAziendale) {
    this.descCentroAziendale = descCentroAziendale;
  }

  public CentroAziendaleDto getCentroAziendale() {
    return centroAziendale;
  }

  public void setCentroAziendale(CentroAziendaleDto centroAziendale) {
    this.centroAziendale = centroAziendale;
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

  public String getUtenteCognome() {
    return utenteCognome;
  }

  public void setUtenteCognome(String utenteCognome) {
    this.utenteCognome = utenteCognome;
  }

  public String getUtenteNome() {
    return utenteNome;
  }

  public void setUtenteNome(String utenteNome) {
    this.utenteNome = utenteNome;
  }

  public String getUtenteEmail() {
    return utenteEmail;
  }

  public void setUtenteEmail(String utenteEmail) {
    this.utenteEmail = utenteEmail;
  }

  public List<GenereSpecieDTO> getListaGeneri() {
    return listaGeneri;
  }

  public void setListaGeneri(List<GenereSpecieDTO> listaGeneri) {
    this.listaGeneri = listaGeneri;
  }


public String getCodiceRup() {
	return codiceRup;
}

public void setCodiceRup(String codiceRup) {
	this.codiceRup = codiceRup;
}

public String getCodiceRuop() {
	return codiceRuop;
}

public void setCodiceRuop(String codiceRuop) {
	this.codiceRuop = codiceRuop;
}

public String getCodCentroAziendale() {
	return codCentroAziendale;
}

public void setCodCentroAziendale(String codCentroAziendale) {
	this.codCentroAziendale = codCentroAziendale;
}




  
}
