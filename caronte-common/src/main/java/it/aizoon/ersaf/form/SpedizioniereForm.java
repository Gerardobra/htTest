package it.aizoon.ersaf.form;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import it.aizoon.ersaf.util.CaronteUtils;

public class SpedizioniereForm extends UtenteForm {

  private Long idSpedizioniere;
  private String denomTipoSpedizioniere;
  private Long idTipoSpedizioniere;
  private boolean attivo;
  private Long idUtenteInsert;
  private Long idUtenteUpdate;
  private Date dataInsert;
  private Date dataUpdate;
  private Date dataCancellazione;
  private String cuaa;
  private String denomSpedizioniere;
  private boolean autorizPagamPosticip;
  private Long idStatoAzienda;
  private List<Long> statoCentroAz;
  private String tipologiaAttivita; 
  private String codiceFitok;
  
  
  //per la registrazione
  private Long idProvinciaSedeSociale;
  private Long idComuneSedeSociale;
  private String indirizzoSedeSociale;
  private String capSedeSociale;
  private String partitaIVA;
  private String numeroTelefono;
  private String numeroCellulare;
  private String codiceRUP;
  private String codiceRUOP;
  private String emailSpedizioniere;
  private String denomProvinciaSedeSociale;
  private String denomComuneSedeSociale;
  private String motivoRichiesta;
  private String pec;
  private String nomeDitta;
  private String cognomeDitta;  
  private String tipoSpedizioniereAltro;
  
  // Check di accettazione privacy policy
  private String privacyPolicy;
  private Date dataAccettazionePrivacy;
  
  // In caso di sede legale fuori regione  
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date dataRegistrazioneRuop;

  public Long getIdSpedizioniere() {
    return idSpedizioniere;
  }

  public void setIdSpedizioniere(Long idSpedizioniere) {
    this.idSpedizioniere = idSpedizioniere;
  }

  public String getDenomTipoSpedizioniere() {
    return denomTipoSpedizioniere;
  }

  public void setDenomTipoSpedizioniere(String denomTipoSpedizioniere) {
    this.denomTipoSpedizioniere = CaronteUtils.convertToUpperCase(denomTipoSpedizioniere);
  }

  public Long getIdTipoSpedizioniere() {
    return idTipoSpedizioniere;
  }

  public void setIdTipoSpedizioniere(Long idTipoSpedizioniere) {
    this.idTipoSpedizioniere = idTipoSpedizioniere;
  }

  public boolean isAttivo() {
    return attivo;
  }

  public void setAttivo(boolean attivo) {
    this.attivo = attivo;
  }

  public Long getIdUtenteInsert() {
    return idUtenteInsert;
  }

  public void setIdUtenteInsert(Long idUtenteInsert) {
    this.idUtenteInsert = idUtenteInsert;
  }

  public Long getIdUtenteUpdate() {
    return idUtenteUpdate;
  }

  public void setIdUtenteUpdate(Long idUtenteUpdate) {
    this.idUtenteUpdate = idUtenteUpdate;
  }

  public Date getDataInsert() {
    return dataInsert;
  }

  public void setDataInsert(Date dataInsert) {
    this.dataInsert = dataInsert;
  }

  public Date getDataUpdate() {
    return dataUpdate;
  }

  public void setDataUpdate(Date dataUpdate) {
    this.dataUpdate = dataUpdate;
  }

  public Date getDataCancellazione() {
    return dataCancellazione;
  }

  public void setDataCancellazione(Date dataCancellazione) {
    this.dataCancellazione = dataCancellazione;
  }

  public String getCuaa() {
    return cuaa;
  }

  public void setCuaa(String cuaa) {
    this.cuaa = CaronteUtils.convertToUpperCase(cuaa);
  }

  public String getDenomSpedizioniere() {
    return denomSpedizioniere;
  }

  public void setDenomSpedizioniere(String denomSpedizioniere) {
    this.denomSpedizioniere = CaronteUtils.convertToUpperCase(denomSpedizioniere);
  }

public Long getIdProvinciaSedeSociale() {
	return idProvinciaSedeSociale;
}

public void setIdProvinciaSedeSociale(Long idProvinciaSedeSociale) {
	this.idProvinciaSedeSociale = idProvinciaSedeSociale;
}

public Long getIdComuneSedeSociale() {
	return idComuneSedeSociale;
}

public void setIdComuneSedeSociale(Long idComuneSedeSociale) {
	this.idComuneSedeSociale = idComuneSedeSociale;
}

public String getIndirizzoSedeSociale() {
	return indirizzoSedeSociale;
}

public void setIndirizzoSedeSociale(String indirizzoSedeSociale) {
	this.indirizzoSedeSociale = CaronteUtils.convertToUpperCase(indirizzoSedeSociale);
}

public String getCapSedeSociale() {
	return capSedeSociale;
}

public void setCapSedeSociale(String capSedeSociale) {
	this.capSedeSociale = CaronteUtils.convertToUpperCase(capSedeSociale);
}

public String getPartitaIVA() {
	return partitaIVA;
}

public void setPartitaIVA(String partitaIVA) {
	this.partitaIVA = CaronteUtils.convertToUpperCase(partitaIVA);
}

public String getNumeroTelefono() {
	return numeroTelefono;
}

public void setNumeroTelefono(String numeroTelefono) {
	this.numeroTelefono = numeroTelefono;
}

public String getCodiceRUP() {
	return codiceRUP;
}

public void setCodiceRUP(String codiceRUP) {
	this.codiceRUP = CaronteUtils.convertToUpperCase(codiceRUP);
}

public String getCodiceRUOP() {
  return codiceRUOP;
}

public void setCodiceRUOP(String codiceRUOP) {
  this.codiceRUOP = CaronteUtils.convertToUpperCase(codiceRUOP);
}

public String getEmailSpedizioniere() {
	return emailSpedizioniere;
}

public void setEmailSpedizioniere(String emailSpedizioniere) {
	this.emailSpedizioniere = CaronteUtils.convertToUpperCase(emailSpedizioniere);
}

public String getDenomProvinciaSedeSociale() {
	return denomProvinciaSedeSociale;
}

public void setDenomProvinciaSedeSociale(String denomProvinciaSedeSociale) {
	this.denomProvinciaSedeSociale = CaronteUtils.convertToUpperCase(denomProvinciaSedeSociale);
}

public String getDenomComuneSedeSociale() {
	return denomComuneSedeSociale;
}

public void setDenomComuneSedeSociale(String denomComuneSedeSociale) {
	this.denomComuneSedeSociale = CaronteUtils.convertToUpperCase(denomComuneSedeSociale);
}

public String getMotivoRichiesta() {
	return motivoRichiesta;
}

public void setMotivoRichiesta(String motivoRichiesta) {
	this.motivoRichiesta = CaronteUtils.convertToUpperCase(motivoRichiesta);
}

public String getPec() {
	return pec;
}

public void setPec(String pec) {
	this.pec = CaronteUtils.convertToUpperCase(pec);
}

public String getNomeDitta() {
	return nomeDitta;
}

public void setNomeDitta(String nomeDitta) {
	this.nomeDitta = CaronteUtils.convertToUpperCase(nomeDitta);
}

public String getCognomeDitta() {
	return cognomeDitta;
}

public void setCognomeDitta(String cognomeDitta) {
	this.cognomeDitta = CaronteUtils.convertToUpperCase(cognomeDitta);
}

public boolean isAutorizPagamPosticip() {
	return autorizPagamPosticip;
}

public void setAutorizPagamPosticip(boolean autorizPagamPosticip) {
	this.autorizPagamPosticip = autorizPagamPosticip;
}

public String getNumeroCellulare() {
	return numeroCellulare;
}

public void setNumeroCellulare(String numeroCellulare) {
	this.numeroCellulare = numeroCellulare;
}

public Long getIdStatoAzienda() {
	return idStatoAzienda;
}

public void setIdStatoAzienda(Long idStatoAzienda) {
	this.idStatoAzienda = idStatoAzienda;
}

public String getTipoSpedizioniereAltro() {
	return tipoSpedizioniereAltro;
}



public void setTipoSpedizioniereAltro(String tipoSpedizioniereAltro) {
	this.tipoSpedizioniereAltro = CaronteUtils.convertToUpperCase(tipoSpedizioniereAltro);
}

public Date getDataRegistrazioneRuop() {
	return dataRegistrazioneRuop;
}

public void setDataRegistrazioneRuop(Date dataRegistrazioneRuop) {
	this.dataRegistrazioneRuop = dataRegistrazioneRuop;
}

public List<Long> getStatoCentroAz() {
	return statoCentroAz;
}

public void setStatoCentroAz(List<Long> statoCentroAz) {
	this.statoCentroAz = statoCentroAz;
}

public String getPrivacyPolicy() {
	return privacyPolicy;
}

public void setPrivacyPolicy(String privacyPolicy) {
	this.privacyPolicy = CaronteUtils.convertToUpperCase(privacyPolicy);
}

public Date getDataAccettazionePrivacy() {
	return dataAccettazionePrivacy;
}

public void setDataAccettazionePrivacy(Date dataAccettazionePrivacy) {
	this.dataAccettazionePrivacy = dataAccettazionePrivacy;
}

public String getTipologiaAttivita() {
	return tipologiaAttivita;
}

public void setTipologiaAttivita(String tipologiaAttivita) {
	this.tipologiaAttivita = CaronteUtils.convertToUpperCase(tipologiaAttivita);
}

public String getCodiceFitok() {
	return codiceFitok;
}

public void setCodiceFitok(String codiceFitok) {
	this.codiceFitok = CaronteUtils.convertToUpperCase(codiceFitok);
}




}