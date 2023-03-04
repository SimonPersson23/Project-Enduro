package result.checks;

import result.Driver;

import util.TimeUtils;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines different types of error checkers, based on the decorator pattern in
 * CheckChain.
 */
public class ErrorChecks {

    /**
     * CheckMissingTimes extends CheckChain and provides a way to check for missing
     * start or end times for a Driver.
     */
    public static class CheckMissingTimes extends CheckChain {

        /**
         * Returns a string representing the end time of the Driver, or a default string
         * if there are no end times.
         *
         * @author Nollte
         * @param driver The Driver object being checked.
         * @return String representation of the end time or a default string if there
         *         are no end times.
         */
        @Override
        public List<String> getEndTimes(Driver driver) {
            if (driver.getEnds().isEmpty()) {
                List<String> ar = new ArrayList<String>();
                ar.add("Slut?");
                return ar;
            } else {
                return super.getEndTimes(driver);
            }
        }

        /**
         * Returns a string representing the start time of the Driver, or a default
         * string if there are no start times.
         *
         * @author Nollte
         * @param driver The Driver object being checked.
         * @return String representation of the start time or a default string if there
         *         are no start times.
         */
        @Override
        public String getStartTime(Driver driver) {
            return driver.getStarts().isEmpty() ? "Start?" : super.getStartTime(driver);
        }

        /**
         * Returns a string representing the total time of the Driver, or a default
         * string if there are missing start or end times.
         *
         * @author Nollte
         * @param driver The Driver object being checked.
         * @return String representation of the total time or a default string if there
         *         are missing start or end times.
         */
        @Override
        public String getTotalTime(Driver driver) {
            return driver.getStarts().isEmpty() || driver.getEnds().isEmpty() ? "--:--:--" : super.getTotalTime(driver);
        }

        /**
         * Returns a string representing the name of the Driver, or a default
         * string if the driver's name is null.
         *
         * @author Simon Persson
         * @param driver The Driver object being checked.
         * @return String representation of the driver's name or a default string if the
         *         driver's name is null.
         */
        @Override
        public String getName(Driver driver) {
            return driver.getName() == null ? "Namn?" : super.getName(driver);
        }
    }

    /**
     * CheckMultipleTimes extends CheckChain and provides a way to check for
     * multiple start or end times for a Driver. This is used in order to establish
     * where an error has occurred.
     * If there are multiple start or end times, it returns a list of strings and
     * the errors are
     * handled in accordance with the specification.
     */
    public static class CheckMultipleTimes extends CheckChain {
        /**
         * Returns a list of strings representing any errors found, including multiple
         * start or end times.
         *
         * @author Nollte
         * @param driver The Driver object being checked.
         * @return List of strings representing any errors found, including multiple
         *         start or end times.
         */
        @Override
        public List<String> getErrors(Driver driver) {
            List<String> res = super.getErrors(driver);
            checkMultipleTimes(res, driver.getStarts(), "Flera starttider?");
            checkMultipleTimes(res, driver.getEnds(), "Flera maltider?");
            return res;
        }

        /**
         * Adds any errors found due to multiple times to the given list of errors.
         * If there are multiple times, an error message is added to the list, followed
         * by each of the times in the list formatted as a string.
         *
         * @author Osama Hajjouz
         * @param res          The list of errors to add to.
         * @param times        The list of times being checked for duplicates.
         * @param errorMessage The error message to add if there are multiple times.
         */
        private void checkMultipleTimes(List<String> res, List<LocalTime> times, String errorMessage) {
            if (times.size() > 1) {
                res.add(errorMessage);
                for (int i = 1; i < times.size(); i++) {
                    String formattedTime = TimeUtils.formatTime(times.get(i));
                    res.add(formattedTime);
                }
            }
        }
    }

    /**
     * Class that extends `CheckChain` to check if the total time for a `Driver` is
     * less than minimum time defined. Used in order to guarantee a printout in
     * accordance with the specification.
     * If the total time is less than minimum time, it returns "Omojlig Sluttid".
     */
    public static class CheckImpossibleTime extends CheckChain {
        // !denna kanske borde vara Global eller parameter, config fil?
        LocalTime minTimeLocalTime;

        /**
         * Creates a new instance of `CheckImpossibleTime`.
         *
         * @author Simon Persson
         * @param minTime The minimum time allowed for the total time of a `Driver`.
         */
        public CheckImpossibleTime(String minTime) {
            minTimeLocalTime = LocalTime.parse(minTime);
        }

        /**
         * Calculates the total time for a Driver and checks if it is less than
         * minimum time.
         *
         * @author Lucas Wittich
         * @author Julius Ekstrand
         * @author Simon Persson
         * @author Sharmarke Osman
         * @param driver The Driver object to check the total time for.
         * @return List of strings representing any errors found, including impossible
         *         times.
         * @throws IndexOutOfBoundsException If getTotal is not possible to get.
         */
        @Override
        public List<String> getErrors(Driver driver) {

            List<String> res = super.getErrors(driver);
            try {
                LocalTime totalTime = LocalTime.parse(super.getTotalTime(driver));
                if (totalTime.compareTo(minTimeLocalTime) < 0) {
                    res.add("Omojlig Totaltid?");
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            return res;
        }
    }

    public static class CheckRaceCompleted extends CheckChain {
        // !denna kanske borde vara Global eller parameter, config fil?
        LocalTime raceEndTime;

        /**
         * Creates a new instance of `CheckImpossibleTime`.
         *
         * @author Simon Persson
         * @param minTime The minimum time allowed for the total time of a `Driver`.
         */
        public CheckRaceCompleted(String raceEndTime) {
            if (raceEndTime == null) return;
            this.raceEndTime = LocalTime.parse(raceEndTime);
        }

        /**
         * Calculates the total time for a Driver and checks if it is less than
         * minimum time.
         *
         * @author Lucas Wittich
         * @author Julius Ekstrand
         * @author Simon Persson
         * @author Sharmarke Osman
         * @param driver The Driver object to check the total time for.
         * @return List of strings representing any errors found, including impossible
         *         times.
         * @throws IndexOutOfBoundsException If getTotal is not possible to get.
         */
        @Override
        public List<String> getErrors(Driver driver) {

            if (raceEndTime == null) return super.getErrors(driver);

            List<String> res = super.getErrors(driver);
            List<LocalTime> laptimes = driver.getEnds();
            if (laptimes.isEmpty()) {
                res.add("Slut?");
                return res;
            }

            if (laptimes.get(laptimes.size()-1).compareTo(raceEndTime) < 0) {
                res.add("Slut?");
            } else {
                if (laptimes.size() > 1 && laptimes.get(laptimes.size()-2).compareTo(raceEndTime) > 0) res.add("Flera måltider?");
            }
                    
            return res;
        }
    }

}
