package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
@FindBy(xpath="//img[@title='Create Organization...']")
private WebElement createNewOrg;
@FindBy(name="search_text")
private WebElement search;
@FindBy(name="search_field")
private WebElement searchDropDown;
@FindBy(name="submit")
private WebElement searchButton;

public WebElement getSearchButton() {
	return searchButton;
}
public WebElement getSearch() {
	return search;
}
public WebElement getSearchDropDown() {
	return searchDropDown;
}
public WebElement getCreateNewOrg() {
	return createNewOrg;
}

}
