package exceptions;

/* Excepciones producidas durante la ejecución del método parse(),
tales como comando desconocido, número de parámetros incorrecto, tipo de parámetros
no válido*/
public class CommandParseException extends GameException {

	
	private static final long serialVersionUID = -5361065477013583600L;

	public CommandParseException(String typeExpection){
		super(typeExpection);
		
		
	}
}
