
# Selenium Java TestNG UI Automation Framework POC

This is a **UI automation framework** developed using **Selenium WebDriver**, **Java**, **TestNG**, and **Maven**. It is designed to support cross-browser testing with **LambdaTest**, nightly CI/CD integration using **GitHub Actions**, and supports **data-driven testing** using various file formats.

## About Me

- Hi, my name is Pratik Gupte and I have 8 years of experience working as a QA Engineer, including 2 years in Automation Testing using tools like Selenium Webdriver,RobotFramework, Copado Robotic Testing, RestAssured.


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/pgupteqa/)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/pratik-gupte-19145156/)



## Prerequisites

Before running this framework, ensure the following software is installed on your system:
- Java 17 - Make sure Java is installed and the JAVA_HOME environment variable is set.
- Maven - Ensure Maven is installed and added to the system path.
- Downlod Link: https://maven.apache.org/download.cgi

## Key Features

- Built with Java + Selenium WebDriver
- TestNG as the test runner and suite manager
- Data-driven testing from **CSV, Excel, and JSON**
- Test data generation using **Java Faker**
- Modular code using **Page Object Model (POM)** and **POJO classes**
- Maven build with configurable test parameters
- Logging with **Log4j2** for detail logs
- Reporting with **ExtentReports**
- Cloud-based parallel execution on **LambdaTest**
- Faster tests execution by running tests in **Headless mode**.
- Nightly test execution using **GitHub Actions**

## Tech Stack

- **Language**: Java 17
- **Automation**: Selenium WebDriver 4.34.0
- **Test Framework**: TestNG 7.11.0
- **Build Tool**: Maven 
- **Data Readers**: Gson, OpenCSV, Apache POI
- **Faker Library**: javafaker 1.0.2
- **Logging**: Log4j2 (v2.25.1)
- **Cloud Grid**: LambdaTest
- **CI/CD**: GitHub Actions
- **Reporting**: ExtentReport and Github Pages

## Setup Instruction

**Clone the Repository**

```bash
  git clone https://github.com/pgupteqa/Selenium-Java-TestNG-UI-Automation-Framework-POC.git
  cd Selenium-Java-TestNG-UI-Automation-Framework-POC

```
**Running Tests on Local Machine**

```bash
  mvn test -Dbrowsername=chrome  -DisLambdaTest=false -DisHeadLess=false -Denvname=QA -Dsuitexml.file=testsuites/RegressionTests.xml

```
**Running Tests on Local Machine in Headless Mode**

```bash
  mvn test -Dbrowsername=chrome -DisLambdaTest=false -DisHeadLess=true -Denvname=QA -Dsuitexml.file=testsuites/RegressionTests.xml

```
**Running Tests on LambdaTest**

```bash
  mvn test -Dbrowsername=chrome -DisLambdaTest=true -DisHeadLess=false -Denvname=QA -Dsuitexml.file=testsuites/RegressionTests.xml

```

**Running Tests on LambdaTest or Headless using a specific group tags**

```bash
  mvn test -Dbrowsername=chrome -DisLambdaTest=true -DisHeadLess=false -Denvname=QA -Dsuitexml.file=testsuites/RegressionTests.xml -Dgroups=regression

```

```bash
  mvn test -Dbrowsername=chrome -DisLambdaTest=false -DisHeadLess=true -Denvname=QA -Dsuitexml.file=testsuites/RegressionTests.xml -Dgroups=regression

```






    
## Project Files Structure



```bash
├───src
│   └───test
│       ├───java
│       │   └───com
│       │       ├───constants/ # Static constants and enums
│       │       ├───ui
│       │       │   ├───dataproviders/ # Data providers for TestNG (CSV, Excel, JSON)
│       │       │   ├───listeners/ # TestNG listeners for custom logging/reporting
│       │       │   ├───pages/ # Page Object Model classes
│       │       │   ├───pojo/ # POJO classes for structured test data
│       │       │   └───tests/ # Test classes
│       │       └───utility/ # Common reusable utilities (LoggerUtility, BrowserUtility, LambdaTestUtility, etc)
│       └───resources/ # log4j.xml
├───testData/ # Storing Testdata files in json, csv and xlsx
└───testsuites/ # testng xml suite files
├───config/ # Environment-specific config files
├───logs/ # Execution logs
├───screenshot/ # Failure screenshots
├───pom.xml/ # Maven dependencies and build configuration
├───report.html/ # Extent report output
├───Readme.md/ # Project documentation
```


## Data Driven Testing

This framework implements multiple TestNG @DataProvider methods to feed test data dynamically from various sources:

- JSON → Loaded with Gson to deserialize into POJOs (User, InvalidUsers, Registration).
- CSV → Read via a custom CSVReaderUtility.
- Excel → Read via a custom ExcelReaderUtility.
- Centralized Config → File paths managed in TestDataFileName.properties using PropertiesUtil




## Reports & Logs

- Reports: After execution, a detailed HTML report will be generated at ./report.html.
- The report contains information on test cases executed, passed, failed, and skipped, along with screenshots for failed tests.

## Logs:
- Logs are created during the test execution and stored in the ./logs/ directory.

## LambdaTest Integration

To run tests on the cloud:
- Set the following environment variables (or GitHub Secrets):

```bash
LT_USERNAME
LT_ACCESS_KEY

```

- Note: In this POC I have used the GitHub Secrets to store these values.

- Ensure isLambdaTest=true in the Maven command.


## Integrated the Project using GitHub Actions

- This automation framework is integrated with github actions. The tests will be executed at **11:30PM IST** every single day.

- Trigger on push & pull requests to master branch.

- The reports will be archieved in gh-pages branch You can view the html reports at :
    https://pgupteqa.github.io/Selenium-Java-TestNG-UI-Automation-Framework-POC/report.html
