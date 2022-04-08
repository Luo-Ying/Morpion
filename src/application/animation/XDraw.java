package application.animation;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//dessiner x dans une case
public class XDraw extends Parent {
	 public XDraw(Canvas canvas) {
	    	double beginX1 = canvas.getWidth()-25;
	    	double endX1 = 25;
	    	double beginY1 = canvas.getHeight()-25;
	    	double endY1 = 25;
	    	
	    	double beginX2 = 25;
	    	double endX2 = canvas.getWidth()-25;
	    	double beginY2 = canvas.getHeight()-25;
	    	double endY2 = 25;
	    	
	    	GraphicsContext gc = canvas.getGraphicsContext2D();
	    	gc.setLineWidth(3);
	    	gc.setStroke(Color.LIGHTSKYBLUE);
	    	
	    	gc.strokeLine(beginX1, beginY1, endX1, endY1);
	    	gc.strokeLine(beginX2, beginY2, endX2, endY2);
	    	
	    	
	    }

}
