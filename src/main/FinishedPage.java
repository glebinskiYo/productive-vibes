package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishedPage implements ActionListener {
    TimerPage timerPage;
    JButton chillButton = new JButton("CHILL");
    JButton workButton = new JButton("CONTINUE");
    JPanel finishedPagePanel = new JPanel();
    SoundPlayer soundPlayer = new SoundPlayer();



    public FinishedPage(TimerPage timerPage) {
        new Config();


        this.timerPage = timerPage;

        finishedPagePanel.setOpaque(false);
        finishedPagePanel.setSize(420, 420);
        finishedPagePanel.setLayout(null);
        finishedPagePanel.setVisible(true);


        JLabel label = new JLabel("+" + (TimerPage.TimerValue.timerValue/60000) +" min");

        label.setHorizontalTextPosition(SwingConstants.CENTER);


        chillButton.setBounds(210, 210, 125, 50);
        chillButton.setBackground(Color.DARK_GRAY);
        chillButton.setForeground(Color.WHITE);
        chillButton.setFont(new Font("Segoe Print", Font.BOLD, 16));
        chillButton.setFocusable(false);
        chillButton.addActionListener(this);

        chillButton.setHorizontalTextPosition(SwingConstants.CENTER);

        label.setBounds(140, 100, 100, 30);
        label.setForeground(Color.LIGHT_GRAY);
        label.setBackground(Color.BLACK);

        label.setFont(new Font("Segoe Print", Font.BOLD, 20));

        label.setBorder(BorderFactory.createBevelBorder(1));
        label.setOpaque(true);
        label.setHorizontalAlignment(JTextField.CENTER);

        workButton.setBounds(70, 210, 125, 50);
        workButton.setBackground(Color.DARK_GRAY);
        workButton.setForeground(Color.WHITE);
        workButton.setFont(new Font("Segoe Print", Font.BOLD, 16));
        workButton.setFocusable(false);
        workButton.addActionListener(this);

        workButton.setHorizontalTextPosition(SwingConstants.CENTER);

        finishedPagePanel.add(chillButton);
        finishedPagePanel.add(workButton);
        finishedPagePanel.add(label);

        finishedPagePanel.setVisible(true);


        MainFrame.frame.setContentPane(new JLabel
                (new ImageIcon(FinishedPage.class.getResource("/resources/Background/finishedBackground.gif"))));

        MainFrame.frame.add(finishedPagePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                if (e.getSource() == workButton) {
                    soundPlayer.playSound(SoundPlayer.resetAndSetSound);
                    finishedPagePanel.setVisible(false);
                    timerPage.soundPlayer.closeAlarm();
                    new TimerPage();

                } else if (SettingsPage.breakTimeValue != 0 && e.getSource() == chillButton){
                    soundPlayer.playSound(SoundPlayer.resetAndSetSound);
                    timerPage.soundPlayer.closeAlarm();
                    finishedPagePanel.setVisible(false);
                    new ChillPage();
                } else {
                    finishedPagePanel.setVisible(false);
                    timerPage.soundPlayer.closeAlarm();
                    new TimerPage();
                }
    }
}
