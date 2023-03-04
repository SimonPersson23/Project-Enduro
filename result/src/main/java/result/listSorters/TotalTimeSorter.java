package result.listSorters;

import java.time.LocalTime;
import java.util.List;

import result.ResultEntry;

/**
 * Class that sorts the list of {@link ResultEntry} by total time.
 * Can be called by the Formatter class to sort the results.
 */

public class TotalTimeSorter implements Sorter {

    /**
     * Sorts the list of {@link ResultEntry} by total time.
     *
     * @author Lucas Wittich
     * @author Osama Hajjouz
     * @param results The list of {@link ResultEntry} to be sorted.
     * @return The sorted list of {@link ResultEntry}.
     */
    @Override
    public List<ResultEntry> sort(List<ResultEntry> results) {
        results.sort((a, b) -> {
            try{
                return LocalTime.parse(a.getTotal()).compareTo(LocalTime.parse(b.getTotal()));
            } catch (Exception e){
                return -9000;
            }
        });

        return results;
    }

}
