package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarDVersioneDomanda;
import it.aizoon.ersaf.dto.generati.CarDVersioneDomandaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarDVersioneDomandaMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarDVersioneDomanda, CarDVersioneDomandaExample> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_versione_domanda
	 * @mbg.generated  Wed Apr 19 10:14:39 CEST 2023
	 */
	long countByExample(CarDVersioneDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_versione_domanda
	 * @mbg.generated  Wed Apr 19 10:14:39 CEST 2023
	 */
	int deleteByExample(CarDVersioneDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_versione_domanda
	 * @mbg.generated  Wed Apr 19 10:14:39 CEST 2023
	 */
	int deleteByPrimaryKey(Long idVersioneDomanda);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_versione_domanda
	 * @mbg.generated  Wed Apr 19 10:14:39 CEST 2023
	 */
	int insert(CarDVersioneDomanda record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_versione_domanda
	 * @mbg.generated  Wed Apr 19 10:14:39 CEST 2023
	 */
	int insertSelective(CarDVersioneDomanda record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_versione_domanda
	 * @mbg.generated  Wed Apr 19 10:14:39 CEST 2023
	 */
	List<CarDVersioneDomanda> selectByExample(CarDVersioneDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_versione_domanda
	 * @mbg.generated  Wed Apr 19 10:14:39 CEST 2023
	 */
	CarDVersioneDomanda selectByPrimaryKey(Long idVersioneDomanda);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_versione_domanda
	 * @mbg.generated  Wed Apr 19 10:14:39 CEST 2023
	 */
	int updateByExampleSelective(@Param("record") CarDVersioneDomanda record,
			@Param("example") CarDVersioneDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_versione_domanda
	 * @mbg.generated  Wed Apr 19 10:14:39 CEST 2023
	 */
	int updateByExample(@Param("record") CarDVersioneDomanda record,
			@Param("example") CarDVersioneDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_versione_domanda
	 * @mbg.generated  Wed Apr 19 10:14:39 CEST 2023
	 */
	int updateByPrimaryKeySelective(CarDVersioneDomanda record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_versione_domanda
	 * @mbg.generated  Wed Apr 19 10:14:39 CEST 2023
	 */
	int updateByPrimaryKey(CarDVersioneDomanda record);
}