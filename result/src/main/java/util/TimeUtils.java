package util;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A utility class for formatting and converting times.
 */
public class TimeUtils {

    private static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * Formats the given time in the format defined by {@link #TIME_FORMAT}.
     *
     * @author Nollte
     * @param time The time to format.
     * @return The formatted time string.
     */
    public static String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    /**
     * Formats the given duration in the format defined by {@link #TIME_FORMAT}.
     *
     * @author Nollte
     * @param duration The duration to format.
     * @return The formatted duration string.
     */
    public static String formatTime(Duration duration) {
        long s = duration.getSeconds();
        return String.format("%02d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));
    }

}
