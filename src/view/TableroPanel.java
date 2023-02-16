package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;
import model.GameObserver;
import model.Jugador;


public class TableroPanel extends JPanel implements GameObserver{
	private static final long serialVersionUID = 1L;

	private static final int SQUARESIZE = 40;
	//private List<CasillaPanel> tablero;
	private CasillaPanel[][] tablero;
	

	private int width = 20, height = 20;
	private Controller _ctrl;
	
	
	public TableroPanel(Controller ctrl) {
		super(new GridLayout(20, 20));
		tablero = new CasillaPanel[20][20];
		this.setPreferredSize(new Dimension(width * SQUARESIZE, height
				* SQUARESIZE));
		
		ctrl.addObserver(this);
		this._ctrl = ctrl; 

		initGUI();
		
	}

	
	
	private void initGUI() {		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				CasillaPanel casilla = new CasillaPanel(j,i);
				
				casilla.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						_ctrl.anadirFicha(casilla.getXPos(), casilla.getYPos());					
					}
				});
				this.add(casilla);

				casilla.setVisible(true);
				tablero[i][j] = casilla;
			}
		}
	}
	
	@Override
	public void onReset(List<Jugador> jugadores, HashMap<String, String> mapaCasillas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFichaAnadida(int x, int y, int f, Jugador jugador) {
		// TODO Auto-generated method stub
		
		int id = jugador.getId();
		
		switch(id) {
		
			case 0 : tablero[x][y].toggle(Color.red); break;
			case 1 : tablero[x][y].toggle(Color.blue); break;
			case 2 : tablero[x][y].toggle(Color.yellow); break;
			case 3 : tablero[x][y].toggle(Color.green); break;
			default: break;
		}
	
	}



	@Override
	public void update(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}
}
