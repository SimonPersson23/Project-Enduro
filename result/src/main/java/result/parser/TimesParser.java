package result.parser;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A time parser, that can read time files, i.e. start number and time.
 */
public class TimesParser extends RaceFileParser<TimeEntry> {

    /**
     * Adds a time entry to the list of time entries.
     *
     * @author Nollte
     * @param res  The list of time entries.
     * @param line The line to parse.
     */
    protected void addEntry(ArrayList<TimeEntry> res, String line) {
        String[] entry = line.split(";");
        if (entry.length == 2) {
            try {
                res.add(new TimeEntry(entry[0].trim(), LocalTime.parse(entry[1].trim())));
            } catch (DateTimeParseException e) {
                System.err.println("Could not parse " + entry[1] + " as local time");
            }
        } else {
            System.err.println("Not valid line: " + line);
        }
    }
}
