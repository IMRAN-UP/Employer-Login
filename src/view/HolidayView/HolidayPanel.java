package view;

import javax.swing.*;
import java.awt.*;

public class HolidayPanel extends JPanel {

    private HoliInputPanel inPanel;
    private HoliBtnPanel btnPanel;
    private HoliListPanel listPanel;

    public HolidayPanel() {
        setLayout(new BorderLayout());

        inPanel = new HoliInputPanel();
        btnPanel = new HoliBtnPanel();
        listPanel = new HoliListPanel();

        add(inPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(false);
    }

    public HoliInputPanel getInPanel() {
        return inPanel;
    }

    public HoliBtnPanel getBtnPanel() {
        return btnPanel;
    }

    public HoliListPanel getListPanel() {
        return listPanel;
    }
}
