package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import ai.Coup;
import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import javafx.application.Preloader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainViewController  extends Preloader implements Initializable {

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
    
    
    private Task<?> worker;
    
    private Task<?> displayText;
    
    private Thread th;
    
    
   
    @FXML
    void task(ActionEvent event) throws InterruptedException {
    	
    	//Enable the button cancel
		cancel.setDisable(false); 
    	
    	// Part for initialisation of the test
    	int size =9;
    	double lr=0.01;
    	int l=2;
    	int h=6;
    	
    	int[] layers = new int[l+2];
		layers[0] = size ;
		for (int i = 0; i < l; i++) {
			layers[i+1] = h ;
		}
		layers[layers.length-1] = size ;
		

		HashMap<Integer, Coup> mapTrain = ai.Test.loadCoupsFromFile("./resources/train_dev_test/train.txt");
		HashMap<Integer, Coup> mapDev = ai.Test.loadCoupsFromFile("./resources/train_dev_test/dev.txt");
		HashMap<Integer, Coup> mapTest = ai.Test.loadCoupsFromFile("./resources/train_dev_test/test.txt");
		
		//Creation of a new task to display text progress and  progress bar
		displayText=displayProgressText(mapTrain,layers,lr);
		
		worker = displayProgressText(mapTrain,layers,lr);
    	pgbar.progressProperty().unbind();
    	pgbar.progressProperty().bind(worker.progressProperty());
    	new Thread(worker).start();
    	
		
		//Add a listener to the task so that as it progresses the text shows the progress 
    	displayText.messageProperty().addListener((observer, oldVal, newVal) -> {
			textfield.setText(newVal);
		});
    	
    	//New thread
    	th = new Thread(displayText);
    	th.start();
	} 
	
    
    @SuppressWarnings("deprecation")
	@FXML
    void stopTask(ActionEvent event) {
    	worker.cancel(true);
    	pgbar.progressProperty().unbind();
    	pgbar.setProgress(0);
        System.out.println("cancelled.");
        
        //part for display of text
        displayText.cancel(true);
        th.stop();
        textfield.setText("The task has been cancelled");
        
        
        
    }
    
    // The function used to create the task
    public Task<?> displayProgressText(HashMap<Integer, Coup> mapTrain,int[] layers,double lr) {
    	File file= new File("src/resultat/mlp.ser");
    	
    	
        return new Task<Object>() {
        	
        	@Override
            protected Object call() throws Exception {
        		double error = 0.0 ;
        		MultiLayerPerceptron net = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction());
        		//changed the epochs so that it finishes earlier
        		double epochs = 1000000 ;
        		for(int i = 0; i < epochs; i++){

        			Coup c = null ;
        			while ( c == null )
        				c = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));

        			error += net.backPropagate(c.in, c.out);
        			
        			if ( i % 10000 == 0 ) {
//        				System.out.println("Error at step "+i+" is "+ (error/(double)i));
        				updateMessage("Error at step "+i+" is "+ (error/(double)i)); //update message in texfield
        				updateProgress((100/epochs)*i,100);//update progressbar
        			}
        		}

        		updateMessage("Task is finished");
        		//Disable the button cancel
        		cancel.setDisable(true);
        		
        	
        		try {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        			oos.writeObject(net);
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        		
				return null;
            }
        };
    }
    


	@Override
	public void start(Stage arg0) throws Exception {
			
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sc1.setBackground(new Background(new BackgroundFill(Color.rgb(242, 239, 233), null, null)));
		
	}

}
