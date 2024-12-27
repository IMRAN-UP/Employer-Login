package view;

import javax.swing.*;
import java.awt.*;

public class FormFrame extends JFrame {

    private EmployerPanel employerPanel;
    private HolidayPanel holidayPanel;
    private SwitchPanel switchPanel;

    public FormFrame() {
        
        setTitle("Employer Platform");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    
        employerPanel = new EmployerPanel();
        holidayPanel = new HolidayPanel();
        switchPanel = new SwitchPanel(employerPanel, holidayPanel);
        add(switchPanel, BorderLayout.CENTER);
        
        setVisible(false);
    }

    public EmployerPanel getEmployerPanel() {
        return employerPanel;
    }

    public HolidayPanel getHolidayPanel() {
        return holidayPanel;
    }

    public void handleAccess () {
        employerPanel.handleAccess();
        holidayPanel.handleAccess();
    }

}
