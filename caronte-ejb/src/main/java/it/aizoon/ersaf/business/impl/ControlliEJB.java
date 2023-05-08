package it.aizoon.ersaf.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.springframework.util.StringUtils;

import it.aizoon.ersaf.business.IControlliEJB;
import it.aizoon.ersaf.dto.AllegatoDTO;
import it.aizoon.ersaf.dto.CampioneDTO;
import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.ControlloDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.IspettoreDTO;
import it.aizoon.ersaf.dto.MisuraDTO;
import it.aizoon.ersaf.dto.MisuraUfficialeDTO;
import it.aizoon.ersaf.dto.MonitCofinanziatoDTO;
import it.aizoon.ersaf.dto.OrgNocivoGenereSpecieDTO;
import it.aizoon.ersaf.dto.RequisitiProfessionaliDTO;
import it.aizoon.ersaf.dto.SementeDTO;
import it.aizoon.ersaf.dto.SitoProduzioneDTO;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.TipologiaAttMaterialeDTO;
import it.aizoon.ersaf.dto.generati.CarDConoscenzaProfessionale;
import it.aizoon.ersaf.dto.generati.CarDMateriale;
import it.aizoon.ersaf.dto.generati.CarDMetodoProduzione;
import it.aizoon.ersaf.dto.generati.CarDNormaVerbale;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDStrutturaAttrezzatura;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazioneExample;
import it.aizoon.ersaf.dto.generati.CarDTipoIrrigazione;
import it.aizoon.ersaf.dto.generati.CarDTipoStampa;
import it.aizoon.ersaf.dto.generati.CarDTipoStampaExample;
import it.aizoon.ersaf.dto.generati.CarDTipologiaControllo;
import it.aizoon.ersaf.dto.generati.CarRCampioneOrgNoc;
import it.aizoon.ersaf.dto.generati.CarRCampioneOrgNocExample;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoConProf;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoConProfExample;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoIrriga;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoIrrigaExample;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoMetPro;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoMetProExample;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoOrgNoc;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoOrgNocExample;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoReqProf;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoReqProfExample;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoSpecie;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoSpecieExample;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoStrAtt;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoStrAttExample;
import it.aizoon.ersaf.dto.generati.CarRControlloIdentitaSito;
import it.aizoon.ersaf.dto.generati.CarRControlloIspettore;
import it.aizoon.ersaf.dto.generati.CarRControlloIspettoreExample;
import it.aizoon.ersaf.dto.generati.CarRControlloMateriale;
import it.aizoon.ersaf.dto.generati.CarRControlloNormaVerbale;
import it.aizoon.ersaf.dto.generati.CarRControlloNormaVerbaleExample;
import it.aizoon.ersaf.dto.generati.CarRControlloTipoSemente;
import it.aizoon.ersaf.dto.generati.CarRControlloTipologia;
import it.aizoon.ersaf.dto.generati.CarRControlloTipologiaExample;
import it.aizoon.ersaf.dto.generati.CarRMisuraIspettore;
import it.aizoon.ersaf.dto.generati.CarRMisuraIspettoreExample;
import it.aizoon.ersaf.dto.generati.CarRMisuraOrgNoc;
import it.aizoon.ersaf.dto.generati.CarRMisuraOrgNocExample;
import it.aizoon.ersaf.dto.generati.CarRMisuraTipologia;
import it.aizoon.ersaf.dto.generati.CarRMisuraTipologiaExample;
import it.aizoon.ersaf.dto.generati.CarTAllegatoControllo;
import it.aizoon.ersaf.dto.generati.CarTCampione;
import it.aizoon.ersaf.dto.generati.CarTCampioneExample;
import it.aizoon.ersaf.dto.generati.CarTCentroAziendale;
import it.aizoon.ersaf.dto.generati.CarTControllo;
import it.aizoon.ersaf.dto.generati.CarTControlloDocumentale;
import it.aizoon.ersaf.dto.generati.CarTControlloDocumentaleExample;
import it.aizoon.ersaf.dto.generati.CarTControlloExample;
import it.aizoon.ersaf.dto.generati.CarTControlloFisico;
import it.aizoon.ersaf.dto.generati.CarTControlloFisicoExample;
import it.aizoon.ersaf.dto.generati.CarTControlloIdentita;
import it.aizoon.ersaf.dto.generati.CarTControlloIdentitaExample;
import it.aizoon.ersaf.dto.generati.CarTDomanda;
import it.aizoon.ersaf.dto.generati.CarTEsito;
import it.aizoon.ersaf.dto.generati.CarTEsitoExample;
import it.aizoon.ersaf.dto.generati.CarTMisuraUfficiale;
import it.aizoon.ersaf.dto.generati.CarTMisuraUfficialeExample;
import it.aizoon.ersaf.dto.generati.CarTMonitCofinanziato;
import it.aizoon.ersaf.dto.generati.CarTMonitCofinanziatoExample;
import it.aizoon.ersaf.dto.generati.CarTResponsabilePassaporto;
import it.aizoon.ersaf.dto.generati.CarTSitoProduzione;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.ModaliForm;
import it.aizoon.ersaf.form.NuovoControlloForm;
import it.aizoon.ersaf.form.RicercaOperatoreForm;
import it.aizoon.ersaf.integration.ControlliDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.ControlloMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.DomandaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoComunicazioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoStampaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDVersioneControlloMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRCampioneOrgNocMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloFisicoConProfMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloFisicoIrrigaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloFisicoMetProMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloFisicoOrgNocMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloFisicoReqProfMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloFisicoSpecieMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloFisicoStrAttMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloIdentitaSitoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloIspettoreMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloMaterialeMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloNormaVerbaleMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloTipoSementeMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRControlloTipologiaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRMisuraIspettoreMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRMisuraOrgNocMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRMisuraTipologiaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTAllegatoControlloMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTAllegatoDomandaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTCampioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTCentroAziendaleMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTControlloDocumentaleMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTControlloFisicoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTControlloIdentitaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTControlloMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTEsitoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTMisuraUfficialeMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTMonitCofinanziatoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTSitoProduzioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTSpedizioniereMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

@SuppressWarnings("unused")
@Stateless(name = "Controlli")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class ControlliEJB extends AbstractEJB<ControlliDAO, CarTControlloMapper, CarTControllo, CarTControlloExample> implements IControlliEJB {

  private final String THIS_CLASS = "[" + ControlliEJB.class.getCanonicalName() + "]";
 
  @Inject
  ControlloMapper controlloMapper;
  
  @Inject
  CarTControlloMapper carTControlloMapper;
  
  @Inject
  CarRControlloIspettoreMapper carRControlloIspettoreMapper;
  
  @Inject
  CarRControlloNormaVerbaleMapper carRControlloNormaVerbaleMapper;
  
  @Inject
  CarRControlloTipoSementeMapper carRControlloTipoSementeMapper;
  
  @Inject
  CarRControlloTipologiaMapper carRControlloTipologiaMapper;
  
  @Inject
  CarTSpedizioniereMapper carTSpedizioniereMapper;
  
  @Inject
  CarTControlloDocumentaleMapper carTControlloDocMapper;
  
  @Inject
  CarTCentroAziendaleMapper carTCentroAziendaleMapper;
  
  @Inject
  CarTAllegatoDomandaMapper allegatoDomandaMapper;
  
  @Inject
  CarTAllegatoControlloMapper allegatoControlloMapper;

  @Inject
  CarTControlloIdentitaMapper carTControlloIdentitaMapper;
  
  @Inject
  CarRControlloIdentitaSitoMapper carRControlloIdentitaSitoMapper;
  
  @Inject
  CarTSitoProduzioneMapper carTSitoProdMapper;
  
  @Inject 
  CarRControlloIdentitaSitoMapper carRControlloIdSitoMapper;
  
  @Inject
  CarDTipoComunicazioneMapper tipoComunicazioneMapper;
  
  @Inject
  CarDTipoStampaMapper tipoStampaMapper;
  
  @Inject
  DomandaMapper domandaMapper;

  @Inject
  CarTEsitoMapper esitoMapper;
  
  @Inject
  CarTCampioneMapper campioneMapper;
  
  @Inject
  CarRCampioneOrgNocMapper campioneOrgNocMapper;
  
  @Inject
  CarTControlloDocumentaleMapper controlloDocumentaleMapper;
  
  @Inject
  CarTControlloIdentitaMapper controlloIdentitaMapper;
  
  @Inject
  CarTControlloFisicoMapper controlloFisicoMapper;
  
  @Inject
  CarRControlloIspettoreMapper controlloIspettoreMapper;
  
  @Inject
  CarRControlloNormaVerbaleMapper controlloNormaVerbaleMapper;
  
  @Inject
  CarRControlloTipologiaMapper controlloTipologiaMapper;
  
  @Inject
  CarTMisuraUfficialeMapper misuraUfficialeMapper;
  
  @Inject
  CarRMisuraIspettoreMapper carRMisuraIspettoreMapper;
  
  @Inject
  CarRMisuraTipologiaMapper carRMisuraTipologiaMapper;
  
  @Inject
  CarRMisuraOrgNocMapper carRMisuraOrgNocMapper;
  
  @Inject
  CarTMonitCofinanziatoMapper monitCofMapper;
  
  @Inject
  CarDVersioneControlloMapper carDVersioneControlloMapper;
  
  @Inject
  CarRControlloFisicoOrgNocMapper controlloFisicoOrgNocMapper;
    
  @Inject
  CarRControlloMaterialeMapper carRControlloMaterialeMapper;
  
  
 
  
  @Override
  public ControlloDTO getDettaglioAziendaByIdSpedizioniere(Long idSpedizioniere) throws BusinessException {
	  return controlloMapper.getDettaglioAziendaByIdSpedizioniere(idSpedizioniere);
  }

  
  @Override
  public List<SpedizioniereDTO> getElencoOperatori(RicercaOperatoreForm ricercaOperatore) throws BusinessException{
	  return controlloMapper.getElencoOperatori(ricercaOperatore);	 
  }


@Override
public List<ControlloDTO> getElencoControlli(Long idCentroAz) throws BusinessException {
	return controlloMapper.getElencoControlli(idCentroAz);
}


@Override
public void inserisciNuovoControllo(Long idCentroAz, NuovoControlloForm nuovoControlloForm, Long idUtente) {

	CarTControllo controllo = new CarTControllo();
	controllo.setAltreNorme(nuovoControlloForm.getAltreNorme());
	controllo.setAltriSoggetti(nuovoControlloForm.getSoggettiSupportoSopralluogo());
	controllo.setDataControllo(nuovoControlloForm.getDataControllo());
	controllo.setDataInserimento(new Date());
	controllo.setIdCentroAziendale(idCentroAz);
	controllo.setIdTipoRespAzienda(nuovoControlloForm.getIdQualifica());
	controllo.setIdUtenteInserimento(idUtente);	
	controllo.setMonitoraggioCofinanziato(nuovoControlloForm.getMonitoraggioCofinanziato()==null ? "N" : "S");
	controllo.setOraFineControllo(nuovoControlloForm.getOraFineControllo());
	controllo.setOraInizioControllo(nuovoControlloForm.getOraInizioControllo());
	controllo.setResponsabileAzienda(nuovoControlloForm.getResponsabileAzienda());
	controllo.setIdTipoComunicazione(nuovoControlloForm.getIdTipoComunicazione());
	controllo.setIdStatoComunicazione(nuovoControlloForm.getIdStatoComunicazione());
	
	int idControllo = carTControlloMapper.insert(controllo);
	nuovoControlloForm.setIdControllo(controllo.getIdControllo());

	insertTabelleDiRelazioneNuovoControlloDatiBase(controllo, nuovoControlloForm, idUtente);
	
	// aggiorno la tipologia attività sull'azienda
	logger.debug("Aggiorno la tipologia attivita idSpedizionire= "+nuovoControlloForm.getIdSpedizioniere());
	CarTSpedizioniere sped = carTSpedizioniereMapper.selectByPrimaryKey(nuovoControlloForm.getIdSpedizioniere());
	sped.setTipologiaAttivita(nuovoControlloForm.getTipologiaAttivita());
	sped.setDataUpdate(new Date());
	sped.setIdUtenteUpdate(idUtente);
	carTSpedizioniereMapper.updateByPrimaryKey(sped);
	
}


private void insertTabelleDiRelazioneNuovoControlloDatiBase(CarTControllo controllo,
		NuovoControlloForm nuovoControlloForm, Long idUtente) {
		
	//car_r_controllo_ispettore
		for(String idIspettore : nuovoControlloForm.getIdsIspettore())
		{
			CarRControlloIspettore ispettore = new  CarRControlloIspettore();
			ispettore.setIdControllo(controllo.getIdControllo());
			ispettore.setIdIspettore(new Long(idIspettore));
			ispettore.setIdUtenteInserimento(idUtente);
			ispettore.setDataInserimento(new Date());
			carRControlloIspettoreMapper.insert(ispettore);
		}

		//car_r_controllo_norma_verbale
		for(String idNormaVerbale : nuovoControlloForm.getIdsNormaVerbale())
		{
			CarRControlloNormaVerbale normaVerbale = new  CarRControlloNormaVerbale();
			normaVerbale.setIdControllo(controllo.getIdControllo());
			normaVerbale.setIdNormaVerbale(new Long(idNormaVerbale));
			normaVerbale.setIdUtenteInserimento(idUtente);
			normaVerbale.setDataInserimento(new Date());
			carRControlloNormaVerbaleMapper.insert(normaVerbale);
		}
		
		//car_r_controllo_tipo_semente
		if(nuovoControlloForm.getSementi() != null) {
			for(SementeDTO semente : nuovoControlloForm.getSementi()) {
				if(semente.getIdTipologiaSemente()!=null) {
					CarRControlloTipoSemente nuovoSemente = new CarRControlloTipoSemente();
					nuovoSemente.setIdControllo(controllo.getIdControllo());
					nuovoSemente.setIdTipologiaSemente(semente.getIdTipologiaSemente());			
					nuovoSemente.setQuantita(semente.getQuantita());
					nuovoSemente.setIdUtenteInserimento(idUtente);
					nuovoSemente.setDataInserimento(new Date());
					nuovoSemente.setNote(semente.getNote());
					carRControlloTipoSementeMapper.insert(nuovoSemente);
				}
			}
		}
		/*int i=0;
		for(Long idTipologiaSemente : nuovoControlloForm.getIdsTipologiaSementi()) 
		{
			if (nuovoControlloForm.getQuantitaSementi()[i] != null) { 
				CarRControlloTipoSemente nuovoSemente = new CarRControlloTipoSemente();
				nuovoSemente.setIdControllo(controllo.getIdControllo());
				nuovoSemente.setIdTipologiaSemente(idTipologiaSemente);			
				nuovoSemente.setQuantita(nuovoControlloForm.getQuantitaSementi()[i]);
				nuovoSemente.setIdUtenteInserimento(idUtente);
				nuovoSemente.setDataInserimento(new Date());
				nuovoSemente.setNote(nuovoControlloForm.getNoteAltroSemente());
				carRControlloTipoSementeMapper.insert(nuovoSemente);			
			}
			i++;
		}*/
		
		//car_r_controllo_tipologia
		boolean tabDocumentale = false;
		boolean tabIdentita = false;
		boolean tabFisico = false;	
		for(String idTipologiaControllo : nuovoControlloForm.getIdsTipologiaControllo()){
			CarRControlloTipologia tipologiaControllo = new  CarRControlloTipologia();
			tipologiaControllo.setIdControllo(controllo.getIdControllo());
			tipologiaControllo.setIdTipologiaControllo(new Long(idTipologiaControllo));
			tipologiaControllo.setIdUtenteInserimento(idUtente);
			tipologiaControllo.setDataInserimento(new Date());
			logger.debug("-- idTipologiaControllo ="+idTipologiaControllo);
			logger.debug("--- insert into car_r_controllo_tipologia");
			carRControlloTipologiaMapper.insert(tipologiaControllo);
			
					
			// In  base agli inserimenti che vengono effetuati, setto nel form i campi per visualizzare i tab corretti
			if((Long.parseLong(idTipologiaControllo)) == CaronteConstants.ID_TIPOLOGIA_CONTROLLO_DOCUMENTALE){
				tabDocumentale = true;			
			}
			if((Long.parseLong(idTipologiaControllo)) == CaronteConstants.ID_TIPOLOGIA_CONTROLLO_IDENTITA){
				tabIdentita = true;
			}
			if((Long.parseLong(idTipologiaControllo)) == CaronteConstants.ID_TIPOLOGIA_FISICO){
				tabFisico = true;
			}			
		}
		nuovoControlloForm.setTabDocumentale(tabDocumentale);
		nuovoControlloForm.setTabIdentita(tabIdentita);
		nuovoControlloForm.setTabFisico(tabFisico);
		
		// TODO provvisorio in attesa di aggiungere i nuovi tab mancanti
		if (nuovoControlloForm.getAltroControllo() != null) {			
			CarRControlloTipologia tipologiaControllo = new  CarRControlloTipologia();
			tipologiaControllo.setIdControllo(controllo.getIdControllo());
			tipologiaControllo.setIdTipologiaControllo(4L); // tipologia altro controllo
			tipologiaControllo.setNote(nuovoControlloForm.getAltroControllo());
			tipologiaControllo.setIdUtenteInserimento(idUtente);
			tipologiaControllo.setDataInserimento(new Date());
			carRControlloTipologiaMapper.insert(tipologiaControllo);
		}
		
		//car_r_controllo_materiale (salvo le attivita/materiali per i Sementi)
		for(Long idMateriale : nuovoControlloForm.getIdsMaterialeSementi())
		{
			CarRControlloMateriale materiale = new  CarRControlloMateriale();
			materiale.setIdControllo(controllo.getIdControllo());
			materiale.setIdMateriale(idMateriale);
			materiale.setIdUtenteInserimento(idUtente);
			materiale.setDataInserimento(new Date());
			carRControlloMaterialeMapper.insert(materiale);
		}
	
}


@Override
public int aggiornaDatiAzienda(NuovoControlloForm form, Long idUtente) throws BusinessException {

	logger.debug("---- Aggiorno l'azienda con idSpedizioniere= "+form.getIdSpedizioniere());  
	
	CarTSpedizioniere spedizioniere = new CarTSpedizioniere();
	spedizioniere.setIdSpedizioniere(form.getIdSpedizioniere());
	spedizioniere.setEmail(form.getEmail());
	spedizioniere.setPec(form.getPec());
	spedizioniere.setTelefono(form.getTelefono());
	spedizioniere.setCellulare(form.getCellulare());
	spedizioniere.setIdUtenteUpdate(idUtente);
	spedizioniere.setDataUpdate(new Date());
	spedizioniere.setTipologiaAttivita(form.getTipologiaAttivita());
	spedizioniere.setCodiceFitok(form.getCodiceFitok());
    
	return carTSpedizioniereMapper.updateByPrimaryKeySelective(spedizioniere);		
	
  }


@Override
public ControlloDTO getDettaglioCentroAzByIdCentroAz(Long idCentroAz) throws BusinessException {
	return controlloMapper.getDettaglioCentroAzByIdCentroAz(idCentroAz);
}

public void updateNuovoControllo(Long idCentroAz, NuovoControlloForm nuovoControlloForm, Long idUtente)
		throws BusinessException {

	CarTControllo controllo = new CarTControllo();
	controllo.setIdControllo(nuovoControlloForm.getIdControllo());
	controllo.setAltreNorme(nuovoControlloForm.getAltreNorme());
	controllo.setAltriSoggetti(nuovoControlloForm.getSoggettiSupportoSopralluogo());
	controllo.setDataControllo(nuovoControlloForm.getDataControllo());
	controllo.setDataAggiornamento(new Date());
	controllo.setIdCentroAziendale(idCentroAz);
	controllo.setIdTipoRespAzienda(nuovoControlloForm.getIdQualifica());
	controllo.setIdUtenteAggiornamento(idUtente);	
	controllo.setMonitoraggioCofinanziato(nuovoControlloForm.getMonitoraggioCofinanziato()==null ? "N" : "S");
	controllo.setOraFineControllo(nuovoControlloForm.getOraFineControllo());
	controllo.setOraInizioControllo(nuovoControlloForm.getOraInizioControllo());
	controllo.setResponsabileAzienda(nuovoControlloForm.getResponsabileAzienda());
	controllo.setIdTipoComunicazione(nuovoControlloForm.getIdTipoComunicazione());
	controllo.setIdStatoComunicazione(nuovoControlloForm.getIdStatoComunicazione());
	
	carTControlloMapper.updateByPrimaryKeySelective(controllo);

	deleteTabelleDiRelazioneNuovoControlloDatiBase(controllo.getIdControllo(), nuovoControlloForm);
	insertTabelleDiRelazioneNuovoControlloDatiBase(controllo, nuovoControlloForm, idUtente);
	
	// aggiorno la tipologia attività sull'azienda
	logger.debug("Aggiorno la tipologia attivita idSpedizionire= "+nuovoControlloForm.getIdSpedizioniere());
	CarTSpedizioniere sped = carTSpedizioniereMapper.selectByPrimaryKey(nuovoControlloForm.getIdSpedizioniere());
	sped.setTipologiaAttivita(nuovoControlloForm.getTipologiaAttivita());
	sped.setDataUpdate(new Date());
	sped.setIdUtenteUpdate(idUtente);
	carTSpedizioniereMapper.updateByPrimaryKey(sped);
	
}


private void deleteTabelleDiRelazioneNuovoControlloDatiBase(Long idControllo, NuovoControlloForm form) {
	
	controlloMapper.deleteRControlloTipologiaByIdControllo(idControllo);
	// Reset campi sul form per visualizzazione tab (record presenti in car_d_tipologia_controllo)
	form.setTabDocumentale(false);
	form.setTabIdentita(false);
	form.setTabFisico(false);
	
	logger.debug("xXx idcontrollo= " + idControllo);
	controlloMapper.deleteRControlloTipoSementeByIdControllo(idControllo);
	controlloMapper.deleteRControlloNormaVerbaleByIdControllo(idControllo);
	controlloMapper.deleteRControlloIspettoreByIdControllo(idControllo);
	controlloMapper.deleteRControlloMaterialeByIdControllo(idControllo);
}

@Override
public void inserisciControlloDocumentale(NuovoControlloForm form, Long idUtente) throws BusinessException{
	logger.debug("BEGIN inserisciControlloDocumentale");
	
	CarTControlloDocumentale controlloDoc = new CarTControlloDocumentale();
	controlloDoc.setDataInserimento(new Date());
	controlloDoc.setIdUtenteInserimento(idUtente);
	controlloDoc.setIdControllo(form.getIdControllo());
	
	controlloDoc.setFlControllo3x1(form.getFlControllo3x1());
	controlloDoc.setDescControllo3x1(form.getDescControllo3x1());
	
	controlloDoc.setFlControllo3x1x1(form.getFlControllo3x1x1());
	controlloDoc.setNoteControllo3x1x1(form.getNoteControllo3x1x1());
	
	controlloDoc.setFlControllo3x2(form.getFlControllo3x2());
	
	controlloDoc.setFlControllo3x3(form.getFlControllo3x3());
	
	controlloDoc.setFlControllo3x3x1(form.getFlControllo3x3x1());
	controlloDoc.setNoteControllo3x3x1(form.getNoteControllo3x3x1());
	
	controlloDoc.setFlControllo3x4(form.getFlControllo3x4());
	
	controlloDoc.setFlControllo3x5(form.getFlControllo3x5());
	
	controlloDoc.setFlControllo3x6(form.getFlControllo3x6());
	
	controlloDoc.setFlControllo3x7(form.getFlControllo3x7());
	
	controlloDoc.setFlControllo3x8(form.getFlControllo3x8());
	controlloDoc.setNoteControllo3x8(form.getNoteControllo3x8());
	
	controlloDoc.setFlControllo3x9(form.getFlControllo3x9());
	controlloDoc.setNoteControllo3x9(form.getNoteControllo3x9());
	
	controlloDoc.setFlControllo3x10(form.getFlControllo3x10());
	
	controlloDoc.setFlControllo3x11(form.getFlControllo3x11());
	controlloDoc.setNoteControllo3x11(form.getNoteControllo3x11());
	
	controlloDoc.setFlControllo3x12(form.getFlControllo3x12());
	controlloDoc.setNoteControllo3x12(form.getNoteControllo3x12());
	
	controlloDoc.setFlControllo3x13(form.getFlControllo3x13());
	controlloDoc.setNoteControllo3x13(form.getNoteControllo3x13());
	
	controlloDoc.setFlControllo3x14(form.getFlControllo3x14());
	
	controlloDoc.setFlControllo3x15(form.getFlControllo3x15());
	controlloDoc.setNoteControllo3x15(form.getNoteControllo3x15());
	
	controlloDoc.setFlControllo3x16(form.getFlControllo3x16());

	carTControlloDocMapper.insert(controlloDoc);
	form.setIdControlloDocumentale(controlloDoc.getIdControlloDocumentale());
	logger.debug("-- Inserito id_controllo_documentale ="+controlloDoc.getIdControlloDocumentale());
	
	logger.debug("END inserisciControlloDocumentale");
}

@Override
public void updateControlloDocumentale(NuovoControlloForm form, Long idUtente) throws BusinessException{	
	logger.debug("BEGIN updateControlloDocumentale");
	
	CarTControlloDocumentale controlloDoc = new CarTControlloDocumentale();
	controlloDoc.setDataAggiornamento(new Date());
	controlloDoc.setIdUtenteAggiornamento(idUtente);
	controlloDoc.setIdControllo(form.getIdControllo());	
	controlloDoc.setIdControlloDocumentale(form.getIdControlloDocumentale());
	
	controlloDoc.setFlControllo3x1(form.getFlControllo3x1());
	controlloDoc.setDescControllo3x1(form.getDescControllo3x1());
	
	controlloDoc.setFlControllo3x1x1(form.getFlControllo3x1x1());
	controlloDoc.setNoteControllo3x1x1(form.getNoteControllo3x1x1());
	
	controlloDoc.setFlControllo3x2(form.getFlControllo3x2());
	
	controlloDoc.setFlControllo3x3(form.getFlControllo3x3());
	
	controlloDoc.setFlControllo3x3x1(form.getFlControllo3x3x1());
	controlloDoc.setNoteControllo3x3x1(form.getNoteControllo3x3x1());
	
	controlloDoc.setFlControllo3x4(form.getFlControllo3x4());
	
	controlloDoc.setFlControllo3x5(form.getFlControllo3x5());	
	
	controlloDoc.setFlControllo3x6(form.getFlControllo3x6());
	
	controlloDoc.setFlControllo3x7(form.getFlControllo3x7());
	
	controlloDoc.setFlControllo3x8(form.getFlControllo3x8());
	controlloDoc.setNoteControllo3x8(form.getNoteControllo3x8());
	
	controlloDoc.setFlControllo3x9(form.getFlControllo3x9());
	controlloDoc.setNoteControllo3x9(form.getNoteControllo3x9());
	
	controlloDoc.setFlControllo3x10(form.getFlControllo3x10());
	
	controlloDoc.setFlControllo3x11(form.getFlControllo3x11());
	controlloDoc.setNoteControllo3x11(form.getNoteControllo3x11());
	
	controlloDoc.setFlControllo3x12(form.getFlControllo3x12());
	controlloDoc.setNoteControllo3x12(form.getNoteControllo3x12());
	
	controlloDoc.setFlControllo3x13(form.getFlControllo3x13());
	controlloDoc.setNoteControllo3x13(form.getNoteControllo3x13());
	
	controlloDoc.setFlControllo3x14(form.getFlControllo3x14());
	
	controlloDoc.setFlControllo3x15(form.getFlControllo3x15());
	controlloDoc.setNoteControllo3x15(form.getNoteControllo3x15());
	
	controlloDoc.setFlControllo3x16(form.getFlControllo3x16());

	carTControlloDocMapper.updateByPrimaryKeySelective(controlloDoc);
	form.setIdControlloDocumentale(controlloDoc.getIdControlloDocumentale());
	logger.debug("-- Aggiornato id_controllo_documentale ="+controlloDoc.getIdControlloDocumentale());
	
	logger.debug("END updateControlloDocumentale");

	
}


@Override
public int aggiornaDatiCentroAziendale(NuovoControlloForm form, Long idUtente) throws BusinessException {

	logger.debug("---- Aggiorno il centro aziendale con IdCentroAziendale= "+form.getIdCentroAziendale());  
	
	CarTCentroAziendale centroAziendale = carTCentroAziendaleMapper.selectByPrimaryKey(form.getIdCentroAziendale());
	centroAziendale.setIdCentroAziendale(form.getIdCentroAziendale());
	centroAziendale.setEmail(form.getEmailCa());
	centroAziendale.setPec(form.getPecCa());
	centroAziendale.setTelefono(form.getTelefonoCa());
	centroAziendale.setCellulare(form.getCellulareCa());
	centroAziendale.setIdUtenteUpdate(idUtente);
	centroAziendale.setDataUpdate(new Date());
	centroAziendale.setIdIspettore(form.getIdIspettore());
	centroAziendale.setIdTipologiaPassaporto(form.getIdTipologiaPassaporto());
	centroAziendale.setTariffa(form.getTariffa());
    
	return carTCentroAziendaleMapper.updateByPrimaryKey(centroAziendale);		
}
	
	@Override
	public void inserisciNuovoControlloFisico(NuovoControlloForm nuovoControlloForm, Long idUtente)
			throws BusinessException {

		CarTControlloFisico controlloFisico = new CarTControlloFisico();
		controlloFisico.setIdControllo(nuovoControlloForm.getIdControllo());
		controlloFisico.setFlControllo5x1(nuovoControlloForm.getFlControllo5x1());
		controlloFisico.setFlControllo5x2(nuovoControlloForm.getFlControllo5x2());

		controlloFisico.setFlControllo5x4(nuovoControlloForm.getFlControllo5x4());
		controlloFisico.setFlControllo5x5(nuovoControlloForm.getFlControllo5x5());
		controlloFisico.setFlControllo5x6(nuovoControlloForm.getFlControllo5x6());
		controlloFisico.setFlControllo5x7(nuovoControlloForm.getFlControllo5x7());
		controlloFisico.setFlControllo5x8(nuovoControlloForm.getFlControllo5x8());
		controlloFisico.setFlControllo5x9(nuovoControlloForm.getFlControllo5x9());
		controlloFisico.setFlControllo5x10(nuovoControlloForm.getFlControllo5x10());
		
		if(nuovoControlloForm.getOrgNoc().length > 0){
			controlloFisico.setFlControllo5x11("S");
		}else{
			controlloFisico.setFlControllo5x11(nuovoControlloForm.getFlControllo5x11());
		}
		
		controlloFisico.setFlControllo5x12(nuovoControlloForm.getFlControllo5x12());
		controlloFisico.setFlControllo5x13(nuovoControlloForm.getFlControllo5x13());
		controlloFisico.setFlControllo5x14(nuovoControlloForm.getFlControllo5x14());
		controlloFisico.setFlControllo5x15(nuovoControlloForm.getFlControllo5x15());
		controlloFisico.setFlControllo5x16(nuovoControlloForm.getFlControllo5x16());
		controlloFisico.setFlControllo5x17(nuovoControlloForm.getFlControllo5x17());
		controlloFisico.setFlControllo5x18(nuovoControlloForm.getFlControllo5x18());
		controlloFisico.setFlControllo5x19(nuovoControlloForm.getFlControllo5x19());
		controlloFisico.setFlControllo5x20(nuovoControlloForm.getFlControllo5x20());
		controlloFisico.setFlControllo5x21(nuovoControlloForm.getFlControllo5x21());
		controlloFisico.setFlControllo5x22(nuovoControlloForm.getFlControllo5x22());
		controlloFisico.setFlControllo5x23(nuovoControlloForm.getFlControllo5x23());
		
		controlloFisico.setNoteControllo5x11(nuovoControlloForm.getNoteControllo5x11());
		controlloFisico.setNoteControllo5x22(nuovoControlloForm.getNoteControllo5x22());
		controlloFisico.setIdUtenteInserimento(idUtente);
		controlloFisico.setDataInserimento(new Date());

		controlloFisicoMapper.insert(controlloFisico);
		
		nuovoControlloForm.setIdControlloFisico(controlloFisico.getIdControlloFisico());
		insertTabelleDiRelazioneNuovoControlloFisico(controlloFisico, nuovoControlloForm, idUtente);
	}
	
	
	@Override
	public void updateNuovoControlloFisico(NuovoControlloForm nuovoControlloForm, Long idUtente)
			throws BusinessException {
		logger.debug("BEGIN updateNuovoControlloFisico");
		
		CarTControlloFisico controlloFisico = new CarTControlloFisico();
		controlloFisico.setIdControlloFisico(nuovoControlloForm.getIdControlloFisico());
		controlloFisico.setIdControllo(nuovoControlloForm.getIdControllo());
		controlloFisico.setFlControllo5x1(nuovoControlloForm.getFlControllo5x1());
		controlloFisico.setFlControllo5x2(nuovoControlloForm.getFlControllo5x2());

		controlloFisico.setFlControllo5x4(nuovoControlloForm.getFlControllo5x4());
		controlloFisico.setFlControllo5x5(nuovoControlloForm.getFlControllo5x5());
		controlloFisico.setFlControllo5x6(nuovoControlloForm.getFlControllo5x6());
		controlloFisico.setFlControllo5x7(nuovoControlloForm.getFlControllo5x7());
		controlloFisico.setFlControllo5x8(nuovoControlloForm.getFlControllo5x8());
		controlloFisico.setFlControllo5x9(nuovoControlloForm.getFlControllo5x9());
		controlloFisico.setFlControllo5x10(nuovoControlloForm.getFlControllo5x10());
		
		if(nuovoControlloForm.getOrgNoc().length > 0){
			controlloFisico.setFlControllo5x11("S");
		}else{
			controlloFisico.setFlControllo5x11(nuovoControlloForm.getFlControllo5x11());
		}
		
		controlloFisico.setFlControllo5x12(nuovoControlloForm.getFlControllo5x12());
		controlloFisico.setFlControllo5x13(nuovoControlloForm.getFlControllo5x13());
		controlloFisico.setFlControllo5x14(nuovoControlloForm.getFlControllo5x14());
		controlloFisico.setFlControllo5x15(nuovoControlloForm.getFlControllo5x15());
		controlloFisico.setFlControllo5x16(nuovoControlloForm.getFlControllo5x16());
		controlloFisico.setFlControllo5x17(nuovoControlloForm.getFlControllo5x17());
		controlloFisico.setFlControllo5x18(nuovoControlloForm.getFlControllo5x18());
		controlloFisico.setFlControllo5x19(nuovoControlloForm.getFlControllo5x19());
		controlloFisico.setFlControllo5x20(nuovoControlloForm.getFlControllo5x20());
		controlloFisico.setFlControllo5x21(nuovoControlloForm.getFlControllo5x21());
		controlloFisico.setFlControllo5x22(nuovoControlloForm.getFlControllo5x22());
		controlloFisico.setFlControllo5x23(nuovoControlloForm.getFlControllo5x23());
		
		controlloFisico.setNoteControllo5x11(nuovoControlloForm.getNoteControllo5x11());
		controlloFisico.setNoteControllo5x22(nuovoControlloForm.getNoteControllo5x22());
		controlloFisico.setIdUtenteAggiornamento(idUtente);
		controlloFisico.setDataAggiornamento(new Date());

		controlloFisicoMapper.updateByPrimaryKeySelective(controlloFisico);

		deleteTabelleDiRelazioneNuovoControlloFisico(controlloFisico.getIdControlloFisico());
		insertTabelleDiRelazioneNuovoControlloFisico(controlloFisico, nuovoControlloForm, idUtente);
		
		updateControlloFisicoSpecie(nuovoControlloForm, idUtente);

		logger.debug("END updateNuovoControlloFisico");
	}
	
	private void updateControlloFisicoSpecie(NuovoControlloForm form, Long idUtente) throws BusinessException{
		logger.debug("BEGIN updateControlloFisicoSpecie");
		
		List<GenereSpecieDTO> listaGenereSpecie = form.getGenereSpecie();
		String[] numPinateArr = form.getNumeroPiante();
		String[] numPianteSintomaticheArr = form.getNumPianteSintomatiche();
    	if(listaGenereSpecie != null && listaGenereSpecie.size()>0){
    		int i =0;
    		logger.debug("--- Ci sono record in car_r_controllo_fisico_specie sui quali fare update");
    		for (Iterator<GenereSpecieDTO> iterator = listaGenereSpecie.iterator(); iterator.hasNext();) {
				GenereSpecieDTO genereSpecieDTO = (GenereSpecieDTO) iterator.next();
				Long idControlloFisicoSpecie = genereSpecieDTO.getIdControlloFisicoSpecie();
				logger.debug("--- idControlloFisicoSpecie ="+idControlloFisicoSpecie);
				
				CarRControlloFisicoSpecie record = getControlloFisicoSpecieByIdControlloFisicoSpecie(idControlloFisicoSpecie);
				
				if(record != null){
					// Update su numero piante e numero piante asintomatiche
					Long numPiante = null;
					if(numPinateArr[i] != null && !numPinateArr[i].equals("")){
					  numPiante = Long.valueOf(numPinateArr[i]);
					}
					logger.debug("--numPiante = "+numPiante);
					
					Long numPianteSintomatiche = null;
					if(numPianteSintomaticheArr[i] != null && !numPianteSintomaticheArr[i].equals("")){
						numPianteSintomatiche = Long.valueOf(numPianteSintomaticheArr[i]);
					}
					logger.debug("--numPianteSintomatiche = "+numPianteSintomatiche);
					
					i++;

					record.setDataAggiornamento(new Date());
					record.setIdUtenteAggiornamento(idUtente);
					record.setNumPiante(numPiante);
					record.setNumPianteSintomatiche(numPianteSintomatiche);
									
					logger.debug("-- Effettuo UPDATE car_r_controllo_fisico_specie");
					controlloFisicoSpecieMapper.updateByPrimaryKey(record);
			   }
    		}
    	}
    	logger.debug("BEGIN updateControlloFisicoSpecie");    	
	}
	
	@Override
	public void inserisciElencoSitiProduzioneRuopControlloIdentita(NuovoControlloForm nuovoControlloForm, Long idUtente, List<SitoProduzioneDTO> sitiProdList) throws BusinessException{
		logger.debug("BEGIN inserisciElencoSitiProduzioneRuopControlloIdentita");
		
		if(nuovoControlloForm.getIdControlloIdentita() == null){
		  logger.debug("-- Inserire il record in CAR_T_CONTROLLO_IDENTITA");
		  CarTControlloIdentita record = new CarTControlloIdentita();
		  record.setDataInserimento(new Date());
		  record.setIdUtenteInserimento(idUtente);
		  record.setIdControllo(nuovoControlloForm.getIdControllo());
		  carTControlloIdentitaMapper.insert(record);	
		  logger.debug("-- IdControlloIdentita inserito ="+record.getIdControlloIdentita());
		  nuovoControlloForm.setIdControlloIdentita(record.getIdControlloIdentita());
		}
		for (Iterator<SitoProduzioneDTO> iterator = sitiProdList.iterator(); iterator.hasNext();) {
			SitoProduzioneDTO sitoProduzioneDTO = (SitoProduzioneDTO) iterator.next();
			// Recupero l'idSitoProduzione da inserire
			Long idSitoProduzione = sitoProduzioneDTO.getIdSitoProduzione();
			logger.debug("-- idSitoProduzione Ruop ="+idSitoProduzione);
			
			// Inserisco il record in car_r_controllo_identita_sito
			logger.debug("-- Inserisco il record in car_r_controllo_identita_sito");
			CarRControlloIdentitaSito controidsito = new CarRControlloIdentitaSito();
			controidsito.setIdSitoProduzione(idSitoProduzione);
			controidsito.setIdControlloIdentita(nuovoControlloForm.getIdControlloIdentita());
			controidsito.setIdUtenteInserimento(idUtente);
			controidsito.setDataInserimento(new Date());
			carRControlloIdentitaSitoMapper.insert(controidsito);
		}
		
		
		logger.debug("BEGIN inserisciElencoSitiProduzioneRuopControlloIdentita");
	}
	
	@Override
	public List<SitoProduzioneDTO> getSitiProduzioneByIdControlloIdentita(Long idControlloIdentita) throws BusinessException{		
		List<SitoProduzioneDTO> sitProdList = controlloMapper.getSitiProduzioneByIdControlloIdentita(idControlloIdentita);		

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
	public void inserisciSitoProduzioneControlloIdentita(NuovoControlloForm form, Long idUtente) throws BusinessException{
		logger.debug("BEGIN inserisciSitoProduzioneControlloIdentita");
		
		if(form.getIdControlloIdentita() == null){
			  logger.debug("-- Inserire il record in CAR_T_CONTROLLO_IDENTITA");
			  CarTControlloIdentita record = new CarTControlloIdentita();
			  record.setDataInserimento(new Date());
			  record.setIdUtenteInserimento(idUtente);
			  record.setIdControllo(form.getIdControllo());
			  carTControlloIdentitaMapper.insert(record);	
			  logger.debug("-- IdControlloIdentita inserito ="+record.getIdControlloIdentita());
			  form.setIdControlloIdentita(record.getIdControlloIdentita());
		}
		
		// Inserisco il record in car_t_sito_produzione
		logger.debug("-- Inserisco il record in car_t_sito_produzione");
		CarTSitoProduzione sitoProd = setDatiSitoProduzione(form, idUtente);
		carTSitoProdMapper.insert(sitoProd);
		Long idSitoProd = sitoProd.getIdSitoProduzione();
		logger.debug("-- idSitoProd inserito ="+idSitoProd);
		
		// Inserisco il record in car_r_controllo_identita_sito
		logger.debug("-- Inserisco il record in car_r_controllo_identita_sito");
		CarRControlloIdentitaSito record = new CarRControlloIdentitaSito();
		record.setDataInserimento(new Date());
		record.setIdUtenteInserimento(idUtente);
		record.setIdControlloIdentita(form.getIdControlloIdentita());
		record.setIdSitoProduzione(idSitoProd);
		carRControlloIdSitoMapper.insert(record);
				
		logger.debug("END inserisciSitoProduzioneControlloIdentita");
	}
	
	private CarTSitoProduzione setDatiSitoProduzione(NuovoControlloForm form, Long idUtente){
		  CarTSitoProduzione sitoProd = new CarTSitoProduzione();
		  sitoProd.setDataInsert(new Date());
		  sitoProd.setDenominazione(form.getDescSitoProduzione());
		  sitoProd.setIdUtenteInsert(idUtente);
		  
		  logger.debug("-- foglio ="+form.getFoglio());
		  sitoProd.setFoglio(Long.valueOf(form.getFoglio()).longValue());
		  logger.debug("-- *** idCentroAziendale ="+form.getIdCentroAziendale());
		  sitoProd.setIdCentroAziendale(form.getIdCentroAziendale());
		  sitoProd.setIdComune(form.getComuneSitoProd());
		  sitoProd.setIdUtenteInsert(idUtente);
		  
		  logger.debug("-- mappale ="+form.getMappale());
		  sitoProd.setMappale(Long.valueOf(form.getMappale()).longValue());
		 
		  String superficie = form.getSuperficie();
		  logger.debug("-- superficie ="+superficie);
		  sitoProd.setSuperficie(CaronteUtils.parseBigDecimal(form.getSuperficie()));	  
		  
		  sitoProd.setUbicazione(form.getUbicazione());
		  return sitoProd;
	  }

	
	@Inject
	CarRControlloFisicoStrAttMapper controlloFisicoStrAttMapper;
	@Inject
	CarRControlloFisicoMetProMapper controlloFisicoMetProMapper;
	@Inject
	CarRControlloFisicoIrrigaMapper controlloFisicoIrrigaMapper;
	@Inject
	CarRControlloFisicoConProfMapper controlloFisicoConProfMapper;
	@Inject
	CarRControlloFisicoReqProfMapper controlloFisicoReqProfMapper;
	@Inject
	CarRControlloFisicoSpecieMapper controlloFisicoSpecieMapper;
	
	
	private void deleteTabelleDiRelazioneNuovoControlloFisico(Long idControlloFisico) {
		logger.debug("BEGIN deleteTabelleDiRelazioneNuovoControlloFisico");
		
		logger.debug("--- idControlloFisico ="+idControlloFisico);
		
		CarRControlloFisicoStrAttExample ex = new CarRControlloFisicoStrAttExample();
		ex.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		controlloFisicoStrAttMapper.deleteByExample(ex);
		
		CarRControlloFisicoMetProExample ex1 = new CarRControlloFisicoMetProExample();
		ex1.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		controlloFisicoMetProMapper.deleteByExample(ex1);
		
		CarRControlloFisicoIrrigaExample ex2 = new CarRControlloFisicoIrrigaExample();
		ex2.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		controlloFisicoIrrigaMapper.deleteByExample(ex2);
		
		CarRControlloFisicoConProfExample ex3 = new CarRControlloFisicoConProfExample();
		ex3.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		controlloFisicoConProfMapper.deleteByExample(ex3);
		
		CarRControlloFisicoReqProfExample ex4 = new CarRControlloFisicoReqProfExample();
		ex4.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		controlloFisicoReqProfMapper.deleteByExample(ex4);
				
		/*logger.debug("--- delete car_r_controllo_fisico_specie");
		CarRControlloFisicoSpecieExample ex5 = new CarRControlloFisicoSpecieExample();
		ex5.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		controlloFisicoSpecieMapper.deleteByExample(ex5);*/
		
		CarRControlloFisicoOrgNocExample ex6 = new CarRControlloFisicoOrgNocExample();
		ex6.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		controlloFisicoOrgNocMapper.deleteByExample(ex6);
		
		logger.debug("END deleteTabelleDiRelazioneNuovoControlloFisico");
	}
	
	private void insertTabelleDiRelazioneNuovoControlloFisico(CarTControlloFisico controlloFisico, NuovoControlloForm nuovoControlloForm, Long idUtente) {
		logger.debug("BEGIN insertTabelleDiRelazioneNuovoControlloFisico");
		
			logger.debug("--- idControlloFisico ="+controlloFisico.getIdControlloFisico());
		
			for(String id : nuovoControlloForm.getIdsStrutturaAttrezzatura())
			{
				CarRControlloFisicoStrAtt ogg = new  CarRControlloFisicoStrAtt();
				ogg.setIdControlloFisico(controlloFisico.getIdControlloFisico());
				ogg.setIdStrutturaAttrezzatura(new Long(id));
				ogg.setIdUtenteInserimento(idUtente);
				ogg.setDataInserimento(new Date());
				controlloFisicoStrAttMapper.insert(ogg);
			}
			
			for(String id : nuovoControlloForm.getIdsMetodiProduzione())
			{
				CarRControlloFisicoMetPro ogg = new  CarRControlloFisicoMetPro();
				ogg.setIdControlloFisico(controlloFisico.getIdControlloFisico());
				ogg.setIdMetodoProduzione(new Long(id));
				ogg.setIdUtenteInserimento(idUtente);
				ogg.setDataInserimento(new Date());
				controlloFisicoMetProMapper.insert(ogg);
			}
			
			
			for(String id : nuovoControlloForm.getIdsTipiIrrigazione())
			{
				CarRControlloFisicoIrriga ogg = new  CarRControlloFisicoIrriga();
				ogg.setIdControlloFisico(controlloFisico.getIdControlloFisico());
				ogg.setIdTipoIrrigazione(new Long(id));
				ogg.setIdUtenteInserimento(idUtente);
				ogg.setDataInserimento(new Date());
				controlloFisicoIrrigaMapper.insert(ogg);
			}
			
			if(nuovoControlloForm.getIdsConoscenzeProfessionali()!=null && nuovoControlloForm.getIdsConoscenzeProfessionali().length>0)
			for(String id : nuovoControlloForm.getIdsConoscenzeProfessionali())
			{
				CarRControlloFisicoConProf ogg = new  CarRControlloFisicoConProf();
				ogg.setIdControlloFisico(controlloFisico.getIdControlloFisico());
				ogg.setIdConoscenzaProfessionale(new Long(id));
				ogg.setIdUtenteInserimento(idUtente);
				ogg.setDataInserimento(new Date());
				controlloFisicoConProfMapper.insert(ogg);
			}
			
			if(nuovoControlloForm.getIdsRequisitiProfessionali()!=null && nuovoControlloForm.getIdsRequisitiProfessionali().length>0)
			for(String id : nuovoControlloForm.getIdsRequisitiProfessionali())
			{
				CarRControlloFisicoReqProf ogg = new  CarRControlloFisicoReqProf();
				ogg.setIdControlloFisico(controlloFisico.getIdControlloFisico());
				ogg.setIdRequisitoProfessionale(new Long(id));
				ogg.setIdUtenteInserimento(idUtente);
				ogg.setDataInserimento(new Date());
				if(ogg.getIdRequisitoProfessionale()==1l)
				{
					ogg.setDescLaurea(nuovoControlloForm.getLaurea());
					ogg.setDescDiploma(nuovoControlloForm.getDiploma());
				}

				controlloFisicoReqProfMapper.insert(ogg);
			}
						
			if(nuovoControlloForm.getOrgNoc().length>0){
				for(Long idOrgNoc : nuovoControlloForm.getOrgNoc())
				{
					
					CarRControlloFisicoOrgNoc contrFisOrgNoc = new CarRControlloFisicoOrgNoc();
					contrFisOrgNoc.setIdControlloFisico(controlloFisico.getIdControlloFisico());
					contrFisOrgNoc.setIdOrgNocivo(idOrgNoc);
					contrFisOrgNoc.setIdUtenteInserimento(idUtente);
					contrFisOrgNoc.setDataInserimento(new Date());
					controlloFisicoOrgNocMapper.insert(contrFisOrgNoc);
				}
				
			}
			
			
		logger.debug("BEGIN insertTabelleDiRelazioneNuovoControlloFisico");	
	}
	
	@Override
	public Long getMaxIdDomandaValidaByIdCentroAz(Long idCentroAz) throws BusinessException{
		return controlloMapper.getMaxIdDomandaValidaByIdCentroAz(idCentroAz);
	}
	
	@Override
	public CarTResponsabilePassaporto getResponsabileFitosanitarioByIdDomanda(Long idDomanda) throws BusinessException{
		return controlloMapper.getResponsabileFitosanitarioByIdDomanda(idDomanda);
	}
	
	@Override
	public void eliminaSitoProduzione(Long idSitoProduzione, Long idControlloIdentita, Long idUtente) throws BusinessException{
		logger.debug("-- idSitoProduzione da ELIMINARE ="+idSitoProduzione);
		if(null != idSitoProduzione){
			carRControlloIdSitoMapper.deleteByPrimaryKey(idControlloIdentita, idSitoProduzione);

			// Aggiornamento controllo identità
			logger.debug("-- Aggiornamento controllo identita con idControlloIdentita ="+idControlloIdentita);
			CarTControlloIdentita controlloId = new CarTControlloIdentita();
			controlloId.setIdUtenteAggiornamento(idUtente);
			controlloId.setDataAggiornamento(new Date());
			controlloId.setIdControlloIdentita(idControlloIdentita);
			carTControlloIdentitaMapper.updateByPrimaryKeySelective(controlloId);	    	    	    	    
		}
	}
	
	@Override
	public void inserisciControlloIdentita(NuovoControlloForm form, Long idUtente) throws BusinessException{
		logger.debug("BEGIN inserisciControlloDocumentale");
		
		CarTControlloIdentita controlloIdentita = new CarTControlloIdentita();
		controlloIdentita.setDataInserimento(new Date());
		controlloIdentita.setIdUtenteInserimento(idUtente);
		controlloIdentita.setIdControllo(form.getIdControllo());
		
		controlloIdentita.setFlControllo4x1(form.getFlControllo4x1());
		controlloIdentita.setFlControllo4x2(form.getFlControllo4x2());				

		carTControlloIdentitaMapper.insert(controlloIdentita);
		form.setIdControlloIdentita(controlloIdentita.getIdControlloIdentita());
		logger.debug("-- Inserito id_controllo_identita ="+controlloIdentita.getIdControlloIdentita());
		
		logger.debug("END inserisciControlloDocumentale");
	}
	
	@Override
	public void updateControlloIdentita(NuovoControlloForm form, Long idUtente) throws BusinessException{
		logger.debug("BEGIN updateControlloIdentita");
		
		CarTControlloIdentita controlloIdentita = new CarTControlloIdentita();
		controlloIdentita.setDataAggiornamento(new Date());
		controlloIdentita.setIdUtenteAggiornamento(idUtente);
		controlloIdentita.setIdControllo(form.getIdControllo());	
		controlloIdentita.setIdControlloIdentita(form.getIdControlloIdentita());
		
		controlloIdentita.setFlControllo4x1(form.getFlControllo4x1());
		controlloIdentita.setFlControllo4x2(form.getFlControllo4x2());
				
		carTControlloIdentitaMapper.updateByPrimaryKeySelective(controlloIdentita);
		form.setIdControlloIdentita(controlloIdentita.getIdControlloIdentita());
		logger.debug("-- Aggiornato id_controllo_identita ="+controlloIdentita.getIdControlloIdentita());
		
		logger.debug("END updateControlloIdentita");
	}
	@Override
	public byte[] getTemplateTipoStampaControlli(Long idControllo) throws BusinessException {

	  logger.debug("-- getTemplateTipoStampa idControllo = " + idControllo);
	  CarTControlloExample exampleComunicazione = new CarTControlloExample();
	  exampleComunicazione.createCriteria().andIdControlloEqualTo(idControllo);
	  List<CarTControllo> listaControlli = carTControlloMapper.selectByExample(exampleComunicazione);

	  if (null != listaControlli) {
	    // mi recupero il tipo di comunicazione dalla comunicazione
	    Long idTipoComunicazione = listaControlli.get(0).getIdTipoComunicazione();
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
	public byte[] getTemplateTipoStampaControlloById(Long idTipoStampa) throws BusinessException {

	  logger.debug("-- getTemplateTipoStampaControllo idTipoStampa = " + idTipoStampa);
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
	public List<AllegatoDTO> getListaAllegatiControllo(Long idControllo) throws BusinessException {
		return controlloMapper.getListaAllegatiControllo(idControllo);
	}
	
	 @Override
	  public void aggiornaDatiAllegati(Long idControllo, List<AllegatoDTO> listaAllegati, Long idUtente) throws BusinessException {
		logger.debug("BEGIN aggiornaDatiAllegati");  
	   
	    aggiornaAllegati(idControllo, listaAllegati, idUtente);
	    
	    
	    // Aggiorno i dati del controllo
	    CarTControllo controllo = new CarTControllo();
		logger.debug("-- idControllo aggiornato = " + idControllo);
		controllo.setIdControllo(idControllo);	
		controllo.setIdUtenteAggiornamento(idUtente);
		controllo.setDataAggiornamento(new Date());
		
		carTControlloMapper.updateByPrimaryKeySelective(controllo);
	  }

	 private void aggiornaAllegati(Long idControllo, List<AllegatoDTO> listaAllegati, Long idUtente) throws BusinessException {
		  /*
		   * Si ottiene l'elenco aggiornato degli allegati
		   */
		  logger.debug("-- Ricerca dell'elenco aggiornato degli allegati");
		  List<AllegatoDTO> listaAllegatiDB = getListaAllegatiControllo(idControllo);
		  
		  for (AllegatoDTO allegatoDB : listaAllegatiDB) {
			  Iterator<AllegatoDTO> iterAllegati = listaAllegati.iterator();

			  while (iterAllegati.hasNext()) {
				  AllegatoDTO allegato = iterAllegati.next();
				  if (allegato.getIdTipoAllegato().equals(allegatoDB.getIdTipoAllegato())) {
					  Iterator<CarTAllegatoControllo> iterAllegato = allegato.getListaAllegatiControllo().iterator();
					  while (iterAllegato.hasNext()) {
						  CarTAllegatoControllo allegatoInner = iterAllegato.next();
						  if (allegatoInner.getIdAllegatoControllo() == null) {
							  /*
							   * INSERIMENTO
							   */
							  allegatoInner.setIdTipoAllegato(allegato.getIdTipoAllegato());
							  allegatoInner.setIdControllo(idControllo);
							  allegatoInner.setIdUtenteInserimento(idUtente);
							  allegatoInner.setDataInserimento(new Date());
							  // allegatoInner.setDescAllegato(allegato.getDescAllegato());

							  allegatoControlloMapper.insert(allegatoInner);
							  logger.debug("-- Inserito allegato con idAllegatoControllo ="+allegatoInner.getIdAllegatoControllo());
						  } 
						  else {
							  Iterator<CarTAllegatoControllo> iterAllegatoDB = allegatoDB.getListaAllegatiControllo().iterator();
							  while (iterAllegatoDB.hasNext()) {
								  CarTAllegatoControllo allegatoInnerDB = iterAllegatoDB.next();
								  if (allegatoInnerDB.getIdAllegatoControllo().equals(allegatoInner.getIdAllegatoControllo())) {

									  /*
									   * AGGIORNAMENTO
									   */
									  logger.debug("-- Aggiornamento allegato con idAllegatoControllo ="+allegatoInnerDB.getIdAllegatoControllo());
									  allegatoInnerDB = allegatoControlloMapper.selectByPrimaryKey(allegatoInnerDB.getIdAllegatoControllo());

									  if (!StringUtils.isEmpty(allegatoInner.getNomeFile())) {
										  allegatoInnerDB.setNomeFile(allegatoInner.getNomeFile());
										  allegatoInnerDB.setAllegato(allegatoInner.getAllegato());
									  }

									  allegatoInnerDB.setDescAllegato(allegatoInner.getDescAllegato());

									  allegatoControlloMapper.updateByPrimaryKey(allegatoInnerDB);

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
					  for (CarTAllegatoControllo allegatoDaRimuovere : allegatoDB.getListaAllegatiControllo()) {
						  /*
						   * ELIMINAZIONE
						   */
						  logger.debug("-- Eliminazione allegato con idAllegatoControllo ="+allegatoDaRimuovere.getIdAllegatoControllo());
						  allegatoControlloMapper.deleteByPrimaryKey(allegatoDaRimuovere.getIdAllegatoControllo());
					  }

					  iterAllegati.remove();
					  break;
				  }
			  }
		  }
	  }
	  
	  @Override
	  public CarTAllegatoControllo getAllegatoControlloById(Long idAllegatoControllo) throws BusinessException{
		  return allegatoControlloMapper.selectByPrimaryKey(idAllegatoControllo);
	  }


	@Override
	public List<ControlloDTO> getControlliByIdCentroAziendale(Long idCentroAziendale) throws BusinessException {
		return controlloMapper.getControlliByIdCentroAziendale(idCentroAziendale);
	}
	
	@Override
	public void inserisciEsitoControllo(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException{
		logger.debug("BEGIN inserisciEsitoControllo");
		
		CarTEsito esito = new CarTEsito();
		esito.setDataInserimento(new Date());
		esito.setIdUtenteInserimento(idUtente);
		esito.setIdControllo(nuovoControlloForm.getIdControllo());
		esito.setCognomeResp(nuovoControlloForm.getCognomeResp());
		esito.setNomeResp(nuovoControlloForm.getNomeResp());
		esito.setNoteDichiarazione(nuovoControlloForm.getNoteDichiarazione());
		esito.setNote(nuovoControlloForm.getNote());
		esito.setNoteIrregolarita(nuovoControlloForm.getNoteIrregolarita());
		esito.setFlEsito(nuovoControlloForm.getFlEsito());
		esito.setFlEsitoPassaporto(nuovoControlloForm.getFlEsitoPassaporto());
		esito.setFlIrregolarita(nuovoControlloForm.getFlIrregolarita());
		esito.setDataChiusuraVerbale(nuovoControlloForm.getDataChiusuraVerbale());
		esito.setEmailInvioVerbale(nuovoControlloForm.getEmailInvioVerbale());
		// nuovi campi per id_versione = 2
		esito.setNoteEsitoControllo(nuovoControlloForm.getNoteEsitoControllo());
		esito.setFlMisuraUfficiale(nuovoControlloForm.getFlMisuraUfficiale());
		esito.setNumMisuraUfficiale(nuovoControlloForm.getNumMisuraUfficiale());
		esito.setFlMotivoMisuraUfficiale(nuovoControlloForm.getFlMotivoMisuraUfficiale());
		esito.setNoteMotivoMisuraUfficiale(nuovoControlloForm.getNoteMotivoMisuraUfficiale());
		esito.setFlSanzioneAmministrativaEmessa(nuovoControlloForm.getFlSanzioneAmministrativaEmessa());
		esito.setNoteSanzioneAmministrativaEmessa(nuovoControlloForm.getNoteSanzioneAmministrativaEmessa());
		esito.setFlSanzioneAmministrativaProposta(nuovoControlloForm.getFlSanzioneAmministrativaProposta());
		esito.setNoteSanzioneAmministrativaProposta(nuovoControlloForm.getNoteSanzioneAmministrativaProposta());
		esito.setFlPrescrizioni(nuovoControlloForm.getFlPrescrizioni());
		esito.setNotePrescrizioni(nuovoControlloForm.getNotePrescrizioni());
		
		
		logger.debug("--Insert car_t_esito");
		esitoMapper.insert(esito);
		nuovoControlloForm.setIdEsito(esito.getIdEsito());
		
		// update su car_t_controllo per il campo check misura uffciale
		logger.debug("-- Update su car_t_controllo per il campo check misura uffciale");
		CarTControllo controllo = getControllo(nuovoControlloForm.getIdControllo());
		controllo.setMisuraUfficiale(nuovoControlloForm.getMisuraUfficiale()==null ? "N" : "S");
		carTControlloMapper.updateByPrimaryKeySelective(controllo);
				
		logger.debug("END inserisciEsitoControllo");	
	}
	


	@Override
	public void updateEsitoControllo(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException{
		logger.debug("BEGIN updateEsitoControllo");
		
		CarTEsito esito = esitoMapper.selectByPrimaryKey(nuovoControlloForm.getIdEsito());
		
		esito.setIdEsito(nuovoControlloForm.getIdEsito());
		esito.setDataAggiornamento(new Date());
		esito.setIdUtenteAggiornamento(idUtente);
		esito.setIdControllo(nuovoControlloForm.getIdControllo());
		esito.setCognomeResp(nuovoControlloForm.getCognomeResp());
		esito.setNomeResp(nuovoControlloForm.getNomeResp());
		esito.setNoteDichiarazione(nuovoControlloForm.getNoteDichiarazione());
		esito.setNote(nuovoControlloForm.getNote());
		esito.setNoteIrregolarita(nuovoControlloForm.getNoteIrregolarita());
		esito.setFlEsito(nuovoControlloForm.getFlEsito());
		esito.setFlEsitoPassaporto(nuovoControlloForm.getFlEsitoPassaporto());
		esito.setFlIrregolarita(nuovoControlloForm.getFlIrregolarita());
		esito.setDataChiusuraVerbale(nuovoControlloForm.getDataChiusuraVerbale());
		esito.setEmailInvioVerbale(nuovoControlloForm.getEmailInvioVerbale());
		// nuovi campi per id_versione = 2
		esito.setNoteEsitoControllo(nuovoControlloForm.getNoteEsitoControllo());
		esito.setFlMisuraUfficiale(nuovoControlloForm.getFlMisuraUfficiale());
		esito.setNumMisuraUfficiale(nuovoControlloForm.getNumMisuraUfficiale());
		esito.setFlMotivoMisuraUfficiale(nuovoControlloForm.getFlMotivoMisuraUfficiale());
		esito.setNoteMotivoMisuraUfficiale(nuovoControlloForm.getNoteMotivoMisuraUfficiale());
		esito.setFlSanzioneAmministrativaEmessa(nuovoControlloForm.getFlSanzioneAmministrativaEmessa());
		esito.setNoteSanzioneAmministrativaEmessa(nuovoControlloForm.getNoteSanzioneAmministrativaEmessa());
		esito.setFlSanzioneAmministrativaProposta(nuovoControlloForm.getFlSanzioneAmministrativaProposta());
		esito.setNoteSanzioneAmministrativaProposta(nuovoControlloForm.getNoteSanzioneAmministrativaProposta());
		esito.setFlPrescrizioni(nuovoControlloForm.getFlPrescrizioni());
		esito.setNotePrescrizioni(nuovoControlloForm.getNotePrescrizioni());
		
		esitoMapper.updateByPrimaryKey(esito);	
		
		// update su car_t_controllo per il campo check misura uffciale
		logger.debug("-- Update su car_t_controllo per il campo check misura uffciale");
		CarTControllo controllo = getControllo(nuovoControlloForm.getIdControllo());
		controllo.setMisuraUfficiale(nuovoControlloForm.getMisuraUfficiale()==null ? "N" : "S");
		carTControlloMapper.updateByPrimaryKeySelective(controllo);
		
		logger.debug("BEGIN updateEsitoControllo");
	}


	@Override
	public void inviaVerbaleViaMail(Long idControllo, Long idEsito, String indirizzoMail, byte[] pdf, Long idUtente, Long numeroVerbale)
			throws BusinessException {
	
			CarTEsito esito = esitoMapper.selectByPrimaryKey(idEsito);
			CarTControllo controllo = carTControlloMapper.selectByPrimaryKey(idControllo);
			
									
			String fileName = salvaStampa(idEsito, pdf, idUtente, numeroVerbale);
			
			String subject = "Servizio Fitosanitario Regionale - " + fileName;
			String 	message = "Si trasmette in allegato la documentazione in oggetto.<br><br>"
							+ "<small>Questa mail viene generata in automatico, si prega di non rispondere a questo indirizzo, grazie.</small>"
							+ "<br>";

			Properties caronteProperties = CaronteConstants.getProperties();

			postMailWithPDFAttachment(CaronteConstants.INDIRIZZO_MITTENTE_REGISTRAZIONE_UTENTE,
					new String[] { indirizzoMail }, null, subject, message, pdf, fileName);

	}
	 
	protected void postMailWithPDFAttachment(String from, String[] to, String[] ccn, String subject, String message, byte[] bs, String fileName)
			throws BusinessException {
		try {
		  CaronteUtils.postMailWithPDFAttachment(from, to, ccn, subject, message, bs, fileName);
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage());
		}
	}
	
	@Override
	public void inserisciCampione(NuovoControlloForm form, Long idUtente) throws BusinessException{
		logger.debug("BEGIN inserisciCampione");
		
		// Inserisco il record in car_t_campione
		logger.debug("-- Inserisco il record in car_t_campione");
		CarTCampione rec = new CarTCampione();
		rec.setIdUtenteInserimento(idUtente);
		rec.setDataInserimento(new Date());
		rec.setIdControllo(form.getIdControllo());
		rec.setIdGenere(form.getIdGenereCampione());
		rec.setIdSpecie(form.getSpecieCampione());
		rec.setCodCampioneInizio(form.getCodCampioneDal());
		rec.setCodCampioneFine(form.getCodCampioneAl());
		rec.setFlSintomatico(form.getTipoCampione());
		rec.setIdTipologiaCampione(form.getTipologiaCampione());		 
		rec.setFlCofinanziato(form.getFlCofinanziato());
		BigDecimal tempoImpiegato = CaronteUtils.parseBigDecimal(form.getTempoImpiegato());
		logger.debug("-- tempoImpiegato ="+tempoImpiegato);
		rec.setTempoImpiegato(tempoImpiegato);
		rec.setDataRdp(form.getDataRdp());
		rec.setEsitoRdp(form.getEsitoRdp());
		rec.setNumRdp(form.getNumeroRdp());
		rec.setNote(form.getNoteCampione());
		rec.setFlAnalisi(form.getFlAnalisi());
		rec.setFlBanda(form.getFlBanda());
		rec.setFlSacchetti(form.getFlSacchetti());
				
		campioneMapper.insert(rec);
		Long idCampione = rec.getIdCampione();
		logger.debug("-- idCampione inserito ="+idCampione);		
			
		
		
		// Inserisco gli organismi nocivi da ricercare in car_r_campione_org_noc con R:organismo nocivo di Ricerca; A: organismo nocivo Accertato		
		if(form.getOrgNocDaRicercare() != null && form.getOrgNocDaRicercare().length>0){
			logger.debug("-- Inserisco gli organismi nocivi da ricercare in car_r_campione_org_noc :"+form.getOrgNocDaRicercare().length);
			for (int i = 0; i < form.getOrgNocDaRicercare().length; i++) {
				Long idOrgNoc = form.getOrgNocDaRicercare()[i];
				CarRCampioneOrgNoc record = new CarRCampioneOrgNoc();
				record.setIdCampione(idCampione);
				record.setDataInserimento(new Date());
				record.setIdUtenteInserimento(idUtente);
				record.setFlRicercaAccertato(CaronteConstants.FL_ORG_NOC_CAMPIONE_DA_RICERCARE);
				record.setIdOrgNocivo(idOrgNoc);
				
				campioneOrgNocMapper.insert(record);
			}			
		}
		
		// Inserisco gli organismi nocivi accertati in car_r_campione_org_noc con R:organismo nocivo di Ricerca; A: organismo nocivo Accertato		
		if(form.getOrgNocAccertato() != null && form.getOrgNocAccertato().length>0){
			logger.debug("-- Inserisco gli organismi nocivi accertati in car_r_campione_org_noc ="+form.getOrgNocAccertato().length);
			for (int i = 0; i < form.getOrgNocAccertato().length; i++) {
				Long idOrgNoc = form.getOrgNocAccertato()[i];
				CarRCampioneOrgNoc record = new CarRCampioneOrgNoc();
				record.setIdCampione(idCampione);
				record.setDataInserimento(new Date());
				record.setIdUtenteInserimento(idUtente);
				record.setFlRicercaAccertato(CaronteConstants.FL_ORG_NOC_CAMPIONE_ACCERTATO);
				record.setIdOrgNocivo(idOrgNoc);
				
				campioneOrgNocMapper.insert(record);
			}			
		}
		
		
		logger.debug("END inserisciCampione");			
	}
	
	
	public List<CampioneDTO> getListaCampioniByIdControllo(Long idControllo) throws BusinessException{		
		return controlloMapper.getListaCampioniByIdControllo(idControllo);		
	}


	@Override
	public CarTEsito getEsito(Long idEsito) throws BusinessException {
		return esitoMapper.selectByPrimaryKey(idEsito);
	}


	@Override
	public CarTControllo getControllo(Long idControllo) throws BusinessException {
		return carTControlloMapper.selectByPrimaryKey(idControllo);
	}
	
	@Override
	public Long getSalvaNumeroVerbale(Long idControllo, Long idUtente) throws BusinessException {
		Long numeroVerbale = 0L;
		CarTControllo controllo = getControllo(idControllo);
		
		if (controllo != null) {
			if (controllo.getNumeroVerbale() != null) {	
				numeroVerbale = controllo.getNumeroVerbale();
			}
			else {
				// rigenero il numero e salvo			
				controlloMapper.lockControllo(idControllo);
				numeroVerbale = controlloMapper.generaNumeroVerbale();
				controllo.setNumeroVerbale(numeroVerbale);
				controllo.setIdUtenteAggiornamento(idUtente);
				controllo.setDataAggiornamento(new Date());
			    carTControlloMapper.updateByPrimaryKeySelective(controllo);
			}
		}		
		return numeroVerbale;
	}


	@Override
	public String salvaStampa(Long idEsito, byte[] pdf, Long idUtente, Long numeroVerbale) throws BusinessException {
		//verifico se è già presente un numero verbale, altrimenti lo genero e aggiorno la tabella carTcontrollo		
		String fileName = "Verbale di ispezione N "+numeroVerbale.toString()+"_IS";
		
		CarTEsito esitoUpdate = new CarTEsito();
		esitoUpdate.setIdEsito(idEsito);
		esitoUpdate.setStampaVerbale(pdf);
		esitoUpdate.setNomeFileStampa(fileName+".pdf");
		esitoUpdate.setDataAggiornamento(new Date());
		esitoUpdate.setIdUtenteAggiornamento(idUtente);
		esitoMapper.updateByPrimaryKeySelective(esitoUpdate);	

		return fileName;
	}


	@Override
	public Boolean checkAbilitaStampaVerbaleIsp(Long idControllo) throws BusinessException {
		
		//dati di base
		CarTControllo controllo = carTControlloMapper.selectByPrimaryKey(idControllo);
		if(controllo == null){
			logger.debug("dati di base - controllo = null");
			return false;
		}
		boolean flagControlloDoc = true;
		boolean flagControlloId = true;
		boolean flagControlloFis = true;
		//controllo documentale
		CarTControlloDocumentaleExample exampleDocumentale = new CarTControlloDocumentaleExample();
		exampleDocumentale.createCriteria().andIdControlloEqualTo(idControllo);
		List<CarTControlloDocumentale> controlloDocumentale = controlloDocumentaleMapper.selectByExample(exampleDocumentale);
		if (controlloDocumentale.isEmpty()){
			logger.debug("controllo documentale - controlloDocumentale = null");
			flagControlloDoc = false;
		}
		//controllo identita
		CarTControlloIdentitaExample exampleIdentita = new CarTControlloIdentitaExample();
		exampleIdentita.createCriteria().andIdControlloEqualTo(idControllo);
		List<CarTControlloIdentita> controlloIdentita = controlloIdentitaMapper.selectByExample(exampleIdentita);
		if (controlloIdentita.isEmpty()){
			logger.debug("controllo identita - exampleIdentita = null");
			flagControlloId = false;
		}
		//controllo fisico
		CarTControlloFisicoExample exampleFisico = new CarTControlloFisicoExample();
		exampleFisico.createCriteria().andIdControlloEqualTo(idControllo);
		List<CarTControlloFisico> controlloFisico = controlloFisicoMapper.selectByExample(exampleFisico);
		if (controlloFisico.isEmpty()){
			logger.debug("controllo fisico - exampleFisico = null");
			flagControlloFis = false;
		}
		// almeno un controllo dev'essere stato inserito
		// basta che uno dei controlli sia vero per continuare altrimenti esco subito
		if (!flagControlloDoc && !flagControlloId && !flagControlloFis) { 
			return false;
		}
		
		//allegati
		List<AllegatoDTO> listaAllegati = getListaAllegatiControllo(idControllo);
		for(AllegatoDTO allegato : listaAllegati){
			if(allegato.getFlagObbligatorio() && allegato.getListaAllegatiControllo() == null){
				logger.debug("allegati - allegati non inseriti");
				return false;
			}
		}
		//esito
		CarTEsitoExample exampleEsito = new CarTEsitoExample();
		exampleEsito.createCriteria().andIdControlloEqualTo(idControllo);
		List<CarTEsito> esito = esitoMapper.selectByExample(exampleEsito);
		if (esito.isEmpty()){
			logger.debug("esito - exampleEsito = null");
			return false;
		}
		
		logger.debug("tutti i controlli sono stati superati");
		return true;
	}
	
	public void eliminaCampione(Long idCampione) throws BusinessException{
		logger.debug("BEGIN eliminaCampione");		
		
		// Elimino record in car_r_campione_org_noc
		logger.debug("-- 1) Elimino record in car_r_campione_org_noc"); 
		CarRCampioneOrgNocExample example = new CarRCampioneOrgNocExample();
		example.createCriteria().andIdCampioneEqualTo(idCampione);
		campioneOrgNocMapper.deleteByExample(example);
		
		// Elimino record in car_t_campione
		logger.debug("-- 2) Elimino record in car_t_campione");
		campioneMapper.deleteByPrimaryKey(idCampione);
	}


	@Override
	public Boolean checkAbilitaSementi(Long idCentroAz) throws BusinessException {
		logger.debug("BEGIN checkAbilitaSementi");
		Long idDomanda = getMaxIdDomandaValidaByIdCentroAz(idCentroAz);
		if(idDomanda != null){
			logger.debug("-- idDomanda ="+idDomanda);
			List <TipologiaAttMaterialeDTO> tipologieAtt = domandaMapper.getTipologieAttMateriale(idDomanda);
			for(TipologiaAttMaterialeDTO tipologia : tipologieAtt){
				if(tipologia.getIdTipologia() == CaronteConstants.ID_TIPO_ATTIVITA_SEMENTIERA){
					logger.debug("attività sementiera trovata");
					logger.debug("END checkAbilitaSementi");
					return true;
				}
			}
		}
		logger.debug("attività sementiera NON trovata");
		logger.debug("END checkAbilitaSementi");
		return false;
	}
	
	@Override
	public CarTControlloDocumentale getControlloDocumentaleByIdControllo(Long idControllo) throws BusinessException{
		logger.debug("BEGIN getControlloDocumentaleByIdControllo");		
		CarTControlloDocumentale controlloDoc = null;
		CarTControlloDocumentaleExample example = new CarTControlloDocumentaleExample();
		example.createCriteria().andIdControlloEqualTo(idControllo);
		List<CarTControlloDocumentale> controlloDocList = carTControlloDocMapper.selectByExample(example);
		if(controlloDocList != null && controlloDocList.size()>0){
			controlloDoc = 	controlloDocList.get(0);
		}			
		return controlloDoc;
	}
	
	@Override
	public CarTControlloIdentita getControlloIdentitaByIdControllo(Long idControllo) throws BusinessException{
		logger.debug("BEGIN getControlloIdentitaByIdControllo");		
		CarTControlloIdentita controlloId = null;
		CarTControlloIdentitaExample example = new CarTControlloIdentitaExample();
		example.createCriteria().andIdControlloEqualTo(idControllo);
		List<CarTControlloIdentita> controlloIdList = carTControlloIdentitaMapper.selectByExample(example);
		if(controlloIdList != null && controlloIdList.size()>0){
			controlloId = 	controlloIdList.get(0);
		}			
		return controlloId;
	}
	
	@Override
	public CarTControlloFisico getControlloFisicoByIdControllo(Long idControllo) throws BusinessException {
		logger.debug("BEGIN getControlloFisicoByIdControllo");
		CarTControlloFisicoExample example = new CarTControlloFisicoExample();
		example.createCriteria().andIdControlloEqualTo(idControllo);
		
		List<CarTControlloFisico> controlloFisico = controlloFisicoMapper.selectByExample(example);
		if (controlloFisico != null && controlloFisico.size()>0) {
			return controlloFisico.get(0);
		}
		return null;
	}


	@Override
	public List<CarRControlloIspettore> getIspettoriByIdControllo(Long idControllo) throws BusinessException {
		
		CarRControlloIspettoreExample ex = new CarRControlloIspettoreExample();
		ex.createCriteria().andIdControlloEqualTo(idControllo);
		return controlloIspettoreMapper.selectByExample(ex);

	}


	@Override
	public List<CarRControlloNormaVerbale> getNormeVerbaliByIdControllo(Long idControllo) throws BusinessException {

		CarRControlloNormaVerbaleExample ex = new CarRControlloNormaVerbaleExample();
		ex.createCriteria().andIdControlloEqualTo(idControllo);
		return controlloNormaVerbaleMapper.selectByExample(ex);
		
	}
	
	/*model.addAttribute("listaStrutturaAttrezzatura", listaStrutturaAttrezzatura);
	model.addAttribute("listaMetodiProduzione", listaMetodiProduzione);
		public String[] getTipoIrrigazioneByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public String[] getConoscenzaProfByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public String[] getRequisitoProfByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	*/
	@Override
	public String[] getStrutturaAttrByIdControlloFisico(Long idControlloFisico) throws BusinessException {

		CarRControlloFisicoStrAttExample ex = new CarRControlloFisicoStrAttExample();
		ex.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		List<CarRControlloFisicoStrAtt> listaStrutturaAtt =  controlloFisicoStrAttMapper.selectByExample(ex);
		
		String [] idsStrutturaAtt = new String[listaStrutturaAtt.size()];
		for (int i = 0; i < listaStrutturaAtt.size(); i++) {
			idsStrutturaAtt[i] = String.valueOf(listaStrutturaAtt.get(i).getIdStrutturaAttrezzatura());
		}
		return idsStrutturaAtt;
		
	}
	
	@Override
	public String[] getMetodoProduzioneByIdControlloFisico(Long idControlloFisico) throws BusinessException {

		CarRControlloFisicoMetProExample ex = new CarRControlloFisicoMetProExample();
		ex.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		List<CarRControlloFisicoMetPro> listaMetodiProd =  controlloFisicoMetProMapper.selectByExample(ex);
		
		String [] idsMetodoProd = new String[listaMetodiProd.size()];
		for (int i = 0; i < listaMetodiProd.size(); i++) {
			idsMetodoProd[i] = String.valueOf(listaMetodiProd.get(i).getIdMetodoProduzione());
		}
		return idsMetodoProd;
		
	}
	
	@Override
	public String[] getTipoIrrigazioneByIdControlloFisico(Long idControlloFisico) throws BusinessException {

		CarRControlloFisicoIrrigaExample ex = new CarRControlloFisicoIrrigaExample();
		ex.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		List<CarRControlloFisicoIrriga> listaTipoIrriga =  controlloFisicoIrrigaMapper.selectByExample(ex);
		
		String [] idsTipoIrriga = new String[listaTipoIrriga.size()];
		for (int i = 0; i < listaTipoIrriga.size(); i++) {
			idsTipoIrriga[i] = String.valueOf(listaTipoIrriga.get(i).getIdTipoIrrigazione());
		}
		return idsTipoIrriga;
		
	}
	
	@Override
	public String[] getConoscenzaProfByIdControlloFisico(Long idControlloFisico) throws BusinessException {

		CarRControlloFisicoConProfExample ex = new CarRControlloFisicoConProfExample();
		ex.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		List<CarRControlloFisicoConProf> listaConProf =  controlloFisicoConProfMapper.selectByExample(ex);
		
		String [] idsConProf = new String[listaConProf.size()];
		for (int i = 0; i < listaConProf.size(); i++) {
			idsConProf[i] = String.valueOf(listaConProf.get(i).getIdConoscenzaProfessionale());
		}
		return idsConProf;
		
	}
	
	@Override
	public List<CarRControlloFisicoReqProf> getRequisitoProfByIdControlloFisico(Long idControlloFisico) throws BusinessException {

		CarRControlloFisicoReqProfExample ex = new CarRControlloFisicoReqProfExample();
		ex.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		List<CarRControlloFisicoReqProf> listaReqProf =  controlloFisicoReqProfMapper.selectByExample(ex);

		if (listaReqProf != null && listaReqProf.size() > 0) {
			return listaReqProf;
		}
		return null;
		
		
	}
	
	@Override
	public List<GenereSpecieDTO> getGenereSpecieByIdControlloFisisco(Long idControlloFisico) throws BusinessException {
		return controlloMapper.getGenereSpecieByIdControlloFisisco(idControlloFisico);		
	}	
	
	@Override
	public List<CarRControlloTipologia> getControlloTipologiaByIdControllo(Long idControllo) throws BusinessException {		
		CarRControlloTipologiaExample ex = new CarRControlloTipologiaExample();
		ex.createCriteria().andIdControlloEqualTo(idControllo);
		return controlloTipologiaMapper.selectByExample(ex);
		
	}

	@Override
	public List<SementeDTO> getSementiByIdControllo(Long idControllo) throws BusinessException {		
		return controlloMapper.getSementiByIdControllo(idControllo);			
	}


	@Override
	public CarTEsito getEsitoByIdControllo(Long idControllo) throws BusinessException {
		logger.debug("BEGIN getEsitoByIdControllo");
		CarTEsito esito = null;
		CarTEsitoExample ex = new CarTEsitoExample();
		ex.createCriteria().andIdControlloEqualTo(idControllo);
		List<CarTEsito> esitiList = esitoMapper.selectByExample(ex);
		if(esitiList != null && esitiList.size()>0){
			esito = esitiList.get(0);
		}
		logger.debug("END getEsitoByIdControllo");
		return esito;		
	}
	
		
	@Override
	public List<SpedizioniereDTO> getElencoOperatoriAttivi(RicercaOperatoreForm ricercaOperatore) throws BusinessException{
	  return controlloMapper.getElencoOperatoriAttivi(ricercaOperatore);	 
	}
	
	@Override
	public void inserisciMisuraUfficiale(NuovoControlloForm form, Long idUtente) throws BusinessException{
		logger.debug("BEGIN inserisciMisuraUfficiale");
		
		// -------- *** Dati Misura ufficiale - Disposizione ----------
		logger.debug("-- *** Dati Misura ufficiale - Disposizione ----------");
		// Genero il numero verbale misura ufficiale
		Long numVerbaleMf = generaNumeroVerbaleMf();
		CarTMisuraUfficiale misuraUfficiale = getDatiMisuraUfficialeDisposizioneByForm(form);
		logger.debug("-- numVerbaleMf ="+numVerbaleMf);
		misuraUfficiale.setNumeroVerbaleMf(numVerbaleMf);
		logger.debug("-- idControllo ="+form.getIdControllo());
		misuraUfficiale.setIdControllo(form.getIdControllo());
		misuraUfficiale.setIdUtenteInserimento(idUtente);
		misuraUfficiale.setDataInserimento(new Date());
		
		// -------- *** Dati Misura ufficiale - Constatazione ----------		
		logger.debug("-- idConstatazionePresente ="+form.getIdConstatazionePresente());
		if(form.getIdConstatazionePresente() != null){
			logger.debug("-- *** Dati Misura ufficiale - Constatazione ----------");
			// Genero il numero verbale constatazione
			Long numVerbaleCo = generaNumeroVerbaleCo();
			logger.debug("-- numVerbaleCo ="+numVerbaleCo);
			misuraUfficiale.setNumeroVerbaleCo(numVerbaleCo);			
			misuraUfficiale = getDatiMisuraUfficialeConstatazioneByForm(misuraUfficiale, form);
		}
		logger.debug("----- Salvataggio Dati Misura ufficiale in CAR_T_MISURA_UFFICIALE");
		misuraUfficialeMapper.insert(misuraUfficiale);
		Long idMisuraUfficialeSalvata = misuraUfficiale.getIdMisuraUfficiale();
		form.setIdMisuraUfficiale(idMisuraUfficialeSalvata);
		logger.debug("-- ************* idMisuraUfficialeSalvata ="+idMisuraUfficialeSalvata);
		
		// ************** Salvataggio dati nelle tabelle di relazione - Misura ufficiale - Disposizione
		logger.debug("--- *** Salvataggio dati nelle tabelle di relazione - Misura ufficiale - Disposizione");
		// -- Ispettore/i che ha/hanno emesso la misura : insert in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'D'
		if(form.getIdsIspettoreMisura() != null && form.getIdsIspettoreMisura().length>0){
			logger.debug("-- Insert in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'D'");
			for(Long idIspettore : form.getIdsIspettoreMisura()){
				CarRMisuraIspettore ispettore = new CarRMisuraIspettore();
				ispettore.setIdMisuraUfficiale(idMisuraUfficialeSalvata);
				ispettore.setIdIspettore(new Long(idIspettore));
				ispettore.setTipoMisura(CaronteConstants.TIPO_MISURA_DISPOSIZIONE);
				ispettore.setIdUtenteInserimento(idUtente);
				ispettore.setDataInserimento(new Date());
				carRMisuraIspettoreMapper.insert(ispettore);
			}
		}
		
		// Misure applicate : CAR_R_MISURA_TIPOLOGIA
		if(form.getMisure() != null && form.getMisure().size()>0){
			logger.debug("-- Insert in CAR_R_MISURA_TIPOLOGIA");
			for(MisuraDTO misura : form.getMisure()){
				if(misura.getIdMisura() !=null) {
					CarRMisuraTipologia nuovaMisura = new CarRMisuraTipologia();
					nuovaMisura.setIdMisuraUfficiale(idMisuraUfficialeSalvata);
					nuovaMisura.setIdTipologiaMisura(misura.getIdMisura());
					nuovaMisura.setNoteTipologia(misura.getNote());
					nuovaMisura.setIdUtenteInserimento(idUtente);
					nuovaMisura.setDataInserimento(new Date());					
					carRMisuraTipologiaMapper.insert(nuovaMisura);
				}
			}
		}
		
		
		// Organismi nocivi/genere/specie/ + note/numero piante : CAR_R_MISURA_ORG_NOC
		if(form.getOrgNocivoGenereSpecie() != null && form.getOrgNocivoGenereSpecie().size()>0){
			logger.debug("-- Insert in CAR_R_MISURA_ORG_NOC");
			for (Iterator<OrgNocivoGenereSpecieDTO> iterator = form.getOrgNocivoGenereSpecie().iterator(); iterator.hasNext();) {
				OrgNocivoGenereSpecieDTO orgNocivo = (OrgNocivoGenereSpecieDTO) iterator.next();
				CarRMisuraOrgNoc orgNocInsert = new CarRMisuraOrgNoc();
				orgNocInsert.setIdSpecie(orgNocivo.getIdSpecie());
				orgNocInsert.setIdGenere(orgNocivo.getIdGenere());
				orgNocInsert.setIdOrgNocivo(orgNocivo.getIdOrganismoNocivo());
				orgNocInsert.setNote(orgNocivo.getNote());
				orgNocInsert.setNumeroPiante(orgNocivo.getNumeroPiante());
				orgNocInsert.setDataInserimento(new Date());
				orgNocInsert.setIdUtenteInserimento(idUtente);;
				orgNocInsert.setIdMisuraUfficiale(idMisuraUfficialeSalvata);
				carRMisuraOrgNocMapper.insert(orgNocInsert);
			}
		}
		
		
		
		// ************ Salvataggio dati nelle tabelle di relazione - Misura ufficiale - Constatazione
		if(form.getIdConstatazionePresente() != null){
			logger.debug("--- *** Salvataggio dati nelle tabelle di relazione - Misura ufficiale - Constatazione");
			// -- CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'C'
			// -- Ispettore/i che ha/hanno constatato l'esito della misura : insert in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'C'
			if(form.getIdsIspettoreConstMisura() != null && form.getIdsIspettoreConstMisura().length>0){
				logger.debug("-- Insert in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'C'");
				for(Long idIspettore : form.getIdsIspettoreConstMisura()){
					CarRMisuraIspettore ispettore = new CarRMisuraIspettore();
					ispettore.setIdMisuraUfficiale(idMisuraUfficialeSalvata);
					ispettore.setIdIspettore(new Long(idIspettore));
					ispettore.setTipoMisura(CaronteConstants.TIPO_MISURA_CONSTATAZIONE);
					ispettore.setIdUtenteInserimento(idUtente);
					ispettore.setDataInserimento(new Date());
					carRMisuraIspettoreMapper.insert(ispettore);
				}
			}			
		}
		logger.debug("END inserisciMisuraUfficiale");		
	}
	
	private Long generaNumeroVerbaleMf() throws BusinessException{
		return controlloMapper.generaNumeroVerbaleMf();
	}
	
	private Long generaNumeroVerbaleCo() throws BusinessException{
		return controlloMapper.generaNumeroVerbaleCo();
	}
	
	private CarTMisuraUfficiale getDatiMisuraUfficialeDisposizioneByForm(NuovoControlloForm form){
		logger.debug("BEGIN getDatiMisuraUfficialeDisposizioneByForm");
		
		CarTMisuraUfficiale misuraUff = new CarTMisuraUfficiale();
		misuraUff.setDataMisuraDisp(form.getDataMisura());
		misuraUff.setOraMisuraDisp(form.getOraMisura());
		misuraUff.setNoteConformita(form.getNoteNonConformita());
		misuraUff.setLettereMisura(form.getLettereMisura());
		misuraUff.setDataEntroMisura(form.getDataMisuraEntro());
		misuraUff.setModalitaMisura(form.getModalita());
		
		misuraUff.setNoteMisuraDisp(form.getNoteMisuraDisp());
		
		// Persona identificata come custode dei prodotti oggetto di misura ufficiale
		misuraUff.setCongnomeCustode(form.getCognomeCustode());
		misuraUff.setNomeCustode(form.getNomeCustode());
		misuraUff.setIdComuneNascitaCustode(form.getIdComNascitaCustode());
		misuraUff.setDataNascitaCustode(form.getDataNascitaCustode());
		misuraUff.setIdComuneResidenzaCustode(form.getIdComResidCustode());
		misuraUff.setIndirizzoCustode(form.getIndirResidCustode());
		misuraUff.setTipoDocumento(form.getTipoDocIdentificazCustode());
		misuraUff.setCodDocumento(form.getNumDocIdentificazCustode());
		misuraUff.setDataEmissioneDocumento(form.getDataEmissioneDocumento());
		misuraUff.setOrgEmissioneDocumento(form.getOrgEmissioneDocumento());
		misuraUff.setIdTipoRespCustode(form.getIdQualificaCustode());
		misuraUff.setNoteCustode(form.getPrescrizioniCustode());

		// Persona di riferimento per il verbale della Misura ufficiale
		misuraUff.setDataConsegnaDisposizione(form.getDataConsegnaDisp());
		misuraUff.setPersConsegnaDisposizione(form.getPersonaRifVerbale());
		misuraUff.setIdTipoRespConsegnaDisp(form.getIdTipoRespConsegnaDisp());
		misuraUff.setNoteDichiarazione(form.getDichPersRifVerbale());
			
		logger.debug("END getDatiMisuraUfficialeDisposizioneByForm");
		return misuraUff;
	}
	
	private CarTMisuraUfficiale getDatiMisuraUfficialeConstatazioneByForm(CarTMisuraUfficiale misuraUff, NuovoControlloForm form){
		logger.debug("BEGIN getDatiMisuraUfficialeConstatazioneByForm");
		
		misuraUff.setDataConstatazione(form.getDataConstMisura());
		misuraUff.setOraConstatazione(form.getOraConstMisura());
		misuraUff.setFlEsitoConstatazione(form.getFlEsitoMisura());
		misuraUff.setNoteEsitoConst(form.getNoteConstMisura());
		
		// Persona presente durante la Constatazione della Misura ufficiale
		misuraUff.setPersRiferimentoConst(form.getPersonaPresenteConst());
		misuraUff.setIdTipoRespAziendaConst(form.getIdQualificaPersonaPresenteConst());
		
		// Persona di riferimento per il verbale della Constatazione della Misura ufficiale
		misuraUff.setDataConsegnaConst(form.getDataConsegnaConst());
		misuraUff.setPersConsegnaConst(form.getPersonaRifVerbaleConst());
		misuraUff.setIdTipoRespConsegnaConst(form.getIdTipoRespConsegnaConst());
		misuraUff.setNoteDichiarazioneConst(form.getDichPersRifVerbaleConst());
		misuraUff.setNoteConstatazione(form.getNotePersRifVerbaleConst());
		
		
		
		logger.debug("END getDatiMisuraUfficialeConstatazioneByForm");
		return misuraUff;
	}


	@Override
	public CarTCentroAziendale getCentroAziendaleByIdControllo(Long idControllo) throws BusinessException {

			CarTControllo controllo = carTControlloMapper.selectByPrimaryKey(idControllo);
			return carTCentroAziendaleMapper.selectByPrimaryKey(controllo.getIdCentroAziendale());
			
	}
	
	
	@Override
	public void updateMisuraUfficiale(NuovoControlloForm form, Long idUtente) throws BusinessException{
		logger.debug("BEGIN updateMisuraUfficiale");
		
		// -------- *** Dati Misura ufficiale - Disposizione ----------
		logger.debug("-- *** Dati Misura ufficiale - Disposizione ----------");

		// Dati da aggiornare su CAR_T_MISURA_UFFICIALE
		CarTMisuraUfficiale misuraUfficiale = getDatiMisuraUfficialeDisposizioneByForm(form);
		
		// Recupero i dati da risettare sul db con l'update
		MisuraUfficialeDTO misuraUfficialeDb = getMisuraUfficiale(form.getIdControllo());
		CarTMisuraUfficiale misuraDb = misuraUfficialeDb.getMisuraUfficiale();
		misuraUfficiale.setNumeroVerbaleMf(misuraDb.getNumeroVerbaleMf());
		misuraUfficiale.setNumeroVerbaleCo(misuraDb.getNumeroVerbaleCo());
		misuraUfficiale.setDataInserimento(misuraDb.getDataInserimento());
		misuraUfficiale.setIdUtenteInserimento(misuraDb.getIdUtenteInserimento());		
		
		logger.debug("-- idControllo ="+form.getIdControllo());
		misuraUfficiale.setIdControllo(form.getIdControllo());
		logger.debug("-- idMisuraUfficiale da AGGIORNARE ="+form.getIdMisuraUfficiale());
		misuraUfficiale.setIdMisuraUfficiale(form.getIdMisuraUfficiale());
		misuraUfficiale.setIdUtenteAggiornamento(idUtente);
		misuraUfficiale.setDataAggiornamento(new Date());
		
		// -------- *** Dati Misura ufficiale - Constatazione ----------		
		logger.debug("-- idConstatazionePresente ="+form.getIdConstatazionePresente());
		if(form.getIdConstatazionePresente() != null){
			//se sono in update ma da nuova.jsp e non è ancora stato generato il num verb 
			if (misuraUfficiale.getNumeroVerbaleCo() == null) {				
				// Genero il numero verbale constatazione
				Long numVerbaleCo = generaNumeroVerbaleCo();
				logger.debug("-- numVerbaleCo ="+numVerbaleCo);
				misuraUfficiale.setNumeroVerbaleCo(numVerbaleCo);	
			}
			logger.debug("-- *** Dati Misura ufficiale - Constatazione ----------");						
			misuraUfficiale = getDatiMisuraUfficialeConstatazioneByForm(misuraUfficiale, form);
		} else {
			// pulisco i campi della constazione, potrebbero aver compilato la constatazione e conseguentemente aver tolto la spunta
			misuraUfficiale.setDataConstatazione(null);
			misuraUfficiale.setOraConstatazione(null);
			misuraUfficiale.setNumeroVerbaleCo(null);
			misuraUfficiale.setPersRiferimentoConst(null);
			misuraUfficiale.setIdTipoRespAziendaConst(null);
			misuraUfficiale.setFlEsitoConstatazione(null);
			misuraUfficiale.setNoteEsitoConst(null);
			misuraUfficiale.setDataConsegnaConst(null);
			misuraUfficiale.setPersConsegnaConst(null);
			misuraUfficiale.setIdTipoRespConsegnaConst(null);
			misuraUfficiale.setNoteDichiarazioneConst(null);
			misuraUfficiale.setNoteConstatazione(null);
			misuraUfficiale.setStampaConstatazione(null);
			misuraUfficiale.setNomeFileStampaConst(null);
			logger.debug("Pulisco i campi constatazione in update");
		}
		
		logger.debug("----- UPDATE Dati Misura ufficiale in CAR_T_MISURA_UFFICIALE");
		misuraUfficialeMapper.updateByPrimaryKey(misuraUfficiale);
		Long idMisuraUfficialeSalvata = misuraUfficiale.getIdMisuraUfficiale();
		logger.debug("-- ************* idMisuraUfficiale aggiornata ="+idMisuraUfficialeSalvata);
		form.setIdMisuraUfficiale(idMisuraUfficialeSalvata);
		
		// ************** Aggiornameto dati nelle tabelle di relazione - Misura ufficiale - Disposizione		
		logger.debug("--- *** Elimina/Inserisci dati nelle tabelle di relazione - Misura ufficiale - Disposizione");
		
		logger.debug("-- Delete in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'D'");
		eliminaIspettoreMisuraUfficiale(idMisuraUfficialeSalvata, CaronteConstants.TIPO_MISURA_DISPOSIZIONE);
		// -- Ispettore/i che ha/hanno emesso la misura : insert in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'D'
		if(form.getIdsIspettoreMisura() != null && form.getIdsIspettoreMisura().length>0){
			logger.debug("-- Insert in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'D'");
			for(Long idIspettore : form.getIdsIspettoreMisura()){
				CarRMisuraIspettore ispettore = new CarRMisuraIspettore();
				ispettore.setIdMisuraUfficiale(idMisuraUfficialeSalvata);
				ispettore.setIdIspettore(new Long(idIspettore));
				ispettore.setTipoMisura(CaronteConstants.TIPO_MISURA_DISPOSIZIONE);
				ispettore.setIdUtenteInserimento(idUtente);
				ispettore.setDataInserimento(new Date());
				carRMisuraIspettoreMapper.insert(ispettore);
			}
		}
		
		// Misure applicate : CAR_R_MISURA_TIPOLOGIA
		logger.debug("-- Delete in CAR_R_MISURA_TIPOLOGIA");
		eliminaMisureApplicateMisuraUfficiale(idMisuraUfficialeSalvata);
		if(form.getMisure() != null && form.getMisure().size()>0){
			logger.debug("-- Insert in CAR_R_MISURA_TIPOLOGIA");
			for(MisuraDTO misura : form.getMisure()){
				if(misura.getIdMisura() !=null) {
					CarRMisuraTipologia nuovaMisura = new CarRMisuraTipologia();
					nuovaMisura.setIdMisuraUfficiale(idMisuraUfficialeSalvata);
					nuovaMisura.setIdTipologiaMisura(misura.getIdMisura());
					nuovaMisura.setNoteTipologia(misura.getNote());
					nuovaMisura.setIdUtenteInserimento(idUtente);
					nuovaMisura.setDataInserimento(new Date());					
					carRMisuraTipologiaMapper.insert(nuovaMisura);
				}
			}
		}
		
		
		// Organismi nocivi/genere/specie/ + note/numero piante : CAR_R_MISURA_ORG_NOC
		logger.debug("-- Delete in CAR_R_MISURA_ORG_NOC");
		eliminaOrganismiNociviMisuraUfficiale(idMisuraUfficialeSalvata);
		if(form.getOrgNocivoGenereSpecie() != null && form.getOrgNocivoGenereSpecie().size()>0){
			logger.debug("-- Insert in CAR_R_MISURA_ORG_NOC");
			for (Iterator<OrgNocivoGenereSpecieDTO> iterator = form.getOrgNocivoGenereSpecie().iterator(); iterator.hasNext();) {
				OrgNocivoGenereSpecieDTO orgNocivo = (OrgNocivoGenereSpecieDTO) iterator.next();
				CarRMisuraOrgNoc orgNocInsert = new CarRMisuraOrgNoc();
				orgNocInsert.setIdSpecie(orgNocivo.getIdSpecie());
				orgNocInsert.setIdGenere(orgNocivo.getIdGenere());
				orgNocInsert.setIdOrgNocivo(orgNocivo.getIdOrganismoNocivo());
				orgNocInsert.setNote(orgNocivo.getNote());
				orgNocInsert.setNumeroPiante(orgNocivo.getNumeroPiante());
				orgNocInsert.setDataInserimento(new Date());
				orgNocInsert.setIdUtenteInserimento(idUtente);;
				orgNocInsert.setIdMisuraUfficiale(idMisuraUfficialeSalvata);
				carRMisuraOrgNocMapper.insert(orgNocInsert);
			}
		}
		
		
		
		// ************ Salvataggio dati nelle tabelle di relazione - Misura ufficiale - Constatazione
		if(form.getIdConstatazionePresente() != null){
			logger.debug("--- *** Aggiornamento dati nelle tabelle di relazione - Misura ufficiale - Constatazione");
			// -- CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'C'
			logger.debug("-- Delete in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'C'");
			eliminaIspettoreMisuraUfficiale(idMisuraUfficialeSalvata, CaronteConstants.TIPO_MISURA_CONSTATAZIONE);
			// -- Ispettore/i che ha/hanno constatato l'esito della misura : insert in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'C'
			if(form.getIdsIspettoreConstMisura() != null && form.getIdsIspettoreConstMisura().length>0){
				logger.debug("-- Insert in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'C'");
				for(Long idIspettore : form.getIdsIspettoreConstMisura()){
					CarRMisuraIspettore ispettore = new CarRMisuraIspettore();
					ispettore.setIdMisuraUfficiale(idMisuraUfficialeSalvata);
					ispettore.setIdIspettore(new Long(idIspettore));
					ispettore.setTipoMisura(CaronteConstants.TIPO_MISURA_CONSTATAZIONE);
					ispettore.setIdUtenteInserimento(idUtente);
					ispettore.setDataInserimento(new Date());
					carRMisuraIspettoreMapper.insert(ispettore);
				}
			}			
		}
		logger.debug("END updateMisuraUfficiale");	
	}

	
	public MisuraUfficialeDTO getMisuraUfficiale(Long idControllo) throws BusinessException{
		logger.debug("BEGIN getMisuraUfficiale");
		
		MisuraUfficialeDTO misuraUffDto = null;
		
		CarTMisuraUfficialeExample misuraUfficialeEx = new CarTMisuraUfficialeExample();
		misuraUfficialeEx.createCriteria().andIdControlloEqualTo(idControllo);
		
		// Dati di CAR_T_MISURA_UFFICIALE
		logger.debug("-- Cerco i dati in CAR_T_MISURA_UFFICIALE");
		List<CarTMisuraUfficiale> misuraUfficiale = misuraUfficialeMapper.selectByExample(misuraUfficialeEx);
		CarTMisuraUfficiale misuraUff = null;
		if(misuraUfficiale != null && misuraUfficiale.size()>0){
		  misuraUff = misuraUfficiale.get(0);
		  misuraUffDto = new MisuraUfficialeDTO();		  
		  misuraUffDto.setMisuraUfficilae(misuraUff);
		  
		  Long idMisuraUff = misuraUff.getIdMisuraUfficiale();
		  logger.debug("-- idMisuraUff ="+idMisuraUff);
		  
		  // CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'D' (Ispettore/i che ha/hanno emesso la misura - Disposizione)
		  logger.debug("-- Cerco i dati in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'D'");
		  CarRMisuraIspettoreExample misIspettExD = new CarRMisuraIspettoreExample();
		  misIspettExD.createCriteria().andIdMisuraUfficialeEqualTo(idMisuraUff).andTipoMisuraEqualTo(CaronteConstants.TIPO_MISURA_DISPOSIZIONE);
		  List<CarRMisuraIspettore> ispettoriDisposizione = carRMisuraIspettoreMapper.selectByExample(misIspettExD);
		  misuraUffDto.setIspettoriDisposizione(ispettoriDisposizione);
		  
		  // CAR_R_MISURA_TIPOLOGIA (Misure applicate)
		  logger.debug("-- Cerco i dati in CAR_R_MISURA_TIPOLOGIA");
		  CarRMisuraTipologiaExample misuraTipEx = new CarRMisuraTipologiaExample();
		  misuraTipEx.createCriteria().andIdMisuraUfficialeEqualTo(idMisuraUff);
		  List<CarRMisuraTipologia> misuraTipologiaList = carRMisuraTipologiaMapper.selectByExample(misuraTipEx);
		  misuraUffDto.setMisureApplicate(misuraTipologiaList);
		  
		  // CAR_R_MISURA_ORG_NOC
		  logger.debug("-- Cerco i dati in CAR_R_MISURA_ORG_NOC");
		  CarRMisuraOrgNocExample misuraOrgNocEx = new CarRMisuraOrgNocExample();
		  misuraOrgNocEx.createCriteria().andIdMisuraUfficialeEqualTo(idMisuraUff);
		  List<CarRMisuraOrgNoc> misuraOrgNocList = carRMisuraOrgNocMapper.selectByExample(misuraOrgNocEx);
		  misuraUffDto.setOrganismiNociviMisuraUfficiale(misuraOrgNocList);
		  
		  //CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'C' (Ispettore/i che ha/hanno constatato l'esito della misura - Constatazione)
		  logger.debug("-- Cerco i dati in CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'C'");
		  CarRMisuraIspettoreExample misIspettExC = new CarRMisuraIspettoreExample();
		  misIspettExC.createCriteria().andIdMisuraUfficialeEqualTo(idMisuraUff).andTipoMisuraEqualTo(CaronteConstants.TIPO_MISURA_CONSTATAZIONE);
		  List<CarRMisuraIspettore> ispettoriConstatazione = carRMisuraIspettoreMapper.selectByExample(misIspettExC);
		  misuraUffDto.setIspettoriConstatazione(ispettoriConstatazione);
		  
		}
		
		
		
		logger.debug("END getMisuraUfficiale");
		return misuraUffDto;		
	}
	
	

	@Override
	public List<CarDNormaVerbale> getListaNormeVerbaliByIdControllo(Long idControllo) throws BusinessException {
		
		return controlloMapper.getListaNormeVerbaliByIdControllo(idControllo);
	}


	@Override
	public List<SementeDTO> getListaSementiByIdControllo(Long idControllo) throws BusinessException {
		
		return controlloMapper.getListaSementiByIdControllo(idControllo);
	}


	@Override
	public List<CarDTipologiaControllo> getListeTipologiaControlloByIdControllo(Long idControllo)
			throws BusinessException {

		return controlloMapper.getListeTipologiaControlloByIdControllo(idControllo);
	}


	@Override
	public List<CarDStrutturaAttrezzatura> getListaStruttureAttrezzatureByIdControlloFisico(Long idControlloFisico)
			throws BusinessException {
		
		return controlloMapper.getListaStruttureAttrezzatureByIdControlloFisico(idControlloFisico);
	}


	@Override
	public List<CarDMetodoProduzione> getMetodiProduzioneByIdControlloFisico(Long idControlloFisico)
			throws BusinessException {

		return controlloMapper.getMetodiProduzioneByIdControlloFisico(idControlloFisico);
	}


	@Override
	public List<CarDTipoIrrigazione> getListaTipiIrrigazioneByIdControlloFisico(Long idControlloFisico)
			throws BusinessException {

		return controlloMapper.getListaTipiIrrigazioneByIdControlloFisico(idControlloFisico);
	}
	
	private void eliminaIspettoreMisuraUfficiale(Long idMisuraUfficiale, String tipoMisura) throws BusinessException{
		logger.debug("BEGIN eliminaIspettoreMisuraUfficiale");
		
		CarRMisuraIspettoreExample ex = new CarRMisuraIspettoreExample();
		ex.createCriteria().andIdMisuraUfficialeEqualTo(idMisuraUfficiale).andTipoMisuraEqualTo(tipoMisura);
		carRMisuraIspettoreMapper.deleteByExample(ex);
		
		logger.debug("END eliminaIspettoreMisuraUfficiale");
	}
	
	private void eliminaMisureApplicateMisuraUfficiale(Long idMisuraUfficiale) throws BusinessException{
		logger.debug("BEGIN eliminaMisureApplicateMisuraUfficiale");
		
		CarRMisuraTipologiaExample ex = new CarRMisuraTipologiaExample();
		ex.createCriteria().andIdMisuraUfficialeEqualTo(idMisuraUfficiale);
		carRMisuraTipologiaMapper.deleteByExample(ex);
		
		logger.debug("END eliminaMisureApplicateMisuraUfficiale");
	}
	
	private void eliminaOrganismiNociviMisuraUfficiale(Long idMisuraUfficiale) throws BusinessException{
		logger.debug("BEGIN eliminaOrganismiNociviMisuraUfficiale");
		
		CarRMisuraOrgNocExample ex = new CarRMisuraOrgNocExample();
		ex.createCriteria().andIdMisuraUfficialeEqualTo(idMisuraUfficiale);
		carRMisuraOrgNocMapper.deleteByExample(ex);
		
		logger.debug("END eliminaOrganismiNociviMisuraUfficiale");
	}

	@Override
	public Boolean checkAbilitaStampaDisposizioneMisuraUff(Long idControllo) throws BusinessException {
		
		//dati di base
		CarTControllo controllo = carTControlloMapper.selectByPrimaryKey(idControllo);
		if(controllo == null){
			logger.debug("dati di base - controllo = null");
			return false;
		}
		
		//misura ufficiale
		CarTMisuraUfficialeExample exMisura = new CarTMisuraUfficialeExample();
		exMisura.createCriteria().andIdControlloEqualTo(idControllo);
		List<CarTMisuraUfficiale> misura = misuraUfficialeMapper.selectByExample(exMisura);
		if (misura.isEmpty()){
			logger.debug("misura - exMisura = null");
			return false;
		}
		
		logger.debug("tutti i controlli sono stati superati");
		return true;
	}
	
	@Override
	public String salvaStampaDisposizione(Long idMisuraUfficiale, byte[] pdf, Long idUtente, Long numeroVerbaleDisposizione) throws BusinessException {
		String fileName = "Verbale_Misura_Ufficiale_N_"+numeroVerbaleDisposizione.toString()+"_MF";
		CarTMisuraUfficiale misura = new CarTMisuraUfficiale();
		misura.setIdMisuraUfficiale(idMisuraUfficiale);
		misura.setStampaDisposizione(pdf);
		misura.setNomeFileStampaDisp(fileName+".pdf");
		misura.setDataAggiornamento(new Date());
		misura.setIdUtenteAggiornamento(idUtente);
		misuraUfficialeMapper.updateByPrimaryKeySelective(misura);	

		return fileName;
	}
	
	@Override
	public Boolean checkAbilitaStampaConstatazioneMisuraUff(Long idControllo) throws BusinessException {
		
		//dati di base
		CarTControllo controllo = carTControlloMapper.selectByPrimaryKey(idControllo);
		if(controllo == null){
			logger.debug("dati di base - controllo = null");
			return false;
		}
		
		//misura ufficile
		CarTMisuraUfficialeExample exMisura = new CarTMisuraUfficialeExample();
		exMisura.createCriteria().andIdControlloEqualTo(idControllo);
		List<CarTMisuraUfficiale> misura = misuraUfficialeMapper.selectByExample(exMisura);
		if (misura.isEmpty()){
			logger.debug("misura = null");
			return false;
		}// controllo che ci sia almeno un esito della constatazione
		else if (misura.get(0).getFlEsitoConstatazione() == null) {
			logger.debug("misura constatazione senza esito");
			return false;
		}
		
		logger.debug("tutti i controlli sono stati superati");
		return true;
	}
	
	@Override
	public String salvaStampaConstatazione(Long idMisuraUfficiale, byte[] pdf, Long idUtente, Long numeroVerbaleConstatazione) throws BusinessException {
		logger.debug("dentro salvaStampaConstatazione: ");
		String fileName = "Verbale_Constatazione_N_"+numeroVerbaleConstatazione.toString()+"_CO";
		logger.debug("salvaStampaConstatazione fileName: "+fileName);
		CarTMisuraUfficiale misura = new CarTMisuraUfficiale();
		misura.setIdMisuraUfficiale(idMisuraUfficiale);
		misura.setStampaConstatazione(pdf);
		misura.setNomeFileStampaConst(fileName+".pdf");
		misura.setDataAggiornamento(new Date());
		misura.setIdUtenteAggiornamento(idUtente);
		misuraUfficialeMapper.updateByPrimaryKeySelective(misura);	

		return fileName;
	}


	@Override
	public List<CarDConoscenzaProfessionale> getListaConoscenzeProfessionaliByIdControlloFisico(Long idControlloFisico)
			throws BusinessException {

		return controlloMapper.getListaConoscenzeProfessionaliByIdControlloFisico(idControlloFisico);
	}


	@Override
	public List<RequisitiProfessionaliDTO> getListaRequisitiProfessionaliByIdControlloFisico(Long idControlloFisico)
			throws BusinessException {
		
		return controlloMapper.getListaRequisitiProfessionaliByIdControlloFisico(idControlloFisico);
	}


	@Override
	public List<MisuraDTO> getListaTipologieMisuraByIdMisuraUfficiale(Long idMisuraUfficiale) throws BusinessException {

		return controlloMapper.getListaTipologieMisuraByIdMisuraUfficiale(idMisuraUfficiale);
	}


	@Override
	public List<IspettoreDTO> getIspettoriDispByIdMisura(Long idMisuraUfficiale) throws BusinessException {

		return controlloMapper.getIspettoriDispByIdMisura(idMisuraUfficiale);
	}


	@Override
	public List<IspettoreDTO> getIspettoriConstByIdMisura(Long idMisuraUfficiale) throws BusinessException {

		return controlloMapper.getIspettoriConstByIdMisura(idMisuraUfficiale);
	}


	@Override
	public List<IspettoreDTO> getIspettoriControlloByIdControllo(Long idControllo) throws BusinessException {

		return controlloMapper.getIspettoriControlloByIdControllo(idControllo);
	}
	
	@Override
	public void updateCampione(NuovoControlloForm form, ModaliForm modaliForm, Long idUtente) throws BusinessException {		

		logger.debug("-- aggiorno dati car_t_campione");
		CarTCampione campione = campioneMapper.selectByPrimaryKey(modaliForm.getIdCampione());
		campione.setDataRdp(modaliForm.getDataRdp());
		campione.setEsitoRdp(modaliForm.getEsitoRdp());
		campione.setNumRdp(modaliForm.getNumeroRdp());
		campione.setNote(modaliForm.getNoteCampione());
		campione.setDataAggiornamento(new Date());
		campione.setIdUtenteAggiornamento(idUtente);
		campione.setFlAnalisi(modaliForm.getFlAnalisi());
		campione.setFlBanda(modaliForm.getFlBanda());
		campione.setFlSacchetti(modaliForm.getFlSacchetti());
		
		campioneMapper.updateByPrimaryKey(campione);	
		
		
		// rimuovo i vecchi org nocivi accertati(anche se la combo e' stata tutta deselezionata, non mantengo i vecchi)
		logger.debug("-- Elimino gli organismi nocivi accertati");
		CarRCampioneOrgNocExample ex = new CarRCampioneOrgNocExample();
		ex.createCriteria().andIdCampioneEqualTo(modaliForm.getIdCampione()).andFlRicercaAccertatoEqualTo(CaronteConstants.FL_ORG_NOC_CAMPIONE_ACCERTATO);
		campioneOrgNocMapper.deleteByExample(ex);
				
		logger.debug("-- Inserisco gli organismi nocivi accertati in car_r_campione_org_noc ="+form.getOrgNocAccertato().length);
		for (int i = 0; i < modaliForm.getOrgNocAccertato().length; i++) {
			Long idOrgNoc = modaliForm.getOrgNocAccertato()[i];
			CarRCampioneOrgNoc record = new CarRCampioneOrgNoc();
			record.setIdCampione(modaliForm.getIdCampione());
			record.setDataInserimento(new Date());
			record.setIdUtenteInserimento(idUtente);
			record.setFlRicercaAccertato(CaronteConstants.FL_ORG_NOC_CAMPIONE_ACCERTATO);
			record.setIdOrgNocivo(idOrgNoc);
			
			campioneOrgNocMapper.insert(record);
		}			
		
		//pulisco i campi
		form.setDataRdp(null);
		form.setEsitoRdp(null);
		form.setNumeroRdp(null);
		form.setNoteCampione(null);
		form.setOrgNocAccertato(null);
		logger.debug("-- idCampione modificato ="+modaliForm.getIdCampione());		
		
	}

    @Override
    public void inserisciMonitoraggioCofinanziato(NuovoControlloForm form, Long idUtente) throws BusinessException{
    	logger.debug("BEGIN inserisciMonitoraggioCofinanziato");
								
		List<MonitCofinanziatoDTO> monitCofList = form.getMonitoraggiCofinanziati();	
				
		if(monitCofList != null && monitCofList.size()>0){
			logger.debug("-- numero di monitoraggi cofinanziati da inserire ="+monitCofList.size());
			for (Iterator<MonitCofinanziatoDTO> iterator = monitCofList.iterator(); iterator.hasNext();) {
				MonitCofinanziatoDTO monitCofinanziatoDTO = (MonitCofinanziatoDTO) iterator.next();
				CarTMonitCofinanziato monitCof = new CarTMonitCofinanziato();
				logger.debug("-- idControllo ="+form.getIdControllo());
				monitCof.setIdControllo(form.getIdControllo());
				monitCof.setIdUtenteInserimento(idUtente);
				monitCof.setDataInserimento(new Date());
				
				monitCof.setIdOrgNocivo(monitCofinanziatoDTO.getIdOrganismoNocivo());
				monitCof.setIdGenere(monitCofinanziatoDTO.getIdGenere());
				monitCof.setIdSpecie(monitCofinanziatoDTO.getIdSpecie());
				monitCof.setNumeroPiante(monitCofinanziatoDTO.getNumeroPiante());
				monitCof.setNoteAltroOn(monitCofinanziatoDTO.getNote());
				monitCof.setOraInizioMonit(monitCofinanziatoDTO.getOraInizioMonit());
				monitCof.setOraFineMonit(monitCofinanziatoDTO.getOraFineMonit());
				monitCof.setLatitudine(monitCofinanziatoDTO.getLatitudine());
				monitCof.setLongitudine(monitCofinanziatoDTO.getLongitudine());
				monitCof.setNote(monitCofinanziatoDTO.getNoteMonit());
				
				logger.debug("----- Salvataggio Dati in CAR_T_MONIT_COFINANZIATO");
				monitCofMapper.insert(monitCof);
				Long idMonitCofinanziato = monitCof.getIdMonitCofinanziato();
				form.setIdMonitCofinanziato(idMonitCofinanziato);
				logger.debug("-- ************* idMonitCofinanziatoSalvata ="+idMonitCofinanziato);
			}				
		}
		logger.debug("END inserisciMonitoraggioCofinanziato");	
    }
    
    @Override
    public List<MonitCofinanziatoDTO> getMonitoraggiCofinanziato(Long idControllo) throws BusinessException{
    	logger.debug("BEGIN getMonitoraggiCofinanziato");
    	List<MonitCofinanziatoDTO> monitCofList = null;
    	
    	CarTMonitCofinanziatoExample ex =  new CarTMonitCofinanziatoExample();
    	ex.createCriteria().andIdControlloEqualTo(idControllo);
    	List<CarTMonitCofinanziato> monitList = monitCofMapper.selectByExample(ex);
    	    	
    	if(monitList != null && monitList.size()>0){
    		logger.debug("-- numero di MONITORAGGI COFINANZIATI trovati ="+monitList.size());
    		monitCofList = new ArrayList<MonitCofinanziatoDTO>();
    		for (Iterator<CarTMonitCofinanziato> iterator = monitList.iterator(); iterator.hasNext();) {
				CarTMonitCofinanziato carTMonitCofinanziato = (CarTMonitCofinanziato) iterator.next();
				MonitCofinanziatoDTO monit = new MonitCofinanziatoDTO();				
				monit.setIdOrganismoNocivo(carTMonitCofinanziato.getIdOrgNocivo());
				monit.setNote(carTMonitCofinanziato.getNoteAltroOn());
				monit.setIdGenere(carTMonitCofinanziato.getIdGenere());
				monit.setIdSpecie(carTMonitCofinanziato.getIdSpecie());
				monit.setNumeroPiante(carTMonitCofinanziato.getNumeroPiante());
				
				monit.setLatitudine(carTMonitCofinanziato.getLatitudine());
				monit.setLongitudine(carTMonitCofinanziato.getLongitudine());
				monit.setNoteMonit(carTMonitCofinanziato.getNote());
				monit.setOraInizioMonit(carTMonitCofinanziato.getOraInizioMonit());
				monit.setOraFineMonit(carTMonitCofinanziato.getOraFineMonit());		
				
				monitCofList.add(monit);
			}    		
    	}
    	
    	logger.debug("END getMonitoraggiCofinanziato");
    	return monitCofList;
    }
    
    @Override
    public List<TipologiaAttMaterialeDTO> getTipologieAttMaterialeDomandeRuop(Long idDomanda) throws BusinessException{
    	logger.debug("BEGIN getTipologieAttMaterialeDomandeRuop");
    	
    	List<TipologiaAttMaterialeDTO> listTipologieAttivita = controlloMapper.getTipologieAttMaterialeDomandeRuop(idDomanda);
    	
    	/*
    	 * Controllo se nelle tipologie ci sono anche  'IMPORTAZIONE' ed 'ESPORTAZIONE', in tal caso recupero la descrizione da visualizzare nella colonna 'Materiale' del layout
    	 */
    	if(listTipologieAttivita != null && listTipologieAttivita.size()>0){
    		logger.debug("-- Controllo se nelle tipologie ci sono anche  'IMPORTAZIONE' ed 'ESPORTAZIONE'");
    		for (Iterator<TipologiaAttMaterialeDTO> iterator = listTipologieAttivita.iterator(); iterator.hasNext();) {
				TipologiaAttMaterialeDTO tipologiaAttMaterialeDTO = (TipologiaAttMaterialeDTO) iterator.next();
				if(tipologiaAttMaterialeDTO != null){
					if(tipologiaAttMaterialeDTO.getIdTipologia() == CaronteConstants.ID_TIPO_ATTIVITA_IMPORTAZIONE){
						List<CodeDescriptionDTO> tipologieImport = controlloMapper.getDenomTipologieProduttiveDomandeRuop(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_IMP, 1L);
						if(tipologieImport != null && tipologieImport.size()>0){
							String descEstesaMatImport = "";
							for (Iterator<CodeDescriptionDTO> iterator2 = tipologieImport.iterator(); iterator2.hasNext();) {
								CodeDescriptionDTO codeDescriptionDTO = (CodeDescriptionDTO) iterator2.next();
								if(codeDescriptionDTO != null){
									descEstesaMatImport+=codeDescriptionDTO.getDescr();
									if(iterator2.hasNext()){
										descEstesaMatImport+=", ";
									}
								}
							}					
							tipologiaAttMaterialeDTO.setDescEstesa(descEstesaMatImport);
						}
					}
					if(tipologiaAttMaterialeDTO.getIdTipologia() == CaronteConstants.ID_TIPO_ATTIVITA_ESPORTAZIONE){
						List<CodeDescriptionDTO> tipologieExport = controlloMapper.getDenomTipologieProduttiveDomandeRuop(idDomanda, CaronteConstants.ID_TIPO_MODELLO_RUOP_EXP, 3L);
						if(tipologieExport != null && tipologieExport.size()>0){
							String descEstesaMatExport = "";
							for (Iterator<CodeDescriptionDTO> iterator3 = tipologieExport.iterator(); iterator3.hasNext();) {
								CodeDescriptionDTO codeDescriptionDTO = (CodeDescriptionDTO) iterator3.next();
								if(codeDescriptionDTO != null){
									descEstesaMatExport+=codeDescriptionDTO.getDescr();
									if(iterator3.hasNext()){
										descEstesaMatExport+=", ";
									}
								}
							}					
							tipologiaAttMaterialeDTO.setDescEstesa(descEstesaMatExport);
						}
					}
				} // fine ciclo Tipologie
			}
    	}
    	
    	logger.debug("END getTipologieAttMaterialeDomandeRuop");
    	return listTipologieAttivita;
    }
    
    public Long checkVersioneControllo(Long idControllo) throws BusinessException {
    	/* per trovare la versione del controllo mi basta controllare che la data inserimento
    	* del controllo sia compresa tra data inizio e data fine. Se il controllo è nuovo
    	* allora setto la data a sysdate e ritorno l'ultima versione presente in tabella
    	**/
    	Date dataInserimento = new Date();
    	if (idControllo != null) {
    		CarTControllo controllo = carTControlloMapper.selectByPrimaryKey(idControllo);
    		if (controllo != null && controllo.getDataInserimento() != null) {
    			dataInserimento = controllo.getDataInserimento();
    		}   		
    	}       	
    	
    	Long idVersione = controlloMapper.getVersioneControlloByDataInserimento(dataInserimento);
    	logger.debug("-- idVersioneControllo = "+idVersione);
    	return idVersione;
    }


	@Override
	public List<CarRControlloFisicoOrgNoc> getOrgNocByIdControlloFisico(Long idControlloFisico)
			throws BusinessException {
		
		CarRControlloFisicoOrgNocExample ex =  new CarRControlloFisicoOrgNocExample();
		ex.createCriteria().andIdControlloFisicoEqualTo(idControlloFisico);
		return controlloFisicoOrgNocMapper.selectByExample(ex);
		
	}


	@Override
	public List<CarDOrgNocivo> getListaOrgNocByIdControlloFisico(Long idControlloFisico) throws BusinessException {
		return controlloMapper.getListaOrgNocByIdControlloFisico(idControlloFisico);				
	}	
    @Override
    public List<CarDMateriale> getAttivitaSementiByIdDomanda(Long idDomanda) throws BusinessException {
    	return controlloMapper.getAttivitaSementiByIdDomanda(idDomanda);
    }

    @Override    public List<CarDMateriale> getAttivitaSementiByIdControllo(Long idControllo) throws BusinessException {    	
    	return controlloMapper.getAttivitaSementiByIdControllo(idControllo);
    }   
    
    @Override
    public void inserisciControlloFisicoSpecie(List<GenereSpecieDTO> listaGenereSpecie, NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException{
    	logger.debug("BEGIN inserisciControlloFisicoSpecie");
    	
    	if(listaGenereSpecie != null && listaGenereSpecie.size()>0){
    		logger.debug("--- Insert into car_t_controllo_fisico");
    		Long idControlloFisico = null;
    		
    		CarTControlloFisico controlloFisico = new CarTControlloFisico();
    		controlloFisico.setIdControllo(nuovoControlloForm.getIdControllo());
    		controlloFisico.setIdUtenteInserimento(idUtente);
    		controlloFisico.setDataInserimento(new Date());

    		controlloFisicoMapper.insert(controlloFisico);
    		nuovoControlloForm.setIdControlloFisico(controlloFisico.getIdControlloFisico());
    		idControlloFisico = controlloFisico.getIdControlloFisico();
    		logger.debug("-- idControlloFisico inserito ="+idControlloFisico);
    		
	    	logger.debug("--- Insert into car_r_controllo_fisico_specie");
			for(GenereSpecieDTO genereSpecie : listaGenereSpecie){
					CarRControlloFisicoSpecie ogg = new  CarRControlloFisicoSpecie();
					ogg.setIdControlloFisico(controlloFisico.getIdControlloFisico());
					ogg.setIdGenere(genereSpecie.getIdGenere());	
					logger.debug("--- IdGenere ="+ogg.getIdGenere());
					ogg.setIdSpecie(genereSpecie.getIdSpecie());
					logger.debug("--- IdSpecie ="+ogg.getIdSpecie());
					ogg.setIdUtenteInserimento(idUtente);
					ogg.setDataInserimento(new Date());
					ogg.setNumPiante(genereSpecie.getNumeroPiante());
					ogg.setNumPianteSintomatiche(genereSpecie.getNumPianteSintomatiche());
					
					if(genereSpecie.getIdOrgNocivoList() != null && genereSpecie.getIdOrgNocivoList().size()>0){
						logger.debug("--- numero di idOrgNocivo ="+genereSpecie.getIdOrgNocivoList().size());
						for (Iterator<Long> iterator = genereSpecie.getIdOrgNocivoList().iterator(); iterator.hasNext();) {
							Long idOrgNocivo = (Long) iterator.next();
							logger.debug("--- idOrgNocivo ="+idOrgNocivo);
							ogg.setIdOrgNocivo(idOrgNocivo);
							controlloFisicoSpecieMapper.insert(ogg);
						}
					}
					else{
						logger.debug("--- Non è stato indicato l'organismo nocivo");
						controlloFisicoSpecieMapper.insert(ogg);
					}	
			}
    	}
    	logger.debug("END inserisciControlloFisicoSpecie");
    }
    
    @Override
    public void aggiungiGenereSpecieOrgNocFisico(NuovoControlloForm form, Long idUtente) throws BusinessException{    
    	logger.debug("BEGIN aggiungiGenereSpecieOrgNocFisico");

    	// Se è stato inserito il record in CAR_T_CONTROLLO su tab dati base, proseguo
    	if(null != form.getIdControllo()){  
    		logger.debug("-- idControllo = "+form.getIdControllo());
    		
    		// Controllo se è già stato inserito il record su CAR_T_CONTROLLO_FISICO
    		logger.debug("-- Controllo se è già stato inserito il record su CAR_T_CONTROLLO_FISICO, altrimento lo inserisco");
    		CarTControlloFisico controlloFisico = getControlloFisicoByIdControllo(form.getIdControllo());  	  
    		if (controlloFisico == null) {	
    			logger.debug("-- insert record su CAR_T_CONTROLLO_FISICO");
    			controlloFisico = new CarTControlloFisico();
    			controlloFisico.setIdControllo(form.getIdControllo());
    			controlloFisico.setIdUtenteInserimento(idUtente);
    			controlloFisico.setDataInserimento(new Date());

    			controlloFisicoMapper.insert(controlloFisico);
    			form.setIdControlloFisico(controlloFisico.getIdControlloFisico());		
    			logger.debug("-- idControlloFisico inserito ="+controlloFisico.getIdControlloFisico());
    		}    		
    		
    		/*
    		 * Si effettua il lock sul record del controllo per evitare modifiche concorrenti
    		 */
    		//controlloMapper.lockControllo(form.getIdControllo());
    		
    		String[] idSpecieList = form.getSpecieFisico();
    		String[] idOrgNocList = form.getOrgNociviFisico();    		
    		Long idGenereFisico = form.getIdGenereFisico();
    		logger.debug("-- idGenereFisico da inserire ="+idGenereFisico);
    		
    		

    		// 2) Per ogni specie selezionata e per ogni organismo nocivo (se ci sono):  Insert car_r_controllo_fisico_specie
    		if(null != idSpecieList){
    			logger.debug("-- Numero di specie da inserire per FISICO ="+idSpecieList.length);
    			for(int j=0;j<idSpecieList.length;j++){
    				String idSpecie = idSpecieList[j];
    				Long idSpecieL = Long.parseLong(idSpecie);
    				logger.debug("-------- idSpecie da inserire ="+idSpecieL);
    				
    				CarRControlloFisicoSpecie ogg = new CarRControlloFisicoSpecie();
    				logger.debug("------ idControlloFisico ="+controlloFisico.getIdControlloFisico());
    				ogg.setIdControlloFisico(controlloFisico.getIdControlloFisico());
    				ogg.setIdGenere(idGenereFisico);
    				// mi recupero il genere della specie
    				//CarDSpecie dettaglioSpecie = specieMapper.selectByPrimaryKey(idSpecieL);
    				logger.debug("--- IdGenere da inserire="+ogg.getIdGenere());
    				ogg.setIdSpecie(idSpecieL);    				
    				ogg.setIdUtenteInserimento(idUtente);
    				ogg.setDataInserimento(new Date());
    				
    				// inserisco un record per ogni organismo nocivo selezionato
    				if(idOrgNocList != null && idOrgNocList.length>0){
    					logger.debug("-- Numero di organismi nocivi da inserire per SPECIE FISICO ="+idOrgNocList.length);
    					for (int z=0;z<idOrgNocList.length;z++) {
							Long idOrgNocivo = Long.valueOf(idOrgNocList[z]).longValue();							
							logger.debug("--- idOrgNocivo da inserire ="+idOrgNocivo);
							ogg.setIdOrgNocivo(idOrgNocivo);
							controlloFisicoSpecieMapper.insert(ogg);
						}
    				}
    				else{
    					logger.debug("-- Non ci sono organismi nocivi per la specie = "+idSpecieL);
    					controlloFisicoSpecieMapper.insert(ogg);
    				}
    			}
    		} 
    		// 2) Non è stata indicata la specie : inserisco per genere e organismi nocivi (se ci sono):  Insert car_r_controllo_fisico_specie
    		else {
    			if (idGenereFisico != null) {
    				CarRControlloFisicoSpecie ogg = new  CarRControlloFisicoSpecie();
    				ogg.setIdControlloFisico(controlloFisico.getIdControlloFisico());
    				ogg.setIdGenere(idGenereFisico);
    				// mi recupero il genere della specie
    				//CarDSpecie dettaglioSpecie = specieMapper.selectByPrimaryKey(idSpecieL);
    				logger.debug("--- IdGenere da inserire="+ogg.getIdGenere());    				    				
    				ogg.setIdUtenteInserimento(idUtente);
    				ogg.setDataInserimento(new Date());	
    				
    				// inserisco un record per ogni organismo nocivo selezionato
    				if(idOrgNocList != null && idOrgNocList.length>0){
    					logger.debug("-- Numero di organismi nocivi da inserire per SPECIE FISICO ="+idOrgNocList.length);
    					for (int z=0;z<idOrgNocList.length;z++) {
							Long idOrgNocivo = Long.valueOf(idOrgNocList[z]).longValue();							
							logger.debug("--- idOrgNocivo da inserire ="+idOrgNocivo);
							ogg.setIdOrgNocivo(idOrgNocivo);
							controlloFisicoSpecieMapper.insert(ogg);
						}
    				}
    				else{
    					logger.debug("-- Non ci sono organismi nocivi per il genere = "+idGenereFisico);
    					controlloFisicoSpecieMapper.insert(ogg);
    				}
    				
    			}
    		}    		
    	}  
    	logger.debug("END aggiungiGenereSpecieOrgNocFisico");
    }
    
    @Override
    public void eliminaSpecieControlloFisico(Long idControlloFisicoSpecie) throws BusinessException{
    	logger.debug("BEGIN eliminaSpecieControlloFisico");

    	// Elimino record in car_r_controllo_fisico_specie
    	logger.debug("-- Elimino record in car_r_controllo_fisico_specie"); 
    	CarRControlloFisicoSpecieExample example = new CarRControlloFisicoSpecieExample();
    	example.createCriteria().andIdControlloFisicoSpecieEqualTo(idControlloFisicoSpecie);
    	controlloFisicoSpecieMapper.deleteByExample(example);    	
    }	
    
    @Override
    public CarRControlloFisicoSpecie getControlloFisicoSpecieByIdControlloFisicoSpecie(Long idControlloFisicoSpecie) throws BusinessException{
    	logger.debug("BEGIN getControlloFisicoSpecieByIdControlloFisicoSpecie");    	
    	return controlloFisicoSpecieMapper.selectByPrimaryKey(idControlloFisicoSpecie);    	    
    }

	@Override
	public void inserisciCheck(NuovoControlloForm nuovoControlloForm, Long id) throws BusinessException {

		logger.debug("BEGIN inserisciCheck");
		
		List<CampioneDTO> campioniList = getListaCampioniByIdControllo(nuovoControlloForm.getIdControllo());
		

		logger.debug("-- aggiorno dati car_t_campione");
		
		for(CampioneDTO campione : campioniList){
			campione.setFlAnalisi(nuovoControlloForm.getFlAnalisi());
			campione.setFlBanda(nuovoControlloForm.getFlBanda());
			campione.setFlSacchetti(nuovoControlloForm.getFlSacchetti());
			campione.setDataAggiornamento(new Date());
			campione.setIdUtenteAggiornamento(id);
			campioneMapper.updateByPrimaryKeySelective(campione);	
			logger.debug("-- update del campione " + campione.getIdCampione());
		}
		
		logger.debug("END inserisciCheck");			
		}

    
    @Override
    public void salvaModificaOrganismiNocivi(NuovoControlloForm  controlloform, ModaliForm modaliForm, Long idUtente) throws BusinessException{
    	logger.debug("BEGIN salvaModificaOrganismiNocivi");
    	
    	logger.debug("-- idControlloFisicoSpecie ="+modaliForm.getIdControlloFisicoSpecie());
    	logger.debug("-- numPiante indicato nella pagina chiamante ="+modaliForm.getNumPiante());
    	logger.debug("-- numPianteSintomatiche indicato nella pagina chiamante ="+modaliForm.getNumPianteSintomatiche());
    	
    	logger.debug("--- 1) delete car_r_controllo_fisico_specie");    	
		CarRControlloFisicoSpecie controlloFisicoSpecie = controlloFisicoSpecieMapper.selectByPrimaryKey(modaliForm.getIdControlloFisicoSpecie());
		
		// Recupoero i dati da salvare nel/i nuovo/i record
		Long idControlloFisico = controlloFisicoSpecie.getIdControlloFisico();
		Long idGenere = controlloFisicoSpecie.getIdGenere();
		Long idSpecie = controlloFisicoSpecie.getIdSpecie();
		
		// Salvo i valori di numPiante e numPianteSintomatiche indicate nella pagina chiamante
		Long numPiante = null;
		if(modaliForm.getNumPiante() != null && !modaliForm.getNumPiante().equals("")){
			numPiante = Long.valueOf(modaliForm.getNumPiante());
		}
		
		Long numPianteSintomatiche = null;
		if(modaliForm.getNumPianteSintomatiche() != null && !modaliForm.getNumPianteSintomatiche().equals("")){
			numPianteSintomatiche = Long.valueOf(modaliForm.getNumPianteSintomatiche());
		}
		
		controlloFisicoSpecieMapper.deleteByPrimaryKey(modaliForm.getIdControlloFisicoSpecie());
		
		// Per ogni organismo nocivo selezionato nella modale, inserisco i record in car_r_controllo_fisico_specie
		String[] idOrgNocString = modaliForm.getIdOrgNociviFisico();		
		if(idOrgNocString != null && idOrgNocString.length>0){			
			logger.debug("-- numero di record da inserire = "+idOrgNocString.length);
			List<Long> idOrgNocLongList = Stream.of(idOrgNocString).map(Long::valueOf).collect(Collectors.toList());
			for (Iterator<Long> iterator = idOrgNocLongList.iterator(); iterator.hasNext();) {
				Long idOrgNoc = iterator.next();
				logger.debug("-- idOrgNoc da inserire ="+idOrgNoc);
				CarRControlloFisicoSpecie contrFisicoSpecieRecord = new CarRControlloFisicoSpecie();
				contrFisicoSpecieRecord.setIdControlloFisico(idControlloFisico);
				contrFisicoSpecieRecord.setDataInserimento(new Date());
				contrFisicoSpecieRecord.setIdUtenteInserimento(idUtente);
				contrFisicoSpecieRecord.setIdGenere(idGenere);
				contrFisicoSpecieRecord.setIdSpecie(idSpecie);
				contrFisicoSpecieRecord.setNumPiante(numPiante);
				contrFisicoSpecieRecord.setNumPianteSintomatiche(numPianteSintomatiche);
				contrFisicoSpecieRecord.setIdOrgNocivo(idOrgNoc);
				logger.debug("--- 2) insert car_r_controllo_fisico_specie");    
				controlloFisicoSpecieMapper.insert(contrFisicoSpecieRecord);
				logger.debug("-- id_controllo_fisico_specie inserito ="+contrFisicoSpecieRecord.getIdControlloFisicoSpecie());
			}
		}

		logger.debug("BEGIN salvaModificaOrganismiNocivi");
    }


	@Override
	public void eliminaControllo(Long idControllo, Long idUtente) throws BusinessException {
		logger.debug("--- 1) DELETE su car_t_controllo con idControllo="+idControllo);	 
		CarTControllo ex = new CarTControllo();
		ex.setIdControllo(idControllo);
		ex.setIdUtenteAggiornamento(idUtente);
		ex.setDataAggiornamento(new Date());
		ex.setIdStatoComunicazione(CaronteConstants.STATO_COMUNICAZIONE_ANNULLATA);
		carTControlloMapper.updateByPrimaryKeySelective(ex);

	}
	
	@Override
	public  Long getMaxIdDomandaByIdCentroAzPassaporto(Long idCentroAzPassaporto) throws BusinessException{
		return controlloMapper.getMaxIdDomandaByIdCentroAzPassaporto(idCentroAzPassaporto);
	}
	

	
}
	


