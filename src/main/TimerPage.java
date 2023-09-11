package main;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;

public class TimerPage implements ActionListener {
    SoundPlayer soundPlayer = new SoundPlayer();
    public JPanel timerPagePanel = new JPanel();
    static Config config = new Config();
    JButton settingsButton = new JButton();


    JButton startButton = new JButton(RemainingTime.elapsedTime % 300000 == 0 ? "START" : "RESUME");
    JButton setTimerButton = new JButton("SET");
    JButton resetTimerButton = new JButton("RESET");
    JButton statsButton = new JButton("STATS");
    JLabel timeLabel = new JLabel();



    String[] setOptions = {"5 min", "10 min",
            "15 min", "20 min", "25 min", "30 min", "35 min", "40 min", "45 min", "50 min", "55 min", "60 min", "65 min", "70 min", "75 min", "80 min", "85 min", "90 min"};
    JList<String> optionList = new JList<>(setOptions);
    int seconds = RemainingTime.elapsedTime == 0 ? 0 : (int) ((RemainingTime.elapsedTime / 1000) % 60);
    int minutes = Math.toIntExact(RemainingTime.elapsedTime == 0 ? 0 : (RemainingTime.elapsedTime / 60000) % 60);
    int hours = RemainingTime.elapsedTime == 0 ? 0 : (int) (RemainingTime.elapsedTime / 3600000);
    boolean started = false;

    String seconds_string =
            String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, e -> {

        RemainingTime.elapsedTime -= 1000;
        hours = Math.toIntExact((RemainingTime.elapsedTime / 3600000));
        minutes = (int) ((RemainingTime.elapsedTime / 60000) % 60);
        seconds = (int) ((RemainingTime.elapsedTime / 1000) % 60);
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        if (RemainingTime.elapsedTime <= 0) {
            stop();
            soundPlayer.openAlarm(SoundPlayer.alarmSound.getPath());
            soundPlayer.playAlarm();
        }
        if (RemainingTime.elapsedTime == 0) {
            SaveStats.saveStats();
            timerPagePanel.setVisible(false);
            new FinishedPage(this);
            startButton.setText("START");
        }
    });


    TimerPage() {

        UIManager.put("Button.background", Color.DARK_GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Segoe Print", Font.BOLD, 15));
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.DARK_GRAY);
        SaveStats.loadStats();

        timerPagePanel.setOpaque(false);
        timerPagePanel.setSize(420, 420);
        timerPagePanel.setLayout(null);


//        Image scaledImage = originalImage.getScaledInstance(jPanel.getWidth(),jPanel.getHeight(),Image.SCALE_SMOOTH);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(105, 100, 200, 80);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setVerticalAlignment(JLabel.CENTER);
        timeLabel.setFont(new Font("Segoe Print", Font.PLAIN, 32));




//        timeLabel.setBorder(BorderFactory.createBevelBorder(1));

        timeLabel.setOpaque(true);


        timeLabel.setBackground(Color.DARK_GRAY);
        timeLabel.setForeground(Color.WHITE);



        startButton.setBounds(102, 200, 99, 50);
        if (startButton.getText().equals("RESUME")) {
            startButton.setFont(new Font("Segoe Print", Font.BOLD, 14));
        } else {
            startButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
        }

        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.WHITE);
        startButton.setFocusable(false);
        startButton.addActionListener(this);



        settingsButton.setBounds(360, 337, 38, 39);
        this.settingsButton.setIcon(new ImageIcon((new ImageIcon(TimerPage.class.getResource("/resources/Icons/icons8-einstellungen-67.png"))).getImage().getScaledInstance(40, 40, 1)));
        settingsButton.addActionListener(this);
        settingsButton.setFocusPainted(false);

        settingsButton.setBorderPainted(true);
        settingsButton.setContentAreaFilled(false);

        resetTimerButton.setBounds(3, 200, 99, 50);
        resetTimerButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
        resetTimerButton.setBackground(Color.DARK_GRAY);
        resetTimerButton.setForeground(Color.WHITE);
        resetTimerButton.setOpaque(true);
        resetTimerButton.setFocusable(false);
        resetTimerButton.addActionListener(this);

        resetTimerButton.setHorizontalTextPosition(SwingConstants.CENTER);

        setTimerButton.setBounds(202, 200, 99, 50);
        setTimerButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
        setTimerButton.setBackground(Color.DARK_GRAY);
        setTimerButton.setForeground(Color.WHITE);
        setTimerButton.setFocusable(false);
        setTimerButton.addActionListener(this);

        setTimerButton.setHorizontalTextPosition(SwingConstants.CENTER);

        statsButton.setBounds(302, 200, 99, 50);
        statsButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
        statsButton.setBackground(Color.DARK_GRAY);
        statsButton.setForeground(Color.WHITE);
        statsButton.setFocusable(false);
        statsButton.addActionListener(this);
        statsButton.setHorizontalTextPosition(SwingConstants.CENTER);


        optionList.setBackground(Color.DARK_GRAY);
        optionList.setForeground(Color.WHITE);
        optionList.setFont(new Font("Segoe Print", Font.BOLD, 15));
        optionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);





        timerPagePanel.add(timeLabel, BorderLayout.CENTER);
        timerPagePanel.add(resetTimerButton);
        timerPagePanel.add(startButton);
        timerPagePanel.add(setTimerButton);
        timerPagePanel.add(statsButton);
        timerPagePanel.add(settingsButton);

        timerPagePanel.setVisible(true);

        MainFrame.frame.setContentPane(new JLabel(
                (new ImageIcon(TimerPage.class.getResource("/resources/Background/timerpagebackground.gif")))));
        MainFrame.frame.add(timerPagePanel);
        MainFrame.frame.setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
            if (startButton.getText().equals("STOP")) {
                soundPlayer.playSound(SoundPlayer.resetAndSetSound);
            } else {
                soundPlayer.playSound(SoundPlayer.startSound);
            }
            if (RemainingTime.elapsedTime == 0) {
                e.setSource(setTimerButton);
            } else {
                if (!started) {
                    started = true;
                    start();
                    startButton.setText("STOP");
                } else {
                    started = false;
                    startButton.setText("START");
                    stop();
                    if (RemainingTime.elapsedTime != 0) {
                        startButton.setText("RESUME");
                        startButton.setFont(new Font("Segoe Print", Font.BOLD, 14));
                    }
                }
            }
            soundPlayer.closeAlarm();
        }
        if (e.getSource() == resetTimerButton) {
            soundPlayer.playSound(SoundPlayer.resetAndSetSound);
            TimerValue.timerValue = 0;
            started = false;
            startButton.setText("START");
            reset();
        }
        if (e.getSource() == setTimerButton) {
            soundPlayer.playSound(SoundPlayer.resetAndSetSound);
            stop();
            started = false;
            startButton.setText("START");
            JButton okButton = new JButton("OK");
            okButton.setBackground(Color.DARK_GRAY);
            okButton.setForeground(Color.WHITE);
            okButton.setFont(new Font("MV Boli", Font.BOLD, 14));
            UIManager.getLookAndFeelDefaults().put( "ScrollBar.track", Color.DARK_GRAY );
            UIManager.getLookAndFeelDefaults().put( "ScrollBar.thumb", Color.DARK_GRAY );
            UIManager.getLookAndFeelDefaults().put( "ScrollBar.thumbDarkShadow", Color.DARK_GRAY );
            UIManager.getLookAndFeelDefaults().put( "ScrollBar.thumbShadow", Color.DARK_GRAY );
            UIManager.getLookAndFeelDefaults().put( "ScrollBar.thumbHighlight", Color.DARK_GRAY );
            okButton.addActionListener(e1 -> JOptionPane.getRootFrame().dispose());
            JScrollPane scrollPane = new JScrollPane(optionList);
            scrollPane.setBorder(null);
            JOptionPane jOptionPane = new JOptionPane();
            JOptionPane.showMessageDialog(null, scrollPane, "Select an Option", JOptionPane.PLAIN_MESSAGE);


            setTimer();
            Config.saveConfig();
        }
        if (e.getSource() == settingsButton) {
            stop();

            startButton.setText("RESUME");
            soundPlayer.playSound(SoundPlayer.resetAndSetSound);
            timerPagePanel.setVisible(false);
            new SettingsPage();
        }
        if (e.getSource() == statsButton) {
            stop();
            startButton.setText("RESUME");
            soundPlayer.playSound(SoundPlayer.resetAndSetSound);
            timerPagePanel.setVisible(false);
            new StatsPage();


        }
    }

    void start() {
        timer.start();
    }

    void setTimer() {
        reset();
        if (optionList.getSelectedValue() != null) {
            RemainingTime.elapsedTime = Integer.parseInt(optionList.getSelectedValue().replaceAll("[^0-9]", "")) * 60000L;

        }
        TimerValue.timerValue = (int) RemainingTime.elapsedTime;
        hours = (int) (RemainingTime.elapsedTime / 3600000);
        minutes = (int) ((RemainingTime.elapsedTime / 60000) % 60);
        seconds = (int) ((RemainingTime.elapsedTime / 1000) % 60);
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();
        RemainingTime.elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        Config.saveConfig();
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timer.stop();
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }

    public class TimerValue {
        public static long timerValue;
    }
    public class RemainingTime {
        public static long elapsedTime = TimerValue.timerValue;
    }


}


