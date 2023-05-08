package it.aizoon.ersaf.business.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import it.aizoon.ersaf.business.IProtocolloEJB;
import it.aizoon.ersaf.dto.generati.CarDComune;
import it.aizoon.ersaf.dto.generati.CarDCostante;
import it.aizoon.ersaf.dto.generati.CarDCostanteExample;
import it.aizoon.ersaf.dto.generati.CarDProvincia;
import it.aizoon.ersaf.dto.generati.CarDRegione;
import it.aizoon.ersaf.dto.generati.CarTDomanda;
import it.aizoon.ersaf.dto.generati.CarTDomandaExample;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.exception.InternalException;
import it.aizoon.ersaf.integration.DomandeDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDComuneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDCostanteMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDProvinciaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDRegioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTDomandaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTSpedizioniereMapper;
import it.aizoon.ersaf.integration.rest.protocollo.ProtocolloRESTClient;
import it.aizoon.ersaf.integration.rest.protocollo.ProtocolloServiceFactory;
import it.aizoon.ersaf.integration.rest.protocollo.dto.DocumentoOutputDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.InputJsonDocumentoDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.InputJsonProtocolloDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.ProtocolloOutputDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.TokenOutputDto;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;

@SuppressWarnings("unused")
@Stateless(name = "Protocollo")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class ProtocolloEJB extends AbstractEJB<DomandeDAO, CarTDomandaMapper, CarTDomanda, CarTDomandaExample>
		implements IProtocolloEJB {

	private final String THIS_CLASS = "[" + ProtocolloEJB.class.getCanonicalName() + "]";

	@Inject
	CarTDomandaMapper carTDomandaMapper;
	
	@Inject
	CarTSpedizioniereMapper carTSpedizioniereMapper;
	
	@Inject
	CarDComuneMapper carDComuneMapper;
	
	@Inject
	CarDProvinciaMapper carDProvinciaMapper;
	
	@Inject
	CarDRegioneMapper carDRegioneMapper;
	
	@Inject
	CarDCostanteMapper carDCostanteMapper;

	@Override
	@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
	public ProtocolloOutputDto protocollaDomandaInoltrata(Long idDomanda, byte[] fileDaProtocollare, String nomeFileDaProtocollare) throws InternalException {
		logger.debug("BEGIN protocollaDomandaInoltrata");
		try {

			CarTDomanda domanda = carTDomandaMapper.selectByPrimaryKey(idDomanda);
			CarTSpedizioniere azienda = carTSpedizioniereMapper.selectByPrimaryKey(domanda.getIdSpedizioniere());
			ProtocolloRESTClient protocolloClient = ProtocolloServiceFactory.getRestServiceClient();
			
			ProtocolloOutputDto protocolloDto = null;
			
			//stringa da comporre= Caronte-tipocomunicazione num:00000 - azienda:ragioneSociale - cauaa=xxxxx
			StringBuilder oggettoProtocollo = new StringBuilder("Caronte - ");  			
			
			if (domanda.getIdTipoComunicazione().equals(CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP)) {
				oggettoProtocollo.append(CaronteConstants.PROTOCOLLO_OGGETTO_REGISTRAZIONE_RUOP);				
			} else if (domanda.getIdTipoComunicazione().equals(CaronteConstants.ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP)) {
				oggettoProtocollo.append(CaronteConstants.PROTOCOLLO_OGGETTO_VARIAZIONE_RUOP);
			} else if (domanda.getIdTipoComunicazione().equals(CaronteConstants.ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP)) {
				oggettoProtocollo.append(CaronteConstants.PROTOCOLLO_OGGETTO_CANCELLAZIONE_RUOP);
			} else if (domanda.getIdTipoComunicazione().equals(CaronteConstants.ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO)) {
				oggettoProtocollo.append(CaronteConstants.PROTOCOLLO_OGGETTO_PASSAPORTO_RUOP);
			} else {
				oggettoProtocollo.append(CaronteConstants.PROTOCOLLO_OGGETTO_GENERICO);
			}
			oggettoProtocollo.append(domanda.getCodDomanda());
			oggettoProtocollo.append(" - Azienda: "+ azienda.getDenomSpedizioniere());
			oggettoProtocollo.append(" - CUAA: " + azienda.getCuaa());
			logger.debug("-- oggetto del protocollo " + oggettoProtocollo.toString());
				
			// recupero il token dalla tabella delle costanti
			logger.debug("-- recupero il token dalla tabella delle costanti");
			CarDCostante costanteToken = getTokenDB();
			String token = null;
			if (costanteToken != null ) {
				token = costanteToken.getValoreCostante();
				if (token == null || token.equals("0")) {
					// e' presente la constante ma non è mai stata volorizzata, allora genero un nuovo token
					token = generaSalvaTokenDB(costanteToken.getIdCostante(), protocolloClient);
				}					
			} else {
				//TODO non è presente la costante, gestire il caso rilanciando eccezione
				logger.debug("-- Protocollo, costante token non presente sul DB");
				throw new InternalException("Errore in fase di protocollazione");
			}

			logger.debug("-- nomeFileStampa da protocollare: " + nomeFileDaProtocollare);
			InputJsonDocumentoDto inputJsonDoc = setJsonDocumento(domanda, azienda, nomeFileDaProtocollare, oggettoProtocollo.toString());

			logger.debug("-- ******** CHIAMATA A creaDocumentoDaProtocollare");
			DocumentoOutputDto documentoDto = protocolloClient.creaDocumentoDaProtocollare(token, inputJsonDoc, fileDaProtocollare, nomeFileDaProtocollare);
			logger.debug("-- id documento tornato : " + documentoDto.getId());
			
			/* In caso di Token scaduto(1800 sec), viene popolato un oggetto Fault con il message Invalid Credentials
			 * in questo caso rigeneriamo il token e richiamiamo il servizio
			 */			
			if (documentoDto.getFault() != null) {
				logger.debug("*** caso di Token scaduto ***");
				logger.debug("-- Protocollo documentoDto.getFault().getCode: " + documentoDto.getFault().getCode());
				logger.debug("-- Protocollo documentoDto.getFault().getMessage: " + documentoDto.getFault().getMessage());
				if (documentoDto.getFault().getCode() == CaronteConstants.PROTOCOLLO_ERROR_CODE 
						&& documentoDto.getFault().getMessage().equalsIgnoreCase(CaronteConstants.PROTOCOLLO_MESSAGE_ERROR_INVALID_CREDENTIALS)) {					
					token = generaSalvaTokenDB(costanteToken.getIdCostante(), protocolloClient);
					logger.debug("-- Protocollo, generato nuovo token: " + token);	
					// richiamo il servizio
					logger.debug("-- ******** CHIAMATA A creaDocumentoDaProtocollare");
					documentoDto = protocolloClient.creaDocumentoDaProtocollare(token, inputJsonDoc, fileDaProtocollare, nomeFileDaProtocollare);
					logger.debug("-- id documento tornato : " + documentoDto.getId());
				}				
			}
			
			
			//String protocollo = null;			
			// è 0 quando non viene generato un documento
			if (documentoDto.getId() > 0) {
						
				InputJsonProtocolloDto inputJsonProt = setJsonProtocollo(azienda, oggettoProtocollo.toString()); 	
				logger.debug("-- ******** CHIAMATA A protocollaDocumentoByIdDocumento");
				logger.debug("-- TOKEN = "+token);
				logger.debug("-- documentoDto.getId() = "+documentoDto.getId());
				protocolloDto = protocolloClient.protocollaDocumentoByIdDocumento(token,"" + documentoDto.getId(), inputJsonProt);
				
				/* In caso di Token scaduto(1800 sec), viene popolato un oggetto Fault con il message Invalid Credentials
				 * in questo caso rigeneriamo il token e richiamiamo il servizio
				 */
				if (protocolloDto.getFault() != null) {
					logger.debug("*** caso di Token scaduto ***");
					logger.debug("-- Protocollo protocolloDto.getFault().getCode: " + protocolloDto.getFault().getCode());
					logger.debug("-- Protocollo protocolloDto.getFault().getMessage: " + protocolloDto.getFault().getMessage());
					if (protocolloDto.getFault().getCode() == CaronteConstants.PROTOCOLLO_ERROR_CODE && protocolloDto.getFault().getMessage().equalsIgnoreCase(CaronteConstants.PROTOCOLLO_MESSAGE_ERROR_INVALID_CREDENTIALS)) {					
						token = generaSalvaTokenDB(costanteToken.getIdCostante(), protocolloClient);
						logger.debug("-- Protocollo, generato nuovo token: " + token);	
						// richiamo il servizio
						logger.debug("-- ******** CHIAMATA A protocollaDocumentoByIdDocumento");
						protocolloDto = protocolloClient.protocollaDocumentoByIdDocumento(token,"" + documentoDto.getId(), inputJsonProt);						
					}				
				}
				/*if (protocollo.equalsIgnoreCase(CaronteConstants.PROTOCOLLO_MESSAGE_ERROR_INVALID_CREDENTIALS)) {
					logger.debug("*** caso di Token scaduto ***");
					token = generaSalvaTokenDB(costanteToken.getIdCostante(), protocolloClient);
					logger.debug("-- Protocollo, generato nuovo token: " + token);	
					// richiamo il servizio
					logger.debug("-- ******** CHIAMATA A protocollaDocumentoByIdDocumento");
					logger.debug("-- TOKEN = "+token);
					logger.debug("-- documentoDto.getId() = "+documentoDto.getId());
					protocollo = protocolloClient.protocollaDocumentoByIdDocumento(token,"" + documentoDto.getId(), inputJsonProt);
					logger.debug("-- Protocollo tornato : " + protocollo);
				}*/	
											
				
			}
			
			return protocolloDto;

		} catch (InternalException ie) {
			logger.error("Errore in fase di protocollazione: " + ie.getMessage());
			throw ie;
		}
		finally{
			logger.debug("END protocollaDomandaInoltrata");
		}

	}
	
	private InputJsonDocumentoDto setJsonDocumento(CarTDomanda domanda, CarTSpedizioniere azienda, String nomeFileDaProtocollare, String oggettoProtocollo) {
		logger.debug("BEGIN setJsonDocumento");
		
		InputJsonDocumentoDto inputJsonDoc = new InputJsonDocumentoDto();
		inputJsonDoc.setNomeFile(nomeFileDaProtocollare);
		logger.debug("-- NomeFile ="+inputJsonDoc.getNomeFile());
		
		inputJsonDoc.setMetadocumento(CaronteConstants.PROTOCOLLO_METADOCUMENTO_GENERICO);// non modificabile, altrimenti errore
		logger.debug("-- Metadocumento ="+inputJsonDoc.getMetadocumento());
		
		inputJsonDoc.setMetadocumentoAllegato(null);
		logger.debug("-- MetadocumentoAllegato ="+inputJsonDoc.getMetadocumentoAllegato());
		
		inputJsonDoc.setOggetto(oggettoProtocollo);
		logger.debug("-- Oggetto ="+inputJsonDoc.getOggetto());
		
		inputJsonDoc.setProtocollo(null);
		logger.debug("-- Protocollo ="+inputJsonDoc.getProtocollo());
		
		inputJsonDoc.setDataProtocollo(null);
		logger.debug("-- DataProtocollo ="+inputJsonDoc.getDataProtocollo());
		
		inputJsonDoc.setId(null);
		logger.debug("-- Id ="+inputJsonDoc.getId());
		
		inputJsonDoc.setFileName(nomeFileDaProtocollare);
		logger.debug("-- FileName ="+inputJsonDoc.getFileName());
		
		inputJsonDoc.setObject(oggettoProtocollo);
		logger.debug("-- Object ="+inputJsonDoc.getObject());
		
		logger.debug("END setJsonDocumento");

		return inputJsonDoc;
	}
	
	private InputJsonProtocolloDto setJsonProtocollo(CarTSpedizioniere azienda, String oggettoProtocollo) {
		logger.debug("BEGIN setJsonProtocollo");
		
		CarDComune comune = carDComuneMapper.selectByPrimaryKey(azienda.getIdComune());
		CarDProvincia provincia = carDProvinciaMapper.selectByPrimaryKey(comune.getIdProvincia());
		CarDRegione regione = carDRegioneMapper.selectByPrimaryKey(provincia.getIdRegione());
		
		InputJsonProtocolloDto inputJsonProt = new InputJsonProtocolloDto();
			
		inputJsonProt.setIndirizzoMittente(azienda.getIndirizzo());
		logger.debug("-- IndirizzoMittente ="+inputJsonProt.getIndirizzoMittente());
		
		inputJsonProt.setCittaMittente(comune.getDenomComune());
		logger.debug("-- CittaMittente ="+inputJsonProt.getCittaMittente());
		
		inputJsonProt.setComuneMittente(comune.getDenomComune());
		logger.debug("-- ComuneMittente ="+inputJsonProt.getComuneMittente());
		
		inputJsonProt.setProvinciaMittente(provincia.getDenomProvincia());
		logger.debug("-- ProvinciaMittente ="+inputJsonProt.getProvinciaMittente());
		
		inputJsonProt.setCapMittente(azienda.getCap());
		logger.debug("-- CapMittente ="+inputJsonProt.getCapMittente());
		
		inputJsonProt.setRegioneMittente(regione.getDenomRegione());
		logger.debug("-- RegioneMittente ="+inputJsonProt.getRegioneMittente());
		
		inputJsonProt.setDescrizioneMittente(azienda.getDenomSpedizioniere());
		logger.debug("-- DescrizioneMittente ="+inputJsonProt.getDescrizioneMittente());
		
		inputJsonProt.setEmail(azienda.getEmail());
		logger.debug("-- Email ="+inputJsonProt.getEmail());
		
		inputJsonProt.setFax(azienda.getFax());
		logger.debug("-- Fax ="+inputJsonProt.getFax());
		
		inputJsonProt.setTelefono(azienda.getTelefono() != null ? azienda.getTelefono() : azienda.getCellulare());
		logger.debug("-- Telefono ="+inputJsonProt.getTelefono());
		
		inputJsonProt.setPartitaIva(azienda.getPartitaIva());
		logger.debug("-- PartitaIva ="+inputJsonProt.getPartitaIva());
		
		inputJsonProt.setCodFisc(azienda.getCodiceFiscale() != null ? azienda.getCodiceFiscale() : azienda.getCuaa());
		logger.debug("-- CodFisc ="+inputJsonProt.getCodFisc());
		
		String codDestinatario = (String) CaronteConstants.getProperties().get(CaronteConstants.PROTOCOLLO_COD_DESTINATARIO);
		inputJsonProt.setCodiceDestinatario(codDestinatario);//("SYSTEM_CARONTE");
		logger.debug("-- CodiceDestinatario ="+inputJsonProt.getCodiceDestinatario());
		
		
		inputJsonProt.setOggettoProtocollo(oggettoProtocollo);//"oggettoProtocollo");
		logger.debug("-- OggettoProtocollo ="+inputJsonProt.getOggettoProtocollo());
		
		inputJsonProt.setTipoDocumento(CaronteConstants.PROTOCOLLO_TIPO_DOCUMENTO_DOMANDA);
		logger.debug("-- TipoDocumento ="+inputJsonProt.getTipoDocumento());
		
		// inputJsonProt.setRegioneMittente("regioneMittente"); ripetuto nel json, non ha dato errori senza questo campo
		inputJsonProt.setTipoAllegati("Allegati");
		logger.debug("-- TipoAllegati ="+inputJsonProt.getTipoAllegati());
		
		//inputJsonProt.setMezzoSpedizione("Raccomandata"); non obbligatorio		
		String dataToday = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());		
		inputJsonProt.setDataArrivo(dataToday);
		logger.debug("-- DataArrivo ="+inputJsonProt.getDataArrivo());
		
		inputJsonProt.setDataDocumento(dataToday);
		logger.debug("-- DataDocumento ="+inputJsonProt.getDataDocumento());
		
		inputJsonProt.setNumeroAllegati(0);
		logger.debug("-- NumeroAllegati ="+inputJsonProt.getNumeroAllegati());
		
		logger.debug("END setJsonProtocollo");
		return inputJsonProt;
		
	}
	
	private CarDCostante getTokenDB() {
		logger.debug("BEGIN getTokenDB");
		
		CarDCostanteExample costEx = new CarDCostanteExample();
		costEx.createCriteria().andCodCostanteEqualTo(CaronteConstants.PROTOCOLLO_COD_COSTANTE_TOKEN);
		List<CarDCostante> costantiList = carDCostanteMapper.selectByExample(costEx);
		if (costantiList != null && costantiList.size() > 0) {
			logger.debug("END getTokenDB");
			return costantiList.get(0);
		}
		
		logger.debug("END getTokenDB");

		return null;
	}
	
	private String generaSalvaTokenDB(Long idCostante, ProtocolloRESTClient protocolloClient) throws InternalException {
		logger.debug("BEGIN generaSalvaTokenDB");
		
		// chiamo il servizio per generarlo
		logger.debug("****** CHIAMATA A getToken *****");
		TokenOutputDto tokenDto = protocolloClient.getToken();
		// salvo sulla tabella delle costanti il nuovo token
		CarDCostante costanteToken = carDCostanteMapper.selectByPrimaryKey(idCostante);
		costanteToken.setValoreCostante(tokenDto.getAccess_token());
		carDCostanteMapper.updateByPrimaryKey(costanteToken);
		
		logger.debug("END generaSalvaTokenDB");

		return tokenDto.getAccess_token();
	}

}
