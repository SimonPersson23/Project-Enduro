package register.view.buttons;

import register.view.tabels.DriverTableView;

public class AddDriverButton extends Button {
  DriverTableView driversTable;

  /**
   * Creates a new add driver button
   * 
   * @param driversTable the table to add the driver to
   * @author Oliver Levay
   * @author Simon Persson
   */
  public AddDriverButton(DriverTableView driversTable) {
    super("Add Driver");
    this.driversTable = driversTable;
  }

  /**
   * Initiates the create driver workflow
   * 
   * @author Oliver Levay
   * @author Simon Persson
   */
  @Override
  public void onClick() {
    driversTable.createDriver();
  }

}
