package it.aizoon.ersaf.validator;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.util.List;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IOrganismiNociviEJB;
import it.aizoon.ersaf.business.impl.OrganismiNociviEJB;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.GenereSpecieOrgNocivoDTO;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDZonaProtettaExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaDomandaForm;
import it.aizoon.ersaf.form.NuovoOrganismoNocivoForm;
import it.aizoon.ersaf.integration.mybatis.mapper.OrganismiNociviMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDZonaProtettaMapper;

@Component
public class OrganismiNociviValidator extends BaseValidator {
	
	@Autowired
	private IOrganismiNociviEJB organismiNociviEJB = null;
	

	protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".validator");

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> paramClass) {
		return NuovaDomandaForm.class.equals(paramClass);
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

	public void validateDatiNuovoOrgNocivo(NuovoOrganismoNocivoForm form, Errors errors) throws BusinessException{
		logger.debug("BEGIN validateDatiNuovoOrgNocivo");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idTipoOrgNocivo", "Compilare tutti i campi obbligatori");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idTipoProdotto", "Compilare tutti i campi obbligatori");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idGenere", "Compilare tutti i campi obbligatori");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nuovoCodiceEppo", "Compilare tutti i campi obbligatori");
		validateMaxLength(form.getNuovoCodiceEppo(), 40, "nuovoCodiceEppo", errors);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizioneOrganismoNocivo", "Compilare tutti i campi obbligatori");
		validateMaxLength(form.getDescrizioneOrganismoNocivo(), 40, "descrizioneOrganismoNocivo", errors);		
				
		// Controllo se su car_d_zona_protetta ci sono già gli id selezionati
		logger.debug("-- Controllo se su car_d_zona_protetta ci sono già gli id selezionati");
		if(form.getIdTipoOrgNocivo() != null && form.getIdTipoProdotto() != null && form.getIdGenere() != null && form.getNuovoCodiceEppo() != null && form.getDescrizioneOrganismoNocivo() != null){
		  if(organismiNociviEJB.getZonaProtettaByNuovoONForm(form) != null){
			errors.reject("Organismo Nocivo già presente a sistema : controllare Tipo organismo nocivo, Tipo prodotto e Genere indicati");
		  }
		  else{
			// Controllo se su car_d_org_nocivo non ci sia già un record con stesso codice eppo e descr org noc
			  logger.debug("-- Controllo se su car_d_org_nocivo non ci sia già un record con stesso codice eppo e descr org noc");
			  if(organismiNociviEJB.getOrganismoNocivoByCodiceEppoDescrOrgNoc(form.getNuovoCodiceEppo().trim(), form.getDescrizioneOrganismoNocivo().trim()) != null){
				  errors.reject("Organismo Nocivo già presente a sistema con lo stesso Codice Eppo e Descrizione");
			  }  
		  }			
		}
		
		
		logger.debug("END validateDatiNuovoOrgNocivo");
	}
	
	public void validateAggiungiGenereSpecie(NuovoOrganismoNocivoForm form, Errors errors) throws BusinessException{
		logger.debug("BEGIN validateDatiAggiungiGenereSpecie");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nuovoCodiceEppo", "Compilare tutti i campi obbligatori");
		validateMaxLength(form.getNuovoCodiceEppo(), 40, "nuovoCodiceEppo", errors);		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idTipoOrgNocivo", "Compilare tutti i campi obbligatori");		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizioneOrganismoNocivo","Compilare tutti i campi obbligatori");
		validateMaxLength(form.getDescrizioneOrganismoNocivo(), 40, "descrizioneOrganismoNocivo", errors);		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idTipoProdotto", "Compilare tutti i campi obbligatori");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idGenere", "Compilare tutti i campi obbligatori");		
		
		// Controllo se su car_d_zona_protetta ci sono già gli id selezionati
		logger.debug("-- Controllo se su car_d_zona_protetta ci sono già gli id selezionati");
		if (form.getIdTipoOrgNocivo() != null && form.getIdTipoProdotto() != null && form.getIdGenere() != null
				&& form.getNuovoCodiceEppo() != null && form.getDescrizioneOrganismoNocivo() != null) {
			if (organismiNociviEJB.getZonaProtettaByNuovoONForm(form) != null) {
				errors.reject("Organismo Nocivo già presente a sistema: controllare Tipo organismo nocivo, Tipo prodotto e Genere indicati");
			}
		}	
		
		logger.debug("END validateDatiAggiungiGenereSpecie");
	}
	
	
	public void validateModificaNuovoOrgNocivo(NuovoOrganismoNocivoForm form, Errors errors) throws BusinessException {
		logger.debug("BEGIN validateDatiNuovoOrgNocivo");

		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nuovoCodiceEppo", "Compilare tutti i campi obbligatori");
		validateMaxLength(form.getNuovoCodiceEppo(), 40, "nuovoCodiceEppo", errors);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizioneOrganismoNocivo","Compilare tutti i campi obbligatori");
		validateMaxLength(form.getDescrizioneOrganismoNocivo(), 40, "descrizioneOrganismoNocivo", errors);

		logger.debug("END validateDatiNuovoOrgNocivo");
	}

}
