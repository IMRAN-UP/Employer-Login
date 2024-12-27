package view;

import javax.swing.*;
import java.awt.*;

public class EmployerPanel extends JPanel {

    private InputPanel inPanel;
    private BtnPanel btnPanel;
    private ListPanel listPanel;
    private SearchPanel searchPanel;

    public EmployerPanel() {
        setLayout(new BorderLayout());

        inPanel = new InputPanel();
        btnPanel = new BtnPanel(new String [] {"Add", "Remove", "Update", "Create", "Load", "Import","Export"});
        listPanel = new ListPanel();
        searchPanel = new SearchPanel();
        JPanel tmpPanel = new JPanel();

        tmpPanel.setLayout(new BorderLayout());
        tmpPanel.add(inPanel, BorderLayout.CENTER);
        tmpPanel.add(searchPanel, BorderLayout.NORTH);


        add(tmpPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);
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

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    public void handleAccess () {
        removeAll();
        add(listPanel);
    }
}
