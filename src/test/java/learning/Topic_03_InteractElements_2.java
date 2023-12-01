package learning;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Topic_03_InteractElements_2 extends BaseTest{
	AppiumDriver<MobileElement> driver;
	
	@Parameters({"DeviceNumber"})
	@BeforeTest
	public void beforeTest(String deviceNumber){
		log.info("Pre-condition - Step 01: Connect to device and open application");
		driver = getAppiumDriver(deviceNumber);
		verifyTrue(driver.findElementByXPath("//android.widget.TextView[@text='Demo app for the appium-boilerplate']").isDisplayed());
		
		log.info("Pre-condition - Step 02: Go to Login page");
		driver.findElementByAccessibilityId("Login").click();
		driver.findElementByAccessibilityId("button-login-container").click();
	}
	
	@Test
	public void TC06_LoginWithEmptyData() {
		log.info("TC06 - Step 1: Enter empty data");
		driver.findElementByAccessibilityId("input-email").clear();
		driver.findElementByAccessibilityId("input-password").clear();
		
		log.info("TC06 - Step 2: Click Login button");
		driver.findElementByAccessibilityId("button-LOGIN").click();
		
		log.info("TC06 - Step 3: Verify error messages");
		verifyTrue(driver.findElementByXPath("//android.widget.TextView[@text='Please enter a valid email address']").isDisplayed());
		verifyTrue(driver.findElementByXPath("//android.widget.TextView[@text='Please enter at least 8 characters']").isDisplayed());
		
	}
	
	@Test
	public void TC07_LoginWithCorrectPassword() {
		log.info("TC07 - Step 1: Enter empty data");
		driver.findElementByAccessibilityId("input-email").sendKeys(Topic_02_InteractElements_1.email);
		driver.findElementByAccessibilityId("input-password").sendKeys(Topic_02_InteractElements_1.password);
		
		log.info("TC07 - Step 2: Click Login button");
		driver.findElementByAccessibilityId("button-LOGIN").click();
		
		log.info("TC07 - Step 3: Verify Login successful");
		verifyEquals(driver.findElementById("android:id/message").getText(), "You are logged in!");
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
