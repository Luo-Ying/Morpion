package application.models;

import java.util.Arrays;

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
	
	//création d'un tableau de jeu
	public Jeu() {
		
		this.tableau = new double[9];
		
		for(int i=0; i<this.tableau.length; i++) {
			this.tableau[i] = 0.0;
		}
	}
	
	//définir la caseX
	public void setCaseX(int i) {
		this.tableau[i] = -1.0;
	}
	
	//définir la caseO
	public void setCaseO(int i) {
		this.tableau[i] = 1.0;
	}
	
	//définition case IA donc O
	public int roundIA(String level) {
		System.out.println(level);
		IaModel iaModel = new IaModel();
		iaModel.setLevel(level);
		double[] res = iaModel.play(this.tableau);
		System.out.println(Arrays.toString(res));
		double max = 0.0;
		int index = 0;
		for(int i=0; i<res.length; i++) {
			if(res[i] > max) {
				max = res[i];
				index = i;
				System.out.println("max: " + max + " index: " + index);
			}
		}
		System.out.println("tableau: " + Arrays.toString(this.tableau));
		return index;
	}
	
	//On définit les parametres
	public boolean defineParametres(double player, int index1, int index2, int index3) {
		if(this.tableau[index1] == player && this.tableau[index2] == player && this.tableau[index3] == player) {			
			if(player == -1.0) isWinPlayer1 = true; else isWinPlayer2 = true;
			return true;
		}
		return false;
	}
	
	//Definir les parametres les  si on a gagné
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
	
	//verifie si on a gagné
	public boolean verifieGagner(Pane pane, double player) {
		
		isWin(player);
		if(isWinPlayer1 || isWinPlayer2) {
			System.out.println("win");
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
	
	//dessiner une ligne si on a gagné
	public void drawLine(Pane pane,double x1,double y1,double x2, double y2,double x, double y){
		this.line = new Line(x1,y1,x2,y2);
		line.setLayoutX(x);
		line.setLayoutY(y);
		line.setStroke(Color.NAVY);
		line.setStrokeWidth(5);
		pane.getChildren().add(line);
	}

	//récupérer tableau
	public double[] getTableau() {
		return tableau;
	}

	//définir tableau
	public void setTableau(double[] tableau) {
		this.tableau = tableau;
	}
	
	//récuperer line à dessiner lors d'un win
	public Line getLine() {
		return line;
	}

	//définir ligne à dessiner lors d'un win
	public void setLine(Line line) {
		this.line = line;
	}
}

