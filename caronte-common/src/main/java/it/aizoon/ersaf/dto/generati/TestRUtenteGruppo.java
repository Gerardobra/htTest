package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;

public class TestRUtenteGruppo extends BaseDto {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.test_r_utente_gruppo.id_utente
	 * @mbg.generated  Fri Nov 17 17:20:50 CET 2017
	 */
	private Long idUtente;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.test_r_utente_gruppo.id_gruppo
	 * @mbg.generated  Fri Nov 17 17:20:50 CET 2017
	 */
	private Long idGruppo;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.test_r_utente_gruppo.id_utente
	 * @return  the value of caronte.test_r_utente_gruppo.id_utente
	 * @mbg.generated  Fri Nov 17 17:20:50 CET 2017
	 */
	public Long getIdUtente() {
		return idUtente;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.test_r_utente_gruppo.id_utente
	 * @param idUtente  the value for caronte.test_r_utente_gruppo.id_utente
	 * @mbg.generated  Fri Nov 17 17:20:50 CET 2017
	 */
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.test_r_utente_gruppo.id_gruppo
	 * @return  the value of caronte.test_r_utente_gruppo.id_gruppo
	 * @mbg.generated  Fri Nov 17 17:20:50 CET 2017
	 */
	public Long getIdGruppo() {
		return idGruppo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.test_r_utente_gruppo.id_gruppo
	 * @param idGruppo  the value for caronte.test_r_utente_gruppo.id_gruppo
	 * @mbg.generated  Fri Nov 17 17:20:50 CET 2017
	 */
	public void setIdGruppo(Long idGruppo) {
		this.idGruppo = idGruppo;
	}
}