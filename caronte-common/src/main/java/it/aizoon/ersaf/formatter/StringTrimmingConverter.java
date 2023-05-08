package it.aizoon.ersaf.formatter;

import org.springframework.core.convert.converter.Converter;

/**
 * @author Ivan Morra
 *
 */
public class StringTrimmingConverter implements Converter<String, String> {

  @Override
  public String convert(String source) {
    //L'argomento source è garantito non essere null da Spring MVC (da versione 4.1)
    return source.trim();
  }

}
