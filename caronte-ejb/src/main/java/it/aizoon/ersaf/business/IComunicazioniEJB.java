package it.aizoon.ersaf.business;

import java.util.List;

import javax.ejb.Local;

import org.springframework.stereotype.Component;

import it.aizoon.ersaf.dto.CentroAziendaleDto;
import it.aizoon.ersaf.dto.ComunicazioneDto;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazione;
import it.aizoon.ersaf.dto.generati.CarTComunicazione;
import it.aizoon.ersaf.dto.generati.CarTComunicazioneExample;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaComunicazioneForm;
import it.aizoon.ersaf.form.RicercaComunicazioneForm;

@Component
@Local
public interface IComunicazioniEJB extends IAbstractEJB<CarTComunicazione, CarTComunicazioneExample> {

  public List<ComunicazioneDto> getComunicazioniRespinte(Long idUtente) throws BusinessException;

  public List<ComunicazioneDto> getElencoComunicazioni(RicercaComunicazioneForm ricercaComunicazione)
      throws BusinessException;

  public List<CentroAziendaleDto> getListaCentriAziendali(Long idSpedizioniere) throws BusinessException;

  public Boolean isComunicazioneModificabile(Long idUtente, Long idComunicazione) throws BusinessException;

  public Long inserisciComunicazione(NuovaComunicazioneForm datiComunicazione, Long idUtente) throws BusinessException;

  public int aggiornaComunicazione(NuovaComunicazioneForm datiComunicazione, Long idUtente) throws BusinessException;

  public ComunicazioneDto getDettaglioComunicazione(Long idComunicazione) throws BusinessException;

  public int cancellaComunicazione(Long idComunicazione) throws BusinessException;

  public List<CarDStatoComunicazione> getListaStatiComunicazioneSuccessivi(Long idUtente, Long idComunicazione)
      throws BusinessException;

  public boolean avanzaStatoComunicazione(Long idComunicazione, Long idNuovoStato, Long idUtente, String motivazione)
      throws BusinessException;

  public Long copiaComunicazione(Long idComunicazione, Long idUtente) throws BusinessException;
  
  public List<CarDTipoComunicazione> getTipiComunicazioneByIdUtente(Long idUtente, Long idAssociazioneSezione) throws BusinessException;
  
  public List<CarTSpedizioniere> getListaSpedizionieriByDenomRuop(String spedizioniereRuop, Long isAssociazioneSezione) throws BusinessException;
  
  public List<CarTSpedizioniere> getListaSpedizionieriByIdUtente(Long idUtente) throws BusinessException;

  
}
