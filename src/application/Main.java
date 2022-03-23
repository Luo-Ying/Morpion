package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private static Stage primaryStage;
	private Pane mainLayout;

	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.setPrimaryStage(primaryStage);
		Main.getPrimaryStage().setTitle("Jeu de Morpion");
		showMainView();
	}

	private void showMainView() throws IOException {
//	Parent root= FXMLLoader.load(getClass().getResource("./vue/MenuNiveau.fxml"));
//	Scene scene = new Scene(root);
//	getPrimaryStage().setScene(scene);
//	getPrimaryStage().show();
	FXMLLoader loader = new FXMLLoader();
	
	loader.setLocation(Main.class.getResource("./vue/MenuAdversaire.fxml"));
	mainLayout = loader.load();
	Scene scene = new Scene(mainLayout);
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

