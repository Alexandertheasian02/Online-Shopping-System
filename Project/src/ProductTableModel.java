import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
public class ProductTableModel extends AbstractTableModel{
    private String[] columnNames = {"Product ID","Name","Category","Price(£)","Info"};
    // Array representing the column names in the product table
    private ArrayList<Product> productsToBeDisplayed; // List of products to be displayed in the table

    public ProductTableModel(ArrayList<Product> productsToBeDisplayed){ // Parameterized constructor
        this.productsToBeDisplayed = productsToBeDisplayed;
    }

    @Override
    public int getRowCount() { // Getter method for retrieving the number of rows in the table
        return productsToBeDisplayed.size();
    }

    @Override
    public int getColumnCount() { // Getter method for retrieving the number of columns in the table
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) { // Returns the value at the specified cell in the table
        Product selectedProduct = productsToBeDisplayed.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {return selectedProduct.getProductID();}
            case 1 -> {return selectedProduct.getProductName();}
            case 2 -> {
                if (selectedProduct.getProductType().equals("Clothing")) {
                    return "Clothing";
                } else return "Electronics";
            }
            case 3 -> {return selectedProduct.getPrice();}
            case 4 -> {
                if(selectedProduct.getProductType().equals("Clothing")){
                    Clothing selectedClothingProduct = (Clothing)selectedProduct;
                    return selectedClothingProduct.getSize()+", "+selectedClothingProduct.getColour();
                }
                else{
                    Electronics selectedElectronicsProduct = (Electronics) selectedProduct;
                    return selectedElectronicsProduct.getBrand()+ ", " +
                            selectedElectronicsProduct.getWarrantyPeriod().replace("m"," months warranty");
                }
            }
            default -> {return null;}
        }
    }

    @Override
    public String getColumnName(int col) { // Returns the column name for the specified column index
        return columnNames[col];
    }

    public Product getRowObject(int Row){ // Returns the Product object corresponding to the specified row
        for(Product product: productsToBeDisplayed){
            if(this.getValueAt(Row,0).equals(product.getProductID())){
                return product;
            }
        }
        return null;
    }
}
