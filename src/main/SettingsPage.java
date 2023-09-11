package main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPage implements ChangeListener, ActionListener {
    public JPanel settingsPagePanel = new JPanel();
    JButton applyButton = new JButton("APPLY");
    JButton backButton = new JButton("BACK");

    JButton resetButton = new JButton("RESET");
    SoundPlayer soundPlayer = new SoundPlayer();
    JLabel pic = new JLabel();
    JLabel audioIconLabel = new JLabel();
    JLabel breakIconLabel = new JLabel();
    JPanel volumePanel;
    JLabel volumeLabel;
    JSlider volumeSlider;
    static int volumeValue = 100;
    static int breakTimeValue = 30;
    JPanel breakPanel = new JPanel();
    JLabel breakLabel = new JLabel();
    static JSlider breakSlider = new JSlider();


    public static int breakTime() {
        return breakTimeValue;
    }

    public SettingsPage() {


        audioIconLabel.setBounds(25, 200, 40, 40);
        breakIconLabel.setBounds(25, 250, 40, 40);
        adjustIcon(audioIconLabel, new ImageIcon(SettingsPage.class.getResource("/resources/Icons/air-horn.png")));
        adjustIcon(breakIconLabel, new ImageIcon(SettingsPage.class.getResource("/resources/Icons/breakIcon.png")));
        pic.setBounds(30, 30, 350, 150);
        pic.setIcon(new ImageIcon(SettingsPage.class.getResource("/resources/Background/settings.gif")));


        settingsPagePanel.setOpaque(false);
        settingsPagePanel.setSize(420, 420);
        settingsPagePanel.setLayout(null);
        settingsPagePanel.setVisible(true);

        volumePanel = new JPanel();
        volumeLabel = new JLabel();
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, volumeValue);
        volumeSlider.setPreferredSize(new Dimension(200, 100));
        volumeSlider.setBackground(Color.DARK_GRAY);
        volumeSlider.setForeground(Color.WHITE);

        volumeSlider.setPaintTicks(true);
        volumeSlider.setMinorTickSpacing(10);
        volumeSlider.setPaintTrack(true);
        volumeSlider.setMajorTickSpacing(25);

        volumeSlider.setPaintLabels(true);
        volumeSlider.setFont(new Font("Segoe Print", Font.BOLD, 12));

        volumeLabel.setBounds(40, 40, 60, 60);
        volumePanel.setBounds(70, 195, 300, 60);
        volumePanel.setOpaque(false);
        volumePanel.setBackground(Color.BLACK);
        volumePanel.setForeground(Color.BLACK);
        volumePanel.setLayout(new BoxLayout(volumePanel, BoxLayout.PAGE_AXIS));
        volumePanel.add(volumeSlider);
        volumePanel.add(volumeLabel);
        volumePanel.setOpaque(false);

        breakSlider = new JSlider(JSlider.HORIZONTAL, 0, 30, breakTimeValue);
        breakSlider.setBackground(Color.DARK_GRAY);
        breakSlider.setForeground(Color.WHITE);
        breakSlider.setPreferredSize(new Dimension(200, 100));
        breakSlider.setPaintTicks(true);
        breakSlider.setMinorTickSpacing(1);
        breakSlider.setPaintTrack(true);
        breakSlider.setMajorTickSpacing(15);

        breakSlider.setPaintLabels(true);
        breakSlider.setFont(new Font("Segoe Print", Font.BOLD, 12));


        breakLabel.setBounds(40, 40, 60, 60);
        breakPanel.setBounds(70, 255, 300, 60);
        breakPanel.setOpaque(false);
        breakPanel.setBackground(Color.DARK_GRAY);
        breakPanel.setForeground(Color.WHITE);
        breakPanel.setLayout(new BoxLayout(breakPanel, BoxLayout.PAGE_AXIS));
        breakPanel.add(breakSlider);
        breakPanel.add(breakLabel);
        breakPanel.setOpaque(false);

        applyButton.setBounds(70, 320, 85, 40);
        applyButton.setBackground(Color.DARK_GRAY);
        applyButton.setForeground(Color.WHITE);
        applyButton.setFont(new Font("Segoe Print", Font.BOLD, 14));
        applyButton.setFocusable(false);
        applyButton.addActionListener(this);

        applyButton.setHorizontalTextPosition(SwingConstants.CENTER);

        backButton.setBounds(160, 320, 85, 40);
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe Print", Font.BOLD, 14));
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        backButton.setHorizontalTextPosition(SwingConstants.CENTER);

        resetButton.setBounds(250, 320, 85, 40);
        resetButton.setBackground(Color.DARK_GRAY);
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(new Font("Segoe Print", Font.BOLD, 14));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        resetButton.setHorizontalTextPosition(SwingConstants.CENTER);


        settingsPagePanel.add(volumePanel);
        settingsPagePanel.add(breakPanel);
        settingsPagePanel.add(resetButton);
//        frame.pack();
//        frame.setLayout(null);
//        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);

        settingsPagePanel.add(pic);
        settingsPagePanel.add(audioIconLabel);
        settingsPagePanel.add(breakIconLabel);
        settingsPagePanel.add(applyButton);
        settingsPagePanel.add(backButton);

        MainFrame.frame.setContentPane(new JLabel());
        MainFrame.frame.setBackground(Color.DARK_GRAY);
        MainFrame.frame.add(settingsPagePanel);





    }

    public static void adjustIcon(JLabel label, ImageIcon myImage) {
        Image newImage = myImage.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_AREA_AVERAGING);
        ImageIcon newIcon = new ImageIcon(newImage);
        label.setIcon(newIcon);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            soundPlayer.playSound(SoundPlayer.resetAndSetSound);
            settingsPagePanel.setVisible(false);
            new TimerPage();
        }
        if (e.getSource() == applyButton) {
            breakTimeValue = breakSlider.getValue();
            breakSlider = new JSlider(JSlider.HORIZONTAL, 0, 30, breakTimeValue);

            volumeValue = volumeSlider.getValue();
            volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, volumeValue);

            SoundPlayer.volume = ((float) volumeValue / 100);

            Config.saveConfig();
            soundPlayer.playSound(SoundPlayer.resetAndSetSound);
            settingsPagePanel.setVisible(false);
            new TimerPage();
        }

        if (e.getSource() == resetButton) {
            volumeValue = 100;
            breakTimeValue = 30;
            breakSlider = new JSlider(JSlider.HORIZONTAL, 0, 30, 30);
            volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
            Config.saveConfig();
            SoundPlayer.volume = ((float) volumeValue / 100);
            settingsPagePanel.setVisible(false);
//            frame.dispose();
            new SettingsPage();


        }

    }
}
