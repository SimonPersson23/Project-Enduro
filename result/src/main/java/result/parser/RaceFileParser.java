package result.parser;

import result.RaceError;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A file parser that can read lines of text, and parse them into entries.
 */
public abstract class RaceFileParser<E> {
    protected boolean scanningSeniors;

    /**
     * Tries to parse a file and returns a list of entries.
     *
     * @author Nollte
     * @param path the path to the file to parse
     * @return a list of entries
     * @throws RaceError if the file could not be opened or parsed
     */
    public ArrayList<E> tryParse(String path) throws RaceError {
        ArrayList<E> entries;
        try {
            if (path != null) {
                entries = parseTimes(path);
            } else
                return null;
        } catch (IOException e) {
            throw new RaceError("Could not open " + path, e);
        }
        return entries;
    }

    /**
     * Parses the file at the given path, and returns a list of entries
     *
     * @author Nollte
     * @author Simon Persson
     * @param path The file path
     * @return List of entries
     * @throws IOException If the file could not be opened
     */
    private ArrayList<E> parseTimes(String path) throws IOException {
        Scanner scanner = new Scanner(Paths.get(path));
        ArrayList<E> res = new ArrayList<>();

        // We check if the file has a header or if the first row is SENIOR or JUNIOR
        String firstRow = scanner.nextLine();
        if (firstRow.equals("SENIOR")) {
            scanningSeniors = true;
        } else if (firstRow.equals("JUNIOR")) {
            scanningSeniors = false;
        } else {
            try {
                String firstField = firstRow.split(";")[0].trim();
                Integer.parseInt(firstField);
                addEntry(res, firstRow);
            } catch (NumberFormatException e) {
                // First row is not a number, so it is a header
                // Continue with next line
            }
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("SENIOR")) {
                scanningSeniors = true;
            } else if (line.equals("JUNIOR")) {
                scanningSeniors = false;
            } else {
                addEntry(res, line);
            }
        }
        return res;
    }

    /**
     * Adds an entry to the list of entries.
     *
     * @author Nollte
     * @param res  the list of entries
     * @param line the line to parse into an entry
     */
    protected abstract void addEntry(ArrayList<E> res, String line);
}