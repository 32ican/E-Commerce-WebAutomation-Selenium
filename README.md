# TricentisWebShop Automation Framework

## Overview

The TricentisWebShop Automation Framework is a comprehensive testing solution built using Selenium WebDriver and TestNG. It provides a structured and scalable approach to automate the testing of web applications, ensuring robustness and reliability.

## Features

- Supports parallel execution for faster testing
- Generates detailed HTML reports using ExtentReports
- Modular and maintainable test scripts
- Easy configuration with the `config.properties` file
- Integration with TestNG for test execution and management

## Prerequisites

Ensure the following tools and libraries are installed on your system:

- Java JDK 17 or higher
- Maven
- Selenium WebDriver
- TestNG
- ExtentReports

## Setup

1. Clone the repository to your local machine.
2. Update the `config.properties` file with your application details.
3. Install dependencies using Maven: `mvn clean install`

## Running Tests

Execute tests using one of the following methods:

- Run tests with TestNG: `mvn test`
- Run tests from your IDE
- Run tests in parallel for faster execution

## Extent Reports

The framework generates detailed HTML reports located in the `test-output/reports` directory. These reports include comprehensive information about test execution steps, along with screenshots for better analysis.

## Test Cases

### Valid Registration Tests
1. **TC001_ValidRegisterTest**: Verifies successful registration of a new user with valid details.

### Invalid Registration Test
2. **TC002_InValidRegister**: Validates the behavior of the registration process when invalid data is provided.

### Valid Login Test
3. **TC003_ValidLoginTest**: Ensures successful login with valid credentials.

### Invalid Login Tests
4. **TC004_InvalidPasswordLoginTest**: Validates the login process when an invalid password is provided.
5. **TC005_InvalidUserNameLoginTest**: Tests the login functionality with an invalid username.

### Cart Management Test
6. **TC006_AddingProductToCartTest**: Checks the ability to add products to the shopping cart.

### Discount Code Test
7. **TC007_DicountCodeTest**: Verifies the application of discount codes during checkout.

### Terms of Service Test
8. **TC008_TermsOfServiceTest**: Validates the acceptance of terms of service during checkout.

### Billing Address Mandatory Test
9. **TC009_BillingAdressMandatoryTest**: Verifies if the billing address is mandatory during checkout.

### End-to-End Test
10. **TC010_E2ETest**: An end-to-end test scenario covering multiple functionalities from registration to checkout.
