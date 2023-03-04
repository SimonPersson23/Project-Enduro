package result.view;

import javax.swing.*;

import result.model.Table;
import result.view.frames.MainFrame;
import result.view.panels.ActionButtonPanel;
import result.view.panels.MainPanel;

/**
 * The GUI class provides a graphical user interface for displaying and
 * interacting with a table of race results.
 */
public class GUI {
    /**
     * The path to the text file containing the initial race results data.
     */

    /**
     * Starts the GUI
     * 
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @author Osama Hajjouz
     * @author Julius Ekstrand
     */
    public void start() {
        MainFileHandler fileHandler = new MainFileHandler();
        Table resultTableModel = new Table(fileHandler);
        ResultTable resultTable = new ResultTable(resultTableModel);

        MainFrame frame = new MainFrame(resultTable.getWidth());

        JLabel helpLabel = new JLabel(
                "You need to select all files to enable the \"Calculate Result\" button, open settings to change the files");

        JPanel buttonPanel = new ActionButtonPanel(resultTableModel, frame, fileHandler);

        MainPanel panel = new MainPanel(
                resultTable,
                helpLabel,
                buttonPanel);

        frame.addPanel(panel);
    }

}
