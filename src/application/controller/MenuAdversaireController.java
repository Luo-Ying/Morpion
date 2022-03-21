package application.controller;



import java.net.URL;

import java.util.ResourceBundle;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Preloader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuAdversaireController extends Preloader implements Initializable {
	@FXML
    private Pane sc2;
	
	@FXML
    private Button pink;
	
	@FXML
    private Button green;
	
	@FXML
    private Button yellow;


    @FXML
    private Button buttonVs;

    @FXML
    private Button buttonVs2;
    
    

    @FXML
    void colorPink(ActionEvent event) {
    	sc2.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));

    }
    
    @FXML
    void colorGreen(ActionEvent event) {
    	sc2.setBackground(new Background(new BackgroundFill(Color.rgb(210,252,209), null, null)));

    }
    
    @FXML
    void colorYellow(ActionEvent event) {
    	sc2.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));

    }
   
    
    public class ToggleSwitch extends Parent {
    	public BooleanProperty switchedOn = new SimpleBooleanProperty(false);
    	public TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    	public FillTransition fillAnimation= new FillTransition(Duration.seconds(0.25));
    	public ParallelTransition animation = new ParallelTransition(translateAnimation,fillAnimation);
    	
    	
    	public BooleanProperty switchedOnProperty() {
    		return switchedOn;
    	}
    	
    	
    	
    	ToggleSwitch() {
    		Rectangle background = new Rectangle(50,25);
    		background.setArcWidth(25);
    		background.setArcHeight(25);
    		background.setFill(Color.LIGHTBLUE);
    		background.setStroke(Color.LIGHTGREY);
    		
    		Circle trigger = new Circle(12.5);
    		trigger.setCenterX(40);
    		trigger.setCenterY(12.5);
    		trigger.setFill(Color.WHITE);
    		trigger.setStroke(Color.LIGHTGREY);
    		
    		translateAnimation.setNode(trigger);
    		fillAnimation.setShape(background);
    	
    		getChildren().addAll(background,trigger);
    		
    		switchedOn.addListener((observer, oldVal, newVal) -> {
    			boolean isOn = newVal.booleanValue();
    			translateAnimation.setToX(isOn ? -30 : 0);
    			animation.play();
    			fillAnimation.setFromValue(isOn ? Color.LIGHTBLUE : Color.WHITE);
    			fillAnimation.setToValue(isOn ? Color.WHITE : Color.LIGHTBLUE);
    			});
    		setOnMouseClicked(event ->{
    			switchedOn.set(!switchedOn.get());
    		});
    	}
    }
    
	@Override
	public void start(Stage arg0) throws Exception {
			
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sc2.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));
		
		ToggleSwitch toggle= new ToggleSwitch();
		toggle.setTranslateY(68);
		toggle.setTranslateX(770);
		green.setBackground(new Background(new BackgroundFill(Color.rgb(210,252,209), null, null)));
		green.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		pink.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
		pink.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		yellow.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));
		yellow.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		buttonVs.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		buttonVs.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		buttonVs2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		buttonVs2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		sc2.getChildren().add(toggle);
		
	}

}
