package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;
import java.util.Date;

public class CarDStatoAzienda extends BaseDto {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_stato_azienda.id_stato_azienda
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	private Long idStatoAzienda;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_stato_azienda.desc_breve
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	private String descBreve;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_stato_azienda.desc_estesa
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	private String descEstesa;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_stato_azienda.inizio_validita
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	private Date inizioValidita;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_stato_azienda.fine_validita
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	private Date fineValidita;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_stato_azienda.desc_centro_az
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	private String descCentroAz;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_stato_azienda.id_stato_azienda
	 * @return  the value of caronte.car_d_stato_azienda.id_stato_azienda
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public Long getIdStatoAzienda() {
		return idStatoAzienda;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_stato_azienda.id_stato_azienda
	 * @param idStatoAzienda  the value for caronte.car_d_stato_azienda.id_stato_azienda
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public void setIdStatoAzienda(Long idStatoAzienda) {
		this.idStatoAzienda = idStatoAzienda;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_stato_azienda.desc_breve
	 * @return  the value of caronte.car_d_stato_azienda.desc_breve
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public String getDescBreve() {
		return descBreve;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_stato_azienda.desc_breve
	 * @param descBreve  the value for caronte.car_d_stato_azienda.desc_breve
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public void setDescBreve(String descBreve) {
		this.descBreve = descBreve == null ? null : descBreve.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_stato_azienda.desc_estesa
	 * @return  the value of caronte.car_d_stato_azienda.desc_estesa
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public String getDescEstesa() {
		return descEstesa;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_stato_azienda.desc_estesa
	 * @param descEstesa  the value for caronte.car_d_stato_azienda.desc_estesa
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public void setDescEstesa(String descEstesa) {
		this.descEstesa = descEstesa == null ? null : descEstesa.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_stato_azienda.inizio_validita
	 * @return  the value of caronte.car_d_stato_azienda.inizio_validita
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public Date getInizioValidita() {
		return inizioValidita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_stato_azienda.inizio_validita
	 * @param inizioValidita  the value for caronte.car_d_stato_azienda.inizio_validita
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public void setInizioValidita(Date inizioValidita) {
		this.inizioValidita = inizioValidita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_stato_azienda.fine_validita
	 * @return  the value of caronte.car_d_stato_azienda.fine_validita
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public Date getFineValidita() {
		return fineValidita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_stato_azienda.fine_validita
	 * @param fineValidita  the value for caronte.car_d_stato_azienda.fine_validita
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public void setFineValidita(Date fineValidita) {
		this.fineValidita = fineValidita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_stato_azienda.desc_centro_az
	 * @return  the value of caronte.car_d_stato_azienda.desc_centro_az
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public String getDescCentroAz() {
		return descCentroAz;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_stato_azienda.desc_centro_az
	 * @param descCentroAz  the value for caronte.car_d_stato_azienda.desc_centro_az
	 * @mbg.generated  Fri Apr 21 09:53:16 CEST 2023
	 */
	public void setDescCentroAz(String descCentroAz) {
		this.descCentroAz = descCentroAz == null ? null : descCentroAz.trim();
	}
}