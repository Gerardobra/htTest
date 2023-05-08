package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarTMisuraUfficiale;
import it.aizoon.ersaf.dto.generati.CarTMisuraUfficialeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarTMisuraUfficialeMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarTMisuraUfficiale, CarTMisuraUfficialeExample> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	long countByExample(CarTMisuraUfficialeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	int deleteByExample(CarTMisuraUfficialeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	int deleteByPrimaryKey(Long idMisuraUfficiale);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	int insert(CarTMisuraUfficiale record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	int insertSelective(CarTMisuraUfficiale record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	List<CarTMisuraUfficiale> selectByExampleWithBLOBs(CarTMisuraUfficialeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	List<CarTMisuraUfficiale> selectByExample(CarTMisuraUfficialeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	CarTMisuraUfficiale selectByPrimaryKey(Long idMisuraUfficiale);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	int updateByExampleSelective(@Param("record") CarTMisuraUfficiale record,
			@Param("example") CarTMisuraUfficialeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	int updateByExampleWithBLOBs(@Param("record") CarTMisuraUfficiale record,
			@Param("example") CarTMisuraUfficialeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	int updateByExample(@Param("record") CarTMisuraUfficiale record,
			@Param("example") CarTMisuraUfficialeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	int updateByPrimaryKeySelective(CarTMisuraUfficiale record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	int updateByPrimaryKeyWithBLOBs(CarTMisuraUfficiale record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_misura_ufficiale
	 * @mbg.generated  Thu Mar 25 11:14:20 CET 2021
	 */
	int updateByPrimaryKey(CarTMisuraUfficiale record);
}