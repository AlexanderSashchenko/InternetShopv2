# Internet-shop, a Java practice project

# Table of Contents
   * [Project purpose](#purpose)
   * [Project structure](#structure)
   * [For a developer](#developer-start)
   * [Authors](#authors)

# <a name="purpose">Project purpose</a>
    The project is a simple example of an internet shop implementation.
    The purpose of this project is to gain experience and apply knowledge to practice
    for listed below technologies stack:
    * Java 11
    * Maven 4.0.0
    * Tomcat 9.0.30
    * Servlets 3.1.0
    * MySQL 8.0.15
    * Log4j 1.2.7
    * JSTL 1.2

# <a name="structure">Project Structure</a>
    The project uses the most common design patterns:
    * Model-view-controller;
    * Data Access Object;
    * Dependency Injection;

    The models used in the project are:
    * Role;
    * User;
    * Item;
    * Order;
    * Bucket (a shopping cart);

    In order to view the list of items, add them to a shopping cart, confirm order completion 
    or delete it one should pass a registration procedure. In order to view the list of
    users or delete some, add new items to the shop or delete some the user should be provided
    with 'ADMIN' role. While 'USER' role is granted by default after registration, 'ADMIN' role
    should be set directly at the database.

# <a name="developer-start">For a developer</a>

    Open the project in your IDE and add it as maven project.
    * Configure Tomcat:
        - Add artifact internetshop:war exploded;
        - Add Java SDK (prefer version 11 or higher)
    * Run init_db.sql from directory src/main/resources (this would appropriate database 
    structure and insert a few test items to the shop);
    * At src.main.java.Factory class use username and password for your database to create 
    a Connection;
    * Change a path in src.main.java.resources.log4j.properties to properly write logs on the 
    disc if required;
    * Run the project.

# <a name="authors">Authors</a>
Created by a github user [Alexander Sashchenko](https://github.com/AlexanderSashchenko) 
for a self-educational purpose.
