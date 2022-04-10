package application.models;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
	static MediaPlayer music;
	
	public static void playMusic() {
		String path="resources/music/Musique.mp3";
		Media play = new Media(Paths.get(path).toUri().toString());
		music=new MediaPlayer(play);
		music.play();
	}
	public static void stopMusic() {
		music.stop();
	}

}
