package application.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
	
	private  MultiLayerPerceptron net;
	
	private Coup coup;
	
	private double[] res;
	
	public static double Epochs = 10000000 ;
	
	public static HashMap<Integer, Coup> mapTrain;
	public static HashMap<Integer, Coup> mapDev;
	
	private String level;
	

	public IaModel() {
		
	}
	
	public IaModel(Config config) {
		this.config = config;
		this.lr = config.learningRate;
		this.l = config.numberOfhiddenLayers;
		this.h = config.hiddenLayerSize;
		
		mapTrain = loadCoupsFromFile("./resources/train_dev_test/train.txt");
		mapDev = loadCoupsFromFile("./resources/train_dev_test/dev.txt");
		
		this.coup = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));
		
	}
	
	public double[] play(double[] in) {
		try {
			MultiLayerPerceptron net = deserialzeNet(this.level);
			double[] res = net.forwardPropagation(in);
			return res ;
		} 
		catch (Exception e) {
			System.out.println("Test.play()");
			e.printStackTrace();
			System.exit(-1);
		}

		return res ;
	}
	
	public MultiLayerPerceptron deserialzeNet (String level) {
		
		String fileName = null;
		
		if(this.level == "F") fileName = "./src/result/mlp_facile.ser";
		else if(this.level == "M") fileName = "./src/result/mlp_moyen.ser";
		else if(this.level == "D") fileName = "./src/result/mlp_difficile.ser";
		 
		System.out.println(fileName);
		
		MultiLayerPerceptron net = null;
 
		FileInputStream fin = null;
		ObjectInputStream ois = null;
 
		try {
 
			fin = new FileInputStream(fileName);
			ois = new ObjectInputStream(fin);
			net = (MultiLayerPerceptron) ois.readObject();
 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
 
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
 
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
 
		}
 
		return net;
 
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
	
	
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}

	public Coup getCoup() {
		return this.coup;
	}
	
	public void setCoup() {
		this.coup = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));
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
