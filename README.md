 Library Loan Management System API

This Spring Boot application manages book loans using a Drools rules engine integrated via an Excel spreadsheet for business logic validation,
secured with HTTP Basic Authentication.

Table of Contents
1. Technology Stack
2. Prerequisites
3. Getting Started
4. Database Schema
5. Business Rules (Drools Integration)
6. API Endpoints
7. Security

1. Technology Stack
   Java Version: 25
   Spring Boot: 4
   Dependencies: Spring Web, Spring Data JPA, Spring Security, H2 Database (in-memory), Drools (KIE) Suite.
   Rules Engine: Drools (Decision Tables from Excel .xlsx )

2. Prerequisites
JDK 25 or later installed.
Maven installed.
A tool for making API requests (e.g., cURL, Postman, Insomnia).

3. Getting Started
Clone the repository and build the project using Maven:

bash
git clone https://github.com/sinagajunior/booklending.git
cd library-rules-engine

mvn clean install

Use code with caution.
Run the application using the Spring Boot Maven plugin:

bash
mvn spring-boot:run

Use code with caution.
The application will start on http://localhost:8080 .

4. Database Schema

The application uses an H2 in-memory database by default. The following entities are managed:
Book: id , title , author , isbn (unique), totalCopies , availableCopies
Member: id , name , email (unique)
Loan: id , bookId , memberId , borrowedAt , dueDate , returnedAt (nullable)

5. Business Rules (Drools Integration)

Business logic is managed in src/main/resources/rules/LibraryRules.xlsx using a Drools Decision Table (DT). This allows non-developers
to modify loan criteria.
The rules currently implemented are:
Rule Name Description Configuration/Trigger

Max Active
Loans

A member cannot exceed 5 active loans. Member.activeLoans > 5
No Overdue

Loans
Members with any overdue loans are

blocked from new borrowing.

Loan.isOverdue() == true

Loan Duration Due date is automatically set 14 days after

borrowing.

(Implemented in Java service/can be
moved to DT)

The DroolsConfig class compiles this Excel file into a KieContainer upon application startup. The LoanService then inserts relevant
Member and Loan facts into the KieSession to fire the rules.

6. API Endpoints

All API endpoints require authentication (see Security section).


Borrow a Book
Attempt to process a new loan request. This endpoint triggers the Drools validation engine.

Endpoint: POST /api/loans/borrow
Method: POST
Parameters:
memberId (Query Param): The ID of the member.
bookId (Query Param): The ID of the book.


Example Request (using cURL):

bash
postman request POST 'http://localhost:8080/api/loans/borrow?memberId=sida&bookId=0023&name=sida' \
  --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
  --header 'Cookie: JSESSIONID=2EA4EEE7F364E73CCE75DEA9B8AACE2D' \
  --auth-basic-username 'user' \
  --auth-basic-password 'password'

Use code with caution.

Example Success Response:
HTTP/1.1 200 OK
Loan approved and issued successfully.

Example Failure Response:
HTTP/1.1 400 Bad Request
Loan denied: ERROR: Member has too many active loans.


Record a Book
Attempt to process a new record book

Endpoint: POST /api/loans/borrow
Method: POST
Parameters:
memberId (Query Param): The ID of the member.
bookId (Query Param): The ID of the book.


Example Request (using cURL):

bash
postman request POST 'http://localhost:8080/api/loans/recordbook' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
  --header 'Cookie: JSESSIONID=2EA4EEE7F364E73CCE75DEA9B8AACE2D' \
  --body '{
"id":"3234434",
"title":"Harry potter",
"author":"iskak2",
"isbn":"gramedia",
"totalCopies":"34",
"availableCopies":"34"
}' \
  --auth-basic-username 'user' \
  --auth-basic-password 'password'

Use code with caution.

Example Success Response:
HTTP/1.1 200 OK
Loan approved and issued successfully.

Example Failure Response:
HTTP/1.1 400 Bad Request
Book must have title Name



7. Security
The application is secured using Spring Security with HTTP Basic Authentication.
Username Password       Role
user      password      USER

Any request to the /api/** endpoints must include these credentials in the authorization header. The security configuration disables CSRF
protection for simplicity in an API-only context.
