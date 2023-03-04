package register.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * This class is responsible for the table model in the GUI
 */
public abstract class Table extends AbstractTableModel {
  private String fileName;

  /**
   * Constructor for Table model
   */
  public Table(String fileName) {
    this.fileName = fileName;
  }

  public abstract void loadFromFile() throws IOException, NumberFormatException;

  public String getFileName() {
    return fileName;
  }

  public abstract void editStartNumber(int row, int startNumber);

  public abstract void deleteRow(int row) throws IOException;

  /**
   * Saves the table to the file
   *
   * @author Oliver Levay
   * @author Gustav Serger
   * @author Julius Ekstrand
   * @throws IOException
   */
  public void saveToFile() throws IOException {
    Files.write(Paths.get(this.getFileName()), this.generateOutput(),
        StandardCharsets.UTF_8, StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);
  }

  public abstract List<String> generateOutput();

}
