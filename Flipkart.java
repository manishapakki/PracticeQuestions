package Practice.Questions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("https://www.flipkart.com");

		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement fashion = driver.findElement(By.xpath(
				"//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div[1]/div/div[1]/div/div/div/div/div[1]/div[1]"));
		action.moveToElement(fashion).click().build().perform();
		WebElement womenFootWear = driver.findElement(By.xpath("//a[@class='_1BJVlg'][4]"));
		action.moveToElement(womenFootWear).build().perform();
		Thread.sleep(3000);
		WebElement womenSneaker = driver.findElement(By.xpath("//a[text()='Women Sneakers']"));
		action.moveToElement(womenSneaker).click().build().perform();

		WebElement brand = driver.findElement(
				By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div/div/section[8]/div/div"));
		brand.click();
		js.executeScript("window.scrollBy(0,300)", "");
		WebElement skechers = driver.findElement(By.xpath(
				"//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div/div/section[8]/div[2]/div[1]/div[5]/div/label/div[2]"));
		skechers.click();
		Thread.sleep(5000);
		List<WebElement> shoeTitleElements = driver
				.findElements(By.xpath("//div[@class='_13oc-S']/div/div/div//div[@class='_2WkVRV']"));
		List<WebElement> shoePriceElements = driver.findElements(
				By.xpath("//div[@class='_13oc-S']//div[@class='_2B099V']//a[@class='_3bPFwb']//div[@class='_30jeq3']"));

		System.out.println("Number of shoe titles: " + shoeTitleElements.size());
		System.out.println("Number of shoe prices: " + shoePriceElements.size());

		// Create a map to store shoe titles and prices
		Map<String, Integer> shoeMap = new HashMap<>();

		// Ensure that the loop iterates through all elements
		for (int i = 0; i < shoeTitleElements.size(); i++) {
			WebElement shoeTitleElement = shoeTitleElements.get(i);
			WebElement shoePriceElement = shoePriceElements.get(i);

			String title = shoeTitleElement.getText();
			String priceString = shoePriceElement.getText();
			int price = Integer.parseInt(priceString.substring(1).replaceAll(",", ""));

			shoeMap.put(title, price);
		}

		for (Map.Entry<String, Integer> entry : shoeMap.entrySet()) {
			System.out.println("Title: " + entry.getKey() + ", Price: " + entry.getValue());
		}
	}
}

/*
 * for (WebElement eachShoe : shoeTitle) {
 * System.out.println(eachShoe.getText()); }
 * 
 * for (WebElement eachPrice : shoePrice) { String s = eachPrice.getText(); int
 * price = Integer.parseInt(s.substring(1).replaceAll(",", ""));
 * System.out.println(price); }
 */
