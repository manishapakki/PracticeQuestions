package Practice.Questions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

//WebDriver driver = new ChromeDriver();

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("https://www.flipkart.com");
		//((JavascriptExecutor) driver).executeScript("document.body.style.zoom = '0.9'");

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
		Map<Integer, String> shoeMap = new HashMap<>();
		List<Integer> prices = new ArrayList<>();

		String secondLink = new String("");

		// Ensure that the loop iterates through all elements
		for (int i = 0; i < shoeTitleElements.size(); i++) {
			WebElement shoeTitleElement = shoeTitleElements.get(i);
			WebElement shoePriceElement = shoePriceElements.get(i);

			String title = shoeTitleElement.getText();

			String priceString = shoePriceElement.getText();
			int price = Integer.parseInt(priceString.substring(1).replaceAll(",", ""));
			shoeMap.put(price, title);

			System.out.println(price);

			prices.add(price);
			// shoeMap.put(title, price);
		}
		shoeMap.remove(Collections.min(shoeMap.keySet()));
		int smin = Collections.min(shoeMap.keySet());

		System.out.println("Minimun price is : " + smin);
		// System.out.println(shoeMap
		// secondLink = shoeMap.get(smin);
		// System.out.println(secondLink);
		String s = new String("");
		for (int i = 0; i < shoePriceElements.size(); i++) {
			if (Integer.parseInt(shoePriceElements.get(i).getText().replaceAll("[^0-9]", "")) == smin) {
				s = (shoePriceElements.get(i) + "/../..");
				System.out.println(shoePriceElements.get(i) + "/../..");
			}
		}
		// The second lowest price will be at index 1
		Collections.sort(prices);
		int secondLowestPrice = prices.get(1);
		System.out.println("Second lowest price: " + secondLowestPrice);
		driver.findElement(By.xpath(
				"//div[text()='Skechers']/../a/div/div[1][contains(text(),'₹" + String.format("%,d", smin) + "')]"))
				.click();

		Set<String> handles = driver.getWindowHandles();

		// Switch to the new tab
		for (String handle : handles) {
			if (!handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}

		String ratingsNreviews = driver
				.findElement(By.xpath(
						"//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div[4]/div/div/span[2]/span"))
				.getText();
		System.out.println(ratingsNreviews);
		driver.findElement(By.xpath("//a[text()='7']")).click();

		String priceOfShoe = driver.findElement(By.xpath("//div[@class='_30jeq3 _16Jk6d' and contains(text(),'₹')]"))
				.getText();
		System.out.println(priceOfShoe);
		// driver.findElement(By.xpath("//span[text()='Rate Product']")).click();
		// String messageDesplayed1 =
		// driver.findElement(By.xpath("//div[@class='_3ISAFp']")).getText();
		// String messageDesplayed2 =
		// driver.findElement(By.xpath("//div[@class='_3dHsYX']")).getText();
		// System.out.println(messageDesplayed1);
		// System.out.println(messageDesplayed2);
		
		//js.executeScript("arguments[0].scrollIntoView();", addtoCart);
		//js.executeAsyncScript("window.scrollBy(0,1900);");
		//Thread.sleep(3000);

		WebElement addtoCart = driver.findElement(By.xpath("//button[text()='Add to cart']"));

		addtoCart.click();
		System.out.println("clciked");

	}
}