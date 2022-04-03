package application.animation;


import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleDraw extends Parent {
	    public CircleDraw(Canvas canvas) {
	    	double centerX = canvas.getWidth()/2;
	    	double centerY =canvas.getHeight()/2;
	    	double radius = 60;
	    	GraphicsContext gc = canvas.getGraphicsContext2D();
	    	gc.setLineWidth(3);
	    	gc.setStroke(Color.PURPLE);
	    	gc.strokeOval(centerX-radius, centerY-radius, radius * 2, radius * 2);
	    }
	       
}

