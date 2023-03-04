package result.checks;

import result.Driver;

import java.util.List;

/**
 * Uses the decorator pattern to insert/append error messages to a driver.
 * (I feel so smart for coming up with this! :P )
 *
 * The chain is like a linked list, where a link in the chain wraps around a driver. So if you want to get a driver's
 * start time, you call getStart() on the link, which in turns call the driver's getStart() but can alter the return
 * value, e.g. to return an error instead if the start time is missing. Then, that link can be wrapped by another link,
 * which in turn can be wrapped by another link, and another link, etc.
 */

/**
 * CheckChain is a class that uses the decorator pattern to insert/append error
 * messages to a Driver.
 * The chain is like a linked list, where a link in the chain wraps around a
 * Driver.
 * This allows for alteration of the Driver's data (such as returning an error
 * message instead of the actual data
 * if it is missing).
 */
public class CheckChain {

    private CheckChain next;

    /**
     * Adds a new link to the chain and returns it.
     *
     * @author Nollte
     * @param next The next link in the chain.
     * @return The new link.
     */
    public CheckChain addCheck(CheckChain next) {
        this.next = next;
        return next;
    }

    /**
     * Returns the driver's number.
     *
     * @author Nollte
     * @param driver The driver.
     * @return The driver's number.
     */
    public String getNumber(Driver driver) {
        return next.getNumber(driver);
    }

    /**
     * Returns the driver's name.
     *
     * @author Nollte
     * @param driver The driver.
     * @return The driver's name.
     */
    public String getName(Driver driver) {
        return next.getName(driver);
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
    public boolean isSenior(Driver driver) {
        return next.isSenior(driver);
    }

    /**
     * Returns a list of error messages.
     *
     * @author Nollte
     * @param driver The driver.
     * @return A list of error messages.
     */
    public List<String> getErrors(Driver driver) {
        return next.getErrors(driver);
    }

    /**
     * Returns a list of error messages.
     *
     * @author Nollte
     * @param driver  The driver.
     * @param minTime The minimum time.
     * @return A list of error messages.
     */
    public List<String> getErrors(Driver driver, String minTime) {
        return next.getErrors(driver);
    }

    /**
     * Returns the driver's start time.
     *
     * @author Nollte
     * @param driver The driver.
     * @return The driver's start time.
     */
    public String getStartTime(Driver driver) {
        return next.getStartTime(driver);
    }

    /**
     * Returns the driver's end time.
     *
     * @author Nollte
     * @param driver The driver.
     * @return The driver's end time.
     */
    public List<String> getEndTimes(Driver driver) {
        return next.getEndTimes(driver);
    }

    /**
     * Returns the driver's total time.
     *
     * @author Nollte
     * @param driver The driver.
     * @return The driver's total time.
     */
    public String getTotalTime(Driver driver) {
        return next.getTotalTime(driver);
    }
}
