package result.view.panels;

import javax.swing.JPanel;

import result.model.Table;
import result.view.FileHandler;
import result.view.MainFileHandler;
import result.view.buttons.ExitButton;
import result.view.buttons.OpenSettingsButton;
import result.view.buttons.ResultButton;
import result.view.frames.MainFrame;

public class ActionButtonPanel extends JPanel {
  /**
   * Creates a new ActionButtonPanel with a ResultButton and an ExitButton.
   * 
   * @param table the table to be updated when the ResultButton is clicked
   * @param frame the frame to be closed when the ExitButton is clicked
   * @author Oliver Levay
   * @author Sharmarke Osman
   */
  public ActionButtonPanel(Table table, MainFrame frame, MainFileHandler fileHandler) {
    ResultButton resultButton = new ResultButton(table, fileHandler);
    OpenSettingsButton openSettingsButton = new OpenSettingsButton(fileHandler);
    ExitButton exitButton = new ExitButton(frame);
    this.add(resultButton);
    this.add(openSettingsButton);
    this.add(exitButton);
  }
}
