package it.aizoon.ersaf.excel;

import java.util.ArrayList;
import java.util.List;

public class ExcelSheet {

	private String sheetTitle;
	private List<String> headers;
	private ArrayList<List<Object>> listaValori;

	public String getSheetTitle() {
		return sheetTitle;
	}

	public void setSheetTitle(String sheetTitle) {
		this.sheetTitle = sheetTitle;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public ArrayList<List<Object>> getListaValori() {
		return listaValori;
	}

	public void setListaValori(ArrayList<List<Object>> listaValori) {
		this.listaValori = listaValori;
	}

}
