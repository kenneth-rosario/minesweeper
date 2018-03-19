import java.awt.Color;
import java.util.Random;


public class GameSetup {
	private int totalMines; //ReadOnly inside class 
	private int[][] minesLocations; //ReadOnly inside class
	private boolean youLost = false;
	// private int numRows; for future addition of difficulty increment
	// private int numColumns; for future addition of difficulty increment
	
	public GameSetup(int totalMines, int numRows, int numColumns) {
		if(numRows*numColumns == totalMines) {
			throw new RuntimeException("You cannot win if all cells have mines");
		}
		this.totalMines = totalMines;
		generateMinesLocation();
		printMines();
	}
	
	
	public boolean youLose(int positionX, int positionY) {
		//returns true if position you entered exists in minesLocation array 
		int[] orderedPair = {positionX, positionY};
		if(verifyIfExists(this.minesLocations, orderedPair)) {
			this.youLost = true;
			return true;
		}
		return false;
	}
	
	private boolean verifyIfExists(int[][]bigArray, int[]orderedPair) {
		
		// Verify if ordered pair already exist in MinesLocatio
		for(int i = 0; i < bigArray.length; i++) {
			//System.out.println("Comparing: ("+bigArray[i][0]+", "+bigArray[i][1]+ ")"+ " with: ("+orderedPair[0]+", "+orderedPair[1]+")");
			if(orderedPair[0] == bigArray[i][0] && orderedPair[1] == bigArray[i][1]) {
				return true;
			}
		}
		return false;
		
	}
	
	private void generateMinesLocation() {
		// Create array to store the different locations in the form of ordered Pairs
		int [][] locations = new int[totalMines][totalMines];
		//Repeat block this.totalMines times
		int counter = 0;
		Random generator = new Random();
		while(counter < totalMines) {
			//Setup Random Number generator to create random numbers for x and another for y 
			int coordinateX = generator.nextInt(9) ; 
			int coordinateY = generator.nextInt(10) ;
			
			int[] orderedPair = {coordinateX, coordinateY};
			//Check if ordered pair already exists
				if(!verifyIfExists(locations, orderedPair)) {
				// if not Store RandomNumber in an array of size two and store in bigger array
					locations[counter] = orderedPair;
					//only increment if element is added 
					counter++;
				}
		}
		//set this.minesLocation  to the bigger array
		this.minesLocations = locations;
	}
	
	
	
	public boolean hasSurroundingMine(int positionX, int positionY) {
		//Check possible positions where element might have a mine
		int [][] newOrderedPair = {{positionX-1, positionY}, {positionX+1, positionY}, 
				{positionX,positionY-1}, {positionX-1,positionY-1}, {positionX+1, positionY-1}, {positionX,positionY+1},
				{positionX-1, positionY+1}, {positionX+1, positionY+1}};
		
		for(int i = 0; i<newOrderedPair.length;i++) {
			if(verifyIfExists(this.minesLocations, newOrderedPair[i])) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public int getMinesAroundLocation(int positionX, int positionY) {
		//Return the number of mines around a location
		
		int counter = 0;
		int [][] newOrderedPair = {{positionX-1, positionY}, {positionX+1, positionY}, 
				{positionX,positionY-1}, {positionX-1,positionY-1}, {positionX+1, positionY-1}, {positionX,positionY+1},
				{positionX-1, positionY+1}, {positionX+1, positionY+1}};
		for(int i = 0; i < newOrderedPair.length; i++) {
			if(verifyIfExists(this.minesLocations, newOrderedPair[i])) {
				counter++;
			}
		}
		return counter;
		
	}
	
	public void revealAll(Color [][]colorArray) {
		for(int i =0; i< minesLocations.length; i++) {
			colorArray[minesLocations[i][0]][minesLocations[i][1]] = Color.BLACK;
		}
	}
	
	
	public void printMines() {
		System.out.println("Mines Locations Are:");
		for(int i= 0; i < this.minesLocations.length; i++) {
			System.out.println("("+this.minesLocations[i][0]+", "+this.minesLocations[i][1]+ ")");
		}
	}
	
}
