package it.aizoon.ersaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IServiziEJB;
import it.aizoon.ersaf.dto.DatiRichiesteDTO;
import it.aizoon.ersaf.dto.DatiSianDTO;
import it.aizoon.ersaf.dto.MerceRichiestaDatiSianDTO;
import it.aizoon.ersaf.dto.RiexportDatiSianDTO;
import it.aizoon.ersaf.dto.generati.CarDAllegato;
import it.aizoon.ersaf.dto.generati.CarTNotifica;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NotificheForm;
import it.aizoon.ersaf.form.RicercaDatiRichiesteForm;
import it.aizoon.ersaf.form.RicercaDatiSianForm;
import it.aizoon.ersaf.security.SecurityUtils;
import it.aizoon.ersaf.util.CaronteConstants;

/**
 * @author alessandro.morra
 */
@Controller
@RequestMapping(value = { "/import/servizi/datiSian", "/export/servizi/datiSian", "/import/servizi/datiRichieste",
		"/export/servizi/datiRichieste", "/import/servizi/documentazione", "/export/servizi/documentazione",
		"/admin/messaggistica" })
public class ServiziController extends BaseController {

	@Autowired
	private IDecodificheEJB decodificheEJB;

	@Autowired
	private IServiziEJB serviziEJB;

	@PreAuthorize("hasRole('READ')")
	@GetMapping(value = "/elencoSian")
	public String elencoSian(@ModelAttribute("ricercaDatiSianForm") RicercaDatiSianForm ricercaDatiSianForm,
			ModelMap model, HttpSession session) throws BusinessException {
		try {
			setCommonServiziModel(model);
		} catch (BusinessException exc) {
			addErrorMessage("Errore nella ricerca dati sian");
		}

		return "servizi/datiSian/elenco";
	}

	@PreAuthorize("hasRole('READ')")
	@GetMapping(value = "/elencoRichieste")
	public String elencoRichieste(
			@ModelAttribute("ricercaDatiRichiesteForm") RicercaDatiRichiesteForm ricercaDatiRichiesteForm,
			ModelMap model, HttpSession session) throws BusinessException {
		try {
			setCommonServiziModel(model);
		} catch (BusinessException exc) {
			addErrorMessage("Errore nella ricerca dati richieste");
		}

		return "servizi/datiRichieste/elenco";
	}

	@PreAuthorize("hasRole('READ')")
	@GetMapping(value = "esportaDatiSian")
	public ModelAndView esportaDatiSian(ModelMap model,
			@ModelAttribute("ricercaDatiSianForm") @Valid RicercaDatiSianForm ricercaDatiSianForm, BindingResult result,
			HttpSession session, HttpServletRequest request) throws BusinessException {
		List<DatiSianDTO> elencoDatiSian = null;
		List<MerceRichiestaDatiSianDTO> merci = new ArrayList<>();
		List<RiexportDatiSianDTO> riexport = new ArrayList<>();

		if (result.getErrorCount() == 0) {
			try {
				ricercaDatiSianForm.setCodTipoRichiesta(getTipoRichiesta(request));
				elencoDatiSian = serviziEJB.getElencoDatiSian(ricercaDatiSianForm);
				if (elencoDatiSian != null) {
					List<Integer> listIdRichieste = new ArrayList<>();
					for (DatiSianDTO certificato : elencoDatiSian) {
						listIdRichieste.add(Integer.parseInt(certificato.getIdRichiesta()));
					}
					if (listIdRichieste.size() > 0) {
						ricercaDatiSianForm.setListIntIdRichiesta(listIdRichieste);
						merci = serviziEJB.getMerciPerDatiSian(ricercaDatiSianForm);
						riexport = serviziEJB.getDatiRiexportSian(ricercaDatiSianForm);
					}
				}

			} catch (Exception exc) {
				addErrorMessage("Errore nell'esportazione dati sian");
			}

			ModelAndView modelAndView = new ModelAndView("excelDatiSianView", "elenco",
					elencoDatiSian != null ? elencoDatiSian : new ArrayList<DatiSianDTO>());
			modelAndView.addObject("codTipoRichiesta", getTipoRichiesta(request));
			modelAndView.addObject("listaMerci", merci);
			modelAndView.addObject("listaRiexport", riexport);
			return modelAndView;
		}

		// Se ci sono stati errori di validazione.. tornerò alla pagina dell'elenco
		setCommonServiziModel(model);
		return new ModelAndView("servizi/datiSian/elenco");
	}

	@PreAuthorize("hasRole('READ')")
	@GetMapping(value = "esportaDatiRichieste")
	public ModelAndView esportaDatiRichieste(ModelMap model,
			@ModelAttribute("ricercaDatiRichiesteForm") @Valid RicercaDatiRichiesteForm ricercaDatiRichiesteForm,
			BindingResult result, HttpSession session, HttpServletRequest request) throws BusinessException {
		List<DatiRichiesteDTO> elencoDatiRichieste = null;

		if (result.getErrorCount() == 0) {
			try {
				ricercaDatiRichiesteForm.setCodTipoRichiesta(getTipoRichiesta(request));
				elencoDatiRichieste = serviziEJB.getElencoDatiRichieste(ricercaDatiRichiesteForm);
			} catch (Exception exc) {
				addErrorMessage("Errore nell'esportazione dati richieste");
			}

			ModelAndView modelAndView = new ModelAndView("excelDatiRichiesteView", "elenco",
					elencoDatiRichieste != null ? elencoDatiRichieste : new ArrayList<DatiRichiesteDTO>());
			modelAndView.addObject("codTipoRichiesta", getTipoRichiesta(request));
			return modelAndView;
		}

		// Se ci sono stati errori di validazione.. torner� alla pagina dell'elenco
		setCommonServiziModel(model);
		return new ModelAndView("servizi/datiRichieste/elenco");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "configura")
	public ModelAndView configuraMessaggi(@ModelAttribute("notificheForm") @Valid NotificheForm notificheForm,
			BindingResult result, ModelMap model, HttpSession session, HttpServletRequest request)
			throws BusinessException {

		if (result.getErrorCount() == 0) {

			try {

				List<CarTNotifica> listaMessaggi = serviziEJB.getElencoMessaggi();
				for (CarTNotifica messaggio : listaMessaggi) {

					switch (messaggio.getIdTipoNotifica().intValue()) {
					case 1:
						notificheForm.setDataInserimentoDaGenerico(messaggio.getInizioValidita());
						notificheForm.setDataInserimentoAGenerico(messaggio.getFineValidita());
						notificheForm.setMessaggioGenerico(messaggio.getMessaggio());
						break;

					case 2:
						notificheForm.setDataInserimentoDaServizio(messaggio.getInizioValidita());
						notificheForm.setDataInserimentoAServizio(messaggio.getFineValidita());
						notificheForm.setMessaggioServizio(messaggio.getMessaggio());
						break;

					case 3:
						notificheForm.setDataInserimentoDaCertificato(messaggio.getInizioValidita());
						notificheForm.setDataInserimentoACertificato(messaggio.getFineValidita());
						notificheForm.setMessaggioCertificato(messaggio.getMessaggio());
						break;
					}
				}

			} catch (Exception exc) {
				addErrorMessage("Errore nel caricamento dei messaggi");
			}

		}

		return new ModelAndView("servizi/messaggistica/messaggistica");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "configura/salva")
	public ModelAndView salvaMessaggi(@ModelAttribute("notificheForm") @Valid NotificheForm notificheForm,
			BindingResult result, ModelMap model, HttpSession session, HttpServletRequest request)
			throws BusinessException {

		if (result.getErrorCount() == 0) {

			try {

				List<CarTNotifica> listaMessaggi = new ArrayList<CarTNotifica>();

				CarTNotifica generico = new CarTNotifica();
				generico.setInizioValidita(notificheForm.getDataInserimentoDaGenerico());
				generico.setFineValidita(notificheForm.getDataInserimentoAGenerico());
				generico.setIdTipoNotifica(CaronteConstants.TIPO_NOTIFICA_GENERICO);
				generico.setMessaggio(notificheForm.getMessaggioGenerico());

				CarTNotifica servizio = new CarTNotifica();
				servizio.setInizioValidita(notificheForm.getDataInserimentoDaServizio());
				servizio.setFineValidita(notificheForm.getDataInserimentoAServizio());
				servizio.setIdTipoNotifica(CaronteConstants.TIPO_NOTIFICA_SERVIZIO);
				servizio.setMessaggio(notificheForm.getMessaggioServizio());

				CarTNotifica certificato = new CarTNotifica();
				certificato.setInizioValidita(notificheForm.getDataInserimentoDaCertificato());
				certificato.setFineValidita(notificheForm.getDataInserimentoACertificato());
				certificato.setIdTipoNotifica(CaronteConstants.TIPO_NOTIFICA_CERTIFICATO);
				certificato.setMessaggio(notificheForm.getMessaggioCertificato());

				listaMessaggi.add(generico);
				listaMessaggi.add(servizio);
				listaMessaggi.add(certificato);

				serviziEJB.salvaMessaggi(listaMessaggi, SecurityUtils.getUtenteLoggato().getId());

				addSuccessMessage("Aggiornamento messaggi effettuato");

			} catch (Exception exc) {
				addErrorMessage("Errore nel salvataggio dei messaggi");
			}

		}

		return new ModelAndView("servizi/messaggistica/messaggistica");
	}

	@PreAuthorize("hasRole('READ')")
	@GetMapping(value = "elencoDocumenti")
	public ModelAndView getElencoDocumenti(ModelMap model, HttpSession session, HttpServletRequest request)
			throws BusinessException {

		try {

			List<CarDAllegato> documentazione = serviziEJB.getDocumentazione();
			model.put("docList", documentazione);

		} catch (Exception exc) {
			addErrorMessage("Errore nel recupero della documentazione");
		}

		return new ModelAndView("servizi/documentazione/documentazione");
	}
	

	@PreAuthorize("hasRole('READ')")
	@GetMapping(value = "scarica/{idAllegato}", produces = "application/octet-stream")
	public void getDocumento(@PathVariable Long idAllegato, ModelMap model, HttpSession session, HttpServletRequest request,
			 HttpServletResponse response) throws BusinessException {
		
		try {
			
			CarDAllegato documento = serviziEJB.getDocumento(idAllegato);
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + documento.getNomeFile().trim());
			response.setContentLength(documento.getAllegato().length);
			response.getOutputStream().write(documento.getAllegato());
			response.getOutputStream().flush();
			
		} catch (Exception exc) {
			addErrorMessage("Errore nel recupero della documentazione");
		}
	}
	
	@GetMapping(value = "scaricaguida/{idAllegato}", produces = "application/octet-stream")
	public void getGuida(@PathVariable Long idAllegato, ModelMap model, HttpSession session, HttpServletRequest request,
			 HttpServletResponse response) throws BusinessException {
		
		try {
			
			CarDAllegato documento = serviziEJB.getDocumento(idAllegato);
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + documento.getNomeFile().trim());
			response.setContentLength(documento.getAllegato().length);
			response.getOutputStream().write(documento.getAllegato());
			response.getOutputStream().flush();
			
		} catch (Exception exc) {
			addErrorMessage("Errore nel recupero della documentazione");
		}
	}

	/**
	 * Mette nel model tutto ciò che è necessario per il funzionamento del CU dei
	 * Servizi (decodifiche e/o altro)
	 */
	private void setCommonServiziModel(ModelMap model) throws BusinessException {
		model.addAttribute("listaNazioni", decodificheEJB.getListaNazioni(false));
		model.addAttribute("ufficioDoganale", decodificheEJB.getUfficioDoganale(true));
		model.addAttribute("listaClassi", decodificheEJB.getListaClassi());
	}

}
