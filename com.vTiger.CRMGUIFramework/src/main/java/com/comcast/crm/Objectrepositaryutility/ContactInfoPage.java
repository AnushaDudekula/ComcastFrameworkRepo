package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	WebDriver driver;
	public ContactInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//span[@id='dtlview_Last Name']")
	private WebElement LastName;
	@FindBy(xpath="//span[@id='dtlview_Support Start Date']")
	private WebElement infoStartTime;
	@FindBy(xpath="//span[@id='dtlview_Support End Date']")
	private WebElement infoEndTime;
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement hearderText;
	@FindBy(xpath="//td[@id='mouseArea_Organization Name']")
	private WebElement orgName;
	public WebElement getLastName() {
		return LastName;
	}
	public WebElement getInfoStartTime() {
		return infoStartTime;
	}
	public WebElement getInfoEndTime() {
		return infoEndTime;
	}
	public WebElement getHearderText() {
		return hearderText;
	}
	public WebElement getOrgName() {
		return orgName;
	}
	
	
}
