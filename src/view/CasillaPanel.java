package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;

import controller.Controller;
import model.GameObserver;
import model.Jugador;

public class CasillaPanel extends JButton implements GameObserver{
	private static final long serialVersionUID = 1L;
	private int _x, _y;
	
	public CasillaPanel(int x, int y) {
		this._x = x;
		this._y = y;
		
		this.setOpaque(true);				
		this.setBackground(Color.white);
		this.setBorderPainted(true);
		
		/*
		this.addActionListener(new ActionListener() {					
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//anadirFicha(getXPos(), getYPos());
				toggle();
				
				System.out.println("------------------------");
				System.out.println("x: " + getXPos());
				System.out.println("y: " + getYPos());
			}
		});*/
		
	}
	
	public void toggle(Color color) {
		setBackground(color);
		setFocusPainted(false);
		setBorderPainted(false);
		setEnabled(false);
	}
	
	public int getXPos() {
		return _x;
	}
	
	public int getYPos() {
		return _y;
	}

	@Override
	public void onReset(List<Jugador> jugadores, HashMap<String, String> mapaCasillas) {
		// TODO Auto-generated method stub
		
	}


	

	@Override
	public void onFichaAnadida(int x, int y, int f, Jugador jugador) {
		// TODO Auto-generated method stub
			int id = jugador.getId();
		
			switch (id) {
			case 0: toggle(Color.red); break; 
			case 1: toggle(Color.blue); break;
			case 2: toggle(Color.yellow); break;
			case 3: toggle(Color.green); break;
			default: break;
			}
	}

	@Override
	public void update(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}
}
