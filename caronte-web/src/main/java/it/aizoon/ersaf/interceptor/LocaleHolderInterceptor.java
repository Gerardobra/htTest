package it.aizoon.ersaf.interceptor;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * Interceptor che memorizza il valore del Locale selezionato in un thread local per permettere
 * alle componenti che non hanno accesso alla request di poter avere l'informazione tramite il 
 * valore in LocaleContextHolder.getLocale() (che, senza questo interceptor, contiene di default 
 * il locale ricavato dalla richiesta web)
 */
public class LocaleHolderInterceptor extends HandlerInterceptorAdapter {

  private static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + "." + LocaleHolderInterceptor.class);
  @Autowired
  private LocaleResolver localeResolver;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    logger.info("preHandle - LocaleResolver: " + localeResolver);

    if (localeResolver != null) {
      Locale locale = localeResolver.resolveLocale(request);
      LocaleContextHolder.setLocale(locale);
    }

    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
      Exception ex) throws Exception {
    logger.info("afterCompletion - Locale: " + LocaleContextHolder.getLocale());

    if (localeResolver != null) {
      LocaleContextHolder.resetLocaleContext();
    }

  }

}
