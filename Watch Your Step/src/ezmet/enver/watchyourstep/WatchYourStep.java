package ezmet.enver.watchyourstep;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ezmet.enver.mycomponents.TitleLabel;

public class WatchYourStep extends JFrame {
	private static final long serialVersionUID=1L;
	private static final int GRIDSIZE=10;
	private static final int NUMBEROFHOLES=10;
	private TerrianButton[][] terrian=new TerrianButton[GRIDSIZE][GRIDSIZE];
	private int totalRevealed=0;
	Dimension size=new Dimension(GRIDSIZE,NUMBEROFHOLES);

	
	public WatchYourStep(){
		initGUI();
		setHoles();
		setTitle("Watch Your Step");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setPreferredSize(size);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initGUI(){
		TitleLabel titleLabel=new TitleLabel("Watch Your Step");
		add(titleLabel, BorderLayout.PAGE_START);
		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(new GridLayout(GRIDSIZE,GRIDSIZE));
		add(centerPanel,BorderLayout.CENTER);
		
	for(int row=0; row<GRIDSIZE;row++){
		for(int col=0;col<GRIDSIZE;col++){
			terrian[row][col]=new TerrianButton(row, col);
			centerPanel.add(terrian[row][col]);
		}
	}
		
		
		
	}
	public void setHoles(){
		Random rand=new Random();
		for(int i=0;i<NUMBEROFHOLES;i++){
			int pickRow=rand.nextInt(GRIDSIZE);
			int pickCol=rand.nextInt(GRIDSIZE);
			while(terrian[pickRow][pickCol].hasHole()){
				pickRow=rand.nextInt(GRIDSIZE);
				pickCol=rand.nextInt(GRIDSIZE);
			}
			terrian[pickRow][pickCol].setHole(true);
			terrian[pickRow][pickCol].reveal(true);
			
		}
	}
	
  public static void main (String[] args){
		// TODO Auto-generated method stub
		
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);

		} catch (Exception e) {

		}
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new WatchYourStep();

			}
		});

  }

}
