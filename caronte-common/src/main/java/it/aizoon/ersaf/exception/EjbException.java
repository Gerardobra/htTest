package it.aizoon.ersaf.exception;

/**
 * @author ivan.morra
 *
 */
public class EjbException extends BusinessException {

  private static final long serialVersionUID = 1L;

  public EjbException(String message) {
    super(message);
  }
  
  public EjbException(String message, Throwable cause) {
    super(message, cause);
  }

}
