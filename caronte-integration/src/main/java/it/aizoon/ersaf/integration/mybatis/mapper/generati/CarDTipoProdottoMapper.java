package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarDTipoProdotto;
import it.aizoon.ersaf.dto.generati.CarDTipoProdottoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarDTipoProdottoMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarDTipoProdotto, CarDTipoProdottoExample> {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_prodotto
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  long countByExample(CarDTipoProdottoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_prodotto
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int deleteByExample(CarDTipoProdottoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_prodotto
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int deleteByPrimaryKey(Long idTipoProdotto);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_prodotto
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int insert(CarDTipoProdotto record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_prodotto
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int insertSelective(CarDTipoProdotto record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_prodotto
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  List<CarDTipoProdotto> selectByExample(CarDTipoProdottoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_prodotto
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  CarDTipoProdotto selectByPrimaryKey(Long idTipoProdotto);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_prodotto
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByExampleSelective(@Param("record") CarDTipoProdotto record,
      @Param("example") CarDTipoProdottoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_prodotto
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByExample(@Param("record") CarDTipoProdotto record, @Param("example") CarDTipoProdottoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_prodotto
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByPrimaryKeySelective(CarDTipoProdotto record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_prodotto
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  int updateByPrimaryKey(CarDTipoProdotto record);
}