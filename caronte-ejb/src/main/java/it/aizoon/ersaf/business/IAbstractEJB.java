package it.aizoon.ersaf.business;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.exception.DatabaseAutomationException;
import it.aizoon.ersaf.exception.InternalUnexpectedException;

public interface IAbstractEJB<E extends BaseDto, K extends GenericExample> {

  public E queryForObject(SqlParameterSource parameters, Class<E> objClass)
      throws DatabaseAutomationException, BusinessException;

  public List<E> queryForList(SqlParameterSource parameters, Class<E> objClass)
      throws DatabaseAutomationException, BusinessException;

  public Date getSysDate() throws InternalUnexpectedException, BusinessException;

  public abstract long countByExample(K example) throws BusinessException;

  public abstract int deleteByExample(K example) throws BusinessException;

  public abstract int deleteByPrimaryKey(Long idClasse) throws BusinessException;

  public abstract int insert(E record) throws BusinessException;

  public abstract int insertSelective(E record) throws BusinessException;

  public abstract List<E> selectByExample(K example) throws BusinessException;

  public abstract E selectByPrimaryKey(Long idClasse) throws BusinessException;

  public abstract int updateByExampleSelective(@Param("record") E record, @Param("example") K example)
      throws BusinessException;

  public abstract int updateByExample(@Param("record") E record, @Param("example") K example) throws BusinessException;

  public abstract int updateByPrimaryKeySelective(E record) throws BusinessException;

  public abstract int updateByPrimaryKey(E record) throws BusinessException;

}