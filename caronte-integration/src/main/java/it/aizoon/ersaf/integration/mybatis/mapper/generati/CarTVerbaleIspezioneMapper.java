package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarTVerbaleIspezione;
import it.aizoon.ersaf.dto.generati.CarTVerbaleIspezioneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarTVerbaleIspezioneMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarTVerbaleIspezione, CarTVerbaleIspezioneExample> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_verbale_ispezione
	 * @mbg.generated  Wed Dec 19 11:28:28 CET 2018
	 */
	long countByExample(CarTVerbaleIspezioneExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_verbale_ispezione
	 * @mbg.generated  Wed Dec 19 11:28:28 CET 2018
	 */
	int deleteByExample(CarTVerbaleIspezioneExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_verbale_ispezione
	 * @mbg.generated  Wed Dec 19 11:28:28 CET 2018
	 */
	int deleteByPrimaryKey(Long idVerbaleIspezione);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_verbale_ispezione
	 * @mbg.generated  Wed Dec 19 11:28:28 CET 2018
	 */
	int insert(CarTVerbaleIspezione record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_verbale_ispezione
	 * @mbg.generated  Wed Dec 19 11:28:28 CET 2018
	 */
	int insertSelective(CarTVerbaleIspezione record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_verbale_ispezione
	 * @mbg.generated  Wed Dec 19 11:28:28 CET 2018
	 */
	List<CarTVerbaleIspezione> selectByExample(CarTVerbaleIspezioneExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_verbale_ispezione
	 * @mbg.generated  Wed Dec 19 11:28:28 CET 2018
	 */
	CarTVerbaleIspezione selectByPrimaryKey(Long idVerbaleIspezione);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_verbale_ispezione
	 * @mbg.generated  Wed Dec 19 11:28:28 CET 2018
	 */
	int updateByExampleSelective(@Param("record") CarTVerbaleIspezione record,
			@Param("example") CarTVerbaleIspezioneExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_verbale_ispezione
	 * @mbg.generated  Wed Dec 19 11:28:28 CET 2018
	 */
	int updateByExample(@Param("record") CarTVerbaleIspezione record,
			@Param("example") CarTVerbaleIspezioneExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_verbale_ispezione
	 * @mbg.generated  Wed Dec 19 11:28:28 CET 2018
	 */
	int updateByPrimaryKeySelective(CarTVerbaleIspezione record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_verbale_ispezione
	 * @mbg.generated  Wed Dec 19 11:28:28 CET 2018
	 */
	int updateByPrimaryKey(CarTVerbaleIspezione record);
}