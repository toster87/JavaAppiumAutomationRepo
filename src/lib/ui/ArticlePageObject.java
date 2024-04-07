package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private final static String
            TITLE = "//android.widget.TextView[1][@text='{SUBSTRING}']",
            DESCRIPTION = "//android.widget.TextView[2][@text='{SUBSTRING}']",
            FOOTER_ELEMENT = "//android.view.View[@content-desc='View article in browser']/android.widget.TextView",
            OPTIONS_BUTTON = "org.wikipedia:id/page_save",
            ADD_TO_MY_LIST_BUTTON = "//android.widget.Button[@text='Add to list']",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            CREATED_FOLDER = "//*[@text='{SUBSTRING}']";


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

    private static String getDescriptionOfArticle(String substring) {
        return DESCRIPTION.replace("{SUBSTRING}", substring);
    }

    public WebElement waitForDescriptionElement(String substring) {
        String description_of_article = getDescriptionOfArticle(substring);
        return this.waitForElementPresent(By.xpath(description_of_article), "Cannot find description of article on page", 15);
    }

    public String getArticleDescription(String substring) {
        WebElement description_of_article = waitForDescriptionElement(substring);
        return description_of_article.getAttribute("text");
    }

    private static String getNameOfFolderInMyList(String substring) {
        return CREATED_FOLDER.replace("{SUBSTRING}", substring);
    }

    public void waitForNameOfFolderElementAndClick(String substring) {
        String name_of_folder = getNameOfFolderInMyList(substring);
        this.waitForElementAndClick(By.xpath(name_of_folder), "Cannot find name of folder", 15);
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
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

    public void addArticleToCreatedFolder(String name_of_folder) {
        this.waitForElementAndClick(
                By.id(OPTIONS_BUTTON),
                "Cannot find option button to save article",
                5);

        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_BUTTON),
                "Cannot find button to save article",
                5);

        this.waitForNameOfFolderElementAndClick(name_of_folder);
    }

    public void assertArticleHasTitle(String substring, String title) {
        String title_of_article = getTitleOfArticle(substring);
        this.assertElementPresent(By.xpath(title_of_article), "text", title, "Cannot find title " + title + " of article", 10);
    }

}
