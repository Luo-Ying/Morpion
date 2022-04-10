package application.animation;

import javafx.animation.Animation.Status;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point3D;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MusicNoteAnimate extends Parent {
	static RotateTransition rt;
	static ImageView musicImage;
	
	//set transition on imageview music note
    public MusicNoteAnimate(ImageView image) {
    	musicImage=image;
    	rt= new RotateTransition();
    	rt.setNode(image);
    	rt.setDuration(Duration.millis(1250));
        rt.setFromAngle(30);
        rt.setToAngle(-30);
        rt.setAxis(new Point3D(100, 100, 50));
        rt.setCycleCount(Timeline.INDEFINITE);;
        rt.setAutoReverse(true);

    }
    
    //play transition
    public void rotate(){
        rt.play();
    }
    
    //stop transition
    public void stop() {
    	rt.stop();
    }
}
