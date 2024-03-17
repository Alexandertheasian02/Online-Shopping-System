public class Clothing extends Product{
    private String size; // Instance variable for the size of the clothing product
    private String colour; // Instance variable for the color

    public Clothing(String productID,String productName,int numberOfAvailableItems,double price,String size,
                    String colour){ // Parameterized constructor
        super(productID, productName, numberOfAvailableItems, price); // Initializes the parent class constructor
        this.size=size;
        this.colour=colour;
    }

    public String getSize() { // Getter method for retrieving the size
        return size;
    }

    public String getColour() { // Getter method for retrieving the color
        return colour;
    }

    // Overrides the abstract method in the Product superclass
    @Override
    public String getProductType() { // Returns the type of the product, "Main.Clothing"
        return "Clothing";
    }
}
