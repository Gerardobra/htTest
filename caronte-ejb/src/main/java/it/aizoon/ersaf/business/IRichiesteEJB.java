package it.aizoon.ersaf.business;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import org.springframework.stereotype.Component;

import it.aizoon.ersaf.dto.CertificatoRichiestaDto;
import it.aizoon.ersaf.dto.DettaglioRichiestaDto;
import it.aizoon.ersaf.dto.MerceRichiestaDto;
import it.aizoon.ersaf.dto.PagamentoRichiestaDto;
import it.aizoon.ersaf.dto.RichiestaDto;
import it.aizoon.ersaf.dto.TariffaDto;
import it.aizoon.ersaf.dto.TariffeRichiestaDto;
import it.aizoon.ersaf.dto.TrattamentoRichiestaDto;
import it.aizoon.ersaf.dto.generati.CarDStatoRichiesta;
import it.aizoon.ersaf.dto.generati.CarRCampionamentoPartita;
import it.aizoon.ersaf.dto.generati.CarRMerceRichiesta;
import it.aizoon.ersaf.dto.generati.CarTRichiesta;
import it.aizoon.ersaf.dto.generati.CarTRichiestaExample;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaRichiestaForm;
import it.aizoon.ersaf.form.RicercaRichiestaForm;

@Component
@Local
public interface IRichiesteEJB extends IAbstractEJB<CarTRichiesta, CarTRichiestaExample> {

  public Long inserisciNuovaRichiesta(NuovaRichiestaForm nuovaRichiesta, Long idUtente, Long idAllegato) throws BusinessException;

  public List<RichiestaDto> getElencoRichieste(RicercaRichiestaForm ricercaRichiesta) throws BusinessException;

  public int cancellaRichiesta(Long idRichiesta) throws BusinessException;

  public int modificaRichiesta(CarTRichiesta richiesta) throws BusinessException;

  public Long copiaRichiesta(Long idRichiesta, Long utente) throws BusinessException;

  public String stampaRichiesta() throws BusinessException;

  public List<CarDStatoRichiesta> getStatoRichiesta() throws BusinessException;

  public DettaglioRichiestaDto getDettaglioRichiesta(Long idRichiesta) throws BusinessException;

  public int aggiornaDatiRichiesta(NuovaRichiestaForm nuovaRichiesta, Long idUtente, Long idAllegato) throws BusinessException;

  public int aggiornaDatiMittente(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException;

  public int aggiornaDatiDestinatario(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException;

  public int aggiornaDatiTrasporto(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException;

  public int aggiornaDatiMerce(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException;

  public int aggiornaDatiTrattamento(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException;

  public Long aggiornaDatiCertificato(NuovaRichiestaForm nuovaRichiesta, Long idUtente) throws BusinessException;

  public int aggiornaDatiPagamento(NuovaRichiestaForm nuovaRichiesta, String nomeAllegato, byte[] allegato,
      Long idUtente) throws BusinessException;

  public int inserisciDatiMerce(CarRMerceRichiesta merceRichiesta, Long idUtente) throws BusinessException;

  public int cancellaDatiMerce(Long idMerceRichiesta) throws BusinessException;

  public List<MerceRichiestaDto> getListaMerciRichiesta(Long idRichiesta) throws BusinessException;
  
  public List<CarRCampionamentoPartita> getListaCampionamentiVerbale(Long idRichiesta) throws BusinessException;
  
  public List<MerceRichiestaDto> getListaMerciRichiestaExport(Long idRichiesta) throws BusinessException;

  public TariffeRichiestaDto getTotaliTariffeRichiesta(Long idRichiesta) throws BusinessException;

  public List<TariffaDto> getListaTariffeProdotti() throws BusinessException;

  public int cancellaTariffeMerciRichiesta(Long idRichiesta) throws BusinessException;

  public int inserisciTariffeMerciRichiesta(Long idRichiesta, Long idUtente) throws BusinessException;

  public TrattamentoRichiestaDto getTrattamentoRichiesta(Long idRichiesta) throws BusinessException;

  public PagamentoRichiestaDto getPagamentoRichiesta(Long idRichiesta) throws BusinessException;

  public List<CarDStatoRichiesta> getListaStatiRichiestaSuccessivi(Long idUtente, Long idRichiesta)
      throws BusinessException;

  public boolean isUtenteAbilitatoModificaRichiesta(Long idUtente, Long idRichiesta) throws BusinessException;
  
  public boolean isUtenteAbilitatoLetturaRichiesta(Long idUtente, Long idRichiesta) throws BusinessException;

  public CertificatoRichiestaDto getDatiCertificatoRichiesta(Long idRichiesta) throws BusinessException;

  Boolean isRichiestaModificabile(Long idUtente, Long idRichiesta) throws BusinessException;

  public BigDecimal getImportoPrevistoTariffa(Long idTipoRichiesta, MerceRichiestaDto merceRichiesta)
      throws BusinessException;

  public boolean avanzaStatoRichiesta(Long idRichiesta, Long idNuovoStato, Long idUtente, String motivazione) throws BusinessException;
  
  public byte[] getAllegatoPagamento(Long idRichiesta, String nomeAllegato) throws BusinessException;

  public byte[] getAllegatoCertificato(Long idRichiesta) throws BusinessException;

  public String getNomeAllegatoCertificato(Long idAllegato) throws BusinessException;

  public Long inserisciAllegatoCertificato(String nomeAllegato, byte[] allegato) throws BusinessException;
  
  List<CarTRichiesta> getRichiesteRespinte(long idUtente) throws BusinessException;
  
  public List<TariffaDto> getTariffeTipoRichiesta(Long idTipoRichiesta, Long idTipoControllo) throws BusinessException;
  
}
