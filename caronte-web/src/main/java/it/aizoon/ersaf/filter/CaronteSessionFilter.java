package it.aizoon.ersaf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import it.aizoon.ersaf.bean.CaronteRequestWrapper;

/**
 * @author Ivan Morra
 */
@Component
public class CaronteSessionFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    CaronteRequestWrapper customRequest =
      new CaronteRequestWrapper(httpRequest);

    chain.doFilter(customRequest, response);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    //Filter.super.init(filterConfig);
  }

  @Override
  public void destroy() {
    //Filter.super.destroy();
  }

}
