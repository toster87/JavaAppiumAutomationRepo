package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {
    private final static String
    MY_LIST_LINK = "//android.widget.FrameLayout[@content-desc='Saved']",
    NOT_NOW_BUTTON = "//android.widget.Button[@text='Not now']";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList() {
        this.waitForElementAndClick(
                By.xpath(MY_LIST_LINK),
                "Cannot find navigation button to my list",
                5);
        this.waitForElementAndClick(
                By.xpath(NOT_NOW_BUTTON),
                "Cannot find Not now button",
                5);
    }
}
