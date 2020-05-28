import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.swing.Timer;

@SuppressWarnings("deprecation")
public class MemoryModel extends Observable {

	private int numberOfRows = 3;
	private int numberOfColumns = 3;
	private Tile[][] tiles;
	private List<Tile> guesses;
	private Timer timer;
	private int guessesCount;
	public boolean reset = false; // this is how other methods know whether or not it has to be resetted
	
	// Constructor: instantiate the tiles board (tiles[][]), the guesses list and the timer
	// .. also calls the two methods to fill the memory board when the app starts
	public MemoryModel() {
		tiles = new Tile[numberOfRows][numberOfColumns];
		guesses = new ArrayList<Tile>();
		timer = new Timer(1000, new ResetTileController(this));
		
		fillBoard();
		attachNumbersToTiles();
	}

	// Getters which are used in other methods
	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}
	
	public int getNumberOfRows() {
		return numberOfRows;
	}

	public int getNumberOfColumns() {
		return numberOfColumns;
	}		

	public int getGuessesCount() {
		return guessesCount;
	}

	// Fills the board with tiles
	private void fillBoard() {		
		for(int r = 0; r < numberOfRows; r++) {
			for(int c = 0; c < numberOfColumns; c++) {	
				
				// Leave the middle cell empty
				if((numberOfRows % 2 == 1) && (numberOfColumns % 2 == 1) && 
				(r == (numberOfRows / 2)) && (c == (numberOfColumns / 2))) {					
					tiles[r][c] = null;	
					}
				else tiles[r][c] = new Tile(false, false);		
			}
		}
	}
	
	// Give random values to tiles by creating and shuffling them
	private void attachNumbersToTiles() {
		int totalPairs = (numberOfRows * numberOfColumns) / 2;		
		List<Integer> numbers = new ArrayList<Integer>(totalPairs);
		int value = 1;
		
		for(int i = 0; i < totalPairs; i++) {
			numbers.add(value);
			numbers.add(value);
			value++;
		}
		
		Collections.shuffle(numbers);	
		
		for (int r = 0; r < numberOfRows; r++) {
			for(int c = 0; c < numberOfColumns; c++) {
				if(tiles[r][c] != null) {
					tiles[r][c].setNumber(numbers.get(0));
					numbers.remove(0);
				}
			}
		}		
	}
	
	// After mouse event, add the tile to the list of guesses
	public void addToGuesses(int row, int column) {
	
		Tile tile = getTile(row, column);		
		if(guesses.size() < 2) {			
			if(!(tile.isGuessed() || tile.isSelected())) {
				guesses.add(tile);
				tile.setSelected(true);
			}
		}
		else tile.setSelected(false);
	}
	
	// After mouse event & addToGuesses method, checks if the tiles have the same value
	// ..if so, set the tile to guessed (true).
	public void checkMatch() {		
		if(guesses.size() == 2) { 
				
			Tile tile1 = guesses.get(0);
			Tile tile2 = guesses.get(1);
				
			if(tile1.getNumber() == tile2.getNumber()) {
				tile1.setGuessed(true);
				tile2.setGuessed(true);
			}						
				
			timer.start();		
		}
	}	
	
	// This method is called in the ResetTimerController, which is used when the timer is instantiated
	// .. Resets the tile to its hidden state after 1000ms
	public void resetTiles() {		
		timer.stop();		
		
		guesses.get(0).setSelected(false);
		guesses.get(1).setSelected(false);
		
		guesses.remove(1);
		guesses.remove(0);
		
		setChanged();
		notifyObservers();		
	}

	public void countGuesses() {
		guessesCount = 0;		
		int uniqueTilesCount = (numberOfRows * numberOfColumns) / 2;
		
		for (int r = 0; r < numberOfRows; r++) {
			for(int c = 0; c < numberOfColumns; c++) {
			
				Tile tile = getTile(r, c);
				if(tile != null) {
					if(tile.isGuessed() == true) {
						guessesCount++;
					}
				}
			}
		}
		guessesCount = guessesCount / 2; // Add 1 guessed for a match (2 tiles)		
		
		if(guessesCount == uniqueTilesCount) {
			GameEnding end = new GameEnding("You finished the game");
		}
	}

	public void resetGame() {
		System.out.println("\n\n\nNew game!");
		
		guessesCount = 0;
		for (int r = 0; r < numberOfRows; r++) {
			for(int c = 0; c < numberOfColumns; c++) {
				Tile tile = getTile(r, c);
				
				if(tile != null) {
					tile.setGuessed(false);
					tile.setSelected(false);
					tiles[r][c] = new Tile(false, false);
				}
			}
		}
	
		attachNumbersToTiles(); // Attach new numbers to the tiles
		reset = true;
		
		setChanged();
		notifyObservers();
	}
}

