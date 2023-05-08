package it.aizoon.ersaf.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.MisuraDTO;
import it.aizoon.ersaf.dto.MonitCofinanziatoDTO;
import it.aizoon.ersaf.dto.OrgNocivoGenereSpecieDTO;
import it.aizoon.ersaf.dto.SementeDTO;
import it.aizoon.ersaf.dto.SitoProduzioneDTO;
import it.aizoon.ersaf.dto.generati.CarDMateriale;
import it.aizoon.ersaf.dto.generati.CarTMonitCofinanziato;
import it.aizoon.ersaf.dto.generati.CarTSitoProduzione;
import it.aizoon.ersaf.util.CaronteUtils;

public class NuovoControlloForm extends BaseForm {

		private Long idControllo;
		
		// Per la visualizzazione dei tab, in base a quanto scelto nelle tipologie controllo
		private boolean tabDocumentale;
		private boolean tabIdentita;
		private boolean tabFisico;
		

	// *** DATI AZIENDA ***
		private Long idSpedizioniere;
		private Long idTipoSpedizioniere;
		private String cuaa;
		private String partitaIva;
		private String denomSpedizioniere;
		private String nome;
		private String cognome;
		private String telefono;
		private String cellulare;
		private String fax;
		private String cap;
		private String indirizzo;
		private Long idComune;
		private Long idProvincia;
		private String email;
		private String pec;
		private String tipoSpedizioniereAltro;
		private String denomTipoSpedizioniere;
		private String tipologiaAttivita;
		private String codiceFitok;
		private BigDecimal tariffa;
		

		// *** DATI CENTRI AZIENDALI ***
		 private Long idCentroAziendale;
		 private String codCentroAziendale;
		 private Long idComuneCa;
		 private Long idProvinciaCa;
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
		 private Long idIspettore;
		 private Long idTipologiaPassaporto;
		 
		//Tab DatiBase
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date dataControllo;
		private String oraInizioControllo;
		private String oraFineControllo;
				
		@NotNull(message="Campo obbligatorio")
		private String[] idsIspettore;
		
		private String[] idsNormaVerbale;
		private String[] idsTipologiaControllo;
		
		@Size(min=1, max=200)
		private String altreNorme;
		@NotNull(message="Campo obbligatorio")
		@Size(min=1, max=200)
		private String responsabileAzienda;
		@NotNull(message="Campo obbligatorio")
		private Long idQualifica;
		@Size(min=1, max=200)
		private String altroControllo;
		
		@Size(min=0, max=200)
		private String soggettiSupportoSopralluogo;
		
		private List<SementeDTO> sementi;
		
		//Checkboxes		
		private String monitoraggioCofinanziato;
		
		private Long idDomanda;
		private Long idStatoComunicazione;
		private Long idTipoComunicazione;

		private Long[] idsMaterialeSementi;
		
		// *** TAB CONTROLLO DOCUMENTALE ***
		private String flControllo3x1;
		private String descControllo3x1;		
		private String flControllo3x1x1;
		private String noteControllo3x1x1;		
		private String flControllo3x2;		
		private String flControllo3x3;		
		private String flControllo3x3x1;
		private String noteControllo3x3x1;		
		private String flControllo3x4;		
		private String flControllo3x5;
		private String flControllo3x6;
		private String flControllo3x7;
		private String flControllo3x8;
		private String noteControllo3x8;
		private String flControllo3x9;
		private String noteControllo3x9;
		private String flControllo3x10;
		private String flControllo3x11;
		private String noteControllo3x11;
		private String flControllo3x12;
		private String noteControllo3x12;
		private String flControllo3x13;
		private String noteControllo3x13;
		private String flControllo3x14;
		private String flControllo3x15;
		private String noteControllo3x15;
		private String flControllo3x16;
		private Long idControlloDocumentale;
		
		
		//** TAB CONTROLLO IDENTITA'
		private String flControllo4x1;
		private String flControllo4x2;
		
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
		private Long idControlloIdentita;
		private List<SitoProduzioneDTO> listaSitiProdRuop;
		
		//TAB CONTROLLO FISICO
		private Long idControlloFisico;
		private String flControllo5x1;
		private String flControllo5x2;		
		private String flControllo5x4;		
		private String flControllo5x5;
		private String flControllo5x6;
		private String flControllo5x7;
		private String flControllo5x8;
		private String flControllo5x9;
		private String flControllo5x10;
		private String flControllo5x11;
		private Long[] orgNoc;
		@Size(min=1, max=300)
		private String noteControllo5x11;
		private String flControllo5x12;
		private String flControllo5x13;
		private String flControllo5x14;
		private String flControllo5x15;
		private String flControllo5x16;
		private String flControllo5x17;
		private String flControllo5x18;
		private String flControllo5x19;
		private String flControllo5x20;
		private String flControllo5x21;
		private String flControllo5x22;
		@Size(min=1, max=300)
		private String noteControllo5x22;
		private String flControllo5x23;

		private String[] idsStrutturaAttrezzatura;
		private String[] idsMetodiProduzione;
		private String[] idsTipiIrrigazione;
		private String[] idsConoscenzeProfessionali;
		private String[] idsRequisitiProfessionali;
		private String laurea;
		private String diploma;
		
		
		private String[] numeroPiante;
		private String[] numPianteSintomatiche;
		
		/*private String[] idSpecie;
		private String[] denomGenere;		
		private String[] idOrgNocivoTrovato;		
		private Long[] orgNocTrovati;*/

		private List<GenereSpecieDTO> genereSpecie;
		
		// nuova gestione
		private String genereFisico;
		private Long idGenereFisico;
		private String[] specieFisico;
		private String[] orgNociviFisico;
		private String[] idControlloFisicoSpecie;
		
		
		// ** TAB CAMPIONI
		private String genereCampione;
		private Long idGenereCampione;
		private Long specieCampione;
		private String codCampioneDal;
		private String codCampioneAl;
		private String tipoCampione;
		private Long[] orgNocDaRicercare;
		private Long tipologiaCampione;
		private String flCofinanziato;
		private String tempoImpiegato;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date dataRdp;
		private String esitoRdp;
		private String numeroRdp;
		private Long[] orgNocAccertato;
		private String noteCampione;
		private String flSacchetti;
		private String flBanda;
		private String flAnalisi;
		private Long[] idsDaEliminare;

		//TAB ESITO
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date dataChiusuraVerbale;		
		private String cognomeResp;		
		private String nomeResp;
		private String noteDichiarazione;
		@Size(min=0, max=500)
		private String note;
		private String flIrregolarita;
		@Size(min=0, max=300)
		private String noteIrregolarita;
		private String flEsito;
		private String flEsitoPassaporto;
		private Long idEsito;
		private String emailInvioVerbale;
		// check
		private String misuraUfficiale;
		// campi versione 2
		private String noteEsitoControllo;
		private String flMisuraUfficiale;
		private String numMisuraUfficiale;
		private String flMotivoMisuraUfficiale;
		private String noteMotivoMisuraUfficiale;
		private String flSanzioneAmministrativaEmessa;
		private String noteSanzioneAmministrativaEmessa;
		private String flSanzioneAmministrativaProposta;
		private String noteSanzioneAmministrativaProposta;
		private String flPrescrizioni;
		private String notePrescrizioni;
		
		
		// ** TAB MISURA UFFICIALE
		private Long idMisuraUfficiale;
		// ---- Disposizone misura ufficiale ---
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date dataMisura;
		private String oraMisura;
		private String numVerbMisuraUff;
		private Long[] idsIspettoreMisura;
		private String noteNonConformita;
		
		// Organismi nocivi, genere e specie
		private Long idOrgNocMotivoMisura;
		private String altroMotivoMisura;
		private String genereMisura;
		private String[] idSpecieMisura;		
		private String[] numeroPianteMisura;
		private String[] denomGenereMisura;
		private String[] idGenereMisura;
		private String[] idOrganismoNocivoMisura;
		private List<OrgNocivoGenereSpecieDTO> orgNocivoGenereSpecie;
				
		private List<MisuraDTO> misure;
		private String lettereMisura;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date dataMisuraEntro;
		private String modalita;
		private String noteMisuraDisp;
		// Persona di riferimento per il verbale della Misura ufficiale
		private String personaRifVerbale;
		private Long idTipoRespConsegnaDisp;		
		private String dichPersRifVerbale;
		// Persona identifcata come Custode dei prodotti oggetto di misura ufficiale
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date dataConsegnaDisp;
		private String cognomeCustode;
		private String nomeCustode;
		private Long idProvNascitaCustode;
		private Long idComNascitaCustode;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date dataNascitaCustode;
		private Long idProvResidCustode;
		private Long idComResidCustode;
		private String indirResidCustode;
		private String tipoDocIdentificazCustode;
		private String numDocIdentificazCustode;
		private Long idQualificaCustode;
		private String prescrizioniCustode;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date dataEmissioneDocumento;
		private String orgEmissioneDocumento;
				
		// --- Constatazione misura ufficiale ---
		private String idConstatazionePresente;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date dataConstMisura;
		private String oraConstMisura;
		private String numVerbConstatazMisuraUff;
		private Long[] idsIspettoreConstMisura;
		private String flEsitoMisura;
		private String noteConstMisura;
		// Persona di riferimento per il verbale della Constatazione della Misura ufficiale
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date dataConsegnaConst;
		private String personaRifVerbaleConst;
		private Long idTipoRespConsegnaConst;		
		private String dichPersRifVerbaleConst;
		private String notePersRifVerbaleConst;
		// Persona presente nella constatazione
		private String personaPresenteConst;
		private Long idQualificaPersonaPresenteConst;
		
		// ** TAB MONITORAGGIO
		// Organismi nocivi, genere e specie		
		private Long idOrgNocMotivoMonit;
		private String altroMotivoMonit;
		private String genereMonit;
		private String[] idSpecieMonit;		
		private String[] numeroPianteMonit;
		private String[] denomGenereMonit;
		private String[] idGenereMonit;
		private String[] idOrganismoNocivoMonit;
		private List<MonitCofinanziatoDTO> monitoraggiCofinanziati;
		
		private String[] oraInizioMonit;
		private String[] oraFineMonit;
		private String[] latitudine;
		private String[] longitudine;
		private String[] noteMonit;
		
		private String latitudineM;
		private String longitudineM;
		private String noteM;
		
		private Long idMonitCofinanziato;
		
		private Long idVersioneControllo;		
		

		public Long getIdSpedizioniere() {
			return idSpedizioniere;
		}
		public void setIdSpedizioniere(Long idSpedizioniere) {
			this.idSpedizioniere = idSpedizioniere;
		}
		public Long getIdTipoSpedizioniere() {
			return idTipoSpedizioniere;
		}
		public void setIdTipoSpedizioniere(Long idTipoSpedizioniere) {
			this.idTipoSpedizioniere = idTipoSpedizioniere;
		}
		public String getCuaa() {
			return cuaa;
		}
		public void setCuaa(String cuaa) {
			this.cuaa = CaronteUtils.convertToUpperCase(cuaa);
		}
		public String getPartitaIva() {
			return partitaIva;
		}
		public void setPartitaIva(String partitaIva) {
			this.partitaIva = CaronteUtils.convertToUpperCase(partitaIva);
		}
		public String getDenomSpedizioniere() {
			return denomSpedizioniere;
		}
		public void setDenomSpedizioniere(String denomSpedizioniere) {
			this.denomSpedizioniere = CaronteUtils.convertToUpperCase(denomSpedizioniere);
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = CaronteUtils.convertToUpperCase(nome);
		}
		public String getCognome() {
			return cognome;
		}
		public void setCognome(String cognome) {
			this.cognome = CaronteUtils.convertToUpperCase(cognome);
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
		public String getFax() {
			return fax;
		}
		public void setFax(String fax) {
			this.fax = fax;
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
			this.indirizzo = CaronteUtils.convertToUpperCase(indirizzo);
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = CaronteUtils.convertToUpperCase(email);
		}
		public String getPec() {
			return pec;
		}
		public void setPec(String pec) {
			this.pec = CaronteUtils.convertToUpperCase(pec);
		}

		public String getTipoSpedizioniereAltro() {
			return tipoSpedizioniereAltro;
		}
		public void setTipoSpedizioniereAltro(String tipoSpedizioniereAltro) {
			this.tipoSpedizioniereAltro = CaronteUtils.convertToUpperCase(tipoSpedizioniereAltro);
		}		
		public Date getDataControllo() {
			return dataControllo;
		}
		public void setDataControllo(Date dataControllo) {
			this.dataControllo = dataControllo;
		}
		public String getOraInizioControllo() {
			return oraInizioControllo;
		}
		public void setOraInizioControllo(String oraInizioControllo) {
			this.oraInizioControllo = oraInizioControllo;
		}
		public String getOraFineControllo() {
			return oraFineControllo;
		}
		public void setOraFineControllo(String oraFineControllo) {
			this.oraFineControllo = oraFineControllo;
		}
		public String getAltreNorme() {
			return altreNorme;
		}
		public void setAltreNorme(String altreNorme) {
			this.altreNorme = CaronteUtils.convertToUpperCase(altreNorme);
		}
		public String getResponsabileAzienda() {
			return responsabileAzienda;
		}
		public void setResponsabileAzienda(String responsabileAzienda) {
			this.responsabileAzienda = CaronteUtils.convertToUpperCase(responsabileAzienda);
		}
		public Long getIdQualifica() {
			return idQualifica;
		}
		public void setIdQualifica(Long idQualifica) {
			this.idQualifica = idQualifica;
		}
		public String getAltroControllo() {
			return altroControllo;
		}
		public void setAltroControllo(String altroControllo) {
			this.altroControllo = CaronteUtils.convertToUpperCase(altroControllo);
		}
		public String getMisuraUfficiale() {
			return misuraUfficiale;
		}
		public void setMisuraUfficiale(String misuraUfficiale) {
			this.misuraUfficiale = CaronteUtils.convertToUpperCase(misuraUfficiale);
		}
		public String getMonitoraggioCofinanziato() {
			return monitoraggioCofinanziato;
		}
		public void setMonitoraggioCofinanziato(String monitoraggioCofinanziato) {
			this.monitoraggioCofinanziato = CaronteUtils.convertToUpperCase(monitoraggioCofinanziato);
		}
		public String[] getIdsIspettore() {
			return idsIspettore;
		}
		public void setIdsIspettore(String[] idsIspettore) {
			this.idsIspettore = idsIspettore;
		}
		public String[] getIdsNormaVerbale() {
			return idsNormaVerbale;
		}
		public void setIdsNormaVerbale(String[] idsNormaVerbale) {
			this.idsNormaVerbale = idsNormaVerbale;
		}
		public String[] getIdsTipologiaControllo() {
			return idsTipologiaControllo;
		}
		public void setIdsTipologiaControllo(String[] idsTipologiaControllo) {
			this.idsTipologiaControllo = idsTipologiaControllo;
		}
		
		/*public List<SementeDTO> getSementi() {
			return sementi;
		}
		public void setSementi(List<SementeDTO> sementi) {
			this.sementi = sementi;
		}*/
		public String getSoggettiSupportoSopralluogo() {
			return soggettiSupportoSopralluogo;
		}
		public void setSoggettiSupportoSopralluogo(String soggettiSupportoSopralluogo) {
			this.soggettiSupportoSopralluogo = CaronteUtils.convertToUpperCase(soggettiSupportoSopralluogo);
		}
		public Long getIdCentroAziendale() {
			return idCentroAziendale;
		}
		public void setIdCentroAziendale(Long idCentroAziendale) {
			this.idCentroAziendale = idCentroAziendale;
		}

		public String getDenomTipoSpedizioniere() {
			return denomTipoSpedizioniere;
		}
		public void setDenomTipoSpedizioniere(String denomTipoSpedizioniere) {
			this.denomTipoSpedizioniere = denomTipoSpedizioniere;
		}
		public String getCodCentroAziendale() {
			return codCentroAziendale;
		}
		public void setCodCentroAziendale(String codCentroAziendale) {
			this.codCentroAziendale = CaronteUtils.convertToUpperCase(codCentroAziendale);
		}
		public Long getIdComuneCa() {
			return idComuneCa;
		}
		public void setIdComuneCa(Long idComuneCa) {
			this.idComuneCa = idComuneCa;
		}
		public Long getIdProvinciaCa() {
			return idProvinciaCa;
		}
		public void setIdProvinciaCa(Long idProvinciaCa) {
			this.idProvinciaCa = idProvinciaCa;
		}
		public String getCapCa() {
			return capCa;
		}
		public void setCapCa(String capCa) {
			this.capCa = capCa;
		}
		public String getIndirizzoCa() {
			return indirizzoCa;
		}
		public void setIndirizzoCa(String indirizzoCa) {
			this.indirizzoCa = CaronteUtils.convertToUpperCase(indirizzoCa);
		}
		public String getFrazioneCa() {
			return frazioneCa;
		}
		public void setFrazioneCa(String frazioneCa) {
			this.frazioneCa = CaronteUtils.convertToUpperCase(frazioneCa);
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
			this.emailCa = CaronteUtils.convertToUpperCase(emailCa);
		}
		public String getPecCa() {
			return pecCa;
		}
		public void setPecCa(String pecCa) {
			this.pecCa = CaronteUtils.convertToUpperCase(pecCa);
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
			this.cognomeRP = CaronteUtils.convertToUpperCase(cognomeRP);
		}
		public String getNomeRP() {
			return nomeRP;
		}
		public void setNomeRP(String nomeRP) {
			this.nomeRP = CaronteUtils.convertToUpperCase(nomeRP);
		}
		public String getTelefonoRP() {
			return telefonoRP;
		}
		public void setTelefonoRP(String telefonoRP) {
			this.telefonoRP = telefonoRP;
		}
		public Long getIdControllo() {
			return idControllo;
		}
		public void setIdControllo(Long idControllo) {
			this.idControllo = idControllo;
		}
		public String getFlControllo3x1() {
			return flControllo3x1;
		}
		public void setFlControllo3x1(String flControllo3x1) {
			this.flControllo3x1 = CaronteUtils.convertToUpperCase(flControllo3x1);
		}
		public String getDescControllo3x1() {
			return descControllo3x1;
		}
		public void setDescControllo3x1(String descControllo3x1) {
			this.descControllo3x1 = CaronteUtils.convertToUpperCase(descControllo3x1);
		}
		public String getFlControllo3x1x1() {
			return flControllo3x1x1;
		}
		public void setFlControllo3x1x1(String flControllo3x1x1) {
			this.flControllo3x1x1 = CaronteUtils.convertToUpperCase(flControllo3x1x1);
		}
		public String getNoteControllo3x1x1() {
			return noteControllo3x1x1;
		}
		public void setNoteControllo3x1x1(String noteControllo3x1x1) {
			this.noteControllo3x1x1 = CaronteUtils.convertToUpperCase(noteControllo3x1x1);
		}
		public String getFlControllo3x2() {
			return flControllo3x2;
		}
		public void setFlControllo3x2(String flControllo3x2) {
			this.flControllo3x2 = CaronteUtils.convertToUpperCase(flControllo3x2);
		}
		public String getFlControllo3x3() {
			return flControllo3x3;
		}
		public void setFlControllo3x3(String flControllo3x3) {
			this.flControllo3x3 = CaronteUtils.convertToUpperCase(flControllo3x3);
		}
		public String getFlControllo3x3x1() {
			return flControllo3x3x1;
		}
		public void setFlControllo3x3x1(String flControllo3x3x1) {
			this.flControllo3x3x1 = CaronteUtils.convertToUpperCase(flControllo3x3x1);
		}
		public String getNoteControllo3x3x1() {
			return noteControllo3x3x1;
		}
		public void setNoteControllo3x3x1(String noteControllo3x3x1) {
			this.noteControllo3x3x1 = CaronteUtils.convertToUpperCase(noteControllo3x3x1);
		}
		
		public String getFlControllo3x6() {
			return flControllo3x6;
		}
		public void setFlControllo3x6(String flControllo3x6) {
			this.flControllo3x6 = CaronteUtils.convertToUpperCase(flControllo3x6);
		}
		public String getFlControllo3x7() {
			return flControllo3x7;
		}
		public void setFlControllo3x7(String flControllo3x7) {
			this.flControllo3x7 = CaronteUtils.convertToUpperCase(flControllo3x7);
		}
		public String getFlControllo3x8() {
			return flControllo3x8;
		}
		public void setFlControllo3x8(String flControllo3x8) {
			this.flControllo3x8 = CaronteUtils.convertToUpperCase(flControllo3x8);
		}
		public String getNoteControllo3x8() {
			return noteControllo3x8;
		}
		public void setNoteControllo3x8(String noteControllo3x8) {
			this.noteControllo3x8 = CaronteUtils.convertToUpperCase(noteControllo3x8);
		}
		public String getFlControllo3x9() {
			return flControllo3x9;
		}
		public void setFlControllo3x9(String flControllo3x9) {
			this.flControllo3x9 = CaronteUtils.convertToUpperCase(flControllo3x9);
		}
		public String getNoteControllo3x9() {
			return noteControllo3x9;
		}
		public void setNoteControllo3x9(String noteControllo3x9) {
			this.noteControllo3x9 = CaronteUtils.convertToUpperCase(noteControllo3x9);
		}
		public String getFlControllo3x10() {
			return flControllo3x10;
		}
		public void setFlControllo3x10(String flControllo3x10) {
			this.flControllo3x10 = CaronteUtils.convertToUpperCase(flControllo3x10);
		}
		public String getFlControllo3x11() {
			return flControllo3x11;
		}
		public void setFlControllo3x11(String flControllo3x11) {
			this.flControllo3x11 = CaronteUtils.convertToUpperCase(flControllo3x11);
		}
		public String getNoteControllo3x11() {
			return noteControllo3x11;
		}
		public void setNoteControllo3x11(String noteControllo3x11) {
			this.noteControllo3x11 = CaronteUtils.convertToUpperCase(noteControllo3x11);
		}
		public String getFlControllo3x12() {
			return flControllo3x12;
		}
		public void setFlControllo3x12(String flControllo3x12) {
			this.flControllo3x12 = CaronteUtils.convertToUpperCase(flControllo3x12);
		}
		public String getNoteControllo3x12() {
			return noteControllo3x12;
		}
		public void setNoteControllo3x12(String noteControllo3x12) {
			this.noteControllo3x12 = CaronteUtils.convertToUpperCase(noteControllo3x12);
		}
		public String getFlControllo3x13() {
			return flControllo3x13;
		}
		public void setFlControllo3x13(String flControllo3x13) {
			this.flControllo3x13 = CaronteUtils.convertToUpperCase(flControllo3x13);
		}
		public String getNoteControllo3x13() {
			return noteControllo3x13;
		}
		public void setNoteControllo3x13(String noteControllo3x13) {
			this.noteControllo3x13 = CaronteUtils.convertToUpperCase(noteControllo3x13);
		}
		public String getFlControllo3x14() {
			return flControllo3x14;
		}
		public void setFlControllo3x14(String flControllo3x14) {
			this.flControllo3x14 = CaronteUtils.convertToUpperCase(flControllo3x14);
		}
		public String getFlControllo3x15() {
			return flControllo3x15;
		}
		public void setFlControllo3x15(String flControllo3x15) {
			this.flControllo3x15 = flControllo3x15;
		}
		public String getNoteControllo3x15() {
			return noteControllo3x15;
		}
		public void setNoteControllo3x15(String noteControllo3x15) {
			this.noteControllo3x15 = CaronteUtils.convertToUpperCase(noteControllo3x15);
		}
		public String getFlControllo3x16() {
			return flControllo3x16;
		}
		public void setFlControllo3x16(String flControllo3x16) {
			this.flControllo3x16 = flControllo3x16;
		}
		public Long getIdControlloDocumentale() {
			return idControlloDocumentale;
		}
		public void setIdControlloDocumentale(Long idControlloDocumentale) {
			this.idControlloDocumentale = idControlloDocumentale;
		}
		public Long getIdDomanda() {
			return idDomanda;
		}
		public void setIdDomanda(Long idDomanda) {
			this.idDomanda = idDomanda;
		}
		public Long getIdStatoComunicazione() {
			return idStatoComunicazione;
		}
		public void setIdStatoComunicazione(Long idStatoComunicazione) {
			this.idStatoComunicazione = idStatoComunicazione;
		}
		public Long getIdTipoComunicazione() {
			return idTipoComunicazione;
		}
		public void setIdTipoComunicazione(Long idTipoComunicazione) {
			this.idTipoComunicazione = idTipoComunicazione;
		}
		public String getFlControllo3x4() {
			return flControllo3x4;
		}
		public void setFlControllo3x4(String flControllo3x4) {
			this.flControllo3x4 = flControllo3x4;
		}
		public String getFlControllo3x5() {
			return flControllo3x5;
		}
		public void setFlControllo3x5(String flControllo3x5) {
			this.flControllo3x5 = flControllo3x5;
		}
       public String getFlControllo4x1() {
			return flControllo4x1;
		}
		public void setFlControllo4x1(String flControllo4x1) {
			this.flControllo4x1 = flControllo4x1;
		}
		public String getFlControllo4x2() {
			return flControllo4x2;
		}
		public void setFlControllo4x2(String flControllo4x2) {
			this.flControllo4x2 = flControllo4x2;
		}
		public String getFlControllo5x1() {
			return flControllo5x1;
		}
		public void setFlControllo5x1(String flControllo5x1) {
			this.flControllo5x1 = flControllo5x1;
		}
		public String getFlControllo5x2() {
			return flControllo5x2;
		}
		public void setFlControllo5x2(String flControllo5x2) {
			this.flControllo5x2 = flControllo5x2;
		}
		public String getFlControllo5x4() {
			return flControllo5x4;
		}
		public void setFlControllo5x4(String flControllo5x4) {
			this.flControllo5x4 = flControllo5x4;
		}
		public String getFlControllo5x5() {
			return flControllo5x5;
		}
		public void setFlControllo5x5(String flControllo5x5) {
			this.flControllo5x5 = flControllo5x5;
		}
		public String getFlControllo5x6() {
			return flControllo5x6;
		}
		public void setFlControllo5x6(String flControllo5x6) {
			this.flControllo5x6 = flControllo5x6;
		}
		public String getFlControllo5x7() {
			return flControllo5x7;
		}
		public void setFlControllo5x7(String flControllo5x7) {
			this.flControllo5x7 = flControllo5x7;
		}
		public String getFlControllo5x8() {
			return flControllo5x8;
		}
		public void setFlControllo5x8(String flControllo5x8) {
			this.flControllo5x8 = flControllo5x8;
		}
		public String getFlControllo5x9() {
			return flControllo5x9;
		}
		public void setFlControllo5x9(String flControllo5x9) {
			this.flControllo5x9 = flControllo5x9;
		}
		public String getFlControllo5x10() {
			return flControllo5x10;
		}
		public void setFlControllo5x10(String flControllo5x10) {
			this.flControllo5x10 = flControllo5x10;
		}
		public String getFlControllo5x11() {
			return flControllo5x11;
		}
		public void setFlControllo5x11(String flControllo5x11) {
			this.flControllo5x11 = flControllo5x11;
		}
		public String getNoteControllo5x11() {
			return noteControllo5x11;
		}
		public void setNoteControllo5x11(String noteControllo5x11) {
			this.noteControllo5x11 = CaronteUtils.convertToUpperCase(noteControllo5x11);
		}
		public String getFlControllo5x12() {
			return flControllo5x12;
		}
		public void setFlControllo5x12(String flControllo5x12) {
			this.flControllo5x12 = flControllo5x12;
		}
		public String getFlControllo5x13() {
			return flControllo5x13;
		}
		public void setFlControllo5x13(String flControllo5x13) {
			this.flControllo5x13 = flControllo5x13;
		}
		public String getFlControllo5x14() {
			return flControllo5x14;
		}
		public void setFlControllo5x14(String flControllo5x14) {
			this.flControllo5x14 = flControllo5x14;
		}
		public String getFlControllo5x15() {
			return flControllo5x15;
		}
		public void setFlControllo5x15(String flControllo5x15) {
			this.flControllo5x15 = flControllo5x15;
		}
		public String getFlControllo5x16() {
			return flControllo5x16;
		}
		public void setFlControllo5x16(String flControllo5x16) {
			this.flControllo5x16 = flControllo5x16;
		}
		public String getFlControllo5x17() {
			return flControllo5x17;
		}
		public void setFlControllo5x17(String flControllo5x17) {
			this.flControllo5x17 = flControllo5x17;
		}
		public String getFlControllo5x18() {
			return flControllo5x18;
		}
		public void setFlControllo5x18(String flControllo5x18) {
			this.flControllo5x18 = flControllo5x18;
		}
		public String getFlControllo5x19() {
			return flControllo5x19;
		}
		public void setFlControllo5x19(String flControllo5x19) {
			this.flControllo5x19 = flControllo5x19;
		}
		public String getFlControllo5x20() {
			return flControllo5x20;
		}
		public void setFlControllo5x20(String flControllo5x20) {
			this.flControllo5x20 = flControllo5x20;
		}
		public String getFlControllo5x21() {
			return flControllo5x21;
		}
		public void setFlControllo5x21(String flControllo5x21) {
			this.flControllo5x21 = flControllo5x21;
		}
		public String getFlControllo5x22() {
			return flControllo5x22;
		}
		public void setFlControllo5x22(String flControllo5x22) {
			this.flControllo5x22 = flControllo5x22;
		}
		public String getNoteControllo5x22() {
			return noteControllo5x22;
		}
		public void setNoteControllo5x22(String noteControllo5x22) {
			this.noteControllo5x22 = CaronteUtils.convertToUpperCase(noteControllo5x22);
		}
		public String getFlControllo5x23() {
			return flControllo5x23;
		}
		public void setFlControllo5x23(String flControllo5x23) {
			this.flControllo5x23 = flControllo5x23;
		}
		public Long getIdControlloFisico() {
			return idControlloFisico;
		}
		public void setIdControlloFisico(Long idControlloFisico) {
			this.idControlloFisico = idControlloFisico;
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
			this.superficie = CaronteUtils.convertToUpperCase(superficie);
		}
		public List<CarTSitoProduzione> getSitiProduzioneList() {
			return sitiProduzioneList;
		}
		public void setSitiProduzioneList(List<CarTSitoProduzione> sitiProduzioneList) {
			this.sitiProduzioneList = sitiProduzioneList;
		}
		public Long getIdControlloIdentita() {
			return idControlloIdentita;
		}
		public void setIdControlloIdentita(Long idControlloIdentita) {
			this.idControlloIdentita = idControlloIdentita;
		}		
		public String[] getIdsStrutturaAttrezzatura() {
			return idsStrutturaAttrezzatura;
		}
		public void setIdsStrutturaAttrezzatura(String[] idsStrutturaAttrezzatura) {
			this.idsStrutturaAttrezzatura = idsStrutturaAttrezzatura;
		}
		public String[] getIdsMetodiProduzione() {
			return idsMetodiProduzione;
		}
		public void setIdsMetodiProduzione(String[] idsMetodiProduzione) {
			this.idsMetodiProduzione = idsMetodiProduzione;
		}
		public String[] getIdsTipiIrrigazione() {
			return idsTipiIrrigazione;
		}
		public void setIdsTipiIrrigazione(String[] idsTipiIrrigazione) {
			this.idsTipiIrrigazione = idsTipiIrrigazione;
		}
		public String[] getIdsConoscenzeProfessionali() {
			return idsConoscenzeProfessionali;
		}
		public void setIdsConoscenzeProfessionali(String[] idsConoscenzeProfessionali) {
			this.idsConoscenzeProfessionali = idsConoscenzeProfessionali;
		}
		public String[] getIdsRequisitiProfessionali() {
			return idsRequisitiProfessionali;
		}
		public void setIdsRequisitiProfessionali(String[] idsRequisitiProfessionali) {
			this.idsRequisitiProfessionali = idsRequisitiProfessionali;
		}
		public String getLaurea() {
			return laurea;
		}
		public void setLaurea(String laurea) {
			this.laurea = CaronteUtils.convertToUpperCase(laurea);
		}
		public String getDiploma() {
			return diploma;
		}
		public void setDiploma(String diploma) {
			this.diploma = CaronteUtils.convertToUpperCase(diploma);
		}
		public List<SitoProduzioneDTO> getListaSitiProdRuop() {
			return listaSitiProdRuop;
		}
		public void setListaSitiProdRuop(List<SitoProduzioneDTO> listaSitiProdRuop) {
			this.listaSitiProdRuop = listaSitiProdRuop;
		}
		
		public List<GenereSpecieDTO> getGenereSpecie() {
			return genereSpecie; 
		}
		public void setGenereSpecie(List<GenereSpecieDTO> genereSpecie) {
			this.genereSpecie = genereSpecie;
		}				
		public String getGenereCampione() {
			return genereCampione;
		}
		public void setGenereCampione(String genereCampione) {
			this.genereCampione = CaronteUtils.convertToUpperCase(genereCampione);
		}		
		public Long getSpecieCampione() {
			return specieCampione;
		}
		public void setSpecieCampione(Long specieCampione) {
			this.specieCampione = specieCampione;
		}
		public String getCodCampioneDal() {
			return codCampioneDal;
		}
		public void setCodCampioneDal(String codCampioneDal) {
			this.codCampioneDal = CaronteUtils.convertToUpperCase(codCampioneDal);
		}
		public String getCodCampioneAl() {
			return codCampioneAl;
		}
		public void setCodCampioneAl(String codCampioneAl) {
			this.codCampioneAl = CaronteUtils.convertToUpperCase(codCampioneAl);
		}
		public String getTipoCampione() {
			return tipoCampione;
		}
		public void setTipoCampione(String tipoCampione) {
			this.tipoCampione = CaronteUtils.convertToUpperCase(tipoCampione);
		}
		public Long[] getOrgNocDaRicercare() {
			return orgNocDaRicercare;
		}
		public void setOrgNocDaRicercare(Long[] orgNocDaRicercare) {
			this.orgNocDaRicercare = orgNocDaRicercare;
		}
		public Long getTipologiaCampione() {
			return tipologiaCampione;
		}
		public void setTipologiaCampione(Long tipologiaCampione) {
			this.tipologiaCampione = tipologiaCampione;
		}
		public String getFlCofinanziato() {
			return flCofinanziato;
		}
		public void setFlCofinanziato(String flCofinanziato) {
			this.flCofinanziato = flCofinanziato;
		}
		public String getTempoImpiegato() {
			return tempoImpiegato;
		}
		public void setTempoImpiegato(String tempoImpiegato) {
			this.tempoImpiegato = tempoImpiegato;
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

		public Date getDataChiusuraVerbale() {
			return dataChiusuraVerbale;
		}
		public void setDataChiusuraVerbale(Date dataChiusuraVerbale) {
			this.dataChiusuraVerbale = dataChiusuraVerbale;
		}
		public String getCognomeResp() {
			return cognomeResp;
		}
		public void setCognomeResp(String cognomeResp) {
			this.cognomeResp = CaronteUtils.convertToUpperCase(cognomeResp);
		}
		public String getNomeResp() {
			return nomeResp;
		}
		public void setNomeResp(String nomeResp) {
			this.nomeResp = CaronteUtils.convertToUpperCase(nomeResp);
		}
		public String getNoteDichiarazione() {
			return noteDichiarazione;
		}
		public void setNoteDichiarazione(String noteDichiarazione) {
			this.noteDichiarazione = CaronteUtils.convertToUpperCase(noteDichiarazione);
		}
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = CaronteUtils.convertToUpperCase(note);
		}
		public String getFlIrregolarita() {
			return flIrregolarita;
		}
		public void setFlIrregolarita(String flIrregolarita) {
			this.flIrregolarita = flIrregolarita;
		}
		public String getNoteIrregolarita() {
			return noteIrregolarita;
		}
		public void setNoteIrregolarita(String noteIrregolarita) {
			this.noteIrregolarita = CaronteUtils.convertToUpperCase(noteIrregolarita);
		}
		public String getFlEsito() {
			return flEsito;
		}
		public void setFlEsito(String flEsito) {
			this.flEsito = flEsito;
		}
		public String getFlEsitoPassaporto() {
			return flEsitoPassaporto;
		}
		public void setFlEsitoPassaporto(String flEsitoPassaporto) {
			this.flEsitoPassaporto = flEsitoPassaporto;
		}
		public Long getIdEsito() {
			return idEsito;
		}
		public void setIdEsito(Long idEsito) {
			this.idEsito = idEsito;
		}
        public Long getIdGenereCampione() {
			return idGenereCampione;
		}
		public void setIdGenereCampione(Long idGenereCampione) {
			this.idGenereCampione = idGenereCampione;
		}        public String getEmailInvioVerbale() {
			return emailInvioVerbale;
		}
		public void setEmailInvioVerbale(String emailInvioVerbale) {
			this.emailInvioVerbale = CaronteUtils.convertToUpperCase(emailInvioVerbale);
		}
		public Date getDataMisura() {
			return dataMisura;
		}
		public void setDataMisura(Date dataMisura) {
			this.dataMisura = dataMisura;
		}
		public String getNumVerbMisuraUff() {
			return numVerbMisuraUff;
		}
		public void setNumVerbMisuraUff(String numVerbMisuraUff) {
			this.numVerbMisuraUff = CaronteUtils.convertToUpperCase(numVerbMisuraUff);
		}
		public Long[] getIdsIspettoreMisura() {
			return idsIspettoreMisura;
		}
		public void setIdsIspettoreMisura(Long[] idsIspettoreMisura) {
			this.idsIspettoreMisura = idsIspettoreMisura;
		}		
		public String getAltroMotivoMisura() {
			return altroMotivoMisura;
		}
		public void setAltroMotivoMisura(String altroMotivoMisura) {
			this.altroMotivoMisura = CaronteUtils.convertToUpperCase(altroMotivoMisura);
		}
		public String getGenereMisura() {
			return genereMisura;
		}
		public void setGenereMisura(String genereMisura) {
			this.genereMisura = CaronteUtils.convertToUpperCase(genereMisura);
		}			
		public String getOraMisura() {
			return oraMisura;
		}
		public void setOraMisura(String oraMisura) {
			this.oraMisura = oraMisura;
		}
		public String[] getIdSpecieMisura() {
			return idSpecieMisura;
		}
		public void setIdSpecieMisura(String[] idSpecieMisura) {
			this.idSpecieMisura = idSpecieMisura;
		}
		public String[] getNumeroPianteMisura() {
			return numeroPianteMisura;
		}
		public void setNumeroPianteMisura(String[] numeroPianteMisura) {
			this.numeroPianteMisura = numeroPianteMisura;
		}
		public String[] getDenomGenereMisura() {
			return denomGenereMisura;
		}
		public void setDenomGenereMisura(String[] denomGenereMisura) {
			this.denomGenereMisura = denomGenereMisura;
		}
		public List<OrgNocivoGenereSpecieDTO> getOrgNocivoGenereSpecie() {
			return orgNocivoGenereSpecie;
		}
		public void setOrgNocivoGenereSpecie(List<OrgNocivoGenereSpecieDTO> orgNocivoGenereSpecie) {
			this.orgNocivoGenereSpecie = orgNocivoGenereSpecie;
		}		
		public String getPersonaRifVerbale() {
			return personaRifVerbale;
		}
		public void setPersonaRifVerbale(String personaRifVerbale) {
			this.personaRifVerbale = CaronteUtils.convertToUpperCase(personaRifVerbale);
		}		
		public String getDichPersRifVerbale() {
			return dichPersRifVerbale;
		}
		public void setDichPersRifVerbale(String dichPersRifVerbale) {
			this.dichPersRifVerbale = CaronteUtils.convertToUpperCase(dichPersRifVerbale);
		}
		public String getCognomeCustode() {
			return cognomeCustode;
		}
		public void setCognomeCustode(String cognomeCustode) {
			this.cognomeCustode = CaronteUtils.convertToUpperCase(cognomeCustode);
		}
		public String getNomeCustode() {
			return nomeCustode;
		}
		public void setNomeCustode(String nomeCustode) {
			this.nomeCustode = CaronteUtils.convertToUpperCase(nomeCustode);
		}
		public Long getIdProvNascitaCustode() {
			return idProvNascitaCustode;
		}
		public void setIdProvNascitaCustode(Long idProvNascitaCustode) {
			this.idProvNascitaCustode = idProvNascitaCustode;
		}
		public Long getIdComNascitaCustode() {
			return idComNascitaCustode;
		}
		public void setIdComNascitaCustode(Long idComNascitaCustode) {
			this.idComNascitaCustode = idComNascitaCustode;
		}
		public Long getIdProvResidCustode() {
			return idProvResidCustode;
		}
		public void setIdProvResidCustode(Long idProvResidCustode) {
			this.idProvResidCustode = idProvResidCustode;
		}
		public Long getIdComResidCustode() {
			return idComResidCustode;
		}
		public void setIdComResidCustode(Long idComResidCustode) {
			this.idComResidCustode = idComResidCustode;
		}
		public String getIndirResidCustode() {
			return indirResidCustode;
		}
		public void setIndirResidCustode(String indirResidCustode) {
			this.indirResidCustode = CaronteUtils.convertToUpperCase(indirResidCustode);
		}
		public String getTipoDocIdentificazCustode() {
			return tipoDocIdentificazCustode;
		}
		public void setTipoDocIdentificazCustode(String tipoDocIdentificazCustode) {
			this.tipoDocIdentificazCustode = CaronteUtils.convertToUpperCase(tipoDocIdentificazCustode);
		}
		public String getNumDocIdentificazCustode() {
			return numDocIdentificazCustode;
		}
		public void setNumDocIdentificazCustode(String numDocIdentificazCustode) {
			this.numDocIdentificazCustode = CaronteUtils.convertToUpperCase(numDocIdentificazCustode);
		}
		public Long getIdQualificaCustode() {
			return idQualificaCustode;
		}
		public void setIdQualificaCustode(Long idQualificaCustode) {
			this.idQualificaCustode = idQualificaCustode;
		}
		public String getPrescrizioniCustode() {
			return prescrizioniCustode;
		}
		public void setPrescrizioniCustode(String prescrizioniCustode) {
			this.prescrizioniCustode = CaronteUtils.convertToUpperCase(prescrizioniCustode);
		}		
		public String getNumVerbConstatazMisuraUff() {
			return numVerbConstatazMisuraUff;
		}
		public void setNumVerbConstatazMisuraUff(String numVerbConstatazMisuraUff) {
			this.numVerbConstatazMisuraUff = CaronteUtils.convertToUpperCase(numVerbConstatazMisuraUff);
		}
		public Long[] getIdsIspettoreConstMisura() {
			return idsIspettoreConstMisura;
		}
		public void setIdsIspettoreConstMisura(Long[] idsIspettoreConstMisura) {
			this.idsIspettoreConstMisura = idsIspettoreConstMisura;
		}
		public String getFlEsitoMisura() {
			return flEsitoMisura;
		}
		public void setFlEsitoMisura(String flEsitoMisura) {
			this.flEsitoMisura = flEsitoMisura;
		}
		public Date getDataConstMisura() {
			return dataConstMisura;
		}
		public void setDataConstMisura(Date dataConstMisura) {
			this.dataConstMisura = dataConstMisura;
		}
		public String getOraConstMisura() {
			return oraConstMisura;
		}
		public void setOraConstMisura(String oraConstMisura) {
			this.oraConstMisura = oraConstMisura;
		}
		public String getNoteConstMisura() {
			return noteConstMisura;
		}
		public void setNoteConstMisura(String noteConstMisura) {
			this.noteConstMisura = CaronteUtils.convertToUpperCase(noteConstMisura);
		}
		public String getPersonaRifVerbaleConst() {
			return personaRifVerbaleConst;
		}
		public void setPersonaRifVerbaleConst(String personaRifVerbaleConst) {
			this.personaRifVerbaleConst = CaronteUtils.convertToUpperCase(personaRifVerbaleConst);
		}		
		public String getDichPersRifVerbaleConst() {
			return dichPersRifVerbaleConst;
		}
		public void setDichPersRifVerbaleConst(String dichPersRifVerbaleConst) {
			this.dichPersRifVerbaleConst = CaronteUtils.convertToUpperCase(dichPersRifVerbaleConst);
		}
		public String getNotePersRifVerbaleConst() {
			return notePersRifVerbaleConst;
		}
		public void setNotePersRifVerbaleConst(String notePersRifVerbaleConst) {
			this.notePersRifVerbaleConst = CaronteUtils.convertToUpperCase(notePersRifVerbaleConst);
		}
		public String getPersonaPresenteConst() {
			return personaPresenteConst;
		}
		public void setPersonaPresenteConst(String personaPresenteConst) {
			this.personaPresenteConst = CaronteUtils.convertToUpperCase(personaPresenteConst);
		}
		public Long getIdQualificaPersonaPresenteConst() {
			return idQualificaPersonaPresenteConst;
		}
		public void setIdQualificaPersonaPresenteConst(Long idQualificaPersonaPresenteConst) {
			this.idQualificaPersonaPresenteConst = idQualificaPersonaPresenteConst;
		}
		public String getLettereMisura() {
			return lettereMisura;
		}
		public void setLettereMisura(String lettereMisura) {
			this.lettereMisura = CaronteUtils.convertToUpperCase(lettereMisura);
		}
		public Date getDataMisuraEntro() {
			return dataMisuraEntro;
		}
		public void setDataMisuraEntro(Date dataMisuraEntro) {
			this.dataMisuraEntro = dataMisuraEntro;
		}
		public String getModalita() {
			return modalita;
		}
		public void setModalita(String modalita) {
			this.modalita = CaronteUtils.convertToUpperCase(modalita);
		}
		public Date getDataConsegnaDisp() {
			return dataConsegnaDisp;
		}
		public void setDataConsegnaDisp(Date dataConsegnaDisp) {
			this.dataConsegnaDisp = dataConsegnaDisp;
		}
		public Long getIdTipoRespConsegnaDisp() {
			return idTipoRespConsegnaDisp;
		}
		public void setIdTipoRespConsegnaDisp(Long idTipoRespConsegnaDisp) {
			this.idTipoRespConsegnaDisp = idTipoRespConsegnaDisp;
		}
		public Long getIdTipoRespConsegnaConst() {
			return idTipoRespConsegnaConst;
		}
		public void setIdTipoRespConsegnaConst(Long idTipoRespConsegnaConst) {
			this.idTipoRespConsegnaConst = idTipoRespConsegnaConst;
		}
		public Date getDataConsegnaConst() {
			return dataConsegnaConst;
		}
		public void setDataConsegnaConst(Date dataConsegnaConst) {
			this.dataConsegnaConst = dataConsegnaConst;
		}
		public void setIdOrgNocMotivoMisura(Long idOrgNocMotivoMisura) {
			this.idOrgNocMotivoMisura = idOrgNocMotivoMisura;
		}
		public Long getIdOrgNocMotivoMisura() {
			return idOrgNocMotivoMisura;
		}
		public String getNoteNonConformita() {
			return noteNonConformita;
		}
		public void setNoteNonConformita(String noteNonConformita) {
			this.noteNonConformita = CaronteUtils.convertToUpperCase(noteNonConformita);
		}		
		public String[] getLatitudine() {
			return latitudine;
		}
		public void setLatitudine(String[] latitudine) {
			this.latitudine = latitudine;
		}
		public String[] getLongitudine() {
			return longitudine;
		}
		public void setLongitudine(String[] longitudine) {
			this.longitudine = longitudine;
		}		
		public String[] getIdGenereMisura() {
			return idGenereMisura;
		}
		public void setIdGenereMisura(String[] idGenereMisura) {
			this.idGenereMisura = idGenereMisura;
		}
		public String[] getIdOrganismoNocivoMisura() {
			return idOrganismoNocivoMisura;
		}
		public void setIdOrganismoNocivoMisura(String[] idOrganismoNocivoMisura) {
			this.idOrganismoNocivoMisura = idOrganismoNocivoMisura;

		}
		public Long getIdMisuraUfficiale() {
			return idMisuraUfficiale;
		}
		public void setIdMisuraUfficiale(Long idMisuraUfficiale) {
			this.idMisuraUfficiale = idMisuraUfficiale;
		}
		public String getTipologiaAttivita() {
			return tipologiaAttivita;
		}
		public void setTipologiaAttivita(String tipologiaAttivita) {
			this.tipologiaAttivita = CaronteUtils.convertToUpperCase(tipologiaAttivita);
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
		public String getNumeroRdp() {
			return numeroRdp;
		}
		public void setNumeroRdp(String numeroRdp) {
			this.numeroRdp = CaronteUtils.convertToUpperCase(numeroRdp);
		}
		public String getIdConstatazionePresente() {
			return idConstatazionePresente;
		}
		public void setIdConstatazionePresente(String idConstatazionePresente) {
			this.idConstatazionePresente = CaronteUtils.convertToUpperCase(idConstatazionePresente);
		}
		public List<MisuraDTO> getMisure() {
			return misure;
		}
		public void setMisure(List<MisuraDTO> misure) {
			this.misure = misure;
		}
		public String getNoteMisuraDisp() {
			return noteMisuraDisp;
		}
		public void setNoteMisuraDisp(String noteMisuraDisp) {
			this.noteMisuraDisp = CaronteUtils.convertToUpperCase(noteMisuraDisp);
		}
		public Date getDataNascitaCustode() {
			return dataNascitaCustode;
		}
		public void setDataNascitaCustode(Date dataNascitaCustode) {
			this.dataNascitaCustode = dataNascitaCustode;
		}		
		public String[] getNoteMonit() {
			return noteMonit;
		}
		public void setNoteMonit(String[] noteMonit) {
			this.noteMonit = noteMonit;
		}
		public Long getIdOrgNocMotivoMonit() {
			return idOrgNocMotivoMonit;
		}
		public void setIdOrgNocMotivoMonit(Long idOrgNocMotivoMonit) {
			this.idOrgNocMotivoMonit = idOrgNocMotivoMonit;
		}
		public String getAltroMotivoMonit() {
			return altroMotivoMonit;
		}
		public void setAltroMotivoMonit(String altroMotivoMonit) {
			this.altroMotivoMonit = CaronteUtils.convertToUpperCase(altroMotivoMonit);
		}
		public String getGenereMonit() {
			return genereMonit;
		}
		public void setGenereMonit(String genereMonit) {
			this.genereMonit = CaronteUtils.convertToUpperCase(genereMonit);
		}
		public String[] getIdSpecieMonit() {
			return idSpecieMonit;
		}
		public void setIdSpecieMonit(String[] idSpecieMonit) {
			this.idSpecieMonit = idSpecieMonit;
		}
		public String[] getNumeroPianteMonit() {
			return numeroPianteMonit;
		}
		public void setNumeroPianteMonit(String[] numeroPianteMonit) {
			this.numeroPianteMonit = numeroPianteMonit;
		}
		public String[] getDenomGenereMonit() {
			return denomGenereMonit;
		}
		public void setDenomGenereMonit(String[] denomGenereMonit) {
			this.denomGenereMonit = denomGenereMonit;
		}
		public String[] getIdGenereMonit() {
			return idGenereMonit;
		}
		public void setIdGenereMonit(String[] idGenereMonit) {
			this.idGenereMonit = idGenereMonit;
		}
		public String[] getIdOrganismoNocivoMonit() {
			return idOrganismoNocivoMonit;
		}
		public void setIdOrganismoNocivoMonit(String[] idOrganismoNocivoMonit) {
			this.idOrganismoNocivoMonit = idOrganismoNocivoMonit;
		}		
		public String[] getOraInizioMonit() {
			return oraInizioMonit;
		}
		public void setOraInizioMonit(String[] oraInizioMonit) {
			this.oraInizioMonit = oraInizioMonit;
		}
		public String[] getOraFineMonit() {
			return oraFineMonit;
		}
		public void setOraFineMonit(String[] oraFineMonit) {
			this.oraFineMonit = oraFineMonit;
		}
		public String getLatitudineM() {
			return latitudineM;
		}
		public void setLatitudineM(String latitudineM) {
			this.latitudineM = CaronteUtils.convertToUpperCase(latitudineM);
		}
		public String getLongitudineM() {
			return longitudineM;
		}
		public void setLongitudineM(String longitudineM) {
			this.longitudineM = CaronteUtils.convertToUpperCase(longitudineM);
		}
		public String getNoteM() {
			return noteM;
		}
		public void setNoteM(String noteM) {
			this.noteM = CaronteUtils.convertToUpperCase(noteM);
		}
		public Long getIdMonitCofinanziato() {
			return idMonitCofinanziato;
		}
		public void setIdMonitCofinanziato(Long idMonitCofinanziato) {
			this.idMonitCofinanziato = idMonitCofinanziato;
		}
		public List<MonitCofinanziatoDTO> getMonitoraggiCofinanziati() {
			return monitoraggiCofinanziati;
		}
		public void setMonitoraggiCofinanziati(List<MonitCofinanziatoDTO> monitoraggiCofinanziati) {
			this.monitoraggiCofinanziati = monitoraggiCofinanziati;
		}
		public Date getDataEmissioneDocumento() {
			return dataEmissioneDocumento;
		}
		public void setDataEmissioneDocumento(Date dataEmissioneDocumento) {
			this.dataEmissioneDocumento = dataEmissioneDocumento;
		}
		public String getOrgEmissioneDocumento() {
			return orgEmissioneDocumento;
		}
		public void setOrgEmissioneDocumento(String orgEmissioneDocumento) {
			this.orgEmissioneDocumento = CaronteUtils.convertToUpperCase(orgEmissioneDocumento);
		}

		public String getCodiceFitok() {
			return codiceFitok;
		}

		public void setCodiceFitok(String codiceFitok) {
			this.codiceFitok = CaronteUtils.convertToUpperCase(codiceFitok);
		}

		public BigDecimal getTariffa() {
			return tariffa;
		}

		public void setTariffa(BigDecimal tariffa) {
			this.tariffa = tariffa;
		}

		public boolean isTabDocumentale() {
			return tabDocumentale;
		}
		public void setTabDocumentale(boolean tabDocumentale) {
			this.tabDocumentale = tabDocumentale;
		}
		public boolean isTabIdentita() {
			return tabIdentita;
		}
		public void setTabIdentita(boolean tabIdentita) {
			this.tabIdentita = tabIdentita;
		}
		public boolean isTabFisico() {
			return tabFisico;
		}
		public void setTabFisico(boolean tabFisico) {
			this.tabFisico = tabFisico;

		}
		public Long getIdVersioneControllo() {
			return idVersioneControllo;
		}
		public void setIdVersioneControllo(Long idVersioneControllo) {
			this.idVersioneControllo = idVersioneControllo;
		}
		public Long[] getOrgNoc() {
			return orgNoc;
		}
		
		public void setOrgNoc(Long[] orgNoc) {
			this.orgNoc = orgNoc;
		}
		public String getNoteEsitoControllo() {
			return noteEsitoControllo;
		}
		public void setNoteEsitoControllo(String noteEsitoControllo) {
			this.noteEsitoControllo = CaronteUtils.convertToUpperCase(noteEsitoControllo);
		}
		public String getFlMisuraUfficiale() {
			return flMisuraUfficiale;
		}
		public void setFlMisuraUfficiale(String flMisuraUfficiale) {
			this.flMisuraUfficiale = flMisuraUfficiale;
		}
		public String getNumMisuraUfficiale() {
			return numMisuraUfficiale;
		}
		public void setNumMisuraUfficiale(String numMisuraUfficiale) {
			this.numMisuraUfficiale = CaronteUtils.convertToUpperCase(numMisuraUfficiale);
		}
		public String getFlMotivoMisuraUfficiale() {
			return flMotivoMisuraUfficiale;
		}
		public void setFlMotivoMisuraUfficiale(String flMotivoMisuraUfficiale) {
			this.flMotivoMisuraUfficiale = flMotivoMisuraUfficiale;
		}
		public String getNoteMotivoMisuraUfficiale() {
			return noteMotivoMisuraUfficiale;
		}
		public void setNoteMotivoMisuraUfficiale(String noteMotivoMisuraUfficiale) {
			this.noteMotivoMisuraUfficiale = CaronteUtils.convertToUpperCase(noteMotivoMisuraUfficiale);
		}	
		public String getFlPrescrizioni() {
			return flPrescrizioni;
		}
		public void setFlPrescrizioni(String flPrescrizioni) {
			this.flPrescrizioni = flPrescrizioni;
		}
		public String getNotePrescrizioni() {
			return notePrescrizioni;
		}
		public void setNotePrescrizioni(String notePrescrizioni) {
			this.notePrescrizioni = CaronteUtils.convertToUpperCase(notePrescrizioni);
		}
		public String getFlSanzioneAmministrativaEmessa() {
			return flSanzioneAmministrativaEmessa;
		}
		public void setFlSanzioneAmministrativaEmessa(String flSanzioneAmministrativaEmessa) {
			this.flSanzioneAmministrativaEmessa = flSanzioneAmministrativaEmessa;
		}
		public String getNoteSanzioneAmministrativaEmessa() {
			return noteSanzioneAmministrativaEmessa;
		}
		public void setNoteSanzioneAmministrativaEmessa(String noteSanzioneAmministrativaEmessa) {
			this.noteSanzioneAmministrativaEmessa = CaronteUtils.convertToUpperCase(noteSanzioneAmministrativaEmessa);
		}
		public String getFlSanzioneAmministrativaProposta() {
			return flSanzioneAmministrativaProposta;
		}
		public void setFlSanzioneAmministrativaProposta(String flSanzioneAmministrativaProposta) {
			this.flSanzioneAmministrativaProposta = flSanzioneAmministrativaProposta;
		}
		public String getNoteSanzioneAmministrativaProposta() {
			return noteSanzioneAmministrativaProposta;
		}
		public void setNoteSanzioneAmministrativaProposta(String noteSanzioneAmministrativaProposta) {
			this.noteSanzioneAmministrativaProposta = CaronteUtils.convertToUpperCase(noteSanzioneAmministrativaProposta);
		}

		public Long[] getIdsMaterialeSementi() {
			return idsMaterialeSementi;
		}
		public void setIdsMaterialeSementi(Long[] idsMaterialeSementi) {
			this.idsMaterialeSementi = idsMaterialeSementi;
		}		
		public String[] getNumPianteSintomatiche() {
			return numPianteSintomatiche;
		}
		public void setNumPianteSintomatiche(String[] numPianteSintomatiche) {
			this.numPianteSintomatiche = numPianteSintomatiche;
		}		
		public String getGenereFisico() {
			return genereFisico;
		}
		public void setGenereFisico(String genereFisico) {
			this.genereFisico = CaronteUtils.convertToUpperCase(genereFisico);
		}
		public Long getIdGenereFisico() {
			return idGenereFisico;
		}
		public void setIdGenereFisico(Long idGenereFisico) {
			this.idGenereFisico = idGenereFisico;
		}
		public String[] getSpecieFisico() {
			return specieFisico;
		}
		public void setSpecieFisico(String[] specieFisico) {
			this.specieFisico = specieFisico;
		}
		public String[] getOrgNociviFisico() {
			return orgNociviFisico;
		}
		public void setOrgNociviFisico(String[] orgNociviFisico) {
			this.orgNociviFisico = orgNociviFisico;
		}
		public String[] getNumeroPiante() {
			return numeroPiante;
		}
		public void setNumeroPiante(String[] numeroPiante) {
			this.numeroPiante = numeroPiante;
		}
		public String[] getIdControlloFisicoSpecie() {
			return idControlloFisicoSpecie;
		}
		public void setIdControlloFisicoSpecie(String[] idControlloFisicoSpecie) {
			this.idControlloFisicoSpecie = idControlloFisicoSpecie;
		}
		public List<SementeDTO> getSementi() {
			return sementi;
		}
		public void setSementi(List<SementeDTO> sementi) {
			this.sementi = sementi;
		}
		/**
		 * @return the flSacchetti
		 */
		public String getFlSacchetti() {
			return flSacchetti;
		}
		/**
		 * @param flSacchetti the flSacchetti to set
		 */
		public void setFlSacchetti(String flSacchetti) {
			this.flSacchetti = flSacchetti;
		}
		/**
		 * @return the flBanda
		 */
		public String getFlBanda() {
			return flBanda;
		}
		/**
		 * @param flBanda the flBanda to set
		 */
		public void setFlBanda(String flBanda) {
			this.flBanda = flBanda;
		}
		/**
		 * @return the flAnalisi
		 */
		public String getFlAnalisi() {
			return flAnalisi;
		}
		/**
		 * @param flAnalisi the flAnalisi to set
		 */
		public void setFlAnalisi(String flAnalisi) {
			this.flAnalisi = flAnalisi;
		}	
			
		public Long[] getIdsDaEliminare() {
			return idsDaEliminare;
		}
		public void setIdsDaEliminare(Long[] idsDaEliminare) {
			this.idsDaEliminare = idsDaEliminare;
		}	
		
			

		
}
