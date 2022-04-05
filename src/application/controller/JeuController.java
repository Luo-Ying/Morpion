package application.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import application.Jeu;
import application.PopupWindow;
import application.animation.CircleDraw;
import application.animation.ToggleSwitch;
import application.animation.XDraw;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import javafx.scene.shape.Line;
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
    
    private boolean isIAgame = false;
    
    private boolean isPlayerTurn = false;
    
    private boolean isIATurn = false;
    
    private int nb=1;
    
    private boolean gagne = false;
    
    boolean reinit =false;
    
    
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
		if(!this.isIAgame) {			
			selectCase(canvas1, 0);
		}
		else {
			
		}
    }
	
	@FXML
    void drawCase2(MouseEvent event) {
		if(!this.isIAgame) {
			selectCase(canvas2, 1);
		}
		else {
			
		}
    }
	
	@FXML
    void drawCase3(MouseEvent event) {
		if(!this.isIAgame) {
			selectCase(canvas3, 2);
		}
		else {
			
		}
    }
	
	@FXML
    void drawCase4(MouseEvent event) {
		if(!isIAgame) {
			selectCase(canvas4, 3);
		}
		else {
			
		}
    }
	
	@FXML
    void drawCase5(MouseEvent event) {
		if(!this.isIAgame) {
			selectCase(canvas5, 4);
		}
		else {
			
		}
    }
	
	@FXML
    void drawCase6(MouseEvent event) {
		if(!this.isIAgame) {
			selectCase(canvas6, 5);
		}
		else {
			
		}
    }
	
	@FXML
    void drawCase7(MouseEvent event) {
		if(!this.isIAgame) {
			selectCase(canvas7, 6);
		}
		else {
			
		}
    }
	
	@FXML
    void drawCase8(MouseEvent event) {
		if(!this.isIAgame) {
			selectCase(canvas8, 7);
		}
		else {
			
		}
    }
	
	@FXML
    void drawCase9(MouseEvent event) {
		if(!this.isIAgame) {
			selectCase(canvas9, 8);
		}
		else {
			
		}
    }
	
	public void displayResult(Canvas canvas) {
		gagne=true;
		String playerWin;
		if(tableau.isWinPlayer1) playerWin = "1"; else playerWin = "2";
		Stage window = PopupWindow.displayWinner(getColor(),playerWin);
		if(window.onCloseRequestProperty()!=null) {
			reinitialiserJeu(tableau.getLine());
		}
		else {
			canvas.setDisable(true);
		}
	}
	
	public void IAgame(Canvas canvas,int i) {
		if(this.isIATurn) {
			//TODO: function of IA turn
		}
		else {
			
		}
	}
	
	void selectCase(Canvas canvas,int i) {
		if((!canvas.isDisable()) && ((nb%2)!=0)) {
			XDraw x = new XDraw(canvas);
			sc1.getChildren().add(x);
			
			double player = -1.0;
			tableau.setCaseX(i);
			
//			System.out.println(tableau.verifieGagner(sc1));
			System.out.println("x");
			
			if(tableau.verifieGagner(sc1, player)) {
				displayResult(canvas);
			}
			else {
			nb+=1;}
			if(nb==10 && !gagne) {
				Stage window =PopupWindow.displayDraw(getColor());
				if(window.onCloseRequestProperty()!=null) {
					reinitialiserJeu(tableau.getLine());
				}
			}
			if(!reinit) {
			canvas.setDisable(true);
			reinit=false;
			}
		}
		else if((!canvas.isDisable()) && ((nb%2)==0)){
			CircleDraw circle = new CircleDraw(canvas);
			sc1.getChildren().add(circle);
			
			double player = 1.0;
			tableau.setCaseO(i);
			
			System.out.println("O");
			
			if(tableau.verifieGagner(sc1, player)) {
				displayResult(canvas);
			}
			else {
			nb+=1;
			}
			if(nb==10 && !gagne) {
				Stage window =PopupWindow.displayDraw(getColor());
				if(window.onCloseRequestProperty()!=null) {
					reinitialiserJeu(tableau.getLine());
				}
			}
			if(!reinit) {
				canvas.setDisable(true);
				reinit=false;
				}
		}
	}
	
	public void reinitialiserJeu(Line line) {
		GraphicsContext gc;
		for (int i=0;i<this.canvas.size();i++) {
			gc=canvas.get(i).getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.get(i).getWidth(), canvas.get(i).getHeight());
			canvas.get(i).setDisable(false);
			sc1.getChildren().remove(line);
			
		}
		System.out.println("ok");
		this.tableau = new Jeu();
		nb=1;gagne=false;reinit=true;
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
		addCanvasToList();
	}
}
