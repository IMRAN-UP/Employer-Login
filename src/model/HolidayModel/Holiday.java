package model;

import enums.*;
import java.time.LocalDate;

public class Holiday {
    
    private int id, employerId, daysLeft;
    private String fullName;
    private LocalDate startDate;
    private LocalDate endDate;
    private HolidayType holidayType;

    public Holiday(int id, int employer_id, String fullName, LocalDate startDate, LocalDate endDate, HolidayType holidayType) {
        this.id = id;
        this.employerId = employer_id;
        this.fullName = fullName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.holidayType = holidayType;
        this.daysLeft = daysLeft;
    }

    public int getId() {
        return id;
    }

    public int getEmployerId() {
        return employerId;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public HolidayType getHolidayType() {
        return holidayType;
    }
}
