package lgs;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Klasse zur Verwaltung und L�sung einer Matrix
 * f�r die L�sung wird die Matrix ferner als Matrix a und L�sungsvektor b abgespeichert
 * unter m wird die Anzahl Zeilen der Matrix und unter n die Anzahl Spalten (Spalten von a + 1 von b) gehalten
 * @author Markus M�ller
 */
public class Matrix {

//	Matrix a * Variablenvektor x = L�sungsvektor b
	private double[][] a;
	private double[] b;
//	m Zeilen und n Spalten (Spalten von a + 1 von b)
	private int m, n;
	
	/**
	 * erzeugt Matrix aus �bergebenem Array
	 * Array muss mindestens 1 x 1 gro� sein
	 * @param array double-Array, aus welchem die Matrix erzeugt werden soll
	 * @throws NoMatrixException keine Matrix erzeugt, falls keine Elemente im Array, also m == 0 oder n == 0
	 * 							 oder falls Zeilen unterschiedlich viele Spalten haben, also keine m x n Form
	 */
	public Matrix(double[][] array) throws NoMatrixException {
		try {
			m = array.length;
			n = array[0].length;
			if(m == 0 || n == 0) {
				throw new NoMatrixException("Array muss mindestens ein Element enthalten (mindestens 1 x 1)!");
			}
			for(double[] i: array) {
				if(i.length != n) {
					throw new NoMatrixException("Array ist keine m x n Matrix!");
				}
			}
		} catch(Exception e) {
			RuntimeException re = new RuntimeException("�bergebenes Array oder Teile davon nicht initialisiert!", e);
			re.printStackTrace();
		}
		a = new double[m][n-1];
		b = new double[m];
		for(int i = 0; i < m; i++) {
			b[i] = array[i][n - 1];
		}
		for(int i = 0; i < m; i++){
			for(int j = 0; j < (n - 1); j++) {
				a[i][j] = array[i][j];
			}
		}
	}
	
	/**
	 * 
	 * @return solution L�sungsvektor Matrix (LGS)
	 * @throws NoSolvableMatrixException Matrix wird nicht gel�st, falls weniger als zwei Spalten vorhanden sind
	 * 									 kann dann keine L�sung errechnet werden (aus einem Vektor)
	 */
	public double[] solve() throws NoSolvableMatrixException {
		if(n < 2) {
			throw new NoSolvableMatrixException("Matrix mit nur einer Spalte (Vektor) kann nicht gel�st werden!");
		}
		double[] solution = new double[m];
		double[][] a = this.a;
		double[] b = this.b;
//		Gauss-Algorithmus, Erzeugung einer Dreiecksmatrix
		for(int i = 0; i < m - 1; i++) {
			for(int j = i + 1; j < m; j++){
				double factor = - a[j][i] / a[i][i];
				b[j] += factor * b[i];
				for(int k = j; k < m; k++) {
					for(int l = i; l < n - 1; l++) {
						a[k][l] += factor * a[k - (j - i)][l];
					}
				}
			}
		}
//		Weiterarbeiten nach Gauss-Jordan-Verfahren, Erzeugung der Hauptdiagonalen
		for(int i = m - 1; i > 0; i--) {
			for(int j = i - 1; j > -1; j--){
				double factor = - a[j][i] / a[i][i];
				b[j] += factor * b[i];
				for(int k = j; k > -1; k--) {
					for(int l = i; l > 0; l--) {
						a[k][l] += factor * a[k - (j - i)][l];
					}
				}
			}
		}
		for(int i = 0; i < m; i ++) {
			solution[i] = b[i] / a[i][i];
		}
		return solution;
	}
	
	/**
	 * Zusammenf�gen von Matrix a und L�sungsvektor b in ein zweidimensionales double-Array
	 * @return matrix zweidimensionales double-Array aus Matrix a und Vektor b des Matrixobjekts
	 */
	public double[][] getMatrix() {
		double[][] matrix = new double[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(j != n - 1) {
					matrix[i][j] = a[i][j];
				} else {
					matrix[i][j] = b[i];
				}
			}
		}
		return matrix;
	}

	/**
	 * konvertiert Matrix in einen String
	 * f�r Konsolenausgaben mittels System.out.print
	 * Form: [ wert11 wert12 ]
	 * 		 [ wert21 wert22 ] usw.
	 * @return string Ausgabestring der Matrix
	 */
	@Override
	public String toString() {
		String string = toString(getMatrix());
		return string;
	}
	
	/**
	 * speichert Matrix in einem String zur Dateiausgabe ab
	 * Form: wert11;wert12
	 * 		 wert21;wert22 usw.
	 * @return string Dateiausgabestring der Matrix
	 */
	public String toFileString() {
		String string = new String();
		if(n != 1){
			for(int i = 0; i < m; i++){
				for(int j = 0; j < n; j++) {
					if(j == 0) {
						string += a[i][j];
					} else if(j == (n - 1)) {
						string += ";" + b[i];
						if(i != m - 1) {
							string += "\n";
						}
					} else {
						string += ";" + a[i][j];
					}
				}
			}
		} else {
			for(int i = 0; i < m; i++) {
				string += b[i];
				if(i != m - 1) {
					string += "\n";
				}
			}
		}
		return string;
	}
	
	/**
	 * Umwandlung eines double-Array in einen String
	 * @param array double-Array, welches in String umgewandelt werden soll
	 * @return string Ausgabestring f�r ein double-Array
	 */
	public static String toString(double[] array) {
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#0.00", symbol);
		String string = new String();
		int n = 0;
		for(double i: array) {
			n++;
			string += "[ " + df.format(i) + " ]";
			if(n < array.length) {
				string += "\n";
			}
		}
		return string;
	}
	
	/**
	 * Umwandlung eines zweidimensionalen double-Arrays in einen String
	 * @param array zweidimensionales double-Array, welches in String umgewandelt werden soll
	 * @return string Ausgabestring f�r ein zweidimensionales double-Array
	 */
	public static String toString(double[][] array) {
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#0.00", symbol);
		String string = new String();
		int n = 0;
		for(double[] i: array) {
			string += "[";
			n++;
			for(double j : i) {
				string += " " + df.format(j);
			}
			string += " ]";
			if(n < array.length) {
				string += "\n";
			}
		}
		return string;
	}
	
}
