package com.insiderone.UI.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.insiderone.utilities.ConfigurationReader;
import com.insiderone.utilities.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

public class TestBase {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;

    protected static ExtentReports report;
    protected static ExtentSparkReporter htmlReporter;
    public static ExtentTest test;
    protected String url;
    protected String urlKey = "url";


    @BeforeTest
    public void setUpTest(){
        report = new ExtentReports();

        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/test-output/";
        new File(path).mkdirs();
        htmlReporter = new ExtentSparkReporter(path + "TestReports.html");
        report.attachReporter(htmlReporter);

        htmlReporter.config().setReportName("Insiderone Test");

        //set environment information
        report.setSystemInfo("Environment",ConfigurationReader.get("testEnvironment"));
        report.setSystemInfo("Browser",ConfigurationReader.get("browser"));
        report.setSystemInfo("Tester","Yurt7 Testers");
        report.setSystemInfo("OS",System.getProperty("os.name"));

    }


    @BeforeMethod
    public void setUpMethod(){
        System.out.println("Driver.testEnvironmentDetails.get(\"url\") = " + Driver.testEnvironmentDetails.get("url"));
        driver = Driver.get();
        driver.manage().window().maximize();
        Driver.get().manage().timeouts().getPageLoadTimeout();
        Driver.get().manage().deleteAllCookies();
        System.out.println("urlKey = " + urlKey);
        Driver.setTestEnvironment();  //This method will read the environment specific Data from configuration.json and load it into a statig hashmap testEnvironmentDetails.
        String targetUrl = Driver.testEnvironmentDetails.get(urlKey);

        System.out.println("targetUrl ="+targetUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions=new Actions(driver);
        wait=new WebDriverWait(driver,Duration.ofSeconds(15));
        driver.get(targetUrl);

        test = report.createTest(driver.toString());  // veya test class adını kullanın



    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws IOException, InterruptedException {
        if (result.getStatus() == ITestResult.FAILURE) {

            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String folderPath = "screenshots/" + dateFolder;
            new File(folderPath).mkdirs();

            String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
            String screenshotName = folderPath + "/" +
                    result.getName().replaceAll("[^a-zA-Z0-9]", "_") +
                    "_failed_" + timestamp + ".png";

            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            FileUtils.writeByteArrayToFile(new File(screenshotName), screenshot);
            String base64Image = Base64.getEncoder().encodeToString(screenshot);


            // ExtentReports'a EKLE
            String absolutePath = new File(screenshotName).getAbsolutePath();

            test.fail(result.getName() + " - FAILED");
            test.addScreenCaptureFromPath(screenshotName, "Screenshot");
            test.addScreenCaptureFromBase64String(base64Image, "Failure Screenshot");

            test.fail("Error: " + result.getThrowable());
        }
        Thread.sleep(2000);
        Driver.closeDriver();
    }




    @AfterTest
    public void tearDownTest(){
        //this is when the report is acually created
        report.flush();
    }

}
