import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
public class ProductTableListener implements ListSelectionListener{
    private JTable productsTable; // Reference to the JTable displaying products
    private ArrayList<Product> productArrayList; // List of available products
    private JLabel idLbl, categoryLbl, nameLbl, brandLbl, warrantyPeriodLbl, availableItemsLbl;
    // JLabels to display product information

    public ProductTableListener(JTable productsTable, ArrayList<Product> productArrayList, JLabel idLbl,
                                JLabel categoryLbl, JLabel nameLbl, JLabel brandLbl, JLabel warrantyPeriodLbl,
                                JLabel availableItemsLbl){ // Parameterized constructor
        this.productsTable = productsTable;
        this.productArrayList = productArrayList;
        this.idLbl = idLbl;
        this.categoryLbl = categoryLbl;
        this.nameLbl = nameLbl;
        this.brandLbl = brandLbl;
        this.warrantyPeriodLbl = warrantyPeriodLbl;
        this.availableItemsLbl = availableItemsLbl;
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) { // Check if the selection process has finished
            int selectedRow = productsTable.getSelectedRow();
            if (selectedRow != -1) { // Check if any row is selected
                String selectedProductId = productsTable.getValueAt(selectedRow,0).toString();
                Product selectedProduct = null;
                boolean selectedItemIsNull = true;
                // Iterate through the productList to find the selected product
                for(Product checker : productArrayList){
                    if (checker.getProductID().equals(selectedProductId)) {
                        selectedProduct = checker;
                        selectedItemIsNull = false;
                        break;
                    }
                }
                // If the selected product is found, update the JLabels with its information
                if(!selectedItemIsNull) {
                    idLbl.setText("Product Id: "+ selectedProduct.getProductID());
                    nameLbl.setText("Name: "+ selectedProduct.getProductName());
                    availableItemsLbl.setText("Items Available: "+ selectedProduct.getAvailableItems());
                    // Check if the selected product is of type Clothing or Electronics
                    if (selectedProduct.getProductType().equals("Clothing")) {
                        Clothing selectedCloth = (Clothing)selectedProduct;
                        categoryLbl.setText("Category: Clothing");
                        brandLbl.setText("Size: "+ selectedCloth.getSize());
                        warrantyPeriodLbl.setText("Colour: "+ selectedCloth.getColour());
                    } else {
                        Electronics selectedElectronic = (Electronics)selectedProduct;
                        categoryLbl.setText("Category: Electronics");
                        brandLbl.setText("Brand: "+selectedElectronic.getBrand());
                        warrantyPeriodLbl.setText("Warranty Period: "+selectedElectronic.getWarrantyPeriod().replace
                                ("m"," months"));
                    }
                }
            }
        }
    }
}
