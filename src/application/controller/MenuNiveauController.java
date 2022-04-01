package application.controller;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ai.Config;
import ai.ConfigFileLoader;
import application.Main;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Preloader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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

public class MenuNiveauController extends Preloader implements Initializable {
	@FXML
    private Pane sc2;
	
	@FXML
    private Button pink;
	
	@FXML
    private Button green;
	
	@FXML
    private Button yellow;
	
	@FXML
    private Button facile;

    @FXML
    private Button moyen;
    
    @FXML
    private Button maison;

    @FXML
    private Button difficile;
    
    @FXML
    private Button btnMaison;
    
    @FXML
    private ImageView panda1;
    
    @FXML
    private ImageView panda2;
    
    @FXML
    private ImageView bamboo;
    
    @FXML
    private ImageView petitPrince1;
    
    @FXML
    private ImageView petitPrince2;
    
    @FXML
    private ImageView pawPatrol;
    
    @FXML
    private ImageView pawPatrolIcon;
    
    @FXML
    private ImageView maisonJaune;

    @FXML
    private ImageView maisonVert;

    @FXML
    private ImageView maisonRose;
    
    
    private Config config;
    
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
    void setIALevel(String level) throws IOException {
    	ConfigFileLoader cfl = new ConfigFileLoader();
		cfl.loadConfigFile("./resources/config.txt");
		this.config = cfl.get(level);
    }
    
    public Config getIALevel() {
    	return this.config;
    }
    
    @FXML
    void setIADifficult(ActionEvent event) throws IOException {
    	setIALevel("D");
    	SceneController sController = new SceneController();
		sController.switchToApprentissageController(event,getIALevel(),getColor());
    	
    }

    @FXML
    void setIAEasy(ActionEvent event) throws IOException {
    	setIALevel("F");
    	SceneController sController = new SceneController();
		sController.switchToApprentissageController(event,getIALevel(),getColor());
    }

    @FXML
    void setIANormal(ActionEvent event) throws IOException {
    	setIALevel("M");
    	SceneController sController = new SceneController();
		sController.switchToApprentissageController(event,getIALevel(),getColor());

    }
    @FXML
    void returnHome(MouseEvent event) throws IOException {
    	SceneController sController = new SceneController();
		sController.switchToMenuAdversaireController(event,getColor());
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
		yellowTheme.add(bamboo);
		yellowTheme.add(maisonJaune);
    }
    
    public void addImageToPinkTheme() {
    	pinkTheme.add(petitPrince1);
    	pinkTheme.add(petitPrince2);
    	pinkTheme.add(maisonRose);
    }
    public void addImageToGreenTheme() {
    	greenTheme.add(pawPatrol);
    	greenTheme.add(pawPatrolIcon);
    	greenTheme.add(maisonVert);
    }
    
    public void setTheme(Color color,boolean yellow,boolean pink,boolean green) {
    	sc2.setBackground(new Background(new BackgroundFill(color, null, null)));
    	setColor(color);
    	
    	for(int i=0;i<yellowTheme.size();i++) {
    		yellowTheme.get(i).setVisible(yellow);
    	}
    	
    	for(int i=0;i<pinkTheme.size();i++) {
    		pinkTheme.get(i).setVisible(pink);
    	}
    	
    	for(int i=0;i<greenTheme.size();i++) {
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
		
		ToggleSwitch toggle= new ToggleSwitch();
		toggle.setTranslateY(68);
		toggle.setTranslateX(770);
		
		
		green.setBackground(new Background(new BackgroundFill(Color.rgb(210,252,209), null, null)));
		green.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		pink.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
		pink.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		yellow.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));
		yellow.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		facile.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		facile.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		moyen.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		moyen.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		difficile.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		difficile.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		btnMaison.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		btnMaison.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		sc2.getChildren().add(toggle);
		
		addImageToYellowTheme();
		addImageToPinkTheme();
		addImageToGreenTheme();
		
	}

}
