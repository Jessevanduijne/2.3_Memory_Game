import java.util.Observable;

public class Tile extends Observable {

	private int number;
	private boolean selected;
	private boolean guessed; 	
	
	public Tile(boolean selected, boolean guessed) {
		this.selected = selected;
		this.guessed = guessed;
	}

	// Getters and setters which are used in multiple methods
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public boolean isSelected() {
		return selected;
	}

	public boolean isGuessed() {
		return guessed;
	}

	public void setGuessed(boolean guessed) {
		this.guessed = guessed;
		setChanged();
		notifyObservers(guessed);
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		setChanged();
		notifyObservers(selected);
	}
}
