import javax.swing.table.AbstractTableModel;

public class TableModelCart extends AbstractTableModel {
    private ShoppingCart shoppingCart; // Reference to the Main.ShoppingCart Instance
    private String[] namesOfColumns = {"Product","Quantity","Price"};// Array representing the column names in the table
    public TableModelCart(ShoppingCart shoppingCart) { // Parameterized Constructor
        this.shoppingCart = shoppingCart;
    }

    @Override
    public int getRowCount() {
        //  Returns the number of rows in the table, which is equal to the number of items in the shopping cart
        return shoppingCart.getCartList().size();
    }

    @Override
    public int getColumnCount() {
        // Returns the number of columns in the table, which is equal to the length of the column names array
        return namesOfColumns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) { // Returns the value at the specified cell in the table
        Product selectedProduct = shoppingCart.getCartList().get(rowIndex);
        switch (columnIndex) {
            case 0 -> { // For the "Main.Product" column, create a string with product details
                String details = selectedProduct.getProductID()+", "+selectedProduct.getProductName()+", ";
                if(selectedProduct.getProductType().equals("Clothing")){ // For clothing product
                    Clothing selectedClothingProduct = (Clothing)selectedProduct;
                    details += selectedClothingProduct.getSize()+", "+selectedClothingProduct.getColour();
                }
                else { // For electronics product
                    Electronics selectedElectronicsProduct = (Electronics) selectedProduct;
                    details += selectedElectronicsProduct.getBrand()+", "+
                            selectedElectronicsProduct.getWarrantyPeriod().replace("m"," months");
                }
                return details;
            }
            case 1 -> {
                // For the "Quantity" column, return the quantity of the selected product in the cart
                return shoppingCart.getCartQuantity().get(selectedProduct);}
            case 2 -> {
                // For the "Price" column, calculate and return the total price for the selected product
                double price = selectedProduct.getPrice()*shoppingCart.getCartQuantity().get(selectedProduct);
                price = Math.round(price * 100.0) / 100.0;
                return price + "£";
            }

            default -> {return null;}
        }
    }
    @Override
    public String getColumnName(int col) { // Getter method for returning the column name for the specified column index
        return namesOfColumns[col];
    }

    public ShoppingCart getShoppingCart() { //Gets the associated Main.ShoppingCart for the table model
        return shoppingCart;
    }
}
