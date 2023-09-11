package main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class SoundPlayer{
    Clip alarmClip;
    private AudioInputStream audioStream;
    public static float volume = 1;



    static File resetAndSetSound;
    static File startSound;
    static File alarmSound;

    static {
        try {
            resetAndSetSound = Config.copyToTempFile(SoundPlayer.class.getResource("/resources/Audio/Hi Hats - Purp.wav"), "wav").toFile();
            startSound = Config.copyToTempFile(SoundPlayer.class.getResource("/resources/Audio/UNISON_808_Agent (C).wav"), "wav").toFile();
            alarmSound = Config.copyToTempFile(SoundPlayer.class.getResource(      "/resources/Audio/Playboi Carti x Cochise Type Beat - ＂Tell Em＂.wav"), "wav").toFile();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openAlarm(String filePath) {
        try {

            audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            alarmClip = AudioSystem.getClip();
            alarmClip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void playSound(File file) {
        AudioInputStream audioInputStream;
        Clip clip;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            if (volume < 0f || volume > 1f) {
                throw  new IllegalArgumentException("Volume not valid: " + volume);
            }
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        clip.start();
    }


    public void playAlarm() {
        if (volume < 0f || volume > 1f) {
            throw  new IllegalArgumentException("Volume not valid: " + volume);
        }
        FloatControl gainControl = (FloatControl) alarmClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));

        alarmClip.start();

    }
    void closeAlarm() {
        if (alarmClip != null) {
            alarmClip.stop();
            alarmClip.close();
        }

        try {
            if (audioStream != null) {
                audioStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
