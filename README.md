# pwaksman.api

# Application config
- Java version: 15.0.2
- Apache maven: 3.8.1
- Kotlin 1.5.20
- PostgreSQL 13

# Server config (src/main/resources/application.properties)
- Setup the web server port
- Setup the database url, username and password
- - make sure the user has the rights to create the database objects
- Setup the kafka server address and topic name

# Run application (maven, windows)
- Open the windows command prompt
- Navigate inside the project folder
- build and run the project with maven
- - mvn clean compile spring-boot:run

# API (Postman)
- import the api example with the "Postman.postman_collection.json" file in the root folder
- change the url to match the local machine or the runner server ip address and port number

## V1
- This version exists only to illustrate the difference in each version and how changes are handled
- The only difference is that this version assumes all session have a fixed time to close, set in 1 hour from the start date

## V2
- This version has the requested implementation, changing the session duration to be dynamic or with a default value of 1 minute

# Project architecture
- Core: The core package contains the base controller and service, to minimize code duplication for basic tasks.
- Controller: The controller package has the api version and the controller error handler
- - Api versioning: The version package has a specific controller for each version but all of them consumes the same service. This approach ensure that the application will always have only one business logic, making backwards compatibility with the transport layer when necessary.
- Model: Contains the database model class
- Message: Contains the application message handler (currently only one class configured with kafka)
- Repository: The JPA repository interfaces
- Service: The services class or the business layer
- Transport: The transport class used with the controllers
- - Trnaposrt versioning: When needed, a new api may add or remove fields from the transport layer. When that happens, the previous version must be placed inside the correct version (just like the controller) and the controller must point to the correct transport version, making use of a transport adapter to exchange data between the service and the controller.
