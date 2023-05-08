package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.BeforeClass;
import org.junit.Test;

import it.aizoon.ersaf.dto.IspettoreDTO;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.UtenteDTO;
import it.aizoon.ersaf.form.IspettoreForm;
import it.aizoon.ersaf.form.SpedizioniereForm;
import it.aizoon.ersaf.form.UtenteForm;
import it.aizoon.ersaf.integration.AbstractTestDataSourceInitializer;

/**
 * @author ivan.morra
 */
public class UtenteMapperTest extends AbstractTestDataSourceInitializer<UtenteMapper> {
  
  @BeforeClass
  public static void setUpClass() {
    sqlSessionFactory = getSqlSessionFactory(UtenteMapper.class);
  }
  
  public UtenteMapperTest() {
    super(UtenteMapper.class);
  }
    
  @Test
  public void getElencoSpedizionieri() {
    System.out.println("\n\nGET ELENCO SPEDIZIONIERI INIT");
    SpedizioniereForm spedizioniereForm = new SpedizioniereForm();
    spedizioniereForm.setAttivo(true);
    
    List<SpedizioniereDTO> ll = mapper.getElencoSpedizionieri(spedizioniereForm);
    System.out.println("\n\nLISTA SPEDIZIONIERI: "+ll);
    
    if (ll != null) {
      System.out.println("\n\nNUMERO SPEDIZIONIERI: "+ll.size());
    }
    System.out.println("\n\nGET ELENCO SPEDIZIONIERI FINE");
  }
  
  @Test
  public void getUtente() {
    System.out.println("\n\nGET UTENTE INIT");
    Long idUtenteTest = 3L;
    
    UtenteDTO utente = mapper.getUtente(idUtenteTest);
    
    System.out.println("\n\nUTENTE: "+utente);
    if (utente != null) {
      System.out.println("\n\nDENOMINAZIONE UTENTE: "+utente.getDenominazione());
      if (utente.getSezioni() != null) {
        System.out.println("\n\nNUMERO SEZIONI: "+utente.getSezioni().size());
      }
    }
    System.out.println("\n\nGET UTENTE FINE");
  }
  
  @Test
  public void getElencoUtenti() {
    System.out.println("\n\nGET ELENCO UTENTI INIT");
    UtenteForm utenteForm = new UtenteForm();
    utenteForm.setIdSpedizioniere(1L);
    
    List<UtenteDTO> listaUtenti = mapper.getElencoUtenti(utenteForm);
    
    System.out.println("\n\nLISTA UTENTI: "+listaUtenti);
    if (listaUtenti != null) {
      System.out.println("\n\nNUMERO UTENTI: "+listaUtenti.size());
    }
    System.out.println("\n\nGET ELENCO UTENTI FINE");
  }
  
  @Test
  public void getSpedizioniere() {
    System.out.println("\n\nGET SPEDIZIONIERE INIT");
    Long idSpedizioniereTest = 1L;
    
    SpedizioniereDTO spedizioniere = mapper.getSpedizioniere(idSpedizioniereTest);
    
    System.out.println("\n\nSPEDIZIONIERE: "+spedizioniere);
    if (spedizioniere != null) {
      System.out.println("\n\nDENOMINAZIONE SPEDIZIONIERE: "+spedizioniere.getDenominazione());
      if (spedizioniere.getSezioni() != null) {
        System.out.println("\n\nNUMERO SEZIONI: "+spedizioniere.getSezioni().size());
      }
    }
    System.out.println("\n\nGET SPEDIZIONIERE FINE");
  }
  
  @Test
  public void getIspettore() {
    System.out.println("\n\nGET ISPETTORE INIT");
    Long idIspettoreTest = 1L;
    
    IspettoreDTO ispettore = mapper.getIspettore(idIspettoreTest);
    
    System.out.println("\n\nISPETTORE: "+ispettore);
    if (ispettore != null) {
      System.out.println("\n\nDENOMINAZIONE ISPETTORE: "+ispettore.getDenominazione());
      if (ispettore.getSezioni() != null) {
        System.out.println("\n\nNUMERO SEZIONI: "+ispettore.getSezioni().size());
      }
    }
    System.out.println("\n\nGET ISPETTORE FINE");
  }
  
  @Test
  public void getElencoIspettori() {
    System.out.println("\n\nGET ELENCO ISPETTORI INIT");
    IspettoreForm ispettoreForm = new IspettoreForm();
    ispettoreForm.setIdSpedizioniere(2L);
    
    List<IspettoreDTO> listaIspettori = mapper.getElencoIspettori(ispettoreForm);
    
    System.out.println("\n\nLISTA ISPETTORI: "+listaIspettori);
    if (listaIspettori != null) {
      System.out.println("\n\nNUMERO ISPETTORI: "+listaIspettori.size());
    }
    System.out.println("\n\nGET ELENCO ISPETTORI FINE");
  }
  
  @Test
  public void insertRUtenteGruppo() {
    System.out.println("\n\nINSERT UTENTE GRUPPO INIT");
    UtenteForm utenteForm = new UtenteForm();
    utenteForm.setIdUtente(4L);
    utenteForm.setIdRuolo(2L);
    Long idSezioneTest = 1L;
      
    int inseriti = mapper.insertRUtenteGruppo(utenteForm, idSezioneTest);
    System.out.println("\n\nNUMERO RECORD UTENTE GRUPPO INSERITI: "+inseriti);
    System.out.println("\n\nINSERT UTENTE GRUPPO FINE");
  }
  
  
}
