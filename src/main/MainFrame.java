package main;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    public static JFrame frame = new JFrame();

    MainFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(false);

    }
}
