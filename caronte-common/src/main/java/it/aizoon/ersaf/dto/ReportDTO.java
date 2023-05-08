package it.aizoon.ersaf.dto;

import java.math.BigDecimal;
/**
 * @author alessandro.morra
 *
 */
import java.util.Date;

public class ReportDTO extends BaseDto {

	private Long idRichiesta;
	private Integer idStatoRichiesta;
	private String denomSpedizioniere;
	private String descStatoRichiesta;
	private Date dataEsecuzione;
	private String identifMezzoTrasporto;
	private String numeroCertificato;
	private String denomMittente;
	private String denomDestinatario;
	private BigDecimal tariffa;

	// Vivai
	private Long idComunicazione;
	private Long idStatoComunicazione;
	private String descStatoComunicazione;
	private String ragioneSociale;
	private String codiceFiscale;
	private String partitaIva;
	private String codiceRuop;
	private Long numeroPiante;
	private String descCentroAziendale;
	private String denomGenere;
	private String denomSpecie;
	private String descTipoComunicazione;
	private String codCentroAziendale;
	private Date dataCreazione;
	private Date dataInoltro;
	private Long idOrgNocivo;
	private String denomOrgNocivo;

	// Per report controlli

	private String provincia;
	private String comune;
	private String ispettore;
	private String organismoNocivo;
	private String esito;
	private String note;
	private Long idSpedizioniere;
	private String tipologiaAttivita;
	private String tipologiaAttivitaDom;
	private String cuaa;
	private String indirizzo;
	private Date dataControllo;
	private Long numeroVerbale;
	private String numRdp;
	private String campioniPrelevati;

	// Per report campioni

	//private String codCampione;
	private String codCampioneInizio;
	private String codCampioneFine;
	private String descrizione;
	private String esitoRdp;
	private Date dataRdp;	
	private String descEstesa;
	private String cofinanziato;
	private String flRicercaAccertato;

	// Per report misura ufficiale

	private Date dataMisura;
	private String misuraApplicata;
	private String descOrgNocivo;
	private String numeroVerbaleCo;
	private Date dataConstatazione;
	private String esitoConstatazione;
	private String noteConstatazione;
	// Per report monitoraggio cofinaziato

	// Autorizzazione - Aziende
	private Long idDomanda;
	private Long idStatoAzienda;
	private String descStatoAzienda;
	private Long idTipoTpedizioniere;
	private String denomTipoSpedizioniere;
	private String siglaProvincia;
	private String denomComune;
	private String cap;
	private String tipologiaAttivitaAnnotazioni;
	private String telefono;
	private String fax;
	private String cellulare;
	private String email;
	private String pec;
	private Date dataRegistrazioneRuop;
	private String codiceFitok;
	private String numeroProtocollo;
	private Date dataProtocollo;
	
	// Autorizzazione - Centri aziendali
	
	private Long idTipologiaPassaporto;
	private String descTipologiaPassaporto;
	private String referenteIspettore;
	private Date dataVariazioneStato;
	private Date dataRichiestaPassaporto;
	private Date dataVariazionePassaporto;
	private String denomCentroAziendale;

	public Long getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(Long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getDenomSpedizioniere() {
		return denomSpedizioniere;
	}

	public void setDenomSpedizioniere(String denomSpedizioniere) {
		this.denomSpedizioniere = denomSpedizioniere;
	}

	public String getDescStatoRichiesta() {
		return descStatoRichiesta;
	}

	public void setDescStatoRichiesta(String descStatoRichiesta) {
		this.descStatoRichiesta = descStatoRichiesta;
	}

	public Date getDataEsecuzione() {
		return dataEsecuzione;
	}

	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}

	public String getIdentifMezzoTrasporto() {
		return identifMezzoTrasporto;
	}

	public void setIdentifMezzoTrasporto(String identifMezzoTrasporto) {
		this.identifMezzoTrasporto = identifMezzoTrasporto;
	}

	public String getDenomMittente() {
		return denomMittente;
	}

	public void setDenomMittente(String denomMittente) {
		this.denomMittente = denomMittente;
	}

	public String getNumeroCertificato() {
		return numeroCertificato;
	}

	public void setNumeroCertificato(String numeroCertificato) {
		this.numeroCertificato = numeroCertificato;
	}

	public String getDenomDestinatario() {
		return denomDestinatario;
	}

	public void setDenomDestinatario(String denomDestinatario) {
		this.denomDestinatario = denomDestinatario;
	}

	public BigDecimal getTariffa() {
		return tariffa;
	}

	public void setTariffa(BigDecimal tariffa) {
		this.tariffa = tariffa;
	}

	public Integer getIdStatoRichiesta() {
		return idStatoRichiesta;
	}

	public void setIdStatoRichiesta(Integer idStatoRichiesta) {
		this.idStatoRichiesta = idStatoRichiesta;
	}

	public Long getIdComunicazione() {
		return idComunicazione;
	}

	public void setIdComunicazione(Long idComunicazione) {
		this.idComunicazione = idComunicazione;
	}

	public Long getIdStatoComunicazione() {
		return idStatoComunicazione;
	}

	public void setIdStatoComunicazione(Long idStatoComunicazione) {
		this.idStatoComunicazione = idStatoComunicazione;
	}

	public String getDescStatoComunicazione() {
		return descStatoComunicazione;
	}

	public void setDescStatoComunicazione(String descStatoComunicazione) {
		this.descStatoComunicazione = descStatoComunicazione;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceRuop() {
		return codiceRuop;
	}

	public void setCodiceRuop(String codiceRuop) {
		this.codiceRuop = codiceRuop;
	}

	public Long getNumeroPiante() {
		return numeroPiante;
	}

	public void setNumeroPiante(Long numeroPiante) {
		this.numeroPiante = numeroPiante;
	}

	public String getDenomGenere() {
		return denomGenere;
	}

	public void setDenomGenere(String denomGenere) {
		this.denomGenere = denomGenere;
	}

	public String getDenomSpecie() {
		return denomSpecie;
	}

	public void setDenomSpecie(String denomSpecie) {
		this.denomSpecie = denomSpecie;
	}

	public String getDescCentroAziendale() {
		return descCentroAziendale;
	}

	public void setDescCentroAziendale(String descCentroAziendale) {
		this.descCentroAziendale = descCentroAziendale;
	}

	public String getDescTipoComunicazione() {
		return descTipoComunicazione;
	}

	public void setDescTipoComunicazione(String descTipoComunicazione) {
		this.descTipoComunicazione = descTipoComunicazione;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getCodCentroAziendale() {
		return codCentroAziendale;
	}

	public void setCodCentroAziendale(String codCentroAziendale) {
		this.codCentroAziendale = codCentroAziendale;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Long getIdDomanda() {
		return idDomanda;
	}

	public void setIdDomanda(Long idDomanda) {
		this.idDomanda = idDomanda;
	}

	public String getDescStatoAzienda() {
		return descStatoAzienda;
	}

	public void setDescStatoAzienda(String descStatoAzienda) {
		this.descStatoAzienda = descStatoAzienda;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public String getDenomComune() {
		return denomComune;
	}

	public void setDenomComune(String denomComune) {
		this.denomComune = denomComune;
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

	public String getTipologiaAttivitaAnnotazioni() {
		return tipologiaAttivitaAnnotazioni;
	}

	public void setTipologiaAttivitaAnnotazioni(String tipologiaAttivitaAnnotazioni) {
		this.tipologiaAttivitaAnnotazioni = tipologiaAttivitaAnnotazioni;
	}

	public String getTipologiaAttivita() {
		return tipologiaAttivita;
	}

	public void setTipologiaAttivita(String tipologiaAttivita) {
		this.tipologiaAttivita = tipologiaAttivita;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	public Date getDataRegistrazioneRuop() {
		return dataRegistrazioneRuop;
	}

	public void setDataRegistrazioneRuop(Date dataRegistrazioneRuop) {
		this.dataRegistrazioneRuop = dataRegistrazioneRuop;
	}

	public Long getIdStatoAzienda() {
		return idStatoAzienda;
	}

	public void setIdStatoAzienda(Long idStatoAzienda) {
		this.idStatoAzienda = idStatoAzienda;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getIspettore() {
		return ispettore;
	}

	public void setIspettore(String ispettore) {
		this.ispettore = ispettore;
	}

	public String getOrganismoNocivo() {
		return organismoNocivo;
	}

	public void setOrganismoNocivo(String organismoNocivo) {
		this.organismoNocivo = organismoNocivo;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getIdSpedizioniere() {
		return idSpedizioniere;
	}

	public void setIdSpedizioniere(Long idSpedizioniere) {
		this.idSpedizioniere = idSpedizioniere;
	}

	public String getTipologiaAttivitaDom() {
		return tipologiaAttivitaDom;
	}

	public void setTipologiaAttivitaDom(String tipologiaAttivitaDom) {
		this.tipologiaAttivitaDom = tipologiaAttivitaDom;
	}

	public String getCuaa() {
		return cuaa;
	}

	public void setCuaa(String cuaa) {
		this.cuaa = cuaa;
	}

	public Date getDataControllo() {
		return dataControllo;
	}

	public void setDataControllo(Date dataControllo) {
		this.dataControllo = dataControllo;
	}

	public Long getNumeroVerbale() {
		return numeroVerbale;
	}

	public void setNumeroVerbale(Long numeroVerbale) {
		this.numeroVerbale = numeroVerbale;
	}

	/*public String getCodCampione() {
		return codCampione;
	}

	public void setCodCampione(String codCampione) {
		this.codCampione = codCampione;
	}*/

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getEsitoRdp() {
		return esitoRdp;
	}

	public void setEsitoRdp(String esitoRdp) {
		this.esitoRdp = esitoRdp;
	}

	public Date getDataRdp() {
		return dataRdp;
	}

	public void setDataRdp(Date dataRdp) {
		this.dataRdp = dataRdp;
	}

	public String getDescEstesa() {
		return descEstesa;
	}

	public void setDescEstesa(String descEstesa) {
		this.descEstesa = descEstesa;
	}

	public String getCofinanziato() {
		return cofinanziato;
	}

	public void setCofinanziato(String cofinanziato) {
		this.cofinanziato = cofinanziato;
	}

	public Date getDataMisura() {
		return dataMisura;
	}

	public void setDataMisura(Date dataMisura) {
		this.dataMisura = dataMisura;
	}

	public String getMisuraApplicata() {
		return misuraApplicata;
	}

	public void setMisuraApplicata(String misuraApplicata) {
		this.misuraApplicata = misuraApplicata;
	}

	public String getNumeroVerbaleCo() {
		return numeroVerbaleCo;
	}

	public void setNumeroVerbaleCo(String numeroVerbaleCo) {
		this.numeroVerbaleCo = numeroVerbaleCo;
	}

	public String getDescOrgNocivo() {
		return descOrgNocivo;
	}

	public void setDescOrgNocivo(String descOrgNocivo) {
		this.descOrgNocivo = descOrgNocivo;
	}

	public Date getDataConstatazione() {
		return dataConstatazione;
	}

	public void setDataConstatazione(Date dataConstatazione) {
		this.dataConstatazione = dataConstatazione;
	}

	public String getEsitoConstatazione() {
		return esitoConstatazione;
	}

	public void setEsitoConstatazione(String esitoConstatazione) {
		this.esitoConstatazione = esitoConstatazione;
	}

	public String getNoteConstatazione() {
		return noteConstatazione;
	}

	public void setNoteConstatazione(String noteConstatazione) {
		this.noteConstatazione = noteConstatazione;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNumRdp() {
		return numRdp;
	}



	public void setNumRdp(String numRdp) {
		this.numRdp = numRdp;
	}

	public Long getIdTipoTpedizioniere() {
		return idTipoTpedizioniere;
	}



	public void setIdTipoTpedizioniere(Long idTipoTpedizioniere) {
		this.idTipoTpedizioniere = idTipoTpedizioniere;
	}

	public String getDenomTipoSpedizioniere() {
		return denomTipoSpedizioniere;
	}

	public void setDenomTipoSpedizioniere(String denomTipoSpedizioniere) {
		this.denomTipoSpedizioniere = denomTipoSpedizioniere;
	}

	public Long getIdTipologiaPassaporto() {
		return idTipologiaPassaporto;
	}

	public void setIdTipologiaPassaporto(Long idTipologiaPassaporto) {
		this.idTipologiaPassaporto = idTipologiaPassaporto;
	}

	public String getDescTipologiaPassaporto() {
		return descTipologiaPassaporto;
	}

	public void setDescTipologiaPassaporto(String descTipologiaPassaporto) {
		this.descTipologiaPassaporto = descTipologiaPassaporto;
	}

	public String getReferenteIspettore() {
		return referenteIspettore;
	}

	public void setReferenteIspettore(String referenteIspettore) {
		this.referenteIspettore = referenteIspettore;
	}

	public Date getDataVariazioneStato() {
		return dataVariazioneStato;
	}

	public void setDataVariazioneStato(Date dataVariazioneStato) {
		this.dataVariazioneStato = dataVariazioneStato;
	}

	public Date getDataRichiestaPassaporto() {
		return dataRichiestaPassaporto;
	}

	public void setDataRichiestaPassaporto(Date dataRichiestaPassaporto) {
		this.dataRichiestaPassaporto = dataRichiestaPassaporto;
	}

	public Date getDataVariazionePassaporto() {
		return dataVariazionePassaporto;
	}

	public void setDataVariazionePassaporto(Date dataVariazionePassaporto) {
		this.dataVariazionePassaporto = dataVariazionePassaporto;
	}

	public String getCodiceFitok() {
		return codiceFitok;
	}

	public void setCodiceFitok(String codiceFitok) {
		this.codiceFitok = codiceFitok;
	}

	public Date getDataInoltro() {
		return dataInoltro;
	}

	public void setDataInoltro(Date dataInoltro) {
		this.dataInoltro = dataInoltro;
	}

	public Long getIdOrgNocivo() {
		return idOrgNocivo;
	}

	public void setIdOrgNocivo(Long idOrgNocivo) {
		this.idOrgNocivo = idOrgNocivo;
	}

	public String getDenomOrgNocivo() {
		return denomOrgNocivo;
	}

	public void setDenomOrgNocivo(String denomOrgNocivo) {
		this.denomOrgNocivo = denomOrgNocivo;
	}

	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}

	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	public Date getDataProtocollo() {
		return dataProtocollo;
	}

	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

	/**
	 * @return the flRicercaAccertato
	 */
	public String getFlRicercaAccertato() {
		return flRicercaAccertato;
	}

	/**
	 * @param flRicercaAccertato the flRicercaAccertato to set
	 */
	public void setFlRicercaAccertato(String flRicercaAccertato) {
		this.flRicercaAccertato = flRicercaAccertato;
	}

	public String getDenomCentroAziendale() {
		return denomCentroAziendale;
	}

	public void setDenomCentroAziendale(String denomCentroAziendale) {
		this.denomCentroAziendale = denomCentroAziendale;
	}


	public String getCodCampioneFine() {
		return codCampioneFine;
	}

	public void setCodCampioneFine(String codCampioneFine) {
		this.codCampioneFine = codCampioneFine;
	}

	public String getCodCampioneInizio() {
		return codCampioneInizio;
	}

	public void setCodCampioneInizio(String codCampioneInizio) {
		this.codCampioneInizio = codCampioneInizio;
	}

	/**
	 * @return the campioniPrelevati
	 */
	public String getCampioniPrelevati() {
		return campioniPrelevati;
	}

	/**
	 * @param campioniPrelevati the campioniPrelevati to set
	 */
	public void setCampioniPrelevati(String campioniPrelevati) {
		this.campioniPrelevati = campioniPrelevati;
	}
	
}