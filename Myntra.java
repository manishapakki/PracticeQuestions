package Practice.Questions;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.myntra.com/");
		Thread.sleep(2000);

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Beauty']")))
				.moveToElement(driver.findElement(
						By.xpath("//li[@data-reactid='742']//li[@data-reactid='765']//a[text()='Perfume']")))
				.click().build().perform();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Perfumes for Men & Women']")).isDisplayed(),
				true);

		System.out.println("yes navigated to perfumes page");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='gender-list']/li[1]/label")).click();
		System.out.println("Slelected men");
		
		Thread.sleep(4000);

		List<WebElement> brands = driver.findElements(By.xpath("//div[@class='vertical-filters-filters brand-container']/ul/li"));
		System.out.println("NoOfBrands visible " + brands.size());

		int extractedNumber = 0;
		String brandText = null;
		
		for (WebElement brand : brands) 
		{
			brandText = brand.getText();
			System.out.println(brandText);

			Pattern pattern = Pattern.compile("\\d+");
			Matcher matcher = pattern.matcher(brandText);
			while (matcher.find()) 
			{
				String number = matcher.group();
				extractedNumber = Integer.parseInt(number);
				System.out.println(extractedNumber);
			}
			if(extractedNumber<50)
			{
				System.out.println("count under 50 ........" + brandText);
			}
		}
		
		System.out.println("Myntra...");
	}
}


