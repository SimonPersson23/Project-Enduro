package result.view.frames;

import javax.swing.*;

import result.view.panels.MainPanel;

import java.awt.*;

public class MainFrame {

  JFrame frame;

  /**
   * Creates a new MainFrame with the given table width.
   * 
   * @param tableWidth the width of the table
   * @author Oliver Levay
   * @author Sharmarke Osman
   */
  public MainFrame(int tableWidth) {
    super();
    frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setPreferredSize(new Dimension(tableWidth + 30, 500));
  }

  /**
   * Adds the given panel to the frame and packs it.
   * 
   * @param panel the panel to be added to the frame
   * @author Oliver Levay
   * @author Sharmarke Osman
   */
  public void addPanel(MainPanel panel) {
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * Closes the frame causing the program to exit.
   * 
   * @author Oliver Levay
   * @author Sharmarke Osman
   */
  public void exitProgram() {
    frame.dispose();
  }
}
