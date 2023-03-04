package result.parser;

import java.util.ArrayList;

/**
 * A parser for drivers, i.e. reads the name file with start number and driver
 * name.
 */
public class DriversParser extends RaceFileParser<DriverEntry> {

    /**
     * Adds a driver entry to the list
     *
     * @author Nollte
     * @param res  List of driver entries
     * @param line Line of text containing driver entry data
     */
    @Override
    protected void addEntry(ArrayList<DriverEntry> res, String line) {
        String[] entry = line.split(";");
        if (entry.length == 2) {
            res.add(new DriverEntry(String.valueOf(Integer.parseInt(entry[0].trim())), entry[1].trim(), scanningSeniors));
        } else {
            System.err.println("Not valid line: " + line);
        }
    }
}
