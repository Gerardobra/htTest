package it.aizoon.ersaf.interceptor;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_ADD_BUSINESS;
import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ivan.morra
 *
 */
@Interceptor
public class LoggingInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + LOGGER_ADD_BUSINESS);
  
  @AroundInvoke
  public Object logMethod(InvocationContext ic) throws Exception {
    final String METHOD_NAME = "[" + ic.getTarget().getClass().getSimpleName() + "." + ic.getMethod().getName() + "]";
    long start = System.currentTimeMillis();
    Object result = null;
    
    logger.debug(METHOD_NAME+" - BEGIN");
    
    try {
      //Invocazione del metodo di business
      result =  ic.proceed();
    }catch (Exception exc) {
      logger.error(METHOD_NAME+" - EXCEPTION", exc);
      throw exc;
    }finally {
      long time = System.currentTimeMillis() - start;
      logger.debug(METHOD_NAME+" - END ({} ms)", time);
    }
    
    return result;
  }
}
