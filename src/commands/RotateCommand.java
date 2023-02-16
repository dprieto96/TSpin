package commands;

import java.util.ArrayList;
import java.util.List;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import model.Game;



public class RotateCommand extends Command{
	private static final String nombre = "rotate";
	private static final String shortcut = "ro";
	private static final String help = "rotate the angle the token";//--------------------------------------------------------
	private int numFicha;
	private int angulo;
	
	
	public RotateCommand(int posicion, int angulo) {
		super(nombre, shortcut,  help);
		this.numFicha = posicion;
		this.angulo=angulo;

	}
		

	
	@Override
	public boolean execute(Game game) {
		if(this.numFicha >=0 && this.numFicha < game.dim(numFicha)  && this.angulo>=0 && this.angulo<361) {//CAMPIAR 90 POR EL ARRAY DE PIEZAS JUGADOR			
			if (this.angulo==90 || this.angulo==180 || this.angulo==270) {
				game.rotate(numFicha,angulo);
			}else {
				System.out.println("Angulo no valido");
			}
		}
		else {
			throw new CommandExecuteException("No ha sido posible rotar", "rotate token\n");
			
		}
		return false;
	}

	
	//add 2 3
	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		
		if (shortcut.equals(commandWords[0]) || nombre.equals(commandWords[0])) {
			if(commandWords.length == 3) {
				if(isNumeric(commandWords[1]) && isNumeric(commandWords[2])) {
					this.numFicha = Integer.parseInt(commandWords[1]);
					this.angulo = Integer.parseInt(commandWords[2]);
					return new RotateCommand(numFicha, angulo);	
				}
				else {
					
					throw new CommandParseException ("NumberFormatException\n");
					
				}
			}
			else {
				throw new CommandParseException("Command rotate :Incorrect number of arguments\n");
				
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