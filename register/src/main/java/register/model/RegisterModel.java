package register.model;

/**
 * This class represents one row in the table, aka one register
 */
public class RegisterModel {
    private int startNum;
    private String time;

    /**
     * Constructor for RegisterModel
     *
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @param time The time of the registration
     */
    public RegisterModel(String time) {
        this.time = time;

    }

    /**
     * Set start number, so that registration of startnumbers can occur at a later time.
     * @author Lucas Wittich
     * @author Gustav Serger
     * @param startNum
     */
    public void setStartNum(int startNum){
        
        this.startNum = startNum;
    }

    /**
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @return The start number of the register model (this row in the table)
     */
    public int getStartNum() {
        return startNum;
    }

    /**
     * @author Oliver Levay
     * @author Sharmarke Osman
     * @return The time of the register model (this row in the table)
     */
    public String getTime() {
        return time;
    }
}
