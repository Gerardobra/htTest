package it.aizoon.ersaf.bean;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Ivan Morra
 *
 */
public class CaronteViewResolver extends InternalResourceViewResolver {

  protected String getPrefix() {
    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
    
    if (request != null) {
      String requestURI = request.getRequestURI();
      
      if (requestURI.contains("/export/")) {
        return super.getPrefix()+"export/";
      }
      
      if (requestURI.contains("/vivai/")) {
        return super.getPrefix()+"vivai/";
      }
      
      if (requestURI.contains("/autorizzazioni/")) {
          return super.getPrefix()+"autorizzazioni/";
      }
      
      if (requestURI.contains("/controlli/")) {
          return super.getPrefix()+"controlli/";
      }
    }

    return super.getPrefix();
  }

}
