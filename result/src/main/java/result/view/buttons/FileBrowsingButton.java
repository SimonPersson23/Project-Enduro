package result.view.buttons;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import result.RaceError;
import result.RaceProperties;
import result.view.MainFileHandler;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FileBrowsingButton extends JPanel {
  JButton jButton;
  JTextField jTextField;
  JFileChooser fileChooser;
  String path;
  MainFileHandler fileHandler;
  String propKey;

  /**
   * Creates a new FileBrowsingButton with the given label.
   * 
   * @param label the label to be displayed on the button
   * @author Oliver Levay
   * @author Sharmarke Osman
   */
  public FileBrowsingButton(String label, MainFileHandler fileHandler, String propKey) {
    super();
    JLabel jLabel = new JLabel(label);
    this.jTextField = new JTextField(20);
    jLabel.setLabelFor(jTextField);
    JPanel fieldAndButtonPanel = new JPanel();
    this.jTextField.setEnabled(false);
    this.jButton = new JButton("Browse");
    fieldAndButtonPanel.add(jTextField);
    fieldAndButtonPanel.add(jButton);
    jButton.addActionListener(onButtonClick());
    this.add(jLabel);
    this.add(fieldAndButtonPanel);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    fileChooser.setMultiSelectionEnabled(true);
    this.fileHandler = fileHandler;
    this.propKey = propKey;
    if (propKey != null) {
      this.disableButton();
    }
  }

  /**
   * Enables the button.
   * 
   * @author Oliver Levay
   */
  public void enableButton(RaceProperties props) {
    if (props.getProperty(propKey) != null) {
      setPath(props.getProperty(propKey));
    }
    jButton.setEnabled(true);
  }

  /**
   * Disables the button.
   * 
   * @author Oliver Levay
   */
  public void disableButton() {
    jButton.setEnabled(false);
  }

  public void setPath(String path) {
    this.path = path;
    jTextField.setText(path);
    fileHandler.enableResultButtonIfReady();
  }

  /**
   * Returns the path of the selected file.
   * 
   * @return the path of the selected file
   * @author Oliver Levay
   * @author Sharmarke Osman
   */
  public String getPath() {
    return path;
  }

  /**
   * Returns an ActionListener that opens a file chooser when the button is
   * clicked.
   * 
   * @return an ActionListener that opens a file chooser when the button is
   *         clicked
   * @author Oliver Levay
   * @author Sharmarke Osman
   */
  private ActionListener onButtonClick() {
    return e -> {
      int returnValue = fileChooser.showOpenDialog(null);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        path = file.getAbsolutePath();
        setPath(path);
        try {
          if (propKey != null) {
            addToPropFile();
          } else {
            addResultPath();
            fileHandler.enableButtons();
          }
        } catch (RaceError | IOException e1) {
          e1.printStackTrace();
        }
      }
    };
  }

  /**
   * Adds the path to the properties file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @throws RaceError
   * @throws IOException
   */
  private void addToPropFile() throws RaceError, IOException {
    RaceProperties props = new RaceProperties(fileHandler.getPropPath());
    props.setProperty(propKey, path);
    props.save();
    System.out.println("Added " + propKey + " to " + fileHandler.getPropPath() + " with value " + path);
  }

  /**
   * Adds the result path to the properties file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @throws RaceError
   * @throws IOException
   */
  private void addResultPath() throws RaceError, IOException {
    RaceProperties props = new RaceProperties(fileHandler.getPropPath());
    props.setProperty("driverResultPath", fileHandler.getResultPath());
    props.save();
    System.out.println(
        "Added driverResultPath to " + fileHandler.getPropPath() + " with value " + fileHandler.getResultPath());
  }
}
