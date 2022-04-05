package application.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import ai.Config;
import ai.Coup;
import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;

public class IaModel {
	
	private Config config;
	
	private double lr;
	private int l;
	private int h;
	
	private double error = 0.0;
	
	private MultiLayerPerceptron net;
	
	private Coup coup;
	
	private double[] res;
	
	public static double Epochs = 100000 ;
	
	public static HashMap<Integer, Coup> mapTrain;
	public static HashMap<Integer, Coup> mapDev;
	

	public IaModel(Config config) {
		this.config = config;
		this.lr = config.learningRate;
		this.l = config.numberOfhiddenLayers;
		this.h = config.hiddenLayerSize;
		
		mapTrain = loadCoupsFromFile("./resources/train_dev_test/train.txt");
		mapDev = loadCoupsFromFile("./resources/train_dev_test/dev.txt");
		
		this.coup = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));
		
	}
	
	public void play() {
		
	}
	
	public static HashMap<Integer, Coup> loadCoupsFromFile(String file){
		System.out.println("loadCoupsFromFile from "+file+" ...");
		HashMap<Integer, Coup> map = new HashMap<>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
			String s = "";
			while ((s = br.readLine()) != null) {
				String[] sIn = s.split("\t")[0].split(" ");
				String[] sOut = s.split("\t")[1].split(" ");

				double[] in = new double[sIn.length];
				double[] out = new double[sOut.length];

				for (int i = 0; i < sIn.length; i++) {
					in[i] = Double.parseDouble(sIn[i]);
				}

				for (int i = 0; i < sOut.length; i++) {
					out[i] = Double.parseDouble(sOut[i]);
				}

				Coup c = new Coup(9, "");
				c.in = in ;
				c.out = out ;
				map.put(map.size(), c);
			}
			br.close();
		} 
		catch (Exception e) {
			System.out.println("Test.loadCoupsFromFile()");
			e.printStackTrace();
			System.exit(-1);
		}
		return map ;
	}
	
	public Coup getCoup() {
		return this.coup;
	}
	
	public void setCoup(HashMap<Integer, Coup> mapTrain0) {
		this.coup = mapTrain0.get((int)(Math.round(Math.random() * mapTrain0.size())));
	}
	
	
	public Config getConfig() {
		return config;
	}


	public void setConfig(Config config) {
		this.config = config;
	}


	public void setError(Coup c) {
		this.error += net.backPropagate(c.in, c.out);
	}

	public double getLr() {
		return lr;
	}
	
	public void setLr(double lr) {
		this.lr = lr;
	}
	
	public int getL() {
		return l;
	}
	
	public void setL(int l) {
		this.l = l;
	}
	
	public int getH() {
		return h;
	}
	
	public void setH(int h) {
		this.h = h;
	}
	
	public double getError() {
		return error;
	}
	
	public void setError(double error) {
		this.error = error;
	}
	
	public MultiLayerPerceptron getNet() {
		return net;
	}
	
	public void setNet(int[] layers) {
		this.net = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction());
		
	}
	
}
