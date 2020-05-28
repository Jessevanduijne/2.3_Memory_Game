
public class MemoryApplication {

	MemoryApplication() {
		MemoryModel model = new MemoryModel();
		
		// Open the console version
		ConsoleView consoleView = new ConsoleView(model);
		
		// Open the frame (JPanel) version
		MemoryFrame frame = new MemoryFrame("Memery by Jesse van Duijne", model);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		
		// Run the application
		new MemoryApplication();		
	}

}
