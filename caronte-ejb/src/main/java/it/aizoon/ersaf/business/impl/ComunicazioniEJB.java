package it.aizoon.ersaf.business.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.springframework.util.StringUtils;

import it.aizoon.ersaf.business.IComunicazioniEJB;
import it.aizoon.ersaf.dto.CentroAziendaleDto;
import it.aizoon.ersaf.dto.ComunicazioneDto;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazione;
import it.aizoon.ersaf.dto.generati.CarRComunicazioneSpecie;
import it.aizoon.ersaf.dto.generati.CarRComunicazioneSpecieExample;
import it.aizoon.ersaf.dto.generati.CarTComunicazione;
import it.aizoon.ersaf.dto.generati.CarTComunicazioneExample;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaComunicazioneForm;
import it.aizoon.ersaf.form.RicercaComunicazioneForm;
import it.aizoon.ersaf.formatter.DateFormatter;
import it.aizoon.ersaf.integration.ComunicazioniDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.ComunicazioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRComunicazioneSpecieMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTComunicazioneMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

@Stateless(name = "Comunicazioni")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class ComunicazioniEJB
    extends AbstractEJB<ComunicazioniDAO, CarTComunicazioneMapper, CarTComunicazione, CarTComunicazioneExample>
    implements IComunicazioniEJB {

  private final String THIS_CLASS = "[" + ComunicazioniEJB.class.getCanonicalName() + "]";

  @Inject
  CarTComunicazioneMapper carTComunicazioneMapper;

  @Inject
  ComunicazioneMapper comunicazioneMapper;

  @Inject
  CarRComunicazioneSpecieMapper comunicazioneSpecieMapper;

  @Override
  public List<ComunicazioneDto> getComunicazioniRespinte(Long idUtente) throws BusinessException {

    Long[] idStatiComunicazione = { CaronteConstants.STATO_COMUNICAZIONE_RESPINTA,
        CaronteConstants.STATO_COMUNICAZIONE_ANNULLATA };
    Date oneWeekAgo = new Date(System.currentTimeMillis() - (7 * CaronteConstants.DAY_IN_MS));

    List<ComunicazioneDto> listaComunicazioni = comunicazioneMapper.getElencoComunicazioniRespinte(idUtente, oneWeekAgo,
        Arrays.asList(idStatiComunicazione));

    return listaComunicazioni;
  }

  @Override
  public List<ComunicazioneDto> getElencoComunicazioni(RicercaComunicazioneForm ricercaComunicazione)
      throws BusinessException {

    return comunicazioneMapper.getElencoComunicazioni(ricercaComunicazione);

  }

  @Override
  public List<CentroAziendaleDto> getListaCentriAziendali(Long idSpedizioniere) throws BusinessException {

    return comunicazioneMapper.getElencoCentriAziendali(idSpedizioniere);

  }

  @Override
  public Boolean isComunicazioneModificabile(Long idUtente, Long idComunicazione) throws BusinessException {
    return comunicazioneMapper.isUtenteAbilitatoModificaComunicazione(idUtente, idComunicazione);
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public Long inserisciComunicazione(NuovaComunicazioneForm datiComunicazione, Long idUtente) throws BusinessException {
    CarTComunicazione comunicazione = new CarTComunicazione();

    // Inserimento dati in car_t_comunicazione
    logger.debug("-- Inserimento dati in car_r_comunicazione_specie");
    if (null != datiComunicazione) {
      comunicazione.setIdTipoComunicazione(datiComunicazione.getIdTipoComunicazione());
      comunicazione.setIdStatoComunicazione(CaronteConstants.STATO_COMUNICAZIONE_BOZZA);
      comunicazione.setIdCentroAziendale(datiComunicazione.getIdCentroAziendale());
      comunicazione.setIdUtenteInserimento(idUtente);
      comunicazione.setDataInserimento(new Date());
    }

    carTComunicazioneMapper.insertSelective(comunicazione);
    logger.debug("-- INSERITA COMUNICAZIONE VIVAI con idComunicazione ="+comunicazione.getIdComunicazione());

    // Inserimento dati in car_r_comunicazione_specie
    logger.debug("-- Inserimento dati in car_r_comunicazione_specie");
    if (CaronteConstants.ID_TIPO_COMUNICAZIONE_SPECIE_ANNUALE.equals(comunicazione.getIdTipoComunicazione())) {
      //for (String idSpecie : datiComunicazione.convertiIdSpecie()) {
    	for (Iterator iterator = datiComunicazione.getSpecieList().iterator(); iterator.hasNext();) {
			SpecieDTO specie = (SpecieDTO) iterator.next();
			
			CarRComunicazioneSpecie comunicazSpecie = new CarRComunicazioneSpecie();
			comunicazSpecie.setIdComunicazione(comunicazione.getIdComunicazione());
			if (specie.getIdGenere() != null) {
				comunicazSpecie.setIdGenere(Long.valueOf(specie.getIdGenere()));
			}
			if (specie.getIdSpecie() != null) {
				comunicazSpecie.setIdSpecie(Long.valueOf(specie.getIdSpecie()));
			}
			if(specie.getNumeroPiante() != null) {
			  comunicazSpecie.setNumeroPiante(specie.getNumeroPiante().longValue());
			}
			logger.debug("-- INSERT car_r_comunicazione_specie con id_specie = "+comunicazSpecie.getIdSpecie());
			comunicazioneSpecieMapper.insertSelective(comunicazSpecie);
    	}
      //}
    }

    return comunicazione.getIdComunicazione();
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int aggiornaComunicazione(NuovaComunicazioneForm datiComunicazione, Long idUtente) throws BusinessException {
    int result = 0;

    if (null != datiComunicazione && datiComunicazione.getIdComunicazione() != null) {
      CarTComunicazione comunicazione = carTComunicazioneMapper
          .selectByPrimaryKey(datiComunicazione.getIdComunicazione());
      comunicazione.setIdCentroAziendale(datiComunicazione.getIdCentroAziendale());
      comunicazione.setIdUtenteAggiornamento(idUtente);
      comunicazione.setDataAggiornamento(new Date());

      logger.debug("-- Aggiornamento dati delle comunicazione con id_comunicazione ="+comunicazione.getIdComunicazione());
      result = carTComunicazioneMapper.updateByPrimaryKey(comunicazione);

      if (CaronteConstants.ID_TIPO_COMUNICAZIONE_SPECIE_ANNUALE.equals(comunicazione.getIdTipoComunicazione())) {
    	  
    	CarRComunicazioneSpecieExample exampleSpecie = new CarRComunicazioneSpecieExample();
        exampleSpecie.createCriteria().andIdComunicazioneEqualTo(comunicazione.getIdComunicazione());        
        List<CarRComunicazioneSpecie> listaDB = comunicazioneSpecieMapper.selectByExample(exampleSpecie);
        
        /*
    	 * 1) Elimino tutti i record con id_comunicazione in oggetto
    	 * 2) Inserisco tutte le specie che ci sono nel form
    	*/
        CarRComunicazioneSpecieExample comunicazSpecieEx = new CarRComunicazioneSpecieExample();
        comunicazSpecieEx.createCriteria().andIdComunicazioneEqualTo(comunicazione.getIdComunicazione());
        logger.debug("-- Elimino tutti i record in car_r_comunicazione_specie con id_comunicazione ="+comunicazione.getIdComunicazione());
        comunicazioneSpecieMapper.deleteByExample(comunicazSpecieEx);
        
    	
    	for (Iterator iterator = datiComunicazione.getSpecieList().iterator(); iterator.hasNext();) {
			SpecieDTO specie = (SpecieDTO) iterator.next();
			
			CarRComunicazioneSpecie comunicazSpecie = new CarRComunicazioneSpecie();
			comunicazSpecie.setIdComunicazione(comunicazione.getIdComunicazione());
			if (specie.getIdGenere() != null) {
				comunicazSpecie.setIdGenere(Long.valueOf(specie.getIdGenere()));
			}
			if (specie.getIdSpecie() != null) {
				comunicazSpecie.setIdSpecie(Long.valueOf(specie.getIdSpecie()));
			}
			if(specie.getNumeroPiante() != null) {
				comunicazSpecie.setNumeroPiante(specie.getNumeroPiante().longValue());
    		}
			logger.debug("-- INSERT car_r_comunicazione_specie");
			comunicazioneSpecieMapper.insertSelective(comunicazSpecie);
    	}
    	
      /*  CarRComunicazioneSpecieExample exampleSpecie = new CarRComunicazioneSpecieExample();
        exampleSpecie.createCriteria().andIdComunicazioneEqualTo(comunicazione.getIdComunicazione());
        List<CarRComunicazioneSpecie> listaDB = comunicazioneSpecieMapper.selectByExample(exampleSpecie);

        List<String> listaForm = datiComunicazione.convertiIdSpecie();

        Iterator<String> iteratorListaForm = listaForm.iterator();

        while (iteratorListaForm.hasNext()) {
          Iterator<CarRComunicazioneSpecie> iteratorListaDB = listaDB.iterator();
          String idSpecie = iteratorListaForm.next();

          while (iteratorListaDB.hasNext()) {
            CarRComunicazioneSpecie specieDB = iteratorListaDB.next();

            if (specieDB.getIdSpecie().equals(Long.valueOf(idSpecie))) {
              iteratorListaDB.remove();
              iteratorListaForm.remove();
              break;
            }
          }
        }

        
         * I record rimasti sulla lista DB sono stati eliminati dall'utente, e
         * quindi vanno cancellati
         
        for (CarRComunicazioneSpecie specieDB : listaDB) {
          comunicazioneSpecieMapper.deleteByPrimaryKey(specieDB.getIdComunicazioneSpecie());
        }

        
         * I record rimasti sulla lista del form non erano presenti su DB,
         * quindi vanno inseriti
         
        for (String idSpecie : listaForm) {
          CarRComunicazioneSpecie specie = new CarRComunicazioneSpecie();
          specie.setIdComunicazione(comunicazione.getIdComunicazione());
          specie.setIdSpecie(Long.valueOf(idSpecie));

          comunicazioneSpecieMapper.insertSelective(specie);
        }*/
        
        
        
        
      }
    }

    return result;
  }

  @Override
  public ComunicazioneDto getDettaglioComunicazione(Long idComunicazione) throws BusinessException {

    return comunicazioneMapper.getDettaglioComunicazione(idComunicazione);

  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int cancellaComunicazione(Long idComunicazione) throws BusinessException {

    // Specie
    CarRComunicazioneSpecieExample exampleSpecie = new CarRComunicazioneSpecieExample();
    exampleSpecie.createCriteria().andIdComunicazioneEqualTo(idComunicazione);
    comunicazioneSpecieMapper.deleteByExample(exampleSpecie);

    // Comunicazione
    return carTComunicazioneMapper.deleteByPrimaryKey(idComunicazione);
  }

  @Override
  public List<CarDStatoComunicazione> getListaStatiComunicazioneSuccessivi(Long idUtente, Long idComunicazione)
      throws BusinessException {
    return comunicazioneMapper.getListaStatiComunicazioneSuccessivi(idUtente, idComunicazione);
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public boolean avanzaStatoComunicazione(Long idComunicazione, Long idNuovoStato, Long idUtente, String motivazione)
      throws BusinessException {
    comunicazioneMapper.lockComunicazione(idComunicazione);
    
    logger.debug(" -- idNuovoStato ="+idNuovoStato);

    if (comunicazioneMapper.isUtenteAbilitatoModificaComunicazione(idUtente, idComunicazione) != null) {
      List<CarDStatoComunicazione> listaStatiSuccessivi = comunicazioneMapper
          .getListaStatiComunicazioneSuccessivi(idUtente, idComunicazione);

      for (CarDStatoComunicazione stato : listaStatiSuccessivi) {
        if (stato.getIdStatoComunicazione().equals(idNuovoStato)) {
          CarTComunicazione comunicazione = carTComunicazioneMapper.selectByPrimaryKey(idComunicazione);

          comunicazione.setIdStatoComunicazione(idNuovoStato);
          comunicazione.setIdUtenteAggiornamento(idUtente);
          comunicazione.setDataAggiornamento(new Date());
          comunicazione.setMotivazione(motivazione);

          logger.debug(" -- avanzaStatoComunicazione con idComunicazione ="+comunicazione.getIdComunicazione());          
          carTComunicazioneMapper.updateByPrimaryKey(comunicazione);

          if (!StringUtils.isEmpty(motivazione)) {
            String descStatoComunicazione = null;

            if (CaronteConstants.STATO_COMUNICAZIONE_RESPINTA.equals(idNuovoStato)) {
              descStatoComunicazione = CaronteConstants.DESC_STATO_COMUNICAZIONE_RESPINTA.toLowerCase();
            } else if (CaronteConstants.STATO_COMUNICAZIONE_ANNULLATA.equals(idNuovoStato)) {
              descStatoComunicazione = CaronteConstants.DESC_STATO_COMUNICAZIONE_ANNULLATA.toLowerCase();
            }

            logger.debug(" -- descStatoComunicazione ="+descStatoComunicazione);
            if (descStatoComunicazione != null) {              
              ComunicazioneDto dettaglioComunicazione = comunicazioneMapper.getDettaglioComunicazione(idComunicazione);
              String subject = dettaglioComunicazione.getDescTipoComunicazione() + " per centro aziendale "
                  + dettaglioComunicazione.getCentroAziendale().getDenominazione() + " " + descStatoComunicazione;
              String message = "<html><body><p>Gentile "
                  + StringUtils.capitalize(
                      dettaglioComunicazione.getUtenteCognome() + " " + dettaglioComunicazione.getUtenteNome())
                  + ", " + "<br/>ti avvisiamo che la " + dettaglioComunicazione.getDescTipoComunicazione()
                  + " del Centro Aziendale <b>" + dettaglioComunicazione.getCentroAziendale().getDenominazione() + "</b> del "
                  + new DateFormatter().print(dettaglioComunicazione.getDataInserimento(), null) + " è stata "
                  + descStatoComunicazione + " con la seguente motivazione: " + "<br /><br />"
                  + motivazione.toUpperCase()
                  + "<br /><br />Questa mail è stata generata in automatico, si prega di non rispondere a questo indirizzo, grazie."
                  + "<br/></p></body></html>";

              try {
            	logger.debug("--- Invio MAIL a :"+dettaglioComunicazione.getUtenteEmail());  
                CaronteUtils.postMailWithoutAttachments(CaronteConstants.INDIRIZZO_MITTENTE_REGISTRAZIONE_UTENTE,
                    new String[] { dettaglioComunicazione.getUtenteEmail() }, null, subject, message);
              } catch (Exception ex) {
                throw new BusinessException(ex.getMessage());
              }
            }
          }

          return true;
        }
      }
    }

    return false;
  }
  
  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public Long copiaComunicazione(Long idComunicazione, Long idUtente) throws BusinessException {
    CarTComunicazione comunicazione;

    comunicazione = carTComunicazioneMapper.selectByPrimaryKey(idComunicazione);

    comunicazione.setIdComunicazione(null);
    comunicazione.setIdStatoComunicazione(CaronteConstants.STATO_COMUNICAZIONE_BOZZA);
    
    comunicazione.setIdUtenteInserimento(idUtente);
    comunicazione.setDataInserimento(new Date());
    comunicazione.setIdUtenteAggiornamento(null);
    comunicazione.setDataAggiornamento(null);

    carTComunicazioneMapper.insertSelective(comunicazione);

    // Specie
    CarRComunicazioneSpecieExample exampleSpecie = new CarRComunicazioneSpecieExample();
    exampleSpecie.createCriteria().andIdComunicazioneEqualTo(idComunicazione);
    List<CarRComunicazioneSpecie> listaSpecie = comunicazioneSpecieMapper.selectByExample(exampleSpecie);

    for (CarRComunicazioneSpecie specie : listaSpecie) {
      specie.setIdComunicazioneSpecie(null);
      specie.setIdComunicazione(comunicazione.getIdComunicazione());
      
      comunicazioneSpecieMapper.insertSelective(specie);
    }

    return comunicazione.getIdComunicazione();
  }
  
  public List<CarDTipoComunicazione> getTipiComunicazioneByIdUtente(Long idUtente, Long idAssociazioneSezione) throws BusinessException {
	  return comunicazioneMapper.getTipiComunicazioneByIdUtente(idUtente, idAssociazioneSezione);
  }
  
  public List<CarTSpedizioniere> getListaSpedizionieriByDenomRuop(String spedizioniereRuop, Long idAssociazioneSezione) throws BusinessException {
	  logger.debug("-- getListaSpedizionieriByDenomRuop(spedizioniereRuop) = " + spedizioniereRuop);
	  return comunicazioneMapper.getListaSpedizionieriByDenomRuop(spedizioniereRuop, idAssociazioneSezione);
  }
  
  public List<CarTSpedizioniere> getListaSpedizionieriByIdUtente(Long idUtente) throws BusinessException{
	  return comunicazioneMapper.getListaSpedizionieriByIdUtente(idUtente);
  }


}
