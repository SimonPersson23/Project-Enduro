package result;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVarvloppSystem {

    @Test
    public void testAcceptanceV1() throws RaceError, IOException {
        String root = "testdata/Acceptanstester/Varvlopp/acceptanstestV1-Varvlopp/";
        ResultProgram.tryMain(root + "race.properties");
        String expected = stringFromFile(root + "resultat.txt");
        String actual = stringFromFile(root + "result.txt");
        assertEquals(expected, actual);
    }

    /**
     *
     *
     * @author Osama & Abdulrahman
     * @throws RaceError   if an error occurs during the race
     * @throws IOException if an I/O error occurs while reading the files
     */
    @Test
    public void acceptansTestV2() throws RaceError, IOException {
        ResultProgram.tryMain("testdata/Acceptanstester/Varvlopp/acceptanstestV2-Flera-Input/race.properties");
        String expected = stringFromFile("testdata/Acceptanstester/Varvlopp/acceptanstestV2-Flera-Input/resultat.txt");
        String actual = stringFromFile("testdata/Acceptanstester/Varvlopp/acceptanstestV2-Flera-Input//result.txt");
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    public void acceptanstestV5() throws RaceError, IOException {
        ResultProgram.tryMain("testdata/Acceptanstester/Varvlopp/acceptanstestV5-Sorterat-Resultat/race.properties");
        String expected = stringFromFile("testdata/Acceptanstester/Varvlopp/acceptanstestV5-Sorterat-Resultat/resultat.txt");
        String actual = stringFromFile("testdata/Acceptanstester/Varvlopp/acceptanstestV5-Sorterat-Resultat/result.txt");
        assertEquals(expected.trim(), actual.trim());
    }


    /**
     * Reads the content of a file and returns it as a string.
     *
     * @author Osama & Abdulrahman
     * @param path the path of the file to be read
     * @return the content of the file as a string
     * @throws IOException if an I/O error occurs while reading the files
     */
    static String stringFromFile(String path) throws IOException {
        return String.join("\n", Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
    }
}
