package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class iOSTestCase extends TestCase {

        protected AppiumDriver driver;
        private static String AppiumURL = "http://127.0.0.1:4723/";

        @Override
        protected void setUp() throws Exception {
            super.setUp();

            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE (3rd generation)");
            capabilities.setCapability("platformVersion", "17.2");
            capabilities.setCapability("automationName", "xcuitest");
            capabilities.setCapability("app", "/Users/legai/Documents/GitHub/JavaAppiumAutomation/apks/Wikipedia.app");

            driver = new IOSDriver(new URL(AppiumURL), capabilities);
            this.rotateScreenPortrait();
        }

        @Override
        protected void tearDown() throws Exception {
            super.tearDown();
            driver.quit();
        }

        protected void rotateScreenPortrait() {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }

        protected void rotateScreenLandscape() {
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }

        protected void backgroundApp(int seconds) {
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        }
    }

