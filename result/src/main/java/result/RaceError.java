package result;

/**
 * Exception to be thrown for any errors during a race
 */
public class RaceError extends Exception {

    /**
     * Constructor for RaceError
     *
     * @author Nollte
     * @param s error message
     * @param e the cause of the error
     */
    public RaceError(String s, Exception e) {
        super(s,e);
    }
}