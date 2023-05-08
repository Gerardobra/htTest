package it.aizoon.ersaf.stampe;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

import it.aizoon.ersaf.dto.CentroAziendaleDomandaDTO;
import it.aizoon.ersaf.dto.TariffeRichiestaDto;
import it.aizoon.ersaf.dto.stampe.EverdeDTO;
import it.aizoon.ersaf.dto.stampe.FitosanitarioExportDTO;
import it.aizoon.ersaf.dto.stampe.FitosanitarioRiexportDTO;
import it.aizoon.ersaf.dto.stampe.NullaOstaDTO;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.integration.mybatis.SqlSessionFactoryProvider;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRJdtCompiler;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class StampeManager {
	 
  protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".web");

  public static ByteArrayOutputStream getPdfNullaOsta(NullaOstaDTO nullaOsta) {

    ByteArrayOutputStream byteArrayOutputStream = null;

    try {
      JasperReport jasperReport = caricaTemplateCompilato("/jasper/compiled/allegatoB-Nullaosta.jasper");
      
      separaInfoSupplementari(nullaOsta);
      
      nullaOsta.setOriginale("ORIGINALE");
      
      byteArrayOutputStream = generaPdfNullaOsta(jasperReport, nullaOsta, null);

    } catch (Exception e) {
      logger.error("Errore nella generazione del report Jasper", e);
    }

    return byteArrayOutputStream;
  }

  public static ByteArrayOutputStream getPdfEverde(EverdeDTO everde) {

    ByteArrayOutputStream byteArrayOutputStream = null;

    try {
      JasperReport jasperReport = caricaTemplateCompilato("/jasper/compiled/allegatoEverde.jasper");
      byteArrayOutputStream = generaPdfEverde(jasperReport, everde, null);
      // inputStream = new
      // ByteArrayInputStream(byteArrayOutputStream.toByteArray());

    } catch (Throwable e) {
      logger.error("Errore nella generazione del report Jasper", e);
    }

    return byteArrayOutputStream;
  }

  public static ByteArrayOutputStream getPdfCertificatoExport(FitosanitarioExportDTO export) {

    ByteArrayOutputStream byteArrayOutputStream = null;

    try {
      JasperReport jasperReport = caricaTemplateCompilato(
          "/jasper/compiled/Fitosanitario1.1(solo_campi_dinamici).jasper");

      separaMerciCertificato(export);

      byteArrayOutputStream = generaPdfCertificatoExport(jasperReport, export, null);
      // inputStream = new
      // ByteArrayInputStream(byteArrayOutputStream.toByteArray());

    } catch (Throwable e) {
      logger.error("Errore nella generazione del report Jasper", e);
    }

    return byteArrayOutputStream;
  }

  public static ByteArrayOutputStream getPdfCertificatoRiexport(FitosanitarioRiexportDTO riexport) {
    ByteArrayOutputStream byteArrayOutputStream = null;

    try {
      JasperReport jasperReport = caricaTemplateCompilato(
          "/jasper/compiled/FitosanitarioRiexport(solo_campi_dinamici).jasper");

      separaMerciCertificato(riexport);

      byteArrayOutputStream = generaPdfCertificatoRiexport(jasperReport, riexport, null);
      // inputStream = new
      // ByteArrayInputStream(byteArrayOutputStream.toByteArray());

    } catch (Throwable e) {
      logger.error("Errore nella generazione del report Jasper", e);
    }

    return byteArrayOutputStream;
  }

  private static JasperReport caricaTemplateCompilato(String filePath) throws JRException {

    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(StampeManager.class.getResourceAsStream(filePath));

    return jasperReport;
  }

  private static ByteArrayOutputStream generaPdfNullaOsta(JasperReport jasperReport, NullaOstaDTO nullaOsta,
      HashMap<String, Object> additionalParameters) throws Exception {

    Map<String, Object> parameters = new HashMap<String, Object>();

    if (nullaOsta == null) {
      nullaOsta = new NullaOstaDTO();
    } else if (StringUtils.isEmpty(nullaOsta.getDataTrattamento())) {
      nullaOsta.setDataTrattamento(CaronteUtils.leftPad("", 25, '*'));
    }

    if (additionalParameters != null) {
      for (Entry<String, Object> entry : additionalParameters.entrySet()) {
        parameters.put(entry.getKey(), entry.getValue());
      }
    }

    InputStream stella = StampeManager.class.getResourceAsStream("/jasper/sources/stella.png");
    BufferedImage stellaImg = ImageIO.read(stella);
    parameters.put("stella", stellaImg);
    InputStream regione = StampeManager.class.getResourceAsStream("/jasper/sources/regione.png");
    BufferedImage regioneImg = ImageIO.read(regione);
    parameters.put("regione", regioneImg);

    logger.info("nullaOsta: " + nullaOsta.toString());
    Collection<NullaOstaDTO> list = new ArrayList<NullaOstaDTO>();
    list.add(nullaOsta);

    List<JasperPrint> listaReport = new ArrayList<>();
    
    listaReport.add(JasperFillManager.fillReport(jasperReport, parameters,
        new JRBeanCollectionDataSource(list)));
    
    nullaOsta.setOriginale("COPIA");
    
    listaReport.add(JasperFillManager.fillReport(jasperReport, parameters,
        new JRBeanCollectionDataSource(list)));
    
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
    JRPdfExporter exporter = new JRPdfExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(listaReport)); //Set as export input my list with JasperPrint s
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream)); //or any other out streaam
    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
    configuration.setCreatingBatchModeBookmarks(true); //add this so your bookmarks work, you may set other parameters
    exporter.setConfiguration(configuration);
    exporter.exportReport();
    
    //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

    return outputStream;
  }

  private static ByteArrayOutputStream generaPdfEverde(JasperReport jasperReport, EverdeDTO everde,
      HashMap<String, Object> additionalParameters) throws Exception {

    Map<String, Object> parameters = new HashMap<String, Object>();

    if (everde == null) {
      everde = new EverdeDTO();
    }

    if (additionalParameters != null) {
      for (Entry<String, Object> entry : additionalParameters.entrySet()) {
        parameters.put(entry.getKey(), entry.getValue());
      }
    }

    InputStream accredia = StampeManager.class.getResourceAsStream("/jasper/sources/accredia.jpg");
    BufferedImage accrediaImg = ImageIO.read(accredia);
    parameters.put("accredia", accrediaImg);
    InputStream ersaf = StampeManager.class.getResourceAsStream("/jasper/sources/ersaf.png");
    BufferedImage ersafImg = ImageIO.read(ersaf);
    parameters.put("ersaf", ersafImg);

    logger.info("everde: " + everde.toString());
    Collection<EverdeDTO> list = new ArrayList<EverdeDTO>();
    list.add(everde);

    JRBeanCollectionDataSource collectionMerci = new JRBeanCollectionDataSource(everde.getElencoMerci());
    parameters.put("collectionMerci", collectionMerci);

    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
        new JRBeanCollectionDataSource(list));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

    return outputStream;
  }

  private static ByteArrayOutputStream generaPdfCertificatoExport(JasperReport jasperReport,
      FitosanitarioExportDTO export, HashMap<String, Object> additionalParameters) throws Exception {
    Map<String, Object> parameters = new HashMap<String, Object>();

    if (export == null) {
      export = new FitosanitarioExportDTO();
    }

    convertDateCertificatoExport(export);

    if (additionalParameters != null) {
      for (Entry<String, Object> entry : additionalParameters.entrySet()) {
        parameters.put(entry.getKey(), entry.getValue());
      }
    }

    Collection<FitosanitarioExportDTO> list = new ArrayList<FitosanitarioExportDTO>();
    list.add(export);

    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
        new JRBeanCollectionDataSource(list));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
   
    return outputStream;
  }

  private static ByteArrayOutputStream generaPdfCertificatoRiexport(JasperReport jasperReport,
      FitosanitarioRiexportDTO riexport, HashMap<String, Object> additionalParameters) throws Exception {
    Map<String, Object> parameters = new HashMap<String, Object>();

    if (riexport == null) {
      riexport = new FitosanitarioRiexportDTO();
    }

    convertDateCertificatoRiexport(riexport);

    if (additionalParameters != null) {
      for (Entry<String, Object> entry : additionalParameters.entrySet()) {
        parameters.put(entry.getKey(), entry.getValue());
      }
    }

    Collection<FitosanitarioRiexportDTO> list = new ArrayList<FitosanitarioRiexportDTO>();
    list.add(riexport);

    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
        new JRBeanCollectionDataSource(list));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

    return outputStream;
  }

  public static ByteArrayOutputStream getPdfDettaglioRichiesta(Long idRichiesta,
      TariffeRichiestaDto tariffeRichiestaDto, boolean isImport) {

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    try {
      JasperReport jasperReport = null;
      if (isImport) {
        jasperReport = caricaTemplateCompilato("/jasper/compiled/dettaglioRichiestaImport.jasper");
        byteArrayOutputStream = generaPdfDettaglioRichiesta(jasperReport, idRichiesta, tariffeRichiestaDto);
      } else {
        jasperReport = caricaTemplateCompilato("/jasper/compiled/dettaglioRichiestaExport.jasper");
        JasperReport jasperReportMerce = caricaTemplateCompilato(
            "/jasper/compiled/dettaglioRichiestaExportMerce.jasper");
        // genereazione ed unione dei due report (PDF) dettaglio+merce
        PdfReader dettaglio = new PdfReader(
            generaPdfDettaglioRichiesta(jasperReport, idRichiesta, tariffeRichiestaDto).toByteArray());
        PdfReader merce = new PdfReader(generaPdfDettaglioRichiestaMerce(jasperReportMerce, idRichiesta).toByteArray());
        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, byteArrayOutputStream);
        document.open();
        copy.addDocument(dettaglio);
        copy.addDocument(merce);
        document.close();
        dettaglio.close();
        merce.close();
      }

    } catch (Exception e) {
      logger.error("Errore nella generazione del report Jasper", e);
    }

    return byteArrayOutputStream;
  }

  private static ByteArrayOutputStream generaPdfDettaglioRichiesta(JasperReport jasperReport, Long idRichiesta,
      TariffeRichiestaDto tariffeRichiestaDto) throws Exception {

    Map<String, Object> parameters = new HashMap<String, Object>();

    if (tariffeRichiestaDto == null) {
      tariffeRichiestaDto = new TariffeRichiestaDto();
    }

    Context ctxLookup = null;
    DataSource ds = null;

    try {
      ctxLookup = new InitialContext();
      // ds = (javax.sql.DataSource)
      // ctxLookup.lookup(CaronteConstants.CARONTE_DS);
      Properties dbProperties = SqlSessionFactoryProvider.getDbProperties();

      if (dbProperties == null) {
        ds = (javax.sql.DataSource) ctxLookup.lookup(CaronteConstants.CARONTE_DS);
      } else {
        ds = (javax.sql.DataSource) ctxLookup
            .lookup((String) dbProperties.get(CaronteConstants.DATASOURCE_JNDI_PROPERTY));
      }

    } catch (NamingException e) {
      throw new BusinessException("Errore nella generazione del report Jasper", e);
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (Connection conn = ds.getConnection()) {

      // Parameters
      parameters.put("idRichiesta", idRichiesta);
      parameters.put("totaleDocumentale",
          null != tariffeRichiestaDto.getTotaleDocumentale() ? tariffeRichiestaDto.getTotaleDocumentale() : 0);
      parameters.put("totaleIdentita",
          null != tariffeRichiestaDto.getTotaleIdentita() ? tariffeRichiestaDto.getTotaleIdentita() : 0);
      parameters.put("totaleFitosanitario",
          null != tariffeRichiestaDto.getTotaleFitosanitario() ? tariffeRichiestaDto.getTotaleFitosanitario() : 0);
      parameters.put("massimaleTariffa",
          null != tariffeRichiestaDto.getMassimaleTariffa() ? tariffeRichiestaDto.getMassimaleTariffa() : 0);
      parameters.put("numeroCertificati",
          null != tariffeRichiestaDto.getNumeroCertificati() ? tariffeRichiestaDto.getNumeroCertificati() : 0);
      parameters.put("totaleTariffe",
          null != tariffeRichiestaDto.getTotaleTariffe() ? tariffeRichiestaDto.getTotaleTariffe() : 0);

      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

      JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

    } catch (SQLException e) {
      throw new BusinessException("Errore nella generazione del report Jasper - Connection error ", e);
    } finally {

    }

    return outputStream;

  }

  private static ByteArrayOutputStream generaPdfDettaglioRichiestaMerce(JasperReport jasperReport, Long idRichiesta)
      throws Exception {

    Map<String, Object> parameters = new HashMap<String, Object>();

    Context ctxLookup = null;
    DataSource ds = null;

    try {
      ctxLookup = new InitialContext();
      // ds = (javax.sql.DataSource)
      // ctxLookup.lookup(CaronteConstants.CARONTE_DS);
      Properties dbProperties = SqlSessionFactoryProvider.getDbProperties();

      if (dbProperties == null) {
        ds = (javax.sql.DataSource) ctxLookup.lookup(CaronteConstants.CARONTE_DS);
      } else {
        ds = (javax.sql.DataSource) ctxLookup
            .lookup((String) dbProperties.get(CaronteConstants.DATASOURCE_JNDI_PROPERTY));
      }
    } catch (NamingException e) {
      throw new BusinessException("Errore nella generazione del report Jasper", e);
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (Connection conn = ds.getConnection()) {

      // Parameters
      parameters.put("idRichiesta", idRichiesta);

      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

      JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

    } catch (SQLException e) {
      throw new BusinessException("Errore nella generazione del report Jasper - Connection error ", e);
    } finally {

    }

    return outputStream;

  }

  private static void convertDateCertificatoExport(FitosanitarioExportDTO export) {
    export.setDataRilascio(formatDate(export.getDataRilascio()));

    if (StringUtils.isEmpty(export.getDataTrattamento())) {
      export.setDataTrattamento(CaronteUtils.leftPad("", 25, '*'));
    } else {
      export.setDataTrattamento(formatDate(export.getDataTrattamento()));
    }
  }

  private static void convertDateCertificatoRiexport(FitosanitarioRiexportDTO riexport) {
    riexport.setDataRilascio(formatDate(riexport.getDataRilascio()));

    if (StringUtils.isEmpty(riexport.getDataTrattamento())) {
      riexport.setDataTrattamento(CaronteUtils.leftPad("", 25, '*'));
    } else {
      riexport.setDataTrattamento(formatDate(riexport.getDataTrattamento()));
    }
  }

  private static String formatDate(String date) {
    return date != null && !date.isEmpty()
        ? LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(Locale.US)).toUpperCase()
        : "";
  }
  
  private static void separaInfoSupplementari(NullaOstaDTO dati) {
    if (dati != null) {
      String[] datiInput = { "", "", dati.getInfoVariePagina2(),
          dati.getAnnotazioniGenerali() };
      
      if (dati.getInfoVariePagina2().length() > 0) {
        String title = "<i>box 8) Distinguishing marks; number and description of packages; name of product; botanical name of plants</i><BR><BR>";
        datiInput[2] = title + datiInput[2];
      }

      separaDichiarazioneSupplemetare(datiInput, 300, "11: Annotazioni generali");

      dati.setInfoVariePagina2(datiInput[2]);
      dati.setAnnotazioniGenerali(datiInput[3]);
    }
  }

  private static void separaMerciCertificato(FitosanitarioExportDTO dati) {
    if (dati != null) {
      String[] datiInput = { dati.getNomeMarchiColliProdottoVegetali(), dati.getDatiQuantitaDichiarata(), "",
          dati.getInfoDichiarazioneSupplementare() };
      separaMerciCertificato(datiInput, 8);

      dati.setNomeMarchiColliProdottoVegetali(datiInput[0]);
      dati.setDatiQuantitaDichiarata(datiInput[1]);

      separaDichiarazioneSupplemetare(datiInput, 300, "11: Dichiarazione supplementare");

      dati.setInfoVariePagina2(datiInput[2]);
      dati.setInfoDichiarazioneSupplementare(datiInput[3]);
    }
  }

  private static void separaMerciCertificato(FitosanitarioRiexportDTO dati) {
    if (dati != null) {
      String[] datiInput = { dati.getNomeMarchiColliProdottoVegetali(), dati.getDatiQuantitaDichiarata(), "",
          dati.getInfoDichiarazioneSupplementare() };
      separaMerciCertificato(datiInput, 6);

      dati.setNomeMarchiColliProdottoVegetali(datiInput[0]);
      dati.setDatiQuantitaDichiarata(datiInput[1]);

      separaDichiarazioneSupplemetare(datiInput, 300, "11: Dichiarazione supplementare");

      dati.setInfoVariePagina2(datiInput[2]);
      dati.setInfoDichiarazioneSupplementare(datiInput[3]);
    }
  }

  private static void separaDichiarazioneSupplemetare(String[] datiInput, int i, String nomeRiquadro) {
    String infoDichiarazSupplementare = datiInput[3];
    
    if (infoDichiarazSupplementare != null) {
      if (infoDichiarazSupplementare.length() > i) {
        /*String infoDichiarazPagina1Temp = infoDichiarazSupplementare.substring(0, i);
        String infoDichiarazPagina1 = infoDichiarazPagina1Temp.substring(0, infoDichiarazPagina1Temp.lastIndexOf(" "));
        String infoDichiarazPagina1FinalPart = infoDichiarazPagina1Temp.substring(infoDichiarazPagina1Temp.lastIndexOf(" ") +1);
        datiInput[3] = infoDichiarazPagina1;
        String infoDichiarazPagina2 = infoDichiarazPagina1FinalPart + infoDichiarazSupplementare.substring(i);
        
    	  StringBuilder toAppend;
        
        if (datiInput[2].length() > 0) {
          toAppend = new StringBuilder(datiInput[2]);
          toAppend.append("<BR>");
        } else {
          toAppend = new StringBuilder();
        }
        
        toAppend.append("<i>box ").append(nomeRiquadro).append("</i><BR><BR>");
        toAppend.append(infoDichiarazPagina2);
        datiInput[2] = toAppend.toString();
        */
    	 StringBuilder toAppend;
    	 if (datiInput[2].length() > 0) {
             toAppend = new StringBuilder(datiInput[2]);
             toAppend.append("<BR>");
           } else {
             toAppend = new StringBuilder();
           }
            
         toAppend.append("<i>box ").append(nomeRiquadro).append("</i><BR><BR>");
         toAppend.append(infoDichiarazSupplementare);
         datiInput[2] = toAppend.toString(); 
         datiInput[3] = "See Attached";
    	  
      }
    }

  }

  
  private static void separaMerciCertificato(String[] dati, int limiteSeparazione) {

    if (StringUtils.isEmpty(dati[0]) || !dati[0].toUpperCase().contains("<BR>")) {
      return;
    }

    String[] tokenMerci = dati[0].split("(?i)<BR>");
    String[] tokenQuantita = dati[1].split("(?i)<BR>");
    
    StringBuilder merciResult = new StringBuilder();
    StringBuilder quantitaResult = new StringBuilder();
    StringBuilder infoVarieResult = new StringBuilder();

    for (int i = 0, numRighe = 0; i < tokenMerci.length; i++, numRighe++) {
      String merce = tokenMerci[i];

      if (numRighe < limiteSeparazione) {
        StringBuilder ultimaMerce = new StringBuilder();
        StringBuilder ultimaQuantita = new StringBuilder(tokenQuantita[i]).append("<BR>");

        while (tokenMerci[i].length() > 90) {
          String porzioneStringa = tokenMerci[i].substring(0, 90);
          int indiceSubstring = Math.max(porzioneStringa.lastIndexOf('/'), porzioneStringa.lastIndexOf(' '));

          if (indiceSubstring <= 0) {
            indiceSubstring = 90;
          }

          ultimaMerce.append(tokenMerci[i].substring(0, indiceSubstring)).append("<BR>");
          ultimaQuantita.append("<BR>");
          tokenMerci[i] = tokenMerci[i].substring(indiceSubstring);
          numRighe++;
        }

        ultimaMerce.append(tokenMerci[i]).append("<BR>");

        /*
         * Nel caso si sia superato il numero massimo di righe, l'ultima merce
         * (e relativa quantità) non vengono inserite nella prima pagina, ma in
         * quella successiva
         */
        if (numRighe < limiteSeparazione) {
          merciResult.append(ultimaMerce);
          quantitaResult.append(ultimaQuantita);
        } else {
          tokenMerci[i] = merce;
          i--;
        }
      } else {
        infoVarieResult.append(tokenMerci[i]).append("/").append(tokenQuantita[i]).append("<BR>");
      }
    }

    dati[0] = merciResult.toString();
    dati[1] = quantitaResult.toString();

    if (infoVarieResult.length() > 0) {
      String title = "<i>box 8) Distinguishing marks; number and description of packages; name of product; botanical name of plants</i><BR><BR>";
      dati[2] = title + infoVarieResult.toString();
    }
  }

  
  public static JasperPrint getFillReporByListTemplate(Long id, List<byte[]> listTemplate, String[] params, Connection conn, String nomeImmagine, String pathImmagine, Long idCentroAziendale) throws Exception {
	  logger.debug("getFillReporByListTemplate id - listaTemplate.size = "+id+" - "+listTemplate.size());	
	  
	  List<JasperReport> listJReport = new ArrayList<>();
	  Iterator<byte[]> iterTemplate = listTemplate.iterator();
	  while (iterTemplate.hasNext()) {
		  byte[] template = iterTemplate.next();
		  JasperDesign jDesign = JRXmlLoader.load(new ByteArrayInputStream(template));
		  JRJdtCompiler compiler = new JRJdtCompiler(DefaultJasperReportsContext.getInstance());
		  JasperReport jReport = compiler.compileReport(jDesign);
		  listJReport.add(jReport);
	  }

	  Map<String, Object> parameters = new HashMap<String, Object>();
		
      parameters.put(params[0], id);
      for (int i = 1; i < params.length; i++) {
    	  parameters.put(params[i], listJReport.get(i));
	  }
	  if (nomeImmagine != null) {
		  InputStream regione = StampeManager.class.getResourceAsStream(pathImmagine);
		  BufferedImage regioneImg = ImageIO.read(regione);
		  parameters.put(nomeImmagine, regioneImg);
	  }
	  logger.debug("getFillReporByListTemplate - idCentroAziendale = "+idCentroAziendale);
	  if (idCentroAziendale != null) {
		  logger.debug("getFillReporByListTemplate - idCentroAziendale = "+idCentroAziendale.longValue()+" - "+idCentroAziendale);
		  parameters.put("id_centro_aziendale", idCentroAziendale.longValue());
	  }
	  logger.debug("getFillReporByListTemplate - Parameters = "+parameters.values().toString());	
	  JasperPrint jPrint = JasperFillManager.fillReport(listJReport.get(0), parameters, conn);
		
	  return jPrint;
	}
  
  public static JasperPrint getFillReporByListTemplateConTimbro(Long id, List<byte[]> listTemplate, String[] params, Connection conn, String nomeImmagine, String pathImmagine, 
		  Long idCentroAziendale, String nomeImmagineTimbro, String pathImmagineTimbro) throws Exception {
	  logger.debug("getFillReporByListTemplate id - listaTemplate.size = "+id+" - "+listTemplate.size());	
	  
	  List<JasperReport> listJReport = new ArrayList<>();
	  Iterator<byte[]> iterTemplate = listTemplate.iterator();
	  while (iterTemplate.hasNext()) {
		  byte[] template = iterTemplate.next();
		  JasperDesign jDesign = JRXmlLoader.load(new ByteArrayInputStream(template));
		  JRJdtCompiler compiler = new JRJdtCompiler(DefaultJasperReportsContext.getInstance());
		  JasperReport jReport = compiler.compileReport(jDesign);
		  listJReport.add(jReport);
	  }

	  Map<String, Object> parameters = new HashMap<String, Object>();
		
      parameters.put(params[0], id);
      for (int i = 1; i < params.length; i++) {
    	  parameters.put(params[i], listJReport.get(i));
	  }
	  if (nomeImmagine != null) {
		  InputStream regione = StampeManager.class.getResourceAsStream(pathImmagine);
		  BufferedImage regioneImg = ImageIO.read(regione);
		  parameters.put(nomeImmagine, regioneImg);
	  }
	  
	  logger.debug("getFillReporByListTemplate - idCentroAziendale = "+idCentroAziendale);
	  if (idCentroAziendale != null) {
		  logger.debug("getFillReporByListTemplate - idCentroAziendale = "+idCentroAziendale.longValue()+" - "+idCentroAziendale);
		  parameters.put("id_centro_aziendale", idCentroAziendale.longValue());
	  }
	  
	  if (nomeImmagineTimbro != null) {
		  InputStream timbro = StampeManager.class.getResourceAsStream(pathImmagineTimbro);
		  BufferedImage timbroImg = ImageIO.read(timbro);
		  parameters.put(nomeImmagineTimbro, timbroImg);
	  }
	  
	  logger.debug("getFillReporByListTemplate - Parameters = "+parameters.values().toString());	
	  JasperPrint jPrint = JasperFillManager.fillReport(listJReport.get(0), parameters, conn);
		
	  return jPrint;
	}
  
  private static void generaAndCopyPdf(PdfCopy copy, JasperPrint jPrint) throws Exception {
	  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  	  JasperExportManager.exportReportToPdfStream(jPrint, outputStream);
  	  logger.debug("numero pagine: "+jPrint.getPages().size());
  	  if(jPrint.getPages().size() > 0 ) {
  		  PdfReader pdfReader = new PdfReader(outputStream.toByteArray());
  		  logger.debug("Lunghezza PDF getFileLength() = "+pdfReader.getFileLength());
  		  copy.addDocument(pdfReader);
  		  pdfReader.close();				
  	  } 
  }
  
  public static byte[] getPdfDettaglioDomandaRuop(Long idDomanda, List<byte[]> listTemplateRuop, String[] listNomiRuop, List<byte[]> listTemplateCentriAziendali,
		String[] listNomiCentriAziendali, List<byte[]> listTemplateImport, String[] listNomiImport, List<byte[]> listTemplateExport, String[] listNomiExport, 
		List<byte[]> listTemplatePassaporto, String[] listNomiPassaporto, List<CentroAziendaleDomandaDTO> listaCentriAz) throws Exception {

	  logger.info("BEGIN getPdfDettaglioDomandaRuop");
	  
	  byte[] result = null;
	  logger.debug("-- getPdfDettaglioDomandaRuop idDomanda =" + idDomanda);
	
	  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	   
	    
	  try (Connection conn = getConnection(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

		  JasperPrint jPrintRuop = getFillReporByListTemplate(idDomanda, listTemplateRuop, listNomiRuop, conn, "logoLombardia", "/jasper/sources/regione.png", null);
		  //JasperPrint jPrintRepCentriAziendali = getFillReporByListTemplate(idDomanda, listTemplateCentriAziendali, listNomiCentriAziendali, conn, null, null, 3233L);
 
		  //add di centri aziendali alla stampa
		  //bisogna implementare import, export, passaporto
		  JasperExportManager.exportReportToPdfStream(jPrintRuop, outputStream);      
	  	  PdfReader dettaglio = new PdfReader(outputStream.toByteArray());
	  	  Document document = new Document();
	  	  PdfCopy copy = new PdfCopy(document, byteArrayOutputStream);
	  	  document.open();
	  	  copy.addDocument(dettaglio);
	  	  
	  	  if(listTemplateCentriAziendali != null && listaCentriAz != null && listaCentriAz.size()>0){ 
	  		  logger.debug("-- listaCentriAz.size() ="+listaCentriAz.size());
		  	  // creo e metto in copia un pdf per ogni centro aziendale
		  	  Iterator<CentroAziendaleDomandaDTO> iterCA = listaCentriAz.iterator();
		  	  while (iterCA.hasNext()){
		  		  CentroAziendaleDomandaDTO centroAz = iterCA.next();
		  		  JasperPrint jPrintRepCentriAziendali = getFillReporByListTemplate(idDomanda, listTemplateCentriAziendali, listNomiCentriAziendali, conn, "logoLombardia", "/jasper/sources/regione.png", centroAz.getIdCentroAziendale());
		  		  generaAndCopyPdf(copy, jPrintRepCentriAziendali);
		  	  }
	  	  }
	  	  if (listTemplateImport != null && listTemplateImport.size() > 0) {
	  		  JasperPrint jPrintRepImport = getFillReporByListTemplate(idDomanda, listTemplateImport, listNomiImport, conn, "logoLombardia", "/jasper/sources/regione.png", null);
	  		  generaAndCopyPdf(copy, jPrintRepImport);
	  		logger.debug("-- inserisco listTemplateImport =" + idDomanda);
	  	  }
	  	  
	  	  if (listTemplateExport != null && listTemplateExport.size() > 0) {
	  		  JasperPrint jPrintRepExport = getFillReporByListTemplate(idDomanda, listTemplateExport, listNomiExport, conn, "logoLombardia", "/jasper/sources/regione.png", null);
	  		  generaAndCopyPdf(copy, jPrintRepExport);
	  		logger.debug("-- inserisco listTemplateExport =" + idDomanda);
	  	  }
	  	
	  	  if (listTemplatePassaporto != null && listTemplatePassaporto.size() > 0) {
	  		  JasperPrint jPrintRepPassaporto = getFillReporByListTemplate(idDomanda, listTemplatePassaporto, listNomiPassaporto, conn, "logoLombardia", "/jasper/sources/regione.png", null);
	  		  generaAndCopyPdf(copy, jPrintRepPassaporto);
	  		logger.debug("-- inserisco listTemplatePassaporto =" + idDomanda);
	  	  }
	  	  
		  document.close();
	  	  dettaglio.close();  	  
	  	  result = byteArrayOutputStream.toByteArray();
  
	         
	
	    } 
	    catch (Exception e) {
	    	logger.error("-- Exception in getPdfDettaglioDomandaRuop ="+e.getMessage());
	    	throw new BusinessException("Errore nella generazione del report Jasper - Connection error ", e);
	    }
	    finally {
	    	logger.info("END getPdfDettaglioDomandaRuop");
	    }
	    return result;	
  	}
 

	private static Connection getConnection() throws Exception {	
	    Context ctxLookup = null;
	    DataSource ds = null;
	
	    try {
	      ctxLookup = new InitialContext();
	      // ds = (javax.sql.DataSource)
	      // ctxLookup.lookup(CaronteConstants.CARONTE_DS);
	      Properties dbProperties = SqlSessionFactoryProvider.getDbProperties();
	
	      if (dbProperties == null) {
	        ds = (javax.sql.DataSource) ctxLookup.lookup(CaronteConstants.CARONTE_DS);
	      } else {
	        ds = (javax.sql.DataSource) ctxLookup
	            .lookup((String) dbProperties.get(CaronteConstants.DATASOURCE_JNDI_PROPERTY));
	      }
	    } catch (NamingException e) {
	      throw new BusinessException("Errore nella generazione del report Jasper", e);
	    }
	    return ds.getConnection();
	}

	public static byte[] getPdfVerbaleIspezioneControlli(Long idControllo, List<byte[]> listTemplateVerbaleIspezione, String[] listNomiIspezione) throws Exception {
	
		  byte[] result = null;
	
		  logger.debug("-- getPdfVerbaleIspezioneControlli idControllo =" + idControllo);
		
		  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		   
		    
		  try (Connection conn = getConnection(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
	
			  JasperPrint jPrintVerbale = getFillReporByListTemplateConTimbro(idControllo, listTemplateVerbaleIspezione, listNomiIspezione, conn, 
					  "logoRegioneLombardia", "/jasper/sources/regione.png", null, "imgTimbroSFR", "/jasper/sources/timbroSFR.png");
	
			  JasperExportManager.exportReportToPdfStream(jPrintVerbale, outputStream);      
		  	  PdfReader dettaglio = new PdfReader(outputStream.toByteArray());
		  	  Document document = new Document();
		  	  PdfCopy copy = new PdfCopy(document, byteArrayOutputStream);
		  	  document.open();
		  	  copy.addDocument(dettaglio);
		  	  
			  document.close();
		  	  dettaglio.close();  	  
		  	  result = byteArrayOutputStream.toByteArray();
	
		         
		
		    } catch (SQLException e) {
		    	throw new BusinessException("Errore nella generazione del report Jasper - Connection error ", e);
		    } finally {
		    	logger.debug("-- END getPdfVerbaleIspezioneControlli Ruop");
		    }
		    return result;	
	}

	// valido sia per il verbale disposizione che constatazione
	public static byte[] getPdfVerbaleMisuraUff(Long idControllo, List<byte[]> listTemplateVerbaleMU, String[] listNomiMU) throws Exception {
	
		  byte[] result = null;
	
		  logger.debug("-- getPdfVerbaleMisuraUff idControllo =" + idControllo);
		
		  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		   
		    
		  try (Connection conn = getConnection(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
	
			  JasperPrint jPrintVerbale = getFillReporByListTemplateConTimbro(idControllo, listTemplateVerbaleMU, listNomiMU, conn, 
					  "logoRegioneLombardia", "/jasper/sources/regione.png", null,"imgTimbroSFR", "/jasper/sources/timbroSFR.png");
	
			  JasperExportManager.exportReportToPdfStream(jPrintVerbale, outputStream);      
		  	  PdfReader dettaglio = new PdfReader(outputStream.toByteArray());
		  	  Document document = new Document();
		  	  PdfCopy copy = new PdfCopy(document, byteArrayOutputStream);
		  	  document.open();
		  	  copy.addDocument(dettaglio);
		  	  
			  document.close();
		  	  dettaglio.close();  	  
		  	  result = byteArrayOutputStream.toByteArray();
	
		         
		
		    } catch (SQLException e) {
		    	throw new BusinessException("Errore nella generazione del report Jasper - Connection error ", e);
		    } finally {
		    	logger.debug("-- END getPdfVerbaleMisuraUff");
		    }
		    return result;	
	}
	
	public static byte[] getPdfDettaglioDomandaPassaporto(Long idDomanda, List<byte[]> listTemplatePassaporto, String[] listNomiPassaporto) throws Exception {

		logger.info("BEGIN getPdfDettaglioDomandaPassaporto");

		byte[] result = null;
		logger.debug("-- getPdfDettaglioDomandaPassaporto idDomanda =" + idDomanda);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try (Connection conn = getConnection(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

			JasperPrint jPrintRuop = getFillReporByListTemplate(idDomanda, listTemplatePassaporto, listNomiPassaporto,
					conn, "logoLombardia", "/jasper/sources/regione.png", null);

			JasperExportManager.exportReportToPdfStream(jPrintRuop, outputStream);
			PdfReader dettaglio = new PdfReader(outputStream.toByteArray());
			Document document = new Document();
			PdfCopy copy = new PdfCopy(document, byteArrayOutputStream);
			document.open();
			copy.addDocument(dettaglio);

			document.close();
			dettaglio.close();
			result = byteArrayOutputStream.toByteArray();

		} catch (Exception e) {
			logger.error("-- Exception in getPdfDettaglioDomandaPassaportos =" + e.getMessage());
			throw new BusinessException("Errore nella generazione del report Jasper - Connection error ", e);
		} finally {
			logger.info("END getPdfDettaglioDomandaPassaporto");
		}
		return result;
	}



}
