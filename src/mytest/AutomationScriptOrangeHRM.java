package mytest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Anuj_Manwatkar_Project1_OrangeHRM

public class AutomationScriptOrangeHRM {
	WebDriver driver;
	
	
	@BeforeClass 
	public void setUp() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	@Test(priority=1)
	public void verifyLogin() throws InterruptedException{
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
	}
	
	@Test(priority=2)
	public void addEmployee() throws InterruptedException {
		driver.findElement(By.linkText("PIM")).click();
		Thread.sleep(2000);
        driver.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Anuj");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).sendKeys("Chandrashekhar");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Manwatkar");
        Thread.sleep(2000);
        Actions act=new Actions(driver);
        act.keyDown(Keys.TAB).keyUp(Keys.TAB).build().perform();
        driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--focus']")).sendKeys("01234");
        
        // Using JavaScript Click to ensure saving
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@type='submit']")));

        //driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	@Test (priority=3)
	public void searchEmployee() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
		driver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[1]")).sendKeys("");
		Thread.sleep(3000);
		Actions act=new Actions(driver);
        act.keyDown(Keys.TAB).keyUp(Keys.TAB).build().perform();
		driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--focus']")).sendKeys("01234");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		// Explicit wait to ensure data is stored before searching 
		Thread.sleep(5000); // Give time for the data to be stored
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(3000);
		Thread.sleep(3000);
	}
	
	
	
	@Test (priority=4)
	public void logout() throws InterruptedException {
		driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		Thread.sleep(3000);
	} 
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		
	}

}