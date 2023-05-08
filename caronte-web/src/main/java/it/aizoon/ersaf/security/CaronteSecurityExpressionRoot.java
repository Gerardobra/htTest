package it.aizoon.ersaf.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CaronteSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

  private Object filterObject;
  private Object returnObject;
  private Object target;
  
  public CaronteSecurityExpressionRoot(Authentication authentication) {
    super(authentication);
  }
  
  public boolean hasRoleImpExp(String role, HttpServletRequest request) {
    String requestURI = request == null ? "" : request.getRequestURI();
    String testRole = role;
    
    
    if (requestURI.contains("/export/")) {
      testRole += "_EXPORT";
    }
    else if (requestURI.contains("/vivai/")) {
      testRole += "_VIVAI";
    }
    else if (requestURI.contains("/import/")) {
      testRole += "_IMPORT";
    }
    else if (requestURI.contains("/autorizzazioni/")) {
      testRole += "_AUTORIZ";
    }
    else if (requestURI.contains("/controlli/")) {
        testRole += "_CONTROLLI";
    }
    
    //System.out.println("\nIN hasRoleImpExp - TEST ROLE: "+testRole);
    
    return this.hasRole(testRole);
  }

  void setThis(Object target) {
    this.target = target;
  }
  
  @Override
  public Object getThis() {
    return target;
  }

  @Override
  public void setFilterObject(Object filterObject) {
    this.filterObject = filterObject;
  }
  
  @Override
  public Object getFilterObject() {
    return filterObject;
  }

  @Override
  public void setReturnObject(Object returnObject) {
    this.returnObject = returnObject;    
  }
  
  @Override
  public Object getReturnObject() {
    return returnObject;
  }

}
