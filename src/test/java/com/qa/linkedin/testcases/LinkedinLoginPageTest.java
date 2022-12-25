package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoginPage;

import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LinkedinLoginPageTest extends TestBase {

	private Logger log = LogManager.getLogger(LinkedinHomePageTest.class);
	LinkedinHomePage homePage ;
	LinkedinLoginPage loginPage ;

	@BeforeClass
	public void beforeClass() throws InterruptedException  {
		log.info("creating login page object");
		homePage = new LinkedinHomePage();
		loginPage = new LinkedinLoginPage();
	}

	@Test
	public void navigateToLoginPageTest() throws InterruptedException {
		log.info("logging to login page");
		homePage.clickOnSigninLink();
	}

	@Test
	public void verifyLoginPageTitle() {
		log.debug("Verifying Login Page title");
		Assert.assertTrue(loginPage.getLinkedinSigninpageTitle().contains("Login, Sign in"),
				"signin page title is not correct");
	}
	
	@AfterClass
	public void deleteCookies() {
		log.info("Deleting all browser cache");
		driver.manage().deleteAllCookies();
	}
}