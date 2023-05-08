package it.aizoon.ersaf.integration.mybatis.mapper.generati;

import it.aizoon.ersaf.dto.generati.TestUtente;
import it.aizoon.ersaf.dto.generati.TestUtenteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface TestUtenteMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<TestUtente, TestUtenteExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.test_utente
     *
     * @mbg.generated Fri Nov 17 17:20:50 CET 2017
     */
    long countByExample(TestUtenteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.test_utente
     *
     * @mbg.generated Fri Nov 17 17:20:50 CET 2017
     */
    int deleteByExample(TestUtenteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.test_utente
     *
     * @mbg.generated Fri Nov 17 17:20:50 CET 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.test_utente
     *
     * @mbg.generated Fri Nov 17 17:20:50 CET 2017
     */
    int insert(TestUtente record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.test_utente
     *
     * @mbg.generated Fri Nov 17 17:20:50 CET 2017
     */
    int insertSelective(TestUtente record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.test_utente
     *
     * @mbg.generated Fri Nov 17 17:20:50 CET 2017
     */
    List<TestUtente> selectByExample(TestUtenteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.test_utente
     *
     * @mbg.generated Fri Nov 17 17:20:50 CET 2017
     */
    TestUtente selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.test_utente
     *
     * @mbg.generated Fri Nov 17 17:20:50 CET 2017
     */
    int updateByExampleSelective(@Param("record") TestUtente record, @Param("example") TestUtenteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.test_utente
     *
     * @mbg.generated Fri Nov 17 17:20:50 CET 2017
     */
    int updateByExample(@Param("record") TestUtente record, @Param("example") TestUtenteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.test_utente
     *
     * @mbg.generated Fri Nov 17 17:20:50 CET 2017
     */
    int updateByPrimaryKeySelective(TestUtente record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.test_utente
     *
     * @mbg.generated Fri Nov 17 17:20:50 CET 2017
     */
    int updateByPrimaryKey(TestUtente record);
}