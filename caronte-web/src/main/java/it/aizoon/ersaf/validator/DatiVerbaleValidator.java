package it.aizoon.ersaf.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.aizoon.ersaf.dto.MerceRichiestaVerbaleDto;
import it.aizoon.ersaf.form.DatiVerbaleForm;

@Component
public class DatiVerbaleValidator extends BaseValidator {

  @Override
  public boolean supports(Class<?> paramClass) {
    return DatiVerbaleForm.class.equals(paramClass);
  }

  @Override
  public void validate(Object obj, Errors errors) {

  }

  public void validateDatiIspezione(DatiVerbaleForm form, Errors errors) {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataIspezione", "error.required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oraInizioIspezione", "error.required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oraFineIspezione", "error.required");

    validateMaxLength(form.getMagazzinoDoganale(), 100, "magazzinoDoganale", errors);
    validateMaxLength(form.getPersonaRiferimento(), 100, "personaRiferimento", errors);
    validateMaxLength(form.getPersonaRiferimentoRuolo(), 100, "personaRiferimentoRuolo", errors);
    
    for(int i = 0; i < form.getListaMerceRichiesta().size(); i++) {
    	MerceRichiestaVerbaleDto merce = form.getListaMerceRichiesta().get(i);
    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "listaMerceRichiesta["+i+"].descrizionePartita", "error.required");
    	validateMaxLength(merce.getDescrizionePartita(), 100, "listaMerceRichiesta["+i+"].descrizionePartita", errors);
    }
    
    
    
    if (errors.getErrorCount() > 0) {
      form.setAccordionSelezionato("0");
    }
  }
  
  public void validateDatiSpedizione(DatiVerbaleForm form, Errors errors) {
    if (errors.getErrorCount() > 0) {
      form.setAccordionSelezionato("1");
    }else {
      validateDatiIspezione(form, errors);
    }
  }

  public void validateDatiControlloEsito(DatiVerbaleForm form, Errors errors) {

    if (form.isControlloDocumentale() && "N".equals(form.getControlloDocumentaleCB())) {
      validateMaxLength(form.getNoteControlloDocumentaleCB(), 100, "noteControlloDocumentaleCB", errors);
    }

    if (form.isControlloIdentita() && "N".equals(form.getControlloIdentitaCB())) {
      validateMaxLength(form.getNoteControlloDocumentaleCB(), 100, "noteControlloIdentitaCB", errors);
    }

    if (form.isControlloFitosanitario() && form.isControlloFitosanitarioIspStrum()) {
      validateMaxLength(form.getNoteControlloFitosanitarioCB(), 100, "noteControlloFitosanitarioCB", errors);
    }

    validateMaxLength(form.getPrelievoPerRicerca(), 200, "prelievoPerRicerca", errors);
    
    validateMaxLength(form.getCodiceCampione(), 200, "codiceCampione", errors);

    if ("N".equals(form.getTermIspezioneNullaOsta())) {
      validateMaxLength(form.getTermIspezioneNullaOstaNote(), 100, "termIspezioneNullaOstaNote", errors);
    }

    if (form.isTermIspezioneMisUff()) {
      validateMaxLength(form.getTermIspezioneMisUffNote(), 100, "termIspezioneMisUffNote", errors);
    }

  }

  public void validateDatiMisureUfficiali(DatiVerbaleForm form, Errors errors) {

    if (form.isMisUffA()) {
      validateMaxLength(form.getMisUffANote(), 100, "misUffANote", errors);
    }

    if (form.isMisUffB()) {
      validateMaxLength(form.getMisUffBNote(), 100, "misUffBNote", errors);
    }

    if (form.isMisUffC()) {
      validateMaxLength(form.getMisUffCNote(), 100, "misUffCNote", errors);
    }

    if (form.isMisUffD()) {
      validateMaxLength(form.getMisUffDNote(), 100, "misUffDNote", errors);
    }

    if (form.isMisUffF()) {
      validateMaxLength(form.getMisUffFNote(), 100, "misUffFNote", errors);
    }

  }

  public void validateDatiCustodia(DatiVerbaleForm form, Errors errors) {

    validateMaxLength(form.getCustResponsabileMerce(), 100, "custResponsabileMerce", errors);
    validateMaxLength(form.getCustDocumentoRespMerce(), 100, "custDocumentoRespMerce", errors);
    validateMaxLength(form.getCustRuoloRespMerce(), 100, "custRuoloRespMerce", errors);
    validateMaxLength(form.getCustLocaliMerce(), 100, "custLocaliMerce", errors);
  }

  public void validateDatiDichiarazioniNote(DatiVerbaleForm form, Errors errors) {

    validateMaxLength(form.getDichNoteRespVerbale(), 100, "dichNoteRespVerbale", errors);
    validateMaxLength(form.getDichNoteRuoloRespVerbale(), 100, "dichNoteRuoloRespVerbale", errors);
    validateMaxLength(form.getDichNoteDichiarazioneRespVerb(), 100, "dichNoteDichiarazioneRespVerb", errors);
    validateMaxLength(form.getDichNoteNoteRespVerb(), 1000, "dichNoteNoteRespVerb", errors);
  }

}
