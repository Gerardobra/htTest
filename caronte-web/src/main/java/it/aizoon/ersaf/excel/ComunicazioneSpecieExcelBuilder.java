package it.aizoon.ersaf.excel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import it.aizoon.ersaf.dto.ComunicazioneDto;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.SpecieDTO;

/**
 * @author Ivan Morra
 */
public class ComunicazioneSpecieExcelBuilder extends AbstractXlsView {

  @Override
  protected void buildExcelDocument(Map<String, Object> model, Workbook workbookParam, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    ComunicazioneDto datiComunicazione = (ComunicazioneDto) model.get("datiComunicazione");
    HSSFWorkbook workbook = (HSSFWorkbook)workbookParam;
    HSSFSheet sheet = workbook.createSheet("Dati specie");
    HSSFRow row = null;
    HSSFCell cell = null;
    int rowNumber = 0;
    
    Font font = workbook.createFont();
    Font boldFont = workbook.createFont();
    Font whiteFont = workbook.createFont();
    HSSFPalette palette = workbook.getCustomPalette();
    HSSFCellStyle titleRowStyle = workbook.createCellStyle();
    HSSFCellStyle columnHeaderStyle = workbook.createCellStyle();
    HSSFCellStyle cellStyle = workbook.createCellStyle();
    
    font.setFontName("Arial");
    boldFont.setFontName("Arial");
    boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    whiteFont.setFontName("Arial");
    whiteFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    whiteFont.setColor(HSSFColor.WHITE.index);
    
    palette.setColorAtIndex((short) 57, (byte) 66, (byte) 139, (byte) 202); // color #428BCA
    palette.setColorAtIndex((short) 58, (byte) 211, (byte) 211, (byte) 211); // color #F9F9F9
    
    columnHeaderStyle.setFillForegroundColor(palette.getColor(57).getIndex());
    columnHeaderStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    columnHeaderStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
    columnHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
    columnHeaderStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
    columnHeaderStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
    columnHeaderStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
    columnHeaderStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
    columnHeaderStyle.setFont(whiteFont);
    
    titleRowStyle.setFont(boldFont);
    
    cellStyle.setWrapText(true);
    cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
    cellStyle.setBorderTop(CellStyle.BORDER_THIN);
    cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
    cellStyle.setBorderRight(CellStyle.BORDER_THIN);
    cellStyle.setFont(font);
    
    response.setHeader("Content-Disposition",
        "attachment; filename=comunicazione_specie_" + datiComunicazione.getIdComunicazione() + ".xls");

    row = sheet.createRow(rowNumber++);
    cell = row.createCell(0);
    cell.setCellValue("Spedizioniere");
    cell.setCellStyle(titleRowStyle);
    cell = row.createCell(1);
    cell.setCellValue(datiComunicazione.getDenomSpedizioniere());
    cell.setCellStyle(titleRowStyle);
    
    row = sheet.createRow(rowNumber++);
    cell = row.createCell(0);
    cell.setCellValue("Centro Aziendale");
    cell.setCellStyle(titleRowStyle);
    cell = row.createCell(1);
    cell.setCellValue(datiComunicazione.getCentroAziendale().getNomeIndirizzo());
    cell.setCellStyle(titleRowStyle);
    
    row = sheet.createRow(rowNumber++);
    cell = row.createCell(0);
    cell.setCellValue("Codice Ruop");
    cell.setCellStyle(titleRowStyle);
    cell = row.createCell(1);
    cell.setCellValue(datiComunicazione.getCodiceRuop());
    cell.setCellStyle(titleRowStyle);
    
    
    sheet.createRow(rowNumber++);
    
    row = sheet.createRow(rowNumber++);
    
    cell = row.createCell(0);
    cell.setCellValue("Genere");
    cell.setCellStyle(columnHeaderStyle);
    
    cell = row.createCell(1);
    cell.setCellValue("Specie");
    cell.setCellStyle(columnHeaderStyle);
    
    cell = row.createCell(2);
    cell.setCellValue("Numero piante");
    cell.setCellStyle(columnHeaderStyle);
    
    //sheet.addMergedRegion(new CellRangeAddress(rowNumber-1, rowNumber-1, 0, 1));
    
    sheet.autoSizeColumn(0);
    sheet.autoSizeColumn(1);
    
    
    if (datiComunicazione.getListaGeneri() != null) {
      logger.debug("Ci sono dei generi e delle specie da visualizzare");
      for (GenereSpecieDTO genere : datiComunicazione.getListaGeneri()) {
        
        row = sheet.createRow(rowNumber++);
        cell = row.createCell(0);
        cell.setCellValue(genere.getDenomGenere());
        cell.setCellStyle(cellStyle);
        
       // if (genere.getListaSpecie() != null && genere.getListaSpecie().size() > 0) {
          int numSpecie = 0;
        
         // for (SpecieDTO specie : genere.getListaSpecie()) {
           // if (numSpecie > 0) {
              //row = sheet.createRow(rowNumber++);
            //}
            
            cell = row.createCell(1);
            cell.setCellValue(genere.getDenomSpecie());
            
            cell = row.createCell(2);
            cell.setCellValue(genere.getNumeroPiante());
            
            cell.setCellStyle(cellStyle);
            numSpecie++;
          //}
          
          sheet.addMergedRegion(new CellRangeAddress(rowNumber - numSpecie, rowNumber-1, 0, 0));         
        }
      //}
    }
  }

}
