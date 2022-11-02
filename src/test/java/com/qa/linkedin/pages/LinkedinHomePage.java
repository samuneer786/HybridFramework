package com.qa.linkedin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.linkedin.utils.BasePageWebActions;

public class LinkedinHomePage extends BasePageWebActions {

	private Logger log = LogManager.getLogger(LinkedinHomePage.class);

	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a.nav__button-secondary.btn-md.btn-secondary-emphasis")
	private WebElement signInLink;

	@FindBy(css = "a[class^='nav__logo-link']")
	private WebElement linkedInLogo;

	/**
	 * This method will get linkedIn homepage title
	 * 
	 * @return
	 */
	public String getLinkedinHomePagetitle() {
		log.info("fetching the LinkedinHomePage title");
		return driver.getTitle();
	}

	/**
	 * this method will verify logo is present or not
	 * 
	 * @return
	 */

	public boolean isLinkedInLogoPresent() {
		log.info("checking linkedinlogo is present or not");
		return linkedInLogo.isDisplayed();
	}

	/**
	 * this method will click on Sign in link
	 * 
	 * @return
	 * @throws InterruptedException
	 */

	public LinkedinLoginPage clickOnSigninLink() throws InterruptedException {
		log.info("click on signin link in home page");
		click(signInLink);
		return new LinkedinLoginPage();
	}
}