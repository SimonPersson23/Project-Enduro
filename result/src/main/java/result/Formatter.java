package result;

import java.util.ArrayList;
import java.util.List;

import result.formatters.MarathonFormatter;
import result.formatters.VarvloppFormatter;
import result.listSorters.*;

/**
 * Formats marathon results.
 */
public abstract class Formatter {

    /**
     * Separator used in-between columns in results.
     */
    protected static final String SEP = ";";

    /**
     * The method handles list sorting via Sorter classes that can be instantiated
     * when calling the method, and then formats the results into a list of strings
     * that can be written to a file.
     *
     * @author Lucas Wittich
     * @author Julius Ekstrand
     * @author Gustav Serger
     * @author Osama Hajjouz
     * @author Abdulrahman Husari
     * @param results
     * @param format
     * @param raceName
     * @return formattedResultLines
     */
    public List<String> formatResult(List<ResultEntry> results, Sorter format, String raceName) {
        ArrayList<String> formattedResultLines = new ArrayList<>();
        formattedResultLines.add(raceName);
        formattedResultLines.add("");

        results = format.sort(results);

        return getFormattedResults(results, format, formattedResultLines);
    }
    /**
     * @author Lucas Wittich
     * @author Julius Ekstrand
     * @author Gustav Serger
     * @author Osama Hajjouz
     * @author Abdulrahman Husari
     * @param results
     * @param format
     * @param formattedResultLines
     * @return formattedResultLines
     */

    protected ArrayList<String> getFormattedResults(List<ResultEntry> results, Sorter format, ArrayList<String> formattedResultLines) {
        List<ResultEntry> juniorResults = results.stream().filter(result -> !result.isSenior()).toList();
        List<ResultEntry> seniorResults = results.stream().filter(ResultEntry::isSenior).toList();
        boolean hasDifferentClasses = !juniorResults.isEmpty() && !seniorResults.isEmpty();
        if (hasDifferentClasses) {
            formattedResultLines.add("SENIOR");
            addGoodAndBadResults(seniorResults, format, formattedResultLines);
            formattedResultLines.add("JUNIOR");
            addGoodAndBadResults(juniorResults, format, formattedResultLines);

        } else {
            addGoodAndBadResults(results, format, formattedResultLines);
        }

        formattedResultLines.add("");
        formattedResultLines.add("Resultatgenerering av Team 14 - ...");

        return formattedResultLines;
    }

    /**
     * Combine the god and bad result
     *
     * @author Lucas Wittich
     * @author Julius Ekstrand
     * @author Gustav Serger
     * @author Osama Hajjouz
     * @author Abdulrahman Husari
     * @param results
     * @param format
     * @param formattedResultLines
     */

    protected void addGoodAndBadResults(List<ResultEntry> results, Sorter format,
            ArrayList<String> formattedResultLines) {
        if (format instanceof NoSorter) {
            formattedResultLines.add("StartNr; Namn; Totaltid; Start; Mal");
        } else {
            formattedResultLines.add("Plac; StartNr; Namn; Totaltid; Start; Mal");
        }
        List<ResultEntry> badResults = getResultEntries(results, format, formattedResultLines);

        if (!badResults.isEmpty()) {
            formattedResultLines.add("");
            formattedResultLines.add("Felaktig resultat lista:");
            formattedResultLines.add("StartNr; Namn; Totaltid; Start; Mal");
            formattedResultLines.addAll(formatResults(badResults));
        }
    }

    /**
     *
     * @author Lucas Wittich
     * @author Julius Ekstrand
     * @author Gustav Serger
     * @author Osama Hajjouz
     * @author Abdulrahman Husari
     * @param results
     * @param format
     * @param formattedResultLines
     */

    protected List<ResultEntry> getResultEntries(List<ResultEntry> results, Sorter format, ArrayList<String> formattedResultLines) {
        List<ResultEntry> badResults = new ArrayList<>();
        List<ResultEntry> goodResults = new ArrayList<>();
        for (ResultEntry result : results) {
            if (isValidResult(result)) {
                goodResults.add(result);
            } else {
                badResults.add(result);
            }
        }

        if (format instanceof NoSorter) {
            formattedResultLines.addAll(formatResults(goodResults));
        } else {
            formattedResultLines.addAll(formatSortedResults(goodResults));
        }
        return badResults;
    }

    /**
     * Formats a list of ResultEntry objects and returns a list of formatted result
     * lines.
     *
     * @author Abdulrahman Husari
     * @author Osama Hajjouz
     * @param results the list of ResultEntry objects to format.
     * @return a list of formatted result lines.
     */
    protected List<String> formatResults(List<ResultEntry> results) {
        List<String> formattedResults = new ArrayList<>();
        for (ResultEntry result : results) {
            formattedResults.add(formatDriver(result));
        }
        return formattedResults;
    }

    /**
     * Formats a list of ResultEntry objects and returns a list of formatted result
     * lines including
     * placement numbers.
     *
     * @author Abdulrahman Husari
     * @author Osama Hajjouz
     * @param results the list of ResultEntry objects to format.
     * @return a list of formatted result lines including placement numbers.
     */
    protected List<String> formatSortedResults(List<ResultEntry> results) {
        List<String> formattedResults = new ArrayList<>();
        int placement = 0;
        for (ResultEntry result : results) {
            placement++;

            formattedResults.add(placement + "; " + formatDriver(result));
        }
        return formattedResults;
    }

    /**
     * Checks whether the given result is valid or not.
     *
     * @author Abdulrahman Husari
     * @author Osama Hajjouz
     * @param result
     * @return boolean
     */
    protected boolean isValidResult(ResultEntry result) {
        return result.getName() != null
                && !result.getName().equals("Namn?")
                && result.getErrors().isEmpty()
                && !result.getStart().equals("Start?")
                && !result.getEnds().get(result.getEnds().size() - 1).equals("Slut?");
    }

    /**
     * Formats a driver's results as a string in a specific format.
     *
     * @author Nollte
     * @param driverResult
     * @return Returns a formatted String of the driverResult
     */
    public abstract String formatDriver(ResultEntry driverResult);

    protected String getName(ResultEntry driverResult) {
        String name = driverResult.getName();
        return name != null ? name : "";
    }

    public static Formatter createFormatter(String raceType) {
        switch (raceType) {
            case "marathon":
                return new MarathonFormatter();
            case "varvlopp":
                return new VarvloppFormatter();
            default:
                return new MarathonFormatter();
        }
    }
}
