package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import result.formatters.MarathonFormatter;
import result.listSorters.NumberSorter;
import result.listSorters.TotalTimeSorter;
import result.parser.DriverEntry;
import result.parser.TimeEntry;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SorterProgramTest {
    private Matcher matcher;
    private Formatter formatter;
    private List<DriverEntry> drivers;
    private List<TimeEntry> startTimes;
    private List<TimeEntry> endTimes;
    private List<ResultEntry> result;
    @BeforeEach
    void setUp() {
        matcher = new Matcher();
        formatter = new MarathonFormatter();
        drivers = new ArrayList<>();
        startTimes = new ArrayList<>();
        endTimes = new ArrayList<>();

    }

    @Test
    void TotalTimeSortTest() {
        TotalTimeSorter s = new TotalTimeSorter();

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
        List<String> formattedResults = formatter.formatResult(result, s, "Test");

        String expectedResult2 = "2; 01; Adam Asson; 00:58:00; 12:02:00; 13:00:00";
        String expectedResult1 = "1; 02; Bodil Bsson; 00:29:00; 12:01:00; 12:30:00";
        String expectedResult3 = "3; 03; Caesar Csson; 01:00:00; 12:00:00; 13:00:00";

        assertEquals(expectedResult1, formattedResults.get(3));
        assertEquals(expectedResult2, formattedResults.get(4));
        assertEquals(expectedResult3, formattedResults.get(5));
    }

    @Test
    void StartNumberSortTest() {
        NumberSorter s = new NumberSorter();

        drivers.add(new DriverEntry("50", "Adam Asson"));
        drivers.add(new DriverEntry("35", "Bodil Bsson"));
        drivers.add(new DriverEntry("40", "Caesar Csson"));

        startTimes.add(new TimeEntry("50", LocalTime.parse("12:02:00")));
        startTimes.add(new TimeEntry("35", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("40", LocalTime.parse("12:00:00")));

        endTimes.add(new TimeEntry("50", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("35", LocalTime.parse("12:30:00")));
        endTimes.add(new TimeEntry("40", LocalTime.parse("13:00:00")));

        matcher.addDrivers(drivers);
        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);

        result = matcher.result();
        List<String> formattedResults = formatter.formatResult(result, s, "Test");

        String expectedResult3 = "3; 50; Adam Asson; 00:58:00; 12:02:00; 13:00:00";
        String expectedResult1 = "1; 35; Bodil Bsson; 00:29:00; 12:01:00; 12:30:00";
        String expectedResult2 = "2; 40; Caesar Csson; 01:00:00; 12:00:00; 13:00:00";

        assertEquals(expectedResult1, formattedResults.get(3));
        assertEquals(expectedResult2, formattedResults.get(4));
        assertEquals(expectedResult3, formattedResults.get(5));
    }
}