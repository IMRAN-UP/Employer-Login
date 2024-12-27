package view;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.ZoneId;
import java.time.LocalDate;
import enums.*;

public class HolidaySearchPanel extends JPanel {

    private JFormattedTextField startDateSearchField;
    private JFormattedTextField endDateSearchField;
    private JButton searchButton;

    public HolidaySearchPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("dd/MM/yyyy"));

        startDateSearchField = new JFormattedTextField(dateFormatter);
        startDateSearchField.setValue(new Date());
        startDateSearchField.setColumns(15);
        startDateSearchField.setPreferredSize(new Dimension(150, 30));

        endDateSearchField = new JFormattedTextField(dateFormatter);
        endDateSearchField.setValue(new Date());
        endDateSearchField.setColumns(15);
        endDateSearchField.setPreferredSize(new Dimension(150, 30));

        add(startDateSearchField);
        add(endDateSearchField);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 14));
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setBackground(new Color(173, 216, 230)); 
        searchButton.setFocusPainted(false);  
        searchButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        addButtonHoverEffect(searchButton);
        add(searchButton);
    }

    private void addButtonHoverEffect(JButton button) {
        button.setBackground(new Color(238, 238, 238));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(173, 216, 230)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(238, 238, 238)); 
            }
        });
    }

    public LocalDate getStartDateSearchField() {
        Date date = (Date) startDateSearchField.getValue();
        return date != null ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

    public LocalDate getEndDateSearchField() {
        Date date = (Date) endDateSearchField.getValue();
        return date!= null? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
