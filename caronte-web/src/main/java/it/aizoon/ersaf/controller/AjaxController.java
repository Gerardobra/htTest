package it.aizoon.ersaf.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.aizoon.ersaf.business.IComunicazioniEJB;
import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IDomandeEJB;
import it.aizoon.ersaf.business.IGeneriSpecieEJB;
import it.aizoon.ersaf.business.IRichiesteEJB;
import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.dto.CentroAziendaleDomandaDTO;
import it.aizoon.ersaf.dto.CentroAziendaleDto;
import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.DomandaDto;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.MerceRichiestaDto;
import it.aizoon.ersaf.dto.ModuloDTO;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.dto.TipologiaAttMaterialeDTO;
import it.aizoon.ersaf.dto.TipologiaDomandaDTO;
import it.aizoon.ersaf.dto.TipologiaProdSpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDAutoreEppo;
import it.aizoon.ersaf.dto.generati.CarDClasse;
import it.aizoon.ersaf.dto.generati.CarDComune;
import it.aizoon.ersaf.dto.generati.CarDMateriale;
import it.aizoon.ersaf.dto.generati.CarDProvincia;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDUnitaMisura;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaDomandaForm;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

/**
 * @author Ivan Morra
 */

@RestController
@RequestMapping(value = { "/ajax" })
public class AjaxController extends BaseController {

  @Autowired
  private IDecodificheEJB decodificheEJB = null;

  @Autowired
  private IRichiesteEJB richiesteEJB = null;

  @Autowired
  private IGeneriSpecieEJB generiSpecieEJB = null;

  @Autowired
  private IComunicazioniEJB comunicazioniEJB = null;
  
  @Autowired
  private IDomandeEJB domandeEJB = null;
  
  @Autowired
  private IUtenteEJB utenteEJB = null;
  

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getComuniProvincia")
  public ResponseEntity<String> getComuniProvincia(@RequestParam Long idProvincia) {
    List<CarDComune> result = null;

    try {
      result = decodificheEJB.getListaComuni(idProvincia);
    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getComuniProvincia", exc);
      result = new ArrayList<>();
    }

    System.out.println("\n\n\nRISULTATO: " + getJsonResult(result));

    return getJsonResult(result);
  }

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getUnitaMisuraTipoProdotto")
  public ResponseEntity<String> getUnitaMisuraTipoProdotto(@RequestParam Long idTipoProdotto) {
    CarDUnitaMisura result = null;

    try {
      result = decodificheEJB.getUnitaMisuraTipoProdotto(idTipoProdotto);
    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getUnitaMisuraTipoProdotto", exc);
      result = new CarDUnitaMisura();
    }

    return getJsonResult(result);
  }

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getUnitaMisuraProdotto")
  public ResponseEntity<String> getUnitaMisuraProdotto(@RequestParam Long idProdotto) {
    CarDUnitaMisura result = null;

    try {
      result = decodificheEJB.getUnitaMisuraProdotto(idProdotto);
    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getUnitaMisuraProdotto", exc);
      result = new CarDUnitaMisura();
    }

    return getJsonResult(result);
  }

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getTariffaTeorica")
  public ResponseEntity<String> getTariffaTeorica(@RequestParam Long idTipoRichiesta,
      @RequestParam Long idNazioneOrigine, @RequestParam Long idProdotto, @RequestParam Long idGenere,
      @RequestParam Long idSpecie, @RequestParam BigDecimal quantita) {
    String result = "";
    MerceRichiestaDto merceRichiesta = new MerceRichiestaDto();

    merceRichiesta.setIdNazioneOrigine(idNazioneOrigine);
    merceRichiesta.setIdProdotto(idProdotto);
    merceRichiesta.setIdGenere(idGenere);
    merceRichiesta.setIdSpecie(idSpecie);
    merceRichiesta.setQuantita(quantita);

    try {
      BigDecimal tariffa = richiesteEJB.getImportoPrevistoTariffa(idTipoRichiesta, merceRichiesta);

      result = CaronteUtils.formatBigDecimal(tariffa);
    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getTariffaTeorica", exc);
      result = "0,00";
    }

    return getJsonResult(result);
  }

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getListaGeneri")
  public ResponseEntity<String> getListaGeneri(@RequestParam String nomeGenere) {
    Map<String, String> result = new TreeMap<>();
    ResponseEntity<String> jsonResult = null;

    try {

      List<GenereDTO> listaGeneri = generiSpecieEJB.getListaGeneri(nomeGenere, CaronteConstants.CODICE_LINGUA_LATINO);

      for (GenereDTO genere : listaGeneri) {
        result.put(genere.getDenomGenere(), null);
      }

    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getListaGeneri", exc);
    }

    jsonResult = getJsonResult(result);

    System.out.println("\n\nLISTA GENERI JSON: " + jsonResult);

    return jsonResult;
  }

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getListaSpecieGenere")
  public ResponseEntity<String> getListaSpecieGenere(@RequestParam Long idGenere) {
    List<SpecieDTO> result = null;

    if (idGenere != null && idGenere.longValue() > 0) {
      try {
        result = generiSpecieEJB.getListaSpecieGenere(idGenere, LocaleContextHolder.getLocale().getLanguage());
      } catch (BusinessException exc) {
        logger.error("Eccezione in AjaxController.getListaSpecieGenere", exc);
      }
    }

    if (result == null) {
      result = new ArrayList<>();
    }

    System.out.println("\n\n\nRISULTATO: " + getJsonResult(result));

    return getJsonResult(result);
  }

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getListaSpecieDenomGenere")
  public ResponseEntity<String> getListaSpecieDenomGenere(@RequestParam String denomGenere,@RequestParam String idSpecieSel) {
    List<SpecieDTO> result = null;
    ResponseEntity<String> jsonResult = null;

    if (!StringUtils.isEmpty(denomGenere)) {
      try {
    	logger.debug("-- par denomGenere per getListaSpecieDenomGenere  ="+denomGenere);
    	logger.debug("-- par idSpecieSel per getListaSpecieDenomGenere  ="+idSpecieSel);
        result = generiSpecieEJB.getListaSpecieDenomGenere(denomGenere, CaronteConstants.CODICE_LINGUA_LATINO, idSpecieSel);
      } catch (BusinessException exc) {
        logger.error("Eccezione in AjaxController.getListaSpecieDenomGenere", exc);
      }
    }

    if (result == null) {
      result = new ArrayList<>();
    }

    jsonResult = getJsonResult(result);

    System.out.println("\n\n\nRISULTATO: " + jsonResult);

    return jsonResult;
  }

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getListaAutoriEppo")
  public ResponseEntity<String> getListaAutoriEppo(@RequestParam String nomeAutore) {
    Map<String, String> result = new TreeMap<>();
    ResponseEntity<String> jsonResult = null;

    try {

      List<CarDAutoreEppo> listaAutori = decodificheEJB.getListaAutoriEppo(nomeAutore);

      for (CarDAutoreEppo autore : listaAutori) {
        result.put(autore.getDescAutoreEppo(), null);
      }

    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getListaAutoriEppo", exc);
    }

    jsonResult = getJsonResult(result);

    System.out.println("\n\nLISTA AUTORI EPPO JSON: " + jsonResult);
    
    return jsonResult;
  }

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getDescClasseProdotto")
  public ResponseEntity<String> getDescClasseProdotto(@RequestParam Long idClasseProdotto) {
    String result = "";

    try {
      CarDClasse classeProdotto = decodificheEJB.getClasseProdotto(idClasseProdotto);

      if (classeProdotto != null) {
        result = classeProdotto.getDenomClasse();
      }
    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getDescClasseProdotto", exc);
    }

    return getJsonResult(result);
  }

  @PostMapping(value = "/accettaCookie")
  public void accettaCookie(HttpServletRequest request, HttpServletResponse response) {
    Cookie newCookie = new Cookie(CaronteConstants.NOME_COOKIE_HOMEPAGE, "OK");
    String path = request.getServletContext().getSessionCookieConfig().getPath();

    if (StringUtils.isEmpty(path)) {
      path = request.getContextPath();
    }

    newCookie.setPath(path);

    System.out.println("\n\nCONTEXT PATH SERVLET CONTEXT: " + request.getServletContext().getContextPath());
    System.out
        .println("\n\nSESSION COOKIE CONFIG PATH: " + request.getServletContext().getSessionCookieConfig().getPath());

    newCookie.setMaxAge(30 * 24 * 60 * 60);
    response.addCookie(newCookie);
  }

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getCentriAziendaliSpedizioniere")
  public ResponseEntity<String> getListaCentriAziendali(@RequestParam Long idSpedizioniere) {
    List<CentroAziendaleDto> result = new ArrayList<>();

    try {

      result = comunicazioniEJB.getListaCentriAziendali(idSpedizioniere);

    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getListaCentriAziendali", exc);
    }

    System.out.println("\n\nLISTA CENTRI AZIENDALI JSON: " + getJsonResult(result));

    return getJsonResult(result);
  }

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getDatiDenomGenere")
  public ResponseEntity<String> getDatiDenomGenere(@RequestParam String denomGenere) {
    GenereDTO result = null;

    try {
      result = generiSpecieEJB.getGenereDenominazione(denomGenere);
    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getDatiDenomGenere", exc);
      result = new GenereDTO();
    }

    return getJsonResult(result);
  }

  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getDatiDenomAutoreEppo")
  public ResponseEntity<String> getDatiDenomAutoreEppo(@RequestParam String nomeAutore) {
    CarDAutoreEppo result = null;

    try {
      result = decodificheEJB.getDatiDenomAutoreEppo(nomeAutore);
    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getDatiDenomAutoreEppo", exc);
      result = new CarDAutoreEppo();
    }

    return getJsonResult(result);
  }
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getListaSpedizionieriByDenomRuop")
  public ResponseEntity<String> getListaSpedizionieriByDenomRuop(@RequestParam String spedizioniereRuop) {
    List<CarTSpedizioniere> result = new ArrayList<>();

    try {
    	logger.debug("-- AjaxController spedizioniereRuop = " + spedizioniereRuop);
    	if (spedizioniereRuop != null ) {
    		result = comunicazioniEJB.getListaSpedizionieriByDenomRuop(spedizioniereRuop, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI);
    	} else {
    		result = decodificheEJB.getListaSpedizionieri(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI);
    	}
    		
    } catch (BusinessException exc) {
    	logger.error("Eccezione in AjaxController.getListaSpedizionieriByDenomRuop", exc);
    }

   // System.out.println("\n\nLISTA SPEDIZIONIERI JSON: " + getJsonResult(result));

    return getJsonResult(result);
  }
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getListaSpecieByIdTipologiaProd")
  public ResponseEntity<String> getListaSpecieDenomGenereByIdTipologiaProd(@RequestParam Long idTipologiaProdSel) {
    List<SpecieDTO> listaSpecie = null;
    Map<String, String> result = new TreeMap<>();
    ResponseEntity<String> jsonResult = null;

    if (idTipologiaProdSel > 0) {
      try {
    	logger.debug("-- idTipologiaProdSel getListaSpecieByIdTipologiaProd  ="+idTipologiaProdSel);
    	listaSpecie = generiSpecieEJB.getListaSpecieByIdVoce(idTipologiaProdSel);
      } catch (BusinessException exc) {
        logger.error("Eccezione in AjaxController.getListaSpecieByIdTipologiaProd", exc);
      }
    }

    if (listaSpecie == null) {
    	listaSpecie = new ArrayList<>();
    }
 
    jsonResult = getJsonResult(listaSpecie);

   // System.out.println("\n\n\nRISULTATO: " + jsonResult);

    return jsonResult;
  }
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getListaGenereByIdGruppoZP")
  public ResponseEntity<String> getListaGenereByIdGruppoZP(@RequestParam Long idGruppoZonaProtettaSel) {
    List<GenereDTO> listaGenere = null;
    ResponseEntity<String> jsonResult = null;

    if (idGruppoZonaProtettaSel > 0) {
      try {
    	logger.debug("-- idGruppoZonaProtettaSel getListaSpecieByIdTipologiaProd  ="+idGruppoZonaProtettaSel);
    	listaGenere = generiSpecieEJB.getListaGenereByIdGruppoZonaProtetta(idGruppoZonaProtettaSel);
      } catch (BusinessException exc) {
        logger.error("Eccezione in AjaxController.getListaSpecieByIdTipologiaProd", exc);
      }
    }

    if (listaGenere == null) {
    	listaGenere = new ArrayList<>();
    }
 
    jsonResult = getJsonResult(listaGenere);

    //System.out.println("\n\n\nRISULTATO: " + jsonResult);

    return jsonResult;
  }
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getListaSpecieByIdGruppoZPIdGenere")
  public ResponseEntity<String> getListaSpecieByIdGruppoZPIdGenere(@RequestParam Long idGruppoZonaProtettaSel, @RequestParam Long idGenereSel) {
    List<SpecieDTO> listaSpecie = null;
    ResponseEntity<String> jsonResult = null;

    if (idGruppoZonaProtettaSel > 0) {
    	try {
			logger.debug("-- idGruppoZonaProtettaSel getListaSpecieByIdGruppoZPIdGenere  ="+idGruppoZonaProtettaSel);
			listaSpecie = generiSpecieEJB.getListaSpecieByIdGruppoZPIdGenere(idGruppoZonaProtettaSel, idGenereSel);
			/* se non ho trovato nella tabella car_d_zona_protetta una specifica specie 
			allora recupero tutte le specie di quel genere*/
			if (listaSpecie.isEmpty()) {
		    	listaSpecie = generiSpecieEJB.getListaSpecieGenere(idGenereSel, "la");
			}   	
    	} catch (BusinessException exc) {
    		logger.error("Eccezione in AjaxController.getListaSpecieByIdGruppoZPIdGenere", exc);
    	}
    }

    if (listaSpecie == null) {
    	listaSpecie = new ArrayList<>();
    }
 
    jsonResult = getJsonResult(listaSpecie);

   // System.out.println("\n\n\nRISULTATO: " + jsonResult);

    return jsonResult;
  }
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getListaMateriliByIdTipoAttivita")
  public ResponseEntity<String> getListaMateriliByIdTipoAttivita(@RequestParam Long idTipoAttivitaSel) {
    List<CarDMateriale> listaMateriale = null;
    ResponseEntity<String> jsonResult = null;

    if (idTipoAttivitaSel > 0) {
      try {
    	logger.debug("-- idTipoAttivitaSel getListaMateriliByIdTipoAttivita  ="+idTipoAttivitaSel);
    	listaMateriale = decodificheEJB.getListaMaterialiByIdTipoAttivita(idTipoAttivitaSel);
      } catch (BusinessException exc) {
        logger.error("Eccezione in AjaxController.getListaMateriliByIdTipoAttivita", exc);
      }
    }

    if (listaMateriale == null) {
    	listaMateriale = new ArrayList<>();
    }
 
    jsonResult = getJsonResult(listaMateriale);

    //System.out.println("\n\n\nRISULTATO: " + jsonResult);

    return jsonResult;
  }
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getAttivitaMaterialiByIdDomanda")
  public ResponseEntity<String> getAttivitaMaterialiByIdDomanda(@RequestParam Long idDomanda) {
	List<TipologiaAttMaterialeDTO> listaAttMateriale = null;
    ResponseEntity<String> jsonResult = null;

    if (idDomanda > 0) {
      try {
    	logger.debug("-- idDomanda getAttivitaMaterialiByIdDomanda  ="+idDomanda);
    	listaAttMateriale = domandeEJB.getTipologieAttMateriale(idDomanda);
      } catch (BusinessException exc) {
        logger.error("Eccezione in AjaxController.getAttivitaMaterialiByIdDomanda", exc);
      }
    }

    if (listaAttMateriale == null) {
    	listaAttMateriale = new ArrayList<>();
    }
 
    jsonResult = getJsonResult(listaAttMateriale);

   // System.out.println("\n\n\nRISULTATO: " + jsonResult);

    return jsonResult;
  }
  
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getFlussoDomanda")
  public ResponseEntity<String> getFlussoDomanda(@RequestParam Long idDomanda) {
	logger.debug("BEGIN getFlussoDomanda");  
    List<DomandaDto> result = null;
    try {
      result = domandeEJB.getListaFlussoDomanda(idDomanda);
    } 
    catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getFlussoDomanda", exc);
      result = new ArrayList<>();
    }
    finally{
      logger.debug("END getFlussoDomanda");  	
    }

    System.out.println("\n\n\nRISULTATO: " + getJsonResult(result));
    return getJsonResult(result);
  }
  
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getGeneriByIdOrgNocivo")
  public ResponseEntity<String> getGeneriByIdOrgNocivo(@RequestParam Long idOrgNocivo) {
	logger.debug("BEGIN getGeneriByIdOrgNocivo");
    List<CodeDescriptionDTO> result = null;
    ResponseEntity<String> jsonResult = null;

    if (idOrgNocivo != null) {
      try {
    	logger.debug("-- idOrgNocivo  ="+idOrgNocivo);    	
        result = decodificheEJB.getGeneriByIdOrgNocivo(idOrgNocivo);
      } 
      catch (BusinessException exc) {
        logger.error("Eccezione in AjaxController.getGeneriByIdOrgNocivo", exc);
      }
    }

    if (result == null) {
      result = new ArrayList<>();
    }

    jsonResult = getJsonResult(result);

    System.out.println("\n\n\nRISULTATO: " + jsonResult);

    return jsonResult;
  }
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getSpecieByIdGeneri")
  public ResponseEntity<String> getSpecieByIdGeneri(@RequestParam String idGenereList) throws BusinessException{
	  logger.debug("BEGIN getGeneriByIdOrgNocivo");
	  List<CodeDescriptionDTO> result = null;
	  ResponseEntity<String> jsonResult = null;
	  try {
		  logger.debug("idGenereList ="+idGenereList);
		  if (idGenereList != null && !idGenereList.equals("")) {
			  List<String> idGenereListString = jsonStringToArray(idGenereList);
			  List<Long> idGenereListLong = new ArrayList<Long>();
			  if(idGenereListString != null && idGenereListString.size()>0){
				  for (int i = 0; i < idGenereListString.size(); i++) {
					String idGenereStr = idGenereListString.get(i);
					logger.debug("-- idGenereStr ="+idGenereStr);
					if(idGenereStr != null && !idGenereStr.equals("")){
					  Long idGenereL = Long.parseLong(idGenereStr);
					  logger.debug("-- idGenereL ="+idGenereL);
					  idGenereListLong.add(idGenereL);
					}  
					
				   }					  
			  }			  
			  if(idGenereListLong != null && idGenereListLong.size()>0){  
				  logger.debug("-- Ricerco le specie collegate ai generi");
				  result = decodificheEJB.getSpecieByIdGeneri(idGenereListLong);
			  }	      
		  }
	  } 
	  catch (Exception exc) {
		  logger.error("Eccezione in AjaxController.getSpecieByIdGeneri", exc);
	  }

	  if (result == null) {
		  result = new ArrayList<>();
	  }

	  jsonResult = getJsonResult(result);

	  System.out.println("\n\n\nRISULTATO: " + jsonResult);

	  return jsonResult;
  }
  
  private ArrayList<String> jsonStringToArray(String jsonString) throws JSONException {
	    logger.debug("BEGIN jsonStringToArray");
	    ArrayList<String> stringArray = new ArrayList<String>();
	    JSONArray jsonArray = new JSONArray(jsonString);

	    for (int i = 0; i < jsonArray.length(); i++) {
	        stringArray.add(jsonArray.getString(i));
	    }

	    logger.debug("END jsonStringToArray");
	    return stringArray;
  }
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/setDataAccettazionePrivacy")
  public ResponseEntity<String> setDataAccettazionePrivacy(@RequestParam Long idUtente) {
	  
	logger.debug(" +-+ Dentro setDataAccettazionePrivacy");
	logger.debug("idUtente= " + idUtente);
    String result = "";
    

    try {
    	utenteEJB.setDataAccettazionePrivacy(idUtente);
    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.setDataAccettazionePrivacy", exc);
      result = "errore";
    }

    return getJsonResult(result);
  }
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getListaSpecieByIdGenere")
  public ResponseEntity<String> getListaSpecieByIdGenere(@RequestParam Long idGenere) {
    List<SpecieDTO> listaSpecie = null;
    Map<String, String> result = new TreeMap<>();
    ResponseEntity<String> jsonResult = null;
    logger.debug("-- idGenere= "+idGenere);

    if (idGenere > 0) {
      try {
    	logger.debug("-- idGenere getListaSpecieByIdGenere  ="+idGenere);
    	listaSpecie = generiSpecieEJB.getListaSpecieGenere(idGenere, CaronteConstants.CODICE_LINGUA_LATINO);
      } catch (BusinessException exc) {
        logger.error("Eccezione in AjaxController.getListaSpecieByIdGenere", exc);
      }
    }

    if (listaSpecie == null) {
    	listaSpecie = new ArrayList<>();
    }
 
    jsonResult = getJsonResult(listaSpecie);

   // System.out.println("\n\n\nRISULTATO: " + jsonResult);

    return jsonResult;
  }
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getElencoTabMancanti_{idDomanda}")
  @ResponseBody
  public String getElencoTabMancanti(@PathVariable("idDomanda") Long idDomanda)
		  throws BusinessException {
	  logger.debug("BEGIN getElencoTabMancanti ajax");

	  logger.debug("-- idDomanda ="+idDomanda);
	  DomandaDto dettaglioDomanda = domandeEJB.getDettaglioAnagraficaAziendaByIdDomanda(idDomanda);	

	  // --- Controllo sui dati obbligatori a db per l'inoltra -----------------
	  /*
	   * ---------- Si può inoltrare la domanda se :
	   * 
	   * 1) sono stati inseriti sul db i 'Dati anagrafici' (controllo su Codice fiscale)
	   * 
	   * 2) sono stati inseriti sul db i 'Dati azienda' (controllo su cuaa)
	   * 
	   * 3) è stata scelta almeno una tipologia (presenza di almeno un record in CAR_R_DOMANDA_TIPOLOGIA con idDomanda)
	   * 
	   * 4) in base alla Tipologia scelta :
	   * 	-- id_tipologia = 1 (import)
			-- id_tipologia = 2 (export)
			-- id_tipologia = 3 (vivai)
			-- id_tipologia = 4 (richiesta passaporto)

	   *   - se import : controllare che siano stati inseriti sul db i dati di import
	   *       controllare che sia stata salvata almeno una Tipologia produttiva tra quelle previste in 'Le importazioni riguardano le seguenti tipologie di merci/produzioni':
	   *        delle voci previste su CAR_D_VOCE, controllare che ce ne sia almeno una in CAR_T_VOCE_UTENTE
	   *       
	   *   - se export : controllare che siano stati inseriti sul db i dati di export
	   *      controllare che sia stata salvata almeno una Tipologia tra quelle previste in 'Le esportazioni riguardano le seguenti tipologie * : ':
	   *        delle voci previste su CAR_D_VOCE, controllare che ce ne sia almeno una in CAR_T_VOCE_UTENTE
	   *   
	   *   - se passaporto : controllare che siano stati inseriti sul db i dati del passaporto
	   *     controllare che sia stata salvata almeno una Tipologia tra quelle previste in 'Selezionare le categorie di interesse: '
	   *       delle voci previste su CAR_D_VOCE, controllare che ce ne sia almeno una in CAR_T_VOCE_UTENTE
	   * 
	   *       
	   * 5) se per il tipo domanda, sono previsti degli Allegati obbligatori :
	   *      verificare che siano stati salvati i relativi allegati 
	   *      
	   *         
	   * 6) se sono previsti dei moduli, verificare che siano stati caricati
	   *
	   */
	  boolean datiAnagraficiPresenti = false;
	  boolean datiAziendaPresenti = false;
	  boolean datiTipologiaPresenti = false;
	  boolean datiImportPresenti = false;
	  boolean datiExportPresenti = false;
	  boolean datiPassaportoPresenti = false;
	  boolean datiAllegatiPresenti = false;
	  boolean datiModuliPresenti = true;
	  boolean datiCentriAziendaliPresenti = false;

	  boolean datiAttivitaAziendaPresenti = false;
	  boolean datiTipologiaProduttivaCentriAz = true;

	  // Dati obbligatori Tab Import
	  boolean datiTipologiaImport = false;
	  boolean datiContinentiImport = false; 
	  boolean datiZoneProtetteImport = false;
	  
	  // Dati obbligatori Tab Export
	  boolean datiTipologiaExport = false;
	  boolean datiCheckExportRiguarda = false;
	  boolean datiContinentiExport = false; 
	  

	  // 1) Controllo presenza Dati Anagrafici
	  logger.debug("-- 1) Controllo presenza Dati Anagrafici");
	  if (!StringUtils.isEmpty(dettaglioDomanda.getCodiceFiscale())) {
		  datiAnagraficiPresenti = true;
		  // 2) Controllo presenza Dati Azienda
		  logger.debug("-- 2) Controllo presenza Dati Azienda");
		  if(!StringUtils.isEmpty(dettaglioDomanda.getPartitaIva())){
			  datiAziendaPresenti = true;		
			  logger.debug("-+ idTipoComunicazione= " + dettaglioDomanda.getIdTipoComunicazione());
			  // Solo per Domande non Passaporto : controllo presenza tipologia attività (Tab dati azienda)
			  if(dettaglioDomanda.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
				  logger.debug("-- Controllo presenza tipologia attività (Tab dati azienda)");
				  datiAttivitaAziendaPresenti = !domandeEJB.getTipologieAttMateriale(idDomanda).isEmpty();
			  }	
			  // per le Domande Passaporto forzo a true (non commpaiono sulla pagina questi dati)
			  else{
				  datiAttivitaAziendaPresenti =  true;
			  }

			  // 3) Controllo dati Tipologie domanda
			  boolean tabImport = false;
			  boolean tabExport = false;
			  boolean tabPassaporto = false;

			  // Solo per Domanda non di Passaporto
			  if(dettaglioDomanda.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
				  logger.debug("-- 3) Controllo dati tipologie Domande");
				  TipologiaDomandaDTO tipologiaDto = domandeEJB.getDettTipologiaDomanda(dettaglioDomanda.getIdDomanda());						
				  if(null != tipologiaDto && tipologiaDto.getTipologieDomandaList() != null && tipologiaDto.getTipologieDomandaList().size()>0){
					  logger.debug("-- tipologiaDto.getTipologieDomandaList().size() = "+tipologiaDto.getTipologieDomandaList().size());												
					  datiTipologiaPresenti = true;
					  // Controllo quali tipologie domanda sono state salvate
					  logger.debug("-- Controllo quali tipologie domanda sono state salvate");

					  for (int i = 0; i < tipologiaDto.getTipologieDomandaList().size(); i++) {
						  Long idTipologia = tipologiaDto.getTipologieDomandaList().get(i).getIdTipologia();
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
					  // Se c'è il Tab Import					 
					  if(tabImport){
						  logger.debug("-- 4a) Controllo dati obbligatori Tab Import");						  
						  // controllare che sia stata salvata almeno una Tipologia produttiva tra quelle previste in 'Le importazioni riguardano le seguenti tipologie di merci/produzioni':
						  //-- Tipologia produttiva
						  List<TipologiaProdSpecieDTO> tipologieProdDbImport = domandeEJB.getTipologieProdByIdDomanda(dettaglioDomanda.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 1L);
						  if(null != tipologieProdDbImport && tipologieProdDbImport.size()>0){
							datiTipologiaImport = true;
						    // -- Zone protette							
							logger.debug("-- Ricerco i dati dei radio Zone protette");
							Long[] idVoceRadioZoneProtetteImport = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 3L, idDomanda);
							// -- Continenti							
							logger.debug("-- Ricerco i dati dei check Continenti Import");
							Long[] idVoceCheckContinentiImp = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 2L, idDomanda);
							if( null != idVoceRadioZoneProtetteImport && idVoceRadioZoneProtetteImport.length>0){
								datiZoneProtetteImport = true;								
							}
							if(null != idVoceCheckContinentiImp && idVoceCheckContinentiImp.length>0){
								datiContinentiImport = true;
							}
							logger.debug("-- datiZoneProtetteImport ="+datiZoneProtetteImport);
							logger.debug("-- datiContinentiImport ="+datiContinentiImport);							
						  }			    		  
						  if(datiTipologiaImport && datiZoneProtetteImport && datiContinentiImport){
								datiImportPresenti = true;
						  }
					  }
					  else{
						  datiImportPresenti = true;
					  }

					  // Se c'è il Tab Export
					  if(tabExport){
						  logger.debug("-- 4b) Controllo dati obbligatori Tab Export");
						  // controllare che sia stata salvata almeno una Tipologia tra quelle previste in 'Le esportazioni riguardano le seguenti tipologie * : ':
						  List<TipologiaProdSpecieDTO> tipologieProdDbExport = domandeEJB.getTipologieProdByIdDomanda(dettaglioDomanda.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 3L);
						  if(null != tipologieProdDbExport && tipologieProdDbExport.size()>0){
							  datiTipologiaExport = true;
							 
							  // Check 'Le esportazioni riguardano principalmente'
							  Long[] idVoceCheckEsportazioni = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 1L, idDomanda);
							  if (null != idVoceCheckEsportazioni && idVoceCheckEsportazioni.length > 0) {
								  datiCheckExportRiguarda = true;
							  }
							  // Check Continenti Export
							  Long[] idVoceCheckContinentiExp = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 2L, idDomanda);
							  if (null != idVoceCheckContinentiExp && idVoceCheckContinentiExp.length > 0) {
								  datiContinentiExport = true;
							  }
							  
						  }
						  if (datiTipologiaExport && datiCheckExportRiguarda && datiContinentiExport) {
							  datiExportPresenti = true;
						  }
					  }
					  else{
						  datiExportPresenti = true;	
					  }

					  // Se c'è il Tab Passaporto
					  if(tabPassaporto){
						  logger.debug("-- 4c) Controllo dati obbligatori Tab Passaporto Domanda Ruop");
						  // controllare che sia stata salvata almeno una Tipologia tra quelle previste in 'Selezionare le categorie di interesse: '
						  List<TipologiaProdSpecieDTO> tipologieProdDbPass = domandeEJB.getTipologieProdByIdDomanda(dettaglioDomanda.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 4L);
						  if(null != tipologieProdDbPass && tipologieProdDbPass.size()>0){
							  datiPassaportoPresenti = true;
							  // Controllare che sia stata selezionata un radio tra 'Prima richiesta' o 'Aggiornamento'
							  Long idVoceSel = null;							 
							  logger.debug("-- Ricerco se è stato selezionato 'Prima richiesta' o 'Aggiornamento'");
							  Long[] idVoceRadioTipoRichiestaArr = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 1L, idDomanda);
							  if (idVoceRadioTipoRichiestaArr != null && idVoceRadioTipoRichiestaArr.length > 0) {	  
								  for (int i = 0; i < idVoceRadioTipoRichiestaArr.length; i++) {				    				   		        
									  idVoceSel = idVoceRadioTipoRichiestaArr[i];
									  logger.debug("-- idVoceSel ="+idVoceSel);				    				                   
								  }
							  }
							  if(idVoceSel == null){
								  datiPassaportoPresenti = false;
							  }
						  }
					  }
					  else{
						  datiPassaportoPresenti = true;	
					  }				    		
				  }
				  /* Ramo previsto per gestire il caso di nessuna tipologia domanda presenti mostriamo comunque il tasto inoltra :  
				   * ci sono aziende che gestiscono solo scatolame										
				   */
				  else {							
					  logger.debug("-- nel caso non ci fossero tipologie presenti mostriamo comunque il tasto inoltra");
					  datiTipologiaPresenti = true;
					  datiImportPresenti = true;
					  datiExportPresenti = true;	
					  datiPassaportoPresenti = true;	
				  }
			  }


			  /* Nel caso di Domanda Passaporto : setto tab Passaporto a true (non ci sono dati salvati su car_r_domanda_tipologia, ma si devono fare i controlli
			   *  per i dati obbligatori previsti per il tab Passaporto
			   */		    		
			  if(dettaglioDomanda.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
				  // Per la Domanda Passaporto questi dati non vengono compilati, forzo a true le variabili controllate in fondo
				  datiTipologiaPresenti = true;
				  datiImportPresenti = true;
				  datiExportPresenti = true;	

				  logger.debug("-- 4c) Controllo dati obbligatori Tab Passaporto Domanda Passaporto");
				  // controllare che sia stata salvata almeno una Tipologia tra quelle previste in 'Selezionare le categorie di interesse: '
				  List<TipologiaProdSpecieDTO> tipologieProdDbPass = domandeEJB.getTipologieProdByIdDomanda(dettaglioDomanda.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 4L);
				  if(null != tipologieProdDbPass && tipologieProdDbPass.size()>0){
					  datiPassaportoPresenti = true;
					  // Controllare che sia stata selezionata un radio tra 'Prima richiesta' o 'Aggiornamento'
					  Long idVoceSel = null;
					  logger.debug("-- Ricerco se è stato selezionato 'Prima richiesta' o 'Aggiornamento'");
					  Long[] idVoceRadioTipoRichiestaArr = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 1L, idDomanda);
					  if (idVoceRadioTipoRichiestaArr != null && idVoceRadioTipoRichiestaArr.length > 0) {	  
						  for (int i = 0; i < idVoceRadioTipoRichiestaArr.length; i++) {				    				   		        
							  idVoceSel = idVoceRadioTipoRichiestaArr[i];
							  logger.debug("-- idVoceSel ="+idVoceSel);				    				                   
						  }       
					  }
					  if(idVoceSel == null){
						  datiPassaportoPresenti = false;
					  }
				  }
			  }





			  // 5) Controllo se per il tipo domanda, sono previsti degli Allegati obbligatori sul db, in caso positivo, controllo che siano stati allegati i tipi allegati che sono obbligatori
			  logger.debug("-- Controllo se per il tipo domanda, sono previsti degli Allegati obbligatori sul db, in caso positivo, controllo che siano stati allegati i tipi allegati che sono obbligatori");
			  datiAllegatiPresenti = domandeEJB.checkAllegatiByIdDomanda(dettaglioDomanda.getIdDomanda());
			  logger.debug("-- datiAllegatiPresenti ="+datiAllegatiPresenti);


			  /* *** Controllo per gli allegati : 
			   * -- Domande diverse da Domanda Passaporto : 
			   *  se è stata scelta la tipologia 'Richiesta Passaporto', è obbligatorio anche l'id_tipo_allegato = 5  ('Scansione Marca da Bollo da 16 euro) (gestito da codice)
			   *  
			   *  -- Domande di Passaporto:																									
						- caso in cui non sia presente una Domanda Ruop convalidata per la stessa azienda 
								(caso di dati importati, di aziende che hanno già fatto Domanda Ruop al di fuori di Caronte)
							    (allegati obbligatori per la Domanda Ruop e allegati obbligatori per il Passaporto):						
							o	Scansione Marca da Bollo da 16 euro (obbligatorietà gestita da db)
							o	'Autorizzazione Ruop rilasciata dalla regione di competenza' (obbligatorietà gestita sul codice, non da db) :
							      se la provincia della sede legale dell'azienda dell'utente della domanda è fuori regione Lombardia
			   */


			  // - CASO Domande diverse da Domanda Passaporto e tabPassaporto compilato 
			  if(dettaglioDomanda.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO && tabPassaporto){
				  logger.debug("-- Domanda diversa da Domanda Passaporto, se è stata scelta la tipologia 'Richiesta Passaporto', è obbligatorio anche l'id_tipo_allegato = 5  'Scansione Marca da Bollo da 16 euro");
				  Long countAllegatiPassaporto = domandeEJB.countAllegatiByIdDomandaIdTipoAllegato(dettaglioDomanda.getIdDomanda(), CaronteConstants.ID_TIPO_ALLEGATO_MARCA_DA_BOLLO);
				  logger.debug("-- countAllegatiPassaporto = "+countAllegatiPassaporto);
				  if(countAllegatiPassaporto == 0){
					  datiAllegatiPresenti = false;
				  }
				  logger.debug("-- datiAllegatiPresenti ="+datiAllegatiPresenti);
			  }
			  // Controlli per Domande di Passaporto per Allegato : 'Scansione Marca da Bollo da 16 euro'
			  /* else if(form.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
		    			logger.debug("-- Domanda Passaporto, controllo se è obbligatorio l'allegato 'Scansione Marca da Bollo da 16 euro'");
		    			boolean marcaDaBolloObbligatorio = false;
		    			if(form.isDomandaRuopConvalidataPresente()){
		    				logger.debug("-- Caso Allegati domandaRuopConvalidataPresente");
		    				// Controllo se per la Domanda Ruop Convalidata era stato compilato il tab Passaporto
		    				logger.debug("-- Controllo se per la Domanda Ruop Convalidata era stato compilato il tab Passaporto");
		    				TipologiaDomandaDTO tipologiaDomanda = domandeEJB.getDettTipologiaDomanda(form.getIdDomandaPrecedente());
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
		    					marcaDaBolloObbligatorio = true;
		    				}
		    			}

		    			//  In questo caso non sappiamo cos'era stato compilato sulla Domanda Ruop importata o fuori Caronte, quindi controlliamo se l'utente ha checkato :
		    			//  'Prima richiesta(82) o Aggiornamento'(83) 		    			  		    			    		 
		    			else{    			 
		    				logger.debug("-- Caso domandaRuopConvalidata NON Presente - DATI IMPORTATI");				
		    				// Ricerco se è stato selezionato 'Prima richiesta' o 'Aggiornamento'
		    				logger.debug("-- Ricerco se è stato selezionato 'Prima richiesta' o 'Aggiornamento'");
		    				Long[] idVoceRadioTipoRichiesta = domandeEJB.getVociUtenteByIdTipoModelloGruppo(CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 1L, form.getIdDomanda());
		    				if(idVoceRadioTipoRichiesta != null && idVoceRadioTipoRichiesta.length>0){
		    					Long voceRadioSel = idVoceRadioTipoRichiesta[0];
		    					logger.debug("-- voceRadioSel ="+voceRadioSel);
		    					if(voceRadioSel.longValue() == CaronteConstants.PRIMA_RICHIESTA_PASSAPORTO.longValue()){
		    						marcaDaBolloObbligatorio = true;
		    					}
		    				}
		    			}

		    			logger.debug("-- marcaDaBolloObbligatorio ="+marcaDaBolloObbligatorio);
		    			if(marcaDaBolloObbligatorio){
		    				Long countAllegatiPassaporto = domandeEJB.countAllegatiByIdDomandaIdTipoAllegato(dettaglioDomanda.getIdDomanda(), CaronteConstants.ID_TIPO_ALLEGATO_MARCA_DA_BOLLO);
			    			logger.debug("-- countAllegatiPassaporto = "+countAllegatiPassaporto);
			    			if(countAllegatiPassaporto == 0){
			    				datiAllegatiPresenti = false;
			    			}
			    			logger.debug("-- datiAllegatiPresenti 2 ="+datiAllegatiPresenti);
		    			}
		    		}*/



			  /* 
			   * *** GESTIONE ALLEGATO 'Autorizzazione Ruop rilasciata dalla regione di competenza' *****
			   * 
			   *  - Caso Domanda diversa da Domanda Passaorto :
			   *  se la provincia della sede legale dell'azienda dell'utente della domanda è fuori regione Lombardia, 
			   *  devo richiedere anche l'Allegato 'Autorizzazione Ruop rilasciata dalla regione di competenza', altrimenti no
			   *  
			   *  - Caso Domanda Passaporto :
			   *  questo controllo deve essere effettuato solo nel caso in cui non sia presente una Domanda Ruop convalidata per la stessa azienda
			   */

			  boolean domandaRuopConvalidataPresente = false;	
			  // Nel caso di Domanda Passaporto, controllo se c'è una domanda ruop convalidata	
			  if(dettaglioDomanda.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
				  Long idSpedizioniere = dettaglioDomanda.getIdSpedizioniere();
				  logger.debug("-- idSpedizioniere Domanda Passaporto ="+idSpedizioniere);
				  Long idDomandaRuopConvalidata = domandeEJB.getIdDomandaRuopConvalidata(idSpedizioniere);
				  logger.debug("-- idDomandaRuopConvalidata ="+idDomandaRuopConvalidata);					 
				  if(idDomandaRuopConvalidata != null){
					  domandaRuopConvalidataPresente = true;
				  }
				  logger.debug(" -- domandaRuopConvalidataPresente ="+domandaRuopConvalidataPresente);
			  }					   


			  if(dettaglioDomanda.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO ||
					  (dettaglioDomanda.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO && !domandaRuopConvalidataPresente)
					  ){	    				    		
				  logger.debug("-- CASO obbligatorio di tipo Allegato 6 (Autorizzazione Ruop rilasciata dalla regione di competenza)");
				  // Controllo se l'azienda è fuori regione Lombardia
				  boolean aziendaFuoriRegione = false;
				  Long idAziendaDomanda = dettaglioDomanda.getIdSpedizioniere();
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
				  // Il tipo di Allegato 6 è obbligatorio solo per la Domanda Ruop, non per la Domanda di Cancellazione e solo in alcuni casi per la Domanda di Passaporto
				  if(aziendaFuoriRegione){
					  logger.debug("-- azienda fuori regione, controllo se è stato inserito il tipo Allegato 6");
					  Long countAllegatiAutorizzazioneRuop = domandeEJB.countAllegatiByIdDomandaIdTipoAllegato(dettaglioDomanda.getIdDomanda(), CaronteConstants.ID_TIPO_ALLEGATO_AUTORIZZAZIONE_RUOP);
					  logger.debug("-- countAllegatiAutorizzazioneRuop = "+countAllegatiAutorizzazioneRuop);
					  if(countAllegatiAutorizzazioneRuop == 0){
						  datiAllegatiPresenti = false;
					  }
					  logger.debug("-- datiAllegatiPresenti 3 ="+datiAllegatiPresenti);
				  }			       	
			  }  				       	
		  }
	  }
	  // 6) Controllo presenza Moduli
	  logger.debug("-- 6) Controllo presenza Moduli");
	  List<ModuloDTO> listaModuli = decodificheEJB.getListaModuliComunicazione(idDomanda);
	  if(listaModuli != null){
		  for(ModuloDTO modulo:listaModuli){
			  if(modulo.getIdModulo() == null){
				  datiModuliPresenti = false;
			  }
		  }
	  }
	  // Controllo da fare solo per Domande diverse da Domanda Passaporto
	  if(dettaglioDomanda.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
		  //7) Controllo presenza Centri Aziendali
		  logger.debug("-- 7) Controllo presenza Centri Aziendali");
		  List<CentroAziendaleDomandaDTO> centriAziendaliDb = domandeEJB.getCentriAziendaliByIdDomanda(idDomanda);
		  if((null != centriAziendaliDb && centriAziendaliDb.size()>0) || (dettaglioDomanda.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP)){
			  datiCentriAziendaliPresenti = true;
		  }
		  // Controllo presenza di almeno una tipologia produttiva per ogni centro aziendale in Domanda
		  logger.debug("-- Controllo presenza di almeno una tipologia produttiva per ogni centro aziendale in Domanda");
		  List<CentroAziendaleDomandaDTO> centriAziendali = domandeEJB.getCentriAziendaliByIdDomanda(idDomanda);
		  for(CentroAziendaleDomandaDTO ca : centriAziendali){
			  if(domandeEJB.getTipologieProdCentroAz(ca.getIdCentroAziendale(), idDomanda).isEmpty()){
				  datiTipologiaProduttivaCentriAz = false;
			  }
		  }
	  }
	  else{
		  datiCentriAziendaliPresenti = true;
	  }

	  logger.debug("*** datiAnagraficiPresenti =" + datiAnagraficiPresenti);
	  logger.debug("*** datiAziendaPresenti =" + datiAziendaPresenti);
	  logger.debug("*** datiTipologiaPresenti =" + datiTipologiaPresenti);
	  logger.debug("*** datiImportPresenti =" + datiImportPresenti);
	  logger.debug("*** datiExportPresenti =" + datiExportPresenti);
	  logger.debug("*** datiPassaportoPresenti =" + datiPassaportoPresenti);
	  logger.debug("*** datiAllegatiPresenti =" + datiAllegatiPresenti);
	  logger.debug("*** datiModuliPresenti =" + datiModuliPresenti);
	  logger.debug("*** datiCentriAziendaliPresenti =" + datiCentriAziendaliPresenti);
	  logger.debug("*** datiAttivitaAziendaPresenti =" + datiAttivitaAziendaPresenti);
	  logger.debug("*** datiTipologiaProduttivaCentriAz =" + datiTipologiaProduttivaCentriAz);

	  StringBuilder tabMancanti = new StringBuilder();

	  if(!datiAnagraficiPresenti){
		  tabMancanti.append("</br> - Compilare tutto il tab Anagrafica e salvare cliccando sul tasto salva in fondo alla pagina");
		  logger.debug("*** tabMancante: = Anagrafica");
	  }
	  if(!datiAziendaPresenti || !datiAttivitaAziendaPresenti || !datiTipologiaPresenti){
		  tabMancanti.append("</br> - Compilare tutto il tab Dati Azienda e salvare cliccando sul tasto salva in fondo alla pagina");
		  logger.debug("*** tabMancante: = Dati Azienda");
	  }
	  if(!datiCentriAziendaliPresenti || !datiTipologiaProduttivaCentriAz){
		  tabMancanti.append("</br> - Compilare tutto il tab Centri Aziendali e salvare cliccando sul tasto salva in fondo alla pagina");
		  logger.debug("*** tabMancante: = Centri Aziendali");
	  }

	  //senza questo if l'utente potrebbe vedere un messaggio errato in domanda passaporto
	  if(dettaglioDomanda.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){			
		  if(datiTipologiaPresenti){
			  if(!datiImportPresenti){
				  tabMancanti.append("</br> - Compilare tutto il tab Import e salvare cliccando sul tasto salva in fondo alla pagina");
				 /* if(!datiTipologiaImport){
					  tabMancanti.append("</br> - indicare almeno una Tipologia di importazione");
				  }
				  if(!datiContinentiImport){
					  tabMancanti.append("</br> - indicare almeno un Continente di importazione");
				  }
				  if(!datiZoneProtetteImport){
					  tabMancanti.append("</br> - indicare se l'importazione avviene in Zone protette");
				  }*/
				  logger.debug("*** tabMancante: = Import");
			  }
			  if(!datiExportPresenti){
				  tabMancanti.append("</br> - Compilare tutto il tab Export e salvare cliccando sul tasto salva in fondo alla pagina");
				  logger.debug("*** tabMancante: = Export");
			  }				
		  }			
	  }

	  if(!datiPassaportoPresenti){
		  tabMancanti.append("</br> - Compilare tutto il tab Passaporto e salvare cliccando sul tasto salva in fondo alla pagina");
		  logger.debug("*** tabMancante: = Passaporto");
	  }

	  if(!datiAllegatiPresenti || !datiModuliPresenti){
		  tabMancanti.append("</br> - Compilare tutto il tab Allegati e salvare cliccando sul tasto salva in fondo alla pagina");
		  logger.debug("*** tabMancante: = Allegati");
	  }


	  logger.debug("tabMancanti:= " + tabMancanti);
	  return tabMancanti.toString();

	  /*return abilitaInoltra && datiAnagraficiPresenti &&
				datiAziendaPresenti && datiTipologiaPresenti &&
				datiImportPresenti && datiExportPresenti && 
				datiPassaportoPresenti && datiAllegatiPresenti &&
				datiModuliPresenti && datiCentriAziendaliPresenti;
	   */
  }
  
  @PreAuthorize("hasRole('READ')")
  @GetMapping(value = "/getListaDenomSpedizionieri")
  public ResponseEntity<String> getListaDenomSpedizionieri(@RequestParam String denomSpedizioniere) {
    Map<String, String> result = new TreeMap<>();
    ResponseEntity<String> jsonResult = null;

    try {
    	
      List<CarTSpedizioniere> listaSpedizionieri = decodificheEJB.getListaSpedizionieri();
      
      for (CarTSpedizioniere spedizioniere : listaSpedizionieri) {
    	//non mostro gli spedizionieri di tipo: "utente privato" e "ditta individuale"
    	  if(spedizioniere.getIdTipoSpedizioniere()!=CaronteConstants.DITTA_INDIVIDUALE && spedizioniere.getIdTipoSpedizioniere()!=CaronteConstants.UTENTE_PRIVATO){
    		  result.put(spedizioniere.getDenomSpedizioniere(), null);
    	  }
      }

    } catch (BusinessException exc) {
      logger.error("Eccezione in AjaxController.getListaDenomSpedizionieri: ", exc);
    }

    jsonResult = getJsonResult(result);

    System.out.println("\n\nLISTA denom spedizionieri JSON: " + jsonResult);

    return jsonResult;
  }

}
