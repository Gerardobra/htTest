package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;
import java.util.Date;

public class CarRTipoAttivitaMateriale extends BaseDto {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_tipo_attivita_materiale.id_tipo_attivita_materiale
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	private Long idTipoAttivitaMateriale;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_tipo_attivita_materiale.id_tipo_attivita
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	private Long idTipoAttivita;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_tipo_attivita_materiale.id_materiale
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	private Long idMateriale;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_tipo_attivita_materiale.ordinamento
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	private Long ordinamento;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_tipo_attivita_materiale.inizio_validita
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	private Date inizioValidita;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_tipo_attivita_materiale.fine_validita
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	private Date fineValidita;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_tipo_attivita_materiale.id_tipo_attivita_materiale
	 * @return  the value of caronte.car_r_tipo_attivita_materiale.id_tipo_attivita_materiale
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public Long getIdTipoAttivitaMateriale() {
		return idTipoAttivitaMateriale;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_tipo_attivita_materiale.id_tipo_attivita_materiale
	 * @param idTipoAttivitaMateriale  the value for caronte.car_r_tipo_attivita_materiale.id_tipo_attivita_materiale
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public void setIdTipoAttivitaMateriale(Long idTipoAttivitaMateriale) {
		this.idTipoAttivitaMateriale = idTipoAttivitaMateriale;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_tipo_attivita_materiale.id_tipo_attivita
	 * @return  the value of caronte.car_r_tipo_attivita_materiale.id_tipo_attivita
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public Long getIdTipoAttivita() {
		return idTipoAttivita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_tipo_attivita_materiale.id_tipo_attivita
	 * @param idTipoAttivita  the value for caronte.car_r_tipo_attivita_materiale.id_tipo_attivita
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public void setIdTipoAttivita(Long idTipoAttivita) {
		this.idTipoAttivita = idTipoAttivita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_tipo_attivita_materiale.id_materiale
	 * @return  the value of caronte.car_r_tipo_attivita_materiale.id_materiale
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public Long getIdMateriale() {
		return idMateriale;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_tipo_attivita_materiale.id_materiale
	 * @param idMateriale  the value for caronte.car_r_tipo_attivita_materiale.id_materiale
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public void setIdMateriale(Long idMateriale) {
		this.idMateriale = idMateriale;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_tipo_attivita_materiale.ordinamento
	 * @return  the value of caronte.car_r_tipo_attivita_materiale.ordinamento
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public Long getOrdinamento() {
		return ordinamento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_tipo_attivita_materiale.ordinamento
	 * @param ordinamento  the value for caronte.car_r_tipo_attivita_materiale.ordinamento
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public void setOrdinamento(Long ordinamento) {
		this.ordinamento = ordinamento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_tipo_attivita_materiale.inizio_validita
	 * @return  the value of caronte.car_r_tipo_attivita_materiale.inizio_validita
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public Date getInizioValidita() {
		return inizioValidita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_tipo_attivita_materiale.inizio_validita
	 * @param inizioValidita  the value for caronte.car_r_tipo_attivita_materiale.inizio_validita
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public void setInizioValidita(Date inizioValidita) {
		this.inizioValidita = inizioValidita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_tipo_attivita_materiale.fine_validita
	 * @return  the value of caronte.car_r_tipo_attivita_materiale.fine_validita
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public Date getFineValidita() {
		return fineValidita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_tipo_attivita_materiale.fine_validita
	 * @param fineValidita  the value for caronte.car_r_tipo_attivita_materiale.fine_validita
	 * @mbg.generated  Tue Nov 17 15:01:16 CET 2020
	 */
	public void setFineValidita(Date fineValidita) {
		this.fineValidita = fineValidita;
	}
}