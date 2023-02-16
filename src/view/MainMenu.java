package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import controller.Controller;
import model.IAType;

public class MainMenu  extends JPanel {
	

	private Controller _controller;
	private JPanel _previousPanel;
	private MainWindow _mainWindow;

	
	public MainMenu(Controller controller, JPanel previousPanel, MainWindow mainWindow) {
		//super("Blockus");
		_controller=controller;
		_previousPanel = previousPanel;
		_mainWindow = mainWindow;
		initGUI();
	}
	private void initGUI(){

	    this.creaElementos();
	    this.setLayout(null);
	    this.setSize(_previousPanel.getSize());
	    
	}
	
	

	public void creaElementos(){
		
		JLabel titulo = new JLabel();
		String[] opciones = { "Vacio", "Bot(fácil)", "Bot(medio)", "Bot(díficil)", "Jugador" };
		JButton exitB = new JButton();

		JButton nextB = new JButton();
		JComboBox<String> p1 = new JComboBox<String>(opciones);
		JComboBox<String> p2 = new JComboBox<String>(opciones);
		JComboBox<String> p3 = new JComboBox<String>(opciones);
		JComboBox<String> p4 = new JComboBox<String>(opciones);
		
		List<JComboBox<String>> pList = new ArrayList<JComboBox<String>>();

		pList.add(p1);
		pList.add(p2);
		pList.add(p3);
		pList.add(p4);
		
		titulo.setBounds(400, 250, 2000, 30);
		titulo.setFont(new Font("comic-sans", 1, 21));
		titulo.setText("Numero de Jugadores");
		this.add(titulo);

		
		
		
		p1.setBounds(440, 300, 120, 30);
		this.add(p1);

		p2.setBounds(440, 350, 120, 30);
		this.add(p2);
		
		p3.setBounds(440, 400, 120, 30);
		this.add(p3);
		
		p4.setBounds(440, 450, 120, 30);
		this.add(p4);

		nextB.setBounds(440, 550, 120, 30);
		nextB.setText("Siguiente");
		this.add(nextB);
		
		exitB.setBounds(440, 600, 120, 30);
		exitB.setText("Salir");
		this.add(exitB);

		
		nextB.addActionListener(new PlayersButtonListener(pList));
		exitB.addActionListener(new ExitButtonListener());

	}

	class PlayersButtonListener implements ActionListener {
		
		int _numPlayers = 0;
		List<IAType> IAs = new ArrayList<IAType>();
		private List<JComboBox<String>> pList;

		public PlayersButtonListener(List<JComboBox<String>> pList) {
			super();
			this.pList = pList;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for (JComboBox<String> p: pList) {
				
				if (p.getSelectedIndex() > 0 && p.getSelectedIndex() < 4) {
					IAs.add(new IAType(0, p.getSelectedIndex()));
				}
				if(p.getSelectedIndex() == 4)
					_numPlayers++;
			}
			
			if(_numPlayers + IAs.size() >= 2) {
				_mainWindow.newGame(_numPlayers, IAs, _previousPanel);
			}
			
		}
	}

	class ExitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(WIDTH);
		}
		
	}
}



