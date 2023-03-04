package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.parser.DriverEntry;
import result.parser.TimeEntry;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link Matcher}.
 */
public class TestMarathonMatcher {

    private Matcher matcher;
    private List<DriverEntry> drivers;
    private List<TimeEntry> startTimes;
    private List<TimeEntry> endTimes;
    private List<ResultEntry> result;

    /**
     * Setup method to initialize required objects.
     *
     * @author Nollte
     * @version 1.0
     */
    @BeforeEach
    public void setup() {
        matcher = new Matcher();
        drivers = new ArrayList<>();
        startTimes = new ArrayList<>();
        endTimes = new ArrayList<>();
    }

    /**
     * Test case for simple pairing of start and end times.
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testSimplePairing() {

        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));

        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();

        assertEquals(1, result.size());

        ResultEntry r = result.get(0);
        assertEquals("01", r.getNumber());
        assertEquals("12:00:00", r.getStart());
        assertEquals("13:00:00", r.getEnds().get(0));
    }

    /**
     * Test case for ordered start and end times.
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testOrderedList() {

        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
        startTimes.add(new TimeEntry("04", LocalTime.parse("12:03:00")));
        startTimes.add(new TimeEntry("05", LocalTime.parse("12:04:00")));

        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("02", LocalTime.parse("12:49:23")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:03:19")));
        endTimes.add(new TimeEntry("04", LocalTime.parse("12:58:34")));
        endTimes.add(new TimeEntry("05", LocalTime.parse("12:59:01")));

        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();

        assertEquals(5, result.size());

        ResultEntry r = result.get(0);
        assertEquals("01", r.getNumber());
        assertEquals("12:00:00", r.getStart());
        assertEquals("13:00:00", r.getEnds().get(0));

        r = result.get(1);
        assertEquals("02", r.getNumber());
        assertEquals("12:01:00", r.getStart());
        assertEquals("12:49:23", r.getEnds().get(0));

        r = result.get(2);
        assertEquals("03", r.getNumber());
        assertEquals("12:02:00", r.getStart());
        assertEquals("13:03:19", r.getEnds().get(0));

        r = result.get(3);
        assertEquals("04", r.getNumber());
        assertEquals("12:03:00", r.getStart());
        assertEquals("12:58:34", r.getEnds().get(0));

        r = result.get(4);
        assertEquals("05", r.getNumber());
        assertEquals("12:04:00", r.getStart());
        assertEquals("12:59:01", r.getEnds().get(0));

    }

    /**
     * Test method for unordered start and end time lists
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testUnOrderedList() {

        startTimes.add(new TimeEntry("04", LocalTime.parse("12:03:00")));
        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
        startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("05", LocalTime.parse("12:04:00")));

        endTimes.add(new TimeEntry("04", LocalTime.parse("12:58:34")));
        endTimes.add(new TimeEntry("02", LocalTime.parse("12:49:23")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:03:19")));
        endTimes.add(new TimeEntry("05", LocalTime.parse("12:59:01")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));

        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();

        assertEquals(5, result.size());

        ResultEntry r = result.get(0);
        assertEquals("01", r.getNumber());
        assertEquals("12:00:00", r.getStart());
        assertEquals("13:00:00", r.getEnds().get(0));

        r = result.get(1);
        assertEquals("02", r.getNumber());
        assertEquals("12:01:00", r.getStart());
        assertEquals("12:49:23", r.getEnds().get(0));

        r = result.get(2);
        assertEquals("03", r.getNumber());
        assertEquals("12:02:00", r.getStart());
        assertEquals("13:03:19", r.getEnds().get(0));

        r = result.get(3);
        assertEquals("04", r.getNumber());
        assertEquals("12:03:00", r.getStart());
        assertEquals("12:58:34", r.getEnds().get(0));

        r = result.get(4);
        assertEquals("05", r.getNumber());
        assertEquals("12:04:00", r.getStart());
        assertEquals("12:59:01", r.getEnds().get(0));

    }

    /**
     * Test method for checking the calculated total time for each driver.
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testTotalTime() {

        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));

        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();

        ResultEntry resultRow = result.get(0);
        assertEquals("01:00:00", resultRow.getTotal());

    }

    /**
     * Tests scenario where start time is missing for a driver.
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testMissingStartTime() {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Ceasar Csson"));
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

        assertEquals("12:00:00", result.get(0).getStart());
        assertEquals("Start?", result.get(1).getStart());
        assertEquals("12:02:00", result.get(2).getStart());
    }

    /**
     * Tests scenario where end time is missing for a driver.
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testMissingEndTime() {

        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Ceasar Csson"));
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

        assertEquals("13:00:00", result.get(0).getEnds().get(0));
        assertEquals("Slut?", result.get(1).getEnds().get(0));
        assertEquals("13:00:00", result.get(2).getEnds().get(0));
    }

    /**
     * Tests scenario where multiple start times exist for a single driver.
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testMultiStartTimes() {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Ceasar Csson"));
        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        // two start time
        startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:01")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        matcher.addDrivers(drivers);
        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();

        assertEquals(3, result.size());

        assertEquals("12:00:00", result.get(0).getStart());
        assertEquals("12:01:00", result.get(1).getStart());
        assertEquals("12:02:00", result.get(2).getStart());

        assertTrue(result.get(0).getErrors().isEmpty());
        assertEquals("Flera starttider?", result.get(1).getErrors().get(0));
        assertEquals("12:01:01", result.get(1).getErrors().get(1));
        assertTrue(result.get(2).getErrors().isEmpty());
    }

    /**
     * Tests scenario where multiple end times exist for a single driver.
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testMultiEndTimes() {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Ceasar Csson"));
        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        // two end time
        endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:01")));
        endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        matcher.addDrivers(drivers);
        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();

        assertEquals(3, result.size());

        assertEquals("13:00:00", result.get(0).getEnds().get(0));
        assertEquals("13:00:01", result.get(1).getEnds().get(0));
        assertEquals("13:00:00", result.get(2).getEnds().get(0));

        assertTrue(result.get(0).getErrors().isEmpty());
        assertTrue(!result.get(1).getErrors().isEmpty());
        assertEquals("Flera maltider?", result.get(1).getErrors().get(0));
        assertEquals("13:00:00", result.get(1).getErrors().get(1));
        assertTrue(result.get(2).getErrors().isEmpty());
    }
}
