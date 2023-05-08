package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;
import java.util.Date;

public class CarRLinguaGenere extends BaseDto {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_lingua_genere.id_genere
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	private Long idGenere;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_lingua_genere.id_lingua
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	private Long idLingua;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_lingua_genere.denom_genere
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	private String denomGenere;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_lingua_genere.denom_genere_commerciale
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	private String denomGenereCommerciale;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_lingua_genere.preferred
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	private Boolean preferred;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_lingua_genere.id_utente_insert
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	private Long idUtenteInsert;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_lingua_genere.data_insert
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	private Date dataInsert;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_lingua_genere.id_utente_update
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	private Long idUtenteUpdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_lingua_genere.data_update
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	private Date dataUpdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_lingua_genere.tipo_genere_famiglia
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	private String tipoGenereFamiglia;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_lingua_genere.id_genere
	 * @return  the value of caronte.car_r_lingua_genere.id_genere
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public Long getIdGenere() {
		return idGenere;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_lingua_genere.id_genere
	 * @param idGenere  the value for caronte.car_r_lingua_genere.id_genere
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public void setIdGenere(Long idGenere) {
		this.idGenere = idGenere;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_lingua_genere.id_lingua
	 * @return  the value of caronte.car_r_lingua_genere.id_lingua
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public Long getIdLingua() {
		return idLingua;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_lingua_genere.id_lingua
	 * @param idLingua  the value for caronte.car_r_lingua_genere.id_lingua
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public void setIdLingua(Long idLingua) {
		this.idLingua = idLingua;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_lingua_genere.denom_genere
	 * @return  the value of caronte.car_r_lingua_genere.denom_genere
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public String getDenomGenere() {
		return denomGenere;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_lingua_genere.denom_genere
	 * @param denomGenere  the value for caronte.car_r_lingua_genere.denom_genere
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public void setDenomGenere(String denomGenere) {
		this.denomGenere = denomGenere == null ? null : denomGenere.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_lingua_genere.denom_genere_commerciale
	 * @return  the value of caronte.car_r_lingua_genere.denom_genere_commerciale
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public String getDenomGenereCommerciale() {
		return denomGenereCommerciale;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_lingua_genere.denom_genere_commerciale
	 * @param denomGenereCommerciale  the value for caronte.car_r_lingua_genere.denom_genere_commerciale
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public void setDenomGenereCommerciale(String denomGenereCommerciale) {
		this.denomGenereCommerciale = denomGenereCommerciale == null ? null : denomGenereCommerciale.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_lingua_genere.preferred
	 * @return  the value of caronte.car_r_lingua_genere.preferred
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public Boolean getPreferred() {
		return preferred;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_lingua_genere.preferred
	 * @param preferred  the value for caronte.car_r_lingua_genere.preferred
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_lingua_genere.id_utente_insert
	 * @return  the value of caronte.car_r_lingua_genere.id_utente_insert
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public Long getIdUtenteInsert() {
		return idUtenteInsert;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_lingua_genere.id_utente_insert
	 * @param idUtenteInsert  the value for caronte.car_r_lingua_genere.id_utente_insert
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public void setIdUtenteInsert(Long idUtenteInsert) {
		this.idUtenteInsert = idUtenteInsert;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_lingua_genere.data_insert
	 * @return  the value of caronte.car_r_lingua_genere.data_insert
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public Date getDataInsert() {
		return dataInsert;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_lingua_genere.data_insert
	 * @param dataInsert  the value for caronte.car_r_lingua_genere.data_insert
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public void setDataInsert(Date dataInsert) {
		this.dataInsert = dataInsert;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_lingua_genere.id_utente_update
	 * @return  the value of caronte.car_r_lingua_genere.id_utente_update
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public Long getIdUtenteUpdate() {
		return idUtenteUpdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_lingua_genere.id_utente_update
	 * @param idUtenteUpdate  the value for caronte.car_r_lingua_genere.id_utente_update
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public void setIdUtenteUpdate(Long idUtenteUpdate) {
		this.idUtenteUpdate = idUtenteUpdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_lingua_genere.data_update
	 * @return  the value of caronte.car_r_lingua_genere.data_update
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public Date getDataUpdate() {
		return dataUpdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_lingua_genere.data_update
	 * @param dataUpdate  the value for caronte.car_r_lingua_genere.data_update
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_lingua_genere.tipo_genere_famiglia
	 * @return  the value of caronte.car_r_lingua_genere.tipo_genere_famiglia
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public String getTipoGenereFamiglia() {
		return tipoGenereFamiglia;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_lingua_genere.tipo_genere_famiglia
	 * @param tipoGenereFamiglia  the value for caronte.car_r_lingua_genere.tipo_genere_famiglia
	 * @mbg.generated  Fri Oct 23 14:45:57 CEST 2020
	 */
	public void setTipoGenereFamiglia(String tipoGenereFamiglia) {
		this.tipoGenereFamiglia = tipoGenereFamiglia == null ? null : tipoGenereFamiglia.trim();
	}
}