Project Title

Restaurant Order Management System (Endterm Project)

Project Description

This project is a backend application built with Spring Boot for managing restaurant orders.
The system allows users to create orders, view all orders, update order status, and delete orders.
All data is stored in a PostgreSQL database and accessed through a REST API.

Project Goal

The goal of this project is to demonstrate practical skills in:

Spring Boot

REST API development

Database integration

Backend architecture

Design patterns

Technologies Used

The following technologies were used in this project:

Java

Spring Boot

PostgreSQL

JDBC

Maven

IntelliJ IDEA

Project Architecture

The project follows a layered (multi-tier) architecture:

Controller layer – handles HTTP requests and responses

Service layer – contains business logic

Repository layer – communicates with the database

DTO layer – transfers data between layers

Model layer – represents domain objects

Patterns layer – contains design pattern implementations

Utils layer – helper and utility classes

Exception layer – error handling

This structure improves readability, maintainability, and scalability of the application.

Order Management Features

The system supports the following operations:

create a new order

retrieve all orders

retrieve an order by ID

update order status

delete an order

All operations are performed via RESTful HTTP requests.

Database Usage

The application uses PostgreSQL as a relational database.

The database stores information about:

orders

order items

menu items

All database operations are handled through the backend application.
Design Patterns Used

The following design patterns are implemented in the project:

Singleton – used for logging functionality

Factory – used for creating menu items

Builder – used for creating order objects

These patterns help make the code cleaner, reusable, and easier to extend.

Conclusion

This project demonstrates a complete backend system using Spring Boot, REST APIs, and PostgreSQL.
It follows good software design practices and meets all requirements of the endterm project.

Bonus Task – In-Memory Cache

A simple in-memory caching layer was implemented using the Singleton pattern.

The CacheManager class ensures that only one cache instance exists in the application.
The cache uses ConcurrentHashMap to store frequently requested data.

The cache is applied in the Service layer for the getAll() method.

Flow:

First request → data is loaded from database

Next requests → data is returned from cache

After create/update/delete → cache is cleared automatically

This improves performance by reducing unnecessary database calls while preserving the layered architecture.
