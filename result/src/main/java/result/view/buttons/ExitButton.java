package result.view.buttons;

import result.view.frames.MainFrame;

public class ExitButton extends Button {
    MainFrame frame;

    /**
     * Creates a new ExitButton with the given text.
     * 
     * @param frame the frame to be closed when the button is clicked
     * @author Oliver Levay
     * @author Sharmarke Osman
     */
    public ExitButton(MainFrame frame) {
        super("Exit");
        this.frame = frame;
    }

    /**
     * Closes the frame when the button is clicked.
     * 
     * @author Oliver Levay
     * @author Sharmarke Osman
     */
    @Override
    public void onClick() {
        frame.exitProgram();
    }
}
