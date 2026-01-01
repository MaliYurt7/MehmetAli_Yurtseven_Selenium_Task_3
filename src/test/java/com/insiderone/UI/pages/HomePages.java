package com.insiderone.UI.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePages extends BasePage {


    @FindBy(css ="div.homepage-hero-content button.redirect-button")
    public WebElement getADemoButton;

    @FindBy(css ="[id='email']")
    public WebElement typeBusinessEmail;



    @FindBy(css ="div.homepage-hero-content label p")
    public WebElement emailContainer;

}
