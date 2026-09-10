package kingscall.audio;

import nl.saxion.app.audio.MediaPlayer;

/**
 * Fire-and-forget sound effects, played on a background thread through
 * SaxionApp's {@link MediaPlayer}. A new call is ignored while a clip is still
 * playing, so overlapping effects never stack up.
 */
public final class AudioHelper {

    private static MediaPlayer mediaPlayer;
    private static Thread playerThread;

    private AudioHelper() {
    }

    /**
     * Plays {@code filename}, optionally looping. Does nothing if a clip is
     * already playing.
     */
    public static synchronized void play(String filename, boolean loop) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            return;
        }
        stop();
        mediaPlayer = new MediaPlayer(filename, loop);
        playerThread = new Thread(mediaPlayer);
        playerThread.start();
    }

    /** Stops playback and releases the worker thread. No-op if nothing is playing. */
    public static synchronized void stop() {
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.stop();
        try {
            playerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        mediaPlayer = null;
        playerThread = null;
    }

    public static synchronized void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public static synchronized void resume() {
        if (mediaPlayer != null && mediaPlayer.isPaused()) {
            mediaPlayer.resume();
        }
    }

    public static synchronized void setVolume(float volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    public static synchronized void setLoop(boolean loop) {
        if (mediaPlayer != null) {
            mediaPlayer.setLoop(loop);
        }
    }

    public static synchronized String getFilename() {
        return mediaPlayer != null ? mediaPlayer.getFilename() : null;
    }

    public static synchronized boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    public static synchronized boolean isPaused() {
        return mediaPlayer != null && mediaPlayer.isPaused();
    }

    public static synchronized boolean isLoop() {
        return mediaPlayer != null && mediaPlayer.isLoop();
    }

    /** Stops whatever is playing and immediately starts {@code filename}. */
    public static synchronized void newSong(String filename, boolean loop) {
        stop();
        play(filename, loop);
    }
}
