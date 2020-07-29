package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {
	String inputpath="F:\\December_Selenium\\ERP_MAVEN\\TestInput\\InputSheet.xlsx";
	String outputpath="F:\\December_Selenium\\ERP_MAVEN\\TestOutput\\hybridOutput.xlsx";
	WebDriver driver;
	ExtentReports reports;
	ExtentTest test;
	public void startTest() throws Throwable{
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		for (int i = 1; i <=xl.rowCount("MasterTestCases"); i++) 
		{
			if (xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule=xl.getCellData("MasterTestCases", i, 1);
				reports=new ExtentReports("./ExtentReports/"+TCModule+FunctionLibrary.generateDate()+".html",true);
				for(int j = 1;j<=xl.rowCount(TCModule);j++)
				{
					test=reports.startTest(TCModule);
					test.assignAuthor("Ranga QA Manager");
					test.assignCategory(TCModule);
					String Description=xl.getCellData(TCModule, j, 0);
					String Function_Name=xl.getCellData(TCModule, j, 1);
					String Locator_Type=xl.getCellData(TCModule, j, 2);
					String Locator_Value=xl.getCellData(TCModule, j, 3);
					String Test_Data=xl.getCellData(TCModule, j, 4);
					try{
						if (Function_Name.equalsIgnoreCase("startBrowser")) 
						{
							driver=FunctionLibrary.startBrowser();
							test.log(LogStatus.INFO, Description);
						}
						else if (Function_Name.equalsIgnoreCase("openApplication"))
						{
							FunctionLibrary.openApplication(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if (Function_Name.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, Locator_Type	,Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);

						}
						else if (Function_Name.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);	
							test.log(LogStatus.INFO, Description);
						}
						else if (Function_Name.equalsIgnoreCase("clickAction")) 
						{
							FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if (Function_Name.equalsIgnoreCase("closeBrowser")) 
						{
							FunctionLibrary.closeBrowser(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("captureData"))
						{
							FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("tableValidation"))
						{
							FunctionLibrary.tableValidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("stockCategories"))
						{
							FunctionLibrary.stockCategories(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("stockValidation"))
						{
						FunctionLibrary.stockValidation(driver, Test_Data);	
						test.log(LogStatus.INFO, Description);
						}
						xl.setCellData(TCModule, j, 5, "Pass", outputpath);
						xl.setCellData("MasterTestCases", i, 3, "Pass", outputpath);

					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
						xl.setCellData(TCModule, j, 5, "Fail", outputpath);
						test.log(LogStatus.PASS, Description);
						File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(screen, new File("./Screens/"+TCModule+FunctionLibrary.generateDate()+".png"));
						xl.setCellData("MasterTestCases", i, 3, "Fail", outputpath);
					}
				reports.endTest(test);
				reports.flush();
				}	
			}
			else
			{
				xl.setCellData("MasterTestCases", i, 3, "Not Executed", outputpath);
			}
		}


	}
}
