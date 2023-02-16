package commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import model.Game;



public class AddCommand extends Command{
	private static final String nombre = "add";
	private static final String shortcut = "a";
	private static final String help = "Add a new piece.";//--------------------------------------------------------
	private int fila;
	private int columna;
	private int ficha;
	
	
	public AddCommand(int ficha, int fila, int columna) {
		super(nombre, shortcut,  help);
		this.fila = fila;
		this.columna = columna;
		this.ficha = ficha;
	}
		

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		if(columna >= 0 && columna < 20 && fila >= 0 && fila < 20) {
			
			if(game.getPrimeraRonda()) {				
				if(game.jugarPrimeraRonda(ficha, fila, columna)) {
					game.update();
				}
				
			}
			else {
				if(game.anadirFicha(ficha, fila, columna)) {
					game.update();
				}
			}
		}
		else {
			throw new CommandExecuteException("Posicion fuera de rango", "add new piece\n");	
		}
		
		return true;
	}
	

	
	//add 2 3
	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		
		if (shortcut.equals(commandWords[0]) || nombre.equals(commandWords[0])) {
			if(commandWords.length == 4) {
				if(isNumeric(commandWords[1]) && isNumeric(commandWords[2]) && isNumeric(commandWords[3])) {
					this.ficha = Integer.parseInt(commandWords[1]);
					this.fila = Integer.parseInt(commandWords[2]);
					this.columna = Integer.parseInt(commandWords[3]);
					return new AddCommand(ficha, fila, columna);	
				}
				else {
					
					throw new CommandParseException ("NumberFormatException\n");
					
				}
			}
			else {
			throw new CommandParseException("Command add :Incorrect number of arguments\n");
				
			}
			
			
		}
		return null;
	
	}
	
	private static boolean isNumeric(String commandWords){
		try {
			Integer.parseInt(commandWords);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}







	
	
}
