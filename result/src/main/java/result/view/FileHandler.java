package result.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;

import result.RaceError;

public interface FileHandler {

  public List<JComponent> getComponents();

  /**
   * Enables the file browsing buttons and initializes their path from the props
   * file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @throws RaceError
   */
  public void enableButtons() throws RaceError;

  public void setResultButton(JButton resultButton);

  public void enableResultButtonIfReady();

  /**
   * Returns the selected path of the start file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @return the selected path of the start file
   */
  public String getStartPath();

  /**
   * Returns the selected path of the finish file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @return the selected path of the finish file
   */
  public String getFinishPath();

  /**
   * Returns the selected path of the name file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @return the selected path of the name file
   */
  public String getNamePath();

  /**
   * Returns the selected path of the properties file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @return the selected path of the properties file
   */
  public String getPropPath();

  /**
   * Returns the path of the result file based on where the prop file is.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @return the path of the result file
   */
  public String getResultPath();
}
