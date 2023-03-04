package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.parser.DriverEntry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for the {@link Matcher} class.
 *
 * <p>This test class contains tests to verify the functionality of the {@link Matcher} class.
 */
public class TestAbstractMatcher {

    private Matcher matcher;
    private List<DriverEntry> drivers;
    private List<ResultEntry> result;

    /**
     * Setup method for the testing environment before each test.
     *
     * <p>This method initializes the {@link Matcher} instance and the list of {@link DriverEntry} objects.
     *
     * @author Nollte
     * @version 1.0
     */
    @BeforeEach
    public void setup() {
        matcher = new Matcher();
        drivers = new ArrayList<>();
    }

    /**
     * Test the correct naming of drivers in the result.
     *
     * <p>This test verifies that the names of the drivers in the result match the expected names.
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testDriverNames() {

        String name1 = "Adam Asson";
        String name2 = "Bodil Bsson";
        String name3 = "Caesar Csson";
        drivers.add(new DriverEntry("01", name1));
        drivers.add(new DriverEntry("02", name2));
        drivers.add(new DriverEntry("03", name3));

        matcher.addDrivers(drivers);

        result = matcher.result();

        assertEquals(3, result.size());

        assertEquals(name1, result.get(0).getName());
        assertEquals(name2, result.get(1).getName());
        assertEquals(name3, result.get(2).getName());

    }

    /**
     * Test the correct naming of drivers in the result, regardless of order.
     *
     * <p>This test verifies that the names of the drivers in the result match the expected names, even if the
     * order of the drivers in the list is different from the expected order.
     *
     * @author Nollte
     * @version 1.0
     */
    @Test
    public void testUnorderedDriverNames() {

        String name1 = "Adam Asson";
        String name2 = "Bodil Bsson";
        String name3 = "Caesar Csson";
        drivers.add(new DriverEntry("02", name2));
        drivers.add(new DriverEntry("03", name3));
        drivers.add(new DriverEntry("01", name1));

        matcher.addDrivers(drivers);

        result = matcher.result();

        assertEquals(3, result.size());

        assertEquals(name1, result.get(0).getName());
        assertEquals(name2, result.get(1).getName());
        assertEquals(name3, result.get(2).getName());

    }
}