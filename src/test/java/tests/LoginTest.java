package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest{
	@Test
	public void testValidLogin() {
		LoginPage loginpage= new LoginPage(driver);
		loginpage.enterUserName("admin@yourstore.com");
		loginpage.enterPassword("admin");
		loginpage.clickLogin();
		
		System.out.println("Title of the page is : "+driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
		
	}

}
