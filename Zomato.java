package Practice.Questions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
		System.out.println("\ncliked on sweets");
		Thread.sleep(4000);

		List<WebElement> sweetsNamesNprices = driver
				.findElements(By.xpath("//h4[text()='Sweets']/..//span[contains(text(),'₹')]"));
		List<WebElement> sweetsNames = driver.findElements(
				By.xpath("//h4[text()='Sweets']/..//span[contains(text(),'₹')]/../preceding-sibling::h4[1]"));

		System.out.println(sweetsNamesNprices.size());
		System.out.println(sweetsNames.size());

		Map<String, Double> sweetsMap = new HashMap<>();

		// Find the highest price
		double highestPrice = 0;

		for (int i = 0; i < sweetsNames.size(); i++) {
			String name = sweetsNames.get(i).getText();
			String priceText = sweetsNamesNprices.get(i).getText();

			String numberString = priceText.substring(1);
			double price = Double.parseDouble(numberString);
			sweetsMap.put(name, price);

			if (price > highestPrice) {
				highestPrice = price;
			}
		}

		// prints the names and prices
		for (Entry<String, Double> entry : sweetsMap.entrySet()) {
			System.out.println("Name: " + entry.getKey() + ", Price: " + entry.getValue());
		}

		// Print the highest price
		System.out.println("\nHighest price: ₹" + highestPrice);

		// Find the sweet with the highest price
		String sweetWithHighestPrice = null;
		for (Map.Entry<String, Double> entry : sweetsMap.entrySet()) {
			if (entry.getValue() == highestPrice) {
				sweetWithHighestPrice = entry.getKey();
				break;
			}
		}

		// Print the sweet with the highest price
		System.out.println("Sweet with the highest price: " + sweetWithHighestPrice);

//		Clik photos&validate the no_photos listed matches the total no imagesdisplayed across the listed pages.
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[text()='Photos']")).click();
		String AllnoShown = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/section[4]/div/div[1]/div/button[1]/span/span"))
				.getText();
		System.out.println("\nAll no os images shown :" + AllnoShown);
		List<WebElement> noOfImages = driver.findElements(By.xpath("//div[@class='sc-bke1zw-0 fIuLDK']/div/div/img"));
		System.out.println("Actual no of images : " + noOfImages.size());

		// driver.quit();

	}
}

//List<WebElement> sweetsNamesNprices = driver.findElements(By.xpath("//section[@class='sc-doidfC hCfpBg'][2]//div[@class='sc-bwNswr bUAylp']/div/div/div/div[2]/div/div/div[2]/span"));		
