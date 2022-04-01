package application.controller;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	private ImageView panda1;

	@FXML
	private ImageView panda2;

	@FXML
	private ImageView panda3;
	
	@FXML
	private ImageView panda4;
	
	@FXML
	private ImageView panda5;
	
	@FXML
	private ImageView bamboo;

	@FXML
	private ImageView soleil;
	
	@FXML
	private ImageView renard;

	@FXML
	private ImageView mouton;

	@FXML
	private ImageView petitPrince1;

	@FXML
	private ImageView petitPrince2;

	@FXML
	private ImageView petitPrince3;
	
	@FXML
    private ImageView marcus;

    @FXML
    private ImageView ruben;

    @FXML
    private ImageView stella;

    @FXML
    private ImageView pawPatrol;

    @FXML
    private ImageView pawPatrolIcon;
	 
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
    
    private List <ImageView> yellowTheme = new ArrayList<>();
    
    private List <ImageView> pinkTheme = new ArrayList<>();
    
    private List <ImageView> greenTheme = new ArrayList<>();
    
    private Color color;

    @FXML
    void colorPink(ActionEvent event) {
    	setTheme(Color.LIGHTPINK,false,true,false);

    }
    
    @FXML
    void colorGreen(ActionEvent event) {
    	setTheme(Color.rgb(210,252,209),false,false,true);
    }
    
    @FXML
    void colorYellow(ActionEvent event) {
    	setTheme(Color.LIGHTYELLOW,true,false,false);
    	
    }
    
    @FXML
    void switchToMenuNiveau(MouseEvent event) throws IOException {
    	SceneController sController = new SceneController();
		sController.switchToMenuNiveauController(event,getColor());
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
    
    public void addImageToYellowTheme() {
    	yellowTheme.add(panda1);
		yellowTheme.add(panda2);
		yellowTheme.add(panda3);
		yellowTheme.add(panda4);
		yellowTheme.add(panda5);
		yellowTheme.add(bamboo);
		yellowTheme.add(soleil);
    }
    
    public void addImageToPinkTheme() {
    	pinkTheme.add(renard);
    	pinkTheme.add(mouton);
    	pinkTheme.add(petitPrince1);
    	pinkTheme.add(petitPrince2);
    	pinkTheme.add(petitPrince3);
    }
    
    public void addImageToGreenTheme() {
    	greenTheme.add(pawPatrolIcon);
    	greenTheme.add(marcus);
    	greenTheme.add(ruben);
    	greenTheme.add(stella);
    	greenTheme.add(pawPatrol);
    }
    
    public void setTheme(Color color,boolean yellow,boolean pink,boolean green) {
    	sc2.setBackground(new Background(new BackgroundFill(color, null, null)));
    	setColor(color);
    	for(int i=0;i<yellowTheme.size();i++) {
    		yellowTheme.get(i).setVisible(yellow);
    	}
    	//pour le theme rose et vert ensemble
    	for(int i=0;i<pinkTheme.size();i++) {
    		pinkTheme.get(i).setVisible(pink);
    		greenTheme.get(i).setVisible(green);
    	}
    }
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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
		
		addImageToYellowTheme();
		addImageToPinkTheme();
		addImageToGreenTheme();
		setColor(Color.LIGHTYELLOW);
		
	}

}
