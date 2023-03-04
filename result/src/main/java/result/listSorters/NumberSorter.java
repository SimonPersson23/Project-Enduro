package result.listSorters;

import java.util.List;

import result.ResultEntry;

/**
 * Class that sorts by start number
 * Can be called by the Formatter class to sort the results.
 */
public class NumberSorter implements Sorter{

    /**
     * Sorts the given list of {@link ResultEntry} by their start number
     *
     * @author Lucas Wittich
     * @author Osama Hajjouz
     * @param results List of {@link ResultEntry} objects to be sorted
     * @return the sorted List of {@link ResultEntry} objects
     */
    @Override
    public List<ResultEntry> sort(List<ResultEntry> results) {
        results.sort((a, b) -> {
            try{
                return (a.getNumber()).compareTo((b.getNumber()));
            } catch (Exception e){
                return -9000;
            }
        });
        return results;
    }


}