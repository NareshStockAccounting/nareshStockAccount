package DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AdminLoginTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\naresh.a\\Downloads\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.navigate().to("http://webapp.qedge.com/login.php");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("Qedge123!@#");
		driver.findElement(By.id("btnsubmit")).click();
		driver.quit();

	}

}
