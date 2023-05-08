package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.CarRCampionamentoPartita;
import it.aizoon.ersaf.dto.generati.CarRCampionamentoPartitaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarRCampionamentoPartitaMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<CarRCampionamentoPartita, CarRCampionamentoPartitaExample> {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_r_campionamento_partita
   * @mbg.generated  Fri Jan 11 18:47:26 CET 2019
   */
  long countByExample(CarRCampionamentoPartitaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_r_campionamento_partita
   * @mbg.generated  Fri Jan 11 18:47:26 CET 2019
   */
  int deleteByExample(CarRCampionamentoPartitaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_r_campionamento_partita
   * @mbg.generated  Fri Jan 11 18:47:26 CET 2019
   */
  int insert(CarRCampionamentoPartita record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_r_campionamento_partita
   * @mbg.generated  Fri Jan 11 18:47:26 CET 2019
   */
  int insertSelective(CarRCampionamentoPartita record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_r_campionamento_partita
   * @mbg.generated  Fri Jan 11 18:47:26 CET 2019
   */
  List<CarRCampionamentoPartita> selectByExample(CarRCampionamentoPartitaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_r_campionamento_partita
   * @mbg.generated  Fri Jan 11 18:47:26 CET 2019
   */
  int updateByExampleSelective(@Param("record") CarRCampionamentoPartita record,
      @Param("example") CarRCampionamentoPartitaExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_r_campionamento_partita
   * @mbg.generated  Fri Jan 11 18:47:26 CET 2019
   */
  int updateByExample(@Param("record") CarRCampionamentoPartita record,
      @Param("example") CarRCampionamentoPartitaExample example);
}