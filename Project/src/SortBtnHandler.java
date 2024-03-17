import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortBtnHandler implements ActionListener {
    ProductTableModel tableModel; // Reference to the Main.ProductTableModel associated with
    JTable productTable; // Reference to the JTable displaying the products
    JComboBox<String> selectCategoryComboBox; // Reference to the JComboBox for selecting product categories
    public SortBtnHandler(ProductTableModel tableModel, JTable productTable, JComboBox<String> selectCategoryComboBox){
        // Parameterized constructor
        this.tableModel=tableModel;
        this.productTable = productTable;
        this.selectCategoryComboBox = selectCategoryComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Creating a TableRowSorter to handle sorting for the table
        TableRowSorter<ProductTableModel> sorter = new TableRowSorter<>(tableModel);
        productTable.setRowSorter(sorter);

        // ActionListener to the JComboBox for category filtering
        selectCategoryComboBox.addActionListener(new ProductCategoryHandler(selectCategoryComboBox,productTable,sorter));
        // Set the initial sorting order for the table (ascending by default)
        sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(0,SortOrder.ASCENDING)));
    }
}
