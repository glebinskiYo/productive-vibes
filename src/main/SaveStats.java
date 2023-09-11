package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SaveStats {

    private static Path saveData = Config.findPath("stats.txt");
    public static HashMap<String, Long> stats = new LinkedHashMap<>();
   static ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());

    static String currentDay = zdt.toString().substring(0, 10);

    public static void saveStats() {
        long newValue = !stats.containsKey(currentDay)
                ? TimerPage.TimerValue.timerValue : stats.get(currentDay) + TimerPage.TimerValue.timerValue;
        stats.put(currentDay, newValue);
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(saveData.toFile()));
            bw.write("");
            bw = new BufferedWriter(new FileWriter(saveData.toFile(), true));
            for (Map.Entry<String, Long> entry : stats.entrySet()) {
                bw.write(entry.getKey() + ", " + entry.getValue());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadStats() {
        if (!saveData.toFile().isFile()) {
            try {
                Files.createFile(saveData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(saveData.toFile()));
            String line;
            String date;
            String millis;
            while ((line = br.readLine()) != null) {
                date = line.split(", ")[0];
                millis = line.split(", ")[1];
//                System.out.println(date);
                stats.put(date, Long.parseLong(millis));
//                System.out.println(stats.get(date));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static long getWeeklyStats() {
        LocalDate today = LocalDate.now();
        long sum = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Map.Entry entry : SaveStats.stats.entrySet()) {
            LocalDate date = LocalDate.parse(entry.getKey().toString(), formatter);
            if (date.isAfter(today.minusDays(7)) && date.isBefore(today.plusDays(1))) {
                sum += Long.parseLong(entry.getValue().toString());
            }
        }
        return sum;
    }

    public static String getStatsString(long value) {
        int minutes = (int) value / 60000;
        int hours;
        if (minutes <= 60) {
            return minutes + " min";
        } else {
            hours = ((minutes) / 60);
            minutes = minutes - (hours  * 60);
            return hours + " h " + minutes + " min";
        }


    }
    public static long findValue(String date) {
        if (!stats.containsKey(date)) {
            return 0;
        } else {
            return stats.get(date);
        }
    }
}
