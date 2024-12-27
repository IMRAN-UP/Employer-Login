package controller;

import view.*;
import dao.*;
import model.*;
import enums.*;
import java.util.List;
import javax.swing.JOptionPane;

public class HolidayController {

    private FormFrame frame;
    private HolidayLogic holidayLogic;

    public HolidayController(FormFrame frame, HolidayLogic holidayLogic) {
        this.holidayLogic = holidayLogic;
        this.frame = frame;

        frame.getHolidayPanel().getBtnPanel().getButtonByLabel("Add").addActionListener(addEvent -> addHoliday());
        frame.getHolidayPanel().getBtnPanel().getButtonByLabel("Update").addActionListener(updateEvent -> updateHoliday());
        frame.getHolidayPanel().getBtnPanel().getButtonByLabel("Remove").addActionListener(deleteEvent -> deleteHoliday());
        frame.getHolidayPanel().getBtnPanel().getButtonByLabel("Load").addActionListener(createEvent -> loadHolidays());
        frame.getHolidayPanel().getSearchPanel().getSearchButton().addActionListener(getByDates -> getHolidayByDates());
        loadHolidays();
    }

    private void addHoliday() {
        try {
            if (holidayLogic.addHoliday(
                    1,
                    frame.getEmployerPanel().getListPanel().getSelectedRowId(),
                    "fulll name",
                    frame.getHolidayPanel().getInPanel().getStartDate(),
                    frame.getHolidayPanel().getInPanel().getEndDate(),
                    HolidayType.valueOf(frame.getHolidayPanel().getInPanel().getSelectedHolidayType().toString())
                ))    
            {
                JOptionPane.showMessageDialog(frame, "Holiday added successfully.");
                loadHolidays();
                frame.getHolidayPanel().getInPanel().clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add Holiday", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to add Holiday", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateHoliday() {
        try {
            if (holidayLogic.updateHoliday(
                       frame.getHolidayPanel().getListPanel().getSelectedRowId(),
                       frame.getEmployerPanel().getListPanel().getSelectedRowId(),
                       "fulll name",
                       frame.getHolidayPanel().getInPanel().getStartDate(),
                       frame.getHolidayPanel().getInPanel().getEndDate(),
                       HolidayType.valueOf(frame.getHolidayPanel().getInPanel().getSelectedHolidayType().toString())
               )) 
            {
               JOptionPane.showMessageDialog(frame, "Holiday updated successfully.");
               loadHolidays();
               frame.getHolidayPanel().getInPanel().clearFields();
            } else {
               JOptionPane.showMessageDialog(null, "Failed to update Holiday", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Failed to update Holiday", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteHoliday() {
     try {
         if (holidayLogic.deleteHoliday(frame.getHolidayPanel().getListPanel().getSelectedRowId())) {
            JOptionPane.showMessageDialog(frame, "Holiday deleted successfully.");
            loadHolidays();
            frame.getHolidayPanel().getInPanel().clearFields();
         } else {
            JOptionPane.showMessageDialog(null, "Failed to delete Holiday", "Error", JOptionPane.ERROR_MESSAGE);
         }
     } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failed to delete Holiday", "Error", JOptionPane.ERROR_MESSAGE);
     }
    }

    private void loadHolidays() {
        frame.getHolidayPanel().getListPanel().updateHolidayList(holidayLogic.getAllHolidays());
    }

    private void getHolidayByDates() {
        frame.getHolidayPanel().getListPanel().updateHolidayList(holidayLogic.getHolidayByDates(frame.getHolidayPanel().getSearchPanel().getStartDateSearchField(),frame.getHolidayPanel().getSearchPanel().getEndDateSearchField()));
    }
}