package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import model.Holiday;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HoliListPanel extends JPanel {

    private JPanel contentPanel;
    private int selectedRowId = -1;
    private List<JPanel> rowPanels = new ArrayList<>();

    public HoliListPanel() {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1, 5));
        titlePanel.add(new JLabel("Id", SwingConstants.CENTER));
        titlePanel.add(new JLabel("Nom", SwingConstants.CENTER));
        titlePanel.add(new JLabel("Start Date", SwingConstants.CENTER));
        titlePanel.add(new JLabel("End Date", SwingConstants.CENTER));
        titlePanel.add(new JLabel("Holiday Type", SwingConstants.CENTER));
        titlePanel.setBorder(new LineBorder(Color.BLACK));
        add(titlePanel, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateHolidayList(List<Holiday> Holidays) {
        contentPanel.removeAll();
        rowPanels.clear();

        for (Holiday Holiday : Holidays) {
            JPanel rowPanel = new JPanel(new GridLayout(1, 5));
            rowPanel.setBorder(new LineBorder(Color.GRAY));
            rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            JLabel idLabel = new JLabel(String.valueOf(Holiday.getId()), SwingConstants.CENTER);
            JLabel fullNameLabel = new JLabel(Holiday.getFullName(), SwingConstants.CENTER);
            JLabel startDateLabel = new JLabel(Holiday.getStartDate().format(formatter), SwingConstants.CENTER);
            JLabel endDateLabel = new JLabel(Holiday.getEndDate().format(formatter), SwingConstants.CENTER);
            JLabel HoliTypeLabel = new JLabel(String.valueOf(Holiday.getHolidayType()), SwingConstants.CENTER);

            rowPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    highlightRow(rowPanel, Holiday.getId());
                }
            });

            rowPanel.add(idLabel);
            rowPanel.add(fullNameLabel);
            rowPanel.add(startDateLabel);
            rowPanel.add(endDateLabel);
            rowPanel.add(HoliTypeLabel);

            contentPanel.add(rowPanel);
            rowPanels.add(rowPanel);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void highlightRow(JPanel selectedRow, int HolidayId) {
        for (JPanel row : rowPanels) {
            row.setBackground(Color.WHITE);
        }

        selectedRow.setBackground(Color.LIGHT_GRAY);
        selectedRowId = HolidayId;
    }

    public int getSelectedRowId() {
        return selectedRowId;
    }
}