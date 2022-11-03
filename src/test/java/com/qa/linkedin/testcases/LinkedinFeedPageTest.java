package com.qa.linkedin.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinFeedPage;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoginPage;

public class LinkedinFeedPageTest extends TestBase {

	Logger log = LogManager.getLogger(LinkedinFeedPageTest.class);
	LinkedinHomePage homePage = null;
	LinkedinLoginPage loginPage = null;
	LinkedinFeedPage feedPage = null;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		log.info("creating feed page object");
		homePage = new LinkedinHomePage();
		loginPage = new LinkedinLoginPage();
		feedPage = new LinkedinFeedPage();
	}
	
	@Test(description ="navigate to login page",priority=0)
	public void navigateToLoginPageTest() throws InterruptedException {
		log.info("navigating to login page");
		homePage.clickOnSigninLink();
	}

	@Test(description = "login to linkedin", priority=1)
	public void doLoginTest() throws InterruptedException, IOException {
		log.debug("entering username and password");
		feedPage = loginPage.doLogin(readPropertyValue("username"), readPropertyValue("password"));
		log.info("Login is Successful");
	}

	@Test(description = "verifying profile railcard element", dependsOnMethods = { "doLoginTest" },priority=2)
	public void verifyProfileRailcardTest() {
		log.debug("verifying element ProfileRailCardPresent or not");
		Assert.assertTrue(feedPage.isProfileRailCardPresent(), "profilerailcard element is not present");
		
	}

	@Test(dependsOnMethods = { "doLoginTest" },priority=3)
	public void verifyFeedPageTitleTest() {
		log.debug("Verifying feed page title");
		Assert.assertTrue(feedPage.getLinkedinFeedPageTitle().contains("Feed | LinkedIn"),
				"Feed | LinkedIn is not present");
	}

	@Test(priority=4)
	public void doLogoutTest() throws InterruptedException {
		log.debug("Logging out from linkedin application");
		Thread.sleep(2000);
		feedPage.doLogout();
	}
}