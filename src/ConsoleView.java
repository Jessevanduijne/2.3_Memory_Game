import java.util.Observable;
import java.util.Observer;

public class ConsoleView implements Observer {

	private MemoryModel model;
	
	// Constructor: add this view as an observer & call method which shows the answers
	public ConsoleView(MemoryModel model) {
		this.model = model;
		model.addObserver(this);	
		
		System.out.println("These are the answers: ");
		showTiles();
		
		if(model.reset == true) showTiles();
	}

	// Shows the answers by representing its number
	private void showTiles() {
		int numberOfRows = model.getNumberOfRows();
		int numberOfColumns = model.getNumberOfColumns();
		
		for (int r = 0; r < numberOfRows; r++) {
			for(int c = 0; c < numberOfColumns; c++) {
				
				Tile tile = model.getTile(r, c);
				
				if(tile != null)  System.out.format("%02d ", tile.getNumber()); 				
				else System.out.print("-- ");					
			}
			System.out.println();
		}
	}
	
	// If the model changes, calls the method which shows what tiles are guessed
	private void refresh() {
		System.out.println("\nThese are the tiles you have guessed:");
		guessedTiles();
		System.out.println();
	}
	
	// shows which tiles are guessed by representing its number when guessed
	private void guessedTiles() {
		int numberOfRows = model.getNumberOfRows();
		int numberOfColumns = model.getNumberOfColumns();
		
		for (int r = 0; r < numberOfRows; r++) {
			for(int c = 0; c < numberOfColumns; c++) {
				
				Tile tile = model.getTile(r, c);
				
				if(tile != null) {
					if(tile.isGuessed()) {
						System.out.format("%02d ", tile.getNumber()); 
					}
					else System.out.print("-- ");
				}
				else System.out.print("-- ");
			}
			System.out.println();
		}
	}
	
	// Automatically update when the model changes
	@Override
	public void update(Observable model, Object info) {
		refresh();
	}
}
