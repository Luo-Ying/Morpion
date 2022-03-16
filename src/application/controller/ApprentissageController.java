package application.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;



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
    private Pane sc1; //sc1=Apprentissage
    
    @FXML
    private Pane sc2; //sc2=MenuAdversaire

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
    private Button colorGreen;
    
    @FXML
    private Button colorPink;
    
    @FXML
    private Button colorYellow;
    
    @FXML
    private Button buttonVs;

    @FXML
    private Button buttonVs2;
    
    
   //on click start of learning IA
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
    public Task<?> displayProgressText(HashMap<Integer, Coup> mapTrain,int[] layers,double lr) {
    	File file= new File("src/resultat/mlp.ser");
    	
    	
        return new Task<Object>() {
        	
        	File fichier =  new File("./src/result/mlp.ser") ;
        	
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
    public class ToggleSwitch extends Parent {
    	public BooleanProperty switchedOn = new SimpleBooleanProperty(false);
    	public TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    	public FillTransition fillAnimation= new FillTransition(Duration.seconds(0.25));
    	public ParallelTransition animation = new ParallelTransition(translateAnimation,fillAnimation);
    	
    	
    	public BooleanProperty switchedOnProperty() {
    		return switchedOn;
    	}
    	
    	
    	
    	ToggleSwitch() {
    		Rectangle background = new Rectangle(50,25);
    		background.setArcWidth(25);
    		background.setArcHeight(25);
    		background.setFill(Color.LIGHTBLUE);
    		background.setStroke(Color.LIGHTGREY);
    		
    		Circle trigger = new Circle(12.5);
    		trigger.setCenterX(40);
    		trigger.setCenterY(12.5);
    		trigger.setFill(Color.WHITE);
    		trigger.setStroke(Color.LIGHTGREY);
    		
    		translateAnimation.setNode(trigger);
    		fillAnimation.setShape(background);
    	
    		getChildren().addAll(background,trigger);
    		
    		switchedOn.addListener((observer, oldVal, newVal) -> {
    			boolean isOn = newVal.booleanValue();
    			translateAnimation.setToX(isOn ? -30 : 0);
    			animation.play();
    			fillAnimation.setFromValue(isOn ? Color.LIGHTBLUE : Color.WHITE);
    			fillAnimation.setToValue(isOn ? Color.WHITE : Color.LIGHTBLUE);
    			});
    		setOnMouseClicked(event ->{
    			switchedOn.set(!switchedOn.get());
    		});
    	}
    }
    
    @FXML
    void changePink(ActionEvent event) {
    	sc2.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
    }

    @FXML
    void changeGreen(ActionEvent event) {
    	sc2.setBackground(new Background(new BackgroundFill(Color.rgb(210,252,209), null, null)));
    }

    @FXML
    void changeYellow(ActionEvent event) {
    	sc2.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));
    }


	@Override
	public void start(Stage arg0) throws Exception {
			
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sc2.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));
		
		ToggleSwitch toggle= new ToggleSwitch();
		toggle.setTranslateY(68);
		toggle.setTranslateX(770);
		colorGreen.setBackground(new Background(new BackgroundFill(Color.rgb(210,252,209), null, null)));
		colorGreen.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		colorPink.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
		colorPink.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		colorYellow.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));
		colorYellow.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		buttonVs.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		buttonVs.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		buttonVs2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		buttonVs2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		sc2.getChildren().add(toggle);
		
	}

}
