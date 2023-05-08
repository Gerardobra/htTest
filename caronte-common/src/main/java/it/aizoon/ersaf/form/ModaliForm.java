package it.aizoon.ersaf.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import it.aizoon.ersaf.util.CaronteUtils;

/**
 * @author V. Arcarese
 */

public class ModaliForm extends BaseForm {

	private Long idDomanda;
	
	
	/*Modale tipologia attivita e materiali - Autorizzazioni*/
	private Long idTipoAttivitaMat;
	private Long idMateriale;
	private String[] idMaterialeList;
	private String note;
	private Long idAttivitaMaterialeUtente;
	
	/*Modale modifica campioni - Controlli*/
	private Long idCampione;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataRdp;
	private String esitoRdp;
	private String numeroRdp;
	private Long[] orgNocAccertato;
	private String noteCampione;
	
	// Modale Modifica Organismi nocivi - Controlli - Tab Controllo Fisico
	private Long idControlloFisicoSpecie;
	private String denomGenereFisico;
	private String denomSpecieFisico;	
	private String[] idOrgNociviFisico;
	private String flSacchetti;
	private String flBanda;
	private String flAnalisi;
	private String numPiante;
	private String numPianteSintomatiche;
	
	public Long getIdTipoAttivitaMat() {
		return idTipoAttivitaMat;
	}
	public void setIdTipoAttivitaMat(Long idTipoAttivitaMat) {
		this.idTipoAttivitaMat = idTipoAttivitaMat;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = CaronteUtils.convertToUpperCase(note);
	}
	public Long getIdDomanda() {
		return idDomanda;
	}
	public void setIdDomanda(Long idDomanda) {
		this.idDomanda = idDomanda;
	}
	public Long getIdAttivitaMaterialeUtente() {
		return idAttivitaMaterialeUtente;
	}
	public void setIdAttivitaMaterialeUtente(Long idAttivitaMaterialeUtente) {
		this.idAttivitaMaterialeUtente = idAttivitaMaterialeUtente;
	}
	public Long getIdMateriale() {
		return idMateriale;
	}
	public void setIdMateriale(Long idMateriale) {
		this.idMateriale = idMateriale;
	}
	public String[] getIdMaterialeList() {
		return idMaterialeList;
	}
	public void setIdMaterialeList(String[] idMaterialeList) {
		this.idMaterialeList = idMaterialeList;
	}
	public Long getIdCampione() {
		return idCampione;
	}
	public void setIdCampione(Long idCampione) {
		this.idCampione = idCampione;
	}
	public Date getDataRdp() {
		return dataRdp;
	}
	public void setDataRdp(Date dataRdp) {
		this.dataRdp = dataRdp;
	}
	public String getEsitoRdp() {
		return esitoRdp;
	}
	public void setEsitoRdp(String esitoRdp) {
		this.esitoRdp = CaronteUtils.convertToUpperCase(esitoRdp);
	}
	public String getNumeroRdp() {
		return numeroRdp;
	}
	public void setNumeroRdp(String numeroRdp) {
		this.numeroRdp = CaronteUtils.convertToUpperCase(numeroRdp);
	}
	public Long[] getOrgNocAccertato() {
		return orgNocAccertato;
	}
	public void setOrgNocAccertato(Long[] orgNocAccertato) {
		this.orgNocAccertato = orgNocAccertato;
	}
	public String getNoteCampione() {
		return noteCampione;
	}
	public void setNoteCampione(String noteCampione) {
		this.noteCampione = CaronteUtils.convertToUpperCase(noteCampione);
	}
	public Long getIdControlloFisicoSpecie() {
		return idControlloFisicoSpecie;
	}
	public void setIdControlloFisicoSpecie(Long idControlloFisicoSpecie) {
		this.idControlloFisicoSpecie = idControlloFisicoSpecie;
	}
	public String getDenomGenereFisico() {
		return denomGenereFisico;
	}
	public void setDenomGenereFisico(String denomGenereFisico) {
		this.denomGenereFisico = CaronteUtils.convertToUpperCase(denomGenereFisico);
	}
	public String getDenomSpecieFisico() {
		return denomSpecieFisico;
	}
	public void setDenomSpecieFisico(String denomSpecieFisico) {
		this.denomSpecieFisico = CaronteUtils.convertToUpperCase(denomSpecieFisico);
	}
	public String[] getIdOrgNociviFisico() {
		return idOrgNociviFisico;
	}
	public void setIdOrgNociviFisico(String[] idOrgNociviFisico) {
		this.idOrgNociviFisico = idOrgNociviFisico;
	}
	public String getFlSacchetti() {
		return flSacchetti;
	}
	public void setFlSacchetti(String flSacchetti) {
		this.flSacchetti = flSacchetti;
	}
	public String getFlBanda() {
		return flBanda;
	}
	public void setFlBanda(String flBanda) {
		this.flBanda = flBanda;
	}
	public String getFlAnalisi() {
		return flAnalisi;
	}
	public void setFlAnalisi(String flAnalisi) {
		this.flAnalisi = flAnalisi;
	}
	public String getNumPiante() {
		return numPiante;
	}
	public void setNumPiante(String numPiante) {
		this.numPiante = numPiante;
	}
	public String getNumPianteSintomatiche() {
		return numPianteSintomatiche;
	}
	public void setNumPianteSintomatiche(String numPianteSintomatiche) {
		this.numPianteSintomatiche = numPianteSintomatiche;
	}

	

	


}
