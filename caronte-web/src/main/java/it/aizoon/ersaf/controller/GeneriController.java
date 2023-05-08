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
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovoGenereForm;
import it.aizoon.ersaf.form.RicercaGeneriForm;
import it.aizoon.ersaf.security.SecurityUtils;

@Controller
@SessionAttributes({ "ricercaGeneriForm", "nuovoGenereForm" })
@RequestMapping(value = { "/admin/generi" })
public class GeneriController extends BaseController {

  @Autowired
  private IGeneriSpecieEJB generiSpecieEJB = null;

  @Autowired
  private IDecodificheEJB decodificheEJB = null;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/elenco")
  public String cerca(@ModelAttribute("ricercaGeneriForm") @Valid RicercaGeneriForm ricercaGeneriForm, Model model,
      SessionStatus sessionStatus) throws BusinessException {
    
    String referer = getReferer();
    
    if (!referer.contains("/admin/generi/elenco")) {
      
      if (referer.contains("/admin/generi")) {
        this.keepSessionAttribute("ricercaGeneriForm");
      }else {
        ricercaGeneriForm = getRicercaGeneriForm();
        model.addAttribute("ricercaGeneriForm", ricercaGeneriForm);
      }
      
      // resetto le variabili di sessione
      sessionStatus.setComplete();
    }
    
    try {
      ricercaGeneriForm.setCodLingua(LocaleContextHolder.getLocale().getLanguage());
      model.addAttribute("elencoGeneri", generiSpecieEJB.getListaGeneri(ricercaGeneriForm));
    } catch (Exception exc) {
      model.addAttribute("page_error", "Errore nella ricerca dei generi");
    }
    // Il path della JSP
    return "servizi/generi/elenco";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/nuovo")
  public String nuovoGenere(@ModelAttribute("nuovoGenereForm") NuovoGenereForm nuovoGenereForm, Model model)
      throws BusinessException {
    nuovoGenereForm = new NuovoGenereForm();

    model.addAttribute("elencoNazioni", decodificheEJB.getListaNazioni(false));
    // model.addAttribute("elencoLingue", decodificheEJB.getListaLingue());

    return "servizi/generi/nuovo";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/modifica/{id}")
  public String modificaGenere(@PathVariable Long id, @ModelAttribute("nuovoGenereForm") NuovoGenereForm form,
      Model model) throws BusinessException {
    try {

      GenereDTO genere = generiSpecieEJB.getDettaglioGenere(id, LocaleContextHolder.getLocale().getLanguage());
      model.addAttribute("elencoNazioni", decodificheEJB.getListaNazioni(false));
      form.setDatiFromDTO(genere);

    } catch (BusinessException ex) {
      addErrorMessage("Errore durante la modifica del genere");
    }
    return "servizi/generi/modifica";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(params = "datiNuovoGenere", value = "/nuovo")
  public String salvaNuovoGenere(@Valid @ModelAttribute("nuovoGenereForm") NuovoGenereForm nuovoGenereForm,
      BindingResult bindResult, Model model, HttpServletRequest request) throws BusinessException {

    if (!bindResult.hasErrors()) {
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();

        generiSpecieEJB.inserisciNuovoGenere(nuovoGenereForm, idUtente);

        addSuccessMessage("Salvataggio del genere avvenuto con successo");

        return getRedirect("admin/generi/elenco", request);

      } catch (BusinessException be) {
        addErrorMessage(be.getMessage());
        logger.error("Errore durante il salvataggio del genere " + be.getMessage());
      } catch (Exception ex) {
        addErrorMessage("Errore durante il salvataggio del genere");
        logger.error("Errore durante il salvataggio del genere " + ex.getMessage());
      }
    }

    return "servizi/generi/nuovo";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(params = "datiNuovoGenere", value = "/modifica")
  public String aggiornaGenere(@Valid @ModelAttribute("nuovoGenereForm") NuovoGenereForm nuovoGenereForm,
      BindingResult binding, Model model, HttpServletRequest request) throws BusinessException {

    if (!binding.hasErrors()) {
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();

        generiSpecieEJB.aggiornaGenere(nuovoGenereForm, idUtente);

        addSuccessMessage("Salvataggio avvenuto con successo");

        return getRedirect("admin/generi/elenco", request);

      } catch (BusinessException be) {
        addErrorMessage(be.getMessage());
        logger.error("Errore durante il salvataggio del genere " + be.getMessage());
      } catch (Exception ex) {
        addErrorMessage("Errore durante il salvataggio del genere");
        logger.error("Errore durante il salvataggio del genere " + ex.getMessage());
      }
    }

    return "servizi/generi/modifica";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/dettaglio/{id}")
  public String dettaglio(@PathVariable Long id, Model model) throws BusinessException {
    try {
      model.addAttribute("dettaglioGenere",
          generiSpecieEJB.getDettaglioGenere(id, LocaleContextHolder.getLocale().getLanguage()));
    } catch (BusinessException be) {
      addErrorMessage("Errore nel reperimento del dettaglio della richiesta");
    }

    return "servizi/generi/dettaglio";
  }

  @ModelAttribute("ricercaGeneriForm")
  public RicercaGeneriForm getRicercaGeneriForm() {
    return new RicercaGeneriForm();
  }
  
  @ModelAttribute("nuovoGenereForm")
  public NuovoGenereForm getNuovoGenereForm() {
    return new NuovoGenereForm();
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/elimina/{id}")
  public String eliminaGenere(@PathVariable Long id, Model model, HttpServletRequest request)
      throws BusinessException {
    try {
      String messaggioErrore = generiSpecieEJB.eliminaGenere(id);
      
      if (messaggioErrore == null) {
        addSuccessMessage("Il Genere è stata eliminato");
      }else {
        addErrorMessage(messaggioErrore);
      }
    } catch (BusinessException be) {
      addErrorMessage("Errore nell'eliminazione del Genere");
    }

    return getRedirect("admin/generi/elenco", request);
  }

}