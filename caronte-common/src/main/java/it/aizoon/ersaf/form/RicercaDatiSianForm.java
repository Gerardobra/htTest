package it.aizoon.ersaf.form;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import it.aizoon.ersaf.util.CaronteUtils;

@Validated
public class RicercaDatiSianForm extends BaseForm {

	private String codTipoRichiesta;

	private String tipoOperazione = "I";
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

	// Filtri sul tracciato: Merci
	private String codiceProdotto;
	private String classeProdotto;
	private String marchioProdotto;
	private String codiceBayer;
	private List<Integer> listIntIdRichiesta;

	public List<Integer> getListIntIdRichiesta() {
		return listIntIdRichiesta;
	}

	public void setListIntIdRichiesta(List<Integer> listIntIdRichiesta) {
		this.listIntIdRichiesta = listIntIdRichiesta;
	}

	public String getCodTipoRichiesta() {
		return codTipoRichiesta;
	}

	public void setCodTipoRichiesta(String codTipoRichiesta) {
		this.codTipoRichiesta = codTipoRichiesta;
	}

	public String getTipoOperazione() {
		return tipoOperazione;
	}

	public void setTipoOperazione(String tipoOperazione) {
		this.tipoOperazione = CaronteUtils.convertToUpperCase(tipoOperazione);
	}

	/**
	 * @return the periodoDa
	 */
	public final Date getPeriodoDa() {
		return this.periodoDa;
	}

	/**
	 * @param periodoDa
	 *            the periodoDa to set
	 */
	public final void setPeriodoDa(Date periodoDa) {
		this.periodoDa = periodoDa;
	}

	/**
	 * @return the periodoA
	 */
	public final Date getPeriodoA() {
		return this.periodoA;
	}

	/**
	 * @param periodoA
	 *            the periodoA to set
	 */
	public final void setPeriodoA(Date periodoA) {
		this.periodoA = periodoA;
	}

	/**
	 * @return the nazioneMittente
	 */
	public final String getNazioneMittente() {
		return this.nazioneMittente;
	}

	/**
	 * @param nazioneMittente
	 *            the nazioneMittente to set
	 */
	public final void setNazioneMittente(String nazioneMittente) {
		this.nazioneMittente = nazioneMittente;
	}

	/**
	 * @return the nazioneDestinatario
	 */
	public final String getNazioneDestinatario() {
		return this.nazioneDestinatario;
	}

	/**
	 * @param nazioneDestinatario
	 *            the nazioneDestinatario to set
	 */
	public final void setNazioneDestinatario(String nazioneDestinatario) {
		this.nazioneDestinatario = nazioneDestinatario;
	}

	/**
	 * @return the doganaArrivo
	 */
	public final String getDoganaArrivo() {
		return this.doganaArrivo;
	}

	/**
	 * @param doganaArrivo
	 *            the doganaArrivo to set
	 */
	public final void setDoganaArrivo(String doganaArrivo) {
		this.doganaArrivo = doganaArrivo;
	}

	/**
	 * @return the luogoOrigineMerce
	 */
	public final String getLuogoOrigineMerce() {
		return this.luogoOrigineMerce;
	}

	/**
	 * @param luogoOrigineMerce
	 *            the luogoOrigineMerce to set
	 */
	public final void setLuogoOrigineMerce(String luogoOrigineMerce) {
		this.luogoOrigineMerce = luogoOrigineMerce;
	}

	/**
	 * @return the luogoRilascioCertificato
	 */
	public final String getLuogoRilascioCertificato() {
		return this.luogoRilascioCertificato;
	}

	/**
	 * @param luogoRilascioCertificato
	 *            the luogoRilascioCertificato to set
	 */
	public final void setLuogoRilascioCertificato(String luogoRilascioCertificato) {
		this.luogoRilascioCertificato = luogoRilascioCertificato;
	}

	/**
	 * @return the codiceProdotto
	 */
	public final String getCodiceProdotto() {
		return this.codiceProdotto;
	}

	/**
	 * @param codiceProdotto
	 *            the codiceProdotto to set
	 */
	public final void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = CaronteUtils.convertToUpperCase(codiceProdotto);
	}

	/**
	 * @return the classeProdotto
	 */
	public final String getClasseProdotto() {
		return this.classeProdotto;
	}

	/**
	 * @param classeProdotto
	 *            the classeProdotto to set
	 */
	public final void setClasseProdotto(String classeProdotto) {
		this.classeProdotto = classeProdotto;
	}

	/**
	 * @return the marchioProdotto
	 */
	public final String getMarchioProdotto() {
		return this.marchioProdotto;
	}

	/**
	 * @param marchioProdotto
	 *            the marchioProdotto to set
	 */
	public final void setMarchioProdotto(String marchioProdotto) {
		this.marchioProdotto = CaronteUtils.convertToUpperCase(marchioProdotto);
	}

	/**
	 * @return the codiceBayer
	 */
	public final String getCodiceBayer() {
		return this.codiceBayer;
	}

	/**
	 * @param codiceBayer
	 *            the codiceBayer to set
	 */
	public final void setCodiceBayer(String codiceBayer) {
		this.codiceBayer = CaronteUtils.convertToUpperCase(codiceBayer);
	}

	@Override
	public String toString() {
		return "RicercaDatiSianForm [periodoDa=" + this.periodoDa + ", periodoA=" + this.periodoA + ", nazioneMittente="
				+ this.nazioneMittente + ", nazioneDestinatario=" + this.nazioneDestinatario + ", doganaArrivo="
				+ this.doganaArrivo + ", luogoOrigineMerce=" + this.luogoOrigineMerce + ", luogoRilascioCertificato="
				+ this.luogoRilascioCertificato + ", codiceProdotto=" + this.codiceProdotto + ", classeProdotto="
				+ this.classeProdotto + ", marchioProdotto=" + this.marchioProdotto + ", codiceBayer="
				+ this.codiceBayer + "]";
	}

}