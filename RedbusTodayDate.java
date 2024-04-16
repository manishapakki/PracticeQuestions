package Practice.Questions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedbusTodayDate {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("https://www.redbus.in/");
		driver.findElement(By.xpath("//input[@id='src']")).sendKeys("Mumbai");
		driver.findElement(By.xpath("//input[@id='dest']")).sendKeys("Bangalore");
		Thread.sleep(2000);

		driver.findElement(By.id("onwardCal")).click();
		int date = java.time.LocalDate.now().getDayOfMonth();

		driver.findElement(By.xpath("//span[text()='" + date + "']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("search_button")).click();
		System.out.println("now choose departure time after 6 pm");
		driver.findElement(By.xpath("//label[@for='dtAfter 6 pm'][1]")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//li[@class='fl set-filters' and text()='After 6 pm']")).isDisplayed(),
				true);
		System.out.println("dispalyed after 6pm");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");

		driver.findElement(By.xpath("//label[@for='bt_SLEEPER'][1]")).click();

        js.executeScript("window.scrollTo(0, 0)");		
		driver.findElement(By.xpath("//a[text()='Seats Available']")).click();

//		Thread.sleep(3000);
//		WebElement advertisemnt = driver.findElement(By.xpath("//i[@class='icon icon-close']"));
//		advertisemnt.click();
				
		js.executeScript("window.scrollBy(0,300)");

		String seats_available = driver.findElement(By.xpath("//ul[@class='bus-items']/div[1]//div[@class='column-eight w-15 fl']/div[1]")).getText();
		System.out.println("seats_available : " + seats_available);

		String[] parts = seats_available.split("\\s+");
		String numberString = parts[0];

		int seatsCount = Integer.parseInt(numberString);
		System.out.println(seatsCount);
		

		
		driver.findElement(By.xpath("//ul[@class='bus-items']/div[1]//div[@class='clearfix m-top-16']//div[text()='View Seats']")).click();
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//i[@class='icon-close closepopupbtn']")).click();
		
        


        File RedBusScreenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(RedBusScreenshot, new File("C:\\Users\\MPAKKI\\OneDrive - Capgemini\\Desktop\\screenshotSelenium\\RedbuScrrenshot3.png"));
		
		System.out.println("Redbus seats screenshot captured");


	}

}
