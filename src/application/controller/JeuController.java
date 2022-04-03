package application.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Jeu;
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
    
    private List <Canvas> canvas = new ArrayList<>();
    
    private Color color;
    
    private Jeu tableau = new Jeu();
    
    private int nb=1;
    
    
    //ajout des images au th�me jaune
    public void addImageToYellowTheme() {
    	yellowTheme.add(panda1);
		yellowTheme.add(panda2);
		yellowTheme.add(bamboo);
		yellowTheme.add(maisonJaune);
    }
    
    //ajout des images au th�me rose
    public void addImageToPinkTheme() {
    	pinkTheme.add(petitPrince1);
    	pinkTheme.add(petitPrince2);
    	pinkTheme.add(maisonRose);
    }
    
    //ajout des images au th�me vert
    public void addImageToGreenTheme() {
    	greenTheme.add(stella);
    	greenTheme.add(marcus);
    	greenTheme.add(ruben);
    	greenTheme.add(maisonVert);
    }
    
    public void addCanvasToList() {
    	canvas.add(canvas1);
    	canvas.add(canvas2);
    	canvas.add(canvas3);
    	canvas.add(canvas4);
    	canvas.add(canvas5);
    	canvas.add(canvas6);
    	canvas.add(canvas7);
    	canvas.add(canvas8);
    	canvas.add(canvas9);	
    }
    
    //d�finir un theme
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
    
    //d�finir le bouton menu
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
    
    //r�cuperer couleur du th�me
    public Color getColor() {
		return color;
	}

    //d�finir couleur du th�me
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
			
	}
	
	
	//D�finir un bouton de la sc�ne
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
		selectCase(canvas1,0,0);
    }
	
	@FXML
    void drawCase2(MouseEvent event) {
		selectCase(canvas2,0,1);
    }
	
	@FXML
    void drawCase3(MouseEvent event) {
		selectCase(canvas3,0,2);
    }
	
	@FXML
    void drawCase4(MouseEvent event) {
		selectCase(canvas4,1,0);
    }
	
	@FXML
    void drawCase5(MouseEvent event) {
		selectCase(canvas5,1,1);
    }
	
	@FXML
    void drawCase6(MouseEvent event) {
		selectCase(canvas6,1,2);
    }
	
	@FXML
    void drawCase7(MouseEvent event) {
		selectCase(canvas7,2,0);
    }
	
	@FXML
    void drawCase8(MouseEvent event) {
		selectCase(canvas8,2,1);
    }
	
	@FXML
    void drawCase9(MouseEvent event) {
		selectCase(canvas9,2,2);
    }
	
	void selectCase(Canvas canvas,int i, int j) {
		if((!canvas.isDisable()) && ((nb%2)!=0)) {
			XDraw x = new XDraw(canvas);
			sc1.getChildren().add(x);
			tableau.setCaseX(i, j);
			if(tableau.verifieGagner(tableau.getTableauX(),sc1)) {
				PopupWindow.displayWinner(getColor(),"1", this.canvas,sc1);
			}
			nb+=1;
			if(nb==10) {
				System.out.println("Draw");
			}
			canvas.setDisable(true);
		}
		else if((!canvas.isDisable()) && ((nb%2)==0)){
			CircleDraw circle = new CircleDraw(canvas);
			sc1.getChildren().add(circle);
			tableau.setCaseY(i, j);
			if(tableau.verifieGagner(tableau.getTableauO(),sc1)) {
				PopupWindow.displayWinner(getColor(),"2", this.canvas,sc1);
			}
			nb+=1;
			if(nb==10) {
				System.out.println("Draw");
			}
			canvas.setDisable(true);
		}
	}
	
	//Lors du chargement de la sc�ne
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
		addCanvasToList();
	}
}
