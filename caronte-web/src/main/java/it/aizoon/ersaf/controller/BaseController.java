package it.aizoon.ersaf.controller;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.aizoon.ersaf.bean.CaronteSessionWrapper;
import it.aizoon.ersaf.bean.ViewMessageHolder;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.formatter.BigDecimalEditor;
import it.aizoon.ersaf.formatter.DoubleEditor;
import it.aizoon.ersaf.formatter.LocalTimeEditor;
import it.aizoon.ersaf.util.CaronteConstants;

public abstract class BaseController {

  protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".controller");

  @Autowired
  protected ViewMessageHolder viewMessages;

  @Autowired
  @Qualifier("mvcValidator")
  Validator validator;

  /*
   * Il metodo reindirizza tutte le eccezioni business non gestite a una
   * generica pagina di errore
   */
  @ExceptionHandler(BusinessException.class)
  public ModelAndView handleError(HttpServletRequest req, Exception ex) {
    logger.error("Request: " + req.getRequestURL() + " raised " + ex);

    ModelAndView mav = new ModelAndView();
    InternalResourceView errorView = new InternalResourceView("/WEB-INF/jsp/error.jsp");
    mav.addObject("exception", ex);
    mav.addObject("url", req.getRequestURL());
    mav.setView(errorView);
    // mav.setViewName("error");
    return mav;
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(String.class,
        new StringTrimmerEditor(/* " \t\r\n", */true));
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
    binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor(Locale.ITALIAN));
    binder.registerCustomEditor(Double.class, new DoubleEditor(Locale.ITALIAN));
    binder.registerCustomEditor(LocalTime.class, new LocalTimeEditor());
    binder.setValidator(this.validator);
  }
  
  protected ResponseEntity<String> getJsonResult(Object obj) {
    try {
      return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(obj), HttpStatus.OK);
    } catch (JsonProcessingException e) {
      return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  protected void addErrorMessage(String message) {
    viewMessages.setErrorMessage(message);
  }

  protected void addSuccessMessage(String message) {
    viewMessages.setSuccessMessage(message);
  }

  protected void addAlertSuccessMessage(String message) {
    viewMessages.setAlertSuccessMessage(message);
  }

  protected String getRedirect(String path) {
    return getRedirect(path, null);
  }

  protected String getRedirect(String path, HttpServletRequest request) {
    String requestURI = request == null ? "" : request.getRequestURI();

    if (requestURI.contains("/import/")) {
      return "redirect:"+ CaronteConstants.PATH_ASSOCIAZIONE_SEZIONE_IMPORT + path;
    } else if (requestURI.contains("/export/")) {
      return "redirect:"+ CaronteConstants.PATH_ASSOCIAZIONE_SEZIONE_EXPORT + path;
    } else if (requestURI.contains("/vivai/")) {
      return "redirect:"+ CaronteConstants.PATH_ASSOCIAZIONE_SEZIONE_VIVAI + path;
    } else if (requestURI.contains("/autorizzazioni/")) {
      return "redirect:"+ CaronteConstants.PATH_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI + path;
    } else if (requestURI.contains("/controlli/")) {
      return "redirect:"+ CaronteConstants.PATH_ASSOCIAZIONE_SEZIONE_CONTROLLI + path;
    }else {
      return "redirect:/" + path;
    }
  }

  protected boolean isRichiestaImport(HttpServletRequest request) {
    return request.getRequestURI().contains("/import/");
  }
  
  protected boolean isRichiestaVivai(HttpServletRequest request){
    return request.getRequestURI().contains("/vivai/");
  }
  
  protected boolean isRichiestaAutorizzazioni(HttpServletRequest request){
	    return request.getRequestURI().contains("/autorizzazioni/");
	  }

  protected String getTipoRichiesta(HttpServletRequest request) {
	  String tipoRichiesta = "";
	  if(request.getRequestURI().contains("/import")){
		  tipoRichiesta = "IM";
	  }
	  else if(request.getRequestURI().contains("/export")){
		  tipoRichiesta = "EX";
	  }
	  else if(request.getRequestURI().contains("/vivai")){
		  tipoRichiesta = "VI";
	  }
	  else if(request.getRequestURI().contains("/autorizzazioni")){
		  tipoRichiesta = "AU";
	  }
	  return tipoRichiesta;    
  }

  protected String generateToken() {
    StringBuilder token = new StringBuilder();
    Random random = new Random(System.currentTimeMillis());
    StringBuilder CHARS = new StringBuilder("0123456789");
    for (int chr = 0; chr < 26; chr++) {
      CHARS.append((char) ('a' + chr));
      CHARS.append((char) ('A' + chr));
    }
    int len = CHARS.length();

    for (int i = 0; i < 50; ++i) {
      token.append(CHARS.charAt(random.nextInt(len)));
    }

    return token.toString();
  }

  protected String getReferer() {
    String referer = "";
    HttpServletRequest request = getRequest();

    if (request != null) {
      referer = Optional.ofNullable(request.getHeader("Referer")).orElse("");
    }

    System.out.println("\n\nREFERER: " + referer);
    
    System.out.println("\n\n TESTvale: " + referer);   
    
    

    return referer;
  }

  protected void keepSessionAttribute(String name) {
    HttpSession session = null;
    HttpServletRequest request = getRequest();

    if (request != null) {
      session = request.getSession();
    }

    if (session != null && session instanceof CaronteSessionWrapper) {
      ((CaronteSessionWrapper) session).addKeepAttribute(name);
    }
  }

  protected HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
  }

  protected Map<String, String> convertJsonToMap(String json) {
    Map<String, String> result = null;

    try {
      result = new ObjectMapper().readValue(json, new TypeReference<Map<String, String>>() {
      });
    } catch (IOException e) {
      result = new HashMap<>();
    }

    return result;
  }
  
  protected static final Object[][] arraySezioni = {
			{ CaronteConstants.PATH_ASSOCIAZIONE_SEZIONE_IMPORT, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT },
			{ CaronteConstants.PATH_ASSOCIAZIONE_SEZIONE_EXPORT, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT },
			{ CaronteConstants.PATH_ASSOCIAZIONE_SEZIONE_VIVAI, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI },
		  	{ CaronteConstants.PATH_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI },
		  	{ CaronteConstants.PATH_ASSOCIAZIONE_SEZIONE_CONTROLLI, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI }
			};

	protected Long getSezioneRequest(HttpServletRequest request) {
		for (Object[] sezione : arraySezioni) {
			if (request.getRequestURI().contains("" + sezione[0])) {
				return (Long) sezione[1];
			}
		}
		/*
		 * Valore di default
		 */
		return CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT;
	}
	
	protected String getViewNuovaModifica(String path, HttpServletRequest request) {
		String requestURI = request == null ? "" : request.getRequestURI();
		logger.debug("-- requestURI = "+requestURI);
		if (requestURI.contains("/modifica")) {
		  logger.debug("-- path ="+path + "/modifica");	
		  return path + "/modifica";
		} 
		else {
		  logger.debug("-- path ="+path + "/nuova");
		  return path + "/nuova";
		}
	}
	
	
	protected String getViewDettaglioAvanza(String path, HttpServletRequest request) {
		String requestURI = request == null ? "" : request.getRequestURI();
		logger.debug("-- requestURI = "+requestURI);
		if (requestURI.contains("/dettaglio")) {
		  logger.debug("-- path ="+path + "/dettaglio");	
		  return path + "/dettaglio";
		} 
		else {
		  logger.debug("-- path ="+path + "/avanza");
		  return path + "/avanza";
		}
	}
	
	protected void scaricaFile(String contentType, String contentDisposition, byte[] contenuto, HttpServletResponse response) throws IOException {
		logger.debug("BEGIN scaricaFile");
		response.resetBuffer();

		response.setContentType(contentType);
		response.setHeader("Content-Disposition", contentDisposition);

		if (contenuto == null) {
			response.setContentLength(0);
		} 
		else {
			byte[] buffer = new byte[4096];

			response.setContentLength(contenuto.length);

			try (OutputStream out = response.getOutputStream()) {

				for (int i = 0; i < contenuto.length; i += buffer.length) {
					System.arraycopy(contenuto, i, buffer, 0, Math.min(contenuto.length - i, buffer.length));
					out.write(buffer, 0, Math.min(contenuto.length - i, buffer.length));
					out.flush();
				}

			} 
			catch (IOException ioe) {
				logger.error("Errore nello scarico del file", ioe);
				throw ioe;
			}
		}
	}
}
