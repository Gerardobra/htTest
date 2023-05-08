package it.aizoon.ersaf.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * @author Ivan Morra
 */
public class CaronteRequestWrapper extends HttpServletRequestWrapper {

  public CaronteRequestWrapper(HttpServletRequest request) {
    super(request);
  }
  
  public HttpSession getSession() {
    return getSession(true);
  }

  public HttpSession getSession(boolean createNew) {
    HttpSession session = super.getSession(createNew);
    
    if (session == null) {
      return session;
    }
    
    return new CaronteSessionWrapper(session);
  }

}
