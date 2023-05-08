package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarDProvincia;
import it.aizoon.ersaf.dto.generati.CarDProvinciaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarDProvinciaMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarDProvincia, CarDProvinciaExample> {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_provincia
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  long countByExample(CarDProvinciaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_provincia
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int deleteByExample(CarDProvinciaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_provincia
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int deleteByPrimaryKey(Long idProvincia);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_provincia
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int insert(CarDProvincia record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_provincia
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int insertSelective(CarDProvincia record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_provincia
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  List<CarDProvincia> selectByExample(CarDProvinciaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_provincia
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  CarDProvincia selectByPrimaryKey(Long idProvincia);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_provincia
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByExampleSelective(@Param("record") CarDProvincia record, @Param("example") CarDProvinciaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_provincia
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByExample(@Param("record") CarDProvincia record, @Param("example") CarDProvinciaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_provincia
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByPrimaryKeySelective(CarDProvincia record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_provincia
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByPrimaryKey(CarDProvincia record);
}