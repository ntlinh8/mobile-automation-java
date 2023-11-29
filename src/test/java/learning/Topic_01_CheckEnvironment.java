package learning;


import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Topic_01_CheckEnvironment {
	AppiumDriver<MobileElement> driver;
	
	@BeforeTest
	public void beforeTest()  throws MalformedURLException{
		// Set DesiredCapabilities to send to Appium server
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
		desiredCapabilities.setCapability(MobileCapabilityType.UDID, "d91ba32a");
		desiredCapabilities.setCapability("appPackage", "com.wdiodemoapp");
		desiredCapabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
		URL appiumServer = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AppiumDriver<>(appiumServer, desiredCapabilities);
	}

	@Test
	public void TC01_CheckEnvironment() {
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
