package it.aizoon.ersaf.business;

import java.util.List;

import javax.ejb.Local;
import javax.validation.Valid;

import org.springframework.stereotype.Component;

import it.aizoon.ersaf.dto.ReportDTO;
import it.aizoon.ersaf.dto.generati.CarRCampionamentoPartita;
import it.aizoon.ersaf.dto.generati.CarREsitoTipoControllo;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniereExample;
import it.aizoon.ersaf.dto.generati.CarTVerbaleIspezione;
import it.aizoon.ersaf.dto.stampe.EverdeDTO;
import it.aizoon.ersaf.dto.stampe.FitosanitarioExportDTO;
import it.aizoon.ersaf.dto.stampe.FitosanitarioRiexportDTO;
import it.aizoon.ersaf.dto.stampe.NullaOstaDTO;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.DatiVerbaleForm;
import it.aizoon.ersaf.form.RicercaReportControlliForm;
import it.aizoon.ersaf.form.RicercaReportAutorizzazioniForm;

import it.aizoon.ersaf.form.RicercaReportForm;
import it.aizoon.ersaf.form.RicercaReportVivaiForm;

@Component
@Local
public interface IReportEJB extends IAbstractEJB<CarTSpedizioniere, CarTSpedizioniereExample> {

  // Metodi PDF

  NullaOstaDTO getPdfNullaOsta(Long idRichiesta);

  EverdeDTO getPdfEverde(Long idRichiesta) throws BusinessException;

  FitosanitarioExportDTO getPdfCertificatoExport(Long idRichiesta);

  FitosanitarioRiexportDTO getPdfCertificatoRiexport(Long idRichiesta);

  // Metodi comuni
  List<ReportDTO> getElencoReport(RicercaReportForm ricercaReport) throws BusinessException;
  
  // Per Vivai
  List<ReportDTO> getElencoReportVivai(RicercaReportVivaiForm ricercaReport) throws BusinessException;

  // Metodi verbale ispezione(PDF Verde)

  CarTVerbaleIspezione getVerbaleIspezione(Long idCertificato);

  List<CarREsitoTipoControllo> getElencoEsitoTipoControllo(Long idVerbaleIspezione);

  Long inserisciVerbaleIspezione(DatiVerbaleForm form, Long userId);

  int updateDatiIspezioneVerbale(DatiVerbaleForm form, Long userId);
  
  int updateDatiSpedizioneVerbale(DatiVerbaleForm form, Long userId);  

  int updateDatiControlloEsitoVerbale(DatiVerbaleForm form, Long userId);

  int updateDatiMisureUfficialiVerbale(DatiVerbaleForm form, Long userId);

  int updateDatiCustodiaVerbale(DatiVerbaleForm form, Long userId);

  int updateDatiDichiarazioniNoteVerbale(DatiVerbaleForm form, Long userId);

  List<CarRCampionamentoPartita> getElencoCarRCampionamentoPartita(Long idVerbale);
  
  List<ReportDTO> getElencoReportComVivai(RicercaReportVivaiForm ricercaReport) throws BusinessException;
  
  List<ReportDTO> getElencoReportAziendeRuop(RicercaReportAutorizzazioniForm ricercaReport) throws BusinessException;

  List<ReportDTO> getElencoReportControlli(RicercaReportControlliForm ricercaReportForm) throws BusinessException;

  List<ReportDTO> getElencoReportCampioni(RicercaReportControlliForm ricercaReportForm) throws BusinessException;

  List<ReportDTO> getElencoReportMisure(RicercaReportControlliForm ricercaReportForm) throws BusinessException;
  
  List<ReportDTO> getElencoReportCentriAzRuop(RicercaReportAutorizzazioniForm ricercaReport) throws BusinessException;
  

  List<ReportDTO> getElencoReportMonitoraggi(RicercaReportControlliForm ricercaReportForm) throws BusinessException;

  List<ReportDTO> getElencoReportVivaiNoOn(@Valid RicercaReportVivaiForm ricercaReportForm) throws BusinessException;


}
