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
	}
	
}
