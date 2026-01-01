package com.insiderone.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.*;

public class Driver {
    public static SoftAssert softAssert = new SoftAssert();

    private Driver() {
    }

    // InheritableThreadLocal  --> this is like a container, bag, pool.
    // in this pool we can have separate objects for each thread
    // for each thread, in InheritableThreadLocal we can have separate object for that thread
    // driver class will provide separate webdriver object per thread
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();
    public static HashMap<String, String> testEnvironmentDetails = new HashMap<>();

    public static String downloadFilepath = System.getProperty("user.home") + File.separator + "Sample" + File.separator + "TestFilePath";

    public static String backupFilePath = System.getProperty("user.home") + File.separator + "Sample" + File.separator + "BackupFilePath";

    public static String userHome = System.getProperty("os.home");
    public static  String testEnvironment;


    public static WebDriver get() {
        if (driverPool.get() == null) {
            String browser = System.getProperty("browser") != null ? System.getProperty("browser") : ConfigurationReader.get("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().browserVersion("latest").setup();
                    driverPool.set(new ChromeDriver(setChromeOptions(false)));
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().browserVersion("latest").setup();
                    driverPool.set(new ChromeDriver(setChromeOptions(true)));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(setFirefoxOptions(false)));
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(setFirefoxOptions(true)));
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver(setEdgeOptions()));
                    break;
                case "edge-headless":
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver(setEdgeOptions()));
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.safaridriver().setup();
                    driverPool.set(new SafariDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
        return driverPool.get();
    }

    private static ChromeOptions setChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> preferences = new Hashtable<>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.prompt_for_download", "false");
        preferences.put("download.default_directory", downloadFilepath);
        options.setExperimentalOption("prefs", preferences);
        options.addArguments("--remote-allow-origins=*");
        if (headless) options.addArguments("--headless=new");
        return options;
    }

    private static FirefoxOptions setFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        options.getClass();
        return options;
    }

    private static EdgeOptions setEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> preferences = new Hashtable<>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.prompt_for_download", "false");
        preferences.put("download.default_directory", downloadFilepath);
        preferences.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1); // For multiple file download popup handling

        options.setExperimentalOption("prefs", preferences);

        // Handle profile selection
        options.addArguments("user-data-dir=" + userHome + "//EdgeUserData"); // Use the default profile
        options.addArguments("profile-directory=Profile 1");

        // Add headless mode argument
        options.addArguments("--headless"); // Enable headless mode
        options.addArguments("--disable-gpu"); // Disable GPU for better compatibility
        options.addArguments("--remote-allow-origins=*"); // Allow cross-origin requests


        return options;
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            try {
                Driver.get().manage().deleteAllCookies();
                driverPool.get().close();
                driverPool.get().quit();
                driverPool.remove();
            } catch (org.openqa.selenium.NoSuchSessionException e) {
                System.out.println("session already dead, ignore");
            } finally {
                driverPool.remove();
            }
        }
    }

    public static void setTestEnvironment() {
        System.out.println("System.getProperty(\"browser\") = " + System.getProperty("browser"));
        testEnvironment = System.getProperty("browser") != null ? testEnvironment = System.getProperty("testEnvironment") : ConfigurationReader.get("testEnvironment");
        testEnvironmentDetails = ConfigurationReader.getTestEnvironmentDetails(testEnvironment);
        testEnvironmentDetails.put("webdriverFileDownloadPath", downloadFilepath);
    }
}
