package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinFeedPage;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoginPage;
import com.qa.linkedin.pages.LinkedinSearchResultsPage;
import com.qa.linkedin.utils.ExcelUtils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LinkedinSearchResultsTest extends TestBase {

	private Logger log = LogManager.getLogger(LinkedinSearchResultsTest.class);
	LinkedinLoginPage loginPage;
	LinkedinHomePage homePage;
	LinkedinFeedPage feedPage;
	LinkedinSearchResultsPage searchPage;
		
	private static String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\testData.xlsx";

	@BeforeClass
	public void beforeClass() throws InterruptedException {

		log.info("creating login page object");
		homePage = new LinkedinHomePage();
		loginPage = new LinkedinLoginPage();
		feedPage = new LinkedinFeedPage();
		searchPage = new LinkedinSearchResultsPage();
	}
	
	@Test(description = "Navigate to login page")
	public void navigateToLoginPageTest() throws InterruptedException {
		log.info("Navigating to login page");
		homePage.clickOnSigninLink();
	}

	@Test(description = "login to linkedin", priority = 1)
	public void doLoginTest() throws InterruptedException, IOException {
		log.debug("entering username and password");
		feedPage = loginPage.doLogin(readPropertyValue("username"), readPropertyValue("password"));
		log.info("Login is Successful");
	}

	@Test(dependsOnMethods = { "doLoginTest" }, priority = 2)
	public void doPeopleSearchTest() throws InterruptedException {
		log.debug("Entering search name:");
		searchPage = feedPage.doPeopleSearch("Ramesh");
		Thread.sleep(3000);
		
	}
	
	@DataProvider(name="getTestData")
	public Object[][] getTestData() throws IOException{
		Object[][] data=new ExcelUtils().getTestData(filePath, "Sheet1");
		return data;
	}

	@Test
	public void doLogoutTest() throws InterruptedException {
		log.info("performing the logout from linkedin");
		Thread.sleep(2000);
		feedPage.doLogout();
	}
}