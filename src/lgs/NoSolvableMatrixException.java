package lgs;

/**
 * MatrixException, falls Matrix nicht gelöst werden kann
 * @author Markus Müller
 */
public class NoSolvableMatrixException extends MatrixException {

	private static final long serialVersionUID = -4188331485699582902L;

	public NoSolvableMatrixException(String msg) {
		super(msg);
	}
	
}
