import java.util.ArrayList;
import java.io.*;

public class User {
    private String userName; // Instance variable for the Username
    private String password; // Instance variable for the user password
    private boolean hasPurchased; // Boolean to check if the user has made a purchase

    public User(String userName,String password,boolean hasPurchased){ // Parameterized constructor
        this.userName=userName;
        this.password=password;
        this.hasPurchased = hasPurchased;
    }

    public String getUserName() { // Getter method for retrieving the username
        return userName;
    }

    public String getPassword() { // Getter method for retrieving the user password
        return password;
    }

    public boolean getHasPurchased(){ // Getter method for retrieving the boolean purchase
        return hasPurchased;
    }

    public void setHasPurchased(boolean hasPurchased){ // Setter method for the boolean purchase
        this.hasPurchased = hasPurchased;
    }

    /* Method to save user information to a file
    Writes user details to a text file, one user per line*/
    public static void saveUsersToFile(ArrayList<User> userList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Saved_Users.txt"))) {
            // Iterate through each user in the list
            for (User user : userList) {
                // Write user information to the file
                writer.write(user.getUserName() + ":" + user.getPassword()+ ":" +user.hasPurchased);
                // Move to the next line for the next user
                writer.newLine();
            }
        } catch (IOException e) { // Handle and log an error if writing to the file fails
            System.out.println("An error occurred while writing user file: " + e.getMessage());
        }
    }

    // Method to read user information from a file
    public static ArrayList<User> readUsersFromFile() { // Create an ArrayList to store the read user information
        ArrayList<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Saved_Users.txt"))) {
            // Read each line from the file
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts based on ":" delimiter
                String[] parts = line.split(":");
                String userName = parts[0].trim();
                String password = parts[1].trim();
                boolean hasMadePurchase = Boolean.parseBoolean(parts[2].trim());
                // Create a new Main.User object and add it to the ArrayList
                userList.add(new User(userName, password,hasMadePurchase));
            }
        } catch (IOException e) { // Handle and log an error if reading from the file fails
            System.out.println("An error occurred while reading user file: " + e.getMessage());
        }
        return userList;
    }

}
