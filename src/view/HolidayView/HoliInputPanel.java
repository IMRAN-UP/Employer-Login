package view;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.ZoneId;
import java.time.Instant;
import java.time.LocalDate;
import enums.*;

public class HoliInputPanel extends JPanel {

    private JFormattedTextField startDateField;
    private JFormattedTextField endDateField;
    private JComboBox<HolidayType> typeField;

    public HoliInputPanel() {
        setLayout(new GridLayout(3, 2, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("dd/MM/yyyy"));

        startDateField = new JFormattedTextField(dateFormatter);
        startDateField.setValue(new Date());
        startDateField.setColumns(15);

        endDateField = new JFormattedTextField(dateFormatter);
        endDateField.setValue(new Date());
        endDateField.setColumns(15);

        typeField = new JComboBox<>(HolidayType.values());

        add(new JLabel("Start Date (dd/MM/yyyy):"));
        add(startDateField);

        add(new JLabel("End Date (dd/MM/yyyy):"));
        add(endDateField);

        add(new JLabel("Holiday Type:"));
        add(typeField);
    }

    public LocalDate getStartDate() {
        Date date = (Date) startDateField.getValue();
        return date!= null? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

    public LocalDate getEndDate() {
        Date date = (Date) endDateField.getValue();
        return date!= null? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

    public HolidayType getSelectedHolidayType() {
        return (HolidayType) typeField.getSelectedItem();
    }

    public void clearFields() {
        startDateField.setValue(null);
        endDateField.setValue(null);
        typeField.setSelectedIndex(0);
    }
}
