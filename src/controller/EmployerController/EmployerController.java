package controller;

import view.*;
import dao.*;
import model.*;
import enums.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


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

        frame.getEmployerPanel().getBtnPanel().getButtonByLabel("Add").addActionListener(addEvent -> addEmployer());
        frame.getEmployerPanel().getBtnPanel().getButtonByLabel("Update").addActionListener(updateEvent -> updateEmployer());
        frame.getEmployerPanel().getBtnPanel().getButtonByLabel("Remove").addActionListener(deleteEvent -> deleteEmployer());
        frame.getEmployerPanel().getBtnPanel().getButtonByLabel("Create").addActionListener(createEvent -> createEmployer());
        frame.getEmployerPanel().getBtnPanel().getButtonByLabel("Load").addActionListener(createEvent -> loadEmployers());
        frame.getEmployerPanel().getBtnPanel().getButtonByLabel("Import").addActionListener(importEvent -> importData());
        frame.getEmployerPanel().getBtnPanel().getButtonByLabel("Export").addActionListener(exportEvent -> exportData());
        frame.getEmployerPanel().getSearchPanel().getSearchButton().addActionListener(findByNameEvent -> findEmpoyerByName());
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
                frame.handleAccess();
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

    private void findEmpoyerByName() {
        frame.getEmployerPanel().getListPanel().updateEmployerList(employerLogic.getEmployerByName(frame.getEmployerPanel().getSearchPanel().getSearchField().getText()));
    }

    private void importData () {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an XML file to import");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try{
                JOptionPane.showMessageDialog(frame, employerLogic.importData(selectedFile) + " Data imported successfully.");
            }catch (IOException e){
                JOptionPane.showMessageDialog(null, "Failed to import data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        loadEmployers();
    }

    private void exportData () {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an XML file to import");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(frame," Data exported successfully.");
            try{
                employerLogic.exportData(selectedFile);
            }catch (IOException e){
                JOptionPane.showMessageDialog(null, "Failed to import data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        loadEmployers();
    }
}