package it.aizoon.ersaf.exception;

import it.aizoon.ersaf.util.CaronteUtils;

public class InternalException extends LocalException {

  private static final long serialVersionUID = 1L;
  public final String MSG_GENERIC_ERROR = CaronteUtils.getMessage("error.internal");

  public InternalException(String message) {
    super(message);
  }

  public InternalException(String message, int errorCode) {
    super(message, errorCode);
  }

  public InternalException(String message, Throwable cause) {
    super(message, cause);
  }

  public InternalException(String message, int errorCode, Throwable cause) {
    super(message, errorCode, cause);
  }
}
