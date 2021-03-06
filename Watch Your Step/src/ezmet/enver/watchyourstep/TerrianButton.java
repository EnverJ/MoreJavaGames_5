package ezmet.enver.watchyourstep;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JButton;

public class TerrianButton extends JButton {
	private static final long serialVersionUID=1L;
	private static final int SIZE=50;
	private int row=0;
	private int col=0;
	private int nextToHoles=0;
	private boolean hole=false;
	private boolean revealed=false;
	
	public TerrianButton(int row, int col){
		this.row=row;
		this.col=col;
		
		Dimension size=new Dimension(SIZE,SIZE);
		setPreferredSize(size);
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	public boolean hasHole(){
		return hole;
	}
	public boolean isRevealed(){
		return revealed;
	}
	
	
	public void setHole(boolean hasHole){
		hole=hasHole;
	}
	
	public void increaseHoleCount(){
		nextToHoles+=1;
	}
	public boolean isNextToHoles(){
		return nextToHoles>0;
	}
	
	//change the appearance of the terrain button when the terrain is revealed. 
	public void reveal(boolean reveal){
		revealed=reveal;
		if(revealed){
			if(hasHole()){
				setBackground(Color.BLACK);
			}else{
				setBackground(Color.CYAN);
			
			if(isNextToHoles()){
				setText(""+nextToHoles);
			}
			}
			
		}
		else{
			setBackground(null);
			setText("");
		}
	}
	// reset terrain buttons
	public void reset(){
		hole=false;
		revealed=false;
		nextToHoles=0;
		setText("");
		setBackground(null);
	}
	

}
