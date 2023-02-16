package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import controller.Controller;
import model.IAType;


public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Controller _controller;
	private JPanel mainPanel;
	
	public MainWindow(Controller controller) {
		super ("Blokus");
		_controller=controller;
		initGUI();
	}
	
	private void initGUI(){
		this.setTitle("Blokus");
	    this.setSize(1000,1000);
	    this.setResizable(true);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setLayout(null);
	    this.setIconImage(new ImageIcon("resources/Blokus.jpg").getImage());
	    Image icon = Toolkit.getDefaultToolkit().getImage("resources/Blokus.jpg");
	    this.setVisible(true);
	
		mainPanel=new JPanel (new BorderLayout());
		this.setContentPane(mainPanel);
		
		this.setJMenuBar(new UpMenu(this, _controller));
	
		mainPanel.add(new MainMenu(_controller, mainPanel, this),BorderLayout.CENTER);
		
	}
	
	public void openMenu(JPanel previousPanel) {
			previousPanel.setVisible(false);
			mainPanel.add(new MainMenu(_controller, mainPanel, this),BorderLayout.CENTER);
			mainPanel.setVisible(true);
			this.setContentPane(mainPanel);
	}
	
	public void newGame(int numPlayers, List<IAType> IAs, JPanel previousPanel) {
			_controller.setPlayers(numPlayers, IAs);
			GamePanel gamePanel = new GamePanel(_controller);
			gamePanel.setVisible(true);
			mainPanel.setVisible(false);
			this.setContentPane(gamePanel);
			this.pack();
			previousPanel.setVisible(false);
		}
	
	
	public void reset() {
		_controller.reset();
	}
}