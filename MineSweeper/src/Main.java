import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Black Panther: Minesweeper Mission");
		//Game's name changed to: "Black Panther: Minesweeper Mission.
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//myFrame.setLocation(400,150);
		myFrame.setSize(1000, 1000);
		
		myFrame.setLocation(100,100);
		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
		
		
		
		
		
		
	}		
	
}
	