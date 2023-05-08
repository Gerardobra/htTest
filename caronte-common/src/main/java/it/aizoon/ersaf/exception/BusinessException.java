package it.aizoon.ersaf.exception;

import javax.ejb.ApplicationException;

import it.aizoon.ersaf.util.CaronteUtils;

/**
 * @author ivan.morra
 *
 */

@ApplicationException(rollback=true)
public class BusinessException extends Exception {

  private static final long serialVersionUID = 1L;

  public final String MSG_GENERIC_ERROR = CaronteUtils.getMessage("error.business");

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

}
