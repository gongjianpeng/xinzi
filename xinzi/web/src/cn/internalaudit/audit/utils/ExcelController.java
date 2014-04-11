/**
 * 
 */
package cn.internalaudit.audit.utils;

import java.io.UnsupportedEncodingException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;

@Controller
public class ExcelController {

	public HSSFWorkbook createHSSFWorkbook(String entityName)
			throws UnsupportedEncodingException {

		String sheetName = entityName;   // 
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		sheet.setColumnWidth(0, 5000);
		sheet.setForceFormulaRecalculation(true);
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);

//		 cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
////		
//		 HSSFCellStyle cellStyle2 = wb.createCellStyle();
//		 HSSFDataFormat format = wb.createDataFormat();
//		 cellStyle2.setDataFormat(format.getFormat("(1234.76543210)"));
//		 cellStyle2.set
//		 cell.setCellStyle(cellStyle2);
		 sheet.autoSizeColumn(( short ) 0 );   
		 cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
//		 cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
		 
		 HSSFDataFormat doubleFormat = wb.createDataFormat();
//		 short doubleFormat = HSSFDataFormat.getFormat("0.00000000"); 
		 HSSFCellStyle doubleCellStyle = wb.createCellStyle(); 
		 doubleCellStyle.setDataFormat(doubleFormat.getFormat("0.00000000")); 
	
		 
	
		 cell.setCellStyle(doubleCellStyle); 		
		 
		cell
				.setCellFormula("Rate(24,-(((35000.00*0.009*24)+35000.00)/24),35000.00,0)");
		return wb;
	}

}
