package lgs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * Klasse zur Verwaltung und Lösung einer Matrix
 * für die Lösung wird die Matrix ferner als Matrix a und Lösungsvektor b abgespeichert
 * unter m wird die Anzahl Zeilen der Matrix und unter n die Anzahl Spalten (Spalten von a + 1 von b) gehalten
 * @author Markus Müller
 */
public class Matrix {

//	Matrix a * Variablenvektor x = Lösungsvektor b
	private double[][] a;
	private double[] b;
//	m Zeilen und n Spalten (Spalten von a + 1 von b)
	private int m, n;
	
	/**
	 * erzeugt Matrix aus übergebenem Array
	 * Array muss mindestens 1 x 1 groß sein
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
			RuntimeException re = new RuntimeException("übergebenes Array oder Teile davon nicht initialisiert!", e);
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
	 * ermittelt die Anzahl der Lösungen aus dem LGS der Matrix
	 * @param array double-Array, aus welchem die Matrix erzeugt werden soll
	 * @throws NoMatrixException keine Matrix erzeugt, falls keine Elemente im Array, also m == 0 oder n == 0
	 * 							 oder falls Zeilen unterschiedlich viele Spalten haben, also keine m x n Form
	 */
	public LESType getLESType() {
		if(n < 2) {
			throw new NoSolvableMatrixException("Matrix mit nur einer Spalte (Vektor) kann nicht gelöst werden!");
		}
		double[][] alocal = new double[m][n];
		for (int i = 0; i < alocal.length; i++)
		     alocal[i] = Arrays.copyOf(a[i], a[i].length);
		double[] blocal = Arrays.copyOf(b, b.length);
		
		double factor;
//		Gauss-Algorithmus, Erzeugung einer Dreiecksmatrix
		for(int i = 0; i < m - 1; i++) {
			
			//überprüfung der Ausgangszahl, falls 0 muss Spaltentausch ausgeführt werden
			boolean switched = false;
			if(alocal[i][i] == 0) {
				for(int count = i + 1; count < n; count++) {
					if(alocal[i][count] != 0) {
						//Spaltentausch
						for(int p = 0; p < m; p++) {
							double temp = alocal[p][i];
							alocal[p][i] = alocal[p][count];
							alocal[p][count] = temp;
						}
						switched = true;
						break;
					}
				}
			}
			if(!switched && alocal[i][i] == 0) {
				if(blocal[i] == 0) {
					return LESType.MULTIPLE;
				} else {
					return LESType.NONE;
				}
			}
			
			for(int j = i + 1; j < m; j++){
				factor = - alocal[j][i] / alocal[i][i];
				for(int l = 0; l < n - 1; l++) {
					alocal[j][l] += factor * alocal[i][l];
				}
				blocal[j] += factor * blocal[i];
			}
		}
//		Überprüfung auf Nullen
		boolean[] zerolines = new boolean[alocal.length];
		for(int i = 0; i < alocal.length; i++) {
			int zeros = 0;
			for(int j = 0; j < alocal[0].length; j++) {
				if(alocal[i][j] == 0) {
					zeros++;
				}
			}
			if(zeros == alocal[i].length) {
				zerolines[i] = true;
			} else {
				zerolines[i] = false;
			}
		}
		int counter = 0;
		for(int i = 0; i < zerolines.length; i++) {
			if(zerolines[i]) {
//				falls in einer Zeile nur Nullen stehen und in b eine Zahl ungleich Null -> Ungleichung(keine Lösung)
				if(blocal[i] != 0) {
					return LESType.NONE;
				} else {
					counter++;
				}
			}
		}
		if(alocal[0].length > (zerolines.length - counter)) {
			return LESType.MULTIPLE;
		}
		return LESType.ONE;
	}
	
//	/**
//	 * Zeile mit wenigsten Nullen wird in der Matrix mit der ersten(nullten) Zeile vertauscht
//	 */
//	public void pivoting() {
//		int[] zeros = new int[a.length];
//		for(int i = 0; i < a.length; i++) {
//			for(int j = 0; j < a[0].length; j++) {
//				if(a[i][j] == 0) {
//					zeros[i]++;
//				}
//			}
//		}
//		int line = 0;
//		for(int i = 1; i < a.length; i++) {
//			if(zeros[i - 1] < zeros[i] && a[i][0] != 0) {
//				line = i;
//			}
//		}
//		for(int i = 1; i < a[0].length; i++) {
//			double temp = a[0][i];
//			a[0][i] = a[line][i];
//			a[line][i] = temp;
//		}
//	}
	
	/**
	 * 
	 * @return solution Lösungsvektor Matrix (LGS)
	 * @throws NoSolvableMatrixException Matrix wird nicht gelöst, falls weniger als zwei Spalten vorhanden sind
	 * 									 kann dann keine Lösung errechnet werden (aus einem Vektor)
	 */
	public double[] solve() throws NoSolvableMatrixException {
		if(n < 2) {
			throw new NoSolvableMatrixException("Matrix mit nur einer Spalte (Vektor) kann nicht gelöst werden!");
		}
		double[] solution = new double[m];
		double[][] a = new double[m][n];
		for (int i = 0; i < a.length; i++)
		     a[i] = Arrays.copyOf(this.a[i], this.a[i].length);
		double[] b = Arrays.copyOf(this.b, this.b.length);
		double factor;
//		Gauss-Algorithmus, Erzeugung einer Dreiecksmatrix
		boolean[][] switchLines = new boolean[m][m];
		for(int m1 = 0; m1 < m; m1++) {
			for(int m2 = 0; m2 < m; m2++) {
				switchLines[m1][m2] = false;
			}
		}
		for(int i = 0; i < m - 1; i++) {
			if(a[i][i] == 0) {
				for(int count = i + 1; count < n; count++) {
					if(a[i][count] != 0) {
						//Spaltentausch
						for(int p = 0; p < m; p++) {
							double temp = a[p][i];
							a[p][i] = a[p][count];
							a[p][count] = temp;
						}
						switchLines[i][count] = true;
						break;
					}
				}
			}
			
			for(int j = i + 1; j < m; j++){
				factor = - a[j][i] / a[i][i];
				for(int l = 0; l < n - 1; l++) {
					a[j][l] += factor * a[i][l];
				}
				b[j] += factor * b[i];
			}
		}
		
//		Weiterarbeiten nach Gauss-Jordan-Verfahren, Erzeugung der Hauptdiagonalen
		for(int i = m - 1; i > 0; i--) {
			for(int j = i - 1; j > -1; j--){
				factor = - a[j][i] / a[i][i];
				for(int l = n - 2; l > 0; l--) {
					a[j][l] += factor * a[i][l];
				}
				b[j] += factor * b[i];
			}
		}
		
//		Lösung aus den einzelnen Zeilen errechnen
		for(int i = 0; i < m; i ++) {
			solution[i] = round(b[i] / a[i][i], 2);
		}
		for(int m1 = 0; m1 < m; m1++) {
			for(int m2 = 0; m2 < m; m2++) {
				if(switchLines[m1][m2]) {
					double temp = solution[m1];
					solution[m1] = solution[m2];
					solution[m2] = temp;
				}
			}
		}
		return solution;
	}
	
	/**
	 * Errechnung des Residuums des gelösten LGS
	 * @return residuum Residuum aus der Lösung des LGS
	 */
	public double[] getResiduum() {
		double[] x = solve();
		double[] br = new double[x.length];
		for(int i = 0; i < x.length; i++) {
			for(int j = 0; j < x.length; j++) {
				br[i] += a[i][j] * x[j];
			}
		}
		double[] residuum = new double[x.length];
		for(int i = 0; i < x.length; i++) {
			residuum[i] = round(b[i], 2) - br[i];
		}
		return residuum;
	}
	
	/**
	 * Errechnung εa des gelösten LGS
	 * @return ea εa aus dem Residuum des LGS
	 */
	public double getEa() {
		double[] residuum = getResiduum();
		double ea = 0;
		for(int i = 0; i < residuum.length; i++) {
			ea += residuum[i] * residuum[i];
		}
		ea = Math.sqrt(ea);
		return ea;
	}
	
	/**
	 * Errechnung εr des gelösten LGS
	 * @return er εr aus dem Residuum und Vektor b des LGS
	 */
	public double getEr() {
		double ea = getEa();
		double er = 0;
		for(int i = 0; i < b.length; i++) {
			er += b[i] * b[i];
		}
		er = Math.sqrt(er);
		er = ea / er;
		return er;
	}
	
	/**
	 * Zusammenfügen von Matrix a und Lösungsvektor b in ein zweidimensionales double-Array
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
	 * für Konsolenausgaben mittels System.out.print
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
	 * Umwandlung einer double-Zahl in einen String
	 * @param double-Zahl, welche in String umgewandelt werden soll
	 * @return string Ausgabestring für eine double-Zahl
	 */
	public static String toString(double d) {
		String string = new String();
		string += round(d ,6);
		return string;
	}
	
	/**
	 * Umwandlung eines double-Array in einen String
	 * @param array double-Array, welches in String umgewandelt werden soll
	 * @return string Ausgabestring für ein double-Array
	 */
	public static String toString(double[] array) {
		String string = new String();
		int n = 0;
		for(double i: array) {
			n++;
			string += "[ " + round(i, 6) + " ]";
			if(n < array.length) {
				string += "\n";
			}
		}
		return string;
	}
	
	/**
	 * Umwandlung eines zweidimensionalen double-Arrays in einen String
	 * @param array zweidimensionales double-Array, welches in String umgewandelt werden soll
	 * @return string Ausgabestring für ein zweidimensionales double-Array
	 */
	public static String toString(double[][] array) {
		String string = new String();
		int n = 0;
		for(double[] i: array) {
			string += "[";
			n++;
			for(double j : i) {
				string += " " + round(j, 6);
			}
			string += " ]";
			if(n < array.length) {
				string += "\n";
			}
		}
		return string;
	}
	
	/**
	 * Runden einer double-Zahl auf n Stellen
	 * @param value double-Zahl
	 * 		  n Anzahl Nachkommastellen
	 * @return bd.doubleValue() double-Zahl mit Wert von value und n Nachkommastellen
	 */
	public static double round(double value, int n) {
	    if (n < 0) throw new IllegalArgumentException();
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(n, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
}
