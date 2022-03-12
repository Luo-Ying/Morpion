package view;

import java.util.ArrayList;
import java.util.HashMap;

import ai.Coup;
import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class MainViewController {

    @FXML
    private Pane sc1;

    @FXML
    private TextField textfield;

    @FXML
    private Button start;

    @FXML
    private Button cancel;
    
    @FXML
    private ProgressBar pgbar;
    
 //   @FXML
//   private ProgressIndicator pgIndicator;
    
    
    Task worker;
   
    @FXML
    void task(ActionEvent event) throws InterruptedException {
    	System.out.println("ok");
    	worker = createWorker();
    	pgbar.setProgress(0);
    	pgbar.progressProperty().unbind();
    	pgbar.progressProperty().bind(worker.progressProperty());
    	new Thread(worker).start();
    	
			
    }
    
    @FXML
    void stopTask(ActionEvent event) {
    	worker.cancel(true);
    	pgbar.progressProperty().unbind();
    	pgbar.setProgress(0);
        System.out.println("cancelled.");
        
        
        
    }
    
    public Task createWorker() {
        return new Task() {
        	@Override
            protected Object call() throws Exception {
        		for(int i = 0; i < 100; i++){
                    Thread.sleep(50);
                    updateProgress(i + 1, 100);
                }
                updateMessage("Finish");
                return null;
            }
        };
    }


}
