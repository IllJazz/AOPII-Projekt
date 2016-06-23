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

	//overwrite from IOSteam... syso(Matrix)->print Matrix to console
	public void print() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public double[] solve() {
		return null;
	}
	
}
