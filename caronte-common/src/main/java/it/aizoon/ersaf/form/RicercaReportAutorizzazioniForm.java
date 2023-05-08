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
public class RicercaReportAutorizzazioniForm extends BaseForm {

	
	private List<ReportDTO> elencoReport;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataRuopInizio; 
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataRuopFine;	

	private boolean aziendeConDomandaRuop;
	private Long idTipoSpedizioniere;
	private Long idStatoAzienda;
	private String ragioneSociale;
	private String codiceRuop;
	private String tipologiaAttivita;
	private Long idProvincia;
	private Long idComune;
	private String partitaIva;
	private Long idTipologiaPassaporto;
	private Long idIspettore;
	
	public List<ReportDTO> getElencoReport() {
		return elencoReport;
	}

	public void setElencoReport(List<ReportDTO> elencoReport) {
		this.elencoReport = elencoReport;
	}

	public Date getDataRuopInizio() {
		return dataRuopInizio;
	}

	public void setDataRuopInizio(Date dataRuopInizio) {
		this.dataRuopInizio = dataRuopInizio;
	}

	public Date getDataRuopFine() {
		return dataRuopFine;
	}

	public void setDataRuopFine(Date dataRuopFine) {
		this.dataRuopFine = dataRuopFine;
	}

	public boolean isAziendeConDomandaRuop() {
		return aziendeConDomandaRuop;
	}

	public void setAziendeConDomandaRuop(boolean aziendeConDomandaRuop) {
		this.aziendeConDomandaRuop = aziendeConDomandaRuop;
	}

	public Long getIdTipoSpedizioniere() {
		return idTipoSpedizioniere;
	}

	public void setIdTipoSpedizioniere(Long idTipoSpedizioniere) {
		this.idTipoSpedizioniere = idTipoSpedizioniere;
	}

	public Long getIdStatoAzienda() {
		return idStatoAzienda;
	}

	public void setIdStatoAzienda(Long idStatoAzienda) {
		this.idStatoAzienda = idStatoAzienda;
	}

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

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public Long getIdComune() {
		return idComune;
	}

	public void setIdComune(Long idComune) {
		this.idComune = idComune;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = CaronteUtils.convertToUpperCase(partitaIva);
	}

	public Long getIdTipologiaPassaporto() {
		return idTipologiaPassaporto;
	}

	public void setIdTipologiaPassaporto(Long idTipologiaPassaporto) {
		this.idTipologiaPassaporto = idTipologiaPassaporto;
	}

	public Long getIdIspettore() {
		return idIspettore;
	}

	public void setIdIspettore(Long idIspettore) {
		this.idIspettore = idIspettore;
	}

	public String getTipologiaAttivita() {
		return tipologiaAttivita;
	}

	public void setTipologiaAttivita(String tipologiaAttivita) {
		this.tipologiaAttivita = CaronteUtils.convertToUpperCase(tipologiaAttivita);
	}

	
	

	

	

}