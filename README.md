Logging Service
---------------

The purpose of this project is to create a common logging framework service that will allow logging through simple REST api.
There are also custom handler/appender implementations for the 2 most prevalent Java logging frameworks in play.

1. slf4j
2.	Log4j

## Modules

*   rest-server: wrapper to run jetty with spring framework integration
*   logging-service-server: main class to startup logging service api
*   logging-service: main module contain all the service api and dao classes
*   slf4j-rest-impl: implementation of slflog4j, that will talk to logging service API if enabled else do the normal Log4j
*   log4j-rest-logging-service: Log4j appender for logging to Logging Service API
*   service-client: service client to logging service api


Quick Start
------------

    # from parent project
    $ mvn clean install

    $ cd logging-service-server
    $ mvn exec-java

    # sample insert new log entry
    $ curl -H "Content-Type: application/json" -X POST -d "{\"type\":\"log\",\"category\":\"INFO\",\"message\":\"my log message\",\"details\":\"my long message details\",\"source\":\"sample app\",\"ipAddress\":\"127.0.0.1\",\"logTimeSec\":1247573626}" http://localhost:8080/logs


    # sample get
    $ curl http://localhost:8080/logs/63b2ff7d-d83f-4701-a9e6-09e8fece6f2f