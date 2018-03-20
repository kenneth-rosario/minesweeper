
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javafx.scene.paint.Color;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Black Panther: Minesweeper Mission");
		//Game's name changed to: "Black Panther: Minesweeper Mission.
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//myFrame.setLocation(400,150);
		myFrame.setSize(1000, 1000);
		JMenuBar menubar = new JMenuBar();
		myFrame.setJMenuBar(menubar);
		
		JMenu game = new JMenu("Game");
		JMenu intel = new JMenu("Intel");
		JMenu abort = new JMenu("Abort");
		
		
		menubar.setVisible(true);
		//sets font for the menus in menubar.
		Font textfont = new Font("Impact",Font.PLAIN,12);
		game.setFont(textfont);
		intel.setFont(textfont);
		abort.setFont(textfont);
		
		
		 
		
		
		
		//adds menus to menubar.
		menubar.add(game);
		menubar.add(intel);
		menubar.add(abort);
		
		
		
		JMenuItem reset = new JMenuItem("Reset");
		JMenuItem setdifficulty = new JMenuItem("Set Difficulty");
		JMenuItem exit = new JMenuItem("Abort Mission");
		
		game.add(reset);
		game.add(setdifficulty);
		
		abort.add(exit);
		
		myFrame.validate();
		myFrame.repaint();
		//a class that adds the exit functionality to the "exit" button.
		
		class exitaction implements ActionListener{
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
			}
			
			
		}
		exit.addActionListener(new exitaction());
		
		
		
		
		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
		
		class resetaction implements ActionListener{
			public void actionPerformed (ActionEvent e) {
				MyPanel.gameOptions.resetGame();
				myPanel.resetColors();
			}
		
		}
		reset.addActionListener(new resetaction());
		//GameSetup test = new GameSetup(8, 9, 9);
		//test.printMines();
		
		
		//adds the menu bar to the game.
		
		
		
//		JMenuBar menubar = new JMenuBar();
//		myFrame.setJMenuBar(menubar);
//		
//		JMenu game = new JMenu("Game");
//		JMenu intel = new JMenu("Intel");
//		JMenu abort = new JMenu("Abort");
//		
//		
//		menubar.setVisible(true);
//		//sets font for the menus in menubar.
//		Font textfont = new Font("Impact",Font.PLAIN,12);
//		game.setFont(textfont);
//		intel.setFont(textfont);
//		abort.setFont(textfont);
//		
//		
//		 
//		
//		
//		
//		//adds menus to menubar.
//		menubar.add(game);
//		menubar.add(intel);
//		menubar.add(abort);
//		
//		
//		
//		JMenuItem reset = new JMenuItem("Reset");
//		JMenuItem setdifficulty = new JMenuItem("Set Difficulty");
//		JMenuItem exit = new JMenuItem("Abort Mission");
//		
//		game.add(reset);
//		game.add(setdifficulty);
//		
//		abort.add(exit);
//		
//		myFrame.validate();
//		myFrame.repaint();
//		//a class that adds the exit functionality to the "exit" button.
//		
//		class exitaction implements ActionListener{
//			public void actionPerformed (ActionEvent e) {
//				System.exit(0);
//			}
//			
//			
//		}
//		exit.addActionListener(new exitaction());
//		
//		//adds reset functionality to reset button
//		class resetaction implements ActionListener{
//			public void actionPerformed (ActionEvent e) {
//				MyPanel.gameOptions.resetGame();
//				myPanel.resetColors();
//			}
//		
//		}
//		reset.addActionListener(new resetaction());
		myFrame.setLocation(400,menubar.getHeight() + 10);
	}
	
}
	