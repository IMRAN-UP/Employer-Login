package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import enums.*;
import dao.EmployerDAO;
import java.util.List;
import java.io.*;

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

        return dao.add( new Employer(
                id,
                firstName,
                lastName,
                email,
                phoneNumber,
                salary,
                role,
                poste,
                25
        ));
    }

    public boolean updateEmployer(int id, String firstName, String lastName, String email, int phoneNumber, double salary, Role role, Poste poste) {


        if ( isValidEmail(email) ) {

            return dao.update(new Employer(
                id,
                firstName,
                lastName,
                email,
                phoneNumber,
                salary,
                role,
                poste,
                25
            ));
        }

        return false;
    }

    public boolean handleLogin(String username, String password) {
        return (null == dao.getPassword(username) || !dao.getPassword(username).equals(hashPassword(password))) ? false : true;
    }

    public boolean handleAccess(String username) {
        return (null == dao.getRole(username) || !dao.getRole(username).equals("MANAGER")) ? false : true ;
    }

    public boolean createLogin(int id, String username, String password, String confirmPassword) {
        if( !password.equals(confirmPassword) || username.length() == 0 || password.length() == 0 || confirmPassword.length() == 0 ) return false;
        return dao.createLogin(id, username, hashPassword(password));
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
        return dao.delete(id);
    }

    public List<Employer> getAllEmployers() {
        return dao.getAll();
    }

    public List<Employer> getEmployerByName(String name) {
        return dao.getByName(name);
    }

    private static String hashPassword(String password) {
        try {
            byte[] encodedHash = MessageDigest.getInstance("SHA-256").digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: SHA-256 Algorithm not found!", e);
        }
    }

    public int importData(File file) throws IOException {
        return dao.importData(file);
    }

    public void exportData(File file) throws IOException {
        dao.exportData(file);
    }
}