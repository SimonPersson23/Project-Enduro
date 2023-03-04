package result.checks;

import result.Driver;
import util.TimeUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * The final link in the decorator pattern chain, that actually returns the
 * driver's data, and formats it. Should be
 * added last to check chains.
 */
public class FinalCheckMara extends CheckChain {

    /**
     * Returns the start number of the driver.
     *
     * @author Nollte
     * @version 1.0
     * @param driver The Driver object being checked.
     * @return String representing the start number of the driver.
     */
    @Override
    public String getNumber(Driver driver) {
        return driver.getStartNumber();
    }

    /**
     * Returns the name of the driver.
     *
     * @author Nollte
     * @version 1.0
     * @param driver The Driver object being checked.
     * @return String representing the name of the driver.
     */
    @Override
    public String getName(Driver driver) {
        return driver.getName();
    }

    /**
     * Returns the driver's seniority.
     *
     * @author Oliver
     * @author Julius
     * @version 1.0
     * @param driver The driver.
     * @return The driver's name.
     */
    @Override
    public boolean isSenior(Driver driver) {
        return driver.isSenior();
    }

    /**
     * Returns an empty list of errors, since no errors will be found in the final
     * link of the check chain.
     *
     * @author Nollte
     * @version 1.0
     * @param driver The Driver object being checked.
     * @return Empty list of errors.
     */
    @Override
    public List<String> getErrors(Driver driver) {
        return new ArrayList<>();
    }

    /**
     * Returns the formatted start time of the driver.
     *
     * @author Nollte
     * @version 1.0
     * @param driver The Driver object being checked.
     * @return String representing the formatted start time of the driver.
     */
    @Override
    public String getStartTime(Driver driver) {
        if (driver.getStarts().size() == 0) {
            return "";
        }
        return TimeUtils.formatTime(driver.getStarts().get(0));
    }

    /**
     * Returns the formatted end time of the driver.
     *
     * @author Nollte
     * @version 1.0
     * @param driver The Driver object being checked.
     * @return String representing the formatted end time of the driver.
     */
    @Override
    public List<String> getEndTimes(Driver driver) {
        List<String> endTimes = new ArrayList<>();
        for (int i = 0; i < driver.getEnds().size(); i++) {
            endTimes.add(TimeUtils.formatTime(driver.getEnds().get(i)));
        }
        if (endTimes.size() == 0) {
            endTimes.add("Slut?");
        }
        return endTimes;
    }

    /**
     * Returns the formatted total time of the driver.
     *
     * @author Nollte, Sharmarke, Gustav
     * @version 1.0
     * @param driver The Driver object being checked.
     * @return String representing the formatted total time of the driver.
     */
    @Override
    public String getTotalTime(Driver driver) {
        return TimeUtils.formatTime(Duration.between(driver.getStarts().get(0), driver.getEnds().get(0)));
    }
}
