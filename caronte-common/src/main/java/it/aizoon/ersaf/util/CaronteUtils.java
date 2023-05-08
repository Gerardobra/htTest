package it.aizoon.ersaf.util;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import it.aizoon.ersaf.formatter.BigDecimalFormatter;
import it.aizoon.ersaf.formatter.DoubleEditor;

@Component
@Scope("singleton")
public class CaronteUtils {

	
	protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".util");
	
  /*
   * Questo metodo, per funzionare come atteso, ha bisogno che l'interceptor
   * LocaleHolderInterceptor sia configurato su tutte le richieste
   */
  public static String getMessage(String messageKey, Object... params) {
    String result = null;
    ApplicationContext ac = ApplicationContextProvider.getContext();

    if (ac != null) {
      MessageSource messageSource = ac.getBean(MessageSource.class);
      System.out.println("\n\nMESSAGE KEY: " + messageKey);
      System.out.println("\n\nLOCALE: " + LocaleContextHolder.getLocale());
      result = messageSource.getMessage(messageKey, params, LocaleContextHolder.getLocale());
    }

    return result;
  }

  public static String getMessage(String messageKey) {
    return getMessage(messageKey, (Object) null);
  }

  public static String formatBigDecimal(BigDecimal value) {
    return new BigDecimalFormatter().print(value, LocaleContextHolder.getLocale());
  }
  
  public static BigDecimal parseBigDecimal(String value) {
	  BigDecimal result = null;

	  if (value != null) {
		  value = value.replace(',', '.');

		  if (NumberUtils.isCreatable(value)) {
		    result = NumberUtils.createBigDecimal(value);
		  }
	  }

	  return result;
  }

  public static String formatBigDecimalToFormat(BigDecimal value, String format) {
    return new DecimalFormat(format, new DecimalFormatSymbols(LocaleContextHolder.getLocale()))
        .format(value.doubleValue());
  }

  public static String leftPad(String stringa, int numCaratteri, char caratterePad) {
    if (stringa == null) {
      return stringa;
    }

    final StringBuilder sb = new StringBuilder(stringa);

    while (sb.length() < numCaratteri) {
      sb.insert(0, caratterePad);
    }

    return sb.toString();
  }

  public static String extractFileName(String filePathName) {
    return extractFileName(filePathName, true);
  }

  public static String extractFileName(String filePathName, boolean withExtension) {
    if (filePathName == null) {
      return null;
    }

    int dotPos = filePathName.lastIndexOf('.');
    int slashPos = filePathName.lastIndexOf('\\');
    if (slashPos == -1) {
      slashPos = filePathName.lastIndexOf('/');
    }

    if (!withExtension && dotPos > slashPos) {
      return filePathName.substring(slashPos > 0 ? slashPos + 1 : 0, dotPos);
    }

    return filePathName.substring(slashPos > 0 ? slashPos + 1 : 0);
  }

  public static boolean isPartitaIva(String stringa) {
    boolean result = false;

    int somma = 0;

    if (!isIntegerField(stringa)) {
      return false;
    }

    if (stringa != null && stringa.length() == 11) {
      for (int i = 0; i <= 8; i += 2) {
        somma += stringa.charAt(i) - '0';
      }

      for (int i = 1; i <= 9; i += 2) {
        int temp = (stringa.charAt(i) - '0') * 2;
        if (temp > 9) {
          temp -= 9;
        }
        somma += temp;
      }

      if ((10 - somma % 10) % 10 == stringa.charAt(10) - '0') {
        result = true;
      }
    }
    return result;
  }

  public static boolean isCodiceFiscale(String stringa) {
    char caratt;
    int controllo = -1;
    boolean result = false;
    int resto;
    int sum_pari = 0;
    int sum_dispari = 0;

    char CARATTERI[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
        'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    int VALORE_PARI[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    int VALORE_DISPARI[] = { 1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24,
        23, 1, 0, 5, 7, 9, 13, 15, 17, 19, 21 };

    if (stringa != null && stringa.length() == 16) {
      stringa = stringa.toUpperCase();
      for (int i = 1; i <= 15; i++) {
        int row;
        caratt = stringa.charAt(0);
        stringa = stringa.substring(1);

        for (row = 1; row <= 36; row++) {
          if (CARATTERI[row - 1] == caratt) {
            if (i / 2 * 2 == i) {
              sum_pari += VALORE_PARI[row - 1];
              break;
            } else {
              sum_dispari += VALORE_DISPARI[row - 1];
              break;
            }
          }
        }
        /*
         * Occorre controllare se l'utente ha inserito caratteri non
         * alfanumerici, perché in alcuni casi, con probabilità minima ma non
         * nulla, il metodo potrebbe non restituire il messaggio di errore
         */
        if (row > 36) {
          /*
           * Il carattere non corrisponde a nessun valore salvato nell'array
           * 'carattere', per cui si forza l'uscita dal metodo, per non eseguire
           * altro codice a questo punto inutile
           */
          return false;
        }
      }

      resto = (sum_pari + sum_dispari) % 26;

      caratt = stringa.charAt(0);

      for (int row = 1; row <= 36; row++) {
        if (CARATTERI[row - 1] == caratt) {
          controllo = VALORE_PARI[row - 1];
          break;
        }
      }

      if (controllo == resto) {
        result = true;
      }
    }

    return result;
  }

  public static boolean isIntegerField(String field) {
    return isIntegerField(field, false);
  }

  public static boolean isIntegerField(String field, boolean mayBeNegative) {
    boolean result;

    try {
      result = Long.parseLong(field) < 0 == mayBeNegative;
    } catch (final NumberFormatException nfe) {
      result = false;
    }

    return result;
  }

  public static String checkNull(String field) {
    if (field == null) {
      return "";
    }

    return field;
  }

  public static String capitalize(String stringa) {
    String result = "";

    if (StringUtils.isEmpty(stringa)) {
      return stringa;
    }

    char precedente = ' ';

    stringa = stringa.toLowerCase();

    for (int i = 0; i < stringa.length(); i++) {
      final char carattere = stringa.charAt(i);

      if (Character.isLetter(carattere) && !Character.isLetter(precedente)) {
        result += Character.toUpperCase(carattere);
      } else {
        result += carattere;
      }

      precedente = carattere;
    }

    return result;
  }

  /*
   * Il metodo restituisce il valore boolean dell'istanza wrapper in input, o
   * false se la referenza è null
   */
  public static boolean checkNull(Boolean wrapper) {
    if (wrapper == null) {
      return false;
    }

    return wrapper.booleanValue();
  }

  public static void postMailWithoutAttachments(String from, String[] to, String[] ccn, String subject, String message)
      throws NamingException, AddressException, MessagingException, Exception {

	logger.debug("BEGIN postMailWithoutAttachments");  
    InitialContext ic = new InitialContext();

    Session mailSession = (Session) ic.lookup(CaronteConstants.MAIL_SESSION);
    MimeMessage msg = new MimeMessage(mailSession);

    // Message FROM    
    InternetAddress addressFrom = new InternetAddress(from);
    msg.setFrom(addressFrom);
    logger.debug("-- mittente mail ="+addressFrom);

    // Message TO
    int lengthTo = (to != null) ? to.length : 0;
    Address[] addressesTo = new Address[lengthTo];
    for (int i = 0; i < lengthTo; i++) {
      addressesTo[i] = new InternetAddress(to[i]);
      logger.debug("-- destinatario mail ="+addressesTo[i]);
    }

    // Message CCN
    int lengthCcn = (ccn != null) ? ccn.length : 0;
    Address[] addressesCcn = new Address[lengthCcn];
    for (int i = 0; i < lengthCcn; i++) {
      addressesCcn[i] = new InternetAddress(ccn[i]);
    }

    msg.setRecipients(Message.RecipientType.TO, addressesTo);
    msg.setRecipients(Message.RecipientType.BCC, addressesCcn);

    // Subject
    msg.setSubject(subject);
    logger.debug("-- subject ="+subject);

    // Message
    MimeBodyPart mbp = new MimeBodyPart();
    mbp.setContent(message, "text/html; charset=utf-8");

    // Body
    Multipart mp = new MimeMultipart();
    mp.addBodyPart(mbp);

    msg.setContent(mp, "text/html; charset=utf-8");
    logger.debug("-- INVIO MAIL (Transport.send)");
    Transport.send(msg);
    
    logger.debug("END postMailWithoutAttachments");
  }
  
  public static void postMailWithPDFAttachment(String from, String[] to, String[] ccn, String subject, String message, byte[] bs, String fileName)
	      throws NamingException, AddressException, MessagingException, IOException {

	    InitialContext ic = new InitialContext();

	    Session mailSession = (Session) ic.lookup(CaronteConstants.MAIL_SESSION);
	    MimeMessage msg = new MimeMessage(mailSession);

	    // Message FROM    
	    InternetAddress addressFrom = new InternetAddress(from);
	    msg.setFrom(addressFrom);

	    // Message TO
	    int lengthTo = (to != null) ? to.length : 0;
	    Address[] addressesTo = new Address[lengthTo];
	    for (int i = 0; i < lengthTo; i++) {
	      addressesTo[i] = new InternetAddress(to[i]);
	    }

	    // Message CCN
	    int lengthCcn = (ccn != null) ? ccn.length : 0;
	    Address[] addressesCcn = new Address[lengthCcn];
	    for (int i = 0; i < lengthCcn; i++) {
	      addressesCcn[i] = new InternetAddress(ccn[i]);
	    }

	    msg.setRecipients(Message.RecipientType.TO, addressesTo);
	    msg.setRecipients(Message.RecipientType.BCC, addressesCcn);

	    // Subject
	    msg.setSubject(subject);

	    // Message
      MimeMultipart mimeMultipart = new MimeMultipart();
      MimeBodyPart messageBodyPart = new MimeBodyPart();
      messageBodyPart.setContent(message, "text/html; charset=UTF-8");

      mimeMultipart.addBodyPart(messageBodyPart);
      
      MimeBodyPart attachmentBodyPart = new MimeBodyPart();
      ByteArrayDataSource bds = new ByteArrayDataSource(bs, "application/pdf"); 
      attachmentBodyPart.setDataHandler(new DataHandler(bds)); 
      attachmentBodyPart.setFileName(fileName);
      msg.setContent(mimeMultipart);

      mimeMultipart.addBodyPart(attachmentBodyPart);
      
	    logger.debug("-- INVIO MAIL (Transport.send)");
	    Transport.send(msg);
	  }
  
  public static String formatFourDecimals(BigDecimal value) {
    if (value == null) {
      return "";
    }
    
    DoubleEditor de = new DoubleEditor(LocaleContextHolder.getLocale());
    de.setValue(value);
    return de.getAsText();
  }

 public static boolean isCodiceRuop(String stringa) {
	
//	IT-codice istat regione (2 campi)- n. registrazione (4 campi)
	char carattere[];
	Boolean flag=true;
	String codReg[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" };
	logger.debug("STRINGA= " + stringa + " con lunghezza= " + stringa.length());
	//controllo lunghezza stringa
    if (stringa.length() != 10) {
    	logger.debug("//controllo lunghezza stringa--false");
    	return false;
    }
    stringa = stringa.toUpperCase();
    String[] parole = stringa.split("-");
    //controllo numero substringhe
    if (parole.length != 3){
    	logger.debug("//controllo numero substringhe--false");
    	return false;
    }
    //controllo 1 substringa e lunghezza 2 e 3 substringa
    if (!parole[0].equals("IT") || parole[1].length() != 2 || parole[2].length() != 4){
    	logger.debug("//controllo numero substringhe--false");
    	return false;
    } 
    //controllo 2 substringa
    for(int i=0; i<20; i++){
    	if (parole[1].equals(codReg[i])){
    		logger.debug("//controllo 2 substringa--flag=false");
    		flag=false;
    	}
    }
    if(flag){
    	logger.debug("//return--false");
    	return false;
    }
    //controllo 3 substringa
    carattere=parole[2].toCharArray();
    for(int i=0; i<4; i++){
    	if (carattere[i] < '0' || carattere[i] >'9'){
    		logger.debug("//controllo 3 substringa--flag=true");
    		flag=true;
    	}
    }
    if (flag){
    	logger.debug("//return--false");
    	return false;
    }
    logger.debug("//return finale--true");
    return true;
    
 }
 
 public static Long[] convertStringToLongArray(String[] numbers) {
	 if(numbers != null && numbers.length>0){
		 Long[] result = new Long[numbers.length];
		 for (int i = 0; i < numbers.length; i++)
			 result[i] = Long.parseLong(numbers[i]);
		 return result;
	 }
	 else
		 return null;
 }
 
 public static String convertToUpperCase(String s){
	 if(s != null){
		 s = s.toUpperCase().trim();
	 }
	 return s;
 }
 
}
