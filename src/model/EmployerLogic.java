package model;

import enums.*;
import dao.EmployerDAO;
import java.util.List;

public class EmployerLogic {

    private EmployerDAO dao;

    public EmployerLogic (EmployerDAO dao) {
        this.dao = dao;
    }

    public boolean addEmployer(int id, String firstName, String lastName, String email, int phoneNumber, double salary, Role role, Poste poste) {

        if ( !isValidEmail(email) || 
            !isValidPhoneNumber(phoneNumber) ||
            isValidName(firstName, lastName) ||
            !isValidSalary(salary))
        {
            return false;
        }

        return dao.addEmployer( new Employer(
                id,
                firstName,
                lastName,
                email,
                phoneNumber,
                salary,
                role,
                poste
        ));
    }


    public boolean updateEmployer(int id, String firstName, String lastName, String email, int phoneNumber, double salary, Role role, Poste poste) {


        if ( isValidEmail(email) ) {

            return dao.updateEmployer(new Employer(
                id,
                firstName,
                lastName,
                email,
                phoneNumber,
                salary,
                role,
                poste
            ));
        }

        return false;
    }

    public int isValidLogin(String username, String password) {
        return dao.isValidPassword(username, password);
    }

    public boolean isAdmin(int id) {
        return dao.isAdmin(id);
    }

    public boolean createLogin(int id, String username, String password, String confirmPassword) {
        if( !password.equals(confirmPassword) || username.length() == 0 || password.length() == 0 || confirmPassword.length() == 0 ) return false;
        return dao.createLogin(id, username, password);
    }

    private boolean isValidEmail(String email) {
        return email.contains("@gmail.com");
    }

    private boolean isValidPhoneNumber(int phoneNumber) {
        return String.valueOf(phoneNumber).length() == 10;
    }

    private boolean isValidName(String firstName, String lastName) {
        return firstName.equals("") || lastName.equals("");
    }

    private boolean isValidSalary (double salary) {
        return salary > 0;
    }

    public boolean deleteEmployer(int id) {
        return dao.deleteEmployer(id);
    }

    public List<Employer> getAllEmployers() {
        return dao.getAllEmployers();
    }
}