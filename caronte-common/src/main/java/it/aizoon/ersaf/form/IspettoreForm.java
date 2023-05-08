package it.aizoon.ersaf.form;

import java.util.Date;

import it.aizoon.ersaf.util.CaronteUtils;

public class IspettoreForm extends UtenteForm {

	private Long idIspettore;
	private Long idRegioneServizio;
	private Long idNazioneNascita;
	private Long idComuneNascita;
	private Long idRegioneNascita;
	private Long idProvinciaNascita;
	private String tipoOperazione;
	private String sesso;
	private String titoloStudio;
	private String indirizzoUfficio;
	private String capUfficio;
	private Long idComuneUfficio;
	private Long idUtenteInsert;
	private Long idUtenteUpdate;
	private String numeroTessera;
	private Date dataNascita;
	private String codIspettore;
	private boolean attivo;
	private String denomComuneNascita;
	private String denomComuneUfficio;
	private Date dataInsert;
	private Date dataUpdate;
	
	private String cittaNascita;
	private String noteIspettore;

	
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
		this.tipoOperazione = CaronteUtils.convertToUpperCase(tipoOperazione);
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = CaronteUtils.convertToUpperCase(sesso);
	}
	public String getTitoloStudio() {
		return titoloStudio;
	}
	public void setTitoloStudio(String titoloStudio) {
		this.titoloStudio = CaronteUtils.convertToUpperCase(titoloStudio);
	}
	public String getIndirizzoUfficio() {
		return indirizzoUfficio;
	}
	public void setIndirizzoUfficio(String indirizzoUfficio) {
		this.indirizzoUfficio = CaronteUtils.convertToUpperCase(indirizzoUfficio);
	}
	public String getCapUfficio() {
		return capUfficio;
	}
	public void setCapUfficio(String capUfficio) {
		this.capUfficio = CaronteUtils.convertToUpperCase(capUfficio);
	}
	public Long getIdComuneUfficio() {
		return idComuneUfficio;
	}
	public void setIdComuneUfficio(Long idComuneUfficio) {
		this.idComuneUfficio = idComuneUfficio;
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
		this.numeroTessera = CaronteUtils.convertToUpperCase(numeroTessera);
	}
	public String getCodIspettore() {
		return codIspettore;
	}
	public void setCodIspettore(String codIspettore) {
		this.codIspettore = CaronteUtils.convertToUpperCase(codIspettore);
	}
	public boolean isAttivo() {
		return attivo;
	}
	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
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
	public String getDenomComuneNascita() {
		return denomComuneNascita;
	}
	public void setDenomComuneNascita(String denomComuneNascita) {
		this.denomComuneNascita = CaronteUtils.convertToUpperCase(denomComuneNascita);
	}
	public String getDenomComuneUfficio() {
		return denomComuneUfficio;
	}
	public void setDenomComuneUfficio(String denomComuneUfficio) {
		this.denomComuneUfficio = CaronteUtils.convertToUpperCase(denomComuneUfficio);
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
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
	public String getCittaNascita() {
		return cittaNascita;
	}
	public void setCittaNascita(String cittaNascita) {
		this.cittaNascita = CaronteUtils.convertToUpperCase(cittaNascita);
	}
	public String getNoteIspettore() {
		return noteIspettore;
	}
	public void setNoteIspettore(String noteIspettore) {
		this.noteIspettore = CaronteUtils.convertToUpperCase(noteIspettore);
	}
	
	
}