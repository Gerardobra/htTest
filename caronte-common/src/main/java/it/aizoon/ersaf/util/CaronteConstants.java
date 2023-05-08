package it.aizoon.ersaf.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class CaronteConstants {
  private static Properties caronteProperties = null;

  public static final String LOGGER_NAME = "caronte";
  public static final String LOGGER_ADD_BUSINESS = ".business";
  
  //public static final String CARONTE_DS = "java:/ersaf/jdbc/caronteTxDSLocal";
  public static final String CARONTE_DS = "java:/ersaf/jdbc/caronteTxDS";
  public static final String DATASOURCE_JNDI_PROPERTY = "datasource.jndi.name";
  public static final String APPLICATION_URL_PROPERTY = "url.accesso.applicativo";
  
  public static final String NOME_COOKIE_HOMEPAGE = "homepageCookie";
  
  //TIPO RICHIESTA
  public static final Long TIPO_RICHIESTA_IMPORT = 1L;
  public static final Long TIPO_RICHIESTA_EXPORT = 2L;
  public static final Long TIPO_RICHIESTA_RIEXPORT = 3L;
  public static final String COD_TIPO_RICHIESTA_IMPORT = "IM";
  
  public static final Long TIPO_NOTIFICA_GENERICO = 1L;
  public static final Long TIPO_NOTIFICA_SERVIZIO = 2L;
  public static final Long TIPO_NOTIFICA_CERTIFICATO = 3L;
  
  //TIPO ALLEGATO
  public static final Long TIPO_CERTIFICATO_ORIGINALE_RIEXPORT = 1L;
  
  //STATI RICHIESTA
  public static final Long STATO_RICHIESTA_BOZZA = 1L;
  public static final Long STATO_RICHIESTA_INOLTRATA = 2L;
  public static final Long STATO_RICHIESTA_ESEGUITA = 3L;
  public static final Long STATO_RICHIESTA_LIQUIDATA = 4L;
  public static final Long STATO_RICHIESTA_RESTITUITA = 5L;
  public static final Long STATO_RICHIESTA_ANNULLATA = 6L;
  
  public static final String DESC_STATO_RICHIESTA_BOZZA = "In Bozza";
  public static final String DESC_STATO_RICHIESTA_INOLTRATA = "Inoltrata";
  public static final String DESC_STATO_RICHIESTA_ESEGUITA = "Eseguita";
  public static final String DESC_STATO_RICHIESTA_LIQUIDATA = "Liquidata";
  public static final String DESC_STATO_RICHIESTA_RESTITUITA = "Restituita";
  public static final String DESC_STATO_RICHIESTA_ANNULLATA = "Annullata";
  
  //STATI COMUNICAZIONE
  public static final Long STATO_COMUNICAZIONE_BOZZA = 1L;
  public static final Long STATO_COMUNICAZIONE_INOLTRATA = 2L;
  public static final Long STATO_COMUNICAZIONE_CONVALIDATA = 3L;
  public static final Long STATO_COMUNICAZIONE_RESPINTA = 4L;
  public static final Long STATO_COMUNICAZIONE_ANNULLATA = 5L;
  public static final Long STATO_COMUNICAZIONE_CREA_DOMANDA_SUCCESSIVA = 6L;
  
  public static final String DESC_STATO_COMUNICAZIONE_BOZZA = "In Bozza";
  public static final String DESC_STATO_COMUNICAZIONE_INOLTRATA = "Inoltrata";
  public static final String DESC_STATO_COMUNICAZIONE_CONVALIDATA = "Convalidata";
  public static final String DESC_STATO_COMUNICAZIONE_RESPINTA = "Respinta";
  public static final String DESC_STATO_COMUNICAZIONE_ANNULLATA = "Annullata";
  
  // MAIL DI REGISTRAZIONE / CAMBIO UTENTE
  public static final String MAIL_SESSION = "java:jboss/mail/Default";
  public static final String INDIRIZZO_MITTENTE_REGISTRAZIONE_UTENTE = "infofito@regione.lombardia.it"; //Il nuovo
  //public static final String INDIRIZZO_MITTENTE_REGISTRAZIONE_UTENTE = "assistenza@ersaf.lombardia.it";  //Il vecchio
  public static final String URL_ACCESSO_APPLICATIVO = "https://caronte.ersaflombardia.it/";
  
  //CODICI NAZIONE
  public static final String CODICE_NAZIONE_ITALIA = "IT";
  
  //CODICI LINGUA
  public static final String CODICE_LINGUA_LATINO = "LA";
  //ID LINGUA
  public static final Long ID_LINGUA_LATINO = 138L;
  
  
  //ID REGIONE
  public static final Long ID_REGIONE_LOMBARDIA = 3L;
  
  //TIPO CERTIFICATO
  public static final Long ID_TIPO_CERTIFICATO_PRIMA_STAMPA = 1L;
  public static final Long ID_TIPO_CERTIFICATO_DUPLICATO = 2L;
  
  //TIPO NUMERAZIONE CERTIFICATO
  public static final String NUMERAZIONE_CERTIFICATO_NESSUNA = "1";
  public static final String NUMERAZIONE_CERTIFICATO_MANUALE = "2";
  public static final String NUMERAZIONE_CERTIFICATO_AUTOMATICA = "3";
  
  //TIPI CONTROLLO
  public static final Long ID_TIPO_CONTROLLO_DOCUMENTALE = 1L;
  public static final Long ID_TIPO_CONTROLLO_IDENTITA = 2L;
  public static final Long ID_TIPO_CONTROLLO_FITOSANITARIO = 3L;
  
  //TABELLA COSTANTI
  public static final String CODICE_COSTANTE_DESTINATARIO_PAGAMENTO = "DEST_PAGAM";
  public static final String CODICE_COSTANTE_REGIONE = "COD_REGION";
  public static final String CODICE_COSTANTE_CC_ORIGINALE = "CC_ORIG";
  public static final String CODICE_COSTANTE_CC_COPIA = "CC_COPIA";
  //public static final String CODICE_COSTANTE_AUTOCERTIFICAZIONE_VIVAI = "CHECK_COM_VIVAI";
  public static final String CODICE_COSTANTE_TESTO_MAIL_MASS = "TESTO_MAIL_MASS";
  public static final String CODICE_COSTANTE_INVIO_MAIL_CAMBIO_STATO_DOM_RUOP = "INVIO_MAILRUOP";
  public static final String CODICE_COSTANTE_INVIO_MAIL_ISPETTORE_DOM_RUOP = "INVIO_MAILISPET";
  public static final String CODICE_COSTANTE_TESTO_INOLTRA_R ="TESTO_INOLTRA_R";
  public static final String CODICE_COSTANTE_TESTO_INOLTRA_PARTE_1 ="TESTO_INOLTRA_1";
  public static final String CODICE_COSTANTE_TESTO_INOLTRA_PARTE_2 ="TESTO_INOLTRA_2";
  public static final String CODICE_COSTANTE_TESTO_INOLTRA_PARTE_3 ="TESTO_INOLTRA_3";
  
  //ATTRIBUTI SESSIONE
  public static final String SESSION_MESSAGGIO_ERRORE = "sessionMessaggioErrore";
  public static final String SESSION_MESSAGGIO_SUCCESSO = "sessionMessaggioSuccesso";
  
  //RUOLI UTENTE
  public static final Long ID_TIPO_UTENTE = 1L;
  public static final Long ID_TIPO_UTENTE_CONSULTATORE = 2L;
  public static final Long ID_TIPO_UTENTE_ADMIN = 4L;
  
  //TIPI SPEDIZIONIERI 
  public static final Long DITTA_INDIVIDUALE = 5L;
  public static final Long UTENTE_PRIVATO = 1L; // Note : ID_TIPO_SPEDIZIONIERE associato a tipo spedizioniere : 'ALTRO'
  
  //PRODOTTI
  public static final Long ID_PRODOTTO_MACCHINARI_TERRA = 56L;
  public static final Long ID_PRODOTTO_SUBSTRATI_COLTURALI = 57L;
  public static final Long ID_PRODOTTO_ALTRO = 58L;
  
  //UNITA MISURA
  public static final Long ID_UNITA_MISURA_KGM = 1L;
  public static final Long ID_UNITA_MISURA_MTQ = 2L;
  public static final Long ID_UNITA_MISURA_NMB = 3L;

  //ASSOCIAZIONE SEZIONE
  public static final Long ID_ASSOCIAZIONE_SEZIONE_IMPORT = 1L;
  public static final Long ID_ASSOCIAZIONE_SEZIONE_EXPORT = 2L;
  public static final Long ID_ASSOCIAZIONE_SEZIONE_VIVAI = 4L;
  public static final Long ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI = 5L;
  public static final Long ID_ASSOCIAZIONE_SEZIONE_CONTROLLI = 6L;
  
  //ID UTENTE
  public static final Long ID_UTENTE_PORTING = 1L;
  
  //TIPI COMUNICAZIONE
  public static final Long ID_TIPO_COMUNICAZIONE_SPECIE_ANNUALE = 1L;
  public static final Long ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP = 2L;
  public static final Long ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP = 3L;
  public static final Long ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP = 4L;
  public static final Long ID_TIPO_COMUNICAZIONE_CONTROLLO = 5L;
  public static final Long ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO = 6L;
  
  //STATI COMUNICAZIONE 
  public static final Long ID_STATO_COMUNICAZIONE_CREA_DOMANDA_SUCCESSIVA = 6l;

  
  public static final Long ID_TIPO_ALLEGATO_DOCUMENTAZIONE = 2L;
  public static final Long ID_TIPO_ALLEGATO_MARCA_DA_BOLLO = 5L;
  public static final Long ID_TIPO_ALLEGATO_AUTORIZZAZIONE_RUOP = 6L;
  
  public static final String DESC_TIPO_COMUNICAZIONE_DOMANDA_RUOP = "Domanda di Registrazione RUOP";
  public static final String DESC_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP = "Variazione Domanda di Registrazione RUOP";
  
  public static final String FLAG_TRUE = "S";
  public static final String FLAG_FALSE = "N";

  //COSTANTI GENERICHE
  public static final long DAY_IN_MS = 1000 * 60 * 60 * 24;
  public static final int LIMITE_GIORNI_NOTIFICHE = 30;
  
  // URL SERVIZI ESTERNI
  public static final String ENDPOINT_SERVIZIO_PROTOCOLLO = "endpoint.servizio.protocollo";
  public static final String AUTHORIZATION_SERVIZIO_TOKEN = "authorization.servizio.token";
  
  public static final Long PRIMA_RICHIESTA_PASSAPORTO = 82L;
  public static final Long AGGIORNAMENTO_PASSAPORTO = 83L;
  
  //TIPOLOGIE PRODUTTIVE CON GENERE OBBLIGATORIO
  public static final String ID_TIP_PROD_GENERE_OBBLIGATORIO = "6,7,8,9,11,12,13,14,17";
 
  
  public static Properties getProperties() {
    if (caronteProperties == null) {
      caronteProperties = new Properties();
      
      try (InputStream inputStream = CaronteConstants.class.getClassLoader().getResourceAsStream("caronte.properties")) {
        caronteProperties.load(inputStream);
      }catch (IOException ioe) {
        caronteProperties = null;
      }
    }
    
    return caronteProperties;
  }
  
  public static final String PATH_ASSOCIAZIONE_SEZIONE_IMPORT = "/import/";
  public static final String PATH_ASSOCIAZIONE_SEZIONE_EXPORT = "/export/";
  public static final String PATH_ASSOCIAZIONE_SEZIONE_VIVAI = "/vivai/";
  public static final String PATH_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI = "/autorizzazioni/";
  public static final String PATH_ASSOCIAZIONE_SEZIONE_CONTROLLI = "/controlli/";
  
  public static final long ID_TIPO_MODELLO_RUOP = 1L;
  public static final long ID_TIPO_MODELLO_RUOP_CA = 2L;
  public static final long ID_TIPO_MODELLO_RUOP_IMP = 3L;
  public static final long ID_TIPO_MODELLO_RUOP_EXP = 4L;
  public static final long ID_TIPO_MODELLO_RUOP_PASS = 5L;
  
  public static final long ID_TIPOLOGIA_DOMANDA_IMP = 1L;
  public static final long ID_TIPOLOGIA_DOMANDA_EXP = 2L;
  public static final long ID_TIPOLOGIA_DOMANDA_VIVAI = 3L;
  public static final long ID_TIPOLOGIA_DOMANDA_PASS = 4L;
  
  public static final long ID_TIPO_STAMPA_CENTRI_AZIENDALI =2L;
  public static final long ID_TIPO_STAMPA_IMPORT =3L;
  public static final long ID_TIPO_STAMPA_EXPORT =4L;
  public static final long ID_TIPO_STAMPA_PASSAPORTO =5L;
  
  public static final long ID_TIPO_STAMPA_SUBREP_TIPO_OPERATORE =6L;
  public static final long ID_TIPO_STAMPA_SUBREP_ATTIVITA =7L;
  public static final long ID_TIPO_STAMPA_SUBREP_MATERIALE =8L;
  public static final long ID_TIPO_STAMPA_SUBREP_TIPOLOGIA_PRODUTTIVA =9L;
  public static final long ID_TIPO_STAMPA_SUBREP_TIPO_ALLEGATO =10L;
  public static final long ID_TIPO_STAMPA_SUBREP_PASSAPORTI =11L;
  public static final long ID_TIPO_STAMPA_SUBREP_CONTIN_INFO_EXPORT =12L;
  public static final long ID_TIPO_STAMPA_SUBREP_SITI_PRODUZIONE =13L;
  //subreport per il Verbale ispezione Controlli
  public static final long ID_TIPO_STAMPA_SUBREP_SITI_PROD_CONTR =15L;
  public static final long ID_TIPO_STAMPA_SUBREP_ATTIVITA_CONTR =16L;
  public static final long ID_TIPO_STAMPA_SUBREP_GENERI_SPECIE_CONTR =17L;
  public static final long ID_TIPO_STAMPA_SUBREP_CAMPIONI_CONTR =18L;
  public static final long ID_TIPO_STAMPA_SUBREP_ALLEGATI_CONTR =19L;
  public static final long ID_TIPO_STAMPA_VERBALE_DISPOSIZIONE_MU =20L;
  public static final long ID_TIPO_STAMPA_SUBREP_MISURA_APPL_DISP =21L;
  public static final long ID_TIPO_STAMPA_SUBREP_GENERI_SPECIE_DISP =22L;
  public static final long ID_TIPO_STAMPA_VERBALE_CONSTATAZIONE_MU =23L;
  public static final long ID_TIPO_STAMPA_SUBREP_DICHIARA_PASS = 24L;
  public static final long ID_TIPO_STAMPA_SUBREP_ORGANISMO_NOCIVO = 26L;
  // verbale ispezione seconda versione controllo
  public static final long ID_TIPO_STAMPA_VERB_ISP_SECONDA_VERSIONE = 25L;
  

  
  public static final long ID_CENTRO_AZIENDALE_NUOVO = 0L;
  
  public static final long ID_RUOLO_UTENTE_BASE = 1L;
  public static final long ID_RUOLO_UTENTE_ADMIN = 4L;
  public static final long ID_RUOLO_UTENTE_SUPERUSER = 3L;
  
  // Stati azienda
  public static final long ID_STATO_AZIENDA_ATTIVA = 1L;
  public static final long ID_STATO_AZIENDA_SOSPESA = 2L;
  public static final long ID_STATO_AZIENDA_REVOCATA = 3L;
  public static final long ID_STATO_AZIENDA_CANCELLATA = 4L;
  
  public static final long ID_VOCE_PIANTE_DESTINATE_IMPIANTO = 50L;
  public static final long ID_VOCE_SEMENTI = 54L;
  public static final long ID_VOCE_PASSAPORTO_ZONA_PROTETTA = 78L;
  public static final long ID_VOCE_DICHIARA_CONOSCENZE = 84L;
  public static final long ID_VOCE_DICHIARA_DISPORRE_SISTEMI = 85L;
  public static final long ID_VOCE_PIANO_RISCHI	= 86L;
  
  
  //tipologia passaporto
  public static final long ID_TIPOLOGIA_PASSAPORTO_ORDINARIO = 1L;
  public static final long ID_TIPOLOGIA_PASSAPORTO_ORDINARIO_ZP = 3L;
  
  //Tipo Azienda
  public static final long ID_TIPO_AZIENDA_DITTA_INDIVIDUALE = 5L;
  
  // tipo attivita azienda  
  public static final long ID_TIPO_ATTIVITA_VIVAISMO = 1L;
  public static final long ID_TIPO_ATTIVITA_SEMENTIERA = 2L;
  public static final long ID_TIPO_ATTIVITA_ALTRO = 8L;
  public static final long ID_TIPO_ATTIVITA_IMPORTAZIONE = 10L;
  public static final long ID_TIPO_ATTIVITA_ESPORTAZIONE = 11L;
  public static final long ID_VOCE_ALTRO_TIPOLOG_CENTRI_AZ = 18L;
  public static final long ID_VOCE_ALTRO_TIPOLOG_IMPORT = 39L;
  public static final long ID_VOCE_ALTRO_TIPOLOG_EXPORT = 49L;
    
  // SEZIONE CONTROLLI
  public static final String FLAG_S = "S";
  // Tipo campione
  public static final String COD_SINTOMATICO = "S";
  public static final String DESCR_SINTOMATICO = "Sintomatico";
  public static final String COD_ASINTOMATICO = "A";
  public static final String DESCR_ASINTOMATICO = "Asintomatico";
  public static final String COD_NON_APPLICABILE = "NA";
  public static final String DESCR_NON_APPLICABILE = "Non applicabile";
  public static final String FL_ORG_NOC_CAMPIONE_DA_RICERCARE  = "R";
  public static final String FL_ORG_NOC_CAMPIONE_ACCERTATO  = "A";
  // Esito
  public static final String COD_ASSENTE = "A";
  public static final String DESCR_ASSENTE = "Assente";
  public static final String COD_PRESENTE = "P";
  public static final String DESCR_PRESENTE = "Presente";
  

  
  // organismo nocivo
  public static final Long ID_ORG_NOCIVO_ALTRO = 120L;
  
  // Tipo misura
  public static String TIPO_MISURA_DISPOSIZIONE = "D";
  public static String TIPO_MISURA_CONSTATAZIONE = "C";
  public static String TIPO_ISPETTORE_ISPETTORE = "I";
  
  // Tipologie controlli
  public static final Long ID_TIPOLOGIA_CONTROLLO_DOCUMENTALE = 1L;
  public static final Long ID_TIPOLOGIA_CONTROLLO_IDENTITA = 2L;
  public static final Long ID_TIPOLOGIA_FISICO = 3L;
  
  // Tab di provenienza
  public static final String TAB_DATI_GEBERALI = "DATI_GENERALI";
  public static final String TAB_DOCUMENTALE = "DOCUMENTALE";
  public static final String TAB_IDENTITA = "IDENTITA";
  
  // Versione Controlli(checklist)
  public static final Long ID_VERSIONE_CONTROLLO_BEGIN = 1L;// prima versione della checklist, MODIFICARE LA DATA FINE
  public static final Long ID_VERSIONE_CONTROLLO_04_2021 = 2L;
  
  // costanti per i json del Protocollo
  public static final String PROTOCOLLO_METADOCUMENTO_GENERICO = "ALLEGATO_CARONTE";// non modificabile, altrimenti errore(provato con postman)
  public static final String PROTOCOLLO_OGGETTO_REGISTRAZIONE_RUOP = "Domanda RUOP num: ";
  public static final String PROTOCOLLO_OGGETTO_VARIAZIONE_RUOP = "Domanda variazione RUOP num: ";
  public static final String PROTOCOLLO_OGGETTO_CANCELLAZIONE_RUOP = "Domanda cancellazione RUOP num: ";
  public static final String PROTOCOLLO_OGGETTO_PASSAPORTO_RUOP = "Domanda rilascio passaporto num: ";
  public static final String PROTOCOLLO_OGGETTO_GENERICO = "Domanda num:";
  public static final String PROTOCOLLO_COD_DESTINATARIO = "cod.destinatario.protocollo";
  public static final String PROTOCOLLO_TIPO_DOCUMENTO_DOMANDA = "Domanda";  
  public static final String PROTOCOLLO_COD_COSTANTE_TOKEN = "TOKEN_ARIA";
  public static final int PROTOCOLLO_ERROR_CODE = 900901;// codice di errore associato ad Invalid Credentials
  public static final String PROTOCOLLO_MESSAGE_ERROR_INVALID_CREDENTIALS = "Invalid Credentials";
  public static final String PROTOCOLLO_MESSAGE_ERROR_NOT_PROCESS = "The server cannot or will not process the request due to something that is perceived to be a client error";
  public static final String PROTOCOLLO_COSTANTE_INVIO_MAIL = "INVIO_MAILPROT";
  public static final String COSTANTE_ATTIVA_PROTOCOLLO = "ATTIVA_PROT";
  
  // Costante per la Privacy
  public static final String DATA_PUBBL_PRIV = "DATA_PUBBL_PRIV"; 
  public static final String TESTO_PRIVACY = "TESTO_PRIVACY";
  public static final String COD_COSTANTE_PDF_PRIVACY = "PDF_PRIVACY";
  public static final long ID_VOCE_TORBA_TERRICCIO_EXPORT = 42L;
  public static final long ID_VOCE_TORBA_TERRICCIO_IMPORT = 32L;
  public static final Long ID_GRUPPO_ZONA_PROTETTA_GENERIC = 32L;
  
  //Download manuale utente caronte
  public static final int MAN_CARONTE = 68;
  
  // Versione Domande (versione del layout)
  public static final Long ID_VERSIONE_DOMANDA_1 = 1L;
  public static final Long ID_VERSIONE_DOMANDA_2 = 2L;
  
  
  
  
  
  

}
