package application.controller;


import java.io.IOException;

import ai.Config;
import application.Main;
import application.models.IaModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SceneController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Config config;
	
	
	//changement de scene vers ApprentissageMenu
	public void switchToApprentissageController(ActionEvent event,Config config,Color color) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/./vue/Apprentissage.fxml"));
		root =loader.load();
		scene = new Scene(root);
		ApprentissageController controller= loader.getController();
		controller.setConfig(config);
		if(color==Color.LIGHTYELLOW) {
			controller.setTheme(color,true,false,false);
		}
		else if(color==Color.LIGHTPINK) {
			controller.setTheme(color,false,true,false);
		}
		else {
			controller.setTheme(color,false,false,true);
		}
		stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	//changement de scene vers MenuNiveau
	public void switchToMenuNiveauController(MouseEvent event,Color color) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/./vue/MenuNiveau.fxml"));
		root =loader.load();
		scene = new Scene(root);
		MenuNiveauController controller= loader.getController();
		if(color==Color.LIGHTYELLOW) {
			controller.setTheme(color,true,false,false);
		}
		else if(color==Color.LIGHTPINK) {
			controller.setTheme(color,false,true,false);
		}
		else {
			controller.setTheme(color,false,false,true);
		}
		stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
	}
	
	//changement de scene vers MenuAdversaire
	public void switchToMenuAdversaireController(ActionEvent event,Color color) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/./vue/MenuAdversaire.fxml"));
		root =loader.load();
		scene = new Scene(root);
		MenuAdversaireController controller= loader.getController();
		if(color==Color.LIGHTYELLOW) {
			controller.setTheme(color,true,false,false);
		}
		else if(color==Color.LIGHTPINK) {
			controller.setTheme(color,false,true,false);
		}
		else {
			controller.setTheme(color,false,false,true);
		}
		Stage stage = (Stage) Main.getPrimaryStage().getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	//changement de scène vers Jeu via ActionEvent
	public void switchToJeuController(ActionEvent event,Color color,boolean isIA, String level) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/./vue/Jeu.fxml"));
		root =loader.load();
		scene = new Scene(root);
		JeuController controller= loader.getController();
		if(color==Color.LIGHTYELLOW) {
			controller.setTheme(color,true,false,false);
		}
		else if(color==Color.LIGHTPINK) {
			controller.setTheme(color,false,true,false);
		}
		else {
			controller.setTheme(color,false,false,true);
		}
		controller.setIAGame(isIA);
		controller.setIaLevel(level);
		stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	//changement de scène vers Jeu via MouseEvent
		public void switchToJeuController(MouseEvent event,Color color,boolean isIA) throws IOException {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/./vue/Jeu.fxml"));
			root =loader.load();
			scene = new Scene(root);
			JeuController controller= loader.getController();
			if(color==Color.LIGHTYELLOW) {
				controller.setTheme(color,true,false,false);
			}
			else if(color==Color.LIGHTPINK) {
				controller.setTheme(color,false,true,false);
			}
			else {
				controller.setTheme(color,false,false,true);
			}
			controller.setIAGame(isIA);
			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}
	
}
