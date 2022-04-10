package application.animation;

import application.controller.MenuAdversaireController;
import application.models.Music;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


//bouton de musique
public class ToggleSwitch extends Parent {
	public static BooleanProperty switchedOn = new SimpleBooleanProperty(false);
	public static TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
	public static FillTransition fillAnimation= new FillTransition(Duration.seconds(0.25));
	public static ParallelTransition animation = new ParallelTransition(translateAnimation,fillAnimation);
	public boolean music=true;
	MenuAdversaireController controller = new MenuAdversaireController();
	
	
	public static BooleanProperty switchedOnProperty() {
		return switchedOn;
	}

	public ToggleSwitch() {
		Rectangle background = new Rectangle(50,25);
		background.setArcWidth(25);
		background.setArcHeight(25);
		background.setFill(Color.LIGHTBLUE);
		background.setStroke(Color.LIGHTGREY);
		background.setStyle("-fx-cursor : hand");
		
		Circle trigger = new Circle(12.5);
		trigger.setCenterX(40);
		trigger.setCenterY(12.5);
		trigger.setFill(Color.WHITE);
		trigger.setStroke(Color.LIGHTGREY);
		trigger.setStyle("-fx-cursor : hand");
		
		translateAnimation.setNode(trigger);
		fillAnimation.setShape(background);
	
		getChildren().addAll(background,trigger);
		
		switchedOn.addListener((observer, oldVal, newVal) -> {
			boolean isOn = newVal.booleanValue();
			translateAnimation.setToX(isOn ? -30 : 0);
			animation.play();
			fillAnimation.setFromValue(isOn ? Color.LIGHTBLUE : Color.WHITE);
			fillAnimation.setToValue(isOn ? Color.WHITE : Color.LIGHTBLUE);
			if(music) {
				Music.stopMusic();
				music=false;
				controller.musicNode.stop();
			}
			else {
				Music.playMusic();
				music=true;
				controller.musicNode.rotate();
			}
			
			});
		setOnMouseClicked(event ->{
			switchedOn.set(!switchedOn.get());
			
			
		});	       
	}
}