package it.aizoon.ersaf.integration.rest.protocollo;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.aizoon.ersaf.util.CaronteConstants;


public class ProtocolloServiceFactory{
	
  public static final String ENDPOINT_SERVIZIO_PROTOCOLLO = "endpoint.servizio.protocollo";
  private static ProtocolloRESTClient REST_SERVICE_CLIENT = getClient();
  protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".integration");
  private static final String THIS_CLASS = ProtocolloServiceFactory.class.getSimpleName();

  public static ProtocolloRESTClient getRestServiceClient(){
    if (REST_SERVICE_CLIENT == null){
      REST_SERVICE_CLIENT = getClient();      
    }
    return REST_SERVICE_CLIENT;
  }

  

  private static ProtocolloRESTClient getClient(){
	String url = (String) CaronteConstants.getProperties().get(CaronteConstants.ENDPOINT_SERVIZIO_PROTOCOLLO)+ "/";        
	if (logger != null){
		logger.info("[" + THIS_CLASS + ".getClient] Generazione singleton ProtocolloRESTClient con url = " + url);
	}	
    
    if (url == null){
      if (logger != null){	
        logger.error("[" + THIS_CLASS + ".getClient] Errore fatale nel tentativo di reperire il puntamento all'url del servizio REST: Impossibile trovare la proprietà \""+ ENDPOINT_SERVIZIO_PROTOCOLLO + "\" nel sistema!");
      }
      return null;
    }
    else{
    	if (logger != null){
    		logger.info("[" + THIS_CLASS + ".getClient] Generazione singleton ProtocolloRESTClient con url = " + url + " eseguita con successo");
    	}	
	    ProtocolloRESTClient client = new ProtocolloRESTClient(url);         
	    return client;    
    }
    	
  }

  

  public static void setBaseUrl(String baseUrl){
    REST_SERVICE_CLIENT = new ProtocolloRESTClient(baseUrl);            
  }
}
