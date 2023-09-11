package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ChillPage implements ActionListener {
    JLabel timeLabel = new JLabel();

    JPanel chillPagePanel = new JPanel();
    JButton stopButton = new JButton("STOP");
    JButton backToWorkButton = new JButton("BACK");
    SoundPlayer soundPlayer = new SoundPlayer();
    int elapsedfTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started;
    String seconds_string =
            String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, e -> {

           elapsedfTime -= 1000;
           hours = (elapsedfTime / 3600000);
           minutes = (elapsedfTime / 60000) % 60;
           seconds = (elapsedfTime / 1000) % 60;
           seconds_string = String.format("%02d", seconds);
           minutes_string = String.format("%02d", minutes);
           hours_string = String.format("%02d", hours);
           timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);

        if (elapsedfTime == 0) {
            chillPagePanel.setVisible(false);
            new TimerPage();
        }
    });

    ChillPage() {
        elapsedfTime += SettingsPage.breakTime() * 60000;
        started = true;
        start();
        chillPagePanel.setOpaque(false);
        chillPagePanel.setSize(420, 420);
        chillPagePanel.setLayout(null);




        stopButton.setBounds(145, 240, 105, 50);
        stopButton.setBackground(Color.DARK_GRAY);
        stopButton.setForeground(Color.WHITE);
        stopButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
        stopButton.setFocusable(false);
        stopButton.addActionListener(this);
        stopButton.setHorizontalTextPosition(SwingConstants.CENTER);

        backToWorkButton.setBounds(145, 290, 105, 50);
        backToWorkButton.setBackground(Color.DARK_GRAY);
        backToWorkButton.setForeground(Color.WHITE);
        backToWorkButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
        backToWorkButton.setFocusable(false);
        backToWorkButton.addActionListener(this);
        backToWorkButton.setHorizontalTextPosition(SwingConstants.CENTER);

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100, 100, 200, 80);
//        timeLabel.setForeground(Color.BLACK);
        timeLabel.setFont(new Font("Segoe Print", Font.BOLD, 32));
        timeLabel.setBorder(null);

        timeLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setBackground(Color.DARK_GRAY);
        timeLabel.setForeground(Color.WHITE);

        chillPagePanel.add(timeLabel);
        chillPagePanel.add(stopButton);
        chillPagePanel.add(backToWorkButton);

        chillPagePanel.setVisible(true);

        MainFrame.frame.setContentPane(new JLabel(new ImageIcon(Objects.requireNonNull(ChillPage.class.getResource("/resources/Background/ok-aesthetic.gif")))));
        MainFrame.frame.add(chillPagePanel);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stopButton) {
            if (stopButton.getText().equals("STOP")) {
                stop();
                soundPlayer.playSound(SoundPlayer.resetAndSetSound);
                stopButton.setText("RESUME");
            } else if (stopButton.getText().equals("RESUME")) {
                soundPlayer.playSound(SoundPlayer.resetAndSetSound);
                start();
                stopButton.setText("STOP");
            }
    }
        if (e.getSource() == backToWorkButton) {
            soundPlayer.playSound(SoundPlayer.resetAndSetSound);
            stop();
            chillPagePanel.setVisible(false);
            new TimerPage();
        }
}
    void stop() {
        timer.stop();
    }


    void start() {
        timer.start();
    }
}
