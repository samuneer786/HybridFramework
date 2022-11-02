package com.qa.linkedin.testcases;

import org.testng.annotations.Test;
import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LinkedinHomePageTest extends TestBase {

	private Logger log = LogManager.getLogger(LinkedinHomePageTest.class);
	LinkedinHomePage homePage ;

	@BeforeClass
	public void beforeClass() throws IOException {
		log.info("creating the home page object");
		homePage = new LinkedinHomePage();
		}
	
	@Test
	public void verifyLinkedinHomePageTitleTest() {
		log.info("verifying linkedinHomepage title");
		Assert.assertEquals(homePage.getLinkedinHomePagetitle(), "LinkedIn: Log In or Sign Up");
	}

	@Test
	public void verifyLinkedinLogoTest() {
		log.info("started verifyLinkedinLogoTest()");
		Assert.assertTrue(homePage.isLinkedInLogoPresent(), "linkedinLogo is not present");

	}

	@AfterClass
	public void afterClass() {
		log.info("removing all the cache of the browser...in after class for LinkedinHomeTest ");
		driver.manage().deleteAllCookies();
		
	}
}