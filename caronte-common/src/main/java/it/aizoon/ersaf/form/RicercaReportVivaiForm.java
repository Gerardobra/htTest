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
public class RicercaReportVivaiForm extends BaseForm {

	
	// Per Vivai
	//@NotNull(message = "Dato obbligatorio")
	private String genere;	
	
	//@NotNull(message = "Dato obbligatorio")
	private String specie;	
	
	private List<Integer> idSpecieList;
	
	
	private List<ReportDTO> elencoReport;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCreazioneInizio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCreazioneFine;
	
	private Long idOrgNocivo;
	
		

	public List<ReportDTO> getElencoReport() {
		return elencoReport;
	}

	public void setElencoReport(List<ReportDTO> elencoReport) {
		this.elencoReport = elencoReport;
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

	public Date getDataCreazioneInizio() {
		return dataCreazioneInizio;
	}

	public void setDataCreazioneInizio(Date dataCreazioneInizio) {
		this.dataCreazioneInizio = dataCreazioneInizio;
	}

	public Date getDataCreazioneFine() {
		return dataCreazioneFine;
	}

	public void setDataCreazioneFine(Date dataCreazioneFine) {
		this.dataCreazioneFine = dataCreazioneFine;
	}

	public Long getIdOrgNocivo() {
		return idOrgNocivo;
	}

	public void setIdOrgNocivo(Long idOrgNocivo) {
		this.idOrgNocivo = idOrgNocivo;
	}
	

	

	

}