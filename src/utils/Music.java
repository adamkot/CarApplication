package utils;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Adam
 */
public class Music {

    MediaPlayer mediaPlayer;
    String uriString;

    public Music(String directory) {
        new javafx.embed.swing.JFXPanel();
        uriString = new File(directory).toURI().toString();
        mediaPlayer = new MediaPlayer(new Media(uriString));
    }

    public void play() {
        mediaPlayer.play();
    }
    
    public void pausePlay() {
        mediaPlayer.pause();
    }

    public void stopPlay() {
        mediaPlayer.stop();
    }

}
