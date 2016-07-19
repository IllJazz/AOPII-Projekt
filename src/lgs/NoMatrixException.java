package lgs;

/**
 * MatrixException, falls keine Matrix (fehlerhafte Indizierung)
 * @author Markus Müller
 */
public class NoMatrixException extends MatrixException {

	private static final long serialVersionUID = 2876835609672118229L;

	public NoMatrixException(String msg) {
		super(msg);
	}
	
}
