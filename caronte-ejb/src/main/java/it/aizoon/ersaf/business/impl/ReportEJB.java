package it.aizoon.ersaf.business.impl;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Valid;

import it.aizoon.ersaf.business.IReportEJB;
import it.aizoon.ersaf.business.IRichiesteEJB;
import it.aizoon.ersaf.dto.CertificatoRichiestaDto;
import it.aizoon.ersaf.dto.MerceRichiestaVerbaleColliDto;
import it.aizoon.ersaf.dto.MerceRichiestaVerbaleDto;
import it.aizoon.ersaf.dto.ReportDTO;
import it.aizoon.ersaf.dto.generati.CarRCampionamentoPartita;
import it.aizoon.ersaf.dto.generati.CarRCampionamentoPartitaExample;
import it.aizoon.ersaf.dto.generati.CarREsitoTipoControllo;
import it.aizoon.ersaf.dto.generati.CarREsitoTipoControlloExample;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniereExample;
import it.aizoon.ersaf.dto.generati.CarTVerbaleIspezione;
import it.aizoon.ersaf.dto.generati.CarTVerbaleIspezioneExample;
import it.aizoon.ersaf.dto.stampe.EverdeDTO;
import it.aizoon.ersaf.dto.stampe.FitosanitarioExportDTO;
import it.aizoon.ersaf.dto.stampe.FitosanitarioRiexportDTO;
import it.aizoon.ersaf.dto.stampe.NullaOstaDTO;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.DatiVerbaleForm;
import it.aizoon.ersaf.form.RicercaReportAutorizzazioniForm;
import it.aizoon.ersaf.form.RicercaReportControlliForm;
import it.aizoon.ersaf.form.RicercaReportForm;
import it.aizoon.ersaf.form.RicercaReportVivaiForm;
import it.aizoon.ersaf.integration.RichiesteDAO;
import it.aizoon.ersaf.integration.mybatis.mapper.ReportMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.StampeMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarRCampionamentoPartitaMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarREsitoTipoControlloMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTSpedizioniereMapper;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTVerbaleIspezioneMapper;
import it.aizoon.ersaf.interceptor.BusinessExceptionInterceptor;
import it.aizoon.ersaf.interceptor.LoggingInterceptor;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

@Stateless(name = "Report")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
// @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@Interceptors({ LoggingInterceptor.class, BusinessExceptionInterceptor.class })
public class ReportEJB
		extends AbstractEJB<RichiesteDAO, CarTSpedizioniereMapper, CarTSpedizioniere, CarTSpedizioniereExample>
		implements IReportEJB {

	private final String THIS_CLASS = "[" + ReportEJB.class.getCanonicalName() + "]";

	@Inject
	CarTSpedizioniereMapper carTSpedizioniereMapper;

	@Inject
	ReportMapper reportMapper;

	@Inject
	StampeMapper stampeMapper;

	@Inject
	CarTVerbaleIspezioneMapper verbaleMapper;

	@Inject
	private CarREsitoTipoControlloMapper esitoTipoControlloMapper;

	@Inject
	private CarRCampionamentoPartitaMapper carRCampionamentoPartitaMapper;

	@Inject
	private IRichiesteEJB richiesteEJB;
	
	@Override
	public List<ReportDTO> getElencoReportVivai(RicercaReportVivaiForm ricercaReport) throws BusinessException{		
		 return reportMapper.getElencoReportVivai(ricercaReport);		 
	}

	@Override
	public List<ReportDTO> getElencoReport(RicercaReportForm ricercaReport) throws BusinessException {
		return reportMapper.getElencoReport(ricercaReport);		
	}

	@Override
	public NullaOstaDTO getPdfNullaOsta(Long idRichiesta) {
		return stampeMapper.getPdfNullaOsta(idRichiesta);
	}

	@Override
	public EverdeDTO getPdfEverde(Long idRichiesta) throws BusinessException {

		EverdeDTO verbale = stampeMapper.getPdfEverde(idRichiesta);

		if (verbale == null) {
			verbale = new EverdeDTO();
		} else {
			List<CarRCampionamentoPartita> merciList = richiesteEJB.getListaCampionamentiVerbale(idRichiesta);
			CertificatoRichiestaDto certificato = richiesteEJB.getDatiCertificatoRichiesta(idRichiesta);

			popolaDatiMerci(verbale, merciList);

			verbale.initDatiIspezione(certificato);
			verbale.initPezziColli();
			verbale.initDatiControlloEdEsito();
			verbale.initDatiCustodia();
			verbale.initDatiDichNote();
		}

		return verbale;
	}

	@Override
	public FitosanitarioExportDTO getPdfCertificatoExport(Long idRichiesta) {
		FitosanitarioExportDTO export = stampeMapper.getPdfCertificatoExport(idRichiesta).get(0);
		
		return export != null ? export : new FitosanitarioExportDTO();
	}

	@Override
	public FitosanitarioRiexportDTO getPdfCertificatoRiexport(Long idRichiesta) {
		FitosanitarioRiexportDTO riexport = stampeMapper.getPdfCertificatoRiexport(idRichiesta).get(0);
		return riexport != null ? riexport : new FitosanitarioRiexportDTO();
	}

	private void popolaDatiMerci(EverdeDTO verbale, List<CarRCampionamentoPartita> merciList) {
		final Set<String> paeseOrigine = new TreeSet<String>();

		final List<MerceRichiestaVerbaleDto> elencoMerci = new ArrayList<MerceRichiestaVerbaleDto>();

		final List<MerceRichiestaVerbaleColliDto> elencoColli = new ArrayList<MerceRichiestaVerbaleColliDto>();

		for (int i = 0; i < merciList.size(); i++) {
			MerceRichiestaVerbaleDto merce = new MerceRichiestaVerbaleDto();
			MerceRichiestaVerbaleColliDto collo = new MerceRichiestaVerbaleColliDto();

			String idOrdMerce = "";
			if (i / 26 > 0) {
				idOrdMerce += Character.toChars(i / 26 + 97)[0];
			}
			idOrdMerce += Character.toChars(i % 26 + 97)[0];
			merce.setIdOrdMerce(idOrdMerce);

			collo.setCodCollo(idOrdMerce);
			
			String quantitaAnalizzati = merciList.get(i).getQuantitaAnalizzati() != null ? CaronteUtils.formatBigDecimalToFormat(merciList.get(i).getQuantitaAnalizzati(), "###0.###") : "0";
			
			collo.setNumCollo(quantitaAnalizzati);

			merce.setIdMerceRichiesta(merciList.get(i).getIdMerceRichiesta() != null ? BigDecimal.valueOf(merciList.get(i).getIdMerceRichiesta()) : null);

			merce.setDescrizionePartita(merciList.get(i).getDescrizionePartita());

			merce.setKg(merciList.get(i).getKg());
			
			merce.setMc(merciList.get(i).getMc());
			
			merce.setUnita(merciList.get(i).getUnita());
			
			merce.setColli(merciList.get(i).getColli());
			
			merce.setQuantitaAnalizzati(merciList.get(i).getQuantitaAnalizzati());

			//if (merciList.get(i).getCodNazioneOrigine() != null) {
			//	paeseOrigine.add(merciList.get(i).getCodNazioneOrigine());
			///}

			elencoMerci.add(merce);
			elencoColli.add(collo);
		}

		//verbale.setPaeseOrigineProvenienza(paeseOrigine.toString());
		verbale.setElencoMerci(elencoMerci);
		verbale.setElencoColli(elencoColli);
	}

	@Override
	public Long inserisciVerbaleIspezione(DatiVerbaleForm datiVerbaleForm, Long userId) {
		CarTVerbaleIspezione verbaleIspezione = initCarTVerbaleIspezione(datiVerbaleForm, userId);
		int idVerbaleIspezione = verbaleMapper.insert(verbaleIspezione);

		if (idVerbaleIspezione > 0) {
			datiVerbaleForm.setIdVerbale(verbaleIspezione.getIdVerbaleIspezione());
		}

		return new Long(datiVerbaleForm.getIdVerbale());
	}

	private CarTVerbaleIspezione initCarTVerbaleIspezione(DatiVerbaleForm form, Long userId) {
		CarTVerbaleIspezione verbaleIspezione = new CarTVerbaleIspezione();
		if (null != form) {

			verbaleIspezione.setDataInsert(new Date());
			verbaleIspezione.setIdCertificato(form.getIdCertificato());
			verbaleIspezione.setIdUtenteInsert(userId);
			verbaleIspezione.setIdVerbaleIspezione(form.getIdVerbale());

			// Dati ispezione
			verbaleIspezione.setDataIspezione(form.getDataIspezione());
			verbaleIspezione.setOraInizioIspezione(LocalTime.parse(form.getOraInizioIspezione()));
			verbaleIspezione.setOraFineIspezione(LocalTime.parse(form.getOraFineIspezione()));
			verbaleIspezione.setMagazzinoDoganale(form.getMagazzinoDoganale());
			verbaleIspezione.setDelegatoPresenteIspezione(form.getPersonaRiferimento());
			verbaleIspezione.setDelegatoPresenteRuolo(form.getPersonaRiferimentoRuolo());

			// Dati spedizione
			verbaleIspezione.setFlgAltro(form.isTipoProdottoAltro());
			verbaleIspezione.setFlgCorteccia(form.isTipoProdottoCorteccia());
			verbaleIspezione.setFlgFioriRecisi(form.isTipoProdottoFiori());
			verbaleIspezione.setFlgFrutti(form.isTipoProdottoFrutti());
			verbaleIspezione.setFlgImbalLegno(form.isTipoProdottoImballaggi());
			verbaleIspezione.setFlgLegname(form.isTipoProdottoLegname());
			verbaleIspezione.setFlgPartiPianteVive(form.isTipoProdottoPartiPianteVive());
			verbaleIspezione.setFlgPianteVive(form.isTipoProdottoPianteVive());
			verbaleIspezione.setFlgSementi(form.isTipoProdottoSementi());
			verbaleIspezione.setFlgSemi(form.isTipoProdottoSemi());
			verbaleIspezione.setFlgTerraTerriccio(form.isTipoProdottoTerra());
			verbaleIspezione.setAltraTipologiaProdotto(form.getTipoProdottoAltroNote());

			// Dati Controllo ed esito
			verbaleIspezione.setDittaAssisteAnalisiLab("S".equals(form.getVisioneAnalisiRB()) ? true : false);
			verbaleIspezione.setAllegatoConsulto("S".equals(form.getAllegaEvidenzaRB()) ? true : false);
			verbaleIspezione.setConsultoResponsFilosanit("S".equals(form.getConsultoResponsabileRB()) ? true : false);
			verbaleIspezione.setNullaostaRilasciato("S".equals(form.getTermIspezioneNullaOsta()) ? true : false);
			verbaleIspezione.setMotivoNonRilascioNullaosta(form.getTermIspezioneNullaOstaNote());
			verbaleIspezione.setDispostaMisuraUffic(form.isTermIspezioneMisUff());
			verbaleIspezione.setMotivoDispostaMisuraUffic(form.getTermIspezioneMisUffNote());

			// Dati Misure ufficiali
			verbaleIspezione.setSpedizioneRifiutata(form.isMisUffA());
			verbaleIspezione.setTraspEsternoComun(form.isMisUffB());
			verbaleIspezione.setProdottiInfettiRimossi(form.isMisUffC());
			verbaleIspezione.setDistrutto(form.isMisUffD());
			verbaleIspezione.setQuarantena(form.isMisUffE());
			verbaleIspezione.setTrattamentoAdeguato(form.isMisUffF());

			verbaleIspezione.setMotivoRifiutoSpedizione(form.getMisUffANote());
			verbaleIspezione.setMotivoTraspEsternoComun(form.getMisUffBNote());
			verbaleIspezione.setMotivoRimozProdottiInfetti(form.getMisUffCNote());
			verbaleIspezione.setMotivoDistruzione(form.getMisUffDNote());
			verbaleIspezione.setMotivoTrattamentoAdeguato(form.getMisUffFNote());

			// Dati Custodia
			verbaleIspezione.setCustodeMerceMisUffic(form.getCustResponsabileMerce());
			verbaleIspezione.setDocumentoIdentitaCustode(form.getCustDocumentoRespMerce());
			verbaleIspezione.setRuoloCustode(form.getCustRuoloRespMerce());
			verbaleIspezione.setLocaliCustodia(form.getCustLocaliMerce());

			// Dati Dich e Note
			verbaleIspezione.setCopiaVerbConsegnatoPers(form.getDichNoteRespVerbale());
			verbaleIspezione.setRuoloPersona(form.getDichNoteRuoloRespVerbale());
			verbaleIspezione.setDichiarazionePersona(form.getDichNoteDichiarazioneRespVerb());
			verbaleIspezione.setNote(form.getDichNoteNoteRespVerb());
		}

		return verbaleIspezione;
	}

	private CarREsitoTipoControllo initCarREsitoTipoControllo(DatiVerbaleForm form, Long userId) {
		CarREsitoTipoControllo carREsitoTipoControllo = new CarREsitoTipoControllo();
		if (null != form) {

			CarREsitoTipoControlloExample example = new CarREsitoTipoControlloExample();
			example.createCriteria().andIdVerbaleIspezioneEqualTo(form.getIdVerbale());
			esitoTipoControlloMapper.deleteByExample(example);

			carREsitoTipoControllo.setIdVerbaleIspezione(form.getIdVerbale());
			carREsitoTipoControllo.setDataInsert(new Date());
			carREsitoTipoControllo.setDataUpdate(new Date());
			carREsitoTipoControllo.setIdUtenteInsert(userId);
			carREsitoTipoControllo.setIdUtenteUpdate(userId);

			if (form.isControlloDocumentale()) {
				carREsitoTipoControllo.setIdTipoControllo(CaronteConstants.ID_TIPO_CONTROLLO_DOCUMENTALE);
				carREsitoTipoControllo.setEsitoFavorevole("S".equals(form.getControlloDocumentaleCB()) ? true : false);
				carREsitoTipoControllo.setIspezioneStrumentale(false);
				carREsitoTipoControllo.setIspezioneVisiva(false);
				carREsitoTipoControllo.setMotivoEsitoNonFavorevole(form.getNoteControlloDocumentaleCB());

				esitoTipoControlloMapper.insert(carREsitoTipoControllo);
			}

			if (form.isControlloIdentita()) {
				carREsitoTipoControllo.setIdTipoControllo(CaronteConstants.ID_TIPO_CONTROLLO_IDENTITA);
				carREsitoTipoControllo.setEsitoFavorevole("S".equals(form.getControlloIdentitaCB()) ? true : false);
				carREsitoTipoControllo.setIspezioneStrumentale(false);
				carREsitoTipoControllo.setIspezioneVisiva(false);
				carREsitoTipoControllo.setMotivoEsitoNonFavorevole(form.getNoteControlloIdentitaCB());

				esitoTipoControlloMapper.insert(carREsitoTipoControllo);
			}

			if (form.isControlloFitosanitario()) {
				carREsitoTipoControllo.setIdTipoControllo(CaronteConstants.ID_TIPO_CONTROLLO_FITOSANITARIO);
				carREsitoTipoControllo
						.setEsitoFavorevole("S".equals(form.getControlloFitosanitarioCB()) ? true : false);
				//carREsitoTipoControllo.setMotivoEsitoNonFavorevole(form.getNoteControlloFitosanitarioCB());
				carREsitoTipoControllo.setIspezioneStrumentale(form.isControlloFitosanitarioIspStrum());
				carREsitoTipoControllo.setIspezioneVisiva(form.isControlloFitosanitarioIspVisiva());
				carREsitoTipoControllo.setStrumentoIspezione(form.getNoteControlloFitosanitarioCB());

				esitoTipoControlloMapper.insert(carREsitoTipoControllo);
			}

		}
		return carREsitoTipoControllo;
	}

	@Override
	public CarTVerbaleIspezione getVerbaleIspezione(Long idCertificato) {

		CarTVerbaleIspezioneExample verbaleIspezioneExample = new CarTVerbaleIspezioneExample();
		verbaleIspezioneExample.createCriteria().andIdCertificatoEqualTo(idCertificato);
		List<CarTVerbaleIspezione> carTVerbaleIspezione = verbaleMapper.selectByExample(verbaleIspezioneExample);
		if (null != carTVerbaleIspezione && carTVerbaleIspezione.size() > 0) {
			return carTVerbaleIspezione.get(0);
		}
		return null;

	}

	@Override
	public List<CarREsitoTipoControllo> getElencoEsitoTipoControllo(Long idVerbaleIspezione) {

		CarREsitoTipoControlloExample esitoTipoControlloExample = new CarREsitoTipoControlloExample();
		esitoTipoControlloExample.createCriteria().andIdVerbaleIspezioneEqualTo(idVerbaleIspezione);
		return esitoTipoControlloMapper.selectByExample(esitoTipoControlloExample);
	}

	@Override
	public int updateDatiIspezioneVerbale(DatiVerbaleForm form, Long userId) {

		if (null != form) {

			CarTVerbaleIspezione verbaleIspezione = verbaleMapper.selectByPrimaryKey(form.getIdVerbale());

			if (null != verbaleIspezione) {

				verbaleIspezione.setDataUpdate(new Date());
				verbaleIspezione.setIdUtenteUpdate(userId);

				// Dati ispezione
				verbaleIspezione.setOraInizioIspezione(LocalTime.parse(form.getOraInizioIspezione()));
				verbaleIspezione.setOraFineIspezione(LocalTime.parse(form.getOraFineIspezione()));
				verbaleIspezione.setMagazzinoDoganale(form.getMagazzinoDoganale());
				verbaleIspezione.setDelegatoPresenteIspezione(form.getPersonaRiferimento());
				verbaleIspezione.setDelegatoPresenteRuolo(form.getPersonaRiferimentoRuolo());
				verbaleIspezione.setDataIspezione(form.getDataIspezione());

				return reportMapper.updateDatiIspezioneVerbale(verbaleIspezione);
			}
		}

		return 0;
	}

	@Override
	public int updateDatiSpedizioneVerbale(DatiVerbaleForm form, Long userId) {

		if (null != form) {

			CarTVerbaleIspezione verbaleIspezione = verbaleMapper.selectByPrimaryKey(form.getIdVerbale());

			if (null != verbaleIspezione) {

				verbaleIspezione.setDataUpdate(new Date());
				verbaleIspezione.setIdUtenteUpdate(userId);

				// Dati spedizione
				verbaleIspezione.setFlgAltro(form.isTipoProdottoAltro());
				if (form.isTipoProdottoAltro()) {
					verbaleIspezione.setAltraTipologiaProdotto(form.getTipoProdottoAltroNote());
				}
				verbaleIspezione.setFlgCorteccia(form.isTipoProdottoCorteccia());
				verbaleIspezione.setFlgFioriRecisi(form.isTipoProdottoFiori());
				verbaleIspezione.setFlgFrutti(form.isTipoProdottoFrutti());
				verbaleIspezione.setFlgImbalLegno(form.isTipoProdottoImballaggi());
				verbaleIspezione.setFlgLegname(form.isTipoProdottoLegname());
				verbaleIspezione.setFlgPartiPianteVive(form.isTipoProdottoPartiPianteVive());
				verbaleIspezione.setFlgPianteVive(form.isTipoProdottoPianteVive());
				verbaleIspezione.setFlgSementi(form.isTipoProdottoSementi());
				verbaleIspezione.setFlgSemi(form.isTipoProdottoSemi());
				verbaleIspezione.setFlgTerraTerriccio(form.isTipoProdottoTerra());
				verbaleIspezione.setAltraDocumentazioneAllegata(form.getAltraDocumentazione());
				reportMapper.updateDatiSpedizioneVerbale(verbaleIspezione);

				// gestione campioni
				if (null != form.getListaMerceRichiesta() && form.getListaMerceRichiesta().size() > 0) {
					CarRCampionamentoPartitaExample exampleCampione = new CarRCampionamentoPartitaExample();
					// exampleCampione.createCriteria().andIdMerceRichiestaEqualTo(merceRichiestaVerbale.getIdMerceRichiesta());
					exampleCampione.createCriteria().andIdVerbaleIspezioneEqualTo(form.getIdVerbale());
					carRCampionamentoPartitaMapper.deleteByExample(exampleCampione);

					for (MerceRichiestaVerbaleDto merceRichiestaVerbale : form.getListaMerceRichiesta()) {
						CarRCampionamentoPartita campione = new CarRCampionamentoPartita();
						campione.setIdVerbaleIspezione(form.getIdVerbale());
						campione.setIdMerceRichiesta(merceRichiestaVerbale.getIdMerceRichiesta() != null
								? merceRichiestaVerbale.getIdMerceRichiesta().longValue()
								: null);
						campione.setQuantitaAnalizzati(merceRichiestaVerbale.getQuantitaAnalizzati());
						campione.setDescrizionePartita(merceRichiestaVerbale.getDescrizionePartita());
						campione.setKg(merceRichiestaVerbale.getKg() != null
								? merceRichiestaVerbale.getKg()
								: null);
						campione.setMc(merceRichiestaVerbale.getMc() != null
								? merceRichiestaVerbale.getMc()
								: null);
						campione.setUnita(merceRichiestaVerbale.getUnita() != null
								? merceRichiestaVerbale.getUnita()
								: null);
						campione.setColli(merceRichiestaVerbale.getColli() != null
								? merceRichiestaVerbale.getColli()
								: null);
						campione.setDataInsert(new Date());
						campione.setIdUtenteInsert(userId);
						carRCampionamentoPartitaMapper.insert(campione);
					}
				}

				return 1;
			}
		}

		return 0;
	}

	@Override
	public int updateDatiControlloEsitoVerbale(DatiVerbaleForm form, Long userId) {
		if (null != form) {

			CarTVerbaleIspezione verbaleIspezione = verbaleMapper.selectByPrimaryKey(form.getIdVerbale());

			if (null != verbaleIspezione) {

				verbaleIspezione.setDataUpdate(new Date());
				verbaleIspezione.setIdUtenteUpdate(userId);

				// Dati Controllo ed esito
				verbaleIspezione.setDittaAssisteAnalisiLab("S".equals(form.getVisioneAnalisiRB()) ? true : false);
				verbaleIspezione.setAllegatoConsulto("S".equals(form.getAllegaEvidenzaRB()) ? true : false);
				verbaleIspezione
						.setConsultoResponsFilosanit("S".equals(form.getConsultoResponsabileRB()) ? true : false);
				verbaleIspezione.setNullaostaRilasciato("S".equals(form.getTermIspezioneNullaOsta()) ? true : false);
				verbaleIspezione.setMotivoNonRilascioNullaosta(form.getTermIspezioneNullaOstaNote());
				verbaleIspezione.setDispostaMisuraUffic(form.isTermIspezioneMisUff());
				verbaleIspezione.setMotivoDispostaMisuraUffic(form.getTermIspezioneMisUffNote());
				verbaleIspezione.setPrelievoPerRicerca(form.getPrelievoPerRicerca());
				verbaleIspezione.setCodiceCampione(form.getCodiceCampione());

				initCarREsitoTipoControllo(form, userId);

				return reportMapper.updateDatiControlloEsitoVerbale(verbaleIspezione);
			}
		}
		return 0;
	}

	@Override
	public int updateDatiMisureUfficialiVerbale(DatiVerbaleForm form, Long userId) {
		if (null != form) {

			CarTVerbaleIspezione verbaleIspezione = verbaleMapper.selectByPrimaryKey(form.getIdVerbale());

			if (null != verbaleIspezione) {

				verbaleIspezione.setDataUpdate(new Date());
				verbaleIspezione.setIdUtenteUpdate(userId);

				// Dati Misure ufficiali
				verbaleIspezione.setSpedizioneRifiutata(form.isMisUffA());
				verbaleIspezione.setTraspEsternoComun(form.isMisUffB());
				verbaleIspezione.setProdottiInfettiRimossi(form.isMisUffC());
				verbaleIspezione.setDistrutto(form.isMisUffD());
				verbaleIspezione.setQuarantena(form.isMisUffE());
				verbaleIspezione.setTrattamentoAdeguato(form.isMisUffF());

				verbaleIspezione.setMotivoRifiutoSpedizione(form.getMisUffANote());
				verbaleIspezione.setMotivoTraspEsternoComun(form.getMisUffBNote());
				verbaleIspezione.setMotivoRimozProdottiInfetti(form.getMisUffCNote());
				verbaleIspezione.setMotivoDistruzione(form.getMisUffDNote());
				verbaleIspezione.setMotivoTrattamentoAdeguato(form.getMisUffFNote());

				return reportMapper.updateDatiMisureUfficialiVerbale(verbaleIspezione);
			}
		}
		return 0;
	}

	@Override
	public int updateDatiCustodiaVerbale(DatiVerbaleForm form, Long userId) {
		if (null != form) {

			CarTVerbaleIspezione verbaleIspezione = verbaleMapper.selectByPrimaryKey(form.getIdVerbale());

			if (null != verbaleIspezione) {

				verbaleIspezione.setDataUpdate(new Date());
				verbaleIspezione.setIdUtenteUpdate(userId);

				// Dati Custodia
				verbaleIspezione.setCustodeMerceMisUffic(form.getCustResponsabileMerce());
				verbaleIspezione.setDocumentoIdentitaCustode(form.getCustDocumentoRespMerce());
				verbaleIspezione.setRuoloCustode(form.getCustRuoloRespMerce());
				verbaleIspezione.setLocaliCustodia(form.getCustLocaliMerce());

				return reportMapper.updateDatiCustodiaVerbale(verbaleIspezione);
			}
		}
		return 0;
	}

	@Override
	public int updateDatiDichiarazioniNoteVerbale(DatiVerbaleForm form, Long userId) {
		if (null != form) {

			CarTVerbaleIspezione verbaleIspezione = verbaleMapper.selectByPrimaryKey(form.getIdVerbale());

			if (null != verbaleIspezione) {

				verbaleIspezione.setDataUpdate(new Date());
				verbaleIspezione.setIdUtenteUpdate(userId);

				// Dati Dich e Note
				verbaleIspezione.setCopiaVerbConsegnatoPers(form.getDichNoteRespVerbale());
				verbaleIspezione.setRuoloPersona(form.getDichNoteRuoloRespVerbale());
				verbaleIspezione.setDichiarazionePersona(form.getDichNoteDichiarazioneRespVerb());
				verbaleIspezione.setNote(form.getDichNoteNoteRespVerb());

				return reportMapper.updateDatiDichiarazioniNoteVerbale(verbaleIspezione);
			}
		}
		return 0;
	}

	@Override
	public List<CarRCampionamentoPartita> getElencoCarRCampionamentoPartita(Long idVerbale) {
		CarRCampionamentoPartitaExample exampleCampione = new CarRCampionamentoPartitaExample();
		exampleCampione.createCriteria().andIdVerbaleIspezioneEqualTo(idVerbale);
		return carRCampionamentoPartitaMapper.selectByExample(exampleCampione);
	}

	@Override
	public List<ReportDTO> getElencoReportComVivai(RicercaReportVivaiForm ricercaReport) throws BusinessException {		
		 return reportMapper.getElencoReportComVivai(ricercaReport);		 
	}
	
	@Override
	public List<ReportDTO> getElencoReportAziendeRuop(RicercaReportAutorizzazioniForm ricercaReport) throws BusinessException {		
		if (ricercaReport.isAziendeConDomandaRuop()) {
			return reportMapper.getElencoReportAziendeConDomandaRuop(ricercaReport);	
		} else {
			return reportMapper.getElencoReportAziendeRuopAll(ricercaReport);	
		}		 	 
	}

	@Override
	public List<ReportDTO> getElencoReportControlli(RicercaReportControlliForm ricercaReportForm)
			throws BusinessException {
		
		return reportMapper.getElencoReportControlli(ricercaReportForm);		 	
	}

	@Override
	public List<ReportDTO> getElencoReportCampioni(RicercaReportControlliForm ricercaReportForm)
			throws BusinessException {
		
		return reportMapper.getElencoReportCampioni(ricercaReportForm);		 
	}

	@Override
	public List<ReportDTO> getElencoReportMisure(RicercaReportControlliForm ricercaReportForm)
			throws BusinessException {

		return reportMapper.getElencoReportMisure(ricercaReportForm);		 
	}

@Override
	public List<ReportDTO> getElencoReportMonitoraggi(RicercaReportControlliForm ricercaReportForm)
			throws BusinessException {

		return reportMapper.getElencoReportMonitoraggi(ricercaReportForm);		 
	}

@Override
	public List<ReportDTO> getElencoReportCentriAzRuop(RicercaReportAutorizzazioniForm ricercaReport)  throws BusinessException {
		return reportMapper.getElencoReportCentriAzRuop(ricercaReport);
	}

@Override
public List<ReportDTO> getElencoReportVivaiNoOn(@Valid RicercaReportVivaiForm ricercaReportForm)
		throws BusinessException {
	return reportMapper.getElencoReportVivaiNoOn(ricercaReportForm);		 
}
}
