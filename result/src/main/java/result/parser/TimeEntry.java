package result.parser;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Raw data read from a time file, i.e. one start number and one clock time.
 */
public class TimeEntry {
    private final String number;
    private final LocalTime time;

    /**
     * Constructor for TimeEntry
     *
     * @author Nollte
     * @param number
     */
    public TimeEntry(final String number, final LocalTime time) {
        this.number = number;
        this.time = time;
    }

    /**
     * Override equals method to compare number
     *
     * @author Nollte
     * @param obj to be compered
     * @return Returns true if the number is equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(TimeEntry.class)) {
            return ((TimeEntry) obj).number.equals(number);
        }
        return false;
    }

    /**
     * toString Override to write number + time
     *
     * @author Nollte
     * @return Returns the Overriden toString
     */
    @Override
    public String toString() {
        return number + "; " + time.format(DateTimeFormatter.ofPattern("HH.mm.ss"));
    }

    /**
     * Getter for number
     *
     * @author Nollte
     * @return Returns the number of the TimeEntry
     */
    public String getNumber() {
        return number;
    }

    /**
     * Getter for time
     *
     * @author Nollte
     * @return Returns the time of end TimeEntry
     */
    public LocalTime getTime() {
        return time;
    }

}
