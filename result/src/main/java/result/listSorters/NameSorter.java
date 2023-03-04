package result.listSorters;

import java.util.List;

import result.ResultEntry;

/**
 * Class that sorts the list of {@link ResultEntry} by name
 * Can be called in the Formatter class to sort results.
 */

class NameSorter implements Sorter{

    /**
     * Sorts the list of {@link ResultEntry} by name.
     *
     * @author Lucas Wittich
     * @author Osama Hajjouz
     * @param results The list of {@link ResultEntry} to be sorted.
     * @return The sorted list of {@link ResultEntry} by name.
     */
    @Override
    public List<ResultEntry> sort(List<ResultEntry> results) {

        results.sort((a, b) -> {
            try{
                return (a.getName()).compareTo((b.getName()));
            } catch (Exception e){
                return -9000;
            }
        });

        return results;
    }

}