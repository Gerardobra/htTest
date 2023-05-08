package it.aizoon.ersaf.validator;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.dto.SementeDTO;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovoControlloForm;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

@Component
public class ControlloValidator extends BaseValidator {
	
	@Autowired
	private IDecodificheEJB decodificheEJB = null;

	protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".validator");
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> paramClass) {
		return NuovoControlloForm.class.equals(paramClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	}

	public void validateDatiAzienda(NuovoControlloForm form, Errors errors) {

		validateMandatoryAndMaxLength(form.getEmail(), "email", 100, errors);
		validateMandatoryAndMaxLength(form.getPec(), "pec", 100, errors);


		if(form.getTelefono() != null){
			validateMaxLength(form.getTelefono(), 40, "telefono", errors);
			validateNumeric(form.getTelefono(),"telefono", errors);
		}
		if(form.getCellulare() != null){
			validateMaxLength(form.getCellulare(), 30, "cellulare", errors);
			validateNumeric(form.getCellulare(),"cellulare", errors);
		}
		
	}

	public void validateDatiBaseControllo(NuovoControlloForm nuovoControlloForm, BindingResult bindResult) {

		if (nuovoControlloForm.getIdsIspettore() == null || nuovoControlloForm.getIdsIspettore().length == 0) {
			bindResult.rejectValue("idsIspettore", "nuovoControlloForm.idsIspettore",
					"Selezionare almeno un ispettore.");
		}

		if (nuovoControlloForm.getIdsNormaVerbale() == null || nuovoControlloForm.getIdsNormaVerbale().length == 0) {

			if (nuovoControlloForm.getAltreNorme() == null || "".equals(nuovoControlloForm.getAltreNorme())) {
				bindResult.rejectValue("idsNormaVerbale", "nuovoControlloForm.idsNormaVerbale",
						"Selezionare almeno una norma.");
			} 

		}

		if (nuovoControlloForm.getIdsTipologiaControllo() == null
				|| nuovoControlloForm.getIdsTipologiaControllo().length == 0) {
			if (nuovoControlloForm.getAltroControllo() == null || "".equals(nuovoControlloForm.getAltroControllo())) {
				bindResult.rejectValue("idsTipologiaControllo", "nuovoControlloForm.idsTipologiaControllo",
						"Selezionare almeno un controllo.");
			}
		}

		
		boolean hasSementi = false;
		int i = 0;
		if(nuovoControlloForm.getSementi()!=null)
			for (SementeDTO semente : nuovoControlloForm.getSementi()) {
	
				if (semente.getIdTipologiaSemente() != null) {
					hasSementi = true;
					if (semente.getQuantita() == null) {
						bindResult.rejectValue("sementi[" + i + "].quantita",
								"nuovoControlloForm.sementi[" + i + "].quantita",
								"Quantita obbligatoria se la semente è selezionata.");
					} else if (semente.getQuantita().compareTo(BigDecimal.ZERO) <= 0) {
						bindResult.rejectValue("sementi[" + i + "].quantita",
								"nuovoControlloForm.sementi[" + i + "].quantita",
								"Il valore deve essere maggiore di zero.");
					}
	
					if (semente.getIdTipologiaSemente() == 14) // Altra specie, necessario aggiungere il campo note
					{
						if (semente.getNote() == null) {
							bindResult.rejectValue("sementi[" + i + "].note", "nuovoControlloForm.sementi[" + i + "].note",
									"Specificare semente.");
						}
					}
				}
				i++;
			}
			// se hanno selezionato un'attivita sementiera allora devono anche specificare un semente lavorato
			if (!hasSementi && nuovoControlloForm.getIdsMaterialeSementi().length > 0) {
				if(nuovoControlloForm.getSementi()!=null)
				for (int j = 0; j<nuovoControlloForm.getSementi().size();j++) {
					bindResult.rejectValue("sementi[" + j + "].idTipologiaSemente",
							"nuovoControlloForm.sementi[" + j + "].idTipologiaSemente", "Selezionare almeno una semente.");
				}
	
			}
		
	
		/*if (nuovoControlloForm.getIdsTipologiaSementi().length > 0) {
			hasSementi = true;
			for(int i=0; i < nuovoControlloForm.getIdsTipologiaSementi().length;i++) {
				if (nuovoControlloForm.getQuantitaSementi().length == 0) {
					bindResult.rejectValue("quantitaSementi", "nuovoControlloForm.quantitaSementi",
							"Quantita obbligatoria se la semente è selezionata.");
				} else {
					if (nuovoControlloForm.getQuantitaSementi()[i] != null && nuovoControlloForm.getQuantitaSementi()[i].compareTo(BigDecimal.ZERO) <= 0) {
						bindResult.rejectValue("quantitaSementi","nuovoControlloForm.quantitaSementi",
								"Il valore deve essere maggiore di zero.");
					}
				}
			}
		}
		
		if (!hasSementi && nuovoControlloForm.getIdsMaterialeSementi().length > 0) {
			bindResult.rejectValue("idsTipologiaSementi", "nuovoControlloForm.idsTipologiaSementi",
					"Selezionare almeno una semente.");
		}*/
		
		
			

		if (!bindResult.hasErrors()) {
			String oraInizio = nuovoControlloForm.getOraInizioControllo();
			String oraFine = nuovoControlloForm.getOraFineControllo();
			if(oraFine != null && !oraFine.isEmpty()){
				LocalTime inizio = LocalTime.parse(oraInizio);
				LocalTime fine = LocalTime.parse(oraFine);
				if (inizio.until(fine, ChronoUnit.MINUTES) <= 0) {
					bindResult.rejectValue("oraFineControllo", "nuovoControlloForm.oraFineControllo",
							"L'ora fine deve essere successiva all'ora inizio.");
	
				}
			}

		}
	}

	public void validateDatiCentroAziendale(NuovoControlloForm form, Errors errors) {
		
		if(form.getTelefonoCa() != null){
			validateMaxLength(form.getTelefonoCa(), 40, "telefonoCa", errors);
			validateNumeric(form.getTelefonoCa(),"telefonoCa", errors);
		}
		if(form.getCellulareCa() != null){
			validateMaxLength(form.getCellulareCa(), 30, "cellulareCa", errors);
			validateNumeric(form.getCellulareCa(),"cellulareCa", errors);
		}
	}
	
	public void validateDatiControlloFisico(NuovoControlloForm nuovoControlloForm, BindingResult bindResult) {

		/*
		if(nuovoControlloForm.getFlControllo5x1()==null)
		{
			bindResult.rejectValue("flControllo5x1", "nuovoControlloForm.flControllo5x1", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x2()==null)
		{
			bindResult.rejectValue("flControllo5x2", "nuovoControlloForm.flControllo5x2", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x4()==null)
		{
			bindResult.rejectValue("flControllo5x4", "nuovoControlloForm.flControllo5x4", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x5()==null)
		{
			bindResult.rejectValue("flControllo5x5", "nuovoControlloForm.flControllo5x5", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x6()==null)
		{
			bindResult.rejectValue("flControllo5x6", "nuovoControlloForm.flControllo5x6", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x7()==null)
		{
			bindResult.rejectValue("flControllo5x7", "nuovoControlloForm.flControllo5x7", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x8()==null)
		{
			bindResult.rejectValue("flControllo5x8", "nuovoControlloForm.flControllo5x8", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x9()==null)
		{
			bindResult.rejectValue("flControllo5x9", "nuovoControlloForm.flControllo5x9", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x10()==null)
		{
			bindResult.rejectValue("flControllo5x10", "nuovoControlloForm.flControllo5x10", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x11()==null)
		{
			bindResult.rejectValue("flControllo5x11", "nuovoControlloForm.flControllo5x11", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x12()==null)
		{
			bindResult.rejectValue("flControllo5x12", "nuovoControlloForm.flControllo5x12", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x13()==null)
		{
			bindResult.rejectValue("flControllo5x13", "nuovoControlloForm.flControllo5x13", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x14()==null)
		{
			bindResult.rejectValue("flControllo5x14", "nuovoControlloForm.flControllo5x14", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x15()==null)
		{
			bindResult.rejectValue("flControllo5x15", "nuovoControlloForm.flControllo5x15", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x16()==null)
		{
			bindResult.rejectValue("flControllo5x16", "nuovoControlloForm.flControllo5x16", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x17()==null)
		{
			bindResult.rejectValue("flControllo5x17", "nuovoControlloForm.flControllo5x17", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x18()==null)
		{
			bindResult.rejectValue("flControllo5x18", "nuovoControlloForm.flControllo5x18", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x19()==null)
		{
			bindResult.rejectValue("flControllo5x19", "nuovoControlloForm.flControllo5x19", "Selezionare almeno un'opzione.");
		}
		//if(nuovoControlloForm.getSementi() != null && nuovoControlloForm.getSementi().size() > 0 && nuovoControlloForm.getFlControllo5x20()==null )
		if(nuovoControlloForm.getIdsTipologiaSementi().length > 0 && nuovoControlloForm.getFlControllo5x20()==null )
		{
			bindResult.rejectValue("flControllo5x20", "nuovoControlloForm.flControllo5x20", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x21()==null)
		{
			bindResult.rejectValue("flControllo5x21", "nuovoControlloForm.flControllo5x21", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x22()==null)
		{
			bindResult.rejectValue("flControllo5x22", "nuovoControlloForm.flControllo5x22", "Selezionare almeno un'opzione.");
		}
		if(nuovoControlloForm.getFlControllo5x23()==null)
		{
			bindResult.rejectValue("flControllo5x23", "nuovoControlloForm.flControllo5x23", "Selezionare almeno un'opzione.");
		}
		
		if(nuovoControlloForm.getFlControllo5x11()!=null && "S".equals(nuovoControlloForm.getFlControllo5x11()))
		{
			if(nuovoControlloForm.getNoteControllo5x11()==null)
				bindResult.rejectValue("noteControllo5x11", "nuovoControlloForm.noteControllo5x11", "Campo obbligatorio se la risposta è SI.");
		}
		if(nuovoControlloForm.getFlControllo5x22()!=null && "N".equals(nuovoControlloForm.getFlControllo5x22()))
		{
			if(nuovoControlloForm.getNoteControllo5x22()==null)
				bindResult.rejectValue("noteControllo5x22", "nuovoControlloForm.noteControllo5x22", "Campo obbligatorio se la risposta è No.");
		}
		
		
		if("S".equals(nuovoControlloForm.getFlControllo5x1())){
			if (nuovoControlloForm.getIdsStrutturaAttrezzatura() == null || nuovoControlloForm.getIdsStrutturaAttrezzatura().length == 0) {
				bindResult.rejectValue("idsStrutturaAttrezzatura", "nuovoControlloForm.idsStrutturaAttrezzatura",
						"Selezionare almeno una struttura o un'attrezzatura.");
			}
		}
		if (nuovoControlloForm.getIdsMetodiProduzione() == null || nuovoControlloForm.getIdsMetodiProduzione().length == 0) {
			bindResult.rejectValue("idsMetodiProduzione", "nuovoControlloForm.idsMetodiProduzione",
					"Selezionare almeno un metodo di produzione");
		}
		
		
		if (nuovoControlloForm.getIdsTipiIrrigazione() == null || nuovoControlloForm.getIdsTipiIrrigazione().length == 0) {
			bindResult.rejectValue("idsTipiIrrigazione", "nuovoControlloForm.idsTipiIrrigazione",
					"Selezionare almeno un tipo di irrigazione");
		}
		
	
		
		if(!"N".equals(nuovoControlloForm.getFlControllo5x20()) && nuovoControlloForm.getFlControllo5x20() != null){
			if (nuovoControlloForm.getIdsConoscenzeProfessionali() == null || nuovoControlloForm.getIdsConoscenzeProfessionali().length == 0) {
				bindResult.rejectValue("idsConoscenzeProfessionali", "nuovoControlloForm.idsConoscenzeProfessionali",
						"Selezionare almeno una conoscenza professionale");
			}
		}
		if(!"N".equals(nuovoControlloForm.getFlControllo5x21()))
		if (nuovoControlloForm.getIdsRequisitiProfessionali() == null || nuovoControlloForm.getIdsRequisitiProfessionali().length == 0) {
			bindResult.rejectValue("idsRequisitiProfessionali", "nuovoControlloForm.idsRequisitiProfessionali",
					"Selezionare almeno un requisito professionale");
		}
		else
		{
			for(String req : nuovoControlloForm.getIdsRequisitiProfessionali())
			{
				if(new Long(req)==1l)
				{
					if(nuovoControlloForm.getLaurea()==null && nuovoControlloForm.getDiploma()==null)
					{
						bindResult.rejectValue("laurea", "nuovoControlloForm.laurea", "Inserire laurea o diploma");
						bindResult.rejectValue("diploma", "nuovoControlloForm.diploma", "Inserire laurea o diploma");
					}				
				}
			}
		}
		if(nuovoControlloForm.getNumeroPiante() != null){
			for (int i = 0; i < nuovoControlloForm.getNumeroPiante().length; i++) {
				if(nuovoControlloForm.getNumeroPiante()[i] != null && !nuovoControlloForm.getNumeroPiante()[i].equals("")){
					if(!NumberUtils.isNumber(nuovoControlloForm.getNumeroPiante()[i])){
						bindResult.rejectValue("numeroPiante", "nuovoControlloForm.numeroPiante", "Inserire un valore numerico nel campo numero piante");
					}
				}				
			}
		}
		
	*/
		if(nuovoControlloForm.getFlControllo5x11() != null){
			if (nuovoControlloForm.getFlControllo5x11().toUpperCase().equals("S") && nuovoControlloForm.getOrgNoc().length == 0){
				bindResult.rejectValue("orgNoc", "nuovoControlloForm.orgNoc", "Selezionare almeno un organismo nocivo.");
			}
		}
	}
	
	public void validateAggiungiSitoProdControlloIdentita(NuovoControlloForm form, Errors errors) {
		logger.debug("BEGIN validateAggiungiSitoProdControlloIdentita");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaSitoProd", "error.required");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneSitoProd", "error.required");
	    validateMandatoryAndInteger(form.getFoglio(), "foglio", 0, 9999, errors);
	    validateMandatoryAndInteger(form.getMappale(), "mappale", 0, 99999999, errors);
	    validateMandatoryAndInteger(form.getSuperficie(), "superficie", 0, 999999999, errors);
	    
	    logger.debug("END validateAggiungiSitoProdControlloIdentita");
	}

	public void validateEsito(NuovoControlloForm nuovoControlloForm, BindingResult bindResult) {
		logger.debug("BEGIN validateEsito");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindResult, "dataChiusuraVerbale", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindResult, "cognomeResp", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindResult, "nomeResp", "error.required");

		logger.debug("-- idVersioneControllo ="+nuovoControlloForm.getIdVersioneControllo());
		if(nuovoControlloForm.getIdVersioneControllo() == CaronteConstants.ID_VERSIONE_CONTROLLO_BEGIN){
			if(nuovoControlloForm.getFlEsito()==null)
			{
				bindResult.rejectValue("flEsito", "nuovoControlloForm.flEsito", "Selezionare almeno un'opzione.");
			}
			if(nuovoControlloForm.getFlEsitoPassaporto()==null)
			{
				bindResult.rejectValue("flEsitoPassaporto", "nuovoControlloForm.flEsitoPassaporto", "Selezionare almeno un'opzione.");
			}
			if(nuovoControlloForm.getFlIrregolarita()==null)
			{
				bindResult.rejectValue("flIrregolarita", "nuovoControlloForm.flIrregolarita", "Selezionare almeno un'opzione.");
			}
		}
		else{
			if(nuovoControlloForm.getIdVersioneControllo() == CaronteConstants.ID_VERSIONE_CONTROLLO_04_2021){
				if(nuovoControlloForm.getFlIrregolarita()==null){
					bindResult.rejectValue("flIrregolarita", "nuovoControlloForm.flIrregolarita", "Selezionare almeno un'opzione.");
				}
				if(nuovoControlloForm.getFlEsito()==null){
					bindResult.rejectValue("flEsito", "nuovoControlloForm.flEsito", "Selezionare almeno un'opzione.");
				}
			}
		}
		logger.debug("END validateEsito");
	}
	
	public void validateDatiCampione(NuovoControlloForm form, Errors errors) {
		logger.debug("BEGIN validateDatiCampione");		
		if(form.getTempoImpiegato() != null){		   		   				  
		   /* Se è passata la validazione sopra e c'è la parte decimale : 
		        - controllare che la cifra dopo il punto o la virgola non sia  > 59 (deve indicare i minuti)
		   */       		   
		   if(validateDecimal(form.getTempoImpiegato().trim(), 2, 2, "tempoImpiegato", errors)) {
			   logger.debug("-- tempoImpiegato è un numero");			   
			   // c'è la parte decimale
			   String tempoImpiegatoStr = form.getTempoImpiegato().replace(',', '.');
			   logger.debug("--  tempoImpiegatoStr ="+tempoImpiegatoStr);
			   if(tempoImpiegatoStr.indexOf(".") != -1){
				 logger.debug("-- tempoImpiegato ha la parte decimale");
				 logger.debug("-- Controllo su formato tempoImpiegato");
			     String numDecimale = tempoImpiegatoStr.substring(tempoImpiegatoStr.indexOf(".")+1, tempoImpiegatoStr.length());
			     logger.debug("-- numDecimale ="+numDecimale);
				 Long num = new Long(numDecimale);
				 if(num.longValue()>59){
				   errors.rejectValue("tempoImpiegato", "nuovoControlloForm.tempoImpiegato", "Il numero decimale non può essere maggiore di 59");
				 }
			   }
		   }   		   		   
		}
		
		/*if(form.getIdGenereCampione() == null){
			errors.rejectValue("genereCampione", "nuovoControlloForm.idGenereCampione", "Campo obbligatorio");
		}*/
		if(form.getCodCampioneDal() == null){
			errors.rejectValue("codCampioneDal", "nuovoControlloForm.codCampioneDal", "Campo obbligatorio");
		}
		if(form.getTipoCampione() == null){
			errors.rejectValue("tipoCampione", "nuovoControlloForm.tipoCampione", "Campo obbligatorio");
		}
		if(form.getOrgNocDaRicercare() == null || form.getOrgNocDaRicercare().length == 0){
			errors.rejectValue("orgNocDaRicercare", "nuovoControlloForm.orgNocDaRicercare", "Campo obbligatorio");
		}
		if(form.getTipologiaCampione() == null){
			errors.rejectValue("tipologiaCampione", "nuovoControlloForm.tipologiaCampione", "Campo obbligatorio");
		}
		if(form.getFlCofinanziato() == null){
			errors.rejectValue("flCofinanziato", "nuovoControlloForm.flCofinanziato", "Campo obbligatorio");
		}
		
		logger.debug("END validateDatiCampione");
	}

	public void validateDatiMisura(NuovoControlloForm nuovoControlloForm, Errors errors) {
		
		Date oggi = new Date();
		Date nascita = nuovoControlloForm.getDataNascitaCustode();
		Date documento = nuovoControlloForm.getDataEmissioneDocumento();
		
		//Data nascita custode
		if(nascita.compareTo(oggi) == 1){
			logger.debug("entrato if 1");
			   errors.rejectValue("dataNascitaCustode", "nuovoControlloForm.dataNascitaCustode","La data di nascita non può essere successiva alla data odierna.");
		}
		
		//Data emissione documento
		if(documento.compareTo(oggi) == 1){
			logger.debug("entrato if 2");
			   errors.rejectValue("dataEmissioneDocumento", "nuovoControlloForm.dataEmissioneDocumento","La data di emissione non può essere successiva alla data odierna.");
		}
		
	}	
	
	public void validateSpecieControlloFisico(NuovoControlloForm form, Errors errors) throws BusinessException{
		logger.debug("BEGIN validateSpecieControlloFisico");
		boolean isTipoGenereFamiglia = false;		
		
		if (form.getGenereFisico() != null) {
		  logger.debug("-- genere selezionato ="+form.getGenereFisico());
		  isTipoGenereFamiglia = decodificheEJB.isTipoGenereFamiglia(form.getGenereFisico());
		  logger.debug("-- isTipoGenereFamiglia ="+isTipoGenereFamiglia);
		  if(!isTipoGenereFamiglia){
					if (form.getSpecieFisico() == null || form.getSpecieFisico().length == 0) {
						logger.debug("-- Selezionare almeno una specie");
						errors.rejectValue("specieFisico", "error.specieSel.almenoUna");						
					}
				}			
		  }   

		logger.debug("END validateSpecieControlloFisico");
	}
	

}
