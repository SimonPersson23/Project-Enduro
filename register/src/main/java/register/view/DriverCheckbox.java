package register.view;

import java.util.HashSet;

import javax.swing.JCheckBox;

import register.model.Driver;

/**
 *  A class for selecting drivers
 */
public class DriverCheckbox extends JCheckBox {
  public DriverCheckbox(Driver driver, HashSet<Driver> driversToStart) {
    super(String.format("%s %s %s", driver.startNum, driver.name, driver.senior ? "(Senior)" : ""));

    this.setFont(this.getFont().deriveFont(40f));

    this.addChangeListener(event -> {
      if (this.isSelected()) {
        driversToStart.add(driver);
      } else {
        driversToStart.remove(driver);
      }
    });
  }
}
