import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyMouseAdapter extends MouseAdapter {
	private JOptionPane pane = new JOptionPane();
	private Random generator = new Random();
	private GameSetup gameOptions = new GameSetup(12, 10, 10);
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
			case 1:		//Left mouse button
				Component c = e.getComponent();
				while (!(c instanceof JFrame)) {
					c = c.getParent();
					if (c == null) {
						return;
					}
				}
				// Gets Position of Where user clicks
				JFrame myFrame = (JFrame) c;
				MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
				Insets myInsets = myFrame.getInsets();
				int x1 = myInsets.left;
				int y1 = myInsets.top;
				e.translatePoint(-x1, -y1);
				int x = e.getX();
				int y = e.getY();
				myPanel.x = x;
				myPanel.y = y;
				// Records the position and saves it in a variable;
				myPanel.mouseDownGridX = myPanel.getGridX(x, y);
				myPanel.mouseDownGridY = myPanel.getGridY(x, y);
				System.out.println("MouseDownGridX: "+ myPanel.mouseDownGridX + " MouseDownGridY"+ myPanel.mouseDownGridY );
				myPanel.repaint();
				break;
			case 3:		//Right mouse button
				//Do nothing
				Component c1 = e.getComponent();
				while (!(c1 instanceof JFrame)) {
					c1 = c1.getParent();
					if (c1 == null) {
						return;
					}
				}
				// Gets Position of Where user clicks
				JFrame myFrame1 = (JFrame) c1;
				MyPanel myPanel1 = (MyPanel) myFrame1.getContentPane().getComponent(0);
				Insets myInsets1 = myFrame1.getInsets();
				int x2 = myInsets1.left;
				int y2 = myInsets1.top;
				e.translatePoint(-x2, -y2);
				int x3 = e.getX();
				int y3 = e.getY();
				myPanel1.x = x3;
				myPanel1.y = y3;
				// Records the position and saves it in a variable;
				myPanel1.mouseDownGridX = myPanel1.getGridX(x3, y3);
				myPanel1.mouseDownGridY = myPanel1.getGridY(x3, y3);
				myPanel1.repaint();
				break;
			default:    //Some other button (2 = Middle mouse button, etc.)
				//Do nothing
				break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
			case 1:		//Left mouse button
				Component c = e.getComponent();
				while (!(c instanceof JFrame)) {
					c = c.getParent();
					if (c == null) {
						return;
					}
				}
				JFrame myFrame = (JFrame)c;
				MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
				Insets myInsets = myFrame.getInsets();
				int x1 = myInsets.left;
				int y1 = myInsets.top;
				e.translatePoint(-x1, -y1);
				int x = e.getX();
				int y = e.getY();
				myPanel.x = x;
				myPanel.y = y;
				int gridX = myPanel.getGridX(x, y);
				int gridY = myPanel.getGridY(x, y);
				if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
					//Had pressed outside
					//Do nothing
				} else {
					if ((gridX == -1) || (gridY == -1)) {
						//Is releasing outside
						//Do nothing
					} else {
						if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
							//Released the mouse button on a different cell where it was pressed
							//Do nothing
						} else {
							//Released the mouse button on the same cell where it was pressed
//							if ((gridX == 0) || (gridY == 0)) {
//								//On the left column and on the top row... do nothing
//							} else
							//{
								//On the grid other than on the left column and on the top row:
								if(gameOptions.youLose(myPanel.mouseDownGridX, myPanel.mouseDownGridY)) {
									gameOptions.revealAll(myPanel.colorArray);
									myPanel.repaint();
									//adds the JOption pane to the game.
									final JButton restart = new JButton("Restart");
									final JButton exit = new JButton("Quit Game");
									restart.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											System.exit(0);
											
										}
										
										
									});
//									JOptionPane.show//fixx this!!!!!
								}
								else {
									myPanel.revealAdjacent(myPanel.mouseDownGridX, myPanel.mouseDownGridY, gameOptions);
								}
//								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();
							//}
						}
					}
				}
				myPanel.repaint();
				break;
			case 3:		//Right mouse button
				//Do nothing
				Component c1 = e.getComponent();
				JFrame myFrameR = (JFrame)c1;
				MyPanel myPanelR = (MyPanel) myFrameR.getContentPane().getComponent(0);
				myPanelR.addFlag(myPanelR.mouseDownGridX, myPanelR.mouseDownGridY);
				break;
			default:    //Some other button (2 = Middle mouse button, etc.)
				//Do nothing
				break;
		}
	}
}