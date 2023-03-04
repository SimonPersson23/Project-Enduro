package result.view.buttons;

import result.RaceError;
import result.ResultProgram;
import result.model.Table;
import result.view.FileHandler;
import result.view.MainFileHandler;

import javax.swing.*;
import java.io.IOException;

public class ResultButton extends Button {
    Table table;
    FileHandler fileHandler;

    /**
     * Creates a new ResultButton.
     * 
     * @param table the table to be updated when the button is clicked
     * @author Oliver Levay
     * @author Sharmarke Osman
     */
    public ResultButton(Table table, MainFileHandler fileHandler) {
        super("Calculate result");
        this.table = table;
        this.fileHandler = fileHandler;
        this.setEnabled(false);
        fileHandler.setResultButton(this);
    }

    /**
     * Updates the table when the button is clicked by loading from the file.
     * 
     * @author Oliver Levay
     * @author Sharmarke Osman
     */
    @Override
    public void onClick() {
        try {
            table.clearTable();
            try {
                ResultProgram.tryMain(fileHandler.getPropPath());
            } catch (RaceError e) {
                e.printStackTrace();
            }
            table.loadFromFile();
            table.updateTable();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load from file: " + table.getFileName(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Something is wrong with the file: " + table.getFileName() + ", please check it.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }
}
