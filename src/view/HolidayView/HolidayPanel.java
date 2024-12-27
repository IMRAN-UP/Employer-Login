package view;

import javax.swing.*;
import java.awt.*;

public class HolidayPanel extends JPanel {

    private HoliInputPanel inPanel;
    private BtnPanel btnPanel;
    private HoliListPanel listPanel;
    private HolidaySearchPanel searchPanel;

    public HolidayPanel() {
        setLayout(new BorderLayout());

        inPanel = new HoliInputPanel();
        btnPanel = new BtnPanel(new String[] {"Add", "Update", "Remove", "Load"});
        listPanel = new HoliListPanel();
        JPanel tmpPanel = new JPanel();
        searchPanel = new HolidaySearchPanel();
        tmpPanel.setLayout(new BorderLayout());
        tmpPanel.add(inPanel, BorderLayout.CENTER);
        tmpPanel.add(searchPanel, BorderLayout.NORTH);

        add(tmpPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(false);
    }

    public HoliInputPanel getInPanel() {
        return inPanel;
    }

    public BtnPanel getBtnPanel() {
        return btnPanel;
    }

    public HoliListPanel getListPanel() {
        return listPanel;
    }

    public HolidaySearchPanel getSearchPanel() {
        return searchPanel;
    }

    public void handleAccess () {
        removeAll();
        add(listPanel);
    }
}
