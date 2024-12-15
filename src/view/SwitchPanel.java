package view;

import javax.swing.*;
import java.awt.*;

public class SwitchPanel extends JTabbedPane {
    private EmployerPanel employerPanel;
    private HolidayPanel holidayPanel;

    public SwitchPanel(EmployerPanel employerPanel, HolidayPanel holidayPanel) {
        this.employerPanel = employerPanel;
        this.holidayPanel = holidayPanel;
        
        addTab("Employer", employerPanel);
        addTab("Holiday", holidayPanel);

        setTabPlacement(JTabbedPane.TOP);
        setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
    }
}