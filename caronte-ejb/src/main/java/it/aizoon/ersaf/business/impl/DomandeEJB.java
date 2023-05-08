package it.aizoon.ersaf.business.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.springframework.util.StringUtils;

import it.aizoon.ersaf.business.IDomandeEJB;
import it.aizoon.ersaf.dto.AllegatoDTO;
import it.aizoon.ersaf.dto.CentroAziendaleDomandaDTO;
import it.aizoon.ersaf.dto.CentroAziendaleDto;
import it.aizoon.ersaf.dto.DomandaDto;
import it.aizoon.ersaf.dto.ModuloDTO;
import it.aizoon.ersaf.dto.SitoProduzioneDTO;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.dto.TipologiaAttMaterialeDTO;
import it.aizoon.ersaf.dto.TipologiaDomandaDTO;
import it.aizoon.ersaf.dto.TipologiaProdSpecieDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.ZonaProtettaSpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDComune;
import it.aizoon.ersaf.dto.generati.CarDCostante;
import it.aizoon.ersaf.dto.generati.CarDCostanteExample;
import it.aizoon.ersaf.dto.generati.CarDGenere;
import it.aizoon.ersaf.dto.generati.CarDSpecie;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazioneExample;
import it.aizoon.ersaf.dto.generati.CarDTipoStampa;
import it.aizoon.ersaf.dto.generati.CarDTipoStampaExample;
import it.aizoon.ersaf.dto.generati.CarDVoce;
import it.aizoon.ersaf.dto.generati.CarDZonaProtetta;
import it.aizoon.ersaf.dto.generati.CarRAttMaterialeUtente;
import it.aizoon.ersaf.dto.generati.CarRAttMaterialeUtenteExample;
import it.aizoon.ersaf.dto.generati.CarRDomandaCentroAz;
import it.aizoon.ersaf.dto.generati.CarRDomandaCentroAzExample;
import it.aizoon.ersaf.dto.generati.CarRDomandaTipologia;
import it.aizoon.ersaf.dto.generati.CarRDomandaTipologiaExample;
import it.aizoon.ersaf.dto.generati.CarRFlussoDomanda;
import it.aizoon.ersaf.dto.generati.CarRFlussoDomandaExample;
import it.aizoon.ersaf.dto.generati.CarRSpedizAssocSezione;
import it.aizoon.ersaf.dto.generati.CarRSpedizAssocSezioneExample;
import it.aizoon.ersaf.dto.generati.CarRTipoFlussoCom;
import it.aizoon.ersaf.dto.generati.CarRTipoFlussoComExample;
import it.aizoon.ersaf.dto.generati.CarRVoceUtenteSpecie;
import it.aizoon.ersaf.dto.generati.CarRVoceUtenteSpecieExample;
import it.aizoon.ersaf.dto.generati.CarTAllegatoDomanda;
import it.aizoon.ersaf.dto.generati.CarTAllegatoDomandaExample;
import it.aizoon.ersaf.dto.generati.CarTCentroAziendale;
import it.aizoon.ersaf.dto.generati.CarTDatiDomanda;
import it.aizoon.ersaf.dto.generati.CarTDatiDomandaExample;
import it.aizoon.ersaf.dto.generati.CarTDomanda;
import it.aizoon.ersaf.dto.generati.CarTDomandaExample;
import it.aizoon.ersaf.dto.generati.CarTFlusso;
import it.aizoon.ersaf.dto.generati.CarTIspettore;
import it.aizoon.ersaf.dto.generati.CarTIspettoreExample;
import it.aizoon.ersaf.dto.generati.CarTIterTipoPassaporto;
import it.aizoon.ersaf.dto.generati.CarTIterTipoPassaportoExample;
import it.aizoon.ersaf.dto.generati.CarTModulo;
import it.aizoon.ersaf.dto.generati.CarTModuloExample;
import it.aizoon.ersaf.dto.generati.CarTResponsabilePassaporto;
import it.aizoon.ersaf.dto.generati.CarTResponsabilePassaportoExample;
import it.aizoon.ersaf.dto.generati.CarTSitoProduzione;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniereExample;
import it.aizoon.ersaf.dto.generati.CarTUtente;
import it.aizoon.ersaf.dto.generati.CarTUtenteExample;
import it.aizoon.ersaf.dto.generati.CarTVoceUtente;
import it.aizoon.ersaf.dto.generati.CarTVoceUtenteExample;
import it.aizoon.ersaf.dto.generati.CarTZonaProtettaUtente;
import it.aizoon.ersaf.dto.generati.CarTZonaProtettaUtenteExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.ModaliForm;
import it.aizoon.ersaf.form.NuovaDomandaForm;
import it.aizoon.ersaf.form.RicercaDomandaForm;
import it.aizoon.ersaf.form.UtenteForm;
import it.aizoon.ersaf.formatter.DateFormatter;
import it.aizoon.ersaf.integration.DomandeDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.ComunicazioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.DomandaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.UtenteMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDComuneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDCostanteMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDSpecieMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoComunicazioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoStampaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRAttMaterialeUtenteMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRDomandaCentroAzMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRDomandaTipologiaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRFlussoDomandaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRSpedizAssocSezioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRTipoFlussoComMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRVoceUtenteSpecieMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTAllegatoDomandaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTCentroAziendaleMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTDatiDomandaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTDomandaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTFlussoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTIspettoreMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTIterTipoPassaportoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTModuloMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTResponsabilePassaportoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTSitoProduzioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTSpedizioniereMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTUtenteMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTVoceUtenteMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTZonaProtettaUtenteMapper;
import it.aizoon.ersaf.integration.rest.protocollo.dto.ProtocolloOutputDto;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

@SuppressWarnings("unused")
@Stateless(name = "Domande")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class DomandeEJB extends AbstractEJB<DomandeDAO, CarTDomandaMapper, CarTDomanda, CarTDomandaExample> implements IDomandeEJB {

  private final String THIS_CLASS = "[" + DomandeEJB.class.getCanonicalName() + "]";

  @Inject
  CarTDomandaMapper carTDomandaMapper;

  @Inject
  DomandaMapper domandaMapper;

  @Inject
  CarTUtenteMapper utenteMapper;
  
  @Inject
  CarDComuneMapper comuneMapper;
  
  @Inject
  CarTDomandaMapper carTdomandaMapper;
  
  @Inject
  CarRTipoFlussoComMapper tipoFlussoComMapper;
  
  @Inject
  CarTFlussoMapper flussoMapper;
  
  @Inject
  CarRFlussoDomandaMapper flussoDomandaMapper;
  
  @Inject
  CarRDomandaTipologiaMapper domandaTipologMapper;
  
  @Inject 
  ComunicazioneMapper comunicazMapper;
  
  @Inject
  CarTCentroAziendaleMapper centroAzMapper;
  
  @Inject
  CarRDomandaCentroAzMapper domandaCentroAzMapper;
  
  @Inject 
  CarTVoceUtenteMapper voceUtenteMapper;
  
  @Inject
  CarRVoceUtenteSpecieMapper voceUtenteSpecieMapper;
  
  @Inject
  CarTSitoProduzioneMapper sitoProduzioneMapper;
  
  @Inject
  CarTZonaProtettaUtenteMapper zonaProtettaUtenteMapper;
  
  @Inject
  CarTDatiDomandaMapper datiDomandaMapper;
  
  @Inject
  CarRAttMaterialeUtenteMapper attMaterialeUtenteMapper;
  
  @Inject
  CarTResponsabilePassaportoMapper respPassaportoMapper;
  
  @Inject
  CarDTipoComunicazioneMapper tipoComunicazioneMapper;
  
  @Inject
  CarDTipoStampaMapper tipoStampaMapper;
  
  @Inject
  CarTAllegatoDomandaMapper allegatoDomandaMapper;
  
  @Inject
  CarTResponsabilePassaportoMapper responsabilePassaportoMapper;
  
  @Inject
  CarDSpecieMapper specieMapper;
  
  @Inject
  CarDCostanteMapper costanteMapper;
  
  @Inject
  UtenteMapper utenteCaronteMapper;
  
  @Inject
  CarRSpedizAssocSezioneMapper spedizioniereSezioneMapper;
  
  @Inject
  CarTSpedizioniereMapper spedizioniereMapper;
  
  @Inject 
  CarTUtenteMapper carTUtenteMapper;
  
  @Inject
  CarTIspettoreMapper ispettoreMapper;
  
  @Inject
  CarTModuloMapper moduloMapper;
  
  @Inject
  CarTIterTipoPassaportoMapper iterTipoPassaportoMapper;



 
  @Override
  public List<DomandaDto> getElencoDomande(RicercaDomandaForm ricercaDomanda) throws BusinessException {
    return domandaMapper.getElencoDomande(ricercaDomanda);

  }

  
  @Override
  public List<DomandaDto> getDomandeRespinte(Long idUtente) throws BusinessException {
    Long[] idStatiDomanda = { CaronteConstants.STATO_COMUNICAZIONE_RESPINTA, CaronteConstants.STATO_COMUNICAZIONE_ANNULLATA };
    Date oneWeekAgo = new Date(System.currentTimeMillis() - (7 * CaronteConstants.DAY_IN_MS));
    List<DomandaDto> listaDomande = domandaMapper.getElencoDomandeStato(idUtente, oneWeekAgo, Arrays.asList(idStatiDomanda));
    return listaDomande;
  }
 
  @Override
  public List<CarDTipoComunicazione> getTipiDomandeByIdUtente(Long idUtente, Long idAssociazioneSezione) throws BusinessException{
    return domandaMapper.getTipiDomandeByIdUtente(idUtente, idAssociazioneSezione);
  }
  
  @Override
  public CarTUtente getDatiUtenteByIdUtente(Long idUtente) throws BusinessException{
	  return utenteMapper.selectByPrimaryKey(idUtente);
  }
  
  @Override
  public Long getIdProvinciaByIdComune(Long idComune) throws BusinessException{	  
	  CarDComune comune = comuneMapper.selectByPrimaryKey(idComune);
	  Long idProv = null;
	  if(comune != null){
		  idProv = comune.getIdProvincia();
	  }
	  return idProv;
  }
  
  @Override
  public Long inserisciNuovaDomanda(NuovaDomandaForm nuovaDomanda, UtenteDTO utente) throws BusinessException{
	  logger.debug("BEGIN inserisciNuovaDomanda");
	  CarTDomanda domanda = new CarTDomanda();

	/*  logger.debug("-- Recupero idAssociazioneSezone");    
	  GecoRTipoComSezioneExample comSezEx = new GecoRTipoComSezioneExample();
	  comSezEx.createCriteria().andIdTipoComunicazioneEqualTo(nuovaComunicaz.getIdTipoComunicazione());
	  List<GecoRTipoComSezione> tipoComSezioneList = tipoComSezioneMapper.selectByExample(comSezEx);
	  GecoRTipoComSezione tipoComSezione = null;
	  if(tipoComSezioneList != null){
		  tipoComSezione = tipoComSezioneList.get(0);
		  logger.debug("-- idAssociazioneSezone ="+tipoComSezione.getIdAssociazioneSezione());
	  }*/  

	  if (null != nuovaDomanda) {
		  domanda.setIdTipoComunicazione(nuovaDomanda.getIdTipoComunicazione());
		  domanda.setIdStatoComunicazione(nuovaDomanda.getIdStatoComunicazione());
		  domanda.setIdDomanda(nuovaDomanda.getIdDomanda());
		  logger.debug("CICCIO: "+nuovaDomanda.getIdDomanda());
		  
		  /* GESTIONE DATI ANAGRAFICI UTENTE : 
		   * -- L'utente Superuser potrebbe aver bisogno di inserire una domanda per altri utenti e non per se stesso
		   * -- L'utente base non può invece inserire domande per altri utenti, solo per lui
		   * 
		   * Fare update su car_t_utente
		   */
		  
		  
		  // Dati anagrafici dell'utente
		  logger.debug("Setto i dati anagrafici dell'utente");
		  CarTUtente utenteDaAggiornare = setDatiAnagraficiUtente(nuovaDomanda);			
			 
		  // Cerco idUtente da settare nella domanda
		  logger.debug("--- Cerco idUtente da settare nella domanda");
		  CarTUtenteExample example = new CarTUtenteExample();
		  example.createCriteria().andCodiceFiscaleEqualTo(nuovaDomanda.getCodFiscale());
		  List<CarTUtente> utenti = utenteMapper.selectByExample(example);
		  CarTUtente utenteNuovaDomanda = null;
		  if(utenti != null && utenti.size()>0){
			logger.debug("-- E' stato trovato l'utente con codice fiscale ="+nuovaDomanda.getCodFiscale());
			utenteNuovaDomanda = utenti.get(0);
			Long idUtente = utenteNuovaDomanda.getIdUtente();
			logger.debug("-- idUtente da collegare alla domanda = "+idUtente);
			domanda.setIdUtente(idUtente);
			utenteDaAggiornare.setIdUtente(idUtente);
			
			/* ********************************
			 * Dati che devono essere riportati come valorizzati da db durante l'update su car_t_utente
			 */
			/*logger.debug("-- idSpedizioniere di car_t_utente = "+idUtente);
			utenteDaAggiornare.setIdSpedizioniere(utenteNuovaDomanda.getIdSpedizioniere());
			utenteDaAggiornare.setDataInserimento(utenteNuovaDomanda.getDataInserimento());
			utenteDaAggiornare.setIdUtenteInsert(utenteNuovaDomanda.getIdUtenteInsert());
			utenteDaAggiornare.setIdUtente(utenteNuovaDomanda.getIdUtente());
			utenteDaAggiornare.setAbilitato(utenteNuovaDomanda.getAbilitato());
			utenteDaAggiornare.setPassword(utenteNuovaDomanda.getPassword());
			utenteDaAggiornare.setEmail(utenteNuovaDomanda.getEmail());
			utenteDaAggiornare.setNote(utenteNuovaDomanda.getNote());
			utenteDaAggiornare.setToken(utenteNuovaDomanda.getToken());
			utenteDaAggiornare.setDataToken(utenteNuovaDomanda.getDataToken());
			utenteDaAggiornare.setNascosto(utenteNuovaDomanda.getNascosto());
			utenteDaAggiornare.setRifiutato(utenteNuovaDomanda.getRifiutato());
			utenteDaAggiornare.setMotivoRifiuto(utenteNuovaDomanda.getMotivoRifiuto());*/
			// ********************************
			
			if(utente.isSuperUser()){
			  utenteDaAggiornare.setModificatoAdmin(true);
			}
			else{
			  utenteDaAggiornare.setModificatoAdmin(utenteNuovaDomanda.getModificatoAdmin());	
			}
				
		  }			   
		  
		 
		  // --- UPDATE SU CAR_T_UTENTE --- (in ogni caso faccio update dei dati)
		  logger.debug("-- idUtente da aggiornare ="+domanda.getIdUtente());		  		  		  
		  utenteDaAggiornare.setIdUtenteUpdate(utente.getId());
		  utenteDaAggiornare.setDataUpdate(new Date());
		  		  
		 /* CarTUtenteExample ex = new CarTUtenteExample();
		  ex.createCriteria().andIdUtenteEqualTo(domanda.getIdUtente());
		  utenteMapper.updateByExample(utenteDaAggiornare, ex);*/
		  updateUtenteAnagrafica(utenteDaAggiornare);
		  // ---------------------------
		  
		  
		  logger.debug("-- idUtenteInserimento nuova domanda =" + utente.getId());
		  domanda.setIdUtenteInserimento(utente.getId());
		  domanda.setDataInserimento(new Date());		  

		  
		  // PER GESTIONE FLUSSI COMUNICAZIONE 
		  /*
		   * Nel caso di nuova comunicazione all'interno di un flusso, che ha già un'idComunicazionePrecedente :
		   * - salvo i dati azienda della comunicazione precedente		    
		   */
		 /* if(null != nuovaComunicaz.getIdComunicazionePrecedente()){
			  logger.debug("*** idComunicazionePrecedente valorizzato, salvo anche i dati azienda della comunicazione precedente, presente nel flusso ="+nuovaComunicaz.getIdComunicazionePrecedente());
			  comunicaz = setDatiAzienda(comunicaz, nuovaComunicaz);			 
		  }*/            
	  }


	  // ********* Inserisco un record in CAR_T_DOMANDA
	  logger.debug("-- Inserisco un record in CAR_T_DOMANDA");
	  carTdomandaMapper.insertSelective(domanda);
	  logger.debug("idDomanda inserita =" + domanda.getIdDomanda());


	  /*
	   * Nel caso di nuova comunicazione all'interno di un flusso, che ha già un'idComunicazionePrecedente :
	   *  - se siamo nella sezione di VITI : salvo i dati delle particella della comunicazione precedente in GECO_T_SUPERFICIE_VITATA
	   *  - se siamo nella sezione di NITRATI : salvo i dati del tecnico 
	   */	 
	 /* if(null != nuovaComunicaz.getIdComunicazionePrecedente()){
		  // Se siamo nella sezione di VITI, si devono anche inserire le particelle della comunicazione precedente, se il tipo superficie della comunicazione precedente è lo stesso di quella nuova, che stiamo inserendo
		  if(tipoComSezione != null && GecoConstants.ID_ASSOCIAZIONE_SEZIONE_VITI.equals(tipoComSezione.getIdAssociazioneSezione())){
			  logger.debug("*** idComunicazionePrecedente valorizzato, e sezione VITI : salvo anche i dati delle particelle della comunicazione precedente, presente nel flusso ="+nuovaComunicaz.getIdComunicazionePrecedente());
			  //insertParticelleComunicazionePrecedente(nuovaComunicaz.getIdComunicazionePrecedente(), comunicaz.getIdComunicazione(),idUtente);
	
			  // inserisco solo le particelle che deve avere la nuova comunicazione e non tutte quelle precedenti
			  insertParticelleComunicazionePrecByTipoSuperficie(nuovaComunicaz.getIdComunicazionePrecedente(), comunicaz.getIdComunicazione(), idUtente);
		  }	  
		  // Controllo se sono nella sezione Zootecnia-nitrati : deve essere salvato anche il tecnico in GECO_T_TECNICO
		  if(tipoComSezione != null && GecoConstants.ID_ASSOCIAZIONE_SEZIONE_NITRATI.equals(tipoComSezione.getIdAssociazioneSezione())){
			  logger.debug("*** Caso di comunicazione in flusso nella sezione NITRATI, salvo anche i dati del tecnico");
			  GecoTTecnico tecnico = new GecoTTecnico();			  
			  setDatiTecnico(tecnico, nuovaComunicaz);
			  tecnico.setIdComunicazione(comunicaz.getIdComunicazione());
			  tecnico.setIdUtenteInserimento(comunicaz.getIdUtenteInserimento());
			  tecnico.setDataInserimento(new Date());
			  tecnicoMapper.insert(tecnico);
		  }
	  }*/
	  

	  


	  /*
	   *  *** Parte per la gestione dei flussi :
	   *  
	   *  1) controllare se esiste nel form l'id comunicazione precedente        
         - se esiste : si cerca l'id flusso legato all'id comunicazione precedente su GECO_R_FLUSSO_COMUNICAZIONE                
         - se non esiste : creare un nuovo record su geco_t_flusso (leggo su GECO_R_TIPO_FLUSSO_COM l'id_tipo_flusso associato al tipo comunicazione) ed ottengo l'id_flusso inserito  

        2) inserire record in geco_r_flusso_comunicazione progressivo(max progressivo per id_flusso +1) e id_comunicazione inserita
	   */

	  Long idFlusso = null;
	  if(null != nuovaDomanda.getIdDomandaPrecedente()){
		  // PARTE PER LA GESTIONE DEI FLUSSI
		  logger.debug("*** idComunicazionePrecedente valorizzato ="+nuovaDomanda.getIdDomandaPrecedente());

		  logger.debug("-- cerco l'idFlusso della domanda precedente con idDomanda ="+nuovaDomanda.getIdDomandaPrecedente());
		  CarRFlussoDomandaExample example = new CarRFlussoDomandaExample();
		  example.createCriteria().andIdDomandaEqualTo(nuovaDomanda.getIdDomandaPrecedente());
		  List<CarRFlussoDomanda> flussoDomandaList = flussoDomandaMapper.selectByExample(example);
		  CarRFlussoDomanda flussoDomanda = null;
		  if(flussoDomandaList != null){
			  flussoDomanda = flussoDomandaList.get(0);
		  }
		  if(flussoDomanda != null){
			  idFlusso = flussoDomanda.getIdFlusso();
			  logger.debug("-- idFlusso trovato ="+idFlusso);
		  }
	  }
	  else{
		  logger.debug("*** idDomandaPrecedente non valorizzata");

		  logger.debug("-- cerco idTipoFlusso da inserire, partendo dall'idTipoComunicazione ="+nuovaDomanda.getIdTipoComunicazione());
		  CarRTipoFlussoComExample example = new CarRTipoFlussoComExample();
		  example.createCriteria().andIdTipoComunicazioneEqualTo(nuovaDomanda.getIdTipoComunicazione());
		  List<CarRTipoFlussoCom> tipoFlussoComList = tipoFlussoComMapper.selectByExample(example);
		  CarRTipoFlussoCom tipoFlussocom = null;
		  if(tipoFlussoComList != null){
			  tipoFlussocom = tipoFlussoComList.get(0); 
		  }    	
		  Long idTipoFlusso = null;
		  if(tipoFlussocom != null){
			  idTipoFlusso = tipoFlussocom.getIdTipoFlusso();
		  }
		  logger.debug("-- idTipoFlusso trovato ="+idTipoFlusso);

		  CarTFlusso recordFlusso = new CarTFlusso();
		  recordFlusso.setDataInserimento(new Date());
		  recordFlusso.setIdTipoFlusso(idTipoFlusso);
		  recordFlusso.setIdUtenteInserimento(utente.getId());

		  // ********** Inserisco un record in CAR_T_FLUSSO
		  logger.debug("-- Inserisco un record in CAR_T_FLUSSO");
		  flussoMapper.insertSelective(recordFlusso);
		  idFlusso = recordFlusso.getIdFlusso();
		  logger.debug("-- idFlusso inserito ="+idFlusso);
	  }

	  // Cerco il max progressivo per idFlusso su CAR_R_FLUSSO_DOMANDA
	  logger.debug("-- Cerco il max progressivo per idFlusso su CAR_R_FLUSSO_DOMANDA");
	  Long maxProgressivo = domandaMapper.getMaxProgressivoFlussoDomanda(idFlusso);
	  logger.debug("-- maxProgressivo = "+maxProgressivo);
	  Long progressivo = null;
	  if(maxProgressivo != null){
		  progressivo = maxProgressivo.longValue()+1;
	  }
	  else{
		  progressivo = 1L;
	  }
	  
	  /* progressivo settato a 0 manualmente per evitare problemi nel flusso
	   * 
	   if(domanda.getIdTipoComunicazione() == CaronteConstants.ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP) {
		  progressivo = 0L;
	  }
	  */
	  // ************ Inserisco un record in CAR_R_FLUSSO_DOMANDA
	  logger.debug("-- Inserisco un record in CAR_R_FLUSSO_DOMANDA");
	  CarRFlussoDomanda record = new CarRFlussoDomanda();
	  record.setIdDomanda(domanda.getIdDomanda());
	  record.setIdFlusso(idFlusso);
	  record.setProgressivo(progressivo);
	  flussoDomandaMapper.insertSelective(record);

	  logger.debug("END inserisciNuovaDomanda");
	  return domanda.getIdDomanda();
  }
  
  
  private void updateUtenteAnagrafica(CarTUtente utente) throws BusinessException{
	  domandaMapper.updateUtenteAnagrafica(utente);
  }
  
  private CarTUtente setDatiAnagraficiUtente(NuovaDomandaForm nuovaDomanda) throws BusinessException {
	  CarTUtente utente = new CarTUtente();  
	  try {	      
	      utente.setCognome(nuovaDomanda.getCognome());
	      utente.setNome(nuovaDomanda.getNome());
	      utente.setCodiceFiscale(nuovaDomanda.getCodFiscale());
	      
 	      // Dati nascita
	      utente.setDataNascita(nuovaDomanda.getDataNascita()); 
	      logger.debug("nascita estera =" + nuovaDomanda.isNascitaEstera());
	      if (nuovaDomanda.isNascitaEstera()) {
	    	  utente.setDenomComuneEstNascita(nuovaDomanda.getDenomComuneEstNascita());
	    	  utente.setIdNazioneEstNascita(nuovaDomanda.getIdNazioneEstNascita());
	    	  utente.setIdComuneNascita(null);
	      } 
	      else {
	    	  utente.setIdComuneNascita(nuovaDomanda.getIdComuneNascita());
	    	  utente.setIdNazioneEstNascita(null);
	      }

	      // Dati residenza
	      logger.debug("residenza estera =" + nuovaDomanda.isResidenzaEstera());
	      if (nuovaDomanda.isResidenzaEstera()) {
	    	  utente.setDenomComuneEstResid(nuovaDomanda.getDenomComuneEstResid());
	    	  utente.setIdNazioneEstResid(nuovaDomanda.getIdNazioneEstResid());
	    	  utente.setIdComuneResidenza(null);
	    	  utente.setCap(null);
	      } 
	      else {
	    	  utente.setIdComuneResidenza(nuovaDomanda.getIdComuneResidenza());
	    	  utente.setCap(nuovaDomanda.getCap());
	    	  utente.setIdNazioneEstResid(null);
	      }
	      utente.setIndirizzo(nuovaDomanda.getIndirizzo());
	      
	      utente.setTelefono(nuovaDomanda.getNumTelefono());
	      utente.setCellulare(nuovaDomanda.getCellulare());
	      utente.setEmail(nuovaDomanda.getEmail());
	    } 
	    catch (Exception e) {
	      logger.error("Eccezione in setDatiAnagrafici =" + e.getMessage());
	      throw new BusinessException(e.getMessage());
	    }
	    return utente;
	  }
  
  @Override
  public List<CarTSpedizioniere> getAziendeByIdUtente(Long idUtente) throws BusinessException{
	List<CarTSpedizioniere> listaSpedizionieri = null;
    try{
      listaSpedizionieri = domandaMapper.getAziendeByIdUtente(idUtente);
	}
	catch (Exception e) {
	  logger.error("Eccezione in getAziendeByIdUtente =" + e.getMessage());
	  throw new BusinessException(e.getMessage());
	}
    return listaSpedizionieri;
  }
  
  @Override
  public CarTSpedizioniere getSpedizioniereByIdSpedizIdUtente(Long idAzienda, Long idUtente) throws BusinessException{
    CarTSpedizioniere spedizioniere = null;
	try{
		spedizioniere = domandaMapper.getSpedizioniereByIdSpedizIdUtente(idAzienda, idUtente);
	}
	catch (Exception e) {
	  logger.error("Eccezione in getSpedizioniereById =" + e.getMessage());
	  throw new BusinessException(e.getMessage());
	}
	return spedizioniere;
  }
  
  @Override
  public int aggiornaDatiAzienda(NuovaDomandaForm form, Long idUtente) throws BusinessException{
	logger.debug("---- Aggiorno la domanda con idDomanda ="+form.getIdDomanda());  
	 
	// **** SALVO LE ATTIVITA' SVOLTE DALL'OPERATORE PROFESSIONALE (CHECK)     
    logger.debug("-- 1) SALVO LE ATTIVITA' SVOLTE DALL'OPERATORE PROFESSIONALE (CHECK)"); 	
    // Delete in CAR_T_VOCE_UTENTE
    logger.debug("-- Delete in CAR_T_VOCE_UTENTE");	
    eliminaVoceUtente(form.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP, 1L);    
    if(form.getIdVoceCheckTip() != null){ 
      // Inserimento in CAR_T_VOCE_UTENTE
      logger.debug("-- Insert in CAR_T_VOCE_UTENTE, numero di voci selezionate ="+form.getIdVoceCheckTip().length);
      for (int i = 0; i < form.getIdVoceCheckTip().length; i++) {
    	  inserisciVoceUtente(Long.parseLong(form.getIdVoceCheckTip()[i]), form.getIdDomanda(), idUtente);
	  }      
    }
		
	CarTDomanda domanda = new CarTDomanda();
	domanda.setIdDomanda(form.getIdDomanda());
	logger.debug("-- idSpedizioniere da inserire in car_t_domanda ="+form.getIdAzienda());
	domanda.setIdSpedizioniere(form.getIdAzienda());
	domanda.setIdUtenteAggiornamento(idUtente);
	domanda.setDataAggiornamento(new Date());
    
	return carTDomandaMapper.updateByPrimaryKeySelective(domanda);		
	
  }
  
  @Override
  public void aggiornaTipologia(NuovaDomandaForm form,Long idUtente) throws BusinessException{
    logger.debug("BEGIN aggiornaTipologia");
    logger.debug(" -- idDomanda ="+form.getIdDomanda());
    /*
     * Si effettua il lock sul record della domanda per evitare modifiche concorrenti
     */
    domandaMapper.lockDomanda(form.getIdDomanda());
    
    // **** SALVO LE TIPOLOGIE DOMANDA
    logger.debug("-- 2) SALVO LE TIPOLOGIE DOMANDA");
    // Rimuovo ed inserisco dati in car_r_domanda_tipologia
    logger.debug("Rimuovo dati in car_r_domanda_tipologia con id_domanda ="+form.getIdDomanda());
    CarRDomandaTipologiaExample example = new CarRDomandaTipologiaExample();
    example.createCriteria().andIdDomandaEqualTo(form.getIdDomanda());
    domandaTipologMapper.deleteByExample(example);
    
    logger.debug("Inserisco dati in car_r_domanda_tipologia con id_domanda ="+form.getIdDomanda());    
    if(form.getIdTipologia() != null && form.getIdTipologia().length != 0){
    	for (int i = 0; i < form.getIdTipologia().length; i++) {
    		String idTipologia = form.getIdTipologia()[i];
    		logger.debug("-- idTipologia da inserire ="+idTipologia);
    		CarRDomandaTipologia tipologia = new CarRDomandaTipologia();
    	    tipologia.setIdDomanda(form.getIdDomanda());
    	    tipologia.setIdUtenteInserimento(idUtente);
    	    tipologia.setDataInserimento(new Date());
    	    tipologia.setIdTipologia(Long. parseLong(idTipologia));
    	    domandaTipologMapper.insert(tipologia);	
		}	    
    } 
    
	// aggiorno la tipologia attività anche su CAR_T_SPEDIZIONIERE		
	DomandaDto domanda = domandaMapper.getDettaglioAnagraficaAziendaByIdDomanda(form.getIdDomanda());
	if (domanda.getIdSpedizioniere() != null) {
		// recupero lo stringone dalla domanda
		String tipologieAttivita = getTipologieAttivitaByIdDomanda(form.getIdDomanda());
		logger.debug("-- 3) SALVO LE TIPOLOGIE DOMANDA CONCATENATE SU CAR_T_SPEDIZIONIERE idSped = "+domanda.getIdSpedizioniere());
		CarTSpedizioniere spedizioniere = spedizioniereMapper.selectByPrimaryKey(domanda.getIdSpedizioniere());
		spedizioniere.setTipologiaAttivita(tipologieAttivita);
		spedizioniere.setDataUpdate(new Date());
		spedizioniere.setIdUtenteUpdate(idUtente);
		spedizioniereMapper.updateByPrimaryKey(spedizioniere);
	}
    
  }
  
  @Override
  public List<CentroAziendaleDto> getCentriAziendaliByIdSpediz(Long idSpedizioniere) throws BusinessException{
	  return comunicazMapper.getElencoCentriAziendali(idSpedizioniere);
  }
  
  @Override
  public CarTCentroAziendale getCentroAziendaleById(Long idCentroAziendale) throws BusinessException{
	  return centroAzMapper.selectByPrimaryKey(idCentroAziendale);
  }
  
  @Override
  public String getSiglaProvByIdProvincia(Long idProvincia) throws BusinessException{
	  return domandaMapper.getSiglaProvByIdProvincia(idProvincia);
  }
  
  @Override
  public String getCodiceCentroAzByIdProvIdSpediz(Long idProvincia, Long idSpedizioniere) throws BusinessException{
	  return domandaMapper.getCodiceCentroAzByIdProvIdSpediz(idProvincia, idSpedizioniere);
  }
  
  @Override
  public Boolean isDomandaModificabile(Long idUtente, Long idDomanda) throws BusinessException{
	  return domandaMapper.isUtenteAbilitatoModificaDomanda(idUtente, idDomanda);
  }
  
  @Override
  public int aggiornaDatiAnagrafici(NuovaDomandaForm nuovaDomanda, UtenteDTO utente) throws BusinessException {

    CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(nuovaDomanda.getIdDomanda());

    if (null != domanda) {      
    	 // Dati anagrafici dell'utente
		  logger.debug("-- Setto i dati anagrafici dell'utente DA MODIFICARE");
		  CarTUtente utenteDaAggiornare = setDatiAnagraficiUtente(nuovaDomanda);			
			 
		  // Cerco idUtente da settare nella domanda
		  logger.debug("--- Cerco idUtente da settare nella domanda");
		  CarTUtenteExample example = new CarTUtenteExample();
		  example.createCriteria().andCodiceFiscaleEqualTo(nuovaDomanda.getCodFiscale());
		  List<CarTUtente> utenti = utenteMapper.selectByExample(example);
		  CarTUtente utenteNuovaDomanda = null;
		  if(utenti != null && utenti.size()>0){
			logger.debug("-- E' stato trovato l'utente con codice fiscale ="+nuovaDomanda.getCodFiscale());
			utenteNuovaDomanda = utenti.get(0);			
			
			/* ********************************
			 * Dati che devono essere riportati come valorizzati da db durante l'update su car_t_utente
			 */			
			logger.debug("-- idSpedizioniere di car_t_utente da aggiornare = "+utenteNuovaDomanda.getIdSpedizioniere());
			utenteDaAggiornare.setIdSpedizioniere(utenteNuovaDomanda.getIdSpedizioniere());
			utenteDaAggiornare.setDataInserimento(utenteNuovaDomanda.getDataInserimento());
			utenteDaAggiornare.setIdUtenteInsert(utenteNuovaDomanda.getIdUtenteInsert());
			
			utenteDaAggiornare.setIdUtente(utenteNuovaDomanda.getIdUtente());
			logger.debug("-- idUtente da aggiornare = "+utenteDaAggiornare.getIdUtente());
			
			utenteDaAggiornare.setAbilitato(utenteNuovaDomanda.getAbilitato());
			utenteDaAggiornare.setPassword(utenteNuovaDomanda.getPassword());
			utenteDaAggiornare.setNote(utenteNuovaDomanda.getNote());
			utenteDaAggiornare.setToken(utenteNuovaDomanda.getToken());
			utenteDaAggiornare.setDataToken(utenteNuovaDomanda.getDataToken());
			utenteDaAggiornare.setNascosto(utenteNuovaDomanda.getNascosto());
			utenteDaAggiornare.setRifiutato(utenteNuovaDomanda.getRifiutato());
			utenteDaAggiornare.setMotivoRifiuto(utenteNuovaDomanda.getMotivoRifiuto());
			logger.debug("-- email ="+utenteNuovaDomanda.getEmail());
			utenteDaAggiornare.setEmail(utenteNuovaDomanda.getEmail());
			utenteDaAggiornare.setDataAccettazionePrivacy(utenteNuovaDomanda.getDataAccettazionePrivacy());
			// ********************************
			
			if(utente.isSuperUser()){
			  utenteDaAggiornare.setModificatoAdmin(true);
			}
			else{
			  utenteDaAggiornare.setModificatoAdmin(utenteNuovaDomanda.getModificatoAdmin());	
			}
		 
		  // --- UPDATE SU CAR_T_UTENTE --- 
		  logger.debug("-- ****** Aggiornamento dati su CAR_T_UTENTE");
		  utenteDaAggiornare.setIdUtenteUpdate(utente.getId());
		  utenteDaAggiornare.setDataUpdate(new Date());		  		  		 
		  utenteMapper.updateByPrimaryKey(utenteDaAggiornare);
		  // ---------------------------	

      domanda.setIdUtenteAggiornamento(utente.getIdUtente());
      domanda.setDataAggiornamento(new Date());
    }
		  
    logger.debug("-- ****** Aggiornamento dati su CAR_T_DOMANDA");        
    /*
     * Se l'idUtente è cambiato (può essere stato cercato un nuovo utente cambiando il codice fiscale dal Tab Anagrafica):
     *  - verificare se per il nuovo idUtente è collegato lo stesso spedizioniere indicato in domanda : 
     *    se si, viene mantenuto lo stesso id_spedizionieree in car_t_domanda, altrimenti, deve essere messo a null
     */
    boolean idSpedUguale = false;
    logger.debug("-- domanda.getIdUtente() ="+domanda.getIdUtente());
    logger.debug("-- domanda.getIdUtente() ="+nuovaDomanda.getIdUtente());
   
    if(domanda.getIdUtente().longValue() != nuovaDomanda.getIdUtente()){
    	 logger.debug("-- l'utente ha cambiato l'id utente dell'anagrafica");
    	 logger.debug("-- domanda.getIdSpedizioniere() ="+domanda.getIdSpedizioniere());
    	 List<CarTSpedizioniere> elencoAziendeNuovoUtente = getAziendeByIdUtente(nuovaDomanda.getIdUtente());    	     	 
    	 
    	 logger.debug("-- domanda.getIdSpedizioniere()  ="+domanda.getIdSpedizioniere());
    	 if(domanda.getIdSpedizioniere() != null){
	    	 CarTSpedizioniere spedDomanda = spedizioniereMapper.selectByPrimaryKey(domanda.getIdSpedizioniere());
	    	 	    	 
			 for (Iterator iterator = elencoAziendeNuovoUtente.iterator(); iterator.hasNext();) {
				CarTSpedizioniere carTSpedizioniere = (CarTSpedizioniere) iterator.next();
				if(carTSpedizioniere.getIdSpedizioniere().longValue() == domanda.getIdSpedizioniere()){
					idSpedUguale = true;
					break;
				}			
			}
			if(!idSpedUguale){
			  logger.debug("-- il nuovo utente settato non ha lo stesso idSpedizioniere presente in domanda, metto a null idSpedizioniere");
			  domanda.setIdSpedizioniere(null);
			}
         }
			
		
	}
    logger.debug("-- id_utente da settare in car_t_domanda ="+nuovaDomanda.getIdUtente());
    domanda.setIdUtente(nuovaDomanda.getIdUtente());
    return carTDomandaMapper.updateByPrimaryKey(domanda);
    }
    else return 0;
  }  
  
  
  /* Gestione inserimento dati del Tab Centro Aziendale
  
   - Salvataggio centro aziendale
    1)	fare update dei dati del centro aziendale, nel caso in cui sia stato selezionato un centro aziendale già presente sul db
	2)	fare insert dei dati del centro aziendale, nel caso in cui sia stato selezionato 'Nuovo centro aziendale'	
	3)	legare il centro aziendale alla domanda
	4)  aggiornare domanda		
 */
  
  @Override
  public Long salvaCentroAziendaleDomanda(NuovaDomandaForm form, CentroAziendaleDomandaDTO centroAziendale, UtenteDTO utente) throws BusinessException{
	  Long idCentroAzDomanda = null;
	  try {
		  CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(form.getIdDomanda());
		  if(null != domanda){		  
			  /*
			   * Si effettua il lock sul record della domanda per evitare modifiche concorrenti
			   */
			  domandaMapper.lockDomanda(form.getIdDomanda());			  

			  // ***  ID CENTRO AZIENDALE selezionato nella domanda			  
			  logger.debug("-- idCentroAziendale selezionato nella domanda ="+centroAziendale.getIdCentroAziendale());
			  Long idCentroAziendale = form.getIdCentroAziendale();
			  logger.debug("-- idCentroAziendale del form ="+form.getIdCentroAziendale());

			  if(idCentroAziendale.longValue() == CaronteConstants.ID_CENTRO_AZIENDALE_NUOVO){
				  logger.debug("-- **** Inserimento NUOVO CENTRO AZIENDALE per idDomanda ="+form.getIdDomanda()); 	      	      
				  centroAzMapper.insert(centroAziendale);
				  idCentroAzDomanda = centroAziendale.getIdCentroAziendale();
				  logger.debug("-- *** IdCentroAziendale inserito ="+idCentroAzDomanda);
			  }
			  else{
				  // Recupero i dati del centro aziendale
				  CarTCentroAziendale centroAzDb = centroAzMapper.selectByPrimaryKey(idCentroAziendale);
				  logger.debug("-- *** Aggiornamento CENTRO AZIENDALE con idCentroAziendale ="+idCentroAziendale); 
				  CarTCentroAziendale centroAzDaAgg = setDatiAggCentroAziendale(centroAzDb, form, utente);
				  centroAzMapper.updateByPrimaryKey(centroAzDaAgg);
				  idCentroAzDomanda = centroAzDaAgg.getIdCentroAziendale();
				  logger.debug("-- *** IdCentroAziendale modificato ="+idCentroAzDomanda);
			  }

			  // Legare il centro aziendale alla domanda
			  logger.debug("-- *** Legare il centro aziendale alla domanda : insert in car_r_domanda_centro_az");
			  logger.debug("-- idCentroAziendale da legare con la domanda ="+idCentroAzDomanda);
			  domanda.setDataAggiornamento(new Date());		
			  // controllo che non sia già presente, evito errore chiave duplicata 	  
			  carTDomandaMapper.updateByPrimaryKey(domanda);	
			  CarRDomandaCentroAz domandaCentroAz = domandaCentroAzMapper.selectByPrimaryKey(form.getIdDomanda(), idCentroAzDomanda);			  
			  if (domandaCentroAz == null) {
				  domandaCentroAz = new CarRDomandaCentroAz();
				  domandaCentroAz.setDataInsert(new Date());
				  domandaCentroAz.setIdCentroAziendale(idCentroAzDomanda);
				  domandaCentroAz.setIdDomanda(form.getIdDomanda());
				  domandaCentroAz.setIdUtenteInsert(utente.getId());				
				  domandaCentroAzMapper.insert(domandaCentroAz);
				  
				  logger.debug("-- *** Aggiornamento dati della domanda");
				  domanda.setIdUtenteAggiornamento(utente.getId());
				  domanda.setDataAggiornamento(new Date());			  
				  carTDomandaMapper.updateByPrimaryKey(domanda);	
			  }		 
}
	    }
		catch (Exception e) {
		  logger.error("-- Exception in salvaDatiCentroAziendale ="+e.getMessage());
		  throw new BusinessException(e.getMessage());			
		}	
	    return idCentroAzDomanda;
	  }
  
  @Override
  public List<CentroAziendaleDomandaDTO> getCentriAziendaliByIdDomanda(Long idDomanda) throws BusinessException {
	  return domandaMapper.getCentriAziendaliByIdDomanda(idDomanda);
  }
  

  
  private CarTCentroAziendale setDatiAggCentroAziendale(CarTCentroAziendale centroAzDb, NuovaDomandaForm form,UtenteDTO utente) throws Exception{
	  CarTCentroAziendale centroAz = centroAzDb;
	  centroAz.setCap(form.getCapCentroAz());
	  centroAz.setCellulare(form.getCellulareCentroAz());
	  centroAz.setCodCentroAziendale(form.getCodCentroAz());
	  centroAz.setCodiceFiscale(form.getCodFiscale());
	  centroAz.setDataUpdate(new Date());
	  centroAz.setDenominazione(form.getDenominazCentroAz());
	  centroAz.setEmail(form.getMailCentroAz());
	  centroAz.setFlPrincipale(false);
	  centroAz.setFrazione(form.getFrazioneCentroAz());
	  centroAz.setIdComune(form.getIdComuneCentroAz());
	  centroAz.setIdSpedizioniere(form.getIdAzienda());
	  centroAz.setIdUtenteUpdate(utente.getId());
	  centroAz.setIndirizzo(form.getIndirizzoCentroAz());
	  centroAz.setPec(form.getPecCentroAz());
	  centroAz.setTelefono(form.getTelefonoCentroAz());
	  return centroAz;
  }
  
  @Override
  public List<TipologiaProdSpecieDTO> getTipologieProdCentroAz(Long idCentroAziendale, Long idDomanda) throws BusinessException{
	  return domandaMapper.getTipologieProdCentroAz(idCentroAziendale, idDomanda);
  }
  
  @Override
  public List<SitoProduzioneDTO> getSitiProduzioneCentroAz(Long idCentroAziendale) throws BusinessException{
	  List<SitoProduzioneDTO> sitProdList = domandaMapper.getSitiProduzioneCentroAz(idCentroAziendale);	  
	  // ho necessità di formattare la superficie in 
	  for(int i=0; i<sitProdList.size(); i++) {	
		  if(sitProdList.get(i) != null && sitProdList.get(i).getSuperficie() != null){
		    logger.debug("big decimal superficie #########.## ="+CaronteUtils.formatBigDecimalToFormat(sitProdList.get(i).getSuperficie(), "######.##"));
		    sitProdList.get(i).setSuperficie(CaronteUtils.parseBigDecimal(CaronteUtils.formatBigDecimalToFormat(sitProdList.get(i).getSuperficie(), "######.##")));
		  }
	  }
	  
	  return sitProdList; 
  }
  
  @Override
  public Long salvaTipologiaProduttivaCentroAz(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException{
	  Long idVoceUtenteInserito= null;
	  Long idTipologia = form.getIdTipologiaProd();
	  logger.debug("--- idTipologia da salvare su db ="+idTipologia);
	  logger.debug("--- idCentroAziendale al quale legare i dati ="+form.getIdCentroAziendaleSel());
	  String[] idSpecieList = form.getSpecie();
	  	  			
      // 1) Per ogni tipologia selezionata : Insert CAR_T_VOCE_UTENTE
	  if(null != idTipologia && null != form.getIdCentroAziendaleSel()){
		  CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(form.getIdDomanda());
		  if(null != domanda){		  
			  /*
			  * Si effettua il lock sul record della domanda per evitare modifiche concorrenti
			  */
			  domandaMapper.lockDomanda(form.getIdDomanda());
			  
			  logger.debug("-- *** Inserimento TIPOLOGIE PRODUTTIVE legate al centro aziendale : insert in CAR_T_VOCE_UTENTE e CAR_R_VOCE_UTENTE_SPECIE");
			  CarTVoceUtente voceUtente = new CarTVoceUtente();		  
			  logger.debug("-- idTipologia da inserire ="+idTipologia);
			  voceUtente.setDataInserimento(new Date());
			  voceUtente.setIdCentroAziendale(form.getIdCentroAziendaleSel());
			  logger.debug("-- idDomanda ="+form.getIdDomanda());
			  voceUtente.setIdDomanda(form.getIdDomanda());
			  voceUtente.setIdUtenteInserimento(utente.getId());
			  voceUtente.setIdVoce(idTipologia);
			  voceUtente.setNote(form.getNoteTipologiaCentroAz());
			  voceUtenteMapper.insert(voceUtente);
			  idVoceUtenteInserito = voceUtente.getIdVoceUtente();
			  logger.debug("-- idVoceUtenteInserito ="+idVoceUtenteInserito);
	
			  // 2) Per ogni specie selezionata :  Insert CAR_R_VOCE_UTENTE_SPECIE
			  if(null != idSpecieList){
				  logger.debug("-- Numero di specie da inserire ="+idSpecieList.length+" per idTipologia ="+idTipologia);
				  for(int j=0;j<idSpecieList.length;j++){
					  String idSpecie = idSpecieList[j];
					  Long idSpecieL = Long.parseLong(idSpecie);
					  logger.debug("-- idSpecie da inserire ="+idSpecieL);
					  // mi recupero il genere della specie
				 	  CarDSpecie dettaglioSpecie = specieMapper.selectByPrimaryKey(idSpecieL);	
				 	  
					  CarRVoceUtenteSpecie voceUtenteSpecie = new CarRVoceUtenteSpecie();
					  voceUtenteSpecie.setIdGenere(dettaglioSpecie.getIdGenere());
					  voceUtenteSpecie.setIdSpecie(idSpecieL);
					  voceUtenteSpecie.setIdVoceUtente(idVoceUtenteInserito);
					  voceUtenteSpecieMapper.insert(voceUtenteSpecie);
					  logger.debug("-- id_voce_utente_specie inserito ="+voceUtenteSpecie.getIdVoceUtenteSpecie());
				  }
			  } 
			  else{
				  if(form.getIdGenere() != null){
					  logger.debug("-- idGenere da inserire ="+form.getIdGenere());
					  CarRVoceUtenteSpecie voceUtenteSpecie = new CarRVoceUtenteSpecie();
					  voceUtenteSpecie.setIdGenere(form.getIdGenere());
					  voceUtenteSpecie.setIdVoceUtente(idVoceUtenteInserito);
					  voceUtenteSpecieMapper.insert(voceUtenteSpecie);
					  logger.debug("-- id_voce_utente_specie inserito ="+voceUtenteSpecie.getIdVoceUtenteSpecie());
				  }
			  } 
			  
			  logger.debug("-- Aggiornamento dati della domanda");
			  domanda.setIdUtenteAggiornamento(utente.getId());
			  domanda.setDataAggiornamento(new Date());
			  carTDomandaMapper.updateByPrimaryKey(domanda);
		  }  
	  }
	  return idVoceUtenteInserito;
  }
  
  @Override
  public Long salvaSitoProduzioneCentroAz(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException{
	  Long idSitoProdInserito=null;
	  logger.debug("--- Sito produzione da salvare su db ="+form.getDescSitoProduzione());
	  logger.debug("--- idCentroAziendale al quale legare i dati ="+form.getIdCentroAziendaleSel());
	  
	//  if(null != form.getDescSitoProduzione() && !form.getDescSitoProduzione().isEmpty() &&
	//		  null != form.getIdCentroAziendaleSel()){
		if(null != form.getMappale() && !form.getMappale().isEmpty() && null!= form.getIdCentroAziendaleSel()){  
		  
		  CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(form.getIdDomanda());
		  if(null != domanda){		  
			  /*
			  * Si effettua il lock sul record della domanda per evitare modifiche concorrenti
			  */
			  domandaMapper.lockDomanda(form.getIdDomanda());
			  
			  logger.debug("-- *** Inserimento SITI PRODUZIONE legate al centro aziendale : insert in CAR_T_SITO_PRODUZIONE");
			  CarTSitoProduzione carTSitoProduzione = setDatiSitoProduzione(form, utente);			 					
			  sitoProduzioneMapper.insert(carTSitoProduzione);
			  idSitoProdInserito = carTSitoProduzione.getIdSitoProduzione();
			  logger.debug("-- id_sito_produzione inserito ="+idSitoProdInserito);

			  logger.debug("-- Aggiornamento dati della domanda");
			  domanda.setIdUtenteAggiornamento(utente.getId());
			  domanda.setDataAggiornamento(new Date());
			  carTDomandaMapper.updateByPrimaryKey(domanda);
		  }	  
		  
	  }
	  return idSitoProdInserito;
  }
  
  
  private CarTSitoProduzione setDatiSitoProduzione(NuovaDomandaForm form, UtenteDTO utente){
	  CarTSitoProduzione sitoProd = new CarTSitoProduzione();
	  sitoProd.setDataInsert(new Date());
	  sitoProd.setDenominazione(form.getDescSitoProduzione());
	  
	  logger.debug("-- foglio ="+form.getFoglio());
	  sitoProd.setFoglio(Long.valueOf(form.getFoglio()).longValue());
	  logger.debug("-- *** idCentroAziendale ="+form.getIdCentroAziendaleSel());
	  sitoProd.setIdCentroAziendale(form.getIdCentroAziendaleSel());
	  sitoProd.setIdComune(form.getComuneSitoProd());
	  sitoProd.setIdUtenteInsert(utente.getId());
	  
	  logger.debug("-- mappale ="+form.getMappale());
	  sitoProd.setMappale(Long.valueOf(form.getMappale()).longValue());
	 
	  String superficie = form.getSuperficie();
	  logger.debug("-- superficie ="+superficie);
	  sitoProd.setSuperficie(CaronteUtils.parseBigDecimal(form.getSuperficie()));	  
	  
	  sitoProd.setUbicazione(form.getUbicazione());
	  return sitoProd;
  }
  
  private Long inserisciVoceUtente(Long idVoce, Long idDomanda, Long idUtente) throws BusinessException{	 
	  logger.debug("-- inserisciVoceUtente idVoce ="+idVoce);  
	  CarTVoceUtente voceUtente = new CarTVoceUtente();		  
	  voceUtente.setIdVoce(idVoce);
	  voceUtente.setIdDomanda(idDomanda);
	  voceUtente.setIdUtenteInserimento(idUtente);
	  voceUtente.setDataInserimento(new Date());
	  
	  voceUtenteMapper.insert(voceUtente);
	  	  
	  return voceUtente.getIdVoceUtente();
  }
  
  private void eliminaVoceUtente(Long idDomanda, Long idTipoModello, Long gruppo) throws BusinessException{
	  logger.debug("BEGIN eliminaVoceUtente");	  
	  domandaMapper.eliminaVoceUtente(idDomanda, idTipoModello, gruppo);
  }
  
  
  @Override
  public Long salvaVociPassaporto(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException{
	  Long idVoceUtenteInserito = null;	 
	  Long idDomanda = form.getIdDomanda();
	  
	  domandaMapper.lockDomanda(idDomanda);
	  
	  // ******* Salvo i dati del responsabile fitosanitario
	  Long idResponsabilePassaporto = null;
	  // Controllo se per la domanda in oggetto ci sono già i dati del responsabile fitosanitario
	  CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(idDomanda);
	  if(form.getCheckRespFito() != null && !form.getCheckRespFito().isEmpty()){
		  logger.debug("-- Salva dati responsabile fitosanitario Passaporto");		  
		  if(domanda.getIdResponsabilePassaporto() != null){
			  logger.debug("--- UPDATE CAR_T_RESPONSABILE_PASSAPORTO");
			  logger.debug("-- idResponsabilePassaporto da aggiornare ="+domanda.getIdResponsabilePassaporto());
			  
			  CarTResponsabilePassaporto respDb = respPassaportoMapper.selectByPrimaryKey(domanda.getIdResponsabilePassaporto());
			  
			  idResponsabilePassaporto = domanda.getIdResponsabilePassaporto();
			  
			  // Aggiorno i dati del responsabile passaporto			  
			  CarTResponsabilePassaporto resp = new CarTResponsabilePassaporto();
			  resp.setCodiceFiscale(form.getCodFiscaleRespPass().trim().toUpperCase());
			  resp.setCognome(form.getCognomeRespPass().trim().toUpperCase());
			  resp.setNome(form.getNomeRespPass().trim().toUpperCase());
			  resp.setCellulare(form.getCellulareRespPass());
			  resp.setTelefono(form.getNumTelefonoRespPass());
			  resp.setEmail(form.getEmailRespPass());
			  resp.setQualificaProfessionale(form.getQualificaProfRespPass().trim().toUpperCase());
			  resp.setDataAggiornamento(new Date());
			  resp.setIdUtenteAggiornamento(utente.getId());
			  
			  resp.setDataInserimento(respDb.getDataInserimento());
			  resp.setIdUtenteInserimento(respDb.getIdUtenteInserimento());
			  resp.setIdResponsabilePassaporto(respDb.getIdResponsabilePassaporto());
			  
			  respPassaportoMapper.updateByPrimaryKey(resp);
		  }
		  else{
			  logger.debug("--- INSERT INTO CAR_T_RESPONSABILE_PASSAPORTO");
			  CarTResponsabilePassaporto resp = new CarTResponsabilePassaporto();		  
			  
			  resp.setCodiceFiscale(form.getCodFiscaleRespPass().trim().toUpperCase());
			  resp.setCognome(form.getCognomeRespPass().trim().toUpperCase());
			  resp.setNome(form.getNomeRespPass().trim().toUpperCase());
			  resp.setCellulare(form.getCellulareRespPass());
			  resp.setTelefono(form.getNumTelefonoRespPass());
			  resp.setEmail(form.getEmailRespPass());
			  resp.setQualificaProfessionale(form.getQualificaProfRespPass().trim().toUpperCase());
			  
			  resp.setDataInserimento(new Date());
			  resp.setIdUtenteInserimento(utente.getId());
			  respPassaportoMapper.insertSelective(resp);
			  idResponsabilePassaporto = resp.getIdResponsabilePassaporto();
			  logger.debug("-- idResponsabilePassaporto inserito ="+idResponsabilePassaporto);
		  }
	  }
	  else{
		  logger.debug("-- Elimino eventuali dati del Responsabile del Passaporto");
		  if(domanda.getIdResponsabilePassaporto() != null){			
			// Aggiorno la domanda togliendo l'id_responsabile_passaporto
			logger.debug("-- Aggiorno la domanda togliendo l'id_responsabile_passaporto");  
			Long idRespDaEliminare = domanda.getIdResponsabilePassaporto();
			logger.debug("-- idRespDaEliminare = "+idRespDaEliminare);
			domanda.setIdResponsabilePassaporto(null);
			carTdomandaMapper.updateByPrimaryKey(domanda);
			
			logger.debug("--- DELETE CAR_T_RESPONSABILE_PASSAPORTO con idResponsabilePassaporto ="+domanda.getIdResponsabilePassaporto());
			respPassaportoMapper.deleteByPrimaryKey(idRespDaEliminare);			
		  }		  
	  }
	  
	  //salvo le prime voci selezionate(radio e check) 
	  // radio tipo richiesta passaporto 'Nuova richiesta' o 'Aggiornamento'
	  String [] idVoceRadioTipoRichiestaArr = form.getIdVoceRadioTipoRichiesta();	  
	  logger.debug("-- Elimina idVoce Radio tipo richiesta Passaporto per idDomanda ="+idDomanda);
	  eliminaVoceUtente(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 1L);
	  if (idVoceRadioTipoRichiestaArr != null && idVoceRadioTipoRichiestaArr.length > 0) {	  
	   	  for (int i = 0; i < idVoceRadioTipoRichiestaArr.length; i++) {
	   		  CarDVoce voceRadio = new CarDVoce();        	
	          voceRadio.setIdVoce(Long.parseLong(idVoceRadioTipoRichiestaArr[i]));
	          logger.debug("-- Inserimento idVoce Radio ="+voceRadio.getIdVoce());
	          idVoceUtenteInserito = inserisciVoceUtente(voceRadio.getIdVoce(), idDomanda, utente.getId());          
	   	  }
   	  }  
	  
	  //radio
	  String [] idVoceRadioArr = form.getIdVoceRadio();	  
	  logger.debug("-- Elimina idVoce Radio Passaporto per idDomanda ="+idDomanda);
	  eliminaVoceUtente(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 2L);
	  if (idVoceRadioArr != null && idVoceRadioArr.length > 0) {	  
	   	  for (int i = 0; i < idVoceRadioArr.length; i++) {
	   		  CarDVoce voceRadio = new CarDVoce();        	
	          voceRadio.setIdVoce(Long.parseLong(idVoceRadioArr[i]));
	          logger.debug("-- Inserimento idVoce Radio ="+voceRadio.getIdVoce());
	          idVoceUtenteInserito = inserisciVoceUtente(voceRadio.getIdVoce(), idDomanda, utente.getId());          
	   	  }
   	  }
   	  // check
   	  String [] idVoceCheckArr = form.getIdVoceCheck();
   	  Long idTipologiaPassaporto = CaronteConstants.ID_TIPOLOGIA_PASSAPORTO_ORDINARIO; // assegno sempre di default ORDINARIO
   	  
   	  logger.debug("-- Elimina idVoce Check Passaporto per idDomanda ="+idDomanda);
	  eliminaVoceUtente(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 3L);
  	  if (idVoceCheckArr != null && idVoceCheckArr.length > 0) {	  
	   	  for (int i = 0; i < idVoceCheckArr.length; i++) {
	   		  CarDVoce voceCheck = new CarDVoce();        	
	          voceCheck.setIdVoce(Long.parseLong(idVoceCheckArr[i]));
	          if (voceCheck.getIdVoce() == CaronteConstants.ID_VOCE_PASSAPORTO_ZONA_PROTETTA) {
	        	  idTipologiaPassaporto = CaronteConstants.ID_TIPOLOGIA_PASSAPORTO_ORDINARIO_ZP;// se viene valorizzata la check allora assegno volore di "ORDINARIO; ZONE PROTETTE"
	          }
	          logger.debug("-- Inserimento idVoce Check ="+voceCheck.getIdVoce());            
	          idVoceUtenteInserito = inserisciVoceUtente(voceCheck.getIdVoce(), idDomanda, utente.getId()); 
	   	  }
   	  }
  	  
  	  // elimino e inserisco le voci su car_t_voce_utente solo se l'utente ha messo SI alle dichiarazioni
  	  eliminaVoceUtente(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 5L);
  	  if (form.getVoceDichiaraConoscenze() != null && form.getVoceDichiaraConoscenze().compareTo("S") == 0) {  		  
  		  CarDVoce voceCheck = new CarDVoce();        	
  		  voceCheck.setIdVoce(CaronteConstants.ID_VOCE_DICHIARA_CONOSCENZE);
  		  logger.debug("-- Inserimento idVoce della dichiarzione ="+voceCheck.getIdVoce());            
  		  idVoceUtenteInserito = inserisciVoceUtente(voceCheck.getIdVoce(), idDomanda, utente.getId());   		  
  	  }
  	  if (form.getVoceDichiaraDisporreSistemi() != null && form.getVoceDichiaraDisporreSistemi().compareTo("S") == 0) {
  		  CarDVoce voceCheck = new CarDVoce();        	
  		  voceCheck.setIdVoce(CaronteConstants.ID_VOCE_DICHIARA_DISPORRE_SISTEMI);
  		  logger.debug("-- Inserimento idVoce della dichiarzione ="+voceCheck.getIdVoce());            
  		  idVoceUtenteInserito = inserisciVoceUtente(voceCheck.getIdVoce(), idDomanda, utente.getId()); 
  		  
	  }
  	  
  	  eliminaVoceUtente(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 6L);
	  if (form.getVocePianoRischi() != null && form.getVocePianoRischi().compareTo("S") == 0) {  		  
		  CarDVoce voceCheck = new CarDVoce();        	
		  voceCheck.setIdVoce(CaronteConstants.ID_VOCE_PIANO_RISCHI);
		  logger.debug("-- Inserimento idVoce della dichiarzione ="+voceCheck.getIdVoce());            
		  idVoceUtenteInserito = inserisciVoceUtente(voceCheck.getIdVoce(), idDomanda, utente.getId());   		  
	  }
  	  
   	  
      // Aggiornamento domanda
	  logger.debug("-- Aggiornamento domanda con idDomanda ="+idDomanda);
	  CarTDomanda domandaDaAgg = new CarTDomanda();
	  domandaDaAgg.setIdUtenteAggiornamento(utente.getId());
	  domandaDaAgg.setDataAggiornamento(new Date());
	  domandaDaAgg.setIdDomanda(idDomanda);
	  domandaDaAgg.setIdResponsabilePassaporto(idResponsabilePassaporto);
	  // salvo il centro aziendale associato al passaporto
	  domandaDaAgg.setIdCentroAziendalePassaporto(form.getIdCentroAziendalePassaporto());
      carTDomandaMapper.updateByPrimaryKeySelective(domandaDaAgg);
      
      // salvo la tipologia passaporto sul centro aziendale selezionato 
      if (form.getIdCentroAziendalePassaporto() != null) {
    	  CarTCentroAziendale centroAz = centroAzMapper.selectByPrimaryKey(form.getIdCentroAziendalePassaporto());
    	  centroAz.setIdTipologiaPassaporto(idTipologiaPassaporto);
    	  centroAz.setDataUpdate(new Date());
    	  centroAz.setIdUtenteUpdate(utente.getId());    	  
    	  centroAzMapper.updateByPrimaryKeySelective(centroAz);
    	  logger.debug("-- Aggiornato il centroAziendale  id= "+form.getIdCentroAziendalePassaporto());
    	  
      }
       	  
   	  return idVoceUtenteInserito;
  }
  
  @Override
  public List<TipologiaProdSpecieDTO> getTipologieProdByIdDomanda(Long idDomanda, Long idTipoModello, Long idGruppo) throws BusinessException{
	  return domandaMapper.getTipologieProdByIdDomanda(idDomanda, idTipoModello, idGruppo);
  }
  
  @Override
  public List<ZonaProtettaSpecieDTO> getZoneProtetteSpecieByIdDomanda(Long idDomanda) throws BusinessException {
  	return domandaMapper.getZoneProtetteSpecieByIdDomanda(idDomanda);
  }
@Override
  public Long salvaTipologiaProdSpeciePassaporto(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException {
	  Long idVoceUtenteInserito = null;	 
	  Long idDomanda = form.getIdDomanda();
	  
	  domandaMapper.lockDomanda(idDomanda);
	  
	  //salvo le voci delle tipologie produttive	
	  CarDVoce voceTpProd = new CarDVoce();        	
	  voceTpProd.setIdVoce(form.getIdTipologiaProd());
	  logger.debug("--  Inserimento idVoce tipologia prod ="+voceTpProd.getIdVoce());
	  idVoceUtenteInserito = inserisciVoceUtente(voceTpProd.getIdVoce(), idDomanda, utente.getId());
	  
	  String[] idSpecieTpProdArr = form.getSpecie();	
	  // verifico che ci sia almeno una specie
	  if (idSpecieTpProdArr != null && idSpecieTpProdArr.length > 0) {
		  for (int i = 0; i < idSpecieTpProdArr.length; i++) {  
	   		  SpecieDTO specie = new SpecieDTO();        	
			  specie.setIdSpecie(Long.parseLong(idSpecieTpProdArr[i]));
		 	  logger.debug("-- Inserimento idVoce specie ="+specie.getIdSpecie());
		 	  // mi recupero il genere della specie
		 	  CarDSpecie dettaglioSpecie = specieMapper.selectByPrimaryKey(specie.getIdSpecie());		 	  
			  // insersco le specie in CAR_R_VOCE_UTENTE_SPECIE
			  CarRVoceUtenteSpecie voceUtenteSpecie = new CarRVoceUtenteSpecie();
			  voceUtenteSpecie.setIdSpecie(specie.getIdSpecie());
			  voceUtenteSpecie.setIdGenere(dettaglioSpecie.getIdGenere());
			  voceUtenteSpecie.setIdVoceUtente(idVoceUtenteInserito);
			  voceUtenteSpecieMapper.insert(voceUtenteSpecie);
			  logger.debug("-- id_voce_utente_specie inserito ="+voceUtenteSpecie.getIdVoceUtenteSpecie());
	   	  }
	  } else {
		  if (form.getIdGenerePass() != null) {
			  CarRVoceUtenteSpecie voceUtenteSpecie = new CarRVoceUtenteSpecie();
			  voceUtenteSpecie.setIdGenere(form.getIdGenerePass());
			  voceUtenteSpecie.setIdVoceUtente(idVoceUtenteInserito);
			  voceUtenteSpecieMapper.insert(voceUtenteSpecie);
			  logger.debug("-- id_voce_utente_specie inserito ="+voceUtenteSpecie.getIdVoceUtenteSpecie());
		  }
	  }
	  
	  return idVoceUtenteInserito;
  }
  
  @Override
  public Long salvaZonaProtettaSpeciePassaporto(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException {
	  Long idZonaProtettaUtenteInserito = null;	 
	  Long idDomanda = form.getIdDomanda();
  
	  //salvo zone protette, generi e specie
	  String [] idGenereZP = form.getGenereZP();
	  String [] idSpecieZPArr = form.getSpecieZP();
	  // verifico che ci sia almeno una specie
	  if (idSpecieZPArr != null && idSpecieZPArr.length > 0) {
		  for (int i = 0; i < idSpecieZPArr.length; i++) {
	   		  CarDZonaProtetta zonaProtetta = new CarDZonaProtetta();        	
	   		  zonaProtetta.setIdGruppoZonaProtetta(form.getIdZonaProtetta());
	          
	          CarDGenere genere = new CarDGenere();        	
	          genere.setIdGenere(Long.parseLong(idGenereZP[0]));      
	          
	          SpecieDTO specie = new SpecieDTO();        	
	          specie.setIdSpecie(Long.parseLong(idSpecieZPArr[i]));
	          logger.debug("-- Inserimento idVoce specie ="+specie.getIdSpecie());
	          
	          CarTZonaProtettaUtente zonaProtettaUtente = new CarTZonaProtettaUtente();
	          zonaProtettaUtente.setIdDomanda(idDomanda);
	          zonaProtettaUtente.setIdGruppoZonaProtetta(zonaProtetta.getIdGruppoZonaProtetta());
	          zonaProtettaUtente.setIdGenere(genere.getIdGenere());
	          zonaProtettaUtente.setIdSpecie(specie.getIdSpecie());
	          zonaProtettaUtente.setDataInserimento(new Date());
	          zonaProtettaUtente.setIdUtenteInserimento(utente.getId());
	          zonaProtettaUtenteMapper.insert(zonaProtettaUtente);
	          idZonaProtettaUtenteInserito = zonaProtettaUtente.getIdZonaProtettaUtente();
		  }	
	  }
	  return idZonaProtettaUtenteInserito;
  }

@Override
  /* Attenzione : potrebbe anche esserci il caso in cui non abbiamo idSpecie
   * Per id tipologia selezionata (id_voce_utente e idSpecie) : 
	    1) Per idSpecie selezionato : delete su CAR_R_VOCE_UTENTE_SPECIE per idVoceUtente e idSpecie     	
		2) Per idVoceUtente :
		    - controllo se ci sono ancora record su CAR_R_VOCE_UTENTE_SPECIE per lo stesso idVoceUtente
		     Se non ce ne sono : delete su CAR_T_VOCE_UTENTE per idVoceUtente		
	 3) Aggiornamento dati della domanda (data e utente aggiornamento)(non-Javadoc)  
   */
	public void eliminaTipologProduttiva(Long idVoceUtente, Long idSpecie, Long idDomanda, Long idUtente) throws BusinessException{	
	  logger.debug("-- idVoceUtente da ELIMINARE ="+idVoceUtente);
	  logger.debug("-- idSpecie da ELIMINARE ="+idSpecie);
	  if(idVoceUtente != null){		 
			logger.debug("--- 1) DELETE su CAR_R_VOCE_UTENTE_SPECIE con idVoceUtente ="+idVoceUtente +" e idSpecie ="+idSpecie);	  
			CarRVoceUtenteSpecieExample voceUtenteSpEx = new CarRVoceUtenteSpecieExample();
			if(idSpecie != null) {
			  voceUtenteSpEx.createCriteria().andIdVoceUtenteEqualTo(idVoceUtente).andIdSpecieEqualTo(idSpecie);
			} else {
			  voceUtenteSpEx.createCriteria().andIdVoceUtenteEqualTo(idVoceUtente);		
			}
			voceUtenteSpecieMapper.deleteByExample(voceUtenteSpEx);
		 
		  
		  logger.debug("-- Controllo se ci sono ancora record su CAR_R_VOCE_UTENTE_SPECIE per lo stesso idVoceUtente");
		  CarRVoceUtenteSpecieExample voceUtenteSpExample = new CarRVoceUtenteSpecieExample();
		  voceUtenteSpExample.createCriteria().andIdVoceUtenteEqualTo(idVoceUtente);
		  List<CarRVoceUtenteSpecie> voceUtenteSpecieList = voceUtenteSpecieMapper.selectByExample(voceUtenteSpExample);
		  // Se non ci sono più altri record su CAR_R_VOCE_UTENTE_SPECIE per lo stesso idVoceUtente, faccio delete su CAR_T_VOCE_UTENTE
		  if(null == voceUtenteSpecieList || voceUtenteSpecieList.size()==0){		  
			  logger.debug("--- 2) DELETE su CAR_T_VOCE_UTENTE con idVoceUtente ="+idVoceUtente);
			  voceUtenteMapper.deleteByPrimaryKey(idVoceUtente);
		  }
		  
		  // Aggiornamento domanda
		  logger.debug("-- Aggiornamento domanda con idDomanda ="+idDomanda);
		  CarTDomanda domanda = new CarTDomanda();
		  domanda.setIdUtenteAggiornamento(idUtente);
	      domanda.setDataAggiornamento(new Date());
	      domanda.setIdDomanda(idDomanda);
	      carTDomandaMapper.updateByPrimaryKeySelective(domanda);
	  }
  }
  
  @Override
  public void eliminaSitoProduzione(Long idSitoProduzione, Long idDomanda, Long idUtente) throws BusinessException{
    logger.debug("-- idSitoProduzione da ELIMINARE ="+idSitoProduzione);
    if(null != idSitoProduzione){
    	sitoProduzioneMapper.deleteByPrimaryKey(idSitoProduzione);
    	
    	// Aggiornamento domanda
    	logger.debug("-- Aggiornamento domanda con idDomanda ="+idDomanda);
    	CarTDomanda domanda = new CarTDomanda();
		domanda.setIdUtenteAggiornamento(idUtente);
	    domanda.setDataAggiornamento(new Date());
	    domanda.setIdDomanda(idDomanda);
	    carTDomandaMapper.updateByPrimaryKeySelective(domanda);	    	    	    	    
    }
  }
  
  @Override
  public void slegaCentroAziendaleDomanda(Long idCentroAz, Long idDomanda, Long idUtente) throws BusinessException{
	  logger.debug("-- idDomanda ="+idDomanda);
	  logger.debug("-- idCentroAziendale da SLEGARE DALLA DOMANDA ="+idCentroAz);
	  if(null != idCentroAz){
		  CarRDomandaCentroAzExample ex = new CarRDomandaCentroAzExample();
		  ex.createCriteria().andIdCentroAziendaleEqualTo(idCentroAz).andIdDomandaEqualTo(idDomanda);
		  domandaCentroAzMapper.deleteByExample(ex);

		  // Aggiornamento domanda
		  logger.debug("-- Aggiornamento domanda con idDomanda ="+idDomanda);
		  CarTDomanda domanda = new CarTDomanda();
		  domanda.setIdUtenteAggiornamento(idUtente);
		  domanda.setDataAggiornamento(new Date());
		  domanda.setIdDomanda(idDomanda);
		  carTDomandaMapper.updateByPrimaryKeySelective(domanda);	    	    	    	    
	  } 
  }
  
  @Override
  public void eliminaZonaProtetta(Long idGruppoZonaProtetta, Long idSpecie, Long idDomanda, Long idUtente) throws BusinessException {	
	  logger.debug("-- idGruppoZonaProtetta da ELIMINARE ="+idGruppoZonaProtetta);
	  logger.debug("-- idSpecie da ELIMINARE ="+idSpecie);
	  if(idGruppoZonaProtetta != null){
		  if(idSpecie != null){
			  CarTZonaProtettaUtenteExample zonaProtettaUtente = new CarTZonaProtettaUtenteExample();
			  zonaProtettaUtente.createCriteria().andIdGruppoZonaProtettaEqualTo(idGruppoZonaProtetta).andIdDomandaEqualTo(idDomanda).andIdSpecieEqualTo(idSpecie);	  
			  zonaProtettaUtenteMapper.deleteByExample(zonaProtettaUtente);
		  }
		  
		  // Aggiornamento domanda
		  logger.debug("-- Aggiornamento domanda con idDomanda ="+idDomanda);
		  CarTDomanda domanda = new CarTDomanda();
		  domanda.setIdUtenteAggiornamento(idUtente);
	      domanda.setDataAggiornamento(new Date());
	      domanda.setIdDomanda(idDomanda);
	      carTDomandaMapper.updateByPrimaryKeySelective(domanda);
	  }
  }
  
  @Override
  public Long salvaTipologiaProdSpecieImport(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException{
	  Long idVoceUtenteInserito= null;
	  Long idTipologia = form.getIdTipologiaProdImport();
	  logger.debug("--- idTipologia da salvare su db per IMPORT ="+idTipologia);	  
	  String[] idSpecieList = form.getSpecieImport();
	  	  			
      // 1) Per ogni tipologia selezionata : Insert CAR_T_VOCE_UTENTE
	  if(null != idTipologia){
		  CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(form.getIdDomanda());
		  if(null != domanda){		  
			  /*
			  * Si effettua il lock sul record della domanda per evitare modifiche concorrenti
			  */
			  domandaMapper.lockDomanda(form.getIdDomanda());
			  
			  logger.debug("-- *** Inserimento TIPOLOGIE PRODUTTIVE IMPORT : insert in CAR_T_VOCE_UTENTE e CAR_R_VOCE_UTENTE_SPECIE");
			  CarTVoceUtente voceUtente = new CarTVoceUtente();		  
			  logger.debug("-- idTipologia da inserire ="+idTipologia);
			  voceUtente.setDataInserimento(new Date());			  
			  voceUtente.setIdDomanda(form.getIdDomanda());
			  voceUtente.setIdUtenteInserimento(utente.getId());
			  voceUtente.setIdVoce(idTipologia);
			  voceUtente.setNote(form.getNoteTipologiaImport());
			  voceUtenteMapper.insert(voceUtente);
			  idVoceUtenteInserito = voceUtente.getIdVoceUtente();
			  logger.debug("-- idVoceUtenteInserito ="+idVoceUtenteInserito);
	
			  // 2) Per ogni specie selezionata :  Insert CAR_R_VOCE_UTENTE_SPECIE
			  if(null != idSpecieList){
				  logger.debug("-- Numero di specie da inserire per IMPORT ="+idSpecieList.length+" per idTipologia ="+idTipologia);
				  for(int j=0;j<idSpecieList.length;j++){
					  String idSpecie = idSpecieList[j];
					  Long idSpecieL = Long.parseLong(idSpecie);
					  logger.debug("-- idSpecie da inserire ="+idSpecieL);
					  // mi recupero il genere della specie
				 	  CarDSpecie dettaglioSpecie = specieMapper.selectByPrimaryKey(idSpecieL);
	
					  CarRVoceUtenteSpecie voceUtenteSpecie = new CarRVoceUtenteSpecie();
					  voceUtenteSpecie.setIdSpecie(idSpecieL);
					  voceUtenteSpecie.setIdGenere(dettaglioSpecie.getIdGenere()); // valorizzo anche il genere
					  voceUtenteSpecie.setIdVoceUtente(idVoceUtenteInserito);
					  voceUtenteSpecieMapper.insert(voceUtenteSpecie);
					  logger.debug("-- id_voce_utente_specie inserito ="+voceUtenteSpecie.getIdVoceUtenteSpecie());
				  }				  
			  } else {
				  if (form.getIdGenereImport() != null) {
					 // se non ho la specie allora valorizzo solo il genere(caso genere famiglia)
					  CarRVoceUtenteSpecie voceUtenteSpecie = new CarRVoceUtenteSpecie();
					  voceUtenteSpecie.setIdGenere(form.getIdGenereImport()); 
					  voceUtenteSpecie.setIdVoceUtente(idVoceUtenteInserito);
					  voceUtenteSpecieMapper.insert(voceUtenteSpecie);
					  logger.debug("-- id_voce_utente_specie inserito ="+voceUtenteSpecie.getIdVoceUtenteSpecie());
				  }
			  }
			  
			  logger.debug("-- Aggiornamento dati della domanda");
			  domanda.setIdUtenteAggiornamento(utente.getId());
			  domanda.setDataAggiornamento(new Date());
			  carTDomandaMapper.updateByPrimaryKey(domanda);
		  }  
	  }
	  return idVoceUtenteInserito;
  }
  
  
  @Override
  public void salvaDatiImport(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException{	 
	  Long idDomanda = form.getIdDomanda();
	  
	  logger.debug("BEGIN salvaDatiImport");
	  logger.debug("-- idDomanda ="+form.getIdDomanda());
	  domandaMapper.lockDomanda(idDomanda);
	  
	  // Salvo RADIO Zone protette e check Continenti di importazione (le tipologie produttive sono già state salvate con l'aggiungi)
	  logger.debug("-- 1) Salvo radio Zone protette");	  	  
	  // Salvo Radio Zone protette + note
	  String [] idVoceRadioArr = form.getIdVoceRadioZonaProtetta();	  
	  // DELETE IN CAR_T_VOCE_UTENTE 
	  logger.debug("-- DELETE IN CAR_T_VOCE_UTENTE per Radio Zone protette Import");
	  eliminaVoceUtente(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 3L);
	  if (idVoceRadioArr != null && idVoceRadioArr.length > 0) {	  
	   	  for (int i = 0; i < idVoceRadioArr.length; i++) {
	   		  CarDVoce voceRadio = new CarDVoce();        	
	          voceRadio.setIdVoce(Long.parseLong(idVoceRadioArr[i]));
	          logger.debug("-- Inserimento idVoce Radio Zone protette import ="+voceRadio.getIdVoce());
	          // INSERT IN CAR_T_VOCE_UTENTE
	          inserisciVoceUtente(voceRadio.getIdVoce(), idDomanda, utente.getId());          
	   	  }
   	  }
	  
      // Informazioni aggiuntive (impDatoAggiuntivo) : CAR_T_DATI_DOMANDA.IMP_DATO_AGGIUNTIVO
	  if(form.getImpDatoAggiuntivo() != null && !form.getImpDatoAggiuntivo().isEmpty()){
		  logger.debug("-- 2) Salvo informazioni aggiuntive Import");
		  salvoDatiDomanda(form, utente, form.getImpDatoAggiuntivo().trim(), null, null);  
	  }
	  
   	  // Salvo CHECK continenti di importazione + note
	  logger.debug("-- 3) Salvo check continenti di importazione");
   	  String [] idVoceCheckArr = form.getIdVoceCheckContinenti();
   	  
   	  // DELETE IN CAR_T_VOCE_UTENTE 
	  logger.debug("-- DELETE IN CAR_T_VOCE_UTENTE per Check Continenti Import");
	  eliminaVoceUtente(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 2L);
	  if (idVoceCheckArr != null && idVoceCheckArr.length > 0) {	
			Long idVoceUtenteInserito = null;
			for (int i = 0; i < idVoceCheckArr.length; i++) {
				CarDVoce voceCheck = new CarDVoce();        	
				voceCheck.setIdVoce(Long.parseLong(idVoceCheckArr[i]));
				logger.debug("-- Inserimento idVoce Check Continente import="+voceCheck.getIdVoce());  
				// INSERT IN CAR_T_VOCE_UTENTE
				idVoceUtenteInserito = inserisciVoceUtente(voceCheck.getIdVoce(), idDomanda, utente.getId()); 
			}		

			// Controllo se è stata compilata anche la parte "Indicare eventuali Stati di origine" (statoOrigine)
			if(form.getStatoOrigine() != null && !form.getStatoOrigine().isEmpty()){
				// Update CAR_T_VOCE_UTENTE.NOTE dell'ultimo id_voce_utente inserito per i continenti
				logger.debug(" -- idVoceUtenteInserito ="+idVoceUtenteInserito);
				logger.debug("-- 4) Salvo 'Indicare eventuali Stati di origine' : UPDATE CAR_T_VOCE_UTENTE.NOTE PER inserire");
				CarTVoceUtente voceUtenteContinente = voceUtenteMapper.selectByPrimaryKey(idVoceUtenteInserito);
				if(null != voceUtenteContinente){
					voceUtenteContinente.setNote(form.getStatoOrigine().trim().toUpperCase());
					voceUtenteMapper.updateByPrimaryKeySelective(voceUtenteContinente);
				}
			}
		}  
	   	  
	   	  
   	  
   	  
      // Campo note in fondo alla pagina (noteImport) : CAR_T_DATI_DOMANDA.IMP_NOTE
   	  if(form.getNoteImport() != null && !form.getNoteImport().isEmpty()){
   		  logger.debug("-- 5) Salvo campo note import");
   		  salvoDatiDomanda(form, utente, null, form.getNoteImport().trim(), null);  
   	  }
   	  
   	  
      // Aggiornamento domanda
	  logger.debug("-- 6) Aggiornamento domanda con idDomanda ="+idDomanda);
	  CarTDomanda domanda = new CarTDomanda();
	  domanda.setIdUtenteAggiornamento(utente.getId());
	  domanda.setDataAggiornamento(new Date());
	  domanda.setIdDomanda(idDomanda);
	  carTDomandaMapper.updateByPrimaryKeySelective(domanda);	     	  
  }

  
  // Insert o Update su CAR_T_DATI_DOMANDA
  private void salvoDatiDomanda(NuovaDomandaForm form, UtenteDTO utente, String impDatoAggiuntivo, String noteImport, String expDatoAggiuntivo) throws BusinessException{
	  logger.debug("BEGIN salvoDatiDomanda");
	  
	  // Cerco se c'è già un record su CAR_T_DATI_DOMANDA PER LA DOMANDA IN OGGETTO
	  CarTDatiDomanda datiDomanda = new CarTDatiDomanda();
	  CarTDatiDomandaExample datiDomandaEx = new CarTDatiDomandaExample();
	  datiDomandaEx.createCriteria().andIdDomandaEqualTo(form.getIdDomanda());
	  List<CarTDatiDomanda> datiDomandaList = datiDomandaMapper.selectByExample(datiDomandaEx);
	  if(null != datiDomandaList && datiDomandaList.size()>0){
		  logger.debug("-- Esiste già un record in CAR_T_DATI_DOMANDA per idDomanda ="+form.getIdDomanda());
		  datiDomanda = datiDomandaList.get(0);
		  
		  if(null != impDatoAggiuntivo && !impDatoAggiuntivo.isEmpty()){
			logger.debug("-- salvo dati in CAR_T_DATI_DOMANDA.IMP_DATO_AGGIUNTIVO");
		    datiDomanda.setImpDatoAggiuntivo(impDatoAggiuntivo.toUpperCase());
		  }
		  if(null != noteImport && !noteImport.isEmpty()){
			logger.debug("-- salvo dati in CAR_T_DATI_DOMANDA.IMP_NOTE");
		    datiDomanda.setImpNote(noteImport.toUpperCase());
		  }
		  if(null != expDatoAggiuntivo && !expDatoAggiuntivo.isEmpty()){
			 logger.debug("-- salvo dati in CAR_T_DATI_DOMANDA.EXP_DATO_AGGIUNTIVO");
			 datiDomanda.setExpDatoAggiuntivo(expDatoAggiuntivo.toUpperCase());
		  }
		  
		  datiDomanda.setIdUtenteAggiornamento(utente.getId());
		  datiDomanda.setDataAggiornamento(new Date());
		  logger.debug("-- UPDATE CAR_T_DATI_DOMANDA");
		  datiDomandaMapper.updateByPrimaryKeySelective(datiDomanda);
	  }
	  else{
		  logger.debug("-- Non esiste ancora un record in CAR_T_DATI_DOMANDA per idDomanda ="+form.getIdDomanda());
		  datiDomanda.setIdDomanda(form.getIdDomanda());
		  datiDomanda.setDataInserimento(new Date());
		  datiDomanda.setIdUtenteInserimento(utente.getId());
		  
		  if(null != impDatoAggiuntivo && !impDatoAggiuntivo.isEmpty()){
			logger.debug("-- salvo dati in CAR_T_DATI_DOMANDA.IMP_DATO_AGGIUNTIVO");
		    datiDomanda.setImpDatoAggiuntivo(impDatoAggiuntivo.toUpperCase());
		  }
		  if(null != noteImport && !noteImport.isEmpty()){
		    logger.debug("-- salvo dati in CAR_T_DATI_DOMANDA.IMP_NOTE");
			datiDomanda.setImpNote(noteImport.toUpperCase());
		  }
		  if(null != expDatoAggiuntivo && !expDatoAggiuntivo.isEmpty()){
			 logger.debug("-- salvo dati in CAR_T_DATI_DOMANDA.EXP_DATO_AGGIUNTIVO");
			 datiDomanda.setExpDatoAggiuntivo(expDatoAggiuntivo.toUpperCase());
		  }
		  
		  logger.debug("-- INSERT CAR_T_DATI_DOMANDA");
		  datiDomandaMapper.insertSelective(datiDomanda);
	  }
  }
  
  @Override
  public List<TipologiaAttMaterialeDTO> getTipologieAttMateriale(Long iDomanda) throws BusinessException{
	  return domandaMapper.getTipologieAttMateriale(iDomanda);
  }
  
  @Override
  public void salvaTipologiaAttMateriale(ModaliForm form, UtenteDTO utente) throws BusinessException {

	  Long idTipoAttivita = form.getIdTipoAttivitaMat();
	  logger.debug("--- idTipoAttivita da salvare su db ="+idTipoAttivita);	
	  logger.debug("-- idDomanda "+form.getIdDomanda());
	  
      // 1) Per ogni tipologia attivita selezionata : Insert su car_r_att_materiale_utente
	  if(null != idTipoAttivita){
		  CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(form.getIdDomanda());
		  if(null != domanda){		  
			  /*
			  * Si effettua il lock sul record della domanda per evitare modifiche concorrenti
			  */
			  domandaMapper.lockDomanda(form.getIdDomanda());
			  
			  logger.debug("-- idDomanda ="+form.getIdDomanda());
			  logger.debug("-- idTipoAttivita ="+idTipoAttivita);			  
			  
			  if (form.getIdAttivitaMaterialeUtente() != null ) {
				  // sono nel caso di modifica di un'attivita materiale
				  // cancello il record già presente
				  attMaterialeUtenteMapper.deleteByPrimaryKey(form.getIdAttivitaMaterialeUtente());				 
			  } 
			  // se sono in modifica ho un solo materiale da gestire
			  if(null != form.getIdMateriale()) {
				  CarRAttMaterialeUtente attMaterialeUtente = new CarRAttMaterialeUtente();
				  attMaterialeUtente.setDataInserimento(new Date());
				  attMaterialeUtente.setIdDomanda(form.getIdDomanda());
				  logger.debug("-- idMateriale ="+form.getIdMateriale());
				  attMaterialeUtente.setIdMateriale(form.getIdMateriale());
				  attMaterialeUtente.setIdTipoAttivita(idTipoAttivita);
				  attMaterialeUtente.setRichiestaPassaporto("N");
				  attMaterialeUtente.setIdUtenteInserimento(utente.getId());
				  attMaterialeUtenteMapper.insert(attMaterialeUtente);
			  }
			  // se sono in aggiungi potrei avere piu' materiali da inserire
			  else if (null != form.getIdMaterialeList() && form.getIdMaterialeList().length > 0) {
				  String[] idMaterialeList = form.getIdMaterialeList();
				  for (int i = 0; i < form.getIdMaterialeList().length; i++) {
				    	CarRAttMaterialeUtente attMaterialeUtente = new CarRAttMaterialeUtente();
						attMaterialeUtente.setDataInserimento(new Date());
						attMaterialeUtente.setIdDomanda(form.getIdDomanda());
						logger.debug("-- idMateriale ="+idMaterialeList[i]);
						attMaterialeUtente.setIdMateriale(Long.valueOf(idMaterialeList[i]));
						attMaterialeUtente.setIdTipoAttivita(idTipoAttivita);
						attMaterialeUtente.setRichiestaPassaporto("N");
						attMaterialeUtente.setIdUtenteInserimento(utente.getId());						
						attMaterialeUtenteMapper.insert(attMaterialeUtente);
					} 
			  } else {
				  logger.debug("-- *** CASO NON ci sono  materiali : inserimento TIPOLOGIA ATTIVITA - TAB TIPOLOGIA : insert in car_r_att_materiale_utente per Tipologia attivita selezionata");
				  CarRAttMaterialeUtente attMaterialeUtente = new CarRAttMaterialeUtente();
				  attMaterialeUtente.setDataInserimento(new Date());
				  attMaterialeUtente.setIdDomanda(form.getIdDomanda());					
				  attMaterialeUtente.setIdTipoAttivita(idTipoAttivita);
				  attMaterialeUtente.setNote(form.getNote());
				  attMaterialeUtente.setRichiestaPassaporto("N");
				  attMaterialeUtente.setIdUtenteInserimento(utente.getId());	
				  attMaterialeUtenteMapper.insert(attMaterialeUtente);
			  }
			  
			 		  
			  logger.debug("-- Aggiornamento dati della domanda");
			  domanda.setIdUtenteAggiornamento(utente.getId());
			  domanda.setDataAggiornamento(new Date());
			  carTDomandaMapper.updateByPrimaryKey(domanda);
			  
		  }
	  }
  }



@Override
public byte[] getTemplateTipoStampa(Long idDomanda) throws BusinessException {

  logger.debug("-- getTemplateTipoStampa idDomanda = " + idDomanda);
  CarTDomandaExample exampleComunicazione = new CarTDomandaExample();
  exampleComunicazione.createCriteria().andIdDomandaEqualTo(idDomanda);
  List<CarTDomanda> listaComunicazione = carTDomandaMapper.selectByExample(exampleComunicazione);

  if (null != listaComunicazione) {
    // mi recupero il tipo di comunicazione dalla comunicazione
    Long idTipoComunicazione = listaComunicazione.get(0).getIdTipoComunicazione();
    CarDTipoComunicazioneExample exampleTipoComunicazione = new CarDTipoComunicazioneExample();
    exampleTipoComunicazione.createCriteria().andIdTipoComunicazioneEqualTo(idTipoComunicazione);
    List<CarDTipoComunicazione> listaTipoComunicazione = tipoComunicazioneMapper
        .selectByExample(exampleTipoComunicazione);
    if (null != listaTipoComunicazione) {
      // mi recupero il tipo di stampa dal tipo di comunicazione
      Long idTipoStampa = listaTipoComunicazione.get(0).getIdTipoStampa();
      CarDTipoStampaExample exampleTipoStampa = new CarDTipoStampaExample();
      exampleTipoStampa.createCriteria().andIdTipoStampaEqualTo(idTipoStampa);
      //Mapper, valutare se usare StampeMapper(probabilmente si)
      List<CarDTipoStampa> listaTipoStampa = tipoStampaMapper.selectByExampleWithBLOBs(exampleTipoStampa);
      if (null != listaTipoStampa) {
        return listaTipoStampa.get(0).getTemplate();
      }
    }
  }
  return null;
}

@Override
public byte[] getTemplateTipoStampaById(Long idTipoStampa) throws BusinessException {

  logger.debug("-- getTemplateTipoStampaById idTipoStampa = " + idTipoStampa);
  if (null != idTipoStampa) {
    // mi recupero il tipo di stampa dal tipo di comunicazione
    CarDTipoStampaExample exampleTipoStampa = new CarDTipoStampaExample();
    exampleTipoStampa.createCriteria().andIdTipoStampaEqualTo(idTipoStampa);
    List<CarDTipoStampa> listaTipoStampa = tipoStampaMapper.selectByExampleWithBLOBs(exampleTipoStampa);
    if (null != listaTipoStampa) {
      return listaTipoStampa.get(0).getTemplate();
    }
  }

  return null;
}
  

  
  @Override
  /* - Elimina tipologia attivita e materiale
   * 1) Elimina il record in car_r_att_materiale_utente
	 2) Aggiornamento dati della domanda (data e utente aggiornamento)  
   */
  public void eliminaTipologAttivita(Long idAttivitaMaterialeUtente, Long idDomanda, Long idUtente) throws BusinessException{		  	 
	  if(idAttivitaMaterialeUtente != null){		 		  
		  logger.debug("--- 1) DELETE su car_r_att_materiale_utente con idAttivitaMaterialeUtente ="+idAttivitaMaterialeUtente);	    
		  attMaterialeUtenteMapper.deleteByPrimaryKey(idAttivitaMaterialeUtente);

		  // Aggiornamento domanda
		  logger.debug("-- 2) Aggiornamento domanda con idDomanda ="+idDomanda);
		  CarTDomanda domanda = new CarTDomanda();
		  domanda.setIdUtenteAggiornamento(idUtente);
		  domanda.setDataAggiornamento(new Date());
		  domanda.setIdDomanda(idDomanda);
		  carTDomandaMapper.updateByPrimaryKeySelective(domanda);
	  }
  }

  
  @Override  
  public Long salvaTipologiaProdSpecieExport(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException{
	  logger.debug("BEGIN salvaTipologiaProdSpecieExport");
	  Long idVoceUtenteInserito= null;
	  Long idTipologia = form.getIdTipologiaProdExp();
	  logger.debug("--- idTipologia da salvare su db per EXPORT ="+idTipologia);	  
	  String[] idSpecieList = form.getSpecieExport();
	  	  			
      // 1) Per ogni tipologia selezionata : Insert CAR_T_VOCE_UTENTE
	  if(null != idTipologia){
		  CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(form.getIdDomanda());
		  if(null != domanda){		  
			  /*
			  * Si effettua il lock sul record della domanda per evitare modifiche concorrenti
			  */
			  domandaMapper.lockDomanda(form.getIdDomanda());
			  
			  logger.debug("-- *** Inserimento TIPOLOGIE PRODUTTIVE EXPORT : insert in CAR_T_VOCE_UTENTE e CAR_R_VOCE_UTENTE_SPECIE");
			  CarTVoceUtente voceUtente = new CarTVoceUtente();		  
			  logger.debug("-- idTipologia da inserire ="+idTipologia);
			  voceUtente.setDataInserimento(new Date());			  
			  voceUtente.setIdDomanda(form.getIdDomanda());
			  voceUtente.setIdUtenteInserimento(utente.getId());
			  voceUtente.setIdVoce(idTipologia);
			  voceUtente.setNote(form.getNoteTipologiaExport());
			  voceUtenteMapper.insert(voceUtente);
			  idVoceUtenteInserito = voceUtente.getIdVoceUtente();
			  logger.debug("-- idVoceUtenteInserito ="+idVoceUtenteInserito);
	
			  // 2) Per ogni specie selezionata :  Insert CAR_R_VOCE_UTENTE_SPECIE
			  if(null != idSpecieList){
				  logger.debug("-- Numero di specie da inserire per EXPORT ="+idSpecieList.length+" per idTipologia ="+idTipologia);
				  for(int j=0;j<idSpecieList.length;j++){
					  String idSpecie = idSpecieList[j];
					  Long idSpecieL = Long.parseLong(idSpecie);
					  logger.debug("-- idSpecie da inserire ="+idSpecieL);
					  // mi recupero il genere della specie
				 	  CarDSpecie dettaglioSpecie = specieMapper.selectByPrimaryKey(idSpecieL);
	
					  CarRVoceUtenteSpecie voceUtenteSpecie = new CarRVoceUtenteSpecie();
					  voceUtenteSpecie.setIdSpecie(idSpecieL);
					  voceUtenteSpecie.setIdGenere(dettaglioSpecie.getIdGenere());
					  voceUtenteSpecie.setIdVoceUtente(idVoceUtenteInserito);
					  voceUtenteSpecieMapper.insert(voceUtenteSpecie);
					  logger.debug("-- id_voce_utente_specie inserito ="+voceUtenteSpecie.getIdVoceUtenteSpecie());
				  }
			  } else {
				  if (form.getIdGenereExport() != null) {
					  CarRVoceUtenteSpecie voceUtenteSpecie = new CarRVoceUtenteSpecie();			  
					  voceUtenteSpecie.setIdGenere(form.getIdGenereExport());
					  voceUtenteSpecie.setIdVoceUtente(idVoceUtenteInserito);
					  voceUtenteSpecieMapper.insert(voceUtenteSpecie);
					  logger.debug("-- id_voce_utente_specie inserito ="+voceUtenteSpecie.getIdVoceUtenteSpecie());	
				  }
			  }
			  logger.debug("-- Aggiornamento dati della domanda");
			  domanda.setIdUtenteAggiornamento(utente.getId());
			  domanda.setDataAggiornamento(new Date());
			  carTDomandaMapper.updateByPrimaryKey(domanda);
		  }  
	  }
	  return idVoceUtenteInserito;
  }
  
  @Override
  public void salvaDatiExport(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException{
	  logger.debug("BEGIN salvaDatiExport");
	  Long idDomanda = form.getIdDomanda();	  
	  logger.debug("-- idDomanda ="+form.getIdDomanda());
	  domandaMapper.lockDomanda(idDomanda);
	  
	  // Salvo Check con cosa riguardano le esportazioni e check Continenti di esportazione (le tipologie produttive sono già state salvate con l'aggiungi)
	  logger.debug("-- 1) Salvo check per Cosa riguardano le esportazioni");	  	  
	  // Salvo Radio Zone protette + note
	  String [] idVoceCheckTipExpArr = form.getIdVoceCheckTipExp();	 
	  // DELETE IN CAR_T_VOCE_UTENTE 
	  logger.debug("-- DELETE IN CAR_T_VOCE_UTENTE per 'Le esportazioni riguardano'");
	  eliminaVoceUtente(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 1L);
	  if (idVoceCheckTipExpArr != null && idVoceCheckTipExpArr.length > 0) {	  
	   	  for (int i = 0; i < idVoceCheckTipExpArr.length; i++) {
	   		  CarDVoce voceCheckTip = new CarDVoce();        	
	   		  voceCheckTip.setIdVoce(Long.parseLong(idVoceCheckTipExpArr[i]));
	          logger.debug("-- Inserimento idVoceCheckTipExp ="+voceCheckTip.getIdVoce());
	          // INSERT IN CAR_T_VOCE_UTENTE
	          inserisciVoceUtente(voceCheckTip.getIdVoce(), idDomanda, utente.getId());          
	   	  }
   	  }	  
	  
      // Informazioni aggiuntive (expDatoAggiuntivo) : CAR_T_DATI_DOMANDA.EXP_DATO_AGGIUNTIVO
	  if(form.getExpDatoAggiuntivo() != null && !form.getExpDatoAggiuntivo().isEmpty()){
		  logger.debug("-- 2) Salvo informazioni aggiuntive Export");
		  salvoDatiDomanda(form, utente, null, null, form.getExpDatoAggiuntivo());  
	  }
	  
   	  // Salvo CHECK continenti di importazione + note
	  logger.debug("-- 3) Salvo check continenti di esportazione");
   	  String [] idVoceCheckArr = form.getIdVoceCheckContinentiExp();   	  
   	  // DELETE IN CAR_T_VOCE_UTENTE 
	  logger.debug("-- DELETE IN CAR_T_VOCE_UTENTE per Check Continenti Import");
	  eliminaVoceUtente(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 2L);
	  if (idVoceCheckArr != null && idVoceCheckArr.length > 0) {	
   		Long idVoceUtenteInserito = null;
	   	  for (int i = 0; i < idVoceCheckArr.length; i++) {
	   		  CarDVoce voceCheck = new CarDVoce();        	
	          voceCheck.setIdVoce(Long.parseLong(idVoceCheckArr[i]));
	          logger.debug("-- Inserimento idVoce Check Continente export="+voceCheck.getIdVoce());  
	          // INSERT IN CAR_T_VOCE_UTENTE
	          idVoceUtenteInserito = inserisciVoceUtente(voceCheck.getIdVoce(), idDomanda, utente.getId()); 
	   	  }
	   	  // Controllo se è stata compilata anche la parte "Indicare eventuali Stati di origine" (statoOrigine)
	   	  if(form.getStatoOrigineExp() != null && !form.getStatoOrigineExp().isEmpty()){
	   		// Update CAR_T_VOCE_UTENTE.NOTE dell'ultimo id_voce_utente inserito per i continenti
	   		logger.debug(" -- idVoceUtenteInserito ="+idVoceUtenteInserito);
	   		logger.debug("-- 4) Salvo 'Indicare eventuali Stati di origine export' : UPDATE CAR_T_VOCE_UTENTE.NOTE PER inserire");
	   		CarTVoceUtente voceUtenteContinenteExport = voceUtenteMapper.selectByPrimaryKey(idVoceUtenteInserito);
	   		if(null != voceUtenteContinenteExport){
	   			voceUtenteContinenteExport.setNote(form.getStatoOrigineExp().trim().toUpperCase());
	   			voceUtenteMapper.updateByPrimaryKeySelective(voceUtenteContinenteExport);
	   		}
	   	  }
   	  }
   	           	  
      // Aggiornamento domanda
	  logger.debug("-- 5) Aggiornamento domanda con idDomanda ="+idDomanda);
	  CarTDomanda domanda = new CarTDomanda();
	  domanda.setIdUtenteAggiornamento(utente.getId());
	  domanda.setDataAggiornamento(new Date());
	  domanda.setIdDomanda(idDomanda);
	  carTDomandaMapper.updateByPrimaryKeySelective(domanda);	 
  }
  
  @Override
  public List<AllegatoDTO> getListaAllegatiDomanda(Long idDomanda) throws BusinessException {
    return domandaMapper.getListaAllegatiDomanda(idDomanda);
  }
  
  @Override
  public void aggiornaDatiAllegati(Long idDomanda, List<AllegatoDTO> listaAllegati, Long idUtente) throws BusinessException {
	logger.debug("BEGIN aggiornaDatiAllegati");  
    /*
     * Si effettua il lock sul record della domanda per evitare modifiche
     * concorrenti sui file (scrupolo probabilmente eccessivo)
     */
    domandaMapper.lockDomanda(idDomanda);

    aggiornaAllegati(idDomanda, listaAllegati, idUtente);
    
    
    // Aggiorno i dati della domanda
    CarTDomanda domanda = new CarTDomanda();
	logger.debug("-- idDomanda aggiornata = " + idDomanda);
	domanda.setIdDomanda(idDomanda);	
	domanda.setIdUtenteAggiornamento(idUtente);
	domanda.setDataAggiornamento(new Date());
	
	carTDomandaMapper.updateByPrimaryKeySelective(domanda);
  }
  
  
  private void aggiornaAllegati(Long idDomanda, List<AllegatoDTO> listaAllegati, Long idUtente) throws BusinessException {
	  logger.debug("BEGIN aggiornaAllegati");
	  /*
	   * Si ottiene l'elenco aggiornato degli allegati
	   */
	  logger.debug("-- Ricerca dell'elenco aggiornato degli allegati");
	  List<AllegatoDTO> listaAllegatiDB = getListaAllegatiDomanda(idDomanda);

	  for (AllegatoDTO allegatoDB : listaAllegatiDB) {
		  Iterator<AllegatoDTO> iterAllegati = listaAllegati.iterator();

		  while (iterAllegati.hasNext()) {
			  AllegatoDTO allegato = iterAllegati.next();
			  if (allegato.getIdTipoAllegato().equals(allegatoDB.getIdTipoAllegato())) {
				  Iterator<CarTAllegatoDomanda> iterAllegato = allegato.getListaAllegati().iterator();

				  while (iterAllegato.hasNext()) {
					  CarTAllegatoDomanda allegatoInner = iterAllegato.next();

					  if (allegatoInner.getIdAllegatoDomanda() == null) {
						  /*
						   * INSERIMENTO
						   */
						  logger.debug("-- Inserimento allegato"); 
						  allegatoInner.setIdTipoAllegato(allegato.getIdTipoAllegato());
						  allegatoInner.setIdDomanda(idDomanda);
						  allegatoInner.setIdUtenteInserimento(idUtente);
						  allegatoInner.setDataInserimento(new Date());
						  // allegatoInner.setDescAllegato(allegato.getDescAllegato());

						  allegatoDomandaMapper.insert(allegatoInner);
						  logger.debug("-- Inserito allegato con idAllegatoDomanda ="+allegatoInner.getIdAllegatoDomanda());
					  } 
					  else {
						  Iterator<CarTAllegatoDomanda> iterAllegatoDB = allegatoDB.getListaAllegati().iterator();
						  while (iterAllegatoDB.hasNext()) {
							  CarTAllegatoDomanda allegatoInnerDB = iterAllegatoDB.next();
							  if (allegatoInnerDB.getIdAllegatoDomanda().equals(allegatoInner.getIdAllegatoDomanda())) {

								  /*
								   * AGGIORNAMENTO
								   */
								  logger.debug("-- Aggiornamento allegato con idAllegatoDomanda ="+allegatoInnerDB.getIdAllegatoDomanda());
								  allegatoInnerDB = allegatoDomandaMapper.selectByPrimaryKey(allegatoInnerDB.getIdAllegatoDomanda());

								  if (!StringUtils.isEmpty(allegatoInner.getNomeFile())) {
									  allegatoInnerDB.setNomeFile(allegatoInner.getNomeFile());
									  allegatoInnerDB.setAllegato(allegatoInner.getAllegato());
								  }

								  allegatoInnerDB.setDescAllegato(allegatoInner.getDescAllegato());

								  allegatoDomandaMapper.updateByPrimaryKey(allegatoInnerDB);

								  iterAllegatoDB.remove();
								  break;
							  }
						  }
					  }
				  }

				  /*
				   * I record rimasti nella lista di allegati su DB per il tipo allegato
				   * in esame sono da rimuovere (non hanno più corrispondenza con
				   * l'elenco salvato dall'utente)
				   */
				  for (CarTAllegatoDomanda allegatoDaRimuovere : allegatoDB.getListaAllegati()) {
					  /*
					   * ELIMINAZIONE
					   */
					  logger.debug("-- Eliminazione allegato con idAllegatoDomanda ="+allegatoDaRimuovere.getIdAllegatoDomanda());
					  allegatoDomandaMapper.deleteByPrimaryKey(allegatoDaRimuovere.getIdAllegatoDomanda());
				  }

				  iterAllegati.remove();
				  break;
			  }
		  }
	  }
  }
  
  @Override
  public CarTAllegatoDomanda getAllegatoDomandaById(Long idAllegatoDomanda) throws BusinessException{
	  return allegatoDomandaMapper.selectByPrimaryKey(idAllegatoDomanda);
  }
  
  @Override
  public DomandaDto getDettaglioAnagraficaByIdDomanda(Long idDomanda) throws BusinessException{
	  return domandaMapper.getDettaglioAnagraficaByIdDomanda(idDomanda);
  }
  
  @Override
  public TipologiaDomandaDTO getDettTipologiaDomanda(Long idDomanda) throws BusinessException{
	  List<CarRDomandaTipologia> tipologieDomanda = domandaMapper.getDettTipologiaDomanda(idDomanda);
	  TipologiaDomandaDTO tipDomDto = new TipologiaDomandaDTO();
	  tipDomDto.setTipologieDomandaList(tipologieDomanda);
	  
	  return tipDomDto;
  }
  
  @Override
  public List<CarDStatoComunicazione> getListaStatiDomandaSuccessivi(Long idUtente, Long idDomanda) throws BusinessException{
	  return domandaMapper.getListaStatiDomandaSuccessivi(idUtente, idDomanda);
  }
  
  @Override
  public boolean avanzaStatoDomanda(Long idDomanda, Long idNuovoStato, Long idUtente, String motivazione, String codiceRuop) throws BusinessException{
	  	logger.debug("BEGIN avanzaStatoDomanda");
	  	logger.debug("idDomanda per la quale si vuole modificare lo stato =" + idDomanda);
	    Long idStatoDomanda = domandaMapper.lockDomanda(idDomanda);
	    logger.debug("idStatoDomanda presente su db =" + idStatoDomanda);
	    logger.debug("idNuovoStato =" + idNuovoStato);
	    logger.debug("motivazione =" + motivazione);

	    if (domandaMapper.isUtenteAbilitatoModificaDomanda(idUtente, idDomanda) != null) {
	    	logger.debug("-- l'utente è abilitato a modificare la domanda");
	      List<CarDStatoComunicazione> listaStatiSuccessivi = domandaMapper.getListaStatiDomandaSuccessivi(idUtente, idDomanda);

	      for (CarDStatoComunicazione stato : listaStatiSuccessivi) {
	        if (stato.getIdStatoComunicazione().equals(idNuovoStato)) {
	          logger.debug("lo stato è tra quelli previsti in CAR_R_GRUPPO_STATO_SUCCESSIVO, posso procedere con l'inserimento");

	          // Effettuo update su CAR_T_DOMANDA
	          CarTDomanda domanda = new CarTDomanda();

	          domanda.setIdDomanda(idDomanda);
	          logger.debug("aggiorno con idNuovoStato =" + idNuovoStato);
	          domanda.setIdStatoComunicazione(idNuovoStato);
	          logger.debug("idUtente aggiornamento =" + idUtente);
	          domanda.setIdUtenteAggiornamento(idUtente);
	          domanda.setDataAggiornamento(new Date());
	          domanda.setMotivazione(motivazione);	          

	          logger.debug("*** Modifico lo stato della domanda su CAR_T_DOMANDA");
	          carTDomandaMapper.updateByPrimaryKeySelective(domanda);	          	         
	          
	          
	          // Recupero l'utente della domanda
    		  DomandaDto dettaglioDomanda = getDettaglioAnagraficaByIdDomanda(idDomanda);
    		  Long idUtenteDomanda = dettaglioDomanda.getIdUtente();
    		  logger.debug("-- idUtenteDomanda ="+idUtenteDomanda);
    		  // Recupero lo spedizioniere della domanda 	        		  	        		  		
    		  DomandaDto dettaglioAzienda = getDettaglioAziendaByIdDomanda(idDomanda);
    		  Long idSpedizioniereDomanda = dettaglioAzienda.getIdSpedizioniere();
    		  logger.debug("-- idSpedizioniere domanda ="+idSpedizioniereDomanda);
	          
	          /*
	           *  Se si sta cambiando lo stato in 'CONVALIDATA', si deve abilitare l'utente alle sezioni selezionate in CAR_R_DOMANDA_TIPOLOGIA
	           */	          	          
	          if(idNuovoStato.longValue() == CaronteConstants.STATO_COMUNICAZIONE_CONVALIDATA){
	        	  logger.debug("*** Si sta cambiando lo stato in 'CONVALIDATA', si deve abilitare l'utente alle sezioni selezionate in CAR_R_DOMANDA_TIPOLOGIA");
	        	  // Controllo le tipologie selezionate per la domanda
	        	  TipologiaDomandaDTO tipologieDom = getDettTipologiaDomanda(domanda.getIdDomanda());
	        	  if(tipologieDom!= null && tipologieDom.getTipologieDomandaList()!= null){	        		  	        		  
	        		  boolean sezioneImport = false;
	        		  boolean sezioneExport = false;
	        		  boolean sezioneVivai = false;
	        		  boolean sezioneControlli = false;
	        		  for (Iterator<CarRDomandaTipologia> iterator = tipologieDom.getTipologieDomandaList().iterator(); iterator.hasNext();) {
						CarRDomandaTipologia tipDom = (CarRDomandaTipologia) iterator.next();
						
						if(tipDom.getIdTipologia().longValue() == CaronteConstants.ID_TIPOLOGIA_DOMANDA_IMP){
						  sezioneImport = true;
						}
						else if(tipDom.getIdTipologia().longValue() == CaronteConstants.ID_TIPOLOGIA_DOMANDA_EXP){
						  sezioneExport = true;
						}
						else if(tipDom.getIdTipologia().longValue() == CaronteConstants.ID_TIPOLOGIA_DOMANDA_VIVAI){
						  sezioneVivai = true;	
						}						
					  }
	        		  logger.debug("-- sezioneImport ="+sezioneImport);
	        		  logger.debug("-- sezioneExport ="+sezioneExport);
	        		  logger.debug("-- sezioneVivai ="+sezioneVivai);
	        		  
	        		  /* -- Sezioni che si possono abilitare :
						 * 1 - Import
						 * 2 - Export
						 * 3 - Vivai								
						 * 
						 * ***  Operazioni da eseguire per abilitare l'utente e lo spedizioniere alle sezioni ***
						 * 
						 * -- SPEDIZIONIERE
						 * 1) Si cancella e ricrea l'associazione con le sezioni per lo spedizioniere in car_r_spediz_assoc_sezione
						 * 2) Si attiva lo spedizioniere : settare car_t_spedizioniere.attivo = true
						 * 	
						 * -- UTENTE
						 * 3) Aggiorno record in car_r_utente_gruppo per l'utente della domanda (rimuovo eventuali record precedenti e inserisco quelli nuovi)  
						 * 4) Settare car_t_utente.abilitato a true (così l'amministratore non deve più farlo dal modulo dell'amministrazione)
						 * 					
						 */
	        		  
	        		  
	        		  // Cerco l'id ruolo dell'utente della domanda
	        		  logger.debug("-- Cerco l'id ruolo dell'utente della domanda");
	        		  logger.debug("-- idUtenteDomanda ="+idUtenteDomanda);
	        		  Long idRuolo = getIdRuoloByIdUtente(idUtenteDomanda);
	        		  logger.debug("-- idRuolo ="+idRuolo);						        		  

	        		  // Se l'utente è Superuser o Amministratore, deve essere riabilitato a tutte le sezioni di Ersaf
	        		  if(idRuolo == CaronteConstants.ID_RUOLO_UTENTE_ADMIN || idRuolo == CaronteConstants.ID_RUOLO_UTENTE_SUPERUSER){
	        			  logger.debug("-- L'utente è Superuser o Amministratore, deve essere abilitato a tutte le sezioni");
	        			  sezioneImport = true;
	        			  sezioneExport = true;
	        			  sezioneVivai = true;
	        			  sezioneControlli = true;
	        		  }
						
	        		// *************** PARTE PER LO SPEDIZIONIERE **************** 	        		  		
					logger.debug("-- 1) Si cancella e ricrea l'associazione con le sezioni per lo spedizioniere in car_r_spediz_assoc_sezione, in base alle tipologie selezionate in domanda");
					CarRSpedizAssocSezioneExample filtri = new CarRSpedizAssocSezioneExample();
					filtri.createCriteria().andIdSpedizioniereEqualTo(idSpedizioniereDomanda);

					spedizioniereSezioneMapper.deleteByExample(filtri);
					
					CarRSpedizAssocSezione sezioneSpedizioniere = new CarRSpedizAssocSezione();
					sezioneSpedizioniere.setIdSpedizioniere(idSpedizioniereDomanda);
					if (sezioneImport) {
						sezioneSpedizioniere.setIdAssociazioneSezione(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT);
						logger.debug("-- INSERT SPEDIZIONIERE ABILITATO AD IMPORT");
						spedizioniereSezioneMapper.insertSelective(sezioneSpedizioniere);
					}

					if (sezioneExport) {
						sezioneSpedizioniere.setIdAssociazioneSezione(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT);
						logger.debug("-- INSERT SPEDIZIONIERE ABILITATO AD EXPORT");
						spedizioniereSezioneMapper.insertSelective(sezioneSpedizioniere);
					}
					
					if (sezioneVivai) {
						sezioneSpedizioniere.setIdAssociazioneSezione(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI);
						logger.debug("-- INSERT SPEDIZIONIERE ABILITATO A VIVAI");
						spedizioniereSezioneMapper.insertSelective(sezioneSpedizioniere);
					}
					
					if (sezioneControlli) {
						sezioneSpedizioniere.setIdAssociazioneSezione(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI);
						logger.debug("-- INSERT SPEDIZIONIERE ABILITATO A CONTROLLI");
						spedizioniereSezioneMapper.insertSelective(sezioneSpedizioniere);
					}
					
					// Abilitato sempre ad Autorizzazioni
					sezioneSpedizioniere.setIdAssociazioneSezione(CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI);
					logger.debug("-- INSERT SPEDIZIONIERE ABILITATO AD AUTORIZZAZIONI");
					spedizioniereSezioneMapper.insertSelective(sezioneSpedizioniere);
					
					
					logger.debug("-- 2) Si attiva lo spedizioniere : settare car_t_spedizioniere.attivo = true");
					// Setto car_t_spedizioniere.attivo = true
					CarTSpedizioniere sped = new CarTSpedizioniere();
					sped.setIdSpedizioniere(idSpedizioniereDomanda);
					sped.setAttivo(true);
					sped.setDataUpdate(new Date());
					sped.setIdUtenteUpdate(idUtente);
					spedizioniereMapper.updateByPrimaryKeySelective(sped);
						
						
					
	        		// *************** PARTE PER L'UTENTE **************** 	
					logger.debug("-- 3) Aggiorno record in car_r_utente_gruppo per l'utente della domanda"); 
					
					// Elimino tutti gli eventuali record presenti su car_r_utente_gruppo, per quell'id_utente
					logger.debug("-- Elimino tutti gli eventuali record presenti su car_r_utente_gruppo, per quell'id_utente");
					UtenteForm utenteForm = new UtenteForm();
					utenteForm.setIdUtente(idUtenteDomanda);
					utenteCaronteMapper.deleteCarRUtenteGruppo(utenteForm);
					
					// Inserisco le abilitazioni per l'utente
					logger.debug("-- Inserisco record su car_r_utente_gruppo, per quell'id_utente, in base alle tipologie selezionate in domanda");
					// Setto i dati necessari per la query di abilitazione alle sezioni di Caronte
					logger.debug("-- Setto i dati necessari per la query di abilitazione alle sezioni di Caronte");					
					utenteForm.setIdRuolo(idRuolo);
					
					
					if(sezioneImport){											
				      // Abilitare l'utente alla sezione IMPORT
					  logger.debug(" ---- *** Abilitare l'utente alla sezione IMPORT");						  
					  utenteCaronteMapper.insertRUtenteGruppo(utenteForm, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT);
					}
					if(sezioneExport){						
					  // Abilitare l'utente alla sezione EXPORT						  
					  logger.debug(" ---- *** Abilitare l'utente alla sezione EXPORT");
					  utenteCaronteMapper.insertRUtenteGruppo(utenteForm, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT);
					}
					if(sezioneVivai){					 
					  // Abilitare l'utente alla sezione VIVAI							  
					  logger.debug(" ---- *** Abilitare l'utente alla sezione VIVAI");
					  utenteCaronteMapper.insertRUtenteGruppo(utenteForm, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI);
					}
					if(sezioneControlli){					 
					  // Abilitare l'utente alla sezione CONTROLLI							  
					  logger.debug(" ---- *** Abilitare l'utente alla sezione CONTROLLI");
					  utenteCaronteMapper.insertRUtenteGruppo(utenteForm, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI);
					}
					// Abilitare sempre l'utente alla sezione AUTORIZZAZIONI
					logger.debug(" ---- *** Abilitare l'utente alla sezione AUTORIZZAZIONI");
					utenteCaronteMapper.insertRUtenteGruppo(utenteForm, CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI);
					
					
					logger.debug("-- 4) Settare car_t_utente.abilitato a true");
					// Setto a true il campo car_t_utente.abilitato													
					CarTUtenteExample ex = new CarTUtenteExample();
					ex.createCriteria().andIdUtenteEqualTo(idUtenteDomanda);
					CarTUtente u = new CarTUtente();
					u.setAbilitato(true);
					u.setDataUpdate(new Date());
					carTUtenteMapper.updateByExampleSelective(u, ex);
	        	  }
	          }// fine cambio stato CONVALIDATA
	          
	          
	          /**
	           *  Gestione invio mail : 
	           *  nel caso di cambio stato in 'RESPINTA', 'CONVALIDATA' o 'ANNULLATA', controllare se su CAR_D_COSTANTE c'è il COD_COSTANTE = 'INVIO_MAIL_CAMBIOSTATO_DOM_RUOP'
	           *  Se c'è ed è valorizzata ad 'S' viene inviata la mail
	           */
	          
	          
	          String descStatoDomanda = null;
	          if (CaronteConstants.STATO_COMUNICAZIONE_RESPINTA.equals(idNuovoStato)) {
	            descStatoDomanda = CaronteConstants.DESC_STATO_COMUNICAZIONE_RESPINTA.toLowerCase();
	          } 
	          else if (CaronteConstants.STATO_COMUNICAZIONE_ANNULLATA.equals(idNuovoStato)) {
	            descStatoDomanda = CaronteConstants.DESC_STATO_COMUNICAZIONE_ANNULLATA.toLowerCase();
	          }
	          else if (CaronteConstants.STATO_COMUNICAZIONE_CONVALIDATA.equals(idNuovoStato)) {
	            descStatoDomanda = CaronteConstants.DESC_STATO_COMUNICAZIONE_CONVALIDATA.toLowerCase();
		      }

	          if (!StringUtils.isEmpty(descStatoDomanda)) {
	        	  
	        	  logger.debug("** Cerco su CAR_D_COSTANTE se devo anche inviare la mail");
		          CarDCostanteExample costEx = new CarDCostanteExample();
		          costEx.createCriteria().andCodCostanteEqualTo(CaronteConstants.CODICE_COSTANTE_INVIO_MAIL_CAMBIO_STATO_DOM_RUOP);
		          String valoreCostante = null;
		          List<CarDCostante> costantiList = costanteMapper.selectByExample(costEx);
		          if(costantiList != null && costantiList.size()>0){
		        	  valoreCostante = costantiList.get(0).getValoreCostante();
		          }
		          logger.debug("-- valore costante ="+valoreCostante);
		          if(valoreCostante != null && valoreCostante.equalsIgnoreCase("S")){
	        	  
		        	  logger.debug("-- DEVO INVIARE LA MAIL ALL'O.P.");
		              
		              logger.debug(" -- descStatoDomanda ="+descStatoDomanda);
		              if (descStatoDomanda != null) {              		               
		                dettaglioDomanda.setCentriAziendaliList(getCentriAziendaliByIdDomanda(idDomanda));
		                
		                // --- *** OGGETTO della mail
		                logger.debug("-- Compongo l'oggetto della mail");
		                String subject = dettaglioDomanda.getDescTipoDomanda() +" - Codice Domanda : "+dettaglioDomanda.getCodDomanda();
		                if(dettaglioDomanda.getCentriAziendaliList() != null && dettaglioDomanda.getCentriAziendaliList().size()>0){
		                	logger.debug("-- Sono stati indicati i centri aziendali");
		                	if(dettaglioDomanda.getCentriAziendaliList().size() == 1){
		                	  CentroAziendaleDomandaDTO centroAz = dettaglioDomanda.getCentriAziendaliList().get(0);	
		                	  subject+=", per centro aziendale : "+centroAz.getCodCentroAziendale();
		                	  if(centroAz.getDenominazione() != null && !centroAz.getDenominazione().equals("")){
		                		  subject+=" - "+centroAz.getDenominazione().toUpperCase();
		                	  }
		                	}
		                	else{
		                		subject+=", per centri aziendali : ";
		                		for (Iterator<CentroAziendaleDomandaDTO> iterator = dettaglioDomanda.getCentriAziendaliList().iterator(); iterator.hasNext();) {
									CentroAziendaleDomandaDTO centroAz = (CentroAziendaleDomandaDTO) iterator.next();
									subject+=", "+centroAz.getCodCentroAziendale();
				                	if(centroAz.getDenominazione() != null && !centroAz.getDenominazione().equals("")){
				                		  subject+=" - "+centroAz.getDenominazione().toUpperCase();
				                	}
								}
		                	}
		                }
		                subject+=" - " + descStatoDomanda.toUpperCase();
		                logger.debug("-- OGGETTO DELLA MAIL : "+subject);		
		                
		                /* --- *** MESSAGGIO della mail
		                 * -> il messaggio è diverso nel caso in cui venga cambiato lo stato a CONVALIDATA 
		                 * (in questo caso indicare anche il codice RUOP assegnato e che riceverà comunicazione ufficale a mezzo PEC)
		                 */
		                
		                
		                String message = "<html><body><p>Gentile "
		                    + StringUtils.capitalize(dettaglioDomanda.getCognome() + " "+StringUtils.capitalize(dettaglioDomanda.getNome()))
		                    + ", " + "<br/>ti avvisiamo che la " + dettaglioDomanda.getDescTipoDomanda()+", con Codice Domanda : <b>"+dettaglioDomanda.getCodDomanda()+"</b>";
		                    
		                    if(dettaglioDomanda.getCentriAziendaliList() != null && dettaglioDomanda.getCentriAziendaliList().size()>0){
			                	logger.debug("-- Sono stati indicati i centri aziendali");
			                	if(dettaglioDomanda.getCentriAziendaliList().size() == 1){
			                	  CentroAziendaleDomandaDTO centroAz = dettaglioDomanda.getCentriAziendaliList().get(0);	
			                	  message+= ", con Centro Aziendale :<br/> <b>"+centroAz.getCodCentroAziendale();
			                	  if(centroAz.getDenominazione() != null && !centroAz.getDenominazione().equals("")){
			                		  message+=" - "+StringUtils.capitalize(centroAz.getDenominazione())+ "</b>";
			                	  }
			                	}
			                	else{
			                		message+=", con i Centri Aziendali :<b>";
			                		for (int i=0;i<dettaglioDomanda.getCentriAziendaliList().size();i++) {
										CentroAziendaleDomandaDTO centroAz = dettaglioDomanda.getCentriAziendaliList().get(i);
										message+="<br/> "+centroAz.getCodCentroAziendale();										
					                	if(centroAz.getDenominazione() != null && !centroAz.getDenominazione().equals("")){
					                	  message+=" - "+StringUtils.capitalize(centroAz.getDenominazione());
					                	}	
					                	if(i < dettaglioDomanda.getCentriAziendaliList().size()){
					                	  message+="<br/>";
					                	}  
					                	
									}
			                		message+= "</b>";
			                	}
			                }
		                    message+=" <br/> del "+ new DateFormatter().print(dettaglioDomanda.getDataInserimento(), null) + " è stata <b>"+ descStatoDomanda.toUpperCase()+".</b>";
		                    
		                    if(!StringUtils.isEmpty(motivazione)){
		                    	message+=", con la seguente motivazione: " + "<br />"+ motivazione.toUpperCase();
		                    }
		                    
		                    if (CaronteConstants.STATO_COMUNICAZIONE_CONVALIDATA.equals(idNuovoStato)) {
		                    	message+=" Il codice RUOP assegnato alla domanda è : <b>"+codiceRuop+"</b> </br>";
		                    	message+="Prossimamente verrà inviata la Comunicazione ufficale con una mail PEC.";
		                    	
		                    }
		                    
		                    message+= "<br /><br />Questa mail è stata generata in automatico, si prega di non rispondere a questo indirizzo, grazie."
		                             +"<br/></p></body></html>";
	
		                try {
		              	  logger.debug("--- *** Invio MAIL a :"+dettaglioDomanda.getEmail());  
		                  CaronteUtils.postMailWithoutAttachments(CaronteConstants.INDIRIZZO_MITTENTE_REGISTRAZIONE_UTENTE, new String[] { dettaglioDomanda.getEmail() }, null, subject, message);
		                } 
		                catch (Exception ex) {
		                  logger.error("-- Exception in fase di invio mail ="+ex.getMessage());	
		                  throw new BusinessException(ex.getMessage());
		                }
		              }
		          } // fine costante su db presente e valorizzata ad 'S'  
	            } // fine caso stato che prevede l'invio della mail
	          return true;
	        }
	      }
	    }
	    return false;
  }
  
  @Override
  public Boolean checkAllegatiByIdDomanda(Long idDomanda) throws BusinessException{
	  logger.debug("BEGIN checkAllegatiByIdDomanda");
	  
	  Boolean checkAllegatiB = false;
	  String checkAllegati = domandaMapper.checkAllegatiByIdDomanda(idDomanda);
	  logger.debug("-- checkAllegati ="+checkAllegati);
	  if(null != checkAllegati && checkAllegati.equalsIgnoreCase("S"))
		  checkAllegatiB = true;
	  
	  return checkAllegatiB;
	
  }
  
  @Override
  public DomandaDto getDettaglioAziendaByIdDomanda(Long idDomanda) throws BusinessException{
	  return domandaMapper.getDettaglioAziendaByIdDomanda(idDomanda);
  }

  @Override
  public Long[] getVociUtenteByIdTipoModelloGruppo(Long idTipoModello, Long idGruppo, Long idDomanda) throws BusinessException{
	  return domandaMapper.getVociUtenteByIdTipoModelloGruppo(idTipoModello, idGruppo, idDomanda);
  }
  
  @Override
  public CarTResponsabilePassaporto getRespPassaportoByIdDomanda(Long idDomanda) throws BusinessException {
	  CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(idDomanda);
	  
	  if (domanda != null) {
		  if (domanda.getIdResponsabilePassaporto() != null) {
			  CarTResponsabilePassaportoExample respExample = new CarTResponsabilePassaportoExample();
			  respExample.createCriteria().andIdResponsabilePassaportoEqualTo(domanda.getIdResponsabilePassaporto());
			  CarTResponsabilePassaporto resp = responsabilePassaportoMapper.selectByPrimaryKey(domanda.getIdResponsabilePassaporto());		
			  return resp;
		  }
		  
	  }
	  return null;
  }
  
  @Override
  public CarTDatiDomanda getDatiAggiuntiviByIdDomanda(Long idDomanda) throws BusinessException{
	  logger.debug("BEGIN getDatiAggiuntiviByIdDomanda");
	  CarTDatiDomanda datiDomanda = null;
	  CarTDatiDomandaExample datiDomandaEx = new CarTDatiDomandaExample();
	  datiDomandaEx.createCriteria().andIdDomandaEqualTo(idDomanda);
	  List<CarTDatiDomanda> datiDomList = datiDomandaMapper.selectByExample(datiDomandaEx);	  
	  if(null != datiDomList && datiDomList.size()>0){
		  datiDomanda = datiDomList.get(0);
	  }
	  return datiDomanda;
  }
  
  @Override
  public String getNoteVoceUtenteByIdTipoModelloGruppo(Long idTipoModello, Long idGruppo, Long idDomanda) throws BusinessException{
	  logger.debug("BEGIN getNoteVoceUtenteByIdTipoModelloGruppo");
	  String note = null;
	  CarTVoceUtente voceUt =  domandaMapper.getNoteVoceUtenteByIdTipoModelloGruppo(idTipoModello, idGruppo, idDomanda);
	  if(null != voceUt){
		  note = voceUt.getNote();
	  }
	  return note;
  }
  
  @Override
  public DomandaDto getDettaglioAnagraficaAziendaByIdDomanda(Long idDomanda) throws BusinessException{
	  return domandaMapper.getDettaglioAnagraficaAziendaByIdDomanda(idDomanda);
  }
  
  @Override
  public CentroAziendaleDomandaDTO getDettaglioCentroAziendale(Long idCentroAziendale, Long idDomanda) throws BusinessException{
	  logger.debug("BEGIN getDettaglioCentroAziendale");
	  CentroAziendaleDomandaDTO centroAzDomandaDto = new CentroAziendaleDomandaDTO();
	  
	  logger.debug("-- Cerco le tipologia produttive del centro aziendale con idCentroAziendale ="+idCentroAziendale);
	  List<TipologiaProdSpecieDTO> tipologieProdDb = getTipologieProdCentroAz(idCentroAziendale, idDomanda);
	  centroAzDomandaDto.setTipologieList(tipologieProdDb);
	  logger.debug("-- Cerco i siti produzione del centro aziendale con idCentroAziendale ="+idCentroAziendale);
	  List<SitoProduzioneDTO> sitiProduzioneDb = getSitiProduzioneCentroAz(idCentroAziendale);
	  centroAzDomandaDto.setSitiProduzioneList(sitiProduzioneDb);
	  
	  CarTCentroAziendale centroAz = centroAzMapper.selectByPrimaryKey(idCentroAziendale);
	  if(null != centroAz)
		  centroAzDomandaDto.setDenominazione(centroAz.getDenominazione());
	  
	  centroAzDomandaDto.setIdCentroAziendale(idCentroAziendale);
	  
	  logger.debug("END getDettaglioCentroAziendale");
	  return centroAzDomandaDto;
	  
  }
  
  @Override
  public String[] getDescrVociUtenteByIdTipoModelloGruppo(Long idTipoModello, Long idGruppo, Long idDomanda) throws BusinessException{
	  return domandaMapper.getDescrVociUtenteByIdTipoModelloGruppo(idTipoModello, idGruppo, idDomanda);
  }
  
  @Override
  public String[] getDescrTipologieDomandaByIdDomanda(Long idDomanda) throws BusinessException{
	  return domandaMapper.getDescrTipologieDomandaByIdDomanda(idDomanda);
  }
  
  @Override
  public void salvaStampaDomandaFirmata(Long idDomanda, UtenteDTO utente, byte [] pdfStampaDomanda, String nomeFileStampa) throws BusinessException {
	  logger.debug("BEGIN salvaStampaDomandaFirmata");
	  
	  // Cerco se c'è già un record su CAR_T_DATI_DOMANDA PER LA DOMANDA IN OGGETTO
	  CarTDatiDomanda datiDomanda = new CarTDatiDomanda();
	  CarTDatiDomandaExample datiDomandaEx = new CarTDatiDomandaExample();
	  datiDomandaEx.createCriteria().andIdDomandaEqualTo(idDomanda);
	  List<CarTDatiDomanda> datiDomandaList = datiDomandaMapper.selectByExample(datiDomandaEx);
	  if(null != datiDomandaList && datiDomandaList.size()>0) {
		  logger.debug("-- Esiste già un record in CAR_T_DATI_DOMANDA per idDomanda ="+idDomanda);
		  datiDomanda = datiDomandaList.get(0);
		  datiDomanda.setStampa(pdfStampaDomanda);
		  datiDomanda.setNomeFile(nomeFileStampa);		  
		  datiDomanda.setIdUtenteAggiornamento(utente.getId());
		  datiDomanda.setDataAggiornamento(new Date());
		  logger.debug("-- UPDATE CAR_T_DATI_DOMANDA");
		  datiDomandaMapper.updateByPrimaryKeySelective(datiDomanda);
	  } else {
		  logger.debug("-- Non esiste ancora un record in CAR_T_DATI_DOMANDA per idDomanda ="+idDomanda);
		  datiDomanda.setIdDomanda(idDomanda);
		  datiDomanda.setStampa(pdfStampaDomanda);
		  datiDomanda.setNomeFile(nomeFileStampa);	
		  datiDomanda.setDataInserimento(new Date());
		  datiDomanda.setIdUtenteInserimento(utente.getId());	  
		  
		  logger.debug("-- INSERT CAR_T_DATI_DOMANDA");
		  datiDomandaMapper.insertSelective(datiDomanda);
	  }
  }
  
  @Override
  public CarTDatiDomanda getStampaFirmata(Long idDomanda) throws BusinessException {
	  CarTDatiDomandaExample example = new CarTDatiDomandaExample();
	  example.createCriteria().andIdDomandaEqualTo(idDomanda);
	  

    List<CarTDatiDomanda> listaDatiDomanda= datiDomandaMapper.selectByExampleWithBLOBs(example);

    if (listaDatiDomanda.size() > 0) {    	
      // prendo l'ultimo della lista che dovrebbe contenere l'ultima stampa generata
      return listaDatiDomanda.get(0);
    }

    return null;
  }
  
  @Override
  public DomandaDto getDettaglioGestioneByIdDomanda(Long idDomanda) throws BusinessException{
	  DomandaDto domandaDto = null;
	  
	  domandaDto = domandaMapper.getDettaglioGestioneByIdDomanda(idDomanda);
	  // DA FARE : per ogni centro aziendale della domanda, ricerco i dati indicati nel Tab Gestione
	  
	  
	  
	  return domandaDto;
  }


@Override
public void aggiornaDatiGestione(NuovaDomandaForm form, Long idUtente) throws BusinessException {
//TODO da gestire i campi codice fitok e idispettore nella nuova maniera
	CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(form.getIdDomanda());
	logger.debug("------- idDomanda= " + form.getIdDomanda());
	logger.debug(" -- idTipoComunicazione ="+form.getIdTipoComunicazione());
	
	logger.debug(" --  form.getIdVersioneDomanda() ="+form.getIdVersioneDomanda());
	
	if(form.getIdTipoComunicazione() != CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO){
		domanda.setIdDomanda(form.getIdDomanda());	
		domanda.setNumeroProtocollo(form.getNumeroProtocollo());
		domanda.setDataProtocollo(form.getDataProtocollo());
		domanda.setCodiceFitok(form.getCodiceFitok());
		domanda.setTariffa(form.getTariffa());
		domanda.setNote(form.getNote());
		domanda.setIdUtenteAggiornamento(idUtente);
		domanda.setDataAggiornamento(new Date());
		domanda.setIdIspettore(form.getIdIspettore());
		domanda.setIdTipologiaPassaporto(form.getIdTipologiaPassaporto());
		
		CarTSpedizioniere spedizioniere= new CarTSpedizioniere();
		logger.debug("+++++idSpedizioniere= " + form.getIdSpedizioniere());
		spedizioniere.setIdSpedizioniere(form.getIdSpedizioniere());
		spedizioniere.setCodiceRuop(form.getCodiceRuop());
		spedizioniere.setDataRegistrazioneRuop(form.getDataRegistrazioneRuop());
		spedizioniere.setIdUtenteUpdate(idUtente);
		spedizioniere.setDataUpdate(new Date());
		spedizioniere.setCodiceFitok(form.getCodiceFitok());
		
		carTDomandaMapper.updateByPrimaryKey(domanda);
		spedizioniereMapper.updateByPrimaryKeySelective(spedizioniere);
		
		
		/* Aggiorno i centri aziendali inseriti nella domanda con il nuovo ispettore e tariffa
		 *  -- Se non è una Domanda di Passaporto : in questo caso i dati del centro aziendale sono stati salvati in : car_r_domanda_centro_az.id_domanda - id_centro_aziendale
		 *  -- Se è una Domanda di Passaporto : in questo caso i dati del centro aziendale sono stati salvati in : car_t_domanda.id_centro_aziendale_passaporto
		 */					   
		List<CentroAziendaleDomandaDTO>	listCentroAz = domandaMapper.getCentriAziendaliByIdDomanda(domanda.getIdDomanda());
	    for (Iterator<CentroAziendaleDomandaDTO> iterator = listCentroAz.iterator(); iterator.hasNext();) {
	    	CentroAziendaleDomandaDTO centroAzDTO = (CentroAziendaleDomandaDTO) iterator.next();	    	
	    	CarTCentroAziendale centroAz = centroAzMapper.selectByPrimaryKey(centroAzDTO.getIdCentroAziendale());
	    	logger.debug("-- Aggiornamento del centro aziendale con idCentroAz ="+centroAzDTO.getIdCentroAziendale()+" salvando idIspettore ="+domanda.getIdIspettore()+" e Tariffa ="+domanda.getTariffa());
	    	centroAz.setIdIspettore(domanda.getIdIspettore());
	    	centroAz.setDataUpdate(new Date());
	    	centroAz.setIdUtenteUpdate(idUtente);
	    	centroAz.setTariffa(domanda.getTariffa());
	    	centroAzMapper.updateByPrimaryKey(centroAz);
	    	logger.debug("-- Aggiornato il centroAziendale  id= "+centroAzDTO.getIdCentroAziendale());
	    }
	}
	// nel caso di Domanda Passaporto
	else{	
		logger.debug("-- ** Tab Gestione - Caso Domanda Passaporto");
		domanda.setIdDomanda(form.getIdDomanda());
		logger.debug("-- numero protocollo ="+form.getNumeroProtocollo());
		domanda.setNumeroProtocollo(form.getNumeroProtocollo());
		logger.debug("-- data protocollo ="+form.getDataProtocollo());
		domanda.setDataProtocollo(form.getDataProtocollo());
		domanda.setNote(form.getNote());
		domanda.setTariffa(form.getTariffa());
		domanda.setIdIspettore(form.getIdIspettore());
		
		domanda.setIdUtenteAggiornamento(idUtente);
		domanda.setDataAggiornamento(new Date());
		domanda.setDataAutorizzazionePassaporto(form.getDataAutorizzazionePassaporto());
		domanda.setIdTipologiaPassaporto(form.getIdTipologiaPassaporto());
		carTDomandaMapper.updateByPrimaryKey(domanda);
		
		// NUOVA Gestione campo Tipologia passaporto
		if(form.getIdTipologiaPassaporto() != null){
			logger.debug("-- idTipologiaPassaporto da salvare in car_t_iter_tipo_passaporto ="+form.getIdTipologiaPassaporto());
			salvaStoricoTiplogiaPassaporto(form);
		}
		
		
		
		/* Aggiorno i centri aziendali inseriti nella domanda con il nuovo ispettore e tariffa
		 *  -- Se non è una Domanda di Passaporto : in questo caso i dati del centro aziendale sono stati salvati in : car_r_domanda_centro_az.id_domanda - id_centro_aziendale
		 *  -- Se è una Domanda di Passaporto : in questo caso i dati del centro aziendale sono stati salvati in : car_t_domanda.id_centro_aziendale_passaporto
		 */	
		List<CentroAziendaleDomandaDTO> listCentroAz = domandaMapper.getCentroAziendalePassaportoByIdDomanda(domanda.getIdDomanda());
		for (Iterator<CentroAziendaleDomandaDTO> iterator = listCentroAz.iterator(); iterator.hasNext();) {
	    	CentroAziendaleDomandaDTO centroAzDTO = (CentroAziendaleDomandaDTO) iterator.next();	    	
	    	CarTCentroAziendale centroAz = centroAzMapper.selectByPrimaryKey(centroAzDTO.getIdCentroAziendale());
	    	logger.debug("-- Aggiornamento del centro aziendale con idCentroAz ="+centroAzDTO.getIdCentroAziendale()+" salvando idIspettore ="+domanda.getIdIspettore()+" e Tariffa ="+domanda.getTariffa());
	    	centroAz.setIdIspettore(domanda.getIdIspettore());
	    	centroAz.setDataUpdate(new Date());
	    	centroAz.setIdUtenteUpdate(idUtente);
	    	centroAz.setTariffa(domanda.getTariffa());
	    	centroAzMapper.updateByPrimaryKey(centroAz);
	    	logger.debug("-- Aggiornato il centroAziendale  id= "+centroAzDTO.getIdCentroAziendale());
	    }
	}
	
	
}

/*
 * Controllo se c'è già un record su car_t_iter_tipo_passaporto con id_centro_aziendale = a quello per il quale si sta facendo la domanda di passaporto :
	- se c'è : 
	    - setto DATA_FINE al record
	    - inserisco nuovo record con DATA_FINE  a null
	- se non c'è:
	    - inserisco nuovo record con DATA_FINE  a null
    
    Note : se sul form in input è valorizzato anche il campo idDomanda : siamo nel salva del Tab Gestione della Domanda passaporto, se non lo, è siamo sul salva del Tab Centro aziendale dei Controlli
 */
private void salvaStoricoTiplogiaPassaporto(NuovaDomandaForm form) throws BusinessException{
	logger.debug("BEGIN salvaStoricoTiplogiaPassaporto");
	try{
		CarTIterTipoPassaportoExample exsample = new CarTIterTipoPassaportoExample();
		
		logger.debug("-- IdCentroAziendalePassaporto ="+form.getIdCentroAziendalePassaporto());
		// Caso salva Domanda Passaporto 
		if(form.getIdDomanda() != null){
			logger.debug("-- CASO filtro su idDomanda ="+form.getIdDomanda());			
			exsample.createCriteria().andIdDomandaEqualTo(form.getIdDomanda()).andIdCentroAziendaleEqualTo(form.getIdCentroAziendalePassaporto()).andDataFineIsNull();
		}
		// Caso salva Controlli
		else{
			exsample.createCriteria().andIdCentroAziendaleEqualTo(form.getIdCentroAziendalePassaporto()).andDataFineIsNull();
		}	
		// Controllo se c'è già un record su car_t_iter_tipo_passaporto con data_fine a null
		logger.debug("-- Controllo se c'è già un record su car_t_iter_tipo_passaporto con data_fine a null");
		List<CarTIterTipoPassaporto> iterPassaportoList = iterTipoPassaportoMapper.selectByExample(exsample);
		// se c'è : setto DATA_FINE al record
		if(iterPassaportoList != null && iterPassaportoList.get(0) != null){
			logger.debug("-- Setto DATA_FINE al record su car_t_iter_tipo_passaporto");
			iterPassaportoList.get(0).setDataFine(new Date());
			iterTipoPassaportoMapper.updateByPrimaryKey(iterPassaportoList.get(0));
		}
		// Inserisco nuovo record con DATA_FINE  a null
		logger.debug("-- Inserisco nuovo record su car_t_iter_tipo_passaporto con data_fine a null");
		CarTIterTipoPassaporto iterPass = new CarTIterTipoPassaporto();
		// questo campo sarà valorizzato se si sta facendo salva dalla Domanda Passaporto, mentre non lo sarà se si sta facendo salva dalla sezione Controlli
		iterPass.setIdDomanda(form.getIdDomanda());
		iterPass.setDataInizio(new Date());
		iterPass.setIdCentroAziendale(form.getIdCentroAziendalePassaporto());
		iterPass.setIdTipologiaPassaporto(form.getIdTipologiaPassaporto());
		iterTipoPassaportoMapper.insert(iterPass);
	}
	catch (Exception e){
	  logger.error("-- Exception in salvaStoricoTiplogiaPassaporto =" + e.getMessage());	
      throw new BusinessException(e.getMessage());
	}	
	logger.debug("END salvaStoricoTiplogiaPassaporto");
}

@Override
public Long countAllegatiByIdDomandaIdTipoAllegato(Long idDomanda, Long idTipoAllegato) throws BusinessException{
  return domandaMapper.countAllegatiByIdDomandaIdTipoAllegato(idDomanda, idTipoAllegato);
}

@Override
public Long getIdRuoloByIdUtente(Long idUtente) throws BusinessException{
  return utenteCaronteMapper.getIdRuoloByIdUtente(idUtente);
}

@Override
public TipologiaAttMaterialeDTO getAttivitaMaterialeById(Long idAttivitaMaterialeUtente) throws BusinessException {
  return domandaMapper.getAttivitaMaterialeById(idAttivitaMaterialeUtente);
}

@Override
public void inviaMailGestione(NuovaDomandaForm form) throws BusinessException {

	logger.debug("** Cerco su CAR_D_COSTANTE se devo anche inviare la mail");
    CarDCostanteExample costEx = new CarDCostanteExample();
    costEx.createCriteria().andCodCostanteEqualTo(CaronteConstants.CODICE_COSTANTE_INVIO_MAIL_ISPETTORE_DOM_RUOP);
    String valoreCostante = null;
    List<CarDCostante> costantiList = costanteMapper.selectByExample(costEx);
    if(costantiList != null && costantiList.size()>0){
  	  valoreCostante = costantiList.get(0).getValoreCostante();
    }
    logger.debug("-- valore costante ="+valoreCostante);
    if(valoreCostante != null && valoreCostante.equalsIgnoreCase("S")){
	  
  	  logger.debug("-- DEVO INVIARE LA MAIL ALL'ISPETTORE");
  	  
  	  //Ottengo la mail dell'ispettore
  	  CarTIspettoreExample ispettoreExample = new CarTIspettoreExample();
  	  ispettoreExample.createCriteria().andIdIspettoreEqualTo(form.getIdIspettore());	
  	  CarTIspettore ispettore = ispettoreMapper.selectByPrimaryKey(form.getIdIspettore());
  	
  	  CarTUtenteExample utenteExample = new CarTUtenteExample();
  	  utenteExample.createCriteria().andIdUtenteEqualTo(ispettore.getIdUtente());
	  CarTUtente utenteIspettore = utenteMapper.selectByPrimaryKey(ispettore.getIdUtente());
  	  
	  //Oggetto della mail
	  String oggetto = "Notifica di assegnazione alla domanda Ruop ";
	  oggetto += form.getIdDomanda();
	  
	  //Corpo della mail
	  String messaggio = "Gentile " + utenteIspettore.getCognome() + " " + utenteIspettore.getNome() + ",\n";
	  messaggio += "con la presente si notifica la sua assegnazione come ";
	  if (ispettore.getTipoIspettore().equalsIgnoreCase("I")){
		  messaggio += "ispettore ";
	  } else {
		  messaggio += "agente ";
	  }
	  messaggio += "alla Domanda di Registrazione al Registro Ufficiale degli Operatori Professionali(RUOP) - Codice Domanda: ";
	  messaggio +=  form.getIdDomanda() + " - relativa all' azienda " + form.getDenomAzienda() + "\n";
	  messaggio += "Questa mail è stata generata in automatico, si prega di non rispondere a questo indirizzo, grazie.";
  		
	  try{
		  CaronteUtils.postMailWithoutAttachments(CaronteConstants.INDIRIZZO_MITTENTE_REGISTRAZIONE_UTENTE, new String[] { utenteIspettore.getEmail() }, null, oggetto, messaggio);
	  } catch (Exception e){
		 logger.error("-- Exception in fase di invio mail =" + e.getMessage());	
         throw new BusinessException(e.getMessage());
	    }
    }
}

	@Override
	public List<CarDTipoComunicazione> getTipiDomandaFlusso(Long idUtente, Long idAssociazioneSezione) throws BusinessException{
		return domandaMapper.getTipiDomandaFlusso(idUtente, idAssociazioneSezione);
	}

	@Override
	public CarRTipoFlussoCom getTipoDomandaSuccessiva(Long idDomanda) throws BusinessException{
		return domandaMapper.getTipoDomandaSuccessiva(idDomanda);
	}

@Override
public List<CarRAttMaterialeUtente> getAttivitaMaterialiByIdDomanda(Long idDomanda) throws BusinessException {
  CarRAttMaterialeUtenteExample example = new CarRAttMaterialeUtenteExample();
  example.createCriteria().andIdDomandaEqualTo(idDomanda);  

  List<CarRAttMaterialeUtente> listaAttivitaMateriali = attMaterialeUtenteMapper.selectByExample(example);
	
  return listaAttivitaMateriali;
}

public String[] getTipologieByIdDomanda(Long idDomanda) throws BusinessException {
  return domandaMapper.getTipologieByIdDomanda(idDomanda);
}

@Override
public void aggiornaRichiestaPassaporto(NuovaDomandaForm form, Long idUtente) throws BusinessException {
  logger.debug("BEGIN aggiornaRichiesta");
  logger.debug("-- idDomanda ="+form.getIdDomanda());
  logger.debug("-- form.isRichiestaPassaporto() da salvare: "+form.isRichiestaPassaporto());
  
  domandaMapper.lockDomanda(form.getIdDomanda());
  
  CarRAttMaterialeUtenteExample attMatEx = new CarRAttMaterialeUtenteExample();
  attMatEx.createCriteria().andIdDomandaEqualTo(form.getIdDomanda());
  
  List<CarRAttMaterialeUtente> attMatList = attMaterialeUtenteMapper.selectByExample(attMatEx);
  //aggiorno ogni record della tabella
  for(CarRAttMaterialeUtente attMatUt : attMatList) {
	  attMatUt.setIdUtenteAggiornamento(idUtente);
	  attMatUt.setDataAggiornamento(new Date());
	  attMatUt.setRichiestaPassaporto(form.isRichiestaPassaporto() ? "S" : "N");
	  attMaterialeUtenteMapper.updateByPrimaryKeySelective(attMatUt);
  }
  
}



@Override
public ModuloDTO getModuloById(Long idModulo) throws BusinessException {
	return domandaMapper.getModuloById(idModulo);
}


@Override
public void aggiornaDatModuli(Long idDomanda, List<ModuloDTO> listaModuli, Long idUtente) throws BusinessException {
	CarTModuloExample exampleModulo = new CarTModuloExample();
	exampleModulo.createCriteria().andIdDomandaEqualTo(idDomanda);

	List<CarTModulo> listaModuliDB = moduloMapper.selectByExample(exampleModulo);

	if (listaModuliDB != null) {
		logger.debug("prendo la lista dei moduli sul db");
		for (CarTModulo moduloDB : listaModuliDB) {
			Iterator<ModuloDTO> iterModuli = listaModuli.iterator();


			while (iterModuli.hasNext()) {
				ModuloDTO modulo = iterModuli.next();
				logger.debug("modulo.getmodulo corrente= " + modulo.getModulo());

				if (modulo.getIdTipoModulo().equals(moduloDB.getIdTipoModulo())) {
					logger.debug("aggiorno il modulo su db con id= " + modulo.getIdTipoModulo());
					/*
					 * UPDATE
					 */
					moduloDB.setNomeFile(modulo.getNomeFile());
					moduloDB.setModulo(modulo.getModulo());
					moduloDB.setDataAggiornamento(new Date());
					moduloDB.setIdUtenteAggiornamento(idUtente);

					moduloMapper.updateByPrimaryKeyWithBLOBs(moduloDB);

					iterModuli.remove();
					break;
				}
			}
		}
	}

	/*
	 * I record rimasti in elenco non hanno avuto riscontro su DB, quindi vanno
	 * inseriti
	 */
	for (ModuloDTO modulo : listaModuli) {
		logger.debug("inserimento moduli nuovi su db");
		/*
		 * INSERT
		 */
		CarTModulo moduloInserimento = new CarTModulo();
		moduloInserimento.setIdDomanda(idDomanda);
		logger.debug("moduloInserimento.getIdDomanda()= " + moduloInserimento.getIdDomanda());
		moduloInserimento.setIdTipoModulo(modulo.getIdTipoModulo());
		logger.debug("modulo.getIdTipoModulo()= " + modulo.getIdTipoModulo());
		moduloInserimento.setNomeFile(modulo.getNomeFile());
		logger.debug("modulo.getNomeFile()= " + modulo.getNomeFile());
		moduloInserimento.setModulo(modulo.getModulo());
		logger.debug("modulo.getModulo()= " + modulo.getModulo());
		moduloInserimento.setIdUtenteInserimento(idUtente);
		logger.debug("idUtente= " + idUtente);
		moduloInserimento.setDataInserimento(new Date());
		logger.debug("new Date()= " + new Date());

		logger.debug("id tipo modulo inserito= " + modulo.getIdTipoModulo());
		moduloMapper.insert(moduloInserimento);
	}
	
	// Aggiorno i dati della domanda
    CarTDomanda domanda = new CarTDomanda();
	logger.debug("-- idDomanda aggiornata = " + idDomanda);
	domanda.setIdDomanda(idDomanda);	
	domanda.setIdUtenteAggiornamento(idUtente);
	domanda.setDataAggiornamento(new Date());
	
	carTDomandaMapper.updateByPrimaryKeySelective(domanda);
}

	@Override
	public void duplicaAllegati(Long idDomanda, Long idDomandaPrec, Long idUtente) throws BusinessException{
		logger.debug("BEGIN duplicaAllegati");

		/*
	     * Si effettua il lock sul record della domanda per evitare modifiche
	     * concorrenti sui file (scrupolo probabilmente eccessivo)
	     */
	    domandaMapper.lockDomanda(idDomanda);
	    
		logger.debug("-- Ricerca l'elenco degli allegati della domanda precedente");
		logger.debug("-- idDomandaPrec ="+idDomandaPrec);
		List<AllegatoDTO> listaAllegatiDB = getListaAllegatiDomanda(idDomandaPrec);
		
		for (Iterator<AllegatoDTO> iterator = listaAllegatiDB.iterator(); iterator.hasNext();) {
			AllegatoDTO allegatoDTO = (AllegatoDTO) iterator.next();
			// Controllo se ci sono degli allegati da inserire per idTipoAllegato
			if(allegatoDTO.getListaAllegati() != null && allegatoDTO.getListaAllegati().size()>0){
				logger.debug("-- Inserimento allegati per idTipoAllegato ="+allegatoDTO.getIdTipoAllegato());
				logger.debug("-- Numero di allegati da inserire ="+allegatoDTO.getListaAllegati().size());
				for (Iterator<CarTAllegatoDomanda> iterator2 = allegatoDTO.getListaAllegati().iterator(); iterator2.hasNext();) {
					CarTAllegatoDomanda allegatoDaInserire = (CarTAllegatoDomanda) iterator2.next();
					Long idAllegatoDomanda = allegatoDaInserire.getIdAllegatoDomanda();
					CarTAllegatoDomanda allegato = allegatoDomandaMapper.selectByPrimaryKey(idAllegatoDomanda);
					// setto l'idDomanda della Domanda che si sta inserendo					
					allegato.setIdDomanda(idDomanda);
					
					// rimuovo alcuni dati della domanda precedente
					allegato.setIdAllegatoDomanda(null);
					allegato.setDataAggiornamento(null);
					allegato.setIdUtenteAggiornamento(null);
					
					allegato.setDataInserimento(new Date());
					allegato.setIdUtenteInserimento(idUtente);
					allegatoDomandaMapper.insert(allegato);					
				}
			}			
		}
		logger.debug("END duplicaAllegati");
	}
	
	@Override
	public String abilitaCreaNuovaDomandaFlusso(Long idUtente, Long idDomanda) throws BusinessException{
		logger.debug("BEGIN abilitaCreaNuovaDomandaFlusso");
		boolean isUtenteAbilitatoCreazTipoComunicaz = false;
		boolean abilitazioneCreaNuovaDomandaFlusso = false;	  	  
		/*
		 * Controllare che ci sia una domanda da poter crare nel flusso
		 */
		logger.debug("-- Controllare che ci sia una domanda da poter crare nel flusso");
		CarRTipoFlussoCom tipoFlussoCom = getTipoDomandaSuccessiva(idDomanda);

		if(tipoFlussoCom != null){
			abilitazioneCreaNuovaDomandaFlusso = true;
			Long idStatoComunicazSuccessiva = tipoFlussoCom.getIdStatoComunicazione();
			/*
			 *  Controllare che quel tipo comunicazione sia creabile dall'utente loggato
			 */
			logger.debug("-- Controllare che quel tipo comunicazione sia creabile dall'utente loggato");
			Long idStato= domandaMapper.getStatoAbilitatoByIdUtente(idUtente, idStatoComunicazSuccessiva);
			if(idStato != null){
				logger.debug("-- Trovato idStato per l'idUtente ="+idUtente);
				isUtenteAbilitatoCreazTipoComunicaz = true;
			}

		}	  
		logger.debug("-- abilitazioneCreaNuovaDomandaFlusso ="+abilitazioneCreaNuovaDomandaFlusso);
		logger.debug("-- isUtenteAbilitatoCreazTipoComunicaz ="+isUtenteAbilitatoCreazTipoComunicaz);

		/*
		 *  Cerco la descrizione breve del tipo Comunicazione successiva
		 */	  
		String descrBreveTipoComunicaz = null;

		if(abilitazioneCreaNuovaDomandaFlusso && isUtenteAbilitatoCreazTipoComunicaz){
			logger.debug("-- Cerco la descrizione breve del tipo Domanda successiva");
			CarDTipoComunicazioneExample example = new CarDTipoComunicazioneExample();
			example.createCriteria().andIdTipoComunicazioneEqualTo(tipoFlussoCom.getIdTipoComunicazione());
			List<CarDTipoComunicazione> tipoComunicazioneList = tipoComunicazioneMapper.selectByExample(example);
			if(tipoComunicazioneList != null && tipoComunicazioneList.size()>0){
				CarDTipoComunicazione tipoComunicaz = tipoComunicazioneList.get(0);
				if(tipoComunicaz != null){
					descrBreveTipoComunicaz = tipoComunicaz.getDescBreve();
					logger.debug("-- descrBreveTipoComunicaz ="+descrBreveTipoComunicaz);
				}
			}
		}
		logger.debug("END abilitaCreaNuovaDomandaFlusso");
		return descrBreveTipoComunicaz;
	}

	@Override
	public List<DomandaDto> getListaFlussoDomanda(Long idDomanda) throws BusinessException{
		logger.debug("BEGIN getListaFlussoDomanda");
		return domandaMapper.getListaFlussoDomanda(idDomanda);
	}
	
	@Override
	public List<DomandaDto> getDomandeInoltrate(Long idUtente) throws BusinessException{
		Long[] idStatiDomanda = { CaronteConstants.STATO_COMUNICAZIONE_INOLTRATA };
	    Date oneWeekAgo = new Date(System.currentTimeMillis() - (7 * CaronteConstants.DAY_IN_MS));
	    List<DomandaDto> listaDomande = domandaMapper.getElencoDomandeStato(idUtente, oneWeekAgo, Arrays.asList(idStatiDomanda));
	    return listaDomande;
	}


	@Override
	public DomandaDto getDomandaByIdCentroAziendale(Long idCentroAz) throws BusinessException {
		return domandaMapper.getDomandaByIdCentroAziendale(idCentroAz);
	}
	
	
	@Override
	public String getTipologieAttivitaByIdDomanda(Long idDomanda) throws BusinessException {
		return domandaMapper.getTipologieAttivitaByIdDomanda(idDomanda);
	}
	
	@Override
	public Long getIdCentroAziendalePassaportoByIdDomanda(Long idDomanda) throws BusinessException {		
		CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(idDomanda);		
		return domanda.getIdCentroAziendalePassaporto();		
	}


	@Override
	public boolean isRuopValido(String codiceRuop, Long idSpedizioniere) throws BusinessException {

		logger.debug("-- Controllo se il codice ruop è stato già inserito a sistema");
		CarTSpedizioniereExample example = new CarTSpedizioniereExample();
		example.createCriteria().andCodiceRuopEqualTo(codiceRuop).andIdSpedizioniereNotEqualTo(idSpedizioniere);
		return !spedizioniereMapper.selectByExample(example).isEmpty();
	}
	
	@Override
	public Long checkIfCentroAzExists(Long idComune, String indirizzo, String codiceFiscale) throws BusinessException {
		return domandaMapper.checkIfCentroAzExists(idComune, indirizzo, codiceFiscale);		
	}
	
	@Override
	public Long checkIfCentroAzExistsByIdDomanda(Long idDomanda, Long idComune, String indirizzo) throws BusinessException {
		return domandaMapper.checkIfCentroAzExistsByIdDomanda(idDomanda, idComune, indirizzo);		
	}
	
	@Override
	public Long preSalvataggioCentroAziendaleDomanda(Long idDomanda, CentroAziendaleDto centroAziendale,
			UtenteDTO utente) throws BusinessException {
		try {
			CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(idDomanda);
			if (null != domanda) {
				/*
				 * Si effettua il lock sul record della domanda per evitare
				 * modifiche concorrenti
				 */
				domandaMapper.lockDomanda(idDomanda);

				if (centroAziendale.getIdCentroAziendale() != null && centroAziendale.getIdCentroAziendale() > 0) {
					logger.debug("-- insert in car_r_domanda_centro_az");
					logger.debug("-- idCentroAziendale associato alla domanda =" + centroAziendale.getIdCentroAziendale());
					CarRDomandaCentroAz domandaCentroAz = new CarRDomandaCentroAz();
					domandaCentroAz.setDataInsert(new Date());
					domandaCentroAz.setIdCentroAziendale(centroAziendale.getIdCentroAziendale());
					domandaCentroAz.setIdDomanda(idDomanda);
					domandaCentroAz.setIdUtenteInsert(utente.getId());
					domandaCentroAzMapper.insert(domandaCentroAz);
				}

				logger.debug("-- *** Aggiornamento dati della domanda");
				domanda.setIdUtenteAggiornamento(utente.getId());
				domanda.setDataAggiornamento(new Date());
				carTDomandaMapper.updateByPrimaryKey(domanda);
			}
			
		} catch (Exception e) {
			logger.error("-- Exception in salvaDatiCentroAziendale =" + e.getMessage());
			throw new BusinessException(e.getMessage());
		}
		return centroAziendale.getIdCentroAziendale();
	}


	@Override
	public boolean hasTipologiaAttMaterialeById(Long idTipoAttivita, Long idDomanda) throws BusinessException {
		 return !domandaMapper.hasTipologiaAttMaterialeById(idTipoAttivita, idDomanda).isEmpty();
	}
	
	@Override
	public void salvaProtocolloDomanda(Long idDomanda, ProtocolloOutputDto protocolloDto, UtenteDTO utente) throws BusinessException {
		try {

			/* rimuovo la parte davanti(Documento protocollato), rimane solo
			 numero e data protocollo
			 */
			/*String numeroEDataProt = protocollo.substring(23);
			String[] protArray = numeroEDataProt.split(" ", 2);
			String numeroProtocollo = protArray[0];
			String dataProt = protArray[1];
			logger.debug("-- numero protocollo: " + protArray[0]);
			logger.debug("-- data+ora protocollo: " + protArray[1]);
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dataProtocollo = DATE_FORMAT.parse(dataProt);*/
			
			
			if(protocolloDto != null){
                String numeroProtocollo = protocolloDto.getProtocollo();                
                logger.debug("-- numeroProtocollo = "+numeroProtocollo);
                
                Date dataProtocollo = null;
                String stringDataProtocollo = protocolloDto.getStringDataProtocollo(); // arriva nel formato : yyyy-MM-dd
                logger.debug("-- stringDataProtocollo = "+stringDataProtocollo);
                if(stringDataProtocollo !=  null && !stringDataProtocollo.isEmpty()){
                	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
                	dataProtocollo = DATE_FORMAT.parse(stringDataProtocollo);
                }
                
				CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(idDomanda);
				if (null != domanda) {
					/*
					 * Si effettua il lock sul record della domanda per evitare
					 * modifiche concorrenti
					 */
					domandaMapper.lockDomanda(idDomanda);
					logger.debug("-- *** Aggiornamento dati della domanda");
					domanda.setNumeroProtocollo(numeroProtocollo);
					domanda.setDataProtocollo(dataProtocollo);
					domanda.setIdUtenteAggiornamento(utente.getId());
					domanda.setDataAggiornamento(new Date());
					carTDomandaMapper.updateByPrimaryKey(domanda);
				}
			}

		} catch (Exception e) {
			logger.error("-- Exception in salvaDatiCentroAziendale =" + e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<CarTModulo> getModuloByIdDomanda(Long idDomanda) throws BusinessException {
		CarTModuloExample exampleModulo = new CarTModuloExample();
		exampleModulo.createCriteria().andIdDomandaEqualTo(idDomanda);
		
		List<CarTModulo> listaModuliDB = moduloMapper.selectByExampleWithBLOBs(exampleModulo);
		return listaModuliDB;		
	}

	@Override
	public void inviaMailDomandaProtocollata(Long idDomanda, Long idUtente) throws BusinessException {
		try {
			CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(idDomanda);
			CarTUtente utente = carTUtenteMapper.selectByPrimaryKey(idUtente);
			if (null != domanda) {
				logger.debug("-- Verifico se è attivo l'invio mail su CAR_D_COSTANTE");
				CarDCostanteExample costEx = new CarDCostanteExample();
				costEx.createCriteria().andCodCostanteEqualTo(CaronteConstants.PROTOCOLLO_COSTANTE_INVIO_MAIL);
				String valoreCostante = null;
				List<CarDCostante> costantiList = costanteMapper.selectByExample(costEx);
				if (costantiList != null && costantiList.size() > 0) {
					valoreCostante = costantiList.get(0).getValoreCostante();
				}
				logger.debug("-- valore costante =" + valoreCostante);
				if (valoreCostante != null && valoreCostante.equalsIgnoreCase("S")) {
					String subject = null;
					if (domanda.getIdTipoComunicazione().equals(CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO)) {
						subject = "Domanda di rilascio Passaporto num. " + domanda.getCodDomanda();
					} else {
						subject = "Domanda di registrazione RUOP num. " + domanda.getCodDomanda();
					}					
					String message = "<html><body><p>Gentile "
							+ StringUtils.capitalize(utente.getCognome() + " " + StringUtils.capitalize(utente.getNome()))
							+ ", " + "<br/>l'avvisiamo che la Domanda <b>" + domanda.getCodDomanda() + "</b>"
							+ " è stata protocollata con il numero <b>" + domanda.getNumeroProtocollo() + "</b>"
							+ " in data <b>" + new DateFormatter().print(domanda.getDataProtocollo(), null) + "</b>"
							+ " ed è all'esame della competente struttura.";
					message += "<br /><br />Questa mail è stata generata in automatico, si prega di non rispondere a questo indirizzo, grazie."
							+ "<br/></p></body></html>";

					logger.debug("-- Invio MAIL a  :" + utente.getEmail());
					CaronteUtils.postMailWithoutAttachments(CaronteConstants.INDIRIZZO_MITTENTE_REGISTRAZIONE_UTENTE,
							new String[] { utente.getEmail() }, null, subject, message);
				}
			}				

		} catch (Exception e) {
			logger.error("-- Exception in inviaMailDomandaProtocollata =" + e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	public Long getIdDomandaRuopConvalidata(Long idSpedizioniere) throws BusinessException {			
		return domandaMapper.getIdDomandaRuopConvalidata(idSpedizioniere);			
	}

	@Override
	public void deleteDomandaPassaporto(Long idDomanda) throws BusinessException {	
		logger.info("BEGIN deleteDomandaPassaporto");
		try {
			logger.debug("-- Rimuovo dati in CAR_R_FLUSSO_DOMANDA");
			CarRFlussoDomandaExample ex = new CarRFlussoDomandaExample();
			ex.createCriteria().andIdDomandaEqualTo(idDomanda);		
			flussoDomandaMapper.deleteByExample(ex);
			
			// Se erano stati inseriti dati nel Tab Passaporto : rimuovo
			CarTDomanda domanda = getCarTDomandaByIdDomanda(idDomanda);
			if(domanda != null && domanda.getIdCentroAziendalePassaporto() != null){
				logger.debug("-- Rimuovo i dati salvati nel Tab Passaporto");
				deletePassaportoByIdDomanda(domanda);
			}
			
			// Se era stata generata la stampa : delete car_t_dati_domanda
			logger.debug("-- Rimuovo dati in CAR_T_DATI_DOMANDA");
			CarTDatiDomandaExample datiDomEx = new CarTDatiDomandaExample();
			datiDomEx.createCriteria().andIdDomandaEqualTo(idDomanda);
			datiDomandaMapper.deleteByExample(datiDomEx);
									
			logger.debug("-- Rimuovo dati in CAR_T_DOMANDA per idDomanda = "+idDomanda);
			carTDomandaMapper.deleteByPrimaryKey(idDomanda);
		} 
		catch (Exception e) {
			logger.error("-- Exception in deleteDomandaPassaporto =" + e.getMessage());
			throw new BusinessException(e.getMessage());
		}
		finally{
			logger.info("END deleteDomandaPassaporto");
		}
	}
	
	@Override
	public CarTDomanda getCarTDomandaByIdDomanda(Long idDomanda) throws BusinessException{
		return carTDomandaMapper.selectByPrimaryKey(idDomanda);
	}
	
	
	// Rimuove tutti i dati inseriti nel tab Passaporto
	private void deletePassaportoByIdDomanda(CarTDomanda domanda) throws BusinessException{
		logger.debug("BEGIN deletePassaportoByIdDomanda");
		try{			
			// DELETE IN CAR_T_RESPONSABILE_PASSAPORTO
			logger.debug("-- DELETE IN CAR_T_RESPONSABILE_PASSAPORTO");
			respPassaportoMapper.deleteByPrimaryKey(domanda.getIdResponsabilePassaporto());
			
			// AGGIORNO CAMPI : CAR_T_DOMANDA.ID_RESPONSABILE_PASSAPORTO e CAR_T_DOMANDA.ID_CENTRO_AZIENDALE_PASSAPORTO
			logger.debug("-- AGGIORNO CAMPI : CAR_T_DOMANDA.ID_RESPONSABILE_PASSAPORTO e CAR_T_DOMANDA.ID_CENTRO_AZIENDALE_PASSAPORTO");
			domanda.setIdResponsabilePassaporto(null);
			domanda.setIdCentroAziendalePassaporto(null);
			domandaMapper.updateByPrimaryKey(domanda);
			
			// -> PROBLEMA DA CAPIRE COME GESTIRE : CAR_T_CENTRO_AZIENDALE.ID_TIPOLOGIA_PASSAPORTO 
			
			// Delete valori selezionati in Radio e check
			logger.debug("-- Delete valori selezionati in Radio e check Tab Passaporto");
			logger.debug("-- idDomanda ="+domanda.getIdDomanda());
			eliminaVoceUtente(domanda.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 1L);
			eliminaVoceUtente(domanda.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 3L);
			eliminaVoceUtente(domanda.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 5L);
			eliminaVoceUtente(domanda.getIdDomanda(), CaronteConstants.ID_TIPO_MODELLO_RUOP_PASS, 6L);
		}
		catch (Exception e) {
			logger.error("-- Exception in deletePassaportoByIdDomanda =" + e.getMessage());
			throw new BusinessException(e.getMessage());
		}
		finally{
			logger.debug("END deletePassaportoByIdDomanda");
		}
		
	}


	@Override
	public void eliminaDomandaByIdDomanda(Long id) throws BusinessException {
		
		logger.debug("BEGIN eliminaDomandaByIdDomanda");
		CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(id);
		if(domanda.getIdStatoComunicazione() == CaronteConstants.STATO_RICHIESTA_BOZZA){
			
			CarRFlussoDomandaExample flDomEx = new CarRFlussoDomandaExample();
			flDomEx.createCriteria().andIdDomandaEqualTo(id);
			flussoDomandaMapper.deleteByExample(flDomEx);
			
			CarTAllegatoDomandaExample allegDomEx = new CarTAllegatoDomandaExample();
			allegDomEx.createCriteria().andIdDomandaEqualTo(id);
			allegatoDomandaMapper.deleteByExample(allegDomEx);
			
			CarTDatiDomandaExample datiDomEx = new CarTDatiDomandaExample();
			datiDomEx.createCriteria().andIdDomandaEqualTo(id);
			datiDomandaMapper.deleteByExample(datiDomEx);
			
			CarTVoceUtenteExample voceUtEx = new CarTVoceUtenteExample();
			voceUtEx.createCriteria().andIdDomandaEqualTo(id);
			List<CarTVoceUtente> listaVoci = voceUtenteMapper.selectByExample(voceUtEx);
			
			for(CarTVoceUtente voce:listaVoci){
				CarRVoceUtenteSpecieExample voceUtSpeEx = new CarRVoceUtenteSpecieExample();
				voceUtSpeEx.createCriteria().andIdVoceUtenteEqualTo(voce.getIdVoceUtente());
				voceUtenteSpecieMapper.deleteByExample(voceUtSpeEx);
			}
			voceUtenteMapper.deleteByExample(voceUtEx);
			
			CarTZonaProtettaUtenteExample zonaProtUtEx = new CarTZonaProtettaUtenteExample();
			zonaProtUtEx.createCriteria().andIdDomandaEqualTo(id);
			zonaProtettaUtenteMapper.deleteByExample(zonaProtUtEx);
			
			CarRAttMaterialeUtenteExample attMatUtEx = new CarRAttMaterialeUtenteExample();
			attMatUtEx.createCriteria().andIdDomandaEqualTo(id);
			attMaterialeUtenteMapper.deleteByExample(attMatUtEx);
			
			CarRDomandaCentroAzExample domandaCentroAzEx = new CarRDomandaCentroAzExample();
			domandaCentroAzEx.createCriteria().andIdDomandaEqualTo(id);
			domandaCentroAzMapper.deleteByExample(domandaCentroAzEx);
			
			CarRDomandaTipologiaExample domTipEx = new CarRDomandaTipologiaExample();
			domTipEx.createCriteria().andIdDomandaEqualTo(id);
			domandaTipologMapper.deleteByExample(domTipEx);
			
			CarTModuloExample moduloEx = new CarTModuloExample();
			moduloEx.createCriteria().andIdDomandaEqualTo(id);
			moduloMapper.deleteByExample(moduloEx);
			
			carTDomandaMapper.deleteByPrimaryKey(id);
		}
		logger.debug("END eliminaDomandaByIdDomanda");
	}


	@Override
	public void salvaCentroAziendale(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException {

		 // Recupero i dati del centro aziendale
		  CarTCentroAziendale centroAzDb = centroAzMapper.selectByPrimaryKey(form.getIdCentroAziendaleSel());
		  logger.debug("-- *** Aggiornamento CENTRO AZIENDALE con idCentroAziendale ="+form.getIdCentroAziendaleSel()); 

		  centroAzDb.setCap(form.getCapCentroAz());
		  centroAzDb.setCellulare(form.getCellulareCentroAz());
		  centroAzDb.setCodCentroAziendale(form.getCodCentroAz());
		  centroAzDb.setCodiceFiscale(form.getCodFiscale());
		  centroAzDb.setDataUpdate(new Date());
		  centroAzDb.setDenominazione(form.getDenominazCentroAz());
		  centroAzDb.setEmail(form.getMailCentroAz());
		  centroAzDb.setFlPrincipale(false);
		  centroAzDb.setFrazione(form.getFrazioneCentroAz());
		  centroAzDb.setIdComune(form.getIdComuneCentroAz());
		  centroAzDb.setIdSpedizioniere(form.getIdAzienda());
		  centroAzDb.setIdUtenteUpdate(utente.getId());
		  centroAzDb.setIndirizzo(form.getIndirizzoCentroAz());
		  centroAzDb.setPec(form.getPecCentroAz());
		  centroAzDb.setTelefono(form.getTelefonoCentroAz());
		  centroAzMapper.updateByPrimaryKey(centroAzDb);
		
	}
	
	@Override
	/*
	 *   REGOLA PER SAPERE QUALE VERSIONE VISUALIZZARE :
	 *   Le domande in stato Convalidato o Respinto e con dataAggiornamento < CAR_D_VERSIONE_DOMANDA.DATA_INIZIO_VERSIONE : devono vedere il vecchio layout
	 * 
	 */
	public Long checkVersioneDomanda(Long idDomanda) throws BusinessException{		
    	Date dataAggiornamento = new Date();
    	Long idVersione = null;
    	if (idDomanda != null) {
    		CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(idDomanda);
    		if (domanda != null && domanda.getDataAggiornamento() != null) {
    			/*
    			 *  Controllo lo stato della Domanda : 
    			 *   - se lo stato è Convalidata o Respinta, controllo anche la data di aggiornamento e la confronto con la data inizio versione
    			 *   - altrimenti : prendo la versione con la data_fine_versione non valorizzata e quindi l'ultima
    			 */
    			logger.debug("-- idStatoComunicazione ="+domanda.getIdStatoComunicazione());
    			if(domanda.getIdStatoComunicazione() == CaronteConstants.STATO_COMUNICAZIONE_CONVALIDATA ||  domanda.getIdStatoComunicazione() == CaronteConstants.STATO_COMUNICAZIONE_RESPINTA){
    				dataAggiornamento = domanda.getDataAggiornamento();
    				logger.debug("-- dataAggiornamento ="+dataAggiornamento);
    				idVersione = domandaMapper.getVersioneDomandaByDataAggiornamento(dataAggiornamento);
    		    	logger.debug("-- idVersione = "+idVersione);
    			}
    			else{
    				idVersione = domandaMapper.getUltimaVersioneDomanda();
    				logger.debug("-- idVersione = "+idVersione);
    			}
    		}   		
    	}    	
    	return idVersione;
	}


	@Override
	public CarRDomandaCentroAz getDomandaCentroAzByIdDomandaIdCentroAz(Long idDomanda, Long idCentroAzSel)
			throws BusinessException {
		CarRDomandaCentroAz domandaCentroAz = domandaCentroAzMapper.selectByPrimaryKey(idDomanda, idCentroAzSel);	
		return domandaCentroAz;
	}
	

}
