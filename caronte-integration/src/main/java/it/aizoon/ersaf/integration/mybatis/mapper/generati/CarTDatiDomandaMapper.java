package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarTDatiDomanda;
import it.aizoon.ersaf.dto.generati.CarTDatiDomandaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarTDatiDomandaMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarTDatiDomanda, CarTDatiDomandaExample> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	long countByExample(CarTDatiDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	int deleteByExample(CarTDatiDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	int deleteByPrimaryKey(Long idDatiDomanda);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	int insert(CarTDatiDomanda record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	int insertSelective(CarTDatiDomanda record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	List<CarTDatiDomanda> selectByExampleWithBLOBs(CarTDatiDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	List<CarTDatiDomanda> selectByExample(CarTDatiDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	CarTDatiDomanda selectByPrimaryKey(Long idDatiDomanda);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	int updateByExampleSelective(@Param("record") CarTDatiDomanda record,
			@Param("example") CarTDatiDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	int updateByExampleWithBLOBs(@Param("record") CarTDatiDomanda record,
			@Param("example") CarTDatiDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	int updateByExample(@Param("record") CarTDatiDomanda record, @Param("example") CarTDatiDomandaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	int updateByPrimaryKeySelective(CarTDatiDomanda record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	int updateByPrimaryKeyWithBLOBs(CarTDatiDomanda record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_dati_domanda
	 * @mbg.generated  Mon Sep 28 14:55:54 CEST 2020
	 */
	int updateByPrimaryKey(CarTDatiDomanda record);
}