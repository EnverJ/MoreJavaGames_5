package ezmet.enver.watchyourstep;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ezmet.enver.mycomponents.TitleLabel;

public class WatchYourStep extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int GRIDSIZE = 10;
	private static final int NUMBEROFHOLES = 10;
	private TerrianButton[][] terrian = new TerrianButton[GRIDSIZE][GRIDSIZE];
	private int totalRevealed = 0;
	Dimension size = new Dimension(GRIDSIZE, NUMBEROFHOLES);

	public WatchYourStep() {
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

	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Watch Your Step");
		add(titleLabel, BorderLayout.PAGE_START);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		add(centerPanel, BorderLayout.CENTER);

		for (int row = 0; row < GRIDSIZE; row++) {
			for (int col = 0; col < GRIDSIZE; col++) {
				terrian[row][col] = new TerrianButton(row, col);
				terrian[row][col].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//
						TerrianButton button = (TerrianButton) e.getSource();
						int row = button.getRow();
						int col = button.getCol();
						clickedTerriain(row, col);

					}
				});
				centerPanel.add(terrian[row][col]);
			}
		}

	}

	private void clickedTerriain(int row, int col) {
		if (terrian[row][col].hasHole()) {
			String message = "Ezmet stepped into a hole. Game Over! Do you wanna paly again?";
			promptForNewGame(message);

		} else {
			check(row, col);
			checkNeighbors(row, col);
		}
	}

	private void promptForNewGame(String message) {
		showHoles();
		int option = JOptionPane.showConfirmDialog(this, message, "paly again?", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			newGame();
		} else {
			System.exit(0);
		}
	}
	private void showHoles() {
		for(int row=0;row<GRIDSIZE;row++) {
			for(int col=0;col<GRIDSIZE;col++) {
				if(terrian[row][col].hasHole()) {
					terrian[row][col].reveal(true);
				}
			}
		}
	}

	private void newGame() {
		for (int row = 0; row < GRIDSIZE; row++) {
			for (int col = 0; col < GRIDSIZE; col++) {
				terrian[row][col].reset();
			}
		}
		setHoles();
		totalRevealed = 0;
	}

	private void check(int row, int col) {
		if (row > -1 && row < GRIDSIZE && col > -1 && col < GRIDSIZE && !terrian[row][col].hasHole()
				&& !terrian[row][col].isRevealed()) {
			terrian[row][col].reveal(true);
			totalRevealed++;
			if (!terrian[row][col].isNextToHoles()) {
				check(row,col);
				checkNeighbors(row, col);
				if (totalRevealed == GRIDSIZE * GRIDSIZE - NUMBEROFHOLES) {
					String message = "Congratlations!!! You Won!!! Do you wanna paly again?";
					promptForNewGame(message);

				}

			}
		}
	}

	public void checkNeighbors(int row, int col) {
		check(row - 1, col - 1);
		check(row - 1, col);
		check(row - 1, col + 1);
		check(row, col - 1);
		check(row, col + 1);
		check(row + 1, col - 1);
		check(row + 1, col);
		check(row + 1, col + 1);

	}

	public void setHoles() {
		Random rand = new Random();
		for (int i = 0; i < NUMBEROFHOLES; i++) {
			int pickRow = rand.nextInt(GRIDSIZE);
			int pickCol = rand.nextInt(GRIDSIZE);
			while (terrian[pickRow][pickCol].hasHole()) {
				pickRow = rand.nextInt(GRIDSIZE);
				pickCol = rand.nextInt(GRIDSIZE);
			}
			terrian[pickRow][pickCol].setHole(true);
			addToNeighborsHoleCount(pickRow, pickCol);

		}
	}

	private void addToNeighborsHoleCount(int row, int col) {
		// add 8 times
		addToHoleCount(row - 1, col);
		addToHoleCount(row - 1, col - 1);
		addToHoleCount(row - 1, col + 1);
		addToHoleCount(row, col - 1);
		addToHoleCount(row, col + 1);
		addToHoleCount(row + 1, col);
		addToHoleCount(row + 1, col - 1);
		addToHoleCount(row + 1, col + 1);

	}

	private void addToHoleCount(int row, int col) {
		//
		if (row > -1 && row < GRIDSIZE && col > -1 && col < GRIDSIZE) {
			terrian[row][col].increaseHoleCount();
		}

	}

	public static void main(String[] args) {
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
