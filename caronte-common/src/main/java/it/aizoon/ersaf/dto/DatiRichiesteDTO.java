package it.aizoon.ersaf.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DatiRichiesteDTO extends BaseDto {

	private static final long serialVersionUID = -262024594035554827L;

	private Long idRichiesta;
	private String numeroRichiesta;
	private String numeroCertificato;
	private String ente;
	private String statoRichiesta;
	private Date dataInoltro;
	private Date dataEsecuzione;
	private String utenteEsecuzione;
	private Date dataRilascioCertificato;
	private String numeroTessera;
	private String funzionarioRilascioCertificato;
	private String mittenteNome;
	private String mittenteIndirizzo;
	private String mittenteCitta;
	private String mittenteStato;
	private String destinatarioNome;
	private String destinatarioIndirizzo;
	private String destinatarioComune;
	private String destinatarioProvincia;
	private String destinatarioStato;
	private String statoOrigine;
	private String modoTrasporto;
	private String documentoTrasporto;
	private String dogana;
	private String entrataRichiesta;
	private String trattamento;
	private String trattamentoSostanza;
	private String trattamentoDurata;
	private String trattamentoConcentrazione;
	private Date trattamentoData;
	private String trattamentoInformazioni;
	private String codiceRup;
	private String regioneRup;
	private String luogoRilascio;
	private BigDecimal totaleTariffe;
	private BigDecimal tariffa;
	private String note;
	private String tipoMerce;
	private String genere;
	private String unitaMisura;
	private BigDecimal quantita;
	private List<MerceRichiestaDto> merci;

	public BigDecimal getTotaleTariffe() {
		return totaleTariffe;
	}

	public void setTotaleTariffe(BigDecimal totaleTariffe) {
		this.totaleTariffe = totaleTariffe;
	}

	public String getDestinatarioStato() {
		return destinatarioStato;
	}

	public void setDestinatarioStato(String destinatarioStato) {
		this.destinatarioStato = destinatarioStato;
	}

	public String getNumeroTessera() {
		return numeroTessera;
	}

	public void setNumeroTessera(String numeroTessera) {
		this.numeroTessera = numeroTessera;
	}

	public Long getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(Long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getNumeroRichiesta() {
		return numeroRichiesta;
	}

	public void setNumeroRichiesta(String numeroRichiesta) {
		this.numeroRichiesta = numeroRichiesta;
	}

	public String getNumeroCertificato() {
		return numeroCertificato;
	}

	public void setNumeroCertificato(String numeroCertificato) {
		this.numeroCertificato = numeroCertificato;
	}

	public String getEnte() {
		return ente;
	}

	public void setEnte(String ente) {
		this.ente = ente;
	}

	public String getStatoRichiesta() {
		return statoRichiesta;
	}

	public void setStatoRichiesta(String statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
	}

	public Date getDataInoltro() {
		return dataInoltro;
	}

	public void setDataInoltro(Date dataInoltro) {
		this.dataInoltro = dataInoltro;
	}

	public Date getDataEsecuzione() {
		return dataEsecuzione;
	}

	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}

	public String getUtenteEsecuzione() {
		return utenteEsecuzione;
	}

	public void setUtenteEsecuzione(String utenteEsecuzione) {
		this.utenteEsecuzione = utenteEsecuzione;
	}

	public Date getDataRilascioCertificato() {
		return dataRilascioCertificato;
	}

	public void setDataRilascioCertificato(Date dataRilascioCertificato) {
		this.dataRilascioCertificato = dataRilascioCertificato;
	}

	public String getFunzionarioRilascioCertificato() {
		return funzionarioRilascioCertificato;
	}

	public void setFunzionarioRilascioCertificato(String funzionarioRilascioCertificato) {
		this.funzionarioRilascioCertificato = funzionarioRilascioCertificato;
	}

	public String getMittenteNome() {
		return mittenteNome;
	}

	public void setMittenteNome(String mittenteNome) {
		this.mittenteNome = mittenteNome;
	}

	public String getMittenteIndirizzo() {
		return mittenteIndirizzo;
	}

	public void setMittenteIndirizzo(String mittenteIndirizzo) {
		this.mittenteIndirizzo = mittenteIndirizzo;
	}

	public String getMittenteCitta() {
		return mittenteCitta;
	}

	public void setMittenteCitta(String mittenteCitta) {
		this.mittenteCitta = mittenteCitta;
	}

	public String getMittenteStato() {
		return mittenteStato;
	}

	public void setMittenteStato(String mittenteStato) {
		this.mittenteStato = mittenteStato;
	}

	public String getDestinatarioNome() {
		return destinatarioNome;
	}

	public void setDestinatarioNome(String destinatarioNome) {
		this.destinatarioNome = destinatarioNome;
	}

	public String getDestinatarioIndirizzo() {
		return destinatarioIndirizzo;
	}

	public void setDestinatarioIndirizzo(String destinatarioIndirizzo) {
		this.destinatarioIndirizzo = destinatarioIndirizzo;
	}

	public String getDestinatarioComune() {
		return destinatarioComune;
	}

	public void setDestinatarioComune(String destinatarioComune) {
		this.destinatarioComune = destinatarioComune;
	}

	public String getDestinatarioProvincia() {
		return destinatarioProvincia;
	}

	public void setDestinatarioProvincia(String destinatarioProvincia) {
		this.destinatarioProvincia = destinatarioProvincia;
	}

	public String getStatoOrigine() {
		return statoOrigine;
	}

	public void setStatoOrigine(String statoOrigine) {
		this.statoOrigine = statoOrigine;
	}

	public String getModoTrasporto() {
		return modoTrasporto;
	}

	public void setModoTrasporto(String modoTrasporto) {
		this.modoTrasporto = modoTrasporto;
	}

	public String getDocumentoTrasporto() {
		return documentoTrasporto;
	}

	public void setDocumentoTrasporto(String documentoTrasporto) {
		this.documentoTrasporto = documentoTrasporto;
	}

	public String getDogana() {
		return dogana;
	}

	public void setDogana(String dogana) {
		this.dogana = dogana;
	}

	public String getEntrataRichiesta() {
		return entrataRichiesta;
	}

	public void setEntrataRichiesta(String entrataRichiesta) {
		this.entrataRichiesta = entrataRichiesta;
	}

	public String getTrattamento() {
		return trattamento;
	}

	public void setTrattamento(String trattamento) {
		this.trattamento = trattamento;
	}

	public String getTrattamentoSostanza() {
		return trattamentoSostanza;
	}

	public void setTrattamentoSostanza(String trattamentoSostanza) {
		this.trattamentoSostanza = trattamentoSostanza;
	}

	public String getTrattamentoDurata() {
		return trattamentoDurata;
	}

	public void setTrattamentoDurata(String trattamentoDurata) {
		this.trattamentoDurata = trattamentoDurata;
	}

	public String getTrattamentoConcentrazione() {
		return trattamentoConcentrazione;
	}

	public void setTrattamentoConcentrazione(String trattamentoConcentrazione) {
		this.trattamentoConcentrazione = trattamentoConcentrazione;
	}

	public Date getTrattamentoData() {
		return trattamentoData;
	}

	public void setTrattamentoData(Date trattamentoData) {
		this.trattamentoData = trattamentoData;
	}

	public String getTrattamentoInformazioni() {
		return trattamentoInformazioni;
	}

	public void setTrattamentoInformazioni(String trattamentoInformazioni) {
		this.trattamentoInformazioni = trattamentoInformazioni;
	}

	public String getCodiceRup() {
		return codiceRup;
	}

	public void setCodiceRup(String codiceRup) {
		this.codiceRup = codiceRup;
	}

	public String getRegioneRup() {
		return regioneRup;
	}

	public void setRegioneRup(String regioneRup) {
		this.regioneRup = regioneRup;
	}

	public String getLuogoRilascio() {
		return luogoRilascio;
	}

	public void setLuogoRilascio(String luogoRilascio) {
		this.luogoRilascio = luogoRilascio;
	}

	public BigDecimal getTariffa() {
		return tariffa;
	}

	public void setTariffa(BigDecimal tariffa) {
		this.tariffa = tariffa;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTipoMerce() {
		return tipoMerce;
	}

	public void setTipoMerce(String tipoMerce) {
		this.tipoMerce = tipoMerce;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getUnitaMisura() {
		return unitaMisura;
	}

	public void setUnitaMisura(String unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

	public BigDecimal getQuantita() {
		return quantita;
	}

	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}

	public List<MerceRichiestaDto> getMerci() {
		return merci;
	}

	public void setMerci(List<MerceRichiestaDto> merci) {
		this.merci = merci;
	}

}