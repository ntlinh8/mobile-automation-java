package learning;

import java.net.MalformedURLException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Topic_01_CheckEnvironment extends BaseTest{
	AppiumDriver<MobileElement> driver;
	
	@Parameters({"DeviceNumber"})
	@BeforeTest
	public void beforeTest(String deviceNumber) throws MalformedURLException{
		driver = getAppiumDriver(deviceNumber);
	}

	@Test
	public void TC01_CheckEnvironment() {
		System.out.println("TC01_CheckEnvironment");
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
