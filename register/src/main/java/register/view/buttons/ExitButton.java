package register.view.buttons;

import register.view.frames.MainFrame;

public class ExitButton extends Button {
  MainFrame frame;

  /**
   * Creates a new exit button
   * 
   * @param frame the frame to exit
   * @author Oliver Levay
   * @author Simon Persson
   */
  public ExitButton(MainFrame frame) {
    super("Exit");
    this.frame = frame;
  }

  /**
   * Exits the program
   * 
   * @author Oliver Levay
   * @author Simon Persson
   */
  @Override
  public void onClick() {
    frame.exitProgram();
  }

}
