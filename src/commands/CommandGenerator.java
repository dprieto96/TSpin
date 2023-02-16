package commands;


import exceptions.*;


public class CommandGenerator {
	

	private static int numFicha;
	private static int angulo;
	private static int ficha;
	private static int fila;
	private static int columna;
	private static Command[] availableCommands = {
			new AddCommand(ficha, fila, columna),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new Restantes(),
			new RotateCommand(numFicha,angulo),
			new SkipTurnCommand(),			
			};
	
	public static Command parse(String[ ] commandWords) throws CommandParseException {

        Command comando=null;
        for(int i=0;i<availableCommands.length;i++) {

            if(availableCommands[i].parse(commandWords) != comando) {
                 comando=availableCommands[i].parse(commandWords);
                 //break;
                 return comando;
            }
        }
             throw new CommandParseException("Unknown command\n");


    }
	
	public static String commandHelp() {
		String help = null;
		for(int i=0;i<availableCommands.length;i++) {
			if(availableCommands[i].helpText()==help) {
				help=availableCommands[i].helpText();
				break;
			}
		}
		return help;
		
	}
}
