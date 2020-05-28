import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TileView extends JPanel implements Observer{

	private Tile tile;
	private BufferedImage image;
	
	// Constructor: automatically read the images & add this tileView as an observer from a specific tile
	public TileView(Tile tile) {
		this.tile = tile;
		tile.addObserver(this);	
		
		readImage();
	}
	
	// Read the images from the folder
	private void readImage() {				
		try {					
			int tileNumber = tile.getNumber();
			URL imageURL = this.getClass().getResource("/memes/meme" + tileNumber + ".jpg"); // Shows memes :)
			this.image = ImageIO.read(imageURL);
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	// Print images on the screen when selected or guessed
	public void paintComponent(Graphics g) {		
	
		super.paintComponent(g);
	
		if(tile.isGuessed() || tile.isSelected()) {
			Dimension size = this.getSize();
			g.drawImage(this.image, 0, 0, size.width, size.height, 0, 0,
						this.image.getWidth(), this.image.getHeight(), null);	
		}		
		else {		
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, this.image.getWidth(), this.image.getHeight());
		}
	}
	
	// Repaint uses the method paintComponent, which decides whether a tile should be shown or not
	private void refresh() {
		repaint();
	}

	// Every time a tile changes, the update calls the refresh method
	@Override
	public void update(Observable arg0, Object arg1) {
		refresh();		
	}
}