package com.qa.linkedin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.linkedin.utils.BasePageWebActions;

public class LinkedinLoginPage extends BasePageWebActions {

	private Logger log = LogManager.getLogger(LinkedinLoginPage.class);

	// create a Constructor
	public LinkedinLoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h1[class*='header__content__heading']")
	private WebElement signInHeaderText;

	@FindBy(id = "username")
	private WebElement emailEditbox;

	@FindBy(css = "#password")
	private WebElement passwordEditbox;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signInBtn;

	public String getLinkedinSigninpageTitle() {
		log.info("fetching the linkedin signin page title");
		return driver.getTitle();
	}
	
	/**
	 * this 
	 * @return
	 */

	public boolean isSignInHeaderTextPresent() {
		log.info("check signin header text is present or not");
		return signInHeaderText.isDisplayed();
	}

	/**
	 * this method will click on login button in login page
	 * @throws InterruptedException
	 */
	public void clickSignInBtn() throws InterruptedException {
		log.info("click on signinBtn in login page");
		click(signInBtn);
	}
	
	/**
	 * this method will perform the login operation
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws InterruptedException
	 */

	public LinkedinFeedPage doLogin(String uname, String pwd) throws InterruptedException {
		log.debug("perform the login action ");
		log.info("clear the content and type in emailEditbox");
		clearText(emailEditbox);
		type(emailEditbox, uname);
		log.info("clear the content and type in passwordEditbox");
		clearText(passwordEditbox);
		type(passwordEditbox, pwd);
		clickSignInBtn();
		return new LinkedinFeedPage();
	}
}