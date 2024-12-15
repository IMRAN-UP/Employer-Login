package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HoliBtnPanel extends JPanel {

    private JButton addBtn, removeBtn, updateBtn;

    public HoliBtnPanel() {
        setLayout(new FlowLayout());

        addBtn = new JButton("Add");
        removeBtn = new JButton("Remove");
        updateBtn = new JButton("Update");
        addBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        updateBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        removeBtn.setFont(new Font("Arial", Font.PLAIN, 14));

        addButtonHoverEffect(addBtn);
        addButtonHoverEffect(removeBtn);
        addButtonHoverEffect(updateBtn);

        add(addBtn);
        add(removeBtn);
        add(updateBtn);
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

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getRemoveBtn() {
        return removeBtn;
    }

    public JButton getUpdateBtn() {
        return updateBtn;
    }
}
