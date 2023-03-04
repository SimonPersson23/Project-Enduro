package result.formatters;

import java.util.List;

import result.Formatter;
import result.ResultEntry;
import result.listSorters.Sorter;

public class MarathonFormatter extends Formatter {

    /**
     * Getter for ends
     *
     * @author Nollte
     * @version 1.00
     * @param driverResult
     * @return Returns a formatted String of the driverResult
     */
    @Override
    public String formatDriver(ResultEntry driverResult) {

        StringBuilder sb = new StringBuilder();

        // start number
        sb.append(driverResult.getNumber()).append(SEP).append(" ");

        // driver name
        sb.append(super.getName(driverResult)).append(SEP).append(" ");

        // total time
        sb.append(driverResult.getTotal()).append(SEP).append(" ");

        // start time
        sb.append(driverResult.getStart()).append(SEP).append(" ");

        // end time
        sb.append(driverResult.getEnds().get(0));

        List<String> errors = driverResult.getErrors();
        if (!errors.isEmpty()) {
            sb.append(SEP);
            for (String e : errors) {
                sb.append(" ").append(e);
            }
        }

        return sb.toString();
    }
}
