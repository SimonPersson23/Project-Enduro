package result.view;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;

import result.RaceError;
import result.RaceProperties;
import result.view.buttons.FileBrowsingButton;

public class MainFileHandler implements FileHandler {
  FileBrowsingButton startButton;
  FileBrowsingButton finishButton;
  FileBrowsingButton nameButton;
  FileBrowsingButton propButton;
  SelectRaceDropdown raceDropdown;
  JButton resultButton;

  public MainFileHandler() {
    this.propButton = new FileBrowsingButton("Properties file", this, null);
    this.startButton = new FileBrowsingButton("Start times file", this, "startTimesPath");
    this.finishButton = new FileBrowsingButton("End times file", this, "endTimePath");
    this.nameButton = new FileBrowsingButton("Drivers file", this, "driversPath");
    this.raceDropdown = new SelectRaceDropdown(this);
  }

  public List<JComponent> getComponents() {
    return Arrays.asList(propButton, startButton, finishButton, nameButton, raceDropdown);
  }

  /**
   * Enables the file browsing buttons and initializes their path from the props
   * file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @throws RaceError
   */
  public void enableButtons() throws RaceError {
    RaceProperties props = new RaceProperties(propButton.getPath());
    startButton.enableButton(props);
    finishButton.enableButton(props);
    nameButton.enableButton(props);
  }

  private boolean isReady() {
    return startButton.getPath() != null
        && finishButton.getPath() != null
        && nameButton.getPath() != null
        && propButton.getPath() != null;
  }

  public void setResultButton(JButton resultButton) {
    this.resultButton = resultButton;
  }

  public void enableResultButtonIfReady() {
    if (isReady()) {
      resultButton.setEnabled(true);
    }
  }

  /**
   * Returns the selected path of the start file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @return the selected path of the start file
   */
  public String getStartPath() {
    return startButton.getPath();
  }

  /**
   * Returns the selected path of the finish file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @return the selected path of the finish file
   */
  public String getFinishPath() {
    return finishButton.getPath();
  }

  /**
   * Returns the selected path of the name file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @return the selected path of the name file
   */
  public String getNamePath() {
    return nameButton.getPath();
  }

  /**
   * Returns the selected path of the properties file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @return the selected path of the properties file
   */
  public String getPropPath() {
    return propButton.getPath();
  }

  /**
   * Returns the path of the result file based on where the prop file is.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @return the path of the result file
   */
  public String getResultPath() {
    if (getPropPath().contains("\\")) {
      return new File(getPropPath()).getParent() + "\\result.txt";
    } else {
      return new File(getPropPath()).getParent() + "/result.txt";
    }
  }
}
