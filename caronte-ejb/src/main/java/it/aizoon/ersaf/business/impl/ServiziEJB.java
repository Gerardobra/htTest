package it.aizoon.ersaf.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Valid;

import org.springframework.util.StringUtils;

import it.aizoon.ersaf.business.IServiziEJB;
import it.aizoon.ersaf.dto.DatiRichiesteDTO;
import it.aizoon.ersaf.dto.DatiSianDTO;
import it.aizoon.ersaf.dto.MerceRichiestaDatiSianDTO;
import it.aizoon.ersaf.dto.ProfiloNonAutorizzatoDTO;
import it.aizoon.ersaf.dto.RiexportDatiSianDTO;
import it.aizoon.ersaf.dto.generati.CarDAllegato;
import it.aizoon.ersaf.dto.generati.CarDAllegatoExample;
import it.aizoon.ersaf.dto.generati.CarTCertificato;
import it.aizoon.ersaf.dto.generati.CarTCertificatoExample;
import it.aizoon.ersaf.dto.generati.CarTNotifica;
import it.aizoon.ersaf.dto.generati.CarTNotificaExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NotificheForm;
import it.aizoon.ersaf.form.RicercaDatiRichiesteForm;
import it.aizoon.ersaf.form.RicercaDatiSianForm;
import it.aizoon.ersaf.integration.RichiesteDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.ServiziMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDAllegatoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTCertificatoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTNotificaMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;

@Stateless(name = "Servizi")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
// @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class ServiziEJB
		extends AbstractEJB<RichiesteDAO, CarTCertificatoMapper, CarTCertificato, CarTCertificatoExample>
		implements IServiziEJB {

	// private final String this_class = "[" + ServiziEJB.class.getCanonicalName() +
	// "]";

	@Inject
	ServiziMapper serviziMapper;
	
	@Inject
	CarDAllegatoMapper allegatoMapper;

	@Inject
	CarTNotificaMapper notificaMapper;

	@Override
	public List<DatiRichiesteDTO> getElencoDatiRichieste(RicercaDatiRichiesteForm richiesteForm)
			throws BusinessException {
		List<DatiRichiesteDTO> elenco = null;
		try {
			elenco = serviziMapper.getElencoDatiRichieste(richiesteForm);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return elenco;
	}

	@Override
	public List<DatiSianDTO> getElencoDatiSian(RicercaDatiSianForm sianForm) throws BusinessException {
		List<DatiSianDTO> elenco = null;
		try {
			elenco = serviziMapper.getElencoDatiSian(sianForm);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return elenco;
	}

	@Override
	public List<MerceRichiestaDatiSianDTO> getMerciPerDatiSian(RicercaDatiSianForm sianForm) throws BusinessException {
		List<MerceRichiestaDatiSianDTO> elenco = null;
		try {
			elenco = serviziMapper.getMerciPerDatiSian(sianForm);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return elenco;
	}

	@Override
	public List<RiexportDatiSianDTO> getDatiRiexportSian(RicercaDatiSianForm sianForm) throws BusinessException {
		List<RiexportDatiSianDTO> elenco = null;
		try {
			elenco = serviziMapper.getDatiRiexportSian(sianForm);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return elenco;
	}

	@Override
	public List<CarTNotifica> getElencoMessaggi() throws BusinessException {
		List<CarTNotifica> elenco = null;
		CarTNotificaExample example = new CarTNotificaExample();
		example.setOrderByClause("id_tipo_notifica");
		try {
			elenco = notificaMapper.selectByExample(example);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return elenco;
	}

	@Override
	public int salvaMessaggi(List<CarTNotifica> listaMessaggi, long idUtente) throws BusinessException {

		try {

			List<CarTNotifica> elenco = null;
			CarTNotificaExample example = new CarTNotificaExample();
			example.setOrderByClause("id_notifica");
			elenco = notificaMapper.selectByExample(example);

			for (CarTNotifica notifica : listaMessaggi) {

				long id = isTipoNotificaPresent(notifica.getIdTipoNotifica(), elenco);

				if (StringUtils.isEmpty(notifica.getMessaggio()) && id != -1) {
					notificaMapper.deleteByPrimaryKey(id);
				} else if (!StringUtils.isEmpty(notifica.getMessaggio()) && id != -1) {
					notifica.setIdNotifica(id);
					notifica.setIdUtenteUpdate(idUtente);
					notifica.setDataUpdate(new Date());
					notificaMapper.updateByPrimaryKeySelective(notifica);
				} else if (!StringUtils.isEmpty(notifica.getMessaggio()) && id == -1) {
					notifica.setIdUtenteInsert(idUtente);
					notifica.setDataInsert(new Date());
					notificaMapper.insertSelective(notifica);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return 0;

	}

	private long isTipoNotificaPresent(long idTipoNotifica, List<CarTNotifica> elenco) {

		long id = -1;

		for (CarTNotifica notificaDB : elenco) {
			if (notificaDB.getIdTipoNotifica().equals(idTipoNotifica))
				id = notificaDB.getIdNotifica();
		}

		return id;

	}

	@Override
	public List<ProfiloNonAutorizzatoDTO> getUtentiDaAutorizzare() throws BusinessException {

		List<ProfiloNonAutorizzatoDTO> elenco = null;
		
		try {

			elenco = serviziMapper.getUtentiDaAbilitare();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return elenco;
	}

	@Override
	public List<CarDAllegato> getDocumentazione() throws BusinessException {

		List<CarDAllegato> documentazione = null;
		
		try {
			
			CarDAllegatoExample example = new CarDAllegatoExample();
			example.createCriteria().andIdTipoAllegatoEqualTo(CaronteConstants.ID_TIPO_ALLEGATO_DOCUMENTAZIONE);
			documentazione = allegatoMapper.selectByExampleWithBLOBs(example);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			
		}
		
		return documentazione;
		
	}

	@Override
	public CarDAllegato getDocumento(Long idAllegato) throws BusinessException {
		
		CarDAllegato documento = null;
		
		try {
			
			CarDAllegatoExample example = new CarDAllegatoExample();
			example.createCriteria().andIdAllegatoEqualTo(idAllegato);
			
			documento = allegatoMapper.selectByExampleWithBLOBs(example).get(0);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			
		}
		
		return documento;
	}

}
