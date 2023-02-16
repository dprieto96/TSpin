package exceptions;



public class GameException extends RuntimeException {

	private static final long serialVersionUID = -805161808903720127L;

	public GameException(String typeExpection) {
		super("[ERROR]: "+typeExpection);
	}
	
	
	
	
	
}
