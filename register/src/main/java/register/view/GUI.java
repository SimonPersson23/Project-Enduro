package register.view;

import javax.swing.*;

import register.model.DriverTable;
import register.model.RegisterTable;
import register.view.frames.MainFrame;
import register.view.tabels.DriverTableView;
import register.view.tabels.RegisterTableView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The main GUI class
 */
public class GUI {

  public static final String START_TIMES_TXT = "gui_files/start.txt";
  public static final String FINISH_TIMES_TXT = "gui_files/end.txt";
  public static final String DRIVERS_TXT = "gui_files/drivers.txt";

  /**
   * Starts the GUI
   *
   * @author Oliver Levay
   * @author Sharmarke Osman
   * @author Gustav Serger
   * @author Julius Ekstrand
   * @author Lucas Wittich
   * @author Abdbulrahman Husari
   */
  public void start() {
    try {
      Files.createDirectory(Paths.get("gui_files"));
      Files.createFile(Paths.get(START_TIMES_TXT));
      Files.createFile(Paths.get(FINISH_TIMES_TXT));
      Files.createFile(Paths.get(DRIVERS_TXT));
    } catch (IOException e2) {
      e2.printStackTrace();
    }

    MainFrame frame = new MainFrame();

    DriverTableView driversTable = new DriverTableView(new DriverTable(DRIVERS_TXT));
    RegisterTableView startTable = new RegisterTableView(new RegisterTable(START_TIMES_TXT));
    RegisterTableView finishTable = new RegisterTableView(new RegisterTable(FINISH_TIMES_TXT));

    MainPanel mainPanel = getMainPanel(frame, driversTable, startTable, finishTable);
    frame.addPanel(mainPanel);
  }

  /**
   * Help function for building the panel for GUI
   *
   * @author Oliver Levay
   * @author Sharmarke Osman
   * @author Gustav Serger
   * @author Julius Ekstrand
   * @author Lucas Wittich
   * @author Abdbulrahman Husari
   */
  private static MainPanel getMainPanel(MainFrame frame, DriverTableView driversTable, RegisterTableView startTable, RegisterTableView finishTable) {
    TablesPanel tablesPanel = new TablesPanel(driversTable, startTable, finishTable);
    JPanel buttonPanel = new ButtonPanel(startTable, finishTable, driversTable, frame);
    return new MainPanel(tablesPanel, buttonPanel);
  }
}
