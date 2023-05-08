package it.aizoon.ersaf.formatter;

import java.beans.PropertyEditorSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

public class LocalTimeEditor extends PropertyEditorSupport {

  private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
  
  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    LocalTime value;
    
    if (StringUtils.isEmpty(text)) {
      value = null;
    }else {
      value = LocalTime.parse(text, timeFormatter);
    }
    
    super.setValue(value);
  }
  
  @Override
  public String getAsText() {
    Object value = getValue();
    
    if (value == null) {
      return "";
    }
    
    return timeFormatter.format((LocalTime)value);
  }
}
