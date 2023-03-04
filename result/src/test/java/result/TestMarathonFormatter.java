package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.parser.DriverEntry;
import result.parser.TimeEntry;
import result.formatters.MarathonFormatter;
import result.listSorters.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link Formatter} and {@link Matcher} classes.
 * The tests check if the expected result is generated for various cases
 */
public class TestMarathonFormatter {

    private Matcher matcher;
    private Formatter formatter;
    private List<DriverEntry> drivers;
    private List<TimeEntry> startTimes;
    private List<TimeEntry> endTimes;
    private List<ResultEntry> result;

    /**
     * Setup method that initializes variables before each test
     *
     * @author Nollte
     * @version 1.0
     */
    @BeforeEach
    public void setup() {
        matcher = new Matcher();
        formatter = new MarathonFormatter();
        drivers = new ArrayList<>();
        startTimes = new ArrayList<>();
        endTimes = new ArrayList<>();
    }

    /**
     * Tests the case when a start time is missing
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testMissingStart() {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Caesar Csson"));
        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        // missing start time
        // startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        matcher.addDrivers(drivers);
        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();

        assertEquals(3, result.size());

        String expectedResult1 = "01; Adam Asson; 01:00:00; 12:00:00; 13:00:00";
        String expectedResult2 = "02; Bodil Bsson; --:--:--; Start?; 13:00:00";
        String expectedResult3 = "03; Caesar Csson; 00:58:00; 12:02:00; 13:00:00";

        assertEquals(expectedResult1, formatter.formatDriver(result.get(0)));
        assertEquals(expectedResult2, formatter.formatDriver(result.get(1)));
        assertEquals(expectedResult3, formatter.formatDriver(result.get(2)));

    }

    /**
     * Tests the case when an end time is missing
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testMissingEnd() {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Caesar Csson"));
        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        // missing end time
        // endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        matcher.addDrivers(drivers);
        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();

        assertEquals(3, result.size());

        String expectedResult1 = "01; Adam Asson; 01:00:00; 12:00:00; 13:00:00";
        String expectedResult2 = "02; Bodil Bsson; --:--:--; 12:01:00; Slut?";
        String expectedResult3 = "03; Caesar Csson; 00:58:00; 12:02:00; 13:00:00";

        assertEquals(expectedResult1, formatter.formatDriver(result.get(0)));
        assertEquals(expectedResult2, formatter.formatDriver(result.get(1)));
        assertEquals(expectedResult3, formatter.formatDriver(result.get(2)));

    }

    /**
     * Tests if the function correctly handles multiple missing times.
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testMultipleMissingTimes() {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Caesar Csson"));
        // startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        // startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        // missing end time
        // endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:00")));
        // endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        matcher.addDrivers(drivers);
        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();

        assertEquals(3, result.size());

        String expectedResult1 = "01; Adam Asson; --:--:--; Start?; 13:00:00";
        String expectedResult2 = "02; Bodil Bsson; --:--:--; Start?; Slut?";
        String expectedResult3 = "03; Caesar Csson; --:--:--; 12:02:00; Slut?";

        assertEquals(expectedResult1, formatter.formatDriver(result.get(0)));
        assertEquals(expectedResult2, formatter.formatDriver(result.get(1)));
        assertEquals(expectedResult3, formatter.formatDriver(result.get(2)));

    }

    /**
     * Tests if the results are displayed in correct order
     *
     * @author Julius Ekstrand, Lucas Wittich
     * @version 1.00
     */
    @Test
    public void testFormatResults() {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Caesar Csson"));

        startTimes.add(new TimeEntry("01", LocalTime.parse("12:02:00")));
        startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:00:00")));

        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("02", LocalTime.parse("12:30:00")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        matcher.addDrivers(drivers);
        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();
        List<String> formattedResults = formatter.formatResult(result, new TotalTimeSorter(), "Test");

        assertEquals(3, result.size());

        String expectedResult2 = "2; 01; Adam Asson; 00:58:00; 12:02:00; 13:00:00";
        String expectedResult1 = "1; 02; Bodil Bsson; 00:29:00; 12:01:00; 12:30:00";
        String expectedResult3 = "3; 03; Caesar Csson; 01:00:00; 12:00:00; 13:00:00";

        assertEquals(expectedResult1, formattedResults.get(3));
        assertEquals(expectedResult2, formattedResults.get(4));
        assertEquals(expectedResult3, formattedResults.get(5));

    }

    /**
     * Tests formatting of empty results and if they are at the end
     *
     * @author Julius Ekstrand, Lucas Wittich
     * @version 1.00
     */

    @Test
    public void testFormatEmptyResults() {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Caesar Csson"));

        startTimes.add(new TimeEntry("01", LocalTime.parse("12:02:00")));
        startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:00:00")));

        // endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("02", LocalTime.parse("12:30:00")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        matcher.addDrivers(drivers);
        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();
        List<String> formattedResults = formatter.formatResult(result, new TotalTimeSorter(), "Test");

        assertEquals(3, result.size());

        String expectedResult3 = "01; Adam Asson; --:--:--; 12:02:00; Slut?";
        String expectedResult1 = "1; 02; Bodil Bsson; 00:29:00; 12:01:00; 12:30:00";
        String expectedResult2 = "2; 03; Caesar Csson; 01:00:00; 12:00:00; 13:00:00";

        assertEquals(expectedResult1, formattedResults.get(3));
        assertEquals(expectedResult2, formattedResults.get(4));
        assertEquals(expectedResult3, formattedResults.get(8));
    }

    /**
     * Test the output in a situation where a start number is not included in the
     * drivers file.
     * 
     * @author Gustav Serger, Abdulrahman Husari
     * @version 1.0
     * @throws RaceError   if an error occurs during the race
     * @throws IOException if an I/O error occurs while reading the files
     */
    @Test
    public void testIllegalStartNumber() throws RaceError, IOException {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));

        startTimes.add(new TimeEntry("01", LocalTime.parse("12:02:00")));
        startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:00:00")));

        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("02", LocalTime.parse("12:30:00")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        matcher.addDrivers(drivers);
        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();
        List<String> formattedResults = formatter.formatResult(result, new NoSorter(), "Test");

        List<String> expectedResults = new ArrayList<>() {
            {
                add("Test");
                add("");
                add("StartNr; Namn; Totaltid; Start; Mal");
                add("01; Adam Asson; 00:58:00; 12:02:00; 13:00:00 ");
                add("02; Bodil Bsson; 00:29:00; 12:01:00; 12:30:00 ");
                add("");
                add("Felaktig resultat lista:");
                add("StartNr; Namn; Totaltid; Start; Mal");
                add("03; Namn?; 01:00:00; 12:00:00; 13:00:00 ");
            }
        };

        for (int i = 0; i < expectedResults.size(); i++) {
            assertEquals(expectedResults.get(i).strip(), formattedResults.get(i).strip());
        }
    }

}
