import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ShoppingCartGUI extends JFrame{
    JLabel totalPriceLbl, firstPurchaseDiscountPriceLbl, threeItemsDiscountPriceLbl, finalPriceLbl;
    // Labels for displaying prices

    public ShoppingCartGUI(TableModelCart cartTableModel){
        Font bodyFont = new Font("Arial", Font.PLAIN, 12);
        Font headerFont = new Font("Calibre", Font.BOLD, 13);
        this.setLayout(new BorderLayout());

        // Add the cartTable to a JScrollPane and add it to the frame
        JTable cartTable = new JTable(cartTableModel);
        JScrollPane scrollPane = new JScrollPane(cartTable);

        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new EmptyBorder(40,20,10,20));
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrollPane,BorderLayout.CENTER);

        // Set up cell rendering and column widths
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0; i<3; i++){
            cartTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        cartTable.setRowHeight(30);
        cartTable.getColumnModel().getColumn(0).setMinWidth(150);

        // Set up table header attributes
        JTableHeader tableHeader = cartTable.getTableHeader();
        tableHeader.setReorderingAllowed(false); // Disable reordering
        tableHeader.setFont(headerFont); // Set font to bold
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 35));

        // Bottom panel - contains four panels for each row
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(4,1,0,10));
        bottomPanel.setBorder(new EmptyBorder(15,20,35,100));

        // Panels for 4 rows - each panel has 2 labels
        JPanel totalPanel,firstPurchasePanel,threeItemsPanel,finalTotalPanel;

        totalPanel = new JPanel(new BorderLayout());
        firstPurchasePanel = new JPanel(new BorderLayout());
        threeItemsPanel = new JPanel(new BorderLayout());
        finalTotalPanel = new JPanel(new BorderLayout());

        // Labels for shopping cart purchase section
        JLabel totalLabel,firstPurchaseLabel, threeItemsLabel,finalTotalLabel;

        // Labels for prices description
        totalLabel = new JLabel("Total      ");
        firstPurchaseLabel = new JLabel("First Purchase Discount (10%)      ");
        threeItemsLabel = new JLabel("Three Items in same Category (20%)      ");
        finalTotalLabel = new JLabel("Final Total      ");

        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        firstPurchaseLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        threeItemsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        finalTotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // labels for prices values
        totalPriceLbl = new JLabel("0 £");
        firstPurchaseDiscountPriceLbl = new JLabel("-0 £");
        threeItemsDiscountPriceLbl = new JLabel("-0 £");
        finalPriceLbl = new JLabel("0 £");

        // Set fonts for labels
        totalLabel.setFont(bodyFont);
        firstPurchaseLabel.setFont(bodyFont);
        threeItemsLabel.setFont(bodyFont);
        totalPriceLbl.setFont(bodyFont);
        firstPurchaseDiscountPriceLbl.setFont(bodyFont);
        threeItemsDiscountPriceLbl.setFont(bodyFont);

        // Set preferred size for price labels
        Dimension priceLabelsSize = new Dimension(100,20);
        totalPriceLbl.setPreferredSize(priceLabelsSize);
        firstPurchaseDiscountPriceLbl.setPreferredSize(priceLabelsSize);
        threeItemsDiscountPriceLbl.setPreferredSize(priceLabelsSize);
        finalPriceLbl.setPreferredSize(priceLabelsSize);

        // Add labels to respective panels
        totalPanel.add(totalLabel,BorderLayout.CENTER);
        totalPanel.add(totalPriceLbl,BorderLayout.EAST);
        firstPurchasePanel.add(firstPurchaseLabel,BorderLayout.CENTER);
        firstPurchasePanel.add(firstPurchaseDiscountPriceLbl,BorderLayout.EAST);
        threeItemsPanel.add(threeItemsLabel,BorderLayout.CENTER);
        threeItemsPanel.add(threeItemsDiscountPriceLbl,BorderLayout.EAST);
        finalTotalPanel.add(finalTotalLabel,BorderLayout.CENTER);
        finalTotalPanel.add(finalPriceLbl,BorderLayout.EAST);

        // Add panels to the bottom panel
        bottomPanel.add(totalPanel);
        bottomPanel.add(firstPurchasePanel);
        bottomPanel.add(threeItemsPanel);
        bottomPanel.add(finalTotalPanel);

        // Add components to the frame
        add(tablePanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
    }

    public void updatePrices(double totalPrice,ShoppingCart shoppingCart,boolean firstPurchase){
        // Updates the displayed prices in the GUI
        double discount1 = 0;
        double discount2 = shoppingCart.getThreeItemsDiscount();

        this.totalPriceLbl.setText((Math.round(totalPrice* 100.0) / 100.0) +" £");
        if(firstPurchase) {
            discount1 = Math.round((totalPrice*0.1) * 100.0) / 100.0;
            this.firstPurchaseDiscountPriceLbl.setText("-" + discount1 + " £");
        }
        this.threeItemsDiscountPriceLbl.setText("-"+ discount2 +" £");

        totalPrice = totalPrice-(discount1+discount2);
        this.finalPriceLbl.setText((Math.round(totalPrice* 100.0) / 100.0) +" £");
    }
}
