import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetTileController implements ActionListener {
	
	private MemoryModel model;
	
	public ResetTileController(MemoryModel model) {
		this.model = model;
	}

	// This action is automatically performed after 1000ms
	// ..because it's a parameter of the timer.
	@Override
	public void actionPerformed(ActionEvent e) {
		model.resetTiles();
	}
}
