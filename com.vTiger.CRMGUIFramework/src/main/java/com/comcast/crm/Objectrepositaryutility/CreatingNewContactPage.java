
package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {

	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(name="lastname")
	private WebElement lastName;
	@FindBy(xpath="//input[@id='jscal_field_support_start_date']")
	private WebElement startTime ;
	
	@FindBy(xpath="//input[@id='jscal_field_support_end_date']")
	private WebElement endTime ;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement org ;
	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement save;
	public WebElement getLastName() {
		return lastName;
	}
	
	public WebElement getStartTime() {
		return startTime;
	}

	public WebElement getEndTime() {
		return endTime;
	}
	
	public WebElement getOrg() {
		return org;
	}

	public WebElement getSave() {
		return save;
	}
	public void CreateContact(String lastName) {
		getLastName().sendKeys(lastName);
		getSave().click();
	}

}
