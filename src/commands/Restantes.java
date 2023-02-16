package commands;

import exceptions.CommandParseException;
import model.Game;


public class Restantes extends Command{
	private static final String nombre = "re";
	private static final String shortcut = "remaining";
	private static final String help = "Remaining the tokens.";
	
	public Restantes() {
		super(nombre, shortcut,  help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		
		game.remaining();
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		if (shortcut.equals(commandWords[0]) || nombre.equals(commandWords[0])) {
			if(commandWords.length == 1)
				return new Restantes();
			else {
				throw new CommandParseException("Command Remaining: Incorrect number of arguments\n");
			}
			
		}
		else {
			return null;
		}
	}


}