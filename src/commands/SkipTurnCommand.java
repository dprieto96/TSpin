package commands;

import exceptions.CommandParseException;
import model.Game;


public class SkipTurnCommand extends Command{
	private static final String nombre = "pass";
	private static final String shortcut = "p";

	private static final String help = "Pass the turn.";
	
	public SkipTurnCommand() {
		super(nombre, shortcut,  help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		if (shortcut.equals(commandWords[0]) || nombre.equals(commandWords[0])) {
			if(commandWords.length == 1)
				return new SkipTurnCommand();
			else {
				throw new CommandParseException("Command Reset: Incorrect number of arguments\n");
			}
			
		}
		else {
			return null;
		}
	}


}
