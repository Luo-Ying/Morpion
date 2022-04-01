package application.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import ai.Config;
import ai.ConfigFileLoader;
import ai.Coup;
import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Preloader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ApprentissageController  extends Preloader implements Initializable {

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
     
    private Task<?> worker;
    
    private Task<?> displayText;
    
    private Thread th;

    @FXML
    private ImageView panda1;
    
    @FXML
    private ImageView panda2;
    
    @FXML
    private ImageView bamboo;
    
    @FXML
    private ImageView soleil;
    
    @FXML
    private ImageView petitPrince1;
    
    @FXML
    private ImageView petitPrince2;
    
    @FXML
    private ImageView pawPatrol;
    
    @FXML
    private ImageView pawPatrolIcon;
    
    private Config config;
    
    private List <ImageView> yellowTheme = new ArrayList<>();
    
    private List <ImageView> pinkTheme = new ArrayList<>();
    
    private List <ImageView> greenTheme = new ArrayList<>();

     
   //on click start of learning IA
    
    void setConfig(Config config) {
    	this.config=config;
    }
    @FXML
    void task(ActionEvent event) throws InterruptedException {
    	
    	//Enable the button cancel
		cancel.setDisable(false); 
    	
    	// Part for initialisation of the test
		System.out.println(config.level);
    	int size =9;
    	double lr=config.learningRate;
    	int l=config.numberOfhiddenLayers;
    	int h=config.hiddenLayerSize;
    	
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
		displayText=displayProgressText(mapTrain,layers,lr,config.level);
		
		worker = displayProgressText(mapTrain,layers,lr,config.level);
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
	
    //on click cancel of learning IA
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
    public Task<?> displayProgressText(HashMap<Integer, Coup> mapTrain,int[] layers,double lr,String level) {
    	File fichier;
    	if (level.equals("F")) {
    		fichier =  new File("./src/result/mlp_facile.ser") ;
    	}
    	else if (level.equals("M")) {
    		fichier =  new File("./src/result/mlp_moyen.ser") ;
    	}
    	else {
    		fichier =  new File("./src/result/mlp_difficile.ser") ;
    	}
    	
    	
        return new Task<Object>() {
        	
        	
        	@Override
            protected Object call() throws Exception {
        		double error = 0.0 ;
        		MultiLayerPerceptron net = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction());
        		//changed the epochs so that it finishes earlier
        		double epochs = 100000 ;
        		for(int i = 0; i < epochs; i++){

        			Coup c = null ;
        			while ( c == null )
        				c = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));

        			error += net.backPropagate(c.in, c.out);
        			
        			if ( i % 1000 == 0 ) {
//        				System.out.println("Error at step "+i+" is "+ (error/(double)i));
        				updateMessage("Error at step "+i+" is "+ (error/(double)i)); //update message in texfield
        				updateProgress((100/epochs)*i,100);//update progressbar
        			}
        		}
        		
        		try {
        			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
        			oos.writeObject(net) ;
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}

        		updateMessage("Task is finished");
        		//Disable the button cancel
        		cancel.setDisable(true);
        		
				return null;
            }
        };
    }
    
    public void addImageToYellowTheme() {
    	yellowTheme.add(panda1);
		yellowTheme.add(panda2);
		yellowTheme.add(bamboo);
		yellowTheme.add(soleil);
    }
    
    public void addImageToPinkTheme() {
    	pinkTheme.add(petitPrince1);
    	pinkTheme.add(petitPrince2);
    }
    public void addImageToGreenTheme() {
    	greenTheme.add(pawPatrol);
    	greenTheme.add(pawPatrolIcon);
    }
    
    public void setTheme(Color color,boolean yellow,boolean pink,boolean green) {
    	sc1.setBackground(new Background(new BackgroundFill(color, null, null)));
    	if(color==Color.LIGHTYELLOW) {
    		pgbar.setStyle("-fx-accent: gold;");
		}
		else if(color==Color.LIGHTPINK) {
			pgbar.setStyle("-fx-accent: pink;");
		}
		else {
			pgbar.setStyle("-fx-accent: green;");
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
    
	@Override
	public void start(Stage arg0) throws Exception {
			
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		start.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		start.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		cancel.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		cancel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		addImageToYellowTheme();
		addImageToPinkTheme();
		addImageToGreenTheme(); 
	}
}
