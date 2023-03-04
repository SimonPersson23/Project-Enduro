package result.view.panels;

import javax.swing.*;

public class MainPanel extends JPanel {
  /**
   * Creates a new MainPanel with the given components.
   * 
   * @param components the components to be added to the panel
   * @author Oliver Levay
   * @author Sharmarke Osman
   */
  public MainPanel(JComponent... components) {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    for (JComponent component : components) {
      this.add(component);
    }
  }
}
