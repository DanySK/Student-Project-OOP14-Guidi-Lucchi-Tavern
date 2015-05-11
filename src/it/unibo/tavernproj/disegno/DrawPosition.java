package it.unibo.tavernproj.disegno;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Map;

import javax.swing.JLabel;

/**
 *  @author Giulia Lucchi
 *
 */

public class DrawPosition implements MouseListener,Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = -2547031979468896800L;

  private final Map<Integer, Pair<Integer, Integer>> draw =DrawMap.getMap();
	private final JLabel label;
	private int index=0;
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
		draw.put(index, new Pair<>(x0,y0));
		index++;
		System.out.println(draw);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
  public void paint(Graphics g, int x0, int y0) {
  g.drawRect(x0, y0, 50, 50);
  g.setColor(Color.black); 
  
  }
  public void paintCancel(Graphics g, int x, int y){
    g.setColor(label.getBackground());
    g.drawRect(x,y, 50, 50);
  }
  
  public void cancel(Graphics g1){
    if(!draw.isEmpty()){
      Pair<Integer, Integer> p = draw.get(draw.size()-1);
      this.paintCancel(label.getGraphics(), p.getX(),p.getY());
      draw.remove(draw.size()-1);
    }else{
      System.out.println("Ho cancellato tutti i tavoli");
      
    }
  }

  public void cancelAll(Graphics g1){
   for(int i : draw.keySet()){
     Pair<Integer, Integer> p = draw.get(i);
     this.paintCancel(label.getGraphics(), p.getX(),p.getY());
   }
   draw.clear();
  }
  
 


  }
