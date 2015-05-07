package it.unibo.tavernproj.disegno;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.JLabel;

/**
 *  @author Giulia Lucchi
 *
 */

public class DrawPosition implements MouseListener {

  private final IDrawMap draw = new DrawMap();
	
	private final JLabel label;
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
		this.paint(label.getGraphics(),x0,y0);
		draw.setMap(x0, y0);
		System.out.println(draw.getMap());
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public Map<Integer, Pair<Integer, Integer>> getMap(){
	  return draw.getMap();
	}

  public void paint(Graphics g, int x0, int y0) {
  g.drawRect(x0, y0, 50, 50);
  g.setColor(Color.black); 
  
  }
  public void paintCancel(Graphics g, int x, int y){
    g.setColor(label.getBackground());
    g.drawRect(x,y, 50, 50);
  }
  
  public void cancel(Graphics g1){
    if(!draw.getMap().isEmpty()){
      Pair<Integer, Integer> p = draw.getMap().get(draw.getSize());
      this.paintCancel(label.getGraphics(), p.getX(),p.getY());
      draw.getMap().remove(draw.getSize());
    }else{
      System.out.println("Ho cancellato tutti i tavoli");
      
    }
  }

  public void cancelAll(Graphics g1){
   for(int i : draw.getMap().keySet()){
     Pair<Integer, Integer> p = draw.getMap().get(i);
     this.paintCancel(label.getGraphics(), p.getX(),p.getY());
   }
   draw.removeAll();
  }
  
  }
