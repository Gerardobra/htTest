package it.aizoon.ersaf.validator;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IDomandeEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.TipologiaAttMaterialeDTO;
import it.aizoon.ersaf.dto.TipologiaProdSpecieDTO;
import it.aizoon.ersaf.dto.ZonaProtettaSpecieDTO;
import it.aizoon.ersaf.dto.generati.CarTUtente;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.DatiSitoProduzioneForm;
import it.aizoon.ersaf.form.ModaliForm;
import it.aizoon.ersaf.form.NuovaDomandaForm;
import it.aizoon.ersaf.form.SpedizioniereForm;
import it.aizoon.ersaf.form.UtenteForm;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;


@Component
public class DomandaValidator extends BaseValidator {
	
	protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".validator");

	@Autowired
	private IUtenteEJB utenteEJB = null;
	
	@Autowired
	private IDecodificheEJB decodificheEJB = null;
	
	@Autowired
	private IDomandeEJB domandeEJB = null;
	
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  @Override
  public boolean supports(Class<?> paramClass) {
    return NuovaDomandaForm.class.equals(paramClass);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  @Override
  public void validate(Object target, Errors errors) {
    // TODO Auto-generated method stub
  }
  
  
  // Validazione nel caso di inserimento dati azienda
  public void validateDatiNuovaAzienda(NuovaDomandaForm form, List<TipologiaAttMaterialeDTO> tipologieAttMaterialidb, Errors errors) throws BusinessException{
	  logger.debug("validateDatiNuovaAzienda");
	  validateDatiAzienda(form,errors);

	  // Controlli in base al tipo di azienda
	  logger.debug("-- Controlli in base al tipo di azienda");
	  if (form.getIdTipoAzienda() != null && 
			  form.getIdTipoAzienda() != CaronteConstants.UTENTE_PRIVATO && 
			  form.getIdTipoAzienda() != CaronteConstants.DITTA_INDIVIDUALE) {
		  // La denominazione deve essere univoca
		  logger.debug("-- La denominazione deve essere univoca");
	      if (form.getDenomAzienda() != null){
	    	SpedizioniereForm formSped = new SpedizioniereForm();
	    	formSped.setDenomSpedizioniere(form.getDenomAzienda());
	        if (!utenteEJB.isDenomUnivocal(formSped)){
	          logger.debug("La denominazione deve essere univoca");
	          errors.rejectValue("denomAzienda", "error.univocalDenom");
	        }  
	      }  	     
	  } 	
	  // tipo Azienda Altro (ex-utente privato)
	  if (form.getIdTipoAzienda() == CaronteConstants.UTENTE_PRIVATO) {
		  logger.debug("-- controllo che sia stata specificata la tipologia Altro");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoSpedizioniereAltro", "error.required");	
	  }
	 
	 // Il cuaa deve essere univoco	
	 logger.debug("-- Il cuaa deve essere univoco");
	 if (form.getCodFiscaleAz() != null) {
	   SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniereByCuua(form.getCodFiscaleAz());
	   if (spedizioniere != null) {
	     if (form.getIdSpedizioniere() == null || !spedizioniere.getIdSpedizioniere().equals(form.getIdSpedizioniere())) {
	   	   logger.debug("Il cuaa deve essere univoco");
	       errors.rejectValue("codFiscaleAz", "error.univocalCuaa");
		 }
	   }
	 }	 
	 
	 
	// la partita IVA deve essere univoca	
	 if (form.getPartitaIva() != null) {
	   SpedizioniereDTO spedizioniere = utenteEJB.getSpedizioniereByPartitaIva(form.getPartitaIva());
	   if (spedizioniere != null) {
	     if (form.getIdSpedizioniere() == null || !spedizioniere.getIdSpedizioniere().equals(form.getIdSpedizioniere())) {
	   	   logger.debug("-- la partita IVA deve essere univoca");
	       errors.rejectValue("partitaIva", "error.univocalpiva");
		 }
	   }
	   logger.debug("controllo isPartitaIva: "+ CaronteUtils.isPartitaIva(form.getPartitaIva()));
    	if (form.getPartitaIva().length() != 11 || !CaronteUtils.isPartitaIva(form.getPartitaIva())) {
    		errors.rejectValue("partitaIva", "error.piva.invalid");
    	}	   
	 }	  
	 
	 
	 // ---- Controlli per le attività azienda
	// Attività svolte dall'Operatore professionale (check) 
	if(form.getIdVoceCheckTip() == null || form.getIdVoceCheckTip().length == 0){
	  errors.rejectValue("idVoceCheckTip", "error.idVoceCheckTip.almenouna");
	}
	// Tipologia attività e materiale presenti sul db
	if(tipologieAttMaterialidb == null || tipologieAttMaterialidb.size()==0){
	  errors.rejectValue("idTipologiaAttTip", "error.tipologiaAttivita.almenouna");
	} /*else {
		// se non è stata messa la spunta, controllo che non ci sia l'attività vivaismo(passaporto obbligatorio)
		if (!form.isRichiestaPassaporto()) {
			// se è stata aggiunta l'attività Vivaismo deve aver spuntato la check richiesta passaporto
			for (Iterator<TipologiaAttMaterialeDTO> iterator = tipologieAttMaterialidb.iterator(); iterator.hasNext();) {
				TipologiaAttMaterialeDTO tipologiaAttMaterialeDTO = (TipologiaAttMaterialeDTO) iterator.next();
				if (tipologiaAttMaterialeDTO.getIdTipologia() == CaronteConstants.ID_TIPO_ATTIVITA_VIVAISMO ) { 
					 logger.debug("Errore sul check passaporto, obbligatorio per Attivita Vivaismo");
					errors.rejectValue("richiestaPassaporto", "error.richiestaPassaporto.almenouna");
					break;
				}
			}
		}		
	}*/
	 
	 
  }
  
  // Validazione nel caso di modifica dati azienda
  public void validateDatiModificaAzienda(NuovaDomandaForm form, List<TipologiaAttMaterialeDTO> tipologieAttMaterialidb, Errors errors) throws BusinessException{
	  logger.debug("validateDatiModificaAzienda");
	  validateDatiAzienda(form,errors);	  

	  logger.debug("-- idTipoComunicazione = "+form.getIdTipoComunicazione());
	  logger.debug("-- domandaRuopConvalidataPresente = "+form.isDomandaRuopConvalidataPresente());

	  // I controlli sui check e Tipologie attività si fanno solo per Domanda Ruop, Domanda Variazione Ruop o Domanda Passaporto che non ha la Domanda Ruop, ma ha il codice Ruop
	  if (form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP
			  || 
			  (form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO ) ) {
			  //(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO && form.isDomandaRuopConvalidataPresente()) ) {        
		  return ;
	  }
	  // ---- Controlli per le attività azienda
	  // Attività svolte dall'Operatore professionale (check) 
	  if(form.getIdVoceCheckTip() == null || form.getIdVoceCheckTip().length == 0){
		  errors.rejectValue("idVoceCheckTip", "error.idVoceCheckTip.almenouna");
	  }
	  // Tipologia attività e materiale presenti sul db
	  if(tipologieAttMaterialidb == null || tipologieAttMaterialidb.size()==0){
		  errors.rejectValue("idTipologiaAttTip", "error.tipologiaAttivita.almenouna");
	  } /*else {
		  // se non è stata messa la spunta, controllo che non ci sia l'attività vivaismo(passaporto obbligatorio)
		  if (!form.isRichiestaPassaporto()) {
			  // se è stata aggiunta l'attività Vivaismo deve aver spuntato la check richiesta passaporto
			  for (Iterator<TipologiaAttMaterialeDTO> iterator = tipologieAttMaterialidb.iterator(); iterator.hasNext();) {
				  TipologiaAttMaterialeDTO tipologiaAttMaterialeDTO = (TipologiaAttMaterialeDTO) iterator.next();
				  if (tipologiaAttMaterialeDTO.getIdTipologia() == CaronteConstants.ID_TIPO_ATTIVITA_VIVAISMO ) { 
					  logger.debug("Errore sul check passaporto, obbligatorio per Attivita Vivaismo");
					  errors.rejectValue("richiestaPassaporto", "error.richiestaPassaporto.almenouna");
					  break;
				  }
			  }
		  }		
	  }*/
  }
  
  // Validazioni generiche sui dati dell'azienda
  private void validateDatiAzienda(NuovaDomandaForm form, Errors errors) throws BusinessException{
	  logger.debug("validateDatiAzienda");
	  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idTipoAzienda", "error.required");	   

	  // Controlli in base al tipo di azienda
	  logger.debug("-- Controlli in base al tipo di azienda");
	  if (form.getIdTipoAzienda() != null && 
			  form.getIdTipoAzienda() != CaronteConstants.UTENTE_PRIVATO && 
			  form.getIdTipoAzienda() != CaronteConstants.DITTA_INDIVIDUALE) {
		  
	      validateMandatoryAndMaxLength(form.getDenomAzienda(), "denomAzienda", 200, errors);	       

	      validateMandatoryAndMaxLength(form.getEmailSedeLegale(), "emailSedeLegale", 100, errors);
	      validateMandatoryAndMaxLength(form.getPecSedeLegale(), "pecSedeLegale", 100, errors);
	      
	      validateMaxLength(form.getNumTelefonoSedeLegale(), 40, "numTelefonoSedeLegale", errors);
	      validateMaxLength(form.getNumCellulareSedeLegale(), 30, "numCellulareSedeLegale", errors);
	      validateNumeric(form.getNumCellulareSedeLegale(), "numCellulareSedeLegale", errors);
	      validateMaxLength(form.getFaxSedeLegale(), 20, "faxSedeLegale", errors);

	  } 
	  else {
	    validateMandatoryAndMaxLength(form.getNomeAzienda(), "nomeAzienda", 50, errors);
	    validateMandatoryAndMaxLength(form.getCognomeAzienda(), "cogomeAzienda", 50, errors);
	    
	    if (form.getIdTipoAzienda() == CaronteConstants.DITTA_INDIVIDUALE)
	      validateMandatoryAndMaxLength(form.getPecSedeLegale(), "pecSedeLegale", 100, errors);
	    else
	      validateMaxLength(form.getPecSedeLegale(), 100, "pecSedeLegale", errors);
	    
	    validateMandatoryAndMaxLength(form.getEmailSedeLegale(), "emailSedeLegale", 100, errors);
	    validateMaxLength(form.getNumTelefonoSedeLegale(), 40, "numTelefonoSedeLegale", errors);
	    validateMaxLength(form.getNumCellulareSedeLegale(), 30, "numCellulareSedeLegale", errors);
	    validateNumeric(form.getNumCellulareSedeLegale(), "numCellulareSedeLegale", errors);
	    validateMaxLength(form.getFaxSedeLegale(), 20, "faxSedeLegale", errors);
	 }
	// tipo Azienda Altro (ex-utente privato)
	  if (form.getIdTipoAzienda() == CaronteConstants.UTENTE_PRIVATO) {
		  logger.debug("-- controllo che sia stata specificata la tipologia Altro");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoSpedizioniereAltro", "error.required");	
	  }

	 // Controlli sul cuaa
	 logger.debug("-- Controlli sul cuaa");	 
	 validateMandatoryAndMaxLength(form.getCodFiscaleAz(), "codFiscaleAz", 16, errors);	    
	 if (form.getCodFiscaleAz() != null) {
	   if (form.getCodFiscaleAz().length() != 11 && form.getCodFiscaleAz().length() != 16) {
	     errors.rejectValue("codFiscaleAz", "error.cuaaLength");
	   }
	 }
	 //controllo che il cuaa sia uguale al cf presente in anagrafica, se l' azienda è una ditta individuale
	 if(form.getIdTipoAzienda() == CaronteConstants.ID_TIPO_AZIENDA_DITTA_INDIVIDUALE){
		 if(!form.getCodFiscale().equals(form.getCodFiscaleAz())){
			 errors.rejectValue("codFiscaleAz", "error.cuaaInvalid");
		 }
	 }
	    
	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idProvinciaSedeLegale", "error.required");
	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idComuneSedeLegale", "error.required");
	 
	 validateMandatoryAndMaxLength(form.getIndirizzoSedeLegale(), "indirizzoSedeLegale", 200, errors);
	 validateMandatoryAndMaxLength(form.getCapSedeLegale(), "capSedeLegale", 5, errors);	
	 validateMandatoryAndMaxLength(form.getPartitaIva(), "partitaIva", 11, errors);	 
	 
  }
  
  public void validateDatiAnagrafici(NuovaDomandaForm form, Errors errors) throws BusinessException{	  
	    logger.debug("validateDatiAnagrafici");
	    validateMandatoryAndMaxLength(form.getCognome(), "cognome", 50, errors);
	    validateMandatoryAndMaxLength(form.getNome(), "nome", 50, errors);

	    // Codice fiscale
	    validateMandatoryAndMaxLength(form.getCodFiscale(), "codFiscale", 16, errors);
	    if (form.getCodFiscale() != null) {
	      logger.debug("-- Controllo che il codice fiscale abbia un formato corretto");
	      if (!CaronteUtils.isCodiceFiscale(form.getCodFiscale())) {
	    	logger.debug("-- Il codice fiscale non ha un formato corretto");
	        errors.rejectValue("codFiscale", "error.cf.invalid");
	      }
	      // Se il formato del codice fiscale è corretto
	      else{
		      // Controllare che il codice fiscale ci sia già su car_t_utente
		      logger.debug("-- Controllo che il codice fiscale sia presente su car_t_utente");
		      CarTUtente utente = utenteEJB.getUtenteByCodiceFiscale(form.getCodFiscale());
		      if(utente == null){
		        errors.rejectValue("codFiscale", "error.cf.nonCensito");  
		      }
	      }
	    }
	    //controllo che il cf sia uguale al cuaa, in caso di ditta individuale
		if(form.getIdTipoAzienda()!= null){ 
		    if(form.getIdTipoAzienda() == CaronteConstants.ID_TIPO_AZIENDA_DITTA_INDIVIDUALE){
				 if(!form.getCodFiscale().equals(form.getCodFiscaleAz())){
					 errors.rejectValue("codFiscale", "error.codFiscaleInvalid");
				 }
			 }
		}
	    // *** Dati nascita ***
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "error.required");

	    // Check box 'Nascita Comune estero'
	    logger.debug("-- nascita estera = "+form.isNascitaEstera());
	    if (form.isNascitaEstera()) {
	    	 logger.debug("-- nascita estera true");
	      //validateMandatoryAndMaxLength(form.getDenomComuneEstNascita(), "denomComuneEstNascita", 100, errors);
	    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idNazioneEstNascita", "error.required");
	    }
	    else {
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idProvinciaNascita", "error.required");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idComuneNascita", "error.required");
	    }

	    // *** Dati residenza ***
	    // Check box 'Residenza Comune estero'
	    if (form.isResidenzaEstera()) {
	    	logger.debug("-- residenza estera true");
	     // validateMandatoryAndMaxLength(form.getDenomComuneEstResid(), "denomComuneEstResid", 100, errors);
	    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idNazioneEstResid", "error.required");
	    } 
	    else {
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idProvinciaResidenza", "error.required");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idComuneResidenza", "error.required");
	      validateMandatoryAndMaxLength(form.getCap(), "cap", 5, errors);
	    }

	    validateMandatoryAndMaxLength(form.getIndirizzo(), "indirizzo", 200, errors);	    
	    validateMaxLength(form.getNumTelefono(), 20, "numTelefono", errors);
	    validateMandatoryAndMaxLength(form.getCellulare(), "cellulare", 30, errors);
	    
	    validateMandatoryAndMaxLength(form.getEmail(), "email", 100, errors);
	    UtenteForm formUtente = new UtenteForm();
	    formUtente.setEmail(form.getEmail());	    
	  }
  
  
     // Controllo che sia stata selezionata almeno una tipologia
     public void validateDatiTipologia(NuovaDomandaForm form, Errors errors) throws BusinessException{	  
    	 logger.debug("BEGIN validateDatiTipologia");

    	 // Tipologie domanda (check)
    	 if(form.getIdTipologia() == null || form.getIdTipologia().length == 0){
    		 errors.rejectValue("idTipologia", "error.tipologia.almenouna");
    	 }    	 
     }
     
     // Validazione per i Siti di produzione
     public void validateDatiSitoProduzione(NuovaDomandaForm form, Errors errors) {
    	 logger.debug("validateDatiSitoProduzione"); 
	    //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descSitoProduzione", "error.required");
	    //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ubicazione", "error.required");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaSitoProd", "error.required");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneSitoProd", "error.required");
	    validateMandatoryAndInteger(form.getFoglio(), "foglio", 0, 9999, errors);
	    validateMandatoryAndInteger(form.getMappale(), "mappale", 0, 99999999, errors);
	    validateMandatoryAndDecimal(form.getSuperficie(), "superficie", 6, 2, errors);    	    
    }
     
     // Controllo che sia stata selezionata almeno una tipologia
     public void validateDatiPassaporto(NuovaDomandaForm form, List<TipologiaProdSpecieDTO> tipProdDB, List<ZonaProtettaSpecieDTO> zonaProtettaSpecieDB,  Errors errors) throws BusinessException {	  
    	 logger.debug("validateDatiPassaporto");    	 
    	 validateResponsabileFitosanitarioPassaporto(form, errors);    	 
    	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idVoceRadio", "error.voce.almenouna"); 
    	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idVoceRadioTipoRichiesta", "error.voce.almenouna");   	
    	 
    	 // Le zone protette sono obbligatorie solo se è stato selezionato il check : 'Il commercio avviene anche in zone protette'
    	 boolean zoneProtetteObbligatorie = false;
    	 if(form.getIdVoceCheck() != null && form.getIdVoceCheck().length>0){
    		 for (int i = 0; i < form.getIdVoceCheck().length; i++) {
				String idVoce = form.getIdVoceCheck()[i];
				if(Long.valueOf(idVoce) == CaronteConstants.ID_VOCE_PASSAPORTO_ZONA_PROTETTA){
					zoneProtetteObbligatorie = true;
					break;
				}
				
			}
    	 }
    	 logger.debug("-- zoneProtetteObbligatorie ="+zoneProtetteObbligatorie);
    	 if(zoneProtetteObbligatorie){
			 if(zonaProtettaSpecieDB == null || zonaProtettaSpecieDB.size() == 0){
				 errors.rejectValue("idZonaProtetta", "error.zonaProtetta.almenouna");
			 }	 
    	 }
			 
		 if(tipProdDB == null || tipProdDB.size() == 0){
		   errors.rejectValue("idTipologiaProd", "error.tipologiaProd.almenouna"); 
		 }
		 
		 if (form.getVoceDichiaraConoscenze() == null) {
			 errors.rejectValue("voceDichiaraConoscenze", "nuovaDomandaForm.voceDichiaraConoscenze", "Selezionare almeno un'opzione.");
	 	 }
	     if (form.getVoceDichiaraDisporreSistemi() == null) {
	    	 errors.rejectValue("voceDichiaraDisporreSistemi", "nuovaDomandaForm.voceDichiaraDisporreSistemi", "Selezionare almeno un'opzione.");
	 	 }	 
	     if (form.getVocePianoRischi() == null) {
	    	 errors.rejectValue("vocePianoRischi", "nuovaDomandaForm.vocePianoRischi", "Selezionare almeno un'opzione.");
	 	 }
		 
		 
     }
     
     
     // Controllo sull'aggiungi Tipologia produttiva passaporto
     public void validateAggiungiTipologiaProdPassaporto(NuovaDomandaForm form, Errors errors) throws BusinessException {
    	 /*
    	  * Casi in cui viene visualizzata anche la combo genere :
    	  * - car_d_voce.id_voce = 50 (Tutte le piante destinate all'impianto (eccetto sementi))
    	  * - car_d_voce.id_voce = 54 (Sementi)
    	  * 
    	  *  Se è stato selezionato un genere, controllo se è un tipo genere famiglia :    
    	  *   - se non lo è deve essere stata selezioa
    	  *   
    	  *   Negli altri casi deve essere indicata la specie
    	  */
    	 boolean isTipoGenereFamiglia = false;
    	 boolean erroreGenere = false;
    	 if(form.getIdTipologiaProd() != null &&
    			 (form.getIdTipologiaProd() == CaronteConstants.ID_VOCE_PIANTE_DESTINATE_IMPIANTO ||
    			  form.getIdTipologiaProd() == CaronteConstants.ID_VOCE_SEMENTI)
    	   ){
	    	 if (form.getGenere() != null) {
	    		 logger.debug("-- genere selezionato ="+form.getGenere());
	    		 isTipoGenereFamiglia = decodificheEJB.isTipoGenereFamiglia(form.getGenere());
	    		 logger.debug("-- isTipoGenereFamiglia ="+isTipoGenereFamiglia);
	    		 if(!isTipoGenereFamiglia){
	    			 if (form.getSpecie() == null || form.getSpecie().length == 0) {
	    				 logger.debug("-- Selezionare almeno una specie");
	    				 errors.rejectValue("idTipologiaProd", "error.specieSel.almenoUna");
	    				 erroreGenere = true;
	    			 }
	    		 }			
			 }   
    	 }
    	 
    	 if(!erroreGenere && !isTipoGenereFamiglia){
	    	 if ((form.getSpecie() == null || form.getSpecie().length == 0)) {
	    		 logger.debug("-- Errore sepcie TP");
	    		 errors.rejectValue("idTipologiaProd", "error.tipologiaProd.almenouna");    		 
	    	 }    	 
    	 }
     }
     
    // Controllo che sia stata selezionata almeno una tipologia (al salva)
   /*  public void validateTipologiaProdPassaporto(NuovaDomandaForm form, Errors errors) throws BusinessException {
    	 logger.debug("validateTipologiaProdPassaporto");
    	 
    	 if ((form.getSpecie() == null || form.getSpecie().length == 0)) {
    		 logger.debug("-- Errore sepcie TP");
    		 errors.rejectValue("idTipologiaProd", "error.tipologiaProd.almenouna");    		 
    	 }    	 
     }*/

     // Controllo che sia stata selezionata almeno una zona protetta
     public void validateZoneProtettePassaporto(NuovaDomandaForm form, Errors errors) throws BusinessException {
    	 logger.debug("validateZoneProtettePassaporto");
    	 
    	 if ((form.getSpecieZP() == null || form.getSpecieZP().length == 0) ) {
    		 logger.debug("-- Errore specie ZP");
    		 errors.rejectValue("idZonaProtetta", "error.zonaProtetta.almenouna");
    	 }
     }
     
     
     // Controllo che sia stata selezionata almeno una tipologia import
     public String validateTipologiaProdImport(NuovaDomandaForm form, Errors errors) throws BusinessException {
    	 logger.debug("validateTipologiaProdImport");
    	 logger.debug("-- isTipoGenereFamiglia = " +decodificheEJB.isTipoGenereFamiglia(form.getGenereImport()));

    	 String error= "";
    	 // Se è stato selezionato 'Tipologia' = 'Altro' : le note sono obbligatorie 
    	 if(form.getIdTipologiaProdImport() != null && form.getIdTipologiaProdImport() == CaronteConstants.ID_VOCE_ALTRO_TIPOLOG_IMPORT){
    		 logger.debug("-- Controllare che siano state compilate le Note");
    		 if(form.getNoteTipologiaImport() == null || form.getNoteTipologiaImport().trim().length() == 0){
    			 errors.rejectValue("noteTipologiaImport", "error.noteTipologProd.compilaNote");     
    			 error = "Specificare la Tipologia produttiva nel campo Note";
    		 }
    	 }
    	 // i controlli sul genere/specie li faccio solo se la tipologia produttiva non e' ALTRO e se non è Torba/Terriccio
    	 if (form.getIdTipologiaProdImport() != null && form.getIdTipologiaProdImport() != CaronteConstants.ID_VOCE_ALTRO_TIPOLOG_IMPORT && form.getIdTipologiaProdImport() != CaronteConstants.ID_VOCE_TORBA_TERRICCIO_IMPORT) {
	    	 if (form.getGenereImport() == null) {
	    		 logger.debug("-- Errore Genere TP");
	    		 errors.rejectValue("idTipologiaProdImport", "error.tipologiaProduttiva.almenounaGenere");
	    		 error = "Indicare almeno una Tipologia produttiva e un Genere";
	    	 } else if (form.getSpecieImport() == null || form.getSpecieImport().length == 0) {
	    		 // La specie è obbligatoria se : il genere non è di tipo famiglia    		
	    		 if (!decodificheEJB.isTipoGenereFamiglia(form.getGenereImport())) {
	    			 logger.debug("-- Errore sepcie TP");
	    			 errors.rejectValue("idTipologiaProdImport", "error.tipologiaProduttiva.almenouna");
	    			 error = "Indicare almeno una Specie per la Tipologia produttiva";
	    		 }
	    	 }
    	 }
    	 return error;
     }
     
     
     // Validazione dati Tab import
     public void validateDatiImport(NuovaDomandaForm form, Errors errors, List<TipologiaProdSpecieDTO> tipologieProdDb) throws BusinessException {	  
    	 logger.debug("validateDatiImport");
    	 
    	 // Tipologia produttiva
    	 if(tipologieProdDb == null || tipologieProdDb.size()==0){
    	   errors.rejectValue("idTipologiaProdImport", "error.tipologiaProduttiva.almenouna"); 
    	 }
    	 // Zone protette
    	 if(form.getIdVoceRadioZonaProtetta() == null || form.getIdVoceRadioZonaProtetta().length == 0){
    	   errors.rejectValue("idVoceRadioZonaProtetta", "error.voce.almenouna"); 
    	 }
    	 // Continenti
    	 if(form.getIdVoceCheckContinenti() == null || form.getIdVoceCheckContinenti().length == 0){
    	   errors.rejectValue("idVoceCheckContinenti", "error.idVoceCheckContinenti.almenouno");
    	 }    	     	     	
     }
     
     
     public void validateAttivitaTabAzienda(ModaliForm form, Errors errors) throws BusinessException {
    	 logger.debug("BEGIN validateAttivitaTabAzienda");
    	 // Controllo se è stata selezionata un'attività
    	 if (form.getIdTipoAttivitaMat() == null){    		 
    		 errors.rejectValue("idTipoAttivitaMat", "error.tipologiaAttivita.almenouna");
    	 }
    	 if (form.getIdTipoAttivitaMat() == CaronteConstants.ID_TIPO_ATTIVITA_ALTRO &&
    			 form.getNote() == null) {    		 
    		 errors.rejectValue("note", "error.note.almenouna");
    	 }
     }
     
     public void validateMaterialeTabAzienda(ModaliForm form, Errors errors) throws BusinessException {
    	 logger.debug("BEGIN validateMaterialeTabAzienda");
    	 
    	 // Controllo se è stata selezionato un materiale
    	 if (form.getIdAttivitaMaterialeUtente() != null) {
    		 if (form.getIdMateriale() == null)  {
    			 logger.debug("-- modifica: Nessun materiale aggiunto");
    			 errors.rejectValue("idTipoAttivitaMat", "error.materiale.almenouno");
    		 }
    	 } else if ( form.getIdMaterialeList() == null || form.getIdMaterialeList().length == 0){ 
    		 logger.debug("-- aggiungi: Nessun materiale aggiunto");
    		 errors.rejectValue("idTipoAttivitaMat", "error.materiale.almenouno");
    	 }
     }
     
     private void validateResponsabileFitosanitarioPassaporto(NuovaDomandaForm form, Errors errors) throws BusinessException {
    	 logger.debug("BEGIN validateResponsabileFitosanitarioPassaporto");
    	 /* Se è stato selezionato il check 'Il Responsabile per la comunicazine con 
    	 	il Servizio Fitosanitario regionale è diverso dal Rappresentante legale' : sono obbligatori i dati anagrafici del responsabile
    	 */
    	 if(form.getCheckRespFito() != null && !form.getCheckRespFito().isEmpty()){
    		 logger.debug("-- E' stato selezionato il check, controllo che siano stati inseriti i dati del Responsabile");    		 
    		 validateMandatoryAndMaxLength(form.getCognomeRespPass(), "cognomeRespPass", 50, errors);
    		 validateMandatoryAndMaxLength(form.getNomeRespPass(), "nomeRespPass", 50, errors);
    		 validateMandatoryAndMaxLength(form.getCodFiscaleRespPass(), "codFiscaleRespPass", 16, errors);
    		 
    		 // Controllo formale sul codice fiscale
    		 if (form.getCodFiscaleRespPass() != null && !form.getCodFiscaleRespPass().isEmpty()) {
    		      if (form.getCodFiscaleRespPass().length() != 16) {
    		        errors.rejectValue("codFiscaleRespPass", "error.codiceFiscaleLength");
    		      }     		       
    		      else{
    		    	  if (!CaronteUtils.isCodiceFiscale(form.getCodFiscaleRespPass())) {
    		    		  errors.rejectValue("codFiscaleRespPass", "error.cf.invalid");
        		      }     		          		    	  
    		      }    		        
    		 }
    		 
    		 validateMandatoryAndMaxLength(form.getEmailRespPass(), "emailRespPass", 100, errors);
    		 validateMandatoryAndMaxLength(form.getQualificaProfRespPass(), "qualificaProfRespPass", 200, errors);
    		 
    	 }
     }
     
     
    // Controllo che sia stata selezionata almeno una tipologia import
    public String validateTipologiaProdExport(NuovaDomandaForm form, Errors errors) throws BusinessException {
    	 logger.debug("validateTipologiaProdExport");
    	 
    	 String error = "";
    	 if(form.getIdTipologiaProdExp() != null && form.getIdTipologiaProdExp() == CaronteConstants.ID_VOCE_ALTRO_TIPOLOG_EXPORT){
     		logger.debug("-- Controllare che siano state compilate le Note");
     		if(form.getNoteTipologiaExport() == null || form.getNoteTipologiaExport().trim().length() == 0){
     			errors.rejectValue("idTipologiaProdExp", "error.noteTipologProd.compilaNote");
     			error = "Specificare la Tipologia produttiva nel campo Note";
     		}
     	}
    	 
    	 logger.debug("-- isTipoGenereFamiglia = " +decodificheEJB.isTipoGenereFamiglia(form.getGenereExport()));
    	 // i controlli sul genere/specie li faccio solo se la tipologia produttiva non e' ALTRO e se non è Torba/Terriccio
    	 if (form.getIdTipologiaProdExp() != null && form.getIdTipologiaProdExp() != CaronteConstants.ID_VOCE_ALTRO_TIPOLOG_EXPORT && form.getIdTipologiaProdExp() != CaronteConstants.ID_VOCE_TORBA_TERRICCIO_EXPORT) {
	    	 if (form.getGenereExport() == null ) {
	    		 logger.debug("-- Errore Genere TP");
	    		 errors.rejectValue("idTipologiaProdExp", "error.tipologiaProduttiva.almenounaGenere");
	    		 error = "Indicare almeno una Tipologia produttiva e un Genere"; 
			 } 
	    	 else if (form.getSpecieExport() == null || form.getSpecieExport().length == 0 ){
	    		 // La specie è obbligatoria se : il genere non è di tipo famiglia
				 if (!decodificheEJB.isTipoGenereFamiglia(form.getGenereExport())) {
					 logger.debug("-- Errore sepcie TP Export");
		    		 errors.rejectValue("idTipologiaProdExp", "error.tipologiaProduttiva.almenouna");
		    		 error = "Indicare almeno una Specie per la Tipologia produttiva";
		     	 }
	    	 }
    	 }
    	 return error;
    }
    
    // Validazione dati Tab export
    public void validateDatiExport(NuovaDomandaForm form, Errors errors, List<TipologiaProdSpecieDTO> tipologieProdDb) throws BusinessException {	  
   	 logger.debug("BEGIN validateDatiExport");
   	 
   	 // Cosa riguarda l'esportazione
   	 if(form.getIdVoceCheckTipExp() == null || form.getIdVoceCheckTipExp().length == 0){
   		errors.rejectValue("idVoceCheckTipExp", "error.voceCheckTipExp.almenouna");
   	 }
   	 
     // Continenti
   	 if(form.getIdVoceCheckContinentiExp() == null || form.getIdVoceCheckContinentiExp().length == 0){
   	   errors.rejectValue("idVoceCheckContinentiExp", "error.idVoceCheckContinentiExp.almenouno");
   	 } 
   	 
   	 // Tipologia
   	 if(tipologieProdDb == null || tipologieProdDb.size()==0){
   	   errors.rejectValue("idTipologiaProdExp", "error.tipologiaProduttivaExp.almenouna"); 
   	 }   	    	     	     	
    }

    /*
     *  Controllo :
     *   - che sia stata selezionata almeno una Specie, se ne sono previste per il genere selezionato
     *   - che sia stato compilato il campo 'Note', se nella Tipologia è stato selezionata la voce 'Altro'
     */
    public String validateTipologiaProdCentroAz(NuovaDomandaForm form, Errors errors) throws BusinessException {
    	logger.debug("validateTipologiaProdCentroAz");
    	String error = "";
    	
    	if(form.getIdTipologiaProd() != null && form.getIdTipologiaProd() == CaronteConstants.ID_VOCE_ALTRO_TIPOLOG_CENTRI_AZ){
    		logger.debug("-- Controllare che siano state compilate le Note");
    		if(form.getNoteTipologiaCentroAz() == null || form.getNoteTipologiaCentroAz().trim().length() == 0){
    			errors.rejectValue("noteTipologiaCentroAz", "error.noteTipologProd.compilaNote");
    			error = "Specificare la Tipologia produttiva nel campo Note";
    		}
    	}
    	 	
    	/*if (form.getGenere() == null) {
    		logger.debug("-- Errore Genere TP");
    		errors.rejectValue("idTipologiaProd", "error.tipologiaProduttiva.almenounaGenere");
    		error = "Indicare almeno una tipologia produttiva e un genere";
    	} */
    	//else{
    	/*if (form.getIdTipologiaProd() != null && form.getIdTipologiaProd() != CaronteConstants.ID_VOCE_ALTRO_TIPOLOG_CENTRI_AZ) {
	    	if(form.getGenere() != null){
	    		logger.debug("-- E' stato selezionato un genere");
	    		if (form.getSpecie() == null || form.getSpecie().length == 0) {
	    			logger.debug("-- Non è stata selezionata la specie");
	    			boolean isTipoGenereFamiglia = decodificheEJB.isTipoGenereFamiglia(form.getGenere());
	    			logger.debug("-- isTipoGenereFamiglia = " +isTipoGenereFamiglia);   
		    		// non è stato selezionato una famiglia di generi e quindi diamo errore    		
		    		if (!isTipoGenereFamiglia) {
		    			logger.debug("-- Errore sepcie TP");
		    			errors.rejectValue("idTipologiaProd", "error.tipologiaProduttiva.almenouna");
		    			error = "Indicare almeno una Specie per la Tipologia produttiva";
		    		}
	    		}
	    	}	
    	}*/
    	return error;
    }
    
	public void validateDatiGestione(NuovaDomandaForm form, Errors errors) throws BusinessException {

		logger.debug("BEGIN validateDatiGestione");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idIspettore",
		// "error.required");
		
		// if (form.getTariffa() != null){
		// validateNum(form.getTariffa().toString(), "tariffa", errors);
		// validateDecimal(form.getTariffa().toString(), "tariffa", new
		// Integer(10), new Integer(2), errors);

		// }
		
		// Codice ruop
		ValidationUtils.rejectIfEmpty(errors, "codiceRuop", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataRegistrazioneRuop", "error.required");
		if (form.getCodiceRuop() != null) {
			logger.debug("-- Controllo che il codice ruop abbia un formato corretto");
			if (!CaronteUtils.isCodiceRuop(form.getCodiceRuop())) {
				logger.debug("-- Il codice ruop non ha un formato corretto");
				errors.rejectValue("codiceRuop", "error.codiceruop.invalid");
			}
			logger.debug("-- Controllo che il codice ruop non sia già presente");
			logger.debug("form.getIdSpedizioniere()= " + form.getIdSpedizioniere());
			if (domandeEJB.isRuopValido(form.getCodiceRuop().toUpperCase(), form.getIdSpedizioniere())){
				logger.debug("-- Il codice ruop inserito esiste già");
				errors.rejectValue("codiceRuop", "error.codiceruop.duplicate");
			}
		}
		logger.debug("END validateDatiGestione");
	}

	public void validateDatiCentroAz(NuovaDomandaForm form, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idProvCentroAz", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idComuneCentroAz", "error.required");
		 validateMandatoryAndMaxLength(form.getCapCentroAz(), "capCentroAz", 5, errors);
		 validateMandatoryAndMaxLength(form.getIndirizzoCentroAz(), "indirizzoCentroAz", 200, errors);	
	}

    
}
