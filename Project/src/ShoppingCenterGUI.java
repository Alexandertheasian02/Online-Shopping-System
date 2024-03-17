import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ShoppingCenterGUI extends JFrame{

    public ShoppingCenterGUI(ArrayList<Product> productList,ShoppingCart shoppingCart,boolean firstPurchase){
        // Initialization of GUI components
        JPanel selectProductPanel,selectProductAndCartBtn,topPanel,bottomPanel;
        JLabel selectProductCategoryLbl;
        String[] categories = {"All","Electronics","Clothing"};
        JComboBox<String> selectProductCategoryComboBox;
        JButton cartBtn,addToCartBtn,sortItemBtn;
        Font headerFont = new Font("Calibre", Font.BOLD, 12);
        Font bodyFont = new Font("Arial", Font.PLAIN, 12);

        // Set the layout for the main frame
        this.setLayout(new BorderLayout());

        // Select product panel
        selectProductPanel = new JPanel();
        selectProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,30));

        // Components for selecting product category
        selectProductCategoryLbl = new JLabel("  Select Product Category");
        selectProductCategoryComboBox = new JComboBox<>(categories);
        selectProductCategoryComboBox.setPreferredSize(new Dimension(120,20));
        sortItemBtn = new JButton("Sort Items");

        // Add components to select product panel
        selectProductPanel.add(selectProductCategoryLbl);
        selectProductPanel.add(selectProductCategoryComboBox);
        selectProductPanel.add(sortItemBtn);

        // Select product and cart button panel
        selectProductAndCartBtn = new JPanel();
        selectProductAndCartBtn.setLayout(new BorderLayout());

        // Cart button components
        JPanel cartButtonPanel = new JPanel();
        cartButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        cartBtn = new JButton("Shopping Cart");
        cartBtn.setPreferredSize(new Dimension(120,30));
        cartButtonPanel.add(cartBtn);

        // Add components to select product and cart button panel
        selectProductAndCartBtn.add(selectProductPanel,BorderLayout.CENTER);
        selectProductAndCartBtn.add(cartButtonPanel,BorderLayout.EAST);

        // Top panel containing product table
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Components for the product table
        ProductTableModel tableModel = new ProductTableModel(productList);
        JTable productsTable = new JTable(tableModel);

        // Set header attributes for the product table
        JTableHeader tableHeader = productsTable.getTableHeader();
        tableHeader.setReorderingAllowed(false); // Disable reordering
        tableHeader.setFont(headerFont); // Set font to bold
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 35));

        // Apply custom renderer to each column of the table
        for (int i = 0; i < productsTable.getColumnCount(); i++) {
            productsTable.getColumnModel().getColumn(i).setCellRenderer(new CustomRowRenderer());
        }

        // Set row height and minimum width for columns
        productsTable.setRowHeight(30);
        productsTable.getColumnModel().getColumn(4).setMinWidth(150);

        // Create a panel for the product table
        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new EmptyBorder(40,0,10,0));
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(new JScrollPane(productsTable),BorderLayout.CENTER);

        // Add components to the top panel
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.add(selectProductAndCartBtn,BorderLayout.NORTH);
        topPanel.add(tablePanel,BorderLayout.CENTER);

        // Bottom panel containing product details and add-to-cart button
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Components for displaying product details
        JPanel productDetails = new JPanel();
        productDetails.setLayout(new GridLayout(7,1,10,10));
        productDetails.setBorder(new EmptyBorder(0, 30, 30, 10));

        JLabel productDetailsHeader = new JLabel("Selected Product - Details");
        JLabel idLbl,categoryLbl,nameLbl,brandLbl,warrantyPeriodLbl,availableItemsLbl;
        idLbl = new JLabel("");
        categoryLbl = new JLabel("");
        nameLbl = new JLabel("");
        brandLbl = new JLabel("");
        warrantyPeriodLbl = new JLabel("");
        availableItemsLbl = new JLabel("");
        idLbl.setFont(bodyFont);
        categoryLbl.setFont(bodyFont);
        nameLbl.setFont(bodyFont);
        brandLbl.setFont(bodyFont);
        warrantyPeriodLbl.setFont(bodyFont);
        availableItemsLbl.setFont(bodyFont);
        productDetails.add(productDetailsHeader);
        productDetails.add(idLbl);
        productDetails.add(categoryLbl);
        productDetails.add(nameLbl);
        productDetails.add(brandLbl);
        productDetails.add(warrantyPeriodLbl);
        productDetails.add(availableItemsLbl);

        // Panel for the add-to-cart button
        JPanel addToCartPanel = new JPanel();
        addToCartPanel.setLayout(new FlowLayout());
        addToCartBtn = new JButton("Add to Shopping Cart");
        addToCartPanel.add(addToCartBtn);

        // Add components to the bottom panel
        bottomPanel.add(productDetails,BorderLayout.CENTER);
        bottomPanel.add(addToCartPanel,BorderLayout.SOUTH);

        // Add top and bottom panels to the main frame
        this.add(topPanel,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);

        // Add listeners and handlers
        productsTable.getSelectionModel().addListSelectionListener(new ProductTableListener(productsTable,
                productList,idLbl,categoryLbl,nameLbl,brandLbl,warrantyPeriodLbl,availableItemsLbl));

        //sorting instance to sort table columns
        TableRowSorter<ProductTableModel> sorter = new TableRowSorter<>(tableModel);
        productsTable.setRowSorter(sorter);

        // Action Listener to filtering products on products table
        selectProductCategoryComboBox.addActionListener(new ProductCategoryHandler(selectProductCategoryComboBox,
                productsTable,sorter));

        // Cart table model to create cart GUI
        TableModelCart cartTable = new TableModelCart(shoppingCart);

        // Creating cart GUI
        ShoppingCartGUI shoppingCartGUI = new ShoppingCartGUI(cartTable);
        shoppingCartGUI.setTitle("Shopping Cart");
        shoppingCartGUI.setSize(700, 450);
        shoppingCartGUI.setVisible(false);

        // Adding action listener to the addToCart button
        addToCartBtn.addActionListener(new AddToCartBtnHandler(productsTable,productList,
                cartTable,shoppingCartGUI,firstPurchase));

        // Adding action listener to the cart button
        cartBtn.addActionListener(new CartBtnHandler(shoppingCartGUI));

        // Adding action listener to the sort button
        sortItemBtn.addActionListener(new SortBtnHandler(tableModel,productsTable,selectProductCategoryComboBox));

        // Adding a window listener to handle window closing event
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                shoppingCartGUI.dispose();
            }
        });
    }
}
