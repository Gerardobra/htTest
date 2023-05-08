package it.aizoon.ersaf.dto.stampe;

import java.util.List;

import org.springframework.util.StringUtils;

import it.aizoon.ersaf.dto.CertificatoRichiestaDto;
import it.aizoon.ersaf.dto.MerceRichiestaVerbaleColliDto;
import it.aizoon.ersaf.dto.MerceRichiestaVerbaleDto;
import it.aizoon.ersaf.util.CaronteUtils;

public class EverdeDTO {

  String verbale = "";

  // DATI ISPEZIONE
  String datiIspezione = "";
  private String dataIspezione = "";
  private String oraInizioIspezione = "";
  private String oraFineIspezione = "";
  private String denomSpedizioniere = "";
  private String descUfficioDoganaleUtilizzato = "";
  private String magazzinoDoganale = "";
  private String delegatoPresenteIspezione = "";
  private String delegatoPresenteRuolo = "";

  // DATI SPEDIZIONE
  String flgPianteVive = "";
  String flgPartiVive = "";
  String flgSementi = "";
  String flgSemi = "";
  String flgTerra = "";
  String flgCorteccia = "";
  String flgLegname = "";
  String flgFrutti = "";
  String flgFiori = "";
  String flgImballaggi = "";
  String flgAltro = "";
  String altro = "";

  String paeseOrigineProvenienza = "";
  String awb = "";

  String mittente = "";
  String destinatario = "";
  String destinario2 = "";

  String certificatoFitosanitario = "";
  String dataCertFitosanitario = "";
  String altraDoc = "";

  // DATI CONTROLLO ED ESITO
  String flgCtrlDocumentale = "";
  String flgCtrlDocumentaleFav = "";
  String flgCtrlDocumentaleNotFav = "";
  String nonFavorevole1 = "";

  String flgCtrlIdentita = "";
  String flgCtrlIdentitaFav = "";
  String flgCtrlIdentitaNotFav = "";
  String nonFavorevole2 = "";

  String flgCtrlFitosanitario = "";
  String pezziColliHtml = "";
  String flgCtrlFitosanitarioFav = "";
  String flgCtrlFitosanitarioNotFav = "";
  String flgIspVisiva = "";
  String flgStrumentale = "";
  String ispezioneStrumentale = "";

  String testoCtrlEdEsito = "";
  String codiciCampioni = "";
  String numPrelievi = "";
  String prelievoPerRicerca = "";
  String flgAnalisiNot = "";
  String flgAnalisi = "";

  String flgConsultoResp = "";
  String flgConsultoRespNot = "";

  String flgAllegato = "";
  String flgAllegatoNot = "";

  String flgRilasciatoNullaOstaImp = "";
  String rilasciatoNullaOstaImportazione = "";

  String flgNotRilasciatoNullaOstaImp = "";
  String nonRilasciatoNullaOstaImportazione = "";

  String flgMisuraUfficiale = "";
  String dispostaMisuraUfficiale = "";

  // DATI MISURE UFFICIALI
  String flgMuA = "";
  String rifiutoSpedizione = "";
  String flgMuB = "";
  String trasportoComunita = "";
  String flgMuC = "";
  String rimozioneProdottiInfestati = "";
  String flgMuD = "";
  String distruzione = "";
  String flgMuE = "";
  String imposizioneQuarantena = "";
  String flgMuF = "";
  String trattamentoAdeguato = "";

  // DATI CUSTODIA
  String datiCustodia = "";
  String custodeMerceMisUffic = "";
  String documentoIdentitaCustode = "";
  String ruoloCustode = "";
  String localiCustodia = "";

  // DATI DICHIARAZIONI E NOTE
  String datiDichENote = "";
  String copiaVerbConsegnatoPers = "";
  String ruoloPersona = "";
  String dichiarazionePersona = "";
  String note = "";
  
  // FIRME
  String laParte;

  List<MerceRichiestaVerbaleDto> elencoMerci;
  List<MerceRichiestaVerbaleColliDto> elencoColli;

  public String getVerbale() {
    return verbale;
  }

  public void setVerbale(String verbale) {
    this.verbale = verbale;
  }

  public String getDatiIspezione() {
    return datiIspezione;
  }

  public void setDatiIspezione(String datiIspezione) {
    this.datiIspezione = datiIspezione;
  }

  public String getDataIspezione() {
    return dataIspezione;
  }

  public void setDataIspezione(String dataIspezione) {
    this.dataIspezione = dataIspezione;
  }

  public String getOraInizioIspezione() {
    return oraInizioIspezione;
  }

  public void setOraInizioIspezione(String oraInizioIspezione) {
    this.oraInizioIspezione = oraInizioIspezione;
  }

  public String getOraFineIspezione() {
    return oraFineIspezione;
  }

  public void setOraFineIspezione(String oraFineIspezione) {
    this.oraFineIspezione = oraFineIspezione;
  }

  public String getDenomSpedizioniere() {
    return denomSpedizioniere;
  }

  public void setDenomSpedizioniere(String denomSpedizioniere) {
    this.denomSpedizioniere = denomSpedizioniere;
  }

  public String getDescUfficioDoganaleUtilizzato() {
    return descUfficioDoganaleUtilizzato;
  }

  public void setDescUfficioDoganaleUtilizzato(String descUfficioDoganaleUtilizzato) {
    this.descUfficioDoganaleUtilizzato = descUfficioDoganaleUtilizzato;
  }

  public String getMagazzinoDoganale() {
    return magazzinoDoganale;
  }

  public void setMagazzinoDoganale(String magazzinoDoganale) {
    this.magazzinoDoganale = magazzinoDoganale;
  }

  public String getDelegatoPresenteIspezione() {
    return delegatoPresenteIspezione;
  }

  public void setDelegatoPresenteIspezione(String delegatoPresenteIspezione) {
    this.delegatoPresenteIspezione = delegatoPresenteIspezione;
  }

  public String getDelegatoPresenteRuolo() {
    return delegatoPresenteRuolo;
  }

  public void setDelegatoPresenteRuolo(String delegatoPresenteRuolo) {
    this.delegatoPresenteRuolo = delegatoPresenteRuolo;
  }

  public String getFlgPianteVive() {
    return flgPianteVive;
  }

  public void setFlgPianteVive(String flgPianteVive) {
    this.flgPianteVive = flgPianteVive;
  }

  public String getFlgPartiVive() {
    return flgPartiVive;
  }

  public void setFlgPartiVive(String flgPartiVive) {
    this.flgPartiVive = flgPartiVive;
  }

  public String getFlgSementi() {
    return flgSementi;
  }

  public void setFlgSementi(String flgSementi) {
    this.flgSementi = flgSementi;
  }

  public String getFlgSemi() {
    return flgSemi;
  }

  public void setFlgSemi(String flgSemi) {
    this.flgSemi = flgSemi;
  }

  public String getFlgTerra() {
    return flgTerra;
  }

  public void setFlgTerra(String flgTerra) {
    this.flgTerra = flgTerra;
  }

  public String getFlgCorteccia() {
    return flgCorteccia;
  }

  public void setFlgCorteccia(String flgCorteccia) {
    this.flgCorteccia = flgCorteccia;
  }

  public String getFlgLegname() {
    return flgLegname;
  }

  public void setFlgLegname(String flgLegname) {
    this.flgLegname = flgLegname;
  }

  public String getFlgFrutti() {
    return flgFrutti;
  }

  public void setFlgFrutti(String flgFrutti) {
    this.flgFrutti = flgFrutti;
  }

  public String getFlgFiori() {
    return flgFiori;
  }

  public void setFlgFiori(String flgFiori) {
    this.flgFiori = flgFiori;
  }

  public String getFlgImballaggi() {
    return flgImballaggi;
  }

  public void setFlgImballaggi(String flgImballaggi) {
    this.flgImballaggi = flgImballaggi;
  }

  public String getFlgAltro() {
    return flgAltro;
  }

  public void setFlgAltro(String flgAltro) {
    this.flgAltro = flgAltro;
  }

  public String getAltro() {
    return altro;
  }

  public void setAltro(String altro) {
    this.altro = altro;
  }

  public String getPaeseOrigineProvenienza() {
    return paeseOrigineProvenienza;
  }

  public void setPaeseOrigineProvenienza(String paeseOrigineProvenienza) {
    this.paeseOrigineProvenienza = paeseOrigineProvenienza;
  }

  public String getAwb() {
    return awb;
  }

  public void setAwb(String awb) {
    this.awb = awb;
  }

  public String getMittente() {
    return mittente;
  }

  public void setMittente(String mittente) {
    this.mittente = mittente;
  }

  public String getDestinatario() {
    return destinatario;
  }

  public void setDestinatario(String destinatario) {
    this.destinatario = destinatario;
  }

  public String getDestinario2() {
    return destinario2;
  }

  public void setDestinario2(String destinario2) {
    this.destinario2 = destinario2;
  }

  public String getCertificatoFitosanitario() {
    return certificatoFitosanitario;
  }

  public void setCertificatoFitosanitario(String certificatoFitosanitario) {
    this.certificatoFitosanitario = certificatoFitosanitario;
  }

  public String getDataCertFitosanitario() {
    return dataCertFitosanitario;
  }

  public void setDataCertFitosanitario(String dataCertFitosanitario) {
    this.dataCertFitosanitario = dataCertFitosanitario;
  }

  public String getAltraDoc() {
    return altraDoc;
  }

  public void setAltraDoc(String altraDoc) {
    this.altraDoc = altraDoc;
  }

  public String getFlgCtrlDocumentale() {
    return flgCtrlDocumentale;
  }

  public void setFlgCtrlDocumentale(String flgCtrlDocumentale) {
    this.flgCtrlDocumentale = flgCtrlDocumentale;
  }

  public String getFlgCtrlDocumentaleFav() {
    return flgCtrlDocumentaleFav;
  }

  public void setFlgCtrlDocumentaleFav(String flgCtrlDocumentaleFav) {
    this.flgCtrlDocumentaleFav = flgCtrlDocumentaleFav;
  }

  public String getFlgCtrlDocumentaleNotFav() {
    return flgCtrlDocumentaleNotFav;
  }

  public void setFlgCtrlDocumentaleNotFav(String flgCtrlDocumentaleNotFav) {
    this.flgCtrlDocumentaleNotFav = flgCtrlDocumentaleNotFav;
  }

  public String getNonFavorevole1() {
    return nonFavorevole1;
  }

  public void setNonFavorevole1(String nonFavorevole1) {
    this.nonFavorevole1 = nonFavorevole1;
  }

  public String getFlgCtrlIdentita() {
    return flgCtrlIdentita;
  }

  public void setFlgCtrlIdentita(String flgCtrlIdentita) {
    this.flgCtrlIdentita = flgCtrlIdentita;
  }

  public String getFlgCtrlIdentitaFav() {
    return flgCtrlIdentitaFav;
  }

  public void setFlgCtrlIdentitaFav(String flgCtrlIdentitaFav) {
    this.flgCtrlIdentitaFav = flgCtrlIdentitaFav;
  }

  public String getFlgCtrlIdentitaNotFav() {
    return flgCtrlIdentitaNotFav;
  }

  public void setFlgCtrlIdentitaNotFav(String flgCtrlIdentitaNotFav) {
    this.flgCtrlIdentitaNotFav = flgCtrlIdentitaNotFav;
  }

  public String getNonFavorevole2() {
    return nonFavorevole2;
  }

  public void setNonFavorevole2(String nonFavorevole2) {
    this.nonFavorevole2 = nonFavorevole2;
  }

  public String getFlgCtrlFitosanitario() {
    return flgCtrlFitosanitario;
  }

  public void setFlgCtrlFitosanitario(String flgCtrlFitosanitario) {
    this.flgCtrlFitosanitario = flgCtrlFitosanitario;
  }

  public String getPezziColliHtml() {
    return pezziColliHtml;
  }

  public void setPezziColliHtml(String pezziColliHtml) {
    this.pezziColliHtml = pezziColliHtml;
  }

  public String getFlgCtrlFitosanitarioFav() {
    return flgCtrlFitosanitarioFav;
  }

  public void setFlgCtrlFitosanitarioFav(String flgCtrlFitosanitarioFav) {
    this.flgCtrlFitosanitarioFav = flgCtrlFitosanitarioFav;
  }

  public String getFlgCtrlFitosanitarioNotFav() {
    return flgCtrlFitosanitarioNotFav;
  }

  public void setFlgCtrlFitosanitarioNotFav(String flgCtrlFitosanitarioNotFav) {
    this.flgCtrlFitosanitarioNotFav = flgCtrlFitosanitarioNotFav;
  }

  public String getFlgIspVisiva() {
    return flgIspVisiva;
  }

  public void setFlgIspVisiva(String flgIspVisiva) {
    this.flgIspVisiva = flgIspVisiva;
  }

  public String getFlgStrumentale() {
    return flgStrumentale;
  }

  public void setFlgStrumentale(String flgStrumentale) {
    this.flgStrumentale = flgStrumentale;
  }

  public String getIspezioneStrumentale() {
    return ispezioneStrumentale;
  }

  public void setIspezioneStrumentale(String ispezioneStrumentale) {
    this.ispezioneStrumentale = ispezioneStrumentale;
  }

  public String getTestoCtrlEdEsito() {
    return testoCtrlEdEsito;
  }

  public void setTestoCtrlEdEsito(String testoCtrlEdEsito) {
    this.testoCtrlEdEsito = testoCtrlEdEsito;
  }

  public String getCodiciCampioni() {
    return codiciCampioni;
  }

  public void setCodiciCampioni(String codiciCampioni) {
    this.codiciCampioni = codiciCampioni;
  }

  public String getNumPrelievi() {
    return numPrelievi;
  }

  public void setNumPrelievi(String numPrelievi) {
    this.numPrelievi = numPrelievi;
  }

  public String getPrelievoPerRicerca() {
    return prelievoPerRicerca;
  }

  public void setPrelievoPerRicerca(String prelievoPerRicerca) {
    this.prelievoPerRicerca = prelievoPerRicerca;
  }

  public String getFlgAnalisiNot() {
    return flgAnalisiNot;
  }

  public void setFlgAnalisiNot(String flgAnalisiNot) {
    this.flgAnalisiNot = flgAnalisiNot;
  }

  public String getFlgAnalisi() {
    return flgAnalisi;
  }

  public void setFlgAnalisi(String flgAnalisi) {
    this.flgAnalisi = flgAnalisi;
  }

  public String getFlgConsultoResp() {
    return flgConsultoResp;
  }

  public void setFlgConsultoResp(String flgConsultoResp) {
    this.flgConsultoResp = flgConsultoResp;
  }

  public String getFlgConsultoRespNot() {
    return flgConsultoRespNot;
  }

  public void setFlgConsultoRespNot(String flgConsultoRespNot) {
    this.flgConsultoRespNot = flgConsultoRespNot;
  }

  public String getFlgAllegato() {
    return flgAllegato;
  }

  public void setFlgAllegato(String flgAllegato) {
    this.flgAllegato = flgAllegato;
  }

  public String getFlgAllegatoNot() {
    return flgAllegatoNot;
  }

  public void setFlgAllegatoNot(String flgAllegatoNot) {
    this.flgAllegatoNot = flgAllegatoNot;
  }

  public String getFlgRilasciatoNullaOstaImp() {
    return flgRilasciatoNullaOstaImp;
  }

  public void setFlgRilasciatoNullaOstaImp(String flgRilasciatoNullaOstaImp) {
    this.flgRilasciatoNullaOstaImp = flgRilasciatoNullaOstaImp;
  }

  public String getRilasciatoNullaOstaImportazione() {
    return rilasciatoNullaOstaImportazione;
  }

  public void setRilasciatoNullaOstaImportazione(String rilasciatoNullaOstaImportazione) {
    this.rilasciatoNullaOstaImportazione = rilasciatoNullaOstaImportazione;
  }

  public String getFlgNotRilasciatoNullaOstaImp() {
    return flgNotRilasciatoNullaOstaImp;
  }

  public void setFlgNotRilasciatoNullaOstaImp(String flgNotRilasciatoNullaOstaImp) {
    this.flgNotRilasciatoNullaOstaImp = flgNotRilasciatoNullaOstaImp;
  }

  public String getNonRilasciatoNullaOstaImportazione() {
    return nonRilasciatoNullaOstaImportazione;
  }

  public void setNonRilasciatoNullaOstaImportazione(String nonRilasciatoNullaOstaImportazione) {
    this.nonRilasciatoNullaOstaImportazione = nonRilasciatoNullaOstaImportazione;
  }

  public String getFlgMisuraUfficiale() {
    return flgMisuraUfficiale;
  }

  public void setFlgMisuraUfficiale(String flgMisuraUfficiale) {
    this.flgMisuraUfficiale = flgMisuraUfficiale;
  }

  public String getDispostaMisuraUfficiale() {
    return dispostaMisuraUfficiale;
  }

  public void setDispostaMisuraUfficiale(String dispostaMisuraUfficiale) {
    this.dispostaMisuraUfficiale = dispostaMisuraUfficiale;
  }

  public String getFlgMuA() {
    return flgMuA;
  }

  public void setFlgMuA(String flgMuA) {
    this.flgMuA = flgMuA;
  }

  public String getRifiutoSpedizione() {
    return rifiutoSpedizione;
  }

  public void setRifiutoSpedizione(String rifiutoSpedizione) {
    this.rifiutoSpedizione = rifiutoSpedizione;
  }

  public String getFlgMuB() {
    return flgMuB;
  }

  public void setFlgMuB(String flgMuB) {
    this.flgMuB = flgMuB;
  }

  public String getTrasportoComunita() {
    return trasportoComunita;
  }

  public void setTrasportoComunita(String trasportoComunita) {
    this.trasportoComunita = trasportoComunita;
  }

  public String getFlgMuC() {
    return flgMuC;
  }

  public void setFlgMuC(String flgMuC) {
    this.flgMuC = flgMuC;
  }

  public String getRimozioneProdottiInfestati() {
    return rimozioneProdottiInfestati;
  }

  public void setRimozioneProdottiInfestati(String rimozioneProdottiInfestati) {
    this.rimozioneProdottiInfestati = rimozioneProdottiInfestati;
  }

  public String getFlgMuD() {
    return flgMuD;
  }

  public void setFlgMuD(String flgMuD) {
    this.flgMuD = flgMuD;
  }

  public String getDistruzione() {
    return distruzione;
  }

  public void setDistruzione(String distruzione) {
    this.distruzione = distruzione;
  }

  public String getFlgMuE() {
    return flgMuE;
  }

  public void setFlgMuE(String flgMuE) {
    this.flgMuE = flgMuE;
  }

  public String getImposizioneQuarantena() {
    return imposizioneQuarantena;
  }

  public void setImposizioneQuarantena(String imposizioneQuarantena) {
    this.imposizioneQuarantena = imposizioneQuarantena;
  }

  public String getFlgMuF() {
    return flgMuF;
  }

  public void setFlgMuF(String flgMuF) {
    this.flgMuF = flgMuF;
  }

  public String getTrattamentoAdeguato() {
    return trattamentoAdeguato;
  }

  public void setTrattamentoAdeguato(String trattamentoAdeguato) {
    this.trattamentoAdeguato = trattamentoAdeguato;
  }

  public String getDatiCustodia() {
    return datiCustodia;
  }

  public void setDatiCustodia(String datiCustodia) {
    this.datiCustodia = datiCustodia;
  }

  public String getCustodeMerceMisUffic() {
    return custodeMerceMisUffic;
  }

  public void setCustodeMerceMisUffic(String custodeMerceMisUffic) {
    this.custodeMerceMisUffic = custodeMerceMisUffic;
  }

  public String getDocumentoIdentitaCustode() {
    return documentoIdentitaCustode;
  }

  public void setDocumentoIdentitaCustode(String documentoIdentitaCustode) {
    this.documentoIdentitaCustode = documentoIdentitaCustode;
  }

  public String getRuoloCustode() {
    return ruoloCustode;
  }

  public void setRuoloCustode(String ruoloCustode) {
    this.ruoloCustode = ruoloCustode;
  }

  public String getLocaliCustodia() {
    return localiCustodia;
  }

  public void setLocaliCustodia(String localiCustodia) {
    this.localiCustodia = localiCustodia;
  }

  public String getDatiDichENote() {
    return datiDichENote;
  }

  public void setDatiDichENote(String datiDichENote) {
    this.datiDichENote = datiDichENote;
  }

  public String getCopiaVerbConsegnatoPers() {
    return copiaVerbConsegnatoPers;
  }

  public void setCopiaVerbConsegnatoPers(String copiaVerbConsegnatoPers) {
    this.copiaVerbConsegnatoPers = copiaVerbConsegnatoPers;
  }

  public String getRuoloPersona() {
    return ruoloPersona;
  }

  public void setRuoloPersona(String ruoloPersona) {
    this.ruoloPersona = ruoloPersona;
  }

  public String getDichiarazionePersona() {
    return dichiarazionePersona;
  }

  public void setDichiarazionePersona(String dichiarazionePersona) {
    this.dichiarazionePersona = dichiarazionePersona;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getLaParte() {
    return laParte;
  }

  public void setLaParte(String laParte) {
    this.laParte = laParte;
  }

  public List<MerceRichiestaVerbaleDto> getElencoMerci() {
    return elencoMerci;
  }

  public void setElencoMerci(List<MerceRichiestaVerbaleDto> elencoMerci) {
    this.elencoMerci = elencoMerci;
  }

  public List<MerceRichiestaVerbaleColliDto> getElencoColli() {
    return elencoColli;
  }

  public void setElencoColli(List<MerceRichiestaVerbaleColliDto> elencoColli) {
    this.elencoColli = elencoColli;
  }

  public void initDatiIspezione(CertificatoRichiestaDto certificato) {
    if (!StringUtils.isEmpty(datiIspezione)) {
      datiIspezione = datiIspezione.replace("@IN_DATA@", CaronteUtils.checkNull(dataIspezione))
          .replace("@ORA_INIZIO@", CaronteUtils.checkNull(oraInizioIspezione))
          .replace("@ORA_FINE@", CaronteUtils.checkNull(oraFineIspezione))
          .replace("@ISPETTORE_DOCU@",
              CaronteUtils.checkNull(certificato.getDenominazioneIspettoreDocumentale()).toUpperCase())
          .replace("@ISPETTORE_IDEN@",
              CaronteUtils.checkNull(certificato.getDenominazioneIspettoreIdentita()).toUpperCase())
          .replace("@ISPETTORE_FITO@",
              CaronteUtils.checkNull(certificato.getDenominazioneIspettoreFitosanitario()).toUpperCase())
          .replace("@PUNTO_DICHIARATO@", CaronteUtils.checkNull(descUfficioDoganaleUtilizzato).toUpperCase())
          .replace("@MAGAZZINO_DOGANA@", CaronteUtils.checkNull(magazzinoDoganale).toUpperCase())
          .replace("@PERSONA_RIF@", CaronteUtils.checkNull(delegatoPresenteIspezione).toUpperCase())
          .replace("@PERSONA_RIF_RUOLO@", CaronteUtils.checkNull(delegatoPresenteRuolo).toUpperCase())
          .replace("@DENOM_SPEDIZIONIERE@", CaronteUtils.checkNull(denomSpedizioniere).toUpperCase());
    }
  }

  public void initPezziColli() {
    StringBuilder pezziColliHtml = new StringBuilder();

    for (MerceRichiestaVerbaleColliDto collo : getElencoColli()) {
      pezziColliHtml.append("<b>" + collo.getCodCollo() + ")</b> " + collo.getNumCollo() + "; ");
    }

    setPezziColliHtml(pezziColliHtml.toString());
  }

  public void initDatiControlloEdEsito() {
    if (!StringUtils.isEmpty(testoCtrlEdEsito)) {
      testoCtrlEdEsito = testoCtrlEdEsito.replace("@PRELIEVO_NUM@", CaronteUtils.checkNull(numPrelievi))
          .replace("@CODICI_CAMP_SIGILLATI@", CaronteUtils.checkNull(codiciCampioni))
          .replace("@PER_LA_RICERCA_DI@", CaronteUtils.checkNull(prelievoPerRicerca));
    }
  }

  public void initDatiCustodia() {
    if (!StringUtils.isEmpty(datiCustodia)) {
      datiCustodia = datiCustodia.replace("@CUSTODIA_SIGN@", CaronteUtils.checkNull(custodeMerceMisUffic))
          .replace("@IDENTIFICATIVO_PATDOC@", CaronteUtils.checkNull(documentoIdentitaCustode))
          .replace("@CUSTODIA_RUOLO@", CaronteUtils.checkNull(ruoloCustode))
          .replace("@CUSTODIA_LOCALI@", CaronteUtils.checkNull(localiCustodia));
    }
  }

  public void initDatiDichNote() {
    if (!StringUtils.isEmpty(datiDichENote)) {
      datiDichENote = datiDichENote.replace("@DATA_LETT_SOTTOSCR@", CaronteUtils.checkNull(dataIspezione))
          .replace("@DICH_PERSONA@", CaronteUtils.checkNull(copiaVerbConsegnatoPers))
          .replace("@DICH_RUOLO@", CaronteUtils.checkNull(ruoloPersona))
          .replace("@DICHIARA@", CaronteUtils.checkNull(dichiarazionePersona))
          .replace("@DICH_NOTE@", CaronteUtils.checkNull(note));
    }
  }

}
