package it.aizoon.ersaf.dto;

import java.util.Date;
import java.util.List;

import it.aizoon.ersaf.dto.generati.CarTCentroAziendale;

public class SpedizioniereDTO extends UtenteDTO {

  private static final long serialVersionUID = -4696129013380951948L;
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
  private String descStatoAzienda;
  private List<CarTCentroAziendale> listaCentriAz;
  private boolean flEliminabile;
  private String codiceFitok;
  private String descCentroAz;

 
  
  // per la registrazione
  private Long idProvinciaSedeSociale;
  private Long idComuneSedeSociale;
  private String denomComuneSedeSociale;
  private String indirizzoSedeSociale;
  private String capSedeSociale;
  private String partitaIVA;
  private String numeroTelefono;
  private String numeroCellulare;
  private String numeroFax;
  private String codiceRUP;
  private String codiceRUOP;
  private String emailSpedizioniere;
  private String motivoRichiesta;
  private String pec;
  private String nomeDitta;
  private String cognomeDitta;
  private String tipoSpedizioniereAltro;
  private Date dataRegistrazioneRuop;
  
  // Sezione Controlli
  private String provinciaSedeLegale;
  private String tipoPassaporto;
  private String ispettoreAssegnatario;
  private Long idStatoComunicazione;
  private Long idTipoStampa;
  private String codiceCentroAz;
  private String tipologiaAttivita;
  private Long idCentroAziendale;
  private Long idComune;


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
    this.denomTipoSpedizioniere = denomTipoSpedizioniere;
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
    this.cuaa = cuaa;
  }

  public String getDenomSpedizioniere() {
    return denomSpedizioniere;
  }

  public void setDenomSpedizioniere(String denomSpedizioniere) {
    this.denomSpedizioniere = denomSpedizioniere;
  }

  public boolean isAutorizPagamPosticip() {
    return autorizPagamPosticip;
  }

  public void setAutorizPagamPosticip(boolean autorizPagamPosticip) {
    this.autorizPagamPosticip = autorizPagamPosticip;
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
    this.indirizzoSedeSociale = indirizzoSedeSociale;
  }

  public String getCapSedeSociale() {
    return capSedeSociale;
  }

  public void setCapSedeSociale(String capSedeSociale) {
    this.capSedeSociale = capSedeSociale;
  }

  public String getPartitaIVA() {
    return partitaIVA;
  }

  public void setPartitaIVA(String partitaIVA) {
    this.partitaIVA = partitaIVA;
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
    this.codiceRUP = codiceRUP;
  }

  public String getCodiceRUOP() {
    return codiceRUOP;
  }

  public void setCodiceRUOP(String codiceRUOP) {
    this.codiceRUOP = codiceRUOP;
  }

  public String getDenomComuneSedeSociale() {
    return denomComuneSedeSociale;
  }

  public void setDenomComuneSedeSociale(String denomComuneSedeSociale) {
    this.denomComuneSedeSociale = denomComuneSedeSociale;
  }

  public String getEmailSpedizioniere() {
    return emailSpedizioniere;
  }

  public void setEmailSpedizioniere(String emailSpedizioniere) {
    this.emailSpedizioniere = emailSpedizioniere;
  }

  public String getPec() {
    return pec;
  }

  public void setPec(String pec) {
    this.pec = pec;
  }

  public String getNomeDitta() {
    return nomeDitta;
  }

  public void setNomeDitta(String nomeDitta) {
    this.nomeDitta = nomeDitta;
  }

  public String getCognomeDitta() {
    return cognomeDitta;
  }

  public void setCognomeDitta(String cognomeDitta) {
    this.cognomeDitta = cognomeDitta;
  }

  public String getMotivoRichiesta() {
    return motivoRichiesta;
  }

  public void setMotivoRichiesta(String motivoRichiesta) {
    this.motivoRichiesta = motivoRichiesta;
  }

public String getNumeroCellulare() {
	return numeroCellulare;
}

public void setNumeroCellulare(String numeroCellulare) {
	this.numeroCellulare = numeroCellulare;
}

public String getNumeroFax() {
	return numeroFax;
}

public void setNumeroFax(String numeroFax) {
	this.numeroFax = numeroFax;
}

public String getTipoSpedizioniereAltro() {
	return tipoSpedizioniereAltro;
}

public void setTipoSpedizioniereAltro(String tipoSpedizionireAltro) {
	this.tipoSpedizioniereAltro = tipoSpedizionireAltro;
}

public Date getDataRegistrazioneRuop() {
	return dataRegistrazioneRuop;
}

public void setDataRegistrazioneRuop(Date dataRegistrazioneRuop) {
	this.dataRegistrazioneRuop = dataRegistrazioneRuop;
}

public String getDescStatoAzienda() {
	return descStatoAzienda;
}

public void setDescStatoAzienda(String descStatoAzienda) {
	this.descStatoAzienda = descStatoAzienda;
}

public Long getIdStatoAzienda() {
	return idStatoAzienda;
}

public void setIdStatoAzienda(Long idStatoAzienda) {
	this.idStatoAzienda = idStatoAzienda;
}

public List<CarTCentroAziendale> getListaCentriAz() {
	return listaCentriAz;
}

public void setListaCentriAz(List<CarTCentroAziendale> listaCentriAz) {
	this.listaCentriAz = listaCentriAz;
}

public String getProvinciaSedeLegale() {
	return provinciaSedeLegale;
}

public void setProvinciaSedeLegale(String provinciaSedeLegale) {
	this.provinciaSedeLegale = provinciaSedeLegale;
}

public String getTipoPassaporto() {
	return tipoPassaporto;
}

public void setTipoPassaporto(String tipoPassaporto) {
	this.tipoPassaporto = tipoPassaporto;
}

public String getIspettoreAssegnatario() {
	return ispettoreAssegnatario;
}

public void setIspettoreAssegnatario(String ispettoreAssegnatario) {
	this.ispettoreAssegnatario = ispettoreAssegnatario;
}

public Long getIdStatoComunicazione() {
	return idStatoComunicazione;
}

public void setIdStatoComunicazione(Long idStatoComunicazione) {
	this.idStatoComunicazione = idStatoComunicazione;
}

public Long getIdTipoStampa() {
	return idTipoStampa;
}

public void setIdTipoStampa(Long idTipoStampa) {
	this.idTipoStampa = idTipoStampa;
}

public Long getIdComune() {
	return idComune;
}

public void setIdComune(Long idComune) {
	this.idComune = idComune;
}

public String getCodiceCentroAz() {
	return codiceCentroAz;
}

public void setCodiceCentroAz(String codiceCentroAz) {
	this.codiceCentroAz = codiceCentroAz;
}

public String getTipologiaAttivita() {
	return tipologiaAttivita;
}

public void setTipologiaAttivita(String tipologiaAttivita) {
	this.tipologiaAttivita = tipologiaAttivita;
}

/**
 * @return the idCentroAziendale
 */
public Long getIdCentroAziendale() {
	return idCentroAziendale;
}

/**
 * @param idCentroAziendale the idCentroAziendale to set
 */
public void setIdCentroAziendale(Long idCentroAziendale) {
	this.idCentroAziendale = idCentroAziendale;
}

/**
 * @return the flEliminabile
 */
public boolean isFlEliminabile() {
	return flEliminabile;
}

/**
 * @param flEliminabile the flEliminabile to set
 */
public void setFlEliminabile(boolean flEliminabile) {
	this.flEliminabile = flEliminabile;
}

public String getCodiceFitok() {
	return codiceFitok;
}

public void setCodiceFitok(String codiceFitok) {
	this.codiceFitok = codiceFitok;
}

public String getDescCentroAz() {
	return descCentroAz;
}

public void setDescCentroAz(String descCentroAz) {
	this.descCentroAz = descCentroAz;
}





}