package result;

import result.parser.DriverEntry;
import result.parser.DriversParser;
import result.parser.TimeEntry;
import result.parser.TimesParser;
import result.view.GUI;
import result.listSorters.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * The ResultProgram class is responsible for matching drivers with start and
 * end times,
 * and generating the result file based on the race data and properties
 * provided.
 */
public class ResultProgram {

    private static final Logger LOGGER = Logger.getLogger(ResultProgram.class.getName());

    /**
     * The main method for the Result program. This method starts the program and
     * calls tryMain to load the properties, read the driver entries, start times,
     * and end times, match the drivers with start and end times, generate the
     * result file,
     * and write the result to a specified file.
     *
     * @param args The command line arguments for the program.
     * @throws IOException
     * @throws InterruptedException
     * @throws RaceError
     */
    public static void main(String[] args) throws RaceError, IOException, InterruptedException {

        if (args.length > 0 && args[0].equals("--watch")) {
            final Path path = FileSystems.getDefault().getPath("gui_files/");
            System.out.println("Watching: " + path);
            try (final WatchService watchService = FileSystems.getDefault().newWatchService()) {
                final WatchKey watchKey = path.register(watchService,
                        java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY);
                while (true) {
                    final WatchKey wk = watchService.take();
                    for (WatchEvent<?> event : wk.pollEvents()) {
                        // we only register "ENTRY_MODIFY" so the context is always a Path.
                        final Path changed = (Path) event.context();
                        if (changed.endsWith("end.txt")) {
                            System.out.println("New end time changes");
                            tryMain("race.properties");
                        }
                    }
                    // reset the key
                    boolean valid = wk.reset();
                    if (!valid) {
                        System.out.println("Key has been unregistered");
                    }
                }
            }
        } else {
            new GUI().start();
        }

    }

    /**
     * Loads the properties from the specified file path, reads the driver entries,
     * start times, and end times from the specified files, matches the drivers with
     * their start
     * and end times, generates the result, and writes the result to the specified
     * file.
     *
     * @author Nollte
     * @param propPath The file path to the properties file.
     * @throws RaceError   If an error occurs while reading or writing files, or
     *                     while processing the data.
     * @throws IOException If an IO exception occurs.
     */
    public static void tryMain(String propPath) throws RaceError, IOException {
        // Load settings
        RaceProperties props = new RaceProperties(propPath);

        if (props.getResultFormat() == null) {
            props.setResultFormat("");
        }

        LOGGER.info(props.getRaceName());

        // Load and parse files
        ArrayList<DriverEntry> driverEntries = new DriversParser().tryParse(props.getDriversPath());
        ArrayList<TimeEntry> startTimes = new TimesParser().tryParse(props.getStartTimesPath());
        ArrayList<TimeEntry> endTimes = new TimesParser().tryParse(props.getEndTimesPath());

        // Sort All endtimes
        addMultipleEndTimes(props, endTimes);

        // Sort endtimes
        endTimes.sort(Comparator.comparing(TimeEntry::getTime));

        // Generate result
        Matcher matcher = createMatcher(driverEntries, startTimes, endTimes, props);
        List<ResultEntry> results = matcher.result();

        // Format and write result
        Formatter formatter = Formatter.createFormatter(props.getRaceType());

        SorterParser sorterParser = new SorterParser();
        // remove file at resultpath
        Files.deleteIfExists(Paths.get(props.getResultPath()));
        writeResult(props.getResultPath(),
                formatter.formatResult(results, sorterParser.parse(props.getResultFormat()), props.getRaceName()));
        LOGGER.info("All done! The result has been written to " + props.getResultPath());
    }

    private static void addMultipleEndTimes(RaceProperties props, ArrayList<TimeEntry> endTimes) throws RaceError {
        if (props.getNumberEndTimes() != null) {
            int n = Integer.parseInt(props.getNumberEndTimes());
            for (int i = 2; i <= n; i++) {
                String endTime = props.getEndTimePathN(n);
                ArrayList<TimeEntry> temp = new TimesParser().tryParse(endTime);
                endTimes.addAll(temp);
            }
        }
    }

    /**
     *
     * Creates a Matcher object and adds the driver entries, start times, end times,
     * and minimum time to it.
     *
     * @author Osama Hajjouz
     * @author Simon Persson
     * @param driverEntries An ArrayList of DriverEntry objects.
     * @param startTimes    An ArrayList of TimeEntry objects for the start times.
     * @param endTimes      An ArrayList of TimeEntry objects for the end times.
     * @param minTime       The minimum time to add.
     * @param raceType      The type of race.
     * @return A Matcher object.
     */
    private static Matcher createMatcher(ArrayList<DriverEntry> driverEntries, ArrayList<TimeEntry> startTimes,
            ArrayList<TimeEntry> endTimes, RaceProperties props) {
        Matcher matcher = new Matcher();
        matcher.setRaceType(props.getRaceType());
        matcher.addDrivers(driverEntries);
        matcher.addStartTimes(startTimes);
        matcher.addEndTimes(endTimes);
        matcher.setRaceEndtime(props.getRaceEndTime());
        if (props.getMinTime() != null) {
            matcher.addMinTime(props.getMinTime());
        } else {
            matcher.addMinTime("00:15:00");
        }
        return matcher;
    }

    /**
     * Writes the result to the specified file path.
     *
     * @author Nollte
     * @param resultPath           The file path to write the result to.
     * @param formattedResultLines The formatted result lines to write.
     * @throws RaceError If an error occurs while writing the file
     */
    private static void writeResult(String resultPath, List<String> formattedResultLines) throws RaceError {
        try {
            Files.deleteIfExists(Paths.get(resultPath));
            Files.write(Paths.get(resultPath), formattedResultLines, StandardCharsets.UTF_8, StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RaceError("Error when writing file " + resultPath + " " + e.getMessage(), e);
        }
    }
}
