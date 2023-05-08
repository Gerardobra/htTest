package it.aizoon.ersaf.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.aizoon.ersaf.form.NuovaComunicazioneForm;

/**
 * @author Ivan Morra
 *
 */
@Component
public class ComunicazioneValidator extends BaseValidator {

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  @Override
  public boolean supports(Class<?> paramClass) {
    return NuovaComunicazioneForm.class.equals(paramClass);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  @Override
  public void validate(Object target, Errors errors) {
    // TODO Auto-generated method stub
  }
  
  public void validateDatiSpecie(NuovaComunicazioneForm form, Errors errors) {
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idSpedizioniere", "error.required");
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idCentroAziendale", "error.required");     
    
    // controlli su Numero piante : obbligatorio e numerico
   /* String[] numeroPiante = form.getNumeroPiante();
    if(numeroPiante != null && numeroPiante.length-1>0){
    	for (int i = 0; i < numeroPiante.length-1; i++) {
			String num = numeroPiante[i];
			validateMandatoryAndInteger(num, "numeroPiante", new Integer(1), new Integer(100000), errors);
		}
    }*/
  }
  
  
  public void validateAutocertificazione(boolean autocert, Errors errors){
	  String autocertificazione = null;
	  if(autocert){
		  autocertificazione = "true";
	  }
	  ValidationUtils.rejectIfEmpty(errors, "autocertificazione", "error.required");
  }

}
