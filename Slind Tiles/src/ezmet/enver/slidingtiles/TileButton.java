package ezmet.enver.slidingtiles;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TileButton extends JButton {

	private static final long serialVersionUID = 1L;
	private static int titleSize=0;
	private static int maxTiles=0;
	private ImageIcon imageIcon;
	private int imageId=0;
	private int row=0;
	private int col=0;
	
	
	public void setImage(ImageIcon imageIcon,int imageId) {
		this.imageIcon=imageIcon;
		this.imageId=imageId;
		if(imageId==maxTiles) {
			setIcon(null);
			
		}else {
			setIcon(imageIcon);
		}
		
	}

}
