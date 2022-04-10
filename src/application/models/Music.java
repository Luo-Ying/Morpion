package application.models;

import java.nio.file.Paths;

import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
	static MediaPlayer music;
	
	//ajout de la musique de fond et faire jouer
	public static void playMusic() {
		String path="resources/music/Musique.mp3";
		Media play = new Media(Paths.get(path).toUri().toString());
		music=new MediaPlayer(play);
		music.setCycleCount(Timeline.INDEFINITE);
		music.play();
	}
	
	//arreter la musique de fond
	public static void stopMusic() {
		music.stop();
	}

}
