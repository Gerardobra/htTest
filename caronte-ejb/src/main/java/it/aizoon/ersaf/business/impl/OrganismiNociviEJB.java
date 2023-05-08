package it.aizoon.ersaf.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import it.aizoon.ersaf.business.IGeneriSpecieEJB;
import it.aizoon.ersaf.business.IOrganismiNociviEJB;
import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.GenereSpecieOrgNocivoDTO;
import it.aizoon.ersaf.dto.OrgNocivoGenereSpecieDTO;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDAutoreEppo;
import it.aizoon.ersaf.dto.generati.CarDAutoreEppoExample;
import it.aizoon.ersaf.dto.generati.CarDGenere;
import it.aizoon.ersaf.dto.generati.CarDGenereExample;
import it.aizoon.ersaf.dto.generati.CarDGruppoZonaProtetta;
import it.aizoon.ersaf.dto.generati.CarDGruppoZonaProtettaExample;
import it.aizoon.ersaf.dto.generati.CarDLingua;
import it.aizoon.ersaf.dto.generati.CarDLinguaExample;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivoExample;
import it.aizoon.ersaf.dto.generati.CarDSpecie;
import it.aizoon.ersaf.dto.generati.CarDSpecieExample;
import it.aizoon.ersaf.dto.generati.CarDTipoOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDTipoOrgNocivoExample;
import it.aizoon.ersaf.dto.generati.CarDTipoProdotto;
import it.aizoon.ersaf.dto.generati.CarDTipoProdottoExample;
import it.aizoon.ersaf.dto.generati.CarDZonaProtetta;
import it.aizoon.ersaf.dto.generati.CarDZonaProtettaExample;
import it.aizoon.ersaf.dto.generati.CarRLinguaGenere;
import it.aizoon.ersaf.dto.generati.CarRLinguaGenereExample;
import it.aizoon.ersaf.dto.generati.CarRLinguaSpecie;
import it.aizoon.ersaf.dto.generati.CarRLinguaSpecieExample;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniereExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.exception.DbException;
import it.aizoon.ersaf.form.NuovaSpecieForm;
import it.aizoon.ersaf.form.NuovoGenereForm;
import it.aizoon.ersaf.form.NuovoOrganismoNocivoForm;
import it.aizoon.ersaf.form.RicercaGeneriForm;
import it.aizoon.ersaf.form.RicercaOrganismiNociviForm;
import it.aizoon.ersaf.form.RicercaSpecieForm;
import it.aizoon.ersaf.integration.GeneriSpecieDAO;
import it.aizoon.ersaf.integration.OrganismiNociviDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.DomandaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.GenereMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.OrganismiNociviMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.SpecieMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDAutoreEppoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDGenereMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDGruppoZonaProtettaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDLinguaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDNazioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDOrgNocivoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDSpecieMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoOrgNocivoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoProdottoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDZonaProtettaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRLinguaGenereMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRLinguaSpecieMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

@SuppressWarnings("unused")
@Stateless(name = "OrganismiNocivi")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class OrganismiNociviEJB extends AbstractEJB<OrganismiNociviDAO,  CarDOrgNocivoMapper,  CarDOrgNocivo, CarDOrgNocivoExample>
    implements IOrganismiNociviEJB {

	private final String THIS_CLASS = "[" + OrganismiNociviEJB.class.getCanonicalName() + "]";
	
  @Inject
  OrganismiNociviMapper organismiNociviMapper;
  
  @Inject
  CarDOrgNocivoMapper carDOrgNocivoMapper;
 
  @Inject
  CarDTipoOrgNocivoMapper carDTipoOrgNocivoMapper;

  @Inject
  CarDTipoProdottoMapper carDTipoProdottoMapper;
  
  @Inject
  CarDZonaProtettaMapper carDZonaProtettaMapper;
  
  @Inject
  CarRLinguaGenereMapper carRLinguaGenereMapper;
  
  @Inject
  CarRLinguaSpecieMapper carRLinguaSpecieMapper;
  
  @Inject
  CarDGruppoZonaProtettaMapper carDGruppoZonaProtettaMapper;
  
  @Inject
  CarDSpecieMapper carDSpecieMapper;
  
  @Inject
  GenereMapper genereMapper;
  
  
  
  
	@Override
	public List<OrgNocivoGenereSpecieDTO> getElencoOrganismiNocivi(RicercaOrganismiNociviForm ricercaOrganismiNocivi) throws BusinessException {
		List<OrgNocivoGenereSpecieDTO> orgNocList = organismiNociviMapper.getElencoOrganismiNocivi(ricercaOrganismiNocivi);
		return orgNocList;
	}

	@Override
	public List<CarDOrgNocivo> getOrganismiNocivi() throws BusinessException {
		return organismiNociviMapper.getOrganismiNocivi();
	}

	@Override
	public List<CarDTipoOrgNocivo> getTipiOrganismiNocivi() throws BusinessException {
		return organismiNociviMapper.getTipiOrganismiNocivi();				
	}

	@Override
	public List<CarDTipoProdotto> getTipiProdotto(Long idTipoProdotto) throws BusinessException {
		return organismiNociviMapper.getTipiProdotto(idTipoProdotto);		
	}

	@Override
	public void cancellaOrganismoNocivoByIdOrganismoNocivo(Long idOrganismoNocivo) throws BusinessException {
		CarDOrgNocivo organismoNocivo = new CarDOrgNocivo();
		organismoNocivo.setIdOrgNocivo(idOrganismoNocivo); 
		organismoNocivo.setFineValidita(new Date());
		carDOrgNocivoMapper.updateByPrimaryKeySelective(organismoNocivo);
		return;
	}

	@Override
	public void cancellaZonaProtettaByIdZonaProtetta(Long idZonaProtetta) throws BusinessException {
		CarDZonaProtetta zonaProtetta = new CarDZonaProtetta();
		zonaProtetta.setIdZonaProtetta(idZonaProtetta); 
		zonaProtetta.setFineValidita(new Date());
		carDZonaProtettaMapper.updateByPrimaryKeySelective(zonaProtetta);
		return;
	}

	@Override
	public List<CarRLinguaGenere> getGeneri() throws BusinessException {
		CarRLinguaGenereExample filtri = new CarRLinguaGenereExample();

		filtri.createCriteria().andIdLinguaEqualTo(CaronteConstants.ID_LINGUA_LATINO);
		filtri.setOrderByClause("denom_genere");
		return carRLinguaGenereMapper.selectByExample(filtri);
	}

	@Override
	public List<CarRLinguaSpecie> getSpecie() throws BusinessException {
		CarRLinguaSpecieExample filtri = new CarRLinguaSpecieExample();
		
		filtri.createCriteria().andIdLinguaEqualTo(CaronteConstants.ID_LINGUA_LATINO);
		filtri.setOrderByClause("denom_specie");
		return carRLinguaSpecieMapper.selectByExample(filtri);
	}

	@Override
	public void inserisciNuovoOrganismoNocivo(NuovoOrganismoNocivoForm form) throws BusinessException {		
		logger.debug("BEGIN inserisciNuovoOrganismoNocivo");
		
		/*  Inserimenti lato db su :
			   *  	INSERT INTO CAR_D_ORG_NOCIVO
			   *  
			   *  - Possibilità di legare l'organismo nocivo ad una zona protetta o no: 
			   *  - se checkano il campo 'Organismo nocivo in zona protetta' : INSERT INTO CAR_D_GRUPPO_ZONA_PROTETTA
			   *    altrimenti in CAR_D_ZONA_PROTETTA lo lego con id_gruppo_zona_protetta = 32
			   *        
			   *	INSERT INTO CAR_D_ZONA_PROTETTA  		
		*/		
			logger.debug("-- INSERT INTO CAR_D_ORG_NOCIVO");
			CarDOrgNocivo organismoNocivo = new CarDOrgNocivo();
			organismoNocivo.setCodiceEppo(form.getNuovoCodiceEppo().trim().toUpperCase());
			organismoNocivo.setDescBreve(form.getDescrizioneOrganismoNocivo().trim());
			organismoNocivo.setDescEstesa(form.getDescrizioneOrganismoNocivo().trim());
			organismoNocivo.setInizioValidita(new Date());
			carDOrgNocivoMapper.insert(organismoNocivo);
			
			CarDGruppoZonaProtetta gruppoZonaProtetta = new CarDGruppoZonaProtetta();
			logger.debug("-- orgNocInZonaProtetta ="+form.getOrgNocInZonaProtetta());
			// Caso di legame con ZONA PROTETTA
			if(form.getOrgNocInZonaProtetta() != null && form.getOrgNocInZonaProtetta().equals("S")){
				logger.debug("-- Caso di legame con ZONA PROTETTA");
				logger.debug("-- INSERT INTO CAR_D_GRUPPO_ZONA_PROTETTA");				
				gruppoZonaProtetta.setDescBreve(form.getNuovoCodiceEppo());
				gruppoZonaProtetta.setDescEstesa("Zona protetta per il codice_eppo " + form.getNuovoCodiceEppo());
				gruppoZonaProtetta.setInizioValidita(new Date());
				carDGruppoZonaProtettaMapper.insert(gruppoZonaProtetta);
			}
			// Caso di NON legame con ZONA PROTETTA
			else{
				logger.debug("-- Caso di NON legame con ZONA PROTETTA");
				// forzatura di id_gruppo_zona_protetta = 32 (GENERIC)
				gruppoZonaProtetta.setIdGruppoZonaProtetta(CaronteConstants.ID_GRUPPO_ZONA_PROTETTA_GENERIC);
			}
			
			insertCarDZonaProtetta(organismoNocivo.getIdOrgNocivo(), gruppoZonaProtetta.getIdGruppoZonaProtetta(), form);			
			
			logger.debug("END inserisciNuovoOrganismoNocivo");		
	}
	
	/*private String getCodiceEppoByIdOrgNocivo(Long idOrgNocivo){
		String codiceEppo = null;
		CarDOrgNocivo orgNoc = carDOrgNocivoMapper.selectByPrimaryKey(idOrgNocivo);
		if(orgNoc != null)
		  codiceEppo = orgNoc.getCodiceEppo();
		return codiceEppo;
	}*/
	
	private CarDGruppoZonaProtetta getGruppoZonaProtettaByCodiceEppo(String nuovoCodiceEppo){
		CarDGruppoZonaProtetta gruppoZonaProtetta = null;
		CarDGruppoZonaProtettaExample gruppoZonaProtEx = new CarDGruppoZonaProtettaExample();
		gruppoZonaProtEx.createCriteria().andDescBreveEqualTo(nuovoCodiceEppo);
		List<CarDGruppoZonaProtetta> gruppoZonaProtettaList = carDGruppoZonaProtettaMapper.selectByExample(gruppoZonaProtEx);
		
		if(gruppoZonaProtettaList != null && gruppoZonaProtettaList.size() > 0) {
			gruppoZonaProtetta = gruppoZonaProtettaList.get(0);
		}
		logger.debug("gruppoZonaProtetta= " + gruppoZonaProtetta);
		return gruppoZonaProtetta;
	}
	
	private void insertCarDZonaProtetta(Long idOrganismoNoc, Long idGruppoZonaProtetta, NuovoOrganismoNocivoForm form) throws BusinessException{
		logger.debug("BEGIN insertCarDZonaProtetta");

		//idspecie proviene da nuova.jsp, specie proviene da modifica.jsp
		String[] idSpecieArr = null;
		if(form.getIdSpecie() == null){
			idSpecieArr = form.getSpecie();
		} else {
			idSpecieArr = form.getIdSpecie();
		}
		//logger.debug("-- Numero di specie indicate = "+idSpecieArr.length);
		
		//denomgenere proviene da nuova.jsp, idgenere proviene da modifica.jsp
		
		Long[] idGenereArr = null;
		if (form.getDenomGenere() == null){
			//Creazione array con lunghezza = numero di specie aggiunte nella pagina di modifica.jsp
			//questo perchè in nuova è possibile fare l' insert contemporaneo di più generi diversi (utilizziamo il vettore denomgenere per 
			//salvare le varie denominazioni) mentre in modifica no(utilizziamo idGenere per salvare l'id del genere). 
			
			idGenereArr = new Long[idSpecieArr.length];
			for(int i=0; i<idGenereArr.length; i++){
				idGenereArr[i] = form.getIdGenere();
			}
			
		} else {
			//Per ogni denominazione genere recuperiamo l'idgenere associato
			
			idGenereArr = new Long[form.getDenomGenere().length];
			for(int i=0; i<form.getDenomGenere().length; i++){
				idGenereArr[i] = genereMapper.getGenereDenominazione(form.getDenomGenere()[i]).getIdGenere();
			}
		}
        //logger.debug("-- Numero di generi indicati = "+denomGenereArr.length); 
		
		
		CarDZonaProtetta zonaProtetta = new CarDZonaProtetta();
		zonaProtetta.setIdOrgNocivo(idOrganismoNoc);
		zonaProtetta.setIdTipoOrgNocivo(form.getIdTipoOrgNocivo());
		zonaProtetta.setIdGruppoZonaProtetta(idGruppoZonaProtetta);
		zonaProtetta.setIdTipoProdotto(form.getIdTipoProdotto());
		zonaProtetta.setInizioValidita(new Date());
		
		for(int i=0; i<idGenereArr.length; i++){
			logger.debug("-- idGenere =" + idGenereArr[i]);
			zonaProtetta.setIdGenere(idGenereArr[i]);
			
			if (idSpecieArr[i] != null) { 
				zonaProtetta.setIdSpecie(Long.parseLong(idSpecieArr[i]));
				logger.debug("-- idSpecie =" + Long.parseLong(idSpecieArr[i]));
				logger.debug("-- Inserimento con le specie");
		      } else {
		    	  zonaProtetta.setIdSpecie(null);
		    	  logger.debug("-- idSpecie =" + null);
		    	  logger.debug("-- Inserimento senza specie");
		      }
			logger.debug("-- INSERT INTO CAR_D_ZONA_PROTETTA");
			carDZonaProtettaMapper.insert(zonaProtetta);
			
		}
		 
		
		/*
		// Prendo gli idSpecie selezionati nella multiselect
		if(form.getSpecie() != null && form.getSpecie().length>0){
			logger.debug("-- Inserimento con le specie");
			CarDSpecieExample specieEx = new CarDSpecieExample();			
			for(String specie : form.getSpecie()){
				logger.debug("-- specie ="+specie);				
				zonaProtetta.setIdSpecie(new Long(specie));
				logger.debug("-- INSERT INTO CAR_D_ZONA_PROTETTA");
				carDZonaProtettaMapper.insert(zonaProtetta);
			}
		}
		else{
			logger.debug("-- Inserimento senza specie");
			logger.debug("-- INSERT INTO CAR_D_ZONA_PROTETTA");
			carDZonaProtettaMapper.insert(zonaProtetta);
		}
		
		*/
		logger.debug("END insertCarDZonaProtetta");
	}

	@Override
	public List<CarDZonaProtetta> getZonaProtettaByNuovoONForm(NuovoOrganismoNocivoForm form) {
		
		CarDGruppoZonaProtettaExample gruppoZP = new CarDGruppoZonaProtettaExample();	
		gruppoZP.createCriteria().andDescBreveEqualTo(form.getNuovoCodiceEppo());
		List<CarDGruppoZonaProtetta> gzp = carDGruppoZonaProtettaMapper.selectByExample(gruppoZP);	
		if(gzp.size() == 0){
			return null;
		}
		
		CarDZonaProtettaExample zonaProtetta = new CarDZonaProtettaExample();		

		if(form.getIdSpecie() == null){
		zonaProtetta.createCriteria()
			 .andIdTipoOrgNocivoEqualTo(form.getIdTipoOrgNocivo())
			 .andIdTipoProdottoEqualTo(form.getIdTipoProdotto())
			 .andIdGenereEqualTo(form.getIdGenere())
			 .andIdGruppoZonaProtettaEqualTo(gzp.get(0).getIdGruppoZonaProtetta());
		}		
		else{
			zonaProtetta.createCriteria()
			 .andIdTipoOrgNocivoEqualTo(form.getIdTipoOrgNocivo())
			 .andIdTipoProdottoEqualTo(form.getIdTipoProdotto())
			 .andIdGenereEqualTo(form.getIdGenere())
			 .andIdSpecieEqualTo(form.getIdSpecieLong())
			 .andIdGruppoZonaProtettaEqualTo(gzp.get(0).getIdGruppoZonaProtetta());
		}	 
		
		List<CarDZonaProtetta> zp = carDZonaProtettaMapper.selectByExample(zonaProtetta);		
		return zp.size() == 0 ? null : zp;
		
	}
	
	private Long insertCarDGruppoZonaProtettaByOrgNoc(CarDOrgNocivo orgNoc) {
		CarDGruppoZonaProtetta gruppoZonaProtetta = new CarDGruppoZonaProtetta();
		gruppoZonaProtetta.setDescBreve(orgNoc.getCodiceEppo());
		gruppoZonaProtetta.setDescEstesa("Zona protetta per il codice_eppo " + orgNoc.getCodiceEppo());
		gruppoZonaProtetta.setInizioValidita(new Date());
		carDGruppoZonaProtettaMapper.insert(gruppoZonaProtetta);
		
		return gruppoZonaProtetta.getIdGruppoZonaProtetta();
	}
	
	@Override
	public OrgNocivoGenereSpecieDTO getDatiOrganismoNocivoByIdOrgNoc(Long idOrganismoNocivo) throws BusinessException{		
		return organismiNociviMapper.getDatiOrganismoNocivoByIdOrgNoc(idOrganismoNocivo);		
	}
	
	@Override
	public List<GenereSpecieOrgNocivoDTO> getGenereSpecieOrgNocivoByIdOrgNoc(Long idOrganismoNocivo) throws BusinessException{
		return organismiNociviMapper.getGenereSpecieOrgNocivoByIdOrgNoc(idOrganismoNocivo);
	}
	
	@Override
	public CarDOrgNocivo getOrganismoNocivoByCodiceEppoDescrOrgNoc(String codiceEppo, String descrOrgNoc) throws BusinessException{
		return organismiNociviMapper.getOrganismoNocivoByCodiceEppoDescrOrgNoc(codiceEppo, descrOrgNoc);
	}
	
	
	@Override
	public void salvaModificaOrgNocivo(NuovoOrganismoNocivoForm form) throws BusinessException {		
		logger.debug("BEGIN salvaModificaOrgNocivo");	
	
		
		CarDOrgNocivo organismoNocivo = carDOrgNocivoMapper.selectByPrimaryKey(form.getIdOrgNocivo());
		logger.debug("-- Old codice_eppo ="+organismoNocivo.getCodiceEppo());
		logger.debug("-- New codice_eppo ="+form.getNuovoCodiceEppo());
		
		// mi recupero, se c'è, l'id del gruppo per poter aggiornare anche le descrizioni
		CarDGruppoZonaProtetta gruppoZonaProtetta = getGruppoZonaProtettaByCodiceEppo(organismoNocivo.getCodiceEppo());
		if (form.getOrgNocInZonaProtetta() != null && form.getOrgNocInZonaProtetta().equals("S")) {
			logger.debug("gruppoZonaProtetta == null=" + (gruppoZonaProtetta == null));
			if (gruppoZonaProtetta == null) {
				// allora lo devo inserire come nuovo perchè hanno appena messo il flag
				gruppoZonaProtetta = new CarDGruppoZonaProtetta();
				gruppoZonaProtetta.setDescBreve(form.getNuovoCodiceEppo());
				gruppoZonaProtetta.setDescEstesa("Zona protetta per il codice_eppo " + form.getNuovoCodiceEppo());
				gruppoZonaProtetta.setInizioValidita(new Date());
				carDGruppoZonaProtettaMapper.insert(gruppoZonaProtetta);								
				
			} else {
				// aggiorno il gruppo con le nuove descrizioni
				gruppoZonaProtetta.setDescBreve(form.getNuovoCodiceEppo());
				gruppoZonaProtetta.setDescEstesa("Zona protetta per il codice_eppo " + form.getNuovoCodiceEppo());
				carDGruppoZonaProtettaMapper.updateByPrimaryKeySelective(gruppoZonaProtetta);	
				
			}
	
			// UPDATE SU CAR_D_ZONA_PROTETTA : setto il gruppo zona protetta ai record con idorgnocivo modificato
			CarDZonaProtettaExample zonaProtettaEx = new CarDZonaProtettaExample();
			zonaProtettaEx.createCriteria().andIdOrgNocivoEqualTo(form.getIdOrgNocivo());
			CarDZonaProtetta zonaProtettaRecord = new CarDZonaProtetta();
			zonaProtettaRecord.setIdGruppoZonaProtetta(gruppoZonaProtetta.getIdGruppoZonaProtetta());
			carDZonaProtettaMapper.updateByExampleSelective(zonaProtettaRecord, zonaProtettaEx);

			
		} else if (gruppoZonaProtetta != null) {
			// se hanno tolto il flag ad un ON che era anche ZP allora devo associare a tutti il GENERIC
			CarDZonaProtettaExample zonaProtetta = new CarDZonaProtettaExample();
			logger.debug("1--1");
			zonaProtetta.createCriteria().andIdGruppoZonaProtettaEqualTo(gruppoZonaProtetta.getIdGruppoZonaProtetta());
			logger.debug("2--2");
			List<CarDZonaProtetta> zp = carDZonaProtettaMapper.selectByExample(zonaProtetta);
			logger.debug("zp != null=" + (zp != null));
			logger.debug("zp.size() > 0=" + (zp.size() > 0));
			if (zp != null && zp.size() > 0) {
				for (CarDZonaProtetta zonaP : zp) {
					zonaP.setIdGruppoZonaProtetta(CaronteConstants.ID_GRUPPO_ZONA_PROTETTA_GENERIC);
					carDZonaProtettaMapper.updateByPrimaryKeySelective(zonaP);
				}
			}
		}
		
		// aggiorno l'organismo nocivo
		organismoNocivo.setCodiceEppo(form.getNuovoCodiceEppo().trim().toUpperCase());
		organismoNocivo.setDescBreve(form.getDescrizioneOrganismoNocivo().trim());
		organismoNocivo.setDescEstesa(form.getDescrizioneOrganismoNocivo().trim());		
		carDOrgNocivoMapper.updateByPrimaryKeySelective(organismoNocivo);		
			
		logger.debug("END salvaModificaOrgNocivo");		
	}
	
	@Override
	public void aggiunugiGenereSpecieOrgNocivo(NuovoOrganismoNocivoForm form) throws BusinessException {		
		logger.debug("BEGIN aggiunugiGenereSpecieOrgNocivo");
		
		CarDGruppoZonaProtetta gruppoZonaProtetta = getGruppoZonaProtettaByCodiceEppo(form.getNuovoCodiceEppo());
		
		if (form.getOrgNocInZonaProtetta() != null && form.getOrgNocInZonaProtetta().equals("S")) {
			gruppoZonaProtetta.setIdGruppoZonaProtetta(CaronteConstants.ID_GRUPPO_ZONA_PROTETTA_GENERIC);
			logger.debug("idgruppozonaprotetta= " + gruppoZonaProtetta.getIdGruppoZonaProtetta());
		} else if (gruppoZonaProtetta == null) {
			logger.debug("nell' else if");
			// allora è stata tolta la spunta o non è presente un gruppo per ON indicato
			gruppoZonaProtetta = new CarDGruppoZonaProtetta();
			gruppoZonaProtetta.setDescBreve(form.getNuovoCodiceEppo());
			gruppoZonaProtetta.setDescEstesa("Zona protetta per il codice_eppo " + form.getNuovoCodiceEppo());
			gruppoZonaProtetta.setInizioValidita(new Date());
			carDGruppoZonaProtettaMapper.insert(gruppoZonaProtetta);
		}
		
		insertCarDZonaProtetta(form.getIdOrgNocivo(), gruppoZonaProtetta.getIdGruppoZonaProtetta(), form);	
					
		logger.debug("END aggiunugiGenereSpecieOrgNocivo");		
	}
	
	 @Override
	  public void cancellaFisicoZonaProtettaByIdZonaProtetta(Long idZonaProtetta) throws BusinessException {
	    
	    carDZonaProtettaMapper.deleteByPrimaryKey(idZonaProtetta);
	    
	  }
	 
	  @Override
	  public CarDOrgNocivo getOrganismoNocivoByIdOrgNoc(Long idOrgNoc) throws BusinessException{
	    return carDOrgNocivoMapper.selectByPrimaryKey(idOrgNoc);
	  }
	  
	  @Override
	  public void updateFineValiditaOrgNocivo(Long idOrgNocivo, Date fineValidita) throws BusinessException {
	    
	    CarDOrgNocivo orgNoc = getOrganismoNocivoByIdOrgNoc(idOrgNocivo);
	    orgNoc.setFineValidita(fineValidita);
	    carDOrgNocivoMapper.updateByPrimaryKey(orgNoc);
	    
	  }
	  

}
