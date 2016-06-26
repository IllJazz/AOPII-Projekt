package lgs;

/**
 * 
 * @author Markus Müller
 */
public class Matrix {

	//Matrix a * Variablenvektor x = Lösungsvektor b
	private double[][] a;
	private double[] b;
	private int m, n;
	
	/**
	 * 
	 * @param array
	 * @throws NoMatrixException
	 */
	public Matrix(double[][] array) throws NoMatrixException {
		try {
			m = array.length;
			n = array[0].length;
			if(m == 0 || n == 0) {
				throw new NoMatrixException("Array muss mindestens ein Element enthalten (mindestens 1 x 1)!");
			}
			for(double[] i: array) {
				if(!(i.length == n)) {
					throw new NoMatrixException("Array ist keine m x n Matrix!");
				}
			}
		} catch(Exception e) {
			RuntimeException re = new RuntimeException("Übergebenes Array oder Teile davon nicht initialisiert!", e);
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

	@Override
	public String toString() {
		String string = new String();
		if(n != 1){
			for(int i = 0; i < m; i++){
				for(int j = 0; j < n; j++) {
					if(j == 0) {
						string += "[ " + a[i][j] ;
					} else if(j == (n - 1)) {
						string += " " + b[i] + " ]\n";
					} else {
						string += " " + a[i][j];
					}
				}
			}
		} else {
			for(int i = 0; i < m; i++) {
				string += "[ " + b[i] + " ]\n";
			}
		}
		return string;
	}
	
	/**
	 * 
	 * @return
	 */
	public double[] solve() {
		return null;
	}
	
}
