package Practice.Questions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lenskart {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.lenskart.com/");

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//a[text()='Computer Glasses']")))
				.moveToElement(driver.findElement(By.xpath("(//span[text()='Men'])[2]")))
				.moveToElement(driver.findElement(By.xpath("//span[text()='Blu 0 Computer Glasses']"))).click().build()
				.perform();

		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Computer Glasses']")).isDisplayed(), true);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");

		driver.findElement(By.xpath("//span[text()='Round']")).click();
		System.out.println("Clicked on element round shape");
		
		
		driver.findElement(By.xpath("//span[text()='FRAME COLOR']")).click();

		js.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//span[text()='FRAME COLOR']")));

		WebElement highestCountColor = driver.findElement(By.xpath("//*[@id=\"frame_color_id\"]/div[2]/div/div[1]/label"));
		String colorhighest = highestCountColor.getText();
		System.out.println("Frame color with highest count "+colorhighest);
		
		int startIndex = colorhighest.indexOf('(');
		int endIndex = colorhighest.indexOf(')');
		String numberString = colorhighest.substring(startIndex + 1, endIndex);
		
		int colorCount = Integer.parseInt(numberString);
		System.out.println("Extracted highest Color count: " + colorCount);
		
		highestCountColor.click();
		
		String ResultCount = driver.findElement(By.xpath("//*[@id=\"next\"]/div[2]/div/div/div[2]/div/div[1]/div/div[2]/span[4]")).getText();
		System.out.println("The result showing in page " + ResultCount);
		int resultCountInt = Integer.parseInt(ResultCount);

		
		if (colorCount == resultCountInt) {
		    System.out.println("Counts are equal.");
		} else {
		    System.out.println("Counts are not equal.");
		}
		
		
		driver.findElement(By.xpath("//div[@id='frame_size_id']")).click();
		
		List<WebElement> noOfFramesizes = driver.findElements(By.xpath("//div[@id='frame_size_id']/div[2]/div/div"));
		
		System.out.println("No of frame sizes presnt " + noOfFramesizes.size());

		Thread.sleep(6000);
		 driver.quit();
		 
		 System.out.println("lenskart ....");
	}

}


