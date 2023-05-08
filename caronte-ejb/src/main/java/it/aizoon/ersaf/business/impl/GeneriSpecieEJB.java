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

import org.springframework.context.i18n.LocaleContextHolder;

import it.aizoon.ersaf.business.IGeneriSpecieEJB;
import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDAutoreEppo;
import it.aizoon.ersaf.dto.generati.CarDAutoreEppoExample;
import it.aizoon.ersaf.dto.generati.CarDGenere;
import it.aizoon.ersaf.dto.generati.CarDGenereExample;
import it.aizoon.ersaf.dto.generati.CarDLingua;
import it.aizoon.ersaf.dto.generati.CarDLinguaExample;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDSpecie;
import it.aizoon.ersaf.dto.generati.CarDSpecieExample;
import it.aizoon.ersaf.dto.generati.CarRLinguaGenere;
import it.aizoon.ersaf.dto.generati.CarRLinguaGenereExample;
import it.aizoon.ersaf.dto.generati.CarRLinguaSpecie;
import it.aizoon.ersaf.dto.generati.CarRLinguaSpecieExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.exception.DbException;
import it.aizoon.ersaf.form.NuovaSpecieForm;
import it.aizoon.ersaf.form.NuovoGenereForm;
import it.aizoon.ersaf.form.RicercaGeneriForm;
import it.aizoon.ersaf.form.RicercaSpecieForm;
import it.aizoon.ersaf.integration.GeneriSpecieDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.GenereMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.SpecieMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDAutoreEppoMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDGenereMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDLinguaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDNazioneMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarDSpecieMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRLinguaGenereMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRLinguaSpecieMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

/**
 * @author Ivan Morra
 */

@Stateless(name = "GeneriSpecie")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class GeneriSpecieEJB extends AbstractEJB<GeneriSpecieDAO, CarDGenereMapper, CarDGenere, CarDGenereExample>
    implements IGeneriSpecieEJB {

  private final String THIS_CLASS = "[" + GeneriSpecieEJB.class.getCanonicalName() + "]";

  @Inject
  GenereMapper genereMapper;

  @Inject
  CarDGenereMapper dGenereMapper;

  @Inject
  CarDLinguaMapper linguaMapper;

  @Inject
  CarRLinguaGenereMapper linguaGenereMapper;

  @Inject
  CarDNazioneMapper nazioneMapper;

  @Inject
  CarDAutoreEppoMapper autoreEppoMapper;

  @Inject
  SpecieMapper specieMapper;

  @Inject
  CarDSpecieMapper carDSpecieMapper;

  @Inject
  CarRLinguaSpecieMapper linguaSpecieMapper;

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<GenereDTO> getListaGeneri(String codiceLingua) throws BusinessException {

    return genereMapper.getListaGeneri(codiceLingua);

  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public Long inserisciNuovoGenere(NuovoGenereForm nuovoGenere, Long idUtente) throws BusinessException {

    Long idGenere = null;

    if (null != nuovoGenere) {
      CarDGenere genere = new CarDGenere();

      // Effettuare un controllo che il codice Genere sia univoco
      CarDGenereExample filterCodGenereUnivoco = new CarDGenereExample();
      filterCodGenereUnivoco.createCriteria().andCodGenereLikeInsensitive(nuovoGenere.getCodGenere());
      ArrayList<CarDGenere> genereList = (ArrayList<CarDGenere>) dGenereMapper.selectByExample(filterCodGenereUnivoco);
      if (!genereList.isEmpty()) {
        throw new DbException(CaronteUtils.getMessage("error.valore.duplicato", "codice genere"));
      }

      if (null != nuovoGenere.getIdAutoreEppo()) {
        genere.setIdAutoreEppo(nuovoGenere.getIdAutoreEppo());
      }
      // Ottieni oppure crea l'autore Eppo
      else if (null != nuovoGenere.getDescAutoreEppo() && !nuovoGenere.getDescAutoreEppo().isEmpty()) {
        CarDAutoreEppoExample filterAutoreEppo = new CarDAutoreEppoExample();
        filterAutoreEppo.createCriteria().andDescAutoreEppoLike(nuovoGenere.getDescAutoreEppo());
        ArrayList<CarDAutoreEppo> autoriEppoResultList = (ArrayList<CarDAutoreEppo>) autoreEppoMapper
            .selectByExample(filterAutoreEppo);
        if (!autoriEppoResultList.isEmpty()) {
          genere.setIdAutoreEppo(autoriEppoResultList.get(0).getIdAutoreEppo());
        } else {
          // Crea un nuovo AutoreEppo ed inserisci nel database
          CarDAutoreEppo nuovoAutore = new CarDAutoreEppo();
          nuovoAutore.setDescAutoreEppo(nuovoGenere.getDescAutoreEppo());
          autoreEppoMapper.insertSelective(nuovoAutore);
          genere.setIdAutoreEppo(nuovoAutore.getIdAutoreEppo());
        }
      }

      // Inserisco il nuovo genere sul database
      genere.setCodGenere(nuovoGenere.getCodGenere());
      genere.setIdNazione(nuovoGenere.getIdNazione());
      genere.setAttivo(nuovoGenere.isAttivo());
      genere.setDataInsert(new Date());
      genere.setIdUtenteInsert(idUtente);

      dGenereMapper.insertSelective(genere);

      idGenere = genere.getIdGenere();

      /*
       * la denomGenere per default è considerato in lingua latina quindi va
       * inserito un record in car_r_lingua_genere mentre se viene inserito
       * denomGenereLocale deve essere inserito un nuovo record in
       * car_r_lingua_genere sulla base del LocaleContextHolder
       * 
       */
      if (null != idGenere) {
        CarRLinguaGenere linguaGenere = new CarRLinguaGenere();
        linguaGenere.setIdGenere(idGenere);

        // Cerca lingua da car_d_lingua da codLingua
        CarDLinguaExample filterLinguaByCode = new CarDLinguaExample();
        filterLinguaByCode.createCriteria().andCodLinguaEqualTo(CaronteConstants.CODICE_LINGUA_LATINO.toLowerCase());
        ArrayList<CarDLingua> queryResultLinguaDefault = (ArrayList<CarDLingua>) linguaMapper
            .selectByExample(filterLinguaByCode);
        linguaGenere
            .setIdLingua(!queryResultLinguaDefault.isEmpty() ? queryResultLinguaDefault.get(0).getIdLingua() : null);

        linguaGenere.setIdUtenteInsert(idUtente);
        linguaGenere.setPreferred(nuovoGenere.isPreferred());
        linguaGenere.setDenomGenere(nuovoGenere.getDenomGenere());
        // linguaGenere.setDenomGenereCommerciale(nuovoGenere.getDenomGenereCommerciale());
        linguaGenere.setDataInsert(new Date());
        linguaGenereMapper.insertSelective(linguaGenere);

        if (null != nuovoGenere.getDenomGenereLocale() && !nuovoGenere.getDenomGenereLocale().isEmpty()) {
          CarRLinguaGenere linguaGenereLocale = new CarRLinguaGenere();
          linguaGenereLocale.setIdGenere(idGenere);
          linguaGenereLocale.setDenomGenere(nuovoGenere.getDenomGenereLocale());
          linguaGenereLocale.setDenomGenereCommerciale(nuovoGenere.getDenomGenereCommerciale());

          // Cerca lingua da car_d_lingua da codLingua
          filterLinguaByCode = new CarDLinguaExample();
          filterLinguaByCode.createCriteria().andCodLinguaEqualTo(LocaleContextHolder.getLocale().getLanguage());
          ArrayList<CarDLingua> queryResultLingua = (ArrayList<CarDLingua>) linguaMapper
              .selectByExample(filterLinguaByCode);
          linguaGenereLocale.setIdLingua(!queryResultLingua.isEmpty() ? queryResultLingua.get(0).getIdLingua() : null);

          linguaGenereLocale.setIdUtenteInsert(idUtente);
          linguaGenereLocale.setDataInsert(new Date());
          linguaGenereLocale.setPreferred(!nuovoGenere.isPreferred());
          linguaGenereMapper.insertSelective(linguaGenereLocale);
        }
      }
    }

    return idGenere;
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<GenereDTO> getListaGeneri(String denominazioneLike, String codiceLingua) throws BusinessException {

    return genereMapper.getListaGeneriDenominazione(denominazioneLike, codiceLingua);

  }

  @Override
  public List<GenereDTO> getListaGeneri(RicercaGeneriForm ricercaGeneriForm) throws BusinessException {

    return genereMapper.ricercaListaGeneri(ricercaGeneriForm);

  }

  @Override
  public GenereDTO getDettaglioGenere(Long id, String codiceLingua) throws BusinessException {
		return genereMapper.getDettaglioGenere(id, codiceLingua);
  }

  @Override
  public SpecieDTO getDettaglioSpecie(Long id, String codiceLingua) throws BusinessException {
    return specieMapper.getDettaglioSpecie(id, codiceLingua);
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int aggiornaGenere(NuovoGenereForm nuovoGenere, Long idUtente) throws BusinessException {

    if (null != nuovoGenere) {
      CarDGenere genere = new CarDGenere();
      
      if (nuovoGenere.getIdGenere() != null) {
        genere = dGenereMapper.selectByPrimaryKey(nuovoGenere.getIdGenere());
      }

      // Effettuare un controllo che il codice Genere sia univoco
      CarDGenereExample filterCodGenereUnivoco = new CarDGenereExample();
      filterCodGenereUnivoco.createCriteria().andCodGenereLikeInsensitive(nuovoGenere.getCodGenere())
          .andIdGenereNotEqualTo(nuovoGenere.getIdGenere());
      ArrayList<CarDGenere> genereList = (ArrayList<CarDGenere>) dGenereMapper.selectByExample(filterCodGenereUnivoco);
      if (!genereList.isEmpty()) {
        throw new DbException(CaronteUtils.getMessage("error.valore.duplicato", "codice genere"));
      }
      
      genere.setIdAutoreEppo(nuovoGenere.getIdAutoreEppo());

      // Ottieni dal db oppure crea l'autore Eppo
      if (null == nuovoGenere.getIdAutoreEppo() && null != nuovoGenere.getDescAutoreEppo()
          && !nuovoGenere.getDescAutoreEppo().isEmpty()) {
        CarDAutoreEppoExample filterAutoreEppo = new CarDAutoreEppoExample();
        filterAutoreEppo.createCriteria().andDescAutoreEppoLike(nuovoGenere.getDescAutoreEppo());
        ArrayList<CarDAutoreEppo> autoriEppoResultList = (ArrayList<CarDAutoreEppo>) autoreEppoMapper
            .selectByExample(filterAutoreEppo);
        if (!autoriEppoResultList.isEmpty()) {
          genere.setIdAutoreEppo(autoriEppoResultList.get(0).getIdAutoreEppo());
        } else {
          // Crea un nuovo AutoreEppo ed inserisci nel database
          CarDAutoreEppo nuovoAutore = new CarDAutoreEppo();
          nuovoAutore.setDescAutoreEppo(nuovoGenere.getDescAutoreEppo());
          autoreEppoMapper.insertSelective(nuovoAutore);
          genere.setIdAutoreEppo(nuovoAutore.getIdAutoreEppo());
        }
      }

      // Aggiorno il genere sul database
      genere.setCodGenere(nuovoGenere.getCodGenere());
      genere.setIdNazione(nuovoGenere.getIdNazione());
      genere.setAttivo(nuovoGenere.isAttivo());
      genere.setDataUpdate(new Date());
      genere.setIdUtenteUpdate(idUtente);
      genere.setIdGenere(nuovoGenere.getIdGenere());

      dGenereMapper.updateByPrimaryKey(genere);

      /*
       * Aggiorno il record car_r_linga_genere
       */
      CarRLinguaGenere linguaGenere = linguaGenereMapper.selectByPrimaryKey(nuovoGenere.getIdGenere(), nuovoGenere.getIdLingua());

      if (linguaGenere == null) {
        linguaGenere = new CarRLinguaGenere();
        linguaGenere.setIdGenere(nuovoGenere.getIdGenere());
        linguaGenere.setIdLingua(nuovoGenere.getIdLingua());
        linguaGenere.setIdUtenteInsert(idUtente);
        linguaGenere.setDataInsert(new Date());
      }
      
      linguaGenere.setPreferred(nuovoGenere.isPreferred());
      linguaGenere.setDenomGenere(nuovoGenere.getDenomGenere());
      linguaGenere.setDenomGenereCommerciale(nuovoGenere.getDenomGenereCommerciale());
      linguaGenere.setIdUtenteUpdate(idUtente);
      linguaGenere.setDataUpdate(new Date());
      
      linguaGenereMapper.updateByPrimaryKey(linguaGenere);

      if (null != nuovoGenere.getDenomGenereLocale() && !nuovoGenere.getDenomGenereLocale().isEmpty()) {

        CarRLinguaGenere linguaGenereLocale = new CarRLinguaGenere();
        linguaGenereLocale.setIdGenere(nuovoGenere.getIdGenere());
        linguaGenereLocale.setDenomGenere(nuovoGenere.getDenomGenereLocale());
        linguaGenereLocale.setDenomGenereCommerciale(nuovoGenere.getDenomGenereCommerciale());

        if (nuovoGenere.getIdLinguaLocale() == null) {
          // Cerca lingua da car_d_lingua da codLingua
          CarDLinguaExample filterLinguaByCode = new CarDLinguaExample();
          filterLinguaByCode.createCriteria().andCodLinguaEqualTo(LocaleContextHolder.getLocale().getLanguage());
          ArrayList<CarDLingua> queryResultLingua = (ArrayList<CarDLingua>) linguaMapper
              .selectByExample(filterLinguaByCode);
          linguaGenereLocale.setIdLingua(!queryResultLingua.isEmpty() ? queryResultLingua.get(0).getIdLingua() : null);

          linguaGenereLocale.setDataInsert(new Date());
          linguaGenereLocale.setIdUtenteInsert(idUtente);
          linguaGenereLocale.setPreferred(true);
          linguaGenereMapper.insertSelective(linguaGenereLocale);

        } else {

          linguaGenereLocale.setIdLingua(nuovoGenere.getIdLinguaLocale());
          linguaGenereLocale.setDataUpdate(new Date());
          linguaGenereLocale.setIdUtenteUpdate(idUtente);
          linguaGenereMapper.updateByPrimaryKeySelective(linguaGenereLocale);
        }
      } else if (nuovoGenere.getIdLinguaLocale() != null) {
        linguaGenereMapper.deleteByPrimaryKey(nuovoGenere.getIdLinguaLocale());
      }
    }

    return 1;
  }

  @Override
  public List<SpecieDTO> getListaSpecie(RicercaSpecieForm ricercaSpecieForm) throws BusinessException {
    return specieMapper.ricercaListaSpecie(ricercaSpecieForm);
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int inserisciNuovaSpecie(NuovaSpecieForm nuovaSpecie, Long idUtente) throws BusinessException {

    if (null != nuovaSpecie) {
      CarDSpecie specie = new CarDSpecie();

      // Effettuare un controllo che il codice Genere sia univoco
      CarDSpecieExample filterCodSpecieUnivoco = new CarDSpecieExample();
      filterCodSpecieUnivoco.createCriteria().andCodSpecieLikeInsensitive(nuovaSpecie.getCodSpecie());
      ArrayList<CarDSpecie> specieList = (ArrayList<CarDSpecie>) carDSpecieMapper
          .selectByExample(filterCodSpecieUnivoco);
      if (!specieList.isEmpty())
        throw new DbException(CaronteUtils.getMessage("error.valore.duplicato", "codice specie"));

      specie.setIdAutoreEppo(nuovaSpecie.getIdAutoreEppo());

      // Ottieni oppure crea l'autore Eppo
      if (null == nuovaSpecie.getIdAutoreEppo() && null != nuovaSpecie.getDescAutoreEppo()
          && !nuovaSpecie.getDescAutoreEppo().isEmpty()) {
        CarDAutoreEppoExample filterAutoreEppo = new CarDAutoreEppoExample();
        filterAutoreEppo.createCriteria().andDescAutoreEppoLike(nuovaSpecie.getDescAutoreEppo());
        ArrayList<CarDAutoreEppo> autoriEppoResultList = (ArrayList<CarDAutoreEppo>) autoreEppoMapper
            .selectByExample(filterAutoreEppo);
        if (!autoriEppoResultList.isEmpty()) {
          specie.setIdAutoreEppo(autoriEppoResultList.get(0).getIdAutoreEppo());
        } else {
          // Crea un nuovo AutoreEppo ed inserisci nel database
          CarDAutoreEppo nuovoAutore = new CarDAutoreEppo();
          nuovoAutore.setDescAutoreEppo(nuovaSpecie.getDescAutoreEppo());
          autoreEppoMapper.insertSelective(nuovoAutore);
          specie.setIdAutoreEppo(nuovoAutore.getIdAutoreEppo());
        }
      }

      // Inserisco il nuovo genere sul database
      specie.setCodSpecie(nuovaSpecie.getCodSpecie());
      specie.setIdNazione(nuovaSpecie.getIdNazione());
      specie.setAttivo(nuovaSpecie.isAttivo());
      specie.setIdGenere(nuovaSpecie.getIdGenere());
      specie.setDataInsert(nuovaSpecie.getDataInsert());
      specie.setIdUtenteInsert(idUtente);

      carDSpecieMapper.insertSelective(specie);

      Long idSpecie = specie.getIdSpecie();

      /*
       * la denomGenere per default � considerato in lingua latina quindi va
       * inserito un record in car_r_lingua_genere mentre se viene inserito
       * denomGenereLocale deve essere inserito un nuovo record in
       * car_r_lingua_genere sulla base del LocaleContextHolder
       * 
       */
      if (null != idSpecie) {
        CarRLinguaSpecie linguaSpecie = new CarRLinguaSpecie();
        linguaSpecie.setIdSpecie(idSpecie);

        // Cerca lingua da car_d_lingua da codLingua
        CarDLinguaExample filterLinguaByCode = new CarDLinguaExample();
        filterLinguaByCode.createCriteria().andCodLinguaEqualTo(CaronteConstants.CODICE_LINGUA_LATINO.toLowerCase());
        ArrayList<CarDLingua> queryResultLinguaDefault = (ArrayList<CarDLingua>) linguaMapper
            .selectByExample(filterLinguaByCode);
        linguaSpecie
            .setIdLingua(!queryResultLinguaDefault.isEmpty() ? queryResultLinguaDefault.get(0).getIdLingua() : null);

        linguaSpecie.setIdUtenteInsert(idUtente);
        linguaSpecie.setPreferred(nuovaSpecie.isPreferred());
        linguaSpecie.setDenomSpecie(nuovaSpecie.getDenomSpecie());
        linguaSpecie.setDenomSpecieCommerciale(nuovaSpecie.getDenomSpecieCommerciale());
        linguaSpecie.setDataInsert(nuovaSpecie.getDataInsert());
        linguaSpecieMapper.insertSelective(linguaSpecie);

        if (null != nuovaSpecie.getDenomSpecieLocale() && !nuovaSpecie.getDenomSpecieLocale().isEmpty()) {
          CarRLinguaSpecie linguaSpecieLocale = new CarRLinguaSpecie();
          linguaSpecieLocale.setIdSpecie(idSpecie);
          linguaSpecieLocale.setDenomSpecie(nuovaSpecie.getDenomSpecieLocale());
          linguaSpecieLocale.setDenomSpecieCommerciale(nuovaSpecie.getDenomSpecieCommerciale());

          // Cerca lingua da car_d_lingua da codLingua
          filterLinguaByCode = new CarDLinguaExample();
          filterLinguaByCode.createCriteria().andCodLinguaEqualTo(LocaleContextHolder.getLocale().getLanguage());
          ArrayList<CarDLingua> queryResultLingua = (ArrayList<CarDLingua>) linguaMapper
              .selectByExample(filterLinguaByCode);
          linguaSpecieLocale.setIdLingua(!queryResultLingua.isEmpty() ? queryResultLingua.get(0).getIdLingua() : null);

          linguaSpecieLocale.setPreferred(true);
          linguaSpecieLocale.setIdUtenteInsert(idUtente);
          linguaSpecieLocale.setDataInsert(nuovaSpecie.getDataInsert());
          linguaSpecieMapper.insertSelective(linguaSpecieLocale);
        }
      }
    }

    return 1;
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public int aggiornaSpecie(NuovaSpecieForm nuovaSpecie, Long idUtente) throws BusinessException {

    if (null != nuovaSpecie) {
      CarDSpecie specie = new CarDSpecie();

      // Effettuare un controllo che il codice Genere sia univoco
      CarDSpecieExample filterCodSpecieUnivoco = new CarDSpecieExample();
      filterCodSpecieUnivoco.createCriteria().andCodSpecieLikeInsensitive(nuovaSpecie.getCodSpecie())
          .andIdSpecieNotEqualTo(nuovaSpecie.getIdSpecie());
      ArrayList<CarDSpecie> specieList = (ArrayList<CarDSpecie>) carDSpecieMapper
          .selectByExample(filterCodSpecieUnivoco);
      if (!specieList.isEmpty()) {
        throw new DbException(CaronteUtils.getMessage("error.valore.duplicato", "codice specie"));
      }

      // Ottieni oppure crea l'autore Eppo
      if (null != nuovaSpecie.getDescAutoreEppo() && !nuovaSpecie.getDescAutoreEppo().isEmpty()) {
        CarDAutoreEppoExample filterAutoreEppo = new CarDAutoreEppoExample();
        filterAutoreEppo.createCriteria().andDescAutoreEppoLikeInsensitive(nuovaSpecie.getDescAutoreEppo());
        ArrayList<CarDAutoreEppo> autoriEppoResultList = (ArrayList<CarDAutoreEppo>) autoreEppoMapper
            .selectByExample(filterAutoreEppo);
        if (!autoriEppoResultList.isEmpty()) {
          specie.setIdAutoreEppo(autoriEppoResultList.get(0).getIdAutoreEppo());
        } else {
          // Crea un nuovo AutoreEppo ed inserisci nel database
          CarDAutoreEppo nuovoAutore = new CarDAutoreEppo();
          nuovoAutore.setDescAutoreEppo(nuovaSpecie.getDescAutoreEppo());
          autoreEppoMapper.insertSelective(nuovoAutore);
          specie.setIdAutoreEppo(nuovoAutore.getIdAutoreEppo());
        }
      }

      // Aggiorna la specie
      specie.setCodSpecie(nuovaSpecie.getCodSpecie());
      specie.setIdNazione(nuovaSpecie.getIdNazione());
      specie.setAttivo(nuovaSpecie.isAttivo());
      specie.setIdGenere(nuovaSpecie.getIdGenere());
      specie.setIdSpecie(nuovaSpecie.getIdSpecie());

      // Dati aggiornamento
      specie.setIdUtenteUpdate(idUtente);
      specie.setDataUpdate(new Date());

      carDSpecieMapper.updateByPrimaryKeySelective(specie);

      Long idSpecie = specie.getIdSpecie();

      /*
       * la denomGenere per default è considerato in lingua latina quindi va
       * inserito un record in car_r_lingua_genere mentre se viene inserito
       * denomGenereLocale deve essere inserito un nuovo record in
       * car_r_lingua_genere sulla base del LocaleContextHolder
       * 
       */
      if (null != idSpecie) {
        CarRLinguaSpecie linguaSpecie = new CarRLinguaSpecie();
        linguaSpecie.setIdSpecie(idSpecie);
        linguaSpecie.setIdLingua(nuovaSpecie.getIdLingua());

        linguaSpecie.setIdUtenteUpdate(idUtente);
        linguaSpecie.setPreferred(nuovaSpecie.isPreferred());
        linguaSpecie.setDenomSpecie(nuovaSpecie.getDenomSpecie());
        linguaSpecie.setDenomSpecieCommerciale(nuovaSpecie.getDenomSpecieCommerciale());
        linguaSpecie.setDataUpdate(nuovaSpecie.getDataInsert());
        linguaSpecieMapper.updateByPrimaryKeySelective(linguaSpecie);

        if (null != nuovaSpecie.getDenomSpecieLocale() && !nuovaSpecie.getDenomSpecieLocale().isEmpty()) {
          CarRLinguaSpecie linguaSpecieLocale = new CarRLinguaSpecie();
          linguaSpecieLocale.setIdSpecie(idSpecie);
          linguaSpecieLocale.setDenomSpecie(nuovaSpecie.getDenomSpecieLocale());
          linguaSpecieLocale.setDenomSpecieCommerciale(nuovaSpecie.getDenomSpecieCommerciale());

          if (null == nuovaSpecie.getIdLinguaLocale()) {
            // Cerca lingua da car_d_lingua da codLingua
            CarDLinguaExample filterLinguaByCode = new CarDLinguaExample();
            filterLinguaByCode.createCriteria().andCodLinguaEqualTo(LocaleContextHolder.getLocale().getLanguage());
            ArrayList<CarDLingua> queryResultLingua = (ArrayList<CarDLingua>) linguaMapper
                .selectByExample(filterLinguaByCode);
            linguaSpecieLocale
                .setIdLingua(!queryResultLingua.isEmpty() ? queryResultLingua.get(0).getIdLingua() : null);

            linguaSpecieLocale.setPreferred(true);
            linguaSpecieLocale.setIdUtenteInsert(idUtente);
            linguaSpecieLocale.setDataInsert(nuovaSpecie.getDataInsert());
            linguaSpecieMapper.insertSelective(linguaSpecieLocale);

          } else {
            linguaSpecieLocale.setIdLingua(nuovaSpecie.getIdLinguaLocale());
            linguaSpecieLocale.setIdUtenteUpdate(idUtente);
            linguaSpecieLocale.setDataUpdate(new Date());
            linguaSpecieMapper.updateByPrimaryKeySelective(linguaSpecieLocale);
          }

        }
      }
    }

    return 1;
  }

  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<SpecieDTO> getListaSpecieGenere(Long idGenere, String codiceLingua) throws BusinessException {
    return specieMapper.getListaSpecieGenere(idGenere, codiceLingua);
  }
  
  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<SpecieDTO> getListaSpecieDenomGenere(String denomGenere, String codiceLingua, String idSpecieSel) throws BusinessException {
	List<Integer> idSpecieList = null;
    if(idSpecieSel != null && !idSpecieSel.equals("")){
	  idSpecieList = new ArrayList<Integer>();	  
	  String[] separated = idSpecieSel.split(",");	  
	  Integer[] numbers = new Integer[separated.length];
	  for(int i = 0;i < separated.length;i++)        {
	      try {
	        numbers[i] = Integer.parseInt(separated[i]);
	      }
	      catch (NumberFormatException nfe)   {
	        numbers[i] = null;
	      }
	  }	  
	  idSpecieList = Arrays.asList(numbers);
	}
    return specieMapper.getListaSpecieDenomGenere(denomGenere, codiceLingua, idSpecieList);
  }
  
  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public String eliminaGenere(Long idGenere) throws BusinessException {

    String errorMessage = null;
 
    if (CaronteUtils.checkNull(genereMapper.isGenereEliminabile(idGenere))) {
      // Lingua genere
      CarRLinguaGenereExample exampleLinguaGenere = new CarRLinguaGenereExample();
      exampleLinguaGenere.createCriteria().andIdGenereEqualTo(idGenere);
      linguaGenereMapper.deleteByExample(exampleLinguaGenere);

      // Genere
      dGenereMapper.deleteByPrimaryKey(idGenere);
    }else {
      errorMessage = "Il Genere selezionato è in uso, non è possibile eliminarlo";
    }
    
    return errorMessage;
  }
  
  @Override
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public String eliminaSpecie(Long idSpecie) throws BusinessException { 

    String errorMessage = null;
 
    if (CaronteUtils.checkNull(specieMapper.isSpecieEliminabile(idSpecie))) {
      // Lingua specie
      CarRLinguaSpecieExample exampleLinguaSpecie = new CarRLinguaSpecieExample();
      exampleLinguaSpecie.createCriteria().andIdSpecieEqualTo(idSpecie);
      linguaSpecieMapper.deleteByExample(exampleLinguaSpecie);

      // Specie
      carDSpecieMapper.deleteByPrimaryKey(idSpecie);
    }else {
      errorMessage = "La Specie selezionata è in uso, non è possibile eliminarla";
    }
    
    return errorMessage;
  }
  
  public GenereDTO getGenereDenominazione(String denomGenere) throws BusinessException {
    
    return genereMapper.getGenereDenominazione(denomGenere);
    
  }
  
  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<SpecieDTO> getListaSpecieByIdVoce(Long idVoce) throws BusinessException {
    return specieMapper.getListaSpecieByIdVoce(idVoce);
  }
  
  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<GenereDTO> getListaGenereByIdGruppoZonaProtetta(Long idGruppoZonaProtetta) throws BusinessException {
    return genereMapper.getListaGenereByIdGruppoZonaProtetta(idGruppoZonaProtetta);
  }
  
  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public List<SpecieDTO> getListaSpecieByIdGruppoZPIdGenere(Long idGruppoZonaProtetta, Long idGenere) throws BusinessException {
  	return specieMapper.getListaSpecieByIdGruppoZPIdGenere(idGruppoZonaProtetta, idGenere);
  }
  
  @Override
  @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
  public Long getIdGenereByDescr(String descrGenere) throws BusinessException{
	  Long idGenere = null;
	  CarRLinguaGenereExample example = new CarRLinguaGenereExample();
	  example.createCriteria().andDenomGenereEqualTo(descrGenere);
	  List<CarRLinguaGenere> genList = linguaGenereMapper.selectByExample(example);
	  if(genList!= null){
		  CarRLinguaGenere gen = genList.get(0);
		  if(gen != null)
		    idGenere = gen.getIdGenere(); 
	  }
	  return idGenere;
  }

	@Override
	public List<GenereSpecieDTO> getListaGeneriSpecieComunicazione(Long idCentroAziendale) throws BusinessException {
	    return genereMapper.getListaGeneriSpecieComunicazione(idCentroAziendale);
	}

	@Override
	public List<GenereSpecieDTO> getListaGeneriSpecieDomandaRuop(Long idDomanda) throws BusinessException {
	    return genereMapper.getListaGeneriSpecieDomandaRuop(idDomanda);
	}
	
	@Override
	public CodeDescriptionDTO getGenereByIdSpecie(Long idSpecie) throws BusinessException{
		return genereMapper.getGenereByIdSpecie(idSpecie);
	}
	
	@Override 
	public List<SpecieDTO> getSpecieGenereByListIdSpecie(List<Long> idSpecie) throws BusinessException{
		return genereMapper.getSpecieGenereByListIdSpecie(idSpecie);
	}
	
	@Override
	public CarDOrgNocivo getOrgNocByIdGenere(Long idGenere) throws BusinessException{
		return genereMapper.getOrgNocByIdGenere(idGenere);
	}
	
	@Override
	public CodeDescriptionDTO getGenereByIdGenere(Long idGenere) throws BusinessException{
		return genereMapper.getGenereByIdGenere(idGenere);
	}
	
	@Override
	public CodeDescriptionDTO getOrgNocivoByIdOrgNocivo(Long idOrgNocivo) throws BusinessException{
		return genereMapper.getOrgNocivoByIdOrgNocivo(idOrgNocivo);
	}
  
}
