package exceptions;

/* Excepciones producidas durante la ejecuci�n del m�todo parse(),
tales como comando desconocido, n�mero de par�metros incorrecto, tipo de par�metros
no v�lido*/
public class CommandParseException extends GameException {

	
	private static final long serialVersionUID = -5361065477013583600L;

	public CommandParseException(String typeExpection){
		super(typeExpection);
		
		
	}
}
