package register.view.buttons;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import register.view.tabels.TableView;

public class DeleteButton extends Button {

  private List<TableView> tables;

  /**
   * Creates a new delete button
   * 
   * @param tables the tables to delete from
   * @author Oliver Levay
   * @author Simon Persson
   */
  public DeleteButton(TableView... tables) {
    super("Delete");
    this.tables = Arrays.asList(tables);
  }

  /**
   * Deletes the selected rows from the tables after asking the user for
   * confirmation
   * 
   * @author Oliver Levay
   * @author Simon Persson
   */
  @Override
  public void onClick() {
    boolean result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected row(s)?",
        "Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    if (!result) {
      return;
    }
    try {
      for (TableView table : tables) {
        table.deleteSelectedRows();
      }
    } catch (IOException e1) {
      // Alert user with a dialog
      JOptionPane.showMessageDialog(null, "Failed to save to file", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
