package register.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the model responsible for the table in the GUI
 */
public class RegisterTable extends Table {
    /**
     * Constructor for RegisterTable
     * 
     * @param fileName The name of the file that this table mirrors
     * @author Oliver Levay
     * @author Simon Persson
     */
    public RegisterTable(String fileName) {
        super(fileName);
    }

    /**
     * A list of ResultModel objects representing the race results in the data file.
     */
    private ArrayList<RegisterModel> regList = new ArrayList<>();
    /**
     * An array of column names to be displayed when the race results are presented
     * in a table format.
     */
    private String[] columnNames = { "Start Number", "Time" };

    /**
     * Edit the start number of a row
     * 
     * @author Lucas Wittich
     * @author Gustav Serger
     * @param row
     * @param startNumber
     */
    public void editStartNumber(int row, int startNumber) {
        regList.get(row).setStartNum(startNumber);
    }

    /**
     * Enable editing for the first column
     *
     * @author Lucas Wittich
     * @author Gustav Serger
     * @return boolean
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 0;
    }

    /**
     * Delete the selected row
     *
     * @author Lucas Wittich
     * @author Julius Ekstrand
     * @author Oliver Levay
     */
    public void deleteRow(int row) throws IOException {
        regList.remove(row);
        this.fireTableDataChanged();
        this.saveToFile();
    }

    /**
     * Handle the editing of the start number column
     *
     * @author Lucas Wittich
     * @author Gustav Serger
     * @author Julius Ekstrand
     * @param value usually as string
     * @param row
     * @param col
     *
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        int startNum = 0;
        try {
            String newNum = (String) value;
            startNum = Integer.parseInt(newNum);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
        this.editStartNumber(row, startNum);
        try {
            this.saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @return The number of rows in the table
     */
    @Override
    public int getRowCount() {
        return regList.size();
    }

    /**
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @return The number of columns in the table
     */
    @Override
    public int getColumnCount() {
        return 2;
    }

    /**
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @param columnIndex
     * @return The name of the column at the specified index
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    /**
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @param rowIndex
     * @param columnIndex
     * @return The value at the specified row and column
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return regList.get(rowIndex).getStartNum();
            case 1:
                return regList.get(rowIndex).getTime();
            default:
                return "Out of bounds";
        }
    }

    /**
     * Adds a new register row to the table and sends a notification to the GUI
     *
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @author Gustav Seger
     * @param reg
     */
    public void registerToTable(RegisterModel reg) {
        regList.add(reg);
        this.fireTableDataChanged();
    }

    /**
     * Loads the table from the file
     *
     * @author Oliver Levay
     * @author Simon Persson
     * @author Gustav Serger
     * @throws IOException
     */
    public void loadFromFile() throws IOException, NumberFormatException {
        List<String> lines = Files.readAllLines(Paths.get(this.getFileName()));
        for (String line : lines) {
            String[] parts = line.split(";");
            RegisterModel registerModel = new RegisterModel(parts[1].trim());
            if (parts[0] != null) {
                registerModel.setStartNum(Integer.parseInt(parts[0].trim()));
            }
            regList.add(registerModel);
        }
    }

    /**
     * Generates a list of strings based on the table data
     *
     * @author Oliver Levay
     * @author Gustav Serger
     * @return a list of strings that can be written to the file
     */
    public List<String> generateOutput() {
        return regList
                .stream()
                .map(model -> model.getStartNum() + ";" + model.getTime())
                .toList();
    }
}
