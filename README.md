# Online-Shopping-System
This project is an implementation of an online shopping system with a console menu for the manager and a graphical user interface (GUI) for the users. The system allows the manager to add and delete products, print and save the list of products, while the users can view and select products, add them to the shopping cart, and see the final price.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
JDK 8 or higher
A build tool such as Apache Maven or Gradle

### Building and Running
To build and run the project, follow these steps:

1. Clone the repository to your local machine.
2. Use your preferred build tool to build the project. For example, with Maven, you can run mvn clean install to build the project and create an executable JAR file.
3. Run the WestminsterShoppingManager class to start the console menu for the manager.
4. To open the GUI, select the "Open GUI" option from the console menu.

## Design and Implementation
The system is designed and implemented according to the Object-oriented principles, using UML diagrams and the following classes:

* Product: an abstract class that represents a product, with attributes for the product ID, name, number of available items, and price.
* Electronics: a subclass of Product that represents an electronics product, with additional attributes for the brand and warranty period.
* Clothing: a subclass of Product that represents a clothing product, with additional attributes for the size and color.
* User: a class that represents a user account, with attributes for the username and password.
* ShoppingCart: a class that represents the user's shopping cart, with a list of products and methods for adding, removing, and calculating the total cost.
* WestminsterShoppingManager: a class that implements the ShoppingManager interface and maintains the list of products in the system, providing all the methods for the system manager defined in the console menu.

## Console Menu Implementation
TheWestminsterShoppingManager class displays a menu in the console (not in the GUI) containing the following management actions:

* Add a new product to the system (electronics or clothing, with all the relevant information).
* Delete a product from the system by inserting the product ID.
* Print the list of products in the system, with all the information (attributes) and saying if it is electronics or clothing. The list is ordered alphabetically according to the product ID.
* Save in a file the list of products that have been added to the system, with all the relevant attributes. The next time the application starts, it should be able to read back all the information saved in the file and continue to use the system.

## Graphical User Interface (GUI) Implementation
The GUI is opened from the menu console, and it allows the user to:

* Select from a drop-down menu which type of product to visualize (all, Electronics, or Clothes).
* Visualize the list of products with relevant information in a table, which can be sorted alphabetically.
* Select a product and add it to the shopping cart. The items with reduced availability (less than 3 items available) are in red on the table.
* See the product details (all the information related to the product) in a panel below the table when an item is selected.
* Add the item to the shopping cart by clicking the relevant button.
* Visualize the shopping cart by clicking the "Shopping Cart" button.
* See the list of products and the final price in the shopping cart. The system applies the following discounts: 20% discount when the user buys at least three products of the same category, and 10% discount for the very first purchase.

## Testing and System Validation
The project includes a test plan and automated testing using JUnit. The test plan includes specific instructions about the data and conditions the program will be tested with, and the automated testing runs scenarios of the main use cases implemented in the console menu. The robustness of the code is evaluated through the use of error handling and input validation, and the quality of the code is evaluated based on coding standards and conventions.
