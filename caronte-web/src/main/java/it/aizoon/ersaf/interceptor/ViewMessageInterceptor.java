package it.aizoon.ersaf.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import it.aizoon.ersaf.bean.ViewMessageHolder;
import it.aizoon.ersaf.util.CaronteConstants;

/**
 * @author Ivan Morra
 */
public class ViewMessageInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private ViewMessageHolder viewMessages;
  
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    /*
     * Se si tratta di una redirect, non si leggono i messaggi
     */
    if (modelAndView != null && !(modelAndView.getView() instanceof RedirectView)
        && !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect:")) {
      if (viewMessages != null) {
        String messaggioSuccesso = viewMessages.getSuccessMessage();
    
        if (messaggioSuccesso != null) {
          modelAndView.getModel().putIfAbsent("page_success", messaggioSuccesso);
          viewMessages.setSuccessMessage(null);
        }
    
        String messaggioErrore = viewMessages.getErrorMessage();
    
        if (messaggioErrore != null) {
          modelAndView.getModel().putIfAbsent("page_error", messaggioErrore);
          viewMessages.setErrorMessage(null);
        }
        
        messaggioSuccesso = viewMessages.getAlertSuccessMessage();
        
        if (messaggioSuccesso != null) {
          modelAndView.getModel().putIfAbsent("page_alert_success", messaggioSuccesso);
          viewMessages.setAlertSuccessMessage(null);
        }
      }
    }
  }
}
