package lgs;

import java.util.Random;

public class Main {

	/**
	 * aktuell nur zu Testzwecken
	 * @param args
	 */
	public static void main(String[] args) {
		//Anlegen eines Arrays der Groe�e i x j
		//zuf�llige Belegung mit Werten zwischen 0 und 100
		//i, j >= 1
		double[][] array = new double[3][4];
		Random random = new Random();
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++){
				array[i][j] = (int)(random.nextDouble() * 100);
			}
		}
		//Matrix anlegen und Array einlesen
		//Konsolenausgabe der Matrix
		//L�sen der Matrix
		try {
			//Matrix matrix = new Matrix(array);
			FileIO neueMatrix = new FileIO(); //ge�ndert Ilja
			Matrix matrix = new Matrix(neueMatrix.getMatrix()); //ge�ndert Ilja
			System.out.println(matrix);
			double[] solution = new double[array.length]; 
			solution = matrix.solve();
			System.out.println("L�sung:\n" + Matrix.toString(solution));
		} catch(NoMatrixException e) {
			e.printStackTrace();
		}
	}

}
