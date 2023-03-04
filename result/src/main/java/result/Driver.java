package result;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of a single driver's start number and times.
 */
public class Driver {

    private final String startNumber;
    private final List<LocalTime> starts;
    private final List<LocalTime> ends;
    private boolean senior;
    private String name;

    /**
     * Creates a new instance of Driver with the given start number.
     *
     * @author Nollte
     * @param startNumber the start number of the driver
     */
    public Driver(String startNumber) {
        this.startNumber = startNumber;
        this.starts = new ArrayList<>();
        this.ends = new ArrayList<>();
    }

    /**
     * Returns the list of start times for this driver.
     *
     * @author Nollte
     * @return the list of start times
     */
    public List<LocalTime> getStarts() {
        // Is it ok to return the private attribute here? Maybe it should return only
        // the first value?
        return starts;
    }

    /**
     * Adds a new start time to this driver.
     *
     * @author Nollte
     * @param start the startTime
     */
    public void addStart(LocalTime start) {
        starts.add(start);
    }

    /**
     * Returns the list of end times for this driver.
     *
     * @author Nollte
     * @return Returns the list of end times
     */
    public List<LocalTime> getEnds() {
        return ends;
    }

    /**
     * Adds a new end time to this driver.
     *
     * @author Nollte
     */
    public void addEnd(LocalTime end) {
        ends.add(end);
    }

    /**
     * Returns the start number for this driver.
     *
     * @author Nollte
     * @return Returns the start of the Driver
     */
    public String getStartNumber() {
        return startNumber;
    }

    /**
     * Returns the name of this driver.
     *
     * @author Nollte
     * @return Returns the name of the Driver
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this driver.
     *
     * @author Nollte
     * @param name the name of the driver
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the senior status of this driver.
     *
     * @author Oliver
     * @author Julius
     * @return Returns the senior status of the Driver
     */
    public boolean isSenior() {
        return senior;
    }

    /**
     * Sets the senior status of this driver.
     *
     * @author Oliver
     * @author Julius
     * @param senior
     */
    public void setSenior(boolean senior) {
        this.senior = senior;
    }

}
