---
# Employee Management System

## Overview

The **Employee Management System (EMS)** is a RESTful web application that provides functionality for managing employees, their details, departments, and manager relationships. This system allows users to perform CRUD (Create, Read, Update, Delete) operations on employees and departments. It is designed using **Jakarta EE** (formerly Java EE), making use of **Quarkus** for the development of fast, containerized applications.

This system supports various features such as assigning employees to departments, tracking employees' managers, and paginating employee data for easier browsing.

## Features

- **Employee Management**: Add, update, delete, and view employees.
- **Department Management**: Create, update, and manage departments.
- **Employee-Manager Relationship**: Link employees with their respective managers.
- **Pagination**: Retrieve employee lists with pagination.
- **Public Access**: Public endpoints such as `GET /employee/hello` for a simple greeting message.
  
## Technologies Used

- **Jakarta EE (Jakarta RESTful Web Services)**: For building REST APIs.
- **Quarkus**: To speed up development and deployment with container-first, cloud-native support.
- **Jakarta Persistence (JPA)**: For handling database operations using ORM.
- **Jakarta Transactions**: For transaction management.
- **Jakarta Security**: For authorization and security (e.g., `@PermitAll` annotation).
- **Panache**: A simplified approach to interacting with databases using **PanacheEntityBase**.
- **H2 Database**: In-memory database for testing and development (can be swapped with MySQL or another database for production).
- **Maven**: For dependency management and project builds.
  
## Project Structure

```plaintext
└── src
    └── main
        ├── java
        │   └── org
        │       └── empManagement
        │           ├── EmployeeResource.java
        │           ├── DepartmentResource.java
        │           └── Entities
        │               ├── Employee.java
        │               └── Department.java
        └── resources
            └── META-INF
                └── persistence.xml
```

## Installation

Follow these steps to set up the project locally:

### Prerequisites

- **Java 17** or later
- **Maven** installed
- **Quarkus** (Jakarta EE 9+)
- **H2 Database** (or an alternative RDBMS such as MySQL or PostgreSQL)

### Step-by-Step Guide

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/employee-management.git
   cd employee-management
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application in development mode:

   ```bash
   mvn quarkus:dev
   ```

   The application will be available at `http://localhost:8080` by default.

## Endpoints

### Employee Endpoints

- **POST /employee/new**: Add a new employee.
  - Request Body: `Employee` object in JSON format.
  - Response: Status and message indicating the result.

- **GET /employee/hello**: A simple public endpoint that returns "Hello World".

- **GET /employee/page-wise**: Retrieve a paginated list of employees.
  - Query Parameter: `page-number` (integer) – The page number to fetch.

- **GET /employee/page-count**: Get the total number of pages for employee listing.

- **GET /employee/all**: Retrieve a list of all employees.

- **PUT /employee/{id}/update**: Update employee details.
  - Request Body: Updated `Employee` object in JSON format.
  - Path Parameter: `id` – The employee's ID.

- **GET /employee/{id}**: Retrieve an employee by their ID.

- **DELETE /employee/{id}/delete**: Delete an employee by their ID.

- **GET /employee/{id}/manager**: Get the manager of a specific employee by their ID.

### Department Endpoints

- **GET /department**: Get all departments.

- **POST /department**: Add a new department.
  - Request Body: `Department` object in JSON format.

- **PUT /department/{id}**: Update a department by ID.
  - Path Parameter: `id` – The ID of the department to update.

## Example Request and Response

### Add New Employee (POST /employee/new)

Request:

```json
{
  "fullName": "John Doe",
  "email": "johndoe@example.com",
  "phoneNumber": "123-456-7890",
  "hiredate": "2025-01-01",
  "dateOfBirth": "1990-01-01",
  "gender": "Male",
  "departmentID": 1,
  "ManagerID": 2
}
```

Response:

```json
{
  "status": "success",
  "message": "Employee added successfully"
}
```

### Get Employee Details (GET /employee/{id})

Request: `GET /employee/1`

Response:

```json
{
  "empID": 1,
  "fullName": "John Doe",
  "email": "johndoe@example.com",
  "phoneNumber": "123-456-7890",
  "hiredate": "2025-01-01",
  "dateOfBirth": "1990-01-01",
  "gender": "Male",
  "departmentID": 1,
  "ManagerID": 2
}
```

### Get All Employees (GET /employee/all)

Request: `GET /employee/all`

Response:

```json
[
  {
    "empID": 1,
    "fullName": "John Doe",
    "email": "johndoe@example.com",
    "phoneNumber": "123-456-7890",
    "hiredate": "2025-01-01",
    "dateOfBirth": "1990-01-01",
    "gender": "Male",
    "departmentID": 1,
    "ManagerID": 2
  },
  {
    "empID": 2,
    "fullName": "Jane Smith",
    "email": "janesmith@example.com",
    "phoneNumber": "098-765-4321",
    "hiredate": "2025-02-01",
    "dateOfBirth": "1985-05-15",
    "gender": "Female",
    "departmentID": 2,
    "ManagerID": 1
  }
]
```

### Add New Department (POST /department)

Request:

```json
{
  "departmentName": "Engineering",
  "managerID": 1
}
```

Response:

```json
{
  "status": "success",
  "message": "Department added successfully"
}
```

## Testing

You can use tools like **Postman** or **cURL** to test the API endpoints. For example, to test the `GET /employee/{id}` endpoint, use:

```bash
curl -X GET http://localhost:8080/employee/1
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
