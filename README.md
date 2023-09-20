# Recipe Management System

A powerful and intuitive system built to manage, store, and retrieve recipes with ease. Designed using the Layered Architectural Design pattern and the Abstract Factory pattern to ensure scalability, maintainability, and efficient data access.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Technologies](#technologies)
- [Database Setup](#database-setup)
- [Project Setup](#project-setup)
- [Usage](#usage)
- [Contribution](#contribution)
- [Development Status](#development-status)

## Features

- **Search Recipes**: Quick search using the first letter to retrieve all relevant recipes.
- **User-Friendly GUI**: Intuitive graphical user interface built using Java's Swing framework.
- **CRUD Operations**: Create, Read, and Update operations for recipes. (Delete operation is under development).
- **Ingredient Management**: Separate management system for ingredients linked to recipes (currently under development).
- **Error Handling**: Graceful handling of exceptions with user-friendly error messages.

## Architecture

**Layered Architectural Design Pattern**:  
The system is organized into distinct layers to separate concerns:

1. **View/Controller Layer**: Manages the GUI and user interactions.
2. **Service Layer**: Handles the business logic of the application, making use of DTOs (Data Transfer Objects) for efficient data operations.
3. **DAO Layer**: Interfaces with the database for CRUD operations.

This separation ensures that changes in one layer don't necessarily impact others, making the system more maintainable and scalable.

**Abstract Factory Pattern**:  
Utilized to create objects from several families of classes. In the context of this project, it's used for data storage, enabling a clear separation between the data access objects (DAOs) and the rest of the application. This provides flexibility in changing or scaling the data storage method in the future without affecting the core system.

## Technologies

- **Java**: Core programming language.
- **Swing**: Java library for the GUI.
- **SQL**: For structured data storage. _(Specify the particular SQL DBMS you're using, e.g., MySQL, PostgreSQL, etc.)_
- **Maven**: Dependency management and project organization.

## Database Setup

To ensure the proper functioning of the Recipe Management System, a database needs to be set up:

1. Navigate to the `Database Queries` directory in the root folder of the project.
2. Run the `CreateDatabase.sql` script to set up the necessary database structure and user with owner privileges:

```sql
CREATE USER 'recipeuser'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON recipe.* TO 'recipeuser'@'localhost';
FLUSH PRIVILEGES;
```

3. Execute the PopulateRecipeDB.sql script to populate the database with initial data.
   
Note on User Credentials: The code in the repository is preset to make use of the credentials from the provided query. If you make any alterations to the query, adjust the code accordingly in the DBUtil.java file located in Service Layer -> util.

Note 2: The method to execute these scripts will depend on your SQL DBMS.

Note 3: Ensure you have the necessary privileges to create and modify databases in your SQL system.

## Project Setup

1. Clone the repository:
```sh
git clone https://github.com/JohnNtirintis/Recipe-Management-System
```

2. Navigate to the project directory:
```sh
cd Recipe-Management-System
```

3. Compile and run the Main.java to start the application.

## Usage

1. Main Menu: Launch the application and navigate through various options.
2. Add Recipe: Allows users to add a new recipe along with ingredients and cooking instructions.
3. Edit Recipe: Search for a recipe by its name and make desired edits.
4. Delete Recipe: This feature is under development and will be available in future releases.

## Contribution
Interested in contributing? Fork the repository and submit a pull request. All contributions are welcome!

## Development Status
This project is currently under active development. While many features are fully functional, some functionalities, like the delete operation and ingredient management, are still in the development phase. Please check back regularly for updates and new features.
