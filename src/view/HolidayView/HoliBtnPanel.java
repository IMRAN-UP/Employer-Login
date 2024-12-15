package view;

import javax.swing.*;
import java.awt.*;

public class HoliBtnPanel extends JPanel {

    private JButton addBtn, removeBtn , updateBtn;

    public HoliBtnPanel() {
        setLayout(new FlowLayout());

        addBtn = new JButton("Add");
        removeBtn = new JButton("Remove");
        updateBtn = new JButton("Update");

        add(addBtn);
        add(removeBtn);
        add(updateBtn);
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
