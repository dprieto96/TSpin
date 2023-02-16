package controller;
import java.util.List;
import java.util.Scanner;

import commands.*;
import exceptions.CommandExecuteException;
import model.Game;
import model.GameObserver;
import model.IAType;
import view.BoardPrinter;
import view.TableroPanel;

public class Controller {
    private model.Game game;
    private Scanner in;
	BoardPrinter printer = new BoardPrinter();
	
    //public Command command;
	boolean printGame = true;
	
    public Controller(Game game, Scanner scanner) {
    	this.game = game;
		in = scanner;		
    }
    
    public Controller(Game game) {
    	this.game = game;
    }
    
    
	public void run() {
		
		while(!game.getJuegoTerminado()) {
			if(printGame)
			{
				draw();
			}
			printGame = true;
			
			System.out.print("Command > ");
			String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
			try { 
			Command command = CommandGenerator.parse(words);
			
			if (command != null)
			{
				if (!command.execute(game))
				{
					printGame = false;
				}
			}
		
			}catch(Exception e) {
				System.out.format(e.getMessage());
				printGame = false;
			}

				
			}			
			   

		}
    

    private void draw() {
		// TODO Auto-generated method stub
    	System.out.println("Turno jugador "+ ((game.getCurrentPlayer())+1) + "/" + game.getNumJugadores());
    	System.out.println(printer.toString(game));
		
	}

    public void addObserver(GameObserver o) {
    	game.addObserver(o);
	}
    
    //Metodos temporales
    public void rotate() {
    	int f = game.getCurrentFicha();
    	Command command = new RotateCommand(f, 90); // TODO ficha seleccionada
		command.execute(game);
		//return true;
    }
    
    public void passTurn() {
    	if(!game.getPrimeraRonda()) {
    		Command command = new SkipTurnCommand();
			command.execute(game);
    	}
		//return true;
    }
    
    public boolean anadirFicha(int x, int y) {
    	int f = game.getCurrentFicha();
     	Command command = new AddCommand(f, x , y); // TODO ficha seleccionada
		command.execute(game);
		return true;    	
    }


	public void setPlayers(int numPlayers) {
		//game.initJugadores(numPlayers);
	}
		

	public void setPlayers(int numPlayers, List<IAType> IAs) {
		game.initJugadores(numPlayers, IAs);
	}
	
	public void reset() {
		game.reset();
	}
	
	public int remaining() {
		return game.remaining();
	}
	
	public void setCurrentFicha(int f) {
		game.setCurrentFicha(f);
	}

	public int getNumJugadores() {
		return game.getNumJugadores();
	}
	
	public int getCurrentPlayer() {
		return game.getCurrentPlayer();
	}

	
}
