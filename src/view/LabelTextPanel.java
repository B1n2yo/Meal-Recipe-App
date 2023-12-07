package view;

import javax.swing.*;

public class LabelTextPanel extends JPanel {
    LabelTextPanel(JLabel label, JTextField textField) {
        label.setForeground(new java.awt.Color(222, 247, 250));
        textField.setForeground(new java.awt.Color(222, 247, 250));
        textField.setBackground(new java.awt.Color(23, 32, 46));
        this.add(label);
        this.add(textField);
    }
}
