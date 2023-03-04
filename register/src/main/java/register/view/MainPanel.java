package register.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
  /**
   * Creates a new main panel
   * 
   * @param panels the panels to add
   * @author Oliver Levay
   * @author Simon Persson
   */
  public MainPanel(JPanel... panels) {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    for (JPanel panel : panels) {
      this.add(panel);
    }
  }
}
