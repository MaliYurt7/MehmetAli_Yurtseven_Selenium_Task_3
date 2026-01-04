package com.insiderone.UI.tests.Footer;

import com.insiderone.UI.pages.WeAreHiringPages;
import com.insiderone.UI.tests.TestBase;
import com.insiderone.utilities.BrowserUtils;
import com.insiderone.utilities.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.insiderone.utilities.Driver.testEnvironmentDetails;

public class WeAreHiringTest extends TestBase {

    WeAreHiringPages weAreHiringPages = new WeAreHiringPages();

    @BeforeClass
    public void overrideUrlKey() {
        urlKey = "careersUrl";
    }


    /**
     * Go to https://insiderone.com/careers/quality-assurance/,
     * click “See all QA jobs”
     * filter jobs by Location - Istanbul, Turkey
     * department - Quality Assurance,
     * check presence of jobs list
     * <p>
     * Expected:
     * 1 job in exact location named "Istanbul, Turkey"
     * job name == "Software Quality Assurance Engineer (Remote)"
     * job Type == "Quality Assurance"
     * Job Location == "Istanbul, Turkey"
     * "View Role" button should display
     */
    @Test
    public void qaCareersTest() {
        test = report.createTest("UI Task, part 2,3 and 4");

        BrowserUtils.waitForVisibility(weAreHiringPages.getSeeAllQAJobs, 10);
        weAreHiringPages.getSeeAllQAJobs.click();


        BrowserUtils.getDomCompleted();
        BrowserUtils.waitForPresenceOfElement(By.cssSelector("#career-position-list div[data-animated='true']"), 100);
        //BrowserUtils.waitForVisibility(weAreHiringPages.getJobListDisplayed.get(0),100);

        BrowserUtils.verifyDDLNotEmptyAndContainsSpecificValue(weAreHiringPages.getAllLocation, "Istanbul, Turkiye");
        System.out.println("weAreHiringPages.getAllLocation.getText() = " + weAreHiringPages.getAllLocation.getAttribute("value"));
        System.out.println("BrowserUtils.getFirstSelectedOptionFromSelectTag(weAreHiringPages.getAllLocation) = " + BrowserUtils.getFirstSelectedOptionFromSelectTag(weAreHiringPages.getAllLocation));
        Assert.assertTrue(BrowserUtils.getFirstSelectedOptionFromSelectTag(weAreHiringPages.getAllLocation).contains("Istanbul, Turkiye"));

        BrowserUtils.verifyDDLNotEmptyAndContainsSpecificValue(weAreHiringPages.getFilterAllDepartment, "Quality Assurance");
        Assert.assertTrue(BrowserUtils.getFirstSelectedOptionFromSelectTag(weAreHiringPages.getFilterAllDepartment).contains("Quality Assurance"));

        System.out.println("weAreHiringPages.getFilterAllDepartment = " + weAreHiringPages.getFilterAllDepartment.getAttribute("value"));
        System.out.println("BrowserUtils.getFirstSelectedOptionFromSelectTag(weAreHiringPages.getFilterAllDepartment) = " + BrowserUtils.getFirstSelectedOptionFromSelectTag(weAreHiringPages.getFilterAllDepartment));
        BrowserUtils.waitFor(3);

//     Expected:
//     * 1 job in exact location named "Istanbul, Turkey"
//     * job Position == "Software Quality Assurance Engineer (Remote)"
//     * job Department == "Quality Assurance"
//     * Job Location == "Istanbul, Turkey"
//     * "View Role" button should display
        int expectedNumOfJobDisplayed = 1;
        String expectedJobPosition = "Software Quality Assurance Engineer (Remote)";
        String expectedJobDepartment = "Quality Assurance";
        String expectedJobLocation = "Istanbul, Turkiye";
        String expectedViewRoleBtnText = "View Role";


        System.out.println("weAreHiringPages.getJobListDisplayed.size() = " + weAreHiringPages.getJobListDisplayed.size());
        Assert.assertEquals(weAreHiringPages.getJobListDisplayed.size(), expectedNumOfJobDisplayed);
        Assert.assertTrue(weAreHiringPages.getJobPosition.get(0).getText().contains(expectedJobPosition));
        Assert.assertTrue(weAreHiringPages.getJobDepartment.get(0).getText().contains(expectedJobDepartment));
        Assert.assertTrue(weAreHiringPages.getJobLocation.get(0).getText().contains(expectedJobLocation));
        Assert.assertTrue(weAreHiringPages.getViewRoleBtn.get(0).getText().contains(expectedViewRoleBtnText));


/*
Click “View Role” button and check that this action redirects us to Lever
Application form page
 */
        weAreHiringPages.getViewRoleBtn.get(0).click();

        BrowserUtils.switchToWindow("Insider One - Software Quality Assurance Engineer (Remote)");
        BrowserUtils.getDomCompleted();
        Assert.assertTrue(testEnvironmentDetails.get("jobsLeverUrl").contains(Driver.get().getCurrentUrl()));
        Assert.assertTrue(testEnvironmentDetails.get("qaJobTitle").contains(Driver.get().getTitle()));
        Assert.assertTrue(weAreHiringPages.getOneRoleTitle.getText().contains(expectedJobPosition));
        test.pass("PASSED");

    }


    @Test
    public void getNumberOfIstanbuCityTest() { //negatif testing purpose
        test = report.createTest("UI Task, checking no Dublicating Istnabul city name in the location Dropdown");

        BrowserUtils.waitForVisibility(weAreHiringPages.getSeeAllQAJobs, 10);
        weAreHiringPages.getSeeAllQAJobs.click();


        BrowserUtils.getDomCompleted();
        BrowserUtils.waitForPresenceOfElement(By.cssSelector("#career-position-list div[data-animated='true']"), 100);

        weAreHiringPages.getAllLocation.click();
        int noOfIstanbulInDrpdown=1;
        int getNoOfistanbulInDropdown = weAreHiringPages.getNoOfSameCityNameInDrpdwn("Istanbul").size();
        Assert.assertEquals(getNoOfistanbulInDropdown, noOfIstanbulInDrpdown);
        test.pass("PASSED");

    }
}
