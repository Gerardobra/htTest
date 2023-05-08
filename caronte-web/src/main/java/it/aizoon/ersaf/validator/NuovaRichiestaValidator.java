package it.aizoon.ersaf.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.aizoon.ersaf.form.NuovaRichiestaForm;
import it.aizoon.ersaf.util.CaronteConstants;

/**
 * @author Ivan Morra
 *
 */
@Component
public class NuovaRichiestaValidator extends BaseValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> paramClass) {
		return NuovaRichiestaForm.class.equals(paramClass);
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

	public void validateDatiRichiesta(NuovaRichiestaForm form, Errors errors) {
		
		validateMaxLength(form.getNote(), 1000, "note", errors);
		
		if (CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(form.getIdTipoRichiesta())) {

			//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroCertificatoOriginale", "error.required");
			validateMandatoryAndMaxLength(form.getNumeroCertificatoOriginale(), "numeroCertificatoOriginale", 23, errors);
			
		}
		
	}

	public void validateDatiMittente(NuovaRichiestaForm form, Errors errors) {
		validateMandatoryAndMaxLength(form.getDenominazioneMittente(), "denominazioneMittente", 100, errors);

		if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(form.getIdTipoRichiesta())) {

		  validateMaxLength(form.getIndirizzoMittente(), 200, "indirizzoMittente", errors);
		  
			validateMandatoryAndMaxLength(form.getComuneMittente(), "comuneMittente", 100, errors);

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idNazioneMittente", "error.required");

		} else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(form.getIdTipoRichiesta())
				|| CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(form.getIdTipoRichiesta())) {

		  validateMandatoryAndMaxLength(form.getIndirizzoMittente(), "indirizzoMittente", 200, errors);
		  
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idProvinciaMittente", "error.required");

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idComuneMittente", "error.required");

			validateMandatoryAndMaxLength(form.getCodiceRuopMittente(), "codiceRuopMittente", 20, errors);
			
		}
	}

	public void validateDatiDestinatario(NuovaRichiestaForm form, Errors errors) {
		validateMandatoryAndMaxLength(form.getDenominazioneDestinatario(), "denominazioneDestinatario", 100, errors);

		validateMandatoryAndMaxLength(form.getIndirizzoDestinatario(), "indirizzoDestinatario", 200, errors);

		// validateMaxLength(form.getCodiceRup(), 20, "codiceRup", errors);

		if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(form.getIdTipoRichiesta())) {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idComuneIta", "error.required");

			// validateMaxLength(form.getRegioneRup(), 50, "regioneRup", errors);
		} else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(form.getIdTipoRichiesta())
				|| CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(form.getIdTipoRichiesta())) {

			validateMandatoryAndMaxLength(form.getComuneDestinatario(), "comuneDestinatario", 100, errors);

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idNazioneDestinatario", "error.required");

			// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idStatoRupDestinatario", "error.required");
		}
	}

	public void validateDatiTrasporto(NuovaRichiestaForm form, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idModoTrasporto", "error.required");
		
		// validateMandatoryAndMaxLength(form.getDimensioneSpedizione(),
		// "dimensioneSpedizione", 50, errors);
		
		if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(form.getIdTipoRichiesta())) {
		  
		  validateMandatoryAndMaxLength(form.getNumDocumentoTrasporto(), "numDocumentoTrasporto", 30, errors);
		  
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPuntoEntrataDichiarato", "error.required");
		  
			if (form.isSpedizioneMultipla()) {
				validateMandatoryAndInteger(form.getNumCertificatiCollegati(), "numCertificatiCollegati", 2, 20,
						errors);
			}

		} else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(form.getIdTipoRichiesta())
				|| CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(form.getIdTipoRichiesta())) {

		  validateMaxLength(form.getNumDocumentoTrasporto(), 30, "numDocumentoTrasporto", errors);
		  
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idNazioneProtVeg", "error.required");

			validateMaxLength(form.getPuntoEntrataDichiarato(), 150, "puntoEntrataDichiarato", errors); 
			
			validateMandatoryAndMaxLength(form.getLuogoDeposito(), "luogoDeposito", 150, errors);

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataInizioDisponibilitaMerce", "error.required");

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oraInizioDisponibilitaMerce", "error.required");

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataPartenzaMerce", "error.required");
			
			validateMaxLength(form.getNoteTrasporto(), 1000, "noteTrasporto", errors);
		}
	}

	public void validateDatiTrattamento(NuovaRichiestaForm form, Errors errors) {
		if (form.getIdTrattamento() != null) {
			validateMaxLength(form.getProdottoChimico(), 100, "prodottoChimico", errors);
			validateMaxLength(form.getDurata(), 20, "durata", errors);
			validateMaxLength(form.getTemperatura(), 20, "temperatura", errors);
			validateMaxLength(form.getConcentrazione(), 50, "concentrazione", errors);
			validateMaxLength(form.getInfoSupplementari(), 1000, "infoSupplementari", errors);
		}
	}

	public void validateDatiCertificato(NuovaRichiestaForm form, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idTipoCertificato", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataEsecuzione", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idIspettore", "error.required");

		if (CaronteConstants.NUMERAZIONE_CERTIFICATO_MANUALE.equals(form.getTipoNumerazioneCertificato())) {
		  if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(form.getIdTipoRichiesta())) {
		    validateMandatoryAndMaxLength(form.getNumeroCertificatoManuale(), "numeroCertificatoManuale", 6, errors);
		  }else {
		    validateMandatoryAndMaxLength(form.getNumeroCertificatoManuale(), "numeroCertificatoManuale", 7, errors);
		  }
		}

		if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(form.getIdTipoRichiesta())) {
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idLuogoEsecuzione", "error.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoNumerazioneCertificato", "error.required");
			validateMaxLength(form.getNoteCertificato(), 1000, "noteCertificato", errors);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idIspettoreDocumentale", "error.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idIspettoreIdentita", "error.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idIspettoreFitosanitario", "error.required");

		} else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(form.getIdTipoRichiesta())
				|| CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(form.getIdTipoRichiesta())) {
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idProvinciaEsecuzione", "error.required");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idComuneEsecuzione", "error.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idIspettoreFirmatario", "error.required");
			validateMaxLength(form.getDichiarazioneSupplementare(), 1000, "dichiarazione_supplementare", errors);
		}
	}

	public void validateDatiPagamento(NuovaRichiestaForm form, Errors errors) {
		validateMaxLength(form.getMittentePagamento(), 150, "mittentePagamento", errors);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idTipoPagamento", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataPagamento", "error.required");
		validateMandatoryAndMaxLength(form.getNumeroDocumento(), "numeroDocumento", 50, errors);
	}

}
