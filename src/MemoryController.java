import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MemoryController implements MouseListener {

	private MemoryModel model;
	private int row;
	private int column;
	
	public MemoryController(MemoryModel model, int row, int column) {
		this.model = model;
		this.row = row;
		this.column = column;
	}

	// When a user clicks on a tile, two methods from the model are called
	@Override
	public void mouseClicked(MouseEvent event) {	
		model.addToGuesses(row, column);
		model.checkMatch();		
		model.countGuesses();
	}

	// The following mouse events do nothing, but the methods can't be removed
	// .. because this class implements MouseListener
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {	
	} 
}