package com.qa.linkedin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.linkedin.utils.BasePageWebActions;

public class LinkedinSearchResultsPage extends BasePageWebActions {

	private Logger log = LogManager.getLogger(LinkedinSearchResultsPage.class);

	public LinkedinSearchResultsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='app-aware-link'][contains(.,'See all people results')]")
	private WebElement seeAllPeopleResultsLink;

	@FindBy(xpath = "//div[@class='search-results-container']/h2")
	private WebElement searchResultsText;

	@FindBy(xpath = "//nav[@class='global-nav__nav']/ul/li[1]/a")
	private WebElement homeTab;

	/**
	 * this method will title of search result page
	 * 
	 * @return
	 */

	public String getSearchResultsPageTitle() {
		log.debug("fetching searchResults page title");
		return driver.getTitle();
	}

	/**
	 * this method will fetch the results text count
	 * 
	 * @return
	 * @throws InterruptedException
	 */

	public String getResultsCountText() throws InterruptedException {
		log.info("get the results count text in results page");
		return getText(searchResultsText);
	}

	public void clickOnSeeAllPeopleResultsLink() throws InterruptedException {
		log.info("click on seeAllPeopleResultsLink");
		click(seeAllPeopleResultsLink);
	}

	public void clickOnHomeTab() throws InterruptedException {
		log.info("click on HomeTab");
		click(homeTab);
	}

	public long getResultCount() throws InterruptedException {
		// About 308,000 results
		String str = getResultsCountText();
		String[] strArray = str.split(" ");

		log.debug("results count is:" + strArray[1]);
		log.debug("convert the string into long format");
		long count = Long.parseLong(strArray[1].replace(",", ""));
		return count;
	}
}