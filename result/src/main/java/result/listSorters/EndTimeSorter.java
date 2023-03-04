package result.listSorters;

import java.time.LocalTime;
import java.util.List;

import result.ResultEntry;

/**
 * Class that sorts by finishing time.
 * Can be called by the Formatter class to sort the results.
 */

class EndTimeSorter implements Sorter {

    /**
     * Sorts the given list of {@link ResultEntry} by their finishing time.
     *
     * @author Lucas Wittich
     * @author Osama Hajjouz
     * @param results the list of {@link ResultEntry} to sort.
     * @return the sorted list of {@link ResultEntry}.
     */
    @Override
    public List<ResultEntry> sort(List<ResultEntry> results) {
        results.sort((a, b) -> {
            try {
                return LocalTime.parse(a.getEnds().get(a.getEnds().size() - 1))
                        .compareTo(LocalTime.parse(b.getEnds().get(b.getEnds().size() - 1)));
            } catch (Exception e) {
                return -9000;
            }
        });

        return results;
    }
}