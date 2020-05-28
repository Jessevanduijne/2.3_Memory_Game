import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MemoryView extends JPanel {

	private MemoryModel model;
	private TileView[][] tileViews;
	
	// Constructor: create grid to place the tileViews in
	public MemoryView(MemoryModel model) {
		this.model = model;
		
		int numberOfRows = model.getNumberOfRows();
		int numberOfColumns = model.getNumberOfColumns();	
		int horizontalGap = 10, verticalGap = 10;
		
		GridLayout grid = new GridLayout(numberOfRows, numberOfColumns, horizontalGap, verticalGap);
		this.setLayout(grid);
		
		tileViews = new TileView[numberOfRows][numberOfColumns];
		
		for(int r = 0; r < numberOfRows; r++) {
			for(int c = 0; c < numberOfColumns; c++) {			
				createTileView(r, c);
			}
		}
	}
	
	// Create a tile view for a single tile
	private void createTileView(int r, int c) {
		Tile tile = model.getTile(r, c);
		
		if(tile == null) {
			JPanel dummyPanel = new JPanel();
			dummyPanel.setVisible(false);
			this.add(dummyPanel);		
		}
		
		else {
			tileViews[r][c] = new TileView(tile);	
			tileViews[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			tileViews[r][c].addMouseListener(new MemoryController(model, r, c));
			this.add(tileViews[r][c]);
		}	
	}
}
