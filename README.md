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


