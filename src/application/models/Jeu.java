package application.models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Jeu {
	private double tableau[];
	
	public boolean isWinPlayer1 = false;
	public boolean isWinPlayer2 = false;
	
	public boolean top_Horizontal_Line = false;
	public boolean middle_Horizontal_Line = false;
	public boolean bottom_Line = false;
	public boolean left_Vertical_Line = false;
	public boolean middle_Vertical_Line = false;
	public boolean right_Vertical_Line = false;
	public boolean upper_Left_Diagonal = false;
	public boolean upper_Right_Diagonal = false;
	
	public Line line;
	
	public Jeu() {
		
		this.tableau = new double[9];
		
		for(int i=0; i<this.tableau.length; i++) {
			this.tableau[i] = 0.0;
		}
	}
	
	public void setCaseX(int i) {
		this.tableau[i] = -1.0;
	}
	
	public void setCaseO(int i) {
		this.tableau[i] = 1.0;
	}
	
	public void roundIA() {
//		double[] res = play(net, c);
//		res = play(net, c);
		
	}
	
	public boolean defineParametres(double player, int index1, int index2, int index3) {
		if(this.tableau[index1] == player && this.tableau[index2] == player && this.tableau[index3] == player) {			
			if(player == -1.0) isWinPlayer1 = true; else isWinPlayer2 = true;
			return true;
		}
		return false;
	}
	
	public void isWin(double player) {

		if (this.tableau[0] == this.tableau[1] && this.tableau[1] == this.tableau[2]) {
			top_Horizontal_Line = defineParametres(player, 0, 1, 2);
		}
		if (this.tableau[3] == this.tableau[4] && this.tableau[4] == this.tableau[5]) {
			middle_Horizontal_Line = defineParametres(player, 3, 4, 5);
		}
		if (this.tableau[6] == this.tableau[7] && this.tableau[7] == this.tableau[8]) {
			bottom_Line = defineParametres(player, 6, 7, 8);
		}
		if (this.tableau[0] == this.tableau[3] && this.tableau[3] == this.tableau[6]) {
			left_Vertical_Line = defineParametres(player, 0, 3, 6);
		}
		if (this.tableau[1] == this.tableau[4] && this.tableau[4] == this.tableau[7]) {
			middle_Vertical_Line = defineParametres(player, 1, 4, 7);
		}
		if (this.tableau[2] == this.tableau[5] && this.tableau[5] == this.tableau[8]) {
			right_Vertical_Line = defineParametres(player, 2, 5, 8);
		}
		if (this.tableau[0] == this.tableau[4] && this.tableau[4] == this.tableau[8]) {
			upper_Left_Diagonal = defineParametres(player, 0, 4, 8);
		}
		if (this.tableau[2] == this.tableau[4] && this.tableau[4] == this.tableau[6]) {
			upper_Right_Diagonal = defineParametres(player, 2, 4, 6);
		}
	}
	

	public boolean verifieGagner(Pane pane, double player) {
		
		isWin(player);
		if(isWinPlayer1 || isWinPlayer2) {
			System.out.println("win");
//			System.out.println("Top_Horizontal_Line = " + top_Horizontal_Line);
//			System.out.println("Middle_Horizontal_Line = " + middle_Horizontal_Line);
//			System.out.println("Bottom_Line = " + bottom_Line);
//			System.out.println("Left_Vertical_Line = " + left_Vertical_Line);
//			System.out.println("Middle_Vertical_Line = " + middle_Vertical_Line);
//			System.out.println("Right_Vertical_Line = " + right_Vertical_Line);
//			System.out.println("Upper_Left_Diagonal = " + upper_Left_Diagonal);
//			System.out.println("Upper_Right_Riagonal = " + upper_Right_Diagonal);
			if(top_Horizontal_Line) {
				drawLine(pane,-88.00001525878906,82,460.99993896484375,82,242,135);
				return true;
			}
			else if(middle_Horizontal_Line) {
				drawLine(pane,-88.00001525878906,82,460.99993896484375,82,254,298);
				return true;
			}
			else if(bottom_Line) {
				drawLine(pane,-88.00001525878906,82,460.99993896484375,82,242,461);
				return true;
			}
			else if(left_Vertical_Line) {
				drawLine(pane,88.66665649414062,394.3333740234375,88.66665649414062,-83.99998474121094,168,225);
				return true;
			}
			else if(middle_Vertical_Line) {
				drawLine(pane,88.66665649414062,394.3333740234375,88.66665649414062,-83.99998474121094,355,217);
				return true;
			}
			else if(right_Vertical_Line) {
				drawLine(pane,88.66665649414062,394.3333740234375,88.66665649414062,-83.99998474121094,539,217);
				return true;
			}
			else if(upper_Left_Diagonal) {
				drawLine(pane,20.666641235351562,-24.666671752929688,523.3333129882812,426,167,178);
				return true;
			}
			else if(upper_Right_Diagonal) {
				drawLine(pane,-158.6666717529297,373,327.33331298828125,-62.33332824707031,357,225);
				return true;
			}
		}
		return false;
	}
	
	public void drawLine(Pane pane,double x1,double y1,double x2, double y2,double x, double y){
		this.line = new Line(x1,y1,x2,y2);
		line.setLayoutX(x);
		line.setLayoutY(y);
		line.setStroke(Color.NAVY);
		line.setStrokeWidth(5);
		pane.getChildren().add(line);
	}

	public double[] getTableau() {
		return tableau;
	}

	public void setTableau(double[] tableau) {
		this.tableau = tableau;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
}

