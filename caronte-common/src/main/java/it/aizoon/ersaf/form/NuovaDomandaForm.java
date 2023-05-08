package it.aizoon.ersaf.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.dto.CentroAziendaleDomandaDTO;
import it.aizoon.ersaf.dto.TipologiaProdSpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDTipoAttivita;
import it.aizoon.ersaf.dto.generati.CarDTipologia;
import it.aizoon.ersaf.dto.generati.CarDVoce;
import it.aizoon.ersaf.dto.generati.CarTSitoProduzione;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.util.CaronteUtils;


public class NuovaDomandaForm extends BaseForm {

	private Long idDomanda;
	private Long idDomandaPrecedente;
	private Long idTipoComunicazione;
	private Long idStatoComunicazione;
	private String descStatoComunicazione;
	
	@NumberFormat(style = Style.NUMBER)
	private Long idSpedizioniere;


	//FLAG ABILITAZIONE INOLTRA
	private boolean abilitaInoltra;
	private boolean abilitaAnnulla;
	private boolean abilitaRespingi;
	private boolean abilitaConvalida;

	private String descTipoDomanda;

	// *** DATI ANAGRAFICI ***
	@NumberFormat(style = Style.NUMBER)
	private Long idUtente;
	private String cognome;	
	private String nome;
	private String codFiscale;

	// Dati nascita
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascita;
	@NumberFormat(style = Style.NUMBER)
	private Long idProvinciaNascita;
	@NumberFormat(style = Style.NUMBER)
	private Long idComuneNascita;

	// Dati residenza
	@NumberFormat(style = Style.NUMBER)
	private Long idComuneResidenza;
	@NumberFormat(style = Style.NUMBER)
	private Long idProvinciaResidenza;	
	private String indirizzo;	
	private String cap;
	private String numTelefono;
	private String cellulare;
	private String email;

	// Dati estero
	private boolean nascitaEstera;
	private String denomComuneEstNascita;
	private boolean residenzaEstera;
	private String denomComuneEstResid;
	private Long idNazioneEstNascita;
	private Long idNazioneEstResid;
	
	// *** DATI AZIENDA ***
	private Long idAzienda;
	private Long idTipoAzienda;
	private String denomAzienda;
	private String nomeAzienda;
	private String cognomeAzienda;
	private String codFiscaleAz;
	private String partitaIva;
	private String tipoSpedizioniereAltro;
	
	// Sede legale
	private Long idComuneSedeLegale;
	private String descrComuneSedeLegale;
	private String capSedeLegale;
	private Long idProvinciaSedeLegale;
	private String siglaProvSedeLegale;
	private String indirizzoSedeLegale;	
	private String numTelefonoSedeLegale;
	private String numCellulareSedeLegale;
	private String faxSedeLegale;	
	private String emailSedeLegale;
	private String pecSedeLegale;
	
	
	// ********* TIPOLOGIA ********
	private List<CarDTipologia> elencoTipologie;
	private String[] idTipologia;
	private boolean tabImport;
	private boolean tabExport;
	private boolean tabPassaporto;
	private List<CarDVoce> vociCheckboxTipologia;
	private Long idTipologiaAtt;	
	private String[] idMateriale;
	private String[] idVoceCheckTip;	
	private boolean richiestaPassaporto;
	
	// ******** CENTRI AZIENDALI ********
	// -- Dati centro aziendale
	private Long numCentriAz;
	private Long idCentroAziendale;
	private String idCentroAzNoSedeLeg;
	private String codCentroAz;
	private String denominazCentroAz;
	private Long idComuneCentroAz;
	private Long idProvCentroAz;
	private String capCentroAz;
	private String frazioneCentroAz;
	private String indirizzoCentroAz;
	private String cellulareCentroAz;
	private String telefonoCentroAz;
	private String mailCentroAz;
	private String pecCentroAz;
	private String noteTipologiaCentroAz;
	private List<CentroAziendaleDomandaDTO> listaCentriAz;
	
	private List<CentroAziendaleDomandaDTO> centriAziendaliList;
	public Long idCentroAziendaleSel; // radio button tabella centri azioendali nel Tab Operatore
	public Long idCentroAziendaleGestSel; // radio button tabella centri aziendale nel Tab Gestione
	
	// -- Dati Tipologia produttiva
	private Long idTipologiaProd;
	private String[] idSpecie;
	private String[] specie;
	private String[] idTipologiaProduttiva;
	private List<TipologiaProdSpecieDTO> tipologieList;
	private String genere;
	private String idVoceUtenteElim;
	private String idSpecieElim;
	private Long idGenere;
	
	// -- Dati Siti di produzione	  	  
	private String descSitoProduzione;
	private String ubicazione;
	@NumberFormat(style = Style.NUMBER)
	private Long provinciaSitoProd;
	@NumberFormat(style = Style.NUMBER)
	private Long comuneSitoProd;
	@NumberFormat(style = Style.NUMBER)
	private String foglio;
	@NumberFormat(style = Style.NUMBER)
	private String mappale;
	@NumberFormat(style = Style.NUMBER)
	private String superficie;	
	
	private List<CarTSitoProduzione> sitiProduzioneList;
	
	private String chiamata;
	
	
	
	// Elenco di spedizionieri legati all'utente
	private List<CarTSpedizioniere> spedizionieriList;
	
	// ******** PASSAPORTO ********
    // -- Dati PASSAPORTO
	List<CarDVoce> vociRadioTipoRichiesta;
    List<CarDVoce> vociRadioPassaporto;
	List<CarDVoce> vociCheckboxPassaporto;
	private String[] idVoceRadio;
	private String[] idVoceCheck;
	private Long idZonaProtetta;
	private String[] idVoce;
	private String[] idVoceRadioTipoRichiesta;
	
	private String[] idSpecieTpProd; 	
	private String[] idSpecieZP;
	private String[] idGruppoZonaProtetta;
	private String[] idGenereZP;
	private String[] genereZP;
	private String[] specieZP;
	private Long idGenerePass;
	private Long idTipologiaPassaporto;
	
	private Long idTipologiaAttTip;
	
	private String checkRespFito;
	private String cognomeRespPass;
	private String nomeRespPass;
	private String codFiscaleRespPass;
	private String numTelefonoRespPass;
	private String cellulareRespPass;
	private String emailRespPass;
	private String qualificaProfRespPass;
	
	private Long idVoceRadioChecked;
	
	private String voceDichiaraConoscenze;
	private String voceDichiaraDisporreSistemi;	
	private String vocePianoRischi;
	private Long idCentroAziendalePassaporto;
	
	
	// -- Tab Import
	private Long idTipologiaProdImport;
	private String[] idSpecieImport;
	private String[] specieImport;
	private List<TipologiaProdSpecieDTO> tipologieImportList;
	private String genereImport;
	private Long idGenereImport;
	private String noteTipologiaImport;
	private String statoOrigine;
	private String impDatoAggiuntivo;
	private String noteImport;
	private String[] idVoceRadioZonaProtetta;
	private String[] idVoceCheckContinenti;
	
	
	// -- Tab Export
	private String[] idVoceCheckTipExp;
	private String expDatoAggiuntivo;
	
	private String[] idVoceCheckContinentiExp;
	private String statoOrigineExp;
	
	private Long idTipologiaProdExp;
	private Long idGenereExport;
	private String noteTipologiaExport;
	private String genereExport;
	private String[] specieExport;

	// -- Tab Gestione
	private BigDecimal tariffa;
	private String codiceFitok;
	private String note;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataRegistrazioneRuop;
	private String numeroProtocollo;
	private Date dataProtocollo;
	private String codiceRuop;
	private Long idIspettore;
	private String tipologiaPassaporto;
	private Date dataAutorizzazionePassaporto;
	
	// Domanda successiva flusso
	private String descrBreveDomandaSuccessiva;
	// Flag abilitazione creazione nuova domanda flusso
	private boolean abilitaCreaNuovaDomandaFlusso;
	
	// Gestione Domanda Passaporto
	private boolean domandaRuopConvalidataPresente;	
	
	// Gestione versione Domanda (versione di layout)
	private Long idVersioneDomanda;
	






	public Long getIdTipoComunicazione() {
		return idTipoComunicazione;
	}

	public void setIdTipoComunicazione(Long idTipoComunicazione) {
		this.idTipoComunicazione = idTipoComunicazione;
	}

	public Long getIdStatoComunicazione() {
		return idStatoComunicazione;
	}

	public void setIdStatoComunicazione(Long idStatoComunicazione) {
		this.idStatoComunicazione = idStatoComunicazione;
	}

	public Long getIdSpedizioniere() {
		return idSpedizioniere;
	}

	public void setIdSpedizioniere(Long idSpedizioniere) {
		this.idSpedizioniere = idSpedizioniere;
	}



	public boolean isAbilitaInoltra() {
		return abilitaInoltra;
	}

	public void setAbilitaInoltra(boolean abilitaInoltra) {
		this.abilitaInoltra = abilitaInoltra;
	}



	/*public void setDatiComunicazione(ComunicazioneDto comunicazione) {
		if (comunicazione != null) {
			setIdComunicazione(comunicazione.getIdComunicazione());
			setIdTipoComunicazione(comunicazione.getIdTipoComunicazione());
			setIdStatoComunicazione(comunicazione.getIdStatoComunicazione());
			setIdSpedizioniere(comunicazione.getIdSpedizioniere());
			setIdCentroAziendale(comunicazione.getCentroAziendale().getIdCentroAziendale());
			setListaGeneri(comunicazione.getListaGeneri());
		}
	}*/


	public boolean isAbilitaAnnulla() {
		return abilitaAnnulla;
	}

	public void setAbilitaAnnulla(boolean abilitaAnnulla) {
		this.abilitaAnnulla = abilitaAnnulla;
	}

	public boolean isAbilitaRespingi() {
		return abilitaRespingi;
	}

	public void setAbilitaRespingi(boolean abilitaRespingi) {
		this.abilitaRespingi = abilitaRespingi;
	}

	public boolean isAbilitaConvalida() {
		return abilitaConvalida;
	}

	public void setAbilitaConvalida(boolean abilitaConvalida) {
		this.abilitaConvalida = abilitaConvalida;
	}

	public Long getIdDomanda() {
		return idDomanda;
	}

	public void setIdDomanda(Long idDomanda) {
		this.idDomanda = idDomanda;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public String getDescTipoDomanda() {
		return descTipoDomanda;
	}

	public void setDescTipoDomanda(String descTipoDomanda) {
		this.descTipoDomanda = CaronteUtils.convertToUpperCase(descTipoDomanda);
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = CaronteUtils.convertToUpperCase(cognome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaronteUtils.convertToUpperCase(nome);
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(String codFiscale) {
		this.codFiscale = CaronteUtils.convertToUpperCase(codFiscale);
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Long getIdProvinciaNascita() {
		return idProvinciaNascita;
	}

	public void setIdProvinciaNascita(Long idProvinciaNascita) {
		this.idProvinciaNascita = idProvinciaNascita;
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

	public Long getIdProvinciaResidenza() {
		return idProvinciaResidenza;
	}

	public void setIdProvinciaResidenza(Long idProvinciaResidenza) {
		this.idProvinciaResidenza = idProvinciaResidenza;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = CaronteUtils.convertToUpperCase(indirizzo);
	}	

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = CaronteUtils.convertToUpperCase(cap);
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = CaronteUtils.convertToUpperCase(numTelefono);
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
		this.email = CaronteUtils.convertToUpperCase(email);
	}

	public boolean isNascitaEstera() {
		return nascitaEstera;
	}

	public void setNascitaEstera(boolean nascitaEstera) {
		this.nascitaEstera = nascitaEstera;
	}

	public String getDenomComuneEstNascita() {
		return denomComuneEstNascita;
	}

	public void setDenomComuneEstNascita(String denomComuneEstNascita) {
		this.denomComuneEstNascita = CaronteUtils.convertToUpperCase(denomComuneEstNascita);
	}

	public boolean isResidenzaEstera() {
		return residenzaEstera;
	}

	public void setResidenzaEstera(boolean residenzaEstera) {
		this.residenzaEstera = residenzaEstera;
	}

	public String getDenomComuneEstResid() {
		return denomComuneEstResid;
	}

	public void setDenomComuneEstResid(String denomComuneEstResid) {
		this.denomComuneEstResid = CaronteUtils.convertToUpperCase(denomComuneEstResid);
	}

	public Long getIdDomandaPrecedente() {
		return idDomandaPrecedente;
	}

	public void setIdDomandaPrecedente(Long idDomandaPrecedente) {
		this.idDomandaPrecedente = idDomandaPrecedente;
	}


	public String getDenomAzienda() {
		return denomAzienda;
	}

	public void setDenomAzienda(String denomAzienda) {
		this.denomAzienda = CaronteUtils.convertToUpperCase(denomAzienda);
	}

	public Long getIdComuneSedeLegale() {
		return idComuneSedeLegale;
	}

	public void setIdComuneSedeLegale(Long idComuneSedeLegale) {
		this.idComuneSedeLegale = idComuneSedeLegale;
	}

	public String getDescrComuneSedeLegale() {
		return descrComuneSedeLegale;
	}

	public void setDescrComuneSedeLegale(String descrComuneSedeLegale) {
		this.descrComuneSedeLegale = CaronteUtils.convertToUpperCase(descrComuneSedeLegale);
	}

	public String getCapSedeLegale() {
		return capSedeLegale;
	}

	public void setCapSedeLegale(String capSedeLegale) {
		this.capSedeLegale = CaronteUtils.convertToUpperCase(capSedeLegale);
	}	

	public String getSiglaProvSedeLegale() {
		return siglaProvSedeLegale;
	}

	public void setSiglaProvSedeLegale(String siglaProvSedeLegale) {
		this.siglaProvSedeLegale = CaronteUtils.convertToUpperCase(siglaProvSedeLegale);
	}

	public String getIndirizzoSedeLegale() {
		return indirizzoSedeLegale;
	}

	public void setIndirizzoSedeLegale(String indirizzoSedeLegale) {
		this.indirizzoSedeLegale = CaronteUtils.convertToUpperCase(indirizzoSedeLegale);
	}

	public String getCodFiscaleAz() {
		return codFiscaleAz;
	}

	public void setCodFiscaleAz(String codFiscaleAz) {
		this.codFiscaleAz = CaronteUtils.convertToUpperCase(codFiscaleAz);
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = CaronteUtils.convertToUpperCase(partitaIva);
	}

	public List<CarTSpedizioniere> getSpedizionieriList() {
		return spedizionieriList;
	}

	public void setSpedizionieriList(List<CarTSpedizioniere> spedizionieriList) {
		this.spedizionieriList = spedizionieriList;
	}

	public String getNumTelefonoSedeLegale() {
		return numTelefonoSedeLegale;
	}

	public void setNumTelefonoSedeLegale(String numTelefonoSedeLegale) {
		this.numTelefonoSedeLegale = numTelefonoSedeLegale;
	}

	public String getNumCellulareSedeLegale() {
		return numCellulareSedeLegale;
	}

	public void setNumCellulareSedeLegale(String numCellulareSedeLegale) {
		this.numCellulareSedeLegale = numCellulareSedeLegale;
	}

	public String getFaxSedeLegale() {
		return faxSedeLegale;
	}

	public void setFaxSedeLegale(String faxSedeLegale) {
		this.faxSedeLegale = faxSedeLegale;
	}

	public String getEmailSedeLegale() {
		return emailSedeLegale;
	}

	public void setEmailSedeLegale(String emailSedeLegale) {
		this.emailSedeLegale = CaronteUtils.convertToUpperCase(emailSedeLegale);
	}

	public String getPecSedeLegale() {
		return pecSedeLegale;
	}

	public void setPecSedeLegale(String pecSedeLegale) {
		this.pecSedeLegale = CaronteUtils.convertToUpperCase(pecSedeLegale);
	}

	public Long getIdAzienda() {
		return idAzienda;
	}

	public void setIdAzienda(Long idAzienda) {
		this.idAzienda = idAzienda;
	}

	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = CaronteUtils.convertToUpperCase(nomeAzienda);
	}

	public String getCognomeAzienda() {
		return cognomeAzienda;
	}

	public void setCognomeAzienda(String cognomeAzienda) {
		this.cognomeAzienda = CaronteUtils.convertToUpperCase(cognomeAzienda);
	}

	public Long getIdProvinciaSedeLegale() {
		return idProvinciaSedeLegale;
	}

	public void setIdProvinciaSedeLegale(Long idProvinciaSedeLegale) {
		this.idProvinciaSedeLegale = idProvinciaSedeLegale;
	}

	public Long getIdTipoAzienda() {
		return idTipoAzienda;
	}

	public void setIdTipoAzienda(Long idTipoAzienda) {
		this.idTipoAzienda = idTipoAzienda;
	}

	public List<CarDTipologia> getElencoTipologie() {
		return elencoTipologie;
	}

	public void setElencoTipologie(List<CarDTipologia> elencoTipologie) {
		this.elencoTipologie = elencoTipologie;
	}

	public String[] getIdTipologia() {
		return idTipologia;
	}

	public void setIdTipologia(String[] idTipologia) {
		this.idTipologia = idTipologia;
	}

	public Long getNumCentriAz() {
		return numCentriAz;
	}

	public void setNumCentriAz(Long numCentriAz) {
		this.numCentriAz = numCentriAz;
	}

	public Long getIdCentroAziendale() {
		return idCentroAziendale;
	}

	public void setIdCentroAziendale(Long idCentroAziendale) {
		this.idCentroAziendale = idCentroAziendale;
	}

	public String getIdCentroAzNoSedeLeg() {
		return idCentroAzNoSedeLeg;
	}

	public void setIdCentroAzNoSedeLeg(String idCentroAzNoSedeLeg) {
		this.idCentroAzNoSedeLeg = idCentroAzNoSedeLeg;
	}

	public String getCodCentroAz() {
		return codCentroAz;
	}

	public void setCodCentroAz(String codCentroAz) {
		this.codCentroAz = codCentroAz;
	}

	public Long getIdComuneCentroAz() {
		return idComuneCentroAz;
	}

	public void setIdComuneCentroAz(Long idComuneCentroAz) {
		this.idComuneCentroAz = idComuneCentroAz;
	}

	public Long getIdProvCentroAz() {
		return idProvCentroAz;
	}

	public void setIdProvCentroAz(Long idProvCentroAz) {
		this.idProvCentroAz = idProvCentroAz;
	}

	public String getCapCentroAz() {
		return capCentroAz;
	}

	public void setCapCentroAz(String capCentroAz) {
		this.capCentroAz = capCentroAz;
	}

	public String getFrazioneCentroAz() {
		return frazioneCentroAz;
	}

	public void setFrazioneCentroAz(String frazioneCentroAz) {
		this.frazioneCentroAz = CaronteUtils.convertToUpperCase(frazioneCentroAz);
	}

	public String getIndirizzoCentroAz() {
		return indirizzoCentroAz;
	}

	public void setIndirizzoCentroAz(String indirizzoCentroAz) {
		this.indirizzoCentroAz = CaronteUtils.convertToUpperCase(indirizzoCentroAz);
	}

	public String getCellulareCentroAz() {
		return cellulareCentroAz;
	}

	public void setCellulareCentroAz(String cellulareCentroAz) {
		this.cellulareCentroAz = cellulareCentroAz;
	}

	public String getTelefonoCentroAz() {
		return telefonoCentroAz;
	}

	public void setTelefonoCentroAz(String telefonoCentroAz) {
		this.telefonoCentroAz = telefonoCentroAz;
	}

	public String getMailCentroAz() {
		return mailCentroAz;
	}

	public void setMailCentroAz(String mailCentroAz) {
		this.mailCentroAz = CaronteUtils.convertToUpperCase(mailCentroAz);
	}

	public String getPecCentroAz() {
		return pecCentroAz;
	}

	public void setPecCentroAz(String pecCentroAz) {
		this.pecCentroAz = CaronteUtils.convertToUpperCase(pecCentroAz);
	}

	public String getDenominazCentroAz() {
		return denominazCentroAz;
	}

	public void setDenominazCentroAz(String denominazCentroAz) {
		this.denominazCentroAz = CaronteUtils.convertToUpperCase(denominazCentroAz);
	}

	public Long getIdTipologiaProd() {
		return idTipologiaProd;
	}

	public void setIdTipologiaProd(Long idTipologiaProd) {
		this.idTipologiaProd = idTipologiaProd;
	}

	public String[] getIdSpecie() {
		return idSpecie;
	}

	public void setIdSpecie(String[] idSpecie) {
		this.idSpecie = idSpecie;
	}

	public String[] getIdTipologiaProduttiva() {
		return idTipologiaProduttiva;
	}

	public void setIdTipologiaProduttiva(String[] idTipologiaProduttiva) {
		this.idTipologiaProduttiva = idTipologiaProduttiva;
	}	

	public List<CarDVoce> getVociRadioPassaporto() {
		return vociRadioPassaporto;
	}

	public void setVociRadioPassaporto(List<CarDVoce> vociRadioPassaporto) {
		this.vociRadioPassaporto = vociRadioPassaporto;
	}

	public List<CarDVoce> getVociCheckboxPassaporto() {
		return vociCheckboxPassaporto;
	}

	public void setVociCheckboxPassaporto(List<CarDVoce> vociCheckboxPassaporto) {
		this.vociCheckboxPassaporto = vociCheckboxPassaporto;
	}

	public String[] getIdVoceRadio() {
		return idVoceRadio;
	}

	public void setIdVoceRadio(String[] idVoceRadio) {
		this.idVoceRadio = idVoceRadio;
	}

	public String[] getIdVoceCheck() {
		return idVoceCheck;
	}

	public void setIdVoceCheck(String[] idVoceCheck) {
		this.idVoceCheck = idVoceCheck;
	}

	public String[] getSpecie() {
		return specie;
	}

	public void setSpecie(String[] specie) {
		this.specie = specie;
	}

	public List<TipologiaProdSpecieDTO> getTipologieList() {
		return tipologieList;
	}

	public void setTipologieList(List<TipologiaProdSpecieDTO> tipologieList) {
		this.tipologieList = tipologieList;
	}

	public List<CentroAziendaleDomandaDTO> getCentriAziendaliList() {
		return centriAziendaliList;
	}

	public void setCentriAziendaliList(List<CentroAziendaleDomandaDTO> centriAziendaliList) {
		this.centriAziendaliList = centriAziendaliList;
	}

	public List<CarTSitoProduzione> getSitiProduzioneList() {
		return sitiProduzioneList;
	}

	public void setSitiProduzioneList(List<CarTSitoProduzione> sitiProduzioneList) {
		this.sitiProduzioneList = sitiProduzioneList;
	}

	public String getChiamata() {
		return chiamata;
	}

	public void setChiamata(String chiamata) {
		this.chiamata = chiamata;
	}

	public Long getIdCentroAziendaleSel() {
		return idCentroAziendaleSel;
	}

	public void setIdCentroAziendaleSel(Long idCentroAziendaleSel) {
		this.idCentroAziendaleSel = idCentroAziendaleSel;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = CaronteUtils.convertToUpperCase(genere);
	}

	public String getDescSitoProduzione() {
		return descSitoProduzione;
	}

	public void setDescSitoProduzione(String descSitoProduzione) {
		this.descSitoProduzione = CaronteUtils.convertToUpperCase(descSitoProduzione);
	}

	public String getUbicazione() {
		return ubicazione;
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = CaronteUtils.convertToUpperCase(ubicazione);
	}

	public Long getProvinciaSitoProd() {
		return provinciaSitoProd;
	}

	public void setProvinciaSitoProd(Long provinciaSitoProd) {
		this.provinciaSitoProd = provinciaSitoProd;
	}

	public Long getComuneSitoProd() {
		return comuneSitoProd;
	}

	public void setComuneSitoProd(Long comuneSitoProd) {
		this.comuneSitoProd = comuneSitoProd;
	}

	public String getFoglio() {
		return foglio;
	}

	public void setFoglio(String foglio) {
		this.foglio = CaronteUtils.convertToUpperCase(foglio);
	}

	public String getMappale() {
		return mappale;
	}

	public void setMappale(String mappale) {
		this.mappale = CaronteUtils.convertToUpperCase(mappale);
	}

	public String getSuperficie() {
		return superficie;
	}

	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}

	public Long getIdZonaProtetta() {
		return idZonaProtetta;
	}

	public void setIdZonaProtetta(Long idZonaProtetta) {
		this.idZonaProtetta = idZonaProtetta;
	}

	public String[] getIdVoce() {
		return idVoce;
	}

	public void setIdVoce(String[] idVoce) {
		this.idVoce = idVoce;
	}

	public String[] getIdSpecieTpProd() {
		return idSpecieTpProd;
	}

	public void setIdSpecieTpProd(String[] idSpecieTpProd) {
		this.idSpecieTpProd = idSpecieTpProd;
	}

	public String[] getIdSpecieZP() {
		return idSpecieZP;
	}

	public void setIdSpecieZP(String[] idSpecieZP) {
		this.idSpecieZP = idSpecieZP;
	}

	public String[] getIdGruppoZonaProtetta() {
		return idGruppoZonaProtetta;
	}

	public void setIdGruppoZonaProtetta(String[] idGruppoZonaProtetta) {
		this.idGruppoZonaProtetta = idGruppoZonaProtetta;
	}

	public String[] getIdGenereZP() {
		return idGenereZP;
	}

	public void setIdGenereZP(String[] idGenereZP) {
		this.idGenereZP = idGenereZP;
	}
	public String[] getGenereZP() {
		return genereZP;
	}

	public void setGenereZP(String[] genereZP) {
		this.genereZP = genereZP;
	}

	public String[] getSpecieZP() {
		return specieZP;
	}

	public void setSpecieZP(String[] specieZP) {
		this.specieZP = specieZP;
	}
	public String getIdVoceUtenteElim() {
		return idVoceUtenteElim;
	}

	public void setIdVoceUtenteElim(String idVoceUtenteElim) {
		this.idVoceUtenteElim = idVoceUtenteElim;
	}

	public String getIdSpecieElim() {
		return idSpecieElim;
	}

	public void setIdSpecieElim(String idSpecieElim) {
		this.idSpecieElim = idSpecieElim;
	}

	public boolean isTabImport() {
		return tabImport;
	}

	public void setTabImport(boolean tabImport) {
		this.tabImport = tabImport;
	}

	public boolean isTabExport() {
		return tabExport;
	}

	public void setTabExport(boolean tabExport) {
		this.tabExport = tabExport;
	}

	public boolean isTabPassaporto() {
		return tabPassaporto;
	}

	public void setTabPassaporto(boolean tabPassaporto) {
		this.tabPassaporto = tabPassaporto;
	}	

	public List<CarDVoce> getVociCheckboxTipologia() {
		return vociCheckboxTipologia;
	}

	public void setVociCheckboxTipologia(List<CarDVoce> vociCheckboxTipologia) {
		this.vociCheckboxTipologia = vociCheckboxTipologia;
	}
	
	
	public Long getIdTipologiaProdImport() {
		return idTipologiaProdImport;
	}

	public void setIdTipologiaProdImport(Long idTipologiaProdImport) {
		this.idTipologiaProdImport = idTipologiaProdImport;
	}

	public String[] getIdSpecieImport() {
		return idSpecieImport;
	}

	public void setIdSpecieImport(String[] idSpecieImport) {
		this.idSpecieImport = idSpecieImport;
	}

	public String[] getSpecieImport() {
		return specieImport;
	}

	public void setSpecieImport(String[] specieImport) {
		this.specieImport = specieImport;
	}

	public List<TipologiaProdSpecieDTO> getTipologieImportList() {
		return tipologieImportList;
	}

	public void setTipologieImportList(List<TipologiaProdSpecieDTO> tipologieImportList) {
		this.tipologieImportList = tipologieImportList;
	}

	public String getGenereImport() {
		return genereImport;
	}

	public void setGenereImport(String genereImport) {
		this.genereImport = CaronteUtils.convertToUpperCase(genereImport);
	}

	public String getStatoOrigine() {
		return statoOrigine;
	}

	public void setStatoOrigine(String statoOrigine) {
		this.statoOrigine = CaronteUtils.convertToUpperCase(statoOrigine);
	}

	public String getImpDatoAggiuntivo() {
		return impDatoAggiuntivo;
	}

	public void setImpDatoAggiuntivo(String impDatoAggiuntivo) {
		this.impDatoAggiuntivo = CaronteUtils.convertToUpperCase(impDatoAggiuntivo);
	}

	public String getNoteImport() {
		return noteImport;
	}

	public void setNoteImport(String noteImport) {
		this.noteImport = CaronteUtils.convertToUpperCase(noteImport);
	}

	public String[] getIdVoceRadioZonaProtetta() {
		return idVoceRadioZonaProtetta;
	}

	public void setIdVoceRadioZonaProtetta(String[] idVoceRadioZonaProtetta) {
		this.idVoceRadioZonaProtetta = idVoceRadioZonaProtetta;
	}

	public String[] getIdVoceCheckContinenti() {
		return idVoceCheckContinenti;
	}

	public void setIdVoceCheckContinenti(String[] idVoceCheckContinenti) {
		this.idVoceCheckContinenti = idVoceCheckContinenti;
	}

	public Long getIdTipologiaAttTip() {
		return idTipologiaAttTip;
	}

	public void setIdTipologiaAttTip(Long idTipologiaAttTip) {
		this.idTipologiaAttTip = idTipologiaAttTip;
	}

	public Long getIdTipologiaAtt() {
		return idTipologiaAtt;
	}

	public void setIdTipologiaAtt(Long idTipologiaAtt) {
		this.idTipologiaAtt = idTipologiaAtt;
	}

	public String[] getIdMateriale() {
		return idMateriale;
	}

	public void setIdMateriale(String[] idMateriale) {
		this.idMateriale = idMateriale;
	}

	public String[] getIdVoceCheckTip() {
		return idVoceCheckTip;
	}

	public void setIdVoceCheckTip(String[] idVoceCheckTip) {
		this.idVoceCheckTip = idVoceCheckTip;
	}

	public String getCognomeRespPass() {
		return cognomeRespPass;
	}

	public void setCognomeRespPass(String cognomeRespPass) {
		this.cognomeRespPass = CaronteUtils.convertToUpperCase(cognomeRespPass);
	}

	public String getNomeRespPass() {
		return nomeRespPass;
	}

	public void setNomeRespPass(String nomeRespPass) {
		this.nomeRespPass = CaronteUtils.convertToUpperCase(nomeRespPass);
	}

	public String getCodFiscaleRespPass() {
		return codFiscaleRespPass;
	}

	public void setCodFiscaleRespPass(String codFiscaleRespPass) {
		this.codFiscaleRespPass = CaronteUtils.convertToUpperCase(codFiscaleRespPass);
	}

	public String getNumTelefonoRespPass() {
		return numTelefonoRespPass;
	}

	public void setNumTelefonoRespPass(String numTelefonoRespPass) {
		this.numTelefonoRespPass = numTelefonoRespPass;
	}

	public String getCellulareRespPass() {
		return cellulareRespPass;
	}

	public void setCellulareRespPass(String cellulareRespPass) {
		this.cellulareRespPass = cellulareRespPass;
	}

	public String getEmailRespPass() {
		return emailRespPass;
	}

	public void setEmailRespPass(String emailRespPass) {
		this.emailRespPass = CaronteUtils.convertToUpperCase(emailRespPass);
	}

	public String getCheckRespFito() {
		return checkRespFito;
	}

	public void setCheckRespFito(String checkRespFito) {
		this.checkRespFito = CaronteUtils.convertToUpperCase(checkRespFito);
	}

	public String getQualificaProfRespPass() {
		return qualificaProfRespPass;
	}

	public void setQualificaProfRespPass(String qualificaProfRespPass) {
		this.qualificaProfRespPass = CaronteUtils.convertToUpperCase(qualificaProfRespPass);
	}

	public String[] getIdVoceCheckTipExp() {
		return idVoceCheckTipExp;
	}

	public void setIdVoceCheckTipExp(String[] idVoceCheckTipExp) {
		this.idVoceCheckTipExp = idVoceCheckTipExp;
	}

	public String getExpDatoAggiuntivo() {
		return expDatoAggiuntivo;
	}

	public void setExpDatoAggiuntivo(String expDatoAggiuntivo) {
		this.expDatoAggiuntivo = CaronteUtils.convertToUpperCase(expDatoAggiuntivo);
	}

	public String[] getIdVoceCheckContinentiExp() {
		return idVoceCheckContinentiExp;
	}

	public void setIdVoceCheckContinentiExp(String[] idVoceCheckContinentiExp) {
		this.idVoceCheckContinentiExp = idVoceCheckContinentiExp;
	}

	public String getStatoOrigineExp() {
		return statoOrigineExp;
	}

	public void setStatoOrigineExp(String statoOrigineExp) {
		this.statoOrigineExp = CaronteUtils.convertToUpperCase(statoOrigineExp);
	}

	public Long getIdTipologiaProdExp() {
		return idTipologiaProdExp;
	}

	public void setIdTipologiaProdExp(Long idTipologiaProdExp) {
		this.idTipologiaProdExp = idTipologiaProdExp;
	}

	public String getGenereExport() {
		return genereExport;
	}

	public void setGenereExport(String genereExport) {
		this.genereExport = CaronteUtils.convertToUpperCase(genereExport);
	}

	public String[] getSpecieExport() {
		return specieExport;
	}

	public void setSpecieExport(String[] specieExport) {
		this.specieExport = specieExport;
	}

	public String getDescStatoComunicazione() {
		return descStatoComunicazione;
	}

	public void setDescStatoComunicazione(String descStatoComunicazione) {
		this.descStatoComunicazione = CaronteUtils.convertToUpperCase(descStatoComunicazione);
	}

	public Long getIdVoceRadioChecked() {
		return idVoceRadioChecked;
	}

	public void setIdVoceRadioChecked(Long idVoceRadioChecked) {
		this.idVoceRadioChecked = idVoceRadioChecked;
	}

	public Long getIdGenereImport() {
		return idGenereImport;
	}

	public void setIdGenereImport(Long idGenereImport) {
		this.idGenereImport = idGenereImport;
	}

	public Long getIdGenereExport() {
		return idGenereExport;
	}

	public void setIdGenereExport(Long idGenereExport) {
		this.idGenereExport = idGenereExport;
	}

	public Long getIdGenerePass() {
		return idGenerePass;
	}

	public void setIdGenerePass(Long idGenerePass) {
		this.idGenerePass = idGenerePass;
	}

	public Long getIdGenere() {
		return idGenere;
	}

	public void setIdGenere(Long idGenere) {
		this.idGenere = idGenere;
	}

	public BigDecimal getTariffa() {
		return tariffa;
	}

	public void setTariffa(BigDecimal tariffa) {
		this.tariffa = tariffa;
	}

	public String getCodiceFitok() {
		return codiceFitok;
	}

	public void setCodiceFitok(String codiceFitok) {
		this.codiceFitok = CaronteUtils.convertToUpperCase(codiceFitok);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = CaronteUtils.convertToUpperCase(note);
	}
	public String getTipoSpedizioniereAltro() {
		return tipoSpedizioniereAltro;
	}

	public void setTipoSpedizioniereAltro(String tipoSpedizioniereAltro) {
		this.tipoSpedizioniereAltro = CaronteUtils.convertToUpperCase(tipoSpedizioniereAltro);
	}				
	
	public Date getDataRegistrazioneRuop() {
		return dataRegistrazioneRuop;
	}
	
	public void setDataRegistrazioneRuop(Date dataRegistrazioneRuop) {
		this.dataRegistrazioneRuop = dataRegistrazioneRuop;
	}

	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}

	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = CaronteUtils.convertToUpperCase(numeroProtocollo);
	}

	public Date getDataProtocollo() {
		return dataProtocollo;
	}

	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

	public String getCodiceRuop() {
		return codiceRuop;
	}

	public void setCodiceRuop(String codiceRuop) {
		this.codiceRuop = CaronteUtils.convertToUpperCase(codiceRuop);
	}
	
	public Long getIdIspettore() {
		return idIspettore;
	}

	public void setIdIspettore(Long idIspettore) {
		this.idIspettore = idIspettore;
	}
	public boolean isRichiestaPassaporto() {
		return richiestaPassaporto;
	}

	public void setRichiestaPassaporto(boolean richiestaPassaporto) {
		this.richiestaPassaporto = richiestaPassaporto;
	}	
	public String getNoteTipologiaCentroAz() {
		return noteTipologiaCentroAz;
	}

	public void setNoteTipologiaCentroAz(String noteTipologiaCentroAz) {
		this.noteTipologiaCentroAz = CaronteUtils.convertToUpperCase(noteTipologiaCentroAz);
	}

	public String getNoteTipologiaImport() {
		return noteTipologiaImport;
	}

	public void setNoteTipologiaImport(String noteTipologiaImport) {
		this.noteTipologiaImport = CaronteUtils.convertToUpperCase(noteTipologiaImport);
	}

	public String getNoteTipologiaExport() {
		return noteTipologiaExport;
	}

	public void setNoteTipologiaExport(String noteTipologiaExport) {
		this.noteTipologiaExport = CaronteUtils.convertToUpperCase(noteTipologiaExport);
	}

	public String getDescrBreveDomandaSuccessiva() {
		return descrBreveDomandaSuccessiva;
	}

	public void setDescrBreveDomandaSuccessiva(String descrBreveDomandaSuccessiva) {
		this.descrBreveDomandaSuccessiva = CaronteUtils.convertToUpperCase(descrBreveDomandaSuccessiva);
	}

	public boolean isAbilitaCreaNuovaDomandaFlusso() {
		return abilitaCreaNuovaDomandaFlusso;
	}

	public void setAbilitaCreaNuovaDomandaFlusso(boolean abilitaCreaNuovaDomandaFlusso) {
		this.abilitaCreaNuovaDomandaFlusso = abilitaCreaNuovaDomandaFlusso;
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

	public List<CarDVoce> getVociRadioTipoRichiesta() {
		return vociRadioTipoRichiesta;
	}

	public void setVociRadioTipoRichiesta(List<CarDVoce> vociRadioTipoRichiesta) {
		this.vociRadioTipoRichiesta = vociRadioTipoRichiesta;
	}

	public String[] getIdVoceRadioTipoRichiesta() {
		return idVoceRadioTipoRichiesta;
	}

	public void setIdVoceRadioTipoRichiesta(String[] idVoceRadioTipoRichiesta) {
		this.idVoceRadioTipoRichiesta = idVoceRadioTipoRichiesta;
	}

	public String getVoceDichiaraConoscenze() {
		return voceDichiaraConoscenze;
	}

	public void setVoceDichiaraConoscenze(String voceDichiaraConoscenze) {
		this.voceDichiaraConoscenze = CaronteUtils.convertToUpperCase(voceDichiaraConoscenze);
	}

	public String getVoceDichiaraDisporreSistemi() {
		return voceDichiaraDisporreSistemi;
	}

	public void setVoceDichiaraDisporreSistemi(String voceDichiaraDisporreSistemi) {
		this.voceDichiaraDisporreSistemi = CaronteUtils.convertToUpperCase(voceDichiaraDisporreSistemi);
	}

	public String getVocePianoRischi() {
		return vocePianoRischi;
	}

	public void setVocePianoRischi(String vocePianoRischi) {
		this.vocePianoRischi = CaronteUtils.convertToUpperCase(vocePianoRischi);
	}

	public Long getIdCentroAziendalePassaporto() {
		return idCentroAziendalePassaporto;
	}

	public void setIdCentroAziendalePassaporto(Long idCentroAziendalePassaporto) {
		this.idCentroAziendalePassaporto = idCentroAziendalePassaporto;
	}

	public boolean isDomandaRuopConvalidataPresente() {
		return domandaRuopConvalidataPresente;
	}

	public void setDomandaRuopConvalidataPresente(boolean domandaRuopConvalidataPresente) {
		this.domandaRuopConvalidataPresente = domandaRuopConvalidataPresente;
	}

	/**
	 * @return the listaCentriAz
	 */
	public List<CentroAziendaleDomandaDTO> getListaCentriAz() {
		return listaCentriAz;
	}

	/**
	 * @param listaCentriAz the listaCentriAz to set
	 */
	public void setListaCentriAz(List<CentroAziendaleDomandaDTO> listaCentriAz) {
		this.listaCentriAz = listaCentriAz;
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

	public Long getIdVersioneDomanda() {
		return idVersioneDomanda;
	}

	public void setIdVersioneDomanda(Long idVersioneDomanda) {
		this.idVersioneDomanda = idVersioneDomanda;
	}

	public Long getIdTipologiaPassaporto() {
		return idTipologiaPassaporto;
	}

	public void setIdTipologiaPassaporto(Long idTipologiaPassaporto) {
		this.idTipologiaPassaporto = idTipologiaPassaporto;

	}

	public Long getIdCentroAziendaleGestSel() {
		return idCentroAziendaleGestSel;
	}

	public void setIdCentroAziendaleGestSel(Long idCentroAziendaleGestSel) {
		this.idCentroAziendaleGestSel = idCentroAziendaleGestSel;
	}

}
