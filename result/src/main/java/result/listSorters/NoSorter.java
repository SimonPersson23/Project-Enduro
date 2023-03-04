package result.listSorters;

import java.util.List;

import result.ResultEntry;

/**
 * Class that returns list as it was input
 * A default case in the event of errors while calling the sorter
 * from the config file. 
 */

public class NoSorter implements Sorter{

    /**
     * Returns the list of {@link ResultEntry} objects as it was input
     *
     * @author Lucas Wittich
     * @author Osama Hajjouz
     * @param results List of {@link ResultEntry} objects to be sorted
     * @return List of {@link ResultEntry} objects
     */
    @Override
    public List<ResultEntry> sort(List<ResultEntry> results) {
        return results;
    }
}