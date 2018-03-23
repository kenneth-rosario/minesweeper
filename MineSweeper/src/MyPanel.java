import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 3426940946811133635L;
    private static final int GRID_X = 40;
	private static final int GRID_Y = 40;
	private static final int INNER_CELL_SIZE = 55;
	private static final int TOTAL_COLUMNS = 10;
	private static final int TOTAL_ROWS = 11;   //Last row has only one cell
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	public Color[][] colorArray = new Color[TOTAL_COLUMNS][TOTAL_ROWS];
	public Color[] severityArray = {Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW, Color.decode("#8f42f4")};
	public MyPanel() {   //This is the constructor... this code runs first to initialize
		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}

		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				colorArray[x][y] = Color.WHITE;
			}
		}
	}
	
	private int findIndexOf(Color x) {// findIndexOf x Color inside severityColors Array
		for(int i=0; i < severityArray.length; i++) {
			if(severityArray[i].equals(x)) {
				return i;
			}
		}
		return -1;
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right - 1;
		int y2 = getHeight() - myInsets.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;

		g.setColor(Color.LIGHT_GRAY);
		
		g.setColor(Color.BLACK);
		for (int y = 0; y <= TOTAL_ROWS-1; y++) {
			g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x <= TOTAL_COLUMNS; x++) {
			g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y, x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS - 1)));
		}
		//load black panther image or other image areas
		URL url = getClass().getResource("mine-picture.jpg");
		BufferedImage image = new BufferedImage(INNER_CELL_SIZE, INNER_CELL_SIZE , BufferedImage.SCALE_SMOOTH);
		File f =  new File(url.getPath());
		boolean loaded = false;
		try {
			image = ImageIO.read(f);
			loaded = true;
		} catch (IOException e) {
			System.out.println("Error Loading");
		}
		
		URL flagUrl = getClass().getResource("flag.svg.png"); // flag images
		BufferedImage flagImage = new BufferedImage(INNER_CELL_SIZE, INNER_CELL_SIZE , BufferedImage.SCALE_SMOOTH);
		File flagFile =  new File(flagUrl.getPath());
		boolean loadedFlag = false;
		try {
			flagImage = ImageIO.read(flagFile);
			loadedFlag = true;
		} catch (IOException e) {
			System.out.println("Error Loading");
		}
		
		//Paint cell colors
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS-1; y++) {

					Color c = colorArray[x][y];
					g.setColor(c);
					g.setFont(new Font("Serif", Font.PLAIN, 50));
						if(c.equals(Color.BLACK)) {// if it is a mine
							if(loaded) { // check if image whas loaded 
								g.drawImage(image,x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1,1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, null) ;
							}else { // if not draw normal black cell
								g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
							}
						}
						else if(!(c.equals(Color.BLACK)||c.equals(Color.GRAY) || c.equals(Color.WHITE)|| c.equals(Color.RED))) {
							//if its not 
							g.setColor(c);
							g.setFont(new Font("Comic-Sans", Font.PLAIN, 50));
							//map color to index, by adding one we get the correct number with the correct color
							g.drawString(""+(findIndexOf(colorArray[x][y])+1) ,x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 13 , 1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 45);
						}else if(c.equals(Color.RED)) {
							if(loadedFlag) {
								g.drawImage(flagImage,x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1,1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, null) ;
							}else {
								g.setColor(c);
								g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);	
							}
						}
						else {
							g.setColor(c);
							g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);	
						}
						
			}
		}
	}

	

	// This method helps to find the adjacent boxes that don't have a mine.
	// It is partially implemented since the verify hasn't been discussed in class
	// Verify that the coordinates in the parameters are valid.
	// Also verifies if there are any mines around the x,y coordinate
	public void revealAdjacent(int x, int y, GameSetup gameOptions){
		if((x<0) || (y<0) || (x>9) || (y>9)){	// Stop if it reaches border
			return;
		}
		else if(gameOptions.hasSurroundingMine(x, y)) { // Stop if surrounding has a mine
			int numMines = gameOptions.getMinesAroundLocation(x, y);
			colorArray[x][y] = severityArray[numMines -1]; // set Color according the amount of mines around location
		}
		else if(colorArray[x][y].equals(Color.GRAY)) { // Stop if cell has already been colored Grey
			return;
		}
		else { // Otherwise paint the current cell Gray amd reveal adjacent cells
				colorArray[x][y] = Color.GRAY;
				revealAdjacent(x-1, y, gameOptions);
				revealAdjacent(x, y-1, gameOptions);
				revealAdjacent(x+1, y, gameOptions);
				revealAdjacent(x, y+1, gameOptions);
				revealAdjacent(x-1,y-1, gameOptions);
				revealAdjacent(x+1, y+1, gameOptions);
				revealAdjacent(x+1, y-1, gameOptions);
				revealAdjacent(x-1, y+1, gameOptions);
		}
	}
	
	
	public void clearGrid() { // Clears the grid 
		 for(int i = 0; i < colorArray.length; i++) {
			 for(int j = 0; j < colorArray[i].length; j++) {
				 colorArray[i][j] = Color.WHITE;
			 }
		 }
	}

	public void addFlag(int x, int y) { // adds the flag with toggle 
		if(colorArray[x][y].equals(Color.WHITE)) {
			colorArray[x][y] = Color.RED;
		}else if(colorArray[x][y].equals(Color.RED)) {
			colorArray[x][y] = Color.WHITE;
		}else {
			// do nothing
		}
		repaint();
	}
	
	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		System.out.println("Insets: x:"+x1 +" y:"+y1);
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x == 0 && y == TOTAL_ROWS - 1) {    //The lower left extra cell
			return x;
		}
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 2) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x == 0 && y == TOTAL_ROWS - 1) {    //The lower left extra cell
			return y;
		}
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 2) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}
	
}