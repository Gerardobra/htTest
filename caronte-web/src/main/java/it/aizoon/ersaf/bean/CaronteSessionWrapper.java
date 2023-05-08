package it.aizoon.ersaf.bean;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * @author Ivan Morra
 *
 */
public class CaronteSessionWrapper implements HttpSession {

  private HttpSession session;
  private static final String KEEP_SET_LABEL = "keep.set.label"; 
  
  public CaronteSessionWrapper(HttpSession session) {
    this.session = session;
  }
  
  public long getCreationTime() {
    return session.getCreationTime();
  }

  public String getId() {
    return session.getId();
  }

  public long getLastAccessedTime() {
    return session.getLastAccessedTime();
  }

  public ServletContext getServletContext() {
    return session.getServletContext();
  }

  public void setMaxInactiveInterval(int interval) {
    session.setMaxInactiveInterval(interval);
  }

  public int getMaxInactiveInterval() {
    return session.getMaxInactiveInterval();
  }

  @Deprecated
  public javax.servlet.http.HttpSessionContext getSessionContext() {
    return session.getSessionContext();
  }

  public Object getAttribute(String name) {
    return session.getAttribute(name);
  }

  @Deprecated
  public Object getValue(String name) {
    return session.getValue(name);
  }

  public Enumeration<String> getAttributeNames() {
    return session.getAttributeNames();
  }

  @Deprecated
  public String[] getValueNames() {
    return session.getValueNames();
  }

  public void setAttribute(String name, Object value) {
    session.setAttribute(name, value);
  }

  @Deprecated
  public void putValue(String name, Object value) {
    session.putValue(name, value);
  }

  public void removeAttribute(String name) {
    Set<String> keepAttributes = getKeepAttributes();
    
    if (keepAttributes != null && keepAttributes.contains(name)) {
      keepAttributes.remove(name);
    }else {
      session.removeAttribute(name);
    }
  }

  @Deprecated
  public void removeValue(String name) {
    session.removeValue(name);
  }

  public void invalidate() {
    session.invalidate();
  }

  public boolean isNew() {
    return session.isNew();
  }
  
  public void addKeepAttribute(String name) {
    getKeepAttributes().add(name);
  }
  
  @SuppressWarnings("unchecked")
  private Set<String> getKeepAttributes() {
    Set<String> keepAttributes = (Set<String>)getAttribute(KEEP_SET_LABEL);
    
    if (keepAttributes == null) {
      keepAttributes = new HashSet<>();
      setAttribute(KEEP_SET_LABEL, keepAttributes);
    }
    
    return keepAttributes;
  }

}
