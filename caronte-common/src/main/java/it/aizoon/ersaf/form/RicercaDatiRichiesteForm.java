package it.aizoon.ersaf.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.validation.annotation.Validated;

import it.aizoon.ersaf.util.CaronteUtils;

@Validated
public class RicercaDatiRichiesteForm extends BaseForm {

	private String codTipoRichiesta;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Dato obbligatorio")
	private Date periodoDa;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Dato obbligatorio")
	private Date periodoA;

	// Filtri sul tracciato: Certificato
	private String nazioneMittente;
	private String nazioneDestinatario;
	private String doganaArrivo;
	private String luogoOrigineMerce;
	private String luogoRilascioCertificato;
	private String spedizioniereEnte;

	// Filtri sul tracciato: Merci
	private String numeroRichiesta;
	private String classeProdotto;
	private String genere;
	@NumberFormat(style = Style.NUMBER)
	private Long idGenere;
	private String listIntIdRichiesta;


	public String getCodTipoRichiesta() {
		return codTipoRichiesta;
	}

	public void setCodTipoRichiesta(String codTipoRichiesta) {
		this.codTipoRichiesta = codTipoRichiesta;
	}

	public String getListIntIdRichiesta() {
		return listIntIdRichiesta;
	}

	public void setListIntIdRichiesta(String listIntIdRichiesta) {
		this.listIntIdRichiesta = listIntIdRichiesta;
	}

	public Date getPeriodoDa() {
		return periodoDa;
	}

	public void setPeriodoDa(Date periodoDa) {
		this.periodoDa = periodoDa;
	}

	public Date getPeriodoA() {
		return periodoA;
	}

	public void setPeriodoA(Date periodoA) {
		this.periodoA = periodoA;
	}

	public String getNazioneMittente() {
		return nazioneMittente;
	}

	public void setNazioneMittente(String nazioneMittente) {
		this.nazioneMittente = CaronteUtils.convertToUpperCase(nazioneMittente);
	}

	public String getNazioneDestinatario() {
		return nazioneDestinatario;
	}

	public void setNazioneDestinatario(String nazioneDestinatario) {
		this.nazioneDestinatario = CaronteUtils.convertToUpperCase(nazioneDestinatario);
	}

	public String getDoganaArrivo() {
		return doganaArrivo;
	}

	public void setDoganaArrivo(String doganaArrivo) {
		this.doganaArrivo = doganaArrivo;
	}

	public String getLuogoOrigineMerce() {
		return luogoOrigineMerce;
	}

	public void setLuogoOrigineMerce(String luogoOrigineMerce) {
		this.luogoOrigineMerce = luogoOrigineMerce;
	}

	public String getLuogoRilascioCertificato() {
		return luogoRilascioCertificato;
	}

	public void setLuogoRilascioCertificato(String luogoRilascioCertificato) {
		this.luogoRilascioCertificato = luogoRilascioCertificato;
	}

	public String getSpedizioniereEnte() {
		return spedizioniereEnte;
	}

	public void setSpedizioniereEnte(String spedizioniereEnte) {
		this.spedizioniereEnte = CaronteUtils.convertToUpperCase(spedizioniereEnte);
	}

	public String getNumeroRichiesta() {
		return numeroRichiesta;
	}

	public void setNumeroRichiesta(String numeroRichiesta) {
		this.numeroRichiesta = numeroRichiesta;
	}

	public String getClasseProdotto() {
		return classeProdotto;
	}

	public void setClasseProdotto(String classeProdotto) {
		this.classeProdotto = classeProdotto;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = CaronteUtils.convertToUpperCase(genere);
	}

	public Long getIdGenere() {
    return idGenere;
  }

  public void setIdGenere(Long idGenere) {
    this.idGenere = idGenere;
  }

  @Override
	public String toString() {
		return "RicercaDatiRichiesteForm [periodoDa=" + periodoDa + ", periodoA=" + periodoA + ", nazioneMittente="
				+ nazioneMittente + ", nazioneDestinatario=" + nazioneDestinatario + ", doganaArrivo=" + doganaArrivo
				+ ", luogoOrigineMerce=" + luogoOrigineMerce + ", luogoRilascioCertificato=" + luogoRilascioCertificato
				+ ", spedizioniereEnte=" + spedizioniereEnte + ", numeroRichiesta=" + numeroRichiesta
				+ ", classeProdotto=" + classeProdotto + ", genere=" + genere + "]";
	}

}