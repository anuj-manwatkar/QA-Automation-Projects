package mytest2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
//Anuj_Manwatkar_Project2_SauceDemo

public class AutomationScriptSauceDemo {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the website
        driver.get("https://www.saucedemo.com/v1/index.html");
    }

    @Test(priority = 1)
    public void testLogin() throws InterruptedException {
        // Perform login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(3000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void testSortProducts() throws InterruptedException {
        // Sort products by "Price (low to high)"
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        sortDropdown.click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//option[@value='lohi']")).click();
        Thread.sleep(3000);
    }

    @Test(priority = 3)
    public void testAddToCart() throws InterruptedException {
    	// Add all products to the cart
        List<WebElement> addToCartButtons = driver.findElements(By.className("btn_inventory"));
        
        for (WebElement button : addToCartButtons) {
            button.click();
            Thread.sleep(3000);
            
          // Scroll down slightly to ensure the next "Add to Cart" button is visible and clickable.
            JavascriptExecutor js=(JavascriptExecutor) driver;
    		js.executeScript("window.scrollBy(0,50)");
    		Thread.sleep(3000);		
    	}
    }
   
    @Test(priority = 4)
    public void testCheckout() throws InterruptedException {
    	// Scroll up slightly to ensure the next "Shopping Cart Badge" button is visible and clickable.
        JavascriptExecutor js1=(JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,-400)");
		Thread.sleep(3000);	
		
		// Click on the "Shopping Cart" button to navigate to the cart page.
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(3000);
        
        // Scroll down to ensure the "Checkout" button is visible and clickable.
        JavascriptExecutor js2=(JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,800)");
		Thread.sleep(3000);	

		// Click the "Checkout" button to proceed to the checkout page.
        driver.findElement(By.xpath("//a[@class='btn_action checkout_button']")).click();
        Thread.sleep(3000);
        
        // Fill in the checkout information (First Name, Last Name, Postal Code).
        driver.findElement(By.id("first-name")).sendKeys("Anuj");
        Thread.sleep(2000);
        driver.findElement(By.id("last-name")).sendKeys("Manwatkar");
        Thread.sleep(2000);
        driver.findElement(By.id("postal-code")).sendKeys("441102");
        Thread.sleep(2000);
        
        // Click the "Continue" button to proceed to the next step.
        driver.findElement(By.xpath("//input[@value='CONTINUE']")).click();
        Thread.sleep(3000);

        // Scroll down to ensure the "Finish" button is visible and clickable.
        JavascriptExecutor js3=(JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0,900)");
		Thread.sleep(3000);

		// Click the "Finish" button to complete the checkout process.
        driver.findElement(By.xpath("//a[@class='btn_action cart_button']")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 5)
    public void testLogout() throws InterruptedException {
    	// Open the menu to access logout options.
    	driver.findElement(By.xpath("//button[normalize-space()='Open Menu']")).click();
        Thread.sleep(3000);
        
        // Click the "Logout" link to log out of the application.
        driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown() {
    	// Close the browser and end the WebDriver session.
        driver.quit();
    }
}
