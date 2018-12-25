import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Board extends JPanel{
	
	private String turn = "x";
	
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
	
				getTiles()[j][i] = new Tile(j,i);
				this.add(getTiles()[j][i]);
	
				getTiles()[j][i].addActionListener(new ActionListener(){
							
					public void actionPerformed(ActionEvent e) {
						Tile t = (Tile)e.getSource();	
						mark(t);
						int result = outcome();
						int[] r = toArray();
						System.out.println(r[0] + "" + r[1] + r[2]);
						if(result > 0) {
							if(result == 1) {
								getTiles()[1][1].setText("x won");
							}
							else {
								getTiles()[1][1].setText("o won");
							}
								
						}
					}
							
				});
					
			}	
		}
	    this.setVisible(true);
	    
	}

	public int outcome() {
		int[] arr = toArray();
		int r = 0;
	    if(arr[0] == 1 && arr[1] == 1 && arr[2] == 1) {
	    	r = 1;
	    }
	    if(arr[3] == 1 && arr[4] == 1 && arr[5] == 1) {
	    	r = 1;
	    }
	    if(arr[6] == 1 && arr[7] == 1 && arr[8] == 1) {
	    	r = 1;
	    }
	    if(arr[0] == 1 && arr[4] == 1 && arr[8] == 1) {
	    	r = 1;
	    }
	    if(arr[2] == 1 && arr[4] == 1 && arr[6] == 1) {
	    	r = 1;
	    }
	    if(arr[0] == 1 && arr[3] == 1 && arr[6] == 1) {
	    	r = 1;
	    }
	    if(arr[1] == 1 && arr[4] == 1 && arr[7] == 1) {
	    	r = 1;
	    }
	    if(arr[2] == 1 && arr[5] == 1 && arr[8] == 1) {
	    	r = 1;
	    }
	    
	    
	    if(arr[0] == 2 && arr[1] == 2 && arr[2] == 2) {
	    	r = 2;
	    }
	    if(arr[3] == 2 && arr[4] == 2 && arr[5] == 2) {
	    	r = 2;
	    }
	    if(arr[6] == 2 && arr[7] == 2 && arr[8] == 2) {
	    	r = 2;
	    }
	    if(arr[0] == 2 && arr[4] == 2 && arr[8] == 2) {
	    	r = 2;
	    }
	    if(arr[2] == 2 && arr[4] == 2 && arr[6] == 2) {
	    	r = 2;
	    }
	    if(arr[0] == 2 && arr[3] == 2 && arr[6] == 2) {
	    	r = 2;
	    }
	    if(arr[1] == 2 && arr[4] == 2 && arr[7] == 2) {
	    	r = 2;
	    }
	    if(arr[2] == 2 && arr[5] == 2 && arr[9] == 2) {
	    	r = 2;
	    }
	    return r;
	    
	}
	
	
	
	
	public int[] toArray() {
		int[] arr = new int[9];
		
		for(int i = 0; i < 3 ; i++) {
			for(int j = 0; j < 3 ; j++) {
				if(getTiles()[j][i].getText() == "x"){
					arr[3 * i + j] = 1;
				}
				if(getTiles()[j][i].getText() == "o"){
					arr[3 * i + j] = 2;
				}
			}
		}
		return arr;
	}
	
	
	public void mark(JButton t) {
		if(t.getText() == "") {
			t.setText(String.valueOf(getTurn()));
			changeTurn();
		}
	}
	
	public void changeTurn() {
		if(getTurn() == "x")
			turn = "o";
		else
			turn = "x";
	}
	
	public String getTurn() {
		return turn;
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
	
}
