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

    public EmployerController(LoginFrame loginFrame, EmployerLogic employerLogic) {
        this.employerLogic = employerLogic;
        this.loginFrame = loginFrame;
        this.frame = new FormFrame();
        this.createFrame = new CreateFrame();

        frame.getBtnPanel().getAddBtn().addActionListener(addEvent -> addEmployer());
        frame.getBtnPanel().getUpdateBtn().addActionListener(updateEvent -> updateEmployer());
        frame.getBtnPanel().getRemoveBtn().addActionListener(deleteEvent -> deleteEmployer());
        frame.getBtnPanel().getCreateBtn().addActionListener(createEvent -> createEmployer());
        createFrame.getCreateBtn().addActionListener(createEvent -> createLogin());

        loginFrame.getLoginButton().addActionListener(addEvent -> isValidLogin());
        loadEmployers();
    }

    private void addEmployer() {
        try {
            
            if (employerLogic.addEmployer(
                    1,
                    frame.getInPanel().getFirstNameField().getText(),
                    frame.getInPanel().getLastNameField().getText(),
                    frame.getInPanel().getEmailField().getText(),
                    Integer.parseInt(frame.getInPanel().getTelephoneNumberField().getText()),
                    Double.parseDouble(frame.getInPanel().getSalaryField().getText()),
                    Role.valueOf(frame.getInPanel().getSelectedRole().toString()),
                    Poste.valueOf(frame.getInPanel().getSelectedPoste().toString())
                ))    
            {
                JOptionPane.showMessageDialog(frame, "Employer added successfully.");
                loadEmployers();
                frame.getInPanel().clearFields();
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
                 frame.getListPanel().getSelectedRowId(),
                 frame.getInPanel().getFirstNameField().getText(),
                 frame.getInPanel().getLastNameField().getText(),
                 frame.getInPanel().getEmailField().getText(),
                 Integer.parseInt(frame.getInPanel().getTelephoneNumberField().getText()),
                 Double.parseDouble(frame.getInPanel().getSalaryField().getText()),
                 Role.valueOf(frame.getInPanel().getSelectedRole().toString()),
                 Poste.valueOf(frame.getInPanel().getSelectedPoste().toString())
            )) 
         {
            JOptionPane.showMessageDialog(frame, "Employer updated successfully.");
            loadEmployers();
            frame.getInPanel().clearFields();
         } else {
            JOptionPane.showMessageDialog(null, "Failed to update employer", "Error", JOptionPane.ERROR_MESSAGE);
         }
     } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failed to update employer", "Error", JOptionPane.ERROR_MESSAGE);
     }
    }

    private void deleteEmployer() {
     try {
         if (employerLogic.deleteEmployer(frame.getListPanel().getSelectedRowId())) {
            JOptionPane.showMessageDialog(frame, "Employer deleted successfully.");
            loadEmployers();
            frame.getInPanel().clearFields();
         } else {
            JOptionPane.showMessageDialog(null, "Failed to delete employer", "Error", JOptionPane.ERROR_MESSAGE);
         }
     } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failed to delete employer", "Error", JOptionPane.ERROR_MESSAGE);
     }
    }

    private void loadEmployers() {
        frame.getListPanel().updateEmployerList(employerLogic.getAllEmployers());
    }

    private void isValidLogin() {
        int loginId = employerLogic.isValidLogin(loginFrame.getUsername(), loginFrame.getPassword());
        if( loginId > 0) {
            loginFrame.setVisible(false);
            frame.setVisible(true);
            if (!employerLogic.isAdmin(loginId)){
                frame.handleAdmin();
            }
            return;
        }else {
            JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createEmployer() {
        if (frame.getListPanel().getSelectedRowId() != -1) {
            createFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Failed Create Login Please select a row ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void createLogin () {
        if( employerLogic.createLogin(frame.getListPanel().getSelectedRowId(), createFrame.getUsername(), createFrame.getPassword(), createFrame.getConfirmPassword()) ) {
            JOptionPane.showMessageDialog(frame, "Login created successfully.");
            createFrame.clearFields();
            createFrame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Failed Create Login", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
