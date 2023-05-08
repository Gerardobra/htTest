package it.aizoon.ersaf.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author Ivan Morra
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ViewMessageHolder implements Serializable {

  private static final long serialVersionUID = 1L;
  private String errorMessage;
  private String successMessage;
  private String alertSuccessMessage;
  
  public String getErrorMessage() {
    return errorMessage;
  }
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
  public String getSuccessMessage() {
    return successMessage;
  }
  public void setSuccessMessage(String successMessage) {
    this.successMessage = successMessage;
  }
  public String getAlertSuccessMessage() {
    return alertSuccessMessage;
  }
  public void setAlertSuccessMessage(String alertSuccessMessage) {
    this.alertSuccessMessage = alertSuccessMessage;
  }
  
}
