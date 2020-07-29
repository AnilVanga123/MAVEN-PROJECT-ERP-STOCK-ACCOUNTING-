package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDriven {
	WebDriver driver;
	String input="F:\\December_Selenium\\ERP_MAVEN\\TestInput\\TestData.xlsx";
	String input2="F:\\December_Selenium\\ERP_MAVEN\\TestInput\\TestData2.xlsx";
	String output="F:\\December_Selenium\\ERP_MAVEN\\TestOutput\\DataDriven1.xlsx";
	String output2="F:\\December_Selenium\\ERP_MAVEN\\TestOutput\\DataDriven2.xlsx";
	@BeforeTest
	public void setUp() throws Throwable
	{
		String launch=ERP_Functions.verifyUrl("http://projects.qedgetech.com/webapp");
		Reporter.log(launch,true);
		String loginresults=ERP_Functions.verifyLogin("admin", "master");
		Reporter.log(loginresults,true);

	}
	@Test
	public void supplier()throws Throwable
	{
		ExcelFileUtil xl=new ExcelFileUtil(input);
		int rc=xl.rowCount("supplier");
		Reporter.log("no of rows are::"+rc,true);
		for (int i = 1; i <=rc; i++)
		{
			String sname=xl.getCellData("supplier", i, 0);
			String address=xl.getCellData("Supplier", i, 1);
			String city=xl.getCellData("Supplier", i, 2);
			String country=xl.getCellData("Supplier", i, 3);
			String cperson=xl.getCellData("Supplier", i, 4);
			String pnumber=xl.getCellData("Supplier", i, 5);
			String mail=xl.getCellData("Supplier", i, 6);
			String mnumber=xl.getCellData("Supplier", i, 7);
			String notes=xl.getCellData("Supplier", i, 8);
			String Results=ERP_Functions.verifySupplier(sname, address, city, country, cperson, pnumber, mail, mnumber, notes);
			Reporter.log(Results,true);
			xl.setCellData("supplier", i, 9, Results,output );
		}
	}
	@Test
	public void costumers() throws Throwable{
		ExcelFileUtil xl=new ExcelFileUtil(input2);
		int rc=xl.rowCount("costumers");
		Reporter.log("no of rows are::"+rc,true);
		for (int j = 1; j <=rc; j++)
		{
			String cname=xl.getCellData("costumers", j, 0);
			String address=xl.getCellData("costumers", j, 1);
			String city=xl.getCellData("costumers", j, 2);
			String country=xl.getCellData("costumers", j, 3);
			String cperson=xl.getCellData("costumers", j, 4);
			String pnumber=xl.getCellData("costumers", j, 5);
			String email=xl.getCellData("costumers", j, 6);
			String mnumber=xl.getCellData("costumers", j, 7);
			String notes=xl.getCellData("costumers", j, 8);
			String Results=ERP_Functions.verifySupplier(cname, address, city, country, cperson, pnumber, email, mnumber, notes);
			Reporter.log(Results,true);
			xl.setCellData("costumers", j, 9, Results, output2);

		}
	}	

	@AfterTest
	public void tearDown()
	{
		driver.close();	
	}


}
