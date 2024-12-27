package model;
import enums.*;
import dao.*;
import java.util.List;
import java.time.LocalDate;

public class HolidayLogic {

    private HolidayDAO dao;

    public HolidayLogic (HolidayDAO dao) {
        this.dao = dao;
    }

    public boolean addHoliday(int id, int employer_id, String fullName, LocalDate startDate, LocalDate endDate, HolidayType holidayType) {

        if (!isValidDates(startDate, endDate)) return false;

        int days =  dao.getHolidayDays(employer_id);
        if (days == -1) return false;
        if (getDateDifferenceInDays(startDate, endDate) > days) return false;

        return dao.add( new Holiday(
                id,
                employer_id,
                fullName,
                startDate,
                endDate,
                holidayType
        ));
    }

    public boolean updateHoliday(int id, int employer_id, String fullName, LocalDate startDate, LocalDate endDate, HolidayType holidayType) {

        if (!isValidDates(startDate, endDate)) return false;

        int days =  dao.getHolidayDays(employer_id);
        if (days == -1) return false;
        if (getDateDifferenceInDays(startDate, endDate) > days) return false;

        return dao.update(new Holiday(
                id,
                employer_id,
                fullName,
                startDate,
                endDate,
                holidayType
        ));
    }

    public boolean deleteHoliday(int id) {
        return dao.delete(id);
    }

    public List<Holiday> getAllHolidays() {
        return dao.getAll();
    }

    private boolean isValidDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return false;
        }
        return endDate.isAfter(startDate);
    }

    private int getDateDifferenceInDays(LocalDate startDate, LocalDate endDate) {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    } 

    public List<Holiday> getHolidayByDates(LocalDate startDate, LocalDate endDate) {
        return dao.getByDates(startDate, endDate);
    }
}