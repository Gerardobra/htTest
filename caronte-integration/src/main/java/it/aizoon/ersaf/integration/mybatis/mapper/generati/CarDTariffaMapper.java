package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarDTariffa;
import it.aizoon.ersaf.dto.generati.CarDTariffaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarDTariffaMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarDTariffa, CarDTariffaExample> {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  long countByExample(CarDTariffaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int deleteByExample(CarDTariffaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int deleteByPrimaryKey(Long idTariffa);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int insert(CarDTariffa record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int insertSelective(CarDTariffa record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  List<CarDTariffa> selectByExample(CarDTariffaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  CarDTariffa selectByPrimaryKey(Long idTariffa);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByExampleSelective(@Param("record") CarDTariffa record, @Param("example") CarDTariffaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByExample(@Param("record") CarDTariffa record, @Param("example") CarDTariffaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByPrimaryKeySelective(CarDTariffa record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByPrimaryKey(CarDTariffa record);
}