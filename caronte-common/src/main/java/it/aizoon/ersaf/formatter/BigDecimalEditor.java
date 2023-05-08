package it.aizoon.ersaf.formatter;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.util.StringUtils;

/**
 * @author Ivan Morra
 *
 */
public class BigDecimalEditor extends PropertyEditorSupport {

  /*
   * Sono state aggiunte due cifre facoltative ai decimali, per evitare che le
   * quantità delle merci vengano arrotondate dalla formattazione di Spring MVC
   */
  private static final String DECIMAL_PATTERN = "###0.00##";
  private DecimalFormat decimalFormat;
  private DecimalFormatSymbols symbols;

  public BigDecimalEditor(Locale locale) {
    if (locale == null) {
      locale = Locale.ITALY;
    }

    this.symbols = new DecimalFormatSymbols(locale);
    this.decimalFormat = new DecimalFormat(DECIMAL_PATTERN, symbols);
  }

  @Override
  public String getAsText() {
    Object value = getValue();

    if (value == null) {
      return "";
    }

    return decimalFormat.format(value);
  }

  @Override
  public void setAsText(String param) throws IllegalArgumentException {
    BigDecimal value;

    if (StringUtils.isEmpty(param)) {
      value = null;
    } else {
      try {
        value = new BigDecimal(decimalFormat.parse(param.replace('.', symbols.getDecimalSeparator())).doubleValue());
      } catch (ParseException pe) {
        throw new IllegalArgumentException("Errore nella conversione del campo decimale", pe);
      }
    }

    super.setValue(value);
  }

}
