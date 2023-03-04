package result.view.buttons;

import javax.swing.*;

public abstract class Button extends JButton {

    /**
     * Creates a new button with the given text.
     * 
     * @param text the text to be displayed on the button
     * @author Oliver Levay
     * @author Sharmarke Osman
     */
    public Button(String text) {
        super(text);
        this.addActionListener(e -> onClick());
    }

    /**
     * This method is called when the button is clicked.
     * 
     * @author Oliver Levay
     * @author Sharmarke Osman
     */
    public abstract void onClick();
}
