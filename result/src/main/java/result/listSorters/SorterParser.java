package result.listSorters;


/**
 * Class to parse sorter from config file
 */
public class SorterParser {

    /**
     * Method to parse an input string to a Sorter
     *
     * @author Lucas Wittich
     * @author Osama Hajjouz
     * @param input The input string to parse
     * @return a NoSorter object
     */

    public Sorter parse(String input){

        try {
            if(input.equals("name")) return new NameSorter();
            else if (input.equals("start")) return new StartTimeSorter();
            else if (input.equals("finish")) return new EndTimeSorter();
            else if (input.equals("total")) return new TotalTimeSorter();
            else if (input.equals("number")) return new NumberSorter();
            else return new NoSorter();
        } catch (Exception e) {
            return new NoSorter();
        }
    }

}