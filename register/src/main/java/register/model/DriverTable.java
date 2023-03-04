package register.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for the driver table in the GUI
 */
public class DriverTable extends Table {
  /**
   * A list of Driver objects representing the drivers in the data file.
   */
  private ArrayList<Driver> drivers = new ArrayList<>();
  /**
   * An array of column names to be displayed when the data is presented in a
   * table format.
   */
  private String[] columnNames = { "Start Number", "Name", "Class" };

  /**
   * Constructor for DriverTable
   *
   * @author Oliver Levay
   * @author Lucas Wittich
   * @param fileName The name of the file that this table mirrors
   */
  public DriverTable(String fileName) {
    super(fileName);
  }

  /**
   * Gets the drivers in the table
   * 
   * @return The drivers in the table
   * @author Oliver Levay
   * @author Simon Persson
   */
  public ArrayList<Driver> getDrivers() {
    // return a copy of reglist to ensure that the original list is not modified
    return new ArrayList<Driver>(drivers);
  }

  /**
   * Gets the junior drivers in the table
   * 
   * @return The junior drivers in the table
   * @author Oliver Levay
   * @author Simon Persson
   */
  public List<Driver> getJuniors() {
    return drivers.stream().filter(driver -> !driver.senior).toList();
  }

  /**
   * Gets the senior drivers in the table
   * 
   * @return The senior drivers in the table
   * @author Oliver Levay
   * @author Simon Persson
   */
  public List<Driver> getSeniors() {
    return drivers.stream().filter(driver -> driver.senior).toList();
  }

  /**
   * Edit the start number of a row
   * 
   * @author Lucas Wittich,
   * @author Oliver Levay
   * @param row         the row number
   * @param startNumber the start number of the driver
   */
  public void editStartNumber(int row, int startNumber) {
    drivers.get(row).startNum = startNumber;
  }

  /**
   * Edit driver name in a row
   *
   * @author Lucas Wittich,
   * @author Oliver Levay
   * @param row  the row number
   * @param name the driver name
   */
  public void editName(int row, String name) {
    drivers.get(row).name = name;
  }

  /**
   * Enable editing for the first column
   * 
   * @author Lucas Wittich,
   * @author Gustav Serger
   * @return boolean
   */
  @Override
  public boolean isCellEditable(int row, int column) {
    return column == 0 || column == 1;
  }

  /**
   * Delete the specified row
   * 
   * @author Gustav Serger,
   * @author Julius Ekstrand
   * @author Oliver Levay
   * @param row the row number
   * @throws IOException
   */
  public void deleteRow(int row) throws IOException {
    drivers.remove(row);
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
   * @param row   the row number
   * @param col   the column number
   */
  @Override
  public void setValueAt(Object value, int row, int col) {
    if (col == 0) {
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
    } else if (col == 1) {
      String name = (String) value;
      this.editName(row, name);
      try {
        this.saveToFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * @author Oliver Levay
   * @author Sharmarke Osman
   * @return The number of rows in the table
   */
  @Override
  public int getRowCount() {
    return drivers.size();
  }

  /**
   * @author Oliver Levay
   * @author Sharmarke Osman
   * @return The number of columns in the table
   */
  @Override
  public int getColumnCount() {
    return 3;
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
        return drivers.get(rowIndex).startNum;
      case 1:
        return drivers.get(rowIndex).name;
      case 2:
        if (drivers.get(rowIndex).senior) {
          return "Senior";
        } else {
          return "Junior";
        }
      default:
        return "Out of bounds";
    }
  }

  /**
   * Adds a new register row to the table and sends a notification to the GUI
   *
   * @author Oliver Levay
   * @author Sharmarke Osman
   * @author Gustav Serger
   * @param reg
   */
  public void registerToTable(Driver reg) {
    drivers.add(reg);
    this.fireTableDataChanged();
  }

  /**
   * Loads the table from the file
   * 
   * @author Oliver Levay
   * @author Gustav Serger
   * @throws IOException
   * @throws NumberFormatException
   */
  public void loadFromFile() throws IOException, NumberFormatException {
    List<String> lines = Files.readAllLines(Paths.get(this.getFileName()));
    boolean isSenior = false;
    for (String line : lines) {
      if (line.equals("SENIOR")) {
        isSenior = true;
      } else if (line.equals("JUNIOR")) {
        isSenior = false;
      } else {
        String[] parts = line.split(";");
        Driver driver = new Driver(parts[1].trim(), isSenior);
        if (parts[0] != null) {
          driver.setStartNum(Integer.parseInt(parts[0].trim()));
        }
        drivers.add(driver);
      }
    }
  }

  private List<String> generateDriversOutput(List<Driver> drivers) {
    return drivers
        .stream()
        .map(model -> model.startNum + "; " + model.name)
        .toList();
  }

  /**
   * Generates a list of strings based on the table data
   *
   * @author Oliver Levay
   * @author Gustav Serger
   * @return a list of strings that can be written to the file
   */
  public List<String> generateOutput() {
    List<Driver> seniorDrivers = drivers.stream().filter(driver -> driver.senior).toList();
    List<Driver> juniorDrivers = drivers.stream().filter(driver -> !driver.senior).toList();
    List<String> output = new ArrayList<>();
    output.add("SENIOR");
    output.addAll(this.generateDriversOutput(seniorDrivers));
    output.add("JUNIOR");
    output.addAll(this.generateDriversOutput(juniorDrivers));
    return output;
  }
}
