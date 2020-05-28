import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonBarView extends JPanel {

	private MemoryModel model;
	
	//  Constructor: create buttons
	public ButtonBarView(MemoryModel model) {
		this.model = model;
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ResetGame(model));
		this.add(resetButton, BorderLayout.WEST);
		
		JButton button2 = new JButton("button2");
		this.add(button2, BorderLayout.EAST);	
	}
}