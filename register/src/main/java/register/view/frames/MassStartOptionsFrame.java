package register.view.frames;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import register.model.Driver;
import register.model.DriverTable;
import register.model.RegisterModel;
import register.model.RegisterTable;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.Font;

/**
 * The frame for massStart
 */
public class MassStartOptionsFrame extends JFrame {
  /**
   * Creates a new mass start options frame
   * 
   * @param startTable  the table to add the start times to
   * @param driverTable the table to get the drivers from
   * @author Oliver Levay
   * @author Simon Persson
   */
  public MassStartOptionsFrame(RegisterTable startTable, DriverTable driverTable) {
    super("Mass Start Options");
    JPanel panel = new JPanel();

    JButton startAll = new JButton("All");
    startAll.setFont(new Font("Arial", Font.PLAIN, 40));
    startAll.addActionListener(event -> {
      startDrivers(startTable, driverTable.getDrivers());
    });
    JButton startJuniors = new JButton("Juniors");
    startJuniors.setFont(new Font("Arial", Font.PLAIN, 40));
    startJuniors.addActionListener(event -> {
      startDrivers(startTable, driverTable.getJuniors());
    });
    JButton startSeniors = new JButton("Seniors");
    startSeniors.setFont(new Font("Arial", Font.PLAIN, 40));
    startSeniors.addActionListener(event -> {
      startDrivers(startTable, driverTable.getSeniors());
    });
    JButton startMixed = new JButton("Mixed");
    startMixed.setFont(new Font("Arial", Font.PLAIN, 40));
    startMixed.addActionListener(event -> {
      new MixedMassStartFrame(startTable, driverTable, this);
    });


    panel.add(startAll);
    panel.add(Box.createHorizontalStrut(10));
    panel.add(startJuniors);
    panel.add(Box.createHorizontalStrut(10));
    panel.add(startSeniors);
    panel.add(Box.createHorizontalStrut(10));
    panel.add(startMixed);

    this.add(panel);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.pack();
    this.setVisible(true);

  }


  /**
   * Starts all drivers in the list
   * 
   * @param startTable the table to add the start times to
   * @param drivers    the list of drivers to start
   * @author Oliver Levay
   * @author Simon Persson
   */
  public void startDrivers(RegisterTable startTable, List<Driver> drivers) {
    String time = LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
    for (int i = 0; i < drivers.size(); i++) {
      RegisterModel register = new RegisterModel(time);
      register.setStartNum(drivers.get(i).startNum);
      startTable.registerToTable(register);
    }
    try {
      startTable.saveToFile();
    } catch (IOException error) {
      JOptionPane.showMessageDialog(null, "Failed to write to file", "Error", JOptionPane.ERROR_MESSAGE);
    }
    this.dispose();
  }
}
