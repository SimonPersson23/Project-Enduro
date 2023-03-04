package result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import result.checks.CheckChain;
import result.checks.ErrorChecks;
import result.checks.FinalCheckMara;
import result.checks.FinalCheckVarv;
import result.parser.DriverEntry;
import result.parser.TimeEntry;

/**
 * Matcher class is used to store and match drivers and their start and end
 * times.
 */
public class Matcher {

    private Map<String, Driver> drivers;
    private String minTime = "00:15:00";
    private String raceType;
    private String raceEndTime;
    

    /**
     * Constructor to initialize the drivers TreeMap and check chain.
     *
     * @author Nollte
     */
    public Matcher() {
        drivers = new TreeMap<>();
        raceType = "marathon";
    }

    /**
     * Set Race End Time
     * @param raceEndTime
     */
    public void setRaceEndtime(String raceEndTime) {
        this.raceEndTime = raceEndTime;
    }

    /**
     * Set racetype
     * 
     * @param racetype
     */
    public void setRaceType(String racetype) {
        this.raceType = racetype;
    }

    /**
     * Add start time to Matcher
     *
     * @author Nollte
     * @param newTimes a list with start times
     */
    public void addStartTimes(List<TimeEntry> newTimes) {
        for (TimeEntry entry : newTimes) {
            Driver driver = getDriver(entry.getNumber());
            driver.addStart(entry.getTime());
        }
    }

    /**
     * Add end times to Matcher
     *
     * @author Nollte
     * @param newTimes a list with end times
     */
    public void addEndTimes(List<TimeEntry> newTimes) {
        for (TimeEntry entry : newTimes) {
            Driver driver = getDriver(entry.getNumber());
            driver.addEnd(entry.getTime());
        }
    }

    /**
     * Add drivers to Matcher
     *
     * @author Nollte
     * @param newDrivers a list with drivers
     */
    public void addDrivers(List<DriverEntry> newDrivers) {
        for (DriverEntry entry : newDrivers) {
            Driver driver = getDriver(entry.getNumber());
            driver.setName(entry.getName());
            driver.setSenior(entry.isSenior());
        }
    }

    /**
     * Getter for driver, if the driver does not exist so add a new driver
     *
     * @author Nollte
     * @param number the key of the driver in the treeMap
     * @return the driver with the given number
     */
    private Driver getDriver(String number) {
        Driver driver = drivers.get(number);
        if (driver == null) {
            driver = new Driver(number);
            drivers.put(number, driver);
        }
        return driver;
    }

    /**
     * Getter for driver, if the driver does not exist so add a new driver
     *
     * @author Simon Persson
     * @author Sharmarke Osman
     * @param minTime minimum time specified
     */
    public void addMinTime(String minTime) {
        this.minTime = minTime;
    }

    /**
     * Getter for results of all drivers in the Matcher.
     *
     * @author Simon Persson
     * @author Sharmarke Osman
     * @return a list with the results
     */
    public List<ResultEntry> result() {
        List<ResultEntry> results = new ArrayList<>();

        CheckChain chain = new CheckChain() {
        };
        switch (raceType) {
            case "marathon":
                chain.addCheck(new ErrorChecks.CheckMissingTimes())
                        .addCheck(new ErrorChecks.CheckMultipleTimes())
                        .addCheck(new ErrorChecks.CheckImpossibleTime(minTime))
                        .addCheck(new FinalCheckMara());
                break;

            case "varvlopp":
                chain.addCheck(new ErrorChecks.CheckMissingTimes())
                    .addCheck(new ErrorChecks.CheckImpossibleTime(minTime))
                    .addCheck(new ErrorChecks.CheckRaceCompleted(raceEndTime))
                    .addCheck(new FinalCheckVarv());
                break;

        }

        for (Driver driver : drivers.values()) {
            ResultEntry result = new ResultEntry(
                    chain.getNumber(driver),
                    chain.getName(driver),
                    chain.getErrors(driver),
                    chain.getStartTime(driver),
                    chain.getEndTimes(driver),
                    chain.getTotalTime(driver),
                    chain.isSenior(driver));
            results.add(result);
        }
        return results;
    }

}