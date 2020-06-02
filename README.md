# financial-java-api

[![Build Status](https://travis-ci.org/mariazevedo88/financial-java-api.svg?branch=master)](https://travis-ci.org/mariazevedo88/financial-java-api) ![GitHub forks](https://img.shields.io/github/forks/mariazevedo88/financial-java-api?style=social) ![GitHub language count](https://img.shields.io/github/languages/count/mariazevedo88/financial-java-api) ![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/mariazevedo88/financial-java-api) ![GitHub repo size](https://img.shields.io/github/repo-size/mariazevedo88/financial-java-api) ![GitHub last commit](https://img.shields.io/github/last-commit/mariazevedo88/financial-java-api) ![GitHub](https://img.shields.io/github/license/mariazevedo88/financial-java-api)

## About the API

A financial API for managing transactions. The API main URL `/financial/v1`.

## Features

This API provides HTTP endpoints and tools for the following:

* Create a Transaction: `POST/financial/v1/transactions`
* Update a Transaction: `PUT/financial/v1/transactions`
* Delete a Transaction (by id): `DELETE/financial/v1/transactions/1`
* Get report of all transactions created: `GET/financial/v1/transactions`
* Find a unique transaction by id: `GET/financial/v1/transactions/1`
* Find transactions by NSU (Unique sequential number): `GET/financial/v1/transactions/byNsu/{nsuNumber}`
* Get Statistics about the transactions of the API: `GET/financial/v1/statistics`

### Details

`POST/financial/v1/transaction`

This endpoint is called to create a new transaction.

**Body:**

```json
{
  "nsu": "123456",
  "authorizationNumber": "010203",
  "amount": "22.88",
  "transactionDate": "2020-04-05T09:59:51.312Z",
  "type": "CARD",
}
```

**Where:**

`id` - transaction id. It is automatically generated.

`nsu` - identification number of a sales transaction using cards. May be null if transaction was paid in cash;

`authorizationNumber` - is a one-time code used in the processing of online transactions;

`amount` – transaction amount; a string of arbitrary length that is parsable as a BigDecimal;

`transactionDate` – transaction time in the ISO 8601 format YYYY-MM-DDThh:mm:ss.sssZ in the Local timezone.

`type` - transaction type: CARD (credit-card) or MONEY (paid in cash).

`links` - self-linking URL for the transaction. It is automatically generated.

Returns an empty body with one of the following:

* 201 - Created: Everything worked as expected.
* 400 - Bad Request: the request was unacceptable, often due to missing a required parameter or invalid JSON.
* 404 - Not Found: The requested resource doesn't exist.
* 409 - Conflict: The request conflicts with another request (perhaps due to using the same idempotent key).
* 422 – Unprocessable Entity: if any of the fields are not parsable or the transaction date is in the future.
* 429 - Too Many Requests: Too many requests hit the API too quickly. We recommend an exponential backoff of your requests.
* 500, 502, 503, 504 - Server Errors: something went wrong on API end (These are rare).

`PUT/financial/v1/transaction/{id}`

This endpoint is called to update a transaction.

**Body:**

```json
{
   "id": 1,
   "nsu": "123456",
   "authorizationNumber": "010203",
   "amount": "30.06",
   "transactionDate": "2020-04-05T09:59:51.312Z",
   "type": "CARD"
}
```

Must be submitted the object that will be modified. Must return a transaction specified by ID and all fields recorded above, including links and
the one that was updated.

```json
{
   "data": {   
   		"id": 1,
   		"nsu": "123456",
   		"authorizationNumber": "010203",
   		"amount": "30.06",
   		"transactionDate": "2020-04-05T09:59:51.312Z",
   		"type": "CARD",
   		"links": [
	    	{
	       		"rel": "self",
	        	"href": "http://localhost:8080/financial/v1/transactions/1"
	    	}
   		]
	}
}
```

`GET/financial/v1/transactions`

This endpoint returns all transactions created.

`DELETE/financial/v1/transaction/{id}`

This endpoint causes a transaction for a specific id to be deleted, accepting an empty request body and return a 204 status code.

**Where:**

`id` - transaction id to be deleted.

`GET/financial/v1/statistics`

This endpoint returns the statistics based on the transactions created.

**Returns:**

```json
{
	"data": { 
  		"sum": "150.06",
  		"avg": "75.3",
  		"max": "120.0",
  		"min": "30.06",
  		"count": 2,
  		"links": [
	    	{
	       		"rel": "self",
	        	"href": "http://localhost:8080/financial/v1/statistics/1"
	    	}
   		]
   	}
}
```
 
**Where:**

`sum` – a BigDecimal specifying the total sum of transaction value.

`avg` – a BigDecimal specifying the average amount of transaction value.

`max` – a BigDecimal specifying single highest transaction value.

`min` – a BigDecimal specifying single lowest transaction value.

`count` – a long specifying the total number of transactions.

`links` - self-linking URL for the statistic. It is automatically generated.

All `BigDecimal` values always contain exactly two decimal places, eg: `15.385` is returned as `15.39`.

### Technologies used

This project was developed with:

* **Java 11 (Java Development Kit - JDK: 11.0.3)**
* **Spring Boot 2.2.6**
* **Spring Framework 2.2.6**
* **Maven**
* **JUnit 5**
* **Surfire**
* **PostgreSQL 12**
* **Flyway 6.0.8**
* **Swagger 2.9.2**
* **Model Mapper 2.3.6**
* **Heroku**
* **Bucket4j**

### Compile and Package

The API also was developed to run with an `jar`. In order to generate this `jar`, you should run:

```bash
mvn package
```

It will clean, compile and generate a `jar` at target directory, e.g. `financial-java-api-2.1.0-SNAPSHOT.jar`

### Execution

You need to have **PostgreSQL 9.6.17 or above** installed on your machine to run the API on `dev` profile. After installed, on the `pgAdmin` create a database named `financial`. If you don't have `pgAdmin` installed you can run on the `psql` console the follow command:

```sql
CREATE database financial;
```

After creating the API database, you need to add your **Postgres** root `username` and `password` in the `application.properties` file on `src/main/resource`. The lines that must be modified are as follows:

```properties
spring.datasource.username=
spring.datasource.password=
```

When the application is running **Flyway** will create the necessary tables for the creation of the words and the execution of the compare between the endpoints. In the test profile, the application uses **H2** database (data in memory).

### Test

* For unit test phase, you can run:

```bash
mvn test
```

* To run all tests (including Integration Tests):

```bash
mvn integration-test
```

### Run

In order to run the API, run the jar simply as following:

```bash
java -jar financial-java-api-2.1.0-SNAPSHOT.jar --spring.profiles.active=prod
```
    
or

```bash
mvn spring-boot:run -Dspring.profiles.active=prod
```

By default, the API will be available at [http://localhost:8080/financial/v1](http://localhost:8080/financial/v1)

### Documentation

* Swagger (development environment): http://localhost:8080/swagger-ui.html

### License

This API is licensed under the MIT License.

### Contributing

[![](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/images/0)](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/links/0)[![](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/images/1)](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/links/1)[![](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/images/2)](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/links/2)[![](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/images/3)](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/links/3)[![](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/images/4)](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/links/4)[![](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/images/5)](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/links/5)[![](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/images/6)](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/links/6)[![](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/images/7)](https://sourcerer.io/fame/mariazevedo88/mariazevedo88/financial-java-api/links/7)
