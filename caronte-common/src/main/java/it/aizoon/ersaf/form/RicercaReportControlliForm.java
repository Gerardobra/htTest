package it.aizoon.ersaf.form;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.validation.annotation.Validated;

import it.aizoon.ersaf.dto.ReportDTO;
import it.aizoon.ersaf.util.CaronteUtils;

@Validated
public class RicercaReportControlliForm extends BaseForm {

	// Per report controlli
	
	//	@NotNull(message = "Dato obbligatorio")
	private String ragioneSociale;
	private String codiceRuop;
	private String provincia;
	private String comune;
	private String partitaIva;
	private String ispettore;
	private String organismoNocivo;
	private Date dataControllo;
	private Date dataControlloInizio;
	private Date dataControlloFine;
	private String tipologiaAttivita;
	private Long idIspettore;
	private Long idComune;
	private Long idProvincia;
	private Long idOrgNocivo;
	
	// Per report campioni
	private String codCampione;
	private String denomGenere;
	private String genere;
	private String denomSpecie;
	private String specie;
	private Date dataRdp;
	private Date dataRdpInizio;
	private Date dataRdpFine;
	private List<Integer> idSpecieList;
	
   
	// Per report misura ufficiale
	private Date dataMisura;
	private Date dataMisuraInizio;
	private Date dataMisuraFine;
	private Long numeroVerbale;
	private String descOrgNocivo;
	// Per report monitoraggio cofinaziato
		
	
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = CaronteUtils.convertToUpperCase(ragioneSociale);
	}
	public String getCodiceRuop() {
		return codiceRuop;
	}
	public void setCodiceRuop(String codiceRuop) {
		this.codiceRuop = CaronteUtils.convertToUpperCase(codiceRuop);
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = CaronteUtils.convertToUpperCase(comune);
	}
	public String getpartitaIva() {
		return partitaIva;
	}
	public void setpartitaIva(String partitaIva) {
		this.partitaIva = CaronteUtils.convertToUpperCase(partitaIva);
	}
	public String getIspettore() {
		return ispettore;
	}
	public void setIspettore(String ispettore) {
		this.ispettore = CaronteUtils.convertToUpperCase(ispettore);
	}
	public String getOrganismoNocivo() {
		return organismoNocivo;
	}
	public void setOrganismoNocivo(String organismoNocivo) {
		this.organismoNocivo = CaronteUtils.convertToUpperCase(organismoNocivo);
	}
	public String getCodCampione() {
		return codCampione;
	}
	public void setCodCampione(String codCampione) {
		this.codCampione = CaronteUtils.convertToUpperCase(codCampione);
	}
	public String getDenomGenere() {
		return denomGenere;
	}
	public void setDenomGenere(String denomGenere) {
		this.denomGenere = CaronteUtils.convertToUpperCase(denomGenere);
	}
	public String getDenomSpecie() {
		return denomSpecie;
	}
	public void setDenomSpecie(String denomSpecie) {
		this.denomSpecie = CaronteUtils.convertToUpperCase(denomSpecie);
	}
	
	public Long getNumeroVerbale() {
		return numeroVerbale;
	}
	public void setNumeroVerbale(Long numeroVerbale) {
		this.numeroVerbale = numeroVerbale;
	}
	
	public String getTipologiaAttivita() {
		return tipologiaAttivita;
	}
	public void setTipologiaAttivita(String tipologiaAttivita) {
		this.tipologiaAttivita = CaronteUtils.convertToUpperCase(tipologiaAttivita);
	}
	public String getDescOrgNocivo() {
		return descOrgNocivo;
	}
	public void setDescOrgNocivo(String descOrgNocivo) {
		this.descOrgNocivo = CaronteUtils.convertToUpperCase(descOrgNocivo);
	}
	public Long getIdIspettore() {
		return idIspettore;
	}
	public void setIdIspettore(Long idIspettore) {
		this.idIspettore = idIspettore;
	}
	public Long getIdComune() {
		return idComune;
	}
	public void setIdComune(Long idComune) {
		this.idComune = idComune;
	}
	public Long getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}
	public Long getIdOrgNocivo() {
		return idOrgNocivo;
	}
	public void setIdOrgNocivo(Long idOrgNocivo) {
		this.idOrgNocivo = idOrgNocivo;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = CaronteUtils.convertToUpperCase(genere);
	}
	public String getSpecie() {
		return specie;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	public List<Integer> getIdSpecieList() {
		return idSpecieList;
	}
	public void setIdSpecieList(List<Integer> idSpecieList) {
		this.idSpecieList = idSpecieList;
	}
	/**
	 * @return the dataControlloInizio
	 */
	public Date getDataControlloInizio() {
		return dataControlloInizio;
	}
	/**
	 * @param dataControlloInizio the dataControlloInizio to set
	 */
	public void setDataControlloInizio(Date dataControlloInizio) {
		this.dataControlloInizio = dataControlloInizio;
	}
	/**
	 * @return the dataControlloFine
	 */
	public Date getDataControlloFine() {
		return dataControlloFine;
	}
	/**
	 * @param dataControlloFine the dataControlloFine to set
	 */
	public void setDataControlloFine(Date dataControlloFine) {
		this.dataControlloFine = dataControlloFine;
	}
	/**
	 * @return the dataRdpInizio
	 */
	public Date getDataRdpInizio() {
		return dataRdpInizio;
	}
	/**
	 * @param dataRdpInizio the dataRdpInizio to set
	 */
	public void setDataRdpInizio(Date dataRdpInizio) {
		this.dataRdpInizio = dataRdpInizio;
	}
	/**
	 * @return the dataRdpFine
	 */
	public Date getDataRdpFine() {
		return dataRdpFine;
	}
	/**
	 * @param dataRdpFine the dataRdpFine to set
	 */
	public void setDataRdpFine(Date dataRdpFine) {
		this.dataRdpFine = dataRdpFine;
	}
	/**
	 * @return the dataMisuraInizio
	 */
	public Date getDataMisuraInizio() {
		return dataMisuraInizio;
	}
	/**
	 * @param dataMisuraInizio the dataMisuraInizio to set
	 */
	public void setDataMisuraInizio(Date dataMisuraInizio) {
		this.dataMisuraInizio = dataMisuraInizio;
	}
	/**
	 * @return the dataMisuraFine
	 */
	public Date getDataMisuraFine() {
		return dataMisuraFine;
	}
	/**
	 * @param dataMisuraFine the dataMisuraFine to set
	 */
	public void setDataMisuraFine(Date dataMisuraFine) {
		this.dataMisuraFine = dataMisuraFine;
	}
	/**
	 * @return the dataControllo
	 */
	public Date getDataControllo() {
		return dataControllo;
	}
	/**
	 * @param dataControllo the dataControllo to set
	 */
	public void setDataControllo(Date dataControllo) {
		this.dataControllo = dataControllo;
	}
	/**
	 * @return the dataRdp
	 */
	public Date getDataRdp() {
		return dataRdp;
	}
	/**
	 * @param dataRdp the dataRdp to set
	 */
	public void setDataRdp(Date dataRdp) {
		this.dataRdp = dataRdp;
	}
	/**
	 * @return the dataMisura
	 */
	public Date getDataMisura() {
		return dataMisura;
	}
	/**
	 * @param dataMisura the dataMisura to set
	 */
	public void setDataMisura(Date dataMisura) {
		this.dataMisura = dataMisura;
	}
	
	
	

	

}