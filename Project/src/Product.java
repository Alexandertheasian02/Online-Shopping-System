public abstract class Product {
    private String productID; // Instance variable for the product ID (Unique Identifier)
    private String productName; // Instance variable for the product Name
    private int availableItems; // Instance variable for the number of available items of a product
    private double price; // Instance variable for the price of a product

    public Product(String productID, String productName, int availableItems, double price){
        // Parameterized constructor
        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
    }

    public String getProductID() { // Getter method for retrieving the product ID
        return productID;
    }

    public String getProductName() { // Getter method for retrieving the product Name
        return productName;
    }

    public int getAvailableItems() { // Getter method for retrieving the number of available items
        return availableItems;
    }

    public double getPrice() { // Getter method for retrieving the Price
        return price;
    }

    public abstract String getProductType(); // Abstract method that returns the type of product
}
