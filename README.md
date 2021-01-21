# Bulk-Registration
- It has csv file read.
- It has some validations and save db.
- It has write txt files.

## Requirements
Firstly, Postgresql database is used in the project. So a new database should be created according to the following information:

- Jdk 1.8

- Apache Maven 4.0.0

- Postgresql 


## Setup And Run

-You need to edit the application properties file according to your own db information.

- After you can use app.

- You can change input in membersCsv.cvs file. You have to use csv format. First row is info and this row jumped on project. When you edit file you have to use to separate 
with','

## Endpoints:

 **Swagger:** http://localhost:8080/swagger-ui.html#

 **SpringBoot Api:** http://localhost:8080
 
 **Process all project using CSV file:** localhost:8080/bulk/register  -GET METHOD
 
 **Send sms for all Members with specific message:** localhost:8080/bulk/sms Request: {"message"}

## Used technologies
**SpringBoot:** For api.

**Write/Read Files:** Reading all data from csv later write some info for member to txt files.

**Swagger:** All endpoints are easily schematized.



