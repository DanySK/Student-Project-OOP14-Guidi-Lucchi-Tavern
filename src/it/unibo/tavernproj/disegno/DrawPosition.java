package it.unibo.tavernproj.disegno;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *  @author Giulia Lucchi
 *
 */

public class DrawPosition implements MouseListener {
  private final Map<Integer,Pair<Integer,Integer>> disegno = new HashMap<>();
  
  private int index=0;
	
	private final JLabel label;
	private DrawTable table;
	private int x0=0;
	private int y0=0;
	
	public DrawPosition(JLabel label){
		this.label=label;
	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
			}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.x0=e.getX();
		this.y0=e.getY();
		this.paint(label.getGraphics());
		this.setMap(x0, y0);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	

 
  
  public void setMap(int x, int y){
    index++;
    disegno.put(index, new Pair<>(x,y));
  }
  
  public void paint(Graphics g) {
  g.drawRect(x0, y0, 50, 50);
  g.setColor(Color.black); 
  
}
  
  public void cancel(Graphics g1){
    Pair<Integer, Integer> p = disegno.get(disegno.size());
    g1.setColor(label.getBackground());
    g1.drawRect(p.getX(),p.getY(), 50, 50);
    disegno.remove(disegno.size());
    
    
}

public void cancelAll(Graphics g1){
 for(int i : disegno.keySet()){
   Pair<Integer, Integer> p = disegno.get(i);
   g1.setColor(label.getBackground());
   g1.drawRect(p.getX(),p.getY(), 50, 50);
 }
 disegno.clear();
}

public Map<Integer,Pair<Integer,Integer>> getMap(){
  return disegno;
}
	
}