package com.hs.loan.finance.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hs.base.exception.ServiceException;
import com.hs.utils.StringUtils;

public class ExportExcelUtil {

	public static void writeListToExcel(List list, String filePath, String serverFilename, String sheetName,
			String columnNames, String columnDisplayNames, boolean addToNewFile) {
		FileOutputStream fileOut;
		if (list == null || StringUtils.isEmpty(filePath) || StringUtils.isEmpty(columnNames))
			throw new ServiceException("U_TL_0000020");
		fileOut = null;
		try {
			File pathFile = new File(filePath);
			boolean isPathExist = pathFile.exists();
			if (!isPathExist)
				pathFile.mkdirs();
			Workbook wb = gainWorkbook(filePath, serverFilename, addToNewFile);
			String namesArray[] = columnNames.split(",");
			Sheet sheet = null;
			if (StringUtils.isEmpty(sheetName)) {
				if (wb.getNumberOfSheets() > 0)
					sheet = wb.getSheetAt(0);
				else
					sheetName = "Sheet1";
			} else {
				sheet = wb.getSheet(sheetName);
			}
			if (sheet == null) {
				sheet = wb.createSheet(sheetName);
				if (!StringUtils.isEmpty(columnDisplayNames)) {
					Row row = sheet.createRow(0);
					String displayNamesArray[] = columnDisplayNames.split(",");
					for (int i = 0; i < displayNamesArray.length; i++)
						row.createCell(i).setCellValue(displayNamesArray[i]);

				}
			}
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 0; i < list.size(); i++) {
				Map recordMap = (Map) list.get(i);
				Row rowContent = sheet.createRow(i + lastRowNum);
				for (int j = 0; j < namesArray.length; j++) {
					Object obj = recordMap.get(namesArray[j]);
					Cell cell = rowContent.createCell(j);
					if (obj instanceof String) {
						cell.setCellType(1);
						cell.setCellValue((String) obj);
						continue;
					}
					if (obj instanceof Integer) {
						cell.setCellType(0);
						cell.setCellValue(((Integer) obj).doubleValue());
						continue;
					}
					if (obj instanceof Timestamp) {
						cell.setCellType(0);
						cell.setCellValue((Timestamp) obj);
						continue;
					}
					if (obj instanceof BigDecimal) {
						cell.setCellType(0);
						cell.setCellValue(((BigDecimal) obj).doubleValue());
						continue;
					}
					if (obj instanceof Double) {
						cell.setCellType(0);
						cell.setCellValue(((Double) obj).doubleValue());
						continue;
					}
					if (obj instanceof Float) {
						cell.setCellType(0);
						cell.setCellValue(((Float) obj).doubleValue());
						continue;
					}
					if (obj instanceof Long) {
						cell.setCellType(0);
						cell.setCellValue(((Long) obj).doubleValue());
						continue;
					}
					if (obj instanceof Short) {
						cell.setCellType(0);
						cell.setCellValue(((Short) obj).doubleValue());
						continue;
					}
					if (obj instanceof Date) {
						cell.setCellType(0);
						cell.setCellValue((Date) obj);
						continue;
					}
					if (obj instanceof Boolean) {
						cell.setCellType(4);
						cell.setCellValue(((Boolean) obj).booleanValue());
					} else {
						cell.setCellType(1);
						cell.setCellValue((String) obj);
					}
				}

			}

			fileOut = new FileOutputStream((new StringBuilder()).append(filePath).append(serverFilename).toString());
			wb.write(fileOut);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		} finally {
		}
		if (fileOut != null)
			try {
				fileOut.close();
			} catch (IOException e) {
			}
		try {
			fileOut.close();
		} catch (IOException e) {
		}
	}

	private static Workbook gainWorkbook(String filePath, String serverFilename, boolean addToNewFile)
			throws Exception {
		boolean is2003Plus = true;
		if (serverFilename.endsWith(".xlsx"))
			is2003Plus = true;
		else if (serverFilename.endsWith(".xls"))
			is2003Plus = false;
		Workbook wb;
		if (is2003Plus)
			wb = new SXSSFWorkbook(100);
		else
			wb = new HSSFWorkbook();
		return wb;
	}

}
