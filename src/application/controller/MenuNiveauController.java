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
import application.PopupWindow;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
    private MenuButton menu;
    
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
    
    @FXML
    private MenuItem configuration;

    @FXML
    private MenuItem gestionIA;

    @FXML
    private MenuItem about;
    
    
    private Config config;
    
    private List <ImageView> yellowTheme = new ArrayList<>();
    
    private List <ImageView> pinkTheme = new ArrayList<>();
    
    private List <ImageView> greenTheme = new ArrayList<>();
    
    private Color color;

  //appuie sur bouton thème rose
    @FXML
    void colorPink(ActionEvent event) throws FileNotFoundException {
    	setTheme(Color.LIGHTPINK,false,true,false);
    }
    
  //appuie sur bouton thème vert
    @FXML
    void colorGreen(ActionEvent event) throws FileNotFoundException {
    	setTheme(Color.rgb(210,252,209),false,false,true);
    }
    
  //appuie sur bouton thème jaune
    @FXML
    void colorYellow(ActionEvent event) throws FileNotFoundException {
    	setTheme(Color.LIGHTYELLOW,true,false,false);
    }
    
    //définir le niveau de l'IA
    void setIALevel(String level) throws IOException {
    	ConfigFileLoader cfl = new ConfigFileLoader();
		cfl.loadConfigFile("./resources/config.txt");
		this.config = cfl.get(level);
    }
    
    //récupération du niveau de l'IA
    public Config getIALevel() {
    	return this.config;
    }
    
    
    //appuie sur niveau difficile
    @FXML
    void setIADifficult(ActionEvent event) throws IOException {
    	setIALevel("D");
    	SceneController sController = new SceneController();
		sController.switchToApprentissageController(event,getIALevel(),getColor());
    }

  //appuie sur niveau facile
    @FXML
    void setIAEasy(ActionEvent event) throws IOException {
    	setIALevel("F");
    	SceneController sController = new SceneController();
		sController.switchToApprentissageController(event,getIALevel(),getColor());
    }

  //appuie sur niveau moyen
    @FXML
    void setIANormal(ActionEvent event) throws IOException {
    	setIALevel("M");
    	SceneController sController = new SceneController();
		sController.switchToApprentissageController(event,getIALevel(),getColor());
    }
    
  //appuie sur retour maison
    @FXML
    void returnHome(MouseEvent event) throws IOException {
    	SceneController sController = new SceneController();
		sController.switchToMenuAdversaireController(event,getColor());
    }
    
    
    //appuie sur item a propos du menu
    @FXML
    void popAbout(ActionEvent event) {
    	PopupWindow.displayAbout(getColor());
    }
    
    //appuie sur item configuration du menu
    @FXML
    void popConfig(ActionEvent event) {
    	PopupWindow.displayConfiguration(getColor());
    }
    
    //appuie sur item gestion du menu
    @FXML
    void popGestionIA(ActionEvent event) {
    	PopupWindow.displayGestionIA(getColor());
    }
    
    
  //Création du switch musique
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
    
    
  //ajout des images du thème jaune
    public void addImageToYellowTheme() {
    	yellowTheme.add(panda1);
		yellowTheme.add(panda2);
		yellowTheme.add(bamboo);
		yellowTheme.add(maisonJaune);
    }
    
  //ajout des images du thème rose
    public void addImageToPinkTheme() {
    	pinkTheme.add(petitPrince1);
    	pinkTheme.add(petitPrince2);
    	pinkTheme.add(maisonRose);
    }
    
  //ajout des images du thème vert
    public void addImageToGreenTheme() {
    	greenTheme.add(pawPatrol);
    	greenTheme.add(pawPatrolIcon);
    	greenTheme.add(maisonVert);
    }
    
    //définition d'un thème
    public void setTheme(Color color,boolean yellow,boolean pink,boolean green) throws FileNotFoundException {
    	sc2.setBackground(new Background(new BackgroundFill(color, null, null)));
    	setColor(color);
    	
    	if(color==Color.LIGHTYELLOW) {
    		setMenu("src/images/menu-jaune.png","-fx-text-fill: goldenrod;-fx-font: normal bold 14px 'MV Boli';");
		}
		else if(color==Color.LIGHTPINK) {
			setMenu("src/images/menu-rose.png","-fx-text-fill: pink;-fx-font: normal bold 14px 'MV Boli';");
		}
		else {
			setMenu("src/images/menu-vert.png","-fx-text-fill: green;-fx-font: normal bold 14px 'MV Boli';");
		}
    	
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
    
  //définition du menu
    public void setMenu(String path,String style) throws FileNotFoundException {
    	FileInputStream input = new FileInputStream(path);
        Image image = new Image(input);
        
        ImageView menuCouleur = new ImageView(image);
        menuCouleur.setFitWidth(32.5);
        menuCouleur.setFitHeight(35);
        menu.prefWidthProperty().bind(menuCouleur.fitWidthProperty());           
        menu.prefHeightProperty().bind(menuCouleur.fitHeightProperty());           
        menu.setGraphic(menuCouleur);
        
        configuration.setStyle(style);
		gestionIA.setStyle(style);
		about.setStyle(style);
    }
    
    //récupération de la couleur du thème
    public Color getColor() {
		return color;
	}

  //définition de la couleur du thème
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
			
	}
	
	//définition d'un bouton
	public void setButton(Button button,Color color,MenuButton menu) {
		if(button!=null) {
			button.setBackground(new Background(new BackgroundFill(color, null, null)));
			button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}
		if(menu!=null) {
			menu.setBackground(new Background(new BackgroundFill(color, null, null)));
			menu.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}
	}

	//phase de génération de la scène
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ToggleSwitch toggle= new ToggleSwitch();
		toggle.setTranslateY(68);
		toggle.setTranslateX(770);
		
		setButton(green,Color.rgb(210,252,209),null);
		setButton(pink,Color.LIGHTPINK,null);
		setButton(yellow,Color.LIGHTYELLOW,null);
		setButton(facile,Color.WHITE,null);
		setButton(moyen,Color.WHITE,null);
		setButton(difficile,Color.WHITE,null);
		setButton(btnMaison,Color.WHITE,null);
		setButton(null,Color.WHITE,menu);
		
		sc2.getChildren().add(toggle);
		
		addImageToYellowTheme();
		addImageToPinkTheme();
		addImageToGreenTheme();
		
	}

}
