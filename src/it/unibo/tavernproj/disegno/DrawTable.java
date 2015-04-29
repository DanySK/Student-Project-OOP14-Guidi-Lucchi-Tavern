package it.unibo.tavernproj.disegno;

import it.unibo.tavernproj.view.IconBuilder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

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
	IconBuilder icon = new IconBuilder();
	
	
	public DrawTable(int x, int y, final JLabel label){
		this.x=x;
		this.y=y;
		this.label=label;
		
	}
	
	
	public Rectangle paintComponent(Graphics2D g,int x, int y) {
	//	super.paintComponent(g);
		Rectangle rect = new Rectangle(x, y, 50,50);
		g.draw(rect);
		g.setColor(Color.black); 
		return rect;
	}
	
	@Override
	public void calcel(Graphics g1, int x1, int x2){
		g1.setColor(label.getBackground());
		g1.fillRect(x1, x2, 50, 50);
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
