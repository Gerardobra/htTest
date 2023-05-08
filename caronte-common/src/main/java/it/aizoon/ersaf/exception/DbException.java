package it.aizoon.ersaf.exception;

/**
 * @author ivan.morra
 *
 */
public class DbException extends BusinessException {
  
  private static final long serialVersionUID = 1L;

  public DbException(String message) {
    super(message);
  }
  
  public DbException(String message, Throwable cause) {
    super(message, cause);
  }

}
