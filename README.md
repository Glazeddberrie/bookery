# bookery
![Java](https://img.shields.io/badge/Java-23-green)
![MVC](https://img.shields.io/badge/Architecture-MVC-brightgreen)
![Design Patterns](https://img.shields.io/badge/Design%20Patterns-5-yellow)

## Description
Bookery is a Java application designed to help bookstore owners manage their inventory efficiently. It provides full CRUD (Create, Read, Update, Delete) functionality for book records while implementing 4 key design patterns and following the MVC architecture.

### Problem Solved
- Manual book inventory management is error-prone and time-consuming
- Need for quick access to book information across different categories
- Requirement for special operations like cloning books or applying discounts
- Maintaining clean code architecture for future extensibility

## Design Patterns Implementation

### 1. Singleton Pattern
- **Why**: Ensures only one instance of the inventory database exists
- **How**: Private constructor + static getInstance() method
- **Where**: `InventoryDatabase` class
- **Benefit**: Prevents multiple instances that could lead to data inconsistency

### 2. Iterator Pattern
- **Why**: To provide a standardized way to traverse books by genre
- **How**: `GenreFilterIterator` implements Iterator<Book>
- **Where**: Used when filtering books by genre
- **Benefit**: Decouples iteration logic from book storage implementation

### 3. Adapter Pattern
- **Why**: To adapt Book objects for discount operations without modifying original class
- **How**: `BookDiscountAdapter` implements `DiscountedItem` interface
- **Where**: Used when applying discounts to books
- **Benefit**: Allows adding new functionality without changing existing code

### 4. Prototype Pattern
- **Why**: To efficiently create modified copies of existing books
- **How**: `Book` implements `Cloneable` and overrides `clone()`
- **Where**: Used when creating special edition copies
- **Benefit**: Avoids expensive initialization when creating similar books

## 🖥️ MVC Architecture

| Component     |  Responsibility                        |   Key Classes                       |
|---------------|----------------------------------------|-------------------------------------|
| **Model**     | Data and business logic                | `Book`, `InventoryDatabase`         |
| **View**      | User interface                         | `ConsoleView`, `InventoryView`      |
| **Controller**| Mediates Model and View                | `InventoryController`               |

## 🛠️ Compilation and Execution

### Prerequisites
- Java JDK 23 or later
- Latest Maven

### Steps to Run

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/bookstore-inventory.git
   cd bookstore-inventory
   ```
2. **Compile the project**:
  ```
    mvn clean package
    cd ./target
    java -jar credentialgenerator-1.0-SNAPSHOT.jar
  ```
