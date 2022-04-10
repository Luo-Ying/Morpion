package application;
	
import java.io.IOException;

import application.animation.ToggleSwitch;
import application.controller.MenuAdversaireController;
import application.models.Music;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private static Stage primaryStage;
	private Pane mainLayout;
	private ToggleSwitch toggle;

	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.setPrimaryStage(primaryStage);
		Main.getPrimaryStage().setTitle("Jeu de Morpion");
		Main.getPrimaryStage().getIcons().add(new Image(Main.class.getResourceAsStream("/images/Icon.png")));
		Music.playMusic();
		showMainView();
	}

	private void showMainView() throws IOException {
	
	FXMLLoader loader = new FXMLLoader();
	
	loader.setLocation(Main.class.getResource("./vue/MenuAdversaire.fxml"));
	mainLayout = loader.load();
	Scene scene = new Scene(mainLayout);
	MenuAdversaireController controller= loader.getController();
	controller.firstSet(toggle);
	getPrimaryStage().setScene(scene);
	getPrimaryStage().show();
	}
	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		Main.primaryStage = primaryStage;
	}
}

