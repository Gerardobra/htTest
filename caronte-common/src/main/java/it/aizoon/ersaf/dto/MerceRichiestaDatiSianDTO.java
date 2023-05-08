package it.aizoon.ersaf.dto;

import java.math.BigDecimal;

/**
 * @author alessandro.morra
 */
public class MerceRichiestaDatiSianDTO extends BaseDto {

	private static final long serialVersionUID = 417085276047941689L;

	private String idRichiesta;
	private String numero;
	private String marchio;
	private String numColli;
	private String natura;
	private String classe;
	private String codiceProdotto;
	private String codiceBayer;
	private String codNazione;
	private String um;
	private BigDecimal quantita;

	public String getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(String idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMarchio() {
		return marchio;
	}

	public void setMarchio(String marchio) {
		this.marchio = marchio;
	}

	public String getNumColli() {
		return numColli;
	}

	public void setNumColli(String numColli) {
		this.numColli = numColli;
	}

	public String getNatura() {
		return natura;
	}

	public void setNatura(String natura) {
		this.natura = natura;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getCodiceProdotto() {
		return codiceProdotto;
	}

	public void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	public String getCodiceBayer() {
		return codiceBayer;
	}

	public void setCodiceBayer(String codiceBayer) {
		this.codiceBayer = codiceBayer;
	}

	public String getCodNazione() {
		return codNazione;
	}

	public void setCodNazione(String codNazione) {
		this.codNazione = codNazione;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public BigDecimal getQuantita() {
		return quantita;
	}

	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}

}
