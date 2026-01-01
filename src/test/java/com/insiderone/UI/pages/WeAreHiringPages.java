package com.insiderone.UI.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WeAreHiringPages extends BasePage{

    @FindBy(xpath = "//a[contains(text(),'See all QA jobs')]")
    public WebElement getSeeAllQAJobs;

    @FindBy(id="filter-by-location")
    public WebElement getAllLocation;

    @FindBy(id="filter-by-department")
    public WebElement getFilterAllDepartment;

    @FindBy(css="#career-position-list div[data-animated='true']")
    public List<WebElement> getJobListDisplayed;

    @FindBy(css="#career-position-list div[data-animated='true'] p")
    public List<WebElement> getJobPosition;

    @FindBy(css="#career-position-list div[data-animated='true'] span")
    public List<WebElement> getJobDepartment;


    @FindBy(css="#career-position-list div[data-animated='true'] div")
    public List<WebElement> getJobLocation;


    @FindBy(css="#career-position-list div[data-animated='true'] a")
    public List<WebElement> getViewRoleBtn;

    @FindBy(xpath = "//h2")
    public WebElement getOneRoleTitle;




}
