package lgs;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		//i, j >= 1
		double[][] array = new double[1][0];
		Random random = new Random();
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++){
				array[i][j] = random.nextDouble();
			}
		}
		try {
			Matrix matrix = new Matrix(array);
			System.out.println(matrix);
			matrix.solve();
		} catch(NoMatrixException e) {
			e.printStackTrace();
		}
	}

}
