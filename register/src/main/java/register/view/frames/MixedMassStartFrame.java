package register.view.frames;

import javax.swing.*;

import register.model.Driver;
import register.model.DriverTable;
import register.model.RegisterTable;
import register.view.DriverCheckbox;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The frame for the mixed mass start
 */
public class MixedMassStartFrame extends JFrame {
    private HashSet<Driver> driversToStart = new HashSet<Driver>();

    /**
     * Creates a new mixed mass start frame
     * 
     * @param startTable       the table to add the start times to
     * @param driverTable      the table to get the drivers from
     * @param massStartOptions the mass start options frame to dispose of
     * @author Oliver Levay
     * @author Simon Persson
     */
    public MixedMassStartFrame(RegisterTable startTable, DriverTable driverTable,
            MassStartOptionsFrame massStartOptions) {
        super("Mixed Mass Start");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        driverTable.getDrivers().forEach(driver -> {
            JCheckBox checkbox = new DriverCheckbox(driver, driversToStart);
            panel.add(checkbox);
        });

        JButton start = new JButton("Start");
        start.addActionListener(event -> {
            System.out.println("Starting drivers: " + driversToStart);
            massStartOptions.startDrivers(startTable, new ArrayList<Driver>(driversToStart));
            massStartOptions.dispose();
            this.dispose();
        });
        start.setFont(start.getFont().deriveFont(40.0f));

        panel.add(start);

        this.add(panel);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
