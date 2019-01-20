Poc BDF Postgresql Thorntail

## Run

* create database poc

* mvn clean package -Dmaven.test.skip=true

* java -jar poc-bdf-postgres-thorntail.jar

* stack : OpenApi, Metrics, OpenTracing, CircuitBreaker, fallback, retry, bulkhead

## Use

    http://localhost:8080/postgresThorntail to show that the application prints a 'Hello from BDF' message, then
    http://localhost:8080/postgresThorntail/post to show that the application can write in the postgresql database.
    http://localhost:8080/postgresThorntail/health to show that the application prints a 'Hello from BDF' message

## OpenTracing JAEGER

    export JAEGER_SERVICE_NAME=thorntail
    export JAEGER_REPORTER_LOG_SPANS=true 
    export JAEGER_SAMPLER_TYPE=const
    export JAEGER_SAMPLER_PARAM=1 
   