package register.view.tabels;

import java.io.IOException;

import javax.swing.JOptionPane;

import register.model.Driver;
import register.model.DriverTable;

public class DriverTableView extends TableView {
  DriverTable table;

  /**
   * Creates a new driver table view
   *
   * @param table the table to view
   * @author Oliver Levay
   * @author Simon Persson
   */
  public DriverTableView(DriverTable table) {
    super(table);
    this.table = table;
  }

  /**
   * Gets the model
   *
   * @author Oliver Levay
   * @author Simon Persson
   * @return model
   */
  public DriverTable getModel() {
    return table;
  }

  /**
   * Adds driver in the Gui
   *
   * @author Oliver Levay
   * @author Lucas Wittich
   */
  public void createDriver() {
    boolean isSenior = JOptionPane.showConfirmDialog(null, "Is this a Senior?", "Senior Check",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    table.registerToTable(new Driver("Driver", isSenior));
    try {
      table.saveToFile();
    } catch (IOException error) {
      JOptionPane.showMessageDialog(null, "Failed to write to file", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
