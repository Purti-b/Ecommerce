# 🛒 Selenium E-commerce Automation Framework

**Purpose:** A Java‑based Selenium automation framework built using the Page Object Model (POM) and TestNG. Designed to mimic real user journeys (login, product search, cart checkout, order placement) for learning and future maintainability.

---

## 🔧 Prerequisites

- Java 11+ installed (`java -version`)
- Maven for project build (`mvn -v`)
- ChromeDriver (or other WebDriver binaries) available in PATH
- IDE support (IntelliJ IDEA, Eclipse, VS Code, etc.)

---

## 📁 Project Structure

main/java
├── SeleniumFramework
│ ├── PageObject
│ │ ├── CartPage.java
│ │ ├── CheckOutPage.java
│ │ ├── LandingPage.java
│ │ ├── PlacedOrderPage.java
│ │ └── ProductCatalogue.java
│ ├── ReusableComponent
│ │ └── ReusableComponent.java
│ └── Resources
│   └── GlobalData.properties

test/java
└── SeleniumFramework
├── TestComponents
│ └── BaseTest.java
└── Tests
    ├── ErrorValidationTest.java
    ├── StandAloneTest.java
    └── SubmitOrderTest.java

src/testSuites
├──testng.xml
└──errorValidationTestng.xml

pom.xml

- **PageObject**: Encapsulates all page-related UI logic using WebElements and methods.
- **ReusableComponent**: Utility methods, wait strategies, common flows.
- **GlobalData.properties**: External config vals like browser type and base URL.
- **TestComponents/BaseTest**: Sets up WebDriver, initializes pages, and implements `@BeforeMethod`/`@AfterMethod`.
- **Tests/**: TestNG test cases for specific flows (submission, error validation, etc.).
- **testng.xml**: Configures parallel test execution.

---

## 🚦 Setup & Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/Purti-b/Ecommerce.git
   cd Ecommerce
Configure environment

In GlobalData.properties, set:


Browser=chrome
baseUrl=<your-app-url>
Compile and run tests


mvn clean test -DsuiteXmlFile=testng.xml
Or run individual classes via your IDE’s TestNG runner.

Parallel execution

Configured in testng.xml with parallel="tests" and thread-count="5".

🧩 How Everything Connects
Test classes extend BaseTest.

@BeforeMethod in BaseTest calls initializeDriver() → LandingPage.

Tests use POM methods:

Login via LandingPage

Browse products in ProductCatalogue

Use goToCart() → returns CartPage

Complete checkout via CheckOutPage

Verify success on PlacedOrderPage

ReusableComponent utilities (implicit/explicit waits, JavaScript click) ensure stability.

🧪 Test Coverage
SubmitOrderTest: Full user checkout flow.

ErrorValidationTest: Verifies validation messages.

StandAloneTest: Possibly demo or other scenario (check for custom logic).

🔁 Future Revisit Tips
Start at BaseTest to understand how tests initialize.

Review Page Objects to see where actions (clicks, form entries) are.

Inspect ReusableComponent to trace wait logic and shared helpers.

Explore Tests to follow full end-to-end flows.

Modify GlobalData.properties when updating environment or browser preferences.

Consider sketching a quick flow diagram:


BaseTest → LandingPage → ProductCatalogue → CartPage → CheckOutPage → PlacedOrderPage