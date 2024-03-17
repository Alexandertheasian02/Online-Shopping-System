import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
public class AddToCartBtnHandler implements ActionListener {
    private JTable productsTable; // Reference to the JTable displaying products
    private ArrayList<Product> productList; // Instance variable for the list of available products
    private TableModelCart cartTableModel; // Reference to the TableModelCart
    private ShoppingCartGUI shoppingCartGUI; // Reference to the ShoppingCartGUI
    private boolean firstPurchase; // Flag to indicate if it's the first purchase

    public AddToCartBtnHandler(JTable productsTable,ArrayList<Product> productList,
                                   TableModelCart cartTableModel,ShoppingCartGUI shoppingCartGUI,boolean firstPurchase){
        // Parameterized constructor
        this.productsTable = productsTable;
        this.productList = productList;
        this.cartTableModel = cartTableModel;
        this.shoppingCartGUI = shoppingCartGUI;
        this.firstPurchase = firstPurchase;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        int selectedRow = productsTable.getSelectedRow();
        // Check if a row is selected
        if(selectedRow != -1) {
            String selectedProductId = productsTable.getValueAt(selectedRow, 0).toString();
            Map<Product, Integer> quantityOnCart = cartTableModel.getShoppingCart().getCartQuantity();

            // Get the selected product and check its availability
            for (Product checker : productList) {
                if (checker.getProductID().equals(selectedProductId) && checker.getAvailableItems() > 0) {
                    // Calculate the available items considering items already in the cart
                    int availableItems;
                    if(quantityOnCart.containsKey(checker)){
                        availableItems = checker.getAvailableItems() - quantityOnCart.get(checker);
                    }
                    else availableItems = checker.getAvailableItems();

                    if (availableItems > 0) { // If there are available items, add the product to the cart
                        cartTableModel.getShoppingCart().addProductToCart(checker);
                        shoppingCartGUI.updatePrices(cartTableModel.getShoppingCart().totalCost(),
                                cartTableModel.getShoppingCart(),firstPurchase);
                        cartTableModel.fireTableDataChanged();
                    }
                    break;
                }
            }
        }
    }
}


