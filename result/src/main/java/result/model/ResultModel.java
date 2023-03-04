package result.model;

/**
 * This class represents one row in the table, aka one register
 */
public class ResultModel{
    private int resultNum;
    private int startNum;

    private String name;
    private String totalTime;
    private String startTime;
    private String finishTime;


    /**
     * Constructor for RegisterModel
     *
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @param resultNum The result number of the registration
     * @param startNum   The start number of the registration
     * @param name       The name of the registration
     * @param totalTime  The total time of the registration
     * @param startTime  The start time of the registration
     * @param finishTime The finish time of the registration
     */
    public ResultModel(int resultNum, int startNum, String name, String totalTime, String startTime, String finishTime) {
        this.resultNum = resultNum;
        this.startNum = startNum;
        this.name = name;
        this.totalTime = totalTime;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    /**
     * Getter for resultNum
     *
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @return The result number of the register model (this row in the table)
     */
    public int getResultNum() {
        return resultNum;
    }

    /**
     * Getter for startNum
     *
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @return The start number of the register model (this row in the table)
     */
    public int getStartNum() {
        return startNum;
    }

    /**
     * Getter for name
     *
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @return The name of the register model (this row in the table)
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for totalTime
     *
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @return The total time of the register model (this row in the table)
     */
    public String getTotalTime() {
        return totalTime;
    }

    /**
     * Getter for startTime
     *
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @return The start time of the register model (this row in the table)
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Getter for finishTime
     *
     * @author Sharmarke Osman
     * @author Julius Ekstrand
     * @return The finish time of the register model (this row in the table)
     */
    public String getFinishTime() {
        return finishTime;
    }
}