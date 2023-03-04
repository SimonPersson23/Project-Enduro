package register.view.tabels;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import register.model.RegisterModel;
import register.model.RegisterTable;

public class RegisterTableView extends TableView {
  RegisterTable table;

  /**
   * Creates a new register table view
   * 
   * @param table the table to register to
   * @author Oliver Levay
   * @author Simon Persson
   */
  public RegisterTableView(RegisterTable table) {
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
  public RegisterTable getModel() {
    return table;
  }

  /**
   * Creates a new register and adds it to the table
   * 
   * 
   * @author Oliver Levay
   * @author Sharmarke Osman
   * @author Gustav Serger
   * @author Lucas Wittich
   * @author Abdbulrahman Husari
   * @param table The table to register to
   * @return ActionListener
   */
  public void createNewRegisterAndAddToTable() {
    String time = LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
    boolean validInput = false;
    while (!validInput) {
      String inputValue = JOptionPane.showInputDialog("Start Number or leave empty");

      // Cancel clicked
      if (inputValue == null)
        return;

      RegisterModel register = new RegisterModel(time);
      try {
        int startNumber = Integer.parseInt(inputValue);
        register.setStartNum(startNumber);
        validInput = true;
        table.registerToTable(register);
      } catch (NumberFormatException exception) {
        // Not an integer input
        if (inputValue.equals("")) { // Accept empty input
          table.registerToTable(register);
          validInput = true;
        } else {
          JOptionPane.showMessageDialog(null, "Please enter an integer", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
      try {
        table.saveToFile();
      } catch (IOException error) {
        JOptionPane.showMessageDialog(null, "Failed to write to file", "Error", JOptionPane.ERROR_MESSAGE);
      }

    }

  }
}
