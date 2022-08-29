package ezmet.enver.slidingtiles;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import ezmet.enver.mycomponents.TitleLabel;

public class SlidingTiles extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String FILENAME = "slidingTilesImage.jpg";
	private int tileSize = 50;
	private int gridSize = 4;
	private BufferedImage image = null;
	private TileButton[][] tile=new TileButton[gridSize][gridSize];
	private JPanel centerPanel=new JPanel();

	public SlidingTiles() {
		try {
			image = ImageIO.read(new File(FILENAME));
			TileButton.setTileSizeAndMaxTiles(tileSize, gridSize * gridSize);
			initGUI();
			setTitle("Sliding Tiles");
			setResizable(true);
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		} catch (IOException e) {
			String message="The image file "+ FILENAME + " could not be opened";
			JOptionPane.showMessageDialog(this, message);
			e.printStackTrace();
		}
	}

	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Sliding Tiles");
		add(titleLabel, BorderLayout.PAGE_START);
		
		//main window
		divideImage();
		
		
		//button panel
		//  Ezmet
		
		

	}
	
	private void divideImage() {
		centerPanel.setLayout(new GridLayout(gridSize,gridSize));
		add(centerPanel,BorderLayout.CENTER);
		
		int imageId=0;
		
		for(int row=0;row<gridSize;row++) {
			for(int col=0;col<gridSize;col++) {
				int x=col*gridSize;
				int y=row*gridSize;
				
				BufferedImage subImage=image.getSubimage(x, y, tileSize,tileSize);
				ImageIcon imageIcon=new ImageIcon(subImage);
				tile[row][col]=new TileButton(imageIcon,imageId,row,col);
				centerPanel.add(tile[row][col]);
				imageId++;
			}
			
		}
	}

	public static void main(String[] args) {
		String className = UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(className);
		} catch (Exception e) {
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SlidingTiles();
			}
		});
	}

}
