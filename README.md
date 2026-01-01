# PROJECT NAME
Insiderone_UI_API_Automation

This is a Maven project for testing https://insiderone.com/ using Selenium, TestNG, RestAssured, and Java.

# Tools
Based on the requirement, the following tools and framework were selected to create the automation framework:

- Programming Language: Java
- Build Tool: Maven
- Testing Framework: TestNG
- API Testing: Rest Assured
- IDE - Intellij IDEA

# Design
### Folder Structure
The project has the following folders:

- `testng_runner_${}_.xml` files: xml files which specifies the configuration and location of folder, files which are responsible for handling and managing test cases for test execution.
- `utilities` folder: Contains the utility class `ConfigurationReader.java`, which provides methods to read configuration properties from the `configuration.properties` and `configuration.json`
- `resources` folder: Contains `ModulesOnHomePage.xlsx` file for DataProvider testing for one of the UI scenarios and also having `get-json-schema-Pet.json` file for one of the API schema scenarios.
- `pom.xml` file: Contains required dependencies and plugins

#### Dependencies
The project's `pom.xml` file includes the following dependencies & plugins:

- `webdrivermanager` v.6.3.3
- `selenium` v.4.30.0
- `testng` v.7.11.0
- `gson` v.2.13.1
- `rest-assured` v.5.5.6
- `json-schema-validator` v.5.5.6
- `json-simple` v.1.1
- `extentreports` v.5.1.2
- `json` v.20250517
- `javafaker` v.1.0.2
- `logback-classic` v.1.5.16
- `lombok` v.1.18.42
- `poi-ooxml` v.5.5.1




# Development
The code implementation for the test scenarios is done in accordance with the requirements and design. Based on the provided scenarios positive and negative, are developed to cover the API and UI test cases. Care has been taken to ensure readability, maintainability, and re-usability of the code.


### Running instructions
* Before UI or API test are triggered, go to the `configuration.properties` file and `testEnvironment` key should get `UI` or`API` value
*JDK and Maven required to run with below code*
- Download .zip file and 'Archive utility'
- navigate to root directory
```
cd C:\Path\to\Project
```

- run following command
```
mvn test
```
or
- each test can be triggered inside of the class.
```
hover the mouse such as on `testng_runner_1_PackageLevel.xml` file and right click and click the Run icon on the opening section
```

### Notes on Further Improvements

In addition to the API-focused tests I developed, there is a area I’d consider exploring to expand and
improve overall coverage:

- I’d consider adding some **UI and DB check against API responses** to make sure that values retrieved and shown on the API match with UI and DB side


- To get allure report , allure serve allure-results should be run on the terminal
- The failure tests screenshots can be found under the `screenshots` folder
