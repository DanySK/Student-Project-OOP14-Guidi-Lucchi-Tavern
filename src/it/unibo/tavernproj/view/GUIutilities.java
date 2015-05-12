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
import java.util.List;

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

/* Mi sono basata su questo http://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
 */

public class GUIutilities extends Utilities implements IGUIutilities{
  
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
    final JLabel date = new JLabel(super.getCurrentDate());
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
    return GUIutilities.WIDTH;
  }

  @Override
  public int getDefaultHeight() {
    return GUIutilities.HEIGHT;
  }

  @Override
  public JPanel buildGridPanel(final List<JComponent> list, final int ins) {
    final JPanel panel = this.getDefaultPanel(new GridBagLayout());
    final GridBagConstraints gap = new GridBagConstraints();
    gap.gridy = 0;
    gap.insets = new Insets(ins, ins, ins, ins);
    gap.fill = GridBagConstraints.HORIZONTAL;
    for (final JComponent c: list) {
      panel.add(c, gap);
      gap.gridy++;
    }    
    return panel;
  }
}
