package Practice.Questions;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ajio {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ajio.com/");
		driver.findElement(By.xpath("//button[@aria-label='Allow Location']")).click();
		// Thread.sleep(4000);
		// Actions act = new Actions(driver);
		// act.moveToElement(driver.findElement(By.xpath("//span[text()='WOMEN']")))
		// .moveToElement(driver.findElement(By.xpath("(//a[text()='BRANDS'])[2]"))).build().perform();

		Thread.sleep(3000);
		List<WebElement> All_brands = driver.findElements(By.xpath(
				"//li[@data-test='li-WOMEN']//li[@class='catg inactive-text'][2]//div[@class='items']//span//a[contains(text(), '')]"));
		System.out.println(All_brands.size());

		String brandWithMostLowercasechars = "";
		int maxLowercaseCount = 0;

		for (WebElement eachBrand : All_brands) {
			String eachBrandtext = eachBrand.getAttribute("innerText");
			//System.out.println(eachBrandtext);

			// int lowercaseCount = eachBrandtext.length() - eachBrandtext.replaceAll("[a-z]", "").length();
			int lowercaseCount = 0;
			for (int i = 0; i < eachBrandtext.length(); i++) {
				char c = eachBrandtext.charAt(i);
				if (c >= 'a' && c <= 'z') {
					lowercaseCount++;
				}
			}

			if (lowercaseCount > maxLowercaseCount) {
				maxLowercaseCount = lowercaseCount;
				brandWithMostLowercasechars = eachBrand.getAttribute("href");
			}
		}
		System.out.println(brandWithMostLowercasechars);
		driver.get(brandWithMostLowercasechars);
		
		
		String NoOfProducts = driver.findElement(By.xpath("//div[@class='length']/strong")).getText();
		System.out.println(NoOfProducts);
		
		driver.findElement(By.xpath("//span[text()='size & fit']")).click();
		driver.findElement(By.xpath("//label[@for='S']")).click();
		
		Thread.sleep(4000);
		String noOfSmallsizeproducts = driver.findElement(By.xpath("//div[@class='length']/strong")).getText();
		System.out.println(noOfSmallsizeproducts);
		
		String[] total = NoOfProducts.split(" ");
		int totalProducts = Integer.parseInt(total[0]);

		String[] smallItems = noOfSmallsizeproducts.split("\\s+");
		int smallSizeProducts = Integer.parseInt(smallItems[0]);

		if(totalProducts>smallSizeProducts)
		{
			System.out.println("yes the count is reduced");
		} else {
			System.out.println(" no the count is same or high");
		}
		
		
		WebElement sortBy  = driver.findElement(By.xpath("//select[@id='sortBy']"));
		Select sel = new Select(sortBy);
		sel.selectByVisibleText("Discount");
		
		
		//System.out.println("sorted in descending");
	}
}

// System.out.println("Brand " + (i + 1) + ": " +All_brands.get(i).getAttribute("innerText"));
// System.out.println("Brand " + (i+1) + ": "+All_brands.get(i).getText());