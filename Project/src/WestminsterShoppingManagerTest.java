import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterShoppingManagerTest {

    @Test
    void addProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product electronicsProduct = new Electronics("1234", "Laptop",
                10, 999.99, "Apple", "12m");

        shoppingManager.addProduct(electronicsProduct);
        assertTrue(shoppingManager.productList.contains(electronicsProduct));
    }

    @Test
    void deleteProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product clothingProduct = new Clothing("5678", "T-Shirt",
                20, 39.99, "M", "Pink");

        shoppingManager.productList.add(clothingProduct);
        assertTrue(shoppingManager.productList.contains(clothingProduct));
        shoppingManager.deleteProduct(new java.util.Scanner("5678"));
        assertFalse(shoppingManager.productList.contains(clothingProduct));
    }

    @Test
    void printList() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product electronicsProduct = new Electronics("1234", "Laptop", 10,
                999.99, "Apple", "12m");

        shoppingManager.productList.add(electronicsProduct);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        shoppingManager.printList();

        // Restore System.out
        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Product ID: 1234"));
        assertTrue(outputStream.toString().contains("Product Type: Electronics"));
        assertTrue(outputStream.toString().contains("Product Name: Laptop"));
        assertTrue(outputStream.toString().contains("Available Items: 10"));
        assertTrue(outputStream.toString().contains("Price: 999.99"));
        assertTrue(outputStream.toString().contains("Brand : Apple"));
        assertTrue(outputStream.toString().contains("Warranty Period: 12m"));
    }

    @Test
    void saveToFileTest() {
        // Create an instance of WestminsterShoppingManager
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Create a sample product for testing
        Product sampleProduct = new Electronics("1234", "Laptop", 10,
                999.99, "Apple", "6m");

        // Add the product to the shopping manager
        shoppingManager.addProduct(sampleProduct);

        // Redirect System.out to capture printed output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Save to file
        shoppingManager.saveToFile();

        // Reset System.out
        System.setOut(System.out);

        // Check if the printed output contains expected information
        assertTrue(outContent.toString().contains("Successfully saved to the file!"));
    }

    @Test
    void readFromFile() {
        // Create an instance of WestminsterShoppingManager
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Redirect System.out to capture printed output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Read from file
        shoppingManager.readFromFile();

        // Reset System.out
        System.setOut(System.out);

        // Check if the printed output contains expected information
        assertTrue(outContent.toString().contains("File loaded successfully."));
    }
}

