package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest{
	
	@DataProvider(name="loginData")
	public Object[][] getLoginData() throws IOException{
		
	
			String filePath= "C:\\Users\\shett\\OneDrive\\Documents\\Automation\\selenium-framework\\testdata\\TestData.xlsx";
			ExcelUtils.loadExcel(filePath, "Sheet1");			
			int rowCount= ExcelUtils.getRowCount();
			Object[][] data= new Object[rowCount-1][2];
			
			for(int i=1; i<rowCount; i++) {
				data[i-1][0]= ExcelUtils.getCellData(i, 0);
				data[i-1][1]= ExcelUtils.getCellData(i, 1);
			}
			ExcelUtils.closeExcel();
			return data;
	}
	
	@DataProvider(name="loginData1")
	public Object[][] getData() {
		
	
		return new Object[][] {
			{"User1", "Password1"},
			{"User2", "Password2"},
			{"User3", "Password3"},
			
		};
	}
	
	
	@Test(dataProvider="loginData")
	//@Test
	//@Parameters({"username", "password"})
	public void testValidLogin(String username, String password) {
		
		Log.info("Starting test: testValidLogin");
		test = ExtentReportManager.createTest("testValidLogin" +username);
			
		LoginPage loginpage= new LoginPage(driver);
		Log.info("Adding username and password");
		test.info("Entering username and password");
//		loginpage.enterUserName("admin@yourstore.com");	
//		loginpage.enterPassword("admin");
		loginpage.enterUserName(username);
		loginpage.enterPassword(password);
		test.info("Clicking the login button");
		loginpage.clickLogin();
		
		System.out.println("Title of the page is : "+driver.getTitle());
		Log.info("Asserting the title of the page");
		test.info("Asserting the title of the page");
		Assert.assertEquals(driver.getTitle(), "Just a moment...");
		test.pass("testValidLogin passed");
		
	}

}

