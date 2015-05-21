package it.unibo.tavernproj.view;

import it.unibo.tavernproj.model.Utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Eleonora Guidi
 *
 */
public class BasicGUIutilities extends Utilities implements IBasicGUIutilities {
  
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
  public JPanel getDefaultPanel(final LayoutManager lm, final JComponent c1, final JComponent c2) {
    final JPanel temp = this.getDefaultPanel(lm);
    temp.add(c1);
    temp.add(c2);    
    return temp;
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
  public JLabel getDefaultMap(final String srt) {
    ImageIcon img = new ImageIcon();
    try {
      img = this.getImage(srt);
    } catch (IOException e) {
      // non rilancio eccezioni. Lascio l'immagine vuota.
    }
    final Image temp = img.getImage().getScaledInstance(WIDTH * 25 / 40,
        HEIGHT * 25 / 40, Image.SCALE_SMOOTH);
    img.setImage(temp);
    return new JLabel(img);
  }
  
  @Override
  public int getMapWidth() {
    return WIDTH * 25 / 40;
  }
  
  @Override
  public int getMapHeight() {
    return  HEIGHT * 25 / 40;
  }
  
  protected ImageIcon getImage(final String  srt) throws IOException {
    final BufferedImage myPicture = ImageIO.read(getClass().getResourceAsStream("/" + srt));
    return new ImageIcon(myPicture);
  }
  
  @Override
  public JButton getDefaultButton(final String string) {
    return this.getDefaultButton(string, 18);
  }
  
  @Override
  public JButton getDefaultButton(final String string, final int size) {
    final JButton button = new JButton(string);
    button.setFont(new Font("Arial", Font.BOLD, size));
    button.setBackground(Color.white);
    return button;
  }

  @Override
  public int getDefaultWidth() {
    return BasicGUIutilities.WIDTH;
  }

  @Override
  public int getDefaultHeight() {
    return BasicGUIutilities.HEIGHT;
  }  
}
