package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERP_Functions {
	public  static WebDriver driver;
	public static String verifyUrl(String url)throws Throwable
	{
		String res=null;
		System.setProperty("webdriver.chrome.driver", "F:\\December_Selenium\\ERP_MAVEN\\CommonDrivers\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(4000);
		if(driver.findElement(By.name("btnsubmit")).isDisplayed())
		{
			res="Application Launch Successfully";

		}
		else
		{
			res="Application Launch Fail";
		}
		return res;
	}
	public static String verifyLogin(String username,String password)throws Throwable
	{
		String res=null;
		WebElement user=driver.findElement(By.name("username"));
		user.clear();
		user.sendKeys(username);
		WebElement pass=driver.findElement(By.name("password"));
		pass.clear();
		pass.sendKeys(password);
		driver.findElement(By.name("btnsubmit")).click();
		if (driver.findElement(By.id("mi_logout")).isDisplayed()) 
		{
			res="Login Success";
		}
		else
		{
			res="Login Fail";	
		}
		return res;
	}
	public static String verifySupplier(String sname,String address,String city,String county,String cperson,
			String pnumber,String mail,String mnumber,String notes) throws Throwable{
		String res=null;
		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[3]/a[1]")).click();
		Thread.sleep(4000);

		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
		Thread.sleep(4000);
		String expected=driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
		driver.findElement(By.name("x_Supplier_Name")).sendKeys(sname);
		driver.findElement(By.name("x_Address")).sendKeys(address);
		driver.findElement(By.name("x_City")).sendKeys(city);
		driver.findElement(By.name("x_Country")).sendKeys(county);
		driver.findElement(By.name("x_Contact_Person")).sendKeys(cperson);
		driver.findElement(By.name("x_Phone_Number")).sendKeys(pnumber);
		driver.findElement(By.name("x__Email")).sendKeys(mail);
		driver.findElement(By.name("x_Mobile_Number")).sendKeys(mnumber);
		driver.findElement(By.name("x_Notes")).sendKeys(notes);
		driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[17]/div[2]/div[1]/div[4]/div[2]/button[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[17]/div[2]/div[1]/div[4]/div[2]/button[1]")).click();
		if (!driver.findElement(By.id("psearch")).isDisplayed())
			driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("psearch")).clear();
		driver.findElement(By.id("psearch")).sendKeys(expected);
		driver.findElement(By.name("btnsubmit")).click();
		Thread.sleep(4000);
		String actual=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
		if (actual.equals(expected)) 
		{
			res="pass";
			System.out.println(expected+"   "+actual);
		}
		else
		{
			res="fail";
			System.out.println(expected+"   "+actual);


		}
		return res;
	} 
	public static String verifyCostumers(String cname, String address, String city, String country,String cperson, 
			String pnumber,String email,String mnumber,String notes) throws Throwable {
		String res2=null;
		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[5]/a[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
		Thread.sleep(4000);
		String expected=driver.findElement(By.name("x_Customer_Number")).getAttribute("value");
		driver.findElement(By.name("x_Customer_Name")).sendKeys(cname);
		driver.findElement(By.name("x_Address")).sendKeys(address);
		driver.findElement(By.name("x_City")).sendKeys(city);
		driver.findElement(By.name("x_Country")).sendKeys(country);
		driver.findElement(By.name("x_Contact_Person")).sendKeys(cperson);
		driver.findElement(By.name("x_Phone_Number")).sendKeys(pnumber);
		driver.findElement(By.name("x__Email")).sendKeys(email);
		driver.findElement(By.name("x_Mobile_Number")).sendKeys(mnumber);
		driver.findElement(By.name("x_Notes")).sendKeys(notes);
		driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[19]/div[2]/div[1]/div[4]/div[2]/button[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[17]/div[2]/div[1]/div[4]/div[2]/button[1]")).click();
		Thread.sleep(4000);
		if (!driver.findElement(By.id("psearch")).isDisplayed())
			driver.findElement(By.xpath("")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("psearch")).clear();
		driver.findElement(By.id("psearch")).sendKeys(expected);
		driver.findElement(By.name("btnsubmit")).click();
		Thread.sleep(4000);
		String actual=driver.findElement(By.xpath("//table[@id='tbl_a_customerslist']/tbody/tr[1]/td[5]/div/span/span")).getText();
		if (actual.equals(expected))
		{
			res2 ="pass";
			System.out.println(expected+"  "+actual);
		}
		else
		{
			res2 ="fail";
			System.out.println(expected+"   "+actual);
		}

		return res2;
	}
}
