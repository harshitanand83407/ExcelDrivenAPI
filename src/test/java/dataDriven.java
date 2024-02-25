import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class dataDriven {
	
	//identify testcases column by scanning the entire 1st row
	// once column is identified then scan entire testcase column to identify purchase testcase row
	//after you grab purchase tescase row = pull all the data of that row and feed into test

	//public static void main(String[] args) throws FileNotFoundException {
	public ArrayList<String> getData(String testcaseName,String sheetName) throws IOException
	{
		ArrayList<String> a=new ArrayList<String>();
		FileInputStream fis= new FileInputStream("C://Users//hanand//Documents//ExcelAPI.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        
        for (int i=0;i<sheets;i++)
        {
        	if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
        	{
        		XSSFSheet sheet = workbook.getSheetAt(i);
        		Iterator<Row> rows = sheet.iterator();//sheet is collection of rows
        		Row firstrow = rows.next();
        		Iterator<Cell> ce= firstrow.cellIterator();//row is collection of cells
        		int k=0;
        		int column=0;
        		while(ce.hasNext())
        		{
        			Cell value = ce.next();
        			if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
        			{
        				column=k;
        			}
        			k++;	
        		}
        		
        		System.out.println(column);
   
        		while(rows.hasNext())
        		{
        			Row r = rows.next();
        			if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
        			{
        			
        			  Iterator<Cell> cv=r.cellIterator();
        			  while(cv.hasNext())
						{
						Cell c=	cv.next();
						if(c.getCellType()==CellType.STRING)
						{
							
						a.add(c.getStringCellValue());
						}
						else{
							
							a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
						
						}
						}
        			}
        			
        		}
        		
        		
        	}
        
     
        }
        return a;
	}
	@Test
	public void excelDriven() throws IOException {
	
		
		
		// TODO Auto-generated method stub
		// FileInputStream arguement
		

        
	}
	

}

