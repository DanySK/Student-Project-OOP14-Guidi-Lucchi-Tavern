package it.unibo.tavernproj.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
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

public class Utilities implements IUtilities{
  
  private static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
  private static final int WIDTH = (int) SCREEN.getWidth() * 4 / 5;
  private static final int HEIGHT = (int) SCREEN.getHeight() * 3 / 4;
  
  private static final int LOGO = 1 / 4;
  
  @Override
  public JPanel buildPanel(final LayoutManager lm) {
    final JPanel p = new JPanel(lm);
    p.setBackground(Color.WHITE);
    return p;
  }
  
	public JLabel buildLogo(final String srt){
		try{
			final BufferedImage myPicture = ImageIO.read(getClass().getResourceAsStream("/"+srt));
			//final BufferedImage myPicture = ImageIO.read(new File(srt));
			ImageIcon img = new ImageIcon(myPicture);
			Image temp = img.getImage().getScaledInstance(WIDTH*LOGO, HEIGHT*LOGO, Image.SCALE_DEFAULT);
			img.setImage(temp);
			return new JLabel(img);
		}catch(IOException e){
			throw new IllegalArgumentException();
		}
	}		
	
	public JLabel buildMap(final String srt){
		try{
			final BufferedImage myPicture = ImageIO.read(getClass().getResourceAsStream("/" + srt));
			//final BufferedImage myPicture = ImageIO.read(new File(srt));
			ImageIcon img = new ImageIcon(myPicture);
			Image temp = img.getImage().getScaledInstance(WIDTH*25/40, HEIGHT*25/40, Image.SCALE_SMOOTH);
			img.setImage(temp);
			return new JLabel(img);
		}catch(IOException e){
			throw new IllegalArgumentException();
		}
	}
	
	public JLabel dateLabel(){  
    JLabel date = new JLabel(this.getCurrentDate());
    date.setFont(new Font("Arial", Font.BOLD, 18));
    return date;	  
	}
	
	public JButton buildButton(final String srt){
		final JButton picButton = new JButton(this.getButtonIcon(srt));
		picButton.setBackground(Color.WHITE);
		picButton.setBorderPainted(false);
		return picButton;
	}
	
	public ImageIcon getButtonIcon(final String srt){
		try{
			final BufferedImage myPicture = ImageIO.read(getClass().getResourceAsStream("/"+srt));
			//final BufferedImage myPicture = ImageIO.read(new File(srt));
			ImageIcon img = new ImageIcon(myPicture);
			Image temp = img.getImage().getScaledInstance(HEIGHT*1/8, HEIGHT*1/8, Image.SCALE_SMOOTH);
			img.setImage(temp);
			return img;
		}catch(IOException e){
			throw new IllegalArgumentException();
		}
	}
	
	public String getCurrentDate(){
	  java.util.Calendar localCalendar = java.util.Calendar.getInstance();
    int month = localCalendar.get(java.util.Calendar.MONTH);
    int year = localCalendar.get(java.util.Calendar.YEAR);
    int day = localCalendar.get(java.util.Calendar.DATE);   
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
    localCalendar.set(year, month, day);
    return sdf.format(localCalendar.getTime());
	}



  public JPanel buildGridPanel(JButton c1, JButton c2, int i) {
    final JPanel panel = this.buildPanel(new GridBagLayout());

    final GridBagConstraints gap = new GridBagConstraints();
    gap.gridy = 0;
    gap.insets = new Insets(i, i, i, i);
    gap.fill = GridBagConstraints.HORIZONTAL;
    
    panel.add(c1, gap);
    gap.gridy++;
    panel.add(c2, gap);
    
    return panel;
  }



  public JButton defaultButton(String string) {
    JButton button = new JButton(string);
    button.setFont(new Font("Arial", Font.BOLD, 18));
    button.setBackground(Color.white);
    return button;
  }

  public int getDefaultWidth() {
    return this.WIDTH;
  }



  public int getDefaultHeight() {
    return this.HEIGHT;
  }
}
