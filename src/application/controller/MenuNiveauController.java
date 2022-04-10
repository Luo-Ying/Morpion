package application.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ai.Config;
import ai.ConfigFileLoader;
import application.PopupWindow;
import application.animation.MusicNoteAnimate;
import application.animation.ToggleSwitch;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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
import javafx.stage.Stage;

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
    private ImageView musicNote;
    
    @FXML
    private MenuBar menu;

    @FXML
    private Menu fichier;

    @FXML
    private MenuItem maison;

    @FXML
    private Menu modifier;

    @FXML
    private MenuItem configuration;

    @FXML
    private MenuItem gestionIA;
    
    @FXML
    private Menu aide;


    @FXML
    private MenuItem about;
    
    
    private Config config;
    
    private List <ImageView> yellowTheme = new ArrayList<>();
    
    private List <ImageView> pinkTheme = new ArrayList<>();
    
    private List <ImageView> greenTheme = new ArrayList<>();
    
    private Color color;
    
    private ToggleSwitch toggle;
    
    void setToggleSwitch(ToggleSwitch toggle) {
    	this.toggle=toggle;
    	sc2.getChildren().add(toggle);
    }
    
    ToggleSwitch getToggleSwitch() {
    	return this.toggle;
    }

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
    	verifyResult(event,"src/result/mlp_difficile.ser", "D");
    }

  //appuie sur niveau facile
    @FXML
    void setIAEasy(ActionEvent event) throws IOException {
    	setIALevel("F");
    	verifyResult(event,"src/result/mlp_facile.ser", "F");
    }

  //appuie sur niveau moyen
    @FXML
    void setIANormal(ActionEvent event) throws IOException {
    	setIALevel("M");
    	verifyResult(event,"src/result/mlp_moyen.ser", "M");
    }
    
  //appuie sur retour maison
    @FXML
    void returnHome(ActionEvent event) throws IOException {
    	SceneController sController = new SceneController();
		sController.switchToMenuAdversaireController(event,getColor(),getToggleSwitch());
    }
    
    //Verifie si chemin du fichier d'apprentissage existe
    //si oui alors on va sur jeu.fxml sinon on va sur Apprentissage.fxml
    public void verifyResult(ActionEvent event,String path, String level) throws IOException {
		File f = new File(path) ;
		if(f.exists() && f.isFile()) {
			SceneController sController = new SceneController();
			sController.switchToJeuController(event,getColor(),true, level,getToggleSwitch());
		}
		else {
			SceneController sController = new SceneController();
			sController.switchToApprentissageController(event,getIALevel(),getColor(),getToggleSwitch());
		}
		
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
        
  //ajout des images du thème jaune
    public void addImageToYellowTheme() {
    	yellowTheme.add(panda1);
		yellowTheme.add(panda2);
		yellowTheme.add(bamboo);
    }
    
  //ajout des images du thème rose
    public void addImageToPinkTheme() {
    	pinkTheme.add(petitPrince1);
    	pinkTheme.add(petitPrince2);
    }
    
  //ajout des images du thème vert
    public void addImageToGreenTheme() {
    	greenTheme.add(pawPatrol);
    	greenTheme.add(pawPatrolIcon);
    }
    
    //définition d'un thème
    public void setTheme(Color color,boolean yellow,boolean pink,boolean green) throws FileNotFoundException {
    	sc2.setBackground(new Background(new BackgroundFill(color, null, null)));
    	setColor(color);
    	
    	if(color==Color.LIGHTYELLOW) {
    		setMenu("-fx-text-fill: goldenrod;-fx-font: normal bold 14px 'MV Boli';");
		}
		else if(color==Color.LIGHTPINK) {
			setMenu("-fx-text-fill: pink;-fx-font: normal bold 14px 'MV Boli';");
		}
		else {
			setMenu("-fx-text-fill: green;-fx-font: normal bold 14px 'MV Boli';");
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
    public void setMenu(String style) throws FileNotFoundException {
    	aide.setStyle(style);
        modifier.setStyle(style);
        fichier.setStyle(style);
        maison.setStyle(style);
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
	public void setButton(Button button,Color color,MenuBar menu) {
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
		setButton(green,Color.rgb(210,252,209),null);
		setButton(pink,Color.LIGHTPINK,null);
		setButton(yellow,Color.LIGHTYELLOW,null);
		setButton(facile,Color.WHITE,null);
		setButton(moyen,Color.WHITE,null);
		setButton(difficile,Color.WHITE,null);
		setButton(btnMaison,Color.WHITE,null);
		setButton(null,Color.WHITE,menu);
		
		
		
		addImageToYellowTheme();
		addImageToPinkTheme();
		addImageToGreenTheme();
		MusicNoteAnimate musicNode = new MusicNoteAnimate(musicNote);
		musicNode.rotate();
		
	}

}
