import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;



public class Board extends JPanel{
	
	private String turn = "x";
	
	//temporary
	private int t = 0;
	
	private Tile[][] tiles;
	
	private Tile AITile;
	
	private boolean gameover = false;
	
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
						if(!gameover) {
							//player goes
							Tile t1 = (Tile)e.getSource();	
							mark(t1);
							
							
							int result = outcome(toArray());
							if(result > 0) {
								if(result == 1) {
									getTiles()[1][1].setText("x won");
									gameover = true;
								}
								if(result == 2){
									getTiles()[1][1].setText("o won");
									gameover = true;
								}	
								if(result == 3){
									getTiles()[1][1].setText("tie");
									gameover = true;
								}	
							}
							//AI goes
							AIMove(true, true, toArray());
							//System.out.println(Integer.toString(t));
							mark(AITile);
					
							
							
							result = outcome(toArray());
							if(result > 0) {
								if(result == 1) {
									getTiles()[1][1].setText("x won");
									gameover = true;
								}
								if(result == 2){
									getTiles()[1][1].setText("o won");
									gameover = true;
								}	
								if(result == 3){
									getTiles()[1][1].setText("tie");
									gameover = true;
								}	
							}
						}
					}
							
				});
					
			}	
		}
	    this.setVisible(true);
	    
	}
	
	public String arrayToString(int[] arr) {
		String temp = "";
	     for(int i = 0 ; i < arr.length ; i++) {
	    	 temp = temp + arr[i];
	     }
	     return temp;
	}
	
	public int[] AIMove(boolean max,boolean firstcall, int[] arr) {
		
		int[][] possiblemoves = {arr.clone() , arr.clone(), arr.clone(), arr.clone(), arr.clone(), arr.clone(), arr.clone(), arr.clone(), arr.clone()};
		int[] outcomes = {0,0,0,0,0,0,0,0,0};
		
		//make an array to represent the board state after all possible moves
		//player o turn
		if(max) {
			for(int i = 0; i < 9 ; i++) {
				if(possiblemoves[i][i] == 0) {
					possiblemoves[i][i] = 2;
				}
			}
		}
		//player x turn
		if(!max) {
			for(int i = 0; i < 9 ; i++) {
				if(possiblemoves[i][i] == 0) {
					possiblemoves[i][i] = 1;
				}
			}
		}
		
		if(t < 0) {
			System.out.println("AIMove class has been called");
			System.out.println("base array:" + arrayToString(arr));
			System.out.println("generated arrays:\n" + arrayToString(possiblemoves[0]) + " \n" + arrayToString(possiblemoves[1])+ " \n"+ arrayToString(possiblemoves[2])+ " \n"+ arrayToString(possiblemoves[3])+ " \n"+ arrayToString(possiblemoves[4])+ " \n"+ arrayToString(possiblemoves[5])+ " \n"+ arrayToString(possiblemoves[6])+ " \n"+ arrayToString(possiblemoves[7])+ " \n"+ arrayToString(possiblemoves[8]));
	        t++;
		}
		//outcome of those possible moves
		for(int i = 0; i < 9 ; i++) {

			outcomes[i] = outcome(possiblemoves[i]);
			if(outcomes[i] == 0) {
				//outcome of the possible moves of the possible move
				
				if(!Arrays.equals(arr, possiblemoves[i])) { //don't call on itself
					possiblemoves[i] = AIMove(!max,false, possiblemoves[i]);
					t++;
				}
				outcomes[i] = outcome(possiblemoves[i]);
			}
		}
		
		
		//what tile should we pick at the end of all this
		if(firstcall) {
			//pick the win or draw if max is true
			if(max) {
				System.out.println(arrayToString(outcomes));
				for(int i = 0; i < 9 ; i++) {
					if(outcomes[i] == 1) {
						AITile = getTiles()[i % 3][i / 3];
						//System.out.println("Loss assigned" + i);
					}
				}
				for(int i = 0; i < 9 ; i++) {
					if(outcomes[i] == 3) {
						AITile = getTiles()[i % 3][i / 3];
						//System.out.println("Tie assigned" + i);
					}
				}
				//pick center square if all else is equal
				if(outcomes[4] == 3) {
					AITile = getTiles()[4 % 3][4 / 3];
					//System.out.println("Tie assigned" + i);
				}
				for(int i = 0; i < 9 ; i++) {
					if(outcomes[i] == 2) {
						AITile = getTiles()[i % 3][i / 3];
						//System.out.println("Win assigned" + i);
					}
				}
			}
	
		}
		
		
		//return the best board state
		if(max) {
			for(int i = 0; i < 9 ; i++) {
				if(outcomes[i] == 2) {
					return possiblemoves[i];
				}
			}
			for(int i = 0; i < 9 ; i++) {
				if(outcomes[i] == 3) {
					return possiblemoves[i];
				}
			}
			for(int i = 0; i < 9 ; i++) {
				if(outcomes[i] == 0) {
					return possiblemoves[i];
				}
			}
			for(int i = 0; i < 9 ; i++) {
				if(outcomes[i] == 1) {
					return possiblemoves[i];
				}
			}
		}	
		
		//return best board state for opponent
		if(!max) {
			for(int i = 0; i < 9 ; i++) {
				if(outcomes[i] == 1) {
					return possiblemoves[i];
				}
			}
			for(int i = 0; i < 9 ; i++) {
				if(outcomes[i] == 3) {
					return possiblemoves[i];
				}
			}
			for(int i = 0; i < 9 ; i++) {
				if(outcomes[i] == 0) {
					return possiblemoves[i];
				}
			}
			for(int i = 0; i < 9 ; i++) {
				if(outcomes[i] == 2) {
					return possiblemoves[i];
				}
			}
		}
		
		
		//this should never get run and is here for the compiler
		return possiblemoves[0];
		
		
		
		
	}

	public int outcome(int[] arr) {
		int r = 0;
		
		   //tie
	    if(arr[0] > 0 && arr[1]  > 0 && arr[2]  > 0 && arr[3]  > 0 && arr[4]  > 0 && arr[5]  > 0 && arr[6]  > 0 && arr[7]  > 0 && arr[8]  > 0) {
	    	r = 3;
	    }
		
		//x wins
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
	    
	    //o wins
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
	    if(arr[2] == 2 && arr[5] == 2 && arr[8] == 2) {
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
