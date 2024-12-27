package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BtnPanel extends JPanel {

    private List<JButton> buttons;

    public BtnPanel(String[] btnLabels) {
        setLayout(new FlowLayout());
        buttons = new ArrayList<>();

        for (String label : btnLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            addButtonHoverEffect(button);
            buttons.add(button);
            add(button);
        }
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

    public JButton getButtonByLabel(String label) {
        for (JButton button : buttons) {
            if (button.getText().equals(label)) {
                return button;
            }
        }
        return null;
    }
}
