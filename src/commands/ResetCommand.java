package commands;

import exceptions.CommandParseException;
import model.Game;

public class ResetCommand extends Command{
	private static final String nombre = "reset";
	private static final String shortcut = "r";
	private static final String help = "Starts a new game.";
	
	public ResetCommand() {
		super(nombre, shortcut,  help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		
		game.reset();
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		if (shortcut.equals(commandWords[0]) || nombre.equals(commandWords[0])) {
			if(commandWords.length == 1)
				return new ResetCommand();
			else {
				throw new CommandParseException("Command Reset: Incorrect number of arguments\n");
			}
			
		}
		else {
			return null;
		}
	}


}
