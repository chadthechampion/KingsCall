import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MPlayer {
    private Clip audioClip;
    private boolean isPaused;
    private long clipTimePosition;

    public MPlayer(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        isPaused = false;
        clipTimePosition = 0;
    }

    public void play() {
        if (isPaused) {
            audioClip.setMicrosecondPosition(clipTimePosition);
            isPaused = false;
        }
        audioClip.start();
    }

    public void pause() {
        if (audioClip.isRunning()) {
            clipTimePosition = audioClip.getMicrosecondPosition();
            audioClip.stop();
            isPaused = true;
        }
    }

    public void resume() {
        if (isPaused) {
            audioClip.setMicrosecondPosition(clipTimePosition);
            audioClip.start();
            isPaused = false;
        }
    }


    public void changeSong(String newFilePath) {
        stop();
        try {
            File audioFile = new File(newFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
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

    public static void main(String[] args) {
        MPlayer player = new MPlayer("path/to/your/song.wav");
        player.play();

        // Add your own logic to pause, resume, and change songs
    }
}