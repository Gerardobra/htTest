package it.aizoon.ersaf.validator;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarTUtente;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.IspettoreForm;
import it.aizoon.ersaf.form.SpedizioniereForm;
import it.aizoon.ersaf.form.UtenteForm;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

/**
 * @author Nicolò Mandrile
 *
 */
@Component
public class UtenteValidator extends BaseValidator {
	
  protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".validator");

  @Autowired
  private IUtenteEJB utenteEJB = null;

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  @Override
  public boolean supports(Class<?> paramClass) {
    return UtenteForm.class.equals(paramClass);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#validate(java.lang.Object,
   * org.springframework.validation.Errors)
   */
  @Override
  public void validate(Object obj, Errors errors) {

  }

  public void validateDatiUtente(UtenteForm form, Errors errors) throws BusinessException {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idRuolo", "error.required");

    // almeno uno tra i due flag deve essere selezionato
    /*
     * if(!form.isSezioneExport() && !form.isSezioneImport()) {
     * errors.rejectValue("sezioneImport", "error.importExport");
     * //errors.rejectValue("sezioneExport", "error.importExport"); }
     */

    /*
     * L'utente non può essere abilitato e rifiutato contemporaneamente, se
     * rifiutato deve essere indicato il motivo
     */
    if (form.isAbilitato() && form.isRifiutato()) {
      errors.rejectValue("rifiutato", "error.abilitazioneUtente");
    }

    if (form.isRifiutato() && StringUtils.isBlank(form.getMotivoRifiuto())) {
      errors.rejectValue("motivoRifiuto", "error.required");
    }

    if (form.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE
        && form.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO)
    	
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denomSpedizioniere", "error.required");
    if( form.getDenomSpedizioniere()!= null && !utenteEJB.checkIfDenomSpedizioniereExist(form.getDenomSpedizioniere())){
    	 errors.rejectValue("denomSpedizioniere", "error.operatEnteInesistente");
    }

    validateMandatoryAndMaxLength(form.getNome(), "nome", 50, errors);
    validateMandatoryAndMaxLength(form.getCognome(), "cognome", 50, errors);
    validateMandatoryAndMaxLength(form.getCodiceFiscale(), "codiceFiscale", 16, errors);
    validateMandatoryAndMaxLength(form.getEmail(), "email", 100, errors);

    if (StringUtils.isNotEmpty(form.getCodiceFiscale())) {
      if (!CaronteUtils.isCodiceFiscale(form.getCodiceFiscale())) {
        errors.rejectValue("codiceFiscale", "error.cf.invalid");
      } else if (!utenteEJB.isCFUnivocal(form)) {
        errors.rejectValue("codiceFiscale", "error.univocalCF");
      }
    }

    if (form.getEmail() != null && !utenteEJB.isMailUnivocal(form)) {
      errors.rejectValue("email", "error.univocalMail");
    }

    validateMaxLength(form.getNote(), 1000, "note", errors);
  }

  public void validateDatiIspettore(IspettoreForm form, Errors errors, IDecodificheEJB decodificheEJB,
      HttpServletRequest request, boolean isUpdate) throws BusinessException {

    // VALIDATE DATI UTENTE
    validateMandatoryAndMaxLength(form.getNome(), "nome", 50, errors);
    validateMandatoryAndMaxLength(form.getCognome(), "cognome", 50, errors);
    validateMandatoryAndMaxLength(form.getCodiceFiscale(), "codiceFiscale", 16, errors);
    validateMandatoryAndMaxLength(form.getEmail(), "email", 100, errors);
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idRuolo", "error.required");
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denomSpedizioniere", "error.required");
    if( form.getDenomSpedizioniere()!= null && !utenteEJB.checkIfDenomSpedizioniereExist(form.getDenomSpedizioniere())){
    	 errors.rejectValue("denomSpedizioniere", "error.operatEnteInesistente");
    }

    
    // almeno uno tra i tre flag deve essere selezionato
    if (!form.isSezioneExport() && !form.isSezioneImport() && !form.isSezioneVivai() && !form.isSezioneAutorizzazioni() && !form.isSezioneControlli()) {
      errors.rejectValue("sezioneImport", "error.importExport");
      // errors.rejectValue("sezioneExport", "error.importExport");
    }

    if (StringUtils.isNotEmpty(form.getCodiceFiscale())) {
      if (!CaronteUtils.isCodiceFiscale(form.getCodiceFiscale())) {
        errors.rejectValue("codiceFiscale", "error.cf.invalid");
      } else if (!utenteEJB.isCFUnivocal(form)) {
        errors.rejectValue("codiceFiscale", "error.univocalCF");
      }
    }

    if (isUpdate && form.getEmail() != null && !utenteEJB.isMailUnivocal(form)) {
      errors.rejectValue("email", "error.univocalMail");
    }

    validateMaxLength(form.getNote(), 1000, "note", errors);
    // validateDatiUtente(form, errors);

    validateMandatoryAndMaxLength(form.getNumeroTessera(), "numeroTessera", 10, errors);
    validateMandatoryAndMaxLength(form.getSesso(), "sesso", 1, errors);

    validateMaxLength(form.getTitoloStudio(), 100, "titoloStudio", errors);
    validateMaxLength(form.getCapUfficio(), 5, "capUfficio", errors);
    validateMaxLength(form.getIndirizzoUfficio(), 200, "indirizzoUfficio", errors);
    validateMaxLength(form.getDenomComuneUfficio(), 100, "denomComuneUfficio", errors);
    validateMaxLength(form.getDenomComuneNascita(), 100, "denomComuneNascita", errors);
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "error.required");

    String idNazioneNascitaStr = request.getParameter("idNazioneNascita");

    if (idNazioneNascitaStr != null && !"".equals(idNazioneNascitaStr)) {
      if (Long.parseLong(idNazioneNascitaStr) != 1l) {// NOT ITA
        validateMandatoryAndMaxLength(form.getCittaNascita(), "cittaNascita", 50, errors);
      } else {// ITA
        String idComuneNascitaStr = request.getParameter("idComuneNascita");

        if (idComuneNascitaStr != null && !"".equals(idComuneNascitaStr)) {
          if (idComuneNascitaStr != null && !"".equals(idComuneNascitaStr)) {
            form.setIdComuneNascita(Long.parseLong(idComuneNascitaStr));
          } else {
            form.setIdComuneNascita(null);
            // non c'è l'id ma l'utente ha inserito una denominazione a caso
            // senza
            // selezionarla tra le opzioni dell'autocomplete
            if (form.getDenomComuneNascita() != null && !"".equals(form.getDenomComuneNascita()))
              errors.rejectValue("denomComuneNascita", "error.autocompleteOptional");
          }
        }
      }
    }

    String idComuneUfficioStr = request.getParameter("idComuneUfficio");

    if (idComuneUfficioStr != null && !"".equals(idComuneUfficioStr)) {
      form.setIdComuneUfficio(Long.parseLong(idComuneUfficioStr));
    } else {
      form.setIdComuneUfficio(null);

      // non c'è l'id ma l'utente ha inserito una denominazione a caso senza
      // selezionarla tra le opzioni dell'autocomplete
      if (form.getDenomComuneUfficio() != null && !"".equals(form.getDenomComuneUfficio())) {
        errors.rejectValue("denomComuneUfficio", "error.autocompleteOptional");
      }
    }
  }

  public void validateDatiSpedizioniere(SpedizioniereForm form, Errors errors) throws BusinessException {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idTipoSpedizioniere", "error.required");
    // ValidationUtils.rejectIfEmptyOrWhitespace(errors,
    // "idAssociazioneSezione", "error.required");

    // almeno uno tra i tre flag deve essere selezionato
    if (!form.isSezioneExport() && !form.isSezioneImport() && !form.isSezioneVivai() && !form.isSezioneAutorizzazioni() && !form.isSezioneControlli()) {
      errors.rejectValue("sezioneImport", "error.importExport");
      // errors.rejectValue("sezioneExport", "error.importExport");
    }
   
    if (form.getCuaa() != null) {
      SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniereByCuua(form.getCuaa());
      if (spedizioniere != null) {
        if (form.getIdSpedizioniere() == null
            || !spedizioniere.getIdSpedizioniere().equals(form.getIdSpedizioniere())) {
          errors.rejectValue("cuaa", "error.univocalCuaa");
        }
      }
    }

    if (form.getIdTipoSpedizioniere() != null && form.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO
        && form.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE) {
      validateMandatoryAndMaxLength(form.getDenomSpedizioniere(), "denomSpedizioniere", 200, errors);
      if (form.getDenomSpedizioniere() != null)
        if (!utenteEJB.isDenomUnivocal(form))
          errors.rejectValue("denomSpedizioniere", "error.univocalDenom");

      validateMandatoryAndMaxLength(form.getPec(), "pec", 100, errors);
      
    } else {
      validateMandatoryAndMaxLength(form.getNomeDitta(), "nomeDitta", 50, errors);
      validateMandatoryAndMaxLength(form.getCognomeDitta(), "cogomeDitta", 50, errors);

      if (form.getIdTipoSpedizioniere() == CaronteConstants.DITTA_INDIVIDUALE)
        validateMandatoryAndMaxLength(form.getPec(), "pec", 100, errors);
      else
        validateMaxLength(form.getPec(), 100, "pec", errors);
    }
    
    logger.debug("1_form.getnumerocellulare()= " + form.getNumeroCellulare());
    validateMandatoryAndMaxLength(form.getNumeroCellulare(), "numeroCellulare", 20, errors);

    validateMandatoryAndMaxLength(form.getCuaa(), "cuaa", 16, errors);
    
    if (form.getCuaa() != null) {
      if (form.getCuaa().length() != 11 && form.getCuaa().length() != 16) {
        errors.rejectValue("cuaa", "error.cuaaLength");
      }
    }
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idProvinciaSedeSociale", "error.required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idComuneSedeSociale", "error.required");
    validateMandatoryAndMaxLength(form.getIndirizzoSedeSociale(), "indirizzoSedeSociale", 200, errors);
    validateMandatoryAndMaxLength(form.getCapSedeSociale(), "capSedeSociale", 5, errors);
    /*validateMandatoryAndMaxLength(form.getCodiceRUOP(), "codiceRUOP", 20, errors);*/
 // la partita IVA deve essere univoca	
 	 if (form.getPartitaIVA() != null) {
 	   SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniereByPartitaIva(form.getPartitaIVA());
 	   if (spedizioniere != null) {
 	     if (form.getIdSpedizioniere() == null || !spedizioniere.getIdSpedizioniere().equals(form.getIdSpedizioniere())) {
 	   	   logger.debug("-- la partita IVA deve essere univoca");
 	       errors.rejectValue("partitaIVA", "error.univocalCuaa");
 		 }
 	   }
 	   logger.debug("controllo isPartitaIva: "+ CaronteUtils.isPartitaIva(form.getPartitaIVA()));
     	if (form.getPartitaIVA().length() != 11 || !CaronteUtils.isPartitaIva(form.getPartitaIVA())) {
     		errors.rejectValue("partitaIVA", "error.piva.invalid");
     	}	   
 	 }	  

    validateMandatoryAndMaxLength(form.getEmailSpedizioniere(), "emailSpedizioniere", 100, errors);
  }

  public void validateDatiRegistrazioneUtente(SpedizioniereForm spedizioniereForm, BindingResult result)
      throws BusinessException {

	// *** Validazione dati UTENTE
	logger.debug("*** Validazione dati UTENTE");
	
	//CarTUtente utente = utenteEJB.getUtenteByCodiceFiscale(spedizioniereForm.getCodiceFiscale());
	UtenteForm utenteForm = new UtenteForm();
	utenteForm.setCodiceFiscale(spedizioniereForm.getCodiceFiscale());
	boolean utenteUnivoco = utenteEJB.isCFUnivocal(utenteForm);
	logger.debug("utenteUnivoco = "+utenteUnivoco);
	// CASO validazione UTENTE non ancora presente sul db
	if(utenteUnivoco){
	  logger.debug("CASO validazione UTENTE non ancora presente sul db");
      validateDatiUtenteRegistrazione(spedizioniereForm, result);
      
      // *** Validazione PASSWORD UTENTE
      logger.debug("*** Validazione PASSWORD UTENTE");
      validatePasswordUtente(spedizioniereForm, result);
	}
	// CASO UTENTE già presente sul db : non si deve lasciare proseguire
	else{
		logger.debug("CASO UTENTE già presente sul db, codice fiscale ="+spedizioniereForm.getCodiceFiscale());
		result.rejectValue("codiceFiscale", "error.registeredUser");		
	}

	
	
    if (spedizioniereForm.getIdTipoSpedizioniere() == CaronteConstants.DITTA_INDIVIDUALE || spedizioniereForm.getIdTipoSpedizioniere() == CaronteConstants.UTENTE_PRIVATO)
      spedizioniereForm.setCuaa(spedizioniereForm.getCodiceFiscale());

    SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniereByCuua(spedizioniereForm.getCuaa());
    
	// *** Validazione dati SPEDIZIONIERE
    logger.debug("*** Validazione dati SPEDIZIONIERE");
    // CASO validazione SPEDIZIONIERE non ancora presente sul db
    if (spedizioniere == null){
      logger.debug("CASO validazione SPEDIZIONIERE non ancora presente sul db");
      validateDatiSpedizioniereRegistrazione(spedizioniereForm, result);

      // controllo che sia di 11 o 16 caratteri
      if (spedizioniereForm.getCuaa() != null)
        if (spedizioniereForm.getCuaa().length() != 11 && spedizioniereForm.getCuaa().length() != 16)
          result.rejectValue("cuaa", "error.cuaaLength");
    } 
    // CASO validazione SPEDIZIONIERE presente sul db
    else {
      logger.debug("CASO validazione SPEDIZIONIERE presente sul db");
      validateDatiSpedizioniereGiaEsistenteRegistrazione(spedizioniereForm, result);

      // se lo spedizioniere è di tipo 4 o 5 (ovvero ditta individuale o utente privato) può essere associato ad un solo utente
      if (spedizioniereForm.getIdTipoSpedizioniere() == CaronteConstants.DITTA_INDIVIDUALE || spedizioniereForm.getIdTipoSpedizioniere() == CaronteConstants.UTENTE_PRIVATO){
       		logger.debug("utenteUnivoco = "+utenteUnivoco);
    		// CASO validazione UTENTE non ancora presente sul db
    		if(!utenteUnivoco){
    			logger.debug("-- Lo spedizioniere è di tipo 4 o 5 (ditta individuale o utente privato) : può essere associato ad un solo utente");  
    	        result.rejectValue("cuaa", "error.dittaIndividuale");
    		}    	
      }  
    }
    
    // Validazione sull'Accettazione delle Policy del trattamento dati personali
    logger.debug("-- Validazione sull'Accettazione delle Policy del trattamento dati personali");
    if(StringUtils.isEmpty(spedizioniereForm.getPrivacyPolicy())){
    	result.rejectValue("privacyPolicy", "error.policyPrivacy");
    }

    
  }

  private void validateDatiSpedizioniereGiaEsistenteRegistrazione(SpedizioniereForm spedizioniereForm,
      BindingResult errors) throws BusinessException {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idTipoSpedizioniere", "error.required");

    if (spedizioniereForm.getIdTipoSpedizioniere() != null
        && spedizioniereForm.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE
        && spedizioniereForm.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO)// no
                                                                                         // ditta
                                                                                         // individuale
    {
      // valido denominazione se la ditta non è di tipo individuale
      validateMandatoryAndMaxLength(spedizioniereForm.getDenomSpedizioniere(), "denomSpedizioniere", 200, errors);
    } else {
      // se ditta individuale il cuaa è il codice fiscale dell'utente
      spedizioniereForm.setCuaa(spedizioniereForm.getCodiceFiscale());
      spedizioniereForm.setEmailSpedizioniere(spedizioniereForm.getEmail());
      spedizioniereForm.setNumeroTelefono(spedizioniereForm.getNumeroTelefonoUtente());
      spedizioniereForm.setNumeroCellulare(spedizioniereForm.getNumeroCellUtente());
      if (spedizioniereForm.getDenomSpedizioniere() == null) {
    	  spedizioniereForm.setDenomSpedizioniere(spedizioniereForm.getCognome() + " " + spedizioniereForm.getNome());
      }      
      if ( spedizioniereForm.getIdTipoSpedizioniere() == CaronteConstants.UTENTE_PRIVATO) { // UTENTE PRIVATO = tipo spedizioniere ALTRO,  controllo che sia valorizzato il campo note_altro
    	  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoSpedizioniereAltro", "error.required");
      }
    }

    validateMandatoryAndMaxLength(spedizioniereForm.getCuaa(), "cuaa", 16, errors);
    if (spedizioniereForm.getCuaa() != null && spedizioniereForm.getCuaa().length() != 11
        && spedizioniereForm.getCuaa().length() != 16) {
      errors.rejectValue("cuaa", "error.cuaaLength");
    }
        
    if (spedizioniereForm.getPartitaIVA() != null) {
    	logger.debug("controllo isPartitaIva: "+ CaronteUtils.isPartitaIva(spedizioniereForm.getPartitaIVA()));
    	if (spedizioniereForm.getPartitaIVA().length() != 11 || !CaronteUtils.isPartitaIva(spedizioniereForm.getPartitaIVA())) {
    		errors.rejectValue("partitaIVA", "error.piva.invalid");
    	}
    }

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idProvinciaSedeSociale", "error.required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idComuneSedeSociale", "error.required");
    validateMandatoryAndMaxLength(spedizioniereForm.getIndirizzoSedeSociale(), "indirizzoSedeSociale", 200, errors);
    validateMandatoryAndMaxLength(spedizioniereForm.getCapSedeSociale(), "capSedeSociale", 5, errors);
    //validateMandatoryAndMaxLength(spedizioniereForm.getCodiceRUOP(), "codiceRUOP", 20, errors);
    validateMaxLength(spedizioniereForm.getEmailSpedizioniere(), 100, "emailSpedizioniere", errors);
    validateMaxLength(spedizioniereForm.getPec(), 100, "pec", errors);
    validateMaxLength(spedizioniereForm.getNumeroTelefono(), 20, "numeroTelefono", errors);
    validateMaxLength(spedizioniereForm.getMotivoRichiesta(), 500, "motivoRichiesta", errors);
  }

  public void validatePasswordUtente(UtenteForm utenteForm, BindingResult errors) {

    validateMandatoryAndMaxLength(utenteForm.getPassword(), "password", 50, errors);
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confermaPassword", "error.required");

    if (utenteForm.getPassword() != null && utenteForm.getConfermaPassword() != null
        && utenteForm.getPassword().compareTo(utenteForm.getConfermaPassword()) == 0) {
      // ok pass match -> controllo che abbia i requisiti : almeno 1 minuscola,
      // 1 maiuscola, 1 carattere speciale e un numero e sia di almeno 8
      // caratteri
      String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#\\()/£\"$%^&+=_-{|}])(?=\\S+$).{8,}$";

      if (utenteForm.getPassword().length() < 8) {
        errors.rejectValue("password", "error.passwordLen");
        errors.rejectValue("confermaPassword", "error.passwordLen");
      }
      if (!utenteForm.getPassword().matches(pattern)) {
        errors.rejectValue("password", "error.passwordPattern");
        errors.rejectValue("confermaPassword", "error.passwordPattern");
      }
    } else {
      errors.rejectValue("password", "notmatch.password");
      errors.rejectValue("confermaPassword", "notmatch.password");
    }
  }

  public void validateDatiSpedizioniereRegistrazione(SpedizioniereForm spedizioniereForm, Errors errors)
      throws BusinessException {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idTipoSpedizioniere", "error.required");
    // ValidationUtils.rejectIfEmptyOrWhitespace(errors,
    // "idAssociazioneSezione", "error.required");

    // almeno uno tra i tre flag deve essere selezionato
  /*  if (!spedizioniereForm.isSezioneExport() && !spedizioniereForm.isSezioneImport() && !spedizioniereForm.isSezioneVivai()) {
      errors.rejectValue("sezioneImport", "error.importExport");
    }*/

    if (spedizioniereForm.getIdTipoSpedizioniere() != null
        && spedizioniereForm.getIdTipoSpedizioniere() != CaronteConstants.DITTA_INDIVIDUALE
        && spedizioniereForm.getIdTipoSpedizioniere() != CaronteConstants.UTENTE_PRIVATO)// no
                                                                                         // individuale
    {
      // valido denominazione se la ditta non è di tipo individuale
      validateMandatoryAndMaxLength(spedizioniereForm.getDenomSpedizioniere(), "denomSpedizioniere", 200, errors);
      if (spedizioniereForm.getDenomSpedizioniere() != null)
        if (!utenteEJB.isDenomUnivocal(spedizioniereForm))
          errors.rejectValue("denomSpedizioniere", "error.univocalDenom");
    }
    else {
      // se ditta individuale il cuaa è il codice fiscale dell'utente
      spedizioniereForm.setCuaa(spedizioniereForm.getCodiceFiscale());
      spedizioniereForm.setEmailSpedizioniere(spedizioniereForm.getEmail());
      spedizioniereForm.setNumeroTelefono(spedizioniereForm.getNumeroTelefonoUtente());
      spedizioniereForm.setNumeroCellulare(spedizioniereForm.getNumeroCellUtente());
      if (spedizioniereForm.getDenomSpedizioniere() == null) {
    	  spedizioniereForm.setDenomSpedizioniere(spedizioniereForm.getCognome() + " " + spedizioniereForm.getNome());
      }
      if ( spedizioniereForm.getIdTipoSpedizioniere() == CaronteConstants.UTENTE_PRIVATO) { // UTENTE PRIVATO = tipo spedizioniere ALTRO,  controllo che sia valorizzato il campo note_altro
    	  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoSpedizioniereAltro", "error.required");
      }
    }

    validateMandatoryAndMaxLength(spedizioniereForm.getCuaa(), "cuaa", 16, errors);
    if (spedizioniereForm.getCuaa() != null) {
      if (spedizioniereForm.getCuaa().length() != 11 && spedizioniereForm.getCuaa().length() != 16) {
        errors.rejectValue("cuaa", "error.cuaaLength");
      }
    }
    
    if (spedizioniereForm.getPartitaIVA() != null) {
    	logger.debug("controllo isPartitaIva: "+ CaronteUtils.isPartitaIva(spedizioniereForm.getPartitaIVA()));
    	if (spedizioniereForm.getPartitaIVA().length() != 11 || !CaronteUtils.isPartitaIva(spedizioniereForm.getPartitaIVA())) {
    		errors.rejectValue("partitaIVA", "error.piva.invalid");
    	}
    }

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idProvinciaSedeSociale", "error.required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idComuneSedeSociale", "error.required");
    validateMandatoryAndMaxLength(spedizioniereForm.getIndirizzoSedeSociale(), "indirizzoSedeSociale", 200, errors);
    validateMandatoryAndMaxLength(spedizioniereForm.getCapSedeSociale(), "capSedeSociale", 5, errors);
   // validateMandatoryAndMaxLength(spedizioniereForm.getCodiceRUOP(), "codiceRUOP", 20, errors);
    
    validateMaxLength(spedizioniereForm.getEmailSpedizioniere(), 100, "emailSpedizioniere", errors);
    validateMaxLength(spedizioniereForm.getPec(), 100, "pec", errors);

    validateMaxLength(spedizioniereForm.getNumeroTelefono(), 20, "numeroTelefono", errors);
   // validateMaxLength(spedizioniereForm.getCodiceRUP(), 20, "codiceRUP", errors);
    //validateMandatoryAndMaxLength(spedizioniereForm.getCodiceRUOP(), "codiceRUOP", 20, errors);
    validateMaxLength(spedizioniereForm.getMotivoRichiesta(), 500, "motivoRichiesta", errors);
  }

  
  // Caso di utente non ancora presente sul db
  public void validateDatiUtenteRegistrazione(UtenteForm form, Errors errors) throws BusinessException {
    validateMandatoryAndMaxLength(form.getNome(), "nome", 50, errors);
    validateMandatoryAndMaxLength(form.getCognome(), "cognome", 50, errors);
    validateMandatoryAndMaxLength(form.getCodiceFiscale(), "codiceFiscale", 16, errors);
    if (StringUtils.isNotEmpty(form.getCodiceFiscale())) {
      if (form.getCodiceFiscale().length() != 11 && form.getCodiceFiscale().length() != 16) {
        errors.rejectValue("codiceFiscale", "error.codiceFiscaleLength");
      } else if (form.getCodiceFiscale().length() == 11 && !CaronteUtils.isPartitaIva(form.getCodiceFiscale())) {
        errors.rejectValue("codiceFiscale", "error.piva.invalid");
      } else if (!CaronteUtils.isCodiceFiscale(form.getCodiceFiscale())) {
        errors.rejectValue("codiceFiscale", "error.cf.invalid");
      } else if (!utenteEJB.isCFUnivocal(form)) {
        errors.rejectValue("codiceFiscale", "error.univocalCF");
      }
    }
    validateMandatoryAndMaxLength(form.getEmail(), "email", 100, errors);
    validateMaxLength(form.getNumeroTelefonoUtente(), 50, "numeroTelefonoUtente",  errors);
    validateMandatoryAndMaxLength(form.getNumeroCellUtente(), "numeroCellUtente", 30, errors);    

    if (form.getEmail() != null && !utenteEJB.isMailUnivocal(form)) {
      errors.rejectValue("email", "error.univocalMail");
    }

    validateMaxLength(form.getNote(), 1000, "note", errors);
    
    if(form.getDataNascita() == null){
    	errors.rejectValue("dataNascita", "error.dataNascitaObbl");
    }
  }

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void checkPasswordRiassegnazione(UtenteForm utenteForm, Errors errors) throws BusinessException {

    UtenteDTO utente = utenteEJB.getUtente(utenteForm.getIdUtente());

    // se l'utente aveva già una password faccio il controllo che la vecchia psw
    // inserita sia corretta
    if (utente.getPassword() != null && utenteForm.getOldPassword() != null)
      if (!passwordEncoder.matches(utenteForm.getOldPassword(), utente.getPassword()))
        errors.rejectValue("oldPassword", "error.wrongPassword");

  }

  public void validaDatiDettaglioUtente(UtenteForm form, Errors errors) {

    validateMandatoryAndMaxLength(form.getNome(), "nome", 50, errors);
    validateMandatoryAndMaxLength(form.getCognome(), "cognome", 50, errors);
    validateMandatoryAndMaxLength(form.getCodiceFiscale(), "codiceFiscale", 16, errors);
    validateMaxLength(form.getNumeroTelefonoUtente(), 20, "numeroTelefonoUtente", errors);
    validateMaxLength(form.getNumeroCellUtente(), 30, "numeroCellUtente", errors);
    if (form.getNumeroTelefonoUtente() == null)
      form.setNumeroTelefonoUtente("");
    if (form.getNote() == null)
      form.setNote("");
    if (form.getNumeroCellUtente() == null)
      form.setNumeroCellUtente("");
  }
  
  public void validateDatiSedeFuoriRegione(SpedizioniereForm form, Errors errors){
	  logger.debug("BEGIN validateDatiSedeFuoriRegione");
	  
	  // Codice Ruop 
	  validateMandatoryAndMaxLength(form.getCodiceRUOP(), "codiceRUOP", 10, errors);
	  // DA FARE : validazione formato
	  
	  // Data registrazione Ruop
	  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataRegistrazioneRuop", "error.required");
	  
	  logger.debug("END validateDatiSedeFuoriRegione");
  }

}
