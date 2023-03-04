package register.view.tabels;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import register.model.Table;

public class TableView extends JScrollPane {
  JTable jTable;
  Table table;

  /**
   * Creates a new table view
   *
   * @param table the table to view
   * @author Oliver Levay
   * @author Simon Persson
   */
  public TableView(Table table) {
    super();
    this.table = table;
    this.jTable = new JTable(table);

    jTable.setFont(jTable.getFont().deriveFont(40f));
    jTable.getTableHeader().setFont(jTable.getFont().deriveFont(40f));
    jTable.setRowHeight(40);

    this.setViewportView(jTable);

    this.loadFromFile();
  }

  /**
   * Deletes the selected rows
   * 
   * @author Oliver Levay
   * @author Simon Persson
   * @throws IOException
   */
  public void deleteSelectedRows() throws IOException {
    int[] rows = jTable.getSelectedRows();
    for (int i = rows.length - 1; i >= 0; i--) {
      this.deleteRow(rows[i]);
    }
  }

  /**
   * Loads the table from file
   * 
   * @author Oliver Levay
   * @author Simon Persson
   */
  public void loadFromFile() {
    try {
      table.loadFromFile();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Failed to load from file: " + table.getFileName(), "Error",
          JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
      System.exit(1);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null,
          "Something is wrong with the file: " + table.getFileName() + ", please check it.", "Error",
          JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
      System.exit(1);
    }
  }

  public String getFileName() {
    return table.getFileName();
  }

  public void deleteRow(int row) throws IOException {
    table.deleteRow(row);
  }
}
