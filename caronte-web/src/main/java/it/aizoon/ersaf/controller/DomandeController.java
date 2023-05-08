package it.aizoon.ersaf.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import it.aizoon.ersaf.bean.form.AllegatiDomandaForm;
import it.aizoon.ersaf.bean.form.StampaDomandaForm;
import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IDomandeEJB;
import it.aizoon.ersaf.business.IGeneriSpecieEJB;
import it.aizoon.ersaf.business.IManagerInvioMailEJB;
import it.aizoon.ersaf.business.IProtocolloEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.AllegatoDTO;
import it.aizoon.ersaf.dto.CentroAziendaleDomandaDTO;
import it.aizoon.ersaf.dto.CentroAziendaleDto;
import it.aizoon.ersaf.dto.DomandaDto;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.ModuloDTO;
import it.aizoon.ersaf.dto.SitoProduzioneDTO;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.TipoDomandaDTO;
import it.aizoon.ersaf.dto.TipologiaAttMaterialeDTO;
import it.aizoon.ersaf.dto.TipologiaDomandaDTO;
import it.aizoon.ersaf.dto.TipologiaProdSpecieDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.ZonaProtettaSpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDComune;
import it.aizoon.ersaf.dto.generati.CarDCostante;
import it.aizoon.ersaf.dto.generati.CarDMateriale;
import it.aizoon.ersaf.dto.generati.CarDNazione;
import it.aizoon.ersaf.dto.generati.CarDProvincia;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDTipoAttivita;
import it.aizoon.ersaf.dto.generati.CarDTipoModulo;
import it.aizoon.ersaf.dto.generati.CarDVoce;
import it.aizoon.ersaf.dto.generati.CarRDomandaCentroAz;
import it.aizoon.ersaf.dto.generati.CarRDomandaTipologia;
import it.aizoon.ersaf.dto.generati.CarRTipoFlussoCom;
import it.aizoon.ersaf.dto.generati.CarTAllegatoDomanda;
import it.aizoon.ersaf.dto.generati.CarTCentroAziendale;
import it.aizoon.ersaf.dto.generati.CarTDatiDomanda;
import it.aizoon.ersaf.dto.generati.CarTDomanda;
import it.aizoon.ersaf.dto.generati.CarTModulo;
import it.aizoon.ersaf.dto.generati.CarTResponsabilePassaporto;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarTUtente;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.DatiCentroAziendaleForm;
import it.aizoon.ersaf.form.DatiSitoProduzioneForm;
import it.aizoon.ersaf.form.ModaliForm;
import it.aizoon.ersaf.form.NuovaDomandaForm;
import it.aizoon.ersaf.form.RicercaDomandaForm;
import it.aizoon.ersaf.integration.rest.protocollo.dto.ProtocolloOutputDto;
import it.aizoon.ersaf.security.SecurityUtils;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.validator.DomandaValidator;



@SuppressWarnings("unused")
@Controller
@SessionAttributes({ "nuovaDomandaForm", "datiCentroAziendaleForm","modaliForm" })
@RequestMapping(value = { "/autorizzazioni/comunicazioni" })
public class DomandeController extends BaseController {

  @Autowired
  private IDomandeEJB domandeEJB = null;
  
  @Autowired
  private IUtenteEJB utenteEJB = null;

  @Autowired
  private IDecodificheEJB decodificheEJB = null;

  @Autowired
  private DomandaValidator validator;
  
  @Autowired
  private IManagerInvioMailEJB managerInvioMailEJB = null;
  
  @Autowired
  private IGeneriSpecieEJB generiSpecieEJB = null;
  
  @Autowired
  private IProtocolloEJB protocolloEJB = null;
  
  
  


  @PreAuthorize("hasRoleImpExp('READ', #request)")
  @GetMapping(value = "/elenco")
  public String cerca(@ModelAttribute("ricercaDomandaForm") @Valid RicercaDomandaForm form,
      BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request)
      throws BusinessException {

    // Si resettano i form dei cu figli (nuova, modifica....)
    sessionStatus.setComplete();

    try {
      UtenteDTO utente = SecurityUtils.getUtenteLoggato();
      Long idAssociazioneSezione = getSezioneRequest(request);
      
      logger.debug("-- idAssociazioneSezione ="+idAssociazioneSezione);
      model.addAttribute("tipiComunicazione", domandeEJB.getTipiDomandeByIdUtente(utente.getId(), idAssociazioneSezione));
      model.addAttribute("statiComunicazione", decodificheEJB.getListaStatiComunicazione());     
      model.addAttribute("flSuperUser", utente.isSuperUser());     

      if (utente.isSuperUser()) {
        /*
         * Se è un super user, al primo caricamento della pagina verrà proposto
         * "Inoltrata" come stato selezionato della comunicazione
         */
        if (request.getParameter("idStatoComunicazione") == null) {
          form.setIdStatoComunicazione(CaronteConstants.STATO_COMUNICAZIONE_INOLTRATA);
        }
      } 
      else {
        /*
         * Se è un utente normale, può consultare solo le comunicazioni create da lui
         */
    	  // MODIFICA : Se è un utente normale, può consultare solo le comunicazioni PER LA/LE AZIENDE ALLE QUALI è LEGATO
        form.setIdUtenteInsert(utente.getId());
      }
      
      form.setIdAssociazioneSezione(getSezioneRequest(request));	  
      form.setIdUtenteLoggato(utente.getId());

      List<DomandaDto> elencoDomande = domandeEJB.getElencoDomande(form);
      
	  // Note : ora il numero protocollo arriva formattato corretto
      //modifica per la visualizzazione corretta del campo "numero protocollo"
      for(DomandaDto domanda : elencoDomande){
    	  formattaProtocollo(domanda);
      }

      model.addAttribute("elencoDomande", elencoDomande);
     
    } 
    catch (BusinessException exc) {
      logger.error("BusinessException in cerca getElencoDomande :"+exc.getMessage());	
      addErrorMessage("Errore nella ricerca delle domande");
    }

    return "comunicazioni/elenco";
  }
  
  private void formattaProtocollo(DomandaDto domanda) {
	  if(domanda.getNumeroProtocollo() != null){
  		//nuovo codice: M1.YYYY.XXXXXXX  
  		//Y=anno X=lunghezza massima della parte finale del codice, costituita da 7 caratteri
  		  if(domanda.getNumeroProtocollo().length()<=7){
  			StringBuilder protocolloEsteso = new StringBuilder();
				protocolloEsteso.append("M1.");
				if(domanda.getDataProtocollo() != null){
					protocolloEsteso.append(domanda.getDataProtocollo().getYear() + 1900);
				}
				protocolloEsteso.append(".");  
				for(int i=0; i<(7-domanda.getNumeroProtocollo().length()); i++){
					protocolloEsteso.append("0");
				}
				protocolloEsteso.append(domanda.getNumeroProtocollo());
				domanda.setNumeroProtocollo(protocolloEsteso.toString());
  		}
  	  }
}

@PreAuthorize("hasRoleImpExp('WRITE', #request)")
 	@GetMapping(value = "/nuova")
 	public String nuova(Model model, HttpServletRequest request) throws BusinessException {
 		UtenteDTO utente = SecurityUtils.getUtenteLoggato();
 		Long idAssociazioneSezione = getSezioneRequest(request);
 		logger.debug("-- idAssociazioneSezione =" + idAssociazioneSezione);
 		model.addAttribute("listaTipiComunicazioni", domandeEJB.getTipiDomandaFlusso(utente.getId(), idAssociazioneSezione));

 		return "comunicazioni/nuova";
 	}
  
  @ModelAttribute("nuovaDomandaForm")
  public NuovaDomandaForm getNuovaDomandaForm(HttpServletRequest request) {
	NuovaDomandaForm form = new NuovaDomandaForm();
    return form;
  }
  
  
  // Calcolo codice centro aziendale
  @GetMapping(value = "/getCodiceCentroAzByIdProv_{idProvSel}")  
  @ResponseBody
  public String getCodiceCentroAzByIdProv(@PathVariable("idProvSel") String idProvSel, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {
    String codiceCentroAz = "";
    
	// Calcolo del codice centro aziendale
    logger.debug("*** Calcolo del codice centro aziendale ***");
	 
    Long idSpedizioniere = form.getIdAzienda(); 
    		
	logger.debug("-- idProvincia ="+idProvSel);
	logger.debug("-- idSpedizioniere ="+idSpedizioniere);
	codiceCentroAz = domandeEJB.getCodiceCentroAzByIdProvIdSpediz(Long.parseLong(idProvSel), idSpedizioniere);
	logger.debug("-- codiceCentroAz ="+codiceCentroAz);
	 
		  
	return codiceCentroAz; 
  }
  
  
  // Recupera i dati del centro aziendale
  @GetMapping(value = "/getCentroAzById_{idCentroAz}")
  @ResponseBody
  public String getCentroAzById(@PathVariable("idCentroAz") String idCentroAz, Model model, HttpServletRequest request) throws BusinessException {	    	    
	  // Cerco i dati del centro aziendale
	  logger.debug("-- Ricerco il centro aziendale con id ="+idCentroAz);
	  CarTCentroAziendale centroAz = domandeEJB.getCentroAziendaleById(Long.parseLong(idCentroAz));
	  // Note : non dovrebbe mai succedere questo caso
	  if (centroAz == null) {
		  return "NOTEXISTS";
	  } 
	  else {		  		  
		  StringBuilder sbComuni = new StringBuilder("");;		    	

		  // --- Recupero provincia ed elenco comuni
		  Long idProvincia = null;
		  if(centroAz.getIdComune() != null){    		
			  idProvincia = domandeEJB.getIdProvinciaByIdComune(centroAz.getIdComune());
			  logger.debug("-- idProvincia ="+idProvincia);				  

			  // Dati per la combo Comuni
			  sbComuni = new StringBuilder("<option value=\"\">Selezionare</option>");	      
			  CarDComune comune = decodificheEJB.getComuneByPrimaryKey(centroAz.getIdComune());	      
			  List<CarDComune> listaComuni = new ArrayList<>();

			  listaComuni = decodificheEJB.getListaComuni(comune.getIdProvincia());
			  for (CarDComune com : listaComuni) {
				  sbComuni.append("<option value=\"" + com.getIdComune() + "\">" + com.getDenomComune() + "</option>");
			  }	      		  
		  }		  
		  return 
				  centroAz.getIdCentroAziendale() + "&&&" +
				  centroAz.getCodCentroAziendale() + "&&&" +
				  centroAz.getDenominazione() + "&&&" +
				  idProvincia + "&&&" +
				  centroAz.getIdComune()+ "&&&" +
				  centroAz.getCap() + "&&&" +
				  centroAz.getFrazione() + "&&&" +
				  centroAz.getIndirizzo() + "&&&" +
				  centroAz.getTelefono() + "&&&" +
				  centroAz.getCellulare() + "&&&" +
				  centroAz.getEmail() + "&&&" +
				  centroAz.getPec() + "&&&" +				  
				  sbComuni.toString();								  
	  }	    
  }
  
  // Recupera i dati dell'azienda, legata all'utente
  @GetMapping(value = "/getSpedizioniereById_{idAzienda}")
  @ResponseBody
  public String getSpedizioniereById(@PathVariable("idAzienda") String idAzienda, Model model, HttpServletRequest request) throws BusinessException {	    	    
	  // Cerco i dati dello spedizioniere per idSpedizioniere
	  logger.debug("-- Ricerco il cuaa dello spedizioniere");
	  CarTSpedizioniere spedizioniere = utenteEJB.getCarTSpedizioniere(Long.parseLong(idAzienda));
	  // Note : non dovrebbe mai succedere questo caso
	  if (spedizioniere == null) {
		  return "NOTEXISTS";
	  } 
	  else {
		  logger.debug("-- Ricerco i dati dello spedizioniere da visualizzare nella pagina");
		  SpedizioniereDTO spediz = utenteEJB.getSpedizioniereByCuua(spedizioniere.getCuaa());
		  
		  StringBuilder sbComuniSedeLegale = new StringBuilder("");;		    	

		  // --- Recupero provincia sede legale
		  Long idProvinciaSedeLegale = null;
		  if(spediz.getIdComuneSedeSociale() != null){    		
			  idProvinciaSedeLegale = domandeEJB.getIdProvinciaByIdComune(spediz.getIdComuneSedeSociale());
			  logger.debug("-- idProvinciaSedeLegale ="+idProvinciaSedeLegale);	

			  // Dati per la combo Comuni sede legale
			  sbComuniSedeLegale = new StringBuilder("<option value=\"\">Selezionare</option>");	      
			  CarDComune comune = decodificheEJB.getComuneByPrimaryKey(spediz.getIdComuneSedeSociale());	      
			  List<CarDComune> listaComuniSedeLegale = new ArrayList<>();

			  listaComuniSedeLegale = decodificheEJB.getListaComuni(comune.getIdProvincia());
			  for (CarDComune com : listaComuniSedeLegale) {
				  sbComuniSedeLegale.append("<option value=\"" + com.getIdComune() + "\">" + com.getDenomComune() + "</option>");
			  }	      		  
		  }		  
		  return 
				  spediz.getIdSpedizioniere()+ "&&&" +
				  spediz.getIdTipoSpedizioniere()+ "&&&" +
				  spediz.getCuaa() + "&&&" +
				  spediz.getCognomeDitta() + "&&&" +
				  spediz.getNomeDitta() + "&&&" +
				  spediz.getDenomSpedizioniere() + "&&&" +
				  spediz.getIdProvinciaSedeSociale() + "&&&" +
				  spediz.getIdComuneSedeSociale() + "&&&" +
				  spediz.getCapSedeSociale() + "&&&" +
				  spediz.getIndirizzoSedeSociale() + "&&&" +
				  spediz.getNumeroTelefono() + "&&&" +
				  spediz.getNumeroCellulare()  + "&&&" +
				  spediz.getNumeroFax() + "&&&" +
				  spediz.getEmailSpedizioniere() + "&&&" +
				  spediz.getPec() + "&&&" +
				  sbComuniSedeLegale.toString() + "&&&" +
				  spediz.getPartitaIVA()+ "&&&" +
				  spediz.getTipoSpedizioniereAltro()+"&&&"+
				  spediz.getCodiceRUOP();								  
	  }	    
  }
  
  
  
  // Recupera i dati dell'utente, se l'utente Superuser, valorizza il campo codice fiscale
  @GetMapping(value = "/checkIfUtenteExists_{codiceFiscale}")
  @ResponseBody
  public String checkIfUtenteExists(@PathVariable("codiceFiscale") String codiceFiscale, Model model,  @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, HttpServletRequest request)
      throws BusinessException {

    CarTUtente utente = utenteEJB.getUtenteByCodiceFiscale(codiceFiscale);
    form.setIdUtente(utente.getIdUtente());
    if (utente == null) {
      return "NOTEXISTS";
    } 
    else {    	
    	StringBuilder sbComuniNascita = new StringBuilder("");;
    	StringBuilder sbComuniResidenza = new StringBuilder("");;
    	
    	// --- Recupero provincia di nascita
    	Long idProvinciaNasc = null;
    	if(utente.getIdComuneNascita() != null){    		
		  idProvinciaNasc = domandeEJB.getIdProvinciaByIdComune(utente.getIdComuneNascita());
		  logger.debug("-- idProvinciaNasc ="+idProvinciaNasc);	
		  
		  // Dati per la combo Comuni nascita
		  sbComuniNascita = new StringBuilder("<option value=\"\">Selezionare</option>");	      
	      CarDComune comune = decodificheEJB.getComuneByPrimaryKey(utente.getIdComuneNascita());	      
	      List<CarDComune> listaComuniNascita = new ArrayList<>();

	      listaComuniNascita = decodificheEJB.getListaComuni(comune.getIdProvincia());
	      for (CarDComune com : listaComuniNascita) {
	        sbComuniNascita.append("<option value=\"" + com.getIdComune() + "\">" + com.getDenomComune() + "</option>");
	      }	      		  
    	}  
    	
    	// --- Recupero provincia di residenza
    	Long idProvinciaResid = null;
    	if(utente.getIdComuneResidenza() != null){    		
  		  idProvinciaResid = domandeEJB.getIdProvinciaByIdComune(utente.getIdComuneResidenza());
  		  logger.debug("-- idProvinciaResid ="+idProvinciaResid);
  		  
  		  // Dati per la combo Comuni residenza
  		  sbComuniResidenza = new StringBuilder("<option value=\"\">Selezionare</option>");	      
	      CarDComune comune = decodificheEJB.getComuneByPrimaryKey(utente.getIdComuneResidenza());	      
	      List<CarDComune> listaComuniReside = new ArrayList<>();

	      listaComuniReside = decodificheEJB.getListaComuni(comune.getIdProvincia());
	      for (CarDComune com : listaComuniReside) {
	        sbComuniResidenza.append("<option value=\"" + com.getIdComune() + "\">" + com.getDenomComune() + "</option>");
	      }	
      	}
    	
    	// Gestione formato Data nascita per il Datepicker
    	int year = 0;
    	int month = 0;
    	int day = 0;
    	String dataNascita ="";
    	if(utente.getDataNascita() != null){    	
    		LocalDate date = utente.getDataNascita().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    		day = date.getDayOfMonth();
    		logger.debug("-- day ="+day);
    		month = date.getMonthValue();
    		logger.debug("-- month ="+month);
    		year = date.getYear();
    		logger.debug("-- year ="+year);
    		    
    		String dayStr = "";
    		if(day < 10){
    		  dayStr = "0"+day;
    		}
    		else{
    			dayStr = ""+day;	
    		}
    		String monthStr = "";
    		if(month < 10){
    			monthStr = "0"+month;	
    		}
    		else{
    			monthStr = ""+month;
    		}
    		
    		
    		dataNascita = dayStr+"/"+monthStr+"/"+year;
    		logger.debug("-- dataNascita ="+dataNascita);    		    		
    	}
        return 
    		 utente.getIdUtente()+ "&&&" +
    		 utente.getCognome() + "&&&" +
    		 utente.getNome() + "&&&" +
    		 utente.getCodiceFiscale() + "&&&" + 
    		 dataNascita + "&&&" +
    		 idProvinciaNasc + "&&&" +
    		 utente.getIdComuneNascita() + "&&&" +
    		 utente.getDenomComuneEstNascita() + "&&&" +
    		 idProvinciaResid + "&&&" +
    		 utente.getIdComuneResidenza() + "&&&" +
    		 utente.getCap() + "&&&" +
    		 utente.getDenomComuneEstResid() + "&&&" +
    		 utente.getIndirizzo() + "&&&" +
    		 utente.getTelefono() + "&&&" +
    		 utente.getCellulare() + "&&&" +
    		 utente.getEmail()+ "&&&" +    		     		 
    		 sbComuniNascita.toString()+ "&&&" +
    		 sbComuniResidenza.toString()+ "&&&" +
    		 year + "&&&" +
    		 month + "&&&" +
    		 day + "&&&" +
    		 utente.getIdNazioneEstNascita() + "&&&" +
    		 utente.getIdNazioneEstResid();
    }
  }
  
  // Salva Dati Azienda
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @PostMapping(value = { "/azienda/nuova", "/azienda/modifica" })
  public String salvaDatiAzienda(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {
	logger.debug("-- Validazione dei dati dell'azienda");
	
	logger.debug("form.getDenomAzienda():= " + form.getDenomAzienda());
	logger.debug("form.getDenomAzienda() FORMATTATO:= " + formattaPerProtocollazione(form.getDenomAzienda()));
	
	//Fatto per evitare errori futuri in fase di protocollazione
    form.setDenomAzienda(formattaPerProtocollazione(form.getDenomAzienda()));
    form.setIndirizzoSedeLegale(formattaPerProtocollazione(form.getIndirizzoSedeLegale()));
    form.setDenomAzienda(formattaPerProtocollazione(form.getDenomAzienda()));
    
	// Cerco le Tipologie Attività salvate su db per la domanda
    List<TipologiaAttMaterialeDTO> tipologieAttMaterialidb = domandeEJB.getTipologieAttMateriale(form.getIdDomanda());
    logger.debug("-- idAzienda selezionata ="+form.getIdAzienda());
    logger.debug("-- idTipoComunicazione ="+form.getIdTipoComunicazione());
    /*Mostro il tab passaporto se nelle tipologie attività è presente
     * vivaismo, ...*/
    form.setTabPassaporto(false);
    for(TipologiaAttMaterialeDTO t: tipologieAttMaterialidb){
    	if(t.getIdTipologia() == CaronteConstants.ID_TIPO_ATTIVITA_VIVAISMO /*|| altreAttivitaDaAggiungere*/){
    		form.setTabPassaporto(true);
    	}
    }
    
    /*
     *  Controlli per la Domanda Passaporto : 
     *  1) Presenza codice ruop
     *  2) Assenza codice ruop
     */
    if (form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO) {
    	logger.debug("-- ** CASO DOMANDA DI PASSAPORTO **");    	
    	CarTSpedizioniere sped = utenteEJB.getCarTSpedizioniere(form.getIdAzienda());
    	logger.debug("-- * codiceRuop ="+sped.getCodiceRuop());
    	logger.debug("-- * idSpedizioniere selezionato ="+form.getIdAzienda());
    	
    	// 1) Presenza codice ruop
    	if (sped.getCodiceRuop() != null) {    		
    		Long idDomandaRuopConvalidata = domandeEJB.getIdDomandaRuopConvalidata(form.getIdAzienda());    		
    		logger.debug("-- idDomandaRuopConvalidata ="+idDomandaRuopConvalidata);
    		    		
    		form.setTabPassaporto(true);
    		
    		/* ********* Gestione duplicazione i dati della Domanda Ruop Convalidata, con lo stesso idAzienda *************
			 * - se non è ancora presente l'idAzienda per la Domanda Passaporto : duplico
			 *  - se è già presente l'idAzienda per la domanda Passaporto : 
			     - se è lo stesso : non duplichiamo più i dati (l'utente ha fatto salva ma non ha cambiato l'idAzienda selezionato)
			     - se è cambiato : rimuovo i dati duplicati in precedenza(di tutti i tab) e duplico  
			 */      		    		
    		CarTDomanda domandaPassaporto = domandeEJB.getCarTDomandaByIdDomanda(form.getIdDomanda());
    		// Note : dovremmo sempre entrare in questo ramo perchè la domanda viene inserita nel Tab Dati Anagrafici
    		if(domandaPassaporto != null){
    			Long idSpedDomandaPassDb = domandaPassaporto.getIdSpedizioniere();
    			logger.debug("-- idSpedDomandaPassDb ="+idSpedDomandaPassDb);
    			// non è ancora presente l'idAzienda per la Domanda Passaporto : duplico
    			if(idSpedDomandaPassDb == null){
    				if (idDomandaRuopConvalidata != null) {
    					form.setDomandaRuopConvalidataPresente(true);
    	    			form.setIdDomandaPrecedente(idDomandaRuopConvalidata);    			
    	    			//sulla domanda ruop convalidata potrebbe essere salvato il idCentroAziendale che ha il passaporto
    	    			form.setIdCentroAziendalePassaporto(domandeEJB.getIdCentroAziendalePassaportoByIdDomanda(idDomandaRuopConvalidata));
    	    			logger.debug("-- idCentroAziendalePassaporto della Domanda Ruop convalidata = "+form.getIdCentroAziendalePassaporto());
    	    			
	    				logger.debug("-- Duplico i dati della domanda ruop, idDomandaRuopConvalidata ="+idDomandaRuopConvalidata);    			
	    	    		try {
	    					duplicaDatiDomandaRuop(form, model);
	    				}
	    	    		catch (Exception e) {					
	    	    		  logger.error("-- Exception in duplicaDatiDomandaRuop ="+e.getMessage());	
	    				  addErrorMessage("Errore nel salvataggio dei dati azienda");					
	    				}
    				}
    			}
    			// è già presente l'idAzienda per la domanda Passaporto
    			else{
    				Long idSpedizioniereSel = form.getIdAzienda();
    				logger.debug("-- idSpedizioniereSel ="+idSpedizioniereSel);
    				//  è cambiato : rimuovo i dati duplicati in precedenza(di tutti i tab) e duplico
    				if(idSpedizioniereSel.longValue() != idSpedDomandaPassDb.longValue()){
    					// rimuovo i dati duplicati in precedenza
    					domandeEJB.deleteDomandaPassaporto(form.getIdDomanda());
    					if (idDomandaRuopConvalidata != null) {
    						form.setDomandaRuopConvalidataPresente(true);
    		    			form.setIdDomandaPrecedente(idDomandaRuopConvalidata);    			
    		    			//sulla domanda ruop convalidata potrebbe essere salvato il idCentroAziendale che ha il passaporto, se è nullo i dati non vengono duplicati
    		    			form.setIdCentroAziendalePassaporto(domandeEJB.getIdCentroAziendalePassaportoByIdDomanda(idDomandaRuopConvalidata));
    		    			logger.debug("-- idCentroAziendalePassaporto = "+form.getIdCentroAziendalePassaporto());
    		    			
	    					// duplico i dati
	    					logger.debug("-- Duplico i dati della domanda ruop, idDomandaRuopConvalidata ="+idDomandaRuopConvalidata);    			
	    		    		try {
	    						duplicaDatiDomandaRuop(form, model);
	    					}
	    		    		catch (Exception e) {					
	    		    		  logger.error("-- Exception in duplicaDatiDomandaRuop ="+e.getMessage());	
	    					  addErrorMessage("Errore nel salvataggio dei dati azienda");					
	    					}
    					}
    				}
    			}
    		}
    		
    		
    		/* ************ Gestione visualizzazione Tab e dati in Tab Azienda **********************
    		 *  1) Presenza codice ruop :
    		 * 	1.1) Presenza di una domanda Ruop :
    		 *  	 1.1.1) in stato Convalidata -> OK
    		 *      	- settare nel form il campo domandaRuopConvalidataPresente = true : 
    		 *   	1.1.2) in stato diversa da Convalidata -> KO : messaggio all'utente
    		 *   
    		 * 	1.2) Assenza di una Domanda Ruop : 
    		 *    settare nel form il campo domandaRuopConvalidataPresente = false : 
    		 */    		
    		if (idDomandaRuopConvalidata == null) {
    			/*
    			 *  Non è presente la domanda CONVALIDATA su Caronte, quindi Domanda importata o 
    			 *  l'utente sta creando una domanda di Passaporto prima che venga convalidata la Domanda Ruop inserita per l'azienda stessa
    			 */
    			logger.debug("-- *** CASO INSERIMENTO DOMANDA PASSAPORTO e DOMANDA RUOP CONVALIDATA NON PRESENTE SU DB ***");
    			// Controllare se esiste la Domanda Ruop in stato diverso da convalidata
    			CarTDomanda domandaRuop = domandeEJB.getCarTDomandaByIdDomanda(idDomandaRuopConvalidata);
    			// Esiste la Domanda Ruop ma è in stato diverso da CONVALIDATA, bloccare l'utente
    			if(domandaRuop != null){
    				logger.error("-- ** Esiste la Domanda Ruop ma è in stato diverso da CONVALIDATA, bloccare l'utente");
    				logger.debug("--  domandaRuop.getIdStatoComunicazione() ="+domandaRuop.getIdStatoComunicazione());
    				
    				addErrorMessage("Attenzione: esiste una Domanda Ruop per l'Azienda, ma non è ancora stata Convalidata : non è possibile procedere con l'inserimento di una Domanda di Autorizzazione Passaporto"); 
    	    		domandeEJB.deleteDomandaPassaporto(form.getIdDomanda());
    	    		logger.debug("-- Domanda Passaporto in bozza eliminata: "+form.getIdDomanda());	

    	    		return getRedirect("comunicazioni/elenco", request); 						
    			}
    			/* Non esiste una Domanda Ruop su db per l'azienda selezionata
    			   Verrà mostrato il Tab Passaporto vuoto con dati da inserire (l'utente potrà scegliere se è un aggiornamento con il radio) 
    			   e tutte le parti per poter inserire i dati della Domanda Ruop non presente su db
    			 */   
    			else{
    				logger.debug("-- * Non esiste una Domanda Ruop su db per l'azienda selezionata");
    				form.setDomandaRuopConvalidataPresente(false);
    			}
    		}
    	}
    	else{
    		/* 2) Assenza codice ruop : 
    		 *  - rimuovo l'eventuale domanda Passaporto inserita per un'altra azienda (tutti i tab)
    		 *  - segnalo che l'utente deve fare una nuova domanda ruop
    		 */    		
    		logger.error("-- Azienda senza codice RUOP, impossibile fare un nuovo passaporto");	
    		addErrorMessage("Attenzione: Operatore senza Registrazione RUOP, non è possibile richiedere il passaporto. Procedere con Nuova domanda di registrazione RUOP"); 
    		domandeEJB.deleteDomandaPassaporto(form.getIdDomanda());
    		logger.debug("-- Domanda Passaporto in bozza eliminata: "+form.getIdDomanda());	

    		return getRedirect("comunicazioni/elenco", request); 						
    	} 
    }
	
	if(form.getIdAzienda() == 0){
	  // Validazione dati nuova azienda	
	  validator.validateDatiNuovaAzienda(form, tipologieAttMaterialidb, result);
	}  
	else{
	   // Validazione dati modifica azienda
	  validator.validateDatiModificaAzienda(form, tipologieAttMaterialidb, result);
	}
	
    if (result.getErrorCount() == 0) {
      logger.debug("-- Non ci sono errori di validazione sui dati dell'azienda, posso procedere con il salvataggio");	
      try {
        Long idUtente = SecurityUtils.getUtenteLoggato().getId();
        
        /* - Se dalla combo 'Azienda' è stata selezionata 'Nuova azienda' : inserisco una nuova azienda in car_t_spedizioniere e car_r_utente_spedizionieri
         * - Se dalla combo 'Azienda' è stata selezionata un'azienda : effettuo update su car_t_spedizioniere
         * 
         * Salvare poi l'idSpedizioniere in CAR_T_DOMANDA, per l'idDomanda che si sta inserendo i dati
         */        
        if(form.getIdAzienda() != null){
        	String messaggio ="";
        	Long idSpedizioniere = null;
        	if(form.getIdAzienda() == 0){
        	  logger.debug("-- CASO INSERIMENTO NUOVA AZIENDA in CAR_T_SPEDIZIONIERE");	
        	  UtenteDTO utente = SecurityUtils.getUtenteLoggato();
        	  idSpedizioniere = utenteEJB.insertSpedizioniereDomanda(form, idUtente);
        	  form.setIdAzienda(idSpedizioniere);
        	  messaggio = "Dati azienda inseriti";
        	}
        	else{
        	  logger.debug("-- CASO AGGIORNAMENTO dati in CAR_T_SPEDIZIONIERE");
        	  utenteEJB.updateSpedizioniereDomanda(form,idUtente);
        	  idSpedizioniere = form.getIdAzienda();
        	  messaggio = "Dati azienda modificati";
        	}
        	
        	if (form.getIdDomanda() != null) {
	        	logger.debug("-- ***** AGGIORNO i dati in car_t_domanda (TAB DATI AZIENDA) e salvo le attivita' azienda **********");
	        	logger.debug("-- idDomanda da aggiornare ="+form.getIdDomanda());
	        	logger.debug("--- idSpedizioniere ="+idSpedizioniere);        	
	        	domandeEJB.aggiornaDatiAzienda(form,idUtente);
	        	
	        	logger.debug("-- idTipoComunicazione ="+form.getIdTipoComunicazione());	  
	        	logger.debug("-- isDomandaRuopConvalidataPresente ="+form.isDomandaRuopConvalidataPresente());
	        	// Se non è una Domanda di Passaporto
	        	/*if (form.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO ||
	        		 (form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO && !form.isDomandaRuopConvalidataPresente())*/
	        	  if (form.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO ) { 	        	
		        	// aggiorno i dati per la spunta richiesta passaporto
		    		domandeEJB.aggiornaRichiestaPassaporto(form, idUtente);
		    		aggiornaTipologia(form, idUtente);
		        	
		        	//Aggiorno le tipologie  e abilito i tab da visualizzare
		        	if (form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP || 
		        		form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP 
		        				//||(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO && !form.isDomandaRuopConvalidataPresente())
		        		) {
		        		//recupero le tipologie dalle attivita' che sono state aggiunte
		        		String[] idtipologiaArr = domandeEJB.getTipologieByIdDomanda(form.getIdDomanda());
		        		form.setIdTipologia(idtipologiaArr);	
		
		        		domandeEJB.aggiornaTipologia(form, idUtente);        		
		
		        		boolean tabImport = false;
		        		boolean tabExport = false;
		        		boolean tabPassaporto = false;
		        		for (int i = 0; i < idtipologiaArr.length; i++) {
		        			Long idTipologia = Long.parseLong(idtipologiaArr[i]);
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
		        		form.setTabImport(tabImport);
		        		form.setTabExport(tabExport);
		        		form.setTabPassaporto(tabPassaporto);        
		           	}      	
	        	} 
	        	// Nel caso di Domanda Passaporto vedremo sempre il Tab Passaporto e mai il Tab Import ed Export  
	        	else if (form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
	        		logger.debug("-- Nel caso di Domanda Passaporto vedremo sempre il Tab Passaporto e mai il Tab Imort ed Export ");
	        		form.setTabPassaporto(true);
	        		form.setTabImport(false);
	        		form.setTabExport(false);
	        	}
        	}
			//se ho inserito i dati di un nuovo centro aziendale, lo salvo
					if(form.getCodCentroAz() != null){
						aggiungiCentroAziendale(form, model);
					}
					//caso inserimento nuova domanda: precarico i centri aziendali associati allo spedizioniere, se presenti
					List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB
							.getCentriAziendaliByIdDomanda(form.getIdDomanda());
					if (null == centriAziendaliDb || centriAziendaliDb.size() == 0) {
						logger.debug("-- Non ci sono centri aziendali legati alla domanda da visualizzare");
						// se in anagrafica sono presenti centri aziendali allora
						// precompilo la tabella centri az
						logger.debug("-- form.getIdSpedizioniere() =" + form.getIdAzienda());
						
						List<CentroAziendaleDto> centriAzAnagraficaDB = domandeEJB
								.getCentriAziendaliByIdSpediz(idSpedizioniere);
						logger.debug("-- centriAzAnagraficaDB.size() =" + centriAzAnagraficaDB.size());
						UtenteDTO utente = SecurityUtils.getUtenteLoggato();
						if (centriAzAnagraficaDB.size() > 0) {
							for (int i = 0; i < centriAzAnagraficaDB.size(); i++) {
								CentroAziendaleDto centroAz = centriAzAnagraficaDB.get(i);
								Long idCentroAz = domandeEJB.preSalvataggioCentroAziendaleDomanda(form.getIdDomanda(), centroAz,
										utente);
							}
							
						}
					}
					
        	addSuccessMessage(messaggio);        	
        }        
        
        
        // Nel caso di Nuova domanda, passo al tab successivo, nel caso di modifica si rimane sul Tab Dati Azienda
        if (isNuovaDomanda(request)) {
        	// Se siamo nel caso di Domanda Cancellazione passo al tab Allegati
            if (form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP) {        
            	return getRedirect("comunicazioni/allegati/nuova", request);
            }
            
        	// Se siamo nel caso di Domanda Passaporto : passo al Tab Passaporto
        	//if(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO && form.isDomandaRuopConvalidataPresente()){   
            if(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
        		return getRedirect("comunicazioni/passaporto/nuova", request);
        	}
        	// In tutti gli altri casi passo al Tab Centro Aziendale
        	return getRedirect("comunicazioni/azienda/nuova", request);
        }
	} 
    catch (Exception be) {
		addErrorMessage("Errore nel salvataggio dei dati azienda");
	}
   }
   // Gestione visualizzazione messaggi di errore    
   else{
	   if (result.getFieldError("richiestaPassaporto") != null) {
		   logger.debug("errore richiesta passaporto= "+ result.getFieldError("richiestaPassaporto"));
		   addErrorMessage("Non è stato selezionato il campo richiesta passaporto, obbligatorio per l'attività Vivaismo"); 
	   } 
	    else {
	    	if(result.getFieldError("codFiscaleAz") != null){
		 		   addErrorMessage("Il Cuaa inserito è diverso dal codice fiscale presente nell' anagrafica"); 
	    	}
	    	else{
	 		   addErrorMessage("Indicare i dati anagrafici dell'azienda, almeno un'attivita' svolta dall'Operatore Professionale ed almeno una Tipologia attivita'"); 
	    	}
	    }
	 
     logger.debug("Non sono stati superati i controlli di validazione dati azienda"); 	
   }
   setDatiAziendaModel(model, form, SecurityUtils.getUtenteLoggato());
   return getViewNuovaModifica("comunicazioni/azienda", request);
  }

  
	// Salva Dati anagrafici
    @PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = { "/anagrafica/nuova", "/anagrafica/modifica" })
    public String salvaDatiAnagrafici(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm nuovaDomandaForm, BindingResult result, Model model, HttpServletRequest request) throws BusinessException {

    	// Validazione dati anagrafici
    	logger.debug("-- Validazione dati anagrafici utente");
    	logger.debug("-- Validazione dati anagrafici utente nuovaDomandaForm.isNascitaEstera() = "+nuovaDomandaForm.isNascitaEstera());
    	validator.validateDatiAnagrafici(nuovaDomandaForm, result);

    	if (result.getErrorCount() == 0) {
    		logger.debug("-- Sono stati superati i controlli di validazione dei dati anagrafici utente");
    		try {
    			Long idUtente = SecurityUtils.getUtenteLoggato().getId();
    			/*
    			 * CASO INSERIMENTO NUOVA DOMANDA
    			 */
    			if (nuovaDomandaForm.getIdDomanda() == null) {	    				
    				logger.debug("Caso : inserimento nuova domanda");					
    				logger.debug("*** INSERIMENTO DATI IN CAR_T_DOMANDA ***");
    				nuovaDomandaForm.setIdDomanda(domandeEJB.inserisciNuovaDomanda(nuovaDomandaForm, SecurityUtils.getUtenteLoggato()));										
    				logger.debug("CICCIO: "+nuovaDomandaForm.getIdDomanda());
    				// Setto l'idUtente nel form
    				// Cerco idUtente da settare nella domanda
    				logger.debug("--- Cerco idUtente da settare nella domanda");
    				CarTUtente utenteDomanda = utenteEJB.getUtenteByCodiceFiscale(nuovaDomandaForm.getCodFiscale());
    				if(null != utenteDomanda){
    					logger.debug("-- idUtente delle domanda ="+utenteDomanda.getIdUtente());
    					nuovaDomandaForm.setIdUtente(utenteDomanda.getIdUtente());  
    				}
    				addSuccessMessage("Domanda creata");    				
    			} 
    			//Caso di modifica Dati anagrafici
    			else {
    				/*
    				 * CASO MODIFICA DOMANDA
    				 */	    				
    				logger.debug("-- CASO MODIFICA DATI ANAGRAFICI DELLA DOMANDA");
    				logger.debug("CICCIO: "+nuovaDomandaForm.getIdDomanda());
    				if (!checkModificaDomanda(idUtente, nuovaDomandaForm)) {
    					return getRedirect("comunicazioni/elenco", request);
    				}
    				
    				// Setto l'idUtente nel form
    				// Cerco idUtente da settare nella domanda
    				logger.debug("--- Cerco idUtente da settare nella domanda");
    				CarTUtente utenteDomanda = utenteEJB.getUtenteByCodiceFiscale(nuovaDomandaForm.getCodFiscale());
    				if(null != utenteDomanda){
    					logger.debug("-- idUtente delle domanda ="+utenteDomanda.getIdUtente());
    					nuovaDomandaForm.setIdUtente(utenteDomanda.getIdUtente());  
    				}
    				
    				domandeEJB.aggiornaDatiAnagrafici(nuovaDomandaForm, SecurityUtils.getUtenteLoggato());    	
    				
    				
    				// --- Dati Tab Azienda
 				   	logger.debug("-- Refresh Dati Tab Azienda");
 				   	DomandaDto domandaAzienda = domandeEJB.getDettaglioAziendaByIdDomanda(nuovaDomandaForm.getIdDomanda()); 				   	
 					setDatiAziendaModifica(nuovaDomandaForm, SecurityUtils.getUtenteLoggato(), domandaAzienda);

    				addSuccessMessage("Dati anagrafici modificati");
    			}

    			/*
    			 * Nel caso di Nuova domanda, si passa alla scheda successiva per la compilazione (Tab Azienda)
    			 */
    			if (isNuovaDomanda(request)) {    				
    				// Se si sta creando una domanda nel flusso, di tipo 'Variazione RUOP', si devono salvare sul db anche tutti i dati degli altri tab della Domanda RUOP
    				Long idDomandaPrec = nuovaDomandaForm.getIdDomandaPrecedente();
    				logger.debug("-- idDomandaPrecedente ="+nuovaDomandaForm.getIdDomandaPrecedente());
    				logger.debug("-- idTipoComunicazione salvata ="+nuovaDomandaForm.getIdTipoComunicazione());
    				if(nuovaDomandaForm.getIdDomandaPrecedente() != null && nuovaDomandaForm.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP){

    					duplicaDatiDomandaVariazionRuop(nuovaDomandaForm, model);

    				}	
    				if(nuovaDomandaForm.getIdDomandaPrecedente() != null && nuovaDomandaForm.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP){

    					//salvaDatiAzienda(nuovaDomandaForm, result, model, null, request);
    					logger.debug("-- ***** AGGIORNO i dati in car_t_domanda (TAB DATI AZIENDA)**********");
    		        	logger.debug("-- idDomanda da aggiornare ="+nuovaDomandaForm.getIdDomanda());
    		        	logger.debug("--- idSpedizioniere ="+nuovaDomandaForm.getIdSpedizioniere());        	
    		        	domandeEJB.aggiornaDatiAzienda(nuovaDomandaForm,idUtente);

    				}
    				   				
    				logger.debug("-- Caso di nuova domanda : passo al Tab successivo : Azienda");
    				return getRedirect("comunicazioni/azienda/nuova", request);
    			}

    		} 
    		catch (BusinessException be) {
    			logger.error("-- BusinessException in fase di creazione domanda - Dati anagrafici ="+be.getMessage());
    			addErrorMessage("Errore nel salvataggio dei dati anagrafici");
    		}
    		catch(Exception ex){
    			logger.error("-- Exception in fase di creazione domanda - Dati anagrafici ="+ex.getMessage());
    			addErrorMessage("Errore nel salvataggio dei dati anagrafici");
    		}
    	}
    	else{			
    		logger.debug("-- NON sono stati superati i controlli di validazione dei dati anagrafici utente: "+result.getFieldError());
    	}

    	setDatiAnagraficiModel(model, nuovaDomandaForm);
    	return getViewNuovaModifica("comunicazioni/anagrafica", request);
    }

  
    private boolean isNuovaDomanda(HttpServletRequest request) {
		return request == null ? false : request.getRequestURI().contains("/nuova");
	}
    
    
  
  // Tab Azienda - Elimina Tipologia Attività e Materiale
  @PreAuthorize("hasRoleImpExp('WRITE', #request)") 
  @PostMapping(value = { "azienda/eliminaTipologiaAtt/idAttivitaMaterialeUtente/{idAttivitaMaterialeUtente}" })
  public String eliminaTipologiaAtt(@PathVariable Long idAttivitaMaterialeUtente, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletResponse response) throws BusinessException {
      logger.debug("BEGIN eliminaTipologiaAtt");
	  try { 
		  UtenteDTO utente = SecurityUtils.getUtenteLoggato();
		  logger.debug("---- idDomanda ="+form.getIdDomanda());		  
		  domandeEJB.eliminaTipologAttivita(idAttivitaMaterialeUtente, form.getIdDomanda(), utente.getId());
		  aggiornaTipologia(form, utente.getId());

	  } 
	  catch (Exception e) {
		  logger.error("-- Exception in eliminaTipologiaProd ="+e.getMessage());
		  addErrorMessage("Errore nell'eliminazione della Tipologia produttiva");	  
	  }	 
	  finally{
		  logger.debug("END eliminaTipologiaAtt");
	  }
	  
	  setDatiAziendaModel(model, form, SecurityUtils.getUtenteLoggato());
	 // return getViewNuovaModifica("comunicazioni/azienda", request);
	  return null;
  }
    
  
	// Nuova domanda RUOP - Tab Centri aziendali

	// Tab Dati anagrifici : dati anagrafici precaricati con i dati anagrafici
	// dell'idUtente che si è autenticato
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/centroaziendale/nuova/{id}", "/centroaziendale/modifica/{id}" })
	public String datiCentroAziendale(@PathVariable Long id, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form,
			Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("-- idCentroAziendale selezionato =" + id);

		form.setIdCentroAziendaleSel(id);

		return datiCentriAziendali(form, model, request);
	}

 @PreAuthorize("hasRoleImpExp('WRITE', #request)")
 @GetMapping(value = { "/centroaziendale/nuova", "/centroaziendale/modifica" })
 public String datiCentriAziendali(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {	
   try {
	  UtenteDTO utente = SecurityUtils.getUtenteLoggato();
	  
	  // --- Controllo se ci sono dati da visualizzare nella pagina
	  // Carico i dati dei centri aziendali
	  List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
	  if(null != centriAziendaliDb && centriAziendaliDb.size()>0){
		  logger.debug("-- Ci sono centri aziendali legati alla domanda da visualizzare");
		  model.addAttribute("centriAziendaliDb",centriAziendaliDb);
		  		  
		  // Seleziono il radio del primo centro aziendale presente su db se non ne è già stato selezionato uno
		  logger.debug("-- IdCentroAziendaleSel ="+form.getIdCentroAziendaleSel());
		  if(form.getIdCentroAziendaleSel() == null){
			  form.setIdCentroAziendaleSel(centriAziendaliDb.get(0).getIdCentroAziendale());
			  logger.debug("-- IdCentroAziendale da selezionare ="+form.getIdCentroAziendaleSel());
		  }
		  
		  // Carico i dati delle tipologie produttive
		  List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdCentroAz(form.getIdCentroAziendaleSel(), form.getIdDomanda());
		  model.addAttribute("tipologieProdDb", tipologieProdDb);
							 
		  // Carico i dati dei siti produzione
		  List<SitoProduzioneDTO> sitiProduzioneDb = domandeEJB.getSitiProduzioneCentroAz(form.getIdCentroAziendaleSel());
		  model.addAttribute("sitiProduzioneDb", sitiProduzioneDb);
		  
		  // Seleziono il primo check
		  form.setIdCentroAzNoSedeLeg("1");
	  }
	  else{
	    logger.debug("-- Non ci sono centri aziendali legati alla domanda da visualizzare");  
	    // se in anagrafica sono presenti centri aziendali allora precompilo la tabella centri az
	    logger.debug("-- form.getIdSpedizioniere() =" +form.getIdAzienda());
	    Long idSpedizioniere = form.getIdAzienda();
	    List<CentroAziendaleDto> centriAzAnagraficaDB = domandeEJB.getCentriAziendaliByIdSpediz(idSpedizioniere);
	    logger.debug("-- centriAzAnagraficaDB.size() =" +centriAzAnagraficaDB.size());
	    
	    if (centriAzAnagraficaDB.size() > 0) {
	    	for(int i=0; i<centriAzAnagraficaDB.size(); i++) {
	    		CentroAziendaleDto centroAz = centriAzAnagraficaDB.get(i);
	    		Long idCentroAz = domandeEJB.preSalvataggioCentroAziendaleDomanda(form.getIdDomanda(), centroAz, utente);
	    		// seleziono il primo dei centri aziendali cosi' vengono precaricati gli altri dati
	    		if (i == 0) {
	    			logger.debug("-- valorizzo il radio del primo centro aziendale trovato idCentroAz= "+idCentroAz);
	    			form.setIdCentroAziendaleSel(idCentroAz);
	    			caricoDatiCentroAziendale(form, model);
	    		}
	    	}
	    	List<CentroAziendaleDomandaDTO> centriAziendali = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
	    	logger.debug("-- centriAziendali.size() =" +centriAziendali.size());
	    	model.addAttribute("centriAziendaliDb",centriAziendali);	    	
	    }    
	    
	  }
		  
	  
	  // Carico i dati delle combo sul model
	  setDatiCentriAziendaliModel(model, form, utente);
	  
	}
	catch (BusinessException exc) {
	  addErrorMessage("Errore in fase di caricamento dati centri aziendali");
	}
  	return getViewNuovaModifica("comunicazioni/centroaziendale", request);   
 } 
 

 //Tab Centri aziendali: avanti
 @PreAuthorize("hasRoleImpExp('WRITE', #request)")   
 @PostMapping(params = "avantiCentroAziendale", value = { "/centroaziendale/nuova/salva", "/centroaziendale/modifica/salva" })
 public String avantiCentroAziendale(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, HttpServletRequest request) throws BusinessException {
	 logger.debug("BEGIN avantiCentroAziendale");
	 /* Controlalre se è stato checkato il primo check della pagina
	  * 
	  *  - se è stato selezionato : deve essere stato aggiunto almeno un centro aziendale
	  *  - se non è stato selezionato : controllo se ci sono dei centri aziendali legati alla domanda ed in tal caso li slego
	  *  
	  *  - proseguo sul Tab Tipologia
	  */	
	 // è stato rimosso il flag quindi ora devo rendere obbligatorio l'inserimento del centro aziendale anche se è uguale alla sede 
	 //if(null != form.getIdCentroAzNoSedeLeg() && form.getIdCentroAzNoSedeLeg().equals("1")){
		 logger.debug("-- E' stato selezionato il primo check, controllare che sia stato aggiunto almeno un centro aziendale");
		 List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
		 if(null == centriAziendaliDb || centriAziendaliDb.size()==0){
			 logger.debug("-- Non sono stati aggiunti dei centri aziendali, bloccare l'utente");
			 addErrorMessage("Aggiungere almeno un Centro aziendale");
			 UtenteDTO utente = SecurityUtils.getUtenteLoggato();
			 setDatiCentriAziendaliModel(model, form, utente);
			 return getViewNuovaModifica("comunicazioni/centroaziendale", request);
		 }
		 // Controllo che venga inserita almeno una tipologia produttiva
		
							 
		  // Carico i dati dei siti produzione
		//  List<SitoProduzioneDTO> sitiProduzioneDb = domandeEJB.getSitiProduzioneCentroAz(form.getIdCentroAziendaleSel());
		//  model.addAttribute("sitiProduzioneDb", sitiProduzioneDb);
		 if(domandeEJB.getTipologieProdCentroAz(form.getIdCentroAziendaleSel(), form.getIdDomanda()).isEmpty()){
			 logger.debug("-- Non sono stati aggiunte delle tipologie produttive, bloccare l'utente");
			 addErrorMessage("Aggiungere almeno una tipologia produttiva");
			 return datiCentriAziendali(form, model, request);
		 }
		// Controllo che venga inserito almeno un sito di produzione se l' azienda ha la tipologia attività vivaismo
		 if(domandeEJB.getSitiProduzioneCentroAz(form.getIdCentroAziendaleSel()).isEmpty() &&
			domandeEJB.hasTipologiaAttMaterialeById(CaronteConstants.ID_TIPO_ATTIVITA_VIVAISMO, form.getIdDomanda()) ){
			 logger.debug("-- Non sono stati aggiunti dei siti di produzione, bloccare l'utente");
			 addErrorMessage("Aggiungere almeno un sito di produzione");
			 return datiCentriAziendali(form, model, request);
		 }
		 else{
			 logger.debug("-- Non ci sono errori di validazione per i Centri aziendali, posso passare al Tab successivo");
			 if(form.isTabImport()){
				  if(isNuovaDomanda(request)){
					  return getRedirect("comunicazioni/impAuto/nuova", request);
				  }
				  else{
					  return getRedirect("comunicazioni/impAuto/modifica", request);
				  }
			  }
			  if(form.isTabExport()){
				  if(isNuovaDomanda(request)){
					  return getRedirect("comunicazioni/expAuto/nuova", request);
				  }
				  else{
					  return getRedirect("comunicazioni/expAuto/modifica", request);
				  } 
			  }
			  if(form.isTabPassaporto()){
				  if(isNuovaDomanda(request)){
					  return getRedirect("comunicazioni/passaporto/nuova", request);
				  }
				  else{
					  return getRedirect("comunicazioni/passaporto/modifica", request);
				  } 	 
			  }	
			  else{
				  if(isNuovaDomanda(request)){
					  return getRedirect("comunicazioni/allegati/nuova", request);
				  }
				  else{
					  return getRedirect("comunicazioni/allegati/modifica", request);
				  }  
			  }	
		 }
 }
 
 // Tab Centri aziendali : aggiungi tipologia, aggiungi sito produzione, aggiungi centro aziendale, salva
 @PreAuthorize("hasRoleImpExp('WRITE', #request)")   
 @PostMapping(value = { "/centroaziendale/nuova/salva", "/centroaziendale/modifica/salva" })
 public String salvaDatiCentroAziendale(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, HttpServletRequest request) throws BusinessException {	
	 // Controllo da chi sono stato chiamato
	 logger.debug("--- ID_DOMANDA ="+form.getIdDomanda());
	 logger.debug("--- CHIAMATA ="+form.getChiamata());
	 UtenteDTO utente = SecurityUtils.getUtenteLoggato();	
	 switch (form.getChiamata()) {
		 /*
	   case "aggiungiCentroAziendale":
		   aggiungiCentroAziendale(form, model);
		   setDatiCentriAziendaliModel(model, form, utente);
		  // return getViewNuovaModifica("comunicazioni/centroaziendale", request);
		*/   
	   case "aggiungiTipologiaProdut":
		   logger.debug("ENTRO NEL CASE");
		   aggiungiTipologiaProd(form, model, result);
		   setDatiCentriAziendaliModel(model, form, utente);
		  // return getViewNuovaModifica("comunicazioni/centroaziendale", request);
		break;
				
	   case "aggiungiSitoProduzione":
		   aggiungiSitoProduzione(form, model, result);
		   setDatiCentriAziendaliModel(model, form, utente);
		//   return getViewNuovaModifica("comunicazioni/centroaziendale", request);	   
		break;
		
	   case "caricoDatiCentroAziendale":
		   caricoDatiCentroAziendale(form, model);
		   setDatiCentriAziendaliModel(model, form, utente);
		//   return getViewNuovaModifica("comunicazioni/centroaziendale", request);	
		break;
	}
	 return getRedirect(getViewNuovaModifica("comunicazioni/centroaziendale", request), request);
	 
 }
 

	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = "azienda/modifica/eliminaCentroAz/{id}")
	public String eliminaCentroAziendale(@PathVariable Long id,
			@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request)
			throws BusinessException {
		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();
			logger.debug("-------- idDomanda =" + form.getIdDomanda());
			// Long idCentroAzSel = form.getIdCentroAziendaleSel();
			logger.debug("-------- idCentroAzSel =" + form.getIdCentroAziendaleSel());

			logger.debug("-- Slego il centro aziendale con id =" + id);
			domandeEJB.slegaCentroAziendaleDomanda(id, form.getIdDomanda(), utente.getId());
			addSuccessMessage("Centro aziendale eliminato");

			// Carico i dati dei centri aziendali
			List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB
					.getCentriAziendaliByIdDomanda(form.getIdDomanda());
			model.addAttribute("centriAziendaliDb", centriAziendaliDb);


		} catch (Exception e) {
			logger.error("-- Exception in eliminaCentroAziendale =" + e.getMessage());
			addErrorMessage("Errore nell'eliminazione del Centro aziendale");
		}
		return getRedirect(getViewNuovaModifica("comunicazioni/azienda", request), request);
	}


 
 @PreAuthorize("hasRoleImpExp('WRITE', #request)")
 @GetMapping(value = { "/centroaziendale/eliminaSitoProd/{id}", "/modifica/centroaziendale/eliminaSitoProd/{id}"})
 public String eliminaSitoProduz(@PathVariable Long id, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {
	 try{
		 UtenteDTO utente = SecurityUtils.getUtenteLoggato();	 
		 
		 logger.debug("-------- idDomanda ="+form.getIdDomanda());
		 Long idCentroAzSel = form.getIdCentroAziendaleSel();
		 logger.debug("-------- idCentroAzSel ="+idCentroAzSel);

		 logger.debug("-- Elimino sito produzione con id ="+id);
		 domandeEJB.eliminaSitoProduzione(id, form.getIdDomanda(), utente.getId());		 		 
		 addSuccessMessage("Sito Produzione eliminato");		  


		// Carico i dati dei centri aziendali
		 List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
		 model.addAttribute("centriAziendaliDb",centriAziendaliDb);
						 
		 // Carico i dati delle tipologie produttive
		 List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdCentroAz(idCentroAzSel, form.getIdDomanda());
		 model.addAttribute("tipologieProdDb", tipologieProdDb);
						 
		 // Carico i dati dei siti produzione
		 List<SitoProduzioneDTO> sitiProduzioneDb = domandeEJB.getSitiProduzioneCentroAz(idCentroAzSel);
		 model.addAttribute("sitiProduzioneDb", sitiProduzioneDb);
		 
		 setDatiCentriAziendaliModel(model, form, utente);				
	 }
	 catch (Exception e) {
		 logger.error("-- Exception in eliminaSitoProduz ="+e.getMessage());
		 addErrorMessage("Errore nell'eliminazione del Sito produzione");
	 }
	 return getViewNuovaModifica("comunicazioni/centroaziendale", request);
 }
 
 
 // TAB Centro aziendale : elimina tipologia produttiva : elimina sul db i dati delle tipologie produttive del centro aziendale selezionato (quando hanno l'idSpecie)
 @PreAuthorize("hasRoleImpExp('WRITE', #request)")
 @GetMapping(value = { "/centroaziendale/nuova/eliminaTipProduttiva/idTip/{idTip}", "centroaziendale/nuova/eliminaTipProduttiva/idTip/{idTip}/idSpecie/{idSpecie}",
		 			   "/centroaziendale/modifica/eliminaTipProduttiva/idTip/{idTip}", "/centroaziendale/modifica/eliminaTipProduttiva/idTip/{idTip}/idSpecie/{idSpecie}"})
 public String eliminaTipologiaProd(@PathVariable Long idTip, @PathVariable(required = false) Long idSpecie, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {
	 try{
		 UtenteDTO utente = SecurityUtils.getUtenteLoggato();

		 Long idCentroAzSel = form.getIdCentroAziendaleSel();
		 logger.debug("---- idDomanda ="+form.getIdDomanda());
		 logger.debug("-------- idCentroAzSel ="+idCentroAzSel);

		 logger.debug("------ idVoceUtente ="+idTip);
		 logger.debug("------ idSpecie ="+idSpecie);
		
		 domandeEJB.eliminaTipologProduttiva(idTip, idSpecie, form.getIdDomanda(), utente.getId());

		 // Carico i dati dei centri aziendali
		 List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
		 model.addAttribute("centriAziendaliDb",centriAziendaliDb);

		 // Carico i dati delle tipologie produttive
		 /*List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdCentroAz(idCentroAzSel, form.getIdDomanda());
		 model.addAttribute("tipologieProdDb", tipologieProdDb);*/

		 // Carico i dati dei siti produzione
		 List<SitoProduzioneDTO> sitiProduzioneDb = domandeEJB.getSitiProduzioneCentroAz(idCentroAzSel);
		 model.addAttribute("sitiProduzioneDb", sitiProduzioneDb);

		 setDatiCentriAziendaliModel(model, form, utente);
	 }
	 catch (Exception e) {
		 logger.error("-- Exception in eliminaTipologiaProd ="+e.getMessage());
		 addErrorMessage("Errore nell'eliminazione della Tipologia produttiva");
	 }
	 return getViewNuovaModifica("comunicazioni/centroaziendale", request);
 }
 
/* //TAB Centro aziendale : elimina tipologia produttiva : elimina sul db i dati delle tipologie produttive del centro aziendale selezionato (quando NON hanno l'idSpecie)
 @PreAuthorize("hasRoleImpExp('WRITE', #request)")
 @GetMapping(value = { "/centroaziendale/nuova/eliminaTipProduttiva/idTipologia/{idTipologia}", "/centroaziendale/modifica/eliminaTipProduttiva/idTipologia/{idTipologia}"})
 public String eliminaTipologiaProdSenzaSpecie(@PathVariable Long idTipologia, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {
	 logger.debug("BEGIN eliminaTipologiaProdSenzaSpecie");
	 try{
		 UtenteDTO utente = SecurityUtils.getUtenteLoggato();

		 Long idCentroAzSel = form.getIdCentroAziendaleSel();
		 logger.debug("---- idDomanda ="+form.getIdDomanda());
		 logger.debug("-------- idCentroAzSel ="+idCentroAzSel);

		 logger.debug("------ idVoceUtente ="+idTipologia);		 

		 domandeEJB.eliminaTipologProduttiva(idTipologia, null, form.getIdDomanda(), utente.getId());

		 // Carico i dati dei centri aziendali
		 List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
		 model.addAttribute("centriAziendaliDb",centriAziendaliDb);

		 // Carico i dati delle tipologie produttive
		 List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdCentroAz(idCentroAzSel, form.getIdDomanda());
		 model.addAttribute("tipologieProdDb", tipologieProdDb);

		 // Carico i dati dei siti produzione
		 List<SitoProduzioneDTO> sitiProduzioneDb = domandeEJB.getSitiProduzioneCentroAz(idCentroAzSel);
		 model.addAttribute("sitiProduzioneDb", sitiProduzioneDb);

		 setDatiCentriAziendaliModel(model, form, utente);
	 }
	 catch (Exception e) {
		 logger.error("-- Exception in eliminaTipologiaProd ="+e.getMessage());
		 addErrorMessage("Errore nell'eliminazione della Tipologia produttiva");
	 }
	 return getViewNuovaModifica("comunicazioni/centroaziendale", request);
 }*/
 
 // Aggiungi tipologia produttiva : salva sul db i dati delle tipologie produttive del centro aziendale selezionato
 private void aggiungiTipologiaProd(NuovaDomandaForm form, Model model, BindingResult result) throws BusinessException {	 
	 logger.debug("-- Recupero i valori selezionati per Tipologia e Specie");	 
	 UtenteDTO utente = SecurityUtils.getUtenteLoggato();
	 
	 Long idCentroAzSel = form.getIdCentroAziendaleSel();
	 logger.debug("-------- idCentroAzSel ="+idCentroAzSel);	
	 String error = validator.validateTipologiaProdCentroAz(form, result);
	 logger.debug("-------- result.getErrorCount() ="+result.getErrorCount());
     if (result.getErrorCount() == 0) {	 	
		 if(null != idCentroAzSel && null != form.getIdTipologiaProd()){
			 logger.debug("-- idCentroAzSel ="+idCentroAzSel);
			 logger.debug("--- ***  Salvo sul db i dati della Tipologia produttiva");
			 if (form.getGenere() != null) {
		    		List<GenereDTO> listaGeneri = generiSpecieEJB.getListaGeneri(form.getGenere(), CaronteConstants.CODICE_LINGUA_LATINO);
		    		form.setIdGenere(listaGeneri.get(0).getIdGenere());     	   		
		     }
			 Long idTipologiaSalvata = domandeEJB.salvaTipologiaProduttivaCentroAz(form, utente);
			 addSuccessMessage("Tipologia produttiva aggiunta");
			 // pulisco il form di tutti i dati relativi al centro aziendale
			 logger.debug("-- Pulisco form tipologia produttiva");
			 puliscoFormTipologProduttCentroAz(form);	
		 }
     }
     else{
    	addErrorMessage(error); 
     }
     // Carico i dati dei centri aziendali
	 List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
	 model.addAttribute("centriAziendaliDb",centriAziendaliDb);
			 
	 // Carico i dati delle tipologie produttive
	 List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdCentroAz(idCentroAzSel, form.getIdDomanda());
	 model.addAttribute("tipologieProdDb", tipologieProdDb);
			 
	 // Carico i dati dei siti produzione
	 List<SitoProduzioneDTO> sitiProduzioneDb = domandeEJB.getSitiProduzioneCentroAz(idCentroAzSel);
	 model.addAttribute("sitiProduzioneDb", sitiProduzioneDb); 
  }	
 
   private void puliscoFormTipologProduttCentroAz(NuovaDomandaForm form) {
	   form.setIdTipologiaProd(null);
	   form.setSpecie(null);
	   form.setGenere(null);
	   form.setIdGenere(null);
	   form.setNoteTipologiaCentroAz(null);
   }
 
   
   
 // Ricerco i dati del centro aziendale selezionato con il radio
   private void caricoDatiCentroAziendale(NuovaDomandaForm form, Model model) throws BusinessException{
	   if(null != form.getIdCentroAziendaleSel()){
		   Long idCentroAzSel = form.getIdCentroAziendaleSel();
		   logger.debug("--- Cerco dati tipologie produttive e siti produzione per l'idCentroAziendale ="+idCentroAzSel);
		   // Carico i dati delle tipologie produttive
		   List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdCentroAz(idCentroAzSel, form.getIdDomanda());
		   model.addAttribute("tipologieProdDb", tipologieProdDb);

		   // Carico i dati dei siti produzione
		   List<SitoProduzioneDTO> sitiProduzioneDb = domandeEJB.getSitiProduzioneCentroAz(idCentroAzSel);
		   model.addAttribute("sitiProduzioneDb", sitiProduzioneDb);
	   }

	   // Carico i dati dei centri aziendali
	   List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
	   model.addAttribute("centriAziendaliDb",centriAziendaliDb);

	   puliscoFormTipologProduttCentroAz(form);
	   puliscoFormSitoProduzCentroAz(form);
   }
 

 
 // Aggiungi siti produzione : salva sul db i dati dei siti produzione del centro aziendale selezionato
 private void aggiungiSitoProduzione(NuovaDomandaForm form, Model model, BindingResult result) throws BusinessException{
	 logger.debug("-- Recupero i valori selezionati per Siti di produzione");
	 
     UtenteDTO utente = SecurityUtils.getUtenteLoggato();
     Long idCentroAzL = form.getIdCentroAziendaleSel();
	 logger.debug("-- idCentroAzL ="+idCentroAzL);
	 
	 logger.debug("-- Denominazione sito produzione ="+form.getDescSitoProduzione());
	 validator.validateDatiSitoProduzione(form, result);
	 
	 if (result.getErrorCount() == 0) {
		 model.addAttribute("hasErrorsSitoProd", false); 
		// if(null != form.getIdCentroAziendaleSel() && null != form.getDescSitoProduzione() && !form.getDescSitoProduzione().isEmpty()){				 
		 if(null != form.getMappale() && null != form.getMappale() && !form.getMappale().isEmpty()){		 
			 logger.debug("--- ***  Salvo sul db i dati del Sito Produzione");
			 Long idSitoProdSalvato = domandeEJB.salvaSitoProduzioneCentroAz(form, utente);
		     
					 
			 // pulisco il form di tutti i dati relativi al centro aziendale
			 logger.debug("-- Pulisco form tipologia produttiva");
			 puliscoFormSitoProduzCentroAz(form);
					 
			 addSuccessMessage("Sito produzione aggiunto");
		 }	 
	 } else {
		 model.addAttribute("hasErrorsSitoProd", true); 
		 logger.debug("errore di validazione in aggiungiSitoProduzione = "+result.getFieldError());
	 }
	 
	 // Carico i dati dei centri aziendali
	 List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
	 model.addAttribute("centriAziendaliDb",centriAziendaliDb);
			 
	 // Carico i dati delle tipologie produttive
	 List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdCentroAz(idCentroAzL, form.getIdDomanda());
	 model.addAttribute("tipologieProdDb", tipologieProdDb);
			 
	 // Carico i dati dei siti produzione
	 List<SitoProduzioneDTO> sitiProduzioneDb = domandeEJB.getSitiProduzioneCentroAz(idCentroAzL);
	 model.addAttribute("sitiProduzioneDb", sitiProduzioneDb);
	 
	 // Popolo combo Provincia e Comune
 	 logger.debug("-- Popolo combo Provincia e Comune");
 	 model.addAttribute("listaProvinceCentroAz", decodificheEJB.getListaProvince());
	 if(form.getProvinciaSitoProd() != null) { 
		 model.addAttribute("listaComuniCentroAz", decodificheEJB.getListaComuni(form.getProvinciaSitoProd()));
	 }
 	logger.debug("END aggiungiSitoProduzione");
 }
 
 private void puliscoFormSitoProduzCentroAz(NuovaDomandaForm form){
	 form.setDescSitoProduzione(null);	 
	 form.setFoglio(null);	  
	 form.setComuneSitoProd(null);	 
	 form.setProvinciaSitoProd(null);
	 form.setMappale(null);
	 form.setSuperficie(null);	  
	 form.setUbicazione(null);
 }

 
 
 /* Aggiungi centro aziendale : salva sul db i dati anagrafici del Centro aziendale
  * Controllare che sia stata salvata l'azienda, altrimenti bloccare l'utente
  */
 private void aggiungiCentroAziendale(NuovaDomandaForm form, Model model) throws BusinessException {	 
		 UtenteDTO utente = SecurityUtils.getUtenteLoggato();
		 
		 // ****** Creo l'oggetto CentroAziendaleDTO da salvare sul db		 
		 List<CentroAziendaleDomandaDTO> centriAziendaliList = null;
		 if(null != form.getCentriAziendaliList() && form.getCentriAziendaliList().size()>0){
			centriAziendaliList = form.getCentriAziendaliList();
		 }
		 else{
			centriAziendaliList = new ArrayList<>(); 
		 }
		 CentroAziendaleDomandaDTO centroAz = setDatiNuovoCentroAziendale(form, utente);	
		 // Controllare che sia stata salvata l'azienda, altrimenti bloccare l'utente
		 if(centroAz.getIdSpedizioniere() == null){
			 addErrorMessage("Attenzione, prima di salvare i dati del centro aziendale, compilare i dati dell'azienda");			 
		 }
		 else{
			 boolean centroAzGiaInDomanda = false;
			 /* Se si sta aggiungendo un centro aziendale presente nella lista (no Nuovo centro Aziendale) :
			  *  controllare che l'utente non stia cercando di aggiungere un centro aziendale già presente in domanda, in tal caso segnalarlo e non proseguire
			  *  con il salvataggio dei dati
			  */
			 Long idCentroAziendale = form.getIdCentroAziendale();
			 logger.debug("-- idCentroAziendale del form ="+form.getIdCentroAziendale());			  
			 if(form.getIdDomanda() != null && idCentroAziendale.longValue() != CaronteConstants.ID_CENTRO_AZIENDALE_NUOVO){
				 logger.debug("-- Controllare di non inserire un centro aziendale già legato alla domanda");
				 List<CentroAziendaleDomandaDTO> listaCentriAz = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
				 if(listaCentriAz != null && listaCentriAz.size()>0){
					 logger.debug("-- ci sono già centri az legati alla domanda, controllo");
					 for (Iterator<CentroAziendaleDomandaDTO> iterator = listaCentriAz.iterator(); iterator.hasNext();) {
						CentroAziendaleDomandaDTO centroAziendaleDomandaDTO = (CentroAziendaleDomandaDTO) iterator.next();
						if(centroAziendaleDomandaDTO.getIdCentroAziendale().equals(idCentroAziendale)){
							centroAzGiaInDomanda = true;
							break;
						}						
					}
				 }
			 }
			 /*
			  * Se il centro aziendale non è ancora stato legato alla domanda :
			  *   salvo sul db i dati del centro aziendale			 
			  */
			 Long idCentroAziendaleSalvato = null;
			 logger.debug("-- centroAzGiaInDomanda ="+centroAzGiaInDomanda);
			 if(!centroAzGiaInDomanda){
				 logger.debug("--- ***  Salvo sul db i dati del centro aziendale");
				 idCentroAziendaleSalvato = domandeEJB.salvaCentroAziendaleDomanda(form, centroAz, utente);				 
				 
				 centroAz.setIdCentroAziendale(idCentroAziendaleSalvato);		 
				 centriAziendaliList.add(centroAz);	
				 
				 form.setCentriAziendaliList(centriAziendaliList);
				 form.setIdCentroAziendaleSel(idCentroAziendaleSalvato);
			 }
			 else{
				 // centro aziendale gia' presente nella domanda
				 // salvo comunque i dati anagrafici, potrebbero essere cambiati
				 idCentroAziendaleSalvato = domandeEJB.salvaCentroAziendaleDomanda(form, centroAz, utente);					 }
			 logger.debug("-- idCentroAziendaleSalvato ="+idCentroAziendaleSalvato);
			 
			 // Carico i dati dei centri aziendali
			 List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
			 model.addAttribute("centriAziendaliDb",centriAziendaliDb);
			 
			 // Carico i dati delle tipologie produttive
			 List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdCentroAz(idCentroAziendaleSalvato, form.getIdDomanda());
			 model.addAttribute("tipologieProdDb", tipologieProdDb);
			 
			 // Carico i dati dei siti produzione
			 List<SitoProduzioneDTO> sitiProduzioneDb = domandeEJB.getSitiProduzioneCentroAz(idCentroAziendaleSalvato);
			 model.addAttribute("sitiProduzioneDb", sitiProduzioneDb);
			 
			 // pulisco il form di tutti i dati relativi al centro aziendale
			 logger.debug("-- Pulisco form centro aziendale");
			 puliscoFormCentroAziendale(form);
			 
			 addSuccessMessage("Centro aziendale aggiunto");		 	
		 }
 }
 
 
 private void puliscoFormCentroAziendale(NuovaDomandaForm form) {
	 // Dati centro aziendale
	 form.setCapCentroAz(null);
	 form.setCellulareCentroAz(null);
	 form.setCodCentroAz(null);
	 form.setCodCentroAz(null);	 
	 form.setDenominazCentroAz(null);
	 form.setMailCentroAz(null);	 
	 form.setFrazioneCentroAz(null);
	 form.setIdProvCentroAz(null);
	 form.setIdComuneCentroAz(null);	 	 
	 form.setIndirizzoCentroAz(null);
	 form.setPecCentroAz(null);
	 form.setTelefonoCentroAz(null);
	 form.setIdCentroAziendale(0L);
	 
	 // Dati tipologie produttive
	 form.setTipologieList(null);
	 
	 // Dati siti produzione
	 form.setSitiProduzioneList(null);	 	 
 }
 
 
 
 private CentroAziendaleDomandaDTO setDatiNuovoCentroAziendale(NuovaDomandaForm form, UtenteDTO utente){
	 CentroAziendaleDomandaDTO centroAz = null;
	  if(form != null){
		  logger.debug("-- Setto i campi di CarTCentroAziendale");
		  centroAz = new CentroAziendaleDomandaDTO();
		  centroAz.setCap(form.getCapCentroAz());
		  centroAz.setCellulare(form.getCellulareCentroAz());
		  centroAz.setCodCentroAziendale(form.getCodCentroAz());
		  centroAz.setCodiceFiscale(form.getCodFiscaleAz());
		  centroAz.setDataInsert(new Date());
		  centroAz.setDenominazione(form.getDenominazCentroAz());
		  centroAz.setEmail(form.getMailCentroAz());
		  centroAz.setFlPrincipale(false);
		  centroAz.setFrazione(form.getFrazioneCentroAz());
		  centroAz.setIdComune(form.getIdComuneCentroAz());
		  centroAz.setIdSpedizioniere(form.getIdSpedizioniere());
		  centroAz.setIdUtenteInsert(utente.getId());
		  centroAz.setIndirizzo(form.getIndirizzoCentroAz());
		  centroAz.setPec(form.getPecCentroAz());
		  centroAz.setTelefono(form.getTelefonoCentroAz());
		  centroAz.setIdSpedizioniere(form.getIdAzienda());
		  centroAz.setDataInsert(new Date());
		  centroAz.setIdUtenteInsert(utente.getId());
		  centroAz.setIdStatoAzienda(CaronteConstants.ID_STATO_AZIENDA_ATTIVA);
		  return centroAz;
	  }
	  return centroAz;
 }
 
 
  
  
    
  // Nuova domanda - Tab Dati azienda
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")  
  @GetMapping(value = { "/azienda/nuova", "/azienda/modifica" })
  public String datiAzienda(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {	
    try {
	  // Ricerco le aziende alle quali è collegato l'utente
	  UtenteDTO utente = SecurityUtils.getUtenteLoggato();
	  
	  // Carico i dati delle combo sul model
	  setDatiAziendaModel(model, form, utente);
	  

		// Carico i dati dei centri aziendali
		List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB
				.getCentriAziendaliByIdDomanda(form.getIdDomanda());
		if (null != centriAziendaliDb && centriAziendaliDb.size() > 0) {
			logger.debug("-- Ci sono centri aziendali legati alla domanda da visualizzare");
			form.setCentriAziendaliList(centriAziendaliDb);
			model.addAttribute("centriAziendaliDb", centriAziendaliDb);
			form.setListaCentriAz(centriAziendaliDb);
		} else {
			/*logger.debug("-- Non ci sono centri aziendali legati alla domanda da visualizzare");
			// se in anagrafica sono presenti centri aziendali allora
			// precompilo la tabella centri az
			logger.debug("-- form.getIdSpedizioniere() =" + form.getIdAzienda());
			Long idSpedizioniere = form.getIdAzienda();
			List<CentroAziendaleDto> centriAzAnagraficaDB = domandeEJB
					.getCentriAziendaliByIdSpediz(idSpedizioniere);
			logger.debug("-- centriAzAnagraficaDB.size() =" + centriAzAnagraficaDB.size());

			if (centriAzAnagraficaDB.size() > 0) {
				for (int i = 0; i < centriAzAnagraficaDB.size(); i++) {
					CentroAziendaleDto centroAz = centriAzAnagraficaDB.get(i);
					Long idCentroAz = domandeEJB.preSalvataggioCentroAziendaleDomanda(form.getIdDomanda(), centroAz,
							utente);
				}
				List<CentroAziendaleDomandaDTO> centriAziendali = domandeEJB
						.getCentriAziendaliByIdDomanda(form.getIdDomanda());
				logger.debug("-- centriAziendali.size() =" + centriAziendali.size());
				form.setCentriAziendaliList(centriAziendali);
				model.addAttribute("centriAziendaliDb", centriAziendali);
			}
*/
		}
		logger.debug("-- Ricerco i centri aziendali ai quali è legato lo spedizioniere");
		List<CentroAziendaleDto> elencoCentriAziendali = domandeEJB
				.getCentriAziendaliByIdSpediz(form.getIdAzienda());
		// Aggiungo l'elemento 'Nuova azienda'
		logger.debug("-- Aggiungo l'elemento 'Nuovo centro aziendale'");
		CentroAziendaleDto centroAzNuovo = new CentroAziendaleDto();
		centroAzNuovo.setIdCentroAziendale(new Long(0));
		centroAzNuovo.setDenominazione("Nuovo centro aziendale");
		if (elencoCentriAziendali != null) {
			elencoCentriAziendali.add(0, centroAzNuovo);
		} else {
			elencoCentriAziendali = new ArrayList<CentroAziendaleDto>();
			elencoCentriAziendali.add(0, centroAzNuovo);
		}
		model.addAttribute("centriAziendaliList", elencoCentriAziendali);
		model.addAttribute("listaProvinceCentroAz", decodificheEJB.getListaProvince());
		if (form.getIdProvCentroAz() != null)
			model.addAttribute("listaComuniCentroAz", decodificheEJB.getListaComuni(form.getIdProvCentroAz()));
	  
	}
	catch (BusinessException exc) {
	  addErrorMessage("Errore in fase di caricamento dati azienda");
	}
   	return getViewNuovaModifica("comunicazioni/azienda", request);   
  } 
    
  
  
  // Tab Dati anagrifici : dati anagrafici precaricati con i dati anagrafici dell'idUtente che si è autenticato
  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
  @GetMapping(value = "/anagrafica/nuova/{id}")
  public String datiAnagrafici(@PathVariable Long id, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {	
	logger.debug("-- idTipoComunicazione selezionato =" + id);
   
    form.setIdTipoComunicazione(id);
    
    setDatiTipoComunicazione(decodificheEJB.getTipoComunicazioneByPrimaryKey(id), form);
    
    UtenteDTO utente = SecurityUtils.getUtenteLoggato();
    form.setIdStatoComunicazione(CaronteConstants.STATO_COMUNICAZIONE_BOZZA);
    
    /*
     * -- DATI ANAGRAFICI --- 
     *  Devono essere precaricati i dati dell'utente loggato   
     */
    logger.debug("-- DATI ANAGRAFICI : precarico i dati dell'utente loggato");
    CarTUtente anagraficaUtente = domandeEJB.getDatiUtenteByIdUtente(utente.getId());
    setDatiAnagraficiUtente(anagraficaUtente, form);
            
	return datiDomanda(form, model, request);   
  }
  
  
  
  private void setDatiAnagraficiUtente(CarTUtente anagUtente,NuovaDomandaForm form) throws BusinessException{
	  if(anagUtente != null){
		  logger.debug("-- Setto i DATI ANAGRAFICI DELL'UTENTE LOGGATO");
		  form.setIdUtente(anagUtente.getIdUtente());
		  form.setCognome(anagUtente.getCognome());
		  form.setNome(anagUtente.getNome());
		  form.setCodFiscale(anagUtente.getCodiceFiscale());

		  // Dati nascita
		  form.setDataNascita(anagUtente.getDataNascita());
		  if (anagUtente.getIdComuneNascita() == null && anagUtente.getIdNazioneEstNascita() != null) {//&& anagUtente.getDenomComuneEstNascita() != null) {
			  form.setNascitaEstera(true);
			  //form.setDenomComuneEstNascita(anagUtente.getDenomComuneEstNascita());
			  form.setIdNazioneEstNascita(anagUtente.getIdNazioneEstNascita());
		  } 
		  else {
			  form.setNascitaEstera(false);
			
			  if(anagUtente.getIdComuneNascita() != null){
				  // recupero l'idProvincia
				  Long idProvinciaNasc = domandeEJB.getIdProvinciaByIdComune(anagUtente.getIdComuneNascita());
				  logger.debug("-- idProvinciaNasc ="+idProvinciaNasc);
				  form.setIdProvinciaNascita(idProvinciaNasc);
				  form.setIdComuneNascita(anagUtente.getIdComuneNascita());
			  }
		  }

		  // Dati residenza
		  if (anagUtente.getIdComuneResidenza() == null && anagUtente.getIdNazioneEstResid() != null) {// anagUtente.getDenomComuneEstResid() != null) {
			  form.setResidenzaEstera(true);
			  //form.setDenomComuneEstResid(anagUtente.getDenomComuneEstResid());
			  form.setIdNazioneEstResid(anagUtente.getIdNazioneEstResid());
		  } else {
			  form.setResidenzaEstera(false);
			  if(anagUtente.getIdComuneResidenza() != null){
				// recupero l'idProvincia
				Long idProvinciaResid = domandeEJB.getIdProvinciaByIdComune(anagUtente.getIdComuneResidenza());
				logger.debug("-- idProvinciaResid ="+idProvinciaResid);
			    form.setIdProvinciaResidenza(idProvinciaResid);
			    form.setIdComuneResidenza(anagUtente.getIdComuneResidenza());
			  }
		  }

		  form.setIndirizzo(anagUtente.getIndirizzo());
		  form.setCap(anagUtente.getCap());
		  
		  form.setNumTelefono(anagUtente.getTelefono());
		  form.setCellulare(anagUtente.getCellulare());
		  form.setEmail(anagUtente.getEmail());
	  }
  }
  
  
  private void setDatiTipoComunicazione(TipoDomandaDTO tipoComunicazione, NuovaDomandaForm form) {
	  logger.debug("BEGIN setDatiTipoComunicazione");
	  if (tipoComunicazione != null) {			
		  form.setDescTipoDomanda(tipoComunicazione.getDescBreve());
		  form.setIdTipoComunicazione(tipoComunicazione.getIdTipoComunicazione());
	  }
  }
  
  
    @PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/anagrafica/nuova", "/anagrafica/modifica" })
	public String datiDomanda(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model,HttpServletRequest request) throws BusinessException {
		try {
			setDatiAnagraficiModel(model, form);			
		}
		catch (BusinessException exc) {
			addErrorMessage("Errore in fase di caricamento dati anagrafici");
		}
		return getViewNuovaModifica("comunicazioni/anagrafica", request);
	}
    
    private void setDatiCentriAziendaliModel(Model model, NuovaDomandaForm form, UtenteDTO utente) throws BusinessException {
    	// **** Ricarico i dati delle combo *****	  
    	logger.debug("-- Ricerco i centri aziendali ai quali è legato lo spedizioniere");
    	List<CentroAziendaleDto> elencoCentriAziendali = domandeEJB.getCentriAziendaliByIdSpediz(form.getIdAzienda());
    	// Aggiungo l'elemento 'Nuova azienda'
    	logger.debug("-- Aggiungo l'elemento 'Nuovo centro aziendale'");
    	CentroAziendaleDto centroAzNuovo = new CentroAziendaleDto(); 
    	centroAzNuovo.setIdCentroAziendale(new Long(0));
    	centroAzNuovo.setDenominazione("Nuovo centro aziendale");
    	if(elencoCentriAziendali != null){
    		elencoCentriAziendali.add(0, centroAzNuovo);
    	}
    	else{
    		elencoCentriAziendali = new ArrayList<CentroAziendaleDto>();
    		elencoCentriAziendali.add(0, centroAzNuovo);
    	}
    	model.addAttribute("centriAziendaliList", elencoCentriAziendali);
    	
    	model.addAttribute("listaProvinceCentroAz", decodificheEJB.getListaProvince());

    	if(form.getIdProvCentroAz() != null)
    	  model.addAttribute("listaComuniCentroAz", decodificheEJB.getListaComuni(form.getIdProvCentroAz()));
    	
    	// Combo Tipologie produttive
    	model.addAttribute("listaTipologieProd", decodificheEJB.getVociByIdTipoModello(CaronteConstants.ID_TIPO_MODELLO_RUOP_CA));
    }
  
  
  private void setDatiAziendaModel(Model model, NuovaDomandaForm form, UtenteDTO utente) throws BusinessException { 	   
	  // **** Ricarico i dati delle combo *****	  
	  logger.debug("-- Ricerco le aziende alle quali è collegato l'utente inserito in domanda");
	  Long idUtenteDomnanda = form.getIdUtente();
	  logger.debug("-- idUtenteDomnanda ="+idUtenteDomnanda);
	  List<CarTSpedizioniere> elencoAziende = domandeEJB.getAziendeByIdUtente(idUtenteDomnanda);
	  /* in caso di domanda passaporto,
	   *  non permettiamo l'inserimento di nuova azienda, in quanto andrebbe fatta prima una domanda RUOP
	   */
	  if (!form.getIdTipoComunicazione().equals(CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO)){
		  // Aggiungo l'elemento 'Nuova azienda'
		  logger.debug("-- Aggiungo l'elemento 'Nuova azienda'");
		  CarTSpedizioniere spedNuovo = new CarTSpedizioniere(); 
		  spedNuovo.setIdSpedizioniere(new Long(0));
		  spedNuovo.setDenomSpedizioniere("Nuova azienda");
		  if(elencoAziende != null){
			  elencoAziende.add(0, spedNuovo);
		  }
		  else{
			  elencoAziende = new ArrayList<CarTSpedizioniere>();
			  elencoAziende.add(0, spedNuovo);
		  }
	  }
	  model.addAttribute("spedizionieriList", elencoAziende);
	  	  
	  model.addAttribute("listaTipiAzienda", decodificheEJB.getListaTipiSpedizioniere());
	  model.addAttribute("listaProvinceSedeLegale", decodificheEJB.getListaProvince());
	  
	  if(form.getIdProvinciaSedeLegale() != null) {
	    model.addAttribute("listaComuniSedeLegale", decodificheEJB.getListaComuni(form.getIdProvinciaSedeLegale()));
	  }
	  
	  
	  // Dati attivita azienda
	  List<CarDVoce> vociCheckboxRuopList = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP, 1L);
	  form.setVociCheckboxTipologia(vociCheckboxRuopList);
		  

	  List<CarDTipoAttivita> tipoAttivitaList = decodificheEJB.getTipoAttivita();
	  model.addAttribute("listaTipologieAttivita", tipoAttivitaList);
		  
		  
	  if(form.getIdDomanda() != null){
		// Carico i dati relativi alle tipologie attività e materiali salvati sul db
	    List<TipologiaAttMaterialeDTO> tipologieAttMaterialidb = domandeEJB.getTipologieAttMateriale(form.getIdDomanda());
	    model.addAttribute("tipologieAttDb", tipologieAttMaterialidb); 
	    if (tipologieAttMaterialidb.size() > 0 ){
	    	// tutti i record hanno lo stesso dato o S o N
	    	if(tipologieAttMaterialidb.get(0).getRichiestaPassaporto() != null){
	    		form.setRichiestaPassaporto(tipologieAttMaterialidb.get(0).getRichiestaPassaporto().equals("S") ? true : false);
	    	}	
	    	else{
	    		form.setRichiestaPassaporto(false);
	    	}	
	    }
	  }
	  logger.debug("++++"+form.isRichiestaPassaporto());
	  
	  
  }
  
    private void setDatiAnagraficiModel(Model model, NuovaDomandaForm form) throws BusinessException { 
		// Ricarico i dati delle combo
		model.addAttribute("listaProvince", decodificheEJB.getListaProvince());

		if (form.getIdProvinciaNascita() != null) {
			model.addAttribute("listaComuniNascita", decodificheEJB.getListaComuni(form.getIdProvinciaNascita()));
		}
		if (form.getIdProvinciaResidenza() != null) {
			model.addAttribute("listaComuniResidenza",decodificheEJB.getListaComuni(form.getIdProvinciaResidenza()));
		}	
		
		if(form.getIdDomanda() != null){
			form.setEmail(domandeEJB.getDettaglioAnagraficaByIdDomanda(form.getIdDomanda()).getEmail());
			logger.debug("-- email anagrafica ="+form.getEmail());
		}
		
		// Testo della modale INOLTRA
		if(form.isAbilitaInoltra()){
			CarDCostante testo = decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_TESTO_INOLTRA_PARTE_1);
			model.addAttribute("testoInoltra", testo.getValoreCostante());
			testo = decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_TESTO_INOLTRA_PARTE_2);
			model.addAttribute("testoInoltra2", testo.getValoreCostante());
			testo = decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_TESTO_INOLTRA_PARTE_3);
			model.addAttribute("testoInoltra3", testo.getValoreCostante());
			
		}
		
		List<CarDNazione> listaNazioni = decodificheEJB.getListaNazioni(true);
		model.addAttribute("listaNazioniNascita", listaNazioni);
		model.addAttribute("listaNazioniResid", listaNazioni);
		//per la visualizzazione dei tab centri aziendali
		form.setListaCentriAz(domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda()));
	}



	@ModelAttribute("datiSitoProduzioneForm")
	public DatiSitoProduzioneForm getDatiSitoProduzioneForm(HttpServletRequest request) {
		DatiSitoProduzioneForm form = new DatiSitoProduzioneForm();
		return form;
	}



	private boolean checkModificaDomanda(Long idUtente, NuovaDomandaForm form) throws BusinessException {
		Boolean isUtenteAbilitato = domandeEJB.isDomandaModificabile(idUtente,form.getIdDomanda());
		logger.debug("-- isUtenteAbilitato ="+isUtenteAbilitato);
		if (isUtenteAbilitato == null || !isUtenteAbilitato) {
			addErrorMessage("La domanda non può essere modificata dall'utente corrente");
			return false;
		}

		return true;
	}
	
	
	// Nuova domanda RUOP - Tab Passaporto
    @PreAuthorize("hasRoleImpExp('WRITE', #request)")
    @GetMapping(value = {"/passaporto/nuova", "/passaporto/modifica" })
    public String datiPassporto(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {	
	      try {
	   	  UtenteDTO utente = SecurityUtils.getUtenteLoggato();
	   	  
	   	  // Carico i dati delle combo sul model
	   	  setDatiPassportoModel(model, form, utente);
	   	  
	   	}
	   	catch (BusinessException exc) {
	   	  addErrorMessage("Errore in fase di caricamento dati passaporto");
	   	}
     	return getViewNuovaModifica("comunicazioni/passaporto", request);   
    } 


    private void setDatiPassportoModel(Model model, NuovaDomandaForm form, UtenteDTO utente) throws BusinessException {

    	logger.debug(" Entro in setDatiPassportoModel");
    	
    	// Radio per le voci Prima richiesta e Aggiornamento
    	List<CarDVoce> vociTipoRichiesta = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 1L);
    	form.setVociRadioTipoRichiesta(vociTipoRichiesta);
    	
    	// Radio
    	List<CarDVoce> vociRadioPassaportoList = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 2L);
    	form.setVociRadioPassaporto(vociRadioPassaportoList);
    	
    	// Check
    	List<CarDVoce> vociCheckboxPassaportoList = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 3L);
    	form.setVociCheckboxPassaporto(vociCheckboxPassaportoList);
    	  	  	
    	// Combo Tipologie produttive
    	model.addAttribute("listaTipologieProd", decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 4L));
    	
    	// recupero il testo delle voci dichiarazioni, sono solo 2
    	List<CarDVoce> vociDichiarazioniList = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 5L);
    	logger.debug("Prima dichiarazione ="+vociDichiarazioniList.get(0).getDescEstesa());
    	logger.debug("Seconda dichiarazione ="+vociDichiarazioniList.get(1).getDescEstesa());
    	model.addAttribute("voceDichiaraConoscenze",vociDichiarazioniList.get(0).getDescEstesa());
    	model.addAttribute("voceDichiaraDisporreSistemi",vociDichiarazioniList.get(1).getDescEstesa());
    	
    	// recupero il testo delle voci dichiarazioni, sono solo 2
    	List<CarDVoce> vociPianoRischiList = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 6L);
    	model.addAttribute("vocePianoRischi",vociPianoRischiList.get(0).getDescEstesa());    	
    	
    	// Combo Zone Protetta
    	model.addAttribute("listaZoneProtette", decodificheEJB.getZoneProtette());
    	
    	List<CentroAziendaleDto> elencoCentriAziendali = domandeEJB.getCentriAziendaliByIdSpediz(form.getIdAzienda());
        model.addAttribute("centriAziendaliList", elencoCentriAziendali);
    	
   	 	// Recupero i dati dal DB nel caso si stesse ricaricando la pagina
    	if (form.getIdDomanda() != null ){
    		
    		CarTResponsabilePassaporto resp = domandeEJB.getRespPassaportoByIdDomanda(form.getIdDomanda());
    		if (resp != null) {
	    		form.setCheckRespFito("1");
	    		form.setCognomeRespPass(resp.getCognome());
	    		form.setNomeRespPass(resp.getNome());
	    		form.setCodFiscaleRespPass(resp.getCodiceFiscale());
	    		form.setNumTelefonoRespPass(resp.getTelefono());
	    		form.setCellulareRespPass(resp.getCellulare());
	    		form.setEmailRespPass(resp.getEmail());
	    		form.setQualificaProfRespPass(resp.getQualificaProfessionale());
    		}
    		
    		// recupero la voce del tipo di richiesta(prima richiesta o aggiornamento)
    		Long[] idVoceRadioTipoRichiesta = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 1L, form.getIdDomanda()); 
    		if(idVoceRadioTipoRichiesta != null && idVoceRadioTipoRichiesta.length > 0){
    	    	String strIdVoce[] = new String[idVoceRadioTipoRichiesta.length];
    	    	for (int i = 0; i < idVoceRadioTipoRichiesta.length; i++) {
    	    		strIdVoce[i] = String.valueOf(idVoceRadioTipoRichiesta[i]);	   
    	    	}
    	    	form.setIdVoceRadioTipoRichiesta(strIdVoce);
    	    }
    		
    		// recupero il centro aziendale associato al passaporto
    		Long idCentroAziendalePassaporto = domandeEJB.getIdCentroAziendalePassaportoByIdDomanda(form.getIdDomanda());
    		if (idCentroAziendalePassaporto != null) {
    			form.setIdCentroAziendalePassaporto(idCentroAziendalePassaporto);
    		}
    		Long[] idVoceRadioChecked = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 2L, form.getIdDomanda());
    		if(idVoceRadioChecked != null && idVoceRadioChecked.length > 0){
    	    	String strIdVoce[] = new String[idVoceRadioChecked.length];
    	    	for (int i = 0; i < idVoceRadioChecked.length; i++) {
    	    		strIdVoce[i] = String.valueOf(idVoceRadioChecked[i]);	   
    	    	}
    	    	form.setIdVoceRadio(strIdVoce);
    	    }
    		
    		Long[] idVoceChecked = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 3L, form.getIdDomanda());
    		if(idVoceChecked != null && idVoceChecked.length > 0){
    	    	String strIdVoce[] = new String[idVoceChecked.length];
    	    	for (int i = 0; i < idVoceChecked.length; i++) {
    	    		strIdVoce[i] = String.valueOf(idVoceChecked[i]);	   
    	    	}
    	    	form.setIdVoceCheck(strIdVoce);
    	    }
    		    		  		
        	// tipologie produttive
      		List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 4L);
      		model.addAttribute("tipologieProdDb", tipologieProdDb);
      		// Zone protette
    		List<ZonaProtettaSpecieDTO> zoneProtetteDb = domandeEJB.getZoneProtetteSpecieByIdDomanda(form.getIdDomanda());
    		model.addAttribute("zoneProtetteDb", zoneProtetteDb);
    		
    		Long[] idVoceDichiara = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 5L, form.getIdDomanda());
    		if(idVoceDichiara != null && idVoceDichiara.length > 0) {
    	    	String strIdVoce[] = new String[idVoceDichiara.length];
    	    	for (int i = 0; i < idVoceDichiara.length; i++) { 
    	    		if (idVoceDichiara[i] == CaronteConstants.ID_VOCE_DICHIARA_CONOSCENZE) {
    	    			form.setVoceDichiaraConoscenze("S");    	    			
    	    		} 
    	    		if (idVoceDichiara[i] == CaronteConstants.ID_VOCE_DICHIARA_DISPORRE_SISTEMI) {
    	    			form.setVoceDichiaraDisporreSistemi("S");    	    			
    	    		} 
    	    	}
    	    }
    		// se non sono presenti sul DB le voci allora metto per default a N
    		if (form.getVoceDichiaraConoscenze() == null) {
    			form.setVoceDichiaraConoscenze("N");
    		}
    		if (form.getVoceDichiaraDisporreSistemi() == null) {
    			form.setVoceDichiaraDisporreSistemi("N");
    		}
    		
    		Long[] idVocePianoRischi = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 6L, form.getIdDomanda());
    		if(idVocePianoRischi != null && idVocePianoRischi.length > 0) {
    	    	String strIdVoce[] = new String[idVocePianoRischi.length];
    	    	for (int i = 0; i < idVocePianoRischi.length; i++) { 
    	    		if (idVocePianoRischi[i] == CaronteConstants.ID_VOCE_PIANO_RISCHI) {
    	    			form.setVocePianoRischi("S");    	    			
    	    		} 
    	    	}
    		}
    		// se non sono presenti sul DB le voci allora metto per default a N
    		if (form.getVocePianoRischi() == null) {
    			form.setVocePianoRischi("N");
    		}
    		
    	} 	
    	
    }     
 
    private void puliscoFormTipologiaProdPassaporto(NuovaDomandaForm form){
    	form.setIdTipologiaProd(null);
    	form.setIdGenerePass(null);
    	form.setSpecie(null);
    }
    
    private void puliscoFormZonaProtettaPassaporto(NuovaDomandaForm form){
    	form.setIdZonaProtetta(null);
    	form.setIdGenereZP(null);
    	form.setSpecieZP(null);    	
    }
    
    // Tab Passaporto : aggiungi tipologia produttiva
    @PreAuthorize("hasRoleImpExp('WRITE', #request)")   
    @PostMapping(params = "aggiungiSpecie", value = { "/passaporto/nuova", "/passaporto/modifica" })
    public String aggiungiTipologiaProdSpecie(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, HttpServletRequest request) throws BusinessException {
    	
		logger.debug("-- ID_DOMANDA = "+form.getIdDomanda());
	 	UtenteDTO utente = SecurityUtils.getUtenteLoggato(); 
	    //validator.validateTipologiaProdPassaporto(form, result);
	 	validator.validateAggiungiTipologiaProdPassaporto(form, result);
   	 	
   	    // mi recupero l'id del genere selezionato
   	    if (result.getErrorCount() == 0) {
   	    	if (form.getGenere() != null) {
   	    		List<GenereDTO> listaGeneri = generiSpecieEJB.getListaGeneri(form.getGenere(), CaronteConstants.CODICE_LINGUA_LATINO);
   	    		form.setIdGenerePass(listaGeneri.get(0).getIdGenere());     	   		
   	    	}	   
		   	if (form.getIdDomanda() != null) {
		   		Long idVoceUtente = domandeEJB.salvaTipologiaProdSpeciePassaporto(form, utente);   	    
		   		logger.debug("-- idVoceUtente inserita = "+idVoceUtente);	
		   	}   
   	    } 
   	 	setDatiPassportoModel(model, form, utente);
   	 	puliscoFormTipologiaProdPassaporto(form);

   	 	return getViewNuovaModifica("comunicazioni/passaporto", request);
    }	
    
    // Tab Passaporto: aggiungi zona protetta
    @PreAuthorize("hasRoleImpExp('WRITE', #request)")   
    @PostMapping(params = "aggiungiZonaProtetta", value = { "/passaporto/nuova", "/passaporto/modifica" })
    public String aggiungiZonaProtettaSpecie(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, HttpServletRequest request) throws BusinessException {
    	
    	logger.debug("-- premuto button o dal controller ID_DOMANDA = "+form.getIdDomanda());
   	 	UtenteDTO utente = SecurityUtils.getUtenteLoggato();
   	    //List<ZonaProtettaSpecieDTO> zonaProtettaSpecieDB = domandeEJB.getZoneProtetteSpecieByIdDomanda(form.getIdDomanda());
   	    validator.validateZoneProtettePassaporto(form, result);
   	 	
   	    if (result.getErrorCount() == 0) {
	   	 	logger.debug("-- form.getIdZonaProtetta()= "+form.getIdZonaProtetta());  	    
	   	 	if (form.getSpecieZP() != null && form.getIdDomanda() != null) {
	   	 	    Long idZonaProtettaUtente = domandeEJB.salvaZonaProtettaSpeciePassaporto(form, utente); 	    
		   	    logger.debug("-- idZonaProtettaUtente inserita = "+idZonaProtettaUtente);	
		 	}  	   	 		   	    
	   	    puliscoFormZonaProtettaPassaporto(form);
   	    }
   	    else {
    		logger.debug("-- Errore di validazione in aggiungiZonaProtettaSpecie");
    		addErrorMessage("Aggiungere almeno una zona protetta con specie");
    		setDatiImportModel(model, form, utente);
   	    }
   	    setDatiPassportoModel(model, form, utente);
   	 	return getViewNuovaModifica("comunicazioni/passaporto", request);
    }
    
    @PreAuthorize("hasRoleImpExp('WRITE', #request)")   
    @GetMapping(value = {"/passaporto/nuova/eliminaTipologiaProd/idTip/{idTip}/idSpecie/{idSpecie}", "/passaporto/nuova/eliminaTipologiaProd/idTip/{idTip}",
    					"/passaporto/modifica/eliminaTipologiaProd/idTip/{idTip}/idSpecie/{idSpecie}", "/passaporto/modifica/eliminaTipologiaProd/idTip/{idTip}"})
    public String eliminaTipologiaProdPassaporto(@PathVariable Long idTip, @PathVariable(required = false) Long idSpecie, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form,
    		BindingResult result, Model model, HttpServletRequest request) throws BusinessException {
    	 
    	try { 
    		UtenteDTO utente = SecurityUtils.getUtenteLoggato();
	 		logger.debug("---- idDomanda ="+form.getIdDomanda());
	 		
	 		logger.debug("-- idSpecie ="+idSpecie);
	 		
			domandeEJB.eliminaTipologProduttiva(idTip, idSpecie, form.getIdDomanda(), utente.getId());
			setDatiPassportoModel(model, form, utente);

    	} catch (Exception e) {
    		logger.error("-- Exception in eliminaTipologiaProd ="+e.getMessage());
    		addErrorMessage("Errore nell'eliminazione della Tipologia produttiva");
    	}	 

   	 	return getViewNuovaModifica("comunicazioni/passaporto", request);
    }
    
    @PreAuthorize("hasRoleImpExp('WRITE', #request)")
    @GetMapping(value = {"/passaporto/nuova/eliminaZonaProtetta/idZonaProtetta/{idZonaProtetta}/idSpecie/{idSpecie}", "/passaporto/modifica/eliminaZonaProtetta/idZonaProtetta/{idZonaProtetta}/idSpecie/{idSpecie}"})  
    public String eliminaZonaProtettaPassaporto(@PathVariable Long idZonaProtetta, @PathVariable Long idSpecie, 
    		@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, HttpServletRequest request) throws BusinessException {
    	 
    	try { 
    		UtenteDTO utente = SecurityUtils.getUtenteLoggato();
	 		logger.debug("-- idDomanda ="+form.getIdDomanda());
			domandeEJB.eliminaZonaProtetta(idZonaProtetta, idSpecie, form.getIdDomanda(), utente.getId());
			setDatiPassportoModel(model, form, utente);

    	} catch (Exception e) {
    		logger.error("-- Exception in eliminaZonaProtettaPassaporto ="+e.getMessage());
    		addErrorMessage("Errore nell'eliminazione della Zona protetta");
    	}	 

   	 	return getViewNuovaModifica("comunicazioni/passaporto", request);
    }
    
    // Salva Dati Passaporto
    @PreAuthorize("hasRoleImpExp('WRITE', #request)")
    @PostMapping(value = { "/passaporto/nuova", "/passaporto/modifica" })
    public String salvaDatiPassaporto(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {
    	
    	UtenteDTO utente = SecurityUtils.getUtenteLoggato();
    	logger.debug("-- Validazione dei dati Passaporto");
    	logger.debug("-- Centro aziendale selezionato = "+ form.getIdCentroAziendalePassaporto());
 	 	// in caso di modifica potrei avere sul DB i dati
   	   // CarTResponsabilePassaporto respDB = domandeEJB.getRespPassaportoByIdDomanda(form.getIdDomanda());
   	 	List<TipologiaProdSpecieDTO> tipProdDB = domandeEJB.getTipologieProdByIdDomanda(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 4L);
   	 	List<ZonaProtettaSpecieDTO> zonaProtettaSpecieDB = domandeEJB.getZoneProtetteSpecieByIdDomanda(form.getIdDomanda());
        validator.validateDatiPassaporto(form, tipProdDB, zonaProtettaSpecieDB, result);
        
    	if (result.getErrorCount() == 0 && form.getIdDomanda() != null) {
    		logger.debug("-- Nessun errore di validazione in passaporto");
    		domandeEJB.salvaVociPassaporto(form, utente);    	
    		addSuccessMessage("Dati passaporto inseriti"); 
    		
    		if (isNuovaDomanda(request)) {
        	 	logger.debug("-- Passo al tab allegati");
        	 	return getRedirect("comunicazioni/allegati/nuova", request);
        	}
    		/* in caso di modifica resto nella stessa pagina è sarà l'utente a muoversi nei tab
    		 * else{ 
    		  return getRedirect("comunicazioni/allegati/modifica", request);	
    		}*/
    		
    	} 
    	else {
    		logger.debug("-- Errore di validazione in salvaDatiPassaporto" + result.getFieldError());
    		// Caso in cui non è obbligatorio inserire i dati del responsabile fitosanitario
    		if(form.getCheckRespFito() != null ){
    			addErrorMessage("Indicare il responsabile fitosanitario ed aggiungere almeno una categoria e una zona protetta");    		  
    		} else if (form.getVoceDichiaraConoscenze() == null || form.getVoceDichiaraDisporreSistemi() == null
    				|| form.getVocePianoRischi() == null) {
    			addErrorMessage("E' necessario rispondere alle dichiarazioni");    			
    		} else {
    			addErrorMessage("Aggiungere almeno una categoria e una zona protetta");
    		}
    	}
    	   		
    	setDatiPassportoModel(model, form, utente);
    	return getViewNuovaModifica("comunicazioni/passaporto", request);
    }
    
  
    // Nuova domanda RUOP - Tab Import
    @PreAuthorize("hasRoleImpExp('WRITE', #request)")
    @GetMapping(value = { "/impAuto/nuova", "/impAuto/modifica"})
    public String datiImport(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {	
      try {
   	  UtenteDTO utente = SecurityUtils.getUtenteLoggato();
   	  
   	  // Carico i dati delle combo e i dati della tabellina delle tipologie sul model
   	  setDatiImportModel(model, form, utente);
   	  
   	}
   	catch (BusinessException exc) {
   	  addErrorMessage("Errore in fase di caricamento dati Import");
   	}
     	return getViewNuovaModifica("comunicazioni/impAuto", request);   
    } 
    
    
   
   	 
    private void setDatiImportModel(Model model, NuovaDomandaForm form, UtenteDTO utente) throws BusinessException {    	
    	logger.debug(" BEGIN setDatiImportModel");
    	
    	// Combo Tipologie produttive
    	model.addAttribute("listaTipologieImport", decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 1L));    	
    	List<CarDVoce> vociRadioZoneProtette = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 3L);
    	model.addAttribute("vociRadioZoneProtette", vociRadioZoneProtette);    	
    	List<CarDVoce> listaContinenti  = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 2L);
    	model.addAttribute("listaContinenti", listaContinenti);
    	
    	// Recupero i dati dal DB nel caso si stesse ricaricando la pagina
    	if (form.getIdDomanda() != null ){
        	// tipologie produttive
      		List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 1L);
      		model.addAttribute("tipologieProdImportDb", tipologieProdDb);
      		// Salvo i dati della Tipologia sul db
      		logger.debug("-- Salvo i dati della Tipologia sul db");
      		domandeEJB.aggiornaTipologia(form, utente.getId());     	
    	}
    	
    }
  
    
     // Nuova domanda RUOP - Tab Export
    @PreAuthorize("hasRoleImpExp('WRITE', #request)")
    @GetMapping(value = { "/expAuto/nuova", "/expAuto/modifica"  })
    public String datiExport(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {	
      try {
   	  UtenteDTO utente = SecurityUtils.getUtenteLoggato();
   	  
   	  // Carico i dati delle combo sul model e dati tabellina Tipologie produttive
   	  setDatiExportModel(model, form, utente);
   	  
   	}
   	catch (BusinessException exc) {
   	  addErrorMessage("Errore in fase di caricamento dati Export");
   	}
     	return getViewNuovaModifica("comunicazioni/expAuto", request);   
    } 

       
      	 
       private void setDatiExportModel(Model model, NuovaDomandaForm form, UtenteDTO utente) throws BusinessException {
       	
       	logger.debug(" BEGIN setDatiExportModel");
       	
       	// Check tipologie di esportazione (Titolo : 'Le esportazioni riguradano principalmente :')
       	List<CarDVoce> listaTipologieVegetaliExport  = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 1L);
       	model.addAttribute("listaTipologieVegetaliExport", listaTipologieVegetaliExport);
       	    
       	// Check continenti
       	List<CarDVoce> listaContinenti  = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 2L);
       	model.addAttribute("listaContinentiExp", listaContinenti);       	
       	
       	// Combo Tipologie produttive
       	model.addAttribute("listaTipologieExport", decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 3L));
       	
        // Recupero i dati dal DB nel caso si stesse ricaricando la pagina
    	if (form.getIdDomanda() != null ){
        	// tipologie produttive
      		List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 3L);
      		model.addAttribute("tipologieProdExportDb", tipologieProdDb); 
      		// Salvo i dati della Tipologia sul db
			logger.debug("-- Salvo i dati della Tipologia sul db");
			domandeEJB.aggiornaTipologia(form, utente.getId());
    	}
       	
       }
   

       // Tab Import : aggiungi tipologia produttiva
       @PreAuthorize("hasRoleImpExp('WRITE', #request)")   
       @PostMapping(params = "aggiungiSpecieImport", value = { "/impAuto/nuova", "/impAuto/modifica" })
       public String aggiungiTipologiaProdSpecieImport(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, HttpServletRequest request) throws BusinessException {       	
    	   logger.debug("-- Aggiungi tipologia produttiva IMPORT");
    	   logger.debug("-- genere selezionato = "+form.getGenereImport());
    	   UtenteDTO utente = SecurityUtils.getUtenteLoggato();    	   
    	   String error = validator.validateTipologiaProdImport(form, result);
    	   
    	   // mi recupero l'id del genere selezionato
    	   if (result.getErrorCount() == 0) {
	    	   if (form.getGenereImport() != null) {
	    		   List<GenereDTO> listaGeneri = generiSpecieEJB.getListaGeneri(form.getGenereImport(), CaronteConstants.CODICE_LINGUA_LATINO);
	    		   form.setIdGenereImport(listaGeneri.get(0).getIdGenere());   
	    	   }
	    	   if (form.getIdDomanda() != null) {
	    		   Long idVoceUtente = domandeEJB.salvaTipologiaProdSpecieImport(form, utente);   	    
	    		   logger.debug("-- idVoceUtente inserita = "+idVoceUtente);	
	    	   } 
	    	   puliscoFormTipologiaProdImport(form);
    	   }
    	   else{
    	    	addErrorMessage(error); 
    	   }
    	   setDatiImportModel(model, form, utente);    	   

    	   return getViewNuovaModifica("comunicazioni/impAuto", request);
       }
       
       
       private void puliscoFormTipologiaProdImport(NuovaDomandaForm form) {
    	   form.setIdTipologiaProdImport(null);
    	   form.setSpecieImport(null);
    	   form.setGenereImport(null);
    	   form.setIdGenereImport(null);
    	   form.setNoteTipologiaImport(null);
       }
       
      // TAB IMPORT : Elimina tipologia produttiva : elimina sul db i dati della tipologia produttiva e specie selezionata 
       @PreAuthorize("hasRoleImpExp('WRITE', #request)")
       @GetMapping(value = { "/impAuto/nuova/eliminaTipologiaProd/idTip/{idTip}", "/impAuto/nuova/eliminaTipologiaProd/idTip/{idTip}/idSpecie/{idSpecie}",
    		   				 "/impAuto/modifica/eliminaTipologiaProd/idTip/{idTip}", "/impAuto/modifica/eliminaTipologiaProd/idTip/{idTip}/idSpecie/{idSpecie}"})
       public String eliminaTipologiaProdImport(@PathVariable Long idTip,@PathVariable(required = false) Long idSpecie, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {
      	 try{
      		 UtenteDTO utente = SecurityUtils.getUtenteLoggato();
      		
      		 logger.debug("---- idDomanda ="+form.getIdDomanda());
      		 logger.debug("------ idVoceUtente ="+idTip);
      		logger.debug("------ idSpecie ="+idSpecie);
      		
      		 domandeEJB.eliminaTipologProduttiva(idTip, idSpecie, form.getIdDomanda(), utente.getId());      		

      		 setDatiImportModel(model, form, utente);
      	 }
      	 catch (Exception e) {
      		 logger.error("-- Exception in eliminaTipologiaProd ="+e.getMessage());
      		 addErrorMessage("Errore nell'eliminazione della Tipologia produttiva");
      	 }
      	 return getViewNuovaModifica("comunicazioni/impAuto", request);
       }
       
       // TAB IMPORT : Salva dati Import
       @PreAuthorize("hasRoleImpExp('WRITE', #request)")
       @PostMapping(value = { "/impAuto/nuova", "/impAuto/modifica" })
       public String salvaDatiImport(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {
       	logger.debug("BEGIN salvaDatiImport");
       	UtenteDTO utente = SecurityUtils.getUtenteLoggato();
       	logger.debug("-- Validazione dei dati Import");
       	List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 1L);
        validator.validateDatiImport(form, result, tipologieProdDb);
                   
           
       	if (result.getErrorCount() == 0) {
       		logger.debug("-- Nessun errore di validazione in import");
       		domandeEJB.salvaDatiImport(form, utente);    	
       		addSuccessMessage("Dati import inseriti"); 
       		
       		logger.debug("--- Controllo su quale tab devo andare : Export, Passaporto o Allegati");
       		// Controllo su quale tab devo andare : Export, Passaporto o Allegati
       		if(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP || form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP ){
       			// Se siamo nel caso di salvataggio dati di una nuova Domanda : passo al Tab successivo
       			if(isNuovaDomanda(request)){
				  String[] idtipologiaArr = form.getIdTipologia();					 					 
				  for (int i = 0; i < idtipologiaArr.length; i++) {
					  Long idTipologia = Long.parseLong(idtipologiaArr[i]);					 				  
					  if (idTipologia == CaronteConstants.ID_TIPOLOGIA_DOMANDA_EXP) {
						  form.setTabExport(true);	
					  }					  
					  if (idTipologia == CaronteConstants.ID_TIPOLOGIA_DOMANDA_PASS) {
						  form.setTabPassaporto(true);	
					  }											
				  }	
				  // Export o Passaporto
				  if(form.isTabExport() || form.isTabPassaporto()){
					  if(form.isTabExport()){
						  if(isNuovaDomanda(request)){
							  return getRedirect("comunicazioni/expAuto/nuova", request);
						  }
						  else{
							  return getRedirect("comunicazioni/expAuto/modifica", request);
						  } 
					  }
					  if(form.isTabPassaporto()){
						  if(isNuovaDomanda(request)){
							  return getRedirect("comunicazioni/passaporto/nuova", request);
						  }
						  else{
							  return getRedirect("comunicazioni/passaporto/modifica", request);
						  } 	 
					  }		
				  }
				  // Allegati
				  else{
					  if(isNuovaDomanda(request)){
						  return getRedirect("comunicazioni/allegati/nuova", request);
					  }
					  else{
						  return getRedirect("comunicazioni/allegati/modifica", request);
					  }  
				  }
			  } 
       		}       		
       	} 
       	else {
       		logger.debug("-- Errore di validazione in salvaDatiImport");
       		addErrorMessage("Indicare: almeno una tipologia produttiva; se l'importazione avviene anche in Zone protette; in quale continente avvengono le importazioni");
       		setDatiImportModel(model, form, utente);
       	}
       	   		
       	setDatiImportModel(model, form, utente);
       	return getViewNuovaModifica("comunicazioni/impAuto", request);
       }       
       
       
       // Tab Export : aggiungi tipologia produttiva
       @PreAuthorize("hasRoleImpExp('WRITE', #request)")   
       @PostMapping(params = "aggiungiSpecieExp", value = { "/expAuto/nuova", "/expAuto/modifica" })
       public String aggiungiTipologiaProdSpecieExport(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, HttpServletRequest request) throws BusinessException {       	
    	   logger.debug("-- Aggiungi tipologia produttiva EXPORT");
    	   UtenteDTO utente = SecurityUtils.getUtenteLoggato();
    	   String error = validator.validateTipologiaProdExport(form, result);

    	   // mi recupero l'id del genere selezionato
    	   if (result.getErrorCount() == 0) {
    		   logger.debug("-- form.getGenereExport() = "+form.getGenereExport());
	    	   if (form.getGenereExport() != null) {
	    		   List<GenereDTO> listaGeneri = generiSpecieEJB.getListaGeneri(form.getGenereExport(), CaronteConstants.CODICE_LINGUA_LATINO);
	    		   form.setIdGenereExport(listaGeneri.get(0).getIdGenere());  
	    	   }
	    	   if (form.getIdDomanda() != null) {
    			   Long idVoceUtente = domandeEJB.salvaTipologiaProdSpecieExport(form, utente);   	    
        		   logger.debug("-- idVoceUtente inserita = "+idVoceUtente);		
	    	   }
	    	   puliscoFormTipologiaProdExport(form);
    	   }
    	   else{
    		   addErrorMessage(error);
    	   }

    	   setDatiExportModel(model, form, utente);
    	   return getViewNuovaModifica("comunicazioni/expAuto", request);
       }
       
       
       private void puliscoFormTipologiaProdExport(NuovaDomandaForm form) {
    	   form.setIdTipologiaProdExp(null);
    	   form.setSpecieExport(null);
    	   form.setGenereExport(null);
    	   form.setIdGenereExport(null);
    	   form.setNoteTipologiaExport(null);
       }
       
       // TAB EXPORT : Elimina tipologia produttiva : elimina sul db i dati della tipologia produttiva e specie selezionata 
       @PreAuthorize("hasRoleImpExp('WRITE', #request)")
       @GetMapping(value = {"/expAuto/nuova/eliminaTipologiaProdExp/idTip/{idTip}", "expAuto/nuova/eliminaTipologiaProdExp/idTip/{idTip}/idSpecie/{idSpecie}",
    		   				"/expAuto/modifica/eliminaTipologiaProdExp/idTip/{idTip}", "expAuto/modifica/eliminaTipologiaProdExp/idTip/{idTip}/idSpecie/{idSpecie}" })
       public String eliminaTipologiaProdExport(@PathVariable Long idTip, @PathVariable(required = false) Long idSpecie, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {
      	 try{
      		 UtenteDTO utente = SecurityUtils.getUtenteLoggato();
      		
      		 logger.debug("---- idDomanda ="+form.getIdDomanda());
      		 logger.debug("------ idVoceUtente ="+idTip);
      		logger.debug("------ idSpecie ="+idSpecie);

      		 domandeEJB.eliminaTipologProduttiva(idTip, idSpecie, form.getIdDomanda(), utente.getId());      		

      		 setDatiExportModel(model, form, utente);
      	 }
      	 catch (Exception e) {
      		 logger.error("-- Exception in eliminaTipologiaProdExport ="+e.getMessage());
      		 addErrorMessage("Errore nell'eliminazione della Tipologia produttiva");
      	 }
      	 return getViewNuovaModifica("comunicazioni/expAuto", request);
       }
       
       
       // TAB EXPORT : Salva dati Export
       @PreAuthorize("hasRoleImpExp('WRITE', #request)")
       @PostMapping(value = { "/expAuto/nuova", "/expAuto/modifica" })
       public String salvaDatiExport(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {
       	logger.debug("BEGIN salvaDatiExport");
       	UtenteDTO utente = SecurityUtils.getUtenteLoggato();
       	logger.debug("-- Validazione dei dati Export");
       	// Recupero le tipologie salvate su db
       	List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 3L);
        validator.validateDatiExport(form, result, tipologieProdDb);
                   
           
       	if (result.getErrorCount() == 0) {
       		logger.debug("-- Nessun errore di validazione in Export");
       		domandeEJB.salvaDatiExport(form, utente);    	
       		addSuccessMessage("Dati export inseriti"); 
       		
       		logger.debug("--- Controllo su quale tab devo andare : Passaporto o Allegati");
       		// Controllo su quale tab devo andare : Passaporto o Allegati in caso di nuova domanda
       		if(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP || form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP ){			
       			if(isNuovaDomanda(request)){
       				String[] idtipologiaArr = form.getIdTipologia();					 					 
       				for (int i = 0; i < idtipologiaArr.length; i++) {
       					Long idTipologia = Long.parseLong(idtipologiaArr[i]);					 				  					 				  
       					if (idTipologia == CaronteConstants.ID_TIPOLOGIA_DOMANDA_PASS) {
       						form.setTabPassaporto(true);	
       					}											
       				}	
       				if(form.isTabPassaporto()){					 
       					if(isNuovaDomanda(request)){
       						return getRedirect("comunicazioni/passaporto/nuova", request);
       					}
       					else{
       						return getRedirect("comunicazioni/passaporto/modifica", request);
       					} 	 
       				}
       				// Allegati
       				else{
       					if(isNuovaDomanda(request)){
       						return getRedirect("comunicazioni/allegati/nuova", request);
       					}
       					else{
       						return getRedirect("comunicazioni/allegati/modifica", request);
       					}  
       				}
       			} 
       		}
       	} 
       	else {
       		logger.debug("-- Errore di validazione in salvaDatiExport");
       		addErrorMessage("Indicare le informazioni di esportazione; verso quale continente avvengono le esportazioni; almeno una tipologia di esportazione");
       		setDatiExportModel(model, form, utente);
       	}
       	   		
       	setDatiExportModel(model, form, utente);
       	return getViewNuovaModifica("comunicazioni/expAuto", request);
       }
       
       
       
       // Nuova domanda RUOP - Tab Allegati
       @PreAuthorize("hasRoleImpExp('WRITE', #request)")
       @GetMapping(value = { "/allegati/nuova", "/allegati/modifica" })
       public String datiAllegati(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {	
         try {
   		   setDatiAllegati(model, form);
   		 } 
         catch (BusinessException exc) {
   		   addErrorMessage("Errore in fase di caricamento dati allegati");
   		 }

   		return getViewNuovaModifica("comunicazioni/allegati", request);  
       } 
       
       private void setDatiAllegati(Model model, NuovaDomandaForm form) throws BusinessException {
    	 List<AllegatoDTO> allegatiList =  domandeEJB.getListaAllegatiDomanda(form.getIdDomanda());
    	 
    	 /* 
    	  * Gestione Caso Domanda Passaporto:
    	  *  - Presenza Codice Ruop per l'Azienda selezionata e:    	  *    
				Sempre obbligatori configurati da db :
				- Scansione Documento d'identità in corso di validità (config obbligatorio su db)
				- Marca da bollo (config obbligatorio su db)				
				
				** Gestione caso in cui non sia presente una Domanda Ruop convalidata per la stessa azienda 
					(caso di dati importati, di aziende che hanno già fatto Domanda Ruop al di fuori di Caronte)
				    (allegati obbligatori per la Domanda Ruop e allegati obbligatori per il Passaporto):
				- Scansione Documento d'identità in corso di validità (config obbligatorio su db)
				- Marca da bollo (config obbligatorio su db)	
				- 'Autorizzazione Ruop rilasciata dalla regione di competenza' (obbligatorietà gestita sul codice, non da db) :
				      se la provincia della sede legale dell'azienda dell'utente della domanda è fuori regione Lombardia
    	  * 
    	  */
    	 
    	 
    	 
    	 /*
    	  * Domanda Ruop e Variazione Ruop : il tipo allegato : 'Scansione Marca da Bollo da 16 euro' è obbligatorio solo se hanno scelto la tipologia 'Richiesta passaporto'
    	  * 
    	  */
    	 boolean tipologiaRichiestaPassaporto = false;  
    	 TipologiaDomandaDTO tipologiaDomanda = domandeEJB.getDettTipologiaDomanda(form.getIdDomanda());
    	 if(tipologiaDomanda != null && tipologiaDomanda.getTipologieDomandaList() != null){
    		 for (Iterator<CarRDomandaTipologia> iterator = tipologiaDomanda.getTipologieDomandaList().iterator(); iterator.hasNext();) {
				CarRDomandaTipologia domandaTip = (CarRDomandaTipologia) iterator.next();
				Long idTipologia = domandaTip.getIdTipologia();    							  
    			if (idTipologia == CaronteConstants.ID_TIPOLOGIA_DOMANDA_PASS) {
    				tipologiaRichiestaPassaporto = true;
    			}	
			}
    	 }
    	 /*if(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
    		 logger.debug("-- Gestione allegati obbligatori per la Domanda Passaporto");
    		 if(form.isDomandaRuopConvalidataPresente()){
    			 logger.debug("-- Caso Allegati domandaRuopConvalidataPresente");
    			 Long idAziendaDomanda = form.getIdAzienda();
    	    	 logger.debug("-- idAziendaDomanda ="+idAziendaDomanda);
    			 Long idDomandaRuopConvalidata = domandeEJB.getIdDomandaRuopConvalidata(idAziendaDomanda);
 				 form.setIdDomandaPrecedente(idDomandaRuopConvalidata);
    			 // Controllo se per la Domanda Ruop Convalidata era stato compilato il tab Passaporto
    			 logger.debug("-- Controllo se per la Domanda Ruop Convalidata era stato compilato il tab Passaporto");
    			 tipologiaDomanda = domandeEJB.getDettTipologiaDomanda(form.getIdDomandaPrecedente());
    			 boolean passaportoCompilatoDomandaPrec = false; 
    			 if(tipologiaDomanda != null && tipologiaDomanda.getTipologieDomandaList() != null){
    	    		 for (Iterator<CarRDomandaTipologia> iterator = tipologiaDomanda.getTipologieDomandaList().iterator(); iterator.hasNext();) {
    					CarRDomandaTipologia domandaTip = (CarRDomandaTipologia) iterator.next();
    					Long idTipologia = domandaTip.getIdTipologia();    	    					
    	    			if (idTipologia == CaronteConstants.ID_TIPOLOGIA_DOMANDA_PASS) {    	    				
    	    				passaportoCompilatoDomandaPrec = true;
    	    			}	
    				}
    	    	 }
    			 logger.debug("-- passaportoCompilatoDomandaPrec ="+passaportoCompilatoDomandaPrec);
    			 // se NON era ancora stata compilata la parte del Passaporto il tipo allegato : 'Scansione Marca da Bollo da 16 euro' sarà obbligatorio
    			 if(!passaportoCompilatoDomandaPrec){
    				 tipologiaRichiestaPassaporto = true;
    			 }
    		 }
    		 
    		  //  In questo caso non sappiamo cos'era stato compilato sulla Domanda Ruop importata o fuori Caronte, quindi controlliamo se l'utente ha checkato :
    		  //  'Prima richiesta(82) o Aggiornamento'(83)     		      		
    		 else{    			 
    			 logger.debug("-- Caso Allegati domandaRuopConvalidata NON Presente - DATI IMPORTATI");    			 
    			 // Ricerco se è stato selezionato 'Prima richiesta' o 'Aggiornamento'
    			 logger.debug("-- Ricerco se è stato selezionato 'Prima richiesta' o 'Aggiornamento'");
    			 Long[] idVoceRadioTipoRichiesta = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 1L, form.getIdDomanda());
    			 if(idVoceRadioTipoRichiesta != null && idVoceRadioTipoRichiesta.length>0){
    				Long voceRadioSel = idVoceRadioTipoRichiesta[0];
    				logger.debug("-- voceRadioSel ="+voceRadioSel);
    				if(voceRadioSel.longValue() == CaronteConstants.PRIMA_RICHIESTA_PASSAPORTO.longValue()){
    					tipologiaRichiestaPassaporto = true;
    				}
    			 }
    		 }
    	 }*/
    	 
    	 if(form.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
	    	 // ********** Se tipologiaRichiestaPassaporto = true la Marca da Bollo è obbligatoria
	    	 logger.debug("-- tipologiaRichiestaPassaporto ="+tipologiaRichiestaPassaporto);    	
	    	 if(tipologiaRichiestaPassaporto){    		 
	    		 for (Iterator<AllegatoDTO> iterator = allegatiList.iterator(); iterator.hasNext();) {
	    			 AllegatoDTO allegatoDTO = (AllegatoDTO) iterator.next();
	    			 if(allegatoDTO.getIdTipoAllegato() == CaronteConstants.ID_TIPO_ALLEGATO_MARCA_DA_BOLLO){
	    				 allegatoDTO.setFlagObbligatorio(true);
	    			 }    			 				
	    		 }    		 
	    	 }    	 
	    	 else{
	    		 for (Iterator<AllegatoDTO> iterator = allegatiList.iterator(); iterator.hasNext();) {
	    			 AllegatoDTO allegatoDTO = (AllegatoDTO) iterator.next();
	    			 if(allegatoDTO.getIdTipoAllegato() == CaronteConstants.ID_TIPO_ALLEGATO_MARCA_DA_BOLLO){
	    				 iterator.remove();
	    			 }    			 			
	    		 } 
	    	 }
    	 }
    	 
    	 
    	 /* 
    	  *  Se la provincia della sede legale dell'azienda dell'utente della domanda è fuori regione Lombardia, devo richiedere anche l'Allegato 'Autorizzazione Ruop rilasciata dalla regione di competenza', altrimenti no
    	  *  
    	  *  Note : nel caso di Domanda Passaporto questo controllo deve essere effettuato solo nel caso in cui non sia presente 
    	  *  una Domanda Ruop convalidata per la stessa azienda
    	  */
    	 if(form.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO ||
    			 (form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO && !form.isDomandaRuopConvalidataPresente())
    		 ){
	    	 logger.debug("-- Verifico se la provincia della sede legale dell'azienda dell'utente della domanda è fuori regione Lombardia");
	    	 boolean aziendaFuoriRegione = false;
	    	 Long idAziendaDomanda = form.getIdAzienda();
	    	 logger.debug("-- idAziendaDomanda ="+idAziendaDomanda);    	 
	    	 CarTSpedizioniere azienda = utenteEJB.getCarTSpedizioniere(idAziendaDomanda);
	    	 if(azienda != null){
		    	 CarDComune comuneAz = decodificheEJB.getComuneByPrimaryKey(azienda.getIdComune());
		    	 if(comuneAz != null){
		    		 CarDProvincia provAz = decodificheEJB.getProvinciaByIdProv(comuneAz.getIdProvincia()); 
		    		 if(provAz != null){
		    			 if(provAz.getIdRegione() != CaronteConstants.ID_REGIONE_LOMBARDIA){
		    				 aziendaFuoriRegione = true;
		    			 }
		    		 }
		    	 }
	    	 }
	    	 logger.debug("-- aziendaFuoriRegione ="+aziendaFuoriRegione);
	    	 // ***********  L'azienda non è fuori regione, non deve vedere negli Allegati il tipo Allegato 6 (Autorizzazione Ruop rilasciata dalla regione di competenza)
	    	 if(!aziendaFuoriRegione){
	    		 for (Iterator<AllegatoDTO> iterator = allegatiList.iterator(); iterator.hasNext();) {
	    			 AllegatoDTO allegatoDTO = (AllegatoDTO) iterator.next();
	    			 if(allegatoDTO.getIdTipoAllegato() == CaronteConstants.ID_TIPO_ALLEGATO_AUTORIZZAZIONE_RUOP){
	    				 iterator.remove();
	    			 }    			 			
	    		 } 
	    	 }
	    	 // Se è fuori regione, questo tipo di Allegato deve diventare obbligatorio
	    	 else{
	    		 for (Iterator<AllegatoDTO> iterator = allegatiList.iterator(); iterator.hasNext();) {
	    			 AllegatoDTO allegatoDTO = (AllegatoDTO) iterator.next();
	    			 if(allegatoDTO.getIdTipoAllegato() == CaronteConstants.ID_TIPO_ALLEGATO_AUTORIZZAZIONE_RUOP){
	    				 allegatoDTO.setFlagObbligatorio(true); 
	    			 }    			 			
	    		 } 
	    	 }
    	 }
    	 // In questo caso non si deve visualizzare l'Allegato per il fuori regione
    	 if(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO && form.isDomandaRuopConvalidataPresente()){
    		 for (Iterator<AllegatoDTO> iterator = allegatiList.iterator(); iterator.hasNext();) {
    			 AllegatoDTO allegatoDTO = (AllegatoDTO) iterator.next();
    			 if(allegatoDTO.getIdTipoAllegato() == CaronteConstants.ID_TIPO_ALLEGATO_AUTORIZZAZIONE_RUOP){
    				 iterator.remove();
    			 }    			 			
    		 }    		 
    	 }
    	 
    	 
    	 
    	 model.addAttribute("listaAllegati", allegatiList);
    	
    	 form.setAbilitaInoltra(checkAbilitaInoltra(form.getIdUtente(), form.getIdDomanda()));
    	 model.addAttribute("abilitaInoltra", form.isAbilitaInoltra());
    	 if(form.isAbilitaInoltra()){
	   	     // Testo della modale INOLTRA
	   	     CarDCostante testo = decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_TESTO_INOLTRA_PARTE_1);
			 model.addAttribute("testoInoltra", testo.getValoreCostante());
			 testo = decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_TESTO_INOLTRA_PARTE_2);
			 model.addAttribute("testoInoltra2", testo.getValoreCostante());
			 testo = decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_TESTO_INOLTRA_PARTE_3);
			 model.addAttribute("testoInoltra3", testo.getValoreCostante());
    	 }
 		 
 		 model.addAttribute("listaModuli", decodificheEJB.getListaModuliComunicazione(form.getIdDomanda()));
 		 model.addAttribute("idDomanda", form.getIdDomanda());
   	   }
       
       
       // Tab Allegati : salva allegati
       @PreAuthorize("hasRoleImpExp('WRITE', #request)")
       @PostMapping(value = { "/allegati/nuova", "/allegati/modifica" })
       public String salvaDatiAllegati(@ModelAttribute("allegatiForm") AllegatiDomandaForm allegatiForm, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm domandaForm, Model model, HttpServletRequest request) throws BusinessException {
    	   logger.debug("BEGIN salvaDatiAllegati");
    	   try {
    		   Long idUtente = SecurityUtils.getUtenteLoggato().getId();

    		   if (!checkModificaDomanda(idUtente, domandaForm)) {
    			   return getRedirect("comunicazioni/elenco", request);
    		   } 
    		   List<ModuloDTO> listaModuli = decodificheEJB.getListaModuliComunicazione(domandaForm.getIdDomanda());
    		   
	   		   if (listaModuli != null && listaModuli.size() > 0) {
	   			   logger.debug("converti file moduli");
	   			   logger.debug("modulo[0] =" +listaModuli.get(0).getIdModulo());
	   				allegatiForm.convertiFileModuli(listaModuli);
	   		   }
	   		   domandeEJB.aggiornaDatModuli(domandaForm.getIdDomanda(), listaModuli, idUtente);
	   		   logger.debug("aggiornamento dati moduli ok");
	   		   
    		   List<AllegatoDTO> listaAllegati = allegatiForm.convertiFileAllegati();
    		   logger.debug("Inizio aggiornamento allegati e moduli");
    		   domandeEJB.aggiornaDatiAllegati(domandaForm.getIdDomanda(), listaAllegati, idUtente);
    		   logger.debug("aggiornamento dati allegati ok");
    		   
    		   addSuccessMessage("Dati allegati modificati");    		   

    	   } catch (BusinessException be) {
    		   addErrorMessage("Errore nel salvataggio dei dati allegati");
    	   }

    	   setDatiAllegati(model, domandaForm);
    	   return getViewNuovaModifica("comunicazioni/allegati", request);
       }
       
       
       // Tab Allegati : scarica allegato
       @PreAuthorize("hasRoleImpExp('READ', #request)")
       @GetMapping(value = "/allegato/scarica/{idAllegatoDomanda}", produces = { "application/octet-stream" })
       public void downloadAllegato(@PathVariable Long idAllegatoDomanda, Model model, HttpServletResponse response) {
    	   logger.debug("BEGIN downloadAllegato");
    	   try {
    		   logger.debug("-- idAllegatoDomanda da scaricare ="+idAllegatoDomanda);
    		   CarTAllegatoDomanda allegato = domandeEJB.getAllegatoDomandaById(idAllegatoDomanda);

    		   if (allegato != null && !StringUtils.isEmpty(allegato.getNomeFile())) {
    			   /*
    			    * Il filename viene encodato per evitare vulnerabilità di
    			    * response splitting
    			    */
    			   scaricaFile("application/octet-stream",
    					   "attachment; filename=" + URLEncoder.encode(allegato.getNomeFile(), "UTF-8"),
    					   allegato.getAllegato(), response);
    		   }
    	   } 
    	   catch (BusinessException | IOException exc) {
    		   logger.error("Errore nel reperimento dell'allegato", exc);
    	   }
       }
       
       
       @PreAuthorize("hasRoleImpExp('WRITE', #request)")
       @GetMapping(value = "/modifica/{id}")
       public String modificaDatiAnagrafici(@PathVariable Long id, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {
    	   logger.debug("-- BEGIN modificaDatiAnagrafici");
    	   String messaggioErrore = null;

    	   try {
    		   UtenteDTO utente = SecurityUtils.getUtenteLoggato();
    		   Boolean isUtenteAbilitato = domandeEJB.isDomandaModificabile(utente.getId(), id);

    		   if (isUtenteAbilitato == null) {
    			   messaggioErrore = "La domanda non può essere modificata dall'utente corrente";
    		   } 
    		   else if (!isUtenteAbilitato) {       			   
   				 // L'utente non è abilitato a modificare i dati, ma può avanzare lo stato della richiesta
    			logger.debug("-- L'utente non è abilitato a modificare i dati, ma può avanzare lo stato della richiesta");   
   				return getRedirect("comunicazioni/avanza/" + id, request);
   			   } 
    		   else {
				   logger.debug("-- Recupero i dati di MODIFICA della domanda con id ="+id);   		
				
				   // -- Dati Tab Anagrafici
				   logger.debug("-- Dati Tab Anagrafici");
				   DomandaDto datiAnagrafici = domandeEJB.getDettaglioAnagraficaByIdDomanda(id);
				   form.setCodiceRuop(datiAnagrafici.getCodiceRuop());
				   form.setCodFiscaleAz(datiAnagrafici.getCuaa());
				   if (!isRightSection(datiAnagrafici, request)) {
					   addErrorMessage("Domanda non modificabile nella sezione corrente");
				   return getRedirect("comunicazioni/elenco", request);
				   }
				
				   //  Dati tipologia (servono per sapere quali tab visualizzare)
				   TipologiaDomandaDTO tipologiaDomanda = domandeEJB.getDettTipologiaDomanda(id);
				   if(tipologiaDomanda != null){
					   setDatiTipoComunicazione(decodificheEJB.getTipoComunicazioneByPrimaryKey(datiAnagrafici.getIdTipoComunicazione()), form);
					   setDatiAnagraficiModifica(form, utente, datiAnagrafici, tipologiaDomanda);
				   }
				   
				   // domanda passaporto: in modifica devo sempre mostrare il tab passaporto					 			   
				   if(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
					   logger.debug("-- Gestione Domanda Passaporto");
					   form.setTabPassaporto(true);
					   					   
					   CarTDomanda domanda = domandeEJB.getCarTDomandaByIdDomanda(id);
					   Long idSpedizioniere = domanda.getIdSpedizioniere();
					   logger.debug("-- idSpedizioniere Domanda Passaporto ="+idSpedizioniere);
					   Long idDomandaRuopConvalidata = domandeEJB.getIdDomandaRuopConvalidata(idSpedizioniere);
					   logger.debug("-- idDomandaRuopConvalidata ="+idDomandaRuopConvalidata);
					   if(idDomandaRuopConvalidata != null){
						   form.setDomandaRuopConvalidataPresente(true);
					   }
					   else{
						   form.setDomandaRuopConvalidataPresente(false);
					   }
					   logger.debug(" -- DomandaRuopConvalidataPresente ="+form.isDomandaRuopConvalidataPresente());
				   }

				
				   // --- Dati Tab Azienda
				   logger.debug("-- Dati Tab Azienda");
				   DomandaDto domandaAzienda = domandeEJB.getDettaglioAziendaByIdDomanda(id);
				   if(domandaAzienda != null)
					   setDatiAziendaModifica(form, utente, domandaAzienda);
				
				
				   //-- INIZIO Dati Tab Tipologia   
				   logger.debug("-- Dati Tab Tipologia");    			   
				   // Dati check Attività DB
				   logger.debug("-- Ricerco i dati check Attività DB");
				   Long[] idVoceUtenteAttivita = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP, 1L, form.getIdDomanda());	    
				   if(null != idVoceUtenteAttivita && idVoceUtenteAttivita.length>0){
					   logger.debug("-- sul db ci sono check attività salvate, quanti ="+idVoceUtenteAttivita.length);
					   String strIdVoce[] = new String[idVoceUtenteAttivita.length];
					   for (int i = 0; i < idVoceUtenteAttivita.length; i++)
						   strIdVoce[i] = String.valueOf(idVoceUtenteAttivita[i]);	    
					   form.setIdVoceCheckTip(strIdVoce);
				   }
				   else{
					   form.setIdVoceCheckTip(null);
				   }
				
				
				   // Dati check Tipologie Domanda DB
				   logger.debug("-- Ricerco i dati check Tipologie Domanda DB");
				   TipologiaDomandaDTO tipologiaDomandadto = domandeEJB.getDettTipologiaDomanda(form.getIdDomanda());
				   if(null != tipologiaDomandadto && null != tipologiaDomandadto.getTipologieDomandaList() && tipologiaDomandadto.getTipologieDomandaList().size()>0){
					   logger.debug("-- ci sono tipologie domande selezionate, quante ="+tipologiaDomandadto.getTipologieDomandaList().size());
						   String[] idTipologiaStr = new String[tipologiaDomandadto.getTipologieDomandaList().size()];
						   for (int i=0;i<tipologiaDomandadto.getTipologieDomandaList().size();i++) {		    		
							   idTipologiaStr[i] = tipologiaDomandadto.getTipologieDomandaList().get(i).getIdTipologia().toString();
						   }
						   form.setIdTipologia(idTipologiaStr);
					}
				    //-- FINE Dati Tab Tipologia  
				   
				   
				   CarTDatiDomanda datiDomanda = null;
				   // -- INIZIO Dati Tab Import
				   // Controllo se devono essere visualizzati i dati del Tab Import
				   if(form.isTabImport()){
					   logger.debug("-- Dati Tab Import");
					   // Radio e note Zone protette
					   logger.debug("-- Ricerco i dati dei radio Zone protette");
					   Long[] idVoceRadioZoneProtetteImport = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 3L, form.getIdDomanda());
					   if(null != idVoceRadioZoneProtetteImport && idVoceRadioZoneProtetteImport.length>0){
						   String[] idVoceRadioZoneProtetteImpStr = new String[idVoceRadioZoneProtetteImport.length];
						   for (int i = 0; i < idVoceRadioZoneProtetteImport.length; i++) {
							   idVoceRadioZoneProtetteImpStr[i] = idVoceRadioZoneProtetteImport[i].toString();
						  }
						  form.setIdVoceRadioZonaProtetta(idVoceRadioZoneProtetteImpStr);
					   }
					   // Note delle Zone protette
					   datiDomanda = domandeEJB.getDatiAggiuntiviByIdDomanda(form.getIdDomanda());
					   if(null != datiDomanda){
					     form.setImpDatoAggiuntivo(datiDomanda.getImpDatoAggiuntivo());
					   } 
					   
					   // Check continenti Import
					   logger.debug("-- Ricerco i dati dei check Continenti Import");
					   Long[] idVoceCheckContinentiImp = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 2L, form.getIdDomanda());
					   if(null != idVoceCheckContinentiImp && idVoceCheckContinentiImp.length>0){
						   String[] idVoceCheckContinentiImpStr = new String[idVoceCheckContinentiImp.length];
						   for (int i = 0; i < idVoceCheckContinentiImp.length; i++) {
							   idVoceCheckContinentiImpStr[i] = idVoceCheckContinentiImp[i].toString();
						   }
						   form.setIdVoceCheckContinenti(idVoceCheckContinentiImpStr);
						   
						   // Note check continenti
						   logger.debug("-- Ricerco Note check continenti"); 
						   String noteStatoOrigine = domandeEJB.getNoteVoceUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 2L, form.getIdDomanda());
						   form.setStatoOrigine(noteStatoOrigine);
					   }			   			   
					   			  
					   // Note Import
					   logger.debug("-- Ricerco Note import");
					   if(null != datiDomanda){					 
						 form.setNoteImport(datiDomanda.getImpNote()); 
					   }
					   else{
					     datiDomanda = domandeEJB.getDatiAggiuntiviByIdDomanda(form.getIdDomanda());
					     if(datiDomanda != null){
					       form.setNoteImport(datiDomanda.getImpNote());
					     }  
					   }
					   // -- FINE Dati Tab Import
				   }
				   
				   
				   // -- INIZIO Dati Tab Export
				   // Controllo se devono essere visualizzati i dati del Tab Export
				   if(form.isTabExport()){
				     logger.debug("-- Dati Tab Export");
				     // Check 'Le esportazioni riguardano principalmente'
				     logger.debug("-- Ricerco i dati dei check 'Le esportazioni riguardano principalmente'");
				     Long[] idVoceCheckEsportazioni = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 1L, form.getIdDomanda());
				     if(null != idVoceCheckEsportazioni && idVoceCheckEsportazioni.length>0){
				    	 String[] idVoceCheckEsportazioniStr = new String[idVoceCheckEsportazioni.length];
				    	 for (int i = 0; i < idVoceCheckEsportazioni.length; i++) {
				    		 idVoceCheckEsportazioniStr[i] = idVoceCheckEsportazioni[i].toString();
						}
				    	 form.setIdVoceCheckTipExp(idVoceCheckEsportazioniStr);
				     }
				     logger.debug("-- Ricerco Note export");
				     if(null != datiDomanda){					 
						 form.setExpDatoAggiuntivo(datiDomanda.getExpDatoAggiuntivo()); 
					 }
					 else{
					   datiDomanda = domandeEJB.getDatiAggiuntiviByIdDomanda(form.getIdDomanda());
					   if(datiDomanda != null){
					     form.setExpDatoAggiuntivo(datiDomanda.getExpDatoAggiuntivo());
					   }  
					 }
				     
				     			     
				     // Check Continenti Export
				     logger.debug("-- Ricerco i dati dei check Continenti Export");
				     Long[] idVoceCheckContinentiExp = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 2L, form.getIdDomanda());
				     if(null != idVoceCheckContinentiExp && idVoceCheckContinentiExp.length>0){
				    	 String[] idVoceCheckContinentiExpStr = new String[idVoceCheckContinentiExp.length];
				    	 for (int i = 0; i < idVoceCheckContinentiExp.length; i++) {
				    		 idVoceCheckContinentiExpStr[i] = idVoceCheckContinentiExp[i].toString();
				    	 }
				    	 form.setIdVoceCheckContinentiExp(idVoceCheckContinentiExpStr);
	
				    	 // Note check continenti Export
				    	 logger.debug("-- Ricerco Note check continenti Export"); 
				    	 String noteStatoOrigineExp = domandeEJB.getNoteVoceUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 2L, form.getIdDomanda());
				    	 form.setStatoOrigineExp(noteStatoOrigineExp);
				     }			     
				   }
				   // -- FINE Dati Tab Export
			   
				// --- Dati Tab Gestione
				   logger.debug("-- Dati Tab Gestione");
				   DomandaDto domandaGestione = domandeEJB.getDettaglioGestioneByIdDomanda(id);			   
				   
				   if(domandaAzienda != null)
					   setDatiAziendaModifica(form, utente, domandaAzienda);
				
				
			   
			 }
    	   }
    	   catch (Exception e) {
    		   logger.error("-- Exception in modifica ="+e.getMessage());
    		   messaggioErrore = "Errore nella modifica della domanda";
    		   throw new BusinessException(e.getMessage());
    	   }   		

    	   if (messaggioErrore != null) {
    		   addErrorMessage(messaggioErrore);

    		   return getRedirect("comunicazioni/elenco", request);
    	   }

    	   return datiDomanda(form, model, request);
       }
    
    private Boolean isRightSection(DomandaDto domanda, HttpServletRequest request) throws BusinessException {
    	logger.debug("-- idAssociazioneSezione della domanda ="+domanda.getIdAssociazioneSezione());
		if (domanda.getIdAssociazioneSezione().equals(getSezioneRequest(request))) {
			return true;
		}
		return false;
	}
    
    private void setDatiAnagraficiModifica(NuovaDomandaForm form, UtenteDTO utente, DomandaDto domanda, TipologiaDomandaDTO tipologiaDomanda) throws Exception {
    	logger.debug("BEGIN setDatiAnagraficiModifica");
		if (null != domanda) {
			form.setIdDomanda(domanda.getIdDomanda());
			form.setIdTipoComunicazione(domanda.getIdTipoComunicazione());
			form.setDescTipoDomanda(domanda.getDescTipoDomanda());
			form.setIdStatoComunicazione(domanda.getIdStatoComunicazione());
			form.setDescStatoComunicazione(domanda.getDescStatoDomanda());

			/*
			 * Dati anagrafici
			 */
			form.setIdUtente(domanda.getIdUtente());
			form.setCognome(domanda.getCognome());
			form.setNome(domanda.getNome());
			form.setCodFiscale(domanda.getCodiceFiscale());

			// Dati nascita
			form.setDataNascita(domanda.getDataNascita());
			if (domanda.getIdComuneNascita() == null) {
				form.setNascitaEstera(true);
				form.setDenomComuneEstNascita(domanda.getDenomComuneEstNascita());
				form.setIdNazioneEstNascita(domanda.getIdNazioneEstNascita());
			} 
			else {
				form.setNascitaEstera(false);
				form.setIdProvinciaNascita(domanda.getIdProvNascita());
				form.setIdComuneNascita(domanda.getIdComuneNascita());
			}

			// Dati residenza
			if (domanda.getIdComuneResidenza() == null) {
				form.setResidenzaEstera(true);
				form.setDenomComuneEstResid(domanda.getDenomComuneEstResid());
				form.setIdNazioneEstResid(domanda.getIdNazioneEstResid());
			} else {
				form.setResidenzaEstera(false);
				form.setIdProvinciaResidenza(domanda.getIdProvResidenza());
				form.setIdComuneResidenza(domanda.getIdComuneResidenza());
			}

			form.setIndirizzo(domanda.getIndirizzo());
			form.setCap(domanda.getCap());
			form.setNumTelefono(domanda.getTelefono());
			form.setCellulare(domanda.getCellulare());
			form.setEmail(domanda.getEmail());
			
			/*
			 * Tipologia
			 */
			form = setDatiTipologiaDomanda(form, tipologiaDomanda);

			
		}

		// ** Per visualizzare o meno il pulsante 'INOLTRA'
		form.setAbilitaInoltra(checkAbilitaInoltra(utente.getId(), domanda.getIdDomanda()));
		logger.debug("-- abilitaInoltra ="+form.isAbilitaInoltra());
		
  	    
		// ** Per visualizzare o meno il pulsante 'CONVALIDA'
		form.setAbilitaConvalida(checkAbilitaConvalida(utente.getId(), domanda.getIdDomanda(), true));
		logger.debug("-- abilitaConvalida ="+form.isAbilitaConvalida());
		
		// ** Per visualizzare o meno il pulsante 'RESPINGI'
		form.setAbilitaRespingi(checkAbilitaRespingi(utente.getId(), domanda.getIdDomanda(), true));
		logger.debug("-- abilitaRespingi ="+form.isAbilitaRespingi());
		
		//** Per visualizzare o meno il pulsante 'ANNULLA'
		form.setAbilitaAnnulla(checkAbilitaAnnulla(utente.getId(), domanda.getIdDomanda(), true));
		logger.debug("-- abilitaAnnulla ="+form.isAbilitaAnnulla());
		
		// ** Per visualizzare o meno il pulsante 'NUOVA DOMANDA' (per poter creare una nuova Domanda nel flusso)		
		form.setAbilitaCreaNuovaDomandaFlusso(checkAbilitaCreaNuovaDomandaFlusso(utente.getId(), domanda, true, form));
		logger.debug("-- abilitaCreaNuovaDomandaFlusso ="+form.isAbilitaCreaNuovaDomandaFlusso());
	}
    
    private void setDatiAziendaModifica(NuovaDomandaForm form, UtenteDTO utente, DomandaDto domanda) throws Exception{ 
    	logger.debug("BEGIN setDatiAziendaModifica");

    	/*
    	 * Dati azienda
    	 */
    	
    	if(domanda != null) {
	    	form.setIdAzienda(domanda.getIdSpedizioniere());
	    	form.setIdTipoAzienda(domanda.getIdTipoSpedizioniere());
	    	form.setCodFiscaleAz(domanda.getCuaa());
	    	form.setDenomAzienda(domanda.getDenomSpedizioniere());
	    	form.setNomeAzienda(domanda.getNomeSped());
	    	form.setCognomeAzienda(domanda.getCognomeSped());
	
	    	form.setIdComuneSedeLegale(domanda.getIdComuneSped()); 
	    	if(domanda.getIdComuneSped() != null)
	    		form.setIdProvinciaSedeLegale(domandeEJB.getIdProvinciaByIdComune(domanda.getIdComuneSped()));
	    	form.setCapSedeLegale(domanda.getCapSped());
	    	form.setIndirizzoSedeLegale(domanda.getIndirizzoSped());
	    	form.setNumTelefonoSedeLegale(domanda.getTelefonoSped());			
	    	form.setNumCellulareSedeLegale(domanda.getCellSped());
	    	form.setFaxSedeLegale(domanda.getFaxSped());
	    	form.setEmailSedeLegale(domanda.getEmailSped());
	    	form.setPecSedeLegale(domanda.getPecSped());
	    	form.setPartitaIva(domanda.getPartitaIva());
	    	form.setTipoSpedizioniereAltro(domanda.getTipoSpedizioniereAltro());
    	}
    	else{
    		logger.debug("-- non ci sono i dati dello spedizioniere");
    		form.setIdAzienda(null);
	    	form.setIdTipoAzienda(null);
	    	form.setCodFiscaleAz(null);
	    	form.setDenomAzienda(null);
	    	form.setNomeAzienda(null);
	    	form.setCognomeAzienda(null);
	
	    	form.setIdComuneSedeLegale(null); 	    	
	    	form.setIdProvinciaSedeLegale(null);
	    	form.setCapSedeLegale(null);
	    	form.setIndirizzoSedeLegale(null);
	    	form.setNumTelefonoSedeLegale(null);			
	    	form.setNumCellulareSedeLegale(null);
	    	form.setFaxSedeLegale(null);
	    	form.setEmailSedeLegale(null);
	    	form.setPecSedeLegale(null);
	    	form.setPartitaIva(null);
	    	form.setTipoSpedizioniereAltro(null);    		
    	}
    }
    
    private NuovaDomandaForm setDatiTipologiaDomanda(NuovaDomandaForm form, TipologiaDomandaDTO tipDomDto){
    	logger.debug("BEGIN setDatiTipologiaDomanda");
    	if(null != tipDomDto){
    		// Tipologie domanda
    		boolean tabImport = false;
    		boolean tabExport = false;
    		boolean tabPassaporto = false;
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
    		form.setTabImport(tabImport);
    		form.setTabExport(tabExport);
    		form.setTabPassaporto(tabPassaporto);
    	}
    	return form;
    }
    
    private boolean checkAbilitaInoltra(Long idUtente, Long idDomanda) throws BusinessException {
    	logger.debug("BEGIN checkAbilitaInoltra");
    	DomandaDto dettaglioDomanda = domandeEJB.getDettaglioAnagraficaAziendaByIdDomanda(idDomanda);	
		List<CarDStatoComunicazione> listaStatiSuccessivi = domandeEJB.getListaStatiDomandaSuccessivi(idUtente, dettaglioDomanda.getIdDomanda());

		for (CarDStatoComunicazione stato : listaStatiSuccessivi) {
			Long idStatoSuccessivo = CaronteConstants.STATO_COMUNICAZIONE_INOLTRATA;
			logger.debug("\n\n inoltraUtente sono dentro al loop stati successivi =" + stato.getIdStatoComunicazione());												
			
			if (idStatoSuccessivo.equals(stato.getIdStatoComunicazione())) {
				logger.debug("Nella lista stati successivi domanda c'è INOLTRA. return true");
				return true;
			}
		}
		logger.debug("Nella lista stati successivi domanda non c'è INOLTRA. return false");
		return false;
    }
    
   
    
    // GESTIONE INOLTRA DOMANDA RUOP
    /*
	 * 1) prendo il file scaricato nella pagina (stampa delle domanda firmata)
	 * 2) porto lo stato a INOLTRATA su CAR_T_DOMANDA
	 * 
	 * -- PARTE ANCORA IN SOSPESO, AL MOMENTO NON LA SVILUPPIAMO
	 * 3) inviata la mail alla pec aziendale (se previsto dal tipo di Domanda inoltrata)
	 *
	 **/
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = { "/inoltraDomandaRuop" })
	public String inoltraDomandaRuop(Model model, @ModelAttribute("stampaDomandaForm") StampaDomandaForm stampaDomandaForm, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm domandaForm, HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN inoltraDomandaRuop");
		try {
			Long idDomanda = domandaForm.getIdDomanda();
			logger.debug("-- idDomanda =" + idDomanda);
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();

			// Recupero il file allegato della stampa firmata
			logger.debug("-- Recupero il file allegato della stampa firmata");
			MultipartFile fileStampa = stampaDomandaForm.getFileStampa();

			/*
			 * Modifico lo stato della domanda in INOLTRATA
			 */
			if (!fileStampa.isEmpty()) {
				logger.debug("-- Modifico lo stato della comunicazione in INOLTRATA");
				byte[] pdfStampaDomanda = fileStampa.getBytes();
				logger.debug("-- INOLTRO la domanda");
				inoltra(idDomanda, domandaForm.getIdTipoComunicazione(), request, pdfStampaDomanda, fileStampa.getOriginalFilename());
				//dopo aver inoltrato e protocollato, possiamo inviare la mail all'utente
				CarDCostante costante = decodificheEJB.getCostante(CaronteConstants.COSTANTE_ATTIVA_PROTOCOLLO);
				if (costante != null && costante.getValoreCostante().equalsIgnoreCase("S")) {
					try {
						domandeEJB.inviaMailDomandaProtocollata(idDomanda, utente.getId());
					} catch (Exception e) {
						addSuccessMessage("La domanda è stata inoltrata e portocollata correttamente");
						addErrorMessage("Errore nell'invio della mail di protocollo");
						logger.error("Exception in inoltraDomandaRuop =" + e.getMessage(), e);
					}
				}
				logger.debug("-- Domanda RUOP INOLTRATA");
			} 
			else {
			  logger.error("Non e' stata allegata la stampa della Domanda firmata, non e' possibile inoltrare la Domanda");
			  addErrorMessage("Non e' stata allegata la stampa della Domanda firmata, non e' possibile inoltrare la Domanda");
				
			  return getRedirect("comunicazioni/modifica/"+domandaForm.getIdDomanda(), request);
			}  			

		} catch (Exception e) {
			addErrorMessage("Errore durante l'inoltra della domanda");
			logger.error("Exception in inoltraDomandaRuop =" + e.getMessage(), e);
		}
		return getRedirect("comunicazioni/elenco", request);
	}
      
	/*
	 * 
	 * ****** CAMBIO STATO IN 'INOLTRATA' - operazioni eseguite :
	 * 	 
	 * - chiamata al servizio di protocollo
	 * - salvo dati di protocollazione
	 * 
	 * 1) modificato lo stato a INOLTRATA su CAR_T_DOMANDA 
	 * 
	 * 2) inviata la mail a :
	 * -  mail personale
	 */
	private void inoltra(Long idDomanda, Long idTipoComunicazione, HttpServletRequest request, byte [] pdfStampaDomanda, String nomeFileStampa) throws Exception {
		logger.debug("BEGIN inoltra");
		try {				
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();
			
			// PROTOCOLLO: attivo tramite costante su DB
			CarDCostante costante = decodificheEJB.getCostante(CaronteConstants.COSTANTE_ATTIVA_PROTOCOLLO);
			if (costante != null && costante.getValoreCostante().equalsIgnoreCase("S")) {
				logger.debug("--- Si DEVE chiamare il servizio di PROTOCOLLO");
				ProtocolloOutputDto protocolloDto = protocolloEJB.protocollaDomandaInoltrata(idDomanda, pdfStampaDomanda, nomeFileStampa);						
				logger.debug("--- Salvare i dati di protocollazione");
				domandeEJB.salvaProtocolloDomanda(idDomanda, protocolloDto, utente);			
				
				//domandeEJB.inviaMailDomandaProtocollata(idDomanda, utente.getId());
			} else {
				logger.debug("-- Protocollazione non attiva, verificare costante DB");	
			}
			//salvo la stampa firmata su CAR_T_DATI_DOMANDA
			logger.debug("-- salvo la stampa firmata su CAR_T_DATI_DOMANDA");
			domandeEJB.salvaStampaDomandaFirmata(idDomanda, utente, pdfStampaDomanda, nomeFileStampa);				
			
			logger.debug("-- cambio stato ad INOLTRATA");
			avanza(idDomanda, CaronteConstants.STATO_COMUNICAZIONE_INOLTRATA,CaronteConstants.DESC_STATO_COMUNICAZIONE_INOLTRATA.toLowerCase(), null, null);
	
		} 
		catch (Exception ex) {
			logger.error("-- Exception in inoltra =" + ex.getMessage(), ex);
			throw ex;
		}	
	}
	
	
	/*
	 * Vengono aggiornati i dati in CAR_T_DOMANDA e inviata la mail all'utente
	 */
	private boolean avanza(Long idDomanda, Long idNuovoStato, String descStato, String motivazione, String codiceRuop) throws Exception {
		logger.debug("BEGIN avanza");
		String messaggioErrore = null;

		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();

			if (domandeEJB.avanzaStatoDomanda(idDomanda, idNuovoStato, utente.getId(),motivazione, codiceRuop)) {				
				addSuccessMessage("La domanda è stata " + descStato);				
			} 
			else {
				messaggioErrore = "La domanda non può essere " + descStato + " dall'utente corrente";
			}

		} 
		catch (BusinessException be) {
			logger.error("BusinessException in fase di avanzamento stato della domanda :" + be.getMessage());
			messaggioErrore = "Errore nell'avanzamento dello stato della domanda";
			throw new Exception(be.getMessage());
		}
		catch (Exception ex) {
			logger.error("Exception in fase di avanzamento stato della domanda :" + ex.getMessage());
			messaggioErrore = "Errore nell'avanzamento dello stato della domanda";
			throw ex;
		}

		if (messaggioErrore != null) {
			addErrorMessage(messaggioErrore);
		}

		return messaggioErrore == null;
	}
	
	
	// Dettaglio Domanda RUOP
	@PreAuthorize("hasRoleImpExp('READ', #request)")	
	@GetMapping(value = "/dettaglio/{id}")
	public String getDettaglioDomanda(@PathVariable Long id, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN getDettaglioDomanda");
		try {
			logger.debug("-- *** idDomanda = " + id);
			form.setIdDomanda(id);
			
			DomandaDto dettaglioAnagraficaAzienda = setDatiDettaglioDomanda(id, form);
			form.setIdStatoComunicazione(dettaglioAnagraficaAzienda.getIdStatoComunicazione());
			// Note : ora il numero protocollo arriva formattato corretto
			formattaProtocollo(dettaglioAnagraficaAzienda);
			if (!isRightSection(dettaglioAnagraficaAzienda, request)) {
				addErrorMessage("Domanda non visualizzabile nella sezione corrente");
				return getRedirect("comunicazioni/elenco", request);
			}
			
			model.addAttribute("dettaglioDomanda", dettaglioAnagraficaAzienda);
			model.addAttribute("listaModuli", decodificheEJB.getListaModuliComunicazione(id));
			logger.debug("*+*+moduli comunicazione= " + decodificheEJB.getListaModuliComunicazione(id));
		} 
		catch (Exception exc) {
			logger.error("-- Exception in getDettaglioDomanda ="+exc.getMessage());
			addErrorMessage("Errore nella ricerca delle comunicazioni");
			throw new BusinessException(exc.getMessage());
		}

		return "comunicazioni/dettaglio";
	}
	
	// Modale per il dettaglio del singolo centro aziendale
	@GetMapping(value = {"/centroaziendale/dettaglio/{idCentroAziendale}", "/centroaziendale/dettaglio/" } )
	public String dettaglioCentroAziendale(@PathVariable(required = false) Long idCentroAziendale, Model model,
			@ModelAttribute("datiCentroAziendaleForm") DatiCentroAziendaleForm form,
			@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm domandaForm, HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
		
		logger.debug("BEGIN dettaglioCentroAziendale");
		logger.debug(" -- idCentroAziendale ="+idCentroAziendale);
		logger.debug(" -- idDomanda ="+domandaForm.getIdDomanda());
		
		form.setIdCentroAziendale(idCentroAziendale);
		form.setIdDomanda(domandaForm.getIdDomanda());

		setDatiDettaglioCentroAziendale(idCentroAziendale == null ? null : domandeEJB.getDettaglioCentroAziendale(idCentroAziendale, form.getIdDomanda()), model, form, domandaForm.getIdTipoComunicazione());
		
		return getViewDettaglioAvanza("comunicazioni/centroaziendale", request);
	}
	
	
	// Popola la modale del dettaglio del Centro aziendale
	private void setDatiDettaglioCentroAziendale(CentroAziendaleDomandaDTO centroAziendale, Model model, DatiCentroAziendaleForm form, Long idTipoComunicazione) throws BusinessException {
		logger.debug("BEGIN setDatiDettaglioCentroAziendale");
		if (centroAziendale != null) {
			form.setIdCentroAziendale(centroAziendale.getIdCentroAziendale());
			form.setRagioneSociale(centroAziendale.getDenominazione());
			form.setTipologieList(centroAziendale.getTipologieList());
			form.setSitiProduzioneList(centroAziendale.getSitiProduzioneList());						
		}		
	}
	
	@ModelAttribute("datiCentroAziendaleForm")
	public DatiCentroAziendaleForm getDatiCentroAziendaleForm(HttpServletRequest request) {
		DatiCentroAziendaleForm form = new DatiCentroAziendaleForm();

		return form;
	}
	
	
	@PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = { "/avanza/{id}" })
	public String avanzaStatoDomanda(@PathVariable Long id, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {
		String messaggioErrore = null;

		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();

			Boolean isUtenteAbilitato = domandeEJB.isDomandaModificabile(utente.getId(), id);
			logger.debug("-- AVANZA isUtenteAbilitato = " + isUtenteAbilitato);
			if (isUtenteAbilitato == null) {
				messaggioErrore = "La domanda non può essere modificata dall'utente corrente";
			} 
			else {
				logger.debug("-- idDomanda = " + id);
				form.setIdDomanda(id);
				model.addAttribute("idDomanda", id);				
				
				// Ricerco i dati da visualizzare nella pagina avanza
				logger.debug("-- Ricerco i dati da visualizzare nella pagina avanza");
				DomandaDto dettaglioAnagraficaAzienda = setDatiDettaglioDomanda(id, form);
				form.setIdStatoComunicazione(dettaglioAnagraficaAzienda.getIdStatoComunicazione());
				// Note : ora il numero protocollo arriva formattato corretto				
				formattaProtocollo(dettaglioAnagraficaAzienda);
				model.addAttribute("dettaglioDomanda", dettaglioAnagraficaAzienda);

				
				// ** Per visualizzare i messaggi di parti mancanti all'INOLTRA
				form.setAbilitaInoltra(checkAbilitaInoltra(utente.getId(), id));
				logger.debug("-- abilitaInoltra ="+form.isAbilitaInoltra());
				
				
				// ** Per visualizzare o meno il pulsante 'CONVALIDA'
				form.setAbilitaConvalida(checkAbilitaConvalida(utente.getId(), id, true));
				logger.debug("-- abilitaConvalida ="+form.isAbilitaConvalida());
				
				// ** Per visualizzare o meno il pulsante 'RESPINGI'
				form.setAbilitaRespingi(checkAbilitaRespingi(utente.getId(), id, true));
				logger.debug("-- abilitaRespingi ="+form.isAbilitaRespingi());
				
				//** Per visualizzare o meno il pulsante 'ANNULLA'
				form.setAbilitaAnnulla(checkAbilitaAnnulla(utente.getId(), id, true));				
				logger.debug("-- abilitaAnnulla ="+form.isAbilitaAnnulla());
				
				model.addAttribute("abilitaInoltra",form.isAbilitaInoltra());
				model.addAttribute("abilitaConvalida",form.isAbilitaConvalida());
				model.addAttribute("abilitaRespingi",form.isAbilitaRespingi());
				model.addAttribute("abilitaAnnulla",form.isAbilitaAnnulla());							
				
				List<ModuloDTO> moduli = decodificheEJB.getListaModuliComunicazione(id);
				if (moduli != null) {
					logger.debug("moduli= " + moduli);
					model.addAttribute("listaModuli", moduli);
				}
				
				// Per visualizzare o meno il pulsante per creare la domanda successiva nel flusso
				// ** Per visualizzare o meno il pulsante 'NUOVA DOMANDA' (per poter creare una nuova Domanda nel flusso)		
				form.setAbilitaCreaNuovaDomandaFlusso(checkAbilitaCreaNuovaDomandaFlusso(utente.getId(), dettaglioAnagraficaAzienda, true, form));
				model.addAttribute("abilitaCreaNuovaDomandaFlusso",form.isAbilitaCreaNuovaDomandaFlusso());				
				
				form.setIdStatoComunicazione(dettaglioAnagraficaAzienda.getIdStatoComunicazione());

				model.addAttribute("isSuperUser", SecurityUtils.getUtenteLoggato().isSuperUser());				
			}
		} 
		catch (BusinessException be) {
			logger.error("-- BusinessException in avanzaStatoDomanda ="+be.getMessage());
			messaggioErrore = "Errore nella modifica della domanda";
		}
		if (messaggioErrore != null) {
			addErrorMessage(messaggioErrore);
			return getRedirect("comunicazioni/elenco", request);
		}

		return "comunicazioni/avanza";
	}
	
	
	private DomandaDto setDatiDettaglioDomanda(Long id, NuovaDomandaForm form) throws BusinessException {
		logger.debug("BEGIN setDatiDettaglioDomanda");
		
		// ------ Dettaglio Tab Anagrafica e Dettaglio Tab Azienda
		//La query reperisce anche i dati del tab gestione
		logger.debug("** -- Dettaglio Tab Anagrafica e Tab Azienda con idDomanda ="+id);
		DomandaDto dettaglioAnagraficaAzienda = domandeEJB.getDettaglioAnagraficaAziendaByIdDomanda(id);
		
		// Tab Azienda
		// Check Attività
		logger.debug("-- Ricerco i dati check Attività DB");
		String[] descrVociAttivitaTipologia = domandeEJB.getDescrVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP, 1L, form.getIdDomanda());
		dettaglioAnagraficaAzienda.setDescrVociAttivitaTipologia(descrVociAttivitaTipologia);

		// Tabella tipologia di Attività
		logger.debug("-- Ricerco i dati della tabella tipologie di Attività");
		List<TipologiaAttMaterialeDTO> tipologieAttMaterialidb = domandeEJB.getTipologieAttMateriale(form.getIdDomanda());
		dettaglioAnagraficaAzienda.setTipAttivitaTipologia(tipologieAttMaterialidb);

		//  Dati tipologia (servono per capire quali tab visualizzare)
		logger.debug("-- Controlli per capire quali tab visualizzare");
		UtenteDTO utente = SecurityUtils.getUtenteLoggato();
		TipologiaDomandaDTO tipologiaDomanda = domandeEJB.getDettTipologiaDomanda(id);
		setDatiTipoComunicazione(decodificheEJB.getTipoComunicazioneByPrimaryKey(dettaglioAnagraficaAzienda.getIdTipoComunicazione()), form);
		form = setDatiTipologiaDomanda(form, tipologiaDomanda);		


		// ----- Dettaglio Tab Centro aziendale	
		logger.debug("** -- Dettaglio Tab Centro aziendale");
		dettaglioAnagraficaAzienda.setCentriAziendaliList(domandeEJB.getCentriAziendaliByIdDomanda(id));			


		// ----- Dettaglio Tab Tipologia
		logger.debug("** -- Dettaglio Tab Tipologia");						
		// Check Tipologie Domanda
		logger.debug("-- Ricerco i dati check Tipologie Domanda DB");
		String[] elencoTipologieDomanda = domandeEJB.getDescrTipologieDomandaByIdDomanda(form.getIdDomanda());			
		dettaglioAnagraficaAzienda.setDescrVociTipologieDomande(elencoTipologieDomanda);


		// Se è una Domanda di Passaporto controllare se esiste la Domanda Ruop convalidata per lo stesso idSpedizioniere e settare nel form
		if(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
			logger.debug("--- IdSpedizioniere ="+dettaglioAnagraficaAzienda.getIdSpedizioniere());
			Long idDomandaRuopConvalidata = domandeEJB.getIdDomandaRuopConvalidata(dettaglioAnagraficaAzienda.getIdSpedizioniere());
			logger.debug("-- idDomandaRuopConvalidata ="+idDomandaRuopConvalidata);
			if(idDomandaRuopConvalidata != null){
				form.setDomandaRuopConvalidataPresente(true);
			}
			else{
				form.setDomandaRuopConvalidataPresente(false);
			}
			// Nel caso della Domanda Passsaporto : forzare la visualizzazione del Tab Passaporto
			form.setTabPassaporto(true);
		}

		// Controllo quali altri Tab verranno visualizzati nel dettaglio, in base alle tipologie domande selezionate
		CarTDatiDomanda datiDomanda = domandeEJB.getDatiAggiuntiviByIdDomanda(form.getIdDomanda());

		if(form.isTabImport()){
			// -------- Dettaglio Tab Import
			logger.debug("** -- Dettaglio Tab Import");
			// Tabella Tipologia produttiva import
			logger.debug("-- Ricerco i dati della tabella tipologie produttive Import");
			List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 1L);
			dettaglioAnagraficaAzienda.setTipologieProdImport(tipologieProdDb);

			// Radio zone protette + note
			logger.debug("-- Radio zone protette import + note");
			String[] descrVociRadioZoneProtetteImport = domandeEJB.getDescrVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 3L, form.getIdDomanda());
			dettaglioAnagraficaAzienda.setDescrVociZoneProtetteImport(descrVociRadioZoneProtetteImport);				
			if(null != datiDomanda){
				dettaglioAnagraficaAzienda.setImpDatoAggiuntivo(datiDomanda.getImpDatoAggiuntivo());			

				// Check continenti + note
				logger.debug("-- Check continenti import + note");
				String[] descrVociContinentiImport = domandeEJB.getDescrVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 2L, form.getIdDomanda());
				dettaglioAnagraficaAzienda.setDescrVociContinentiImport(descrVociContinentiImport);
				String noteStatoOrigine = domandeEJB.getNoteVoceUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 2L, form.getIdDomanda());
				dettaglioAnagraficaAzienda.setStatoOrigine(noteStatoOrigine);

				// Note finali
				logger.debug("-- Ricerco Note import");
				if(null != datiDomanda){					 
					dettaglioAnagraficaAzienda.setNoteImport(datiDomanda.getImpNote()); 
				}
				else{
					datiDomanda = domandeEJB.getDatiAggiuntiviByIdDomanda(form.getIdDomanda());
					if(datiDomanda != null){
						form.setNoteImport(datiDomanda.getImpNote());
					}  
				}
			}
		}

		if(form.isTabExport()){
			// -- Dettaglio Tab Export
			logger.debug("** -- Dettaglio Tab Export");
			// Check : recupero i dati di "Le esportazioni riguardano principalmente" + note
			logger.debug("-- Recupero i dati di : Le esportazioni riguardano principalmente + note");
			String[] descrVoceCheckExp = domandeEJB.getDescrVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 1L, form.getIdDomanda());
			dettaglioAnagraficaAzienda.setDescrVoceCheckExp(descrVoceCheckExp);
			if(null != datiDomanda){					 
				dettaglioAnagraficaAzienda.setExpDatoAggiuntivo(datiDomanda.getExpDatoAggiuntivo());   
			}
			else{
				datiDomanda = domandeEJB.getDatiAggiuntiviByIdDomanda(form.getIdDomanda());
				if(datiDomanda != null){
					dettaglioAnagraficaAzienda.setExpDatoAggiuntivo(datiDomanda.getExpDatoAggiuntivo());
				}  
			}				

			// Check : recupero i dati dei continenti + note
			logger.debug("-- Recupero i dati di Check : recupero i dati dei continenti + note");
			String[] descrVoceCheckContinentiExp = domandeEJB.getDescrVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 2L, form.getIdDomanda());
			dettaglioAnagraficaAzienda.setDescrVoceCheckContinentiExp(descrVoceCheckContinentiExp);
			String noteStatoOrigineExp = domandeEJB.getNoteVoceUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 2L, form.getIdDomanda());
			dettaglioAnagraficaAzienda.setStatoOrigineExp(noteStatoOrigineExp);				

			// Tabella : Informazioni relative ai tipi di merci di base, famiglie, generi o specie cui appartengono piante e i prodotti vegetali che l'Operatore professionale intende esportare.
			logger.debug("-- Recupero i dati della tabella Export");
			List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 3L);
			dettaglioAnagraficaAzienda.setTipologieProdExp(tipologieProdDb);

		}

		if(form.isTabPassaporto()){
			// -- Dettaglio Tab Passaporto
			logger.debug("** -- Dettaglio Tab Passaporto");
			logger.debug("-- Recupero i dati del reponsabile passaporto");
			CarTResponsabilePassaporto resp = domandeEJB.getRespPassaportoByIdDomanda(form.getIdDomanda());
			dettaglioAnagraficaAzienda.setRespPassaporto(resp);
			
			logger.debug("-- Recupero radio per il tipo di richiesta nuovo/aggiornamento");
			String[] descrVoceRadioTipoRichiesta = domandeEJB.getDescrVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 1L, form.getIdDomanda());
			dettaglioAnagraficaAzienda.setDescrVoceTipoRichiesta(descrVoceRadioTipoRichiesta);

			logger.debug("-- Recupero i dati di Informazioni relative a piante, prodotti vegetali e altri oggetti che devono essere movimentate con un Passaporto delle piante");
			String[] descrVoceRadioCheckedPass = domandeEJB.getDescrVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 2L, form.getIdDomanda());
			dettaglioAnagraficaAzienda.setDescrVoceRadioCheckedPass(descrVoceRadioCheckedPass);

			logger.debug("-- Recupero check Passaporto");
			String[] descrVoceCheckedPass = domandeEJB.getDescrVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 3L, form.getIdDomanda());
			dettaglioAnagraficaAzienda.setDescrVoceCheckedPass(descrVoceCheckedPass);

			logger.debug("-- Recupero Tipologie Passaporto");
			List<TipologiaProdSpecieDTO> tipologieProdPass = domandeEJB.getTipologieProdByIdDomanda(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 4L);
			dettaglioAnagraficaAzienda.setTipologieProdPass(tipologieProdPass);

			logger.debug("-- Recupero Zone protette Passaporto");
			List<ZonaProtettaSpecieDTO> zoneProtettePass = domandeEJB.getZoneProtetteSpecieByIdDomanda(form.getIdDomanda());
			dettaglioAnagraficaAzienda.setZoneProtettePass(zoneProtettePass);
			
			// recupero il testo delle voci dichiarazioni, sono solo 2
	    	List<CarDVoce> vociDichiarazioniList = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 5L);
	    	logger.debug("-- Prima dichiarazione = "+vociDichiarazioniList.get(0).getDescEstesa());
	    	logger.debug("-- Seconda dichiarazione = "+vociDichiarazioniList.get(1).getDescEstesa());
	    	dettaglioAnagraficaAzienda.setVoceDichiaraConoscenze(vociDichiarazioniList.get(0).getDescEstesa());
	    	dettaglioAnagraficaAzienda.setVoceDichiaraDisporreSistemi(vociDichiarazioniList.get(1).getDescEstesa());
	    	// recupero il valore delle voci
	    	Long[] idVoceDichiara = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 5L, form.getIdDomanda());
    		if(idVoceDichiara != null && idVoceDichiara.length > 0) {
    	    	String strIdVoce[] = new String[idVoceDichiara.length];
    	    	for (int i = 0; i < idVoceDichiara.length; i++) { 
    	    		if (idVoceDichiara[i] == CaronteConstants.ID_VOCE_DICHIARA_CONOSCENZE) {
    	    			dettaglioAnagraficaAzienda.setVoceDichiaraConoscenzeUtente("S");    	    			
    	    		} 
    	    		if (idVoceDichiara[i] == CaronteConstants.ID_VOCE_DICHIARA_DISPORRE_SISTEMI) {
    	    			dettaglioAnagraficaAzienda.setVoceDichiaraDisporreSistemiUtente("S");    	    			
    	    		} 
    	    	}
    	    }
    		// se non sono presenti sul DB le voci allora metto per default a N
    		if (dettaglioAnagraficaAzienda.getVoceDichiaraConoscenzeUtente() == null) {
    			dettaglioAnagraficaAzienda.setVoceDichiaraConoscenzeUtente("N");
    		}
    		if (dettaglioAnagraficaAzienda.getVoceDichiaraDisporreSistemiUtente() == null) {
    			dettaglioAnagraficaAzienda.setVoceDichiaraDisporreSistemiUtente("N");
    		}
	    	
	    	
	    	// recupero il testo delle voci dichiarazioni, sono solo 2
	    	List<CarDVoce> vociPianoRischiList = decodificheEJB.getVociByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 6L);
	    	dettaglioAnagraficaAzienda.setVocePianoRischi(vociPianoRischiList.get(0).getDescEstesa());
	    	// recupero il valore della voce si o no
	    	Long[] idVocePianoRischi = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 6L, form.getIdDomanda());
    		if(idVocePianoRischi != null && idVocePianoRischi.length > 0) {
    	    	String strIdVoce[] = new String[idVocePianoRischi.length];
    	    	for (int i = 0; i < idVocePianoRischi.length; i++) { 
    	    		if (idVocePianoRischi[i] == CaronteConstants.ID_VOCE_PIANO_RISCHI) {
    	    			dettaglioAnagraficaAzienda.setVocePianoRischiUtente("S");    	    			
    	    		} 
    	    	}
    		}
    		// se non sono presenti sul DB le voci allora metto per default a N
    		if (dettaglioAnagraficaAzienda.getVocePianoRischiUtente() == null) {
    			dettaglioAnagraficaAzienda.setVocePianoRischiUtente("N");
    		}
		}

		// -- Dettaglio Tab Allegati
		logger.debug("** -- Dettaglio Tab Allegati");
		logger.debug("-- Recupero gli Allegati");
		List<AllegatoDTO> listaAllegati = domandeEJB.getListaAllegatiDomanda(form.getIdDomanda());
		
	
		// Se la Domanda Ruop convalidata precedente aveva già compilato il Tab Passaporto l'Allegato Marca da Bollo non si deve vedere
		/*boolean tipologiaRichiestaPassaporto = false;
		if(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
			logger.debug("-- Gestione allegati obbligatori per la Domanda Passaporto");
			if(form.isDomandaRuopConvalidataPresente()){
				logger.debug("-- Caso Allegati domandaRuopConvalidataPresente");
				// Controllo se per la Domanda Ruop Convalidata era stato compilato il tab Passaporto				
				Long idDomandaRuopConvalidata = domandeEJB.getIdDomandaRuopConvalidata(dettaglioAnagraficaAzienda.getIdSpedizioniere());
				form.setIdDomandaPrecedente(idDomandaRuopConvalidata);
				logger.debug("-- Controllo se per la Domanda Ruop Convalidata era stato compilato il tab Passaporto per id_domanda ="+form.getIdDomandaPrecedente());
				tipologiaDomanda = domandeEJB.getDettTipologiaDomanda(form.getIdDomandaPrecedente());
				boolean passaportoCompilatoDomandaPrec = false; 
				if(tipologiaDomanda != null && tipologiaDomanda.getTipologieDomandaList() != null){
					for (Iterator<CarRDomandaTipologia> iterator = tipologiaDomanda.getTipologieDomandaList().iterator(); iterator.hasNext();) {
						CarRDomandaTipologia domandaTip = (CarRDomandaTipologia) iterator.next();
						Long idTipologia = domandaTip.getIdTipologia();    	    					
						if (idTipologia == CaronteConstants.ID_TIPOLOGIA_DOMANDA_PASS) {    	    				
							passaportoCompilatoDomandaPrec = true;
						}	
					}
				}
				logger.debug("-- passaportoCompilatoDomandaPrec ="+passaportoCompilatoDomandaPrec);
				// se NON era ancora stata compilata la parte del Passaporto il tipo allegato : 'Scansione Marca da Bollo da 16 euro' sarà obbligatorio
				if(!passaportoCompilatoDomandaPrec){
					tipologiaRichiestaPassaporto = true;
				}
			}			
			  //In questo caso non sappiamo cos'era stato compilato sulla Domanda Ruop importata o fuori Caronte, quindi controlliamo se l'utente ha checkato :
			  //'Prima richiesta(82) o Aggiornamento'(83) 			    		
			else{    			 
				logger.debug("-- Caso Allegati domandaRuopConvalidata NON Presente - DATI IMPORTATI");				
				// Ricerco se è stato selezionato 'Prima richiesta' o 'Aggiornamento'
				logger.debug("-- Ricerco se è stato selezionato 'Prima richiesta' o 'Aggiornamento'");
				Long[] idVoceRadioTipoRichiesta = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 1L, form.getIdDomanda());
				if(idVoceRadioTipoRichiesta != null && idVoceRadioTipoRichiesta.length>0){
					Long voceRadioSel = idVoceRadioTipoRichiesta[0];
					logger.debug("-- voceRadioSel ="+voceRadioSel);
					if(voceRadioSel.longValue() == CaronteConstants.PRIMA_RICHIESTA_PASSAPORTO.longValue()){
						tipologiaRichiestaPassaporto = true;
					}
				}
			}
		}	 		    	 
		logger.debug("-- tipologiaRichiestaPassaporto ="+tipologiaRichiestaPassaporto);
		// Se la Domanda Ruop convalidata precedente aveva già compilato il Tab Passaporto l'Allegato Marca da Bollo non si deve vedere
		if(!tipologiaRichiestaPassaporto){    		 
			for (Iterator<AllegatoDTO> iterator = listaAllegati.iterator(); iterator.hasNext();) {
				AllegatoDTO allegatoDTO = (AllegatoDTO) iterator.next();
				if(allegatoDTO.getIdTipoAllegato() == CaronteConstants.ID_TIPO_ALLEGATO_MARCA_DA_BOLLO){
					iterator.remove();
				}    			 			
			}    		 
		}	    	
		*/
		
		// Gestione allegato fuori regione
		if(form.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO ||
   			 (form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO && !form.isDomandaRuopConvalidataPresente())
   		 ){
			// Se la sede legale dell'azienda non è fuori Regione Lombardia, non deve avere l'allegato di tipo 6 (Autorizzazione Ruop rilasciata dalla Regione di competenza)
			boolean aziendaFuoriRegione = false;
			Long idAziendaDomanda = dettaglioAnagraficaAzienda.getIdSpedizioniere();
			logger.debug("-- idAziendaDomanda ="+idAziendaDomanda);    	 
			CarTSpedizioniere azienda = utenteEJB.getCarTSpedizioniere(idAziendaDomanda);
			if(azienda != null){
				CarDComune comuneAz = decodificheEJB.getComuneByPrimaryKey(azienda.getIdComune());
				if(comuneAz != null){
					CarDProvincia provAz = decodificheEJB.getProvinciaByIdProv(comuneAz.getIdProvincia()); 
					if(provAz != null){
						if(provAz.getIdRegione() != CaronteConstants.ID_REGIONE_LOMBARDIA){
							aziendaFuoriRegione = true;
						}
					}
				}
			}
			logger.debug("-- aziendaFuoriRegione ="+aziendaFuoriRegione);
			// ***********  L'azienda non è fuori regione, non deve vedere negli Allegati il tipo Allegato 6 (Autorizzazione Ruop rilasciata dalla regione di competenza)
			if(!aziendaFuoriRegione){
				for (Iterator<AllegatoDTO> iterator = listaAllegati.iterator(); iterator.hasNext();) {
					AllegatoDTO allegatoDTO = (AllegatoDTO) iterator.next();
					if(allegatoDTO.getIdTipoAllegato() == CaronteConstants.ID_TIPO_ALLEGATO_AUTORIZZAZIONE_RUOP){
						iterator.remove();
					}    			 			
				} 
			}
		}
		// In questo caso non si deve visualizzare l'Allegato per il fuori regione
		if(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO && form.isDomandaRuopConvalidataPresente()){
			for (Iterator<AllegatoDTO> iterator = listaAllegati.iterator(); iterator.hasNext();) {
				AllegatoDTO allegatoDTO = (AllegatoDTO) iterator.next();
				if(allegatoDTO.getIdTipoAllegato() == CaronteConstants.ID_TIPO_ALLEGATO_AUTORIZZAZIONE_RUOP){
					iterator.remove();
				}    			 			
			}    		 
		}
		
		dettaglioAnagraficaAzienda.setListaAllegati(listaAllegati);
		
		logger.debug("END setDatiDettaglioDomanda");
		return dettaglioAnagraficaAzienda;
	}
	
	
	// --- Gestione stati domanda
	private boolean checkAbilitaConvalida(Long idUtente, Long idDomanda, boolean controllaStato) throws BusinessException {
		boolean abilitaConvalida = !controllaStato;
		if (idDomanda == null) {
		  return false;
		}

		logger.debug("controllaStato checkAbilitaAutorizza=" + controllaStato);
		if (controllaStato) {
			List<CarDStatoComunicazione> listaStatiSuccessivi = domandeEJB.getListaStatiDomandaSuccessivi(idUtente, idDomanda);

			for (CarDStatoComunicazione stato : listaStatiSuccessivi) {
				Long idStatoSuccessivo = CaronteConstants.STATO_COMUNICAZIONE_CONVALIDATA;															
				
				if (idStatoSuccessivo.equals(stato.getIdStatoComunicazione())) {
					abilitaConvalida = true;
					logger.debug("Nella lista stati successivi domanda c'è CONVALIDA");
					break;
				}
			}
		}

		logger.debug("abilitaConvalida =" + abilitaConvalida);
		return abilitaConvalida;
	}
	
	private boolean checkAbilitaRespingi(Long idUtente, Long idDomanda, boolean controllaStato) throws BusinessException {
		boolean abilitaRespingi = !controllaStato;
		
		if (idDomanda == null) {
		  return false;
		}

		logger.debug("controllaStato in checkAbilitaRespingi=" + controllaStato);
		if (controllaStato) {
			List<CarDStatoComunicazione> listaStatiSuccessivi = domandeEJB.getListaStatiDomandaSuccessivi(idUtente, idDomanda);

			for (CarDStatoComunicazione stato : listaStatiSuccessivi) {
				Long idStatoSuccessivo = CaronteConstants.STATO_COMUNICAZIONE_RESPINTA;															
				
				if (idStatoSuccessivo.equals(stato.getIdStatoComunicazione())) {
					abilitaRespingi = true;
					logger.debug("Nella lista stati successivi domanda c'è RESPINTA");
					break;
				}
			}
		}
		logger.debug("abilitaRespingi =" + abilitaRespingi);

		return abilitaRespingi;
	}
	
	
	private boolean checkAbilitaAnnulla(Long idUtente, Long idDomanda, boolean controllaStato) throws BusinessException {
		boolean abilitaAnnulla = !controllaStato;
		
		if (idDomanda == null) {
		  return false;
		}

		logger.debug("controllaStato in checkAbilitaRespingi=" + controllaStato);
		if (controllaStato) {
			List<CarDStatoComunicazione> listaStatiSuccessivi = domandeEJB.getListaStatiDomandaSuccessivi(idUtente, idDomanda);

			for (CarDStatoComunicazione stato : listaStatiSuccessivi) {
				Long idStatoSuccessivo = CaronteConstants.STATO_COMUNICAZIONE_ANNULLATA;															
				
				if (idStatoSuccessivo.equals(stato.getIdStatoComunicazione())) {
					abilitaAnnulla = true;
					logger.debug("Nella lista stati successivi domanda c'è ANNULLATA");
					break;
				}
			}
		}
		logger.debug("abilitaAnnulla =" + abilitaAnnulla);

		return abilitaAnnulla;
	}
	
	
	/*
	 * 
	 * ****** CAMBIO STATO IN 'RESPINTA' - operazioni eseguite :
	 * 	 
	 * 
	 * 1) portato lo stato a RESPINTA su CAR_T_DOMANDA 
	 * 
	 * 2) se previsto dal campo in car_d_costante, inviata la mail a :
	 * -  mail personale
	 * 
	 */
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = "/respingi/{id}")
	public String respingiDomanda(@PathVariable Long id,@RequestParam(value = "motivazione", required = false) String motivazione, HttpServletRequest request) {
		logger.debug("BEGIN respingiComunicazione");
	    try {
	    	avanza(id, CaronteConstants.STATO_COMUNICAZIONE_RESPINTA,CaronteConstants.DESC_STATO_COMUNICAZIONE_RESPINTA.toLowerCase(), motivazione, null);	    		    	
		}
	    catch (Exception e) {
	    	addErrorMessage("Errore durante il respingi della domanda");
			logger.error("Exception in respingiComunicazione =" + e.getMessage(), e);
		}
	    logger.debug("END respingiComunicazione");
	    return getRedirect("comunicazioni/elenco", request);
	}
	
	/*
	 * 
	 * ****** CAMBIO STATO IN 'ANNULLATA' - operazioni eseguite :
	 * 	 
	 * 
	 * 1) portato lo stato a ANNULLATA su CAR_T_DOMANDA 
	 * 
	 * 2) se previsto dal campo in car_d_costante, inviata la mail a :
	 * -  mail personale
	 * 
	 */	
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@PostMapping(value = "/annulla/{id}")
	public String annullaDomanda(@PathVariable Long id,@RequestParam(value = "motivazione", required = false) String motivazione, HttpServletRequest request) {
		logger.debug("BEGIN annullaDomanda");
	    try {
	    	avanza(id, CaronteConstants.STATO_COMUNICAZIONE_ANNULLATA,CaronteConstants.DESC_STATO_COMUNICAZIONE_ANNULLATA.toLowerCase(), motivazione, null);	    		    	
		}
	    catch (Exception e) {
	    	addErrorMessage("Errore durante l'annulla della domanda");
			logger.error("Exception in annullaDomanda =" + e.getMessage(), e);
		}
	    logger.debug("END annullaDomanda");
	    return getRedirect("comunicazioni/elenco", request);
	}
	
	/*
	 * 
	 * ****** CAMBIO STATO IN 'CONVALIDATA' - operazioni eseguite :
	 * 	
	 * 1) Devono essere stati compilati i campi : 
	 *  codice RUOP, data di registrazione al RUOP 
	 * 
	 * Se sono stati compilati :
	 * 2) portato lo stato a CONVALIDATA su CAR_T_DOMANDA 
	 * 
	 * 3) quando la domanda è CONVALIDATA, ABILITO L'UTENTE A LAVORARE SULLE SEZIONI selezionate in Tipologia domanda 
	 * 
	 * 3) se previsto dal campo in car_d_costante, inviata la mail a :
	 * -  mail personale (mandare una mail con testo diverso dagli altri due casi)
	 * 
	 */	
	@PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = "/convalida/{id}")
	public String convalidaDomanda(@PathVariable Long id, HttpServletRequest request) {
		logger.debug("BEGIN convalidaDomanda");
		String messaggioErrore = null;
	    try {
	    	// Controllo se sono stati compilati i campi : codice RUOP, data di registrazione al RUOP
	    	logger.debug("--- Controllo se sono stati compilati i campi : codice RUOP e data di registrazione al RUOP");
	    	DomandaDto dto = domandeEJB.getDettaglioGestioneByIdDomanda(id);
	    	
	    	if(((dto.getCodiceRuop() != null && dto.getCodiceRuop().trim() != "") && 
	    		dto.getDataRegistrazioneRuop() != null
	    		) || 
	    		domandeEJB.getDettaglioAnagraficaByIdDomanda(id).getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP){	    	
	    	  String codiceRUOP = dto.getCodiceRuop();
	    	  avanza(id, CaronteConstants.STATO_COMUNICAZIONE_CONVALIDATA,CaronteConstants.DESC_STATO_COMUNICAZIONE_CONVALIDATA.toLowerCase(), null, codiceRUOP);
	    	}
	    	else{
	    		logger.debug("-- Non sono stati compilati i campi nel Tab Gestione");
	    		messaggioErrore = "Per poter convalidare la domanda : "+dto.getCodDomanda()+" è necessario indicare : Codice RUOP e Data di registrazione RUOP";
	    		addErrorMessage(messaggioErrore);
	    	}
		}
	    catch (Exception e) {
	    	addErrorMessage("Errore durante il convalida della domanda");
			logger.error("Exception in convalidaDomanda =" + e.getMessage(), e);
		}	    
	    logger.debug("END convalidaDomanda");
	    return getRedirect("comunicazioni/elenco", request);
	}
	
	
	  // Nuova domanda - Tab Dati gestione
	  @PreAuthorize("hasRoleImpExp('WRITE', #request)")  
	  @GetMapping(value = { "/gestione/modifica" })
	  public String datiGestione(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {	
	    try {
	    	//TODO da gestire i campi codice fitok e idispettore nella nuova maniera
		  UtenteDTO utente = SecurityUtils.getUtenteLoggato();
		  
		  
		  /*
		   *  Versione del layout:
		   *  le domande in stato Convalidato o Respinto e con dataAggiornamento < CAR_D_VERSIONE_DOMANDA.DATA_INIZIO_VERSIONE : devono vedere il vecchio layout
		   */
		  if(form.getIdDomanda() != null){
			  Long idVersioneDomanda = domandeEJB.checkVersioneDomanda(form.getIdDomanda());
			  logger.debug("-- idVersioneDomanda = " + idVersioneDomanda);
			  form.setIdVersioneDomanda(idVersioneDomanda);
			  
			  // Se siamo nel caso di versioni > 1 : carico i dati dei centri aziendali presenti in domanda
			  if(form.getIdVersioneDomanda() > 1){
				List<CentroAziendaleDomandaDTO> centriAzDomandaList = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
				if(centriAzDomandaList != null && centriAzDomandaList.size() > 0){
					model.addAttribute("centriAziendaliDomanda",centriAzDomandaList);
			  		  
					// Seleziono il radio del primo centro aziendale presente su db se non ne è già stato selezionato uno (Note : utilizzo lo stesso campo utilizzato già nel Tab Centro Aziendale)
					logger.debug("-- IdCentroAziendaleSel ="+form.getIdCentroAziendaleGestSel());
					if(form.getIdCentroAziendaleGestSel() == null){
					  form.setIdCentroAziendaleGestSel(centriAzDomandaList.get(0).getIdCentroAziendale());
					  logger.debug("-- IdCentroAziendale da selezionare nel Tab Gestione ="+form.getIdCentroAziendaleGestSel());
					}
				}
			  }
			  model.addAttribute("listaTipologiaPassaporto", decodificheEJB.getListaTipologiaPassaporto());
		  }
		  
		  // Carico i dati delle combo sul model
		  setDatiGestioneModel(model, form, utente);
		  if(form.getIdDomanda() != null){
				// Carico i dati relativi alle tipologie attività e materiali salvati sul db
			    List<TipologiaAttMaterialeDTO> tipologieAttMaterialidb = domandeEJB.getTipologieAttMateriale(form.getIdDomanda());
			    model.addAttribute("tipologieAttDb", tipologieAttMaterialidb); 
			    if (tipologieAttMaterialidb.size() > 0 ){
			    	// tutti i record hanno lo stesso dato o S o N
			    	if(tipologieAttMaterialidb.get(0).getRichiestaPassaporto() != null){
			    		form.setRichiestaPassaporto(tipologieAttMaterialidb.get(0).getRichiestaPassaporto().equals("S") ? true : false);
			    	}	
			    	else{
			    		form.setRichiestaPassaporto(false);
			    	}	
			    }
			  
		  
		  
		  }
		  if (form.getIdDomanda() != null ){
	        	// tipologie produttive
	      		List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 1L);
	      		model.addAttribute("tipologieProdImportDb", tipologieProdDb);     	
		}
		  if (form.getIdDomanda() != null ){
	        	// tipologie produttive
	      		List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 3L);
	      		model.addAttribute("tipologieProdExportDb", tipologieProdDb); 
	    	}
		}
		catch (BusinessException exc) {
		  addErrorMessage("Errore in fase di caricamento dati gestione");
		}
	   	return getViewNuovaModifica("comunicazioni/gestione", request);   
	  } 
	  
	  
	  private void setDatiGestioneModel(Model model, NuovaDomandaForm form, UtenteDTO utente) throws BusinessException { 	   

		  Long idUtenteDomanda = form.getIdUtente();
		  logger.debug("-- idUtenteDomanda ="+idUtenteDomanda);
		  
		  
		  logger.debug("-- idVersioneDomanda = " + form.getIdVersioneDomanda());		  		  
		  
		  // Se siamo nel caso di versioni > 1 : carico i dati del centro aziendale selezionato
		  if(form.getIdVersioneDomanda() > 1){
			  /*
			   * I campi 'codice ruop', 'numero protocollo',  'data protocollo' , 'data registrazione ruop', 'note' e 'Tariffa' sono campi legati alla domanda
 				 I campi : 'Ispettore o Agente', 'Codice fitok' sono legati ai centri aziendali 
			   */			  
			  DomandaDto domanda = domandeEJB.getDettaglioGestioneByIdDomanda(form.getIdDomanda());
			//--  Campi legati alla Domanda ed al Centro aziendale
			  
			  
			  //--  Campi legati alla Domanda
			  // Note : ora il numero protocollo arriva formattato corretto
			  formattaProtocollo(domanda);
			  form.setNumeroProtocollo(domanda.getNumeroProtocollo());
			  form.setDataProtocollo(domanda.getDataProtocollo());
			  form.setCodiceFitok(domanda.getCodiceFitok());
			  form.setTariffa(domanda.getTariffa());
			  form.setNote(domanda.getNote());
			  form.setCodiceRuop(domanda.getCodiceRuop());
			  form.setDataRegistrazioneRuop(domanda.getDataRegistrazioneRuop());
			  form.setIdSpedizioniere(domanda.getIdSpedizioniere());			  			  
			  form.setIdTipologiaPassaporto(domanda.getIdTipologiaPassaporto());
			 
			  model.addAttribute("listaIspettori", decodificheEJB.getListaIspettori());
		  }
		  // Caso versione = 1 : dati tutti legati alla Domanda
		  else{
			  // QUERY DOVE PRENDO I DATI NECESSARI E LI USO PER POPOLARE I CAMPI
			  DomandaDto domanda = domandeEJB.getDettaglioGestioneByIdDomanda(form.getIdDomanda());
			  // Note : ora il numero protocollo arriva formattato corretto
			  formattaProtocollo(domanda);
			  form.setNumeroProtocollo(domanda.getNumeroProtocollo());
			  form.setDataProtocollo(domanda.getDataProtocollo());
			  form.setCodiceFitok(domanda.getCodiceFitok());
			  form.setTariffa(domanda.getTariffa());
			  form.setNote(domanda.getNote());
			  form.setCodiceRuop(domanda.getCodiceRuop());
			  form.setDataRegistrazioneRuop(domanda.getDataRegistrazioneRuop());
			  form.setIdSpedizioniere(domanda.getIdSpedizioniere());
			  form.setIdIspettore(domanda.getIdIspettore());
			  form.setCodiceFitok(domanda.getCodiceFitok());
			  form.setIdTipologiaPassaporto(domanda.getIdTipologiaPassaporto());
			 
			  model.addAttribute("listaIspettori", decodificheEJB.getListaIspettori());
		  }
		  
		  if(form.isAbilitaInoltra()){
			  CarDCostante testo = decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_TESTO_INOLTRA_PARTE_1);
			  model.addAttribute("testoInoltra", testo.getValoreCostante());
			  testo = decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_TESTO_INOLTRA_PARTE_2);
			  model.addAttribute("testoInoltra2", testo.getValoreCostante());
			  testo = decodificheEJB.getCostante(CaronteConstants.CODICE_COSTANTE_TESTO_INOLTRA_PARTE_3);
			  model.addAttribute("testoInoltra3", testo.getValoreCostante());
			 
		  }
			  
		 
	  }
	  
	// Salva Dati Gestione
	  @PreAuthorize("hasRoleImpExp('WRITE', #request)")
	  @PostMapping(value = {"/gestione/modifica" })
	  public String salvaDatiGestione(@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {
		logger.info("BEGIN salvaDatiGestione");
		
		 logger.debug("--- CHIAMATA ="+form.getChiamata());
			
		 // CASO DI RADIO CENTRO AZIENDALE SELEZIONATO NEL TAB GESTIONE
		 if(form.getChiamata() != null && form.getChiamata().equals("caricoDatiGestioneCentroAziendale")){
			 logger.debug("-- CASO DI RADIO CENTRO AZIENDALE SELEZIONATO NEL TAB GESTIONE");
			 caricoDatiCentroAziendaleGestione(form, model);
			 // pulisco il campo
			 form.setChiamata(null);
		 }
		 else{  
		
			  // Nel caso di Domanda Passaporto i campi codice Ruop e data Registrazione Ruop sono sempre valorizzati, non dobbiamo fare validazioni su questi campi
			  if(form.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
				  logger.debug("-- Validazione dei dati gestione");
				  validator.validateDatiGestione(form, result);
			  }
			  if (result.getErrorCount() == 0) {
			      logger.debug("-- Non ci sono errori di validazione sui dati gestione, posso procedere con il salvataggio");	
			      try {
			        Long idUtente = SecurityUtils.getUtenteLoggato().getId();
			        
			        	logger.debug("-- ***** AGGIORNO i dati  (TAB DATI GESTIONE) *****");
			        	logger.debug("-- idDomanda da aggiornare ="+form.getIdDomanda());
			        	
			        	form.setIdSpedizioniere(domandeEJB.getDettaglioAziendaByIdDomanda(form.getIdDomanda()).getIdSpedizioniere());     
			        	logger.debug("--+-- idSpedizioniere da aggiornare ="+form.getIdSpedizioniere());
			        	
			        	//invio mail se si inserisce o si modifica un ispettore
			        	//TODO controllare che questo pezzo di codice funzioni con la nuova gestione dell'ispettore
			        	DomandaDto domandaOld = domandeEJB.getDettaglioAnagraficaAziendaByIdDomanda(form.getIdDomanda());  
			        	logger.debug("--+-- idDomanda ="+form.getIdDomanda());
			        	if (form.getIdIspettore() != null) {
				        	if(domandaOld.getIdIspettore() == null || domandaOld.getIdIspettore() != form.getIdIspettore()) {
				        		domandeEJB.inviaMailGestione(form);
				        	}
			        	}	
			        	domandeEJB.aggiornaDatiGestione(form,idUtente);
			        	logger.debug("dati salvati correttamente(gestione)");
			            
				   } catch (BusinessException be) {
						addErrorMessage("Errore nel salvataggio dei dati gestione");
				   }
			      
			  } 
			  else {
				 addErrorMessage("Compilare tutti i campi obbligatori e indicarli nel formato corretto"); 
			     logger.debug("Non sono stati superati i controlli di validazione dati gestione"); 	
			  }
		  
		 }
		  
		  setDatiGestioneModel(model, form, SecurityUtils.getUtenteLoggato());
		  return getRedirect(getViewNuovaModifica("comunicazioni/gestione", request), request);
		//return getViewNuovaModifica("comunicazioni/gestione", request);		  
	  }
	  
	  // Ricerco i dati del centro aziendale selezionato con il radio
	   private void caricoDatiCentroAziendaleGestione(NuovaDomandaForm form, Model model) throws BusinessException{
		   if(null != form.getIdCentroAziendaleGestSel()){
			   Long idCentroAzSel = form.getIdCentroAziendaleGestSel();
			   logger.debug("--- Cerco dati tipologie produttive e siti produzione per l'idCentroAziendale ="+idCentroAzSel);
			   // Carico i dati dell'ispettore e agente
			   CarRDomandaCentroAz domCentroAz = domandeEJB.getDomandaCentroAzByIdDomandaIdCentroAz(form.getIdDomanda(), idCentroAzSel);
			   form.setIdIspettore(domCentroAz.getIdIspettore());
			   form.setCodiceFitok(domCentroAz.getCodiceFitok());
		   }
		   

		  /*
		   List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
		   model.addAttribute("centriAziendaliDb",centriAziendaliDb);
		  */
	   }
	 
	  
	  
	@ModelAttribute("modaliForm")
	public ModaliForm getModaliForm(HttpServletRequest request) {
		ModaliForm form = new ModaliForm();

		return form;
	}
	
	private void puliscoAttivitaTabAzienda(ModaliForm form){
		form.setIdAttivitaMaterialeUtente(null);
		form.setIdTipoAttivitaMat(null);
		form.setIdMaterialeList(null);
		form.setIdMateriale(null);
		form.setNote(null);
	}

	@GetMapping(value = {"/azienda/editAttivitaMateriali", "/azienda/editAttivitaMateriali/{idAttivitaMaterialeUtente}"})
	public String modificaAttivitaMateriali(@PathVariable(required = false) Long idAttivitaMaterialeUtente, Model model, 
			@ModelAttribute("modaliForm") ModaliForm form, 
			@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm domandaForm,
			HttpServletRequest request, HttpServletResponse response) throws BusinessException {
		UtenteDTO utente = SecurityUtils.getUtenteLoggato();
		logger.debug("\n\n GEt mapping domandaForm.getIdDomanda()  = "+domandaForm.getIdDomanda()); 
		
		//popolo le attività
		List<CarDTipoAttivita> tipoAttivitaList = decodificheEJB.getTipoAttivita();
		model.addAttribute("listaTipologieAttivita", tipoAttivitaList);
		if (idAttivitaMaterialeUtente == null ) {
			puliscoAttivitaTabAzienda(form);
		}
		else {
			setDatiAttivitaMateriali(domandeEJB.getAttivitaMaterialeById(idAttivitaMaterialeUtente), model, form, domandaForm.getIdDomanda());
		}
		return "comunicazioni/azienda/editAttivitaMateriali";
	}
	
	private void setDatiAttivitaMateriali(TipologiaAttMaterialeDTO attMat, Model model, ModaliForm form, Long idDomanda) throws BusinessException {
		if(attMat != null) {
			if (attMat.getIdTipologia() != null) {
				form.setIdTipoAttivitaMat(attMat.getIdTipologia());
			}
			if (attMat.getIdMateriale() != null) {
				form.setIdMateriale(attMat.getIdMateriale());
			}
			if (attMat.getNote() != null) {
				form.setNote(attMat.getNote());
			}			
			List<CarDMateriale> materialiList =  decodificheEJB.getListaMaterialiByIdTipoAttivita(attMat.getIdTipologia());
			model.addAttribute("listaMateriali", materialiList);		
		} else {
			//popolo le attività
			List<CarDTipoAttivita> tipoAttivitaList = decodificheEJB.getTipoAttivita();
			model.addAttribute("listaTipologieAttivita", tipoAttivitaList);
		}
	}
	
	private void aggiornaTipologia(NuovaDomandaForm form, Long idUtente) throws BusinessException {
		//Aggiorno le tipologie  e abilito i tab da visualizzare
		if (form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP) {
			//recupero le tipologie dalle attivita' che sono state aggiunte
			String[] idtipologiaArr = domandeEJB.getTipologieByIdDomanda(form.getIdDomanda());
			form.setIdTipologia(idtipologiaArr);	
			// salvo le tipologie attività in car_r_domanda_tipologia
			domandeEJB.aggiornaTipologia(form, idUtente);        		
	
			boolean tabImport = false;
			boolean tabExport = false;
			boolean tabPassaporto = false;
			for (int i = 0; i < idtipologiaArr.length; i++) {
				Long idTipologia = Long.parseLong(idtipologiaArr[i]);
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
			form.setTabImport(tabImport);
			form.setTabExport(tabExport);
			form.setTabPassaporto(tabPassaporto);        
	   	}
	}

	@PostMapping(value = "/azienda/editAttivitaMateriali")
	public String salvaAttivitaMateriali(Model model, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm domandaForm,
			@ModelAttribute("modaliForm") ModaliForm form, BindingResult result,  
			HttpServletRequest request,  HttpServletResponse response) throws BusinessException {
		UtenteDTO utente = SecurityUtils.getUtenteLoggato();
		
		 // Controllo che sia stata inserita la domanda (dati precendenti)
	    if(domandaForm.getIdDomanda() == null){
	    	logger.debug("-- Bloccare l'utente, deve prima inserire i dati della domanda");
	    	addErrorMessage("Attenzione : inserire prima i dati della domanda");
	    	return "comunicazioni/azienda/editAttivitaMateriali";
	    }
	    logger.error("Se siamo in modifica abbiamo idAttMaterialeUtente valorizzato: "+ form.getIdAttivitaMaterialeUtente());
		validator.validateAttivitaTabAzienda(form, result);
				 
		// Se la Tipologia attività prevede anche i materiali, controllare che abbiamo selezionato anche i materiali
		if (result.getErrorCount() == 0 && form.getIdTipoAttivitaMat() != null){
			try {
				if (!checkModificaDomanda(utente.getId(), domandaForm)) {
					logger.error("Utente non abilitato a modificare la comunicazione "
							+ domandaForm.getIdDomanda());
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return null;
				}	
				// mi salvo idDomanda nel nuovo form della modale per il salvataggio su DB
				Long idDomanda = domandaForm.getIdDomanda();
			    form.setIdDomanda(idDomanda);
				
			    List<CarDMateriale> listaMateriale = decodificheEJB.getListaMaterialiByIdTipoAttivita(form.getIdTipoAttivitaMat());
			    if(listaMateriale != null && listaMateriale.size()>0){
			    	logger.debug("-- Sono previsti materiali per idTipologiaAttivita selezionata, controllo che sia stato selezionato almeno un materiale");
			    	validator.validateMaterialeTabAzienda(form, result);
			    }
			    
				logger.debug("\n\n ERRORI result.getErrorCount()"+result.getErrorCount());
				if (result.getErrorCount() == 0){
				    logger.debug("-- Non ci sono errori di validazione, proseguo con il salvataggio sul db delle attivita/materiali");	  
				 	domandeEJB.salvaTipologiaAttMateriale(form, utente);
				 	puliscoAttivitaTabAzienda(form);
				 	// abilito eventuali tab a seconda della tipologia attività selezionata
				 	aggiornaTipologia(domandaForm, utente.getId());
				 	return null;
				} else{
					logger.debug("-- Ci sono errori di validazione, non posso proseguire con il salvataggio sul db delle attivita/materiali");
				}				
				
			} catch (BusinessException be) {
				logger.error("Errore nel salvataggio dei dati delle attivita e materiali"
						+ form.getIdDomanda(), be);
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return null;
			}	
				    	

	    }
		
		setDatiAttivitaMateriali(null, model, form, domandaForm.getIdDomanda());
		return "comunicazioni/azienda/editAttivitaMateriali";
	}
	

	  /*
	   * Per la creazione nuova domanda all'interno di un flusso
	   */
	  @PreAuthorize("hasRoleImpExp('READ', #request)")
	  @GetMapping(value = "/crea/{id}")
	  public String creaNuovaDomandaFlusso(@PathVariable Long id, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN creaNuovaDomandaFlusso");  
	    // Note : aggiunga questa forzatura perchè se si arriva dall'avanza, il campo è valorizzato e non va bene
	    form.setIdDomanda(null);
	    
	    logger.debug("-- idDomanda precedente =" + id);
	    logger.debug("-- ** Recupero l'idTipoComunicazione della domanda precedente ");
	    DomandaDto domandaPrec = domandeEJB.getDettaglioAnagraficaByIdDomanda(id);
	    Long idTipoDomandaPrec = null;
	    if (domandaPrec != null) {
	    	idTipoDomandaPrec = domandaPrec.getIdTipoComunicazione();
	      logger.debug("-- idTipoDomandaPrec =" + idTipoDomandaPrec);
	    }

	    // setto nel form idTipoComunicazioneSuccessiva
	    logger.debug("-- ** Recupero idTipoComunicazioneSuccessiva **--");
	    CarRTipoFlussoCom tipoFlussoCom = domandeEJB.getTipoDomandaSuccessiva(id);

	    Long idTipoComunicazioneSucc = null;
	    Long idStatoComunicazioneSucc = null;
	    if (tipoFlussoCom != null) {
	      idTipoComunicazioneSucc = tipoFlussoCom.getIdTipoComunicazione();
	      logger.debug("---- idTipoComunicazioneSucc =" + idTipoComunicazioneSucc);
	      idStatoComunicazioneSucc = tipoFlussoCom.getIdStatoComunicazione();

	      logger.debug("---- idStatoComunicazioneSucc =" + idStatoComunicazioneSucc);
	    }
	    logger.debug("-- setto idTipoComunicazione =" + idTipoComunicazioneSucc);
	    form.setIdTipoComunicazione(idTipoComunicazioneSucc);

	    // setto nel form IdComunicazionePrecedente
	    logger.debug("-- idDomandaPrecedente nel form =" + id);
	    form.setIdDomandaPrecedente(id);

	    // precaricare nel form i dati anagrafici com quelli della domanda precedente
	    logger.debug("-- ** Precarico nel form i dati anagrafici com quelli della comunicazione precedente ** --");
	    DomandaDto dettDomanda = domandeEJB.getDettaglioAnagraficaByIdDomanda(id);	    	    
	    if (dettDomanda != null) {
	      /*
	       * Setto dati anagrafici
	       */
	      form.setCognome(dettDomanda.getCognome());
	      form.setNome(dettDomanda.getNome());
	      form.setCodFiscale(dettDomanda.getCodiceFiscale());
	      form.setDataNascita(dettDomanda.getDataNascita());

	      // Dati nascita
	      form.setDataNascita(dettDomanda.getDataNascita());
	      if (dettDomanda.getIdComuneNascita() == null) {
	        form.setNascitaEstera(true);
	        form.setDenomComuneEstNascita(dettDomanda.getDenomComuneEstNascita());
	        form.setIdNazioneEstNascita(dettDomanda.getIdNazioneEstNascita());
	      } else {
	        form.setNascitaEstera(false);
	        form.setIdProvinciaNascita(dettDomanda.getIdProvNascita());
	        form.setIdComuneNascita(dettDomanda.getIdComuneNascita());
	      }

	      // Dati residenza
	      if (dettDomanda.getIdComuneResidenza() == null) {
	        form.setResidenzaEstera(true);
	        form.setDenomComuneEstResid(dettDomanda.getDenomComuneEstResid());
	        form.setIdNazioneEstResid(dettDomanda.getIdNazioneEstResid());
	      } else {
	        form.setResidenzaEstera(false);
	        form.setIdProvinciaResidenza(dettDomanda.getIdProvResidenza());
	        form.setIdComuneResidenza(dettDomanda.getIdComuneResidenza());
	      }

	      form.setIndirizzo(dettDomanda.getIndirizzo());
	      form.setCap(dettDomanda.getCap());
	      form.setNumTelefono(dettDomanda.getTelefono());
	      form.setCellulare(dettDomanda.getCellulare());
	      form.setEmail(dettDomanda.getEmail());
	    }
	    
	    
	    

	    // setto nel form id stato : in bozza (prendendolo da configurazione DB)
	    logger.debug("-- setto nel form id stato : in bozza (prendendolo da configurazione DB) =" + idStatoComunicazioneSucc);
	    form.setIdStatoComunicazione(idStatoComunicazioneSucc);

	    setDatiTipoComunicazione(decodificheEJB.getTipoComunicazioneByPrimaryKey(idTipoComunicazioneSucc), form);
	    return datiDomanda(form, model, request);
	  }
	  
	  private void duplicaDatiTabAzienda(NuovaDomandaForm nuovaDomandaForm, Model model, UtenteDTO utente, Long idDomanda) throws Exception{
		  logger.debug("BEGIN duplicaDatiTabAzienda");
		  try {
			  // *** --- Dati Tab Azienda --- ***
			  Long idDomandaPrec = nuovaDomandaForm.getIdDomandaPrecedente();
			  logger.debug("*** --- Dati Tab Azienda --- ***");
			  DomandaDto domandaAzienda = domandeEJB.getDettaglioAziendaByIdDomanda(idDomandaPrec);				  
			  if(domandaAzienda != null){
				  // -- Setto nel form i dati dell'Azienda
				  logger.debug("-- Setto nel form i dati dell'Azienda --");
				  setDatiAziendaModifica(nuovaDomandaForm, utente, domandaAzienda);				  

				  // -- Salvo i dati dell'Azienda sul db--
				  logger.debug("-- Salvo i dati dell'Azienda sul db --");
				  domandeEJB.aggiornaDatiAzienda(nuovaDomandaForm,utente.getId());

				  // Gestione Tipologia attività e Tipologia Materiali (salvataggio dati in car_r_att_materiale_utente)
				  List<TipologiaAttMaterialeDTO> tipologieAttMaterialidbPrec = domandeEJB.getTipologieAttMateriale(idDomandaPrec);
				  if(tipologieAttMaterialidbPrec != null){
					  logger.debug("-- numero di Tipologie di Attivita ="+tipologieAttMaterialidbPrec.size());
					  for (int i=0;i<tipologieAttMaterialidbPrec.size();i++) {
						  TipologiaAttMaterialeDTO tipologiaAttMaterialeDTO = tipologieAttMaterialidbPrec.get(i);
						  if(tipologiaAttMaterialeDTO.getRichiestaPassaporto().equals("S"))
							  nuovaDomandaForm.setRichiestaPassaporto(true);
						  else
							  nuovaDomandaForm.setRichiestaPassaporto(false);	
						  ModaliForm modaliform = new ModaliForm();					
						  modaliform.setIdTipoAttivitaMat(tipologiaAttMaterialeDTO.getIdTipologia());
						  modaliform.setIdMateriale(tipologiaAttMaterialeDTO.getIdMateriale());
						  modaliform.setIdDomanda(nuovaDomandaForm.getIdDomanda());
						  domandeEJB.salvaTipologiaAttMateriale(modaliform, utente);
					  }
					  // aggiorno i dati per la spunta richiesta passaporto
					  logger.debug("-- Salvo i dati della richiesta passaporto su db car_r_att_materiale_utente");
					  logger.debug("-- isRichiestaPassaporto ="+nuovaDomandaForm.isRichiestaPassaporto());
					  nuovaDomandaForm.setRichiestaPassaporto(nuovaDomandaForm.isRichiestaPassaporto());
					  domandeEJB.aggiornaRichiestaPassaporto(nuovaDomandaForm, utente.getId());
				  }

				  List<TipologiaAttMaterialeDTO> tipologieAttMaterialidb = domandeEJB.getTipologieAttMateriale(nuovaDomandaForm.getIdDomanda()); 
				  model.addAttribute("tipologieAttDb", tipologieAttMaterialidb);  
			  }


			  // *** --- Dati Tipologia --- ***   
			  logger.debug("-- *** --- Dati Tipologia --- *** ");    
			  logger.debug("-- Setto nel form i dati della Tipologia --");
			  // Dati check Attività DB
			  logger.debug("-- Ricerco i dati check Attività DB");
			  Long[] idVoceUtenteAttivita = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP, 1L, idDomandaPrec);	    
			  if(null != idVoceUtenteAttivita && idVoceUtenteAttivita.length>0){
				  logger.debug("-- sul db ci sono check attività salvate, quanti ="+idVoceUtenteAttivita.length);
				  String strIdVoce[] = new String[idVoceUtenteAttivita.length];
				  for (int i = 0; i < idVoceUtenteAttivita.length; i++)
					  strIdVoce[i] = String.valueOf(idVoceUtenteAttivita[i]);	    
				  nuovaDomandaForm.setIdVoceCheckTip(strIdVoce);
			  }
			  else{
				  nuovaDomandaForm.setIdVoceCheckTip(null);
			  }
			  // Dati check Tipologie Domanda DB
			  logger.debug("-- Ricerco i dati check Tipologie Domanda DB");
			  TipologiaDomandaDTO tipologiaDomandadto = domandeEJB.getDettTipologiaDomanda(idDomandaPrec);
			  if(null != tipologiaDomandadto && null != tipologiaDomandadto.getTipologieDomandaList() && tipologiaDomandadto.getTipologieDomandaList().size()>0){
				  logger.debug("-- ci sono tipologie domande selezionate, quante ="+tipologiaDomandadto.getTipologieDomandaList().size());
				  String[] idTipologiaStr = new String[tipologiaDomandadto.getTipologieDomandaList().size()];
				  for (int i=0;i<tipologiaDomandadto.getTipologieDomandaList().size();i++) {		    		
					  idTipologiaStr[i] = tipologiaDomandadto.getTipologieDomandaList().get(i).getIdTipologia().toString();
				  }
				  nuovaDomandaForm.setIdTipologia(idTipologiaStr);
			  }

			  // -- Salvo i dati della Tipologia sul db --
			  logger.debug("-- Salvo i dati della Tipologia sul db --");
			  domandeEJB.aggiornaTipologia(nuovaDomandaForm, utente.getId());

			  //Recupero le tipologie dalle attivita' che sono state aggiunte
			  String[] idtipologiaArr = domandeEJB.getTipologieByIdDomanda(nuovaDomandaForm.getIdDomanda());    		    		     		

			  boolean tabImport = false;
			  boolean tabExport = false;
			  boolean tabPassaporto = false;
			  for (int i = 0; i < idtipologiaArr.length; i++) {
				  Long idTipologia = Long.parseLong(idtipologiaArr[i]);
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
			  nuovaDomandaForm.setTabImport(tabImport);
			  nuovaDomandaForm.setTabExport(tabExport);
			  nuovaDomandaForm.setTabPassaporto(tabPassaporto);
		  }
		  catch (Exception e) {
			  logger.error(" -- Exception in duplicaDatiTabAzienda ="+e.getMessage());
			  throw e;
		  }
		  finally{
			  logger.debug("END duplicaDatiTabAzienda");
		  }
	  }
	  
	  private NuovaDomandaForm setDatiCentroAziendale(CarTCentroAziendale centroAzDb, NuovaDomandaForm form) throws Exception{
		  form.setCapCentroAz(centroAzDb.getCap());
		  form.setCellulareCentroAz(centroAzDb.getCellulare());
		  form.setCodCentroAz(centroAzDb.getCodCentroAziendale());
		  form.setCodFiscale(centroAzDb.getCodiceFiscale());
		  form.setDenominazCentroAz(centroAzDb.getDenominazione());
		  form.setMailCentroAz(centroAzDb.getEmail());
		  form.setFrazioneCentroAz(centroAzDb.getFrazione());
		  form.setIdComuneCentroAz(centroAzDb.getIdComune());
		  form.setIdAzienda(centroAzDb.getIdSpedizioniere());
		  form.setIndirizzoCentroAz(centroAzDb.getIndirizzo());
		  form.setPecCentroAz(centroAzDb.getPec()); 
		  form.setTelefonoCentroAz(centroAzDb.getTelefono());		  		  
		  return form;
	  }
	  
	  private void duplicaDatiTabCentriAziendali(NuovaDomandaForm nuovaDomandaForm, Model model, UtenteDTO utente, Long idDomanda)throws Exception{
		  logger.debug("BEGIN duplicaDatiTabCentriAziendali");
		  try {
			// *** --- Dati Tab Centro Aziendale --- ***
			logger.debug("*** --- Dati Tab Centro Aziendale --- ***");
			Long idDomandaPrec = nuovaDomandaForm.getIdDomandaPrecedente();
			// *** Controllo se ci sono dei centri aziendali nella domanda precedente
			logger.debug("-- Controllo se ci sono dei centri aziendali nella domanda precedente");
			List<CentroAziendaleDomandaDTO> centriAziendaliDbPrec = domandeEJB.getCentriAziendaliByIdDomanda(idDomandaPrec);
			if(null != centriAziendaliDbPrec && centriAziendaliDbPrec.size()>0){
			  logger.debug("-- *** Ci sono centri aziendali legati alla domanda precedente : "+centriAziendaliDbPrec.size()+", li salvo sul db per la nuova domanda");
			  for(int i=0;i<centriAziendaliDbPrec.size();i++){
				  CentroAziendaleDomandaDTO centroAz = centriAziendaliDbPrec.get(i);
				  logger.debug("-- idCentroAziendale da legare alla domanda ="+centroAz.getIdCentroAziendale());
				  nuovaDomandaForm.setIdCentroAziendale(centroAz.getIdCentroAziendale());
				  CarTCentroAziendale centroAziendaleDb = domandeEJB.getCentroAziendaleById(centroAz.getIdCentroAziendale());
				  // Setto i dati del centro aziendale nella form
				  nuovaDomandaForm = setDatiCentroAziendale(centroAziendaleDb, nuovaDomandaForm);				  
				  Long idCentroAziendaleSalvato = domandeEJB.salvaCentroAziendaleDomanda(nuovaDomandaForm, centroAz, utente);
			  
				  // Recupero i centri aziendali appena salvati sul db per la domanda
				  List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(nuovaDomandaForm.getIdDomanda());			  												 
				  model.addAttribute("centriAziendaliDb",centriAziendaliDb);				  
				  // Seleziono il radio del primo centro aziendale presente su db		  
				  nuovaDomandaForm.setIdCentroAziendaleSel(centriAziendaliDb.get(i).getIdCentroAziendale());
				  logger.debug("-- IdCentroAziendale da selezionare ="+nuovaDomandaForm.getIdCentroAziendaleSel());
					  
				  // *** Cerco i dati delle tipologie produttive
				  logger.debug("-- *** Cerco i dati delle tipologie produttive per il centro aziendale :"+centroAz.getIdCentroAziendale()+ " e idDomandaPrec ="+idDomandaPrec);
				  List<TipologiaProdSpecieDTO> tipologieProdDbPrec = domandeEJB.getTipologieProdCentroAz(centroAz.getIdCentroAziendale(), idDomandaPrec);
				  if(tipologieProdDbPrec != null){
					  for(int j=0;j<tipologieProdDbPrec.size();j++){
						logger.debug("-- ***TipologiaProdSpecieDTO della domanda precedente ="+tipologieProdDbPrec.size());
					    TipologiaProdSpecieDTO tipoProd = tipologieProdDbPrec.get(j);
					    // idTipologia
					    logger.debug("-- **** IdVoce **** = "+tipoProd.getIdVoce());					    
					    nuovaDomandaForm.setIdTipologiaProd(tipoProd.getIdVoce());
					    logger.debug("-- note = "+tipoProd.getNote());
					    nuovaDomandaForm.setNoteTipologiaCentroAz(tipoProd.getNote());					    
					    // Id Specie
					    if(tipoProd.getSpecieList() != null && tipoProd.getSpecieList().size()>0){
					    	logger.debug("-- numero idSpecie + idgenere singolo da inserire ="+tipoProd.getSpecieList().size());					    	
					    	String[] idSpecie = null;
					    	List<String> idSpecieList = new ArrayList<String>();
					    	nuovaDomandaForm.setSpecie(null);
					    	List<Long> idGenereSingolo = new ArrayList<Long>();
					    	nuovaDomandaForm.setIdGenere(null);
					    	int cont = 0;
					    	// Estrapolo le specie per idTipologia
					    	for (Iterator<GenereSpecieDTO> iterator = tipoProd.getSpecieList().iterator(); iterator.hasNext();) {
					    		GenereSpecieDTO tipologiaProdSpecieDTO = (GenereSpecieDTO) iterator.next();
					    		// Controllo se c'è l'idSpecie
					    		if(tipologiaProdSpecieDTO.getIdSpecie() != null){
					    		  logger.debug("-- **** idSpecie ="+ tipologiaProdSpecieDTO.getIdSpecie());
					    		  idSpecieList.add(tipologiaProdSpecieDTO.getIdSpecie().toString());
					    		}
								// Altrimenti mi memorizzo l'idGenere								
								else{
									// Estrapolo idGenere per tipologia
									logger.debug("-- **** idGenere ="+ tipologiaProdSpecieDTO.getIdGenere());
									idGenereSingolo.add(tipologiaProdSpecieDTO.getIdGenere());
								}								
							}
					    	if(idSpecieList.size()>0){
					    	  idSpecie = idSpecieList.toArray(new String[0]);
					    	  nuovaDomandaForm.setSpecie(idSpecie);
					    	  logger.debug("-- Salvo le Tipologie produttive del centro aziendale per la nuova domanda con idSpecie");
							  domandeEJB.salvaTipologiaProduttivaCentroAz(nuovaDomandaForm, utente);
					    	}					    	
					    	// Controllo se ci sono delle Tipologie con solo idGenere (senza specie)
					    	if(idGenereSingolo != null && idGenereSingolo.size()>0){					    		
					    		for (Iterator<Long> iterator = idGenereSingolo.iterator(); iterator.hasNext();) {
									Long idGenere = (Long) iterator.next();									
									nuovaDomandaForm.setIdGenere(idGenere);
									logger.debug("-- Salvo le Tipologie produttive del centro aziendale per la nuova domanda con idGenere");
									domandeEJB.salvaTipologiaProduttivaCentroAz(nuovaDomandaForm, utente);
								}					    		
					    	}
					    }					    					    
					  }
					  // Recupero le tipologie produttive appena salvate
					  logger.debug("--  Recupero le tipologie produttive appena salvate");
					  List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdCentroAz(centroAz.getIdCentroAziendale(), nuovaDomandaForm.getIdDomanda());
					  model.addAttribute("tipologieProdDb", tipologieProdDb);
					  // Rimuovo le selezioni del form per tipologia produttiva
					  logger.debug("-- Rimuovo le selezioni del form per tipologia produttiva");
					  nuovaDomandaForm.setIdTipologiaProd(null);
					  nuovaDomandaForm.setSpecie(null);
					  nuovaDomandaForm.setIdGenere(null);
				  }
										 
				  // *** Cerco i dati i dati dei siti produzione				  
				  logger.debug("-- Cerco i dati dei siti produzione per il centro aziendale :"+centroAz.getIdCentroAziendale());
				  List<SitoProduzioneDTO> sitiProduzioneDb = domandeEJB.getSitiProduzioneCentroAz(centroAz.getIdCentroAziendale());				  				  
				  model.addAttribute("sitiProduzioneDb", sitiProduzioneDb);
				}// FINE CICLO SUI CENTRI AZIENDALI				  
			  	
			    // Seleziono il primo check perchè ci sono centri aziendali
				nuovaDomandaForm.setIdCentroAzNoSedeLeg("1");
			  }
			  else{
			    logger.debug("-- Non ci sono centri aziendali legati alla domanda da visualizzare");  
			  }
			
			
		  } 
		  catch (Exception e) {
			logger.debug("-- Exception in duplicaDatiTabCentriAziendali ="+e.getMessage());
			throw e;
		  }
		  finally{
		    logger.debug("END duplicaDatiTabCentriAziendali");
		  }
	  }
	  
	  
	  private void duplicaDatiTabImport(NuovaDomandaForm nuovaDomandaForm, Model model, UtenteDTO utente, Long idDomanda) throws Exception{
		  logger.debug("BEGIN duplicaDatiTabImport");
		  try {
			  CarTDatiDomanda datiDomanda = null;
			  // *** --- Dati Tab Import --- ***
			  Long idDomandaPrec = nuovaDomandaForm.getIdDomandaPrecedente();
			  logger.debug("-- *** --- Dati Tab Import --- *** ");   
			  // Controllo se devono essere visualizzati i dati del Tab Import
			  if(nuovaDomandaForm.isTabImport()){
				  // -- Setto nel form i dati Import
				  logger.debug("-- Setto nel form i dati Import --");

				  // Tipologie produttive
				  List<TipologiaProdSpecieDTO> tipologieProdDbPrec = domandeEJB.getTipologieProdByIdDomanda(idDomandaPrec, CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 1L);					
				  if(tipologieProdDbPrec != null){
					  for(int j=0;j<tipologieProdDbPrec.size();j++){
						  logger.debug("-- ***TipologiaProdSpecieDTO della domanda precedente ="+tipologieProdDbPrec.size());
						  TipologiaProdSpecieDTO tipoProd = tipologieProdDbPrec.get(j);
						  // idTipologia
						  logger.debug("-- **** IdVoce **** = "+tipoProd.getIdVoce());					    
						  nuovaDomandaForm.setIdTipologiaProdImport(tipoProd.getIdVoce());
						  logger.debug("-- note = "+tipoProd.getNote());						    
						  nuovaDomandaForm.setNoteTipologiaImport(tipoProd.getNote());					    
						  // Id Specie
						  if(tipoProd.getSpecieList() != null && tipoProd.getSpecieList().size()>0){
							  logger.debug("-- numero idSpecie + idgenere singolo da inserire ="+tipoProd.getSpecieList().size());					    	
							  String[] idSpecie = null;
							  List<String> idSpecieList = new ArrayList<String>();
							  nuovaDomandaForm.setSpecieImport(null);
							  List<Long> idGenereSingolo = new ArrayList<Long>();
							  nuovaDomandaForm.setIdGenereImport(null);
							  int cont = 0;
							  // Estrapolo le specie per idTipologia
							  for (Iterator<GenereSpecieDTO> iterator = tipoProd.getSpecieList().iterator(); iterator.hasNext();) {
								  GenereSpecieDTO tipologiaProdSpecieDTO = (GenereSpecieDTO) iterator.next();
								  // Controllo se c'è l'idSpecie
								  if(tipologiaProdSpecieDTO.getIdSpecie() != null){
									  logger.debug("-- **** idSpecie ="+ tipologiaProdSpecieDTO.getIdSpecie());
									  idSpecieList.add(tipologiaProdSpecieDTO.getIdSpecie().toString());
								  }
								  // Altrimenti mi memorizzo l'idGenere								
								  else{
									  // Estrapolo idGenere per tipologia
									  logger.debug("-- **** idGenere ="+ tipologiaProdSpecieDTO.getIdGenere());
									  idGenereSingolo.add(tipologiaProdSpecieDTO.getIdGenere());
								  }								
							  }
							  if(idSpecieList.size()>0){
								  idSpecie = idSpecieList.toArray(new String[0]);
								  nuovaDomandaForm.setSpecieImport(idSpecie);
								  logger.debug("-- Salvo le Tipologie produttive import per la nuova domanda con idSpecie");
								  domandeEJB.salvaTipologiaProdSpecieImport(nuovaDomandaForm, utente);
							  }					    	
							  // Controllo se ci sono delle Tipologie con solo idGenere (senza specie)
							  if(idGenereSingolo != null && idGenereSingolo.size()>0){					    		
								  for (Iterator<Long> iterator = idGenereSingolo.iterator(); iterator.hasNext();) {
									  Long idGenere = (Long) iterator.next();									
									  nuovaDomandaForm.setIdGenereImport(idGenere);
									  logger.debug("-- Salvo le Tipologie produttive import per la nuova domanda con idGenere");
									  domandeEJB.salvaTipologiaProdSpecieImport(nuovaDomandaForm, utente);
								  }					    		
							  }
						  }					    					    
					  }
					  // Ricerco le tipologie import appena inserite
					  logger.debug("-- Ricerco le tipologie import appena inserite");	
					  List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 1L);
					  model.addAttribute("tipologieProdImportDb", tipologieProdDb);
					  // Rimuovo le selezioni del form per tipologia produttiva
					  logger.debug("-- Rimuovo le selezioni del form per tipologia produttiva import");
					  nuovaDomandaForm.setIdTipologiaProdImport(null);
					  nuovaDomandaForm.setIdSpecieImport(null);
					  nuovaDomandaForm.setIdGenereImport(null);

				  }

				  // Radio e note Zone protette
				  logger.debug("-- Ricerco i dati dei radio Zone protette");
				  Long[] idVoceRadioZoneProtetteImport = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 3L, idDomandaPrec);
				  if(null != idVoceRadioZoneProtetteImport && idVoceRadioZoneProtetteImport.length>0){
					  String[] idVoceRadioZoneProtetteImpStr = new String[idVoceRadioZoneProtetteImport.length];
					  for (int i = 0; i < idVoceRadioZoneProtetteImport.length; i++) {
						  idVoceRadioZoneProtetteImpStr[i] = idVoceRadioZoneProtetteImport[i].toString();
					  }
					  nuovaDomandaForm.setIdVoceRadioZonaProtetta(idVoceRadioZoneProtetteImpStr);
				  }
				  // Note delle Zone protette
				  datiDomanda = domandeEJB.getDatiAggiuntiviByIdDomanda(idDomandaPrec);
				  if(null != datiDomanda){
					  nuovaDomandaForm.setImpDatoAggiuntivo(datiDomanda.getImpDatoAggiuntivo());
				  } 

				  // Check continenti Import
				  logger.debug("-- Ricerco i dati dei check Continenti Import");
				  Long[] idVoceCheckContinentiImp = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 2L, idDomandaPrec);
				  if(null != idVoceCheckContinentiImp && idVoceCheckContinentiImp.length>0){
					  String[] idVoceCheckContinentiImpStr = new String[idVoceCheckContinentiImp.length];
					  for (int i = 0; i < idVoceCheckContinentiImp.length; i++) {
						  idVoceCheckContinentiImpStr[i] = idVoceCheckContinentiImp[i].toString();
					  }
					  nuovaDomandaForm.setIdVoceCheckContinenti(idVoceCheckContinentiImpStr);

					  // Note check continenti
					  logger.debug("-- Ricerco Note check continenti"); 
					  String noteStatoOrigine = domandeEJB.getNoteVoceUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 2L, idDomandaPrec);
					  nuovaDomandaForm.setStatoOrigine(noteStatoOrigine);
				  }			   			   

				  // Note Import
				  logger.debug("-- Ricerco Note import");
				  if(null != datiDomanda){					 
					  nuovaDomandaForm.setNoteImport(datiDomanda.getImpNote()); 
				  }
				  else{
					  datiDomanda = domandeEJB.getDatiAggiuntiviByIdDomanda(idDomandaPrec);
					  if(datiDomanda != null){
						  nuovaDomandaForm.setNoteImport(datiDomanda.getImpNote());
					  }  
				  }

				  // -- Salvo i dati Import sul db --
				  logger.debug("-- Salvo i dati Import sul db --");
				  domandeEJB.salvaDatiImport(nuovaDomandaForm, utente);				
			  }// -- FINE Dati Tab Import
		  }
		  catch (Exception e) {
			  logger.error("-- Exception in duplicaDatiTabImport ="+e.getMessage());
			  throw e;
		  }
		  finally{
			  logger.debug("END duplicaDatiTabImport");
		  }
	  }
	  
	  	  
	  private void duplicaDatiTabExport(NuovaDomandaForm nuovaDomandaForm, Model model, UtenteDTO utente, Long idDomanda) throws Exception{
		  logger.debug("BEGIN duplicaDatiTabExport");
		  try {
			  CarTDatiDomanda datiDomanda = null;
			  Long idDomandaPrec = nuovaDomandaForm.getIdDomandaPrecedente();
			  // -- INIZIO Dati Tab Export
			  // Controllo se devono essere visualizzati i dati del Tab Export
			  if(nuovaDomandaForm.isTabExport()){
				  // *** --- Dati Tab Export --- ***
				  logger.debug("-- *** --- Dati Tab Export --- *** ");  
				  				  
				  // Tipologie produttive export
				  List<TipologiaProdSpecieDTO> tipologieProdDbPrec = domandeEJB.getTipologieProdByIdDomanda(idDomandaPrec, CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 3L);					
				  if(tipologieProdDbPrec != null){
					  for(int j=0;j<tipologieProdDbPrec.size();j++){
						  logger.debug("-- ***TipologiaProdSpecieDTO export della domanda precedente ="+tipologieProdDbPrec.size());
						  TipologiaProdSpecieDTO tipoProd = tipologieProdDbPrec.get(j);
						  // idTipologia
						  logger.debug("-- **** IdVoce **** = "+tipoProd.getIdVoce());					    
						  nuovaDomandaForm.setIdTipologiaProdExp(tipoProd.getIdVoce());
						  logger.debug("-- note = "+tipoProd.getNote());						    
						  nuovaDomandaForm.setNoteTipologiaExport(tipoProd.getNote());					    
						  // Id Specie
						  if(tipoProd.getSpecieList() != null && tipoProd.getSpecieList().size()>0){
							  logger.debug("-- numero idSpecie + idgenere singolo da inserire ="+tipoProd.getSpecieList().size());					    	
							  String[] idSpecie = null;
							  List<String> idSpecieList = new ArrayList<String>();
							  nuovaDomandaForm.setSpecieExport(null);
							  List<Long> idGenereSingolo = new ArrayList<Long>();
							  nuovaDomandaForm.setIdGenereExport(null);
							  int cont = 0;
							  // Estrapolo le specie per idTipologia
							  for (Iterator<GenereSpecieDTO> iterator = tipoProd.getSpecieList().iterator(); iterator.hasNext();) {
								  GenereSpecieDTO tipologiaProdSpecieDTO = (GenereSpecieDTO) iterator.next();
								  // Controllo se c'è l'idSpecie
								  if(tipologiaProdSpecieDTO.getIdSpecie() != null){
									  logger.debug("-- **** idSpecie ="+ tipologiaProdSpecieDTO.getIdSpecie());
									  idSpecieList.add(tipologiaProdSpecieDTO.getIdSpecie().toString());
								  }
								  // Altrimenti mi memorizzo l'idGenere								
								  else{
									  // Estrapolo idGenere per tipologia
									  logger.debug("-- **** idGenere ="+ tipologiaProdSpecieDTO.getIdGenere());
									  idGenereSingolo.add(tipologiaProdSpecieDTO.getIdGenere());
								  }								
							  }
							  if(idSpecieList.size()>0){
								  idSpecie = idSpecieList.toArray(new String[0]);
								  nuovaDomandaForm.setSpecieExport(idSpecie);
								  logger.debug("-- Salvo le Tipologie produttive export per la nuova domanda con idSpecie");
								  domandeEJB.salvaTipologiaProdSpecieExport(nuovaDomandaForm, utente);
							  }					    	
							  // Controllo se ci sono delle Tipologie con solo idGenere (senza specie)
							  if(idGenereSingolo != null && idGenereSingolo.size()>0){					    		
								  for (Iterator<Long> iterator = idGenereSingolo.iterator(); iterator.hasNext();) {
									  Long idGenere = (Long) iterator.next();									
									  nuovaDomandaForm.setIdGenereExport(idGenere);
									  logger.debug("-- Salvo le Tipologie produttive export per la nuova domanda con idGenere");
									  domandeEJB.salvaTipologiaProdSpecieExport(nuovaDomandaForm, utente);
								  }					    		
							  }
						  }					    					    
					  }
					  // Ricerco le tipologie export appena inserite
					  logger.debug("-- Ricerco le tipologie export appena inserite");	
					  List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 3L);
					  model.addAttribute("tipologieProdExportDb", tipologieProdDb);
					  // Rimuovo le selezioni del form per tipologia produttiva
					  logger.debug("-- Rimuovo le selezioni del form per tipologia produttiva");
					  nuovaDomandaForm.setIdTipologiaProdExp(null);
					  nuovaDomandaForm.setSpecieExport(null);
					  nuovaDomandaForm.setIdGenereExport(null);

				  }
				  
				  
				  
				  // -- Setto nel form i dati Export
				  logger.debug("-- Setto nel form i dati Export --");				
				  // Check 'Le esportazioni riguardano principalmente'
				  logger.debug("-- Ricerco i dati dei check 'Le esportazioni riguardano principalmente'");
				  Long[] idVoceCheckEsportazioni = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 1L, idDomandaPrec);
				  if(null != idVoceCheckEsportazioni && idVoceCheckEsportazioni.length>0){
					  String[] idVoceCheckEsportazioniStr = new String[idVoceCheckEsportazioni.length];
					  for (int i = 0; i < idVoceCheckEsportazioni.length; i++) {
						  idVoceCheckEsportazioniStr[i] = idVoceCheckEsportazioni[i].toString();
					  }
					  nuovaDomandaForm.setIdVoceCheckTipExp(idVoceCheckEsportazioniStr);
				  }
				  logger.debug("-- Ricerco Note export");
				  if(null != datiDomanda){					 
					  nuovaDomandaForm.setExpDatoAggiuntivo(datiDomanda.getExpDatoAggiuntivo()); 
				  }
				  else{
					  datiDomanda = domandeEJB.getDatiAggiuntiviByIdDomanda(idDomandaPrec);
					  if(datiDomanda != null){
						  nuovaDomandaForm.setExpDatoAggiuntivo(datiDomanda.getExpDatoAggiuntivo());
					  }  
				  }

				  // Check Continenti Export
				  logger.debug("-- Ricerco i dati dei check Continenti Export");
				  Long[] idVoceCheckContinentiExp = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 2L, idDomandaPrec);
				  if(null != idVoceCheckContinentiExp && idVoceCheckContinentiExp.length>0){
					  String[] idVoceCheckContinentiExpStr = new String[idVoceCheckContinentiExp.length];
					  for (int i = 0; i < idVoceCheckContinentiExp.length; i++) {
						  idVoceCheckContinentiExpStr[i] = idVoceCheckContinentiExp[i].toString();
					  }
					  nuovaDomandaForm.setIdVoceCheckContinentiExp(idVoceCheckContinentiExpStr);

					  // Note check continenti Export
					  logger.debug("-- Ricerco Note check continenti Export"); 
					  String noteStatoOrigineExp = domandeEJB.getNoteVoceUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 2L, idDomandaPrec);
					  nuovaDomandaForm.setStatoOrigineExp(noteStatoOrigineExp);
				  }

				  // -- Salvo i dati Export sul db --
				  logger.debug("-- Salvo i dati Export sul db --");
				  domandeEJB.salvaDatiExport(nuovaDomandaForm, utente);				
			  }
			  // -- FINE Dati Tab Export
		  } 
		  catch (Exception e) {
			  logger.error("-- Exception in duplicaDatiTabExport ="+e.getMessage());
			  throw e;
		  }
		  finally{
			  logger.debug("END duplicaDatiTabExport");  
		  }
	  }
	  
	  
	private void duplicaDatiTabPassaporto(NuovaDomandaForm nuovaDomandaForm, Model model, UtenteDTO utente, Long idDomanda) throws Exception{
		  logger.debug("BEGIN duplicaDatiTabPassaporto");
		  try {
			  Long idDomandaPrec = nuovaDomandaForm.getIdDomandaPrecedente();
			  logger.debug("--  idDomandaPrec ="+idDomandaPrec);
			  logger.debug("-- nuovaDomandaForm.isTabPassaporto() ="+nuovaDomandaForm.isTabPassaporto());
			  // Dati Tab Passaporto
			  if(nuovaDomandaForm.isTabPassaporto()){
				  // -- Setto nel form i dati Passaporto --
				  logger.debug("-- Setto nel form i dati Passaporto");
				  CarTResponsabilePassaporto resp = domandeEJB.getRespPassaportoByIdDomanda(idDomandaPrec);
				  if (resp != null) {
					  nuovaDomandaForm.setCheckRespFito("1");
					  nuovaDomandaForm.setCognomeRespPass(resp.getCognome());
					  nuovaDomandaForm.setNomeRespPass(resp.getNome());
					  nuovaDomandaForm.setCodFiscaleRespPass(resp.getCodiceFiscale());
					  nuovaDomandaForm.setNumTelefonoRespPass(resp.getTelefono());
					  nuovaDomandaForm.setCellulareRespPass(resp.getCellulare());
					  nuovaDomandaForm.setEmailRespPass(resp.getEmail());
					  nuovaDomandaForm.setQualificaProfRespPass(resp.getQualificaProfessionale());
				  }

				  Long[] idVoceRadioChecked = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 2L, idDomandaPrec);
				  if(idVoceRadioChecked != null && idVoceRadioChecked.length > 0){
					  String strIdVoce[] = new String[idVoceRadioChecked.length];
					  for (int i = 0; i < idVoceRadioChecked.length; i++) {
						  strIdVoce[i] = String.valueOf(idVoceRadioChecked[i]);	   
					  }
					  nuovaDomandaForm.setIdVoceRadio(strIdVoce);
				  }

				  Long[] idVoceChecked = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 3L, idDomandaPrec);
				  if(idVoceChecked != null && idVoceChecked.length > 0){
					  String strIdVoce[] = new String[idVoceChecked.length];
					  for (int i = 0; i < idVoceChecked.length; i++) {
						  strIdVoce[i] = String.valueOf(idVoceChecked[i]);	   
					  }
					  nuovaDomandaForm.setIdVoceCheck(strIdVoce);
				  }
				  
				  // RADIO in fondo alla pagina
				  Long[] idVoceDichiara = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 5L, idDomandaPrec);
				  if(idVoceDichiara != null && idVoceDichiara.length > 0) {
					  String strIdVoce[] = new String[idVoceDichiara.length];
					  for (int i = 0; i < idVoceDichiara.length; i++) { 
						  if (idVoceDichiara[i] == CaronteConstants.ID_VOCE_DICHIARA_CONOSCENZE) {
							  nuovaDomandaForm.setVoceDichiaraConoscenze("S");    	    			
						  } 
						  if (idVoceDichiara[i] == CaronteConstants.ID_VOCE_DICHIARA_DISPORRE_SISTEMI) {
							  nuovaDomandaForm.setVoceDichiaraDisporreSistemi("S");    	    			
						  } 
					  }
				  }
				  // se non sono presenti sul DB le voci allora metto per default a N
				  if (nuovaDomandaForm.getVoceDichiaraConoscenze() == null) {
					  nuovaDomandaForm.setVoceDichiaraConoscenze("N");
				  }
				  if (nuovaDomandaForm.getVoceDichiaraDisporreSistemi() == null) {
					  nuovaDomandaForm.setVoceDichiaraDisporreSistemi("N");
				  }

				  Long[] idVocePianoRischi = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 6L, idDomandaPrec);
				  if(idVocePianoRischi != null && idVocePianoRischi.length > 0) {
					  String strIdVoce[] = new String[idVocePianoRischi.length];
					  for (int i = 0; i < idVocePianoRischi.length; i++) { 
						  if (idVocePianoRischi[i] == CaronteConstants.ID_VOCE_PIANO_RISCHI) {
							  nuovaDomandaForm.setVocePianoRischi("S");    	    			
						  } 
					  }
				  }
				  // se non sono presenti sul DB le voci allora metto per default a N
				  if (nuovaDomandaForm.getVocePianoRischi() == null) {
					  nuovaDomandaForm.setVocePianoRischi("N");
				  }
				  

				  // -- Salvo i dati del Passaporto sul db --
				  logger.debug("-- Salvo i dati del Passaporto sul db --");
				  domandeEJB.salvaVociPassaporto(nuovaDomandaForm, utente); 	
				  
				  //salvo le tipologie produttive				  
				  List<TipologiaProdSpecieDTO> tipologieProdDbPrec = domandeEJB.getTipologieProdByIdDomanda(idDomandaPrec, CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 4L);
				 
				  if (tipologieProdDbPrec != null && tipologieProdDbPrec.size() > 0) {
					  for(TipologiaProdSpecieDTO tipologiaProd : tipologieProdDbPrec)	{
						  logger.debug("-- tipologiaProd.getIdVoce();: "+ tipologiaProd.getIdVoce());
						  nuovaDomandaForm.setIdTipologiaProd(tipologiaProd.getIdVoce());
						  
						  logger.debug("-- tipologiaProd.getSpecieList().size(): "+ tipologiaProd.getSpecieList().size());
						  if(tipologiaProd.getSpecieList() != null && tipologiaProd.getSpecieList().size()>0){
						    	logger.debug("-- numero idSpecie + idgenere singolo da inserire ="+tipologiaProd.getSpecieList().size());					    	
						    	String[] idSpecie = null;
						    	List<String> idSpecieList = new ArrayList<String>();
						    	List<Long> idGenereSingolo = new ArrayList<Long>();
						    	int cont = 0;
						    	// Estrapolo le specie per idTipologia
						    	for (Iterator<GenereSpecieDTO> iterator = tipologiaProd.getSpecieList().iterator(); iterator.hasNext();) {
						    		GenereSpecieDTO tipologiaProdSpecieDTO = (GenereSpecieDTO) iterator.next();
						    		// Controllo se c'è l'idSpecie
						    		if(tipologiaProdSpecieDTO.getIdSpecie() != null){
						    		  logger.debug("-- **** idSpecie ="+ tipologiaProdSpecieDTO.getIdSpecie());
						    		  idSpecieList.add(tipologiaProdSpecieDTO.getIdSpecie().toString());
						    		}
									// Altrimenti mi memorizzo l'idGenere								
									else{
										// Estrapolo idGenere per tipologia
										logger.debug("-- **** idGenere ="+ tipologiaProdSpecieDTO.getIdGenere());
										idGenereSingolo.add(tipologiaProdSpecieDTO.getIdGenere());
									}								
								}
						    	if(idSpecieList.size()>0){
						    	  idSpecie = idSpecieList.toArray(new String[0]);
						    	  nuovaDomandaForm.setSpecie(idSpecie);
						    	  logger.debug("-- Salvo le Tipologie produttive per i vari idSpecie");
								  domandeEJB.salvaTipologiaProdSpeciePassaporto(nuovaDomandaForm, utente);
								  nuovaDomandaForm.setSpecie(null);
						    	}					    	
						    	// Controllo se ci sono delle Tipologie con solo idGenere (senza specie)
						    	if(idGenereSingolo != null && idGenereSingolo.size()>0){					    		
						    		for (Iterator<Long> iterator = idGenereSingolo.iterator(); iterator.hasNext();) {
										Long idGenere = (Long) iterator.next();									
										nuovaDomandaForm.setIdGenerePass(idGenere);
										nuovaDomandaForm.setIdSpecie(null);
										logger.debug("-- Salvo le Tipologie produttive  per il solo idGenere");
										domandeEJB.salvaTipologiaProdSpeciePassaporto(nuovaDomandaForm, utente);
										nuovaDomandaForm.setIdGenerePass(null);
									}					    		
						    	}
						    }					    					    
						  }						  
					  }	
				  List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdByIdDomanda(nuovaDomandaForm.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 4L);
				  model.addAttribute("tipologieProdDb", tipologieProdDb);
				  // Rimuovo le selezioni del form per tipologia produttiva
				  logger.debug("-- Rimuovo le selezioni del form per tipologia produttiva import");
				  nuovaDomandaForm.setIdTipologiaProd(null);
				  nuovaDomandaForm.setSpecie(null);
				  nuovaDomandaForm.setIdGenerePass(null);
				  
				  
				  //salvo Zone protette
				  List<ZonaProtettaSpecieDTO> zoneProtetteDbPrec = domandeEJB.getZoneProtetteSpecieByIdDomanda(idDomandaPrec);
				  
				  if (zoneProtetteDbPrec != null && zoneProtetteDbPrec.size() > 0) {
					  for(ZonaProtettaSpecieDTO zonaProtetta : zoneProtetteDbPrec) {
						  nuovaDomandaForm.setIdZonaProtetta(zonaProtetta.getIdGruppoZonaProtetta());
						  
						  if (zonaProtetta.getSpecieList() != null && zonaProtetta.getSpecieList().size() > 0) {
							  String[] idSpecie = null;
							  String[] genere = null;
						      List<String> idSpecieList = new ArrayList<String>();
						      List<String> idGenereList = new ArrayList<String>();						      
							  for (Iterator<GenereSpecieDTO> iterator = zonaProtetta.getSpecieList().iterator(); iterator.hasNext();) {
								  GenereSpecieDTO tipologiaProdSpecieDTO = (GenereSpecieDTO) iterator.next();
								  // Controllo se c'è l'idSpecie
								  if(tipologiaProdSpecieDTO.getIdSpecie() != null){
									  logger.debug("-- **** idSpecie ="+ tipologiaProdSpecieDTO.getIdSpecie());
						    		  idSpecieList.add(tipologiaProdSpecieDTO.getIdSpecie().toString());
						    		  logger.debug("-- **** idGenere ="+ tipologiaProdSpecieDTO.getIdGenere());
						    		  idGenereList.add(tipologiaProdSpecieDTO.getIdGenere().toString());
								  }									 
							  }
							  if(idSpecieList.size()>0){
						    	  idSpecie = idSpecieList.toArray(new String[0]);
						    	  nuovaDomandaForm.setSpecieZP(idSpecie);
						    	  genere = idGenereList.toArray(new String[0]);
						    	  nuovaDomandaForm.setGenereZP(genere);
						    	  logger.debug("-- Salvo le zone protette ");
								  domandeEJB.salvaZonaProtettaSpeciePassaporto(nuovaDomandaForm, utente);
								  nuovaDomandaForm.setSpecieZP(null);
								  nuovaDomandaForm.setGenereZP(null);
							  }											    		
						  } 
						  						  
					  }
				  }
				  List<ZonaProtettaSpecieDTO> zoneProtetteDb = domandeEJB.getZoneProtetteSpecieByIdDomanda(nuovaDomandaForm.getIdDomanda());
				  model.addAttribute("zoneProtetteDb", zoneProtetteDb);
				  // Rimuovo le selezioni del form
				  nuovaDomandaForm.setIdZonaProtetta(null);
				  nuovaDomandaForm.setSpecieZP(null);
				  nuovaDomandaForm.setGenereZP(null);				  				  
			  }
		  } 
		  catch (Exception e) {
			  logger.error("-- Exception in duplicaDatiTabPassaporto ="+e.getMessage());
			  throw e;
		  }
		  finally{
			  logger.debug("END duplicaDatiTabPassaporto");  
		  }
	  }
	  	  
	  private void duplicaDatiTabAllegati(NuovaDomandaForm nuovaDomandaForm, Model model, UtenteDTO utente, Long idDomanda) throws Exception{
		  logger.debug("BEGIN duplicaDatiTabAllegati"); 
		  try {
			  // *** ---  Dati Tab Allegati --- ***
			  Long idDomandaPrec = nuovaDomandaForm.getIdDomandaPrecedente();
			  logger.debug("-- *** --- Dati Tab Allegati --- *** ");  
			  List<AllegatoDTO> allegatiList =  domandeEJB.getListaAllegatiDomanda(idDomandaPrec);
			  model.addAttribute("listaAllegati", allegatiList);
			  if(allegatiList != null){
				  logger.debug("--- numero di allegati della domanda precedente ="+allegatiList.size());
				  logger.debug("-- Salvo i dati degli Allegati sul db --"); 
				  domandeEJB.duplicaAllegati(nuovaDomandaForm.getIdDomanda(), idDomandaPrec, utente.getId());
				  
				  List<AllegatoDTO> allegatiNuovaDomanda =  domandeEJB.getListaAllegatiDomanda(nuovaDomandaForm.getIdDomanda());
				  model.addAttribute("listaAllegati", allegatiList);
			  }
		  } 
		  catch (Exception e) {
			  logger.error("-- Exception in duplicaDatiTabAllegati ="+e.getMessage());
			  throw e;
		  }
		  finally{
			  logger.debug("BEGIN duplicaDatiTabAllegati");
		  }
	  }
	  
	  
	  private void duplicaDatiTabGestione(NuovaDomandaForm nuovaDomandaForm, Model model, UtenteDTO utente, Long idDomanda) throws Exception{
		  logger.debug("BEGIN duplicaDatiTabGestione");
		  try {
			  // *** --- Dati Tab Gestione --- ***			  
			  logger.debug("-- *** --- Dati Tab Gestione --- *** ");  
			  // Cerco i dati del Tab Gestione della domanda precedente
			  Long idDomandaPrec = nuovaDomandaForm.getIdDomandaPrecedente();
			  DomandaDto dto = domandeEJB.getDettaglioGestioneByIdDomanda(idDomandaPrec);									
			  if(dto != null){
				  logger.debug("-- Setto nel form i dati Gestione --");
				  if(!nuovaDomandaForm.getIdTipoComunicazione().equals(CaronteConstants.ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP)){
					  nuovaDomandaForm.setNumeroProtocollo(dto.getNumeroProtocollo());
					  nuovaDomandaForm.setDataProtocollo(dto.getDataProtocollo()); 
				  } 
				  nuovaDomandaForm.setCodiceFitok(dto.getCodiceFitok());
				  nuovaDomandaForm.setTariffa(dto.getTariffa());
				  nuovaDomandaForm.setNote(dto.getNote());
				  nuovaDomandaForm.setIdIspettore(dto.getIdIspettore());
				  
				  nuovaDomandaForm.setIdSpedizioniere(dto.getIdSpedizioniere());
				  logger.debug("-- codiceRuop  ="+dto.getCodiceRuop());
				  nuovaDomandaForm.setCodiceRuop(dto.getCodiceRuop());
				  nuovaDomandaForm.setDataRegistrazioneRuop(dto.getDataRegistrazioneRuop());
				 
				  // -- Salvo i dati Gestione sul db --
				  logger.debug("-- Salvo i dati del Tab Gestione sul db --");
				  domandeEJB.aggiornaDatiGestione(nuovaDomandaForm, utente.getIdUtente());	
			  }	
		  } 
		  catch (Exception e) {
			  logger.error("-- Exception in duplicaDatiTabGestione ="+e.getMessage());
			  throw e;
		  }
		  finally{
			  logger.debug("BEGIN duplicaDatiTabGestione");
		  }
	  }
	  
	  /*
	   * Vengono duplicati tutti i dati della Domanda precedente
	   */
	  private void duplicaDatiDomandaVariazionRuop(NuovaDomandaForm nuovaDomandaForm, Model model) throws Exception{
		logger.debug("BEGIN duplicaDatiDomandaVariazionRuop");
		try {
			Long idDomanda = nuovaDomandaForm.getIdDomanda();
			Long idDomandaPrec = nuovaDomandaForm.getIdDomandaPrecedente();
			logger.debug("-- idDomanda ="+idDomanda);
			logger.debug("-- idDomandaPrec ="+idDomandaPrec);
			
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();
			logger.debug("-- ******* DUPLICO i dati dei Tab, con i dati della domanda Ruop precedente ****** --");			
			duplicaDatiTabAzienda(nuovaDomandaForm, model, utente, idDomanda);
			duplicaDatiTabCentriAziendali(nuovaDomandaForm, model, utente, idDomanda);
			duplicaDatiTabImport(nuovaDomandaForm, model, utente, idDomanda);
			duplicaDatiTabExport(nuovaDomandaForm, model, utente, idDomanda);
			duplicaDatiTabPassaporto(nuovaDomandaForm, model, utente, idDomanda);
			duplicaDatiTabGestione(nuovaDomandaForm, model, utente, idDomanda);

		} 
		catch (Exception e) {
		  logger.error("-- Exception in duplicaDatiDomandaVariazionRuop ="+e.getMessage());
		  throw e;			  
		}
		finally{
		  logger.debug("END duplicaDatiDomandaVariazionRuop");
		}
	  }
	  



/*
  * Per la cancellazione comunicazione all'interno di un flusso
  */
 @PreAuthorize("hasRoleImpExp('WRITE', #request)")
 @GetMapping(value = {"/variazione/{id}"})
 public String variazioneDomanda(@PathVariable Long id, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {

	 logger.debug("BEGIN variazioneDomanda");  
	 	// Note : aggiunga questa forzatura perchè se si arriva dall'avanza, il campo è valorizzato e non va bene
	    form.setIdDomanda(null);
	    
	    logger.debug("-- idDomanda precedente =" + id);
	    
	    // setto nel form idTipoComunicazioneSuccessiva
	     Long idTipoComunicazioneSucc = CaronteConstants.ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP;
	    logger.debug("---- idTipoComunicazioneSucc =" + idTipoComunicazioneSucc);
	    Long idStatoComunicazioneSucc = CaronteConstants.STATO_COMUNICAZIONE_BOZZA;
	    logger.debug("---- idStatoComunicazioneSucc =" + idStatoComunicazioneSucc);

	    logger.debug("-- setto idTipoComunicazione =" + idTipoComunicazioneSucc);
	    form.setIdTipoComunicazione(idTipoComunicazioneSucc);
	    
	    // setto nel form id stato : in bozza (prendendolo da configurazione DB)
	    form.setIdStatoComunicazione(idStatoComunicazioneSucc);

	    // setto nel form IdComunicazionePrecedente
	    logger.debug("-- idDomandaPrecedente nel form =" + id);
	    form.setIdDomandaPrecedente(id);

	    // precaricare nel form i dati anagrafici com quelli della domanda precedente
	    logger.debug("-- ** Precarico nel form i dati anagrafici com quelli della comunicazione precedente ** --");
	    DomandaDto dettDomanda = domandeEJB.getDettaglioAnagraficaByIdDomanda(id);	    	    
	    if (dettDomanda != null) {
	      /*
	       * Setto dati anagrafici
	       */
	      form.setCognome(dettDomanda.getCognome());
	      form.setNome(dettDomanda.getNome());
	      form.setCodFiscale(dettDomanda.getCodiceFiscale());
	      form.setDataNascita(dettDomanda.getDataNascita());

	      // Dati nascita
	      form.setDataNascita(dettDomanda.getDataNascita());
	      if (dettDomanda.getIdComuneNascita() == null) {
	        form.setNascitaEstera(true);
	        form.setDenomComuneEstNascita(dettDomanda.getDenomComuneEstNascita());
	      } else {
	        form.setNascitaEstera(false);
	        form.setIdProvinciaNascita(dettDomanda.getIdProvNascita());
	        form.setIdComuneNascita(dettDomanda.getIdComuneNascita());
	      }

	      // Dati residenza
	      if (dettDomanda.getIdComuneResidenza() == null) {
	        form.setResidenzaEstera(true);
	        form.setDenomComuneEstResid(dettDomanda.getDenomComuneEstResid());
	      } else {
	        form.setResidenzaEstera(false);
	        form.setIdProvinciaResidenza(dettDomanda.getIdProvResidenza());
	        form.setIdComuneResidenza(dettDomanda.getIdComuneResidenza());
	      }

	      form.setIndirizzo(dettDomanda.getIndirizzo());
	      form.setCap(dettDomanda.getCap());
	      form.setNumTelefono(dettDomanda.getTelefono());
	      form.setCellulare(dettDomanda.getCellulare());
	      form.setEmail(dettDomanda.getEmail());
	    }
	    
		form.setIdTipologia(domandeEJB.getTipologieByIdDomanda(id));
		
		DomandaDto domandaAzienda = domandeEJB.getDettaglioAziendaByIdDomanda(id);			
	    /*
    	 * Dati azienda
    	 */
    	form.setIdAzienda(domandaAzienda.getIdSpedizioniere());
    	form.setIdTipoAzienda(domandaAzienda.getIdTipoSpedizioniere());
    	form.setCodFiscaleAz(domandaAzienda.getCuaa());
    	form.setDenomAzienda(domandaAzienda.getDenomSpedizioniere());
    	form.setNomeAzienda(domandaAzienda.getNomeSped());
    	form.setCognomeAzienda(domandaAzienda.getCognomeSped());

    	form.setIdComuneSedeLegale(domandaAzienda.getIdComuneSped()); 
    	if(domandaAzienda.getIdComuneSped() != null)
    		form.setIdProvinciaSedeLegale(domandeEJB.getIdProvinciaByIdComune(domandaAzienda.getIdComuneSped()));
    	form.setCapSedeLegale(domandaAzienda.getCapSped());
    	form.setIndirizzoSedeLegale(domandaAzienda.getIndirizzoSped());
    	form.setNumTelefonoSedeLegale(domandaAzienda.getTelefonoSped());			
    	form.setNumCellulareSedeLegale(domandaAzienda.getCellSped());
    	form.setFaxSedeLegale(domandaAzienda.getFaxSped());
    	form.setEmailSedeLegale(domandaAzienda.getEmailSped());
    	form.setPecSedeLegale(domandaAzienda.getPecSped());
    	form.setPartitaIva(domandaAzienda.getPartitaIva());
    	form.setTipoSpedizioniereAltro(domandaAzienda.getTipoSpedizioniereAltro());
		  
	    logger.debug("setDatiTipoComunicazione");
	    setDatiTipoComunicazione(decodificheEJB.getTipoComunicazioneByPrimaryKey(idTipoComunicazioneSucc), form);
	    
	    logger.debug("***fine setter***");
	    
	    return datiDomanda(form, model, request);
	 
 }

    @PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = "/modulo/template/{idTipoModulo}", produces = { "application/octet-stream" })
	public void downloadTemplateModulo(@PathVariable Long idTipoModulo, Model model, HttpServletResponse response) {
		try {
			CarDTipoModulo tipoModulo = decodificheEJB.getTipoModuloByPrimaryKey(idTipoModulo);

			if (tipoModulo != null && !StringUtils.isEmpty(tipoModulo.getNomeFile())) {
				/*
				 * Il filename viene encodato per evitare vulnerabilità di
				 * response splitting
				 */
				scaricaFile("application/octet-stream",
						"attachment; filename=" + URLEncoder.encode(tipoModulo.getNomeFile(), "UTF-8"),
						tipoModulo.getModulo(), response);
			}
		} catch (BusinessException | IOException exc) {
			logger.error("Errore nel reperimento del template modulo", exc);
		}
	}

    @PreAuthorize("hasRoleImpExp('READ', #request)")
	@GetMapping(value = "/modulo/scarica/{idModulo}", produces = { "application/octet-stream" })
	public void downloadModulo(@PathVariable Long idModulo, Model model, HttpServletResponse response) {
		try {
			ModuloDTO modulo = domandeEJB.getModuloById(idModulo);

			if (modulo != null && !StringUtils.isEmpty(modulo.getNomeFile())) {
				/*
				 * Il filename viene encodato per evitare vulnerabilità di
				 * response splitting
				 */
				scaricaFile("application/octet-stream",
						"attachment; filename=" + URLEncoder.encode(modulo.getNomeFile(), "UTF-8"), modulo.getModulo(),
						response);
			}
		} catch (BusinessException | IOException exc) {
			logger.error("Errore nel reperimento del modulo", exc);
		}
	}
    @PreAuthorize("hasRoleImpExp('WRITE', #request)")
	@GetMapping(value = { "/inoltra" })
	public String inoltraNoFile(Model model, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm domandaForm, HttpServletRequest request) throws BusinessException {
		logger.debug("BEGIN inoltraNoFile");
		try {
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();
			Long idDomanda = domandaForm.getIdDomanda();
			logger.debug("-- idDomanda =" + idDomanda);
			
			//PROTOCOLLO: attivo con la costante sul DB			
			CarDCostante costante = decodificheEJB.getCostante(CaronteConstants.COSTANTE_ATTIVA_PROTOCOLLO);
			if (costante != null && costante.getValoreCostante().equalsIgnoreCase("S")) {
				logger.debug("--- Si DEVE chiamare il servizio di PROTOCOLLO");
				List<CarTModulo> listaModuliDB = domandeEJB.getModuloByIdDomanda(idDomanda);
				// per la protocallazione è necessario mandare il modulo compilato
				if (listaModuliDB != null && listaModuliDB.size() > 0) {
					logger.debug("-- e' presente un modulo sul db =" + listaModuliDB.get(0).getModulo().length);
					// nella domanda di cancellazione il modulo caricato e compilato e' uno solo
					if (listaModuliDB.get(0).getModulo().length > 0) {
						byte[] moduloCompilato = listaModuliDB.get(0).getModulo();
						String nomeFileModulo = listaModuliDB.get(0).getNomeFile();
						ProtocolloOutputDto protocolloDto = protocolloEJB.protocollaDomandaInoltrata(idDomanda, moduloCompilato, nomeFileModulo);
						logger.debug("--- Salvare i dati di protocollazione");
						domandeEJB.salvaProtocolloDomanda(idDomanda, protocolloDto, utente);						
					}
				}
			} else {
				logger.debug("-- Protocollazione non attiva, verificare costante DB");	
			}
			
			avanza(idDomanda, CaronteConstants.STATO_COMUNICAZIONE_INOLTRATA,CaronteConstants.DESC_STATO_COMUNICAZIONE_INOLTRATA.toLowerCase(), null, null);
			// solo dopo aver protocollato e avanzato lo stato invio mail di protocollo all'utente
			if (costante != null && costante.getValoreCostante().equalsIgnoreCase("S")) {
				try {
					domandeEJB.inviaMailDomandaProtocollata(idDomanda, utente.getId());
				} catch (Exception e) {
					addSuccessMessage("La domanda è stata inoltrata e portocollata correttamente");
					addErrorMessage("Errore nell'invio della mail di protocollo");
					logger.error("Exception in inoltraNoFile =" + e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			addErrorMessage("Errore durante l'inoltra della domanda");
			logger.error("Exception in inoltraNoFile =" + e.getMessage(), e);
		}
		return getRedirect("comunicazioni/elenco", request);
	}

 private boolean checkAbilitaCreaNuovaDomandaFlusso(Long idUtente, DomandaDto dettaglioDomanda, boolean controllaStato, NuovaDomandaForm form) throws BusinessException {		 
		return checkAbilitaCreaNuovaDomandaFlusso(idUtente, null, dettaglioDomanda, controllaStato, form);
 }
	  
	  
 private boolean checkAbilitaCreaNuovaDomandaFlusso(Long idUtente, Long idDomanda, DomandaDto dettaglioDomanda, boolean controllaStato, NuovaDomandaForm form) throws BusinessException {
	 logger.debug("BEGIN checkAbilitaCreaNuovaDomandaFlusso");
	 boolean statoSuccessivoOk = false;
	 boolean abilitaCreaDomanda = false;

	 if (dettaglioDomanda == null) {
		 if (idDomanda == null) {
			 return false;
		 }
		 dettaglioDomanda = domandeEJB.getDettaglioAnagraficaAziendaByIdDomanda(idDomanda);
	 }

	 logger.debug("controllaStato checkAbilitaCreaNuovaDomandaFlusso=" + controllaStato);
	 if (controllaStato) {
		 List<CarDStatoComunicazione> listaStatiSuccessivi = domandeEJB.getListaStatiDomandaSuccessivi(idUtente, dettaglioDomanda.getIdDomanda());

		 // Controllo se negli stati configurati su car_r_gruppo_stato_successivo c'è id_stato_comunicazione = 6 (CREA DOMANDA SUCCESSIVA)
		 for (CarDStatoComunicazione stato : listaStatiSuccessivi) {
			 Long idStatoSuccessivo = CaronteConstants.STATO_COMUNICAZIONE_CREA_DOMANDA_SUCCESSIVA;
			 if (idStatoSuccessivo.equals(stato.getIdStatoComunicazione())) {
				 statoSuccessivoOk = true;
				 logger.debug("Nella lista stati successivi comunicazione c'è CREA DOMANDA SUCCESSIVA");
				 break;
			 }
		 }
	 }

	 logger.debug("--- statoSuccessivoOk =" + statoSuccessivoOk);

	 String descrBreveTipoDomandaSuccessiva = domandeEJB.abilitaCreaNuovaDomandaFlusso(idUtente, dettaglioDomanda.getIdDomanda());
	 logger.debug("--- descrBreveTipoDomandaSuccessiva =" + descrBreveTipoDomandaSuccessiva);
	 if (!StringUtils.isEmpty(descrBreveTipoDomandaSuccessiva)) {
		 abilitaCreaDomanda = true;
		 form.setDescrBreveDomandaSuccessiva(descrBreveTipoDomandaSuccessiva);
	 }
	 
	 logger.debug("-- abilitaCreaDomanda ="+abilitaCreaDomanda);
	 logger.debug("-- statoSuccessivoOk ="+statoSuccessivoOk);
	 logger.debug("END checkAbilitaCreaNuovaDomandaFlusso");	
	 return abilitaCreaDomanda && statoSuccessivoOk;
 }
 
 @GetMapping(value = "/checkIfCentroAzExists_{idCentroAzSelezionato}_{indirizzoCentroAz}_{idComuneCentroAz}")
 @ResponseBody
	public String checkIfCentroAzExists(@PathVariable("idCentroAzSelezionato") String idCentroAzSelezionato,
			@PathVariable("indirizzoCentroAz") String indirizzoCentroAz,
			@PathVariable("idComuneCentroAz") String idComuneCentroAz, Model model,
			@ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, HttpServletRequest request)
			throws BusinessException {

		logger.debug("-- checkIfCentroAzExists indirizzo - idComune = " + indirizzoCentroAz + " - " + idComuneCentroAz);
		logger.debug("-- checkIfCentroAzExists form.getIdCentroAziendale(); = " + form.getIdCentroAziendale());
		logger.debug("-- checkIfCentroAzExists idCentroAzSelezionato = " + idCentroAzSelezionato);
		try {
			//ripristino lo slash che dava problemi nella url
			String indirizzoCaEscape = indirizzoCentroAz.replaceAll("-", "/");
			logger.debug("-- checkIfCentroAzExists indirizzoCentroAz " + indirizzoCentroAz);
			logger.debug("-- checkIfCentroAzExists indirizzoCaEscape " + indirizzoCaEscape);
			if (indirizzoCentroAz != null && idComuneCentroAz != null) {
				Long idCentroAz = null;

				logger.debug("-- idCentroAzSelezionato.compareTo(0)= " + idCentroAzSelezionato.compareTo("0"));
				if (idCentroAzSelezionato.compareTo("0") == 0) {
					// caso di inserimento di nuovo centro aziendale, non deve
					// essere presente un altro centro az allo stesso
					// indirizzo/comune
					idCentroAz = domandeEJB.checkIfCentroAzExists(Long.valueOf(idComuneCentroAz).longValue(),
							indirizzoCaEscape, form.getCodFiscaleAz());
				} else if (form.getIdDomanda() != null) {
					// stesso centro aziendale già presente nella domanda
					idCentroAz = domandeEJB.checkIfCentroAzExistsByIdDomanda(form.getIdDomanda(),
							Long.valueOf(idComuneCentroAz).longValue(), indirizzoCaEscape);
				}
				logger.debug("-- checkIfCentroAzExists idCentroAz = " + idCentroAz);
				if (idCentroAz != null) {
					return "KO";
				}
			}

		} catch (Exception exc) {
			logger.error("Eccezione in checkIfCentroAzExists", exc);
		}

		return "OK";
	}
 
	 /*
	  * Utilizzato per creare una domanda Passaporto, partendo da una Domanda Ruop Convalidata, legata allo stesso spedizioniere
	  * Vengono duplicati tutti i dati della Domanda Ruop che servono alla Domanda Passaporto (Tab Passaporto e Tab Gestione)
	  */
	 private void duplicaDatiDomandaRuop(NuovaDomandaForm nuovaDomandaForm, Model model) throws Exception{
		logger.debug("BEGIN duplicaDatiDomandaRuop");
		try {
			Long idDomanda = nuovaDomandaForm.getIdDomanda();
			Long idDomandaPrec = nuovaDomandaForm.getIdDomandaPrecedente();
			logger.debug("-- idDomanda Passaporto ="+idDomanda);
			logger.debug("-- idDomanda Ruop Prec ="+idDomandaPrec);
			
			UtenteDTO utente = SecurityUtils.getUtenteLoggato();
			logger.debug("-- ******* DUPLICO i dati dei Tab, con i dati della domanda Ruop precedente ****** --");		
			
			// commento il duplica azienda, perchè sto duplicando al salva del tab azienda  
			//duplicaDatiTabAzienda(nuovaDomandaForm, model, utente, idDomanda);
			
			// commento il duplica centri aziendali perchè il Tab Centri aziendali verrà visualizzato solo se non esiste una Domanda Ruop convalidata legata allo stesso idSpedizioniere
			//duplicaDatiTabCentriAziendali(nuovaDomandaForm, model, utente, idDomanda);
			
			// i dati di questi tab non devono essere duplicati perchè non vengono più indicati nella Domanda Passaporto che ha già una Domanda Ruop convalidata per lo stesso id_spedizioniere 
			//duplicaDatiTabImport(nuovaDomandaForm, model, utente, idDomanda);
			//duplicaDatiTabExport(nuovaDomandaForm, model, utente, idDomanda);
			
			duplicaDatiTabPassaporto(nuovaDomandaForm, model, utente, idDomanda);
			
			// non duplico gli allegati, si devono caricare i nuovi per il passaporto
			//duplicaDatiTabAllegati(nuovaDomandaForm, model, utente, idDomanda);
			
			// non duplico i dati del tab gestione perchè non servono per il passaporto
			//duplicaDatiTabGestione(nuovaDomandaForm, model, utente, idDomanda);	
		} 
		catch (Exception e) {
		  logger.error("-- Exception in duplicaDatiDomandaRuop ="+e.getMessage());
		  throw e;			  
		}
		finally{
		  logger.debug("END duplicaDatiDomandaRuop");
		}
	 }

	@GetMapping(value = "/isDomandaRuopConvPresente_{idSpedizioniere}")
	@ResponseBody
	public String isDomandaRuopConvPresente(@PathVariable("idSpedizioniere") Long idSpedizioniere, Model model)
			throws BusinessException {
		logger.debug("BEGIN isDomandaRuopConvPresente");
		String isDomandaRuopConvPresente = "N";
		logger.debug("-- idSpedizioniere =" + idSpedizioniere);
		if (idSpedizioniere != null) {
			Long idDomandaRuopConvalidata = domandeEJB.getIdDomandaRuopConvalidata(idSpedizioniere);
			logger.debug("-- idDomandaRuopConvalidata =" + idDomandaRuopConvalidata);
			if (idDomandaRuopConvalidata != null) {
				isDomandaRuopConvPresente = "S";
			}
		}
		logger.debug("END isDomandaRuopConvPresente");
		return isDomandaRuopConvPresente;
	}

	private String formattaPerProtocollazione(String testo) {
		//Le lettere maiuscole accentate danno errore in protocollazione, devono essere eliminate
		
		String formattato = testo.replace("À", "A'")
								 .replace("à", "A'")
								 .replace("È", "E'")
								 .replace("É", "E'")
								 .replace("è", "E'")
								 .replace("é", "E'")
								 .replace("Ì", "I'")
								 .replace("ì", "I'")
								 .replace("Ò", "O'")
								 .replace("ò", "O'")
								 .replace("Ù", "U'")
								 .replace("ù", "U'")
				 				 .replace("Ý", "Y");
		
		return formattato;
	}

	/*
	  * Per la cancellazione comunicazione all'interno di un flusso
	  */
	 @PreAuthorize("hasRoleImpExp('WRITE', #request)")
	 @GetMapping(value = {"/cancella/{id}"})
	 public String cancellaDomanda(@PathVariable Long id, @ModelAttribute("nuovaDomandaForm") NuovaDomandaForm form, BindingResult result, Model model, SessionStatus sessionStatus, HttpServletRequest request) throws BusinessException {

		 logger.debug("BEGIN cancellaDomanda");  
		 	// Note : aggiunga questa forzatura perchè se si arriva dall'avanza, il campo è valorizzato e non va bene
		    form.setIdDomanda(null);
		    
		    logger.debug("-- idDomanda precedente =" + id);
		    
		    // setto nel form idTipoComunicazioneSuccessiva
		     Long idTipoComunicazioneSucc = CaronteConstants.ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP;
		    logger.debug("---- idTipoComunicazioneSucc =" + idTipoComunicazioneSucc);
		    Long idStatoComunicazioneSucc = CaronteConstants.STATO_COMUNICAZIONE_BOZZA;
		    logger.debug("---- idStatoComunicazioneSucc =" + idStatoComunicazioneSucc);

		    logger.debug("-- setto idTipoComunicazione =" + idTipoComunicazioneSucc);
		    form.setIdTipoComunicazione(idTipoComunicazioneSucc);
		    
		    // setto nel form id stato : in bozza (prendendolo da configurazione DB)
		    form.setIdStatoComunicazione(idStatoComunicazioneSucc);

		    // setto nel form IdComunicazionePrecedente
		    logger.debug("-- idDomandaPrecedente nel form =" + id);
		    form.setIdDomandaPrecedente(id);

		    // precaricare nel form i dati anagrafici com quelli della domanda precedente
		    logger.debug("-- ** Precarico nel form i dati anagrafici com quelli della comunicazione precedente ** --");
		    DomandaDto dettDomanda = domandeEJB.getDettaglioAnagraficaByIdDomanda(id);	    	    
		    if (dettDomanda != null) {
		      /*
		       * Setto dati anagrafici
		       */
		      form.setCognome(dettDomanda.getCognome());
		      form.setNome(dettDomanda.getNome());
		      form.setCodFiscale(dettDomanda.getCodiceFiscale());
		      form.setDataNascita(dettDomanda.getDataNascita());

		      // Dati nascita
		      form.setDataNascita(dettDomanda.getDataNascita());
		      if (dettDomanda.getIdComuneNascita() == null) {
		        form.setNascitaEstera(true);
		        form.setDenomComuneEstNascita(dettDomanda.getDenomComuneEstNascita());
		      } else {
		        form.setNascitaEstera(false);
		        form.setIdProvinciaNascita(dettDomanda.getIdProvNascita());
		        form.setIdComuneNascita(dettDomanda.getIdComuneNascita());
		      }

		      // Dati residenza
		      if (dettDomanda.getIdComuneResidenza() == null) {
		        form.setResidenzaEstera(true);
		        form.setDenomComuneEstResid(dettDomanda.getDenomComuneEstResid());
		      } else {
		        form.setResidenzaEstera(false);
		        form.setIdProvinciaResidenza(dettDomanda.getIdProvResidenza());
		        form.setIdComuneResidenza(dettDomanda.getIdComuneResidenza());
		      }

		      form.setIndirizzo(dettDomanda.getIndirizzo());
		      form.setCap(dettDomanda.getCap());
		      form.setNumTelefono(dettDomanda.getTelefono());
		      form.setCellulare(dettDomanda.getCellulare());
		      form.setEmail(dettDomanda.getEmail());
		    }
		    
			form.setIdTipologia(domandeEJB.getTipologieByIdDomanda(id));
			
			DomandaDto domandaAzienda = domandeEJB.getDettaglioAziendaByIdDomanda(id);			
		    /*
	    	 * Dati azienda
	    	 */
	    	form.setIdAzienda(domandaAzienda.getIdSpedizioniere());
	    	form.setIdTipoAzienda(domandaAzienda.getIdTipoSpedizioniere());
	    	form.setCodFiscaleAz(domandaAzienda.getCuaa());
	    	form.setDenomAzienda(domandaAzienda.getDenomSpedizioniere());
	    	form.setNomeAzienda(domandaAzienda.getNomeSped());
	    	form.setCognomeAzienda(domandaAzienda.getCognomeSped());

	    	form.setIdComuneSedeLegale(domandaAzienda.getIdComuneSped()); 
	    	if(domandaAzienda.getIdComuneSped() != null)
	    		form.setIdProvinciaSedeLegale(domandeEJB.getIdProvinciaByIdComune(domandaAzienda.getIdComuneSped()));
	    	form.setCapSedeLegale(domandaAzienda.getCapSped());
	    	form.setIndirizzoSedeLegale(domandaAzienda.getIndirizzoSped());
	    	form.setNumTelefonoSedeLegale(domandaAzienda.getTelefonoSped());			
	    	form.setNumCellulareSedeLegale(domandaAzienda.getCellSped());
	    	form.setFaxSedeLegale(domandaAzienda.getFaxSped());
	    	form.setEmailSedeLegale(domandaAzienda.getEmailSped());
	    	form.setPecSedeLegale(domandaAzienda.getPecSped());
	    	form.setPartitaIva(domandaAzienda.getPartitaIva());
	    	form.setTipoSpedizioniereAltro(domandaAzienda.getTipoSpedizioniereAltro());
			  
		    logger.debug("setDatiTipoComunicazione");
		    setDatiTipoComunicazione(decodificheEJB.getTipoComunicazioneByPrimaryKey(idTipoComunicazioneSucc), form);
		    
		    logger.debug("***fine setter***");
		    
		    return datiDomanda(form, model, request);
		 
	 }
	 
	 @PreAuthorize("hasRoleImpExp('WRITE', #request)")
	 @GetMapping("/eliminaDomanda_{id}")
	 public String eliminaDomanda(@PathVariable Long id, @ModelAttribute("ricercaDomandaForm") RicercaDomandaForm form, Model model, HttpServletRequest request) throws BusinessException {
		 logger.debug("BEGIN eliminaDomanda");
		 
		 try{
			 UtenteDTO utente = SecurityUtils.getUtenteLoggato();	 
			 
			 logger.debug("-------- idDomanda da eliminare="+ id);
			 

		      if (utente.isSuperUser()) {
		    	  domandeEJB.eliminaDomandaByIdDomanda(id);
		    	  addSuccessMessage("Domanda eliminata");	
		      }
			 
			 /*

			// Carico i dati dei centri aziendali
			 List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(form.getIdDomanda());
			 model.addAttribute("centriAziendaliDb",centriAziendaliDb);
							 
			 // Carico i dati delle tipologie produttive
			 List<TipologiaProdSpecieDTO> tipologieProdDb = domandeEJB.getTipologieProdCentroAz(idCentroAzSel, form.getIdDomanda());
			 model.addAttribute("tipologieProdDb", tipologieProdDb);
							 
			 // Carico i dati dei siti produzione
			 List<SitoProduzioneDTO> sitiProduzioneDb = domandeEJB.getSitiProduzioneCentroAz(idCentroAzSel);
			 model.addAttribute("sitiProduzioneDb", sitiProduzioneDb);
			 
			 setDatiCentriAziendaliModel(model, form, utente);	
			 */			
		 }
		 catch (Exception e) {
			 logger.error("-- Exception in eliminaDomanda ="+e.getMessage());
			 addErrorMessage("Errore nell'eliminazione della domanda");
		 }
		 return getRedirect("autorizzazioni/comunicazioni/elenco");
	 }
	 
	 
}
