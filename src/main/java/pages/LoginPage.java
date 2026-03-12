package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Log;

public class LoginPage {
	
private WebDriver driver;

@FindBy(id="Email")
WebElement UserNameTextBox;
@FindBy(id="Password")
WebElement PasswordTextBox;
@FindBy(xpath="//button[@class='button-1 login-button']")
WebElement LoginButton;

//private By UserNameTextBox = By.id("Email");
//private By PasswordTextBox = By.id("Password");
//private By LoginButton =By.xpath("//button[@class='button-1 login-button']");

//create a constructor
public LoginPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

public void enterUserName(String username) {
	
	UserNameTextBox.clear();
	UserNameTextBox.sendKeys(username);	
//	driver.findElement(UserNameTextBox).clear();
//	driver.findElement(UserNameTextBox).sendKeys(username);
	
}


public void enterPassword(String password) {
	
	PasswordTextBox.clear();
	PasswordTextBox.sendKeys(password);
//	driver.findElement(PasswordTextBox).clear();
//	driver.findElement(PasswordTextBox).sendKeys(password);
	
}


public void clickLogin() {
	
	Log.info("Clicking the login button");
	LoginButton.click();
	//driver.findElement(LoginButton1).click();
}
}
