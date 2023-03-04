package result.view.frames;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import result.view.MainFileHandler;

public class SettingsFrame {
  JFrame frame;

  public SettingsFrame(MainFileHandler fileHandler) {
    super();
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setPreferredSize(new java.awt.Dimension(300, 300));
    JPanel panel = new JPanel();
    for (JComponent component : fileHandler.getComponents()) {
      panel.add(component);
    }
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }

}
