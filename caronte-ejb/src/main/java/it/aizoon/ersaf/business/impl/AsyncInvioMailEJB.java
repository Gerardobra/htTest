package it.aizoon.ersaf.business.impl;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.aizoon.ersaf.business.IAsyncInvioMailEJB;
import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.ITraceIEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarTInvioMailUtente;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

@Stateless(name = "AsyncInvioMail")
@Local(IAsyncInvioMailEJB.class) 
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class AsyncInvioMailEJB implements IAsyncInvioMailEJB{	
	
	protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".business");
	
	// Usata per fare chiamate con transazioni diverse
	@Inject
	private ITraceIEJB traceEJB;	
	
	@Inject
	private IUtenteEJB utenteEJB;
	
	@Inject
	private IDecodificheEJB decodificheEJB;

	@Asynchronous
	public void invioMailMassiva(CarTInvioMailUtente invioMailUtente) throws BusinessException {
		try {	
			// -- TEST ASYNC
			/*logger.debug("****** PRIMA Thread.sleep 60000");
        	Thread.sleep(60000);
        	logger.debug("****** DOPO Thread.sleep 60000");*/
        
			
			// Invio mail (viene generato il token, salvato sul db su car_t_utente e inviata la mail con il token generato)
			logger.debug("-- invio mail con idInvioMailUtente ="+invioMailUtente.getIdInvioMailUtente());
			inviaMailReimpostaPsw(invioMailUtente);
        				
			// Traccio sul car_t_invio_mail_utente che è stata inviata la mail
			invioMailUtente.setEsitoInvio("OK");
			invioMailUtente.setFlMailInviata(true);
			invioMailUtente.setDataMailInviata(new Date());
			logger.debug("-- L'INVIO MAIL E'ANDATO A BUON FINE");
			traceEJB.updateInvioMailUtente(invioMailUtente);						
		} 
		catch (Exception e) {
			// Traccio sul db l'eccezione durante l'invio mail
			invioMailUtente.setEsitoInvio("KO");
			invioMailUtente.setFlMailInviata(true);
			invioMailUtente.setDataMailInviata(new Date());
			if(ExceptionUtils.getStackTrace(e).length()>1999){
			  invioMailUtente.setNote(ExceptionUtils.getStackTrace(e).substring(0, 1999));
			}
			else{
			  invioMailUtente.setNote(ExceptionUtils.getStackTrace(e));
			}
							
			logger.debug("-- L'INVIO MAIL non E'ANDATO A BUON FINE");
			logger.error("-- Exception in invioMailMassiva ="+e.getMessage());
			traceEJB.updateInvioMailUtente(invioMailUtente);
			
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}	
	}
	
	public String inviaMailReimpostaPsw(CarTInvioMailUtente invioMailUtente) throws BusinessException {
		logger.debug("-- Ricerco l'utente al quale inviare la mail su car_t_utente, con idUtente ="+invioMailUtente.getIdUtente());
		UtenteDTO utente = utenteEJB.getUtente(invioMailUtente.getIdUtente());
						
		// scrivo il token nel db con la data di validita SYSDATE
		logger.debug("-- Inserisco il token su car_t_utente con data_token = SYSDATE");
		String token = generateToken();
		utente.setDataToken(new Date());
		utente.setToken(token);
		utenteEJB.updateTokenUtente(utente);
			
		inviaMailCambioPassword(invioMailUtente.getEmail(), token);		

		return "SUCCESS";
	}
	
	
	private void inviaMailCambioPassword(String email, String token) throws BusinessException{
		logger.debug("-- **** email ="+email);
		logger.debug("-- token ="+token);
		
		String title = null;
		String message = null;
		String url = null;

		Properties caronteProperties = CaronteConstants.getProperties();

		if (caronteProperties != null) {
			url = (String) CaronteConstants.getProperties().get(CaronteConstants.APPLICATION_URL_PROPERTY);
		}

		if (url == null) {
			url = CaronteConstants.URL_ACCESSO_APPLICATIVO;
		}

		if (!url.endsWith("/")) {
			url += "/";
		}

	
		url += "registrazione/riassegnaPassword_" + token;
		
		// Recupero da car_d_costante il primo pezzo del testo della mail (TESTO_MAIL_MASS)
		message = "<html><body><p>";
		message += decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_TESTO_MAIL_MASS).getValoreCostante();
				
		message += "<br/> Nel caso in cui sia un nuovo utente, il link di seguito le servir&agrave; per inserire una password e ad abilitarsi"
				 + "<br/> al portale sopra citato, per la denuncia dei vegetali in produzione."
				 + "<br/> Nel caso in cui invece sia un utente già registrato sul portale, il link le servirà per reimpostare la sua password."
				 + "<br/> Accedendo al sistema troverà la nuova sezione 'Vivai'."
				 + "<br><br> Il suo account &egrave; : <strong>:EMAIL</strong>."
			
				+ "<br />Per procedere con l'operazione utilizzare il link:" + "<br /><a href=':URL'>:URL</a>"
				+ "<br><br>NB: Questo link rester&agrave; valido per le prossime 72 ore."
				+ "<br /><br />Questa mail &egrave; stata generata in automatico, si prega di non rispondere a questo indirizzo, grazie."
				+ "<br/></p></body></html>\n";
		
		title = "Abilitazione al Registro Ufficiale degli Operatori Professionali";
			
		logger.debug("-- url ="+url);
		logger.debug("-- utente ="+email);
		message = message.replaceAll(":URL", url).replaceAll(":EMAIL",email);
		
		postMailWithoutAttachments(CaronteConstants.INDIRIZZO_MITTENTE_REGISTRAZIONE_UTENTE,
				new String[] { email }, null, title, message);
	}
	
	
	protected void postMailWithoutAttachments(String from, String[] to, String[] ccn, String subject, String message)
			throws BusinessException {
		try {
		  CaronteUtils.postMailWithoutAttachments(from, to, ccn, subject, message);
		} 
		catch (Exception ex) {
			logger.error("-- Exception in postMailWithoutAttachments ="+ex.getMessage());
			throw new BusinessException(ex.getMessage());
		}
	}
	
	private String generateToken() {
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

	
	
}
