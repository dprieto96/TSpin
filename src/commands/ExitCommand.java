package commands;

import exceptions.CommandParseException;
import model.Game;


public class ExitCommand extends Command{
	private static final String nombre = "exit";
	private static final String shortcut = "e";
	private static final String help = "Terminate the game";

	public ExitCommand() {
		super(nombre, shortcut, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		game.exit();
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		if (shortcut.equals(commandWords[0]) || nombre.equals(commandWords[0])) {
			if(commandWords.length == 1)
				return new ExitCommand();
			else {
				throw new CommandParseException("Command exit: Incorrect number of arguments\n");
			}
			
		}
		else {
			return null;
		}
	}

}