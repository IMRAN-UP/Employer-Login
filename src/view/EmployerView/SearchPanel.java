package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchPanel extends JPanel {

    private JTextField searchField;
    private JButton searchButton;

    public SearchPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(300, 30));
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBackground(new Color(245, 245, 245));
        searchField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 14));
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setBackground(new Color(173, 216, 230)); 
        searchButton.setFocusPainted(false);  
        searchButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        addButtonHoverEffect(searchButton);
        add(searchButton);
    }

    private void addButtonHoverEffect(JButton button) {
        button.setBackground(new Color(238, 238, 238));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(173, 216, 230)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(238, 238, 238)); 
            }
        });
    }

    // Getters for the components
    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
