package it.aizoon.ersaf.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

/**
 * @author Ivan Morra
 *
 */
public class DateFormatter implements Formatter<Date> {

  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

  /*{
    System.out.println("\n\nINIZIALIZZAZIONE DATE FORMATTER!!!!");
  }*/
  
  @Override
  public String print(Date value, Locale locale) {
    if (value == null) {
      return "";
    }
    
    return dateFormat.format(value);
  }

  @Override
  public Date parse(String value, Locale locale) throws ParseException {
    System.out.println("\n\nPARSIFICO DATE!!!!");
    return dateFormat.parse(value);
  }

}
