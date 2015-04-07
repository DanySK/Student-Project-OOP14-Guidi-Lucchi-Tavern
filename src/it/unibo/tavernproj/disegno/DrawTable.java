package it.unibo.tavernproj.disegno;

import java.awt.Color;
import java.awt.Graphics;

/**
 *  @author Giulia Lucchi
 *
 */

public class DrawTable implements IDrawTable{
	
	private int x;
	private int y;
	
	
	public DrawTable(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.fillRect(this.x, this.y, 50,50);
		g.setColor(Color.black); 
	}
	
	@Override
	public void calcel(Graphics g1, int x1, int x2){
		g1.clearRect(x1, x2, 70, 70);
		g1.setColor(Color.WHITE);
		
	}
	
	@Override
	public int getX(){
		return this.x;
	}
	
	@Override
	public int getY(){
		return this.y;
	}
	
}
