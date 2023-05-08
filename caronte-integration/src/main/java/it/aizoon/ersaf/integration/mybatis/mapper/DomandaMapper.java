package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.AllegatoDTO;
import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.CentroAziendaleDomandaDTO;
import it.aizoon.ersaf.dto.DomandaDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.ModuloDTO;
import it.aizoon.ersaf.dto.SitoProduzioneDTO;
import it.aizoon.ersaf.dto.TipologiaAttMaterialeDTO;
import it.aizoon.ersaf.dto.TipologiaProdSpecieDTO;
import it.aizoon.ersaf.dto.ZonaProtettaSpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazione;
import it.aizoon.ersaf.dto.generati.CarRDomandaTipologia;
import it.aizoon.ersaf.dto.generati.CarRTipoFlussoCom;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarTUtente;
import it.aizoon.ersaf.dto.generati.CarTVoceUtente;
import it.aizoon.ersaf.form.RicercaDomandaForm;

@Mapper
public interface DomandaMapper extends IBaseDao<BaseDto, GenericExample> { 

  public List<DomandaDto> getElencoDomande(RicercaDomandaForm ricercaDomanda);
  
  public List<DomandaDto> getElencoDomandeStato(@Param("idUtente") Long idUtente, @Param("dataLimite") Date dataLimite, @Param("listaStati") List<Long> listaStati);
  
  public List<CarDTipoComunicazione> getTipiDomandeByIdUtente(@Param("idUtente") Long idUtente, @Param("idAssociazioneSezione") Long idAssociazioneSezione);

  public Long getMaxProgressivoFlussoDomanda(Long idFlusso);
  
  public List<CarTSpedizioniere> getAziendeByIdUtente(@Param("idUtente") Long idUtente);
  
  public CarTSpedizioniere getSpedizioniereByIdSpedizIdUtente(@Param("idAzienda") Long idAzienda, @Param("idUtente") Long idUtente);
  
  public Long lockDomanda(Long idDomanda);
  
  public String getSiglaProvByIdProvincia(Long idProvincia);
  
  public String getCodiceCentroAzByIdProvIdSpediz(@Param("idProvincia") Long idProvincia, @Param("idSpedizioniere") Long idSpedizioniere);
  
  Boolean isUtenteAbilitatoModificaDomanda(@Param("idUtente") Long idUtente, @Param("idDomanda") Long idDomanda);
  
  public List<CentroAziendaleDomandaDTO> getCentriAziendaliByIdDomanda(@Param("idDomanda") Long idDomanda);
  
  public List<TipologiaProdSpecieDTO> getTipologieProdCentroAz(@Param("idCentroAziendale") Long idCentroAziendale,@Param("idDomanda") Long idDomanda);
  
  public List<SitoProduzioneDTO> getSitiProduzioneCentroAz(@Param("idCentroAziendale") Long idCentroAziendale);
  
  public List<TipologiaProdSpecieDTO> getTipologieProdByIdDomanda(@Param("idDomanda") Long idDomanda, @Param("idTipoModello") Long idTipoModello, @Param("gruppo") Long idGruppo);
  
  public List<ZonaProtettaSpecieDTO> getZoneProtetteSpecieByIdDomanda(@Param("idDomanda") Long idDomanda);
  
  public List<TipologiaAttMaterialeDTO> getTipologieAttMateriale(@Param("idDomanda") Long iDomanda);
  
  public void eliminaVoceUtente(@Param("idDomanda") Long idDomanda,@Param("idTipoModello") Long idTipoModello,@Param("gruppo") Long gruppo);
  
  public List<AllegatoDTO> getListaAllegatiDomanda(@Param("idDomanda") Long idDomanda);
  
  public DomandaDto getDettaglioAnagraficaByIdDomanda(@Param("idDomanda") Long idDomanda);
  
  public List<CarRDomandaTipologia> getDettTipologiaDomanda(@Param("idDomanda") Long idDomanda);
  
  List<CarDStatoComunicazione> getListaStatiDomandaSuccessivi(@Param("idUtente") Long idUtente, @Param("idDomanda") Long idDomanda);
  
  public String checkAllegatiByIdDomanda(@Param("idDomanda") Long idDomanda);
  
  public DomandaDto getDettaglioAziendaByIdDomanda(@Param("idDomanda") Long idDomanda);
  
  public Long[] getVociUtenteByIdTipoModelloGruppo(@Param("idTipoModello") Long idTipoModello, @Param("gruppo") Long idGruppo, @Param("idDomanda") Long idDomanda);
  
  public CarTVoceUtente getNoteVoceUtenteByIdTipoModelloGruppo(@Param("idTipoModello") Long idTipoModello, @Param("gruppo") Long idGruppo, @Param("idDomanda") Long idDomanda);
  
  public DomandaDto getDettaglioAnagraficaAziendaByIdDomanda(@Param("idDomanda") Long idDomanda);
  
  public String[] getDescrVociUtenteByIdTipoModelloGruppo(@Param("idTipoModello") Long idTipoModello,@Param("gruppo") Long idGruppo,@Param("idDomanda") Long idDomanda);
  
  public String[] getDescrTipologieDomandaByIdDomanda(@Param("idDomanda") Long idDomanda);
  
  public void updateUtenteAnagrafica(CarTUtente utente);

  public DomandaDto getDettaglioGestioneByIdDomanda(@Param("idDomanda") Long idDomanda);
  
  public Long countAllegatiByIdDomandaIdTipoAllegato(@Param("idDomanda") Long idDomanda,@Param("idTipoAllegato") Long idTipoAllegato);
  
  public TipologiaAttMaterialeDTO getAttivitaMaterialeById(@Param("idAttivitaMaterialeUtente") Long idAttivitaMaterialeUtente);
  
  public List<CarDTipoComunicazione> getTipiDomandaFlusso(@Param("idUtente") Long idUtente, @Param("idAssociazioneSezione") Long idAssociazioneSezione);
  
  public String[] getTipologieByIdDomanda(@Param("idDomanda") Long idDomanda);

  public CarRTipoFlussoCom getTipoDomandaSuccessiva(@Param("idDomanda") Long idDomanda);

  public ModuloDTO getModuloById(@Param("idModulo") Long idModulo);
  
  public Long getStatoAbilitatoByIdUtente(@Param("idUtente") Long idUtente, @Param("idStato") Long idStato);
  
  public List<DomandaDto> getListaFlussoDomanda(@Param("idDomanda") Long idDomanda);

  public DomandaDto getDomandaByIdCentroAziendale(Long idCentroAz);
  
  public String getTipologieAttivitaByIdDomanda(@Param("idDomanda") Long idDomanda);
  
  public Long checkIfCentroAzExists(@Param("idComune") Long idComune, @Param("indirizzo") String indirizzo,  @Param("codiceFiscale") String codiceFiscale);
  
  public Long checkIfCentroAzExistsByIdDomanda(@Param("idDomanda") Long idDomanda, @Param("idComune") Long idComune, @Param("indirizzo") String indirizzo);

  public List<Long> hasTipologiaAttMaterialeById(@Param("idTipoAttivita") Long idTipoAttivita, @Param("idDomanda") Long idDomanda);
  
  public Long getIdDomandaRuopConvalidata(@Param("idSpedizioniere") Long idSpedizioniere);
  
  public List<CentroAziendaleDomandaDTO> getCentroAziendalePassaportoByIdDomanda(@Param("idDomanda") Long idDomanda);
  
  public Long getVersioneDomandaByDataAggiornamento(@Param("dataAggiornamento") Date dataAggiornamento);
  
  public Long getUltimaVersioneDomanda();
  
  }

