package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;

public class CarDAssociazioneSezione extends BaseDto {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_associazione_sezione.id_associazione_sezione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Long idAssociazioneSezione;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_associazione_sezione.desc_associazione_sezione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private String descAssociazioneSezione;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_associazione_sezione.id_associazione_sezione
   * @return  the value of caronte.car_d_associazione_sezione.id_associazione_sezione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Long getIdAssociazioneSezione() {
    return idAssociazioneSezione;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_associazione_sezione.id_associazione_sezione
   * @param idAssociazioneSezione  the value for caronte.car_d_associazione_sezione.id_associazione_sezione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setIdAssociazioneSezione(Long idAssociazioneSezione) {
    this.idAssociazioneSezione = idAssociazioneSezione;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_associazione_sezione.desc_associazione_sezione
   * @return  the value of caronte.car_d_associazione_sezione.desc_associazione_sezione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public String getDescAssociazioneSezione() {
    return descAssociazioneSezione;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_associazione_sezione.desc_associazione_sezione
   * @param descAssociazioneSezione  the value for caronte.car_d_associazione_sezione.desc_associazione_sezione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setDescAssociazioneSezione(String descAssociazioneSezione) {
    this.descAssociazioneSezione = descAssociazioneSezione == null ? null : descAssociazioneSezione.trim();
  }
}