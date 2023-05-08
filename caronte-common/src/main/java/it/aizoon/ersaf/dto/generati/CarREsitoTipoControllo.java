package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;
import java.util.Date;

public class CarREsitoTipoControllo extends BaseDto {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_esito_tipo_controllo.id_verbale_ispezione
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  private Long idVerbaleIspezione;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_esito_tipo_controllo.id_tipo_controllo
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  private Long idTipoControllo;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_esito_tipo_controllo.esito_favorevole
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  private Boolean esitoFavorevole;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_esito_tipo_controllo.motivo_esito_non_favorevole
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  private String motivoEsitoNonFavorevole;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_esito_tipo_controllo.ispezione_visiva
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  private Boolean ispezioneVisiva;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_esito_tipo_controllo.ispezione_strumentale
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  private Boolean ispezioneStrumentale;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_esito_tipo_controllo.strumento_ispezione
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  private String strumentoIspezione;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_esito_tipo_controllo.id_utente_insert
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  private Long idUtenteInsert;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_esito_tipo_controllo.data_insert
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  private Date dataInsert;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_esito_tipo_controllo.id_utente_update
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  private Long idUtenteUpdate;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_r_esito_tipo_controllo.data_update
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  private Date dataUpdate;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_esito_tipo_controllo.id_verbale_ispezione
   * @return  the value of caronte.car_r_esito_tipo_controllo.id_verbale_ispezione
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public Long getIdVerbaleIspezione() {
    return idVerbaleIspezione;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_esito_tipo_controllo.id_verbale_ispezione
   * @param idVerbaleIspezione  the value for caronte.car_r_esito_tipo_controllo.id_verbale_ispezione
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setIdVerbaleIspezione(Long idVerbaleIspezione) {
    this.idVerbaleIspezione = idVerbaleIspezione;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_esito_tipo_controllo.id_tipo_controllo
   * @return  the value of caronte.car_r_esito_tipo_controllo.id_tipo_controllo
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public Long getIdTipoControllo() {
    return idTipoControllo;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_esito_tipo_controllo.id_tipo_controllo
   * @param idTipoControllo  the value for caronte.car_r_esito_tipo_controllo.id_tipo_controllo
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setIdTipoControllo(Long idTipoControllo) {
    this.idTipoControllo = idTipoControllo;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_esito_tipo_controllo.esito_favorevole
   * @return  the value of caronte.car_r_esito_tipo_controllo.esito_favorevole
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public Boolean getEsitoFavorevole() {
    return esitoFavorevole;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_esito_tipo_controllo.esito_favorevole
   * @param esitoFavorevole  the value for caronte.car_r_esito_tipo_controllo.esito_favorevole
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setEsitoFavorevole(Boolean esitoFavorevole) {
    this.esitoFavorevole = esitoFavorevole;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_esito_tipo_controllo.motivo_esito_non_favorevole
   * @return  the value of caronte.car_r_esito_tipo_controllo.motivo_esito_non_favorevole
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public String getMotivoEsitoNonFavorevole() {
    return motivoEsitoNonFavorevole;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_esito_tipo_controllo.motivo_esito_non_favorevole
   * @param motivoEsitoNonFavorevole  the value for caronte.car_r_esito_tipo_controllo.motivo_esito_non_favorevole
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setMotivoEsitoNonFavorevole(String motivoEsitoNonFavorevole) {
    this.motivoEsitoNonFavorevole = motivoEsitoNonFavorevole == null ? null : motivoEsitoNonFavorevole.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_esito_tipo_controllo.ispezione_visiva
   * @return  the value of caronte.car_r_esito_tipo_controllo.ispezione_visiva
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public Boolean getIspezioneVisiva() {
    return ispezioneVisiva;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_esito_tipo_controllo.ispezione_visiva
   * @param ispezioneVisiva  the value for caronte.car_r_esito_tipo_controllo.ispezione_visiva
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setIspezioneVisiva(Boolean ispezioneVisiva) {
    this.ispezioneVisiva = ispezioneVisiva;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_esito_tipo_controllo.ispezione_strumentale
   * @return  the value of caronte.car_r_esito_tipo_controllo.ispezione_strumentale
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public Boolean getIspezioneStrumentale() {
    return ispezioneStrumentale;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_esito_tipo_controllo.ispezione_strumentale
   * @param ispezioneStrumentale  the value for caronte.car_r_esito_tipo_controllo.ispezione_strumentale
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setIspezioneStrumentale(Boolean ispezioneStrumentale) {
    this.ispezioneStrumentale = ispezioneStrumentale;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_esito_tipo_controllo.strumento_ispezione
   * @return  the value of caronte.car_r_esito_tipo_controllo.strumento_ispezione
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public String getStrumentoIspezione() {
    return strumentoIspezione;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_esito_tipo_controllo.strumento_ispezione
   * @param strumentoIspezione  the value for caronte.car_r_esito_tipo_controllo.strumento_ispezione
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setStrumentoIspezione(String strumentoIspezione) {
    this.strumentoIspezione = strumentoIspezione == null ? null : strumentoIspezione.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_esito_tipo_controllo.id_utente_insert
   * @return  the value of caronte.car_r_esito_tipo_controllo.id_utente_insert
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public Long getIdUtenteInsert() {
    return idUtenteInsert;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_esito_tipo_controllo.id_utente_insert
   * @param idUtenteInsert  the value for caronte.car_r_esito_tipo_controllo.id_utente_insert
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setIdUtenteInsert(Long idUtenteInsert) {
    this.idUtenteInsert = idUtenteInsert;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_esito_tipo_controllo.data_insert
   * @return  the value of caronte.car_r_esito_tipo_controllo.data_insert
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public Date getDataInsert() {
    return dataInsert;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_esito_tipo_controllo.data_insert
   * @param dataInsert  the value for caronte.car_r_esito_tipo_controllo.data_insert
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setDataInsert(Date dataInsert) {
    this.dataInsert = dataInsert;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_esito_tipo_controllo.id_utente_update
   * @return  the value of caronte.car_r_esito_tipo_controllo.id_utente_update
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public Long getIdUtenteUpdate() {
    return idUtenteUpdate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_esito_tipo_controllo.id_utente_update
   * @param idUtenteUpdate  the value for caronte.car_r_esito_tipo_controllo.id_utente_update
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setIdUtenteUpdate(Long idUtenteUpdate) {
    this.idUtenteUpdate = idUtenteUpdate;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_r_esito_tipo_controllo.data_update
   * @return  the value of caronte.car_r_esito_tipo_controllo.data_update
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public Date getDataUpdate() {
    return dataUpdate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_r_esito_tipo_controllo.data_update
   * @param dataUpdate  the value for caronte.car_r_esito_tipo_controllo.data_update
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setDataUpdate(Date dataUpdate) {
    this.dataUpdate = dataUpdate;
  }
}