Selenium Tests in Java
-------------------

### Requirements to run tests:

* Java Version >= 8 (JDK)
* Chrome / firefox browser should be installed
* mvn is optional as maven wrapper is installed
* To run tests in chrome
    * `export TARGET_ENV=qa && export APP_USERNAME=standard_user && export APP_PASSWORD=secret_sauce && ./mvnw clean install`
* To run tests in firefox
    * `export TARGET_ENV=dev && export APP_USERNAME=standard_user && export APP_PASSWORD=secret_sauce && ./mvnw clean install`

### Features included in the automation test:

* Test data is read from a CSV file
* Page Object Factory design pattern is used to develop tests
* Environment variables are used for passing sensitive data
* Developed code keeping separation of concerns design principle in mind

### Test scenario

* Login to a shopping website
* Add items to shopping cart
* Review shopping cart items
* Enter customer information before checkout
* Verify the total amount of shopping
