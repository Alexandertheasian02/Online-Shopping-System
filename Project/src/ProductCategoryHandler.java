import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ProductCategoryHandler implements ActionListener{

    JComboBox<String> selectProductCategoryComboBox; // Reference to the JComboBox for selecting product categories
    JTable productsTable; // Reference to the JTable displaying products
    TableRowSorter<ProductTableModel> sorter; // TableRowSorter for sorting and filtering the JTable

    public ProductCategoryHandler(JComboBox<String> selectProductCategoryComboBox,JTable productsTable,
                                  TableRowSorter<ProductTableModel> sorter){ // Parameterized constructor
        this.selectProductCategoryComboBox = selectProductCategoryComboBox;
        this.productsTable = productsTable;
        this.sorter = sorter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedIndex = selectProductCategoryComboBox.getSelectedIndex();
        if(selectedIndex == 1){
            sorter.setRowFilter(RowFilter.regexFilter("^[E]", 2)); // Hide clothing items
        } else if (selectedIndex == 2) {
            sorter.setRowFilter(RowFilter.regexFilter("^[C]",2)); // Hide electronics items
        } else {
            // Reset filters if no category is selected
            sorter.setRowFilter(null);
        }
    }
}
