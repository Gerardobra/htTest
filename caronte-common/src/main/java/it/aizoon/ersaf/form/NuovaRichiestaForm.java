package it.aizoon.ersaf.form;

import java.math.BigDecimal;
import java.time.LocalTime;
/**
 * @author francesco.giuffrida
 *
 */
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.util.StringUtils;

import it.aizoon.ersaf.dto.CertificatoRichiestaDto;
import it.aizoon.ersaf.dto.DettaglioRichiestaDto;
import it.aizoon.ersaf.dto.MerceRichiestaDto;
import it.aizoon.ersaf.dto.TrattamentoRichiestaDto;
import it.aizoon.ersaf.dto.generati.CarTPagamento;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

public class NuovaRichiestaForm extends BaseForm {

	private int schedaSelezionata;
	private int schedaAbilitata;
	@NumberFormat(style = Style.NUMBER)
	private Long idTipoRichiesta;

	// DATI RICHIESTA
	@NumberFormat(style = Style.NUMBER)
	private Long idRichiesta;
	private String codRichiesta; // N.Richiesta (car_t_richiesta)
	@NumberFormat(style = Style.NUMBER)
	private Long idStatoRichiesta; // Stato Richiesta (car_t_richiesta)
	private String descStatoRichiesta;
	@NumberFormat(style = Style.NUMBER)
	private Long utenteResponsabile; // Utente Responsabile (car_t_richiesta)
	private String descUtenteResponsabile;
	private String utenteNome; // Utente Responsabile (car_t_utente)
	private String utenteCognome; // Utente Responsabile (car_t_utente)
	@NumberFormat(style = Style.NUMBER)
	private Long idSpedizioniere;
	private String spedizioniere; // Denominazione Spedizioniere
	@NumberFormat(style = Style.NUMBER)
	private Long idTipoSpedizioniere; // Tipo Spedizioniere
	private String denomTipoSpedizioniere;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataApertura; // Data apertura
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataUltimaModifica; // Data modifica
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInoltro; // Data inoltro
	private String note; // Note
	
	//nuovi campi export
	private String numeroCertificatoOriginale;

	// DATI MITTENTE
	private String denominazioneMittente; // Cognome e nome / Denominazione
	private String indirizzoMittente; // Indirizzo
	private String comuneMittente; // Citta
	@NumberFormat(style = Style.NUMBER)
	private Long idNazioneMittente; // Stato
	// nuovi campi export
	@NumberFormat(style = Style.NUMBER)
	private Long idProvinciaMittente;
	@NumberFormat(style = Style.NUMBER)
	private Long idComuneMittente;
	private String codiceRuopMittente;
	private String noteMittenteCertificato;

	// DATI DESTINATARIO
	private String denominazioneDestinatario; // Cognome e nome / Denominazione
	private String indirizzoDestinatario; // Indirizzo
	@NumberFormat(style = Style.NUMBER)
	private Long idProvincia; // Provincia (non usato in BackEnd)
	private String provinciaDestinatario; // (non usato in BackEnd)
	private String comuneDestinatario; // Comune
	private Long idNazioneDestinatario; // Stato
	@NumberFormat(style = Style.NUMBER)
	private Long idComuneIta;
	private String codiceRup;
	@NumberFormat(style = Style.NUMBER)
	private Long idRegioneRup;
	// nuovi campi export
	@NumberFormat(style = Style.NUMBER)
	private Long idStatoRupDestinatario;

	// DATI TRASPORTO
	private String numDocumentoTrasporto; // N. Documento di trasporto
											// (car_t_richiesta.identifMezzoTrasporto)
	@NumberFormat(style = Style.NUMBER)
	private Long idModoTrasporto; // Mezzo di trasporto
									// dichiarato(car_t_richiesta.id_modo_trasporto)
									// -> (car_d_modo_trasporto)
	@NumberFormat(style = Style.NUMBER)
  private Long idPuntoEntrataDichiarato;
	private String puntoEntrataDichiarato;
	private boolean spedizioneMultipla;
	private String numCertificatiCollegati;

	// nuovi campi export
	@NumberFormat(style = Style.NUMBER)
	private Long idNazioneProtVeg;
	private String luogoDeposito; // Luogo deposito (export)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInizioDisponibilitaMerce;
	//@DateTimeFormat(pattern = "HH:mm")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime oraInizioDisponibilitaMerce;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataPartenzaMerce;
	private String noteTrasporto;

	// DATI MERCE
	/*
	 * @NumberFormat(style = Style.NUMBER) private Long idNazioneOrigine; // Stato
	 * origine (car_r_merce_richiesta.id_nazione_origine)
	 * 
	 * @NumberFormat(style = Style.NUMBER) private Long idTipoProdotto; // Tipologia
	 * (car_r_tipo_prodotto)
	 * 
	 * @NumberFormat(style = Style.NUMBER) private Long idGenere; // Genere
	 * (car_d_genere) private Double quantitaMerce; // quantita
	 * (car_r_merce_richiesta )
	 */
	private String genereMerce;
	private List<MerceRichiestaDto> listaMerceRichiesta;

	// TARIFFA (SOLO LETTURA FRONT-END , non vengono presi in considerazione nel
	// beck-end)
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal tariffaIdentita;
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal tariffaDocumentali;
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal tariffaFitosanitari;
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal massimalePartite;
	@NumberFormat(style = Style.NUMBER)
	private Long numeroCertificati;
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal tariffaTotale;

	// DATI TRATTAMENTO (car_r_trattamento_richiesta)
	@NumberFormat(style = Style.NUMBER)
	private Long idTrattamento;
	private String descTrattamento;
	private String prodottoChimico;
	private String durata;
	private String temperatura;
	private String concentrazione;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataTrattamento;
	private String infoSupplementari;

	// DATI ESEGUI
	private Long idCertificatoRichiesta;
	private Long idTipoCertificato;
	private Long idLuogoEsecuzione;
	private Date dataEsecuzione;
	private Long idIspettore;
	private String tipoNumerazioneCertificato;
	private String codiceEnte;
	private String numeroCertificato;
	private String numeroCertificatoManuale;
	private String noteCertificato;
	private Long idIspettoreDocumentale;
	private Long idIspettoreIdentita;
	private Long idIspettoreFitosanitario;

	// nuovi campi export
	@NumberFormat(style = Style.NUMBER)
	private Long idProvinciaEsecuzione;
	@NumberFormat(style = Style.NUMBER)
	private Long idComuneEsecuzione;
	private Long idIspettoreFirmatario;
	private String dichiarazioneSupplementare;
	private String tipoCertificatoPrecedente;
	@NumberFormat(style = Style.NUMBER)
	private Long idMerceColliOriginali;
	private boolean certificatoFitoOriginale;
	private boolean ispezioneSupplementare;

	// DATI PAGAMENTO
	private Long idPagamentoRichiesta;
	private String mittentePagamento;
	private String destinatarioPagamento; // (no data in back-end .costante.)
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal importoTotaleDovuto; // (no data in back-end .costante.)
	@NumberFormat(style = Style.NUMBER)
	private Long idTipoPagamento; // Tipologia Pagamento
									// (car_t_pagamento.id_mezzo_pagamento)
	private String tipoPagamento; // (car_d_mezzo_pagamento.desc_mezzo_pagamento)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	private String numeroDocumento; // (car_t_pagamento.numero_documento)
	private boolean visualizzaModalInoltra;
	
	// ALLEGATI
	private String nomeFileAllegato;
	private byte[] allegato;
	
	private String nomeFileAllegatoCertificato;
	
	// FLAG ABILITAZIONE INOLTRA
	private boolean abilitaInoltra;

	public int getSchedaSelezionata() {
		return schedaSelezionata;
	}

	public void setSchedaSelezionata(int schedaSelezionata) {
		this.schedaSelezionata = schedaSelezionata;
		
		if (schedaSelezionata > getSchedaAbilitata()) {
		  setSchedaAbilitata(schedaSelezionata);
		}
	}

	public int getSchedaAbilitata() {
    return schedaAbilitata;
  }

  public void setSchedaAbilitata(int schedaAbilitata) {
    this.schedaAbilitata = schedaAbilitata;
  }

  public Long getIdTipoRichiesta() {
		return idTipoRichiesta;
	}

	public void setIdTipoRichiesta(Long idTipoRichiesta) {
		this.idTipoRichiesta = idTipoRichiesta;
	}

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

	public Long getIdStatoRichiesta() {
		return idStatoRichiesta;
	}

	public void setIdStatoRichiesta(Long idStatoRichiesta) {
		this.idStatoRichiesta = idStatoRichiesta;
	}

	public String getDescStatoRichiesta() {
		return descStatoRichiesta;
	}

	public void setDescStatoRichiesta(String descStatoRichiesta) {
		this.descStatoRichiesta = CaronteUtils.convertToUpperCase(descStatoRichiesta);
	}

	public Long getUtenteResponsabile() {
		return utenteResponsabile;
	}

	public void setUtenteResponsabile(Long utenteResponsabile) {
		this.utenteResponsabile = utenteResponsabile;
	}

	public String getDescUtenteResponsabile() {
		return descUtenteResponsabile;
	}

	public void setDescUtenteResponsabile(String descUtenteResponsabile) {
		this.descUtenteResponsabile = CaronteUtils.convertToUpperCase(descUtenteResponsabile);
	}

	public String getUtenteNome() {
		return utenteNome;
	}

	public void setUtenteNome(String utenteNome) {
		this.utenteNome = CaronteUtils.convertToUpperCase(utenteNome);
	}

	public String getUtenteCognome() {
		return utenteCognome;
	}

	public void setUtenteCognome(String utenteCognome) {
		this.utenteCognome = CaronteUtils.convertToUpperCase(utenteCognome);
	}

	public Long getIdSpedizioniere() {
		return idSpedizioniere;
	}

	public void setIdSpedizioniere(Long idSpedizioniere) {
		this.idSpedizioniere = idSpedizioniere;
	}

	public String getSpedizioniere() {
		return spedizioniere;
	}

	public void setSpedizioniere(String spedizioniere) {
		this.spedizioniere = CaronteUtils.convertToUpperCase(spedizioniere);
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
		this.denomTipoSpedizioniere = CaronteUtils.convertToUpperCase(denomTipoSpedizioniere);
	}

	public Date getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}

	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public Date getDataInoltro() {
		return dataInoltro;
	}

	public void setDataInoltro(Date dataInoltro) {
		this.dataInoltro = dataInoltro;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = CaronteUtils.convertToUpperCase(note);
	}

	public String getLuogoDeposito() {
		return luogoDeposito;
	}

	public void setLuogoDeposito(String luogoDeposito) {
		this.luogoDeposito = CaronteUtils.convertToUpperCase(luogoDeposito);
	}

	public String getDenominazioneMittente() {
		return denominazioneMittente;
	}

	public void setDenominazioneMittente(String denominazioneMittente) {
		this.denominazioneMittente = CaronteUtils.convertToUpperCase(denominazioneMittente);
	}

	public String getIndirizzoMittente() {
		return indirizzoMittente;
	}

	public void setIndirizzoMittente(String indirizzoMittente) {
		this.indirizzoMittente = CaronteUtils.convertToUpperCase(indirizzoMittente);
	}

	public String getComuneMittente() {
		return comuneMittente;
	}

	public void setComuneMittente(String comuneMittente) {
		this.comuneMittente = CaronteUtils.convertToUpperCase(comuneMittente);
	}

	public Long getIdNazioneMittente() {
		return idNazioneMittente;
	}

	public void setIdNazioneMittente(Long idNazioneMittente) {
		this.idNazioneMittente = idNazioneMittente;
	}

	public Long getIdComuneIta() {
		return idComuneIta;
	}

	public void setIdComuneIta(Long idComuneIta) {
		this.idComuneIta = idComuneIta;
	}

	public String getCodiceRup() {
		return codiceRup;
	}

	public void setCodiceRup(String codiceRup) {
		this.codiceRup = CaronteUtils.convertToUpperCase(codiceRup);
	}

	public Long getIdRegioneRup() {
		return idRegioneRup;
	}

	public void setIdRegioneRup(Long idRegioneRup) {
		this.idRegioneRup = idRegioneRup;
	}

	public String getDenominazioneDestinatario() {
		return denominazioneDestinatario;
	}

	public void setDenominazioneDestinatario(String denominazioneDestinatario) {
		this.denominazioneDestinatario = CaronteUtils.convertToUpperCase(denominazioneDestinatario);
	}

	public String getIndirizzoDestinatario() {
		return indirizzoDestinatario;
	}

	public void setIndirizzoDestinatario(String indirizzoDestinatario) {
		this.indirizzoDestinatario = CaronteUtils.convertToUpperCase(indirizzoDestinatario);
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getProvinciaDestinatario() {
		return provinciaDestinatario;
	}

	public void setProvinciaDestinatario(String provinciaDestinatario) {
		this.provinciaDestinatario = provinciaDestinatario;
	}

	public String getComuneDestinatario() {
		return comuneDestinatario;
	}

	public void setComuneDestinatario(String comuneDestinatario) {
		this.comuneDestinatario = CaronteUtils.convertToUpperCase(comuneDestinatario);
	}

	public Long getIdNazioneDestinatario() {
		return idNazioneDestinatario;
	}

	public void setIdNazioneDestinatario(Long idNazioneDestinatario) {
		this.idNazioneDestinatario = idNazioneDestinatario;
	}

	public String getNumDocumentoTrasporto() {
		return numDocumentoTrasporto;
	}

	public void setNumDocumentoTrasporto(String numDocumentoTrasporto) {
		this.numDocumentoTrasporto = numDocumentoTrasporto;
	}

	public Long getIdModoTrasporto() {
		return idModoTrasporto;
	}

	public void setIdModoTrasporto(Long idModoTrasporto) {
		this.idModoTrasporto = idModoTrasporto;
	}

	public boolean isSpedizioneMultipla() {
		return spedizioneMultipla;
	}

	public void setSpedizioneMultipla(boolean spedizioneMultipla) {
		this.spedizioneMultipla = spedizioneMultipla;
	}

	public String getNumCertificatiCollegati() {
		return numCertificatiCollegati;
	}

	public void setNumCertificatiCollegati(String numCertificatiCollegati) {
		this.numCertificatiCollegati = numCertificatiCollegati;
	}

	public String getGenereMerce() {
		return genereMerce;
	}

	public void setGenereMerce(String genereMerce) {
		this.genereMerce = CaronteUtils.convertToUpperCase(genereMerce);
	}

	public List<MerceRichiestaDto> getListaMerceRichiesta() {
		return listaMerceRichiesta;
	}

	public void setListaMerceRichiesta(List<MerceRichiestaDto> listaMerceRichiesta) {
		this.listaMerceRichiesta = listaMerceRichiesta;
	}

	public BigDecimal getTariffaIdentita() {
		return tariffaIdentita;
	}

	public void setTariffaIdentita(BigDecimal tariffaIdentita) {
		this.tariffaIdentita = tariffaIdentita;
	}

	public BigDecimal getTariffaDocumentali() {
		return tariffaDocumentali;
	}

	public void setTariffaDocumentali(BigDecimal tariffaDocumentali) {
		this.tariffaDocumentali = tariffaDocumentali;
	}

	public BigDecimal getTariffaFitosanitari() {
		return tariffaFitosanitari;
	}

	public void setTariffaFitosanitari(BigDecimal tariffaFitosanitari) {
		this.tariffaFitosanitari = tariffaFitosanitari;
	}

	public BigDecimal getMassimalePartite() {
		return massimalePartite;
	}

	public void setMassimalePartite(BigDecimal massimalePartite) {
		this.massimalePartite = massimalePartite;
	}

	public Long getNumeroCertificati() {
		return numeroCertificati;
	}

	public void setNumeroCertificati(Long numeroCertificati) {
		this.numeroCertificati = numeroCertificati;
	}

	public BigDecimal getTariffaTotale() {
		return tariffaTotale;
	}

	public void setTariffaTotale(BigDecimal tariffaTotale) {
		this.tariffaTotale = tariffaTotale;
	}

	public Long getIdTrattamento() {
		return idTrattamento;
	}

	public void setIdTrattamento(Long idTrattamento) {
		this.idTrattamento = idTrattamento;
	}

	public String getDescTrattamento() {
		return descTrattamento;
	}

	public void setDescTrattamento(String descTrattamento) {
		this.descTrattamento = descTrattamento;
	}

	public String getProdottoChimico() {
		return prodottoChimico;
	}

	public void setProdottoChimico(String prodottoChimico) {
		this.prodottoChimico = CaronteUtils.convertToUpperCase(prodottoChimico);
	}

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = CaronteUtils.convertToUpperCase(durata);
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = CaronteUtils.convertToUpperCase(temperatura);
	}

	public String getConcentrazione() {
		return concentrazione;
	}

	public void setConcentrazione(String concentrazione) {
		this.concentrazione = CaronteUtils.convertToUpperCase(concentrazione);
	}

	public Date getDataTrattamento() {
		return dataTrattamento;
	}

	public void setDataTrattamento(Date dataTrattamento) {
		this.dataTrattamento = dataTrattamento;
	}

	public String getInfoSupplementari() {
		return infoSupplementari;
	}

	public void setInfoSupplementari(String infoSupplementari) {
		this.infoSupplementari = CaronteUtils.convertToUpperCase(infoSupplementari);
	}

	public Long getIdCertificatoRichiesta() {
		return idCertificatoRichiesta;
	}

	public void setIdCertificatoRichiesta(Long idCertificatoRichiesta) {
		this.idCertificatoRichiesta = idCertificatoRichiesta;
	}

	public Long getIdTipoCertificato() {
		return idTipoCertificato;
	}

	public void setIdTipoCertificato(Long idTipoCertificato) {
		this.idTipoCertificato = idTipoCertificato;
	}

	public Long getIdLuogoEsecuzione() {
		return idLuogoEsecuzione;
	}

	public void setIdLuogoEsecuzione(Long idLuogoEsecuzione) {
		this.idLuogoEsecuzione = idLuogoEsecuzione;
	}

	public Date getDataEsecuzione() {
		return dataEsecuzione;
	}

	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}

	public Long getIdIspettore() {
		return idIspettore;
	}

	public void setIdIspettore(Long idIspettore) {
		this.idIspettore = idIspettore;
	}

	public String getTipoNumerazioneCertificato() {
		return tipoNumerazioneCertificato;
	}

	public void setTipoNumerazioneCertificato(String tipoNumerazioneCertificato) {
		this.tipoNumerazioneCertificato = tipoNumerazioneCertificato;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = CaronteUtils.convertToUpperCase(codiceEnte);
	}

	public String getNumeroCertificato() {
		return numeroCertificato;
	}

	public void setNumeroCertificato(String numeroCertificato) {
		this.numeroCertificato = CaronteUtils.convertToUpperCase(numeroCertificato);
	}

	public String getNumeroCertificatoManuale() {
		return numeroCertificatoManuale;
	}

	public void setNumeroCertificatoManuale(String numeroCertificatoManuale) {
		this.numeroCertificatoManuale = CaronteUtils.convertToUpperCase(numeroCertificatoManuale);
	}

	public String getNoteCertificato() {
		return noteCertificato;
	}

	public void setNoteCertificato(String noteCertificato) {
		this.noteCertificato = CaronteUtils.convertToUpperCase(noteCertificato);
	}

	public Long getIdIspettoreDocumentale() {
		return idIspettoreDocumentale;
	}

	public void setIdIspettoreDocumentale(Long idIspettoreDocumentale) {
		this.idIspettoreDocumentale = idIspettoreDocumentale;
	}

	public Long getIdIspettoreIdentita() {
		return idIspettoreIdentita;
	}

	public void setIdIspettoreIdentita(Long idIspettoreIdentita) {
		this.idIspettoreIdentita = idIspettoreIdentita;
	}

	public Long getIdIspettoreFitosanitario() {
		return idIspettoreFitosanitario;
	}

	public void setIdIspettoreFitosanitario(Long idIspettoreFitosanitario) {
		this.idIspettoreFitosanitario = idIspettoreFitosanitario;
	}

	public Long getIdPagamentoRichiesta() {
		return idPagamentoRichiesta;
	}

	public void setIdPagamentoRichiesta(Long idPagamentoRichiesta) {
		this.idPagamentoRichiesta = idPagamentoRichiesta;
	}

	public String getMittentePagamento() {
		return mittentePagamento;
	}

	public void setMittentePagamento(String mittentePagamento) {
		this.mittentePagamento = CaronteUtils.convertToUpperCase(mittentePagamento);
	}

	public String getDestinatarioPagamento() {
		return destinatarioPagamento;
	}

	public void setDestinatarioPagamento(String destinatarioPagamento) {
		this.destinatarioPagamento = CaronteUtils.convertToUpperCase(destinatarioPagamento);
	}

	public BigDecimal getImportoTotaleDovuto() {
		return importoTotaleDovuto;
	}

	public void setImportoTotaleDovuto(BigDecimal importoTotaleDovuto) {
		this.importoTotaleDovuto = importoTotaleDovuto;
	}

	public Long getIdTipoPagamento() {
		return idTipoPagamento;
	}

	public void setIdTipoPagamento(Long idTipoPagamento) {
		this.idTipoPagamento = idTipoPagamento;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = CaronteUtils.convertToUpperCase(numeroDocumento);
	}

	public boolean isVisualizzaModalInoltra() {
    return visualizzaModalInoltra;
  }

  public void setVisualizzaModalInoltra(boolean visualizzaModalInoltra) {
    this.visualizzaModalInoltra = visualizzaModalInoltra;
  }

  public String getNomeFileAllegato() {
		return nomeFileAllegato;
	}

	public void setNomeFileAllegato(String nomeFileAllegato) {
		this.nomeFileAllegato = nomeFileAllegato;
	}

	public byte[] getAllegato() {
		return allegato;
	}

	public void setAllegato(byte[] allegato) {
		this.allegato = allegato;
	}

	public void setDati(DettaglioRichiestaDto richiesta, TrattamentoRichiestaDto trattamento,
			CertificatoRichiestaDto certificato, CarTPagamento pagamento, Long idIspettore, String nomeFileCertificato) {
		if (richiesta == null) {
			return;
		}

		// Dati richiesta
		setIdRichiesta(richiesta.getIdRichiesta());
		setCodRichiesta(richiesta.getCodRichiesta());
		setIdTipoRichiesta(richiesta.getIdTipoRichiesta());
		setIdStatoRichiesta(richiesta.getIdStatoRichiesta());
		setDescStatoRichiesta(richiesta.getDescStatoRichiesta());
		setUtenteResponsabile(richiesta.getIdUtenteInsert());
		setDescUtenteResponsabile(richiesta.getUtenteNome() + " " + richiesta.getUtenteCognome());
		setUtenteNome(richiesta.getUtenteNome());
		setUtenteCognome(richiesta.getUtenteCognome());
		setIdTipoSpedizioniere(richiesta.getIdTipoSpedizioniere());
		setDenomTipoSpedizioniere(richiesta.getDenomTipoSpedizioniere());
		setIdSpedizioniere(richiesta.getIdSpedizioniere());
		setSpedizioniere(richiesta.getDenomSpedizioniere());
		setDataApertura(richiesta.getDataInsert());
		setDataUltimaModifica(richiesta.getDataUpdate());
		setDataInoltro(richiesta.getDataInoltro());
		setNote(richiesta.getNoteDatiRichiesta());
		
		if (CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(getIdTipoRichiesta())) {
			setNumeroCertificatoOriginale(richiesta.getNumeroCertificatoOriginale());
			setNomeFileAllegatoCertificato(nomeFileCertificato);
		}

		// Dati mittente
		setDenominazioneMittente(richiesta.getDenomMittente());
		setIndirizzoMittente(richiesta.getIndirizzoMittente());

		if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(getIdTipoRichiesta())) {
			setComuneMittente(richiesta.getDenomComuneEstMittente());
			setIdNazioneMittente(richiesta.getIdNazioneMittente());
		} else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(getIdTipoRichiesta())
				|| CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(getIdTipoRichiesta())) {
		  setIdProvinciaMittente(richiesta.getIdProvincia());
			setIdComuneMittente(richiesta.getIdComuneIta());
			setComuneMittente(richiesta.getDenomComuneIta());
			setCodiceRuopMittente(richiesta.getCodiceRupMittente());
			setNoteMittenteCertificato(richiesta.getNoteMittenteCertif());
		}

		// Dati destinatario
		setDenominazioneDestinatario(richiesta.getDenomDestinatario());
		setIndirizzoDestinatario(richiesta.getIndirizzoDestinatario());
		setCodiceRup(richiesta.getCodiceRupDestinatario());

		if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(getIdTipoRichiesta())) {
			setIdProvincia(richiesta.getIdProvincia());
			setIdComuneIta(richiesta.getIdComuneIta());
			setIdRegioneRup(richiesta.getIdRegioneRupDestinatario());
		} else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(getIdTipoRichiesta())
				|| CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(getIdTipoRichiesta())) {
			setComuneDestinatario(richiesta.getDenomComuneEstDestinatario());
			setIdStatoRupDestinatario(richiesta.getIdNazioneRupDestinatario());
			setIdNazioneDestinatario(richiesta.getIdNazioneDestinatario());
		}

		// Dati trasporto
		setNumDocumentoTrasporto(richiesta.getIdentifMezzoTrasporto());
		setIdModoTrasporto(richiesta.getIdModoTrasporto());
		setIdPuntoEntrataDichiarato(richiesta.getIdUfficioDoganaleUtilizzato());
		setPuntoEntrataDichiarato(richiesta.getPuntoEntrataDichiarato());
		
		if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(getIdTipoRichiesta())) {
			
			if (richiesta.getSpedizioneMultipla() != null && richiesta.getSpedizioneMultipla()) {
				setSpedizioneMultipla(true);
				setNumCertificatiCollegati("" + richiesta.getNumeroCertificatiRichiesti());
			}
		} else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(getIdTipoRichiesta())
				|| CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(getIdTipoRichiesta())) {
			setIdNazioneProtVeg(richiesta.getIdNazioneProtVegDestinat());
			setLuogoDeposito(richiesta.getLuogoDeposito());
			setDataInizioDisponibilitaMerce(richiesta.getDataInizioDisponibilita());
			setOraInizioDisponibilitaMerce(richiesta.getOraInizioDisponibilita());
			setDataPartenzaMerce(richiesta.getDataPartenzaMerce());
			setNoteTrasporto(richiesta.getNoteTrasporto());
		}
		
		// Dati merce

		// Dati tariffe

		// Dati trattamento
		if (trattamento != null) {
			setIdTrattamento(trattamento.getIdTrattamento());
			setProdottoChimico(trattamento.getProdottoChimico());
			setDurata(trattamento.getDurata());
			setTemperatura(trattamento.getTemperatura());
			setConcentrazione(trattamento.getConcentrazione());
			setDataTrattamento(trattamento.getDataTrattamento());
			setInfoSupplementari(trattamento.getInformazioniSupplementari());
		}

		// Dati esegui
		setDatiCertificato(certificato, idIspettore);

		// Dati pagamento
		if (pagamento != null) {
			setIdPagamentoRichiesta(pagamento.getIdPagamento());
			setMittentePagamento(pagamento.getMittente());
			setIdTipoPagamento(pagamento.getIdMezzoPagamento());
			setDataPagamento(pagamento.getDataPagamento());
			setNumeroDocumento(pagamento.getNumeroDocumento());
			setNomeFileAllegato(pagamento.getNomeFileAllegato());
		} else {
			setMittentePagamento(richiesta.getUtenteNome() + " " + richiesta.getUtenteCognome());
		}
	}

	public void setDatiCertificato(CertificatoRichiestaDto certificato, Long idIspettore) {
		
		if (certificato != null) {
			setIdCertificatoRichiesta(certificato.getIdCertificato());
			setIdTipoCertificato(certificato.getIdTipoCertificato());
			setDataEsecuzione(certificato.getDataEsecuzione());
			setIdIspettore(certificato.getIdIspettore());
			setNumeroCertificatoManuale(certificato.getNumeroCertificatoManuale());

			if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(getIdTipoRichiesta())) {
			  setIdLuogoEsecuzione(certificato.getIdUfficioDoganaleEsecuzione());
			  
				if (certificato.getNumeroCertificato() != null) {
					// Numerazione automatica
				  setTipoNumerazioneCertificato(CaronteConstants.NUMERAZIONE_CERTIFICATO_AUTOMATICA);
				} else if (!StringUtils.isEmpty(certificato.getNumeroCertificatoManuale())) {
					// Numerazione manuale
				  setTipoNumerazioneCertificato(CaronteConstants.NUMERAZIONE_CERTIFICATO_MANUALE);
				} else {
					// Nessuna numerazione
				  setTipoNumerazioneCertificato(CaronteConstants.NUMERAZIONE_CERTIFICATO_NESSUNA);
				}

				setNumeroCertificato(certificato.getNumeroCertificato());
				setNoteCertificato(certificato.getNote());
				setIdIspettoreDocumentale(certificato.getIdIspettoreDocumentale());
				setIdIspettoreIdentita(certificato.getIdIspettoreIdentita());
				setIdIspettoreFitosanitario(certificato.getIdIspettoreFitosanitario());
				
			} else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(getIdTipoRichiesta())
					|| CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(getIdTipoRichiesta())) {
			  setIdComuneEsecuzione(certificato.getIdComuneEsecuzione());
			  setIdProvinciaEsecuzione(certificato.getIdProvinciaEsecuzione());
			  
			  // Numerazione manuale
				setIdIspettoreFirmatario(certificato.getIdIspettoreDocumentale());
				setDichiarazioneSupplementare(certificato.getDichiarazioneSupplementare());
				
				if (CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(getIdTipoRichiesta())) {
				  if (certificato.getCopiaConforme() != null && certificato.getCopiaConforme()) {
				    setTipoCertificatoPrecedente(Boolean.TRUE.toString());
				  }else {
				    setTipoCertificatoPrecedente(Boolean.FALSE.toString());
				  }
				  
				  setIdMerceColliOriginali(certificato.getIdTipoImballaggio());
				  
				  if (certificato.getConformeCertifOrig() != null) {
				    setCertificatoFitoOriginale(certificato.getConformeCertifOrig());
				  }
				  
				  if (certificato.getIspezioneSupplementare() != null) {
				    setIspezioneSupplementare(certificato.getIspezioneSupplementare());
				  }
				}
			}

			
		} else {
			/*
			 * Se luogo esecuzione non è ancora valorizzato, proporre di default la dogana
			 * scelta in Dati trasporto
			 */
			setIdTipoCertificato(CaronteConstants.ID_TIPO_CERTIFICATO_PRIMA_STAMPA);
			setDataEsecuzione(new Date());
			
			if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(getIdTipoRichiesta())) {
			  setIdLuogoEsecuzione(getIdPuntoEntrataDichiarato());
			  setTipoNumerazioneCertificato(CaronteConstants.NUMERAZIONE_CERTIFICATO_AUTOMATICA);
			}else {
			  setTipoNumerazioneCertificato(CaronteConstants.NUMERAZIONE_CERTIFICATO_MANUALE);
			}

			if (idIspettore != null) {
				setIdIspettore(idIspettore);
				setIdIspettoreDocumentale(idIspettore);
				setIdIspettoreIdentita(idIspettore);
				setIdIspettoreFitosanitario(idIspettore);
				
				if (!CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(getIdTipoRichiesta())) {
				  setIdIspettoreFirmatario(idIspettore);
				}
			}
		}
		
	}

	public Long getIdProvinciaMittente() {
		return idProvinciaMittente;
	}

	public void setIdProvinciaMittente(Long idProvinciaMittente) {
		this.idProvinciaMittente = idProvinciaMittente;
	}

	public Long getIdComuneMittente() {
		return idComuneMittente;
	}

	public void setIdComuneMittente(Long idComuneMittente) {
		this.idComuneMittente = idComuneMittente;
	}

	public String getCodiceRuopMittente() {
    return codiceRuopMittente;
  }

  public void setCodiceRuopMittente(String codiceRuopMittente) {
    this.codiceRuopMittente = CaronteUtils.convertToUpperCase(codiceRuopMittente);
  }

	public String getNoteMittenteCertificato() {
    return noteMittenteCertificato;
  }

  public void setNoteMittenteCertificato(String noteMittenteCertificato) {
    this.noteMittenteCertificato = CaronteUtils.convertToUpperCase(noteMittenteCertificato);
  }

  public Long getIdStatoRupDestinatario() {
		return idStatoRupDestinatario;
	}

	public void setIdStatoRupDestinatario(Long idStatoRupDestinatario) {
		this.idStatoRupDestinatario = idStatoRupDestinatario;
	}

	public Long getIdNazioneProtVeg() {
		return idNazioneProtVeg;
	}

	public void setIdNazioneProtVeg(Long idNazioneProtVeg) {
		this.idNazioneProtVeg = idNazioneProtVeg;
	}

	public Date getDataInizioDisponibilitaMerce() {
		return dataInizioDisponibilitaMerce;
	}

	public void setDataInizioDisponibilitaMerce(Date dataInizioDisponibilitaMerce) {
		this.dataInizioDisponibilitaMerce = dataInizioDisponibilitaMerce;
	}

  public LocalTime getOraInizioDisponibilitaMerce() {
    return oraInizioDisponibilitaMerce;
  }

  public void setOraInizioDisponibilitaMerce(LocalTime oraInizioDisponibilitaMerce) {
    this.oraInizioDisponibilitaMerce = oraInizioDisponibilitaMerce;
  }

  public Date getDataPartenzaMerce() {
		return dataPartenzaMerce;
	}

	public void setDataPartenzaMerce(Date dataPartenzaMerce) {
		this.dataPartenzaMerce = dataPartenzaMerce;
	}

	public String getNoteTrasporto() {
		return noteTrasporto;
	}

	public void setNoteTrasporto(String noteTrasporto) {
		this.noteTrasporto = CaronteUtils.convertToUpperCase(noteTrasporto);
	}

	public Long getIdPuntoEntrataDichiarato() {
		return idPuntoEntrataDichiarato;
	}

	public void setIdPuntoEntrataDichiarato(Long idPuntoEntrataDichiarato) {
		this.idPuntoEntrataDichiarato = idPuntoEntrataDichiarato;
	}

	public String getPuntoEntrataDichiarato() {
    return puntoEntrataDichiarato;
  }

  public void setPuntoEntrataDichiarato(String puntoEntrataDichiarato) {
    this.puntoEntrataDichiarato = CaronteUtils.convertToUpperCase(puntoEntrataDichiarato);
  }

  public Long getIdProvinciaEsecuzione() {
    return idProvinciaEsecuzione;
  }

  public void setIdProvinciaEsecuzione(Long idProvinciaEsecuzione) {
    this.idProvinciaEsecuzione = idProvinciaEsecuzione;
  }

  public Long getIdComuneEsecuzione() {
    return idComuneEsecuzione;
  }

  public void setIdComuneEsecuzione(Long idComuneEsecuzione) {
    this.idComuneEsecuzione = idComuneEsecuzione;
  }

  public Long getIdIspettoreFirmatario() {
		return idIspettoreFirmatario;
	}

	public void setIdIspettoreFirmatario(Long idIspettoreFirmatario) {
		this.idIspettoreFirmatario = idIspettoreFirmatario;
	}

	public String getDichiarazioneSupplementare() {
		return dichiarazioneSupplementare;
	}

	public void setDichiarazioneSupplementare(String dichiarazioneSupplementare) {
		this.dichiarazioneSupplementare = CaronteUtils.convertToUpperCase(dichiarazioneSupplementare);
	}
	
  public String getTipoCertificatoPrecedente() {
    return tipoCertificatoPrecedente;
  }

  public void setTipoCertificatoPrecedente(String tipoCertificatoPrecedente) {
    this.tipoCertificatoPrecedente = tipoCertificatoPrecedente;
  }

  public Long getIdMerceColliOriginali() {
    return idMerceColliOriginali;
  }

  public void setIdMerceColliOriginali(Long idMerceColliOriginali) {
    this.idMerceColliOriginali = idMerceColliOriginali;
  }

  public boolean isCertificatoFitoOriginale() {
    return certificatoFitoOriginale;
  }

  public void setCertificatoFitoOriginale(boolean certificatoFitoOriginale) {
    this.certificatoFitoOriginale = certificatoFitoOriginale;
  }

  public boolean isIspezioneSupplementare() {
    return ispezioneSupplementare;
  }

  public void setIspezioneSupplementare(boolean ispezioneSupplementare) {
    this.ispezioneSupplementare = ispezioneSupplementare;
  }

  public String getNumeroCertificatoOriginale() {
		return numeroCertificatoOriginale;
	}

	public void setNumeroCertificatoOriginale(String numeroCertificatoOriginale) {
		this.numeroCertificatoOriginale = CaronteUtils.convertToUpperCase(numeroCertificatoOriginale);
	}

	public String getNomeFileAllegatoCertificato() {
		return nomeFileAllegatoCertificato;
	}

	public void setNomeFileAllegatoCertificato(String nomeFileAllegatoCertificato) {
		this.nomeFileAllegatoCertificato = nomeFileAllegatoCertificato;
	}

  public boolean isAbilitaInoltra() {
    return abilitaInoltra;
  }

  public void setAbilitaInoltra(boolean abilitaInoltra) {
    this.abilitaInoltra = abilitaInoltra;
  }

}