package it.aizoon.ersaf.dto;

import java.math.BigDecimal;
import java.util.List;

import it.aizoon.ersaf.dto.generati.CarRCampionamentoPartita;

@SuppressWarnings("serial")
public class MerceRichiestaVerbaleDto {

	private String idOrdMerce;
	private BigDecimal idMerceRichiesta;
	private String descrizionePartita;
	private BigDecimal colli;
	private BigDecimal kg;
	private BigDecimal mc;
	private BigDecimal unita;
	private BigDecimal quantitaAnalizzati;
	
	public BigDecimal getQuantitaAnalizzati() {
		return quantitaAnalizzati;
	}
	public void setQuantitaAnalizzati(BigDecimal quantitaAnalizzati) {
		this.quantitaAnalizzati = quantitaAnalizzati;
	}
	public String getIdOrdMerce() {
		return idOrdMerce;
	}
	public void setIdOrdMerce(String idOrdMerce) {
		this.idOrdMerce = idOrdMerce;
	}
	public BigDecimal getIdMerceRichiesta() {
		return idMerceRichiesta;
	}
	public void setIdMerceRichiesta(BigDecimal idMerceRichiesta) {
		this.idMerceRichiesta = idMerceRichiesta;
	}
	public String getDescrizionePartita() {
		return descrizionePartita;
	}
	public void setDescrizionePartita(String descrizionePartita) {
		this.descrizionePartita = descrizionePartita;
	}
	public BigDecimal getColli() {
		return colli;
	}
	public void setColli(BigDecimal colli) {
		this.colli = colli;
	}
	public BigDecimal getKg() {
		return kg;
	}
	public void setKg(BigDecimal kg) {
		this.kg = kg;
	}
	public BigDecimal getMc() {
		return mc;
	}
	public void setMc(BigDecimal mc) {
		this.mc = mc;
	}
	public BigDecimal getUnita() {
		return unita;
	}
	public void setUnita(BigDecimal unita) {
		this.unita = unita;
	}
	

}
