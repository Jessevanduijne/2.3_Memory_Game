import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MemoryFrame extends JFrame {

	@SuppressWarnings("unused")
	private MemoryModel model;
	
	// Configures the frame settings, and opens the items (buttons, labels & panel) within the frame
	public MemoryFrame(String title, MemoryModel model) {
		this.model = model;
		
		this.setBounds(300, 200, 1000, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(title);
		this.setVisible(true);
		
		ButtonBarView buttonBar = new ButtonBarView(model);
		this.add(buttonBar, BorderLayout.NORTH);
		
		MemoryView panel = new MemoryView(model);
		this.add(panel, BorderLayout.CENTER);
		
		StatusView statusView = new StatusView(model);
		this.add(statusView, BorderLayout.SOUTH);
		
		if(model.reset == true) {
			this.remove(panel);
			MemoryView newPanel = new MemoryView(model);
			this.add(newPanel, BorderLayout.CENTER);
		}
	}
}
