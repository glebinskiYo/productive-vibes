package main;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StatsPage implements ActionListener {
    JPanel statsPagePanel = new JPanel();
    JButton refreshButton = new JButton("UPDATE");
    DatePicker datePicker = new DatePicker();
    SoundPlayer soundPlayer = new SoundPlayer();
    JLabel todayText = new JLabel("TODAY: " + SaveStats.getStatsString(SaveStats.stats.get(SaveStats.currentDay) == null ? 0 : SaveStats.stats.get(SaveStats.currentDay)));
    JLabel lastWeekText = new JLabel("LAST WEEK: " + SaveStats.getStatsString(SaveStats.getWeeklyStats()));
    JLabel dayText = new JLabel("DAY: ");
    JLabel dayValue = new JLabel();
    JButton backButton = new JButton("BACK");


 

    StatsPage() {


        statsPagePanel.setOpaque(false);
        statsPagePanel.setSize(420, 420);
        statsPagePanel.setLayout(null);
        statsPagePanel.setVisible(true);


        datePicker.setDate(LocalDate.now());
        datePicker.setBounds(20, 180, 200, 40);
        datePicker.setVisible(true);
        todayText.setBounds(20, 20, 250, 50);
        todayText.setFont(new Font("Segoe Print", Font.BOLD, 25));
        todayText.setForeground(Color.WHITE);


        if (datePicker.getComponentDateTextField() == null) {
            dayValue.setText("0 min");
        } else {
            DateValue.dateValue = SaveStats.findValue(datePicker.getDate().format(DateTimeFormatter.ofPattern("uuuu-MM-dd")));
            dayValue.setText(("" + SaveStats.getStatsString(DateValue.dateValue)));
        }


        lastWeekText.setBounds(20, 70, 400, 50);
        lastWeekText.setFont(new Font("Segoe Print", Font.BOLD, 25));
        lastWeekText.setForeground(Color.WHITE);

        backButton.setBounds(155, 320, 95, 40);
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(true);
        backButton.setFont(new Font("Segoe Print", Font.BOLD, 16));
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        backButton.setHorizontalTextPosition(SwingConstants.CENTER);


        refreshButton.setBounds(260, 180, 105, 40);
        refreshButton.setFont(new Font("Segoe Print", Font.BOLD, 16));
        refreshButton.setBackground(Color.DARK_GRAY);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setOpaque(true);
        refreshButton.setFocusable(false);
        refreshButton.addActionListener(this);

        refreshButton.setHorizontalTextPosition(SwingConstants.CENTER);




        dayText.setBounds(20, 120, 100, 50);
        dayText.setFont(new Font("Segoe Print", Font.BOLD, 25));
        dayText.setForeground(Color.WHITE);

        dayValue.setBounds(95, 120, 400, 50);
        dayValue.setFont(new Font("Segoe Print", Font.BOLD, 25));
        dayValue.setForeground(Color.WHITE);
        statsPagePanel.add(refreshButton);
        statsPagePanel.add(dayValue);
        statsPagePanel.add(todayText);
        statsPagePanel.add(datePicker);
        statsPagePanel.add(lastWeekText);
        statsPagePanel.add(dayText);
        statsPagePanel.add(backButton);

        statsPagePanel.setVisible(true);

        MainFrame.frame.setContentPane(new JLabel());
        MainFrame.frame.setBackground(Color.DARK_GRAY);
        MainFrame.frame.add(statsPagePanel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            soundPlayer.playSound(SoundPlayer.resetAndSetSound);
            statsPagePanel.setVisible(false);
            new TimerPage();
        }

        if (e.getSource() == refreshButton) {
            if (datePicker.getComponentDateTextField() == null) {
                dayValue.setText("0 min");
            } else {
                DateValue.dateValue = SaveStats.findValue(datePicker.getDate().format(DateTimeFormatter.ofPattern("uuuu-MM-dd")));
                dayValue.setText(("" + SaveStats.getStatsString(DateValue.dateValue)));
            }

        }
    }


    public class DateValue {
        public static long dateValue;

    }


}
