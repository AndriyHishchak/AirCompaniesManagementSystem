# AirCompaniesManagementSystem

An airline management API is required to create and manage airlines, their aircraft, and the flights on which they operate.

The API is built on and Framework, the Postgres database.
To get acquainted with the API collection you can use:
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/d51443db2c1268b6ca13)

# Features

1) Simple CRUD operations for Air company entity.
2) Endpoint to move airplanes between companies (simple endpoint to reassign airplane to
   another company).
3) Endpoint to find all Air Company Flights by status (use company name for identification
   of Air Company).
4) Endpoint to find all Flights in ACTIVE status and started more than 24 hours ago.
5) Endpoint to add new Airplane (Could be assign to a company immediately or moved
   later).
6) Endpoint to add new Flight (set status to PENDING)
7) Endpoint to change Flight status:
   if status to change is DELAYED – set delay started at
   if status to change is ACTIVE – set started at
   if status to change is COMPLETED set ended at
8) Endpoint to find all Flights in COMPLETED status and difference between
   started and ended time is bigger than estimated flight time.
   
# Requirements
1) Java 15
2) Spring Boot 2.4.3
3) MySQL
4) Rest API
5) Docker
# Installation
First setup <a href="https://docs.docker.com/get-docker/">Docker<a/> and <a href="https://docs.docker.com/compose/install/">Docker Compose<a/> on your machine.

Start the Docker daemon.


The application will be accessible from localhost:8001 on your browser or API Client (Such as <a href="https://www.postman.com/">Postman<a/>).

#Documentation
The app documentation is accessipp documentation is accessible <a href="https://documenter.getpostman.com/view/10965008/TWDfDYye">here</a>.