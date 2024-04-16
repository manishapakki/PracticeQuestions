package Practice.Questions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Walmart {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.walmart.com/");
		Actions actions = new Actions(driver);
		//Thread.sleep(4000);
		WebElement button = driver.findElement(By.xpath("//div[@aria-label='Human challenge']/div/p"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Human challenge']/div/p")));
		actions.keyDown(Keys.CONTROL).click(button).pause(5000).keyUp(Keys.CONTROL).perform();

		// actions.clickAndHold(button).pause(5000).release().build().perform();
		System.out.println("holded");
		driver.findElement(By.xpath("//i[@class='ld ld-Grid pr2']")).click(); // Department button

//		WebElement ClothingNAcces = driver.findElement(By.xpath("//button[text()='Clothing, Shoes, & Accessories ']"));
//		WebElement shoes = driver.findElement(By.xpath("//h2[text()='Women']/following::a[text()[normalize-space()='Shoes']][1]"));

//		actions.moveToElement(ClothingNAcces).moveToElement(shoes).click();
//		System.out.println("clciked on shoes");

//		int size = driver.findElements(By.tagName("iframe")).size();
//		System.out.println(size);

//		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Human verification challenge']")));
//		System.out.println("switched frame");

		// driver.quit();
	}
}

//driver.get("https://www.walmart.com/");
//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[text()='Press & Hold']")));
//driver.findElement(By.xpath("//button[@aria-label='Close dialog']")).click();
//driver.switchTo().frame(2);	

//WebElement walmart = driver.findElement(By.id("APjFqb"));
//walmart.sendKeys("walmart.com");
//walmart.sendKeys(Keys.ENTER);
//driver.findElement(By.xpath("//div[@class='eKjLze']")).click();
