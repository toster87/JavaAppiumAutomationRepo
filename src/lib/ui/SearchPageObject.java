package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {
    private final static String
    SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
    SEARCH_INPUT = "org.wikipedia:id/search_container",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']",
    SEARCH_TITLE_AND_DESCRIPTION_RESULT_BY_SUBSTRING_TPL = "//android.widget.TextView[@text='{SUBSTRING}']",
    SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
    SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']",
    SEARCH_SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button",
    SEARCH_INPUT_TEXT = "org.wikipedia:id/search_src_text";

     public SearchPageObject(AppiumDriver driver) {
         super(driver);
     }

     /*TEMPLATES METHODS*/
     private static String getResultSearchElement(String substring) {
         return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
     }

    private static String getTitleAndDescriptionOfSearchElement(String substring) {
        return SEARCH_TITLE_AND_DESCRIPTION_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHODS*/
    public void initSearchInput() {
        this.waitForElementPresent(By.id(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 10);
    }

    public void clickSkipButton() {
        this.waitForElementAndClick(By.id(SEARCH_SKIP_BUTTON), "Cannot find and click skip button", 5);
    }
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
         this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
         String search_result_xpath = getResultSearchElement(substring);
         this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring, 20);
    }

    public void waitForElementByTitleAndDescription(String substringTitle, String substringDescription) {
        String search_title_xpath = getTitleAndDescriptionOfSearchElement(substringTitle);
        String search_description_xpath = getTitleAndDescriptionOfSearchElement(substringDescription);
        this.waitForElementPresent(By.xpath(search_title_xpath), "Cannot find title in search result with substring " + substringTitle, 20);
        this.waitForElementPresent(By.xpath(search_description_xpath), "Cannot find description in search result with substring " + substringDescription, 20);
    }


    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 30);
    }

    public int getAmountOfSearchArticles() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find anything by the request", 15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result label by the request", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results by request");
    }

    public void waitForAppearOfResultsOfSearch() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed to find any results by request", 20);
    }

    public void waitForDisappearOfResultsOfSearch() {
        this.waitForElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Search results are still present on page", 10);
    }

    public WebElement waitForSearchInputText() {
        return this.waitForElementPresent(By.id(SEARCH_INPUT_TEXT), "Cannot find search input text", 10);
    }

    public void clearSearchInputAndTypeSearchLine(String search_line) {
        this.waitForElementAndClear(By.id(SEARCH_INPUT_TEXT), "Cannot find and clear search input text", 10);
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT_TEXT), search_line, "Cannot find and type into search input", 5);
    }

    public void assertSearchInputHasText(String value) {
        WebElement text_element = waitForSearchInputText();
        String inputText = text_element.getAttribute("text");
        this.assertElementHasText("Cannot find input text", value, inputText);
    }

    public void assertSearchResultsHaveText(String text) {
        boolean found = false;
        List<WebElement> searchResults = findElements(By.xpath(SEARCH_RESULT_ELEMENT));
        for (WebElement webElement : searchResults) {
            if (webElement.getText().contains(text)) {
                found = true;
                break;
            }
        }
        if (!found)
            Assert.fail("Search results have no text " + text);
    }
}
