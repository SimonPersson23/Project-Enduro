package result.view;

import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import result.RaceError;
import result.RaceProperties;

public class SelectRaceDropdown extends JPanel {
  /**
   * Creates a new SelectRaceDropdown with a label and a dropdown menu.
   * 
   * @author Oliver Levay
   * @author Sharmarke Osman
   */
  public SelectRaceDropdown(FileHandler fileHandler) {
    super();
    JLabel raceTypeLabel = new JLabel("Välj lopp typ");
    String raceTypeList[] = { "marathon", "varvlopp" };
    JComboBox<String> raceTypeBox = new JComboBox<>(raceTypeList);
    raceTypeBox.addActionListener(e -> {
      String raceType = (String) raceTypeBox.getSelectedItem();

      RaceProperties properties;
      try {
        properties = new RaceProperties(fileHandler.getPropPath());
        properties.setProperty("raceType", raceType);
        properties.save();
      } catch (RaceError e1) {
        e1.printStackTrace();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    });
    this.add(raceTypeLabel);
    this.add(raceTypeBox);
  }
}
