# Project

Vehicle search service is implemented for both of the rest and soap. Vehicle information fetched from file into the in-memory database. 

## Note

Only tested with intellij idea ultimate edition

## Installation

Get repository then run "mvn install" (compatible with JDK 1.8 )

In order to get up services, run application (environment JRE is 1.8 )

## Test

There are two simple test class insede project. RestServiceTest will be run during mvn install.
SoapServiceTest need to be run when service up. To run it, comment out Ignore annotation.

While service is up, project can be tested using methods which are explained below.  

Note : searchCriteria can be one of the brand, model, class and all.

Rest Service Get
search criteria and key used as a path variable
demonstration -> http://localhost:9191/restService/searchVehicle/criteria/key
real world ex -> http://localhost:9191/restService/searchVehicle/all/audi

Rest Service Post
can be tested using Postman
method : POST
url: http://localhost:9191/restService/searchVehicle
request/json
{
    "searchCriteria":"all",
    "searchKey" : "z"
}

Soap Service

can be tested using SoapUI

url : http://localhost:9191/soapService/searchVehicle.wsdl
create new soap project
SoapUI -> file -> New Soap Project
paste url in Initial WSDL box 
fill request with desired parameters and send request.


