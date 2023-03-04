package result.listSorters;

import result.ResultEntry;
import java.util.List;

/**
 * Interface for all different sorting classes, input parameter to resultformatter
 * Designed with strategy pattern in mind in order to be able to define
 * all types of sorting as one.
 */

public interface Sorter {
    /**
     * Sorting algorithm that is unique to each class it is implemented in.
     *
     * @author Lucas Wittich
     * @author Osama Hajjouz
     * @param results - a list of results to be sorted.
     * @return a sorted list according to algorithm
     */
    List<ResultEntry> sort(List<ResultEntry> results);
}