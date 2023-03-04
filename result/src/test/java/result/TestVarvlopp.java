package result;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class TestVarvlopp {
    /**
    * Test resultoutput from the resultprogram when there exist impossible times
    *
    * @author Simon & Sharmarke
    * @version 2.0
    * @throws RaceError   if an error occurs during the race
    * @throws IOException if an I/O error occurs while reading the files
    */
    @Test
    public void testAcceptanceV1() throws RaceError, IOException {
        String root = "testdata/Acceptanstester/Varvlopp/acceptanstestV1-Varvlopp/";
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
