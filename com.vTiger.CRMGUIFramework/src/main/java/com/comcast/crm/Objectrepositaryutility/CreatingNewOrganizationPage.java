package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	@FindBy(name="industry")
	private WebElement industry;
	@FindBy(xpath="//input[@id='phone']")
	private WebElement phoneNo;
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement org;
	
	
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}
	public WebElement getIndustry() {
		return industry;
	}

	public WebElement getPhoneNo() {
		return phoneNo;
	}
	
	public WebElement getOrg() {
		return org;
	}
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveButton.click();
	}
	public void createOrg(String orgName,String Industry) {
		orgNameEdt.sendKeys(orgName);
		Select s=new Select(industry);
		s.selectByVisibleText(Industry);
		saveButton.click();
	}
	public void createOrgWithPhoneNumber(String orgName,String phoneNumber) {
		orgNameEdt.sendKeys(orgName);
		phoneNo.sendKeys(phoneNumber);
		saveButton.click();
	}
}