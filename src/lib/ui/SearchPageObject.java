package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    private final static String
    SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
    SEARCH_INPUT = "org.wikipedia:id/search_container",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']",
    SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
    SEARCH_EMPTY_RESUL_ELEMENT = "//*[@text='No results']",
    SEARCH_SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button";
     public SearchPageObject(AppiumDriver driver) {
         super(driver);
     }

     /*TEMPLATES METHODS*/
     private static String getResultSearchElement(String substring) {
         return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
     }
    /*TEMPLATES METHODS*/
    public void initSearchInput() {
        this.waitForElementPresent(By.id(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
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

    public void clickCancelSearch(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
         this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring){
         String search_result_xpath = getResultSearchElement(substring);
         this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring, 20);
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 30);
    }

    public int getAmountOfSearchArticles() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find anything by the request", 15);

       return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESUL_ELEMENT), "Cannot find empty result label by the request", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results by request");
    }
}
