# cucumber-api-tests-poc

Initial version of POC for the API test automation framework in BDD manner using `Java`, `Cucumber`, `Rest Assured`, `Maven` and `Junit 4`.

Some POJO classes use `Lombok` for better code readability, but some of them do not and implement getters, setters, constructors and builders in a classic way.

`PicoContainer` was used for dependency injection.

### AUT
Is a randomly found Pet Store application: https://petstore.swagger.io/

### Project structure

Feature files:
```
/src/test/resources/features
```

Step definitions:
```
/src/test/java/stepdefinitions
```

AUT segregated to "step objects" to encapsulate common behavior:
```
/src/test/java/services/petstore
```
Each step definition class extends the corresponding "step objects" class.



Base Rest Assured `RequestSpecification` initialization happens in:
```
src/test/java/base/RequestSpecificationFactory.java
```

Custom wrapped HTTP methods:
```
src/test/java/base/HttpRequest.java
```

Common used methods:
```
src/test/java/base/BaseApi.java
```


### Tests execution

Scenarios executed in parallel.

1. Locally via Maven
   ```
   mvn clean test
   ```
2. Remotely on Github Actions via manual triggering workflow

   Workflow produces cucumber report as downloadable artifact after test execution.

   https://github.com/sergey-fedorov/cucumber-api-tests-poc/actions

   and also hosts on http://3.76.206.135/cucumber/cucumber.html
   
3. Via Jenkins pipeline, it hosts report file on http://3.76.206.135/cucumber-jenkins/cucumber.html
