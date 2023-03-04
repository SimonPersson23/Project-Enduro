package register.view.buttons;

import register.model.DriverTable;
import register.model.RegisterTable;
import register.view.frames.MassStartOptionsFrame;

public class MassStartButton extends Button {
  private RegisterTable startTable;
  private DriverTable driverTable;

  /**
   * Creates a new mass start button
   * 
   * @param startTable   the table to add the start times to
   * @param driversTable the table to get the drivers from
   * @author Oliver Levay
   * @author Simon Persson
   */
  public MassStartButton(RegisterTable startTable, DriverTable driversTable) {
    super("Mass Start");
    this.startTable = startTable;
    this.driverTable = driversTable;
  }

  /**
   * Initiates the mass start workflow
   * 
   * @author Oliver Levay
   * @author Simon Persson
   */
  @Override
  public void onClick() {
    new MassStartOptionsFrame(startTable, driverTable);
  }

}
