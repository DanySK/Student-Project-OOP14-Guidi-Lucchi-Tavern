package it.unibo.tavernproj.view;

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

/**
 * @author Eleonora Guidi
 *
 */

/* Mi sono basata su questo http://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
 * 
 * */

public class IconBuilder implements IIconBuilder{
	
	public JPanel buildPanel(final LayoutManager lm){
		final JPanel p = new JPanel(lm);
		p.setBackground(Color.WHITE);
		return p;
	}
	
	public JLabel buildLabel(final String str){
		try{
			final BufferedImage myPicture = ImageIO.read(new File(str));	
			return new JLabel(new ImageIcon(myPicture));
		}catch(IOException e){
			throw new IllegalArgumentException();
		}
	}		
	
	public JButton buildButton(final String str){
		try{
			final BufferedImage myPicture = ImageIO.read(new File(str));
			final JButton picButton = new JButton(new ImageIcon(myPicture));
			picButton.setBackground(Color.WHITE);
			picButton.setBorderPainted(false);
			return picButton;
		}catch(IOException e){
			throw new IllegalArgumentException();
		}
	}

}
