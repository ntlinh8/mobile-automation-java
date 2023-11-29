package commons;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {
	AppiumDriver<MobileElement> driver;
	protected final Log log;

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	public AppiumDriver<MobileElement> getDriverInstance() {
		return this.driver;
	}
	
	public AppiumDriver<MobileElement> getAppiumDriver(String deviceNumber) throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		AppiumDriver<MobileElement> driver;
		switch (deviceNumber) {
		case "1":
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, GlobalConstaints.DEVICE1_PLATFORM_NAME);
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalConstaints.DEVICE1_PLATFORM_VERSION);
			desiredCapabilities.setCapability(MobileCapabilityType.UDID, GlobalConstaints.DEVICE1_UDID);
			break;
		case "2":
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, GlobalConstaints.DEVICE2_PLATFORM_NAME);
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalConstaints.DEVICE2_PLATFORM_VERSION);
			desiredCapabilities.setCapability(MobileCapabilityType.UDID, GlobalConstaints.DEVICE2_UDID);
			break;
		case "3":
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, GlobalConstaints.DEVICE3_PLATFORM_NAME);
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalConstaints.DEVICE3_PLATFORM_VERSION);
			desiredCapabilities.setCapability(MobileCapabilityType.UDID, GlobalConstaints.DEVICE3_UDID);
			break;
		default:
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, GlobalConstaints.DEVICE1_PLATFORM_NAME);
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalConstaints.DEVICE1_PLATFORM_VERSION);
			desiredCapabilities.setCapability(MobileCapabilityType.UDID, GlobalConstaints.DEVICE1_UDID);
			break;
		}
		
		desiredCapabilities.setCapability("appPackage", GlobalConstaints.APP_PACKAGE);
		desiredCapabilities.setCapability("appActivity", GlobalConstaints.APP_ACTIVITY);
		desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
		desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
		desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
		desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
		URL appiumServer = new URL(GlobalConstaints.APPIUM_SERVER);
		driver = new AppiumDriver<>(appiumServer, desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(GlobalConstaints.IMPLICIT_LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
		
	}
	
	protected void SleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected boolean verifyTrue(boolean condition) {
		boolean isPassed = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			isPassed = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return isPassed;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean isPassed = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			isPassed = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return isPassed;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean isPassed = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			isPassed = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return isPassed;
	}
	
	
	
	
}
