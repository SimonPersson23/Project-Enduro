package result;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class RaceProperties {
  Properties properties;
  String path;

  /**
   * Creates a new RaceProperties object with the specified path.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @param path
   * @throws RaceError
   */
  public RaceProperties(String path) throws RaceError {
    this.path = path;
    try (InputStream file = Files.newInputStream(Paths.get(path))) {
      this.properties = new Properties();
      this.properties.load(file);
    } catch (IOException e) {
      throw new RaceError("Error when reading file " + path, e);
    }
  }

  public String getDriversPath() {
    return this.properties.getProperty(Constants.DRIVERS_PATH);
  }

  public String getStartTimesPath() {
    return this.properties.getProperty(Constants.START_PATH);
  }

  public String getEndTimesPath() {
    return this.properties.getProperty(Constants.END_PATH);
  }

  public String getNumberEndTimes() {
    return this.properties.getProperty(Constants.NUMBER_END_TIMES);
  }

  public String getResultPath() {
    return this.properties.getProperty(Constants.RESULT_PATH);
  }

  public String getRaceName() {
    return this.properties.getProperty(Constants.RACE_NAME);
  }

  public String getMinTime() {
    return (String) this.properties.get(Constants.MIN_TIME);
  }

  public String getResultFormat() {
    return this.properties.getProperty(Constants.RESULT_FORMAT);
  }

  public String getRaceType() {
    String raceType = this.properties.getProperty(Constants.RACE_TYPE);
    if (raceType == null) {
      return Constants.MARATHON;
    }
    return raceType;
  }

  public String getRaceEndTime() {
    return this.properties.getProperty(Constants.END_TIME);
  }

  public String getEndTimePathN(int n) {
    return this.properties.getProperty(Constants.END_PATH + n);
  }

  public void setResultFormat(String format) {
    this.properties.setProperty(Constants.RESULT_FORMAT, format);
  }

  public String getProperty(String key) {
    return this.properties.getProperty(key);
  }

  /**
   * Sets the value of the property with the specified key.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   * @param key
   * @param value
   */
  public void setProperty(String key, String value) {
    this.properties.setProperty(key, value);
  }

  /**
   * Saves the properties to the file.
   * 
   * @author Oliver Levay
   * @author Julius Ekstrand
   */
  public void save() throws IOException {
    FileWriter writer = new FileWriter(path);
    properties.store(writer, "");
    writer.close();
  }
}
