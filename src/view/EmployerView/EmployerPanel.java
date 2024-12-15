package view;

import javax.swing.*;
import java.awt.*;

public class EmployerPanel extends JPanel {

    private InputPanel inPanel;
    private BtnPanel btnPanel;
    private ListPanel listPanel;

    public EmployerPanel() {
        setLayout(new BorderLayout());

        inPanel = new InputPanel();
        btnPanel = new BtnPanel();
        listPanel = new ListPanel();

        add(inPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(false);
    }

    public InputPanel getInPanel() {
        return inPanel;
    }

    public BtnPanel getBtnPanel() {
        return btnPanel;
    }

    public ListPanel getListPanel() {
        return listPanel;
    }
}
