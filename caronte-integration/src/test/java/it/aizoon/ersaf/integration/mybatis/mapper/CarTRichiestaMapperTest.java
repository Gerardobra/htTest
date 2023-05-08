package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import it.aizoon.ersaf.dto.generati.CarTRichiesta;
import it.aizoon.ersaf.form.NuovaRichiestaForm;
import it.aizoon.ersaf.integration.AbstractTestDataSourceInitializer;
import it.aizoon.ersaf.integration.mybatis.mapper.generati.CarTRichiestaMapper;

public class CarTRichiestaMapperTest extends AbstractTestDataSourceInitializer<CarTRichiestaMapper> {
  
  @BeforeClass
  public static void setUpClass() {
    sqlSessionFactory = getSqlSessionFactory(CarTRichiestaMapper.class);
  }
  
  public CarTRichiestaMapperTest() {
    super(CarTRichiestaMapper.class);
  }  
  
  @Test
  public void getProvaInserisciNuovaRichiesta() {
    
    System.out.println("\nPROVA INIT");
    
    Long returnValue = null;
    NuovaRichiestaForm nuovaRichiesta = new NuovaRichiestaForm();
    
    nuovaRichiesta.setIdStatoRichiesta(1L);
    nuovaRichiesta.setNote("questa è una prova");
    nuovaRichiesta.setDenominazioneMittente("Pippo Mittente");
    nuovaRichiesta.setIndirizzoMittente("via di Pippo");
    nuovaRichiesta.setComuneMittente("comune di Pippo");
    nuovaRichiesta.setIdComuneIta(1L);
    nuovaRichiesta.setIdNazioneMittente(1L);    
    nuovaRichiesta.setDenominazioneDestinatario("Aldo Destinatario");
    nuovaRichiesta.setIndirizzoDestinatario("via di Aldo");
    nuovaRichiesta.setComuneDestinatario("comune di Aldo");
    nuovaRichiesta.setIdNazioneDestinatario(2L);
    nuovaRichiesta.setNumDocumentoTrasporto("AS123FG");
    nuovaRichiesta.setIdModoTrasporto(1L);
    nuovaRichiesta.setIdPuntoEntrataDichiarato(1L);
    nuovaRichiesta.setIdNazioneDestinatario(2L);
    
    
    CarTRichiesta richiesta = null;

    if (null != nuovaRichiesta) {

      richiesta = new CarTRichiesta();

      richiesta.setIdStatoRichiesta(nuovaRichiesta.getIdStatoRichiesta());
      richiesta.setNoteDatiRichiesta(nuovaRichiesta.getNote());

      richiesta.setDenomMittente(nuovaRichiesta.getDenominazioneMittente());
      richiesta.setIndirizzoMittente(nuovaRichiesta.getIndirizzoMittente());
      richiesta.setDenomComuneEstMittente(nuovaRichiesta.getComuneMittente());
      richiesta.setIdNazioneMittente(nuovaRichiesta.getIdNazioneMittente());

      richiesta.setIdComuneIta(nuovaRichiesta.getIdComuneIta());
      
      
      richiesta.setDenomDestinatario(nuovaRichiesta.getDenominazioneDestinatario());
      richiesta.setIndirizzoDestinatario(nuovaRichiesta.getIndirizzoDestinatario());
      richiesta.setDenomComuneEstDestinatario(nuovaRichiesta.getComuneDestinatario());
      richiesta.setIdNazioneDestinatario(nuovaRichiesta.getIdNazioneDestinatario());

      richiesta.setIdentifMezzoTrasporto(nuovaRichiesta.getNumDocumentoTrasporto());
      richiesta.setIdModoTrasporto(nuovaRichiesta.getIdModoTrasporto());
      richiesta.setIdUfficioDoganaleEntrata(nuovaRichiesta.getIdPuntoEntrataDichiarato());

      // default
      richiesta.setIdUtenteInsert(1L);
      richiesta.setDataInsert(new Date());

      // TODO : da eliminare ??? (DB + JAVA)
      //richiesta.setIdTipoCertificato(1L);
      richiesta.setLuogoDeposito("boh");   
      richiesta.setDataEsecuzione(new Date());
      richiesta.setDataInoltro(new Date());
      richiesta.setDataPartenzaMerce(new Date());
      richiesta.setIdUtenteUpdate(1L);
      richiesta.setDataUpdate(new Date());
      richiesta.setIdNazioneProtVegDestinat(nuovaRichiesta.getIdNazioneDestinatario());
      
    }
       
    
    try {

      mapper.insertSelective(richiesta);

      returnValue = richiesta.getIdRichiesta();

    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("\nVALORE : ->"+returnValue+"<-");
    
    System.out.println("\nPROVA END");
  }
}
