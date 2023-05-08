package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarTCertificato;
import it.aizoon.ersaf.dto.generati.CarTCertificatoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarTCertificatoMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarTCertificato, CarTCertificatoExample> {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_certificato
   * @mbg.generated  Wed Oct 31 16:30:00 CET 2018
   */
  long countByExample(CarTCertificatoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_certificato
   * @mbg.generated  Wed Oct 31 16:30:00 CET 2018
   */
  int deleteByExample(CarTCertificatoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_certificato
   * @mbg.generated  Wed Oct 31 16:30:00 CET 2018
   */
  int deleteByPrimaryKey(Long idCertificato);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_certificato
   * @mbg.generated  Wed Oct 31 16:30:00 CET 2018
   */
  int insert(CarTCertificato record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_certificato
   * @mbg.generated  Wed Oct 31 16:30:00 CET 2018
   */
  int insertSelective(CarTCertificato record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_certificato
   * @mbg.generated  Wed Oct 31 16:30:00 CET 2018
   */
  List<CarTCertificato> selectByExample(CarTCertificatoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_certificato
   * @mbg.generated  Wed Oct 31 16:30:00 CET 2018
   */
  CarTCertificato selectByPrimaryKey(Long idCertificato);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_certificato
   * @mbg.generated  Wed Oct 31 16:30:00 CET 2018
   */
  int updateByExampleSelective(@Param("record") CarTCertificato record,
      @Param("example") CarTCertificatoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_certificato
   * @mbg.generated  Wed Oct 31 16:30:00 CET 2018
   */
  int updateByExample(@Param("record") CarTCertificato record, @Param("example") CarTCertificatoExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_certificato
   * @mbg.generated  Wed Oct 31 16:30:00 CET 2018
   */
  int updateByPrimaryKeySelective(CarTCertificato record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_certificato
   * @mbg.generated  Wed Oct 31 16:30:00 CET 2018
   */
  int updateByPrimaryKey(CarTCertificato record);
}