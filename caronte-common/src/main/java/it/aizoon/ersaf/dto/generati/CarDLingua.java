package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;

public class CarDLingua extends BaseDto {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_lingua.id_lingua
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private Long idLingua;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_lingua.cod_lingua
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private String codLingua;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column caronte.car_d_lingua.desc_lingua
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  private String descLingua;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_lingua.id_lingua
   * @return  the value of caronte.car_d_lingua.id_lingua
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Long getIdLingua() {
    return idLingua;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_lingua.id_lingua
   * @param idLingua  the value for caronte.car_d_lingua.id_lingua
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setIdLingua(Long idLingua) {
    this.idLingua = idLingua;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_lingua.cod_lingua
   * @return  the value of caronte.car_d_lingua.cod_lingua
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public String getCodLingua() {
    return codLingua;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_lingua.cod_lingua
   * @param codLingua  the value for caronte.car_d_lingua.cod_lingua
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setCodLingua(String codLingua) {
    this.codLingua = codLingua == null ? null : codLingua.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column caronte.car_d_lingua.desc_lingua
   * @return  the value of caronte.car_d_lingua.desc_lingua
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public String getDescLingua() {
    return descLingua;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column caronte.car_d_lingua.desc_lingua
   * @param descLingua  the value for caronte.car_d_lingua.desc_lingua
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setDescLingua(String descLingua) {
    this.descLingua = descLingua == null ? null : descLingua.trim();
  }
}