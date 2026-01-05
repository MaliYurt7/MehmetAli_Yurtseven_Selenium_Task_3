package com.insiderone.UI.pages;

import com.insiderone.utilities.BrowserUtils;
import com.insiderone.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

abstract class BasePage {


    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "a.btn.btn-text")
    public WebElement getLoginBtn;

    @FindBy(css = "div.header-top-action-lang button i.icon-arrow")
    public WebElement getLanguagaBtn;

    /**
     *
     * @param language ==> target language
     * @return WebElement
     */
    public WebElement getDesiredLanguage(String language){
        WebElement getContainer = Driver.get().findElement(By.cssSelector("div.header-top-action-lang")); // More specific
        return getContainer.findElement(By.xpath(".//a[text()='"+language+"']"));
    }

    /**
     *
     * @param buttonName ==> "Platform Tour", "Get a Demo"
     * @return
     */
    public WebElement getHeaderMenuActionBtns(String buttonName){
        String desiredButton="//button[normalize-space()='"+buttonName+"']";
        return Driver.get().findElement(By.xpath(desiredButton));
    }

    /**
     *covers Partners, Insider One Academy, Help Center, Contact Us
     *covers Platform, Industries, Customers, Resources (Partially)
     * @param tab
     * @param module
     */

    public void navigateToModule(String tab, String module) {// switch between modules
        String tabLocator = "[data-text*='"+tab+"']";
        String moduleLocator = "ul [data-text='"+module+"']";
        try {
            BrowserUtils.waitForClickablility(By.cssSelector(tabLocator), 5);
            WebElement tabElement = Driver.get().findElement(By.cssSelector(tabLocator));
            new Actions(Driver.get()).moveToElement(tabElement).pause(200).click(tabElement).build().perform();
        } catch (Exception e) {
            BrowserUtils.clickWithWait(By.cssSelector(tabLocator), 5);
        }
        if(!module.isEmpty()){
            try {
                BrowserUtils.waitForPresenceOfElement(By.cssSelector(moduleLocator), 5);
                BrowserUtils.waitForVisibility(By.cssSelector(moduleLocator), 5);
                BrowserUtils.scrollToElement(Driver.get().findElement(By.cssSelector(moduleLocator)));
                Driver.get().findElement(By.cssSelector(moduleLocator)).click();
            } catch (Exception e) {
                BrowserUtils.clickWithTimeOut(Driver.get().findElement(By.cssSelector(moduleLocator)), 5);
            }
        }
    }




}
