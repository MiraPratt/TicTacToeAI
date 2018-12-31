import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Mira Pratt
 * This class is a window that contains 
 * the buttons which represent the squares for tictactoe
 * */
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
 
 public Board getBoard() {
  return b;
 }
 
}
