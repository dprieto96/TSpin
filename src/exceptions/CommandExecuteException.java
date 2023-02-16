package exceptions;



public class CommandExecuteException extends GameException {
	
	private static final long serialVersionUID = -8956566776528489450L;
	String failed;
	public CommandExecuteException(String typeExpection,String failed) {
		super(typeExpection);
		this.failed=failed;
	}
	
	@Override
		public String getMessage() {
		String mensaje=super.getMessage()+" %n"+"[ERROR]: Failed to "+failed;
			// TODO Esbozo de método generado automáticamente
			return mensaje;
		}
	
}
