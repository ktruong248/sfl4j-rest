SLF4J Logging Service
---------------

The purpose of this project is to create an implementation of SLF4J API that will do logging through REST api.

Modules

   rest-server: wrapper to run jetty with spring as daemon

   slflog4j-rest: implementation of slflog4j, that will talk to logging service if enabled else do the normal logging

   service-client: client to REST logging service api

   service: the REST api for logging as well as query
