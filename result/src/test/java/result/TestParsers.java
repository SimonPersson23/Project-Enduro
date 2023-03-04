package result;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import result.parser.DriverEntry;
import result.parser.DriversParser;

/**
 * Test class for the Parsers.
 */
public class TestParsers {

    String driverInfoFile = "testdata/maraton1/drivers.txt";
    String driverInfoFileWithHeaders = "testdata/maraton1/driversWithHeaders.txt";


    /**
     * Runs the setup method before each test case.
     */
    @BeforeEach
    public void setup() {
    }

    /**
     * Try to parse
     * This file has a header column so it should parse
     * Then we check if the driver entries have the right values
     *
     * @author Gustav Serger, Simon Persson
     * @version 1.0
     * @throws IOException if the file is not found or cannot be read
     * @throws RaceError if an error occurs while parsing the file
     */
    @Test
    public void testReadDriversFromFileWithHeader() throws IOException, RaceError {
        // Test file with headers
        ArrayList<DriverEntry> drivers = new DriversParser().tryParse(driverInfoFileWithHeaders);
        assertEquals("1", drivers.get(0).getNumber());
        assertEquals("Gustav", drivers.get(0).getName());
        assertEquals("2", drivers.get(1).getNumber());
        assertEquals("Simon", drivers.get(1).getName());
        assertEquals("3", drivers.get(2).getNumber());
        assertEquals("Linnea", drivers.get(2).getName());
        assertEquals("4", drivers.get(3).getNumber());
        assertEquals("Gustav2", drivers.get(3).getName());

        // Test file without headers 
        drivers = new DriversParser().tryParse(driverInfoFile);
        assertEquals("1", drivers.get(0).getNumber());
        assertEquals("A", drivers.get(0).getName());
        assertEquals("2", drivers.get(1).getNumber());
        assertEquals("B", drivers.get(1).getName());
        assertEquals("3", drivers.get(2).getNumber());
        assertEquals("C", drivers.get(2).getName());

    }
}
