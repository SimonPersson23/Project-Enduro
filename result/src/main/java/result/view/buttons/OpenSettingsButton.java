package result.view.buttons;

import result.view.MainFileHandler;
import result.view.frames.SettingsFrame;

public class OpenSettingsButton extends Button {
  private MainFileHandler fileHandler;

  public OpenSettingsButton(MainFileHandler fileHandler) {
    super("Open settings");
    this.fileHandler = fileHandler;
  }

  @Override
  public void onClick() {
    new SettingsFrame(fileHandler);
  }

}
