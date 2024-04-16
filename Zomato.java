package Practice.Questions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Zomato {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("https://www.zomato.com/chennai");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement locationInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Chennai']")));
		locationInput.click();
		locationInput.sendKeys("Velacherry");

		WebElement velacheryOption = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Velachery')]")));
		velacheryOption.click();

		WebElement restaurant = driver.findElement(By.xpath("//input[@class='sc-fxgLge jUPfKP']"));
		restaurant.click();
		restaurant.sendKeys("A2B-Adyar Ananda Bhavan");

		WebElement A2Boption = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Adyar Ananda Bhavan')]")));
		A2Boption.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)");
		String OpenCloseStaus = driver.findElement(By.xpath("//section[@class='sc-jXQZqI dEfcmc']/span[1]")).getText();
		System.out.println("Restaurant is " + OpenCloseStaus);

		driver.findElement(By.xpath("//a[text()='Order Online']")).click();
		List<WebElement> NoOfVarieties = driver.findElements(By.xpath("//section[@class='sc-enOJk hBoVtV']/p"));
		System.out.println(NoOfVarieties.size());

		// Print the costliest sweet in that page
		driver.findElement(By.xpath("//section[@class='sc-enOJk hBoVtV']//p[contains(text(), 'Sweets')]")).click();
		System.out.println("cliked on sweets");
		Thread.sleep(4000);

		List<WebElement> sweetsNamesNprices = driver.findElements(By.xpath(
				"//section[@class='sc-doidfC hCfpBg'][2]//div[@class='sc-bwNswr bUAylp']/div/div/div/div[2]/div/div/div[2]/span"));
		System.out.println(sweetsNamesNprices.size());
		double highestPrice = 0;

		for (WebElement eachPrice : sweetsNamesNprices) 
		{
			String s = eachPrice.getText();
			String numberString = s.substring(1);
			System.out.println(numberString);
			double price = Double.parseDouble(numberString);

			if (price > highestPrice) {
				highestPrice = price;
			}
		}
		System.out.println("highestPrice :" + highestPrice);

//		Clik photos&validate the no_photos listed matches the total no imagesdisplayed across the listed pages.
//		driver.findElement(By.xpath("//a[text()='Photos']")).click();
//		String AllnoShown = driver
//				.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/section[4]/div/div[1]/div/button[1]/span/span"))
//				.getText();
//		System.out.println("All no os images shown :" + AllnoShown);
//		List<WebElement> noOfImages = driver.findElements(By.xpath("//div[@class='sc-bke1zw-0 fIuLDK']/div/div/img"));
//		System.out.println("Actual no of images : " + noOfImages.size());

		// driver.quit();
	}
}
