package Practice.Questions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ammazon {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.google.com/");
		WebElement searchInput = driver.findElement(By.name("q"));
		searchInput.sendKeys("amazon.in");
		searchInput.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//h3[text()='Amazon.in']")).click();

		driver.findElement(By.xpath("//div[@id='nav-xshop']//a[text()='Mobiles']")).click();
		WebElement laptopNAccesories = driver.findElement(By.xpath("//span[text()[normalize-space()='Laptops & Accessories']]")); 
		// WebElement Samsung =driver.findElement(By.xpath("//div[15]/div[2]/div/div[3]/ul[2]/li[1]/a[text()='Samsung']"));

		Actions act = new Actions(driver);
		act.moveToElement(laptopNAccesories).build().perform();
		WebElement Samsung1 = driver.findElement(By.xpath("(//a[text()='Samsung'])[2]"));

		act.moveToElement(Samsung1).click().build().perform();
		System.out.println("Moved to 'laptop and accessories' and selected 'Samsung'");
		
		List<WebElement> results  = driver.findElements(By.xpath("//div[@class='a-section a-spacing-medium _octopus-search-result-card_style_apbSearchResultsContainer__bCqjb']/div/span/div/div/div/div[1]/h2/a/span"));
		System.out.println(results.size());
		
		boolean AllvaluesAreofSameBrand = false;
		
		for(WebElement brand : results)
		{
			String brandDetails = brand.getText();
			System.out.println(brandDetails);
			
			String[] brandParts = brandDetails.split(" ");
			
			
			if(brandParts[0].equals("Samsung"))
			{		
				AllvaluesAreofSameBrand=true;
			}
		}
		
		if(AllvaluesAreofSameBrand==true)
		{
			System.out.println("yes all brand names started with Samsung");

		}
		System.out.println("Amazon.....");
	}
}

//Actions act = new Actions(driver);
//act.moveToElement(laptopNAccesories).moveToElement(Samsung).click().build().perform();
//System.out.println("moved to laptopn and accessories");
// driver.findElement(By.xpath(//div[@class='mega-menu']//div[2]//ul//li[3]//a[text()='Samsung']
