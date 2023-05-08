package it.aizoon.ersaf.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IRichiesteEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.CertificatoRichiestaDto;
import it.aizoon.ersaf.dto.DecodificaDTO;
import it.aizoon.ersaf.dto.DettaglioRichiestaDto;
import it.aizoon.ersaf.dto.MerceRichiestaDto;
import it.aizoon.ersaf.dto.PagamentoRichiestaDto;
import it.aizoon.ersaf.dto.RichiestaDto;
import it.aizoon.ersaf.dto.TariffeRichiestaDto;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.generati.CarDStatoRichiesta;
import it.aizoon.ersaf.dto.generati.CarDTipoSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaRichiestaForm;
import it.aizoon.ersaf.form.RicercaRichiestaForm;
import it.aizoon.ersaf.security.SecurityUtils;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;
import it.aizoon.ersaf.validator.NuovaRichiestaValidator;

/**
 * @author Ivan Morra
 */

@Controller
@SessionAttributes({ "nuovaRichiestaForm"/* , "modificaRichiestaForm" */, "datiVerbaleForm" })
@RequestMapping(value = { "/import/", "/export/richieste" })
public class RichiesteController extends BaseController {

  @Autowired
  private IRichiesteEJB richiesteEJB = null;

  @Autowired
  private IDecodificheEJB decodificheEJB = null;

  @Autowired
  private IUtenteEJB utenteEJB = null;

  @Autowired
  private NuovaRichiestaValidator validator;

  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/elenco")
  public String cerca(@ModelAttribute("ricercaRichiestaForm") @Valid RicercaRichiestaForm ricercaRichiestaForm,
      BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request)
      throws BusinessException {

    // Si resettano i form dei cu figli (nuova, modifica....)
    sessionStatus.setComplete();

    try {
      model.addAttribute("statoRichiesta", richiesteEJB.getStatoRichiesta());
      model.addAttribute("ufficioDoganale", decodificheEJB.getUfficioDoganale());
      model.addAttribute("listaispettori", utenteEJB.getListaIspettoriRichiesta());
      
      UtenteDTO utente = SecurityUtils.getUtenteLoggato();

      if (utente.isSuperUser()) {
        /*
         * Se è un super user, al primo caricamento della pagina verrà proposto
         * "Inoltrata" come stato selezionato della richiesta
         */
        if (request.getParameter("idStatoRichiesta") == null) {
          ricercaRichiestaForm.setIdStatoRichiesta(CaronteConstants.STATO_RICHIESTA_INOLTRATA);
        }
      } else {
        /*
         * Se è un utente normale, può consultare solo le richieste create da
         * lui o da altri utenti dello stesso spedizioniere
         */
        ricercaRichiestaForm.setIdUtenteInsert(utente.getId());
      }

      if (isRichiestaImport(request)) {
        ricercaRichiestaForm.setCodTipoRichiesta(CaronteConstants.COD_TIPO_RICHIESTA_IMPORT);
      }else {
        model.addAttribute("listaProvince", 
            decodificheEJB.getListaProvince(CaronteConstants.ID_REGIONE_LOMBARDIA));
      }

      List<RichiestaDto> elencoRichieste = richiesteEJB.getElencoRichieste(ricercaRichiestaForm);

      model.addAttribute("elencoRichieste", elencoRichieste);

    } catch (BusinessException exc) {
      addErrorMessage("Errore nella ricerca delle richieste");
      // model.addAttribute("page_error", "Errore nella ricerca delle
      // richieste");
    }
    /*
     * if (result.hasErrors()) { return "richieste/elenco"; }
     */
    return "richieste/elenco";
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/nuova")
  public String nuova(@ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm nuovaRichiestaForm, Model model,
      HttpServletRequest request) throws BusinessException {

    setDatiNuovaRichiesta(model, nuovaRichiestaForm);

    return "richieste/nuova";
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiRichiesta", value = { "/nuova", "/modifica" })
  public String salvaDatiRichiesta(@ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm nuovaRichiestaForm,
      BindingResult result,
      @RequestParam(value = "allegatoCertificato", required = false) MultipartFile allegatoCertificato, Model model,
      HttpServletRequest request) throws BusinessException {

    validator.validateDatiRichiesta(nuovaRichiestaForm, result);

    if (result.getErrorCount() == 0) {
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();

        if (nuovaRichiestaForm.getIdRichiesta() == null) {
          if (allegatoCertificato == null || StringUtils.isEmpty(allegatoCertificato.getOriginalFilename())) {
            nuovaRichiestaForm.setIdRichiesta(richiesteEJB.inserisciNuovaRichiesta(nuovaRichiestaForm, idUtente, null));
          } else {
            Long idAllegato = richiesteEJB.inserisciAllegatoCertificato(
                CaronteUtils.extractFileName(allegatoCertificato.getOriginalFilename()),
                allegatoCertificato.getBytes());
            nuovaRichiestaForm
                .setIdRichiesta(richiesteEJB.inserisciNuovaRichiesta(nuovaRichiestaForm, idUtente, idAllegato));
          }

          addSuccessMessage("Richiesta creata");

        } else {
          if (!checkModificaRichiesta(idUtente, nuovaRichiestaForm)) {
            return getRedirect("richieste/elenco", request);
          }

          if (allegatoCertificato == null) {
            richiesteEJB.aggiornaDatiRichiesta(nuovaRichiestaForm, idUtente, null);
          } else {
            Long idAllegato = richiesteEJB.inserisciAllegatoCertificato(
                CaronteUtils.extractFileName(allegatoCertificato.getOriginalFilename()),
                allegatoCertificato.getBytes());
            richiesteEJB.aggiornaDatiRichiesta(nuovaRichiestaForm, idUtente, idAllegato);
          }
          nuovaRichiestaForm.setDataUltimaModifica(new Date());
        }

        nuovaRichiestaForm.setSchedaSelezionata(2);

      } catch (BusinessException | IOException be) {
        addErrorMessage("Errore nel salvataggio della richiesta");
        nuovaRichiestaForm.setSchedaSelezionata(1);
      }
    } else {
      nuovaRichiestaForm.setSchedaSelezionata(1);
    }
    
    setDatiNuovaRichiesta(model, nuovaRichiestaForm);

    return getViewNuovaModifica(request);
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiMittente", value = { "/nuova", "/modifica" })
  public String salvaDatiMittente(@ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm nuovaRichiestaForm,
      BindingResult result, Model model, HttpServletRequest request) throws BusinessException {

    validator.validateDatiMittente(nuovaRichiestaForm, result);

    if (result.getErrorCount() == 0) {
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();

        if (!checkModificaRichiesta(idUtente, nuovaRichiestaForm)) {
          return getRedirect("richieste/elenco", request);
        }

        richiesteEJB.aggiornaDatiMittente(nuovaRichiestaForm, idUtente);

        /*
         * Si rieffettua il test sull'abilitazione del pulsante Inoltra
         */
        nuovaRichiestaForm.setAbilitaInoltra(checkAbilitaInoltra(idUtente, nuovaRichiestaForm.getIdRichiesta(), null));

        nuovaRichiestaForm.setDataUltimaModifica(new Date());
        nuovaRichiestaForm.setSchedaSelezionata(3);

      } catch (BusinessException be) {
        addErrorMessage("Errore nel salvataggio del mittente");
        nuovaRichiestaForm.setSchedaSelezionata(2);
      }
    } else {
      nuovaRichiestaForm.setSchedaSelezionata(2);
    }

    setDatiNuovaRichiesta(model, nuovaRichiestaForm);

    return getViewNuovaModifica(request);
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiDestinatario", value = { "/nuova", "/modifica" })
  public String salvaDatiDestinatario(@ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm nuovaRichiestaForm,
      BindingResult result, Model model, HttpServletRequest request) throws BusinessException {

    validator.validateDatiDestinatario(nuovaRichiestaForm, result);

    if (result.getErrorCount() == 0) {
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();

        if (!checkModificaRichiesta(idUtente, nuovaRichiestaForm)) {
          return getRedirect("richieste/elenco", request);
        }

        richiesteEJB.aggiornaDatiDestinatario(nuovaRichiestaForm, idUtente);

        /*
         * Si rieffettua il test sull'abilitazione del pulsante Inoltra
         */
        nuovaRichiestaForm.setAbilitaInoltra(checkAbilitaInoltra(idUtente, nuovaRichiestaForm.getIdRichiesta(), null));

        nuovaRichiestaForm.setDataUltimaModifica(new Date());
        nuovaRichiestaForm.setSchedaSelezionata(4);

      } catch (BusinessException be) {
        addErrorMessage("Errore nel salvataggio del destinatario");
        nuovaRichiestaForm.setSchedaSelezionata(3);
      }
    } else {
      nuovaRichiestaForm.setSchedaSelezionata(3);
    }

    setDatiNuovaRichiesta(model, nuovaRichiestaForm);

    return getViewNuovaModifica(request);
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiTrasporto", value = { "/nuova", "/modifica" })
  public String salvaDatiTrasporto(@ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm nuovaRichiestaForm,
      BindingResult result, Model model, HttpServletRequest request) throws BusinessException {

    validator.validateDatiTrasporto(nuovaRichiestaForm, result);

    if (result.getErrorCount() == 0) {
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();

        if (!checkModificaRichiesta(idUtente, nuovaRichiestaForm)) {
          return getRedirect("richieste/elenco", request);
        }

        richiesteEJB.aggiornaDatiTrasporto(nuovaRichiestaForm, idUtente);

        /*
         * Si rieffettua il test sull'abilitazione del pulsante Inoltra
         */
        nuovaRichiestaForm.setAbilitaInoltra(checkAbilitaInoltra(idUtente, nuovaRichiestaForm.getIdRichiesta(), null));

        nuovaRichiestaForm.setDataUltimaModifica(new Date());
        nuovaRichiestaForm.setSchedaSelezionata(5);

        if (nuovaRichiestaForm.getIdCertificatoRichiesta() == null) {
          nuovaRichiestaForm.setIdLuogoEsecuzione(nuovaRichiestaForm.getIdPuntoEntrataDichiarato());
        }

      } catch (BusinessException be) {
        addErrorMessage("Errore nel salvataggio del trasporto");
        nuovaRichiestaForm.setSchedaSelezionata(4);
      }
    } else {
      nuovaRichiestaForm.setSchedaSelezionata(4);
    }

    setDatiNuovaRichiesta(model, nuovaRichiestaForm);

    return getViewNuovaModifica(request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiMerce", value = { "/nuova", "/modifica" })
  public String salvaDatiMerci(@ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm nuovaRichiestaForm,
      BindingResult result, Model model, HttpServletRequest request) throws BusinessException {

    Iterator<MerceRichiestaDto> iterMerci = nuovaRichiestaForm.getListaMerceRichiesta().iterator();

    /*
     * Si rimuovono dalla lista i record che non hanno corrispondenza nei valori
     * passati in request, dato che Spring non rimuove automaticamente dalla
     * lista i record cancellati dall'utente
     */
    for (int i = 0; iterMerci.hasNext(); i++) {
      iterMerci.next();
      if (request.getParameter("listaMerceRichiesta[" + i + "].idNazioneOrigine") == null) {
        iterMerci.remove();
      }
    }

    if (nuovaRichiestaForm.getListaMerceRichiesta().size() > 0) {
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();

        if (!checkModificaRichiesta(idUtente, nuovaRichiestaForm)) {
          return getRedirect("richieste/elenco", request);
        }

        richiesteEJB.aggiornaDatiMerce(nuovaRichiestaForm, idUtente);

        /*
         * Si ricaricano i dati aggiornati di merci e tariffe (le query sono
         * diverse tra import ed export perchè in export id_tipo_prodotto non è
         * mai valorizzato)
         */
        if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(nuovaRichiestaForm.getIdTipoRichiesta())) {

          nuovaRichiestaForm.setListaMerceRichiesta(new AutoPopulatingList<>(
              richiesteEJB.getListaMerciRichiesta(nuovaRichiestaForm.getIdRichiesta()), MerceRichiestaDto.class));

        } else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(nuovaRichiestaForm.getIdTipoRichiesta())
            || CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(nuovaRichiestaForm.getIdTipoRichiesta())) {

          nuovaRichiestaForm.setListaMerceRichiesta(new AutoPopulatingList<>(
              richiesteEJB.getListaMerciRichiestaExport(nuovaRichiestaForm.getIdRichiesta()), MerceRichiestaDto.class));

        }

        TariffeRichiestaDto tariffe = richiesteEJB.getTotaliTariffeRichiesta(nuovaRichiestaForm.getIdRichiesta());
        setDatiTariffe(nuovaRichiestaForm, tariffe);

        /*
         * Si rieffettua il test sull'abilitazione del pulsante Inoltra
         */
        nuovaRichiestaForm
            .setAbilitaInoltra(checkAbilitaInoltra(idUtente, nuovaRichiestaForm.getIdRichiesta(), tariffe));

        nuovaRichiestaForm.setDataUltimaModifica(new Date());
        nuovaRichiestaForm.setSchedaSelezionata(6);
        nuovaRichiestaForm.setSchedaAbilitata(7);

      } catch (BusinessException be) {
        addErrorMessage("Errore nel salvataggio delle merci");
        nuovaRichiestaForm.setSchedaSelezionata(5);
      }
    } else {
      addErrorMessage("Aggiungere almeno una merce");
      nuovaRichiestaForm.setSchedaSelezionata(5);
    }

    setDatiNuovaRichiesta(model, nuovaRichiestaForm);

    return getViewNuovaModifica(request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiTrattamento", value = { "/nuova", "/modifica" })
  public String salvaDatiTrattamento(@ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm nuovaRichiestaForm,
      BindingResult result, Model model, HttpServletRequest request) throws BusinessException {

    validator.validateDatiTrattamento(nuovaRichiestaForm, result);

    if (result.getErrorCount() == 0) {
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();

        if (!checkModificaRichiesta(idUtente, nuovaRichiestaForm)) {
          return getRedirect("richieste/elenco", request);
        }

        richiesteEJB.aggiornaDatiTrattamento(nuovaRichiestaForm, idUtente);

        nuovaRichiestaForm.setDataUltimaModifica(new Date());

        /*
         * Nel caso la tariffa totale sia a zero, e la richiesta sia
         * inoltrabile, si propone l'inoltro della richiesta invece di
         * visualizzare la scheda del pagamento
         */
        TariffeRichiestaDto tariffe = richiesteEJB.getTotaliTariffeRichiesta(nuovaRichiestaForm.getIdRichiesta());

        if (tariffe.getTotaleTariffe() != null && BigDecimal.ZERO.compareTo(tariffe.getTotaleTariffe()) == 0) {

          if (checkAbilitaInoltra(idUtente, nuovaRichiestaForm.getIdRichiesta(), tariffe)) {
            nuovaRichiestaForm.setVisualizzaModalInoltra(true);
          }
        }

        nuovaRichiestaForm.setSchedaSelezionata(8);

      } catch (BusinessException be) {
        addErrorMessage("Errore nel salvataggio dei dati del trattamento");
        nuovaRichiestaForm.setSchedaSelezionata(7);
      }
    } else {
      nuovaRichiestaForm.setSchedaSelezionata(7);
    }

    setDatiNuovaRichiesta(model, nuovaRichiestaForm);

    return getViewNuovaModifica(request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiPagamento", value = { "/nuova", "/modifica" })
  public String salvaDatiPagamento(@ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm form, BindingResult result,
      @RequestParam(value = "allegatoPagamento", required = false) MultipartFile allegatoPagamento, Model model,
      HttpServletRequest request) throws BusinessException {

    UtenteDTO utente = SecurityUtils.getUtenteLoggato();
    boolean isNuovaRichiesta = request.getRequestURI().contains("/nuova");

    validator.validateDatiPagamento(form, result);

    if (result.getErrorCount() == 0) {
      try {
        Long idUtente = utente.getId();
        String filename = null;

        if (!checkModificaRichiesta(idUtente, form)) {
          return getRedirect("richieste/elenco", request);
        }

        if (allegatoPagamento == null) {
          richiesteEJB.aggiornaDatiPagamento(form, null, null, idUtente);
        } else {
          filename = new File(CaronteUtils.extractFileName(allegatoPagamento.getOriginalFilename())).getName();

          richiesteEJB.aggiornaDatiPagamento(form, filename, allegatoPagamento.getBytes(), idUtente);
        }

        /*
         * Se sono stati compilati i dati di Mittente, Destinatario, Trasporto,
         * Merci e Pagamento, si rimane nella pagina e si dà la possibilità di
         * inoltrare la richiesta in bozza
         */
        DettaglioRichiestaDto dettaglioRichiesta = richiesteEJB.getDettaglioRichiesta(form.getIdRichiesta());

        if (/* isNuovaRichiesta && */checkAbilitaInoltra(idUtente, dettaglioRichiesta, true)) {
          form.setVisualizzaModalInoltra(true);
          form.setSchedaSelezionata(8);
        } else if (!isNuovaRichiesta && utente.isSuperUser()) {
          form.setSchedaSelezionata(9);

          if (filename != null) {
            form.setNomeFileAllegato(filename);
          }
        } else {
          return getRedirect("richieste/elenco", request);
        }
      } catch (BusinessException | IOException exc) {
        logger.error("Errore nel salvataggio del pagamento della richiesta", exc);
        addErrorMessage("Errore nel salvataggio dei dati del pagamento");
        form.setSchedaSelezionata(8);
      }
    } else {
      form.setSchedaSelezionata(8);
    }

    setDatiNuovaRichiesta(model, form);

    return getViewNuovaModifica(request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "posticipaPagamento", value = { "/nuova" })
  public String posticipaPagamento(@ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm form, Model model,
      HttpServletRequest request) throws BusinessException {

    UtenteDTO utente = SecurityUtils.getUtenteLoggato();

    try {
      Long idUtente = utente.getId();

      /*
       * Se sono stati compilati i dati di Mittente, Destinatario, Trasporto,
       * Merci e Pagamento, si rimane nella pagina e si dà la possibilità di
       * inoltrare la richiesta in bozza
       */
      if (utente.isAutorizPagamPosticip()) {
        if (checkAbilitaInoltra(idUtente, form.getIdRichiesta(), null)) {
          form.setVisualizzaModalInoltra(true);
          form.setSchedaSelezionata(8);
        } else {
          return getRedirect("richieste/elenco", request);
        }
      } else {
        form.setSchedaSelezionata(8);
        addErrorMessage("Utente non abilitato al pagamento posticipato");
      }
    } catch (BusinessException exc) {
      logger.error("Errore nel salvataggio del pagamento della richiesta", exc);
      addErrorMessage("Errore nel salvataggio dei dati del pagamento");
      form.setSchedaSelezionata(8);
    }

    setDatiNuovaRichiesta(model, form);

    return getViewNuovaModifica(request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiEsegui", value = { "/avanza" })
  public String salvaDatiCertificatoAvanza(@ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm form,
      BindingResult result, Model model, HttpServletRequest request) throws BusinessException {
    return salvaDatiCertificato(form, result, model, request, true);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiEsegui", value = { "/modifica" })
  public String salvaDatiCertificato(@ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm form,
      BindingResult result, Model model, HttpServletRequest request, boolean isAvanza) throws BusinessException {

    validator.validateDatiCertificato(form, result);

    if (result.getErrorCount() == 0) {
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();

        if (!isAvanza && !checkModificaRichiesta(idUtente, form)) {
          return getRedirect("richieste/elenco", request);
        }

        form.setIdCertificatoRichiesta(richiesteEJB.aggiornaDatiCertificato(form, idUtente));

        form.setDataUltimaModifica(new Date());

        if (!CaronteConstants.NUMERAZIONE_CERTIFICATO_MANUALE.equals(form.getTipoNumerazioneCertificato())) {
          form.setNumeroCertificatoManuale(null);
        }

      } catch (BusinessException be) {
        addErrorMessage("Errore nel salvataggio dei dati del certificato");
      }
    }

    form.setSchedaSelezionata(9);

    if (isAvanza) {
      setDatiAvanzaRichiesta(model, form);
    } else {
      setDatiNuovaRichiesta(model, form);
    }

    if (isAvanza) {
      UtenteDTO utente = SecurityUtils.getUtenteLoggato();

      Boolean isUtenteAbilitato = richiesteEJB.isRichiestaModificabile(utente.getId(), form.getIdRichiesta());

      if (isUtenteAbilitato == null) {
        return getViewNuovaModifica(request);
      } else if (!isUtenteAbilitato) {
        return "richieste/avanza";
      }
    }

    return getViewNuovaModifica(request);
  }

  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/dettaglio/{id}")
  public String getDettaglioRichiesta(@PathVariable Long id, Model model, HttpServletRequest request)
      throws BusinessException {
    try {
      DettaglioRichiestaDto dettaglioRichiesta = richiesteEJB.getDettaglioRichiesta(id);

      model.addAttribute("dettaglioRichiesta", dettaglioRichiesta);
      model.addAttribute("tariffe", richiesteEJB.getTotaliTariffeRichiesta(id));

      if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(dettaglioRichiesta.getIdTipoRichiesta())) {

        model.addAttribute("listaMerci", richiesteEJB.getListaMerciRichiesta(id));
        model.addAttribute("listaTariffeProdotti", richiesteEJB.getListaTariffeProdotti());

      } else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(dettaglioRichiesta.getIdTipoRichiesta())
          || CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(dettaglioRichiesta.getIdTipoRichiesta())) {

        model.addAttribute("listaMerci", richiesteEJB.getListaMerciRichiestaExport(id));
        model.addAttribute("listaNaturaColli", decodificheEJB.getListaNaturaColli());

        model.addAttribute("listaTariffeExport", richiesteEJB.getTariffeTipoRichiesta(
            dettaglioRichiesta.getIdTipoRichiesta(), CaronteConstants.ID_TIPO_CONTROLLO_FITOSANITARIO));

      }

      model.addAttribute("trattamentoRichiesta", richiesteEJB.getTrattamentoRichiesta(id));
      model.addAttribute("pagamentoRichiesta", richiesteEJB.getPagamentoRichiesta(id));
      model.addAttribute("destinatarioPagamento", decodificheEJB.getCostante("DEST_PAGAM"));
      model.addAttribute("datiCertificato", richiesteEJB.getDatiCertificatoRichiesta(id));
      model.addAttribute("isSuperUser", SecurityUtils.getUtenteLoggato().isSuperUser());
    } catch (BusinessException be) {
      addErrorMessage("Errore nel reperimento del dettaglio della richiesta");
    }

    return "richieste/dettaglio";
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = { "/avanza/{id}", "/avanza/{id}/{scheda}" })
  public String avanzaStatoRichiesta(@PathVariable Long id, @PathVariable(required = false) Long scheda, Model model,
      HttpServletRequest request, @ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm form)
      throws BusinessException {
    String messaggioErrore = null;

    try {
      UtenteDTO utente = SecurityUtils.getUtenteLoggato();

      Boolean isUtenteAbilitato = richiesteEJB.isRichiestaModificabile(utente.getId(), id);

      if (isUtenteAbilitato == null /* || !isUtenteAbilitato */) {
        messaggioErrore = "La richiesta non può essere modificata dall'utente corrente";
      } else {
        boolean abilitaInoltra = false;
        boolean abilitaEsegui = false;
        boolean abilitaLiquida = false;
        boolean abilitaRestituisci = false;
        boolean abilitaAnnulla = false;

        DettaglioRichiestaDto dettaglioRichiesta = richiesteEJB.getDettaglioRichiesta(id);

        List<CarDStatoRichiesta> listaStatiSuccessivi = richiesteEJB.getListaStatiRichiestaSuccessivi(utente.getId(),
            id);

        for (CarDStatoRichiesta stato : listaStatiSuccessivi) {
          if (CaronteConstants.STATO_RICHIESTA_INOLTRATA.equals(stato.getIdStatoRichiesta())) {
            /*
             * Per inoltrare una richiesta, occorre verificare che siano state
             * compilate diverse sezioni della richiesta (mittente,
             * destinatario, trasporto, merci, pagamento)
             */
            abilitaInoltra = checkAbilitaInoltra(utente.getId(), dettaglioRichiesta, false);
          } else if (CaronteConstants.STATO_RICHIESTA_ESEGUITA.equals(stato.getIdStatoRichiesta())) {
            abilitaEsegui = true;
          } else if (CaronteConstants.STATO_RICHIESTA_LIQUIDATA.equals(stato.getIdStatoRichiesta())) {
            abilitaLiquida = true;
          } else if (CaronteConstants.STATO_RICHIESTA_RESTITUITA.equals(stato.getIdStatoRichiesta())) {
            abilitaRestituisci = true;
          } else if (CaronteConstants.STATO_RICHIESTA_ANNULLATA.equals(stato.getIdStatoRichiesta())) {
            abilitaAnnulla = true;
          }
        }

        model.addAttribute("abilitaInoltra", abilitaInoltra);
        model.addAttribute("abilitaEsegui", abilitaEsegui);
        model.addAttribute("abilitaLiquida", abilitaLiquida);
        model.addAttribute("abilitaRestituisci", abilitaRestituisci);
        model.addAttribute("abilitaAnnulla", abilitaAnnulla);

        model.addAttribute("dettaglioRichiesta", dettaglioRichiesta);

        model.addAttribute("tariffe", richiesteEJB.getTotaliTariffeRichiesta(id));

        if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(dettaglioRichiesta.getIdTipoRichiesta())) {

          model.addAttribute("listaMerci", richiesteEJB.getListaMerciRichiesta(id));
          model.addAttribute("listaTariffeProdotti", richiesteEJB.getListaTariffeProdotti());

        } else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(dettaglioRichiesta.getIdTipoRichiesta())
            || CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(dettaglioRichiesta.getIdTipoRichiesta())) {

          model.addAttribute("listaMerci", richiesteEJB.getListaMerciRichiestaExport(id));
          model.addAttribute("listaNaturaColli", decodificheEJB.getListaNaturaColli());
          model.addAttribute("listaTariffeExport", richiesteEJB.getTariffeTipoRichiesta(
              dettaglioRichiesta.getIdTipoRichiesta(), CaronteConstants.ID_TIPO_CONTROLLO_FITOSANITARIO));
        }

        model.addAttribute("trattamentoRichiesta", richiesteEJB.getTrattamentoRichiesta(id));
        model.addAttribute("pagamentoRichiesta", richiesteEJB.getPagamentoRichiesta(id));
        model.addAttribute("destinatarioPagamento", decodificheEJB.getCostante("DEST_PAGAM"));
        // model.addAttribute("datiCertificato",
        // richiesteEJB.getDatiCertificatoRichiesta(id));
        model.addAttribute("isSuperUser", SecurityUtils.getUtenteLoggato().isSuperUser());

        form.setIdRichiesta(id);
        form.setIdStatoRichiesta(dettaglioRichiesta.getIdStatoRichiesta());
        setDatiAvanzaRichiesta(model, form);

        if (scheda != null) {
          form.setSchedaSelezionata(scheda.intValue());
        }
      }
    } catch (BusinessException be) {
      messaggioErrore = "Errore nella modifica della richiesta";
    }

    if (messaggioErrore != null) {
      addErrorMessage(messaggioErrore);
      return getRedirect("richieste/elenco", request);
    }

    return "richieste/avanza";
  }

  private void setDatiAvanzaRichiesta(Model model, NuovaRichiestaForm form) throws BusinessException {
    CertificatoRichiestaDto certificato = richiesteEJB.getDatiCertificatoRichiesta(form.getIdRichiesta());
    form.setDatiCertificato(certificato, SecurityUtils.getUtenteLoggato().getIdIspettore());
    form.setCodiceEnte(decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_REGIONE).getValoreCostante());

    model.addAttribute("listaProvinceRegione", decodificheEJB.getListaProvince(CaronteConstants.ID_REGIONE_LOMBARDIA));
    model.addAttribute("listaPuntiEntrata", decodificheEJB.getUfficioDoganale(true));
    model.addAttribute("listaTipiCertificato", decodificheEJB.getListaTipiCertificato());
    model.addAttribute("listaNumerazioniCertificato", getListaNumerazioniCertificato());
    model.addAttribute("listaispettori", utenteEJB.getListaIspettoriRichiesta());

    if (form.getIdProvinciaEsecuzione() != null) {
      model.addAttribute("listaComuniEsecuzione", decodificheEJB.getListaComuni(form.getIdProvinciaEsecuzione()));
    }
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/inoltra/{id}")
  public String inoltraRichiesta(@PathVariable Long id, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_RICHIESTA_INOLTRATA,
        CaronteConstants.DESC_STATO_RICHIESTA_INOLTRATA.toLowerCase());

    return getRedirect("richieste/elenco", request);
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/esegui/{id}")
  public String eseguiRichiesta(@PathVariable Long id, HttpServletRequest request) {
    boolean result = avanza(id, CaronteConstants.STATO_RICHIESTA_ESEGUITA,
        CaronteConstants.DESC_STATO_RICHIESTA_ESEGUITA.toLowerCase());

    if (result) {
      return getRedirect("richieste/avanza/" + id + "/9", request);
    }

    return getRedirect("richieste/elenco", request);
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/liquida/{id}")
  public String liquidaRichiesta(@PathVariable Long id, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_RICHIESTA_LIQUIDATA,
        CaronteConstants.DESC_STATO_RICHIESTA_LIQUIDATA.toLowerCase());

    return getRedirect("richieste/elenco", request);
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/restituisci/{id}")
  public String restituisciRichiesta(@PathVariable Long id, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_RICHIESTA_RESTITUITA,
        CaronteConstants.DESC_STATO_RICHIESTA_RESTITUITA.toLowerCase());

    return getRedirect("richieste/elenco", request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(value = "/restituisci/{id}")
  public String restituisciRichiesta(@PathVariable Long id,
      @RequestParam(value = "motivazione", required = false) String motivazione, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_RICHIESTA_RESTITUITA,
        CaronteConstants.DESC_STATO_RICHIESTA_RESTITUITA.toLowerCase(), motivazione);

    return getRedirect("richieste/elenco", request);
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/annulla/{id}")
  public String annullaRichiesta(@PathVariable Long id, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_RICHIESTA_ANNULLATA,
        CaronteConstants.DESC_STATO_RICHIESTA_ANNULLATA.toLowerCase());

    return getRedirect("richieste/elenco", request);
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(value = "/annulla/{id}")
  public String annullaRichiesta(@PathVariable Long id,
      @RequestParam(value = "motivazione", required = false) String motivazione, HttpServletRequest request) {
    avanza(id, CaronteConstants.STATO_RICHIESTA_ANNULLATA,
        CaronteConstants.DESC_STATO_RICHIESTA_ANNULLATA.toLowerCase(), motivazione);

    return getRedirect("richieste/elenco", request);
  }

  private boolean avanza(Long idRichiesta, Long idStatoSuccessivo, String descStato) {
    return avanza(idRichiesta, idStatoSuccessivo, descStato, null);
  }

  private boolean avanza(Long idRichiesta, Long idStatoSuccessivo, String descStato, String motivazione) {
    String messaggioErrore = null;

    try {
      UtenteDTO utente = SecurityUtils.getUtenteLoggato();

      if (richiesteEJB.avanzaStatoRichiesta(idRichiesta, idStatoSuccessivo, utente.getId(), motivazione)) {
        addSuccessMessage("La richiesta è stata " + descStato);
      } else {
        messaggioErrore = "La richiesta non può essere " + descStato + " dall'utente corrente";
      }
    } catch (BusinessException be) {
      messaggioErrore = "Errore nell'avanzamento dello stato della richiesta";
    }

    if (messaggioErrore != null) {
      addErrorMessage(messaggioErrore);
    }

    return messaggioErrore == null;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/dettaglio/{id}/stampa")
  public String getStampaCertificato(@PathVariable Long id, Model model) throws BusinessException {

    return "richieste/dettaglio";
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/elimina/{id}")
  public String eliminaRichiesta(@PathVariable Long id, Model model, HttpServletRequest request)
      throws BusinessException {
    try {
      DettaglioRichiestaDto richiesta = richiesteEJB.getDettaglioRichiesta(id);
      String messaggioErrore = null;

      if (!CaronteConstants.STATO_RICHIESTA_BOZZA.equals(richiesta.getIdStatoRichiesta())) {
        messaggioErrore = "Solo una richiesta in stato In Bozza può essere eliminata";
      } else {
        UtenteDTO utente = SecurityUtils.getUtenteLoggato();

        if (!utente.isSuperUser() && !utente.getId().equals(richiesta.getIdUtenteInsert())) {
          messaggioErrore = "La richiesta non può essere cancellata dall'utente corrente";
        }
      }

      if (messaggioErrore == null) {
        richiesteEJB.cancellaRichiesta(id);
        addSuccessMessage("La richiesta è stata eliminata");
      } else {
        addErrorMessage(messaggioErrore);
      }

    } catch (BusinessException be) {
      addErrorMessage("Errore nell'eliminazione della richiesta");
    }

    return getRedirect("richieste/elenco", request);
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/copia/{id}")
  public String copiaRichiesta(@PathVariable Long id, Model model, HttpServletRequest request)
      throws BusinessException {
    Long idNuovaRichiesta = null;

    try {
      DettaglioRichiestaDto richiesta = richiesteEJB.getDettaglioRichiesta(id);
      String messaggioErrore = null;

      UtenteDTO utente = SecurityUtils.getUtenteLoggato();
      logger.debug("-- controller copiaRichiesta utente.getId()="+ utente.getId());

      if (!utente.isSuperUser() && !utente.getId().equals(richiesta.getIdUtenteInsert())) {
        messaggioErrore = "La richiesta non può essere copiata dall'utente corrente";
      }

      if (messaggioErrore == null) {
        idNuovaRichiesta = richiesteEJB.copiaRichiesta(id, utente.getId());
        logger.debug("-- controller copiaRichiesta idNuovaRichiesta ="+ idNuovaRichiesta);
        addSuccessMessage("La copia della richiesta è stata creata");
      } else {
        addErrorMessage(messaggioErrore);
      }

    } catch (BusinessException be) {
      addErrorMessage("Errore nella copia della richiesta");
    }

    // return getRedirect("richieste/elenco", request);
    return getRedirect("richieste/modifica/" + idNuovaRichiesta, request);
  }

  // @PreAuthorize("hasRole('WRITE')")
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/modifica/{id}")
  public String modifica(@PathVariable Long id, @ModelAttribute("nuovaRichiestaForm") NuovaRichiestaForm form,
      Model model, HttpServletRequest request) throws BusinessException {
    String messaggioErrore = null;

    try {
      UtenteDTO utente = SecurityUtils.getUtenteLoggato();

      Boolean isUtenteAbilitato = richiesteEJB.isRichiestaModificabile(utente.getId(), id);

      if (isUtenteAbilitato == null) {
        messaggioErrore = "La richiesta non può essere modificata dall'utente corrente";
      } else if (!isUtenteAbilitato) {
        /*
         * L'utente non è abilitato a modificare i dati, ma può avanzare lo
         * stato della richiesta
         */
        return getRedirect("richieste/avanza/" + id, request);
      } else {

        DettaglioRichiestaDto richiesta = richiesteEJB.getDettaglioRichiesta(id);

        setDatiModificaRichiesta(model, form, richiesta, utente, !isRichiestaImport(request));

      }
    } catch (BusinessException be) {
      messaggioErrore = "Errore nella modifica della richiesta";
    }

    if (messaggioErrore != null) {
      addErrorMessage(messaggioErrore);

      return getRedirect("richieste/elenco", request);
    }

    return "richieste/modifica";
  }

  // @PreAuthorize("hasRole('READ')")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/allegato/{idRichiesta}/{nomeFile:.+}", produces = { MediaType.IMAGE_PNG_VALUE,
      MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, "image/bmp" })
  public void getAllegatoPagamento(@PathVariable Long idRichiesta, @PathVariable String nomeFile, Model model,
      HttpServletRequest request, HttpServletResponse response) {
    scaricaAllegatoPagamento(idRichiesta, nomeFile, "inline", response);
  }

  // @PreAuthorize("hasRole('READ')")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/allegato/certificato/{idRichiesta}/{nomeFile:.+}", produces = { MediaType.IMAGE_PNG_VALUE,
      MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, "image/bmp" })
  public void getAllegatoCertificato(@PathVariable Long idRichiesta, @PathVariable String nomeFile, Model model,
      HttpServletRequest request, HttpServletResponse response) {
    scaricaAllegatoCertificato(idRichiesta, nomeFile, "inline", response);
  }

  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/allegato/scarica/{idRichiesta}", produces = { "application/octet-stream" })
  public void downloadAllegatoPagamento(@PathVariable Long idRichiesta, Model model, HttpServletRequest request,
      HttpServletResponse response) {
    try {
      PagamentoRichiestaDto datiPagamento = richiesteEJB.getPagamentoRichiesta(idRichiesta);

      if (!StringUtils.isEmpty(datiPagamento.getNomeFileAllegato())) {
        /*
         * Il filename viene encodato per evitare vulnerabilità di response
         * splitting
         */
        scaricaAllegatoPagamento(idRichiesta, datiPagamento.getNomeFileAllegato(),
            "attachment; filename=" + URLEncoder.encode(datiPagamento.getNomeFileAllegato(), "UTF-8"), response);
      }
    } catch (BusinessException | IOException exc) {
      logger.error("Errore nel reperimento dell'allegato", exc);
    }

  }

  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/allegato/certificato/scarica/{idRichiesta}", produces = { "application/octet-stream" })
  public void downloadAllegatoCertificato(@PathVariable Long idRichiesta, Model model, HttpServletRequest request,
      HttpServletResponse response) {
    try {

      DettaglioRichiestaDto dettaglioRichiesta = richiesteEJB.getDettaglioRichiesta(idRichiesta);

      if (dettaglioRichiesta.getIdAllegato() != null) {
        /*
         * Il filename viene encodato per evitare vulnerabilità di response
         * splitting
         * 
         */
        String nomeFile = richiesteEJB.getNomeAllegatoCertificato(dettaglioRichiesta.getIdAllegato());

        scaricaAllegatoCertificato(idRichiesta, nomeFile,
            "attachment; filename=" + URLEncoder.encode(nomeFile, "UTF-8"), response);
      }
    } catch (BusinessException | IOException exc) {
      logger.error("Errore nel reperimento dell'allegato", exc);
    }
  }

  private void scaricaAllegatoPagamento(Long idRichiesta, String nomeFile, String contentDisposition,
      HttpServletResponse response) {
    Long idUtente = SecurityUtils.getUtenteLoggato().getId();
    byte[] contenuto = new byte[0];
    byte[] buffer = new byte[4096];

    try {
      if (richiesteEJB.isUtenteAbilitatoLetturaRichiesta(idUtente, idRichiesta)) {
        contenuto = richiesteEJB.getAllegatoPagamento(idRichiesta, nomeFile);
      }

      if (contenuto != null) {
        response.resetBuffer();
        OutputStream out = response.getOutputStream();

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", contentDisposition);
        response.setContentLength(contenuto.length);

        for (int i = 0; i < contenuto.length; i += buffer.length) {
          System.arraycopy(contenuto, i, buffer, 0, Math.min(contenuto.length - i, buffer.length));
          out.write(buffer, 0, Math.min(contenuto.length - i, buffer.length));
          out.flush();
        }

        out.close();
      }

    } catch (BusinessException | IOException exc) {
      logger.error("Errore nel reperimento dell'allegato", exc);
    }
  }

  private void scaricaAllegatoCertificato(Long idRichiesta, String nomeFile, String contentDisposition,
      HttpServletResponse response) {
    Long idUtente = SecurityUtils.getUtenteLoggato().getId();
    byte[] contenuto = new byte[0];
    byte[] buffer = new byte[4096];

    try {
      if (richiesteEJB.isUtenteAbilitatoLetturaRichiesta(idUtente, idRichiesta)) {
        contenuto = richiesteEJB.getAllegatoCertificato(idRichiesta);
      }

      if (contenuto != null) {
        response.resetBuffer();
        OutputStream out = response.getOutputStream();

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", contentDisposition);
        response.setContentLength(contenuto.length);

        for (int i = 0; i < contenuto.length; i += buffer.length) {
          System.arraycopy(contenuto, i, buffer, 0, Math.min(contenuto.length - i, buffer.length));
          out.write(buffer, 0, Math.min(contenuto.length - i, buffer.length));
          out.flush();
        }

        out.close();
      }

    } catch (BusinessException | IOException exc) {
      logger.error("Errore nel reperimento dell'allegato", exc);
    }
  }

  /*
   * <%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
   * 
   * <a href="${s:mvcUrl('PC#getPerson').arg(0,"123").build()}">Get Person</a>
   */
  @ModelAttribute("nuovaRichiestaForm")
  public NuovaRichiestaForm getNuovaRichiestaForm(HttpServletRequest request) {
    NuovaRichiestaForm form = new NuovaRichiestaForm();
    UtenteDTO utente = SecurityUtils.getUtenteLoggato();
    form.setIdStatoRichiesta(CaronteConstants.STATO_RICHIESTA_BOZZA);
    form.setDescStatoRichiesta(CaronteConstants.DESC_STATO_RICHIESTA_BOZZA);
    form.setSchedaSelezionata(1);

    if (request.getRequestURI().contains("/import/")) {
      form.setIdTipoRichiesta(CaronteConstants.TIPO_RICHIESTA_IMPORT);
    } else {
      form.setIdTipoRichiesta(CaronteConstants.TIPO_RICHIESTA_EXPORT);
      form.setTipoNumerazioneCertificato(CaronteConstants.NUMERAZIONE_CERTIFICATO_MANUALE);
    }

    if (utente != null) {
      form.setUtenteResponsabile(utente.getId());
      form.setDescUtenteResponsabile(utente.getNome() + " " + utente.getCognome());
      form.setIdSpedizioniere(utente.getIdSpedizioniere());

      try {
        CarTSpedizioniere spedizioniere = utenteEJB.getCarTSpedizioniere(utente.getIdSpedizioniere());

        if (spedizioniere != null) {
          form.setSpedizioniere(spedizioniere.getDenomSpedizioniere());

          CarDTipoSpedizioniere tipoSpedizioniere = decodificheEJB
              .getTipoSpedizioniere(spedizioniere.getIdTipoSpedizioniere());

          if (tipoSpedizioniere != null) {
            form.setDenomTipoSpedizioniere(tipoSpedizioniere.getDenomTipoSpedizioniere());
          }
        }
      } catch (BusinessException exc) {
        logger.error("Errore nel reperimento dei dati dello spedizioniere", exc);
      }
    }

    form.setListaMerceRichiesta(new AutoPopulatingList<>(MerceRichiestaDto.class));
    form.setMittentePagamento(form.getDescUtenteResponsabile());

    try {
      form.setDestinatarioPagamento(
          decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_DESTINATARIO_PAGAMENTO).getValoreCostante());
    } catch (BusinessException exc) {
      logger.error("Errore nel reperimento dei dati del destinatario pagamento", exc);
    }

    return form;
  }

  private void setDatiNuovaRichiesta(Model model, NuovaRichiestaForm form) throws BusinessException {

    /*
     * Nel caso di richiesta Import, l'Italia deve essere esclusa dall'elenco di
     * nazioni, mentre deve essere inclusa nell'Export
     */
    model.addAttribute("listaNazioni",
        decodificheEJB.getListaNazioni(CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(form.getIdTipoRichiesta())));
    model.addAttribute("listaProvince", decodificheEJB.getListaProvince());

    if (form.getIdProvincia() != null) {
      model.addAttribute("listaComuni", decodificheEJB.getListaComuni(form.getIdProvincia()));
    } else if (form.getIdProvinciaMittente() != null) {
      model.addAttribute("listaComuni", decodificheEJB.getListaComuni(form.getIdProvinciaMittente()));
    }

    model.addAttribute("listaRegioni", decodificheEJB.getListaRegioni());
    model.addAttribute("listaModiTrasporto", decodificheEJB.getListaModiTrasporto());
    model.addAttribute("listaPuntiEntrata", decodificheEJB.getUfficioDoganale(true));
    // model.addAttribute("listaGeneriMerce",
    // generiSpecieEJB.getListaGeneri(/*LocaleContextHolder.getLocale().getLanguage()*/CaronteConstants.CODICE_LINGUA_LATINO));
    model.addAttribute("listaProdotti", decodificheEJB.getListaProdotti());

    model.addAttribute("listaTrattamenti", decodificheEJB.getListaTrattamenti());

    model.addAttribute("listaTipiCertificato", decodificheEJB.getListaTipiCertificato());
    model.addAttribute("listaispettori", utenteEJB.getListaIspettoriRichiesta());
    model.addAttribute("listaNumerazioniCertificato", getListaNumerazioniCertificato());
    model.addAttribute("listaMezziPagamento", decodificheEJB.getListaMezziPagamento());

    if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(form.getIdTipoRichiesta())
        || CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(form.getIdTipoRichiesta())) {
      model.addAttribute("listaNaturaColli", decodificheEJB.getListaNaturaColli());
      model.addAttribute("listaProvinceRegione",
          decodificheEJB.getListaProvince(CaronteConstants.ID_REGIONE_LOMBARDIA));

      if (form.getIdProvinciaEsecuzione() != null) {
        model.addAttribute("listaComuniEsecuzione", decodificheEJB.getListaComuni(form.getIdProvinciaEsecuzione()));
      }

      model.addAttribute("listaTariffeExport", richiesteEJB.getTariffeTipoRichiesta(form.getIdTipoRichiesta(),
          CaronteConstants.ID_TIPO_CONTROLLO_FITOSANITARIO));

      if (CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(form.getIdTipoRichiesta())) {
        model.addAttribute("listaTipiCertificatoPrecedente", getListaTipiCertificatoPrecedente());
        model.addAttribute("listaTipiImballaggi", decodificheEJB.getListaTipiImballaggi());
      }
    } else {
      model.addAttribute("listaTariffeProdotti", richiesteEJB.getListaTariffeProdotti());
    }
  }

  private void setDatiModificaRichiesta(Model model, NuovaRichiestaForm form, DettaglioRichiestaDto richiesta,
      UtenteDTO utente, boolean isExport) throws BusinessException {
    form.setDati(richiesta, richiesteEJB.getTrattamentoRichiesta(richiesta.getIdRichiesta()),
        richiesteEJB.getDatiCertificatoRichiesta(richiesta.getIdRichiesta()),
        richiesteEJB.getPagamentoRichiesta(richiesta.getIdRichiesta()), utente.getIdIspettore(),
        richiesta.getIdAllegato() != null ? richiesteEJB.getNomeAllegatoCertificato(richiesta.getIdAllegato()) : null);
    this.setDatiNuovaRichiesta(model, form);

    if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(form.getIdTipoRichiesta())) {

      form.setListaMerceRichiesta(new AutoPopulatingList<>(richiesteEJB.getListaMerciRichiesta(form.getIdRichiesta()),
          MerceRichiestaDto.class));

    } else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(form.getIdTipoRichiesta())
        || CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(form.getIdTipoRichiesta())) {

      form.setListaMerceRichiesta(new AutoPopulatingList<>(
          richiesteEJB.getListaMerciRichiestaExport(form.getIdRichiesta()), MerceRichiestaDto.class));
    }

    setDatiTariffe(form, richiesteEJB.getTotaliTariffeRichiesta(form.getIdRichiesta()));

    form.setCodiceEnte(decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_REGIONE).getValoreCostante());

    form.setAbilitaInoltra(checkAbilitaInoltra(utente.getId(), richiesta, true));
  }

  private void setDatiTariffe(NuovaRichiestaForm form, TariffeRichiestaDto tariffe) {
    form.setTariffaIdentita(tariffe.getTotaleIdentita());
    form.setTariffaDocumentali(tariffe.getTotaleDocumentale());
    form.setTariffaFitosanitari(tariffe.getTotaleFitosanitario());
    form.setMassimalePartite(tariffe.getMassimaleTariffa());
    form.setNumeroCertificati(tariffe.getNumeroCertificati());
    form.setTariffaTotale(tariffe.getTotaleTariffe());
    form.setImportoTotaleDovuto(tariffe.getTotaleTariffe());
  }

  private String getViewNuovaModifica(HttpServletRequest request) {
    String requestURI = request == null ? "" : request.getRequestURI();

    if (requestURI.contains("/modifica")) {
      return "richieste/modifica";
    } else {
      return "richieste/nuova";
    }
  }

  private boolean checkModificaRichiesta(Long idUtente, NuovaRichiestaForm form) throws BusinessException {
    Boolean isUtenteAbilitato = richiesteEJB.isRichiestaModificabile(idUtente, form.getIdRichiesta());

    if (isUtenteAbilitato == null || !isUtenteAbilitato) {
      addErrorMessage("La richiesta non può essere modificata dall'utente corrente");
      return false;
    }

    return true;
  }

  private List<DecodificaDTO<String>> getListaTipiCertificatoPrecedente() throws BusinessException {
    List<DecodificaDTO<String>> listaTipiCertificato = new ArrayList<>();
    listaTipiCertificato.add(new DecodificaDTO<String>(Boolean.FALSE.toString(),
        decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_CC_ORIGINALE).getValoreCostante()));
    listaTipiCertificato.add(new DecodificaDTO<String>(Boolean.TRUE.toString(),
        decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_CC_COPIA).getValoreCostante()));
    return listaTipiCertificato;
  }

  private List<DecodificaDTO<String>> getListaNumerazioniCertificato() {
    List<DecodificaDTO<String>> listaNumerazioni = new ArrayList<>();
    listaNumerazioni.add(new DecodificaDTO<String>(CaronteConstants.NUMERAZIONE_CERTIFICATO_NESSUNA, "Non numerare"));
    listaNumerazioni
        .add(new DecodificaDTO<String>(CaronteConstants.NUMERAZIONE_CERTIFICATO_MANUALE, "Numerazione manuale"));
    listaNumerazioni
        .add(new DecodificaDTO<String>(CaronteConstants.NUMERAZIONE_CERTIFICATO_AUTOMATICA, "Numerazione automatica"));
    return listaNumerazioni;
  }

  private boolean checkAbilitaInoltra(Long idUtente, DettaglioRichiestaDto dettaglioRichiesta, boolean controllaStato)
      throws BusinessException {
    return checkAbilitaInoltra(idUtente, null, dettaglioRichiesta, controllaStato, null);
  }

  private boolean checkAbilitaInoltra(Long idUtente, Long idRichiesta, TariffeRichiestaDto tariffe)
      throws BusinessException {
    return checkAbilitaInoltra(idUtente, idRichiesta, null, true, tariffe);
  }

  private boolean checkAbilitaInoltra(Long idUtente, Long idRichiesta, DettaglioRichiestaDto dettaglioRichiesta,
      boolean controllaStato, TariffeRichiestaDto tariffe) throws BusinessException {
    boolean abilitaInoltra = !controllaStato;

    if (dettaglioRichiesta == null) {
      if (idRichiesta == null) {
        return false;
      }

      dettaglioRichiesta = richiesteEJB.getDettaglioRichiesta(idRichiesta);
    }

    if (controllaStato) {
      List<CarDStatoRichiesta> listaStatiSuccessivi = richiesteEJB.getListaStatiRichiestaSuccessivi(idUtente,
          dettaglioRichiesta.getIdRichiesta());

      for (CarDStatoRichiesta stato : listaStatiSuccessivi) {
        if (CaronteConstants.STATO_RICHIESTA_INOLTRATA.equals(stato.getIdStatoRichiesta())) {
          abilitaInoltra = true;
          break;
        }
      }
    }

    /*
     * Si può inoltrare la richiesta senza pagamento nel caso l'importo totale
     * della richiesta sia zero, oppure se lo spedizioniere sia autorizzato al
     * pagamento posticipato
     */
    boolean pagamentoMancante = false;

    if (dettaglioRichiesta.getIdPagamento() == null) {
      pagamentoMancante = !dettaglioRichiesta.isAutorizPagamPosticip();

      if (pagamentoMancante) {

        if (tariffe == null) {
          tariffe = richiesteEJB.getTotaliTariffeRichiesta(dettaglioRichiesta.getIdRichiesta());
        }

        pagamentoMancante = tariffe.getTotaleTariffe() == null
            || BigDecimal.ZERO.compareTo(tariffe.getTotaleTariffe()) != 0;
      }
    }

    return abilitaInoltra && !StringUtils.isEmpty(dettaglioRichiesta.getDenomMittente())
        && !StringUtils.isEmpty(dettaglioRichiesta.getDenomDestinatario())
        && !StringUtils.isEmpty(dettaglioRichiesta.getIdModoTrasporto()) && dettaglioRichiesta.getNumeroMerci() > 0
        && !pagamentoMancante;
  }

}
