# calendarbooking server

REST API for calendar booking assignment, using Grails 4.0.6.

## Prerequisites
- Java 8 or newer
- Gradle

## Getting started
Use the gradle wrapper to run tests using: `./gradlew check`

To run the application, run: 
`./gradlew bootRun`

Visit 
`http://localhost:8090/api/photographers/available?date=2020-11-25&bookingId=5`

to get your available photographers. 

## Test data
When starting the application or running tests, test data from the assignment [is added](grails-app/init/calendarbooking/BootStrap.groovy).

The booking used is hardcoded to the following:
```json
{
  "booking": {
    "id": "3",
    "durationInMinutes": 90
  }
}
```