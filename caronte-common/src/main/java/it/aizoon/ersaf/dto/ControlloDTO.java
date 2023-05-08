package it.aizoon.ersaf.dto;


import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import it.aizoon.ersaf.dto.generati.CarTDomanda;
import it.aizoon.ersaf.dto.generati.CarTResponsabilePassaporto;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;



@SuppressWarnings("serial")
public class ControlloDTO extends CarTSpedizioniere {

	private Long idProvincia;
	private String denomComune;
	private String denomProvincia;
	private String denomTipoSpedizioniere;
	private String codiceFitok;
	private BigDecimal tariffa;
	
	
	//Parte relativa ai centri aziendali
	private Long idCentroAziendale;
	private String codCentroAziendale;
	private String denominazioneCa;
    private Long idComuneCa;
    private String denomComuneCa;
    private Long idProvinciaCa;
    private String denomProvinciaCa;
    private String capCa;
    private String indirizzoCa;
    private String frazioneCa;
    private String telefonoCa;
    private String cellulareCa;
    private String emailCa;
    private String pecCa;
    private Long idResponsabilePassaporto;
    private String cognomeRP;
    private String nomeRP;
    private String telefonoRP;
    private Long idDomanda;
    private Long idIspettore;
    private Long idTipologiaPassaporto;
    private String descrStatoAzienda;
    
    //parte relativa ai controlli
    private Long idControllo;
    private Long numeroVerbale;
	private String numeroVerbaleIS;
	private Date dataControllo;
	private String esitoControllo;
	private String flEsito;

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getDenomComune() {
		return denomComune;
	}

	public void setDenomComune(String denomComune) {
		this.denomComune = denomComune;
	}

	public String getDenomProvincia() {
		return denomProvincia;
	}

	public void setDenomProvincia(String denomProvincia) {
		this.denomProvincia = denomProvincia;
	}

	public String getDenomTipoSpedizioniere() {
		return denomTipoSpedizioniere;
	}

	public void setDenomTipoSpedizioniere(String denomTipoSpedizioniere) {
		this.denomTipoSpedizioniere = denomTipoSpedizioniere;
	}

	public String getDenominazioneCa() {
		return denominazioneCa;
	}

	public void setDenominazioneCa(String denominazioneCa) {
		this.denominazioneCa = denominazioneCa;
	}

	public Long getIdComuneCa() {
		return idComuneCa;
	}

	public void setIdComuneCa(Long idComuneCa) {
		this.idComuneCa = idComuneCa;
	}

	public String getDenomComuneCa() {
		return denomComuneCa;
	}

	public void setDenomComuneCa(String denomComuneCa) {
		this.denomComuneCa = denomComuneCa;
	}

	public Long getIdProvinciaCa() {
		return idProvinciaCa;
	}

	public void setIdProvinciaCa(Long idProvinciaCa) {
		this.idProvinciaCa = idProvinciaCa;
	}

	public String getDenomProvinciaCa() {
		return denomProvinciaCa;
	}

	public void setDenomProvinciaCa(String denomProvinciaCa) {
		this.denomProvinciaCa = denomProvinciaCa;
	}

	public String getIndirizzoCa() {
		return indirizzoCa;
	}

	public void setIndirizzoCa(String indirizzoCa) {
		this.indirizzoCa = indirizzoCa;
	}

	public String getCapCa() {
		return capCa;
	}

	public void setCapCa(String capCa) {
		this.capCa = capCa;
	}

	public String getFrazioneCa() {
		return frazioneCa;
	}

	public void setFrazioneCa(String frazioneCa) {
		this.frazioneCa = frazioneCa;
	}

	public String getTelefonoCa() {
		return telefonoCa;
	}

	public void setTelefonoCa(String telefonoCa) {
		this.telefonoCa = telefonoCa;
	}

	public String getCellulareCa() {
		return cellulareCa;
	}

	public void setCellulareCa(String cellulareCa) {
		this.cellulareCa = cellulareCa;
	}

	public String getEmailCa() {
		return emailCa;
	}

	public void setEmailCa(String emailCa) {
		this.emailCa = emailCa;
	}

	public String getPecCa() {
		return pecCa;
	}

	public void setPecCa(String pecCa) {
		this.pecCa = pecCa;
	}

	public Long getIdResponsabilePassaporto() {
		return idResponsabilePassaporto;
	}

	public void setIdResponsabilePassaporto(Long idResponsabilePassaporto) {
		this.idResponsabilePassaporto = idResponsabilePassaporto;
	}

	public String getCognomeRP() {
		return cognomeRP;
	}

	public void setCognomeRP(String cognomeRP) {
		this.cognomeRP = cognomeRP;
	}

	public String getNomeRP() {
		return nomeRP;
	}

	public void setNomeRP(String nomeRP) {
		this.nomeRP = nomeRP;
	}

	public Long getIdDomanda() {
		return idDomanda;
	}

	public void setIdDomanda(Long idDomanda) {
		this.idDomanda = idDomanda;
	}

	public String getTelefonoRP() {
		return telefonoRP;
	}

	public void setTelefonoRP(String telefonoRP) {
		this.telefonoRP = telefonoRP;
	}

	public Long getIdCentroAziendale() {
		return idCentroAziendale;
	}

	public void setIdCentroAziendale(Long idCentroAziendale) {
		this.idCentroAziendale = idCentroAziendale;
	}

	public String getCodCentroAziendale() {
		return codCentroAziendale;
	}

	public void setCodCentroAziendale(String codCentroAziendale) {
		this.codCentroAziendale = codCentroAziendale;
	}

	public Long getIdControllo() {
		return idControllo;
	}

	public void setIdControllo(Long idControllo) {
		this.idControllo = idControllo;
	}

	public Date getDataControllo() {
		return dataControllo;
	}

	public void setDataControllo(Date dataControllo) {
		this.dataControllo = dataControllo;
	}

	public String getEsitoControllo() {
		return esitoControllo;
	}

	public void setEsitoControllo(String esitoControllo) {
		this.esitoControllo = esitoControllo;
	}

	public String getFlEsito() {
		return flEsito;
	}

	public void setFlEsito(String flEsito) {
		this.flEsito = flEsito;
	}

	public Long getNumeroVerbale() {
		return numeroVerbale;
	}

	public void setNumeroVerbale(Long numeroVerbale) {
		this.numeroVerbale = numeroVerbale;
	}

	public String getNumeroVerbaleIS() {
		return numeroVerbaleIS;
	}

	public void setNumeroVerbaleIS(String numeroVerbaleIS) {
		this.numeroVerbaleIS = numeroVerbaleIS;
	}

	public Long getIdIspettore() {
		return idIspettore;
	}

	public void setIdIspettore(Long idIspettore) {
		this.idIspettore = idIspettore;
	}

	public Long getIdTipologiaPassaporto() {
		return idTipologiaPassaporto;
	}

	public void setIdTipologiaPassaporto(Long idTipologiaPassaporto) {
		this.idTipologiaPassaporto = idTipologiaPassaporto;
	}

	public String getDescrStatoAzienda() {
		return descrStatoAzienda;
	}

	public void setDescrStatoAzienda(String descrStatoAzienda) {
		this.descrStatoAzienda = descrStatoAzienda;
	}

	/**
	 * @return the codiceFitok
	 */
	public String getCodiceFitok() {
		return codiceFitok;
	}

	/**
	 * @param codiceFitok the codiceFitok to set
	 */
	public void setCodiceFitok(String codiceFitok) {
		this.codiceFitok = codiceFitok;
	}

	/**
	 * @return the tariffa
	 */
	public BigDecimal getTariffa() {
		return tariffa;
	}

	/**
	 * @param tariffa the tariffa to set
	 */
	public void setTariffa(BigDecimal tariffa) {
		this.tariffa = tariffa;
	}

	
}
