package it.aizoon.ersaf.excel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import it.aizoon.ersaf.dto.BaseDto;

public abstract class BaseExcelBuilder extends AbstractXlsView {

  public static final String EURO_PATTERN = "###0.00";
  
  public static final String LONG_PATTERN = "###0";
  
	protected List<ExcelSheet> pagineExcel = null;
	protected String fileName;
	protected String codTipoRichiesta;
	protected Map<String, Object> model;

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		 * Mi sembra l'ideale per fare in modo che si possa mettere (e recuperare) nel model tutto
		 * quello che può servire per le specifiche stampe caso per caso. Cioè quando i
		 * dati da stampare non sono presenti in una sola List
		 */
		this.model = model;

		pagineExcel = new ArrayList<>();
		fileName = "";
		codTipoRichiesta = (String) model.get("codTipoRichiesta");
		setPagineExcel((List<BaseDto>) model.get("elenco"));
		setFileName();

		if (fileName == null || fileName.trim().equals("")) {
			fileName = "documentoExcel";
		}
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");

		// create style for header cells
		HSSFCellStyle style = ((HSSFWorkbook) workbook).createCellStyle();
		HSSFPalette palette = ((HSSFWorkbook) workbook).getCustomPalette();
		final DataFormat dataFormat = workbook.createDataFormat();
		
		palette.setColorAtIndex((short) 57, (byte) 66, (byte) 139, (byte) 202); // color #428BCA
		palette.setColorAtIndex((short) 58, (byte) 211, (byte) 211, (byte) 211); // color #F9F9F9
		style.setFillForegroundColor(palette.getColor(57).getIndex());
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setBorderBottom(CellStyle.BORDER_MEDIUM);
		style.setBorderTop(CellStyle.BORDER_MEDIUM);
		style.setBorderLeft(CellStyle.BORDER_MEDIUM);
		style.setBorderRight(CellStyle.BORDER_MEDIUM);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		
		// CELL STYLE GENERICO, DI DEFAULT
		HSSFCellStyle styleValDef = ((HSSFWorkbook) workbook).createCellStyle();
		font = workbook.createFont();
		font.setFontName("Arial");
		styleValDef.setWrapText(true);
		styleValDef.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		styleValDef.setBorderBottom(CellStyle.BORDER_THIN);
		styleValDef.setBorderTop(CellStyle.BORDER_THIN);
		styleValDef.setBorderLeft(CellStyle.BORDER_THIN);
		styleValDef.setBorderRight(CellStyle.BORDER_THIN);
		font.setColor(HSSFColor.BLACK.index);
		styleValDef.setFont(font);
		
		// CELL STYLE PER I VALORI NUMERICI
		HSSFCellStyle styleValNumeric = ((HSSFWorkbook) workbook).createCellStyle();
		styleValNumeric.cloneStyleFrom(styleValDef);
		styleValNumeric.setDataFormat(dataFormat.getFormat(EURO_PATTERN));
		
		// CELL STYLE PER I VALORI LONG
		HSSFCellStyle styleValLong = ((HSSFWorkbook) workbook).createCellStyle();
		styleValLong.cloneStyleFrom(styleValDef);
		styleValLong.setDataFormat(dataFormat.getFormat(LONG_PATTERN));
    
    
    
    
		for (ExcelSheet excelSheet : pagineExcel) {
			List<String> headers = excelSheet.getHeaders();
			ArrayList<List<Object>> listaValori = excelSheet.getListaValori();
			String excelSheetTitle = excelSheet.getSheetTitle();

			// create a new Excel sheet
			Sheet sheet = workbook.createSheet(excelSheetTitle);
			sheet.setDefaultColumnWidth(45);

			// create header row
			int col = -1;
			Row header = sheet.createRow(0);

			for (String chiave : headers) {
				col++;
				header.createCell(col).setCellValue(chiave);
				header.getCell(col).setCellStyle(style);
			}

			// create data rows
			int rowCount = 1;
			int countCol = -1;
			for (List<Object> riga : listaValori) {
				countCol = -1;
				Row aRow = sheet.createRow(rowCount++);
				for (Object colonna : riga) {
					countCol++;
					
					if (colonna instanceof BigDecimal) {
					  final Cell cell = aRow.createCell(countCol);
					  
					  if (colonna != null) {
					    cell.setCellValue(((BigDecimal)colonna).doubleValue());
					  }				    
				      cell.setCellStyle(styleValNumeric);
					}
					else if (colonna instanceof String) {
					  aRow.createCell(countCol).setCellValue(colonna != null ? (String)colonna : "");
	                  aRow.getCell(countCol).setCellStyle(styleValDef);
					}
					else if(colonna instanceof Long){
						final Cell cell = aRow.createCell(countCol);
						if (colonna != null) {
						      cell.setCellValue(((Long)colonna));
						}
						cell.setCellStyle(styleValLong);
					}
				}
			}

			// Setto l'auto-size per ogni colonna dell'header. L'impostazione vale per tutte
			// le righe
			Row row = workbook.getSheetAt(0).getRow(0);
			for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
				sheet.autoSizeColumn(colNum);
			}

		}

	}

	protected abstract <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items);

	protected abstract void setFileName();

	protected void addExcelSheet(String sheetTitle, List<String> headers, ArrayList<List<Object>> listaValori) {
		ExcelSheet sheet = new ExcelSheet();
		sheet.setSheetTitle(sheetTitle != null && !sheetTitle.isEmpty() ? sheetTitle : "Nuova_pagina");
		sheet.setHeaders(headers);
		sheet.setListaValori(listaValori);
		pagineExcel.add(sheet);
	}
	
}
