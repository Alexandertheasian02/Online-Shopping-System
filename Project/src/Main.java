import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Create an object of WestminsterShoppingManager to manage the shopping system
        WestminsterShoppingManager system = new WestminsterShoppingManager();
        boolean exit = false;
        File dataFile = new File("Products.txt"); // File that stores products in the system

        if(dataFile.exists()){ // If the data file exists, read data from it to initialize the WestminsterShoppingManager
            system.readFromFile();
        }System.out.println("\nWelcome to Online Shopping System!");
        while (!exit){ // While true, loop the main menu
            exit = system.runMenu();
        }
    }
}