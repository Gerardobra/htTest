package it.aizoon.ersaf.dto;


import java.util.Date;
import java.util.List;

import it.aizoon.ersaf.dto.generati.CarTDomanda;
import it.aizoon.ersaf.dto.generati.CarTResponsabilePassaporto;



@SuppressWarnings("serial")
public class DomandaDto extends CarTDomanda {

	// Dati domanda
	private String descTipoDomanda;
	private String descStatoDomanda;  	
	private String utenteCognome;
	private String utenteNome;
	private String utenteEmail;
	private String codDomanda;
	
	private Long idAssociazioneSezione;
	private Long idTipoFlusso;
	private Long idFlusso;
	private String descTipoComSuccessiva;

	// Dati anagrafici
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private Date dataNascita;
	private String cap;
	private String indirizzo;
	private String telefono;
	private String cellulare;
	private String email;	
	
	private Long idProvNascita;
	private String provNascita;
	private Long idComuneNascita;
	private String comuneNascita;
	private String denomComuneEstNascita;
	private Long idNazioneEstNascita;
	private String nazioneEstNascita;

	private Long idProvResidenza;
	private String provResidenza;
	private Long idComuneResidenza;
	private String comuneResidenza;
	private String denomComuneEstResid;
	private Long idNazioneEstResid;
	private String nazioneEstResid;

	// Dati azienda
	private Long idSpedizioniere;
	private String denomSpedizioniere;
	private Long idTipoSpedizioniere;
	private String denomTipoSpedizioniere;
	private String cuaa;
	private String nomeSped;
	private String cognomeSped;
	private String telefonoSped;
	private String cellSped;
	private String faxSped;
	private String capSped;
	private String indirizzoSped;
	private Long idComuneSped;
	private String denomComuneSped;
	private Long idProvinciaSped;
	private String denomProvinciaSped;
	private String emailSped;
	private String pecSped;
	private String partitaIva;
	private String tipoSpedizioniereAltro;
	
	// Dati centri aziendali
	private List<CentroAziendaleDomandaDTO> centriAziendaliList;	
	
	//Dati passaporto
	private String[] idVoceChecked;	
	private String tipologiaPassaporto;
	private Date dataAutorizzazionePassaporto;
	private Long idTipologiaPassaporto;
	
	// Dati dettaglio Tipologia
	private String[] descrVociAttivitaTipologia;
	private String[] descrVociTipologieDomande;	
	private List<TipologiaAttMaterialeDTO> tipAttivitaTipologia;
	
	// Dati dattaglio Import
	private List<TipologiaProdSpecieDTO> tipologieProdImport;
	private String[] descrVociZoneProtetteImport;
	private String impDatoAggiuntivo;
	private String[] descrVociContinentiImport;
	private String statoOrigine;
	private String noteImport;
		
	// Dati dettaglio Export
	private String[] descrVoceCheckExp;
	private String expDatoAggiuntivo;
	private String[] descrVoceCheckContinentiExp;
	private String statoOrigineExp;
	private List<TipologiaProdSpecieDTO> tipologieProdExp;
	
	// Dati dettaglio Passaporto
	private CarTResponsabilePassaporto respPassaporto;
	private String[] descrVoceTipoRichiesta;
	private String[] descrVoceRadioCheckedPass;
	private String[] descrVoceCheckedPass;
	private List<TipologiaProdSpecieDTO> tipologieProdPass;
	private List<ZonaProtettaSpecieDTO> zoneProtettePass;
	private String voceDichiaraConoscenze;
	private String voceDichiaraConoscenzeUtente;
	private String voceDichiaraDisporreSistemi;	
	private String voceDichiaraDisporreSistemiUtente;
	private String vocePianoRischi;
	private String vocePianoRischiUtente;
	
	// Dati dettaglio Allegati
	private List<AllegatoDTO> listaAllegati;
	
	//Dati tab Gestione	
	private String codiceRuop;
	private Date dataRegistrazioneRuop;	
	private String descIspettore;
	private Long idIspettore;

	// Gestione flusso
	private Long tipoDomSuccessiva;
	private String descTipoDomSuccessiva;
	private Long statoDomSuccessiva;
	private Long numDomandeFlusso;
	private Boolean flagModDomanda;
	private Long progressivo;	
	private Long maxIdDomandaFlusso;
	
	//Stampa
	private Long idTipoStampa;
	


	public Long getIdSpedizioniere() {
		return idSpedizioniere;
	}
	public void setIdSpedizioniere(Long idSpedizioniere) {
		this.idSpedizioniere = idSpedizioniere;
	}
	public String getDenomSpedizioniere() {
		return denomSpedizioniere;
	}
	public void setDenomSpedizioniere(String denomSpedizioniere) {
		this.denomSpedizioniere = denomSpedizioniere;
	}
	public String getUtenteCognome() {
		return utenteCognome;
	}
	public void setUtenteCognome(String utenteCognome) {
		this.utenteCognome = utenteCognome;
	}
	public String getUtenteNome() {
		return utenteNome;
	}
	public void setUtenteNome(String utenteNome) {
		this.utenteNome = utenteNome;
	}
	public String getUtenteEmail() {
		return utenteEmail;
	}
	public void setUtenteEmail(String utenteEmail) {
		this.utenteEmail = utenteEmail;
	}
	public String getCodDomanda() {
		return codDomanda;
	}
	public void setCodDomanda(String codDomanda) {
		this.codDomanda = codDomanda;
	}
	public Long getIdAssociazioneSezione() {
		return idAssociazioneSezione;
	}
	public void setIdAssociazioneSezione(Long idAssociazioneSezione) {
		this.idAssociazioneSezione = idAssociazioneSezione;
	}
	public Long getIdProvNascita() {
		return idProvNascita;
	}
	public void setIdProvNascita(Long idProvNascita) {
		this.idProvNascita = idProvNascita;
	}
	public String getProvNascita() {
		return provNascita;
	}
	public void setProvNascita(String provNascita) {
		this.provNascita = provNascita;
	}
	public String getComuneNascita() {
		return comuneNascita;
	}
	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}
	public Long getIdProvResidenza() {
		return idProvResidenza;
	}
	public void setIdProvResidenza(Long idProvResidenza) {
		this.idProvResidenza = idProvResidenza;
	}
	public String getProvResidenza() {
		return provResidenza;
	}
	public void setProvResidenza(String provResidenza) {
		this.provResidenza = provResidenza;
	}
	public String getComuneResidenza() {
		return comuneResidenza;
	}
	public void setComuneResidenza(String comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}
	
	
	public Long getIdTipoFlusso() {
		return idTipoFlusso;
	}
	public void setIdTipoFlusso(Long idTipoFlusso) {
		this.idTipoFlusso = idTipoFlusso;
	}
	public Long getIdFlusso() {
		return idFlusso;
	}
	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}
	public String getDescTipoComSuccessiva() {
		return descTipoComSuccessiva;
	}
	public void setDescTipoComSuccessiva(String descTipoComSuccessiva) {
		this.descTipoComSuccessiva = descTipoComSuccessiva;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getIdComuneNascita() {
		return idComuneNascita;
	}
	public void setIdComuneNascita(Long idComuneNascita) {
		this.idComuneNascita = idComuneNascita;
	}
	public Long getIdComuneResidenza() {
		return idComuneResidenza;
	}
	public void setIdComuneResidenza(Long idComuneResidenza) {
		this.idComuneResidenza = idComuneResidenza;
	}
	public String getDenomComuneEstNascita() {
		return denomComuneEstNascita;
	}
	public void setDenomComuneEstNascita(String denomComuneEstNascita) {
		this.denomComuneEstNascita = denomComuneEstNascita;
	}
	public String getDenomComuneEstResid() {
		return denomComuneEstResid;
	}
	public void setDenomComuneEstResid(String denomComuneEstResid) {
		this.denomComuneEstResid = denomComuneEstResid;
	}
	public String getDescStatoDomanda() {
		return descStatoDomanda;
	}
	public void setDescStatoDomanda(String descStatoDomanda) {
		this.descStatoDomanda = descStatoDomanda;
	}
	public String getDescTipoDomanda() {
		return descTipoDomanda;
	}
	public void setDescTipoDomanda(String descTipoDomanda) {
		this.descTipoDomanda = descTipoDomanda;
	}
	public Long getIdTipoSpedizioniere() {
		return idTipoSpedizioniere;
	}
	public void setIdTipoSpedizioniere(Long idTipoSpedizioniere) {
		this.idTipoSpedizioniere = idTipoSpedizioniere;
	}
	public String getDenomTipoSpedizioniere() {
		return denomTipoSpedizioniere;
	}
	public void setDenomTipoSpedizioniere(String denomTipoSpedizioniere) {
		this.denomTipoSpedizioniere = denomTipoSpedizioniere;
	}
	public String getCuaa() {
		return cuaa;
	}
	public void setCuaa(String cuaa) {
		this.cuaa = cuaa;
	}
	public String getNomeSped() {
		return nomeSped;
	}
	public void setNomeSped(String nomeSped) {
		this.nomeSped = nomeSped;
	}
	public String getCognomeSped() {
		return cognomeSped;
	}
	public void setCognomeSped(String cognomeSped) {
		this.cognomeSped = cognomeSped;
	}
	public String getTelefonoSped() {
		return telefonoSped;
	}
	public void setTelefonoSped(String telefonoSped) {
		this.telefonoSped = telefonoSped;
	}
	public String getCellSped() {
		return cellSped;
	}
	public void setCellSped(String cellSped) {
		this.cellSped = cellSped;
	}
	public String getFaxSped() {
		return faxSped;
	}
	public void setFaxSped(String faxSped) {
		this.faxSped = faxSped;
	}
	public String getCapSped() {
		return capSped;
	}
	public void setCapSped(String capSped) {
		this.capSped = capSped;
	}
	public String getIndirizzoSped() {
		return indirizzoSped;
	}
	public void setIndirizzoSped(String indirizzoSped) {
		this.indirizzoSped = indirizzoSped;
	}
	public Long getIdComuneSped() {
		return idComuneSped;
	}
	public void setIdComuneSped(Long idComuneSped) {
		this.idComuneSped = idComuneSped;
	}
	public String getDenomComuneSped() {
		return denomComuneSped;
	}
	public void setDenomComuneSped(String denomComuneSped) {
		this.denomComuneSped = denomComuneSped;
	}
	public String getEmailSped() {
		return emailSped;
	}
	public void setEmailSped(String emailSped) {
		this.emailSped = emailSped;
	}
	public String getPecSped() {
		return pecSped;
	}
	public void setPecSped(String pecSped) {
		this.pecSped = pecSped;
	}
	public String[] getIdVoceChecked() {
		return idVoceChecked;
	}
	public void setIdVoceChecked(String[] idVoceChecked) {
		this.idVoceChecked = idVoceChecked;
	}
	public Long getIdProvinciaSped() {
		return idProvinciaSped;
	}
	public void setIdProvinciaSped(Long idProvinciaSped) {
		this.idProvinciaSped = idProvinciaSped;
	}
	public String getDenomProvinciaSped() {
		return denomProvinciaSped;
	}
	public void setDenomProvinciaSped(String denomProvinciaSped) {
		this.denomProvinciaSped = denomProvinciaSped;
	}
	public List<CentroAziendaleDomandaDTO> getCentriAziendaliList() {
		return centriAziendaliList;
	}
	public void setCentriAziendaliList(List<CentroAziendaleDomandaDTO> centriAziendaliList) {
		this.centriAziendaliList = centriAziendaliList;
	}
	public String[] getDescrVociAttivitaTipologia() {
		return descrVociAttivitaTipologia;
	}
	public void setDescrVociAttivitaTipologia(String[] descrVociAttivitaTipologia) {
		this.descrVociAttivitaTipologia = descrVociAttivitaTipologia;
	}
	public String[] getDescrVociTipologieDomande() {
		return descrVociTipologieDomande;
	}
	public void setDescrVociTipologieDomande(String[] descrVociTipologieDomande) {
		this.descrVociTipologieDomande = descrVociTipologieDomande;
	}
	public List<TipologiaAttMaterialeDTO> getTipAttivitaTipologia() {
		return tipAttivitaTipologia;
	}
	public void setTipAttivitaTipologia(List<TipologiaAttMaterialeDTO> tipAttivitaTipologia) {
		this.tipAttivitaTipologia = tipAttivitaTipologia;
	}
	public List<TipologiaProdSpecieDTO> getTipologieProdImport() {
		return tipologieProdImport;
	}
	public void setTipologieProdImport(List<TipologiaProdSpecieDTO> tipologieProdImport) {
		this.tipologieProdImport = tipologieProdImport;
	}
	public String[] getDescrVociZoneProtetteImport() {
		return descrVociZoneProtetteImport;
	}
	public void setDescrVociZoneProtetteImport(String[] descrVociZoneProtetteImport) {
		this.descrVociZoneProtetteImport = descrVociZoneProtetteImport;
	}
	public String getImpDatoAggiuntivo() {
		return impDatoAggiuntivo;
	}
	public void setImpDatoAggiuntivo(String impDatoAggiuntivo) {
		this.impDatoAggiuntivo = impDatoAggiuntivo;
	}
	public String[] getDescrVociContinentiImport() {
		return descrVociContinentiImport;
	}
	public void setDescrVociContinentiImport(String[] descrVociContinentiImport) {
		this.descrVociContinentiImport = descrVociContinentiImport;
	}
	public String getNoteImport() {
		return noteImport;
	}
	public void setNoteImport(String noteImport) {
		this.noteImport = noteImport;
	}
	public String getStatoOrigine() {
		return statoOrigine;
	}
	public void setStatoOrigine(String statoOrigine) {
		this.statoOrigine = statoOrigine;
	}
	public String[] getDescrVoceCheckContinentiExp() {
		return descrVoceCheckContinentiExp;
	}
	public void setDescrVoceCheckContinentiExp(String[] descrVoceCheckContinentiExp) {
		this.descrVoceCheckContinentiExp = descrVoceCheckContinentiExp;
	}
	public String getStatoOrigineExp() {
		return statoOrigineExp;
	}
	public void setStatoOrigineExp(String statoOrigineExp) {
		this.statoOrigineExp = statoOrigineExp;
	}
	public List<TipologiaProdSpecieDTO> getTipologieProdExp() {
		return tipologieProdExp;
	}
	public void setTipologieProdExp(List<TipologiaProdSpecieDTO> tipologieProdExp) {
		this.tipologieProdExp = tipologieProdExp;
	}
	public String[] getDescrVoceCheckExp() {
		return descrVoceCheckExp;
	}
	public void setDescrVoceCheckExp(String[] descrVoceCheckExp) {
		this.descrVoceCheckExp = descrVoceCheckExp;
	}
	public String getExpDatoAggiuntivo() {
		return expDatoAggiuntivo;
	}
	public void setExpDatoAggiuntivo(String expDatoAggiuntivo) {
		this.expDatoAggiuntivo = expDatoAggiuntivo;
	}
	public CarTResponsabilePassaporto getRespPassaporto() {
		return respPassaporto;
	}
	public void setRespPassaporto(CarTResponsabilePassaporto respPassaporto) {
		this.respPassaporto = respPassaporto;
	}
	public String[] getDescrVoceRadioCheckedPass() {
		return descrVoceRadioCheckedPass;
	}
	public void setDescrVoceRadioCheckedPass(String[] descrVoceRadioCheckedPass) {
		this.descrVoceRadioCheckedPass = descrVoceRadioCheckedPass;
	}
	public String[] getDescrVoceCheckedPass() {
		return descrVoceCheckedPass;
	}
	public void setDescrVoceCheckedPass(String[] descrVoceCheckedPass) {
		this.descrVoceCheckedPass = descrVoceCheckedPass;
	}
	public List<TipologiaProdSpecieDTO> getTipologieProdPass() {
		return tipologieProdPass;
	}
	public void setTipologieProdPass(List<TipologiaProdSpecieDTO> tipologieProdPass) {
		this.tipologieProdPass = tipologieProdPass;
	}
	public List<ZonaProtettaSpecieDTO> getZoneProtettePass() {
		return zoneProtettePass;
	}
	public void setZoneProtettePass(List<ZonaProtettaSpecieDTO> zoneProtettePass) {
		this.zoneProtettePass = zoneProtettePass;
	}
	public List<AllegatoDTO> getListaAllegati() {
		return listaAllegati;
	}
	public void setListaAllegati(List<AllegatoDTO> listaAllegati) {
		this.listaAllegati = listaAllegati;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getTipoSpedizioniereAltro() {
		return tipoSpedizioniereAltro;
	}
	public void setTipoSpedizioniereAltro(String tipoSpedizioniereAltro) {
		this.tipoSpedizioniereAltro = tipoSpedizioniereAltro;
	}			
	public String getCodiceRuop() {
		return codiceRuop;
	}
	public void setCodiceRuop(String codiceRuop) {
		this.codiceRuop = codiceRuop;
	}		
	public Date getDataRegistrazioneRuop() {
		return dataRegistrazioneRuop;
	}
	public void setDataRegistrazioneRuop(Date dataRegistrazioneRuop) {
		this.dataRegistrazioneRuop = dataRegistrazioneRuop;
	}		
	public String getDescIspettore() {
		return descIspettore;
	}
	public void setDescIspettore(String descIspettore) {
		this.descIspettore = descIspettore;
	}	
	
	
	public boolean isNuovaDomandaFlusso() {
		return getTipoDomSuccessiva() != null && getStatoDomSuccessiva() != null;
	}
	public Long getTipoDomSuccessiva() {
		return tipoDomSuccessiva;
	}
	public void setTipoDomSuccessiva(Long tipoDomSuccessiva) {
		this.tipoDomSuccessiva = tipoDomSuccessiva;
	}
	public String getDescTipoDomSuccessiva() {
		return descTipoDomSuccessiva;
	}
	public void setDescTipoDomSuccessiva(String descTipoDomSuccessiva) {
		this.descTipoDomSuccessiva = descTipoDomSuccessiva;
	}
	public Long getStatoDomSuccessiva() {
		return statoDomSuccessiva;
	}
	public void setStatoDomSuccessiva(Long statoDomSuccessiva) {
		this.statoDomSuccessiva = statoDomSuccessiva;
	}
	public Long getNumDomandeFlusso() {
		return numDomandeFlusso;
	}
	public void setNumDomandeFlusso(Long numDomandeFlusso) {
		this.numDomandeFlusso = numDomandeFlusso;
	}
	public Boolean getFlagModDomanda() {
		return flagModDomanda;
	}
	public void setFlagModDomanda(Boolean flagModDomanda) {
		this.flagModDomanda = flagModDomanda;
	}
	public Long getProgressivo() {
		return progressivo;
	}
	public void setProgressivo(Long progressivo) {
		this.progressivo = progressivo;
	}
	public Long getIdTipoStampa() {
		return idTipoStampa;
	}
	public void setIdTipoStampa(Long idTipoStampa) {
		this.idTipoStampa = idTipoStampa;
	}
	
	public Long getIdIspettore() {
		return idIspettore;
	}
	public void setIdIspettore(Long idIspettore) {
		this.idIspettore = idIspettore;
	}
	
	public Long getIdNazioneEstNascita() {
		return idNazioneEstNascita;
	}
	public void setIdNazioneEstNascita(Long idNazioneEstNascita) {
		this.idNazioneEstNascita = idNazioneEstNascita;
	}
	public Long getIdNazioneEstResid() {
		return idNazioneEstResid;
	}
	public void setIdNazioneEstResid(Long idNazioneEstResid) {
		this.idNazioneEstResid = idNazioneEstResid;
	}
	public String getNazioneEstNascita() {
		return nazioneEstNascita;
	}
	public void setNazioneEstNascita(String nazioneEstNascita) {
		this.nazioneEstNascita = nazioneEstNascita;
	}
	public String getNazioneEstResid() {
		return nazioneEstResid;
	}
	public void setNazioneEstResid(String nazioneEstResid) {
		this.nazioneEstResid = nazioneEstResid;
	}
	public String[] getDescrVoceTipoRichiesta() {
		return descrVoceTipoRichiesta;
	}
	public void setDescrVoceTipoRichiesta(String[] descrVoceTipoRichiesta) {
		this.descrVoceTipoRichiesta = descrVoceTipoRichiesta;
	}
	public String getVoceDichiaraConoscenze() {
		return voceDichiaraConoscenze;
	}
	public void setVoceDichiaraConoscenze(String voceDichiaraConoscenze) {
		this.voceDichiaraConoscenze = voceDichiaraConoscenze;
	}
	public String getVoceDichiaraDisporreSistemi() {
		return voceDichiaraDisporreSistemi;
	}
	public void setVoceDichiaraDisporreSistemi(String voceDichiaraDisporreSistemi) {
		this.voceDichiaraDisporreSistemi = voceDichiaraDisporreSistemi;
	}
	public String getVocePianoRischi() {
		return vocePianoRischi;
	}
	public void setVocePianoRischi(String vocePianoRischi) {
		this.vocePianoRischi = vocePianoRischi;
	}
	public String getVocePianoRischiUtente() {
		return vocePianoRischiUtente;
	}
	public void setVocePianoRischiUtente(String vocePianoRischiUtente) {
		this.vocePianoRischiUtente = vocePianoRischiUtente;
	}
	public String getVoceDichiaraConoscenzeUtente() {
		return voceDichiaraConoscenzeUtente;
	}
	public void setVoceDichiaraConoscenzeUtente(String voceDichiaraConoscenzeUtente) {
		this.voceDichiaraConoscenzeUtente = voceDichiaraConoscenzeUtente;
	}
	public String getVoceDichiaraDisporreSistemiUtente() {
		return voceDichiaraDisporreSistemiUtente;
	}
	public void setVoceDichiaraDisporreSistemiUtente(String voceDichiaraDisporreSistemiUtente) {
		this.voceDichiaraDisporreSistemiUtente = voceDichiaraDisporreSistemiUtente;
	}
	/**
	 * @return the maxIdDomandaFlusso
	 */
	public Long getMaxIdDomandaFlusso() {
		return maxIdDomandaFlusso;
	}
	/**
	 * @param maxIdDomandaFlusso the maxIdDomandaFlusso to set
	 */
	public void setMaxIdDomandaFlusso(Long maxIdDomandaFlusso) {
		this.maxIdDomandaFlusso = maxIdDomandaFlusso;
	}
	public String getTipologiaPassaporto() {
		return tipologiaPassaporto;
	}
	public void setTipologiaPassaporto(String tipologiaPassaporto) {
		this.tipologiaPassaporto = tipologiaPassaporto;
	}
	public Date getDataAutorizzazionePassaporto() {
		return dataAutorizzazionePassaporto;
	}
	public void setDataAutorizzazionePassaporto(Date dataAutorizzazionePassaporto) {
		this.dataAutorizzazionePassaporto = dataAutorizzazionePassaporto;
	}
	public Long getIdTipologiaPassaporto() {
		return idTipologiaPassaporto;
	}
	public void setIdTipologiaPassaporto(Long idTipologiaPassaporto) {
		this.idTipologiaPassaporto = idTipologiaPassaporto;
	}


}
