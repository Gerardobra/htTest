package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.ReportDTO;
import it.aizoon.ersaf.dto.generati.CarTVerbaleIspezione;
import it.aizoon.ersaf.form.RicercaReportAutorizzazioniForm;
import it.aizoon.ersaf.form.RicercaReportControlliForm;
import it.aizoon.ersaf.form.RicercaReportForm;
import it.aizoon.ersaf.form.RicercaReportVivaiForm;

/**
 * @author alessandro.morra
 */
@Mapper
public interface ReportMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<BaseDto, GenericExample> {

  List<ReportDTO> getElencoReport(RicercaReportForm ricercaReport);
  
  List<ReportDTO> getElencoReportVivai(RicercaReportVivaiForm ricercaReport);

  int updateDatiIspezioneVerbale(CarTVerbaleIspezione verbaleIspezione);
  
  int updateDatiSpedizioneVerbale(CarTVerbaleIspezione verbaleIspezione);

  int updateDatiControlloEsitoVerbale(CarTVerbaleIspezione verbaleIspezione);

  int updateDatiMisureUfficialiVerbale(CarTVerbaleIspezione verbaleIspezione);

  int updateDatiCustodiaVerbale(CarTVerbaleIspezione verbaleIspezione);

  int updateDatiDichiarazioniNoteVerbale(CarTVerbaleIspezione verbaleIspezione);
  
  List<ReportDTO> getElencoReportComVivai(RicercaReportVivaiForm ricercaReport);

  List<ReportDTO> getElencoReportControlli(RicercaReportControlliForm ricercaReportForm);

  List<ReportDTO> getElencoReportCampioni(RicercaReportControlliForm ricercaReportForm);

  List<ReportDTO> getElencoReportMisure(RicercaReportControlliForm ricercaReportForm);
  
  List<ReportDTO> getElencoReportAziendeRuopAll(RicercaReportAutorizzazioniForm ricercaReport);
  
  List<ReportDTO> getElencoReportAziendeConDomandaRuop(RicercaReportAutorizzazioniForm ricercaReport);

  List<ReportDTO> getElencoReportMonitoraggi(RicercaReportControlliForm ricercaReportForm);
  
  List<ReportDTO> getElencoReportCentriAzRuop(RicercaReportAutorizzazioniForm ricercaReport);

List<ReportDTO> getElencoReportVivaiNoOn(RicercaReportVivaiForm ricercaReport);

}
