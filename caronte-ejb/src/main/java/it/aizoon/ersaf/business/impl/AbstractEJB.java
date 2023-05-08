package it.aizoon.ersaf.business.impl;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_ADD_BUSINESS;
import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import it.aizoon.ersaf.business.IAbstractEJB;
import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.exception.DatabaseAutomationException;
import it.aizoon.ersaf.exception.InternalUnexpectedException;
import it.aizoon.ersaf.integration.BaseDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao;

public abstract class AbstractEJB<TDAO extends BaseDAO, TMAPPER extends IBaseDao<E, K>, E extends BaseDto, K extends GenericExample>
    implements IAbstractEJB<E, K> {

  final String THIS_CLASS = AbstractEJB.class.getSimpleName();
  protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + LOGGER_ADD_BUSINESS);
  @Resource
  protected EJBContext context;
  protected TDAO dao;

  protected TMAPPER tmapper;

  // @Autowired
  @Inject
  public void setDao(TDAO dao) {
    this.dao = dao;
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public E queryForObject(SqlParameterSource parameters, Class<E> objClass)
      throws DatabaseAutomationException, BusinessException {
    return dao.queryForObject(parameters, objClass);
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<E> queryForList(SqlParameterSource parameters, Class<E> objClass)
      throws DatabaseAutomationException, BusinessException {
    return dao.queryForList(parameters, objClass);
  }

  @Override
  public Date getSysDate() throws InternalUnexpectedException, BusinessException {
    return dao.getSysDate();
  }

  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  public long countByExample(K example) throws BusinessException {
    return tmapper.countByExample(example);
  }

  public int deleteByExample(K example) throws BusinessException {
    return tmapper.deleteByExample(example);
  }

  public int deleteByPrimaryKey(Long idClasse) throws BusinessException {
    return tmapper.deleteByPrimaryKey(idClasse);
  }

  public int insert(E record) throws BusinessException {
    return tmapper.insert(record);
  }

  public int insertSelective(E record) throws BusinessException {
    return tmapper.insertSelective(record);
  }

  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  public List<E> selectByExample(K example) throws BusinessException {
    return tmapper.selectByExample(example);
  }

  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  public E selectByPrimaryKey(Long idClasse) throws BusinessException {
    return tmapper.selectByPrimaryKey(idClasse);
  }

  public int updateByExampleSelective(@Param("record") E record, @Param("example") K example) throws BusinessException {
    return tmapper.updateByExampleSelective(record, example);
  }

  public int updateByExample(@Param("record") E record, @Param("example") K example) throws BusinessException {
    return tmapper.updateByExample(record, example);
  }

  public int updateByPrimaryKeySelective(E record) throws BusinessException {
    return tmapper.updateByPrimaryKeySelective(record);
  }

  public int updateByPrimaryKey(E record) throws BusinessException {
    return tmapper.updateByPrimaryKey(record);
  }

  // esempio utilizzo reflection
  // @Override
  // public long countByExample(Object mapper, Object example){
  // return (long) invocateMethod(mapper,
  // Thread.currentThread().getStackTrace()[0].getMethodName(), example);
  // }
  //
  // private Object invocateMethod(Object cls, String methodName, Object...
  // param){
  // Object t = null;
  // try {
  // // t = cls.newInstance();
  // Method[] allMethods = cls.getClass().getDeclaredMethods();
  //
  // for (Method m : allMethods) {
  // String mname = m.getName();
  // if (mname.startsWith(methodName)) {
  // try {
  // // m.setAccessible(true);
  // if(null != param){
  // if(param.length == 1)
  // t = m.invoke(t, param[0]);
  // else if(param.length == 2)
  // t = m.invoke(t, param[0], param[1]);
  // }
  // } catch (InvocationTargetException e) {
  // // LogConfig.error(this, "Errore in fase di creazione del codice errore");
  // // LogConfig.dumpStackTrace(BusinessAspect.class, "[ aroundTask ]", e);
  // }
  // }
  // }
  // } catch (IllegalAccessException x) {
  // // LogConfig.dumpStackTrace(BusinessAspect.class, "[ aroundTask ]", x);
  // }
  // return t;
  // }

}