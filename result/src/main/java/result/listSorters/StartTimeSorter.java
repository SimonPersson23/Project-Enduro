package result.listSorters;

import java.time.LocalTime;
import java.util.List;

import result.ResultEntry;

/**
 * Class that sorts by Start Time
 * Can be called by the Formatter class to sort the results.
 */

class StartTimeSorter implements Sorter{

    /**
     * Sorts the given list of {@link ResultEntry} by their starting time
     *
     * @author Lucas Wittich
     * @author Osama Hajjouz
     * @param results the list of {@link ResultEntry} objects to sort.
     * @return a sorted list of {@link ResultEntry} objects by start time.
     */
    @Override
    public List<ResultEntry> sort(List<ResultEntry> results) {
        results.sort((a, b) -> {
            try{
                return LocalTime.parse(a.getStart()).compareTo(LocalTime.parse(b.getStart()));
            } catch (Exception e){
                return -9000;
            }
        });

        return results;
    }
}