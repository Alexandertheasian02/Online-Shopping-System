import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {

    ArrayList<Product> productList; // ArrayList for storing products
    ArrayList<User> userList = User.readUsersFromFile();
    // Reads from the user data file and stores the data in an ArrayList

    public WestminsterShoppingManager(){ // Parameterized constructor
        productList = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) { // Adds a product to the product list, with a maximum limit of 50 products
        int numOfProducts = 50;
        if(productList.size()< numOfProducts)
            productList.add(product);
        else
            System.out.println("Only 50 products are allowed.");
    }

    @Override
    public void deleteProduct(Scanner input) { // Deletes a product from the system, by inserting the product ID
        while (true) {
            System.out.println("Enter the product ID you want to delete: ");
            String productID = input.next();
            // Iterator for safe removal during iteration
            Iterator<Product> iterator = productList.iterator();
            boolean productFound = false;

            while (iterator.hasNext()) {
                int productCount=0;
                Product product = iterator.next();
                if (product.getProductID().equals(productID)) {
                    String ProductType = product.getProductType();
                    iterator.remove();  // Safe removal using iterator
                    productFound = true;

                    // Count remaining products of the same type
                    for(Product product1: productList){
                        if(product1.getProductType().equals(ProductType))
                            productCount++;
                    }
                    System.out.println("\n"+ ProductType +" product successfully deleted!");
                    System.out.println("There are "+productCount+" "+ ProductType +" product(s) remaining.");
                    break;  // Exit the loop once the product is found and removed
                }
            }
            if (!productFound) {
                System.out.println("There is no product with that product ID.");
                continue;
            }
            break;
        }
    }

    @Override
    public void printList() { // Prints the list of the products in the alphabetical order according to the product ID
        if(productList.isEmpty()){
            System.out.println("There is no products added. Add products to print.");
        }else {
            // Create a sorted copy of the product list based on product ID
            ArrayList<Product> sortedList = new ArrayList<>(productList);
            sortedList.sort(Comparator.comparing(Product::getProductID));
            // The syntax ClassName::methodName is known as a method reference
            for(Product product : sortedList) // Print details for each product
                if (product instanceof Electronics) {  // Print details for Main.Electronics type product
                    System.out.println("Product ID: " + product.getProductID());
                    System.out.println("Product Type: Electronics");
                    System.out.println("Product Name: " + product.getProductName());
                    System.out.println("Available Items: " + product.getAvailableItems());
                    System.out.println("Price: " + product.getPrice());
                    System.out.println("Brand : " + ((Electronics) product).getBrand());
                    System.out.println("Warranty Period: "+((Electronics) product).getWarrantyPeriod());
                    System.out.println("\n");
                } else if (product instanceof Clothing) { // Print details for Main.Clothing type product
                    System.out.println("Product ID: " + product.getProductID());
                    System.out.println("Product Type: Clothing");
                    System.out.println("Product Name: " + product.getProductName());
                    System.out.println("Available Items: " + product.getAvailableItems());
                    System.out.println("Price: " + product.getPrice());
                    System.out.println("Size: " + ((Clothing) product).getSize());
                    System.out.println("Colour: " + ((Clothing) product).getColour());
                    System.out.println("\n");
                }
        }
    }

    @Override
    public void saveToFile() {
        // Saves in a file the list of products that have been added to the system, with all the relative attributes
        try {
            FileWriter writer = new FileWriter("Products.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Write product details to the file
            for (Product product:productList){
                if(product instanceof Electronics){ // Write details for Electronics type product
                    bufferedWriter.write("Electronics "+" | "+product.getProductID()+" | "+product.getProductName()
                            +" | "+product.getAvailableItems()+" | "+product.getPrice()+" | "+
                            ((Electronics) product).getBrand()+" | "+((Electronics) product).getWarrantyPeriod() + " | ");
                    bufferedWriter.newLine();
                } else if(product instanceof Clothing){ // Write details for Clothing type product
                    bufferedWriter.write("Clothing "+" | "+product.getProductID()+" | "+product.getProductName()+
                            " | "+product.getAvailableItems()
                            +" | "+product.getPrice()+" | "+((Clothing) product).getSize()+" | "+
                            ((Clothing) product).getColour());
                    bufferedWriter.newLine();
                }
            }
            // Close the writer and inform about successful save
            bufferedWriter.close();
            writer.close();
            System.out.println("Successfully saved to the file!");
        } catch (IOException e){
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    @Override
    public void readFromFile() { // Reads product details from the "Products.txt" file and fills the product list
        try (FileReader reader = new FileReader("Products.txt");
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            // Read each line from the file and create corresponding products
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String type = parts[0].trim();

                if (type.equals("Electronics")) { // Create and add an Electronics product
                    String productID = parts[1].trim();
                    String productName = parts[2].trim();
                    int noOfAvailableItems = Integer.parseInt(parts[3].trim());
                    double price = Double.parseDouble(parts[4].trim());
                    String brand = parts[5].trim();
                    String warrantyPeriod = parts[6].trim();
                    Product product = new Electronics(productID, productName, noOfAvailableItems, price, brand,
                            warrantyPeriod);
                    productList.add(product);

                }else if(type.equals("Clothing")){ // Create and add a Clothing product
                    String productID = parts[1].trim();
                    String productName = parts[2].trim();
                    int noOfAvailableItems = Integer.parseInt(parts[3].trim());
                    double price = Double.parseDouble(parts[4].trim());
                    String size = parts[5].trim();
                    String colour = parts[6].trim();
                    Product product = new Clothing(productID,productName,noOfAvailableItems,price,size,colour);
                    productList.add(product);

                } else {
                    System.out.println("Invalid product type: " + type);
                }
            }
            System.out.println("File loaded successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    @Override
    public void openGUI() { // Opens the graphical user interface (GUI) for the Shopping System
        GUILogin loginGUI = new GUILogin(userList,productList);
        loginGUI.setTitle("Login Portal");
        loginGUI.setSize(500, 500);
        loginGUI.setResizable(false);
        loginGUI.setVisible(true);
    }

    @Override
    public void saveUserToFile() { // Saves the user details to a file using the User class
        User.saveUsersToFile(userList);
    }

    @Override
    public boolean runMenu() { // Displays the main menu, takes user input, and performs the corresponding actions
        boolean exit = false;
        int choice , numberOfAvailableItems;
        double price;
        String type,size,colour,warrantyPeriod;
        boolean sameProductIDExist; // To check if the same product ID exists

        // List of available warranty periods
        ArrayList<String> periodArray= new ArrayList<>(Arrays.asList("1m", "2m", "3m", "4m", "6m", "12m", "24m",
                "36m", "48m"));

        Scanner input = new Scanner(System.in);
        System.out.println(); // Display main menu options
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("1 : Add a new Product to the System");
        System.out.println("2 : Delete a Product from the System");
        System.out.println("3 : Print the list of the products in the System");
        System.out.println("4 : Save Products to a file");
        System.out.println("5 : Open Graphical User Interface (GUI)");
        System.out.println("6 : Exit from the System");
        System.out.println("-----------------------------------------------------------------------------------------");
        while (true) { // Input validation loop
            try {
                System.out.println("\nSelect an option: ");
                choice = input.nextInt();
                break;
            } catch (InputMismatchException e) { // Handle invalid input
                System.out.println("Wrong Input, Please enter a number from the above menu.");
                input.next();
            }
        }

        switch (choice){ // Switch statement for menu options
            case 1:
                while (true){ // Adding a new product to the system
                    System.out.println("What type of Product do you want to add (electronics(e)/clothing(c)): ");
                    type = input.next().toLowerCase().trim();
                    if(type.equals("electronics") || type.equals("e")){ // Electronics product case

                        System.out.println("Enter Product ID (4 digits): ");
                        String productID = input.next();

                        // Validate if the product ID has exactly 4 digits
                        if (productID.length() != 4) {
                            System.out.println("Invalid Product ID. Please enter a 4-digit ID.\n");
                            continue;
                        }
                        // Check if the product ID already exists
                        sameProductIDExist = false;
                        for (Product existingProduct : productList) {
                            if (existingProduct.getProductID().equals(productID)) {
                                sameProductIDExist = true;
                                break;
                            }
                        }
                        // If product ID already exists, prompt for a new one
                        if (sameProductIDExist) {
                            System.out.println("Product with the same ID already exists. Please enter a different ID.\n");
                            continue;
                        }

                        System.out.println("Enter Product Name: ");
                        System.out.println("(Use underscore '_' instead of white spaces)");
                        String productName = input.next();

                        while (true){
                            try{
                                System.out.println("Enter number of available items: ");
                                numberOfAvailableItems = input.nextInt();
                                break;
                            }catch (Exception e){
                                System.out.println("Invalid Input, available items should be a number.");
                                input.next();
                            }
                        }

                        while (true){
                            try{
                                System.out.println("Enter price of the Product: ");
                                price = input.nextDouble();
                                break;
                            }catch (Exception e){
                                System.out.println("Invalid Input, price should be a number.");
                                input.next();
                            }
                        }

                        System.out.println("Enter the brand of the Product: ");
                        System.out.println("(Use underscore '_' instead of white spaces)");
                        String brand = input.next();

                        while (true){

                            System.out.println("Select warranty period from these [1m,2m,3m,4m,6m,12m,24m,36m,48m]");
                            System.out.println("Warranty Period: ");
                            warrantyPeriod = input.next().toLowerCase().trim();
                            if(periodArray.contains(warrantyPeriod)){
                                break;
                            }else{
                                System.out.println("Please enter your choice from the given warranty periods.");
                            }
                        }
                        // Creating and adding the electronic product to the system
                        Product product = new Electronics(productID,productName,numberOfAvailableItems,price,brand,
                                warrantyPeriod);
                        addProduct(product);
                        System.out.println("Product added successfully!");
                        break;

                    }else if(type.equals("clothing") || type.equals("c")){ // Clothing product case
                        System.out.println("Enter Product ID (4 digits): ");
                        String productID = input.next();
                        sameProductIDExist = false;

                        // Validate if the product ID has exactly 4 digits
                        if (productID.length() != 4) {
                            System.out.println("Invalid Product ID. Please enter a 4-digit ID.\n");
                            continue;
                        }
                        // Check if the product ID already exists
                        for (Product existingProduct : productList) {
                            if (existingProduct.getProductID().equals(productID)) {
                                sameProductIDExist = true;
                                break;
                            }
                        }
                        // If product ID already exists, prompt for a new one
                        if (sameProductIDExist) {
                            System.out.println("Product with the same ID already exists. Please enter a different ID.\n");
                            continue;
                        }

                        System.out.println("Enter Product Name: ");
                        System.out.println("(Use underscore '_' instead of white spaces)");
                        String productName = input.next();

                        while (true){
                            try{
                                System.out.println("Enter number of Available items: ");
                                numberOfAvailableItems = input.nextInt();
                                break;
                            }catch (Exception e){
                                System.out.println("Invalid Input, Available items should be a number.");
                                input.next();
                            }
                        }

                        while (true){
                            try{
                                System.out.println("Enter price of the Product: ");
                                price = input.nextDouble();
                                break;
                            }catch (Exception e){
                                System.out.println("Invalid Input, Price should be a number.");
                                input.next();
                            }
                        }

                        while (true){
                            System.out.println("Enter the size of the Clothing(XS/S/M/L/XL): ");
                            size = input.next().toUpperCase().trim();

                            if(size.equals("XS") || size.equals("S") || size.equals("M") || size.equals("L") ||
                                    size.equals("XL")){

                                System.out.println("Enter the colour of the Clothing: ");
                                colour = input.next();
                                break;
                            }else {
                                System.out.println("Wrong Input, please enter your choice from the given sizes.");
                            }
                        }
                        // Create and add the clothing product to the system
                        Product product = new Clothing(productID,productName,numberOfAvailableItems,price,size,colour);
                        addProduct(product);
                        System.out.println("Product added successfully!");
                        break;
                    }else{
                        System.out.println("Wrong input, please enter again.");
                    }
                }
                break;

            case 2:
                deleteProduct(input);
                break;

            case 3:
                printList();
                break;

            case 4:
                saveToFile();
                break;

            case 5:
                openGUI();
                break;

            case 6:
                // Save user data to file and exit the system
                saveUserToFile();
                exit = true;
                System.out.println("System termination successful, Thank You! ");
                break;

            default:
                // Invalid menu option input
                System.out.println("Invalid input , Please enter a number from the menu");
        }
        // Return the exit status
        return exit;
    }
}
