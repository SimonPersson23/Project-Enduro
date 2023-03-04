package register.view.buttons;

import javax.swing.JButton;

public abstract class Button extends JButton {
  /**
   * Creates a new button, sets the font and adds an action listener
   * 
   * @param text the text to display on the button
   * @author Oliver Levay
   * @author Simon Persson
   */
  public Button(String text) {
    super(text);
    this.setFont(this.getFont().deriveFont(40f));
    this.addActionListener(e -> onClick());
  }

  public abstract void onClick();
}
