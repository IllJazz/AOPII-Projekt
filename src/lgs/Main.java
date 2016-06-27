package lgs;

import java.util.Random;

public class Main {

	/**
	 * aktuell nur zu Testzwecken
	 * @param args
	 */
	public static void main(String[] args) {
		//Anlegen eines Arrays der Groeße i x j
		//zufällige Belegung mit Werten zwischen 0 und 10
		//i, j >= 1
		double[][] array = new double[4][5];
		Random random = new Random();
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++){
				array[i][j] = (int)(random.nextDouble() * 10);
			}
		}
		//Matrix anlegen und Array einlesen
		//Konsolenausgabe der Matrix
		//Lösen der Matrix
		try {
			Matrix matrix = new Matrix(array);
			System.out.println(matrix);
			double[] solution = new double[array.length]; 
			solution = matrix.solve();
			System.out.println("Lösung:\n" + Matrix.toString(solution));
		} catch(NoMatrixException e) {
			e.printStackTrace();
		}
	}

}
