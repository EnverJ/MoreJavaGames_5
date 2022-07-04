package ezmet.enver.watchyourstep;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ezmet.enver.mycomponents.TitleLabel;

public class WatchYourStep extends JFrame {
	private static final long serialVersionUID=1L;
	private static final int GRIDSIZE=10;
	private static final int NUMBEROFHOLES=10;
	Dimension terrain=new Dimension(GRIDSIZE,NUMBEROFHOLES);
	private int totalRevealed=0;
	public WatchYourStep(){
		initGUI();
		setTitle("Watch Your Step");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initGUI(){
		TitleLabel titleLabel=new TitleLabel("Watch Your Step");
		add(titleLabel, BorderLayout.PAGE_START);
		
		JPanel centerPanel=new JPanel();
		
		
		
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
