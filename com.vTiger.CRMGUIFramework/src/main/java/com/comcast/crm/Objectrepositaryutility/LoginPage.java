package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
//rule=1 create a seperate java class
	//rule=2 ObjectCreation
	//rule=3 Object Initialization
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(name="user_name")
	 private WebElement username;
	@FindBy(name="user_password")
	 private WebElement password;
	@FindBy(id="submitButton")
	 private WebElement login;
	//rule:4 Object Encapsulation
	public WebElement getUsername() {
		return username;
	}
	public WebElement getPassword() {
		return password;
	}
	public WebElement getLogin() {
		return login;
	}
	public void Login(String url,String username,String password) {
		driver.manage().window().maximize();
		driver.get(url);
		getUsername().sendKeys(username);
        getPassword().sendKeys(password);
        getLogin().click(); 
	}
}
