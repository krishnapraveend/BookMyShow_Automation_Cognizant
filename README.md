# BookMyShow_Automation_Cognizant
This project is a Selenium TestNG automation framework developed to test key functionalities of the BookMyShow website. It is structured using the Page Object Model (POM) design pattern, which improves code readability, modularity, and ease of maintenance. It serves as a scalable baseline automation suite with a clean structure, reusable utilities, and easy integration for future modules like payments and bookings.

Key highlights of the framework:
🚀 Selenium WebDriver Integration – Automates end-to-end test flows on modern browsers.
🧩 Page Object Model (POM) – Ensures reusability, readability, and separation of concerns.
🖥 Cross-Browser & Parallel Execution – Tests can be executed seamlessly across Chrome, Edge, and Firefox.
⏱ Smart Wait Handling – Uses WebDriverWait, FluentWait, and optimized JS-based click handling.
📊 ExtentReports Integration – Generates rich, interactive reports with step-level logging, screenshots, and consolidated cross-browser results.
🔧 Configurable Setup – Driven by property files for browser, environment, and other parameters.
🛠 TestNG Features – Supports grouping, parameterization, dependencies, and data-driven testing.

This framework is ideal for functional testing, smoke/sanity validation, and regression suites, with the ability to easily extend towards CI/CD pipelines.

## 🛠 Tech Stack  **

- *Language:* Java  
- *Automation Tool:* Selenium
- *Testing Framework:* TestNG
- *Design Pattern:* Page Object Model (POM) 
- *Reporting:* ExtentReports  
- *Build Tool:* Maven
- *IDE*: Eclipse/IntelliJ


*Framework Structure*
📦 project-root
│
├── 📁 src
│   ├── 📁 main
│   │   ├── 📁 java
│   │   │   ├── 📁 base                  # Base setup classes
│   │   │   │   ├── ConfigLoader.java
│   │   │   │   └── DriverSetup.java
│   │   │   │
│   │   │   ├── 📁 constants             # Framework constants
│   │   │   │   └── FrameworkConstants.java
│   │   │   │
│   │   │   ├── 📁 locators              # Object repository handler
│   │   │   │   └── LocatorRepository.java
│   │   │   │
│   │   │   ├── 📁 pages                 # Page Object Model (POM) classes
│   │   │   │   ├── CityPage.java
│   │   │   │   ├── GiftCardPage.java
│   │   │   │   ├── LoginPage.java
│   │   │   │   └── MoviePage.java
│   │   │   │
│   │   │   ├── 📁 reports               # Report generation & listeners
│   │   │   │   ├── ExtentManager.java
│   │   │   │   └── ExtentTestNGListener.java
│   │   │   │
│   │   │   └── 📁 utils                 # Utility classes
│   │   │       ├── ExcelUtils.java
│   │   │       ├── LocatorReader.java
│   │   │       ├── ScreenshotUtil.java
│   │   │       └── WaitUtils.java
│   │   │
│   │   └── 📁 resources                 # Configuration and locator files
│   │       ├── config.properties
│   │       └── locator.properties
│   │
│   └── 📁 test
│       ├── 📁 java
│       │   ├── 📁 runners               # Test suite runner

│       │   │   └── TestSuiteRunner.java
│       │   │
│       │   └── 📁 tests                 # Test classes
│       │       ├── CityTest.java
│       │       ├── GiftCardTest.java
│       │       ├── LoginTest.java
│       │       ├── MovieTest.java
│       │       └── qeaBMS.java
│       │
│       └── 📁 resources                 # Test resource files (if any)
│
├── 📁 test-output                      # TestNG generated reports
│
├── 📄 pom.xml                          # Maven project file
├── 📄 testng.xml                       # TestNG suite configuration



View Reports
After execution, detailed HTML reports are generated in:
/test-output/ExtentReports
