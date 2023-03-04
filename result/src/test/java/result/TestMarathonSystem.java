package result;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the {@link ResultProgram} class.
 */
public class TestMarathonSystem {

    /**
     * Test the output produced by the ResultProgram's main method when the input
     * file is "testdata/maraton1/race.properties".
     * This test case asserts that the actual output file
     * "testdata/maraton1/result.txt" is equal to the expected output file
     * "testdata/maraton1/result.expected".
     *
     * @author Nollte
     * @version 1.0
     * @throws RaceError   if an error occurs during the race
     * @throws IOException if an I/O error occurs while reading the files
     */
    @Test
    public void testMarathon1() throws RaceError, IOException {
        ResultProgram.tryMain("testdata/maraton1/race.properties");
        String expected = stringFromFile("testdata/maraton1/result.expected");
        String actual = stringFromFile("testdata/maraton1/result.txt");
        assertEquals(expected.trim(), actual.trim());
    }

    /**
     * Test the output produced by the ResultProgram's main method when the input
     * file is "testdata/maraton2/race.properties".
     * This test case asserts that the actual output file
     * "testdata/maraton2/result.txt" is equal to the expected output file
     * "testdata/maraton2/result.expected".
     *
     * @author Nollte
     * @version 1.0
     * @throws RaceError   if an error occurs during the race
     * @throws IOException if an I/O error occurs while reading the files
     */

    @Test
    public void testMarathon2() throws RaceError, IOException {
        ResultProgram.tryMain("testdata/maraton2/race.properties");
        String expected = stringFromFile("testdata/maraton2/result.expected");
        String actual = stringFromFile("testdata/maraton2/result.txt");
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    public void testAcceptanceM3() throws RaceError, IOException {
        String root = "testdata/Acceptanstester/Maraton/acceptanstestM3-Totaltid/";
        ResultProgram.tryMain(root + "race.properties");
        String expected = stringFromFile(root + "resultat.txt");
        String actual = stringFromFile(root + "result.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testAcceptanceM4() throws RaceError, IOException {
        String root = "testdata/Acceptanstester/Maraton/acceptanstestM4-Felhantering/";
        ResultProgram.tryMain(root + "race.properties");
        String expected = stringFromFile(root + "resultat.txt");
        String actual = stringFromFile(root + "result.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testAcceptanceM7() throws RaceError, IOException {
        String root = "testdata/Acceptanstester/Maraton/acceptanstestM7-Sorterat-Resultat/";
        ResultProgram.tryMain(root + "race.properties");
        String expected = stringFromFile(root + "sortresultat.txt");
        String actual = stringFromFile(root + "result.txt");
        assertEquals(expected, actual);
    }

    /**
     * Test resultoutput from the resultprogram when there exist impossible times
     *
     * @author Simon & Sharmarke
     * @version 2.0
     * @throws RaceError   if an error occurs during the race
     * @throws IOException if an I/O error occurs while reading the files
     */
    @Test
    public void testAcceptanceG5() throws RaceError, IOException {
        String root = "testdata/Acceptanstester/Generella/acceptanstestG5-Minimitid/maratonlopp/";
        ResultProgram.tryMain(root + "race.properties");
        String expected = stringFromFile(root + "resultat.txt");
        String actual = stringFromFile(root + "result.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testAcceptanceG2() throws RaceError, IOException {
        String root = "testdata/Acceptanstester/Generella/acceptanstestG2-Klasser/";
        ResultProgram.tryMain(root + "race.properties");
        String expected = stringFromFile(root + "resultat.txt");
        String actual = stringFromFile(root + "result.txt");
        assertEquals(expected, actual);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @author Nollte
     * @version 1.0
     * @param path the path of the file to be read
     * @return the content of the file as a string
     * @throws IOException if an I/O error occurs while reading the files
     */
    static String stringFromFile(String path) throws IOException {
        // return new String(Files.readAllBytes(Paths.get(path)),
        // StandardCharsets.UTF_8);
        // We noticed that Files.write() uses the OS's EOL, so \r\n on Windows, but test
        // our files uses \n
        // So, we use readAllLines() instead, which handles both and removes them, then
        // join the strings with \n
        return String.join("\n", Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
    }

}
