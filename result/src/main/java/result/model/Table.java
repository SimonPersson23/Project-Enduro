package result.model;

import javax.swing.table.AbstractTableModel;

import result.view.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the model responsible for the table in the GUI
 */
public class Table extends AbstractTableModel {
    private FileHandler fileHandler;
    /**
     * A list of ResultModel objects representing the race results in the data file.
     */
    private ArrayList<ResultModel> regList = new ArrayList<>();
    /**
     * An array of column names to be displayed when the race results are presented
     * in a table format.
     */
    private String[] columnNames = { "Result Number", "Start Number", "Name", "Total Time", "Start Time",
            "Finish Time" };

    /**
     * Constructor for Table
     *
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @param fileName The name of the file that this table mirrors
     */
    public Table(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    /**
     * Returns the number of rows in the table
     *
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @return The number of rows in the table
     */
    @Override
    public int getRowCount() {
        return regList.size();
    }

    /**
     * Returns the file name
     *
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @return The name of the file that this table mirrors
     */
    public String getFileName() {
        return fileHandler.getResultPath();
    }

    /**
     * Returns the number of columns in the table
     *
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @return The number of columns in the table
     */
    @Override
    public int getColumnCount() {
        return 6;
    }

    /**
     * Returns the name of the column at the specified index
     *
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @param columnIndex The index of the column to get the name of
     * @return The name of the column at the specified index
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    /**
     * Returns the value at the specified row and column
     *
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @param rowIndex    The index of the row
     * @param columnIndex The index of the column
     * @return The value at the specified row and column
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return regList.get(rowIndex).getResultNum();
            case 1:
                return regList.get(rowIndex).getStartNum();
            case 2:
                return regList.get(rowIndex).getName();
            case 3:
                return regList.get(rowIndex).getTotalTime();
            case 4:
                return regList.get(rowIndex).getStartTime();
            case 5:
                return regList.get(rowIndex).getFinishTime();
            default:
                return "Out of bounds";
        }
    }

    /**
     * Loads the table from the file
     *
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @author Gustav Serger
     * @throws IOException           If an error occurs while reading the file
     * @throws NumberFormatException If the file contains invalid numbers
     */
    public void loadFromFile() throws IOException, NumberFormatException {
        List<String> lines = Files.readAllLines(Paths.get(getFileName()));
        for (int i = 3; i < lines.size() - 2; i++) {
            String line = lines.get(i);
            String[] parts = line.split(";");
            regList.add(new ResultModel(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()),
                    parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim()));
        }
    }

    /**
     * Clears the regList to make the table empty
     *
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     */
    public void clearTable() {
        // regList = new ArrayList<>();
        regList.clear();
    }

    /**
     * Updates the view to reflect changes in the table
     *
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     */
    public void updateTable() {
        this.fireTableDataChanged();
    }

}
