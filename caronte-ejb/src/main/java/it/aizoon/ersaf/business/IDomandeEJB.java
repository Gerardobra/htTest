package it.aizoon.ersaf.business;

import java.util.List;

import javax.ejb.Local;

import org.springframework.stereotype.Component;

import it.aizoon.ersaf.dto.AllegatoDTO;
import it.aizoon.ersaf.dto.CentroAziendaleDomandaDTO;
import it.aizoon.ersaf.dto.CentroAziendaleDto;
import it.aizoon.ersaf.dto.DomandaDto;
import it.aizoon.ersaf.dto.ModuloDTO;
import it.aizoon.ersaf.dto.SitoProduzioneDTO;
import it.aizoon.ersaf.dto.TipologiaAttMaterialeDTO;
import it.aizoon.ersaf.dto.TipologiaDomandaDTO;
import it.aizoon.ersaf.dto.TipologiaProdSpecieDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.dto.ZonaProtettaSpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazione;
import it.aizoon.ersaf.dto.generati.CarRAttMaterialeUtente;
import it.aizoon.ersaf.dto.generati.CarRDomandaCentroAz;
import it.aizoon.ersaf.dto.generati.CarRTipoFlussoCom;
import it.aizoon.ersaf.dto.generati.CarTAllegatoDomanda;
import it.aizoon.ersaf.dto.generati.CarTCentroAziendale;
import it.aizoon.ersaf.dto.generati.CarTDatiDomanda;
import it.aizoon.ersaf.dto.generati.CarTDomanda;
import it.aizoon.ersaf.dto.generati.CarTDomandaExample;
import it.aizoon.ersaf.dto.generati.CarTModulo;
import it.aizoon.ersaf.dto.generati.CarTResponsabilePassaporto;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarTUtente;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.ModaliForm;
import it.aizoon.ersaf.form.NuovaDomandaForm;
import it.aizoon.ersaf.form.RicercaDomandaForm;
import it.aizoon.ersaf.integration.rest.protocollo.dto.ProtocolloOutputDto;

@Component
@Local
public interface IDomandeEJB extends IAbstractEJB<CarTDomanda, CarTDomandaExample> {  

  public List<DomandaDto> getElencoDomande(RicercaDomandaForm ricercaDomanda) throws BusinessException;
  
  public List<DomandaDto> getDomandeRespinte(Long idUtente) throws BusinessException;
  
  public List<CarDTipoComunicazione> getTipiDomandeByIdUtente(Long idUtente, Long idAssociazioneSezione) throws BusinessException;
  
  public CarTUtente getDatiUtenteByIdUtente(Long idUtente) throws BusinessException;
  
  public Long getIdProvinciaByIdComune(Long idComune) throws BusinessException;
  
  public Long inserisciNuovaDomanda(NuovaDomandaForm nuovaDomanda, UtenteDTO utente) throws BusinessException;
  
  public List<CarTSpedizioniere> getAziendeByIdUtente(Long idUtente) throws BusinessException;
  
  public CarTSpedizioniere getSpedizioniereByIdSpedizIdUtente(Long idAzienda, Long idUtente) throws BusinessException;
  
  public int aggiornaDatiAzienda(NuovaDomandaForm form, Long idUtente) throws BusinessException;
  
  public void aggiornaTipologia(NuovaDomandaForm form,Long idUtente) throws BusinessException;
  
  public List<CentroAziendaleDto> getCentriAziendaliByIdSpediz(Long idSpedizioniere) throws BusinessException;
  
  public CarTCentroAziendale getCentroAziendaleById(Long idCentroAziendale) throws BusinessException;
  
  public String getSiglaProvByIdProvincia(Long idProvincia) throws BusinessException;
  
  public String getCodiceCentroAzByIdProvIdSpediz(Long idProvincia, Long idSpedizioniere) throws BusinessException;
  
  public Boolean isDomandaModificabile(Long idUtente, Long idDomanda) throws BusinessException;
  
  public int aggiornaDatiAnagrafici(NuovaDomandaForm formDomanda, UtenteDTO utente) throws BusinessException;
  
  public Long salvaCentroAziendaleDomanda(NuovaDomandaForm formDomanda, CentroAziendaleDomandaDTO centroAz, UtenteDTO utente) throws BusinessException;
  
  public List<CentroAziendaleDomandaDTO> getCentriAziendaliByIdDomanda(Long idDomanda) throws BusinessException;
  
  public List<TipologiaProdSpecieDTO> getTipologieProdCentroAz(Long idCentroAziendale, Long idDomanda) throws BusinessException;
  
  public List<SitoProduzioneDTO> getSitiProduzioneCentroAz(Long idCentroAziendale) throws BusinessException;
  
  public Long salvaTipologiaProduttivaCentroAz(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException;
  
  public Long salvaSitoProduzioneCentroAz(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException;
  
  public Long salvaVociPassaporto(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException;
  
  public List<TipologiaProdSpecieDTO> getTipologieProdByIdDomanda(Long idDomanda, Long idTipoModello, Long idGruppo) throws BusinessException;
  
  public List<ZonaProtettaSpecieDTO> getZoneProtetteSpecieByIdDomanda(Long idDomanda) throws BusinessException;
  
  public Long salvaTipologiaProdSpeciePassaporto(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException;
  
  public Long salvaZonaProtettaSpeciePassaporto(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException;
  
  public void eliminaTipologProduttiva(Long idVoceUtente, Long idSpecie, Long idDomanda, Long idUtente) throws BusinessException;
  
  public void eliminaSitoProduzione(Long idSitoProduzione, Long idDomanda, Long idUtente) throws BusinessException;
  
  public void slegaCentroAziendaleDomanda(Long idCentroAz, Long idDomanda, Long idUtente) throws BusinessException;
  
  public void eliminaZonaProtetta(Long idGruppoZonaProtetta, Long idSpecie, Long idDomanda, Long idUtente) throws BusinessException;
  
  public Long salvaTipologiaProdSpecieImport(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException;
  
  public void salvaDatiImport(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException;
  
  public List<TipologiaAttMaterialeDTO> getTipologieAttMateriale(Long iDomanda) throws BusinessException;
  
  public void eliminaTipologAttivita(Long idAttivitaMaterialeUtente, Long idDomanda, Long idUtente) throws BusinessException;

  public Long salvaTipologiaProdSpecieExport(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException;

  public byte[] getTemplateTipoStampaById(Long idTipoStampa) throws BusinessException;

  public byte[] getTemplateTipoStampa(Long idDomanda) throws BusinessException;
  
  public void salvaDatiExport(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException;
  
  public List<AllegatoDTO> getListaAllegatiDomanda(Long idDomanda) throws BusinessException;
  
  public void aggiornaDatiAllegati(Long idDomanda, List<AllegatoDTO> listaAllegati, Long idUtente) throws BusinessException;
  
  public CarTAllegatoDomanda getAllegatoDomandaById(Long idAllegatoDomanda) throws BusinessException;
  
  public DomandaDto getDettaglioAnagraficaByIdDomanda(Long idDomanda) throws BusinessException;
  
  public TipologiaDomandaDTO getDettTipologiaDomanda(Long idDomanda) throws BusinessException;
  
  public List<CarDStatoComunicazione> getListaStatiDomandaSuccessivi(Long idUtente, Long idDomanda) throws BusinessException;
  
  public boolean avanzaStatoDomanda(Long idDomanda, Long idNuovoStato, Long idUtente, String motivazione, String codiceRuop) throws BusinessException;
  
  public Boolean checkAllegatiByIdDomanda(Long idDomanda) throws BusinessException;
  
  public DomandaDto getDettaglioAziendaByIdDomanda(Long idDomanda) throws BusinessException;
  
  public Long[] getVociUtenteByIdTipoModelloGruppo(Long idTipoModello, Long idGruppo, Long idDomanda) throws BusinessException;
  
  public CarTResponsabilePassaporto getRespPassaportoByIdDomanda(Long idDomanda) throws BusinessException;
  
  public CarTDatiDomanda getDatiAggiuntiviByIdDomanda(Long idDomanda) throws BusinessException;
  
  public String getNoteVoceUtenteByIdTipoModelloGruppo(Long idTipoModello, Long idGruppo, Long idDomanda) throws BusinessException;
  
  public DomandaDto getDettaglioAnagraficaAziendaByIdDomanda(Long idDomanda) throws BusinessException;
  
  public CentroAziendaleDomandaDTO getDettaglioCentroAziendale(Long idCentroAziendale, Long idDomanda) throws BusinessException;
  
  public String[] getDescrVociUtenteByIdTipoModelloGruppo(Long idTipoModello, Long idGruppo, Long idDomanda) throws BusinessException;
  
  public String[] getDescrTipologieDomandaByIdDomanda(Long idDomanda) throws BusinessException;
  
  public void salvaStampaDomandaFirmata(Long idDomanda, UtenteDTO utente, byte [] pdfStampaDomanda, String nomeFileStampa) throws BusinessException;
  
  public CarTDatiDomanda getStampaFirmata(Long idDomanda) throws BusinessException;

  public DomandaDto getDettaglioGestioneByIdDomanda(Long id) throws BusinessException;

  public void aggiornaDatiGestione(NuovaDomandaForm form, Long idUtente) throws BusinessException;

  public Long countAllegatiByIdDomandaIdTipoAllegato(Long idDomanda, Long idTipoAllegato) throws BusinessException;
  
  public Long getIdRuoloByIdUtente(Long idUtente) throws BusinessException;

  public TipologiaAttMaterialeDTO getAttivitaMaterialeById(Long idAttivitaMaterialeUtente) throws BusinessException;

  public void inviaMailGestione(NuovaDomandaForm form) throws BusinessException;

  public List<CarDTipoComunicazione> getTipiDomandaFlusso(Long idUtente, Long idAssociazioneSezione) throws BusinessException;  
  
  public void salvaTipologiaAttMateriale(ModaliForm form, UtenteDTO utente) throws BusinessException;
  
  public List<CarRAttMaterialeUtente> getAttivitaMaterialiByIdDomanda(Long idDomanda) throws BusinessException;
  
  public String[] getTipologieByIdDomanda(Long idDomanda) throws BusinessException;
  
  public void aggiornaRichiestaPassaporto(NuovaDomandaForm form, Long idUtente) throws BusinessException;

  public CarRTipoFlussoCom getTipoDomandaSuccessiva(Long idDomanda) throws BusinessException;

  public ModuloDTO getModuloById(Long idModulo) throws BusinessException;

  public void aggiornaDatModuli(Long idDomanda, List<ModuloDTO> listaModuli, Long idUtente) throws BusinessException;
  
  public void duplicaAllegati(Long idDomanda, Long idDomandaPrec, Long idUtente) throws BusinessException;
  
  public String abilitaCreaNuovaDomandaFlusso(Long idUtente, Long idDomanda) throws BusinessException;
  
  public List<DomandaDto> getListaFlussoDomanda(Long idDomanda) throws BusinessException;
  
  public List<DomandaDto> getDomandeInoltrate(Long idUtente) throws BusinessException;

  public DomandaDto getDomandaByIdCentroAziendale(Long idCentroAz)throws BusinessException;
  
  public String getTipologieAttivitaByIdDomanda(Long idDomanda) throws BusinessException;
  
  public Long getIdCentroAziendalePassaportoByIdDomanda(Long idDomanda) throws BusinessException;

  public boolean isRuopValido(String codiceRuop, Long idSpedizioniere) throws BusinessException;
  
  public Long checkIfCentroAzExists(Long idComune, String indirizzo, String codiceFiscale) throws BusinessException;
    
  public Long checkIfCentroAzExistsByIdDomanda(Long idDomanda, Long idComune, String indirizzo) throws BusinessException;
  
  public Long preSalvataggioCentroAziendaleDomanda(Long idDomanda, CentroAziendaleDto centroAziendale, UtenteDTO utente) throws BusinessException;

  public boolean hasTipologiaAttMaterialeById(Long idTipoAttivita, Long idDomanda) throws BusinessException;
  
  public void salvaProtocolloDomanda(Long idDomanda, ProtocolloOutputDto protocolloDto, UtenteDTO utente) throws BusinessException;
  
  public List<CarTModulo> getModuloByIdDomanda(Long idDomanda) throws BusinessException;
  
  public void inviaMailDomandaProtocollata(Long idDomanda, Long idUtente) throws BusinessException;
  
  public Long getIdDomandaRuopConvalidata(Long idSpedizioniere ) throws BusinessException;
  
  public void deleteDomandaPassaporto(Long idDomanda) throws BusinessException;
  
  public CarTDomanda getCarTDomandaByIdDomanda(Long idDomanda) throws BusinessException;

  public void eliminaDomandaByIdDomanda(Long id) throws BusinessException;
  
  public Long checkVersioneDomanda(Long idDomanda) throws BusinessException;

  public void salvaCentroAziendale(NuovaDomandaForm form, UtenteDTO utente) throws BusinessException;

  public CarRDomandaCentroAz getDomandaCentroAzByIdDomandaIdCentroAz(Long idDomanda, Long idCentroAzSel) throws BusinessException;		  

}
