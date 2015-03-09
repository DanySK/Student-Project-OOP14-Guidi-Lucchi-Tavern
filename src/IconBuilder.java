package src;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* Mi sono basata su questo http://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
 * 
 * */

public class IconBuilder {
	
	//private Default def = new Default();
	
	private JPanel p;
	
	public JPanel buildPanel(LayoutManager lm){
		p = new JPanel(lm);
		p.setBackground(Color.WHITE);
		return this.p;
	}
	
	public JLabel buildLabel(String name){
		if (name == null){
			throw new NullPointerException();
		}
		try{
			BufferedImage myPicture = ImageIO.read(new File(name));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));	
			return picLabel;
		}catch(IOException e){
			throw new IllegalArgumentException();
		}
	}	
	
	public JButton buildButton(String name, String s){
		if (name == null){
			throw new NullPointerException();
		}
		try{
			BufferedImage myPicture = ImageIO.read(new File(name));
			JButton picButton = new JButton(new ImageIcon(myPicture));
			picButton.setBackground(Color.WHITE);
			picButton.setBorderPainted(false);
			return picButton;
		}catch(IOException e){
			throw new IllegalArgumentException();
		}
	}	
	
	public JButton buildButton(String name){
		if (name == null){
			throw new NullPointerException();
		}
		try{
			BufferedImage myPicture = ImageIO.read(new File(name));
			JButton picButton = new JButton(new ImageIcon(myPicture));
			picButton.setBackground(Color.WHITE);
			return picButton;
		}catch(IOException e){
			throw new IllegalArgumentException();
		}
	}

}
