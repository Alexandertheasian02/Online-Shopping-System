import java.util.ArrayList;
import java.util.Map;

public class ShoppingCart{
    private ArrayList<Product> cartList; // ArrayList of products in the cart
    private Map<Product, Integer> cartQuantity; // Map to store the quantity of each product in the cart

    public ShoppingCart(ArrayList<Product> cartList, Map<Product, Integer> cartQuantity){ // Parameterized Constructor
        this.cartList = cartList;
        this.cartQuantity = cartQuantity;
    }

    public  ArrayList<Product> getCartList(){ // Getter method for retrieving the cart ArrayList
        return cartList;
    }

    public Map<Product, Integer> getCartQuantity() { // Getter method for retrieving the quantity Map
        return cartQuantity;
    }

    public void addProductToCart(Product product){ // Adds a product to the cart, updates its quantity
        if(cartList.contains(product)){
            cartQuantity.put(product, cartQuantity.get(product) +1);
        }
        else {
            cartList.add(product);
            cartQuantity.put(product,1);
        }
    }

    public double totalCost(){ // Calculates the total cost of all items in the cart
        double totalCost = 0;
        double itemPrice;
        for(Product product:cartList){
            itemPrice = product.getPrice() * cartQuantity.get(product);
            totalCost += Math.round(itemPrice * 100.0)/100.0;
        }
        return totalCost;
    }

    public double getThreeItemsDiscount (){ // Calculates the discount for three or more products from the same category
        double addedDiscount = 0;
        int clothingCategoryItem = 0;
        int electronicsCategoryItem = 0;
        int clothingQuantity = 0;
        int electronicsQuantity = 0;
        boolean sameThreeProducts = false;

        for (Product product: cartList) {
            // Checking if the quantity of the current product in the cart is at least three
            if (getCartQuantity().containsKey(product) && getCartQuantity().get(product) >= 3)
                sameThreeProducts = true;

            if (product instanceof Clothing) {
                clothingCategoryItem++;
                clothingQuantity = clothingQuantity + getCartQuantity().get(product);
            } else {
                electronicsCategoryItem++;
                electronicsQuantity = electronicsQuantity + getCartQuantity().get(product);
            }
        }
        /* Applying a 20% discount if there are at least three items of the same category, or if there are at least
        three items of the same product in the cart*/
        if (clothingCategoryItem >= 3 || electronicsCategoryItem >= 3 || sameThreeProducts || clothingQuantity >= 3 ||
                electronicsQuantity >= 3){
            addedDiscount = totalCost()*0.2;
        }
        return  Math.round(addedDiscount * 100.0)/100.0;  // Rounding the calculated discount to two decimal places
    }

}
