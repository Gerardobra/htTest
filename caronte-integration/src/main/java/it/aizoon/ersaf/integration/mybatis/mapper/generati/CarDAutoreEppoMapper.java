package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarDAutoreEppo;
import it.aizoon.ersaf.dto.generati.CarDAutoreEppoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarDAutoreEppoMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarDAutoreEppo, CarDAutoreEppoExample> {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  long countByExample(CarDAutoreEppoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int deleteByExample(CarDAutoreEppoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int deleteByPrimaryKey(Long idAutoreEppo);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int insert(CarDAutoreEppo record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int insertSelective(CarDAutoreEppo record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  List<CarDAutoreEppo> selectByExample(CarDAutoreEppoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  CarDAutoreEppo selectByPrimaryKey(Long idAutoreEppo);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByExampleSelective(@Param("record") CarDAutoreEppo record, @Param("example") CarDAutoreEppoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByExample(@Param("record") CarDAutoreEppo record, @Param("example") CarDAutoreEppoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByPrimaryKeySelective(CarDAutoreEppo record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_autore_eppo
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByPrimaryKey(CarDAutoreEppo record);
}