package it.aizoon.ersaf.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import it.aizoon.ersaf.business.IControlliEJB;
import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IDomandeEJB;
import it.aizoon.ersaf.business.IReportEJB;
import it.aizoon.ersaf.business.IRichiesteEJB;
import it.aizoon.ersaf.dto.CentroAziendaleDomandaDTO;
import it.aizoon.ersaf.dto.CertificatoRichiestaDto;
import it.aizoon.ersaf.dto.DettaglioRichiestaDto;
import it.aizoon.ersaf.dto.MerceRichiestaDto;
import it.aizoon.ersaf.dto.MerceRichiestaVerbaleDto;
import it.aizoon.ersaf.dto.ReportDTO;
import it.aizoon.ersaf.dto.TariffeRichiestaDto;
import it.aizoon.ersaf.dto.TipologiaDomandaDTO;
import it.aizoon.ersaf.dto.generati.CarDCostante;
import it.aizoon.ersaf.dto.generati.CarRCampionamentoPartita;
import it.aizoon.ersaf.dto.generati.CarREsitoTipoControllo;
import it.aizoon.ersaf.dto.generati.CarTDatiDomanda;
import it.aizoon.ersaf.dto.generati.CarTDomanda;
import it.aizoon.ersaf.dto.generati.CarTVerbaleIspezione;
import it.aizoon.ersaf.dto.stampe.EverdeDTO;
import it.aizoon.ersaf.dto.stampe.FitosanitarioExportDTO;
import it.aizoon.ersaf.dto.stampe.FitosanitarioRiexportDTO;
import it.aizoon.ersaf.dto.stampe.NullaOstaDTO;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.DatiVerbaleForm;
import it.aizoon.ersaf.form.RicercaReportAutorizzazioniForm;
import it.aizoon.ersaf.form.RicercaReportControlliForm;
import it.aizoon.ersaf.form.RicercaReportForm;
import it.aizoon.ersaf.form.RicercaReportVivaiForm;
import it.aizoon.ersaf.security.SecurityUtils;
import it.aizoon.ersaf.stampe.StampeManager;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.validator.DatiVerbaleValidator;

@Controller
@RequestMapping(value = { "/import/report", "/export/report", "/vivai/report", "/autorizzazioni/report", "/controlli/report", "/registrazione/report"})
@SessionAttributes("datiVerbaleForm")
public class ReportController extends BaseController {

  @Autowired
  private IRichiesteEJB richiesteEJB;

  @Autowired
  private IReportEJB reportEJB;

  @Autowired
  private IDecodificheEJB decodificheEJB = null;

  @Autowired
  private DatiVerbaleValidator verbaleValidator;
  
  @Autowired
  private IDomandeEJB domandeEJB = null;
  
  @Autowired
  private IControlliEJB controlliEJB = null;


  // @PreAuthorize("hasRole('READ')")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/elenco")
  public String elenco(@ModelAttribute("ricercaReportForm") RicercaReportForm ricercaReportForm, ModelMap model,
      HttpSession session, HttpServletRequest request) throws BusinessException {
    try {
      // Report Vivai	
      if(isRichiestaVivai(request)){
    	logger.debug("-- Richiesta REPORT VIVAI");          
        model.remove("elencoReport");
      }
      // Report Import ed Export
      else{	
        setCommonReportModel(model, isRichiestaImport(request));
      }              
      session.removeAttribute("elencoReportPerExcel");      
    } catch (BusinessException exc) {
      addErrorMessage("Errore nella ricerca dei report");
    }

    return "report/elenco";
  }
  
  
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/cercaVivai")
  public String cercaVivai(ModelMap model, @ModelAttribute("ricercaReportForm") @Valid RicercaReportVivaiForm ricercaReportForm,
      BindingResult result, HttpSession session, HttpServletRequest request) throws BusinessException {
    try {
      if (result.getErrorCount() == 0) {           	    	    	
    	String specie = ricercaReportForm.getSpecie();
    	logger.debug("specie ="+specie);
    	 
    	List<Integer> idSpecieList = null;
        if(specie != null && !specie.equals("")){
    	  idSpecieList = new ArrayList<Integer>();	  
    	  String[] separated = specie.split(",");	  
    	  Integer[] numbers = new Integer[separated.length];
    	  for(int i = 0;i < separated.length;i++)        {
    	      try {
    	        numbers[i] = Integer.parseInt(separated[i]);
    	      }
    	      catch (NumberFormatException nfe)   {
    	        numbers[i] = null;
    	      }
    	  }	  
    	  idSpecieList = Arrays.asList(numbers);
        }	  
        
        ricercaReportForm.setIdSpecieList(idSpecieList);
    	
        logger.debug("-- Ricerca comunicazioni vivai per REPORT");
        List<ReportDTO> elencoReport = reportEJB.getElencoReportVivai(ricercaReportForm);
        List<ReportDTO> elencoReportNoOn = reportEJB.getElencoReportVivaiNoOn(ricercaReportForm);
        //il no on permetti di avere un elenco delle aziende slegati dagli on.(evita la ripetizione nell' excel di righe con uguali generi, specie, ecc)
        if(elencoReport !=null){
          logger.debug("-- numero di comunicazioni trovate per il Report ="+elencoReport.size());
        }
        ricercaReportForm.setElencoReport(elencoReport);
        
        model.addAttribute("elencoReport", elencoReport);
        session.setAttribute("elencoReportPerExcel", elencoReportNoOn);
	    // ripopolo la lista organismi nocivi
	    model.addAttribute("listaOrgNocivo", decodificheEJB.getListaOrganismiNocivi());
      } else {
    	  logger.debug("errore di validazione ="+result.getFieldError());
    	  addErrorMessage("Errore di validazione dei campi");
      }
    	  
    } catch (BusinessException exc) {
      addErrorMessage("Errore nella ricerca dei report");
    }

    return "report/elencoSpecie";
  }

  // @PreAuthorize("hasRole('READ')")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/cerca")
  public String cerca(ModelMap model, @ModelAttribute("ricercaReportForm") @Valid RicercaReportForm ricercaReportForm,
      BindingResult result, HttpSession session, HttpServletRequest request) throws BusinessException {
    try {
      if (result.getErrorCount() == 0) {
        ricercaReportForm.setCodTipoRichiesta(getTipoRichiesta(request));
        List<ReportDTO> elencoReport = reportEJB.getElencoReport(ricercaReportForm);
        ricercaReportForm.setElencoReport(elencoReport);
        if (!elencoReport.isEmpty()) {
          Double totaleTariffa = elencoReport.stream().mapToDouble(report -> report.getTariffa().doubleValue()).sum();
          model.addAttribute("totaleTariffa", totaleTariffa);
        }
        model.addAttribute("elencoReport", elencoReport);
        session.setAttribute("elencoReportPerExcel", elencoReport);
      }

      setCommonReportModel(model, isRichiestaImport(request));
    } catch (BusinessException exc) {
      addErrorMessage("Errore nella ricerca dei report");
    }

    return "report/elenco";
  }
  
  
  /*
   *  Metodo che implementa l'Esporta Vivai
   */
  @SuppressWarnings("unchecked")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "esportaVivai")
  public ModelAndView downloadExcelVivai(ModelMap model, HttpSession session, HttpServletRequest request) throws BusinessException {
	    List<ReportDTO> elencoReport = null;
	    try {
	      /*
	       * Nel remotissimo caso, tendenzialmente impossibile, che possa arrivare
	       * una classcastexception..
	       */
	      elencoReport = (List<ReportDTO>) session.getAttribute("elencoReportPerExcel");
	    } 
	    catch (Exception exc) {
	      logger.error("-- Exception in downloadExcelVivai = "+exc.getMessage());
	      addErrorMessage("Errore nell'esportazione report");
	    }

	    ModelAndView modelAndView = new ModelAndView("excelReportVivaiView", "elenco", elencoReport != null ? elencoReport : new ArrayList<ReportDTO>());
	    
	    return modelAndView;
  }

  /*
   * Metodo per la stampa Vivai nella sezione Report
   */
  @SuppressWarnings("unchecked")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "stampaVivai")
  public ModelAndView downloadPdfVivai(ModelMap model, HttpSession session, HttpServletRequest request) throws BusinessException {
    List<ReportDTO> elencoReport = null;
    try {
      /*
       * Nel remotissimo caso, tendenzialmente impossibile, che possa arrivare
       * una classcastexception..
       */
      elencoReport = (List<ReportDTO>) session.getAttribute("elencoReportPerExcel");
    } 
    catch (Exception exc) {
      logger.error("-- Exception in downloadPdfVivai = "+exc.getMessage());
      addErrorMessage("Errore nella stampa report");
    }

    ModelAndView modelAndView = new ModelAndView("pdfElencoReportVivaiView", "elenco",
        elencoReport != null ? elencoReport : new ArrayList<ReportDTO>());
    modelAndView.addObject("codTipoRichiesta", getTipoRichiesta(request));
    return modelAndView;
  }
  

  // @PreAuthorize("hasRole('READ')")
  @SuppressWarnings("unchecked")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "esporta")
  public ModelAndView downloadExcel(ModelMap model, HttpSession session, HttpServletRequest request)
      throws BusinessException {
    List<ReportDTO> elencoReport = null;
    try {
      /*
       * Nel remotissimo caso, tendenzialmente impossibile, che possa arrivare
       * una classcastexception..
       */
      elencoReport = (List<ReportDTO>) session.getAttribute("elencoReportPerExcel");
    } catch (Exception exc) {
      addErrorMessage("Errore nell'esportazione report");
    }

    ModelAndView modelAndView = new ModelAndView("excelElencoReportView", "elenco",
        elencoReport != null ? elencoReport : new ArrayList<ReportDTO>());
    modelAndView.addObject("codTipoRichiesta", getTipoRichiesta(request));
    return modelAndView;
  }

  // @PreAuthorize("hasRole('READ')")
  @SuppressWarnings("unchecked")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "stampa")
  public ModelAndView downloadPdf(ModelMap model, HttpSession session, HttpServletRequest request)
      throws BusinessException {
    List<ReportDTO> elencoReport = null;
    try {
      /*
       * Nel remotissimo caso, tendenzialmente impossibile, che possa arrivare
       * una classcastexception..
       */
      elencoReport = (List<ReportDTO>) session.getAttribute("elencoReportPerExcel");
    } catch (Exception exc) {
      addErrorMessage("Errore nella stampa report");
    }

    ModelAndView modelAndView = new ModelAndView("pdfElencoReportView", "elenco",
        elencoReport != null ? elencoReport : new ArrayList<ReportDTO>());
    modelAndView.addObject("codTipoRichiesta", getTipoRichiesta(request));
    return modelAndView;
  }

  
  
  /**
   * Mette nel model tutto ciò che è necessario per il funzionamento dei report
   * (decodifiche e/o altro)
   * 
   * Utilizzato per i report di Import ed Export
   */
  private void setCommonReportModel(ModelMap model, boolean isImport) throws BusinessException {    		
		model.addAttribute("statoRichiesta", richiesteEJB.getStatoRichiesta());
	    model.addAttribute("spedizionieri", decodificheEJB.getListaSpedizionieri(
	        isImport ? CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT : CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT));
	
	    Locale locale = LocaleContextHolder.getLocale();
	    Map<Integer, String> mesi = new HashMap<Integer, String>();
	
	    for (Month m : Month.values()) {
	      mesi.put(m.getValue(), m.getDisplayName(TextStyle.FULL_STANDALONE, locale));
	    }
	
	    model.addAttribute("mesi", mesi);
	    // model.addAttribute("ricercaReportForm", ricercaReportForm);	
  }

  /* Download PDF Nulla Osta */
  // @PreAuthorize("hasRole('READ')")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/esporta/nullaosta/{idRichiesta}", produces = "application/pdf")
  public void downloadPdfNullaOsta(@PathVariable Long idRichiesta, Model model, HttpServletRequest request,
      HttpServletResponse response) throws BusinessException {

    try {
      NullaOstaDTO nullaOsta = reportEJB.getPdfNullaOsta(idRichiesta);
      logger.debug("-------------------------nullaOstaDTO: " + nullaOsta.toString());
      // Set the input stream
      ByteArrayOutputStream data = StampeManager.getPdfNullaOsta(nullaOsta);
      // asume that it was a PDF file

      response.setContentType("application/pdf");
      response.setHeader("Content-Disposition", "attachment; filename=reportNullaOsta" + idRichiesta + ".pdf");
      response.setContentLength(data.toByteArray().length);
      response.getOutputStream().write(data.toByteArray());
      response.getOutputStream().flush();

    } catch (IOException e) {
      addErrorMessage("Errore nella generazione del pdf");
    }
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = { "/verbale/{idRichiesta}" })
  public String inserisciDatiVerbaleIspezione(@PathVariable Long idRichiesta,
      @ModelAttribute("datiVerbaleForm") DatiVerbaleForm form, Model model, HttpServletRequest request)
      throws BusinessException {

    CertificatoRichiestaDto certificato = richiesteEJB.getDatiCertificatoRichiesta(idRichiesta);

    if (null != certificato) {

      CarTVerbaleIspezione carTVerbaleIspezione = reportEJB.getVerbaleIspezione(certificato.getIdCertificato());

      if (null != carTVerbaleIspezione) {

        List<CarREsitoTipoControllo> esitoTipoControllo = reportEJB
            .getElencoEsitoTipoControllo(carTVerbaleIspezione.getIdVerbaleIspezione());

        initDatiVerbaleForm(carTVerbaleIspezione, esitoTipoControllo, form);
      }

      List<MerceRichiestaDto> merciList = richiesteEJB.getListaMerciRichiesta(idRichiesta);
      initDatiMerci(form, merciList);

      form.setIdCertificato(certificato.getIdCertificato());
      form.setMagazzinoDoganale(certificato.getLuogoEsecuzione());
      DettaglioRichiestaDto dettaglioRichiesta = richiesteEJB.getDettaglioRichiesta(idRichiesta);
      form.setIdRichiesta(idRichiesta);
      form.setCodRichiesta(dettaglioRichiesta.getCodRichiesta() + "/IMP");
      form.setNumeroCertificato(dettaglioRichiesta.getNumeroCertificato());
    }

    return "richieste/verbale";
  }

  /* Download PDF Verbale D'Ispezione */
  // @PreAuthorize("hasRole('READ')")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/verbale/{idRichiesta}/stampa", produces = "application/pdf")
  public void downloadPdfVerbale(@PathVariable Long idRichiesta, Model model, HttpServletRequest request,
      HttpServletResponse response) throws BusinessException {

    try {
      EverdeDTO everde = reportEJB.getPdfEverde(idRichiesta);

      // Set the input stream
      ByteArrayOutputStream data = StampeManager.getPdfEverde(everde);

      // assume that it was a PDF file
      response.setContentType("application/pdf");
      response.setHeader("Content-Disposition", "attachment; filename=verbaleIspezione" + ".pdf");
      response.setContentLength(data.toByteArray().length);
      response.getOutputStream().write(data.toByteArray());
      response.getOutputStream().flush();

    } catch (IOException e) {
      addErrorMessage("Errore nella generazione del pdf");
    }
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiIspezione", value = { "/verbale/{idRichiesta}" })
  public String salvaDatiIspezioneVerbale(@PathVariable Long idRichiesta, Model model,
      @ModelAttribute("datiVerbaleForm") DatiVerbaleForm form, BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws BusinessException {

    verbaleValidator.validateDatiIspezione(form, result);
    form.setAccordionSelezionato("1");

    if (result.getErrorCount() != 0) {
      // addErrorMessage("Errore nel salvataggio delle informazioni");
      form.setAccordionSelezionato("0");
    } else {
      if (null != form.getIdVerbale()) {
        reportEJB.updateDatiIspezioneVerbale(form, SecurityUtils.getUtenteLoggato().getId());
      } else {
        // insert
        Long idVerbaleIspezione = reportEJB.inserisciVerbaleIspezione(form, SecurityUtils.getUtenteLoggato().getId());

        form.setIdVerbale(new Long(idVerbaleIspezione));
      }
      
      addSuccessMessage("Dati Ispezione salvati");
    }

    return "richieste/verbale";
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiSpedizione", value = { "/verbale/{idRichiesta}" })
  public String salvaDatiSpedizioneVerbale(@PathVariable Long idRichiesta, Model model,
      @ModelAttribute("datiVerbaleForm") DatiVerbaleForm form, BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws BusinessException {

    form.setAccordionSelezionato("1");

    // check campioni
    /*
     * Si rimuovono dalla lista i record che non hanno corrispondenza nei valori
     * passati in request, dato che Spring non rimuove automaticamente dalla
     * lista i record cancellati dall'utente
     */
    if (null != form.getListaMerceRichiesta() && form.getListaMerceRichiesta().size() > 0) {
      if (null != form.getListaMerceRichiesta() && form.getListaMerceRichiesta().size() > 0) {
        Iterator<MerceRichiestaVerbaleDto> iterMerci = form.getListaMerceRichiesta().iterator();
        for (int i = 0; iterMerci.hasNext(); i++) {
          MerceRichiestaVerbaleDto merce = iterMerci.next();
          if (request.getParameter("listaMerceRichiesta[" + i + "].idOrdMerce") == null) {
            iterMerci.remove();
          }
        }
      }
    }

    verbaleValidator.validateDatiSpedizione(form, result);

    if (result.getErrorCount() != 0) {
      addErrorMessage("Errore nel salvataggio delle informazioni");
    } else {
      if (null != form.getIdVerbale()) {

        reportEJB.updateDatiSpedizioneVerbale(form, SecurityUtils.getUtenteLoggato().getId());
      } else {
        // insert
        Long idVerbaleIspezione = reportEJB.inserisciVerbaleIspezione(form, SecurityUtils.getUtenteLoggato().getId());

        form.setIdVerbale(Long.valueOf(idVerbaleIspezione));
      }

      addSuccessMessage("Dati Spedizione salvati");
    }

    return "richieste/verbale";
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiControlloEsito", value = { "/verbale/{idRichiesta}" })
  public String salvaDatiControlloEsitoVerbale(@PathVariable Long idRichiesta, Model model,
      @ModelAttribute("datiVerbaleForm") DatiVerbaleForm form, HttpServletRequest request, HttpServletResponse response,
      BindingResult result) throws BusinessException {

    if ("S".equals(form.getTermIspezioneNullaOsta())) {
      form.setTermIspezioneNullaOstaNote(form.getNumeroCertificato());
    } else if (null == form.getTermIspezioneNullaOsta()) {
      form.setTermIspezioneNullaOstaNote(null);
    }
    verbaleValidator.validateDatiControlloEsito(form, result);
    form.setAccordionSelezionato("2");

    if (result.getErrorCount() != 0) {
      addErrorMessage("Errore nel salvataggio delle informazioni");
      form.setAccordionSelezionato("2");
    } else {
      verbaleValidator.validateDatiIspezione(form, result);
      if (result.getErrorCount() != 0) {
        addErrorMessage("Errore nel salvataggio delle informazioni");
        form.setAccordionSelezionato("0");
      } else {
        if (null != form.getIdVerbale()) {
          reportEJB.updateDatiControlloEsitoVerbale(form, SecurityUtils.getUtenteLoggato().getId());
        } else {
          // insert
          Long idVerbaleIspezione = reportEJB.inserisciVerbaleIspezione(form, SecurityUtils.getUtenteLoggato().getId());

          form.setIdVerbale(new Long(idVerbaleIspezione));
        }
        addSuccessMessage("Dati Controllo ed Esito salvati");
      }
    }

    return "richieste/verbale";
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiMisureUfficiali", value = { "/verbale/{idRichiesta}" })
  public String salvaDatiMisureUfficialiVerbale(@PathVariable Long idRichiesta, Model model,
      @ModelAttribute("datiVerbaleForm") DatiVerbaleForm form, HttpServletRequest request, HttpServletResponse response,
      BindingResult result) throws BusinessException {

    verbaleValidator.validateDatiMisureUfficiali(form, result);
    form.setAccordionSelezionato("3");

    if (result.getErrorCount() != 0) {
      addErrorMessage("Errore nel salvataggio delle informazioni");
      form.setAccordionSelezionato("3");
    } else {
      verbaleValidator.validateDatiIspezione(form, result);
      if (result.getErrorCount() != 0) {
        addErrorMessage("Errore nel salvataggio delle informazioni");
        form.setAccordionSelezionato("0");
      } else {
        if (null != form.getIdVerbale()) {
          reportEJB.updateDatiMisureUfficialiVerbale(form, SecurityUtils.getUtenteLoggato().getId());
        } else {
          // insert
          Long idVerbaleIspezione = reportEJB.inserisciVerbaleIspezione(form, SecurityUtils.getUtenteLoggato().getId());

          form.setIdVerbale(new Long(idVerbaleIspezione));
        }
        addSuccessMessage("Dati Misure Ufficiali salvati");
      }
    }

    return "richieste/verbale";
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiCustodia", value = { "/verbale/{idRichiesta}" })
  public String salvaDatiCustodiaVerbale(@PathVariable Long idRichiesta, Model model,
      @ModelAttribute("datiVerbaleForm") DatiVerbaleForm form, HttpServletRequest request, HttpServletResponse response,
      BindingResult result) throws BusinessException {

    verbaleValidator.validateDatiCustodia(form, result);
    form.setAccordionSelezionato("4");

    if (result.getErrorCount() != 0) {
      addErrorMessage("Errore nel salvataggio delle informazioni");
      form.setAccordionSelezionato("4");
    } else {
      verbaleValidator.validateDatiIspezione(form, result);
      if (result.getErrorCount() != 0) {
        addErrorMessage("Errore nel salvataggio delle informazioni");
        form.setAccordionSelezionato("0");
      } else {
        if (null != form.getIdVerbale()) {
          reportEJB.updateDatiCustodiaVerbale(form, SecurityUtils.getUtenteLoggato().getId());
        } else {
          // insert
          Long idVerbaleIspezione = reportEJB.inserisciVerbaleIspezione(form, SecurityUtils.getUtenteLoggato().getId());

          form.setIdVerbale(new Long(idVerbaleIspezione));
        }
        addSuccessMessage("Dati Custodia salvati");
      }
    }

    return "richieste/verbale";
  }

  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(params = "datiDichiarazioniNote", value = { "/verbale/{idRichiesta}" })
  public String salvaDatiDichiarazioniNoteVerbale(@PathVariable Long idRichiesta, Model model,
      @ModelAttribute("datiVerbaleForm") DatiVerbaleForm form, HttpServletRequest request, HttpServletResponse response,
      BindingResult result) throws BusinessException {

    verbaleValidator.validateDatiDichiarazioniNote(form, result);
    form.setAccordionSelezionato("5");

    if (result.getErrorCount() != 0) {
      addErrorMessage("Errore nel salvataggio delle informazioni");
      form.setAccordionSelezionato("5");
    } else {
      verbaleValidator.validateDatiIspezione(form, result);
      if (result.getErrorCount() != 0) {
        addErrorMessage("Errore nel salvataggio delle informazioni");
        form.setAccordionSelezionato("0");
      } else {
        if (null != form.getIdVerbale()) {
          reportEJB.updateDatiDichiarazioniNoteVerbale(form, SecurityUtils.getUtenteLoggato().getId());
        } else {
          // insert
          Long idVerbaleIspezione = reportEJB.inserisciVerbaleIspezione(form, SecurityUtils.getUtenteLoggato().getId());

          form.setIdVerbale(new Long(idVerbaleIspezione));
        }
        addSuccessMessage("Dati Dichiarazioni e Note salvati");
      }
    }

    return "richieste/verbale";
  }

  private void initDatiVerbaleForm(CarTVerbaleIspezione carTVerbaleIspezione,
      List<CarREsitoTipoControllo> listaEsitoTipoControllo, DatiVerbaleForm form) {

    form.setIdVerbale(carTVerbaleIspezione.getIdVerbaleIspezione());
    form.setDataIspezione(carTVerbaleIspezione.getDataIspezione());
    form.setOraInizioIspezione(carTVerbaleIspezione.getOraInizioIspezione().toString());
    form.setOraFineIspezione(carTVerbaleIspezione.getOraFineIspezione().toString());
    form.setMagazzinoDoganale(carTVerbaleIspezione.getMagazzinoDoganale());
    form.setPersonaRiferimento(carTVerbaleIspezione.getDelegatoPresenteIspezione());
    form.setPersonaRiferimentoRuolo(carTVerbaleIspezione.getDelegatoPresenteRuolo());

    // Dati Spedizione
    form.setTipoProdottoPianteVive(
        null != carTVerbaleIspezione.getFlgPianteVive() ? carTVerbaleIspezione.getFlgPianteVive() : false);
    form.setTipoProdottoPartiPianteVive(
        null != carTVerbaleIspezione.getFlgPartiPianteVive() ? carTVerbaleIspezione.getFlgPartiPianteVive() : false);
    form.setTipoProdottoSementi(
        null != carTVerbaleIspezione.getFlgSementi() ? carTVerbaleIspezione.getFlgSementi() : false);
    form.setTipoProdottoSemi(null != carTVerbaleIspezione.getFlgSemi() ? carTVerbaleIspezione.getFlgSemi() : false);
    form.setTipoProdottoTerra(
        null != carTVerbaleIspezione.getFlgTerraTerriccio() ? carTVerbaleIspezione.getFlgTerraTerriccio() : false);
    form.setTipoProdottoCorteccia(
        null != carTVerbaleIspezione.getFlgCorteccia() ? carTVerbaleIspezione.getFlgCorteccia() : false);
    form.setTipoProdottoLegname(
        null != carTVerbaleIspezione.getFlgLegname() ? carTVerbaleIspezione.getFlgLegname() : false);
    form.setTipoProdottoFrutti(
        null != carTVerbaleIspezione.getFlgFrutti() ? carTVerbaleIspezione.getFlgFrutti() : false);
    form.setTipoProdottoFiori(
        null != carTVerbaleIspezione.getFlgFioriRecisi() ? carTVerbaleIspezione.getFlgFioriRecisi() : false);
    form.setTipoProdottoImballaggi(
        null != carTVerbaleIspezione.getFlgImbalLegno() ? carTVerbaleIspezione.getFlgImbalLegno() : false);
    form.setTipoProdottoAltro(null != carTVerbaleIspezione.getFlgAltro() ? carTVerbaleIspezione.getFlgAltro() : false);
    form.setTipoProdottoAltroNote(carTVerbaleIspezione.getAltraTipologiaProdotto());

    // Dati Controllo ed Esito
    if (null != listaEsitoTipoControllo && listaEsitoTipoControllo.size() > 0) {
      for (CarREsitoTipoControllo esitoTipoControllo : listaEsitoTipoControllo) {
        if (CaronteConstants.ID_TIPO_CONTROLLO_DOCUMENTALE.equals(esitoTipoControllo.getIdTipoControllo())) {
          form.setControlloDocumentale(true);
          form.setControlloDocumentaleCB(
              null != esitoTipoControllo.getEsitoFavorevole() && esitoTipoControllo.getEsitoFavorevole() ? "S" : "N");
          form.setNoteControlloDocumentaleCB(esitoTipoControllo.getMotivoEsitoNonFavorevole());
        } else if (CaronteConstants.ID_TIPO_CONTROLLO_IDENTITA.equals(esitoTipoControllo.getIdTipoControllo())) {
          form.setControlloIdentita(true);
          form.setControlloIdentitaCB(
              null != esitoTipoControllo.getEsitoFavorevole() && esitoTipoControllo.getEsitoFavorevole() ? "S" : "N");
          form.setNoteControlloIdentitaCB(esitoTipoControllo.getMotivoEsitoNonFavorevole());
        } else if (CaronteConstants.ID_TIPO_CONTROLLO_FITOSANITARIO.equals(esitoTipoControllo.getIdTipoControllo())) {
          form.setControlloFitosanitario(true);
          form.setControlloFitosanitarioCB(
              null != esitoTipoControllo.getEsitoFavorevole() && esitoTipoControllo.getEsitoFavorevole() ? "S" : "N");
          form.setControlloFitosanitarioIspVisiva(
              null != esitoTipoControllo.getIspezioneVisiva() && esitoTipoControllo.getIspezioneVisiva() ? true
                  : false);
          form.setControlloFitosanitarioIspStrum(
              null != esitoTipoControllo.getIspezioneStrumentale() && esitoTipoControllo.getIspezioneStrumentale()
                  ? true : false);
          form.setNoteControlloFitosanitarioCB(esitoTipoControllo.getStrumentoIspezione());
        }
      }
    }
    
    form.setPrelievoPerRicerca(carTVerbaleIspezione.getPrelievoPerRicerca());
    form.setCodiceCampione(carTVerbaleIspezione.getCodiceCampione());
    form.setAltraDocumentazione(carTVerbaleIspezione.getAltraDocumentazioneAllegata());

    form.setVisioneAnalisiRB(null != carTVerbaleIspezione.getDittaAssisteAnalisiLab()
        ? (carTVerbaleIspezione.getDittaAssisteAnalisiLab() ? "S" : "N") : null);

    form.setConsultoResponsabileRB(null != carTVerbaleIspezione.getConsultoResponsFilosanit()
        ? (carTVerbaleIspezione.getConsultoResponsFilosanit() ? "S" : "N") : null);
    form.setAllegaEvidenzaRB(null != carTVerbaleIspezione.getAllegatoConsulto()
        ? (carTVerbaleIspezione.getAllegatoConsulto() ? "S" : "N") : null);

    form.setTermIspezioneNullaOsta(null != carTVerbaleIspezione.getNullaostaRilasciato()
        ? (carTVerbaleIspezione.getNullaostaRilasciato() ? "S" : "N") : null);
    form.setTermIspezioneNullaOstaNote(carTVerbaleIspezione.getMotivoNonRilascioNullaosta());

    form.setTermIspezioneMisUff(
        null != carTVerbaleIspezione.getDispostaMisuraUffic() && carTVerbaleIspezione.getDispostaMisuraUffic() ? true
            : false);
    form.setTermIspezioneMisUffNote(carTVerbaleIspezione.getMotivoDispostaMisuraUffic());

    // Dati Misure Ufficiali
    form.setMisUffA(
        null != carTVerbaleIspezione.getSpedizioneRifiutata() && carTVerbaleIspezione.getSpedizioneRifiutata() ? true
            : false);
    form.setMisUffANote(carTVerbaleIspezione.getMotivoRifiutoSpedizione());
    form.setMisUffB(null != carTVerbaleIspezione.getTraspEsternoComun() && carTVerbaleIspezione.getTraspEsternoComun()
        ? true : false);
    form.setMisUffBNote(carTVerbaleIspezione.getMotivoTraspEsternoComun());
    form.setMisUffC(
        null != carTVerbaleIspezione.getProdottiInfettiRimossi() && carTVerbaleIspezione.getProdottiInfettiRimossi()
            ? true : false);
    form.setMisUffCNote(carTVerbaleIspezione.getMotivoRimozProdottiInfetti());
    form.setMisUffD(null != carTVerbaleIspezione.getDistrutto() && carTVerbaleIspezione.getDistrutto() ? true : false);
    form.setMisUffDNote(carTVerbaleIspezione.getMotivoDistruzione());
    form.setMisUffE(
        null != carTVerbaleIspezione.getQuarantena() && carTVerbaleIspezione.getQuarantena() ? true : false);
    form.setMisUffF(
        null != carTVerbaleIspezione.getTrattamentoAdeguato() && carTVerbaleIspezione.getTrattamentoAdeguato() ? true
            : false);
    form.setMisUffFNote(carTVerbaleIspezione.getMotivoTrattamentoAdeguato());

    // Dati Custodia
    form.setCustResponsabileMerce(carTVerbaleIspezione.getCustodeMerceMisUffic());
    form.setCustDocumentoRespMerce(carTVerbaleIspezione.getDocumentoIdentitaCustode());
    form.setCustRuoloRespMerce(carTVerbaleIspezione.getRuoloCustode());
    form.setCustLocaliMerce(carTVerbaleIspezione.getLocaliCustodia());

    // Dichiarazioni e note
    form.setDichNoteRespVerbale(carTVerbaleIspezione.getCopiaVerbConsegnatoPers());
    form.setDichNoteRuoloRespVerbale(carTVerbaleIspezione.getRuoloPersona());
    form.setDichNoteDichiarazioneRespVerb(carTVerbaleIspezione.getDichiarazionePersona());
    form.setDichNoteNoteRespVerb(carTVerbaleIspezione.getNote());
  }

  @ModelAttribute("datiVerbaleForm")
  public DatiVerbaleForm setDatiVerbaleForm(HttpServletRequest request) {

    DatiVerbaleForm form = new DatiVerbaleForm();

    form.setDataIspezione(new Date());

    // Controlli ed Esito
    form.setControlloDocumentale(false);
    form.setControlloDocumentaleCB("S");

    form.setControlloIdentita(false);
    form.setControlloIdentitaCB("S");

    form.setControlloFitosanitario(false);
    form.setControlloFitosanitarioCB("S");

    form.setTermIspezioneNullaOsta("S");
    form.setTermIspezioneMisUff(false);

    // Misure ufficiali
    form.setMisUffA(false);
    form.setMisUffB(false);
    form.setMisUffC(false);
    form.setMisUffD(false);
    form.setMisUffE(false);
    form.setMisUffF(false);

    form.setAccordionSelezionato("0");

    return form;
  }

  /* Download PDF Certificato Export (da spostare su export) */
  // @PreAuthorize("hasRole('READ')")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/esporta/certificatoexport/{idRichiesta}", produces = "application/pdf")
  public void downloadPdfCertificatoExport(@PathVariable Long idRichiesta, Model model, HttpServletRequest request,
      HttpServletResponse response) throws BusinessException {

    try {

      Long idTipoRichiesta = richiesteEJB.getDettaglioRichiesta(idRichiesta).getIdTipoRichiesta();

      ByteArrayOutputStream data = null;
      String filename = null;

      if (idTipoRichiesta == CaronteConstants.TIPO_RICHIESTA_EXPORT) {

        FitosanitarioExportDTO export = reportEJB.getPdfCertificatoExport(idRichiesta);
        
        data = StampeManager.getPdfCertificatoExport(export);

        filename = "certificatoExport";

      } else if (idTipoRichiesta == CaronteConstants.TIPO_RICHIESTA_RIEXPORT) {

        FitosanitarioRiexportDTO riexport = reportEJB.getPdfCertificatoRiexport(idRichiesta);

        data = StampeManager.getPdfCertificatoRiexport(riexport);

        filename = "certificatoRiexport";
      }

      response.setContentType("application/pdf");
      response.setHeader("Content-Disposition", "attachment; filename=" + filename + idRichiesta + ".pdf");
      response.setContentLength(data.toByteArray().length);
      response.getOutputStream().write(data.toByteArray());
      response.getOutputStream().flush();

    } catch (IOException e) {
      addErrorMessage("Errore nella generazione del pdf");
    }

  }

  /* Download PDF Dettaglio Richiesta Import */
  // @PreAuthorize("hasRole('READ')")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/richiesta/{idRichiesta}", produces = "application/pdf")
  public void downloadPdfDettaglioRichiesta(@PathVariable Long idRichiesta, Model model, HttpServletRequest request,
      HttpServletResponse response) throws BusinessException {

    try {
      DettaglioRichiestaDto dettaglioRichiesta = richiesteEJB.getDettaglioRichiesta(idRichiesta);

      TariffeRichiestaDto tariffeRichiestaDto = richiesteEJB.getTotaliTariffeRichiesta(idRichiesta);

      ByteArrayOutputStream data = StampeManager.getPdfDettaglioRichiesta(idRichiesta, tariffeRichiestaDto,
          this.isRichiestaImport(request));

      response.setContentType("application/pdf");
      response.setHeader("Content-Disposition",
          "attachment; filename=dettaglioRichiesta" + dettaglioRichiesta.getCodRichiesta() + ".pdf");
      response.setContentLength(data.toByteArray().length);
      response.getOutputStream().write(data.toByteArray());
      response.getOutputStream().flush();

    } catch (IOException e) {
      addErrorMessage("Errore nella generazione del pdf");
    }

  }

  private void initDatiMerci(DatiVerbaleForm form, List<MerceRichiestaDto> merciList) {

    final List<MerceRichiestaVerbaleDto> elencoMerci = new ArrayList<MerceRichiestaVerbaleDto>();
    List<CarRCampionamentoPartita> elencoCampioni = null;

    if (form.getIdVerbale() != null) {
      elencoCampioni = reportEJB.getElencoCarRCampionamentoPartita(form.getIdVerbale());
    }

    if (elencoCampioni == null || elencoCampioni.size() == 0) {

      for (int i = 0; i < merciList.size(); i++) {
        MerceRichiestaVerbaleDto merce = new MerceRichiestaVerbaleDto();

        String idOrdMerce = "";
        if (i / 26 > 0) {
          idOrdMerce += Character.toChars(i / 26 + 97)[0];
        }
        idOrdMerce += Character.toChars(i % 26 + 97)[0];
        merce.setIdOrdMerce(idOrdMerce);

        merce.setIdMerceRichiesta(BigDecimal.valueOf(merciList.get(i).getIdMerceRichiesta()));

        if (null != merciList.get(i).getDescSpecie()
            && !merciList.get(i).getDescSpecie().contains(merciList.get(i).getDescGenere())) {
          merce.setDescrizionePartita(merciList.get(i).getDescGenere() + " / " + merciList.get(i).getDescSpecie()
              + " / " + merciList.get(i).getDescProdotto());
        } else if (null != merciList.get(i).getDescSpecie()) {
          merce.setDescrizionePartita(merciList.get(i).getDescSpecie() + " / " + merciList.get(i).getDescProdotto());
        } else {
          merce.setDescrizionePartita(merciList.get(i).getDescGenere() + " / " + merciList.get(i).getDescProdotto());
        }

        if (null != merciList.get(i).getIdUnitaMisura()
            && merciList.get(i).getIdUnitaMisura().equals(CaronteConstants.ID_UNITA_MISURA_KGM)) {
          merce.setKg(merciList.get(i).getQuantita());
        } else if (null != merciList.get(i).getIdUnitaMisura()
            && merciList.get(i).getIdUnitaMisura().equals(CaronteConstants.ID_UNITA_MISURA_MTQ)) {
          merce.setMc(merciList.get(i).getQuantita());
        } else if (null != merciList.get(i).getIdUnitaMisura()
            && merciList.get(i).getIdUnitaMisura().equals(CaronteConstants.ID_UNITA_MISURA_NMB)) {
          merce.setUnita(merciList.get(i).getQuantita());
        }

        merce.setColli(
            merciList.get(i).getNumeroColli() != null ? BigDecimal.valueOf(merciList.get(i).getNumeroColli()) : null);

        /*
         * DecimalFormat bdFormat = new DecimalFormat("##0.###");
         * merce.setQuantita(null != merciList.get(i).getQuantita() ?
         * bdFormat.format(merciList.get(i).getQuantita()) : "0");
         * 
         * merce.setUnitaMisura(merciList.get(i).getCodUnitaMisura());
         */

        elencoMerci.add(merce);

      }

    } else {

      for (int i = 0; i < elencoCampioni.size(); i++) {
        MerceRichiestaVerbaleDto merce = new MerceRichiestaVerbaleDto();

        String idOrdMerce = "";
        if (i / 26 > 0) {
          idOrdMerce += Character.toChars(i / 26 + 97)[0];
        }
        idOrdMerce += Character.toChars(i % 26 + 97)[0];
        merce.setIdOrdMerce(idOrdMerce);

        merce.setIdMerceRichiesta(elencoCampioni.get(i).getIdMerceRichiesta() != null
            ? BigDecimal.valueOf(elencoCampioni.get(i).getIdMerceRichiesta()) : null);

        merce.setQuantitaAnalizzati(elencoCampioni.get(i).getQuantitaAnalizzati());

        merce.setDescrizionePartita(elencoCampioni.get(i).getDescrizionePartita());

        merce.setKg(elencoCampioni.get(i).getKg() != null ? elencoCampioni.get(i).getKg() : null);

        merce.setMc(elencoCampioni.get(i).getMc() != null ? elencoCampioni.get(i).getMc() : null);

        merce.setUnita(elencoCampioni.get(i).getUnita() != null ? elencoCampioni.get(i).getUnita() : null);

        merce.setColli(elencoCampioni.get(i).getColli() != null ? elencoCampioni.get(i).getColli() : null);

        elencoMerci.add(merce);

      }

    }

    form.setListaMerceRichiesta(elencoMerci);

  }
  
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/elencoSpecie")
  public String elencoSpecie(@ModelAttribute("ricercaReportForm") RicercaReportForm ricercaReportForm, ModelMap model,
      HttpSession session, HttpServletRequest request) throws BusinessException {
  
	logger.debug("-- Richiesta REPORT SPECIE VIVAI");          
	model.remove("elencoReport");
	model.addAttribute("listaOrgNocivo", decodificheEJB.getListaOrganismiNocivi());
	
    return "report/elencoSpecie";
  }
  
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/elencoComunicazioni")
  public String elencoComunicazioni(@ModelAttribute("ricercaReportForm") RicercaReportVivaiForm ricercaReportForm, ModelMap model,
      HttpSession session, HttpServletRequest request) throws BusinessException {
  
	logger.debug("-- Richiesta REPORT COMUNICAZIONI VIVAI");      
	model.remove("elencoReport");
	
    return "report/elencoComunicazioni";
  }
  
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/cercaComVivai")
  public String cercaComVivai(ModelMap model, @ModelAttribute("ricercaReportForm") RicercaReportVivaiForm ricercaReportForm,
      BindingResult result, HttpSession session, HttpServletRequest request) throws BusinessException {
    try {
        logger.debug("-- Ricerca comunicazioni vivai per REPORT");
        List<ReportDTO> elencoReport = reportEJB.getElencoReportComVivai(ricercaReportForm);
        if(elencoReport !=null){
          logger.debug("-- numero di comunicazioni trovate per il Report ="+elencoReport.size());
        }
        ricercaReportForm.setElencoReport(elencoReport);
        
        model.addAttribute("elencoReport", elencoReport);
        session.setAttribute("elencoReportPerExcel", elencoReport);

    } catch (BusinessException exc) {
      addErrorMessage("Errore nella ricerca dei report");
    }

    return "report/elencoComunicazioni";
  }
  
  @SuppressWarnings("unchecked")
  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "esportaComVivai")
  public ModelAndView downloadExcelComVivai(ModelMap model, HttpSession session, HttpServletRequest request) throws BusinessException {
	    List<ReportDTO> elencoReport = null;
	    try {
	      /*
	       * Nel remotissimo caso, tendenzialmente impossibile, che possa arrivare
	       * una classcastexception..
	       */
	      elencoReport = (List<ReportDTO>) session.getAttribute("elencoReportPerExcel");
	    } 
	    catch (Exception exc) {
	      logger.error("-- Exception in downloadExcelVivai = "+exc.getMessage());
	      addErrorMessage("Errore nell'esportazione report");
	    }

	    ModelAndView modelAndView = new ModelAndView("excelElencoComVivaiView", "elenco", elencoReport != null ? elencoReport : new ArrayList<ReportDTO>());
	    
	    return modelAndView;
  }
  

	/* Download PDF Dettaglio Comunicazione */
	@PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = "/domanda/{id}", produces = "application/pdf")
	public void downloadPdfDettaglioDomanda(@PathVariable Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
	    logger.info("BEGIN downloadPdfDettaglioDomanda");
		try {
			byte[] data = null;
			String filename = null;
			CarTDatiDomanda datiDomandaStampa = domandeEJB.getStampaFirmata(id);

			if (datiDomandaStampa != null && datiDomandaStampa.getStampa() != null) {
				filename = datiDomandaStampa.getNomeFile();
				data = datiDomandaStampa.getStampa();
			}
			/* non genero una nuova stampa se è già presente */
			if (data == null) {
				generaPdfDettaglioDomanda(id, model, request, response);
			} else {
				logger.error("Stampa presente per la domanda inoltrata :" + datiDomandaStampa.getStampa().length);
				// response.reset();
				response.resetBuffer();
				response.setContentType("application/octet-stream");
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
				LocalDateTime dataStampa = LocalDateTime.now();	
				response.setHeader("Content-disposition", "attachment;filename=" + (filename == null
						? "domanda" + id + "_" + dtf.format(dataStampa) +".pdf" : URLEncoder.encode(filename, "UTF-8")));
				response.setContentLength(data.length);
				response.getOutputStream().write(data);
				response.getOutputStream().flush();
			}

		} catch (Exception e) {
			logger.error("Errore nella generazione del pdf :" + e.getMessage());
			addErrorMessage("Errore nella generazione del pdf");
		}
		finally{
			logger.info("END downloadPdfDettaglioDomanda");
		}

	}
	
	/*
	 * Metodo comune a : Domanda Ruop, Variazione Ruop, Domanda Passaporto
	 */
	public void generaPdfDettaglioDomanda( Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("BEGIN generaPdfDettaglioDomanda");
		try {
			byte[] data = null;
			String filename = null;
			
			logger.debug("--- idDomanda=" + id);
			logger.debug("model =" + model);
			logger.debug("request =" + request);
			logger.debug("response =" + response);

			Long idAssociazioneSezione = getSezioneRequest(request);
			logger.debug("--- idAssociazioneSezione =" + idAssociazioneSezione);
			
			// sono in una comunicazione utente o regionale
//			Long idRuolo = comunicazioneService.getRuoloTipoComunicazione(id);
			

			//si deve modificare il controllo per ruop/no ruop/ecc
			if (idAssociazioneSezione == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI) {
				logger.debug("\n\n downloadPdfDettaglioComunicazione AUTORIZZAZIONI idComunicazione =" + id);
				
				/* Gestione Stampa Domanda Passaporto :
				 *     visualizzare solo la parte relativa al Passaporto (no Centri aziendali, Import e Export)				
				 *  
				 *  // TODO
				 *  - Capire quale flag visualizzare nella parte del Passaporto :
				 *  - 'flag nuova' se la Domanda Ruop non aveva ancora la parte del Passaporto compilata per il centro aziendale selezionato nel Tab Passaporto
					- 'flag aggiornamento' se la Domanda Ruop aveva già la parte del Passaporto compilata per il centro aziendale selezionato nel Tab Passaporto
				 */	
				CarTDomanda domanda = domandeEJB.getCarTDomandaByIdDomanda(id);
				if (domanda != null && (domanda.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP
						|| domanda.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP)) {
				
				    // mi recupero il template dal DB	
					List<byte[]> listTemplateRuop = new ArrayList<byte[]>();
					listTemplateRuop.add(domandeEJB.getTemplateTipoStampa(id));
					listTemplateRuop.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_TIPO_OPERATORE));
					listTemplateRuop.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_ATTIVITA));
					listTemplateRuop.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_MATERIALE));
					listTemplateRuop.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_TIPO_ALLEGATO));
					String[] listNomiRuop = {"id_domanda", "subreportParameterOperatore","subreportParameterAttivita", "subrepParameterMateriale", "subreportParameterTipoAllegato"};
					
		
					boolean tabImport = false;
		    		boolean tabExport = false;
		    		boolean tabPassaporto = false;	
					
					List<CentroAziendaleDomandaDTO> listaCentriAz = null;
					List<byte[]> listTemplateCentriAziendali = new ArrayList<byte[]>();
					String[] listNomiCentriAziendali = null;
						
					listNomiCentriAziendali = new String[3];
					// recupero i template aggiuntivi da db (modifica: RECUPERARE SOLO QUELLI NECESSARI)
					//CENTRI AZIENDALI				
					listTemplateCentriAziendali = new ArrayList<byte[]>();						
					listTemplateCentriAziendali.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_CENTRI_AZIENDALI));
					listTemplateCentriAziendali.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_TIPOLOGIA_PRODUTTIVA));
					listTemplateCentriAziendali.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_SITI_PRODUZIONE));
					listNomiCentriAziendali[0] = "id_domanda";
					listNomiCentriAziendali[1] = "subreportParameterTipologiaProduttiva";
					listNomiCentriAziendali[2] = "subreportParameterSitoProduzione";					
					//listNomiCentriAziendali = {"id_domanda", "subreportParameterTipologiaProduttiva","subreportParameterSitoProduzione"};
					listaCentriAz = domandeEJB.getCentriAziendaliByIdDomanda(id);
						
						
					TipologiaDomandaDTO tipDomDto = domandeEJB.getDettTipologiaDomanda(id);				
					if(null != tipDomDto){
						if(tipDomDto.getTipologieDomandaList() !=null && tipDomDto.getTipologieDomandaList().size()>0){
							logger.debug("-- Tipologie domanda presenti");
							// Tipologie domanda					
							for (int i = 0; i < tipDomDto.getTipologieDomandaList().size(); i++) {
								Long idTipologia = tipDomDto.getTipologieDomandaList().get(i).getIdTipologia();
								if (idTipologia == CaronteConstants.ID_TIPOLOGIA_DOMANDA_IMP) {							
									tabImport = true; 
								}					  
								if (idTipologia == CaronteConstants.ID_TIPOLOGIA_DOMANDA_EXP) {
									tabExport = true;
								}					  
								if (idTipologia == CaronteConstants.ID_TIPOLOGIA_DOMANDA_PASS) {
									tabPassaporto = true;
								}											
							}
						}
					}
						
						
					logger.debug("-- tabImport ="+tabImport);
					logger.debug("-- tabExport ="+tabExport);
					logger.debug("-- tabPassaporto ="+tabPassaporto);
					//IMPORT
					List<byte[]> listTemplateImport = new ArrayList<byte[]>();	
					String[] listNomiImport = new String[4];
					if (tabImport) {
						listTemplateImport.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_IMPORT));
						listTemplateImport.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_TIPOLOGIA_PRODUTTIVA));
						listTemplateImport.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_CONTIN_INFO_EXPORT));
						listTemplateImport.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_CONTIN_INFO_EXPORT));
						listNomiImport[0] = "id_domanda";
						listNomiImport[1] = "subreportParameterTipologiaProduttiva";
						listNomiImport[2] = "subreportParameterZoneProtette";
						listNomiImport[3] = "subreportParameterContinenti";
					}
					
					//EXPORT
					List<byte[]> listTemplateExport = new ArrayList<byte[]>();
					String[] listNomiExport = new String[4];
					if (tabExport) {
						listTemplateExport.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_EXPORT));
						listTemplateExport.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_CONTIN_INFO_EXPORT));
						listTemplateExport.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_CONTIN_INFO_EXPORT));
						listTemplateExport.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_TIPOLOGIA_PRODUTTIVA));
						listNomiExport[0] = "id_domanda";
						listNomiExport[1] = "subreportParameterInfoExport";
						listNomiExport[2] = "subreportParameterContinenti";
						listNomiExport[3] = "subreportParameterTipologiaProduttiva";
					}
					//PASSAPORTO
					List<byte[]> listTemplatePassaporto = new ArrayList<byte[]>();
					String[] listNomiPassaporto = new String[6]; // modificare lunghezza array in caso di altri parametri
					if (tabPassaporto) {
						listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_PASSAPORTO));
						listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_CONTIN_INFO_EXPORT));
						listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_CONTIN_INFO_EXPORT));
						listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_TIPOLOGIA_PRODUTTIVA));
						listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_PASSAPORTI));
						listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_DICHIARA_PASS));
						listNomiPassaporto[0] = "id_domanda";
						listNomiPassaporto[1] = "subreportParameterCheckCommercio";
						listNomiPassaporto[2] = "subreportParameterInfo";
						listNomiPassaporto[3] = "subreportParameterTipologiaProduttiva";
						listNomiPassaporto[4] = "subreportParameterPassaporti";
						listNomiPassaporto[5] = "subreportDichiaraPass";
					} 
					data = StampeManager.getPdfDettaglioDomandaRuop(id, listTemplateRuop, listNomiRuop, listTemplateCentriAziendali, listNomiCentriAziendali, listTemplateImport, 
																	listNomiImport, listTemplateExport, listNomiExport, listTemplatePassaporto, listNomiPassaporto, listaCentriAz);
					
				} 
				if (domanda != null && domanda.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO) {
					logger.debug("-- Stampa Domanda Passaporto ");
					List<byte[]> listTemplatePassaporto = new ArrayList<byte[]>();
					String[] listNomiPassaporto = new String[6]; // modificare lunghezza array in caso di altri parametri
					
					listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_PASSAPORTO));
					listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_CONTIN_INFO_EXPORT));
					listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_CONTIN_INFO_EXPORT));
					listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_TIPOLOGIA_PRODUTTIVA));
					listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_PASSAPORTI));
					listTemplatePassaporto.add(domandeEJB.getTemplateTipoStampaById(CaronteConstants.ID_TIPO_STAMPA_SUBREP_DICHIARA_PASS));
					listNomiPassaporto[0] = "id_domanda";
					listNomiPassaporto[1] = "subreportParameterCheckCommercio";
					listNomiPassaporto[2] = "subreportParameterInfo";
					listNomiPassaporto[3] = "subreportParameterTipologiaProduttiva";
					listNomiPassaporto[4] = "subreportParameterPassaporti";
					listNomiPassaporto[5] = "subreportDichiaraPass";
					 
					data = StampeManager.getPdfDettaglioDomandaPassaporto(id, listTemplatePassaporto, listNomiPassaporto);
				}
			}
			response.resetBuffer();
			response.setContentType("application/octet-stream");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
			LocalDateTime dataStampa = LocalDateTime.now();			
			response.setHeader("Content-disposition", "attachment;filename=" + 
								(filename == null ? "domanda" + id + "_" + dtf.format(dataStampa) + ".pdf" : URLEncoder.encode(filename, "UTF-8")));
			response.setContentLength(data.length);
			response.getOutputStream().write(data);
			response.getOutputStream().flush();

		} catch (Exception e) {
			logger.error("Errore nella generazione del pdf :" + e.getMessage());
			addErrorMessage("Errore nella generazione del pdf");
		}
		finally{
			logger.info("END generaPdfDettaglioDomanda");
		}
		
	}
	

	@PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = "/domanda/download-{id}", produces = "application/pdf")
	public void downloadPdfDettaglioDomandaByInoltro(@PathVariable Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("BEGIN downloadPdfDettaglioDomandaByInoltro");
		try {
			generaPdfDettaglioDomanda(id, model, request, response);

		} catch (Exception e) {
			logger.error("Errore nella generazione del pdf :" + e.getMessage());
			addErrorMessage("Errore nella generazione del pdf");
		}
		finally{
			logger.info("END downloadPdfDettaglioDomandaByInoltro");	
		}

	}
	
	@PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = "/elencoAziende")
	public String elencoAziendeAutorizzazione(@ModelAttribute("ricercaReportForm") RicercaReportAutorizzazioniForm ricercaReportForm,
			ModelMap model, HttpSession session, HttpServletRequest request) throws BusinessException {
		logger.debug("-- Richiesta REPORT Aziende autorizzate");
		model.remove("elencoReport");
		model.addAttribute("listaTipiSpedizionieri", decodificheEJB.getListaTipiSpedizioniere());
		model.addAttribute("listaStatiAzienda", decodificheEJB.getListaStatiAzienda());
		model.addAttribute("listaProvince", decodificheEJB.getListaProvince());
		if(ricercaReportForm.getIdProvincia() != null) { 
			model.addAttribute("listaComuni", decodificheEJB.getListaComuni(ricercaReportForm.getIdProvincia()));
		}

		return "report/elencoAziende";
	}
	
	@PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = "/cercaAziendeAut")
	public String cercaAziendeAutorizzazione(ModelMap model,
			@ModelAttribute("ricercaReportForm") RicercaReportAutorizzazioniForm ricercaReportForm, BindingResult result,
			HttpSession session, HttpServletRequest request) throws BusinessException {
		try {
			logger.debug("-- Ricerca aziende per REPORT");
			logger.debug("-- ricercaReportForm.isAziendeConDomandaRuop() = "+ricercaReportForm.isAziendeConDomandaRuop());
			List<ReportDTO> elencoReport = reportEJB.getElencoReportAziendeRuop(ricercaReportForm);
			if (elencoReport != null) {
				logger.debug("-- numero di aziende trovate per il Report =" + elencoReport.size());
			}
			ricercaReportForm.setElencoReport(elencoReport);

			model.addAttribute("elencoReport", elencoReport);
			model.addAttribute("listaTipiSpedizionieri", decodificheEJB.getListaTipiSpedizioniere());
			model.addAttribute("listaStatiAzienda", decodificheEJB.getListaStatiAzienda());
			model.addAttribute("listaProvince", decodificheEJB.getListaProvince());
			if(ricercaReportForm.getIdProvincia() != null) { 
				model.addAttribute("listaComuni", decodificheEJB.getListaComuni(ricercaReportForm.getIdProvincia()));
			}
			
			session.setAttribute("elencoReportPerExcel", elencoReport);

		} catch (BusinessException exc) {
			addErrorMessage("Errore nella ricerca dei report");
		}

		return "report/elencoAziende";
	}
	
	@SuppressWarnings("unchecked")
	  @PreAuthorize("hasRoleImpExp('READ', #request)")
	  @GetMapping(value = "/esportaAziendeAut")
	  public ModelAndView downloadExcelAziendeAutorizzate(ModelMap model, HttpSession session, HttpServletRequest request) throws BusinessException {
		    List<ReportDTO> elencoReport = null;
		    try {
		      /*
		       * Nel remotissimo caso, tendenzialmente impossibile, che possa arrivare
		       * una classcastexception..
		       */
		      elencoReport = (List<ReportDTO>) session.getAttribute("elencoReportPerExcel");
		    } 
		    catch (Exception exc) {
		      logger.error("-- Exception in downloadExcelAziendeAutorizzate = "+exc.getMessage());
		      addErrorMessage("Errore nell'esportazione report");
		    }

		    ModelAndView modelAndView = new ModelAndView("excelElencoAziendeAutorizzate", "elenco", elencoReport != null ? elencoReport : new ArrayList<ReportDTO>());
		    
		    return modelAndView;
	  }
	
	@PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = "/elencoCentriAziendali")
	public String elencoCentriAzAutorizzazione(@ModelAttribute("ricercaReportForm") RicercaReportAutorizzazioniForm ricercaReportForm,
			ModelMap model, HttpSession session, HttpServletRequest request) throws BusinessException {
		logger.debug("-- Richiesta REPORT Aziende autorizzate");
		model.remove("elencoReport");
		//model.addAttribute("listaTipiSpedizionieri", decodificheEJB.getListaTipiSpedizioniere());
		 // Popolo combo Provincia e Comune
	 	model.addAttribute("listaProvince", decodificheEJB.getListaProvince());
		if(ricercaReportForm.getIdProvincia() != null) { 
			model.addAttribute("listaComuni", decodificheEJB.getListaComuni(ricercaReportForm.getIdProvincia()));
		}
		model.addAttribute("listaStatiAzienda", decodificheEJB.getListaStatiAzienda());
		model.addAttribute("listaIspettori", decodificheEJB.getListaIspettori());
		model.addAttribute("listaTipologiaPassaporto", decodificheEJB.getListaTipologiaPassaporto());

		return "report/elencoCentriAziendali";
	}
	
	@PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = "/cercaCentriAziendali")
	public String cercaCentriAzAutorizzazione(ModelMap model,
			@ModelAttribute("ricercaReportForm") RicercaReportAutorizzazioniForm ricercaReportForm, BindingResult result,
			HttpSession session, HttpServletRequest request) throws BusinessException {
		try {
			logger.debug("-- Ricerca aziende per REPORT");
			logger.debug("-- ricercaReportForm.isAziendeConDomandaRuop() = "+ricercaReportForm.isAziendeConDomandaRuop());
			List<ReportDTO> elencoReport = reportEJB.getElencoReportCentriAzRuop(ricercaReportForm);
			if (elencoReport != null) {
				logger.debug("-- numero di aziende trovate per il Report =" + elencoReport.size());
			}
			ricercaReportForm.setElencoReport(elencoReport);

			model.addAttribute("elencoReport", elencoReport);
			//model.addAttribute("listaTipiSpedizionieri", decodificheEJB.getListaTipiSpedizioniere());
			model.addAttribute("listaProvince", decodificheEJB.getListaProvince());
			if(ricercaReportForm.getIdProvincia() != null) { 
				model.addAttribute("listaComuni", decodificheEJB.getListaComuni(ricercaReportForm.getIdProvincia()));
			}
			model.addAttribute("listaStatiAzienda", decodificheEJB.getListaStatiAzienda());
			model.addAttribute("listaIspettori", decodificheEJB.getListaIspettori());
			model.addAttribute("listaTipologiaPassaporto", decodificheEJB.getListaTipologiaPassaporto());
			session.setAttribute("elencoReportPerExcel", elencoReport);

		} catch (BusinessException exc) {
			addErrorMessage("Errore nella ricerca dei report");
		}

		return "report/elencoCentriAziendali";
	}
	
	@SuppressWarnings("unchecked")
	  @PreAuthorize("hasRoleImpExp('READ', #request)")
	  @GetMapping(value = "/esportaCentriAzAut")
	  public ModelAndView downloadExcelCentriAzAutorizzati(ModelMap model, HttpSession session, HttpServletRequest request) throws BusinessException {
		    List<ReportDTO> elencoReport = null;
		    try {
		      /*
		       * Nel remotissimo caso, tendenzialmente impossibile, che possa arrivare
		       * una classcastexception..
		       */
		      elencoReport = (List<ReportDTO>) session.getAttribute("elencoReportPerExcel");
		    } 
		    catch (Exception exc) {
		      logger.error("-- Exception in downloadExcelAziendeAutorizzate = "+exc.getMessage());
		      addErrorMessage("Errore nell'esportazione report");
		    }

		    ModelAndView modelAndView = new ModelAndView("excelElencoCentriAzAutorizzati", "elenco", elencoReport != null ? elencoReport : new ArrayList<ReportDTO>());
		    
		    return modelAndView;
	  }
	  

	@PreAuthorize("hasRoleImpExp('READ', #request)")
	  @GetMapping(value = {"/cercaControlli", "/cercaCampioni", "/cercaMisure", "/cercaMonitoraggi"})
	  public String cercaControlli(ModelMap model, @ModelAttribute("ricercaReportForm") RicercaReportControlliForm ricercaReportForm,
	      BindingResult result, HttpSession session, HttpServletRequest request) throws BusinessException {
	    
		 String requestURI = request.getRequestURI();
		 logger.debug("-- requestURI = "+requestURI);
			
		 List<ReportDTO> elencoReport = null;
		 String valueReturn = null;
		 try {
			 
	    	if (requestURI.contains("/cercaControlli")) {
	    		logger.debug("-- Ricerca controlli per il report Controlli");
		        elencoReport = reportEJB.getElencoReportControlli(ricercaReportForm);
		        model.addAttribute("listaIspettori", decodificheEJB.getListaIspettori());
				model.addAttribute("listaProvince", decodificheEJB.getListaProvince());
				model.addAttribute("listaOrgNoc", decodificheEJB.getListaOrganismiNocivi());
				if(ricercaReportForm.getIdProvincia() != null) { 
					model.addAttribute("listaComuni", decodificheEJB.getListaComuni(ricercaReportForm.getIdProvincia()));
				}
		        valueReturn = "report/elencoControlli";
	    	}
	    	
	    	if (requestURI.contains("/cercaCampioni")) {
	    		logger.debug("-- Ricerca controlli per il report Campioni");
	    		//trasformo le specie in una lista
	    		String specie = ricercaReportForm.getSpecie();
	        	logger.debug("specie ="+specie);
	    		List<Integer> idSpecieList = null;
	            if(specie != null && !specie.equals("")){
	        	  idSpecieList = new ArrayList<Integer>();	  
	        	  String[] separated = specie.split(",");	  
	        	  Integer[] numbers = new Integer[separated.length];
	        	  for(int i = 0;i < separated.length;i++)        {
	        	      try {
	        	        numbers[i] = Integer.parseInt(separated[i]);
	        	      }
	        	      catch (NumberFormatException nfe)   {
	        	        numbers[i] = null;
	        	      }
	        	  }	  
	        	  idSpecieList = Arrays.asList(numbers);
	            }	  
	            ricercaReportForm.setIdSpecieList(idSpecieList);
	            
		        elencoReport = reportEJB.getElencoReportCampioni(ricercaReportForm);
				model.addAttribute("listaOrgNoc", decodificheEJB.getListaOrganismiNocivi());
	    		valueReturn = "report/elencoCampioni";
	    	}
	    	
	    	if (requestURI.contains("/cercaMisure")) {
	    		logger.debug("-- Ricerca controlli per il report Misura");
	    		model.addAttribute("listaIspettori", decodificheEJB.getListaIspettori());
	    		model.addAttribute("listaOrgNoc", decodificheEJB.getListaOrganismiNocivi());
		        elencoReport = reportEJB.getElencoReportMisure(ricercaReportForm);
	    		valueReturn = "report/elencoMisure";
	    	}
	    	
	    	if (requestURI.contains("/cercaMonitoraggi")) {
	    		logger.debug("-- Ricerca controlli per il report Monitoraggio");
	    		
	    		//trasformo le specie in una lista
	    		String specie = ricercaReportForm.getSpecie();
	        	logger.debug("specie ="+specie);
	    		List<Integer> idSpecieList = null;
	            if(specie != null && !specie.equals("")){
	        	  idSpecieList = new ArrayList<Integer>();	  
	        	  String[] separated = specie.split(",");	  
	        	  Integer[] numbers = new Integer[separated.length];
	        	  for(int i = 0;i < separated.length;i++)        {
	        	      try {
	        	        numbers[i] = Integer.parseInt(separated[i]);
	        	      }
	        	      catch (NumberFormatException nfe)   {
	        	        numbers[i] = null;
	        	      }
	        	  }	  
	        	  idSpecieList = Arrays.asList(numbers);
	            }	  
	            ricercaReportForm.setIdSpecieList(idSpecieList);


		        elencoReport = reportEJB.getElencoReportMonitoraggi(ricercaReportForm);
		        model.addAttribute("listaOrgNoc", decodificheEJB.getListaOrganismiNocivi());
	    		valueReturn = "report/elencoMonitoraggi";
	    	}
	        
	        if(elencoReport !=null){
	          logger.debug("-- numero di comunicazioni trovate per il Report =" + elencoReport.size());
	        }
	       
	        model.addAttribute("elencoReport", elencoReport);
	        session.setAttribute("elencoReportPerExcel", elencoReport);


	    } catch (BusinessException exc) {
	      addErrorMessage("Errore nella ricerca dei report");
	    }

	    return valueReturn;
	  }
	  
	  @PreAuthorize("hasRoleImpExp('READ', #request)")
	  @GetMapping(value = {"/elencoControlli", "/elencoCampioni", "/elencoMisure", "/elencoMonitoraggi"})
	  public String elencoControlli(@ModelAttribute("ricercaReportForm") RicercaReportControlliForm ricercaReportForm, ModelMap model,
	      HttpSession session, HttpServletRequest request) throws BusinessException {
	  
		String requestURI = request.getRequestURI();
		logger.debug("-- requestURI = "+requestURI);
		
		 
		model.remove("elencoReport");
		
		if (requestURI.contains("/elencoControlli")) {
    		logger.debug("-- Richiesta report Controlli");
    		model.addAttribute("listaIspettori", decodificheEJB.getListaIspettori());
			model.addAttribute("listaProvince", decodificheEJB.getListaProvince());
			model.addAttribute("listaOrgNoc", decodificheEJB.getListaOrganismiNocivi());
			if(ricercaReportForm.getIdProvincia() != null) { 
				model.addAttribute("listaComuni", decodificheEJB.getListaComuni(ricercaReportForm.getIdProvincia()));
			}
	        return "report/elencoControlli";
    	}
    	
    	if (requestURI.contains("/elencoCampioni")) {
    		logger.debug("-- Richiesta report Campioni");
			model.addAttribute("listaOrgNoc", decodificheEJB.getListaOrganismiNocivi());
    		return "report/elencoCampioni";
    	}
    	
    	if (requestURI.contains("/elencoMisure")) {
    		logger.debug("-- Richiesta report Misure");
    		model.addAttribute("listaIspettori", decodificheEJB.getListaIspettori());
    		model.addAttribute("listaOrgNoc", decodificheEJB.getListaOrganismiNocivi());
    		return "report/elencoMisure";
    	}
    	
    	if (requestURI.contains("/elencoMonitoraggi")) {
    		logger.debug("-- Richiesta report Monitoraggi");
    		model.addAttribute("listaOrgNoc", decodificheEJB.getListaOrganismiNocivi());
    		return "report/elencoMonitoraggi";
    	}
		
    	//caso limite
    	return "report/elenco";
	  }
	  
	  @SuppressWarnings("unchecked")
	  @PreAuthorize("hasRoleImpExp('READ', #request)")
	  @GetMapping(value = {"esportaControlli", "esportaCampioni", "esportaMisure", "esportaMonitoraggi"})
	  public ModelAndView downloadExcelControlli(ModelMap model, HttpSession session, HttpServletRequest request) throws BusinessException {
		  
		    List<ReportDTO> elencoReport = null;
		    String requestURI = request.getRequestURI();
			logger.debug("-- requestURI = "+requestURI);
			ModelAndView modelAndView = null;
			
		    try {
		      /*
		       * Nel remotissimo caso, tendenzialmente impossibile, che possa arrivare
		       * una classcastexception..
		       */
		      elencoReport = (List<ReportDTO>) session.getAttribute("elencoReportPerExcel");
		    } 
		    catch (Exception exc) {
		      logger.error("-- Exception in downloadExcelControlli = "+exc.getMessage());
		      addErrorMessage("Errore nell'esportazione report");
		    }

			if (requestURI.contains("esportaControlli")) {
	    		logger.debug("-- esportaControlli");
	    		modelAndView = new ModelAndView("reportExcelControlliView", "elenco", elencoReport != null ? elencoReport : new ArrayList<ReportDTO>());
	    	}
	    	
	    	if (requestURI.contains("esportaCampioni")) {
	    		logger.debug("-- esportaCampioni");
	    		modelAndView = new ModelAndView("reportExcelCampioniView", "elenco", elencoReport != null ? elencoReport : new ArrayList<ReportDTO>());
	    	}
	    	
	    	if (requestURI.contains("esportaMisure")) {
	    		logger.debug("-- esportaMisure");
	    		modelAndView = new ModelAndView("reportExcelMisureView", "elenco", elencoReport != null ? elencoReport : new ArrayList<ReportDTO>());
	    	}
	    	
	    	if (requestURI.contains("esportaMonitoraggi")) {
	    		logger.debug("-- esportaMonitoraggi");
	    		//TODO COMPLETARE ReportExcelMonitoraggiBuilder.java
	    		//modelAndView = new ModelAndView("reportExcelMonitoraggiView", "elenco", elencoReport != null ? elencoReport : new ArrayList<ReportDTO>());
	    	}
		    
		    return modelAndView;
	  }
	  
	  
	  /* Download PDF Privacy */	 
	  @GetMapping(value = "/privacy", produces = "application/pdf")
	  public void downloadPdfPrivacy(Model model, HttpServletRequest request, HttpServletResponse response) {
		  logger.info("BEGIN downloadPdfPrivacy");
		  try {
			  byte[] data = null;
			  String filename = "Informativa_Privacy.pdf";
			  CarDCostante costante = decodificheEJB.getCostanteWithBlob(CaronteConstants.COD_COSTANTE_PDF_PRIVACY);

			  if (costante != null && costante.getDocumento() != null) {
				  data = costante.getDocumento();
			  }							
			  response.resetBuffer();
			  response.setContentType("application/octet-stream");			 
			  response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			  response.setContentLength(data.length);
			  response.getOutputStream().write(data);
			  response.getOutputStream().flush();		
		  } 
		  catch (Exception e) {
			  logger.error("Errore nella generazione del pdf privacy:" + e.getMessage());
			  addErrorMessage("Errore nella generazione del pdf privacy");
		  }
		  finally{
			  logger.info("END downloadPdfPrivacy");
		  }

	  }


	
	

}