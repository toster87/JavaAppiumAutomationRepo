package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
    private final static String
            TITLE = "//android.widget.TextView[@text='{SUBSTRING}']",
            FOOTER_ELEMENT = "//android.widget.TextView[@text='View article in browser']",
    OPTIONS_BUTTON = "org.wikipedia:id/page_save",
    ADD_TO_MY_LIST_BUTTON = "//android.widget.Button[@text='Add to list']",

    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "//*[@text='OK']",
    NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";


    public WebElement waitForTitleElement(String substring) {
        String title = getTitleOfArticle(substring);
        return this.waitForElementPresent(By.xpath(title), "Cannot find article title on page", 15);
    }

    public String getArticleTitle(String substring) {
        WebElement title_element = waitForTitleElement(substring);
        return title_element.getAttribute("text");
    }

    private static String getTitleOfArticle(String substring) {
        return TITLE.replace("{SUBSTRING}", substring);
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath( FOOTER_ELEMENT),
                "Cannontd the end of article",
                20);
    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                By.id(OPTIONS_BUTTON),
                "Cannot find option button to save article",
                5);

        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_BUTTON),
                "Cannot find button to save article",
                5);

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of article folder",
                5);

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find OK button",
                10);
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot close article",
                5);
    }
}
