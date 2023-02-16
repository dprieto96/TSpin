package commands;

import exceptions.CommandParseException;
import model.Game;


public class HelpCommand extends Command{

	private static final String nombre = "help";
	private static final String shortcut = "h";
	private static final String help = "show help about each command";
	
	public static final String helpMsg = String.format(//Mensaje de ayuda para introducir una opcion
			
			"Comandos disponibles: %n" +
			"[a]dd <token> <x> <y>: add a token in position x, y %n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[re]maining tokens%n"+
			"[ro]tate: rotate the token%n"+
			"[p]ass%n"
			);
	
	public HelpCommand() {
		super(nombre, shortcut, help);
		
		
	}

	@Override
	public boolean execute(Game game) {
		
		System.out.println(helpMsg);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		
		if (shortcut.equals(commandWords[0]) || nombre.equals(commandWords[0])) {
			if(commandWords.length == 1) {
				
				return new HelpCommand();
			}
				
				
			else {
				throw new CommandParseException("Command Help: Incorrect number of arguments\n");
			}
			
		}
		else {
			return null;
		}
	}


}
