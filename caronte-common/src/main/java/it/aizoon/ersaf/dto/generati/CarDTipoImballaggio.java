package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;

public class CarDTipoImballaggio extends BaseDto {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_tipo_imballaggio.id_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Long idTipoImballaggio;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_tipo_imballaggio.desc_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private String descTipoImballaggio;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_tipo_imballaggio.id_tipo_imballaggio
   * @return  the value of caronte.car_d_tipo_imballaggio.id_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Long getIdTipoImballaggio() {
    return idTipoImballaggio;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_tipo_imballaggio.id_tipo_imballaggio
   * @param idTipoImballaggio  the value for caronte.car_d_tipo_imballaggio.id_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setIdTipoImballaggio(Long idTipoImballaggio) {
    this.idTipoImballaggio = idTipoImballaggio;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_tipo_imballaggio.desc_tipo_imballaggio
   * @return  the value of caronte.car_d_tipo_imballaggio.desc_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public String getDescTipoImballaggio() {
    return descTipoImballaggio;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_tipo_imballaggio.desc_tipo_imballaggio
   * @param descTipoImballaggio  the value for caronte.car_d_tipo_imballaggio.desc_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setDescTipoImballaggio(String descTipoImballaggio) {
    this.descTipoImballaggio = descTipoImballaggio == null ? null : descTipoImballaggio.trim();
  }
}