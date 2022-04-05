package application.models;

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
	
	public static double Epochs = 100000 ;
	

	public IaModel(Config config) {
		this.config = config;
		this.lr = config.learningRate;
		this.l = config.numberOfhiddenLayers;
		this.h = config.hiddenLayerSize;
		
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
