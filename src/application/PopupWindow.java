package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.sun.glass.events.WindowEvent;

import ai.Config;
import ai.ConfigFileLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupWindow {
	
	//item a propos
	public static void displayAbout(Color color)
	{
		
	Stage popupWindow=new Stage();
	      
	popupWindow.initModality(Modality.APPLICATION_MODAL);
	popupWindow.setTitle("About");
	popupWindow.getIcons().add(new Image(Main.class.getResourceAsStream("/images/IconAbout.png")));
	
	//Set colorStyle to textarea 
	String colorStyle;

	if(color==Color.LIGHTYELLOW) {
		colorStyle = "-fx-control-inner-background : lightyellow;";
	}
	else if(color==Color.LIGHTPINK) {
		colorStyle = "-fx-control-inner-background : lightpink;";
	}
	else {
		colorStyle = "-fx-control-inner-background : honeydew";
	}
	
	
	VBox layout= new VBox(20);       
	layout.setAlignment(Pos.CENTER);
	
	
	String style = "-fx-font: normal bold 14px 'MV Boli'; -fx-line-spacing :10px; " + colorStyle;
	
	//textarea of the popup
	TextArea textArea = new TextArea();
	textArea.setText("\n\n\n Version du jeu : 1.0.0\n Crée par : Marie ZEPHIR & Yingqi LUO\n IA présentée par : Mohammed Morchid");
	textArea.setStyle(style);
	textArea.setEditable(false);
	
	layout.getChildren().add(textArea);
	
	Scene scene1= new Scene(layout, 300, 250);
	      
	popupWindow.setScene(scene1);
	      
	popupWindow.showAndWait();
	       
	}
	
	
	//item configuration
	public static void displayConfiguration(Color color)
	{
		
	//get config levels
	ConfigFileLoader cfl = new ConfigFileLoader();
	cfl.loadConfigFile("./resources/config.txt");
	Config levelF = cfl.get("F");
	Config levelM = cfl.get("M");
	Config levelD = cfl.get("D");
	
	//Create stage
	Stage popupWindow=new Stage();
	      
	popupWindow.initModality(Modality.APPLICATION_MODAL);
	popupWindow.setTitle("Configuration");
	popupWindow.setHeight(400);
	popupWindow.setWidth(400);
	popupWindow.getIcons().add(new Image(Main.class.getResourceAsStream("/images/IconConfiguration.png")));
	
	
	//MainScene
	Pane pane = new Pane();
	pane.setPrefHeight(400);
	pane.setPrefWidth(400);
	pane.setBackground(new Background(new BackgroundFill(color, null, null)));

	//Text
	Text facile = new Text();
	facile.setText("Niveau facile : \n");
	facile.setLayoutX(10);
	facile.setLayoutY(110);
	facile.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	Text moyen = new Text();
	moyen.setText("Niveau moyen : \n");
	moyen.setLayoutX(10);
	moyen.setLayoutY(170);
	moyen.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	Text difficile = new Text();
	difficile.setText("Niveau difficile : \n");
	difficile.setLayoutX(10);
	difficile.setLayoutY(240);
	difficile.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	
	
	
	
	//TextFields facile
	TextField hiddenLayerSizeF = new TextField();
	hiddenLayerSizeF.setText(Integer.toString(levelF.hiddenLayerSize));
	setSize(hiddenLayerSizeF,105,90,75,25);
	hiddenLayerSizeF.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	TextField learningRateF = new TextField();
	learningRateF.setText(Double.toString(levelF.learningRate));
	setSize(learningRateF,200,90,75,25);
	learningRateF.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	
	TextField numberOfhiddenLayersF = new TextField();
	numberOfhiddenLayersF.setText(Integer.toString(levelF.numberOfhiddenLayers));
	setSize(numberOfhiddenLayersF,300,90,75,25);
	numberOfhiddenLayersF.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	
	
	
	
	//TextFields moyen
	TextField hiddenLayerSizeM = new TextField();
	hiddenLayerSizeM.setText(Integer.toString(levelM.hiddenLayerSize));
	setSize(hiddenLayerSizeM,105,150,75,25);
	hiddenLayerSizeM.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	TextField learningRateM = new TextField();
	learningRateM.setText(Double.toString(levelM.learningRate));
	setSize(learningRateM,200,150,75,25);
	learningRateM.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	
	TextField numberOfhiddenLayersM = new TextField();
	numberOfhiddenLayersM.setText(Integer.toString(levelM.numberOfhiddenLayers));
	setSize(numberOfhiddenLayersM,300,150,75,25);
	numberOfhiddenLayersM.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	
	
	
	
	//TextFields difficile
	TextField hiddenLayerSizeD = new TextField();
	hiddenLayerSizeD.setText(Integer.toString(levelD.hiddenLayerSize));
	setSize(hiddenLayerSizeD,105,220,75,25);
	hiddenLayerSizeD.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	TextField learningRateD = new TextField();
	learningRateD.setText(Double.toString(levelD.learningRate));
	setSize(learningRateD,200,220,75,25);
	learningRateD.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	
	TextField numberOfhiddenLayersD = new TextField();
	numberOfhiddenLayersD.setText(Integer.toString(levelD.numberOfhiddenLayers));
	setSize(numberOfhiddenLayersD,300,220,75,25);
	numberOfhiddenLayersD.setStyle("-fx-font: normal bold 12px 'MV Boli';");
	
	
	
	
	//Bouton modifier
	Button modifier = new Button();
	modifier.setText("Modifier");
	modifier.setLayoutX(150);
	modifier.setLayoutY(300);
	modifier.setPrefWidth(100);
	modifier.setPrefHeight(30);
	modifier.setStyle("-fx-font: normal bold 14px 'MV Boli';");
	modifier.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
	modifier.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	
	
	
	
	//setAction bouton Modifier
	modifier.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        	File f= new File("./resources/config.txt");           //file to be deleted  
        	f.delete();
        	
        	File file = new File("./resources/config.txt");      //file to be created
        	try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	 String ligneF = "F:"+verifyText(hiddenLayerSizeF.getText())+":"+verifyText(learningRateF.getText())+":"+verifyText(numberOfhiddenLayersF.getText())+"\n";
        	 String ligneM = "M:"+verifyText(hiddenLayerSizeM.getText())+":"+verifyText(learningRateM.getText())+":"+verifyText(numberOfhiddenLayersM.getText())+"\n";
        	 String ligneD = "D:"+verifyText(hiddenLayerSizeD.getText())+":"+verifyText(learningRateD.getText())+":"+verifyText(numberOfhiddenLayersD.getText())+"\n";
        	 String ligne =ligneF+ligneM+ligneD;
        	 BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter("./resources/config.txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	 try {
				writer.write(ligne);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	popupWindow.close();
        }
    });
	
	//Include all nodes in pane
	
	pane.getChildren().addAll(facile,moyen,difficile,hiddenLayerSizeF,numberOfhiddenLayersF,learningRateF,
			hiddenLayerSizeM,numberOfhiddenLayersM,learningRateM,hiddenLayerSizeD,numberOfhiddenLayersD,learningRateD,modifier);
	Scene scene1= new Scene(pane);
	      
	popupWindow.setScene(scene1);
	      
	popupWindow.showAndWait();
	       
	}
	
	
	//item Gestion IA
	public static void displayGestionIA(Color color)
	{
	Stage popupWindow=new Stage();
	      
	popupWindow.initModality(Modality.APPLICATION_MODAL);
	popupWindow.setTitle("Gestion IA");
	popupWindow.getIcons().add(new Image(Main.class.getResourceAsStream("/images/IconGestionIA.png")));
	
	
	VBox layout = new VBox(10);
	layout.setBackground(new Background(new BackgroundFill(color, null, null)));    
	layout.setAlignment(Pos.CENTER);
	
	
	//Création de boutons
	Button btnFacile = new Button();
	Button btnMoyen = new Button();
	Button btnDifficile = new Button();
	
	boolean facile=verifyResult("src/result/mlp_facile.ser",btnFacile,"Supprimer l'apprentissage facile",layout,"green");
	boolean moyen =verifyResult("src/result/mlp_moyen.ser",btnMoyen,"Supprimer l'apprentissage moyen",layout,"goldenrod");
	boolean difficile =verifyResult("src/result/mlp_difficile.ser",btnDifficile,"Supprimer l'apprentissage difficile",layout,"red");
	
	//Si existe alors on supprime lors de l'appuie
	if(facile==true) {
		btnFacile.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	File f = new File("src/result/mlp_facile.ser") ;	
	        	f.delete();
	        	layout.getChildren().remove(btnFacile);
	        }
		});
	}
	
	//Si existe alors on supprime lors de l'appuie
	if(moyen==true) {
		btnMoyen.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	File f = new File("src/result/mlp_moyen.ser") ;	
	        	f.delete();
	        	layout.getChildren().remove(btnMoyen);
	        }
		});
	}
	//Si existe alors on suprrime lors de l'appuie
	if(difficile==true) {
		btnDifficile.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	File f = new File("src/result/mlp_difficile.ser") ;	
	        	f.delete();
	        	layout.getChildren().remove(btnDifficile);
	        }
		});
	}
	
	Scene scene1= new Scene(layout,400,400);
	      
	popupWindow.setScene(scene1);
	      
	popupWindow.showAndWait();
	       
	}
	
	//Définir les textfields
	public static void setSize(TextField field,double x, double y, double w, double h) {
		field.setLayoutX(x);
		field.setLayoutY(y);
		field.setPrefWidth(w);
		field.setPrefHeight(h);
	}
	
	//verifie si resultat d'un mpl existe, si oui alors on initialise bouton et on ajoute au Vbox
	public static boolean verifyResult(String path,Button button,String message,VBox vbox,String color) {
		File f = new File(path) ;
		if(f.exists() && f.isFile()) {
			button.setText(message);
			button.setPrefWidth(300);
			button.setPrefHeight(100);
			String style = "-fx-font: normal bold 16px 'MV Boli'; -fx-line-spacing :10px; -fx-text-fill: "+color+";";
			button.setStyle(style);
			button.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			vbox.getChildren().add(button);
			return true;
		}
		return false;
	}
	
	//Verifie si texte est null, si oui alors on retourne 0 en texte
	public static String verifyText(String text) {
		if(text.equals("")) {
			return "0";
		}
		return text;
	}
	
	//item a propos
		public static void displayWinner(Color color,String winner,List <Canvas> canvas,Pane pane)
		{
			
		Stage popupWindow=new Stage();
		      
		popupWindow.initModality(Modality.APPLICATION_MODAL);
		popupWindow.setTitle("Gagnant");
		popupWindow.getIcons().add(new Image(Main.class.getResourceAsStream("/images/Icon.png")));
		
		//Set colorStyle to textarea 
		String colorStyle;

		if(color==Color.LIGHTYELLOW) {
			colorStyle = "-fx-control-inner-background : lightyellow;";
		}
		else if(color==Color.LIGHTPINK) {
			colorStyle = "-fx-control-inner-background : lightpink;";
		}
		else {
			colorStyle = "-fx-control-inner-background : honeydew";
		}
		
		
		VBox layout= new VBox(20);       
		layout.setAlignment(Pos.CENTER);
		
		
		String style = "-fx-font: normal bold 16px 'MV Boli'; -fx-line-spacing :10px; " + colorStyle +" -fx-text-fill : navy";
		
		
		String gagnant = "\n\n\n\t     Félicitation !\n\n\tLe joueur "+winner+" a gagné!";
		//textarea of the popup
		TextArea textArea = new TextArea();
		textArea.setText(gagnant);
		textArea.setStyle(style);
		textArea.setEditable(false);
		
		layout.getChildren().add(textArea);
		
		Scene scene1= new Scene(layout, 300, 250);
		      
		popupWindow.setScene(scene1);
		      
		popupWindow.showAndWait();
		
	}
	
}
