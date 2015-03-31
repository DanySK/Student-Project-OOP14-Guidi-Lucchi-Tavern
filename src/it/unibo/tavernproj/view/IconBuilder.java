package it.unibo.tavernproj.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
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
	
	final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private final int sw = (int) screen.getWidth() * 3/4;
	private final int sh = (int) screen.getHeight() * 3/4;
	
	public JPanel buildPanel(final LayoutManager lm){
		final JPanel p = new JPanel(lm);
		p.setBackground(Color.WHITE);
		return p;
	}
	
	public JLabel buildLogo(final String srt){
		try{
			//final BufferedImage myPicture = ImageIO.read(getClass().getResourceAsStream("/"+srt));
			final BufferedImage myPicture = ImageIO.read(new File(srt));
			ImageIcon img = new ImageIcon(myPicture);
			Image temp = img.getImage().getScaledInstance(sw*1/4, sh*1/4, Image.SCALE_DEFAULT);
			img.setImage(temp);
			return new JLabel(img);
		}catch(IOException e){
			throw new IllegalArgumentException();
		}
	}		
	
	public JLabel buildMap(final String srt){
		try{
			//final BufferedImage myPicture = ImageIO.read(getClass().getResourceAsStream(srt));
			final BufferedImage myPicture = ImageIO.read(new File(srt));
			ImageIcon img = new ImageIcon(myPicture);
			Image temp = img.getImage().getScaledInstance(sw*25/40, sh*25/40, Image.SCALE_SMOOTH);
			img.setImage(temp);
			return new JLabel(img);
		}catch(IOException e){
			throw new IllegalArgumentException();
		}
	}
	
	public JButton buildButton(final String srt){
		try{
			final BufferedImage myPicture = ImageIO.read(new File(srt));
			final JButton picButton = new JButton(new ImageIcon(myPicture));
			picButton.setBackground(Color.WHITE);
			picButton.setBorderPainted(false);
			return picButton;
		}catch(IOException e){
			throw new IllegalArgumentException();
		}
	}

}
