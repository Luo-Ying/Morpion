package application.controller;


import java.io.IOException;

import ai.Config;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Config config;
	
	

	public void switchToApprentissageController(ActionEvent event,Config config) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/./vue/Apprentissage.fxml"));
		root =loader.load();
		scene = new Scene(root);
		ApprentissageController controller= loader.getController();
		controller.setConfig(config);
		stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToMenuNiveauController(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/./vue/MenuNiveau.fxml"));
		root =loader.load();
		scene = new Scene(root);
		stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
	}
}
