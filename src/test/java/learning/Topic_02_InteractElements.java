package learning;

import java.net.MalformedURLException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utilities.DataHelper;

public class Topic_02_InteractElements extends BaseTest{
	AppiumDriver<MobileElement> driver;
	private String email, password, confirmPassword;
	
	@Parameters({"DeviceNumber"})
	@BeforeTest
	public void beforeTest(String deviceNumber) throws MalformedURLException{
		log.info("Pre-condition - Step 01: Prepare data");
		DataHelper datahelper = DataHelper.getDataHelper();
		email = datahelper.getEmailAddress();
		password = "Abc@12345";
		confirmPassword = "Abc@12345";
		
		log.info("Pre-condition - Step 02: Connect to device and open application");
		driver = getAppiumDriver(deviceNumber);
		verifyTrue(driver.findElementByXPath("//android.widget.TextView[@text='Demo app for the appium-boilerplate']").isDisplayed());
		
		log.info("Pre-condition - Step 03: Go to Sign up page");
		driver.findElementByAccessibilityId("Login").click();
		driver.findElementByAccessibilityId("button-sign-up-container").click();
	}
	
	@Test
	public void TC01_SignUpWithEmptyData() {
		log.info("TC01 - Step 01: Click to Sign up button");
		driver.findElementByAccessibilityId("button-SIGN UP").click();
		
		log.info("TC01 - Step 02: Verify error messages displays in the screen");
		verifyTrue(driver.findElementByXPath("//android.widget.TextView[@text='Please enter a valid email address']").isDisplayed());
		verifyTrue(driver.findElementByXPath("//android.widget.TextView[@text='Please enter at least 8 characters']").isDisplayed());
		verifyTrue(driver.findElementByXPath("//android.widget.TextView[@text='Please enter the same password']").isDisplayed());
	}
	
	@Test
	public void TC02_SignUpWithInvalidEmail() {
		log.info("TC02 - Step 01: Enter invalid information");
		driver.findElementByAccessibilityId("input-email").sendKeys("Synny");
		driver.findElementByAccessibilityId("input-password").sendKeys(password);
		driver.findElementByAccessibilityId("input-repeat-password").sendKeys(confirmPassword);
		
		log.info("TC02 - Step 02: Click to Sign up button");
		driver.findElementByAccessibilityId("button-SIGN UP").click();
		
		log.info("TC02 - Step 03: Verify error messages displays in the screen");
		verifyTrue(driver.findElementByXPath("//android.widget.TextView[@text='Please enter a valid email address']").isDisplayed());
	}
	
	@Test
	public void TC03_SignUpWithInvalidPassword() {
		log.info("TC03 - Step 01: Enter invalid information");
		driver.findElementByAccessibilityId("input-email").sendKeys(email);
		driver.findElementByAccessibilityId("input-password").sendKeys("Abc");
		driver.findElementByAccessibilityId("input-repeat-password").sendKeys("Abc");
		
		log.info("TC03 - Step 02: Click to Sign up button");
		driver.findElementByAccessibilityId("button-SIGN UP").click();
		
		log.info("TC03 - Step 03: Verify error messages displays in the screen");
		verifyTrue(driver.findElementByXPath("//android.widget.TextView[@text='Please enter at least 8 characters']").isDisplayed());
	}
	
	@Test
	public void TC04_SignUpWithNotMatchConfirmPassword() {
		log.info("TC04 - Step 01: Enter invalid information");
		driver.findElementByAccessibilityId("input-email").sendKeys(email);
		driver.findElementByAccessibilityId("input-password").sendKeys(password);
		driver.findElementByAccessibilityId("input-repeat-password").sendKeys("Abc");
		
		log.info("TC04 - Step 02: Click to Sign up button");
		driver.findElementByAccessibilityId("button-SIGN UP").click();
		
		log.info("TC04 - Step 03: Verify error messages displays in the screen");
		verifyTrue(driver.findElementByXPath("//android.widget.TextView[@text='Please enter the same password']").isDisplayed());
	}
	
	@Test
	public void TC05_SignUpWithValidData() {
		log.info("TC05 - Step 01: Enter valid information");
		driver.findElementByAccessibilityId("input-email").sendKeys(email);
		driver.findElementByAccessibilityId("input-password").sendKeys(password);
		driver.findElementByAccessibilityId("input-repeat-password").sendKeys(confirmPassword);
		
		log.info("TC05 - Step 02: Click to Sign up button");
		driver.findElementByAccessibilityId("button-SIGN UP").click();
		
		log.info("TC05 - Step 03: Verify Sign Up success");
		verifyEquals(driver.findElementById("android:id/message").getText(), "You successfully signed up!");
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
