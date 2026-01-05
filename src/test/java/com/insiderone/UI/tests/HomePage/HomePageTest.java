package com.insiderone.UI.tests.HomePage;

import com.insiderone.UI.pages.HomePages;
import com.insiderone.UI.tests.TestBase;
import com.insiderone.utilities.BrowserUtils;
import com.insiderone.utilities.Driver;
import com.insiderone.utilities.ExcelUtil;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

public class HomePageTest extends TestBase {

    HomePages homePage = new HomePages();

    protected int moduleNavigationDataNumber;
    protected String[][] dataArray;

    @BeforeClass
    public void setUpClass() {
        setUpMethod();
    }


    @DataProvider
    public Object[][] moduleNavigationData() {
        ExcelUtil getModuleNavigationData = new ExcelUtil("src/test/resources/uiTestData/ModulesOnHomePage.xlsx", "ModuleNavigationData");
        dataArray = getModuleNavigationData.getDataArrayWithoutFirstRow();
        moduleNavigationDataNumber = dataArray.length;
        return dataArray;
    }

    @Test(dataProvider = "moduleNavigationData")
    public void navigateToPartnersPage(String Module, String SubModule, String Title) {

        test = report.createTest("UI Task, first way for part 1 ");

        // Remove the FOR loop - DataProvider already iterates through all rows
        homePage.navigateToModule(Module, SubModule);
        BrowserUtils.waitForExactTitle(Driver.get(),Title,20);
        assertTrue(Driver.get().getTitle().contains(Title));
        test.pass("PASSED");
    }

    @Test
    public void testAllRowsTogether() {
        test = report.createTest("UI Task, Second way for part 1");
        // Loop through ALL rows in one browser
        moduleNavigationData();
        for (String[] row : dataArray) {
            String Module = row[0];
            String SubModule = row[1];
            String Title = row[2];

            homePage.navigateToModule(Module, SubModule);

            BrowserUtils.waitForExactTitle(Driver.get(),Title,30);
            assertTrue(Driver.get().getTitle().contains(Title));

            test.pass("PASSED");

        }
    }

}
