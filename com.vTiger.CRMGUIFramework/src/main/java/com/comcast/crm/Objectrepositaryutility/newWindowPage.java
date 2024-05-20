package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class newWindowPage {

	WebDriver driver;
	public newWindowPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
@FindBy(xpath="//input[@id='search_txt']")
private WebElement searchbox;
@FindBy(name="search")
private WebElement search;
public WebElement getSearchbox() {
	return searchbox;
}
public WebElement getSearch() {
	return search;
}

}
