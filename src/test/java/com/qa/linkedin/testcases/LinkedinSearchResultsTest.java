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

	private static  Logger log = LogManager.getLogger(LinkedinSearchResultsTest.class);
	LinkedinLoginPage loginPage;
	LinkedinHomePage homePage;
	LinkedinFeedPage feedPage;
	LinkedinSearchResultsPage searchPage;
		
	private static String workbookPath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\testData.xlsx";

	@BeforeClass
	public void beforeClass() throws InterruptedException {

		log.info("creating login page object");
		homePage = new LinkedinHomePage();
		loginPage = new LinkedinLoginPage();
		feedPage = new LinkedinFeedPage();
		searchPage = new LinkedinSearchResultsPage();
	}
	
	@Test(description = "Navigate to login page",priority=0)
	public void navigateToLoginPageTest() throws InterruptedException {
		log.info("Navigating to login page");
		homePage.clickOnSigninLink();
	}

	@Test(description = "login to linkedin", priority=1)
	public void doLoginTest() throws InterruptedException, IOException {
		log.debug("entering username and password");
		feedPage = loginPage.doLogin(readPropertyValue("username"), readPropertyValue("password"));
		log.info("Login is Successful");
	}

	@Test(dependsOnMethods = { "doLoginTest" }, priority=2, dataProvider="getData")
	public void doPeopleSearchTest(String name) throws InterruptedException {
		log.debug("Entering search name: "+name);
		searchPage = feedPage.doPeopleSearch(name);
		Thread.sleep(3000);
		log.info("click on seeAllpeopleResultsLink");
		searchPage.clickOnSeeAllPeopleResultsLink();
		Thread.sleep(1000);
		log.info("verify the search results page title");
		Assert.assertTrue(searchPage.getSearchResultsPageTitle().contains("Search | LinkedIn"));
		log.info("fetching search results count for:"+name);
		long count=searchPage.getResultCount();
		log.info("count for "+name+" is:"+count);
		log.info("click on Home tab");
		searchPage.clickOnHomeTab();
		Thread.sleep(1000);
	}
	
	@DataProvider(name="getData")
	public Object[][] getTestData() throws IOException{
		Object[][] sheetname=new ExcelUtils().getTestData(workbookPath, "Sheet1");
		return sheetname;
	}
	
	
	@Test(dependsOnMethods= {"doLoginTest"},priority=3)
	public void doLogoutTest() throws InterruptedException {
		log.info("performing the logout from linkedin");
		Thread.sleep(2000);
		feedPage.doLogout();
	}
}