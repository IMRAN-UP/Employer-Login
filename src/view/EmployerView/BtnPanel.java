package view;

import javax.swing.*;
import java.awt.*;

public class BtnPanel extends JPanel {

    private JButton addBtn, removeBtn , updateBtn, createBtn;

    public BtnPanel() {
        setLayout(new FlowLayout());

        addBtn = new JButton("Add");
        removeBtn = new JButton("Remove");
        updateBtn = new JButton("Update");
        createBtn = new JButton("Create");

        add(addBtn);
        add(removeBtn);
        add(updateBtn);
        add(createBtn);
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

    public JButton getCreateBtn() {
        return createBtn;
    }
}
