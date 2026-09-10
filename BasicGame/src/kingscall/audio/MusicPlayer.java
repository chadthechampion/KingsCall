package kingscall.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * Background-music player built on {@link Clip}. Handles the intro sting and
 * then the looping in-game track; {@link #changeSong(String)} swaps the track
 * and loops it continuously.
 */
public class MusicPlayer {

    private Clip audioClip;
    private boolean paused;
    private long clipTimePosition;

    public MusicPlayer(String filePath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        paused = false;
        clipTimePosition = 0;
    }

    public void play() {
        if (paused) {
            audioClip.setMicrosecondPosition(clipTimePosition);
            paused = false;
        }
        audioClip.start();
    }

    public void pause() {
        if (audioClip.isRunning()) {
            clipTimePosition = audioClip.getMicrosecondPosition();
            audioClip.stop();
            paused = true;
        }
    }

    public void resume() {
        if (paused) {
            audioClip.setMicrosecondPosition(clipTimePosition);
            audioClip.start();
            paused = false;
        }
    }

    /** Stops the current track and starts {@code newFilePath} looping forever. */
    public void changeSong(String newFilePath) {
        stop();
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(newFilePath));
            audioClip.open(audioStream);
            audioClip.setLoopPoints(0, -1);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (audioClip.isRunning()) {
            audioClip.stop();
        }
        audioClip.close();
    }
}
