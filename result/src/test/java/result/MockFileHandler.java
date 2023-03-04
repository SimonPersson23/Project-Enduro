package result;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;

import result.view.FileHandler;

public class MockFileHandler implements FileHandler {
  private String resultPath;

  public MockFileHandler(String resultPath) {
    this.resultPath = resultPath;
  }

  @Override
  public List<JComponent> getComponents() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getComponents'");
  }

  @Override
  public void enableButtons() throws RaceError {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'enableButtons'");
  }

  @Override
  public void setResultButton(JButton resultButton) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setResultButton'");
  }

  @Override
  public void enableResultButtonIfReady() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'enableResultButtonIfReady'");
  }

  @Override
  public String getStartPath() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getStartPath'");
  }

  @Override
  public String getFinishPath() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getFinishPath'");
  }

  @Override
  public String getNamePath() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getNamePath'");
  }

  @Override
  public String getPropPath() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPropPath'");
  }

  @Override
  public String getResultPath() {
    return resultPath;
  }

}
