package it.aizoon.ersaf.formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

/**
 * @author Ivan Morra
 *
 */
public class BigDecimalFormatter implements Formatter<BigDecimal> {

  private static final String DECIMAL_PATTERN = "###0.00";
  
  @Override
  public String print(BigDecimal value, Locale locale) {
    if (value == null) {
      return "";
    }
    
    return new DecimalFormat(DECIMAL_PATTERN, new DecimalFormatSymbols(locale)).format(value);
  }

  @Override
  public BigDecimal parse(String value, Locale locale) throws ParseException {
    return new BigDecimal(new DecimalFormat(DECIMAL_PATTERN, new DecimalFormatSymbols(locale)).parse(value).doubleValue());
  }
  

}
