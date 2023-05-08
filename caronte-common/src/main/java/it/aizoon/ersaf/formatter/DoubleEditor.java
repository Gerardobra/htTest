package it.aizoon.ersaf.formatter;

import java.beans.PropertyEditorSupport;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.util.StringUtils;

/**
 * @author Ivan Morra
 *
 */
public class DoubleEditor extends PropertyEditorSupport {

  private static final String DECIMAL_PATTERN = "###0.####";
  private DecimalFormat decimalFormat;
  private DecimalFormatSymbols symbols;
  
  public DoubleEditor(Locale locale) {
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
    Double value;
    
    if (StringUtils.isEmpty(param)) {
      value = null;
    }else {
      try {
        value = new Double(decimalFormat.parse(param.replace('.', symbols.getDecimalSeparator())).doubleValue());
      }catch (ParseException pe) {
        throw new IllegalArgumentException("Errore nella conversione del campo decimale", pe);
      }
    }
    
    super.setValue(value);
  }
}
