package dao;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Employer;
import enums.Role;
import enums.Poste;

public class EmployerDAO implements EmployerInterface {

    private Connection connection;

    public EmployerDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException connectionException) {
            connectionException.printStackTrace();
        }
    }


    @Override
    public boolean addEmployer(Employer employer) {
        try (PreparedStatement addStatement = connection.prepareStatement(
            "INSERT INTO employers (first_name, last_name, email, phone, salary, role, poste) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            addStatement.setString(1, employer.getFirstName());
            addStatement.setString(2, employer.getLastName());
            addStatement.setString(3, employer.getEmail());
            addStatement.setInt(4, employer.getPhoneNumber());
            addStatement.setDouble(5, employer.getSalary());
            addStatement.setString(6, employer.getRole().name());
            addStatement.setString(7, employer.getPoste().name());

            return addStatement.executeUpdate() > 0;

        } catch (SQLException addException) {
            addException.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateEmployer(Employer employer) {
        try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE employers SET first_name = ?, last_name = ?, email = ?, phone = ?, salary = ?, role = ?, poste = ? WHERE id = ?")) {

            updateStatement.setString(1, employer.getFirstName());
            updateStatement.setString(2, employer.getLastName());
            updateStatement.setString(3, employer.getEmail());
            updateStatement.setInt(4, employer.getPhoneNumber());
            updateStatement.setDouble(5, employer.getSalary());
            updateStatement.setString(6, employer.getRole().name());
            updateStatement.setString(7, employer.getPoste().name());
            updateStatement.setInt(8, employer.getId());

            return updateStatement.executeUpdate() > 0;

        } catch (SQLException updateException) {
            updateException.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmployer(int id) {
        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM employers WHERE id = ?")) {

            deleteStatement.setInt(1, id);
            return deleteStatement.executeUpdate() > 0;

        } catch (SQLException deleteException) {
            return false;
        }
    }

    @Override

    public List<Employer> getAllEmployers() {
        List<Employer> employers = new ArrayList<>();
        try (ResultSet getResult = connection.prepareStatement("SELECT * FROM employers").executeQuery()) {

            while (getResult.next()) {
                employers.add(new Employer(
                    getResult.getInt("id"), 
                    getResult.getString("first_name"), 
                    getResult.getString("last_name"), 
                    getResult.getString("email"), 
                    getResult.getInt("phone"), 
                    getResult.getDouble("salary"), 
                    Role.valueOf(getResult.getString("role")), 
                    Poste.valueOf(getResult.getString("poste"))
                ));
            }

        } catch (SQLException getException) {
            getException.printStackTrace();
        }
        return employers;
    }

    @Override
    public int isValidPassword(String username, String password) {
        try (PreparedStatement loginStatement = connection.prepareStatement("SELECT * FROM login WHERE username = ?")) {
            loginStatement.setString(1, username);

            ResultSet getResult = loginStatement.executeQuery();
            if (getResult.next() && getResult.getString("password").equals(hashPassword(password))) { 
                return getResult.getInt("employee_id");
            }
            return -1;
        }catch (SQLException getException) {
            getException.printStackTrace();
            return -1;
        }
    }

    public boolean isAdmin(int employer_id) {
        try (PreparedStatement loginStatement = connection.prepareStatement("SELECT * FROM employers WHERE id = ?")) {
            loginStatement.setString(1, String.valueOf(employer_id));

            ResultSet getResult = loginStatement.executeQuery();
            if (getResult.next()) { 
                return getResult.getString("role").equals("MANAGER");
            }
            return false;
        }catch (SQLException getException) {
            getException.printStackTrace();
            return false;
        }
    }

    public boolean createLogin(int employee_id, String username, String password) {
        try (PreparedStatement createLoginStatement = connection.prepareStatement("INSERT INTO login (username, password, employee_id) VALUES (?, ?, ?)")) {
            createLoginStatement.setString(1, username);
            createLoginStatement.setString(2, hashPassword(password));
            createLoginStatement.setString(3, String.valueOf(employee_id));

            return createLoginStatement.executeUpdate() > 0;
        }catch (SQLException getException) {
            getException.printStackTrace();
            return false;
        }
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

}
