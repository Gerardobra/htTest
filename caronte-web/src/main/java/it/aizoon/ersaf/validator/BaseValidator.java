package it.aizoon.ersaf.validator;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Ivan Morra
 */
public abstract class BaseValidator implements Validator {

  protected void validateMaxLength(String value, int maxLength, String field, Errors errors) {
    if (value != null && value.length() > maxLength) {
      errors.rejectValue(field, "error.maxLength", new Object[]{""+maxLength}, "Il campo non può superare i "+maxLength+" caratteri");
    }
  }
  
  protected void validateMandatoryAndMaxLength(String value, String field, int maxLength, Errors errors) {
    if (StringUtils.isEmpty(value)) {
      errors.rejectValue(field, "error.required");
    }else {
      validateMaxLength(value, maxLength, field, errors);
    }
  }
  
  protected void validateInteger(String value, Integer minValue, Integer maxValue, String field, Errors errors) {
    if (value != null) {
      try {
        int intValue = Integer.parseInt(value);
        
        if (minValue != null && minValue > intValue) {
          errors.rejectValue(field, "error.minValue", new Object[]{""+minValue}, "Il campo deve essere uguale o maggiore di "+minValue);
        }
        
        if (!errors.hasFieldErrors(field) && maxValue != null && maxValue < intValue) {
          errors.rejectValue(field, "error.maxValue", new Object[]{""+maxValue}, "Il campo deve essere minore o uguale a "+maxValue);
        }
      }catch (NumberFormatException nfe) {
        errors.rejectValue(field, "error.integerValue", "Il campo deve contenere un valore numerico intero");
      }
    }
  }
  
  protected void validateMandatoryAndInteger(String value, String field, Integer minValue, Integer maxValue, Errors errors) {
    if (StringUtils.isEmpty(value)) {
      errors.rejectValue(field, "error.required");
    }else {
      validateInteger(value, minValue, maxValue, field, errors);
    }
  }
  
  protected void validateNumeric(String value, String field, Errors errors) {
	  if (!NumberUtils.isNumber(value)) {
		  errors.rejectValue(field, "error.numericValue", "Il campo deve avere un valore numerico");  
	  }
	  
  }
  
  protected void validateMandatoryAndDecimal(String value, String field, Integer numIntegers, Integer numDecimals, 
	      Errors errors) {
	    if (StringUtils.isEmpty(value)) {
	      errors.rejectValue(field, "error.required");
	    } else {
	      validateDecimal(value, numIntegers, numDecimals, field, errors);
	    }
  }
  
  public boolean validateDecimal(String value, Integer numIntegers, Integer numDecimals, String field, Errors errors) {
	  boolean controlloOk=true;
	    if (value != null) {
	      value = value.replace(',', '.');
	      
	      if (validateNum(value, field, errors)) {
	        if (value.indexOf('.') > numIntegers) {
	          errors.rejectValue(field, "error.integerPlaces", new Object[] { "" + numIntegers },
	              "Il numero deve contenere al massimo " + numIntegers + " cifre intere");
	          controlloOk = false;
	        }else if (value.indexOf('.') >= 0 && value.length() - 1 - value.indexOf('.') > numDecimals) {
	          errors.rejectValue(field, "error.decimalPlaces", new Object[] { "" + numDecimals },
	              "Il numero deve contenere al massimo " + numDecimals + " cifre decimali");
	          controlloOk = false;
	        }
	      }
	      else{
	    	  controlloOk = false;
	      }
	    }
	    return controlloOk;
  }
  protected boolean validateNum(String value, String field, Errors errors) {
		if (!NumberUtils.isNumber(value)) {
			errors.rejectValue(field, "error.numericValue", "Il campo deve avere un valore numerico");
			return false;
		}
		
		return true;
  }
}
