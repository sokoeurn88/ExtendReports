package sokoeurnchhayacademy.ExtendReports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo {
	ExtentReports extent; // make as class level object, so it can accesss in initialDemo method
	WebDriver driver;
	
	@BeforeTest
	public void config() {

		// ExtentReports, ExtentSparkReporter
		String path = System.getProperty("user.dir") + "\\reporters\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports(); // ExtentReporters is the main class. ExtentSparkReporter is attached to it.
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sokoeurn Chhay");
	}

	@Test
	public void initialDemo() {
		extent.createTest("initialDemo"); // initiate extent object
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getTitle());
		extent.flush();	//notify the test is done. No more monitoring
	}
	
	@Test
	public void getEleText() {
		ExtentTest test = extent.createTest("getEleText");
		
		String learningTextEle = driver.findElement(By.xpath("//a[contains(text(),'Learning Path')]")).getText();
		System.out.println(learningTextEle);
		
		driver.close();
		
		test.fail("Result does not match");
		
		extent.flush();
	}

}
