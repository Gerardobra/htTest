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
public class RicercaReportForm extends BaseForm {

	@NotNull(message = "Dato obbligatorio")
  	@NumberFormat(style = Style.NUMBER)
	@Min(value = 1, message = "Dato obbligatorio") // parametrizzare tra le risorse
	private Integer anno;

	@NotNull(message = "Dato obbligatorio")
	@NumberFormat(style = Style.NUMBER)
	private Integer mese;

	private Long idSpedizioniere;
	private Integer idStatoRichiesta;
	private String codTipoRichiesta;	
	
	// Per Vivai
	//@NotNull(message = "Dato obbligatorio")
	private String genere;	
	
	//@NotNull(message = "Dato obbligatorio")
	private String specie;
	
	private String idSpecie;
	
	private List<ReportDTO> elencoReport;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCreazioneInizio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCreazioneFine;
	
	private Long idOrgNocivo;
	
	public String getCodTipoRichiesta() {
		return codTipoRichiesta;
	}

	public void setCodTipoRichiesta(String codTipoRichiesta) {
		this.codTipoRichiesta = codTipoRichiesta;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getMese() {
		return mese;
	}

	public void setMese(Integer mese) {
		this.mese = mese;
	}

	public Long getIdSpedizioniere() {
		return idSpedizioniere;
	}

	public void setIdSpedizioniere(Long idSpedizioniere) {
		this.idSpedizioniere = idSpedizioniere;
	}

	public Integer getIdStatoRichiesta() {
		return idStatoRichiesta;
	}

	public void setIdStatoRichiesta(Integer idStatoRichiesta) {
		this.idStatoRichiesta = idStatoRichiesta;
	}

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

	public String getIdSpecie() {
		return idSpecie;
	}

	public void setIdSpecie(String idSpecie) {
		this.idSpecie = idSpecie;
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