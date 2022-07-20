package sampletestingmaven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browserlaunch {
	
	static List<String> usernameList = new ArrayList<String>();
	static List<String> passwordList = new ArrayList<String>();
	
	

	public void readexcel() throws IOException {

		File f = new File("C:\\Users\\DELL\\Documents\\datadriven.xlsx");

		FileInputStream stream = new FileInputStream(f);

		Workbook book = new XSSFWorkbook(stream);

		Sheet sheet = book.getSheet("Sheet1");

		/*Row row = sheet.getRow(2);

		Cell cell = row.getCell(0);

		String stringcellValue = cell.getStringCellValue();

		System.out.println(stringcellValue);
		
		*/
		
	Iterator<Row> rowsheet	= sheet.iterator();
	
	while(rowsheet.hasNext()) {
		
		Row rowvalue = rowsheet.next();
		
		Iterator<Cell> colmniterator = rowvalue.iterator();
		
		int i=2;
		
		while(colmniterator.hasNext()) {
			
			if(i%2==0) {
				
				usernameList.add(colmniterator.next().getStringCellValue());
			}
			
			else {
				
				passwordList.add(colmniterator.next().getStringCellValue());
			}
			
			i++;
		}
	}
	
	
		
		
		
		

	}

	public void Browlaunch(String Uname, String Upass) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		WebElement username = driver.findElement(By.id("email"));
		username.sendKeys(Uname);
		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys(Upass);
		
		WebElement btnlogin = driver.findElement(By.xpath("//button[@name='login']"));
		btnlogin.click();
		
		Thread.sleep(2000);
		
		driver.close();
		
	}
	
	
	public void execute() throws InterruptedException {
		
		for (int i = 0; i < usernameList.size(); i++) {
			
			Browlaunch(usernameList.get(i),passwordList.get(i));
			
			
		}
		
		
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Browserlaunch brow = new Browserlaunch();
		brow.readexcel();
		System.out.println("Username is" +usernameList);
		System.out.println("Username is" +passwordList);
		brow.execute();
		
		
		
		
			

	}

}
