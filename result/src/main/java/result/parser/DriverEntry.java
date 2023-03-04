package result.parser;

/**
 * Raw data read from name file, i.e. one start number and one driver name.
 */
public class DriverEntry {

    private final String number;
    private final String name;
    private boolean senior;

    /**
     * DriverEntry Constructor
     *
     * @author Nollte
     * @param number Start nummber of a driver
     * @param name   The name of a driver
     */
    public DriverEntry(String number, String name) {
        this.number = number;
        this.name = name;
    }

    /**
     * DriverEntry Constructor
     *
     * @author Oliver
     * @author Julius
     * @version 1.00
     * @param number Start nummber of a driver
     * @param name   The name of a driver
     */
    public DriverEntry(String number, String name, boolean senior) {
        this.number = number;
        this.name = name;
        this.senior = senior;
    }

    /**
     * Getter for the start number of a driver
     *
     * @author Nollte
     * @return strat number of the driver
     */
    public String getNumber() {
        return number;
    }

    /**
     * Getter for the name of a driver
     *
     * @author Nollte
     * @return the name of the driver
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the seniority of a driver
     *
     * @author Oliver
     * @author Julius
     * @version 1.00
     * @return the seniority of the driver
     */
    public boolean isSenior() {
        return senior;
    }

}
