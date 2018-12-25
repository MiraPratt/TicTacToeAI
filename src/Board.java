import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Board extends JPanel{
	
	private char turn = 'x';
	
	private Tile[][] tiles;
	
	public  Board() {
		
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch(Exception e) {}
		
		this.setSize(300, 300);
		this.tiles = new Tile[3][3];
		this.setLayout(new GridLayout(3,3));
		
		for(int i = 0 ; i < 3 ; i++) {
			
			for(int j = 0 ; j < 3 ; j++) {
	
				tiles[j][i] = new Tile(j,i);
				this.add(tiles[j][i]);
	
				tiles[j][i].addActionListener(new ActionListener(){
							
					public void actionPerformed(ActionEvent e) {
						Tile t = (Tile)e.getSource();	
						mark(t);
					}
							
				});
					
			}	
		}
	    this.setVisible(true);
	    
	}

	
	public void mark(JButton t) {
		if(t.getText() == "") {
			t.setText(String.valueOf(getTurn()));
			changeTurn();
		}
	}
	
	public void changeTurn() {
		if(getTurn() == 'x')
			turn = 'o';
		else
			turn = 'x';
	}
	
	public char getTurn() {
		return turn;
	}
	
}
