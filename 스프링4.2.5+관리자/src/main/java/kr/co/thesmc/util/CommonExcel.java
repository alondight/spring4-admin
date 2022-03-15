package kr.co.thesmc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonExcel {
	private String file;

	private List<String> headers = new LinkedList<String>();
	private List<Map<String,String>> data = new LinkedList<Map<String,String>>();

	private XSSFWorkbook book;

	public CommonExcel() {
	}

	public CommonExcel( String file ) {
		this.setFile(file);
	}

	public CommonExcel load() {
		try {
			File file = new File(this.file);
			book = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = book.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();

			if ( ! itr.hasNext() ) {
				return this;
			}
			TreeMap<String,String> dataRow;

			int rowId = 0;
			for (Row row : sheet) {
				if ( ++ rowId == 1 ) {
					this.setHeaders( row );
					continue;
				}
				int i = 0;
				dataRow = new TreeMap<String,String>();
				for (int cn = 0; cn < row.getLastCellNum(); cn++) {
					Cell cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
					dataRow.put(this.headers.get(i++), this.getCellValue( cell ));
				}
				this.data.add(dataRow);
			}
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return this;
	}

	public CommonExcel load( String file ) {
		this.setFile( file );
		return this.load();
	}

	private String getCellValue( Cell cell ) {
		int cellType = cell.getCellType();
		if ( cellType == Cell.CELL_TYPE_FORMULA ) {
			return cell.getCellFormula();
		} else if (cellType == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cellType == Cell.CELL_TYPE_NUMERIC) {
			if ( DateUtil.isCellDateFormatted(cell) ) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df.format( cell.getDateCellValue() );
			} else {
				return String.valueOf(cell.getNumericCellValue());
			}
		} else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		}
		return "";
	}

	public List<String> getHeaders() {
		return this.headers;
	}

	private CommonExcel setHeaders(Row row) {
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			String header = getCellValue( cell );
			this.headers.add( header );
		}
		return this;
	}

	public CommonExcel setHeaders(List<String> headers) {
		this.headers = headers;
		return this;
	}

	public CommonExcel setHeaders(Set<String> headers) {
		this.headers.clear();
		for( String header: headers ) {
			this.headers.add(header);
		}
		return this;
	}

	public void download( OutputStream out ) {
		try {
			book = this.getBook();
			book.write(out);
			book.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	private XSSFWorkbook getBook() {
		XSSFWorkbook book = new XSSFWorkbook();
		book.createSheet();
		XSSFSheet sheet = book.getSheetAt(0);

		int rownum = sheet.getLastRowNum();
		Row row = sheet.createRow(rownum++);
		this.addColumnData( row, this.getHeaders() );
		
		for (Map<String, String> rowData : this.data) {
			row = sheet.createRow(rownum++);
			this.addColumnData( row, rowData.values() );
		}
		return book;
	}

	private void addColumnData( Row row, Collection<String> rowData ) {
		int cellnum = 0;
		for (Object obj : rowData) {
			Cell cell = row.createCell(cellnum++);
			if (obj instanceof String) {
				cell.setCellValue((String) obj);
			} else if (obj instanceof Boolean) {
				cell.setCellValue((Boolean) obj);
			} else if (obj instanceof Date) {
				cell.setCellValue((Date) obj);
			} else if (obj instanceof Double) {
				cell.setCellValue((Double) obj);
			}
		}
	}

	public String getFile() {
		return file;
	}

	public CommonExcel setFile(String file) {
		this.file = file;
		return this;
	}

	public List<Map<String,String>> getData() {
		return this.data;
	}

	public void setData(List<Map<String,String>> data) {
		this.data = data;
	}

	public int getRows() {
		return this.data.size();
	}

}