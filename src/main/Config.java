package main;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private static Path saveData = findPath("config.txt");
    public Config() {
        loadConfig();
        findPath("config.txt");
    }

    public static void saveConfig() {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(saveData.toFile()));
            // Write volume settings
            bw.write("" + SettingsPage.volumeValue);
            bw.newLine();


            // Break Time:
                bw.write("" +SettingsPage.breakTimeValue);
                bw.newLine();

                // Work Time

            bw.write("" + TimerPage.TimerValue.timerValue);
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


public static Path copyToTempFile(URL url, String suffix) throws IOException{
        Path tempFile =  Files.createTempFile(null, suffix);
        try(InputStream in = url.openStream();
            OutputStream out = Files.newOutputStream(tempFile)) {

            in.transferTo(out);
        }
        return tempFile;
}

public static Path findPath(String fileName) {
        Path configParent;

        String appData = System.getenv("APPDATA");
        if (appData != null) {
            configParent = Paths.get(appData);
        } else {
            configParent = Paths.get(System.getProperty("user.home"), "AppData", "Local");
        }
        Path configDir = configParent.resolve("Productive Vibes");

    try {
        Files.createDirectories(configDir);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return configDir.resolve(fileName);
    }




    public static void loadConfig() {
        

        BufferedReader br;
        if (!saveData.toFile().isFile()) {
            saveConfig();
        }
        try {
            br = new BufferedReader(new FileReader(saveData.toFile()));
            //Volume value
            String line = br.readLine();
            if(line == null) {
                saveConfig();
            } else {
                SettingsPage.volumeValue = Integer.parseInt(line);
                SoundPlayer.volume = ((float) SettingsPage.volumeValue / 100);


                // Break Time Value


                line = br.readLine();

                SettingsPage.breakTimeValue = Integer.parseInt(line);

                //Work Time Value

                line = br.readLine();

                TimerPage.TimerValue.timerValue = Integer.parseInt(line);
            }
            br.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
