package it.aizoon.ersaf.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import it.aizoon.ersaf.business.IRichiesteEJB;
import it.aizoon.ersaf.dto.CertificatoRichiestaDto;
import it.aizoon.ersaf.dto.DettaglioRichiestaDto;
import it.aizoon.ersaf.dto.MerceRichiestaDto;
import it.aizoon.ersaf.dto.PagamentoRichiestaDto;
import it.aizoon.ersaf.dto.RichiestaDto;
import it.aizoon.ersaf.dto.TariffaDto;
import it.aizoon.ersaf.dto.TariffeRichiestaDto;
import it.aizoon.ersaf.dto.TrattamentoRichiestaDto;
import it.aizoon.ersaf.dto.generati.CarDAllegato;
import it.aizoon.ersaf.dto.generati.CarDAllegatoExample;
import it.aizoon.ersaf.dto.generati.CarDStatoRichiesta;
import it.aizoon.ersaf.dto.generati.CarDStatoRichiestaExample;
import it.aizoon.ersaf.dto.generati.CarRCampionamentoPartita;
import it.aizoon.ersaf.dto.generati.CarRIspetCertifContr;
import it.aizoon.ersaf.dto.generati.CarRIspetCertifContrExample;
import it.aizoon.ersaf.dto.generati.CarRMerceRichiesta;
import it.aizoon.ersaf.dto.generati.CarRMerceRichiestaExample;
import it.aizoon.ersaf.dto.generati.CarRMerceTariffa;
import it.aizoon.ersaf.dto.generati.CarRMerceTariffaExample;
import it.aizoon.ersaf.dto.generati.CarRRichiestaTariffa;
import it.aizoon.ersaf.dto.generati.CarRRichiestaTariffaExample;
import it.aizoon.ersaf.dto.generati.CarRTrattamentoRichiesta;
import it.aizoon.ersaf.dto.generati.CarRTrattamentoRichiestaExample;
import it.aizoon.ersaf.dto.generati.CarTCertificato;
import it.aizoon.ersaf.dto.generati.CarTCertificatoExample;
import it.aizoon.ersaf.dto.generati.CarTPagamento;
import it.aizoon.ersaf.dto.generati.CarTPagamentoExample;
import it.aizoon.ersaf.dto.generati.CarTRichiesta;
import it.aizoon.ersaf.dto.generati.CarTRichiestaExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaRichiestaForm;
import it.aizoon.ersaf.form.RicercaRichiestaForm;
import it.aizoon.ersaf.formatter.DateFormatter;
import it.aizoon.ersaf.integration.RichiesteDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.RichiestaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDAllegatoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDStatoRichiestaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRIspetCertifContrMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRMerceRichiestaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRMerceTariffaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRRichiestaTariffaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRTrattamentoRichiestaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTCertificatoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTPagamentoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTRichiestaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTUploadTempMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

@Stateless(name = "Richieste")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
// @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class RichiesteEJB extends AbstractEJB<RichiesteDAO, CarTRichiestaMapper, CarTRichiesta, CarTRichiestaExample>
    implements IRichiesteEJB {

  private final String THIS_CLASS = "[" + RichiesteEJB.class.getCanonicalName() + "]";

  @Inject
  CarTRichiestaMapper carTRichiestaMapper;

  @Inject
  CarDStatoRichiestaMapper carDStatoRichiestaMapper;

  @Inject
  RichiestaMapper richiestaMapper;

  @Inject
  CarRMerceRichiestaMapper carRMerceRichiestaMapper;

  @Inject
  CarRMerceTariffaMapper merceTariffaMapper;

  @Inject
  CarRTrattamentoRichiestaMapper trattamentoMapper;

  @Inject
  CarTCertificatoMapper certificatoMapper;

  @Inject
  CarTPagamentoMapper pagamentoMapper;

  @Inject
  CarRIspetCertifContrMapper ispettoreCertificatoMapper;

  @Inject
  CarDAllegatoMapper allegatoMapper;

  @Inject
  CarRRichiestaTariffaMapper richiestaTariffaMapper;

  @Inject
  CarTUploadTempMapper uploadTempMapper;

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public Long inserisciNuovaRichiesta(NuovaRichiestaForm nuovaRichiesta, Long idUtente, Long idAllegato)
      throws BusinessException {

    CarTRichiesta richiesta = new CarTRichiesta();

    if (null != nuovaRichiesta) {

      richiesta.setIdTipoRichiesta(nuovaRichiesta.getIdTipoRichiesta());
      richiesta.setIdStatoRichiesta(nuovaRichiesta.getIdStatoRichiesta());
      richiesta.setNoteDatiRichiesta(nuovaRichiesta.getNote());
      richiesta.setIdUtenteInsert(idUtente);
      richiesta.setDataInsert(new Date());

      if (CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
        richiesta.setNumeroCertificatoOriginale(nuovaRichiesta.getNumeroCertificatoOriginale());
      }

    }

    if (idAllegato != null) {

      richiesta.setIdAllegato(idAllegato);

    }

    carTRichiestaMapper.insertSelective(richiesta);

    return richiesta.getIdRichiesta();
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int aggiornaDatiRichiesta(NuovaRichiestaForm nuovaRichiesta, Long idUtente, Long idAllegato)
      throws BusinessException {

    CarTRichiesta richiesta = new CarTRichiesta();

    if (null != nuovaRichiesta) {
      richiesta.setIdRichiesta(nuovaRichiesta.getIdRichiesta());
      richiesta.setNoteDatiRichiesta(nuovaRichiesta.getNote());
      richiesta.setIdUtenteUpdate(idUtente);
      richiesta.setDataUpdate(new Date());

      /*
       * Nell'export non aggiorno il tipo richiesta perchè su modifica non è più
       * modificabile
       */
      if (CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
        richiesta.setNumeroCertificatoOriginale(nuovaRichiesta.getNumeroCertificatoOriginale());
      }
    }

    if (idAllegato != null) {
      richiesta.setIdAllegato(idAllegato);
    }

    return richiestaMapper.updateDatiRichiesta(richiesta);
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public Long inserisciAllegatoCertificato(String nomeAllegato, byte[] allegato) throws BusinessException {

    if (!StringUtils.isEmpty(nomeAllegato) && allegato != null) {

      CarDAllegato allegatoCertificatoOriginale = new CarDAllegato();
      allegatoCertificatoOriginale.setAllegato(allegato);
      allegatoCertificatoOriginale.setNomeFile(nomeAllegato);
      allegatoCertificatoOriginale.setIdTipoAllegato(CaronteConstants.TIPO_CERTIFICATO_ORIGINALE_RIEXPORT);
      allegatoCertificatoOriginale.setInizioValidita(new Date());

      allegatoMapper.insert(allegatoCertificatoOriginale);

      return allegatoCertificatoOriginale.getIdAllegato();
    }

    return null;
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int aggiornaDatiMittente(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException {

    CarTRichiesta richiesta = carTRichiestaMapper.selectByPrimaryKey(nuovaRichiesta.getIdRichiesta());

    if (null != nuovaRichiesta) {
      richiesta.setDenomMittente(nuovaRichiesta.getDenominazioneMittente());
      richiesta.setIndirizzoMittente(nuovaRichiesta.getIndirizzoMittente());

      if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {

        richiesta.setDenomComuneEstMittente(nuovaRichiesta.getComuneMittente());
        richiesta.setIdNazioneMittente(nuovaRichiesta.getIdNazioneMittente());
        // richiesta.setIdComuneIta(nuovaRichiesta.getIdComuneIta());

      } else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())
          || CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {

        richiesta.setIdComuneIta(nuovaRichiesta.getIdComuneMittente());
        richiesta.setCodiceRupMittente(nuovaRichiesta.getCodiceRuopMittente());
        richiesta.setNoteMittenteCertif(nuovaRichiesta.getNoteMittenteCertificato());
      }

      richiesta.setIdUtenteUpdate(idUtente);
      richiesta.setDataUpdate(new Date());
    }

    return richiestaMapper.updateDatiMittente(richiesta);
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int aggiornaDatiDestinatario(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException {

    CarTRichiesta richiesta = carTRichiestaMapper.selectByPrimaryKey(nuovaRichiesta.getIdRichiesta());

    if (null != nuovaRichiesta) {
      richiesta.setIdRichiesta(nuovaRichiesta.getIdRichiesta());
      richiesta.setDenomDestinatario(nuovaRichiesta.getDenominazioneDestinatario());
      richiesta.setIndirizzoDestinatario(nuovaRichiesta.getIndirizzoDestinatario());

      if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
        richiesta.setIdComuneIta(nuovaRichiesta.getIdComuneIta());
        richiesta.setCodiceRupDestinatario(nuovaRichiesta.getCodiceRup());
        richiesta.setIdRegioneRupDestinatario(nuovaRichiesta.getIdRegioneRup());
      } else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())
          || CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
        richiesta.setDenomComuneEstDestinatario(nuovaRichiesta.getComuneDestinatario());
        richiesta.setIdNazioneDestinatario(nuovaRichiesta.getIdNazioneDestinatario());
        richiesta.setCodiceRupDestinatario(nuovaRichiesta.getCodiceRup());
        richiesta.setIdNazioneRupDestinatario(nuovaRichiesta.getIdStatoRupDestinatario());
      }

      richiesta.setIdUtenteUpdate(idUtente);
      richiesta.setDataUpdate(new Date());
    }

    return richiestaMapper.updateDatiDestinatario(richiesta);
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int aggiornaDatiTrasporto(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException {

    CarTRichiesta richiesta = new CarTRichiesta();

    if (null != nuovaRichiesta) {
      richiesta.setIdRichiesta(nuovaRichiesta.getIdRichiesta());
      richiesta.setIdentifMezzoTrasporto(nuovaRichiesta.getNumDocumentoTrasporto());
      richiesta.setIdModoTrasporto(nuovaRichiesta.getIdModoTrasporto());
      richiesta.setNoteTrasporto(nuovaRichiesta.getNoteTrasporto());

      if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
        richiesta.setIdUfficioDoganaleUtilizzato(nuovaRichiesta.getIdPuntoEntrataDichiarato());
        richiesta.setIdUfficioDoganaleEntrata(nuovaRichiesta.getIdPuntoEntrataDichiarato());
        richiesta.setSpedizioneMultipla(nuovaRichiesta.isSpedizioneMultipla());

        if (nuovaRichiesta.isSpedizioneMultipla()) {
          richiesta.setNumeroCertificatiRichiesti(Long.valueOf(nuovaRichiesta.getNumCertificatiCollegati()));
        } else {
          richiesta.setNumeroCertificatiRichiesti(null);
        }
      } else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())
          || CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
        richiesta.setIdNazioneProtVegDestinat(nuovaRichiesta.getIdNazioneProtVeg());
        richiesta.setPuntoEntrataDichiarato(nuovaRichiesta.getPuntoEntrataDichiarato());
        richiesta.setLuogoDeposito(nuovaRichiesta.getLuogoDeposito());
        richiesta.setDataInizioDisponibilita(nuovaRichiesta.getDataInizioDisponibilitaMerce());
        richiesta.setOraInizioDisponibilita(nuovaRichiesta.getOraInizioDisponibilitaMerce());
        richiesta.setDataPartenzaMerce(nuovaRichiesta.getDataPartenzaMerce());
      }

      richiesta.setIdUtenteUpdate(idUtente);
      richiesta.setDataUpdate(new Date());
    }

    return richiestaMapper.updateDatiTrasporto(richiesta);
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int inserisciDatiMerce(CarRMerceRichiesta merceRichiesta, Long idUtente) throws BusinessException {

    if (null != merceRichiesta) {

      merceRichiesta.setIdUtenteInsert(idUtente);
      merceRichiesta.setDataInsert(new Date());
      /*
      logger.debug("++ IdMerceRichiesta: " + merceRichiesta.getIdMerceRichiesta());
      logger.debug("++ IdGenere: " + merceRichiesta.getIdGenere());
      logger.debug("++ IdSpecie: " + merceRichiesta.getIdSpecie());
      logger.debug("++ NumeroColli: " + merceRichiesta.getNumeroColli());
      logger.debug("++ IdNaturaCollo: " + merceRichiesta.getIdNaturaCollo());
      logger.debug("++ IdTipoProdotto: " + merceRichiesta.getIdTipoProdotto());
      logger.debug("++ IdProdotto: " + merceRichiesta.getIdProdotto());
      logger.debug("++ IdNazioneOrigine: " + merceRichiesta.getIdNazioneOrigine());
      logger.debug("++ NumeroCertificato: " + merceRichiesta.getNumeroCertificato());
      logger.debug("++ Marchio: " + merceRichiesta.getMarchio());
      logger.debug("++ Quantita: " + merceRichiesta.getQuantita());
      logger.debug("++ QuantitaLordaProdotto: " + merceRichiesta.getQuantitaLordaProdotto());
      logger.debug("++ IdRichiesta: " + merceRichiesta.getIdRichiesta());
      logger.debug("++ Accettato: " + merceRichiesta.getAccettato());
      logger.debug("++ IdUtenteInsert: " + merceRichiesta.getIdUtenteInsert());
      logger.debug("++ DataInsert: " + merceRichiesta.getDataInsert());
      logger.debug("++ IdUtenteUpdate: " + merceRichiesta.getIdUtenteUpdate());
      logger.debug("++ DataUpdate: " + merceRichiesta.getDataUpdate());
      logger.debug("++ NumeroPezzi: " + merceRichiesta.getNumeroPezzi());
      */
      return carRMerceRichiestaMapper.insert(merceRichiesta);
    }

    return 0;
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int aggiornaDatiMerce(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException {

    int status = 0;

    if (null != nuovaRichiesta && null != nuovaRichiesta.getListaMerceRichiesta()) {

      // Cancello tutti i record dalla car_r_merce_tariffa
      cancellaTariffeMerciRichiesta(nuovaRichiesta.getIdRichiesta());

      /*
       * Cancello i record associati alla richiesta su car_r_richiesta_tariffa
       */
      CarRRichiestaTariffaExample exampleRichiestaTariffa = new CarRRichiestaTariffaExample();
      exampleRichiestaTariffa.createCriteria().andIdRichiestaEqualTo(nuovaRichiesta.getIdRichiesta());
      richiestaTariffaMapper.deleteByExample(exampleRichiestaTariffa);

      CarRMerceRichiestaExample exampleMerceRichiesta = new CarRMerceRichiestaExample();
      exampleMerceRichiesta.createCriteria().andIdRichiestaEqualTo(nuovaRichiesta.getIdRichiesta());
      List<CarRMerceRichiesta> listaDB = carRMerceRichiestaMapper.selectByExample(exampleMerceRichiesta);

      List<MerceRichiestaDto> listaForm = nuovaRichiesta.getListaMerceRichiesta();

      /*
       * confronto tra due liste : - Lista DB - lista FORM
       * 
       * 1. ciclare lista form su lista DB (tramite iterator in modo da
       * eliminare la corrispondenza e rigenerare gli indici della lista) 2. SE
       * : id Form != null && id Form = id DB ---> aggiorna e rimuovi record
       * dalle liste (iterator) break del ciclo interno 3. a questo punto fare
       * le insert dei record Form 4. a questo punto fare le delete dei record
       * DB
       */

      Iterator<MerceRichiestaDto> iteratorListaForm = listaForm.iterator();

      while (iteratorListaForm.hasNext()) {
        Iterator<CarRMerceRichiesta> iteratorListaDB = listaDB.iterator();
        CarRMerceRichiesta merceRichiestaForm = iteratorListaForm.next();

        while (iteratorListaDB.hasNext()) {
          CarRMerceRichiesta merceRichiestaDB = iteratorListaDB.next();

          if (merceRichiestaDB.getIdMerceRichiesta().equals(merceRichiestaForm.getIdMerceRichiesta())) {
            merceRichiestaDB.setDataUpdate(new Date());
            merceRichiestaDB.setIdUtenteUpdate(idUtente);
            /*
            logger.debug("++ IdMerceRichiesta: " + merceRichiestaForm.getIdMerceRichiesta());
            logger.debug("++ IdGenere: " + merceRichiestaForm.getIdGenere());
            logger.debug("++ IdSpecie: " + merceRichiestaForm.getIdSpecie());
            logger.debug("++ NumeroColli: " + merceRichiestaForm.getNumeroColli());
            logger.debug("++ IdNaturaCollo: " + merceRichiestaForm.getIdNaturaCollo());
            logger.debug("++ IdTipoProdotto: " + merceRichiestaForm.getIdTipoProdotto());
            logger.debug("++ IdProdotto: " + merceRichiestaForm.getIdProdotto());
            logger.debug("++ IdNazioneOrigine: " + merceRichiestaForm.getIdNazioneOrigine());
            logger.debug("++ NumeroCertificato: " + merceRichiestaForm.getNumeroCertificato());
            logger.debug("++ Marchio: " + merceRichiestaForm.getMarchio());
            logger.debug("++ Quantita: " + merceRichiestaForm.getQuantita());
            logger.debug("++ QuantitaLordaProdotto: " + merceRichiestaForm.getQuantitaLordaProdotto());
            logger.debug("++ IdRichiesta: " + merceRichiestaForm.getIdRichiesta());
            logger.debug("++ Accettato: " + merceRichiestaForm.getAccettato());
            logger.debug("++ IdUtenteInsert: " + merceRichiestaForm.getIdUtenteInsert());
            logger.debug("++ DataInsert: " + merceRichiestaForm.getDataInsert());
            logger.debug("++ IdUtenteUpdate: " + merceRichiestaForm.getIdUtenteUpdate());
            logger.debug("++ DataUpdate: " + merceRichiestaForm.getDataUpdate());
            logger.debug("++ NumeroPezzi: " + merceRichiestaForm.getNumeroPezzi());
            */
            status += carRMerceRichiestaMapper.updateByPrimaryKey(merceRichiestaForm);
            //logger.debug("++ status: " + status);
            iteratorListaDB.remove();
            iteratorListaForm.remove();
            break;
          }
        }
      }

      for (CarRMerceRichiesta merceRichiestaDB : listaDB) {
    	  
        status += carRMerceRichiestaMapper.deleteByPrimaryKey(merceRichiestaDB.getIdMerceRichiesta());
      }

      for (MerceRichiestaDto merceRichiestaForm : listaForm) {
        merceRichiestaForm.setIdRichiesta(nuovaRichiesta.getIdRichiesta());
        merceRichiestaForm.setIdUtenteInsert(idUtente);
        merceRichiestaForm.setDataInsert(new Date());
        /*
        logger.debug("++ IdMerceRichiesta: " + merceRichiestaForm.getIdMerceRichiesta());
        logger.debug("++ IdGenere: " + merceRichiestaForm.getIdGenere());
        logger.debug("++ IdSpecie: " + merceRichiestaForm.getIdSpecie());
        logger.debug("++ NumeroColli: " + merceRichiestaForm.getNumeroColli());
        logger.debug("++ IdNaturaCollo: " + merceRichiestaForm.getIdNaturaCollo());
        logger.debug("++ IdTipoProdotto: " + merceRichiestaForm.getIdTipoProdotto());
        logger.debug("++ IdProdotto: " + merceRichiestaForm.getIdProdotto());
        logger.debug("++ IdNazioneOrigine: " + merceRichiestaForm.getIdNazioneOrigine());
        logger.debug("++ NumeroCertificato: " + merceRichiestaForm.getNumeroCertificato());
        logger.debug("++ Marchio: " + merceRichiestaForm.getMarchio());
        logger.debug("++ Quantita: " + merceRichiestaForm.getQuantita());
        logger.debug("++ QuantitaLordaProdotto: " + merceRichiestaForm.getQuantitaLordaProdotto());
        logger.debug("++ IdRichiesta: " + merceRichiestaForm.getIdRichiesta());
        logger.debug("++ Accettato: " + merceRichiestaForm.getAccettato());
        logger.debug("++ IdUtenteInsert: " + merceRichiestaForm.getIdUtenteInsert());
        logger.debug("++ DataInsert: " + merceRichiestaForm.getDataInsert());
        logger.debug("++ IdUtenteUpdate: " + merceRichiestaForm.getIdUtenteUpdate());
        logger.debug("++ DataUpdate: " + merceRichiestaForm.getDataUpdate());
        logger.debug("++ NumeroPezzi: " + merceRichiestaForm.getNumeroPezzi());
        */
        status += carRMerceRichiestaMapper.insertSelective(merceRichiestaForm);
        //logger.debug("++ status: " + status);
      }

      if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
        // Inserisco/Aggiorno i dati riguardanti la car_r_merce_tariffa
        inserisciTariffeMerciRichiesta(nuovaRichiesta.getIdRichiesta(), idUtente);
      } else {
        // Inserisco i dati riguardanti la car_r_richiesta_tariffa
        richiestaMapper.inserisciTariffeRichiesta(nuovaRichiesta.getIdRichiesta(), idUtente);
      }
    }

    return status;
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int cancellaDatiMerce(Long idMerceRichiesta) throws BusinessException {

    if (null != idMerceRichiesta) {

      return carRMerceRichiestaMapper.deleteByPrimaryKey(idMerceRichiesta);

    }

    return 0;
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int aggiornaDatiTrattamento(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException {
    CarRTrattamentoRichiestaExample filtri = new CarRTrattamentoRichiestaExample();
    filtri.createCriteria().andIdRichiestaEqualTo(nuovaRichiesta.getIdRichiesta());

    if (nuovaRichiesta.getIdTrattamento() == null) {
      /*
       * Cancellazione del record di trattamento della richiesta
       */
      return trattamentoMapper.deleteByExample(filtri);
    } else {
      CarRTrattamentoRichiesta trattamento = null;
      List<CarRTrattamentoRichiesta> listaTrattamenti = trattamentoMapper.selectByExample(filtri);

      if (listaTrattamenti.size() == 0) {
        trattamento = new CarRTrattamentoRichiesta();
        trattamento.setIdRichiesta(nuovaRichiesta.getIdRichiesta());
        trattamento.setIdUtenteInsert(idUtente);
        trattamento.setDataInsert(new Date());
      } else {
        trattamento = listaTrattamenti.get(0);
        trattamento.setIdUtenteUpdate(idUtente);
        trattamento.setDataUpdate(new Date());
      }

      trattamento.setIdTrattamento(nuovaRichiesta.getIdTrattamento());
      trattamento.setProdottoChimico(nuovaRichiesta.getProdottoChimico());
      trattamento.setDurata(nuovaRichiesta.getDurata());
      trattamento.setTemperatura(nuovaRichiesta.getTemperatura());
      trattamento.setConcentrazione(nuovaRichiesta.getConcentrazione());
      trattamento.setDataTrattamento(nuovaRichiesta.getDataTrattamento());
      trattamento.setInformazioniSupplementari(nuovaRichiesta.getInfoSupplementari());

      if (trattamento.getIdTrattamentoRichiesta() == null) {
        /*
         * Inserimento del recod
         */
        return trattamentoMapper.insert(trattamento);
      } else {
        /*
         * Aggiornamento del record
         */
        return trattamentoMapper.updateByPrimaryKey(trattamento);
      }
    }
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public Long aggiornaDatiCertificato(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException {
    CarTCertificatoExample filtri = new CarTCertificatoExample();
    filtri.createCriteria().andIdRichiestaEqualTo(nuovaRichiesta.getIdRichiesta());

    CarTCertificato certificato = null;
    List<CarTCertificato> listaCertificati = certificatoMapper.selectByExample(filtri);

    if (listaCertificati.size() == 0) {
      certificato = new CarTCertificato();
      certificato.setIdRichiesta(nuovaRichiesta.getIdRichiesta());
      certificato.setIdUtenteInsert(idUtente);
      certificato.setDataInsert(new Date());
      /*
       * CarTRichiesta richiesta = new CarTRichiesta();
       * richiesta.setIdRichiesta(nuovaRichiesta.getIdRichiesta());
       * richiesta.setDataEsecuzione(new Date());
       * richiestaMapper.updateDataEsecuzione(richiesta);
       */
    } else {
      certificato = listaCertificati.get(0);
      certificato.setIdUtenteUpdate(idUtente);
      certificato.setDataUpdate(new Date());
    }

    certificato.setIdTipoCertificato(nuovaRichiesta.getIdTipoCertificato());
    certificato.setDataEsecuzione(nuovaRichiesta.getDataEsecuzione());
    certificato.setIdIspettore(nuovaRichiesta.getIdIspettore());

    if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
      certificato.setIdUfficioDoganaleEsecuzione(nuovaRichiesta.getIdLuogoEsecuzione());
    } else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())
        || CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
      certificato.setDichiarazioneSupplementare(nuovaRichiesta.getDichiarazioneSupplementare());
      certificato.setIdComuneEsecuzione(nuovaRichiesta.getIdComuneEsecuzione());

      if (CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
        if (Boolean.TRUE.toString().equalsIgnoreCase(nuovaRichiesta.getTipoCertificatoPrecedente())) {
          certificato.setCopiaConforme(true);
        } else {
          certificato.setCopiaConforme(false);
        }

        certificato.setIdTipoImballaggio(nuovaRichiesta.getIdMerceColliOriginali());
        certificato.setConformeCertifOrig(nuovaRichiesta.isCertificatoFitoOriginale());
        certificato.setIspezioneSupplementare(nuovaRichiesta.isIspezioneSupplementare());
      }
    }

    if (CaronteConstants.NUMERAZIONE_CERTIFICATO_MANUALE.equals(nuovaRichiesta.getTipoNumerazioneCertificato())) {
      certificato.setNumeroCertificatoManuale(nuovaRichiesta.getNumeroCertificatoManuale());
    } else {
      certificato.setNumeroCertificatoManuale(null);

      if (CaronteConstants.NUMERAZIONE_CERTIFICATO_AUTOMATICA.equals(nuovaRichiesta.getTipoNumerazioneCertificato())
          && certificato.getNumeroCertificato() == null) {
        certificato.setNumeroCertificato(getNuovoNumeroCertificato());
      }
    }

    certificato.setNote(nuovaRichiesta.getNoteCertificato());

    if (certificato.getIdCertificato() == null) {
      /*
       * Inserimento del record
       */
      certificatoMapper.insert(certificato);

      /*
       * Creazione dei record di relazione tra certificato e ispettori di
       * controllo
       */

    } else {
      /*
       * Aggiornamento del record
       */
      certificatoMapper.updateByPrimaryKey(certificato);
    }

    /*
     * Aggiornamento dei record di relazione tra certificato e ispettori di
     * controllo
     */
    if (CaronteConstants.TIPO_RICHIESTA_IMPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
      setDatiIspettoreCertificato(certificato.getIdCertificato(), nuovaRichiesta.getIdIspettoreDocumentale(),
          CaronteConstants.ID_TIPO_CONTROLLO_DOCUMENTALE, idUtente);
      setDatiIspettoreCertificato(certificato.getIdCertificato(), nuovaRichiesta.getIdIspettoreIdentita(),
          CaronteConstants.ID_TIPO_CONTROLLO_IDENTITA, idUtente);
      setDatiIspettoreCertificato(certificato.getIdCertificato(), nuovaRichiesta.getIdIspettoreFitosanitario(),
          CaronteConstants.ID_TIPO_CONTROLLO_FITOSANITARIO, idUtente);
    } else if (CaronteConstants.TIPO_RICHIESTA_EXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())
        || CaronteConstants.TIPO_RICHIESTA_RIEXPORT.equals(nuovaRichiesta.getIdTipoRichiesta())) {
      setDatiIspettoreCertificato(certificato.getIdCertificato(), nuovaRichiesta.getIdIspettoreFirmatario(),
          CaronteConstants.ID_TIPO_CONTROLLO_DOCUMENTALE, idUtente);
    }

    return certificato.getIdCertificato();
  }

  private void setDatiIspettoreCertificato(Long idCertificato, Long idIspettore, Long idTipoControllo, Long idUtente) {
    CarRIspetCertifContrExample filtri = new CarRIspetCertifContrExample();
    filtri.createCriteria().andIdCertificatoEqualTo(idCertificato).andIdTipoControlloEqualTo(idTipoControllo);

    CarRIspetCertifContr ispettore = null;
    List<CarRIspetCertifContr> listaIspettori = ispettoreCertificatoMapper.selectByExample(filtri);

    if (listaIspettori.size() == 0) {
      ispettore = new CarRIspetCertifContr();
      ispettore.setIdCertificato(idCertificato);
      ispettore.setIdTipoControllo(idTipoControllo);
      ispettore.setIdUtenteInsert(idUtente);
      ispettore.setDataInsert(new Date());
    } else {
      ispettore = listaIspettori.get(0);
    }

    ispettore.setIdIspettore(idIspettore);
    ispettore.setIdUtenteUpdate(idUtente);
    ispettore.setDataUpdate(new Date());

    if (ispettore.getIdIspetCertifContr() == null) {
      /*
       * Inserimento
       */
      ispettoreCertificatoMapper.insert(ispettore);
    } else {
      /*
       * Aggiornamento
       */
      ispettoreCertificatoMapper.updateByPrimaryKey(ispettore);
    }
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int aggiornaDatiPagamento(NuovaRichiestaForm nuovaRichiesta, String nomeAllegato, byte[] allegato,
      Long idUtente) throws BusinessException {
    CarTPagamentoExample filtri = new CarTPagamentoExample();
    filtri.createCriteria().andIdRichiestaEqualTo(nuovaRichiesta.getIdRichiesta());

    CarTPagamento pagamento = null;
    List<CarTPagamento> listaPagamenti = pagamentoMapper.selectByExample(filtri);

    if (listaPagamenti.size() == 0) {
      pagamento = new CarTPagamento();
      pagamento.setIdRichiesta(nuovaRichiesta.getIdRichiesta());
      pagamento.setIdUtenteInsert(idUtente);
      pagamento.setDataInsert(new Date());
    } else {
      pagamento = listaPagamenti.get(0);
      pagamento.setIdUtenteUpdate(idUtente);
      pagamento.setDataUpdate(new Date());
    }

    pagamento.setMittente(nuovaRichiesta.getMittentePagamento());
    pagamento.setIdMezzoPagamento(nuovaRichiesta.getIdTipoPagamento());
    pagamento.setDataPagamento(nuovaRichiesta.getDataPagamento());
    pagamento.setNumeroDocumento(nuovaRichiesta.getNumeroDocumento());

    if (!StringUtils.isEmpty(nomeAllegato)) {
      pagamento.setNomeFileAllegato(nomeAllegato);
      pagamento.setAllegato(allegato);
    }

    if (pagamento.getIdPagamento() == null) {
      /*
       * Inserimento del recod
       */
      return pagamentoMapper.insert(pagamento);
    } else if (pagamento.getAllegato() == null) {
      /*
       * Aggiornamento del record
       */
      return pagamentoMapper.updateByPrimaryKey(pagamento);
    } else {
      return pagamentoMapper.updateByPrimaryKeyWithBLOBs(pagamento);
    }
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<RichiestaDto> getElencoRichieste(RicercaRichiestaForm ricercaRichiesta) throws BusinessException {

    return richiestaMapper.getElencoRichieste(ricercaRichiesta);

  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int cancellaRichiesta(Long idRichiesta) throws BusinessException {

    // Tariffe merce richiesta
    richiestaMapper.cancellaTariffeMerciRichiesta(idRichiesta);

    // Tariffe richiesta (per export)
    CarRRichiestaTariffaExample exampleTariffa = new CarRRichiestaTariffaExample();
    exampleTariffa.createCriteria().andIdRichiestaEqualTo(idRichiesta);
    richiestaTariffaMapper.deleteByExample(exampleTariffa);

    // Merci richiesta
    CarRMerceRichiestaExample exampleMerce = new CarRMerceRichiestaExample();
    exampleMerce.createCriteria().andIdRichiestaEqualTo(idRichiesta);
    carRMerceRichiestaMapper.deleteByExample(exampleMerce);

    // Trattamento richiesta
    CarRTrattamentoRichiestaExample exampleTrattamento = new CarRTrattamentoRichiestaExample();
    exampleTrattamento.createCriteria().andIdRichiestaEqualTo(idRichiesta);
    trattamentoMapper.deleteByExample(exampleTrattamento);

    // Ispettori Certificato
    richiestaMapper.cancellaispettoriCertificatiRichiesta(idRichiesta);

    // Certificato
    CarTCertificatoExample exampleCertificato = new CarTCertificatoExample();
    exampleCertificato.createCriteria().andIdRichiestaEqualTo(idRichiesta);
    certificatoMapper.deleteByExample(exampleCertificato);

    // Pagamento
    CarTPagamentoExample examplePagamento = new CarTPagamentoExample();
    examplePagamento.createCriteria().andIdRichiestaEqualTo(idRichiesta);
    pagamentoMapper.deleteByExample(examplePagamento);

    // Richiesta
    return carTRichiestaMapper.deleteByPrimaryKey(idRichiesta);
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public Long copiaRichiesta(Long idRichiesta, Long idUtente) throws BusinessException {
    CarTRichiesta richiesta;
    logger.debug("-- Copia richiesta vecchio idRichiesta = "+ idRichiesta);
    richiesta = carTRichiestaMapper.selectByPrimaryKey(idRichiesta);

    richiesta.setIdRichiesta(null);
    richiesta.setCodRichiesta(null);
    richiesta.setIdUtenteInsert(idUtente);
    richiesta.setIdStatoRichiesta(CaronteConstants.STATO_RICHIESTA_BOZZA);

    richiesta.setDataInsert(new Date());
    richiesta.setDataUpdate(new Date());
    richiesta.setDataInizioDisponibilita(null);
    richiesta.setDataPartenzaMerce(new Date());
    richiesta.setDataInoltro(null);
    richiesta.setDataEsecuzione(null);
    richiesta.setOraInizioDisponibilita(null);

    richiesta.setNotePerIspettore(null);

    carTRichiestaMapper.insertSelective(richiesta);
    
    logger.debug("-- Copia richiesta nuovo idRichiesta = "+ richiesta.getIdRichiesta());

    // Merci richiesta
    CarRMerceRichiestaExample exampleMerce = new CarRMerceRichiestaExample();
    exampleMerce.createCriteria().andIdRichiestaEqualTo(idRichiesta);
    List<CarRMerceRichiesta> listaMerci = carRMerceRichiestaMapper.selectByExample(exampleMerce);

    for (CarRMerceRichiesta merce : listaMerci) {
      Long idMerceRichiesta = merce.getIdMerceRichiesta();
      merce.setIdMerceRichiesta(null);
      merce.setIdRichiesta(richiesta.getIdRichiesta());
      merce.setIdUtenteInsert(idUtente);
      merce.setDataInsert(new Date());
      merce.setIdUtenteUpdate(idUtente);
      merce.setDataUpdate(merce.getDataInsert());

      carRMerceRichiestaMapper.insertSelective(merce);
      logger.debug("-- Copia richiesta merce.getIdMerceRichiesta()= "+ merce.getIdMerceRichiesta());

      // Tariffe merce richiesta
      CarRMerceTariffaExample exampleTariffa = new CarRMerceTariffaExample();
      exampleTariffa.createCriteria().andIdMerceRichiestaEqualTo(idMerceRichiesta);
      List<CarRMerceTariffa> listaTariffe = merceTariffaMapper.selectByExample(exampleTariffa);

      for (CarRMerceTariffa tariffa : listaTariffe) {
        tariffa.setIdMerceRichiesta(merce.getIdMerceRichiesta());
        tariffa.setIdUtenteInsert(idUtente);
        tariffa.setDataInsert(new Date());
        tariffa.setIdUtenteUpdate(idUtente);
        tariffa.setDataUpdate(merce.getDataInsert());

        merceTariffaMapper.insertSelective(tariffa);
        logger.debug("-- Copia richiesta tariffa.getIdTariffa()= "+ tariffa.getIdTariffa());
      }
    }

    // Tariffe richiesta
    CarRRichiestaTariffaExample exampleTariffa = new CarRRichiestaTariffaExample();
    exampleTariffa.createCriteria().andIdRichiestaEqualTo(idRichiesta);
    List<CarRRichiestaTariffa> listaTariffe = richiestaTariffaMapper.selectByExample(exampleTariffa);

    for (CarRRichiestaTariffa tariffa : listaTariffe) {
      tariffa.setIdRichiesta(richiesta.getIdRichiesta());
      tariffa.setIdUtenteInsert(idUtente);
      tariffa.setDataInsert(new Date());
      tariffa.setIdUtenteUpdate(idUtente);
      tariffa.setDataUpdate(tariffa.getDataInsert());

      richiestaTariffaMapper.insertSelective(tariffa);
      logger.debug("-- Copia richiesta tariffaRichiesta getIdTariffa()= "+ tariffa.getIdTariffa());
    }

    // Trattamento richiesta
    CarRTrattamentoRichiestaExample exampleTrattamento = new CarRTrattamentoRichiestaExample();
    exampleTrattamento.createCriteria().andIdRichiestaEqualTo(idRichiesta);
    List<CarRTrattamentoRichiesta> listaTrattamenti = trattamentoMapper.selectByExample(exampleTrattamento);

    for (CarRTrattamentoRichiesta trattamento : listaTrattamenti) {
      // Dovrebbe essercene solo uno...
      trattamento.setIdTrattamentoRichiesta(null);
      trattamento.setIdRichiesta(richiesta.getIdRichiesta());
      trattamento.setIdUtenteInsert(idUtente);
      trattamento.setDataInsert(new Date());
      trattamento.setIdUtenteUpdate(idUtente);
      trattamento.setDataUpdate(trattamento.getDataInsert());

      trattamentoMapper.insertSelective(trattamento);
      logger.debug("-- Copia richiesta trattamento= "+ trattamento.getIdTrattamento());
    }
    logger.debug("-- Copia richiesta fine copia= "+ richiesta.getIdRichiesta());
    return richiesta.getIdRichiesta();
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int modificaRichiesta(CarTRichiesta richiesta) throws BusinessException {

    int status = 0;

    status = carTRichiestaMapper.updateByPrimaryKey(richiesta);

    return status;
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public String stampaRichiesta() throws BusinessException {
    return null;
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<CarDStatoRichiesta> getStatoRichiesta() throws BusinessException {

    return carDStatoRichiestaMapper.selectByExample(new CarDStatoRichiestaExample());

  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public DettaglioRichiestaDto getDettaglioRichiesta(Long idRichiesta) throws BusinessException {

    return richiestaMapper.getDettaglioRichiesta(idRichiesta);

  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<MerceRichiestaDto> getListaMerciRichiesta(Long idRichiesta) throws BusinessException {

    return richiestaMapper.getListaMerciRichiesta(idRichiesta);

  }

  @Override
  public List<CarRCampionamentoPartita> getListaCampionamentiVerbale(Long idRichiesta) throws BusinessException {

    return richiestaMapper.getListaCampionamentiVerbale(idRichiesta);

  }

  @Override
  public List<MerceRichiestaDto> getListaMerciRichiestaExport(Long idRichiesta) throws BusinessException {

    return richiestaMapper.getListaMerciRichiestaExport(idRichiesta);

  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public TariffeRichiestaDto getTotaliTariffeRichiesta(Long idRichiesta) throws BusinessException {

    return richiestaMapper.getTotaliTariffeRichiesta(idRichiesta);

  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<TariffaDto> getListaTariffeProdotti() throws BusinessException {

    return richiestaMapper.getListaTariffeProdotti();

  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public int cancellaTariffeMerciRichiesta(Long idRichiesta) throws BusinessException {

    return richiestaMapper.cancellaTariffeMerciRichiesta(idRichiesta);

  }

  @Override
  public int inserisciTariffeMerciRichiesta(Long idRichiesta, Long idUtente) throws BusinessException {

    return richiestaMapper.inserisciTariffeMerciRichiesta(idRichiesta, idUtente);

  }

  @Override
  public TrattamentoRichiestaDto getTrattamentoRichiesta(Long idRichiesta) throws BusinessException {
    return richiestaMapper.getTrattamentoRichiesta(idRichiesta);
  }

  @Override
  public PagamentoRichiestaDto getPagamentoRichiesta(Long idRichiesta) {
    return richiestaMapper.getPagamentoRichiesta(idRichiesta);
  }

  @Override
  public List<CarDStatoRichiesta> getListaStatiRichiestaSuccessivi(Long idUtente, Long idRichiesta)
      throws BusinessException {
    return richiestaMapper.getListaStatiRichiestaSuccessivi(idUtente, idRichiesta);
  }

  @Override
  public boolean isUtenteAbilitatoModificaRichiesta(Long idUtente, Long idRichiesta) throws BusinessException {
    return richiestaMapper.isUtenteAbilitatoModificaRichiesta(idUtente, idRichiesta);
  }

  @Override
  public CertificatoRichiestaDto getDatiCertificatoRichiesta(Long idRichiesta) throws BusinessException {
    return richiestaMapper.getDatiCertificatoRichiesta(idRichiesta);
  }

  @Override
  /**
   * Il metodo controlla se l'utente è abilitato a modificare i dati della
   * richiesta
   * 
   * @param idUtente
   *          Identificativo dell'utente
   * @param idRichiesta
   *          Identificativo della richiesta
   * @return Boolean Vale TRUE se l'utente è abilitato a modificare i dati della
   *         richiesta, FALSE se l'utente non è abilitato alla modifica ma può
   *         avanzare lo stato della richiesta, NULL altrimenti
   */
  public Boolean isRichiestaModificabile(Long idUtente, Long idRichiesta) throws BusinessException {
    return richiestaMapper.isUtenteAbilitatoModificaRichiesta(idUtente, idRichiesta);
  }

  @Override
  /**
   * Il metodo controlla se l'utente è abilitato a consultare i dati della
   * richiesta
   * 
   * @param idUtente
   *          Identificativo dell'utente
   * @param idRichiesta
   *          Identificativo della richiesta
   * @return boolean Vale TRUE se l'utente è abilitato a consultare i dati della
   *         richiesta, FALSE altrimenti
   */
  public boolean isUtenteAbilitatoLetturaRichiesta(Long idUtente, Long idRichiesta) throws BusinessException {
    return richiestaMapper.isUtenteAbilitatoLetturaRichiesta(idUtente, idRichiesta);
  }

  @Override
  public BigDecimal getImportoPrevistoTariffa(Long idTipoRichiesta, MerceRichiestaDto merceRichiesta)
      throws BusinessException {
    BigDecimal result = BigDecimal.ZERO;

    if (idTipoRichiesta != 1 || isTariffaApplicabile(merceRichiesta)) {
      result = richiestaMapper.getImportoPrevistoTariffa(idTipoRichiesta, merceRichiesta.getIdProdotto(),
          merceRichiesta.getQuantita().doubleValue());
    }

    return result;
  }

  /*
   * Il metodo effettua il lock sulla tabella car_t_certificato e di seguito
   * richiama la funzione per staccare un nuovo numero di certificato (il lock
   * viene effettuato per garantire che la funzione non resituisca lo stesso
   * numero certificato a due transazioni concorrenti)
   */
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  private String getNuovoNumeroCertificato() {
    richiestaMapper.lockCertificato();

    return richiestaMapper.getNuovoNumeroCertificato();
  }

  /*
   * Il metodo effettua il lock del record della richiesta e di seguito verifica
   * che l'utente sia abilitato ad effettuare il cambio di stato. Se il
   * controllo viene superato, si avanza la richiesta al nuovo stato.
   */
  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public boolean avanzaStatoRichiesta(Long idRichiesta, Long idNuovoStato, Long idUtente, String motivazione)
      throws BusinessException {
    richiestaMapper.lockRichiesta(idRichiesta);

    if (richiestaMapper.isUtenteAbilitatoModificaRichiesta(idUtente, idRichiesta) != null) {
      List<CarDStatoRichiesta> listaStatiSuccessivi = richiestaMapper.getListaStatiRichiestaSuccessivi(idUtente,
          idRichiesta);

      for (CarDStatoRichiesta stato : listaStatiSuccessivi) {
        if (stato.getIdStatoRichiesta().equals(idNuovoStato)) {
          CarTRichiesta richiesta = new CarTRichiesta();

          richiesta.setIdRichiesta(idRichiesta);
          richiesta.setIdStatoRichiesta(idNuovoStato);
          richiesta.setIdUtenteUpdate(idUtente);
          richiesta.setDataUpdate(new Date());
          richiesta.setMotivazione(motivazione);

          if (CaronteConstants.STATO_RICHIESTA_INOLTRATA.equals(idNuovoStato)) {
            richiesta.setDataInoltro(new Date());
          } else if (CaronteConstants.STATO_RICHIESTA_ESEGUITA.equals(idNuovoStato)) {
            richiesta.setIdUtenteEsecuzione(idUtente);
            richiesta.setDataEsecuzione(new Date());
          }

          carTRichiestaMapper.updateByPrimaryKeySelective(richiesta);

          if (!StringUtils.isEmpty(motivazione)) {
            String descStatoRichiesta = null;

            if (CaronteConstants.STATO_RICHIESTA_RESTITUITA.equals(idNuovoStato)) {
              descStatoRichiesta = CaronteConstants.DESC_STATO_RICHIESTA_RESTITUITA.toLowerCase();
            } else if (CaronteConstants.STATO_RICHIESTA_ANNULLATA.equals(idNuovoStato)) {
              descStatoRichiesta = CaronteConstants.DESC_STATO_RICHIESTA_ANNULLATA.toLowerCase();
            }

            if (descStatoRichiesta != null) {
              DettaglioRichiestaDto dettaglioRichiesta = richiestaMapper.getDettaglioRichiesta(idRichiesta);
              String subject = "Richiesta numero " + dettaglioRichiesta.getCodRichiesta() + " " + descStatoRichiesta;
              String message = "<html><body><p>Gentile "
                  + StringUtils
                      .capitalize(dettaglioRichiesta.getUtenteCognome() + " " + dettaglioRichiesta.getUtenteNome())
                  + ", " + "<br/>ti avvisiamo che la richiesta numero <b>" + dettaglioRichiesta.getCodRichiesta()
                  + "</b> del " + new DateFormatter().print(dettaglioRichiesta.getDataInsert(), null) + " è stata "
                  + descStatoRichiesta + " con la seguente motivazione: " + "<br /><br />" + motivazione.toUpperCase()
                  + "<br /><br />Questa mail è stata generata in automatico, si prega di non rispondere a questo indirizzo, grazie."
                  + "<br/></p></body></html>";

              try {
                CaronteUtils.postMailWithoutAttachments(CaronteConstants.INDIRIZZO_MITTENTE_REGISTRAZIONE_UTENTE,
                    new String[] { dettaglioRichiesta.getUtenteEmail() }, null, subject, message);
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
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public byte[] getAllegatoPagamento(Long idRichiesta, String nomeAllegato) throws BusinessException {
    CarTPagamentoExample examplePagamento = new CarTPagamentoExample();
    examplePagamento.createCriteria().andIdRichiestaEqualTo(idRichiesta).andNomeFileAllegatoEqualTo(nomeAllegato);
    List<CarTPagamento> listaPagamento = pagamentoMapper.selectByExampleWithBLOBs(examplePagamento);

    if (null != listaPagamento) {
      return listaPagamento.get(0).getAllegato();
    }

    return new byte[0];
  }

  @Override
  public byte[] getAllegatoCertificato(Long idRichiesta) throws BusinessException {

    CarTRichiestaExample richiestaExample = new CarTRichiestaExample();
    richiestaExample.createCriteria().andIdRichiestaEqualTo(idRichiesta);
    List<CarTRichiesta> listaRichieste = carTRichiestaMapper.selectByExample(richiestaExample);

    if (listaRichieste != null) {

      CarDAllegatoExample exampleAllegato = new CarDAllegatoExample();
      exampleAllegato.createCriteria().andIdAllegatoEqualTo(listaRichieste.get(0).getIdAllegato());
      List<CarDAllegato> listaAllegati = allegatoMapper.selectByExampleWithBLOBs(exampleAllegato);

      if (listaAllegati != null) {
        return listaAllegati.get(0).getAllegato();
      }

    }

    return new byte[0];
  }

  @Override
  public String getNomeAllegatoCertificato(Long idAllegato) throws BusinessException {

    CarDAllegatoExample exampleAllegato = new CarDAllegatoExample();
    exampleAllegato.createCriteria().andIdAllegatoEqualTo(idAllegato);
    List<CarDAllegato> listaAllegati = allegatoMapper.selectByExampleWithBLOBs(exampleAllegato);

    if (listaAllegati != null) {
      return listaAllegati.get(0).getNomeFile();
    }

    return null;
  }

  private boolean isTariffaApplicabile(MerceRichiestaDto merceRichiesta) {

    return richiestaMapper.getApplicazioneTariffa(merceRichiesta) != null;

  }

  @Override
  public List<CarTRichiesta> getRichiesteRespinte(long idUtente) throws BusinessException {

    CarTRichiestaExample exampleRichiesta = new CarTRichiestaExample();
    List<Long> idStatiRichiestaList = new ArrayList<Long>();
    idStatiRichiestaList.add(CaronteConstants.STATO_RICHIESTA_RESTITUITA);
    idStatiRichiestaList.add(CaronteConstants.STATO_RICHIESTA_ANNULLATA);    
    Date oneWeekAgo = new Date(System.currentTimeMillis() - (7 * CaronteConstants.DAY_IN_MS));
    exampleRichiesta.createCriteria().andIdUtenteInsertEqualTo(idUtente).andIdStatoRichiestaIn(idStatiRichiestaList)
        .andDataUpdateGreaterThan(oneWeekAgo);
    List<CarTRichiesta> richiesteList = carTRichiestaMapper.selectByExample(exampleRichiesta);

    return richiesteList;

  }

  @Override
  public List<TariffaDto> getTariffeTipoRichiesta(Long idTipoRichiesta, Long idTipoControllo) throws BusinessException {

    return richiestaMapper.getTariffeTipoRichiesta(idTipoRichiesta, idTipoControllo);

  }

  /*
   * Il metodo inserisce un nuovo record sulla tabella CAR_T_UPLOAD_TEMP
   */
  // @Override
  // @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  // public Long salvaUploadTemp(Long idUploadTemp, String nomeFile, byte[]
  // allegato) {
  // Long idUploadTemp = null;
  // /*
  // * Viene effettuato il lock sulla tabella per evitare che thread concorrenti
  // possano
  // * ottenere lo stesso idUploadTemp
  // */
  // richiestaMapper.lockUploadTemp();
  //
  // /*
  // * Vengono cancellati i record inseriti da più di 24 ore
  // */
  // CarTUploadTempExample exampleUploadTemp = new CarTUploadTempExample();
  //
  // Date oneDayAgo = new Date(System.currentTimeMillis() - (CaronteConstants.DAY_IN_MS));
  // exampleUploadTemp.createCriteria().andDataUploadLessThanOrEqualTo(oneDayAgo);
  //
  // uploadTempMapper.deleteByExample(exampleUploadTemp);
  //
  // idUploadTemp = richiestaMapper.getNextIdUploadTemp();
  //
  // CarTUploadTemp uploadTemp = new CarTUploadTemp();
  // uploadTemp.setIdUploadTemp(idUploadTemp);
  // uploadTemp.setNomeFile(nomeFile);
  // uploadTemp.setAllegato(allegato);
  //
  // uploadTempMapper.insertSelective(uploadTemp);
  //
  // return idUploadTemp;
  // }

}
