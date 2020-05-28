import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StatusView extends JPanel implements Observer {
	
	private MemoryModel model;
	private JLabel label2;

	// Constructor: create two labels
	public StatusView(MemoryModel model) {
		this.model = model;
		model.addObserver(this);
		
		int uniqueTiles = (model.getNumberOfColumns() * model.getNumberOfRows()) / 2;
		
		JLabel label1 = new JLabel("Total unique tiles: " + String.valueOf(uniqueTiles));
		this.add(label1);
		
		label2 = new JLabel("Tiles guessed: " + updateCurrentTilesInfo()); // Must be 0 on initialization
		this.add(label2);
	}

	private int updateCurrentTilesInfo() {
		int currentGuesses = model.getGuessesCount();
		return currentGuesses;
	}
	
	private void refresh() {				
		label2.setText("Tiles guessed: " + updateCurrentTilesInfo());
	}
	
	// Update current amount of tiles which are guessed
	@Override
	public void update(Observable model, Object info) {
		refresh();
	}
}
