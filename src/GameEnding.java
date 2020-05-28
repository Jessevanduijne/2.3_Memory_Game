import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameEnding extends JFrame {

	private String title;
	
	public GameEnding(String title) {
		this.setBounds(400, 300, 700, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(title);
		this.setVisible(true);		
		
		JLabel label = new JLabel("Congratulations, you finished the game!");
		label.setFont(label.getFont().deriveFont(30.0f));
		this.add(label, BorderLayout.CENTER);
	}
}
