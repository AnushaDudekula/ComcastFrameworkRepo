package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindAll;
public class OraganizationInfoPage {
	WebDriver driver;
	public OraganizationInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerMSG;
	@FindAll({@FindBy(xpath="//span[@id='dtlview_Organization Name']"),@FindBy(id="mouseArea_Organization Name")})
	private WebElement OrganizationName;
	@FindBy(xpath="//span[@id='dtlview_Phone']")
	private WebElement PhoNo;
	@FindBy(xpath="//span[@id='dtlview_Industry']")
	private WebElement industry;

	public WebElement getHeaderMSG() {
		return headerMSG;

	}
	public WebElement getOrganizationName() {
		return OrganizationName;
	}
	public WebElement getPhoNo() {
		return PhoNo;
	}
	public WebElement getIndustry() {
		return industry;
	}
	
	

}
