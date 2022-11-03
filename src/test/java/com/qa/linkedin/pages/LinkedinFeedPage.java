package com.qa.linkedin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.linkedin.utils.BasePageWebActions;

public class LinkedinFeedPage extends BasePageWebActions {

	
	private Logger log = LogManager.getLogger(LinkedinFeedPage.class);
	
	//create a constructor
	public LinkedinFeedPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class='feed-identity-module artdeco-card overflow-hidden mb2']")
	private WebElement profileRailCard;
	
	@FindBy(css="input[class*='search-global-typeahead__input']")
	private WebElement searchEditbox;
	
	@FindBy(xpath="//img[contains(@class,'global-nav__me-photo')]")
	private WebElement profileMeIcon;
	
	@FindBy(xpath ="//a[contains(@href,'logout')]")
	private WebElement signoutLink;
	
	
	/**
	 * this method will return title of feed page
	 * @return
	 */
	public String getLinkedinFeedPageTitle() {
		log.info("fetching the Linkedin feed page title");
		return driver.getTitle();
	}
	
	/**
	 * this method will check profile card is availability in feed page 
	 * @return
	 */
	public boolean isProfileRailCardPresent() {
		log.info("checking the profile rail card availability");
		return profileRailCard.isDisplayed();
	}
	
	/**
	 * this method will click on logout link in feed page
	 * @throws InterruptedException
	 */
	
	public void doLogout() throws InterruptedException {
		log.info("click on profilemeicon");
		click(profileMeIcon);
		Thread.sleep(1000);
		click(signoutLink);
		
	}
	
	/**
	 * this method will search for the required text
	 * @param keyword
	 * @return
	 * @throws InterruptedException
	 */
	public LinkedinSearchResultsPage doPeopleSearch(String searchText) throws InterruptedException {
		log.debug("clear & type the keyword "+searchText+" in searchEditbox");
		clearText(searchEditbox);
		type(searchEditbox,searchText);
		searchEditbox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return new LinkedinSearchResultsPage();
		
	}
}