package register.view;

import javax.swing.Box;
import javax.swing.JPanel;

import register.view.buttons.AddDriverButton;
import register.view.buttons.Button;
import register.view.buttons.DeleteButton;
import register.view.buttons.ExitButton;
import register.view.buttons.MassStartButton;
import register.view.buttons.RegisterButton;
import register.view.frames.MainFrame;
import register.view.tabels.DriverTableView;
import register.view.tabels.RegisterTableView;

public class ButtonPanel extends JPanel {
  /**
   * Creates a new button panel
   *
   * @author Oliver Levay
   * @author Simon Persson
   *
   * @param startTable
   * @param finishTable
   * @param driversTable
   * @param frame
   */
  public ButtonPanel(RegisterTableView startTable, RegisterTableView finishTable, DriverTableView driversTable,
      MainFrame frame) {
    AddDriverButton addDriverButton = new AddDriverButton(driversTable);
    RegisterButton startButton = new RegisterButton("Start", startTable);
    MassStartButton massStartButton = new MassStartButton(startTable.getModel(), driversTable.getModel());
    RegisterButton finishButton = new RegisterButton("Finish", finishTable);
    DeleteButton deleteButton = new DeleteButton(startTable, finishTable, driversTable);
    ExitButton exitButton = new ExitButton(frame);
    addButtons(addDriverButton, startButton, massStartButton, finishButton, deleteButton, exitButton);
  }

  /**
   * @author Oliver Levay
   * @author Simon Persson
   * @param buttons the buttons to add
   */
  private void addButtons(Button... buttons) {
    for (Button button : buttons) {
      add(button);
      this.add(Box.createHorizontalStrut(10));
    }
  }
}
