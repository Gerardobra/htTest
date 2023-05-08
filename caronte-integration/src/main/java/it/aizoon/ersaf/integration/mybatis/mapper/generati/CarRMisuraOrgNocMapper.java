package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarRMisuraOrgNoc;
import it.aizoon.ersaf.dto.generati.CarRMisuraOrgNocExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarRMisuraOrgNocMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarRMisuraOrgNoc, CarRMisuraOrgNocExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_org_noc
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    long countByExample(CarRMisuraOrgNocExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_org_noc
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    int deleteByExample(CarRMisuraOrgNocExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_org_noc
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    int insert(CarRMisuraOrgNoc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_org_noc
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    int insertSelective(CarRMisuraOrgNoc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_org_noc
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    List<CarRMisuraOrgNoc> selectByExample(CarRMisuraOrgNocExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_org_noc
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    int updateByExampleSelective(@Param("record") CarRMisuraOrgNoc record, @Param("example") CarRMisuraOrgNocExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_org_noc
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    int updateByExample(@Param("record") CarRMisuraOrgNoc record, @Param("example") CarRMisuraOrgNocExample example);
}