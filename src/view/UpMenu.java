package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import controller.Controller;

public class UpMenu extends JMenuBar {
	private String ayuda = "El objetivo es colocar la mayor cantidad de las 21 piezas en el tablero. Cada pieza debe tocar al menos\r\n"
			+ "otra del mismo color, pero solo por una de las esquinas. El juego termina cuando ningun jugador\r\n"
			+ "puede colocar una pieza. ";
	private JMenu nPartida, ayudas, ventana, salir;
	private JMenuItem NPartida, instrucciones, Rpartida, tam1, tam2, SalirE, VolverP;
	private Controller _controller;
	private MainWindow _mainWindow;
	private JPanel _previousPanel;

	public UpMenu(MainWindow mw, Controller controller) {
		_mainWindow = mw;
		nPartida = new JMenu("Nueva Partida");
		ayudas = new JMenu("Instrucciones");
		salir = new JMenu("Salir");

		_controller = controller;
		_previousPanel = (JPanel) _mainWindow.getContentPane();
		initGUI();

	}

	public void initGUI() {
		this.add(nPartida);
		this.add(ayudas);
		this.add(new JSeparator());
		this.add(salir);
		
		nPartida.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Nueva Partida");
				_mainWindow.reset();
				_mainWindow.openMenu(_previousPanel);
			}
			
		});

		ayudas.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, ayuda);

			}
			
		});
		
		salir.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				System.exit(WIDTH);
			}
			
		});
	}

}
