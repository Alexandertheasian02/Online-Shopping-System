import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUILogin extends JFrame implements ActionListener{
    private JTextField usernameInputField; // JTextField for user's name input
    private JPasswordField passwordInputField; // JPasswordField for user's password input
    private JLabel messageToDisplay; // JLabel for  Error and successful messages
    private ArrayList<User> userList; // ArrayList of users
    private ArrayList<Product> productList; // ArrayList of products

    public GUILogin(ArrayList<User> userList,ArrayList<Product> productList){
        this.userList = userList;
        this.productList = productList;

        Font headerFont = new Font("Arial", Font.BOLD, 13);
        // Set JFrame properties
        setTitle("Login Portal");
        setSize(500, 500);
        setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel(new GridLayout(2,2,25,20));
        topPanel.setBorder(new EmptyBorder(110,0,40,95));

        JLabel username = new JLabel("Username :");
        username.setFont(headerFont);
        username.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel password = new JLabel("Password :");
        password.setFont(headerFont);
        password.setHorizontalAlignment(SwingConstants.RIGHT);

        usernameInputField = new JTextField();
        passwordInputField = new JPasswordField();
        usernameInputField.setFont(headerFont);
        passwordInputField.setFont(headerFont);
        usernameInputField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordInputField.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(username);
        topPanel.add(usernameInputField);
        topPanel.add(password);
        topPanel.add(passwordInputField);

        // Buttons and Message Display
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        messageToDisplay = new JLabel("    ");
        messageToDisplay.setBorder(new EmptyBorder(20,0,0,0));
        messageToDisplay.setFont(headerFont);
        messageToDisplay.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2,20,20));
        buttonPanel.setBorder(new EmptyBorder(0,150,0,150));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(0,0,150,0));

        bottomPanel.add(buttonPanel,BorderLayout.CENTER);
        bottomPanel.add(messageToDisplay,BorderLayout.SOUTH);

        // Add action listeners
        loginButton.addActionListener( this);
        registerButton.addActionListener( this);

        // Add components to the JFrame
        add(topPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) { // Extract username and password from input fields
        String username = usernameInputField.getText();
        String password = new String(passwordInputField.getPassword()); // Convert char[] to String for password

        boolean firstPurchase ; // Variable to check if it's the user's first purchase
        if (e.getActionCommand().equals("Login")) { // Check username and password for login
            for(User user: userList){
                if(user.getUserName().equals(username) && user.getPassword().equals(password)){
                    firstPurchase = !user.getHasPurchased();
                    if(firstPurchase){
                        user.setHasPurchased(true);
                    }
                    User.saveUsersToFile(userList);
                    this.dispose();

                    // Create shopping cart object and launch Main.ShoppingCenterGUI
                    final ArrayList<Product> productsOnCart = new ArrayList<>(50);
                    final Map<Product, Integer> quantityOnCart = new HashMap<>();
                    ShoppingCart shoppingCart = new ShoppingCart(productsOnCart,quantityOnCart);
                    ShoppingCenterGUI frame = new ShoppingCenterGUI(productList,shoppingCart,firstPurchase);
                    frame.setTitle("Westminster Shopping Center");
                    frame.setSize(700,650);
                    frame.setMinimumSize(new Dimension(600, 400));
                    frame.setVisible(true);
                    return;
                }
                else if(user.getUserName().equals(username)){ // Incorrect Password
                    messageToDisplay.setText("Password is incorrect");
                    passwordInputField.setText("");
                    passwordInputField.setBackground(new Color(255,100,100));
                    this.repaint();
                    return;
                }
            }

            // Incorrect username and password
            usernameInputField.setText("");
            passwordInputField.setText("");
            usernameInputField.setBackground(new Color(255, 100, 100));
            passwordInputField.setBackground(new Color(255,100,100));
            messageToDisplay.setText("Incorrect username and password");
            this.repaint();

        } else if (e.getActionCommand().equals("Register")) {
            boolean newUser = true;
            // Check for existing username
            for(User user: userList){
                if(user.getUserName().equals(username)){
                    newUser = false;
                    break;
                }
            }

            // Register a new user
            if(!username.isEmpty() && !password.isEmpty() && newUser){
                userList.add(new User(username,password,false));
                usernameInputField.setBackground(new Color(100, 200, 200));
                passwordInputField.setBackground(new Color(100,200,200 ));
                messageToDisplay.setText("Registered");
            } else if (!newUser) { // Username already exists
                usernameInputField.setBackground(new Color(255, 100, 100));
                messageToDisplay.setText("This username is already given");
            } else if(username.isEmpty() && password.isEmpty()){ // Empty username and password fields
                messageToDisplay.setText("Please enter the username and password");
                usernameInputField.setBackground(new Color(255, 100, 100));
                passwordInputField.setBackground(new Color(255,100,100));
            } else if (password.isEmpty()) { // Empty password field
                messageToDisplay.setText("Please enter the password");
                passwordInputField.setBackground(new Color(255, 100, 100));
            } else { // Empty username field
                messageToDisplay.setText("Please enter the username");
                usernameInputField.setBackground(new Color(255, 100, 100));
            }
        }
    }
}