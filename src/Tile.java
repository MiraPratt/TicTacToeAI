import javax.swing.*;

public class Tile extends JButton {
	
	private int x;
	
	private int y;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int get_X() {
		return x;
	}
	
	public int get_Y() {
		return y;
	}

}
