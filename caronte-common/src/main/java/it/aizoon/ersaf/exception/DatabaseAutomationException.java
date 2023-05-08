package it.aizoon.ersaf.exception;

public class DatabaseAutomationException extends InternalException {

  private static final long serialVersionUID = 1L;

  public DatabaseAutomationException(String message) {
    super(message);
  }

  public DatabaseAutomationException(String message, int errorCode) {
    super(message, errorCode);
  }

  public DatabaseAutomationException(String message, Throwable cause) {
    super(message, cause);
  }

  public DatabaseAutomationException(String message, int errorCode, Throwable cause) {
    super(message, errorCode, cause);
  }
}
