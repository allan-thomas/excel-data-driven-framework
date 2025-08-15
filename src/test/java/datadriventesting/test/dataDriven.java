package datadriventesting.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	// step1- Identify TestCases column
	// step 2 - Scan the entire column for "Purchase"
	// step 3 get the full data in "Purchase" row

	public ArrayList<String> getData(String testCaseName) throws IOException {

		FileInputStream file = new FileInputStream(
				"C:\\Users\\167557\\Documents\\Excel Data driven Testing\\Excel Data driven Testing.xlsx");

		ArrayList<String> a = new ArrayList<String>();

		XSSFWorkbook workbook = new XSSFWorkbook(file);

		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata1")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// step1- Identify TestCases column
				Iterator<Row> rows = sheet.iterator();
				Row firstrow = rows.next(); // 1st row in the excel is selected
				Iterator<Cell> ce = firstrow.cellIterator();
				int k = 0, column = 0;
				while (ce.hasNext()) {
					Cell value = ce.next(); // 1st row cell is selected and to be checked for Purchase
					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						// desire column
						column = k;
					}
					k++;
				}

				// step 2 - Scan the entire column for "Purchase"
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						// step 3 get the full data in "Purchase" row
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell value = cv.next();
							if (value.getCellType() == CellType.STRING)
								a.add(value.getStringCellValue());
							else if(value.getCellType() == CellType.NUMERIC)
								a.add(NumberToTextConverter.toText(value.getNumericCellValue()));

						}
					}
				}

			}

		}
		return a;

	}
}