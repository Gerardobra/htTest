package it.aizoon.ersaf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IGeneriSpecieEJB;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaSpecieForm;
import it.aizoon.ersaf.form.RicercaSpecieForm;
import it.aizoon.ersaf.security.SecurityUtils;

@Controller
@RequestMapping(value = { "/admin/specie" })
@SessionAttributes({ "ricercaSpecieForm", "nuovaSpecieForm" })
public class SpecieController extends BaseController {

  @Autowired
  private IGeneriSpecieEJB generiSpecieEJB = null;

  @Autowired
  private IDecodificheEJB decodificheEJB = null;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/elenco")
  public String elencoSpecie(@ModelAttribute("ricercaSpecieForm") RicercaSpecieForm ricercaSpecieForm, Model model,
      SessionStatus sessionStatus) throws BusinessException {

    String referer = getReferer();
    if (!referer.contains("/admin/specie/elenco")) {
      if (referer.contains("/admin/specie")) {
        this.keepSessionAttribute("ricercaSpecieForm");
      }else {
        ricercaSpecieForm = getRicercaSpecieForm();
        model.addAttribute("ricercaSpecieForm", ricercaSpecieForm);
      }
      
      // resetto le variabili di sessione
      sessionStatus.setComplete();
    }

    try {
      ricercaSpecieForm.setCodLingua(LocaleContextHolder.getLocale().getLanguage());
      model.addAttribute("elencoSpecie", generiSpecieEJB.getListaSpecie(ricercaSpecieForm));
    } catch (BusinessException ex) {
      addErrorMessage("Errore durante l'ottenimento del risultati");
    }
    return "servizi/specie/elenco";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/nuovo")
  public String nuovaSpecie(@ModelAttribute("nuovaSpecieForm") NuovaSpecieForm nuovaSpecieForm, Model model)
      throws BusinessException {
    nuovaSpecieForm = new NuovaSpecieForm();

    setDatiNuovaSpecie(model);

    return "servizi/specie/nuovo";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "/nuovo")
  public String salvaSpecie(@Valid @ModelAttribute("nuovaSpecieForm") NuovaSpecieForm nuovaSpecieForm,
      BindingResult bindingResult, Model model, HttpServletRequest request) throws BusinessException {

    if (!bindingResult.hasErrors()) {
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();

        int status = generiSpecieEJB.inserisciNuovaSpecie(nuovaSpecieForm, idUtente);

        if (status > 0) {
          addSuccessMessage("Salvataggio della specie è avvenuta con successo");
          return getRedirect("admin/specie/elenco", request);
        }

      } catch (BusinessException be) {
        addErrorMessage(be.getMessage());
        logger.error("Errore durante il salvataggio del genere " + be.getMessage());
      } catch (Exception ex) {
        addErrorMessage("Errore durante il salvataggio della nuova specie");
        logger.error("Errore durante il salvataggio del genere " + ex.getMessage());
      }
    }

    setDatiNuovaSpecie(model);

    return "servizi/specie/nuovo";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "/modifica")
  public String aggiornaSpecie(@Valid @ModelAttribute("nuovaSpecieForm") NuovaSpecieForm nuovaSpecieForm,
      BindingResult bindingResult, Model model, HttpServletRequest request) throws BusinessException {
    if (!bindingResult.hasErrors()) {
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();

        int status = generiSpecieEJB.aggiornaSpecie(nuovaSpecieForm, idUtente);

        if (status > 0) {
          addSuccessMessage("Salvataggio della specie avvenuto con successo");
          return getRedirect("admin/specie/elenco", request);
        }

      } catch (BusinessException be) {
        addErrorMessage(be.getMessage());
        logger.error("Errore durante il salvataggio della specie " + be.getMessage());
      } catch (Exception ex) {
        addErrorMessage("Errore durante il salvataggio della specie");
        logger.error("Errore durante il salvataggio della specie " + ex.getMessage());
      }
    }

    setDatiNuovaSpecie(model);

    return "servizi/specie/modifica";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/modifica/{id}")
  public String modificaSpecie(@PathVariable Long id, @ModelAttribute("nuovaSpecieForm") NuovaSpecieForm form,
      Model model) throws BusinessException {
    try {

      SpecieDTO specie = generiSpecieEJB.getDettaglioSpecie(id, LocaleContextHolder.getLocale().getLanguage());

      form.setDatiFromDTO(specie);

      setDatiNuovaSpecie(model);

    } catch (BusinessException be) {
      addErrorMessage(be.getMessage());
      logger.error("Errore durante il salvataggio della specie " + be.getMessage());
    } catch (Exception ex) {
      addErrorMessage("Errore durante il salvataggio della specie");
      logger.error("Errore durante il salvataggio della specie " + ex.getMessage());
    }

    return "servizi/specie/modifica";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/dettaglio/{id}")
  public String dettaglio(@PathVariable Long id, Model model) throws BusinessException {
    try {
      model.addAttribute("dettaglioSpecie",
          generiSpecieEJB.getDettaglioSpecie(id, LocaleContextHolder.getLocale().getLanguage()));
    } catch (BusinessException be) {
      addErrorMessage("Errore nel reperimento del dettaglio della richiesta");
    }

    return "servizi/specie/dettaglio";
  }

  @ModelAttribute("ricercaSpecieForm")
  public RicercaSpecieForm getRicercaSpecieForm() {
    return new RicercaSpecieForm();
  }

  @ModelAttribute("nuovaSpecieForm")
  public NuovaSpecieForm getNuovaSpecieForm() {
    return new NuovaSpecieForm();
  }

  private void setDatiNuovaSpecie(Model model) throws BusinessException {
    model.addAttribute("elencoNazioni", decodificheEJB.getListaNazioni(false));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/elimina/{id}")
  public String eliminaSpecie(@PathVariable Long id, Model model, HttpServletRequest request) throws BusinessException {
    try {
      String messaggioErrore = generiSpecieEJB.eliminaSpecie(id);

      if (messaggioErrore == null) {
        addSuccessMessage("La Specie è stata eliminato");
      } else {
        addErrorMessage(messaggioErrore);
      }
    } catch (BusinessException be) {
      addErrorMessage("Errore nell'eliminazione della Specie");
    }

    return getRedirect("admin/specie/elenco", request);
  }
}
