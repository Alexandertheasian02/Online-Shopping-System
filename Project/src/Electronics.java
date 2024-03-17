public class Electronics extends Product{
    private String brand; // Instance variable for the brand of the electronic product
    private String warrantyPeriod; // Instance variable for the warranty period

    public Electronics(String productID,String productName,int numberOfAvailableItems,double price,String brand,
                       String warrantyPeriod){ // Parameterized constructor
        super(productID, productName, numberOfAvailableItems, price); // Initializes the parent class constructor
        this.brand=brand;
        this.warrantyPeriod=warrantyPeriod;
    }

    public String getBrand() { // Getter method for retrieving the brand
        return brand;
    }

    public String getWarrantyPeriod() { // Getter method for retrieving the warranty period
        return warrantyPeriod;
    }

    // Overrides the abstract method in the Product superclass
    @Override
    public String getProductType() { // Returns the type of the product, "Electronics"
        return "Electronics";
    }
}
