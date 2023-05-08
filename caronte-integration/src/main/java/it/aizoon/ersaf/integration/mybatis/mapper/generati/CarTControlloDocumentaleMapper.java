package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarTControlloDocumentale;
import it.aizoon.ersaf.dto.generati.CarTControlloDocumentaleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarTControlloDocumentaleMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarTControlloDocumentale, CarTControlloDocumentaleExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_t_controllo_documentale
     *
     * @mbg.generated Tue Dec 22 12:22:59 CET 2020
     */
    long countByExample(CarTControlloDocumentaleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_t_controllo_documentale
     *
     * @mbg.generated Tue Dec 22 12:22:59 CET 2020
     */
    int deleteByExample(CarTControlloDocumentaleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_t_controllo_documentale
     *
     * @mbg.generated Tue Dec 22 12:22:59 CET 2020
     */
    int deleteByPrimaryKey(Long idControlloDocumentale);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_t_controllo_documentale
     *
     * @mbg.generated Tue Dec 22 12:22:59 CET 2020
     */
    int insert(CarTControlloDocumentale record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_t_controllo_documentale
     *
     * @mbg.generated Tue Dec 22 12:22:59 CET 2020
     */
    int insertSelective(CarTControlloDocumentale record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_t_controllo_documentale
     *
     * @mbg.generated Tue Dec 22 12:22:59 CET 2020
     */
    List<CarTControlloDocumentale> selectByExample(CarTControlloDocumentaleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_t_controllo_documentale
     *
     * @mbg.generated Tue Dec 22 12:22:59 CET 2020
     */
    CarTControlloDocumentale selectByPrimaryKey(Long idControlloDocumentale);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_t_controllo_documentale
     *
     * @mbg.generated Tue Dec 22 12:22:59 CET 2020
     */
    int updateByExampleSelective(@Param("record") CarTControlloDocumentale record, @Param("example") CarTControlloDocumentaleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_t_controllo_documentale
     *
     * @mbg.generated Tue Dec 22 12:22:59 CET 2020
     */
    int updateByExample(@Param("record") CarTControlloDocumentale record, @Param("example") CarTControlloDocumentaleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_t_controllo_documentale
     *
     * @mbg.generated Tue Dec 22 12:22:59 CET 2020
     */
    int updateByPrimaryKeySelective(CarTControlloDocumentale record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_t_controllo_documentale
     *
     * @mbg.generated Tue Dec 22 12:22:59 CET 2020
     */
    int updateByPrimaryKey(CarTControlloDocumentale record);
}