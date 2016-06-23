package lgs;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		double[][] array = new double[2][2];
		Random random = new Random();
		for(double[] i: array) {
			for(double j: i){
				j = random.nextDouble()*1000;
			}
		}
		try {
			Matrix matrix = new Matrix(array);
			matrix.solve();
		} catch(NoMatrixException e) {
			e.printStackTrace();
		}
	}

}
