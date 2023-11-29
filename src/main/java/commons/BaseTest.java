package commons;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {
	
	public AppiumDriver<MobileElement> getAppiumDriver(String deviceNumber) throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		
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
		return new AppiumDriver<>(appiumServer, desiredCapabilities);
	}
}
