package view;


import java.util.HashMap;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.Controller;
import model.GameObserver;
import model.Jugador;

public class PlayerMove extends JPanel implements GameObserver{

	private Controller _ctrl;
	
	public PlayerMove(Controller ctrl) {
		_ctrl = ctrl;
		_ctrl.addObserver(this);
		initGUI();
	}
	
	private void initGUI() {
		
		// BOTONES:
		//	1. Rotar ficha
		//	2. Pasar turno
		
		// Layout
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//this.setLayout(new BorderLayout(0, 0));
		
		// rotate button
		JButton rotateB = new JButton();
		rotateB.setToolTipText("Rotar ficha");
		rotateB.setPreferredSize(new Dimension(150,150));
		rotateB.setIcon(new ImageIcon("resources/rotate.png"));

		// rotateB listener using an anonymus inner class
		rotateB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				
				try{_ctrl.rotate();}
				catch (IllegalArgumentException e) {
			    	 showExceptionMessage("No se ha podido rotar la ficha");
			     }
			}
			
		});
		
		// skip button
		JButton skipB = new JButton();
		skipB.setToolTipText("Pasar turno");
		skipB.setPreferredSize(new Dimension(150,150));
		skipB.setIcon(new ImageIcon("resources/skip.png"));

		// skipB listener using an anonymus inner class
		skipB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				
				try{_ctrl.passTurn();}
				catch (IllegalArgumentException e) {
			    	 showExceptionMessage("No se ha podido pasar el turno");
			     }
			}
			
		});

		this.add(rotateB, BorderLayout.WEST);
		this.add(skipB, BorderLayout.EAST);	
		
	}
	
	private void showExceptionMessage(String message) {
		JOptionPane.showMessageDialog(this, message,
	    		  "Exception Message",
	    		  JOptionPane.WARNING_MESSAGE);
	}
	
	@Override
	public void onReset(List<Jugador> jugadores, HashMap<String, String> mapaCasillas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFichaAnadida(int x, int y, int f, Jugador jugador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}

}
