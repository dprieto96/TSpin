package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller;
import model.GameObserver;
import model.Jugador;

public class GamePanel extends JPanel implements GameObserver {

	private static final long serialVersionUID = -9060831481452731983L;
	private TableroPanel tablero;
	private PanelJugadores panelJ;
	private PlayerMove move;
	private JPanel pFichas;
	private Controller _ctrl;
	
	private JScrollPane scrollPane;
	
	
	
	
	public GamePanel(Controller _ctrl) {
		super(new BorderLayout());
		this._ctrl = _ctrl;
		initGui();
	}
	
	
	private void initGui() {
		
		tablero = new TableroPanel(_ctrl);
		panelJ = new PanelJugadores(_ctrl);
		move = new PlayerMove(_ctrl);
		
		pFichas = new JPanel();
		pFichas.add(new FichasPanel(_ctrl, pFichas, this));
		
		scrollPane = new JScrollPane(pFichas, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(0,200));
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BorderLayout(0, 0));
		eastPanel.setPreferredSize(new Dimension(420,500));
		eastPanel.add(panelJ.numJugadores(), BorderLayout.NORTH);
		eastPanel.add(move, BorderLayout.SOUTH);
	
		this.add(eastPanel, BorderLayout.EAST);
		this.add(scrollPane, BorderLayout.SOUTH);
		this.add(tablero, BorderLayout.CENTER);
	}
	
	public void cargarFichasJugador(JPanel previousPanel, Controller ctrl) {
		
		previousPanel.setVisible(false);
		pFichas.removeAll();
		pFichas.add(new FichasPanel(ctrl, pFichas, this));
		pFichas.setVisible(true);	
		pFichas.revalidate();
		
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

		
	}
	
}

