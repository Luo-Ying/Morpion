package application.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.PopupWindow;
import application.animation.CircleDraw;
import application.animation.ToggleSwitch;
import application.animation.XDraw;
import application.models.IaModel;
import application.models.Jeu;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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
    private MenuBar menu;

    @FXML
    private Menu fichier;

    @FXML
    private MenuItem maison;

    @FXML
    private Menu aide;

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
    
    private boolean isIAgame;
    
    private String level;
    
    private boolean isIATurn = false;
    
    private int nb=1;
    
    private boolean gagne = false;
    
    boolean reinit =false;
    
    
    //mettre le booleen si le jeu est contre IA
    public void setIAGame(boolean isAI) {
    	this.isIAgame=isAI;
    }
    
    //Mettre le niveau de difficultÈ
    public void setIaLevel(String level) {
    	this.level = level;
    }
    
    
    
    //ajout des images au th√®me jaune
    public void addImageToYellowTheme() {
    	yellowTheme.add(panda1);
		yellowTheme.add(panda2);
		yellowTheme.add(bamboo);
    }
    
    //ajout des images au th√®me rose
    public void addImageToPinkTheme() {
    	pinkTheme.add(petitPrince1);
    	pinkTheme.add(petitPrince2);
    }
    
    //ajout des images au th√®me vert
    public void addImageToGreenTheme() {
    	greenTheme.add(stella);
    	greenTheme.add(marcus);
    	greenTheme.add(ruben);
    }
    
    //ajout de toutes les cases dans une liste
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
    
    //dÈfinir un theme
    public void setTheme(Color color,boolean yellow,boolean pink,boolean green) throws FileNotFoundException {
    	sc1.setBackground(new Background(new BackgroundFill(color, null, null)));
    	setColor(color);
    	if(color==Color.LIGHTYELLOW) {
    		setMenu("-fx-text-fill: goldenrod;-fx-font: normal bold 14px 'MV Boli';","-fx-accent: gold;");
		}
		else if(color==Color.LIGHTPINK) {
			setMenu("-fx-text-fill: pink;-fx-font: normal bold 14px 'MV Boli';","-fx-accent: pink;");
		}
		else {
			setMenu("-fx-text-fill: green;-fx-font: normal bold 14px 'MV Boli';","-fx-accent: green;");
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
    
    //d√©finir le bouton menu
    public void setMenu(String style,String pgbarStyle) throws FileNotFoundException {
    	aide.setStyle(style);
        fichier.setStyle(style);
        maison.setStyle(style);
		about.setStyle(style);
    }
    
    //Appuie sur retour maison
    @FXML
    void returnHome(ActionEvent event) throws IOException {
    	SceneController sController = new SceneController();
		sController.switchToMenuAdversaireController(event,getColor());
    }
    
    
    //Appuie sur l'item a propos du menu
    @FXML
    void popAbout(ActionEvent event) {
    	PopupWindow.displayAbout(getColor());
    }
    
    //r√©cuperer couleur du th√®me
    public Color getColor() {
		return color;
	}

    //d√©finir couleur du th√®me
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
			
	}
	
	
	//D√©finir un bouton de la sc√®ne
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
	
	//Dessiner sur une case 
	@FXML
	void drawCase1(MouseEvent event) {
		gameTurn(canvas1, 0);
	}
	
	@FXML
	void drawCase2(MouseEvent event) {
		gameTurn(canvas2, 1);
	}
	
	@FXML
	void drawCase3(MouseEvent event) {
		gameTurn(canvas3, 2);
	}
	
	@FXML
	void drawCase4(MouseEvent event) {
		gameTurn(canvas4, 3);
	}
	
	@FXML
	void drawCase5(MouseEvent event) {
		gameTurn(canvas5, 4);
	}
	
	@FXML
	void drawCase6(MouseEvent event) {
		gameTurn(canvas6, 5);
	}
	
	@FXML
	void drawCase7(MouseEvent event) {
		gameTurn(canvas7, 6);
	}
	
	@FXML
	void drawCase8(MouseEvent event) {
		gameTurn(canvas8, 7);
	}
	
	@FXML
	void drawCase9(MouseEvent event) {
		gameTurn(canvas9, 8);
	}
	//fin dessiner sur une case
	
	
	
	//Si le jeu c'est humain vs humain/IA
	public void gameTurn(Canvas canvas, int turn) {
		if(!this.isIAgame) {			
			selectCase(canvas, turn);
		}
		else {
			if(this.isIATurn) {
				IAplay();
			}
			else {
				selectCaseDrawX(canvas, turn);
				if(this.nb != 1) {
					this.isIATurn = true;
					gameTurn(canvas, turn);
				}
			}
		}
	}
	
	//Quand l'IA joue
	public void IAplay() {
		int index = this.tableau.roundIA(this.level);
		Canvas canvas = getCanvas(index);
		selectCaseDrawO(canvas, index);
		canvas.setDisable(true);
		this.isIATurn = false;
	}
	
	
	//recupÈrer les cases
	public Canvas getCanvas(int i) {
		switch (i) {
			case 0:
				return canvas1;
			case 1:
				return canvas2;
			case 2:
				return canvas3;
			case 3:
				return canvas4;
			case 4:
				return canvas5;
			case 5:
				return canvas6;
			case 6:
				return canvas7;
			case 7:
				return canvas8;
			case 8:
				return canvas9;
			default:
				break;
		}
		return null;
	}
	
	
	//rÈcupÈration de rÈsultat
	public void getResult(Canvas canvas, double player) {
		if(tableau.verifieGagner(sc1, player)) {
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
	
	//Cas o˘ on selecte la case X
	public void selectCaseDrawX(Canvas canvas, int i) {
		XDraw x = new XDraw(canvas);
		sc1.getChildren().add(x);
		
		double player = -1.0;
		tableau.setCaseX(i);
		
		getResult(canvas, player);
	}
	
	//cas o˘ on selecte la case Y
	public void selectCaseDrawO(Canvas canvas, int i) {
		CircleDraw circle = new CircleDraw(canvas);
		sc1.getChildren().add(circle);
		
		double player = 1.0;
		tableau.setCaseO(i);
		
		getResult(canvas, player);
	}
	
	//Selection d'une case
	public void selectCase(Canvas canvas,int i) {
		if((!canvas.isDisable()) && ((nb%2)!=0)) {
			selectCaseDrawX(canvas, i);
		}
		else if((!canvas.isDisable()) && ((nb%2)==0)){
			selectCaseDrawO(canvas, i);
		}
	}
	
	//rÈinitialiser le jeu
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
		nb=1;gagne=false;reinit=true;isIATurn=false;
	}
	
	//Lors du chargement de la scËne
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ToggleSwitch toggle= new ToggleSwitch();
		toggle.setTranslateY(68);
		toggle.setTranslateX(770);
	
		setButton(null,Color.WHITE,menu);
		
		sc1.getChildren().add(toggle);
		
		addImageToYellowTheme();
		addImageToPinkTheme();
		addImageToGreenTheme(); 
		addCanvasToList();
	}
}
