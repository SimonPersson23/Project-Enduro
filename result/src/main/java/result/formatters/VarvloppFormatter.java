package result.formatters;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import result.Formatter;
import result.ResultEntry;
import result.listSorters.NoSorter;
import result.listSorters.Sorter;
import util.TimeUtils;

public class VarvloppFormatter extends Formatter {

    int nLaps;

    /**
     * Method to format results for varvlopp race type
     * @author Sharmarke
     * @author Gustav
     * @param results list of results
     * @param format sorter to use
     * @param raceName name of race
     * 
     */
    @Override
    public List<String> formatResult(List<ResultEntry> results, Sorter format, String raceName) {
        ArrayList<String> formattedResultLines = new ArrayList<>();
        formattedResultLines.add(raceName);
        formattedResultLines.add("");

        nLaps = results.get(0).getEnds().size();



        return getFormattedResults(results, format, formattedResultLines);
    }

    /**
     * Combine the god and bad result
     * @author Sharmarke
     * @author Gustav
     * @param results
     * @param format
     * @param formattedResultLines
     */
    @Override
    protected void addGoodAndBadResults(List<ResultEntry> results, Sorter format,
            ArrayList<String> formattedResultLines) {

        formattedHeaders(format, formattedResultLines);
        List<ResultEntry> badResults = getResultEntries(results, format, formattedResultLines);

        if (!badResults.isEmpty()) {
            formattedResultLines.add("");
            formattedResultLines.add("Felaktig resultat lista:");
            formattedHeaders(format, formattedResultLines);
            formattedResultLines.addAll(formatResults(badResults));
            formattedResultLines.add("");
        }
    }

    /**
     * @author Sharmarke
     * @author Gustav
     * @param format
     * @param formattedResultLines
     */
    private void formattedHeaders(Sorter format, ArrayList<String> formattedResultLines) {
        StringBuilder sb = new StringBuilder();
        if (!(format instanceof NoSorter)) sb.append("Plac;");
        sb.append("StartNr; Namn; #Varv; Totaltid;");
        for (int i = 0; i < nLaps; i++) {
            sb.append(" Varv")
            .append(i + 1)
            .append(";");
        }
        sb.append(" Start;");
        for (int i = 0; i < nLaps - 1; i++) {
            sb.append(" Varvning")
            .append(i + 1)
            .append(";");
        }
        sb.append(" Mal");
        formattedResultLines.add(sb.toString());
    }

    /**
     * Getter for ends
     *
     * @author Nollte
     * @param driverResult
     * @return Returns a formatted String of the driverResult
     */
    @Override
    public String formatDriver(ResultEntry driverResult) {
        StringBuilder sb = new StringBuilder();

        // start number
        sb.append(driverResult.getNumber()).append(SEP).append(" ");

        // driver name
        sb.append(super.getName(driverResult)).append(SEP).append(" ");

        //laps
        sb.append(driverResult.getEnds().size()).append(SEP).append(" ");

        // total time
        sb.append(driverResult.getTotal()).append(SEP).append(" ");

        //lap times
        lapTime(driverResult, sb);

        // start time
        sb.append(driverResult.getStart()).append(SEP).append(" ");

        //lap finish
        lapFinnish(driverResult, sb);

        // end time
        sb.append(driverResult.getEnds().get(driverResult.getEnds().size()-1));

        List<String> errors = driverResult.getErrors();
        if (!errors.isEmpty()) {
            sb.append(SEP);
            for (String e : errors) {
                sb.append(" ").append(e);
            }
        }

        return sb.toString();
    }

    private void lapFinnish(ResultEntry driverResult, StringBuilder sb) {
        for (int i = 0; i < nLaps - 1; i++) {
            String time = "";
            if (i < driverResult.getEnds().size() - 1) {
                time = driverResult.getEnds().get(i);
            }
            sb.append(time).append(SEP).append(" ");
        }
    }

    private void lapTime(ResultEntry driverResult, StringBuilder sb) {
        LocalTime lastLap = LocalTime.parse(driverResult.getStart());
        for (int i = 0; i < nLaps; i++) {
            String laptime = "";
            if (i < driverResult.getEnds().size()) {
                String s = driverResult.getEnds().get(i);
                LocalTime lap = LocalTime.parse(s);
                laptime = TimeUtils.formatTime(Duration.between(lastLap, lap));
                lastLap = lap;
            }
            sb.append(laptime).append(SEP).append(" ");

        }
    }
}
