package it.unibo.tavernproj.disegno;

import it.unibo.tavernproj.view.Utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Map;

import javax.swing.JLabel;

/**
 *  @author Giulia Lucchi
 *
 */

public class DrawTable implements IDrawTable{
	
	private final JLabel label;
	private int x;
	private int y;
	private int table=0;
	Utilities icon = new Utilities();
	
	
	public DrawTable(int x, int y, final JLabel label){
		this.x=x;
		this.y=y;
		this.label=label;
		
	}
	
	
	public void paint(Graphics g) {
//		super.paint(g);
		g.drawRect(x, y, 50, 50);;
		g.setColor(Color.black); 
		
	}
	
	@Override
	public void cancel(Graphics g1){
//  Map<Integer,Pair<Integer,Integer>> map = draw.getMap();
	//  	  System.out.println(map);
//	  Pair<Integer, Integer> p = map.get(draw.maxKey());
//	  System.out.println(p);
//		g1.clearRect(0,0, 50, 50);
//		g1.setColor(Color.white);
	}
	
	public void cancelAll(Graphics g1){
//	  Map<Integer,Pair<Integer,Integer>> map = draw.getMap();
//	  for(int i : map.keySet()){
//	    Pair<Integer, Integer> p = map.get(i);
//	    g1.fillRect(p.getX(),p.getY(), 50, 50);
//	    g1.setColor(label.getBackground());
//	  }
	}
	
	@Override
	public int getX(){
		return this.x;
	}
	
	@Override
	public int getY(){
		return this.y;
	}
	
	public int getTable(){
		return this.table;
	}




	

}
