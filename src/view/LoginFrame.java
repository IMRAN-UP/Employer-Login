package view;
import javax.swing.*;
import java.awt.*;
import dao.*;


public class LoginFrame extends JFrame {
    private JTextField usernameField, passwordField;
    private JButton loginButton, signupButton;

    public LoginFrame() {

        setTitle("Person Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    
        usernameField = new JTextField(25);
        passwordField = new JPasswordField(25);

        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2, 1, 5, 5));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);

        btnPanel.add(loginButton);
        btnPanel.add(signupButton);

        add(inputPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}