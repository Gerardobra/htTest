package it.aizoon.ersaf.integration.rest.protocollo;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.aizoon.ersaf.exception.InternalException;
import it.aizoon.ersaf.integration.rest.protocollo.dto.DocumentoOutputDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.InputJsonDocumentoDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.InputJsonProtocolloDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.ProtocolloOutputDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.TokenOutputDto;
import it.aizoon.ersaf.util.CaronteConstants;


public class ProtocolloRESTClient{

	private String restServiceUrl    = null;
	protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".integration");
	public static final ObjectMapper JACKSON_MAPPER    = new ObjectMapper().configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	private static final String THIS_CLASS        = ProtocolloRESTClient.class.getSimpleName();
	private int timeout = 4000;
	private int connectionTimeout = 4000;

	public ProtocolloRESTClient(String restServiceUrl){
		this.restServiceUrl = restServiceUrl;
	}

	@SuppressWarnings("unchecked")
	protected <T> T callServiceUsingGetMethod(String serviceName, Class<T> clazz, Param... params) throws InternalException{
		logger.info("BEGIN callServiceUsingGetMethod");
		String inputStream=null;
		try
		{
			String finalUrl = createUrl(serviceName, params);
			boolean debugEnabled = logger != null && logger.isDebugEnabled();
			if (debugEnabled)
			{
				logger.debug("[" + THIS_CLASS + ".callServiceUsingGetMethod] calling rest service at url: " + finalUrl);
			}
			GetMethod method = new GetMethod(finalUrl);
			org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
			//setTimeout(client);
			int status = client.executeMethod(method);
			if (debugEnabled)
			{
				logger.debug("[" + THIS_CLASS + ".callServiceUsingGetMethod] return code " + status);
			}
			if (status == 200)
			{
				inputStream = method.getResponseBodyAsString();
				if (inputStream==null || inputStream.length()==0)
				{
					return null;
				}
				return JACKSON_MAPPER.readValue(inputStream, clazz);
			}
			else
			{
				inputStream = method.getResponseBodyAsString();
				Map<String, String> map = JACKSON_MAPPER.readValue(inputStream, HashMap.class);
				String exceptionType = map.get("exceptionType");
				Class<Exception> exceptionClass = null;
				if (exceptionType != null)
				{
					try
					{
						exceptionClass = (Class<Exception>) Class.forName(exceptionType);
					}
					catch (ClassNotFoundException e)
					{
						exceptionClass = Exception.class; // Non ho la classe nel mio classpath e quindi la leggo come un'eccezione
						// generica
					}
				}
				else
				{
					exceptionClass = Exception.class; // Non conosco il tipo e quindi la leggo come un'eccezione generica
				}
				throw (Exception) JACKSON_MAPPER.readValue(inputStream, exceptionClass);
			}
		}
		catch (InternalException e){
			logger.error("-- InternalException in callServiceUsingGetMethod ="+e.getMessage());
			throw e;
		}
		catch (JsonParseException e){
			logger.error("-- JsonParseException in callServiceUsingGetMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (JsonMappingException e){
			logger.error("-- JsonMappingException in callServiceUsingGetMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (MalformedURLException e){
			logger.error("-- MalformedURLException in callServiceUsingGetMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (IOException e){
			logger.error("-- IOException in callServiceUsingGetMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (Exception e){
			logger.error("-- IOException in callServiceUsingGetMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		finally{
			logger.info("END callServiceUsingGetMethod");
		}
	}

	/*@SuppressWarnings("deprecation")
	private void setTimeout(HttpClient httpClient)
	{
		httpClient.setConnectionTimeout(connectionTimeout);
		httpClient.setTimeout(timeout);
	}*/

	@SuppressWarnings("unchecked")
	protected <T> T callServiceUsingPostMethod(String serviceName, Class<T> clazz, String authorization, Param... params) throws InternalException{
		logger.info("BEGIN callServiceUsingPostMethod");  
		String inputStream = null;
		try{
			String finalUrl = restServiceUrl + serviceName;

			logger.debug("[" + THIS_CLASS + ".callServiceUsingPostMethod] calling rest service at url: " + finalUrl);      
			//PostMethod method = new PostMethod(finalUrl);	
			
			CloseableHttpClient httpclient = getHttpClientCustomSSL();// metodo per non verificare i certificati SSL
					/* HttpClients.custom()
		            .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
		            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
		            .build(); */
			HttpPost httpPost = new HttpPost(finalUrl);//("https://hookb.in/9XXy2xqq8ZiW1OXX1mPn");//(finalUrl);
			
			httpPost.addHeader("Authorization","Basic "+ authorization);
			
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();  

			
			//setTimeout(client);
			if (params != null)
			{
				for (Param param : params)
				{
					if(param.value != null)
					{
						postParameters.addAll(param.getHttpParam());
					}
				}
			}
			
			httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			int status = statusLine.getStatusCode();			
			    
			logger.debug("[" + THIS_CLASS + ".callServiceUsingPostMethod] return code " + status);

			if (status == 200)
			{
				String content = EntityUtils.toString(response.getEntity());
				if(content==null || content.length()==0) {
					return null;
				}
			    return JACKSON_MAPPER.readValue(content, clazz);
			}
			else
			{
				String content = EntityUtils.toString(response.getEntity());
				Map<String, String> map = JACKSON_MAPPER.readValue(content, HashMap.class);
				String error_description = map.get("error_description");
				logger.error("-- error_description tornato in callServiceUsingPostMethod = "+error_description);
				String error = map.get("error");
				logger.error("-- error tornato in callServiceUsingPostMethod = "+error);				
				Class<Exception> exceptionClass = Exception.class;
				
				throw (Exception) JACKSON_MAPPER.readValue(inputStream, exceptionClass);
			}
		}
		catch (InternalException e){
			logger.error("-- InternalException in callServiceUsingPostMethod ="+e.getMessage());
			throw e;
		}
		catch (JsonParseException e){
			logger.error("-- JsonParseException in callServiceUsingPostMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (JsonMappingException e){
			logger.error("-- JsonMappingException in callServiceUsingPostMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (MalformedURLException e){
			logger.error("-- MalformedURLException in callServiceUsingPostMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (IOException e){
			logger.error("-- IOException in callServiceUsingPostMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (Exception e){
			logger.error("-- IOException in callServiceUsingPostMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		finally{
			logger.info("END callServiceUsingPostMethod");
		}
	}
	
	
	@SuppressWarnings("unchecked")
	protected <T> T callServiceUsingPostMethodMultipartFormData(String serviceName, Class<T> clazz, String token, InputJsonDocumentoDto inputJsonDoc, byte [] fileDaProtocollare, String nomeFile) throws InternalException{
		logger.info("BEGIN callServiceUsingPostMethodMultipartFormData");  
		//String inputStream = null;
		try{
			String finalUrl = restServiceUrl + serviceName;

			logger.debug("[" + THIS_CLASS + ".callServiceUsingPostMethodMultipartFormData] calling rest service at url: " + finalUrl);      
		
			/*RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(timeout).
					setConnectTimeout(connectionTimeout).setConnectionRequestTimeout(connectionTimeout).build();*/
					
			//CloseableHttpClient httpclient = HttpClients.createDefault();//custom().setDefaultRequestConfig(defaultRequestConfig).build();
			CloseableHttpClient httpclient = getHttpClientCustomSSL();
					/*HttpClients.custom()
					.setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
					.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();*/
			
			HttpPost httpPost = new HttpPost(finalUrl);//("https://hookb.in/9XXy2xqq8ZiW1OXX1mPn");//(finalUrl);
			httpPost.addHeader("Authorization","Bearer "+ token);
			
			// converte un oggetto in una stringa in formato json
			String inputJson = JACKSON_MAPPER.writeValueAsString(inputJsonDoc);

			MultipartEntityBuilder entity = MultipartEntityBuilder.create();	
			entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			entity.addBinaryBody("input.json", inputJson.getBytes(),ContentType.APPLICATION_JSON, "input.json");
			entity.addBinaryBody(nomeFile, fileDaProtocollare, ContentType.APPLICATION_OCTET_STREAM, nomeFile);
			
			HttpEntity multipart = entity.build();
			httpPost.setEntity(multipart);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			int status = statusLine.getStatusCode();
			logger.debug("[" + THIS_CLASS + ".callServiceUsingPostMethodMultipartFormData] return code " + status);

			if (status == 201 || status == 401) {
				String content = EntityUtils.toString(response.getEntity());
				if(content==null || content.length()==0) {
					return null;
				}
			    return JACKSON_MAPPER.readValue(content, clazz);
						 			    							
			} else {
				String content = EntityUtils.toString(response.getEntity());
				Map<String, String> map = JACKSON_MAPPER.readValue(content, HashMap.class);
				String error_description = map.get("error_description");
				logger.error("-- error_description tornato in callServiceUsingPostMethodMultipartFormData = "+error_description);
				String error = map.get("error");
				logger.error("-- error tornato in callServiceUsingPostMethodMultipartFormData = "+error);				
				Class<Exception> exceptionClass = Exception.class;
				throw (Exception) JACKSON_MAPPER.readValue(content, exceptionClass);								
			}
		}
		catch (InternalException e){
			logger.error("-- InternalException in callServiceUsingPostMethodMultipartFormData ="+e.getMessage());
			throw e;
		}
		catch (JsonParseException e){
			logger.error("-- JsonParseException in callServiceUsingPostMethodMultipartFormData ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (JsonMappingException e){
			logger.error("-- JsonMappingException in callServiceUsingPostMethodMultipartFormData ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (MalformedURLException e){
			logger.error("-- MalformedURLException in callServiceUsingPostMethodMultipartFormData ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (IOException e){
			logger.error("-- IOException in callServiceUsingPostMethodMultipartFormData ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (Exception e){
			logger.error("-- IOException in callServiceUsingPostMethodMultipartFormData ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		finally{
			logger.info("END callServiceUsingPostMethodMultipartFormData");
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T callServiceUsingPutMethod(String serviceName, Class<T> clazz, String token, InputJsonProtocolloDto inputJsonProt) throws InternalException{
		logger.info("BEGIN callServiceUsingPutMethod");  
		//String inputStream = null;
		try{
			String finalUrl = restServiceUrl + serviceName;

			logger.debug("[" + THIS_CLASS + ".callServiceUsingPutMethod] calling rest service at url: " + finalUrl);      
		
		  
			CloseableHttpClient httpclient = getHttpClientCustomSSL();
			HttpPut httpPut = new HttpPut(finalUrl);		//	("https://hookb.in/9XXy2xqq8ZiW1OXX1mPn");
			httpPut.addHeader("Authorization","Bearer "+ token);
			httpPut.addHeader("Accept", "application/json"); 			 
			httpPut.addHeader("Content-type", "application/json");
			
			// capire come e quando generare un json di questo tipo
			/*String inputJsonProtocollo = "{\"indirizzoMittente\": \"indirizzoMittente\",\"cittaMittente\": \"cittaMittente\",\"comuneMittente\": \"comuneMittente\","
					+ "\"provinciaMittente\": \"provinciaMittente\",\"capMittente\": \"capMittente\",\"regioneMittente\": \"regioneMittente\","
					+ "\"descrizioneMittente\": \"descrizioneMittente\",\"email\": \"email@email.it\",\"fax\": \"fax\",\"telefono\": \"telefono\","
					+ "\"partitaIva\": \"partitaIva\",\"codFisc\": \"cficfh76t54r567u\",\"codiceDestinatario\": \"SYSTEM_CARONTE\","
					+ "\"oggettoProtocollo\": \"oggettoProtocollo\",\"tipoDocumento\": \"domanda\",\"tipoAllegati\": \"Allegati di test\","
					+ "\"mezzoSpedizione\": \"Raccomandata\",\"dataArrivo\": \"2021-07-09\",\"dataDocumento\": \"2021-07-09\",\"numeroAllegati\": 0}";
			*/
			// converte un oggetto in una stringa in formato json
			String inputJsonProtocollo = JACKSON_MAPPER.writeValueAsString(inputJsonProt);

			StringEntity stringEntity = new StringEntity(inputJsonProtocollo);
			httpPut.setEntity(stringEntity);
			CloseableHttpResponse response = httpclient.execute(httpPut);			
			StatusLine statusLine = response.getStatusLine();
			int status = statusLine.getStatusCode();

			logger.debug("[" + THIS_CLASS + ".callServiceUsingPutMethod] return code " + status);
			String content = null;
			if (status == 201) {
				content = EntityUtils.toString(response.getEntity());
				if(content==null || content.length()==0) {
					logger.debug("-- content è null");
					return null;
				}				
				return JACKSON_MAPPER.readValue(content, clazz);	
				//return (T)content;
			} 
			else if (status == 401) {
				// caso in cui non è autorizzato - unauthorized
				logger.debug("-- caso in cui non è autorizzato - unauthorized");
				content = CaronteConstants.PROTOCOLLO_MESSAGE_ERROR_INVALID_CREDENTIALS;
				//return (T)content;
				return JACKSON_MAPPER.readValue(content, clazz);
			} 
			/*
			 * The server cannot or will not process the request due to something that is perceived to 
				be a client error (e.g., malformed request syntax, invalid JSON format).
			 */
			else if (status == 400) {				
				logger.debug("-- Il server non può elaborare la richiesta");
				content = CaronteConstants.PROTOCOLLO_MESSAGE_ERROR_NOT_PROCESS;
				//return (T)content;
				return JACKSON_MAPPER.readValue(content, clazz);
			} 
			else {		
				logger.debug("-- Prima di EntityUtils.toString");
				content = EntityUtils.toString(response.getEntity());
				logger.debug("-- *** content ="+content);
				logger.debug("-- Prima di JACKSON_MAPPER.readValue");
				Map<String, String> map = JACKSON_MAPPER.readValue(content, HashMap.class);
				String error_description = map.get("error_description");
				logger.error("-- error_description tornato in callServiceUsingPutMethod = "+error_description);
				String error = map.get("error");
				logger.error("-- error tornato in callServiceUsingPutMethod = "+error);				
				Class<Exception> exceptionClass = Exception.class;
				throw (Exception) JACKSON_MAPPER.readValue(content, exceptionClass);								
			}
		}
		catch (InternalException e){
			logger.error("-- InternalException in callServiceUsingPutMethod ="+e.getMessage());
			throw e;
		}
		catch (JsonParseException e){
			logger.error("-- JsonParseException in callServiceUsingPutMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (JsonMappingException e){
			logger.error("-- JsonMappingException in callServiceUsingPutMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (MalformedURLException e){
			logger.error("-- MalformedURLException in callServiceUsingPutMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (IOException e){
			logger.error("-- IOException in callServiceUsingPutMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		catch (Exception e){
			logger.error("-- IOException in callServiceUsingPutMethod ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
		finally{
			logger.info("END callServiceUsingPutMethod");
		}
	}

	protected String createUrl(String url, Param[] params) throws UnsupportedEncodingException{
		logger.info("BEGIN createUrl");
		if (params == null || params.length == 0)
		{
			return url;
		}
		logger.info("END createUrl");
		return restServiceUrl + url + "?" + getHttpParameters(params);
	}

	protected String stringOf(Object value){
		return value == null ? "" : value.toString();
	}
	
	
	/*
	 *  *** Servizio per ottenere il token d'accesso (POST) ***
	 *  Pasare in input le credenziali dell'applicazione e gli scope richiesti
	 */
	public TokenOutputDto getToken() throws InternalException{
		String authorization = (String) CaronteConstants.getProperties().get(CaronteConstants.AUTHORIZATION_SERVIZIO_TOKEN);
		return callServiceUsingPostMethod("oauth2/token", TokenOutputDto.class, authorization, new Param("grant_type", "client_credentials"),
																		new Param("scope", "apidoc"));
	}
	
	
	/*
	 *  *** Servizio per creare il Documento da protocollare (POST) ***
	 * Il servizio per la creazione di un DocumentoFile prende un input di tipo “multipart", un input 
	 * cioè composto da più parti. In questo caso il multipart è composto da due parti:  una parte dati 
	 * rappresentata da una struttura dati descritta da DocumentoArchiviatoModel e una parte       
	 * composta dal file da associare al documento da archiviare.
	 */
	// Note : dovrà prendere in input il pdf da protocollare (stampa salvata sul db in fase di inoltra Domanda)
	public DocumentoOutputDto creaDocumentoDaProtocollare(String token, InputJsonDocumentoDto inputJsonDoc, byte [] fileDaProtocollare, String nomeFile) throws InternalException{		
		return callServiceUsingPostMethodMultipartFormData("c/servizi.rl/ApiDoc/v1.0.0/web/apirestful/edma/documentoarchiviato/documento", DocumentoOutputDto.class, token, inputJsonDoc, fileDaProtocollare, nomeFile);
	}
	
	
	/*
	 * Il servizio fa la protocollazione e ci viene inviato l'esito. 
	 * Attualmente il content-type e application/json ma non formattato correttamente.
	 * Output tornato : Json mappato in ProtocolloOutputDto 
	 */
	public ProtocolloOutputDto protocollaDocumentoByIdDocumento(String token, String idDocumento, InputJsonProtocolloDto inputJsonProt) throws InternalException{			
		return callServiceUsingPutMethod("c/servizi.rl/ApiDoc/v1.0.0/web/apirestful/edma/documentoarchiviato/documento/protocollo/"+idDocumento,  ProtocolloOutputDto.class, token, inputJsonProt);
	}	
	


	protected String getHttpParameters(Param[] params) throws UnsupportedEncodingException
	{
		StringBuilder sb = new StringBuilder();
		if (params != null)
		{
			for (Param param : params)
			{
				if(param.value != null)
				{
					if (sb.length() > 0)
					{
						sb.append("&");
					}
					sb.append(param.toHttpParam());
				}
			}
		}
		return sb.toString();
	}

	protected class Param
	{
		String name;
		Object value;

		public Param(String name, Object value)
		{
			this.name = name;
			this.value = value;
		}

		public String toHttpParam() throws UnsupportedEncodingException
		{
			StringBuilder sb = new StringBuilder();
			if (value.getClass().isArray())
			{
				int len = java.lang.reflect.Array.getLength(value);
				boolean amp = false;
				for (int i = 0; i < len; ++i)
				{
					Object item = java.lang.reflect.Array.get(value, i);
					if (!amp)
					{
						amp = true;
					}
					else
					{
						sb.append("&");
					}
					sb.append(name).append("=");
					sb.append(java.net.URLEncoder.encode(stringOf(item), "ISO-8859-1"));
				}
			}
			else
			{
				sb.append(name).append("=");
				sb.append(java.net.URLEncoder.encode(stringOf(value), "ISO-8859-1"));
			}
			return sb.toString();
		}

		public void setHttpParam(PostMethod method) throws UnsupportedEncodingException
		{
			if (value.getClass().isArray())
			{
				int len = java.lang.reflect.Array.getLength(value);
				for (int i = 0; i < len; ++i)
				{
					Object item = java.lang.reflect.Array.get(value, i);
					method.addParameter(name, java.net.URLEncoder.encode(stringOf(item), "ISO-8859-1"));
				}
			}
			else
			{
				method.setParameter(name, java.net.URLEncoder.encode(stringOf(value), "ISO-8859-1"));
			}
		}
		
		public ArrayList<NameValuePair> getHttpParam() throws UnsupportedEncodingException
		{
			
		    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		    
			if (value.getClass().isArray())
			{
				int len = java.lang.reflect.Array.getLength(value);
				for (int i = 0; i < len; ++i)
				{
					Object item = java.lang.reflect.Array.get(value, i);					
					postParameters.add(new BasicNameValuePair(name, java.net.URLEncoder.encode(stringOf(item), "ISO-8859-1")));
					
				}
			}
			else
			{
				postParameters.add(new BasicNameValuePair(name, java.net.URLEncoder.encode(stringOf(value), "ISO-8859-1")));
			}
			return postParameters;
		}
	}

	

	public int getTimeout()
	{
		return timeout;
	}

	public void setTimeout(int timeout)
	{
		this.timeout = timeout;
	}

	public int getConnectionTimeout()
	{
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout)
	{
		this.connectionTimeout = connectionTimeout;
	}

	public String getRestServiceUrl()
	{
		return restServiceUrl;
	}
	
	public CloseableHttpClient getHttpClientCustomSSL() throws InternalException {
		try {
			return  HttpClients.custom()
					.setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
					.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
					
		} catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
			logger.error("-- Eccezione in getHttpClientCustomSSL ="+e.getMessage());	
			throw new InternalException(e.getMessage(), e);
		}
	}
	


}
