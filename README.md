# Car List Service

## Installation

* You will need a JDK 7+ (e.g. [http://www.oracle.com/technetwork/java/javase/downloads/index.html](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Install sbt ([http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html)

You will need PostgreSQL. The database name, user and password should be specified in `/src/main/resources/application.conf`

## Run and Test

Run sbt

        $ sbt

Compile everything and run all tests:

        > test

Start the application:

        > re-start

Browse to [http://localhost:8080](http://localhost:8080/)

You can stop the application by running:

        > re-stop


### API:

      GET     /v1/cars              => list all the cars
      GET     /v1/cars/:id          => get a single car by id
      POST    /v1/cars              => create a new car from params
      PUT     /v1/cars/:id          => update the car by id
      DELETE  /v1/cars/:id          => delete the car by id
