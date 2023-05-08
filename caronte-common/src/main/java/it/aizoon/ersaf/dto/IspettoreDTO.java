package it.aizoon.ersaf.dto;

import java.util.Date;

public class IspettoreDTO extends UtenteDTO {

	private static final long serialVersionUID = 6396809834013748430L;
	
	private Long idIspettore;
	private Long idRegioneServizio;
	private String denomRegioneServizio;
	private Long idNazioneNascita;
	private Long idRegioneNascita;
	private Long idProvinciaNascita;
	private Long idComuneNascita;
	private String tipoOperazione;
	private String sesso;
	private String titoloStudio;
	private String indirizzoUfficio;
	private String capUfficio;
	private Long idComuneUfficio;
	private boolean attivo;
	private Long idUtenteInsert;
	private Long idUtenteUpdate;
	private String numeroTessera;
	private Date dataNascita;
	private String codIspettore;
	private String denomComuneNascita;
	private String denomProvinciaNascita;
	private String denomNazioneNascita;

	private String denomComuneUfficio;
	private Date dataInsert;
	private Date dataUpdate;
	
	private String codComuneNascita;
	private String codNazioneUfficio;
	private String codComuneUfficio;
	private String codNazioneNascita;
	private String codProvinciaUfficio;
	private String codProvinciaNascita;
	private String denomProvinciaUfficio;
	private String codRegioneServizio;

	private Date dataInizio;
	private Date dataFine;

	private String cittaNascita;
	private String noteIspettore;
	private String tipoIspettore;
	private String descIspettore;
	private String ruolo;

	public Long getIdIspettore() {
		return idIspettore;
	}
	public void setIdIspettore(Long idIspettore) {
		this.idIspettore = idIspettore;
	}
	public Long getIdRegioneServizio() {
		return idRegioneServizio;
	}
	public void setIdRegioneServizio(Long idRegioneServizio) {
		this.idRegioneServizio = idRegioneServizio;
	}
	public Long getIdNazioneNascita() {
		return idNazioneNascita;
	}
	public void setIdNazioneNascita(Long idNazioneNascita) {
		this.idNazioneNascita = idNazioneNascita;
	}
	public Long getIdComuneNascita() {
		return idComuneNascita;
	}
	public void setIdComuneNascita(Long idComuneNascita) {
		this.idComuneNascita = idComuneNascita;
	}
	public String getTipoOperazione() {
		return tipoOperazione;
	}
	public void setTipoOperazione(String tipoOperazione) {
		this.tipoOperazione = tipoOperazione;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getTitoloStudio() {
		return titoloStudio;
	}
	public void setTitoloStudio(String titoloStudio) {
		this.titoloStudio = titoloStudio;
	}
	public String getIndirizzoUfficio() {
		return indirizzoUfficio;
	}
	public void setIndirizzoUfficio(String indirizzoUfficio) {
		this.indirizzoUfficio = indirizzoUfficio;
	}
	public String getCapUfficio() {
		return capUfficio;
	}
	public void setCapUfficio(String capUfficio) {
		this.capUfficio = capUfficio;
	}
	public Long getIdComuneUfficio() {
		return idComuneUfficio;
	}
	public void setIdComuneUfficio(Long idComuneUfficio) {
		this.idComuneUfficio = idComuneUfficio;
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
	public String getNumeroTessera() {
		return numeroTessera;
	}
	public void setNumeroTessera(String numeroTessera) {
		this.numeroTessera = numeroTessera;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getCodIspettore() {
		return codIspettore;
	}
	public void setCodIspettore(String codIspettore) {
		this.codIspettore = codIspettore;
	}
	public Long getIdRegioneNascita() {
		return idRegioneNascita;
	}
	public void setIdRegioneNascita(Long idRegioneNascita) {
		this.idRegioneNascita = idRegioneNascita;
	}
	public Long getIdProvinciaNascita() {
		return idProvinciaNascita;
	}
	public void setIdProvinciaNascita(Long idProvinciaNascita) {
		this.idProvinciaNascita = idProvinciaNascita;
	}
	public String getDenomRegioneServizio() {
		return denomRegioneServizio;
	}
	public void setDenomRegioneServizio(String denomRegioneServizio) {
		this.denomRegioneServizio = denomRegioneServizio;
	}
	public String getDenomComuneNascita() {
		return denomComuneNascita;
	}
	public void setDenomComuneNascita(String denomComuneNascita) {
		this.denomComuneNascita = denomComuneNascita;
	}
	public String getDenomComuneUfficio() {
		return denomComuneUfficio;
	}
	public void setDenomComuneUfficio(String denomComuneUfficio) {
		this.denomComuneUfficio = denomComuneUfficio;
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
	
	public String getDenominazioneIspettore() {
    return getNumeroTessera() + " (" + getNome() + " " + getCognome() + ")";
  }
	public String getCodComuneNascita() {
		return codComuneNascita;
	}
	public void setCodComuneNascita(String codComuneNascita) {
		this.codComuneNascita = codComuneNascita;
	}
	public String getCodNazioneUfficio() {
		return codNazioneUfficio;
	}
	public void setCodNazioneUfficio(String codNazioneUfficio) {
		this.codNazioneUfficio = codNazioneUfficio;
	}
	public String getCodComuneUfficio() {
		return codComuneUfficio;
	}
	public void setCodComuneUfficio(String codComuneUfficio) {
		this.codComuneUfficio = codComuneUfficio;
	}
	public String getCodNazioneNascita() {
		return codNazioneNascita;
	}
	public void setCodNazioneNascita(String codNazioneNascita) {
		this.codNazioneNascita = codNazioneNascita;
	}
	public String getCodProvinciaUfficio() {
		return codProvinciaUfficio;
	}
	public void setCodProvinciaUfficio(String codProvinciaUfficio) {
		this.codProvinciaUfficio = codProvinciaUfficio;
	}
	public String getCodProvinciaNascita() {
		return codProvinciaNascita;
	}
	public void setCodProvinciaNascita(String codProvinciaNascita) {
		this.codProvinciaNascita = codProvinciaNascita;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public String getDenomProvinciaNascita() {
		return denomProvinciaNascita;
	}
	public void setDenomProvinciaNascita(String denomProvinciaNascita) {
		this.denomProvinciaNascita = denomProvinciaNascita;
	}
	public String getDenomProvinciaUfficio() {
		return denomProvinciaUfficio;
	}
	public void setDenomProvinciaUfficio(String denomProvinciaUfficio) {
		this.denomProvinciaUfficio = denomProvinciaUfficio;
	}
	public String getDenomNazioneNascita() {
		return denomNazioneNascita;
	}
	public void setDenomNazioneNascita(String denomNazioneNascita) {
		this.denomNazioneNascita = denomNazioneNascita;
	}
	public String getCodRegioneServizio() {
		return codRegioneServizio;
	}
	public void setCodRegioneServizio(String codRegioneServizio) {
		this.codRegioneServizio = codRegioneServizio;
	}
	public String getCittaNascita() {
		return cittaNascita;
	}
	public void setCittaNascita(String cittaNascita) {
		this.cittaNascita = cittaNascita;
	}
	public String getNoteIspettore() {
		return noteIspettore;
	}
	public void setNoteIspettore(String noteIspettore) {
		this.noteIspettore = noteIspettore;
	}
	public String getTipoIspettore() {
		return tipoIspettore;
	}
	public void setTipoIspettore(String tipoIspettore) {
		this.tipoIspettore = tipoIspettore;
	}
	public String getDescIspettore() {
		return descIspettore;
	}
	public void setDescIspettore(String descIspettore) {
		this.descIspettore = descIspettore;
	}
	/**
	 * @return the ruolo
	 */
	public String getRuolo() {
		return ruolo;
	}
	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

}