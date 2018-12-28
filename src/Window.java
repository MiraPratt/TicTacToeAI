import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Window extends JFrame {

	private Board b = new Board();
	
	public Window() {
		this.setSize(600,600);
		this.getContentPane().add(b);
		this.setTitle("TIC-TAC-TOE");
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		Window win = new Window();
		
		//testing a board state
		/*
		win.getBoard().getTiles()[0][0].setText("x");
		win.getBoard().getTiles()[1][1].setText("o");
		win.getBoard().getTiles()[2][1].setText("x");
		win.getBoard().getTiles()[2][2].setText("o");
		*/
	}
	
	public Board getBoard() {
		return b;
	}
	
}
