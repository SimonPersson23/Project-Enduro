package result;

import java.util.List;

/**
 * ResultEntry class, used to format the result of a race.
 * The class defines the format of a race result.
 * It handles the creation of a result entry, and the retrieval of the values of
 * a result entry.
 */
public class ResultEntry {
    String number;
    String name;
    List<String> errors;
    String start;
    List<String> ends;
    String total;
    boolean senior;

    /**
     * ResultEntry constructor, creates a ResultEntry object with provided values
     * for a driver.
     *
     * @param number Number of the driver.
     * @param name   Name of the driver.
     * @param errors List of errors in the driver.
     * @param start  Start time of the driver.
     * @param ends End times of the driver.
     * @param total  Total time of the driver.
     * @param senior The class of the driver.
     */
    public ResultEntry(String number, String name, List<String> errors, String start, List<String> ends, String total,
            boolean senior) {
        this.number = number;
        this.name = name;
        this.errors = errors;
        this.start = start;
        this.ends = ends;
        this.total = total;
        this.senior = senior;
    }

    /**
     * Get the start number of the driver
     *
     * @author Nollte
     * @return Returns the number of the ResultEntry
     */
    public String getNumber() {
        return number;
    }

    /**
     * Get the name of the driver
     *
     * @author Nollte
     * @return Returns the name of the ResultEntry
     */
    public String getName() {
        return name;
    }

    /**
     * Get the list of errors in the ResultEntry
     *
     * @author Nollte
     * @return Returns the list of errors in the ResultEntry
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Get the start time of the driver
     *
     * @author Nollte
     * @return Returns start of the ResultEntry
     */
    public String getStart() {
        return start;
    }

    /**
     * Get the end time of the driver
     *
     * @author Nollte
     * @return Returns end of the ResultEntry
     */
    public List<String> getEnds() {
        return ends;
    }

    /**
     * Get the total time of the driver
     *
     * @author Nollte
     * @return Returns the total of the ResultEntry
     */
    public String getTotal() {
        return total;
    }

    /**
     * Get the seniority of the driver
     *
     * @author Oliver
     * @author Julius
     * @return Returns the seniority of the ResultEntry
     */
    public boolean isSenior() {
        return senior;
    }
}
