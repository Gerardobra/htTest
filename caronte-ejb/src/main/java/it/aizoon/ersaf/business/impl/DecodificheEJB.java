package it.aizoon.ersaf.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.apache.ibatis.annotations.Param;

import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.dto.CentroAziendaleDto;
import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.DomandaDto;
import it.aizoon.ersaf.dto.ModuloDTO;
import it.aizoon.ersaf.dto.TipoDomandaDTO;
import it.aizoon.ersaf.dto.generati.CarDAssociazioneSezione;
import it.aizoon.ersaf.dto.generati.CarDAssociazioneSezioneExample;
import it.aizoon.ersaf.dto.generati.CarDAutoreEppo;
import it.aizoon.ersaf.dto.generati.CarDClasse;
import it.aizoon.ersaf.dto.generati.CarDClasseExample;
import it.aizoon.ersaf.dto.generati.CarDComune;
import it.aizoon.ersaf.dto.generati.CarDComuneExample;
import it.aizoon.ersaf.dto.generati.CarDConoscenzaProfessionale;
import it.aizoon.ersaf.dto.generati.CarDConoscenzaProfessionaleExample;
import it.aizoon.ersaf.dto.generati.CarDCostante;
import it.aizoon.ersaf.dto.generati.CarDCostanteExample;
import it.aizoon.ersaf.dto.generati.CarDGruppoZonaProtetta;
import it.aizoon.ersaf.dto.generati.CarDLingua;
import it.aizoon.ersaf.dto.generati.CarDLinguaExample;
import it.aizoon.ersaf.dto.generati.CarDMateriale;
import it.aizoon.ersaf.dto.generati.CarDMetodoProduzione;
import it.aizoon.ersaf.dto.generati.CarDMetodoProduzioneExample;
import it.aizoon.ersaf.dto.generati.CarDMezzoPagamento;
import it.aizoon.ersaf.dto.generati.CarDMezzoPagamentoExample;
import it.aizoon.ersaf.dto.generati.CarDModoTrasporto;
import it.aizoon.ersaf.dto.generati.CarDModoTrasportoExample;
import it.aizoon.ersaf.dto.generati.CarDNaturaCollo;
import it.aizoon.ersaf.dto.generati.CarDNaturaColloExample;
import it.aizoon.ersaf.dto.generati.CarDNazione;
import it.aizoon.ersaf.dto.generati.CarDNazioneExample;
import it.aizoon.ersaf.dto.generati.CarDNormaVerbale;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivoExample;
import it.aizoon.ersaf.dto.generati.CarDProdotto;
import it.aizoon.ersaf.dto.generati.CarDProdottoExample;
import it.aizoon.ersaf.dto.generati.CarDProvincia;
import it.aizoon.ersaf.dto.generati.CarDProvinciaExample;
import it.aizoon.ersaf.dto.generati.CarDRegione;
import it.aizoon.ersaf.dto.generati.CarDRegioneExample;
import it.aizoon.ersaf.dto.generati.CarDRequisitoProfessionale;
import it.aizoon.ersaf.dto.generati.CarDRequisitoProfessionaleExample;
import it.aizoon.ersaf.dto.generati.CarDRuolo;
import it.aizoon.ersaf.dto.generati.CarDRuoloExample;
import it.aizoon.ersaf.dto.generati.CarDStatoAzienda;
import it.aizoon.ersaf.dto.generati.CarDStatoAziendaExample;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazioneExample;
import it.aizoon.ersaf.dto.generati.CarDStrutturaAttrezzatura;
import it.aizoon.ersaf.dto.generati.CarDStrutturaAttrezzaturaExample;
import it.aizoon.ersaf.dto.generati.CarDTipoAttivita;
import it.aizoon.ersaf.dto.generati.CarDTipoAttivitaExample;
import it.aizoon.ersaf.dto.generati.CarDTipoCertificato;
import it.aizoon.ersaf.dto.generati.CarDTipoCertificatoExample;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazioneExample;
import it.aizoon.ersaf.dto.generati.CarDTipoImballaggio;
import it.aizoon.ersaf.dto.generati.CarDTipoImballaggioExample;
import it.aizoon.ersaf.dto.generati.CarDTipoIrrigazione;
import it.aizoon.ersaf.dto.generati.CarDTipoIrrigazioneExample;
import it.aizoon.ersaf.dto.generati.CarDTipoModulo;
import it.aizoon.ersaf.dto.generati.CarDTipoProdotto;
import it.aizoon.ersaf.dto.generati.CarDTipoProdottoExample;
import it.aizoon.ersaf.dto.generati.CarDTipoRespAzienda;
import it.aizoon.ersaf.dto.generati.CarDTipoRespAziendaExample;
import it.aizoon.ersaf.dto.generati.CarDTipoSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarDTipoSpedizioniereExample;
import it.aizoon.ersaf.dto.generati.CarDTipologia;
import it.aizoon.ersaf.dto.generati.CarDTipologiaCampione;
import it.aizoon.ersaf.dto.generati.CarDTipologiaCampioneExample;
import it.aizoon.ersaf.dto.generati.CarDTipologiaControllo;
import it.aizoon.ersaf.dto.generati.CarDTipologiaExample;
import it.aizoon.ersaf.dto.generati.CarDTipologiaMisura;
import it.aizoon.ersaf.dto.generati.CarDTipologiaMisuraExample;
import it.aizoon.ersaf.dto.generati.CarDTipologiaPassaporto;
import it.aizoon.ersaf.dto.generati.CarDTipologiaPassaportoExample;
import it.aizoon.ersaf.dto.generati.CarDTipologiaSemente;
import it.aizoon.ersaf.dto.generati.CarDTrattamento;
import it.aizoon.ersaf.dto.generati.CarDTrattamentoExample;
import it.aizoon.ersaf.dto.generati.CarDUfficioDoganale;
import it.aizoon.ersaf.dto.generati.CarDUfficioDoganaleExample;
import it.aizoon.ersaf.dto.generati.CarDUnitaMisura;
import it.aizoon.ersaf.dto.generati.CarDUnitaMisuraExample;
import it.aizoon.ersaf.dto.generati.CarDVoce;
import it.aizoon.ersaf.dto.generati.CarDVoceExample;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoStrAtt;
import it.aizoon.ersaf.dto.generati.CarRSpedizAssocSezione;
import it.aizoon.ersaf.dto.generati.CarRSpedizAssocSezioneExample;
import it.aizoon.ersaf.dto.generati.CarTControllo;
import it.aizoon.ersaf.dto.generati.CarTControlloExample;
import it.aizoon.ersaf.dto.generati.CarTDatiDomanda;
import it.aizoon.ersaf.dto.generati.CarTDatiDomandaExample;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniereExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.integration.DecodificheDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.AutoreEppoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.DecodificheMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDAssociazioneSezioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDClasseMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDComuneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDConoscenzaProfessionaleMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDCostanteMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDGruppoZonaProtettaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDLinguaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDMetodoProduzioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDMezzoPagamentoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDModoTrasportoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDNaturaColloMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDNazioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDOrgNocivoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDProdottoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDProvinciaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDRegioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDRequisitoProfessionaleMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDRuoloMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDStatoAziendaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDStatoComunicazioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDStrutturaAttrezzaturaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoAttivitaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoCertificatoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoComunicazioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoImballaggioMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoIrrigazioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoModuloMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoProdottoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoRespAziendaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipoSpedizioniereMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipologiaCampioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipologiaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipologiaMisuraMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTipologiaPassaportoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDTrattamentoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDUfficioDoganaleMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDUnitaMisuraMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDVoceMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRSpedizAssocSezioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTCentroAziendaleMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTControlloMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTIspettoreMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTSpedizioniereMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;

/**
 * @author Ivan Morra
 *
 */

@Stateless(name = "Decodifiche")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })

public class DecodificheEJB extends
  AbstractEJB<DecodificheDAO, CarDProvinciaMapper, CarDProvincia, CarDProvinciaExample> implements IDecodificheEJB {

	private final String THIS_CLASS = "[" + DecodificheEJB.class.getCanonicalName() + "]";

	@Inject
	CarDProvinciaMapper provinciaMapper;

	@Inject
	CarDComuneMapper comuneMapper;
	
	@Inject
	CarDAssociazioneSezioneMapper associazioneSezioneMapper;

	@Inject
	CarDRegioneMapper regioneMapper;

	@Inject
	CarDUfficioDoganaleMapper ufficioDoganaleMapper;

	@Inject
	CarDTipoSpedizioniereMapper tipoSpedizioniereMapper;

	@Inject
	CarDNazioneMapper nazioneMapper;

	@Inject
	CarDModoTrasportoMapper modoTrasportoMapper;

	@Inject
	CarDUnitaMisuraMapper unitaMisuraMapper;

	@Inject
	CarDTrattamentoMapper trattamentoMapper;

	@Inject
	CarDMezzoPagamentoMapper mezzoPagamentoMapper;

	@Inject
	CarDTipoProdottoMapper tipoProdottoMapper;

	@Inject
	CarDCostanteMapper carDCostanteMapper;

	@Inject
	CarDRuoloMapper ruoloMapper;

	@Inject
	CarTSpedizioniereMapper spedizioniereMapper;

	@Inject
    CarRSpedizAssocSezioneMapper spedizioniereSezioneMapper;
	
	@Inject 
	CarDLinguaMapper linguaMapper;
	
	@Inject
	CarDClasseMapper classeMapper;

	@Inject
	CarDTipoCertificatoMapper certificatoMapper;
	
	@Inject
	AutoreEppoMapper autoreEppoMapper;
	
	@Inject
	CarDProdottoMapper prodottoMapper;
	
	@Inject
	CarDNaturaColloMapper naturaColloMapper;
	
	@Inject
	CarDTipoImballaggioMapper tipoImballaggioMapper;

	@Inject
	CarDTipoComunicazioneMapper tipoComunicazioneMapper;
	
	@Inject
	CarDStatoComunicazioneMapper statoComunicazioneMapper;
	
	@Inject
	CarTCentroAziendaleMapper centroAziendaleMapper;
	
	@Inject
	DecodificheMapper decodificheMapper;
	
	@Inject
	CarDTipologiaMapper tipologiaMapper;
	
	@Inject
	CarDVoceMapper voceMapper;
	
	@Inject
	CarDGruppoZonaProtettaMapper gruppoZonaProtettaMapper;
	
	@Inject
	CarDTipoAttivitaMapper tipoAttivitaMapper;
	
	@Inject
	  CarTIspettoreMapper ispettoreMapper;
	
	@Inject
	CarDStatoAziendaMapper aziendaMapper;
	
	@Inject
	CarDTipoModuloMapper tipoModuloMapper;
	
	@Inject
	CarDOrgNocivoMapper orgNocivoMapper;
	
	@Inject
	CarDMetodoProduzioneMapper metodoProduzioneMapper;
	
	@Inject
	CarDTipoIrrigazioneMapper tipoIrrigazioneMapper;
	
	@Inject
	CarDConoscenzaProfessionaleMapper conoscenzaProfessionaleMapper;
	
	@Inject
	CarDRequisitoProfessionaleMapper requisitoProfessionaleMapper;
	
	@Inject
	CarDStrutturaAttrezzaturaMapper strutturaAttrezzaturaMapper;
	
	@Inject
	CarDTipologiaCampioneMapper tipologiaCampioneMapper;
	
	@Inject
	CarDTipologiaMisuraMapper tipologiaMisuraMapper;
	
	@Inject
	CarDTipologiaPassaportoMapper tipologiaPassaportoMapper;
	
	@Inject
	CarDTipoRespAziendaMapper tipoRespAziendaMapper;

	
	@Override
	public List<CarDUfficioDoganale> getUfficioDoganale() throws BusinessException {
	  return getUfficioDoganale(false);
	}
	
	@Override
	public List<CarDUfficioDoganale> getUfficioDoganale(boolean utilizzato) throws BusinessException {
    CarDUfficioDoganaleExample filtri = new CarDUfficioDoganaleExample();
    
    filtri.createCriteria().andUtilizzatoEqualTo(utilizzato);
    
    /*if (utilizzato) {
      filtri.createCriteria().andUtilizzatoEqualTo(utilizzato);
    }*/
    
    filtri.setOrderByClause("denom_ufficio_doganale");
    return ufficioDoganaleMapper.selectByExample(filtri);
  }

	@Override
	public List<CarDProvincia> getListaProvince() throws BusinessException {
		CarDProvinciaExample filtri = new CarDProvinciaExample();
		filtri.setOrderByClause("denom_provincia");

		return provinciaMapper.selectByExample(filtri);
	}

	@Override
	public List<CarDComune> getListaComuni(Long idProvincia) throws BusinessException {
		CarDComuneExample filtri = new CarDComuneExample();
		filtri.createCriteria().andIdProvinciaEqualTo(idProvincia);
		filtri.setOrderByClause("denom_comune");

		return comuneMapper.selectByExample(filtri);
	}
	
	@Override
  public List<CarDComune> getListaComuni() throws BusinessException {
    CarDComuneExample filtri = new CarDComuneExample();
    filtri.setOrderByClause("denom_comune");

    return comuneMapper.selectByExample(filtri);
  }

	@Override
	public List<CarDRegione> getListaRegioni() throws BusinessException {
		CarDRegioneExample filtri = new CarDRegioneExample();
		filtri.setOrderByClause("denom_regione");

		return regioneMapper.selectByExample(filtri);
	}

	@Override
	public List<CarDLingua> getListaLingue() throws BusinessException{
		CarDLinguaExample orderByFilter = new CarDLinguaExample();
		orderByFilter.setOrderByClause("desc_lingua");
		return linguaMapper.selectByExample(orderByFilter);
	}


	@Override
	public List<CarDTipoSpedizioniere> getListaTipiSpedizioniere() throws BusinessException {
		CarDTipoSpedizioniereExample ex = new CarDTipoSpedizioniereExample();
		ex.setOrderByClause("ordine");
		return tipoSpedizioniereMapper.selectByExample(ex);
	}

	@Override
	public CarDTipoSpedizioniere getTipoSpedizioniere(Long idTipoSpedizioniere) throws BusinessException {
		return tipoSpedizioniereMapper.selectByPrimaryKey(idTipoSpedizioniere);
	}

	@Override
	public List<CarDNazione> getListaNazioni(boolean escludiItalia) throws BusinessException {
		CarDNazioneExample filtri = new CarDNazioneExample();

		if (escludiItalia) {
			filtri.createCriteria().andCodNazioneNotEqualTo(CaronteConstants.CODICE_NAZIONE_ITALIA);
		}
		
		filtri.setOrderByClause("denom_nazione");

		return nazioneMapper.selectByExample(filtri);
	}

	@Override
	public List<CarDModoTrasporto> getListaModiTrasporto() throws BusinessException {
		CarDModoTrasportoExample filtri = new CarDModoTrasportoExample();
		filtri.setOrderByClause("desc_modo_trasporto");
		return modoTrasportoMapper.selectByExample(filtri);
	}

	@Override
	public List<CarDUnitaMisura> getListaUnitaMisura() throws BusinessException {
		CarDUnitaMisuraExample filtri = new CarDUnitaMisuraExample();
		filtri.setOrderByClause("desc_unita_misura");
		return unitaMisuraMapper.selectByExample(filtri);
	}

	@Override
	public List<CarDTrattamento> getListaTrattamenti() throws BusinessException {
		CarDTrattamentoExample filtri = new CarDTrattamentoExample();
		filtri.setOrderByClause("desc_trattamento");
		return trattamentoMapper.selectByExample(filtri);
	}

	@Override
	public List<CarDMezzoPagamento> getListaMezziPagamento() throws BusinessException {
		CarDMezzoPagamentoExample filtri = new CarDMezzoPagamentoExample();
		filtri.setOrderByClause("desc_mezzo_pagamento");
		return mezzoPagamentoMapper.selectByExample(filtri);
	}

	@Override
	public List<CarDTipoProdotto> getListaTipiProdotto() throws BusinessException {
		CarDTipoProdottoExample filtri = new CarDTipoProdottoExample();
		filtri.setOrderByClause("desc_tipo_prodotto");
		return tipoProdottoMapper.selectByExample(filtri);
	}

	@Override
	public CarDUnitaMisura getUnitaMisuraTipoProdotto(Long idTipoProdotto) throws BusinessException {
		CarDUnitaMisura result = null;
		CarDTipoProdotto tipoProdotto = null;

		if (idTipoProdotto != null) {
			tipoProdotto = tipoProdottoMapper.selectByPrimaryKey(idTipoProdotto);

			if (tipoProdotto != null && tipoProdotto.getIdUnitaMisura() != null) {
				result = unitaMisuraMapper.selectByPrimaryKey(tipoProdotto.getIdUnitaMisura());
			}
		}

		return result;
	}

	@Override
	public CarDCostante getCostante(String codCostante) {

		CarDCostanteExample filter = new CarDCostanteExample();
		filter.createCriteria().andCodCostanteEqualTo(codCostante);

		List<CarDCostante> listaCostanti = carDCostanteMapper.selectByExample(filter);

		if (listaCostanti.isEmpty()) {
			return new CarDCostante();
		}

		return listaCostanti.get(0);
	}
	
	@Override
	public List<CarTSpedizioniere> getListaSpedizionieri() throws BusinessException {
		CarTSpedizioniereExample filtri = new CarTSpedizioniereExample();
		filtri.setOrderByClause("denom_spedizioniere");
		return spedizioniereMapper.selectByExample(filtri);
	}
	
	@Override
	public List<CarTSpedizioniere> getListaSpedizionieri(Long idAssociazioneSezione) throws BusinessException {
    CarTSpedizioniereExample filtri = new CarTSpedizioniereExample();
    filtri.setOrderByClause("denom_spedizioniere");
    
    List<CarTSpedizioniere> result = spedizioniereMapper.selectByExample(filtri);
    
    if (idAssociazioneSezione != null && result.size() > 0) {
      CarRSpedizAssocSezioneExample exampleSezione = new CarRSpedizAssocSezioneExample();
      exampleSezione.createCriteria().andIdAssociazioneSezioneEqualTo(idAssociazioneSezione);
      List<CarRSpedizAssocSezione> associazioni = 
          spedizioniereSezioneMapper.selectByExample(exampleSezione);
      
      Iterator<CarTSpedizioniere> iterSpedizionieri = result.iterator();
      
      while (iterSpedizionieri.hasNext()) {
        CarTSpedizioniere spedizioniere = iterSpedizionieri.next();
        
        String denomSpedizioniere = null;
        if(spedizioniere.getCodiceRuop() != null){
          denomSpedizioniere = spedizioniere.getCodiceRuop().toUpperCase();
          if(spedizioniere.getDenomSpedizioniere() != null){
        	  denomSpedizioniere+=" - "+spedizioniere.getDenomSpedizioniere().toUpperCase();
          }
        }
        else{
        	if(spedizioniere.getDenomSpedizioniere() != null){
        		denomSpedizioniere = spedizioniere.getDenomSpedizioniere().toUpperCase();
        	}
        }
        spedizioniere.setDenomSpedizioniere(denomSpedizioniere);	
        
        
        boolean trovato = false;
        Iterator<CarRSpedizAssocSezione> iterAssociazioni = associazioni.iterator();
        
        while (iterAssociazioni.hasNext()) {
          if (spedizioniere.getIdSpedizioniere().equals(iterAssociazioni.next().getIdSpedizioniere())) {
            trovato = true;
            iterAssociazioni.remove();
            break;
          }
        }
        
        if (!trovato) {
          iterSpedizionieri.remove();
        }
      }
      
    }
    
    return result;
  }

	@Override
	public List<CarDRuolo> getTipiRuolo() throws BusinessException {
	  CarDRuoloExample filtri = new CarDRuoloExample();
    filtri.setOrderByClause("denom_ruolo");

    return ruoloMapper.selectByExample(filtri);
	}

	@Override
	public List<CarDRegione> getListaRegioni(Long idNazione) throws BusinessException {
		CarDRegioneExample filtri = new CarDRegioneExample();
		filtri.createCriteria().andIdNazioneEqualTo(idNazione);
		return regioneMapper.selectByExample(filtri);
	}

	@Override
	public List<CarDProvincia> getListaProvince(Long idRegione) throws BusinessException {
		CarDProvinciaExample filtri = new CarDProvinciaExample();
		filtri.createCriteria().andIdRegioneEqualTo(idRegione);
		filtri.setOrderByClause("denom_provincia");
		return provinciaMapper.selectByExample(filtri);
	}

	@Override
	public CarDLingua getLinguaFromCodice(String codLingua) throws BusinessException {
		CarDLinguaExample filterLinguaByCode = new CarDLinguaExample();
		filterLinguaByCode.createCriteria().andCodLinguaEqualTo(codLingua);
		ArrayList<CarDLingua> queryResultLingua = (ArrayList<CarDLingua>)linguaMapper.selectByExample(filterLinguaByCode);
		if(!queryResultLingua.isEmpty())
			return queryResultLingua.get(0);
		return null;
	}


	@Override
	public List<CarDTipoCertificato> getListaTipiCertificato() throws BusinessException {
		CarDTipoCertificatoExample filtri = new CarDTipoCertificatoExample();
		filtri.setOrderByClause("id_tipo_certificato");

		return certificatoMapper.selectByExample(filtri);
	}

	@Override
	public List<CarDAutoreEppo> getListaAutoriEppo(String denomAutore) throws BusinessException {
		return autoreEppoMapper.getListaAutoriEppoDenominazione(denomAutore);
	}

	@Override
	public List<CarDClasse> getListaClassi() throws BusinessException {
		CarDClasseExample filtri = new CarDClasseExample();
		filtri.setOrderByClause("denom_classe");
		return classeMapper.selectByExample(filtri);
	}

	@Override
	public CarDComune getComuneByPrimaryKey(Long idComune) throws BusinessException {
		return comuneMapper.selectByPrimaryKey(idComune);
	}

	@Override
	public List<CarDAssociazioneSezione> getListaSezioni() throws BusinessException {
		CarDAssociazioneSezioneExample ass = new CarDAssociazioneSezioneExample();
		ass.setOrderByClause("id_associazione_sezione");
		return associazioneSezioneMapper.selectByExample(ass);
	}

	@Override
	public List<CarDProdotto> getListaProdotti() throws BusinessException {
		CarDProdottoExample filtri = new CarDProdottoExample();
		filtri.setOrderByClause("denom_prodotto");
		return prodottoMapper.selectByExample(filtri);
	}

	@Override
	public List<CarDNaturaCollo> getListaNaturaColli() throws BusinessException {
		CarDNaturaColloExample filtri = new CarDNaturaColloExample();
		filtri.setOrderByClause("desc_natura_collo");
		return naturaColloMapper.selectByExample(filtri);
	}

	@Override
	public CarDUnitaMisura getUnitaMisuraProdotto(Long idProdotto) throws BusinessException {
		CarDTipoProdotto tipoProdotto = null;
		CarDProdotto prodotto = null;
		CarDUnitaMisura misura = null;

		if (idProdotto != null) {
			prodotto = prodottoMapper.selectByPrimaryKey(idProdotto);

			if (prodotto != null && prodotto.getIdTipoProdotto() != null) {
				tipoProdotto = tipoProdottoMapper.selectByPrimaryKey(prodotto.getIdTipoProdotto());
				
				if(tipoProdotto != null && tipoProdotto.getIdUnitaMisura() != null) {
					misura = unitaMisuraMapper.selectByPrimaryKey(tipoProdotto.getIdUnitaMisura());
				}
			}
			
		}

		return misura;
	}

	@Override
  public CarDClasse getClasseProdotto(Long idClasseProdotto) throws BusinessException {
    return classeMapper.selectByPrimaryKey(idClasseProdotto);
  }
	
	@Override
  public List<CarDTipoImballaggio> getListaTipiImballaggi() throws BusinessException {
	  CarDTipoImballaggioExample filtri = new CarDTipoImballaggioExample();
    filtri.setOrderByClause("id_tipo_imballaggio");
    return tipoImballaggioMapper.selectByExample(filtri);
  }
	
	@Override
  public List<CarDTipoComunicazione> getListaTipiComunicazione() throws BusinessException {
    CarDTipoComunicazioneExample filtri = new CarDTipoComunicazioneExample();
    filtri.setOrderByClause("desc_breve");

    return tipoComunicazioneMapper.selectByExample(filtri);
  }
	
	public List<CarDStatoComunicazione> getListaStatiComunicazione() throws BusinessException {
	  CarDStatoComunicazioneExample filtri = new CarDStatoComunicazioneExample();
	  
	  filtri.createCriteria().andIdStatoComunicazioneNotEqualTo(CaronteConstants.ID_STATO_COMUNICAZIONE_CREA_DOMANDA_SUCCESSIVA);
	  filtri.setOrderByClause("id_stato_comunicazione");

    return statoComunicazioneMapper.selectByExample(filtri);
	}
	
  @Override
  public List<CentroAziendaleDto> getListaCentriAziendali(Long idSpedizioniere) throws BusinessException {
    return decodificheMapper.getListaCentriAziendali(idSpedizioniere);
  }
	
	@Override
	public CarDAutoreEppo getDatiDenomAutoreEppo(String denomAutore) throws BusinessException {
    return autoreEppoMapper.getDatiDenomAutoreEppo(denomAutore);
  }
	
	@Override
	public TipoDomandaDTO getTipoComunicazioneByPrimaryKey(Long idTipoDomanda) throws BusinessException {
	  return decodificheMapper.getDatiTipoDomanda(idTipoDomanda);
	}
	
	@Override
	public List<CarDTipologia> getListaTipologie() throws BusinessException{
	  CarDTipologiaExample example = new CarDTipologiaExample();
	  example.createCriteria().andFineValiditaIsNull();
	  example.setOrderByClause("desc_breve");
	  return tipologiaMapper.selectByExample(example);
	}
	
	@Override
	public List<CarDVoce> getVociByIdTipoModello(Long idTipoModello) throws BusinessException{
		CarDVoceExample example = new CarDVoceExample();
		example.createCriteria().andIdTipoModelloEqualTo(idTipoModello);
		example.setOrderByClause("desc_breve");
		return voceMapper.selectByExample(example);		
	}
	
	@Override
	public List<CarDVoce> getVociByIdTipoModelloGruppo(Long idTipoModello, Long gruppo) throws BusinessException {
		CarDVoceExample example = new CarDVoceExample();
		// recupero tutte le voci appartenenti ad un gruppo del modello ordinate per il campo ordinamento
		example.createCriteria().andIdTipoModelloEqualTo(idTipoModello).andGruppoEqualTo(gruppo);
		example.setOrderByClause("ordinamento");
		return voceMapper.selectByExample(example);		
	}
	
	@Override
	public List<CarDGruppoZonaProtetta> getZoneProtette() throws BusinessException {
		return decodificheMapper.getZoneProtette();		
	}
	
	@Override
	public List<CarDTipoAttivita> getTipoAttivita() throws BusinessException {
		CarDTipoAttivitaExample example = new CarDTipoAttivitaExample();
		example.createCriteria();
		example.setOrderByClause("desc_breve");
		return tipoAttivitaMapper.selectByExample(example);		
	}
	
	@Override
	public List<CarDMateriale> getListaMaterialiByIdTipoAttivita(Long idTipoAttivita) throws BusinessException {
		return decodificheMapper.getListaMaterialiByIdTipoAttivita(idTipoAttivita);		
	}
	
	
	@Override
	public boolean isTipoGenereFamiglia(String denomGenere) throws BusinessException {		
		return decodificheMapper.isTipoGenereFamiglia(denomGenere) != null;		
	}

	@Override
	public List<DomandaDto> getListaIspettori() throws BusinessException {
		return decodificheMapper.getListaIspettori();
	}
	
	@Override
	public CarDProvincia getProvinciaByIdProv(Long idProvincia) throws BusinessException{
		return provinciaMapper.selectByPrimaryKey(idProvincia);
	}
	
	@Override
	public List<CarDStatoAzienda> getListaStatiAzienda() throws BusinessException{
		CarDStatoAziendaExample filtri = new CarDStatoAziendaExample();
		filtri.setOrderByClause("id_stato_azienda");
		return aziendaMapper.selectByExample(filtri);	
	}

	@Override
	public List<ModuloDTO> getListaModuliComunicazione(Long idDomanda) throws BusinessException {
		return decodificheMapper.getListaModuliComunicazione(idDomanda);
	}

	@Override
	public CarDTipoModulo getTipoModuloByPrimaryKey(Long idTipoModulo) throws BusinessException {
		return tipoModuloMapper.selectByPrimaryKey(idTipoModulo);
	}
	
	@Override
	public List<CarDOrgNocivo> getListaOrganismiNocivi() throws BusinessException{
		CarDOrgNocivoExample ex = new CarDOrgNocivoExample();
		ex.createCriteria().andFineValiditaIsNull();
		ex.setOrderByClause("desc_estesa");
		return orgNocivoMapper.selectByExample(ex);
	}

	@Override
	public List<CarDNormaVerbale> getListaNormeVerbale() throws BusinessException {
		return decodificheMapper.getListaNormeVerbale();
	}

	@Override
	public List<CarDTipologiaSemente> getListaTipologiaSementi() throws BusinessException {
		return decodificheMapper.getListaTipologiaSementi();
	}

	@Override
	public List<CarDTipoRespAzienda> getListaTipiRespAzienda() throws BusinessException {
		return decodificheMapper.getListaTipiRespAzienda();
	}

	@Override
	public List<CarDTipologiaControllo> getListTipologieControlli() throws BusinessException {
		return decodificheMapper.getListTipologieControlli();
	}

	@Override
	public List<CarDMetodoProduzione> getListaMetodiProduzione() throws BusinessException {
		CarDMetodoProduzioneExample ex = new CarDMetodoProduzioneExample();
		ex.createCriteria().andDataFineValiditaIsNull();
		ex.setOrderByClause("desc_estesa");
		return metodoProduzioneMapper.selectByExample(ex);
	}

	@Override
	public List<CarDTipoIrrigazione> getListaTipoIrrigazione() throws BusinessException {
		CarDTipoIrrigazioneExample ex = new CarDTipoIrrigazioneExample();
		ex.createCriteria().andDataFineValiditaIsNull();
		ex.setOrderByClause("desc_estesa");
		return tipoIrrigazioneMapper.selectByExample(ex);
	}

	@Override
	public List<CarDConoscenzaProfessionale> getListaConoscenzaProfessionale() throws BusinessException {
		CarDConoscenzaProfessionaleExample ex = new CarDConoscenzaProfessionaleExample();
		ex.createCriteria().andDataFineValiditaIsNull();
		ex.setOrderByClause("desc_estesa");
		return conoscenzaProfessionaleMapper.selectByExample(ex);
	}

	@Override
	public List<CarDRequisitoProfessionale> getListaRequisitoProfessionale() throws BusinessException {
		CarDRequisitoProfessionaleExample ex = new CarDRequisitoProfessionaleExample();
		ex.createCriteria().andDataFineValiditaIsNull();
		ex.setOrderByClause("desc_estesa");
		return requisitoProfessionaleMapper.selectByExample(ex);
	}

	@Override
	public List<CarDStrutturaAttrezzatura> getListaStrutturaAttrezzatura() throws BusinessException {
		CarDStrutturaAttrezzaturaExample ex = new CarDStrutturaAttrezzaturaExample();
		ex.createCriteria().andDataFineValiditaIsNull();
		ex.setOrderByClause("desc_estesa");
		return strutturaAttrezzaturaMapper.selectByExample(ex);
	}
	
	@Override
	public List<CarDTipologiaCampione> getListaTipologiaCampione() throws BusinessException{
		CarDTipologiaCampioneExample ex = new CarDTipologiaCampioneExample();
		ex.createCriteria().andDataFineValiditaIsNull();
		ex.setOrderByClause("desc_estesa_it");
		return tipologiaCampioneMapper.selectByExample(ex);
	}
	
	@Override
	public List<CarDTipologiaMisura> getTipologieMisura() throws BusinessException{
		CarDTipologiaMisuraExample ex = new CarDTipologiaMisuraExample();
		ex.createCriteria().andDataFineValiditaIsNull();
		ex.setOrderByClause("id_tipologia_misura");
		return tipologiaMisuraMapper.selectByExample(ex);
	}
	
	@Override
	public List<CodeDescriptionDTO> getGeneriByIdOrgNocivo(Long idOrganismoNocivo) throws BusinessException{
		return decodificheMapper.getGeneriByIdOrgNocivo(idOrganismoNocivo);
	}
	
	@Override
	public List<CodeDescriptionDTO> getSpecieByIdGeneri(List<Long> idGenereList) throws BusinessException{
		return decodificheMapper.getSpecieByIdGeneri(idGenereList);
	}
	
	@Override
	public List<CarDTipologiaPassaporto> getListaTipologiaPassaporto() throws BusinessException {
		CarDTipologiaPassaportoExample ex = new CarDTipologiaPassaportoExample();
		ex.createCriteria().andFineValiditaIsNull();
		ex.setOrderByClause("id_tipologia_passaporto");
		return tipologiaPassaportoMapper.selectByExample(ex);
	}

	@Override
	public String getQualificaByIdTipoRespAzienda(Long idTipoRespAzienda) throws BusinessException {			
			return tipoRespAziendaMapper.selectByPrimaryKey(idTipoRespAzienda).getDescEstesa();
	}
	
	@Override
	public List<CodeDescriptionDTO> getListaIspettoriByTipoIspettore(String tipoIspettore) throws BusinessException{
		return decodificheMapper.getListaIspettoriByTipoIspettore(tipoIspettore);
	}
	
	@Override
	public List<CarDTipologiaSemente> getListaTipologiaSementiByIdVersioneControllo(@Param("idVersioneControllo") Long idVersioneControllo) throws BusinessException {
		return decodificheMapper.getListaTipologiaSementiByIdVersioneControllo(idVersioneControllo);
	}
	
	@Override
	public CarDCostante getCostanteWithBlob(String codCostante)  throws BusinessException{
		CarDCostanteExample example = new CarDCostanteExample();
		example.createCriteria().andCodCostanteEqualTo(codCostante);		  
	    List<CarDCostante> listaCostante= carDCostanteMapper.selectByExampleWithBLOBs(example);
	    if (listaCostante.size() > 0) {    		      
	      return listaCostante.get(0);
	    }
	    return null;
	}

	
}
