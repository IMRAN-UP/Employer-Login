package controller;

import view.*;
import dao.*;
import model.*;
import enums.*;
import java.util.List;
import javax.swing.JOptionPane;

public class EmployerController {

    private FormFrame frame;
    private LoginFrame loginFrame;
    private EmployerLogic employerLogic;
    private CreateFrame createFrame;

    public EmployerController(FormFrame frame, EmployerLogic employerLogic) {
        this.loginFrame = new LoginFrame();
        this.employerLogic = employerLogic;
        this.frame = frame;
        this.createFrame = new CreateFrame();

        frame.getEmployerPanel().getBtnPanel().getAddBtn().addActionListener(addEvent -> addEmployer());
        frame.getEmployerPanel().getBtnPanel().getUpdateBtn().addActionListener(updateEvent -> updateEmployer());
        frame.getEmployerPanel().getBtnPanel().getRemoveBtn().addActionListener(deleteEvent -> deleteEmployer());
        frame.getEmployerPanel().getBtnPanel().getCreateBtn().addActionListener(createEvent -> createEmployer());
        createFrame.getCreateBtn().addActionListener(createEvent -> createLogin());
        loginFrame.getLoginButton().addActionListener(addEvent -> isValidLogin());

        loadEmployers();
    }

    private void addEmployer() {
        try {
            
            if (employerLogic.addEmployer(
                    1,
                    frame.getEmployerPanel().getInPanel().getFirstNameField().getText(),
                    frame.getEmployerPanel().getInPanel().getLastNameField().getText(),
                    frame.getEmployerPanel().getInPanel().getEmailField().getText(),
                    Integer.parseInt(frame.getEmployerPanel().getInPanel().getTelephoneNumberField().getText()),
                    Double.parseDouble(frame.getEmployerPanel().getInPanel().getSalaryField().getText()),
                    Role.valueOf(frame.getEmployerPanel().getInPanel().getSelectedRole().toString()),
                    Poste.valueOf(frame.getEmployerPanel().getInPanel().getSelectedPoste().toString())
                ))    
            {
                JOptionPane.showMessageDialog(frame, "Employer added successfully.");
                loadEmployers();
                frame.getEmployerPanel().getInPanel().clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add employer", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to add employer", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployer() {
     try {
         if (employerLogic.updateEmployer(
                 frame.getEmployerPanel().getListPanel().getSelectedRowId(),
                 frame.getEmployerPanel().getInPanel().getFirstNameField().getText(),
                 frame.getEmployerPanel().getInPanel().getLastNameField().getText(),
                 frame.getEmployerPanel().getInPanel().getEmailField().getText(),
                 Integer.parseInt(frame.getEmployerPanel().getInPanel().getTelephoneNumberField().getText()),
                 Double.parseDouble(frame.getEmployerPanel().getInPanel().getSalaryField().getText()),
                 Role.valueOf(frame.getEmployerPanel().getInPanel().getSelectedRole().toString()),
                 Poste.valueOf(frame.getEmployerPanel().getInPanel().getSelectedPoste().toString())
            )) 
         {
            JOptionPane.showMessageDialog(frame, "Employer updated successfully.");
            loadEmployers();
            frame.getEmployerPanel().getInPanel().clearFields();
         } else {
            JOptionPane.showMessageDialog(null, "Failed to update employer", "Error", JOptionPane.ERROR_MESSAGE);
         }
     } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failed to update employer", "Error", JOptionPane.ERROR_MESSAGE);
     }
    }

    private void deleteEmployer() {
     try {
         if (employerLogic.deleteEmployer(frame.getEmployerPanel().getListPanel().getSelectedRowId())) {
            JOptionPane.showMessageDialog(frame, "Employer deleted successfully.");
            loadEmployers();
            frame.getEmployerPanel().getInPanel().clearFields();
         } else {
            JOptionPane.showMessageDialog(null, "Failed to delete employer", "Error", JOptionPane.ERROR_MESSAGE);
         }
     } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failed to delete employer", "Error", JOptionPane.ERROR_MESSAGE);
     }
    }

    private void loadEmployers() {
        frame.getEmployerPanel().getListPanel().updateEmployerList(employerLogic.getAllEmployers());
    }

    private void isValidLogin() {
        if(employerLogic.handleLogin(loginFrame.getUsername(), loginFrame.getPassword())) {
            loginFrame.setVisible(false);
            frame.setVisible(true);
            if (!employerLogic.handleAccess(loginFrame.getUsername())){
                System.out.println("This is Employer");
            }
            return;
        }else {
            JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createEmployer() {
        if (frame.getEmployerPanel().getListPanel().getSelectedRowId() != -1) {
            createFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Failed Create Login Please select a row ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createLogin () {
        if( employerLogic.createLogin(frame.getEmployerPanel().getListPanel().getSelectedRowId(), createFrame.getUsername(), createFrame.getPassword(), createFrame.getConfirmPassword()) ) {
            JOptionPane.showMessageDialog(frame, "Login created successfully.");
            createFrame.clearFields();
            createFrame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Failed Create Login", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}