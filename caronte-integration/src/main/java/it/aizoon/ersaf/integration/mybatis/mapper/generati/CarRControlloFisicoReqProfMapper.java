package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarRControlloFisicoReqProf;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoReqProfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarRControlloFisicoReqProfMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarRControlloFisicoReqProf, CarRControlloFisicoReqProfExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    long countByExample(CarRControlloFisicoReqProfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int deleteByExample(CarRControlloFisicoReqProfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int deleteByPrimaryKey(@Param("idControlloFisico") Long idControlloFisico, @Param("idRequisitoProfessionale") Long idRequisitoProfessionale);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int insert(CarRControlloFisicoReqProf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int insertSelective(CarRControlloFisicoReqProf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    List<CarRControlloFisicoReqProf> selectByExample(CarRControlloFisicoReqProfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    CarRControlloFisicoReqProf selectByPrimaryKey(@Param("idControlloFisico") Long idControlloFisico, @Param("idRequisitoProfessionale") Long idRequisitoProfessionale);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int updateByExampleSelective(@Param("record") CarRControlloFisicoReqProf record, @Param("example") CarRControlloFisicoReqProfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int updateByExample(@Param("record") CarRControlloFisicoReqProf record, @Param("example") CarRControlloFisicoReqProfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int updateByPrimaryKeySelective(CarRControlloFisicoReqProf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    int updateByPrimaryKey(CarRControlloFisicoReqProf record);
}