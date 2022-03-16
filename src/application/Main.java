package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private Stage primaryStage;
	private Pane mainLayout;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Jeu de Morpion");
		showMainView();
	}

	private void showMainView() throws IOException {
	FXMLLoader loader = new FXMLLoader();
	
	loader.setLocation(Main.class.getResource("./vue/MenuAdversaire.fxml"));
	mainLayout = loader.load();
	Scene scene = new Scene(mainLayout);
	primaryStage.setScene(scene);
	primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}

