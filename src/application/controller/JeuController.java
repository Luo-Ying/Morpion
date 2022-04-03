package application.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import application.PopupWindow;
import application.animation.CircleDraw;
import application.animation.ToggleSwitch;
import application.animation.XDraw;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
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

public class JeuController extends Preloader implements Initializable {
	@FXML
    private Pane sc1;
    
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
    private ImageView stella;
    
    @FXML
    private ImageView marcus;
    
    @FXML
    private ImageView ruben;
    
    @FXML
    private ImageView maisonJaune;

    @FXML
    private ImageView maisonVert;

    @FXML
    private ImageView maisonRose;
    
    @FXML
    private MenuItem about;
    
    @FXML
    private Canvas canvas1;
    
    @FXML
    private Canvas canvas2;
    
    @FXML
    private Canvas canvas3;
    
    @FXML
    private Canvas canvas4;
    
    @FXML
    private Canvas canvas5;
    
    @FXML
    private Canvas canvas6;
    
    @FXML
    private Canvas canvas7;
    
    @FXML
    private Canvas canvas8;
    
    @FXML
    private Canvas canvas9;
    
    private List <ImageView> yellowTheme = new ArrayList<>();
    
    private List <ImageView> pinkTheme = new ArrayList<>();
    
    private List <ImageView> greenTheme = new ArrayList<>();
    
    private Color color;
    
    //ajout des images au thème jaune
    public void addImageToYellowTheme() {
    	yellowTheme.add(panda1);
		yellowTheme.add(panda2);
		yellowTheme.add(bamboo);
		yellowTheme.add(maisonJaune);
    }
    
    //ajout des images au thème rose
    public void addImageToPinkTheme() {
    	pinkTheme.add(petitPrince1);
    	pinkTheme.add(petitPrince2);
    	pinkTheme.add(maisonRose);
    }
    
    //ajout des images au thème vert
    public void addImageToGreenTheme() {
    	greenTheme.add(stella);
    	greenTheme.add(marcus);
    	greenTheme.add(ruben);
    	greenTheme.add(maisonVert);
    }
    
    //définir un theme
    public void setTheme(Color color,boolean yellow,boolean pink,boolean green) throws FileNotFoundException {
    	sc1.setBackground(new Background(new BackgroundFill(color, null, null)));
    	setColor(color);
    	if(color==Color.LIGHTYELLOW) {
    		setMenu("src/images/menu-jaune.png","-fx-text-fill: goldenrod;-fx-font: normal bold 14px 'MV Boli';","-fx-accent: gold;");
		}
		else if(color==Color.LIGHTPINK) {
			setMenu("src/images/menu-rose.png","-fx-text-fill: pink;-fx-font: normal bold 14px 'MV Boli';","-fx-accent: pink;");
		}
		else {
			setMenu("src/images/menu-vert.png","-fx-text-fill: green;-fx-font: normal bold 14px 'MV Boli';","-fx-accent: green;");
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
    
    //définir le bouton menu
    public void setMenu(String path,String style,String pgbarStyle) throws FileNotFoundException {
    	FileInputStream input = new FileInputStream(path);
        Image image = new Image(input);
        
        ImageView menuCouleur = new ImageView(image);
        menuCouleur.setFitWidth(32.5);
        menuCouleur.setFitHeight(35);
        menu.prefWidthProperty().bind(menuCouleur.fitWidthProperty());           
        menu.prefHeightProperty().bind(menuCouleur.fitHeightProperty());           
        menu.setGraphic(menuCouleur);
        
		about.setStyle(style);
    }
    
    //Appuie sur retour maison
    @FXML
    void returnHome(MouseEvent event) throws IOException {
    	SceneController sController = new SceneController();
		sController.switchToMenuAdversaireController(event,getColor());
    }
    
    
    //Appuie sur l'item a propos du menu
    @FXML
    void popAbout(ActionEvent event) {
    	PopupWindow.displayAbout(getColor());
    }
    
    //récuperer couleur du thème
    public Color getColor() {
		return color;
	}

    //définir couleur du thème
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
			
	}
	
	
	//Définir un bouton de la scène
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
	
	@FXML
    void drawCase1(MouseEvent event) {
		//CircleDraw circle = new CircleDraw(canvas1);
		//sc1.getChildren().add(circle);
		XDraw x = new XDraw(canvas1);
		sc1.getChildren().add(x);
    }
	
	@FXML
    void drawCase2(MouseEvent event) {
		CircleDraw circle = new CircleDraw(canvas2);
		sc1.getChildren().add(circle);
    }
	
	@FXML
    void drawCase3(MouseEvent event) {
		CircleDraw circle = new CircleDraw(canvas3);
		sc1.getChildren().add(circle);
    }
	
	@FXML
    void drawCase4(MouseEvent event) {
		CircleDraw circle = new CircleDraw(canvas4);
		sc1.getChildren().add(circle);
    }
	
	@FXML
    void drawCase5(MouseEvent event) {
		CircleDraw circle = new CircleDraw(canvas5);
		sc1.getChildren().add(circle);
    }
	
	@FXML
    void drawCase6(MouseEvent event) {
		CircleDraw circle = new CircleDraw(canvas6);
		sc1.getChildren().add(circle);
    }
	
	@FXML
    void drawCase7(MouseEvent event) {
		CircleDraw circle = new CircleDraw(canvas7);
		sc1.getChildren().add(circle);
    }
	
	@FXML
    void drawCase8(MouseEvent event) {
		CircleDraw circle = new CircleDraw(canvas8);
		sc1.getChildren().add(circle);
    }
	
	@FXML
    void drawCase9(MouseEvent event) {
		CircleDraw circle = new CircleDraw(canvas9);
		sc1.getChildren().add(circle);
    }
	
	//Lors du chargement de la scène
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ToggleSwitch toggle= new ToggleSwitch();
		toggle.setTranslateY(68);
		toggle.setTranslateX(770);
	
		
		setButton(btnMaison,Color.WHITE,null);
		setButton(null,Color.WHITE,menu);
		
		sc1.getChildren().add(toggle);
		
		addImageToYellowTheme();
		addImageToPinkTheme();
		addImageToGreenTheme(); 
	}
}
