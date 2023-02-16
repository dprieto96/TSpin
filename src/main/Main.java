package main;
import model.Game;
import view.MainWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.PrivateKey;
import javax.swing.*;
import java.util.*;


import controller.Controller;

public class Main {
	
	private static String _mode = "gui";
	
	private static Controller controller;
	private static Game game;

	private static void startBatchMode(String[] args) throws IOException {

    	Scanner scanner;
    	int numJugadores = 2;
    	//String[] args = null;
		//TEST
    	if (args.length == 2) {
    		numJugadores = Integer.parseInt(args[1]);
    	}
//    	//TEST
    	scanner = new Scanner(System.in);
    	game = new Game();
    	controller = new Controller(game, scanner);
   		controller.run(); //Ejecuta el juego

   		System.out.print("Game closed.");
	}
	
	private static void startGUIMode () throws IOException{
		
		game = new Game();
		controller = new Controller(game);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {	new MainWindow(controller);	}
						
		});		
	}
	
	private static void start(String[] args) throws IOException{		
		
		if(_mode.contentEquals("console")) {		
			startBatchMode(args);				
		}else {
			startGUIMode();
		}	
	}
	
	public static void main(String[] args)  {
		
		try {
			_mode = args[0];			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			start(args);
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
}

