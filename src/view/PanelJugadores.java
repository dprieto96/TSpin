package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.GameObserver;
import model.Jugador;

public class PanelJugadores extends JPanel implements GameObserver {

	private Controller _ctrl;
	
	private JLabel ju0;
	private JLabel ju1;
	private JLabel ju2;
	private JLabel ju3;
	
	private ArrayList<JLabel> js;
	
	public PanelJugadores(Controller ctrl) {
		_ctrl = ctrl;
		_ctrl.addObserver(this);
	}
	
	public JPanel numJugadores() {
		
		JLabel titulo = new JLabel(lineBreak("PUNTUACION"));
		ju0 = new JLabel(lineBreak("Jugador 1: " + Integer.toString(0)));
		ju1 = new JLabel(lineBreak("Jugador 2: " + Integer.toString(0)));
		ju2 = new JLabel(lineBreak("Jugador 3: " + Integer.toString(0)));
		ju3 = new JLabel(lineBreak("Jugador 4: " + Integer.toString(0)));
		
		js = new ArrayList<JLabel>();
	
		JPanel supPanel = new JPanel(new FlowLayout());
		supPanel.setPreferredSize(new Dimension(100,300));
		supPanel.add(titulo);
		
		supPanel.add(ju0);
		js.add(ju0);
		
		int j = _ctrl.getNumJugadores();
		
		switch(j) {
			case 2:
				supPanel.add(ju1);
				js.add(ju1);
				break;
				
			case 3:
				supPanel.add(ju1);
				supPanel.add(ju2);
				
				js.add(ju1);
				js.add(ju2);
				break;
				
			case 4:
				supPanel.add(ju1);
				supPanel.add(ju2);
				supPanel.add(ju3);
				
				js.add(ju1);
				js.add(ju2);
				js.add(ju3);
				break;
			default: break;
		}
		
		return supPanel;
	}
	
	private String lineBreak(String text) {
		return "<html><p style=\"width:150px\">" + text + "</p></html>";
	}

	@Override
	public void onReset(List<Jugador> jugadores, HashMap<String, String> mapaCasillas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFichaAnadida(int x, int y, int f, Jugador jugador) {
		// TODO Auto-generated method stub
		int id = jugador.getId()+1;
		int puntuacion = jugador.getPuntuacion();
		
		
		js.get(id-1).setText("Jugador " + id +" : " + Integer.toString(puntuacion));
					
	}

	@Override
	public void update(Jugador jugador) {
		// TODO Auto-generated method stub
	}
}
