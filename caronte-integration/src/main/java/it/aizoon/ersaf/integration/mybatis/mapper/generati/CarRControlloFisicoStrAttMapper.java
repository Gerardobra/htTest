package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarRControlloFisicoStrAtt;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoStrAttExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarRControlloFisicoStrAttMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarRControlloFisicoStrAtt, CarRControlloFisicoStrAttExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_str_att
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    long countByExample(CarRControlloFisicoStrAttExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_str_att
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int deleteByExample(CarRControlloFisicoStrAttExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_str_att
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int deleteByPrimaryKey(@Param("idControlloFisico") Long idControlloFisico, @Param("idStrutturaAttrezzatura") Long idStrutturaAttrezzatura);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_str_att
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int insert(CarRControlloFisicoStrAtt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_str_att
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int insertSelective(CarRControlloFisicoStrAtt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_str_att
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    List<CarRControlloFisicoStrAtt> selectByExample(CarRControlloFisicoStrAttExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_str_att
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    CarRControlloFisicoStrAtt selectByPrimaryKey(@Param("idControlloFisico") Long idControlloFisico, @Param("idStrutturaAttrezzatura") Long idStrutturaAttrezzatura);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_str_att
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int updateByExampleSelective(@Param("record") CarRControlloFisicoStrAtt record, @Param("example") CarRControlloFisicoStrAttExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_str_att
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int updateByExample(@Param("record") CarRControlloFisicoStrAtt record, @Param("example") CarRControlloFisicoStrAttExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_str_att
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int updateByPrimaryKeySelective(CarRControlloFisicoStrAtt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_str_att
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int updateByPrimaryKey(CarRControlloFisicoStrAtt record);
}