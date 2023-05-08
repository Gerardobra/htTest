package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;
import java.util.Date;

public class CarDSpecie extends BaseDto {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_specie.id_specie
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Long idSpecie;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_specie.cod_specie
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private String codSpecie;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_specie.id_genere
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Long idGenere;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_specie.id_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Long idAutoreEppo;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_specie.id_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Long idNazione;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_specie.attivo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Boolean attivo;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_specie.id_utente_insert
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Long idUtenteInsert;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_specie.data_insert
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Date dataInsert;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_specie.id_utente_update
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Long idUtenteUpdate;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_specie.data_update
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Date dataUpdate;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_specie.id_specie
   * @return  the value of caronte.car_d_specie.id_specie
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Long getIdSpecie() {
    return idSpecie;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_specie.id_specie
   * @param idSpecie  the value for caronte.car_d_specie.id_specie
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setIdSpecie(Long idSpecie) {
    this.idSpecie = idSpecie;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_specie.cod_specie
   * @return  the value of caronte.car_d_specie.cod_specie
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public String getCodSpecie() {
    return codSpecie;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_specie.cod_specie
   * @param codSpecie  the value for caronte.car_d_specie.cod_specie
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setCodSpecie(String codSpecie) {
    this.codSpecie = codSpecie == null ? null : codSpecie.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_specie.id_genere
   * @return  the value of caronte.car_d_specie.id_genere
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Long getIdGenere() {
    return idGenere;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_specie.id_genere
   * @param idGenere  the value for caronte.car_d_specie.id_genere
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setIdGenere(Long idGenere) {
    this.idGenere = idGenere;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_specie.id_autore_eppo
   * @return  the value of caronte.car_d_specie.id_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Long getIdAutoreEppo() {
    return idAutoreEppo;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_specie.id_autore_eppo
   * @param idAutoreEppo  the value for caronte.car_d_specie.id_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setIdAutoreEppo(Long idAutoreEppo) {
    this.idAutoreEppo = idAutoreEppo;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_specie.id_nazione
   * @return  the value of caronte.car_d_specie.id_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Long getIdNazione() {
    return idNazione;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_specie.id_nazione
   * @param idNazione  the value for caronte.car_d_specie.id_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setIdNazione(Long idNazione) {
    this.idNazione = idNazione;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_specie.attivo
   * @return  the value of caronte.car_d_specie.attivo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Boolean getAttivo() {
    return attivo;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_specie.attivo
   * @param attivo  the value for caronte.car_d_specie.attivo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setAttivo(Boolean attivo) {
    this.attivo = attivo;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_specie.id_utente_insert
   * @return  the value of caronte.car_d_specie.id_utente_insert
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Long getIdUtenteInsert() {
    return idUtenteInsert;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_specie.id_utente_insert
   * @param idUtenteInsert  the value for caronte.car_d_specie.id_utente_insert
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setIdUtenteInsert(Long idUtenteInsert) {
    this.idUtenteInsert = idUtenteInsert;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_specie.data_insert
   * @return  the value of caronte.car_d_specie.data_insert
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Date getDataInsert() {
    return dataInsert;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_specie.data_insert
   * @param dataInsert  the value for caronte.car_d_specie.data_insert
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setDataInsert(Date dataInsert) {
    this.dataInsert = dataInsert;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_specie.id_utente_update
   * @return  the value of caronte.car_d_specie.id_utente_update
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Long getIdUtenteUpdate() {
    return idUtenteUpdate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_specie.id_utente_update
   * @param idUtenteUpdate  the value for caronte.car_d_specie.id_utente_update
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setIdUtenteUpdate(Long idUtenteUpdate) {
    this.idUtenteUpdate = idUtenteUpdate;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_specie.data_update
   * @return  the value of caronte.car_d_specie.data_update
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Date getDataUpdate() {
    return dataUpdate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_specie.data_update
   * @param dataUpdate  the value for caronte.car_d_specie.data_update
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setDataUpdate(Date dataUpdate) {
    this.dataUpdate = dataUpdate;
  }
}