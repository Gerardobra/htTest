package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;
import java.util.Date;

public class CarRCampioneOrgNoc extends BaseDto {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_campione_org_noc.id_campione
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	private Long idCampione;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_campione_org_noc.id_org_nocivo
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	private Long idOrgNocivo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_campione_org_noc.fl_ricerca_accertato
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	private String flRicercaAccertato;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_campione_org_noc.id_utente_inserimento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	private Long idUtenteInserimento;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_campione_org_noc.data_inserimento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	private Date dataInserimento;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_campione_org_noc.id_utente_aggiornamento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	private Long idUtenteAggiornamento;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_campione_org_noc.data_aggiornamento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	private Date dataAggiornamento;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_campione_org_noc.id_campione
	 * @return  the value of caronte.car_r_campione_org_noc.id_campione
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public Long getIdCampione() {
		return idCampione;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_campione_org_noc.id_campione
	 * @param idCampione  the value for caronte.car_r_campione_org_noc.id_campione
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public void setIdCampione(Long idCampione) {
		this.idCampione = idCampione;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_campione_org_noc.id_org_nocivo
	 * @return  the value of caronte.car_r_campione_org_noc.id_org_nocivo
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public Long getIdOrgNocivo() {
		return idOrgNocivo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_campione_org_noc.id_org_nocivo
	 * @param idOrgNocivo  the value for caronte.car_r_campione_org_noc.id_org_nocivo
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public void setIdOrgNocivo(Long idOrgNocivo) {
		this.idOrgNocivo = idOrgNocivo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_campione_org_noc.fl_ricerca_accertato
	 * @return  the value of caronte.car_r_campione_org_noc.fl_ricerca_accertato
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public String getFlRicercaAccertato() {
		return flRicercaAccertato;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_campione_org_noc.fl_ricerca_accertato
	 * @param flRicercaAccertato  the value for caronte.car_r_campione_org_noc.fl_ricerca_accertato
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public void setFlRicercaAccertato(String flRicercaAccertato) {
		this.flRicercaAccertato = flRicercaAccertato == null ? null : flRicercaAccertato.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_campione_org_noc.id_utente_inserimento
	 * @return  the value of caronte.car_r_campione_org_noc.id_utente_inserimento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public Long getIdUtenteInserimento() {
		return idUtenteInserimento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_campione_org_noc.id_utente_inserimento
	 * @param idUtenteInserimento  the value for caronte.car_r_campione_org_noc.id_utente_inserimento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public void setIdUtenteInserimento(Long idUtenteInserimento) {
		this.idUtenteInserimento = idUtenteInserimento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_campione_org_noc.data_inserimento
	 * @return  the value of caronte.car_r_campione_org_noc.data_inserimento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public Date getDataInserimento() {
		return dataInserimento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_campione_org_noc.data_inserimento
	 * @param dataInserimento  the value for caronte.car_r_campione_org_noc.data_inserimento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_campione_org_noc.id_utente_aggiornamento
	 * @return  the value of caronte.car_r_campione_org_noc.id_utente_aggiornamento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public Long getIdUtenteAggiornamento() {
		return idUtenteAggiornamento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_campione_org_noc.id_utente_aggiornamento
	 * @param idUtenteAggiornamento  the value for caronte.car_r_campione_org_noc.id_utente_aggiornamento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public void setIdUtenteAggiornamento(Long idUtenteAggiornamento) {
		this.idUtenteAggiornamento = idUtenteAggiornamento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_campione_org_noc.data_aggiornamento
	 * @return  the value of caronte.car_r_campione_org_noc.data_aggiornamento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public Date getDataAggiornamento() {
		return dataAggiornamento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_campione_org_noc.data_aggiornamento
	 * @param dataAggiornamento  the value for caronte.car_r_campione_org_noc.data_aggiornamento
	 * @mbg.generated  Wed Dec 23 09:18:17 CET 2020
	 */
	public void setDataAggiornamento(Date dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
}