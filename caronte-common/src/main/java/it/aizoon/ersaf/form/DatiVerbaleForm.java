package it.aizoon.ersaf.form;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.dto.MerceRichiestaVerbaleDto;
import it.aizoon.ersaf.util.CaronteUtils;

public class DatiVerbaleForm {

	// DATI RICHIESTA
	@NumberFormat(style = Style.NUMBER)
	private Long idRichiesta;
	private String codRichiesta;
	private Long idCertificato;

	// DATI VERBALE
	@NumberFormat(style = Style.NUMBER)
	private Long idVerbale;

	// DATI ISPEZIONE
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataIspezione;
	private String oraInizioIspezione;
	private String oraFineIspezione;
	private String magazzinoDoganale;
	private String personaRiferimento;
	private String personaRiferimentoRuolo;
	private String altraDocumentazione;

	// Dati Spedizione
	private boolean tipoProdottoPianteVive;
	private boolean tipoProdottoPartiPianteVive;
	private boolean tipoProdottoSementi;
	private boolean tipoProdottoSemi;
	private boolean tipoProdottoTerra;
	private boolean tipoProdottoCorteccia;
	private boolean tipoProdottoLegname;
	private boolean tipoProdottoFrutti;
	private boolean tipoProdottoFiori;
	private boolean tipoProdottoImballaggi;
	private boolean tipoProdottoAltro;
	private String tipoProdottoAltroNote;
	private List<MerceRichiestaVerbaleDto> listaMerceRichiesta;

	// Controllo ed Esito
	private boolean controlloDocumentale;
	private String controlloDocumentaleCB;
	private String noteControlloDocumentaleCB;

	private boolean controlloIdentita;
	private String controlloIdentitaCB;
	private String noteControlloIdentitaCB;

	private boolean controlloFitosanitario;
	private String controlloFitosanitarioCB;
	private boolean controlloFitosanitarioIspVisiva;
	private boolean controlloFitosanitarioIspStrum;
	private String noteControlloFitosanitarioCB;

	private String prelievoPerRicerca;
	private String codiceCampione;

	private String visioneAnalisiRB;
	private String consultoResponsabileRB;
	private String allegaEvidenzaRB;

	private String termIspezioneNullaOsta;
	private String termIspezioneNullaOstaNote;
	private String numeroCertificato;

	private boolean termIspezioneMisUff;
	private String termIspezioneMisUffNote;

	// Misure ufficiali
	private boolean misUffA;
	private String misUffANote;
	private boolean misUffB;
	private String misUffBNote;
	private boolean misUffC;
	private String misUffCNote;
	private boolean misUffD;
	private String misUffDNote;
	private boolean misUffE;
	private boolean misUffF;
	private String misUffFNote;

	// Custodia
	private String custResponsabileMerce;
	private String custDocumentoRespMerce;
	private String custRuoloRespMerce;
	private String custLocaliMerce;

	// Dichiarazioni e note
	private String dichNoteRespVerbale;
	private String dichNoteRuoloRespVerbale;
	private String dichNoteDichiarazioneRespVerb;
	private String dichNoteNoteRespVerb;

	// Accordion
	private String accordionSelezionato;

	public Long getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(Long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getCodRichiesta() {
		return codRichiesta;
	}

	public void setCodRichiesta(String codRichiesta) {
		this.codRichiesta = CaronteUtils.convertToUpperCase(codRichiesta);
	}

	public Long getIdCertificato() {
		return idCertificato;
	}

	public void setIdCertificato(Long idCertificato) {
		this.idCertificato = idCertificato;
	}

	public Long getIdVerbale() {
		return idVerbale;
	}

	public void setIdVerbale(Long idVerbale) {
		this.idVerbale = idVerbale;
	}

	public Date getDataIspezione() {
		return dataIspezione;
	}

	public void setDataIspezione(Date dataIspezione) {
		this.dataIspezione = dataIspezione;
	}

	public String getOraInizioIspezione() {
		return oraInizioIspezione;
	}

	public void setOraInizioIspezione(String oraInizioIspezione) {
		this.oraInizioIspezione = CaronteUtils.convertToUpperCase(oraInizioIspezione);
	}

	public String getOraFineIspezione() {
		return oraFineIspezione;
	}

	public void setOraFineIspezione(String oraFineIspezione) {
		this.oraFineIspezione = CaronteUtils.convertToUpperCase(oraFineIspezione);
	}

	public String getMagazzinoDoganale() {
		return magazzinoDoganale;
	}

	public void setMagazzinoDoganale(String magazzinoDoganale) {
		this.magazzinoDoganale = CaronteUtils.convertToUpperCase(magazzinoDoganale);
	}

	public String getPersonaRiferimento() {
		return personaRiferimento;
	}

	public void setPersonaRiferimento(String personaRiferimento) {
		this.personaRiferimento = CaronteUtils.convertToUpperCase(personaRiferimento);
	}

	public String getPersonaRiferimentoRuolo() {
		return personaRiferimentoRuolo;
	}

	public void setPersonaRiferimentoRuolo(String personaRiferimentoRuolo) {
		this.personaRiferimentoRuolo = CaronteUtils.convertToUpperCase(personaRiferimentoRuolo);
	}

	public boolean isTipoProdottoPianteVive() {
		return tipoProdottoPianteVive;
	}

	public void setTipoProdottoPianteVive(boolean tipoProdottoPianteVive) {
		this.tipoProdottoPianteVive = tipoProdottoPianteVive;
	}

	public boolean isTipoProdottoPartiPianteVive() {
		return tipoProdottoPartiPianteVive;
	}

	public void setTipoProdottoPartiPianteVive(boolean tipoProdottoPartiPianteVive) {
		this.tipoProdottoPartiPianteVive = tipoProdottoPartiPianteVive;
	}

	public boolean isTipoProdottoSementi() {
		return tipoProdottoSementi;
	}

	public void setTipoProdottoSementi(boolean tipoProdottoSementi) {
		this.tipoProdottoSementi = tipoProdottoSementi;
	}

	public boolean isTipoProdottoSemi() {
		return tipoProdottoSemi;
	}

	public void setTipoProdottoSemi(boolean tipoProdottoSemi) {
		this.tipoProdottoSemi = tipoProdottoSemi;
	}

	public boolean isTipoProdottoTerra() {
		return tipoProdottoTerra;
	}

	public void setTipoProdottoTerra(boolean tipoProdottoTerra) {
		this.tipoProdottoTerra = tipoProdottoTerra;
	}

	public boolean isTipoProdottoCorteccia() {
		return tipoProdottoCorteccia;
	}

	public void setTipoProdottoCorteccia(boolean tipoProdottoCorteccia) {
		this.tipoProdottoCorteccia = tipoProdottoCorteccia;
	}

	public boolean isTipoProdottoLegname() {
		return tipoProdottoLegname;
	}

	public void setTipoProdottoLegname(boolean tipoProdottoLegname) {
		this.tipoProdottoLegname = tipoProdottoLegname;
	}

	public boolean isTipoProdottoFrutti() {
		return tipoProdottoFrutti;
	}

	public void setTipoProdottoFrutti(boolean tipoProdottoFrutti) {
		this.tipoProdottoFrutti = tipoProdottoFrutti;
	}

	public boolean isTipoProdottoFiori() {
		return tipoProdottoFiori;
	}

	public void setTipoProdottoFiori(boolean tipoProdottoFiori) {
		this.tipoProdottoFiori = tipoProdottoFiori;
	}

	public boolean isTipoProdottoImballaggi() {
		return tipoProdottoImballaggi;
	}

	public void setTipoProdottoImballaggi(boolean tipoProdottoImballaggi) {
		this.tipoProdottoImballaggi = tipoProdottoImballaggi;
	}

	public boolean isTipoProdottoAltro() {
		return tipoProdottoAltro;
	}

	public void setTipoProdottoAltro(boolean tipoProdottoAltro) {
		this.tipoProdottoAltro = tipoProdottoAltro;
	}

	public String getTipoProdottoAltroNote() {
		return tipoProdottoAltroNote;
	}

	public void setTipoProdottoAltroNote(String tipoProdottoAltroNote) {
		this.tipoProdottoAltroNote = CaronteUtils.convertToUpperCase(tipoProdottoAltroNote);
	}

	public boolean isControlloDocumentale() {
		return controlloDocumentale;
	}

	public void setControlloDocumentale(boolean controlloDocumentale) {
		this.controlloDocumentale = controlloDocumentale;
	}

	public String getControlloDocumentaleCB() {
		return controlloDocumentaleCB;
	}

	public void setControlloDocumentaleCB(String controlloDocumentaleCB) {
		this.controlloDocumentaleCB = controlloDocumentaleCB;
	}

	public String getNoteControlloDocumentaleCB() {
		return noteControlloDocumentaleCB;
	}

	public void setNoteControlloDocumentaleCB(String noteControlloDocumentaleCB) {
		this.noteControlloDocumentaleCB = CaronteUtils.convertToUpperCase(noteControlloDocumentaleCB);
	}

	public boolean isControlloIdentita() {
		return controlloIdentita;
	}

	public void setControlloIdentita(boolean controlloIdentita) {
		this.controlloIdentita = controlloIdentita;
	}

	public String getControlloIdentitaCB() {
		return controlloIdentitaCB;
	}

	public void setControlloIdentitaCB(String controlloIdentitaCB) {
		this.controlloIdentitaCB = controlloIdentitaCB;
	}

	public String getNoteControlloIdentitaCB() {
		return noteControlloIdentitaCB;
	}

	public void setNoteControlloIdentitaCB(String noteControlloIdentitaCB) {
		this.noteControlloIdentitaCB = CaronteUtils.convertToUpperCase(noteControlloIdentitaCB);
	}

	public boolean isControlloFitosanitario() {
		return controlloFitosanitario;
	}

	public void setControlloFitosanitario(boolean controlloFitosanitario) {
		this.controlloFitosanitario = controlloFitosanitario;
	}

	public String getControlloFitosanitarioCB() {
		return controlloFitosanitarioCB;
	}

	public void setControlloFitosanitarioCB(String controlloFitosanitarioCB) {
		this.controlloFitosanitarioCB = controlloFitosanitarioCB;
	}

	public boolean isControlloFitosanitarioIspVisiva() {
		return controlloFitosanitarioIspVisiva;
	}

	public void setControlloFitosanitarioIspVisiva(boolean controlloFitosanitarioIspVisiva) {
		this.controlloFitosanitarioIspVisiva = controlloFitosanitarioIspVisiva;
	}

	public boolean isControlloFitosanitarioIspStrum() {
		return controlloFitosanitarioIspStrum;
	}

	public void setControlloFitosanitarioIspStrum(boolean controlloFitosanitarioIspStrum) {
		this.controlloFitosanitarioIspStrum = controlloFitosanitarioIspStrum;
	}

	public String getNoteControlloFitosanitarioCB() {
		return noteControlloFitosanitarioCB;
	}

	public void setNoteControlloFitosanitarioCB(String noteControlloFitosanitarioCB) {
		this.noteControlloFitosanitarioCB = CaronteUtils.convertToUpperCase(noteControlloFitosanitarioCB);
	}

	public String getPrelievoPerRicerca() {
		return prelievoPerRicerca;
	}

	public void setPrelievoPerRicerca(String prelievoPerRicerca) {
		this.prelievoPerRicerca = CaronteUtils.convertToUpperCase(prelievoPerRicerca);
	}

	public String getVisioneAnalisiRB() {
		return visioneAnalisiRB;
	}

	public void setVisioneAnalisiRB(String visioneAnalisiRB) {
		this.visioneAnalisiRB = visioneAnalisiRB;
	}

	public String getConsultoResponsabileRB() {
		return consultoResponsabileRB;
	}

	public void setConsultoResponsabileRB(String consultoResponsabileRB) {
		this.consultoResponsabileRB = consultoResponsabileRB;
	}

	public String getAllegaEvidenzaRB() {
		return allegaEvidenzaRB;
	}

	public void setAllegaEvidenzaRB(String allegaEvidenzaRB) {
		this.allegaEvidenzaRB = allegaEvidenzaRB;
	}

	public String getTermIspezioneNullaOsta() {
		return termIspezioneNullaOsta;
	}

	public void setTermIspezioneNullaOsta(String termIspezioneNullaOsta) {
		this.termIspezioneNullaOsta = termIspezioneNullaOsta;
	}

	public String getTermIspezioneNullaOstaNote() {
		return termIspezioneNullaOstaNote;
	}

	public void setTermIspezioneNullaOstaNote(String termIspezioneNullaOstaNote) {
		this.termIspezioneNullaOstaNote = CaronteUtils.convertToUpperCase(termIspezioneNullaOstaNote);
	}

	public String getNumeroCertificato() {
		return numeroCertificato;
	}

	public void setNumeroCertificato(String numeroCertificato) {
		this.numeroCertificato = CaronteUtils.convertToUpperCase(numeroCertificato);
	}

	public boolean isTermIspezioneMisUff() {
		return termIspezioneMisUff;
	}

	public void setTermIspezioneMisUff(boolean termIspezioneMisUff) {
		this.termIspezioneMisUff = termIspezioneMisUff;
	}

	public String getTermIspezioneMisUffNote() {
		return termIspezioneMisUffNote;
	}

	public void setTermIspezioneMisUffNote(String termIspezioneMisUffNote) {
		this.termIspezioneMisUffNote = CaronteUtils.convertToUpperCase(termIspezioneMisUffNote);
	}

	public boolean isMisUffA() {
		return misUffA;
	}

	public void setMisUffA(boolean misUffA) {
		this.misUffA = misUffA;
	}

	public String getMisUffANote() {
		return misUffANote;
	}

	public void setMisUffANote(String misUffANote) {
		this.misUffANote = CaronteUtils.convertToUpperCase(misUffANote);
	}

	public boolean isMisUffB() {
		return misUffB;
	}

	public void setMisUffB(boolean misUffB) {
		this.misUffB = misUffB;
	}

	public String getMisUffBNote() {
		return misUffBNote;
	}

	public void setMisUffBNote(String misUffBNote) {
		this.misUffBNote = CaronteUtils.convertToUpperCase(misUffBNote);
	}

	public boolean isMisUffC() {
		return misUffC;
	}

	public void setMisUffC(boolean misUffC) {
		this.misUffC = misUffC;
	}

	public String getMisUffCNote() {
		return misUffCNote;
	}

	public void setMisUffCNote(String misUffCNote) {
		this.misUffCNote = CaronteUtils.convertToUpperCase(misUffCNote);
	}

	public boolean isMisUffD() {
		return misUffD;
	}

	public void setMisUffD(boolean misUffD) {
		this.misUffD = misUffD;
	}

	public String getMisUffDNote() {
		return misUffDNote;
	}

	public void setMisUffDNote(String misUffDNote) {
		this.misUffDNote = CaronteUtils.convertToUpperCase(misUffDNote);
	}

	public boolean isMisUffE() {
		return misUffE;
	}

	public void setMisUffE(boolean misUffE) {
		this.misUffE = misUffE;
	}

	public boolean isMisUffF() {
		return misUffF;
	}

	public void setMisUffF(boolean misUffF) {
		this.misUffF = misUffF;
	}

	public String getMisUffFNote() {
		return misUffFNote;
	}

	public void setMisUffFNote(String misUffFNote) {
		this.misUffFNote = CaronteUtils.convertToUpperCase(misUffFNote);
	}

	public String getCustResponsabileMerce() {
		return custResponsabileMerce;
	}

	public void setCustResponsabileMerce(String custResponsabileMerce) {
		this.custResponsabileMerce = CaronteUtils.convertToUpperCase(custResponsabileMerce);
	}

	public String getCustDocumentoRespMerce() {
		return custDocumentoRespMerce;
	}

	public void setCustDocumentoRespMerce(String custDocumentoRespMerce) {
		this.custDocumentoRespMerce = CaronteUtils.convertToUpperCase(custDocumentoRespMerce);
	}

	public String getCustRuoloRespMerce() {
		return custRuoloRespMerce;
	}

	public void setCustRuoloRespMerce(String custRuoloRespMerce) {
		this.custRuoloRespMerce = CaronteUtils.convertToUpperCase(custRuoloRespMerce);
	}

	public String getCustLocaliMerce() {
		return custLocaliMerce;
	}

	public void setCustLocaliMerce(String custLocaliMerce) {
		this.custLocaliMerce = CaronteUtils.convertToUpperCase(custLocaliMerce);
	}

	public String getDichNoteRespVerbale() {
		return dichNoteRespVerbale;
	}

	public void setDichNoteRespVerbale(String dichNoteRespVerbale) {
		this.dichNoteRespVerbale = CaronteUtils.convertToUpperCase(dichNoteRespVerbale);
	}

	public String getDichNoteRuoloRespVerbale() {
		return dichNoteRuoloRespVerbale;
	}

	public void setDichNoteRuoloRespVerbale(String dichNoteRuoloRespVerbale) {
		this.dichNoteRuoloRespVerbale = CaronteUtils.convertToUpperCase(dichNoteRuoloRespVerbale);
	}

	public String getDichNoteDichiarazioneRespVerb() {
		return dichNoteDichiarazioneRespVerb;
	}

	public void setDichNoteDichiarazioneRespVerb(String dichNoteDichiarazioneRespVerb) {
		this.dichNoteDichiarazioneRespVerb = CaronteUtils.convertToUpperCase(dichNoteDichiarazioneRespVerb);
	}

	public String getDichNoteNoteRespVerb() {
		return dichNoteNoteRespVerb;
	}

	public void setDichNoteNoteRespVerb(String dichNoteNoteRespVerb) {
		this.dichNoteNoteRespVerb = CaronteUtils.convertToUpperCase(dichNoteNoteRespVerb);
	}

	public String getAccordionSelezionato() {
		return accordionSelezionato;
	}

	public void setAccordionSelezionato(String accordionSelezionato) {
		this.accordionSelezionato = CaronteUtils.convertToUpperCase(accordionSelezionato);
	}

	public String getAltraDocumentazione() {
		return altraDocumentazione;
	}

	public void setAltraDocumentazione(String altraDocumentazione) {
		this.altraDocumentazione = CaronteUtils.convertToUpperCase(altraDocumentazione);
	}

	public List<MerceRichiestaVerbaleDto> getListaMerceRichiesta() {
		return listaMerceRichiesta;
	}

	public void setListaMerceRichiesta(List<MerceRichiestaVerbaleDto> listaMerceRichiesta) {
		this.listaMerceRichiesta = listaMerceRichiesta;
	}

	public String getCodiceCampione() {
		return codiceCampione;
	}

	public void setCodiceCampione(String codiceCampione) {
		this.codiceCampione = CaronteUtils.convertToUpperCase(codiceCampione);
	}

}
