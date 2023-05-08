package it.aizoon.ersaf.business;

import java.util.List;

import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDGenere;
import it.aizoon.ersaf.dto.generati.CarDGenereExample;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaSpecieForm;
import it.aizoon.ersaf.form.NuovoGenereForm;
import it.aizoon.ersaf.form.RicercaGeneriForm;
import it.aizoon.ersaf.form.RicercaSpecieForm;

public interface IGeneriSpecieEJB extends IAbstractEJB<CarDGenere, CarDGenereExample> {


  public List<GenereDTO> getListaGeneri(RicercaGeneriForm ricercaGeneriForm) throws BusinessException;
  
  public List<GenereDTO> getListaGeneri(String codiceLingua) throws BusinessException;
  
  public Long inserisciNuovoGenere(NuovoGenereForm nuovoGenere, Long idUtente) throws BusinessException;

  List<GenereDTO> getListaGeneri(String denominazioneLike, String codiceLingua) throws BusinessException;
 
  public GenereDTO getDettaglioGenere(Long id, String codiceLingua) throws BusinessException;

  public int aggiornaGenere(NuovoGenereForm nuovoGenereForm, Long idUtente) throws BusinessException;
  
  public List<SpecieDTO> getListaSpecie(RicercaSpecieForm ricercaSpecieForm) throws BusinessException;

  int inserisciNuovaSpecie(NuovaSpecieForm nuovaSpecie, Long idUtente) throws BusinessException ;

  public SpecieDTO getDettaglioSpecie(Long id, String codiceLingua) throws BusinessException;

  public int aggiornaSpecie(NuovaSpecieForm nuovaSpecieForm, Long idUtente) throws BusinessException;
  
  public List<SpecieDTO> getListaSpecieGenere(Long idGenere, String codiceLingua) throws BusinessException;
  
  public List<SpecieDTO> getListaSpecieDenomGenere(String denomGenere, String codiceLingua, String idSpecieSel) throws BusinessException;
  
  public String eliminaGenere(Long idGenere) throws BusinessException;
  
  public String eliminaSpecie(Long idSpecie) throws BusinessException;
  
  public GenereDTO getGenereDenominazione(String denomGenere) throws BusinessException;
  
  public List<SpecieDTO> getListaSpecieByIdVoce(Long idVoce) throws BusinessException;
  
  public List<GenereDTO> getListaGenereByIdGruppoZonaProtetta(Long idGruppoZonaProtetta) throws BusinessException; 
  
  public List<SpecieDTO> getListaSpecieByIdGruppoZPIdGenere(Long idGruppoZonaProtetta, Long idGenere) throws BusinessException;
  
  public Long getIdGenereByDescr(String descrGenere) throws BusinessException;

  public List<GenereSpecieDTO> getListaGeneriSpecieComunicazione(Long idCentroAziendale) throws BusinessException;

  public List<GenereSpecieDTO> getListaGeneriSpecieDomandaRuop(Long idDomanda) throws BusinessException;
  
  public CodeDescriptionDTO getGenereByIdSpecie(Long idSpecie) throws BusinessException;
  
  public List<SpecieDTO> getSpecieGenereByListIdSpecie(List<Long> idSpecie) throws BusinessException;
  
  public CarDOrgNocivo getOrgNocByIdGenere(Long idGenere) throws BusinessException;
  
  public CodeDescriptionDTO getGenereByIdGenere(Long idGenere) throws BusinessException;
  
  public CodeDescriptionDTO getOrgNocivoByIdOrgNocivo(Long idOrgNocivo) throws BusinessException;

  
  
}
