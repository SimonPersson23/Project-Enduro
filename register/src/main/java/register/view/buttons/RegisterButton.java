package register.view.buttons;

import register.view.tabels.RegisterTableView;

public class RegisterButton extends Button {
  RegisterTableView table;

  /**
   * Creates a new register button
   * 
   * @param text  the text to display on the button
   * @param table the table to add the register to
   * @author Oliver Levay
   * @author Simon Persson
   */
  public RegisterButton(String text, RegisterTableView table) {
    super(text);
    this.table = table;
  }

  /**
   * Creates a new register and adds it to the table and saves in to the file
   * 
   * @author Oliver Levay
   * @author Simon Persson
   */
  @Override
  public void onClick() {
    table.createNewRegisterAndAddToTable();
  }
}
