package register.model;

public class Driver {
  /**
   * Constructor for Driver
   */
  public Driver(String name, boolean senior) {
    this.name = name;
    this.senior = senior;
  }

  /**
   * Sets the start-number of the Driver
   * 
   * @author Oliver Levay
   * @author Lucas Wittich
   * @author Abdulrahman Husari
   *
   * @param startNum The start number of the Driver
   */
  public void setStartNum(int startNum) {
    this.startNum = startNum;
  }

  @Override
  public String toString() {
    return String.format("%s %s %s", startNum, name, senior ? "(Senior)" : "");
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Driver) {
      Driver other = (Driver) obj;
      return other.name.equals(name) && other.senior == senior && other.startNum == startNum;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return name.hashCode() + (senior ? 1 : 0) + startNum;
  }

  public String name;
  public int startNum;
  public boolean senior;
}
