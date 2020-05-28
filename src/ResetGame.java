import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ResetGame implements ActionListener {

	private MemoryModel model;
	
	public ResetGame(MemoryModel model) {
		this.model = model;
	}

	
	// Reset the game and create new views
	@Override
	public void actionPerformed(ActionEvent event) {
		
		JFrame frame = new JFrame();
		int selection = JOptionPane.showConfirmDialog(frame , "Are you sure you want to reset the game?", "Are you sure?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (selection == JOptionPane.OK_OPTION) {
			model.resetGame();
		}
	}
}
