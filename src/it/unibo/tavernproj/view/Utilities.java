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
 */

public class Utilities implements IUtilities{
  
  private static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
  private static final int WIDTH = (int) SCREEN.getWidth() * 4 / 5;
  private static final int HEIGHT = (int) SCREEN.getHeight() * 3 / 4;
  
  @Override
  public JPanel getDefaultPanel(final LayoutManager lm) {
    final JPanel p = new JPanel(lm);
    p.setBackground(Color.WHITE);
    return p;
  }
  
  @Override
  public JLabel getDefaultLogo(final String srt) throws IOException {
    final ImageIcon img = this.getImage(srt);
    final Image temp = img.getImage().getScaledInstance(WIDTH * 1 / 4,
        HEIGHT * 1 / 4, Image.SCALE_DEFAULT);
    img.setImage(temp);
    return new JLabel(img);
  }

  @Override
  public JLabel getDefaultMap(final String srt) throws IOException {
    final ImageIcon img = this.getImage(srt);
    final Image temp = img.getImage().getScaledInstance(WIDTH * 25 / 40,
        HEIGHT * 25 / 40, Image.SCALE_SMOOTH);
    img.setImage(temp);
    return new JLabel(img);
  }

  @Override
  public JLabel getDateLabel() {
    final JLabel date = new JLabel(this.getCurrentDate());
    date.setFont(new Font("Arial", Font.BOLD, 18));
    return date;
  }

  @Override
  public JButton getPicButton(final String srt) throws IOException {
    final JButton picButton = new JButton(this.getButtonIcon(srt));
    picButton.setBackground(Color.WHITE);
    picButton.setBorderPainted(false);
    return picButton;
  }

  private ImageIcon getButtonIcon(final String srt) throws IOException {
    final ImageIcon img = this.getImage(srt);
    final Image temp = img.getImage().getScaledInstance(HEIGHT * 1 / 8,
        HEIGHT * 1 / 8, Image.SCALE_SMOOTH);
    img.setImage(temp);
    return img;
  }

  private ImageIcon getImage(final String  srt) throws IOException {
    final BufferedImage myPicture = ImageIO.read(getClass().getResourceAsStream("/" + srt));
    return new ImageIcon(myPicture);
  }

  @Override
  public String getCurrentDate() {
    final java.util.Calendar localCalendar = java.util.Calendar.getInstance();
    final int month = localCalendar.get(java.util.Calendar.MONTH);
    final int year = localCalendar.get(java.util.Calendar.YEAR);
    final int day = localCalendar.get(java.util.Calendar.DATE);   
    final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
    localCalendar.set(year, month, day);
    return sdf.format(localCalendar.getTime());
  }

  @Override
  public JPanel buildGridPanel(final JButton c1, final JButton c2, final int ins) {
    final JPanel panel = this.getDefaultPanel(new GridBagLayout());
    final GridBagConstraints gap = new GridBagConstraints();
    gap.gridy = 0;
    gap.insets = new Insets(ins, ins, ins, ins);
    gap.fill = GridBagConstraints.HORIZONTAL;    
    panel.add(c1, gap);
    gap.gridy++;
    panel.add(c2, gap);    
    return panel;
  }

  @Override
  public JButton getDefaultButton(final String string) {
    final JButton button = new JButton(string);
    button.setFont(new Font("Arial", Font.BOLD, 18));
    button.setBackground(Color.white);
    return button;
  }

  @Override
  public int getDefaultWidth() {
    return Utilities.WIDTH;
  }

  @Override
  public int getDefaultHeight() {
    return Utilities.HEIGHT;
  }
}
