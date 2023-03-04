package register.view.frames;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import register.view.MainPanel;

/**
 * The main frame
 */
public class MainFrame {
  JFrame frame;

  /**
   * Creates a new main frame
   * 
   * @author Oliver Levay
   * @author Simon Persson
   */
  public MainFrame() {
    frame = new JFrame("Register");
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  /**
   * Exits the program
   * 
   * @author Oliver Levay
   * @author Simon Persson
   */
  public void exitProgram() {
    frame.dispose();
  }

  /**
   * Adds the main panel to the frame and displays it
   * 
   * @param panel the panel to add
   * @author Oliver Levay
   * @author Simon Persson
   */
  public void addPanel(MainPanel panel) {
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}
