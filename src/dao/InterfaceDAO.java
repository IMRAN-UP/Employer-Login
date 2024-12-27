package dao;

import java.util.List;
import java.time.LocalDate;
import java.io.*;

public interface InterfaceDAO<Type> {
    boolean add(Type entity);
    boolean update(Type entity);
    boolean delete(int id);
    List<Type> getAll();
    String getPassword(String username);
    String getRole(String username);
    int getHolidayDays(int id);
    List<Type> getByName(String name);
    List<Type> getByDates(LocalDate startDate, LocalDate endDate);
    boolean createLogin(int employee_id, String username, String password);
    int importData(File filepath) throws IOException;
    void exportData(File filepath) throws IOException;
}
