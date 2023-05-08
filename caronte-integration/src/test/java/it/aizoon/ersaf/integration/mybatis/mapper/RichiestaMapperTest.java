package it.aizoon.ersaf.integration.mybatis.mapper;

import org.junit.BeforeClass;
import org.junit.Test;

import it.aizoon.ersaf.dto.DettaglioRichiestaDto;
import it.aizoon.ersaf.form.NuovaRichiestaForm;
import it.aizoon.ersaf.integration.AbstractTestDataSourceInitializer;

public class RichiestaMapperTest extends AbstractTestDataSourceInitializer<RichiestaMapper> {
  
  @BeforeClass
  public static void setUpClass() {
    sqlSessionFactory = getSqlSessionFactory(RichiestaMapper.class);
  }
  
  public RichiestaMapperTest() {
    super(RichiestaMapper.class);
  }  

  //@Test
  public void getProvaDettaglioRichiesta() {
    System.out.println("\nPROVA INIT");
     
    DettaglioRichiestaDto returnValue = null;
    try {
      returnValue = mapper.getDettaglioRichiesta(1L);
    } catch (Exception e) {      
      e.printStackTrace();
    }
    
    System.out.println("\nVALORE : ->"+returnValue.getIdRichiesta()+"<-");
    
    System.out.println("\nPROVA END");
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
    nuovaRichiesta.setIdNazioneMittente(1L);
    nuovaRichiesta.setIdComuneIta(1L);
    nuovaRichiesta.setDenominazioneDestinatario("Aldo Destinatario");
    nuovaRichiesta.setIndirizzoDestinatario("via di Aldo");
    nuovaRichiesta.setComuneDestinatario("comune di Aldo");
    nuovaRichiesta.setIdNazioneDestinatario(2L);
    nuovaRichiesta.setNumDocumentoTrasporto("AS123FG");
    nuovaRichiesta.setIdModoTrasporto(1L);
    nuovaRichiesta.setIdPuntoEntrataDichiarato(1L);
    nuovaRichiesta.setIdNazioneDestinatario(2L);
       
    
    try {
      returnValue = mapper.inserisciNuovaRichiesta(nuovaRichiesta, 1L);
    } catch (Exception e) {      
      e.printStackTrace();
    }
    
    System.out.println("\nVALORE : ->"+returnValue+"<-");
    
    System.out.println("\nPROVA END");
  }
}
