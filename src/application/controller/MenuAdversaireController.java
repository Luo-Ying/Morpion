package application.controller;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.PopupWindow;
import application.animation.ToggleSwitch;

import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.stage.Stage;

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
    
    @FXML
    private MenuButton menu;
    
    @FXML
    private MenuItem configuration;

    @FXML
    private MenuItem gestionIA;

    @FXML
    private MenuItem about;
    
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
    
    //changement de scène pour aller vers MenuNiveau
    @FXML
    void switchToMenuNiveau(MouseEvent event) throws IOException {
    	SceneController sController = new SceneController();
		sController.switchToMenuNiveauController(event,getColor());
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
    
  //appuie sur item gestionIA du menu
    @FXML
    void popGestionIA(ActionEvent event) {
    	PopupWindow.displayGestionIA(getColor());
    }
    
    //vers jeu.fxml
    @FXML
    void goToJeu(MouseEvent event) throws IOException {
    	SceneController sController = new SceneController();
		sController.switchToJeuController(event,getColor());
    }
   
    //ajout des images du thème jaune
    public void addImageToYellowTheme() {
    	yellowTheme.add(panda1);
		yellowTheme.add(panda2);
		yellowTheme.add(panda3);
		yellowTheme.add(panda4);
		yellowTheme.add(panda5);
		yellowTheme.add(bamboo);
    }
    
  //ajout des images du thème rose
    public void addImageToPinkTheme() {
    	pinkTheme.add(renard);
    	pinkTheme.add(mouton);
    	pinkTheme.add(petitPrince1);
    	pinkTheme.add(petitPrince2);
    	pinkTheme.add(petitPrince3);
    }
    
  //ajout des images du thème vert
    public void addImageToGreenTheme() {
    	greenTheme.add(pawPatrolIcon);
    	greenTheme.add(marcus);
    	greenTheme.add(ruben);
    	greenTheme.add(stella);
    	greenTheme.add(pawPatrol);
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
    	//pour le theme rose et vert ensemble
    	for(int i=0;i<pinkTheme.size();i++) {
    		pinkTheme.get(i).setVisible(pink);
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
		sc2.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));
		
		
		ToggleSwitch toggle= new ToggleSwitch();
		toggle.setTranslateY(68);
		toggle.setTranslateX(770);
		
		setButton(green,Color.rgb(210,252,209),null);
		setButton(pink,Color.LIGHTPINK,null);
		setButton(yellow,Color.LIGHTYELLOW,null);
		setButton(buttonVs,Color.WHITE,null);
		setButton(buttonVs2,Color.WHITE,null);
		setButton(null,Color.WHITE,menu);
		
		
	
		sc2.getChildren().add(toggle);
		
		addImageToYellowTheme();
		addImageToPinkTheme();
		addImageToGreenTheme();
		setColor(Color.LIGHTYELLOW);
		try {
			setMenu("src/images/menu-jaune.png","-fx-text-fill: goldenrod;-fx-font: normal bold 14px 'MV Boli';");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
