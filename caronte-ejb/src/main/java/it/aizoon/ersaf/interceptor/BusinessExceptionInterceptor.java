package it.aizoon.ersaf.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.ibatis.exceptions.PersistenceException;

import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.exception.DbException;
import it.aizoon.ersaf.exception.EjbException;

/**
 * @author ivan.morra
 *
 */

@Interceptor
public class BusinessExceptionInterceptor {

  @AroundInvoke
  public Object wrapException(InvocationContext ic) throws BusinessException {
    Object result = null;
    
    try {
      //Invocazione del metodo di business
      result =  ic.proceed();
    }catch (PersistenceException exc) {      
      throw new DbException(exc.getMessage(), exc);
    }catch (Exception exc) {
      throw new EjbException(exc.getMessage(), exc);
    }
    
    return result;
  }
}
