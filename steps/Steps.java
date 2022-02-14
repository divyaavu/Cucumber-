package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {

	public ChromeDriver driver;
	public WebDriverWait wait;
	public Actions opt;
	public int firstitemprice;
	public String priceofitem1;
	public int priceofsecond;
	
	@Given("Open the ChromeBrowser and load the URL")
	public void openURL() {
		ChromeOptions options = new ChromeOptions();

		//Add chrome switch to disable notification - "**--disable-notifications**"
		options.addArguments("--disable-notifications");

		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","./drivers3/chromedriver.exe");
		driver= new ChromeDriver(options);
		driver.get("https://www.snapdeal.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	@And("Mouse over on Toys, Kids' Fashion & more and click on Toys")
	public void Clicktoys() {
		Actions opt= new Actions(driver);
		WebElement toys = driver.findElementByXPath("(//li[@class='navlink lnHeight'])[3]");
		opt.moveToElement(toys).build().perform();
		
		
	}
	
	@And("Click Educational Toys in Toys & Games")
	public void ClickEducationalToys() {
		driver.findElementByXPath("//span[text()='Educational Toys']").click();
	}
	
    @And("Click the Customer Rating 4 star and Up")
	public void Clickrating() {
	    wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(("//label[@for='avgRating-4.0']")))).click();
        
	}
    
    @And("Click the offer as 40-50")
    public void offer() throws InterruptedException {
    	Thread.sleep(5000);
        driver.findElementByXPath("(//i[@class='sd-icon sd-icon-plus'])[1]").click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(("//label[@for='discount-40%20-%2050']")))).click();
        
    }
    
    @And("Check the availability for the pincode as (.*)")
    public void pincode(String pincode) throws InterruptedException {
    	driver.findElementByXPath("//input[@placeholder='Enter your pincode']").sendKeys(pincode);
    	Thread.sleep(3000);
        driver.findElementByXPath("//button[text()='Check']").click();
        Thread.sleep(5000);
        	
    }
    
    @And("Click the Quick View of the first product")
    public void QuickviewofFirstItem() throws InterruptedException {
    	List<WebElement> images = driver.findElementsByXPath("//div[@class='product-tuple-description ']");
        Thread.sleep(5000);
        WebElement firstelement = images.get(0);
        opt.moveToElement(firstelement).build().perform();
        driver.findElementByXPath("(//div[@class='clearfix row-disc']/div)[1]").click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class=' btn btn-theme-secondary prodDetailBtn']"))).click();
        
    }
    
    @And("Click on View Details")
    public void ClickViewDetails() {
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class=' btn btn-theme-secondary prodDetailBtn']"))).click();
        	
    }
    
    @And("Capture the Price of the Product and Delivery charge")
    public void ProductPrice() {
    	priceofitem1 = driver.findElementByXPath("//span[@class='pdp-final-price']").getText();
        String pr = priceofitem1.replaceAll("[^0-9]","");
        firstitemprice = Integer.parseInt(pr);
        System.out.println("the price of first item is "+ firstitemprice);
      }
    
    @And("Validate the amount")
	public void validateAmount() {
    	 driver.findElementById("add-cart-button-id").click();
         String youpayamount = driver.findElementByXPath("(//span[@class='price'])[2]").getText();
         String youpay = priceofitem1.replaceAll("[^0-9]","");
         int payable = Integer.parseInt(youpay);
         System.out.println("the amount you pay is "+ payable);
         if(firstitemprice==payable)
         {
         	System.out.println("Yes the amount is correct");
         }
         else
         {
         	System.out.println("incorrect amount");
         }
        	
	}
    
    @And("Search for Sanitizer")
    public void SearchSanitizer() {
    	driver.findElementByXPath("//input[@placeholder='Search products & brands']").sendKeys("Sanitizer",Keys.ENTER);
    }
    
    @And("Click on Product Vedic Valley Hand Sanitizer 5000 mL Pack of 1")
    public void chosenSanitizer() {
    	driver.findElementByXPath("(//div[@class='tile-desc marT5'])[3]").click();
        Set<String> allwindows = driver.getWindowHandles();
        List<String> list = new ArrayList<String>(allwindows);
        String currentwindow = list.get(1);
        driver.switchTo().window(currentwindow);
        	
    }
    
    @And("Capture the Price and Delivery Charge")
    public void Price() {
    	String price = driver.findElementByXPath("//span[@class='pdp-final-price']").getText();
        String pr2= price.replaceAll("[^0-9]", "");
        priceofsecond = Integer.parseInt(pr2);
        System.out.println("The price of second item is "+ priceofsecond);
        	
    }
    
    @And("Click on Add to Cart")
    public void AddTocart() throws InterruptedException {
    	driver.findElementByXPath("//span[text()='add to cart']").click();
    	driver.findElementByXPath("//i[@class='sd-icon sd-icon-cart-icon-white-2']").click();
        Thread.sleep(2000);
        
    }
    
    @When("Validate the Proceed to Pay matches the total amount of both the products")
    public void ValidateAmount() {
    	String subtotal = driver.findElementByXPath("//span[@class='rfloat']").getText();
        String subt = subtotal.replaceAll("[^0-9]","");
        int finalvalue = Integer.parseInt(subt);
        System.out.println("The total price which has to be paid "+ finalvalue);
        driver.findElementByXPath("//span[@class='icon-font-grey-size24 close-popup-icon']/i").click();
        if((firstitemprice + priceofsecond)==finalvalue)
        {
        	System.out.println("The amounts are equal");
        }
        else
        {
        	System.out.println("oops something went wrong");
        }
        	
    }
    
    @Then("Close all the windows")
    public void CloseWindows() {
    	driver.quit();	
    }
    

}
