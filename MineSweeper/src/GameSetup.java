;

public class GameSetup {
	private int totalMines; //ReadOnly inside class 
	private int[][] minesLocations; //ReadOnly inside class
	// private int numRows; for future addition of difficulty increment
	// private int numColumns; for future addition of difficulty increment
	
	public GameSetup(int totalMines, int numRows, int numColumns) {
		if(numRows*numColumns == totalMines) {
			throw new RuntimeException("You cannot win if all cells have mines");
		}
		this.totalMines = totalMines;
		generateMinesLocation();
	}
	
	public boolean youLose(int positionX, int positionY) {
		//returns true if position you entered exists in minesLocation array 
		return false;
	}
	
	private boolean verifyIfExists(int[][]bigArray, int[]orderedPair) {
		
		// Verify if ordered pair already exist in MinesLocation
		
		return false;
	}
	
	private void generateMinesLocation() {
		// Create array to store the different locations in the form of ordered Pairs
		
		//Repeat block this.totalMines times
		
			//Setup Random Number generator to create random numbers for x and another for y 
			
			//Check if ordered pair already exists
		
				// if not Store RandomNumber in an array of size two and store in bigger array
		
		//set this.minesLocation  to the bigger array
	}
	
	
	public boolean surroundingHasMine(int positionX, int positionY) {
		//Check possible positions where element might have a mine
		return false;
		
	}
	
	
	public int getMinesAroundLocation(int positionX, int positionY) {
		//Return the number of mines around a location
		return 0;
		
	}
	
	
}
