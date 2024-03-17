import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartBtnHandler implements ActionListener {
    ShoppingCartGUI shoppingCartGUI; // Reference to the ShoppingCartGUI instance

    public CartBtnHandler(ShoppingCartGUI shoppingCartGUI){ // Parameterized constructor
        this.shoppingCartGUI = shoppingCartGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!shoppingCartGUI.isActive()){ // Check if the shopping cart GUI is not already active
            shoppingCartGUI.setVisible(true); // Set it visible
        }
    }
}
