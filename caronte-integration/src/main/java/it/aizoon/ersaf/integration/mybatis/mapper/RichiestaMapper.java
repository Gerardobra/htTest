package it.aizoon.ersaf.integration.mybatis.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.CertificatoRichiestaDto;
import it.aizoon.ersaf.dto.DettaglioRichiestaDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.MerceRichiestaDto;
import it.aizoon.ersaf.dto.PagamentoRichiestaDto;
import it.aizoon.ersaf.dto.RichiestaDto;
import it.aizoon.ersaf.dto.TariffaDto;
import it.aizoon.ersaf.dto.TariffeRichiestaDto;
import it.aizoon.ersaf.dto.TrattamentoRichiestaDto;
import it.aizoon.ersaf.dto.generati.CarDStatoRichiesta;
import it.aizoon.ersaf.dto.generati.CarRCampionamentoPartita;
import it.aizoon.ersaf.dto.generati.CarTRichiesta;
import it.aizoon.ersaf.form.NuovaRichiestaForm;
import it.aizoon.ersaf.form.RicercaRichiestaForm;

/**
 * @author francesco.giuffrida
 */
@Mapper
public interface RichiestaMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<BaseDto, GenericExample> {

  Long inserisciNuovaRichiesta(@Param("nuovaRichiesta") NuovaRichiestaForm nuovaRichiesta,
      @Param("idUtente") Long idUtente);

  List<RichiestaDto> getElencoRichieste(RicercaRichiestaForm ricercaRichiesta);

  int cancellaRichiesta(Long idRichiesta);

  int modificaRichiesta(CarTRichiesta richiesta);

  int copiaRichiesta(Long idRichiesta, Long utente);

  String stampaRichiesta();

  DettaglioRichiestaDto getDettaglioRichiesta(Long idRichiesta);

  int updateDatiRichiesta(CarTRichiesta richiesta);

  int updateDatiMittente(CarTRichiesta richiesta);

  int updateDatiDestinatario(CarTRichiesta richiesta);
  
  int updateDataEsecuzione(CarTRichiesta richiesta);

  int updateDatiTrasporto(CarTRichiesta richiesta);

  List<MerceRichiestaDto> getListaMerciRichiesta(Long idRichiesta);
  
  List<CarRCampionamentoPartita> getListaCampionamentiVerbale(Long idRichiesta);
  
  List<MerceRichiestaDto> getListaMerciRichiestaExport(Long idRichiesta);

  TariffeRichiestaDto getTotaliTariffeRichiesta(Long idRichiesta);

  List<TariffaDto> getListaTariffeProdotti();

  int cancellaTariffeMerciRichiesta(Long idRichiesta);

  int inserisciTariffeMerciRichiesta(@Param("idRichiesta") Long idRichiesta, @Param("idUtente") Long idUtente);
  
  int inserisciTariffeRichiesta(@Param("idRichiesta") Long idRichiesta, @Param("idUtente") Long idUtente);

  TrattamentoRichiestaDto getTrattamentoRichiesta(Long idRichiesta);

  PagamentoRichiestaDto getPagamentoRichiesta(Long idRichiesta);

  List<CarDStatoRichiesta> getListaStatiRichiestaSuccessivi(@Param("idUtente") Long idUtente,
      @Param("idRichiesta") Long idRichiesta);

  Boolean isUtenteAbilitatoModificaRichiesta(@Param("idUtente") Long idUtente, @Param("idRichiesta") Long idRichiesta);

  Boolean isUtenteAbilitatoLetturaRichiesta(@Param("idUtente") Long idUtente, @Param("idRichiesta") Long idRichiesta);
  
  CertificatoRichiestaDto getDatiCertificatoRichiesta(Long idRichiesta);

  BigDecimal getImportoPrevistoTariffa(@Param("idTipoRichiesta") Long idTipoRichiesta,
      @Param("idProdotto") Long idProdotto, @Param("quantita") Double quantita);

  List<Long> lockCertificato();
  
  String getNuovoNumeroCertificato();
  
  Long lockRichiesta(Long idRichiesta);
  
  int cancellaispettoriCertificatiRichiesta(Long idRichiesta);
  
  Long getApplicazioneTariffa(MerceRichiestaDto merceRichiesta); 
  
  List<TariffaDto> getTariffeTipoRichiesta(@Param("idTipoRichiesta") Long idTipoRichiesta, @Param("idTipoControllo") Long idTipoControllo);
  
  List<Long> lockUploadTemp();
  
  Long getNextIdUploadTemp();
  
}
