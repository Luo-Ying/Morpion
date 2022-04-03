package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Jeu {
	public  int tableauX[][];
	public  int tableauO[][];
	
	public Jeu() {
		this.tableauX = new int [3][3];
		this.tableauO = new int [3][3];
		
		for (int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				this.tableauX[i][j]=0;
				this.tableauO[i][j]=0;
			}
		}
	}
	
	public void setCaseX(int i, int j) {
		this.tableauX[i][j]=1;
	}
	public void setCaseY(int i, int j) {
		this.tableauO[i][j]=1;
	}
	
	public int[][] getTableauX() {
		return tableauX;
	}

	public int[][] getTableauO() {
		return tableauO;
	}

	public boolean verifieGagner(int choix[][],Pane pane) {
		if(loopHorizontal(0,choix)==3) {
			drawLine(pane,-88.00001525878906,82,460.99993896484375,82,242,135);
			return true;
		}
		else if(loopHorizontal(1,choix)==3) {
			drawLine(pane,-88.00001525878906,82,460.99993896484375,82,254,298);
			return true;
		}
		else if(loopHorizontal(2,choix)==3) {
			drawLine(pane,-88.00001525878906,82,460.99993896484375,82,242,461);
			return true;
		}
		else if(loopVertical(0,choix)==3) {
			drawLine(pane,88.66665649414062,394.3333740234375,88.66665649414062,-83.99998474121094,168,225);
			return true;
		}
		else if(loopVertical(1,choix)==3) {
			drawLine(pane,88.66665649414062,394.3333740234375,88.66665649414062,-83.99998474121094,355,217);
			return true;
		}
		else if(loopVertical(2,choix)==3) {
			drawLine(pane,88.66665649414062,394.3333740234375,88.66665649414062,-83.99998474121094,539,217);
			return true;
		}
		else if(loopCrossLeftToRight(choix)==3) {
			drawLine(pane,20.666641235351562,-24.666671752929688,523.3333129882812,426,167,178);
			return true;
		}
		else if(loopCrossRightToLeft(choix)==3) {
			drawLine(pane,-158.6666717529297,373,327.33331298828125,-62.33332824707031,357,225);
			return true;
		}
		return false;
	}
	
	public int loopHorizontal(int position,int choix[][]) {
		int gagne=0;
		for(int i=0;i<3;i++) {
			if (choix[position][i]==1) {
				gagne+=1;
			}
		}
		return gagne;
	}
	public int loopVertical(int position,int choix[][]) {
		int gagne=0;
		for(int i=0;i<3;i++) {
			if (choix[i][position]==1) {
				gagne+=1;
			}
		}
		return gagne;
	}
	public int loopCrossLeftToRight(int choix[][]) {
		int gagne=0;
		for(int i=0;i<3;i++) {
			if (choix[i][i]==1) {
				gagne+=1;
			}
		}
		return gagne;
	}
	public int loopCrossRightToLeft(int choix[][]) {
		int gagne=0;
		int j=2;
		for(int i=0;i<3;i++) {
			if (choix[i][j]==1) {
				gagne+=1;
			}
			j-=1;
		}
		return gagne;
	}
	
	public void drawLine(Pane pane,double x1,double y1,double x2, double y2,double x, double y){
		Line line = new Line(x1,y1,x2,y2);
		line.setLayoutX(x);
		line.setLayoutY(y);
		line.setStroke(Color.NAVY);
		line.setStrokeWidth(5);
		pane.getChildren().add(line);
	}
}

