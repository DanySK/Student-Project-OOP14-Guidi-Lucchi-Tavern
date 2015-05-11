package it.unibo.tavernproj.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * @author Eleonora Guidi
 *
 */

/*Usato l'esame 01b del 2015 per fare la form!*/
public class ReservationForm extends JFrame implements IReservationForm {
  
  private static final long serialVersionUID = 1L;
  //numero massimo di campi per il form
  private static final int MAX = 100;
  
  //costanti che gestiscono i nomi che si trovano sulle JLabel della from
  private static final String TAV = "Tavolo";
  private static final String NOME = "Nome"; 
  private static final String NUM = "Numero di persone";
  private static final String ORA = "Orario";
  private static final String TEL = "Telefono";
  private static final String MENU = "Menu";
  private static final String MENUFISSO = "Menu Fisso";

  private final ProgressiveAcceptor<JPanel> panelAggregator = 
      new ProgressiveAcceptorImpl<>();
  private final Map<String, JComponent> map = new HashMap<>();

  private final JButton okButton = new JButton("OK");

  /**
   * Builds a new form.
   */
  public ReservationForm() {
    super();
    setLocationByPlatform(true);
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    final int sw = (int) screen.getWidth() * 1 / 2;
    final int sh = (int) screen.getHeight() * 1 / 2;
    this.setSize(sw, sh);
    this.setResizable(true);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    buildLayout();
    setHandlers();
    this.setVisible(true);
  }

  private void buildLayout() {
    this.getContentPane().setLayout(new BorderLayout());    
    buildForm();    
  }

  private void buildForm() {
    panelAggregator.setProgressiveFilter(new ProgressiveFilter<JPanel>(){
      @Override
      public boolean isNextOK(final JPanel previous, final JPanel next) {
        return true;
      }
    });

    panelAggregator.setAggregator(new Aggregator<JPanel>(){
      @Override
      public JPanel aggregate(final JPanel one, final JPanel two) {
        final JPanel temp = new JPanel(new GridBagLayout());
        GridBagConstraints gap = new GridBagConstraints();
        gap.gridy = 0;
        temp.add(one, gap);
        gap.gridy++;
        temp.add(two, gap);
        return temp;
      }
    });
    panelAggregator.setSize(MAX);

    // Pannello centrale, ossia una griglia a due colonne
    final JPanel center = new JPanel(new GridLayout(0,2));

    acceptPanel(new JLabel(TAV), FlowLayout.RIGHT, 0);
    acceptPanel(new JLabel(NOME),FlowLayout.RIGHT, 1);
    acceptPanel(new JLabel(ORA),FlowLayout.RIGHT, 2);
    acceptPanel(new JLabel(NUM),FlowLayout.RIGHT, 3);
    acceptPanel(new JLabel(TEL),FlowLayout.RIGHT, 4);
    acceptPanel(new JLabel(MENUFISSO),FlowLayout.RIGHT, 5);
    acceptPanel(new JLabel(""),FlowLayout.RIGHT, 6);
    center.add(panelAggregator.aggregateAll());

    acceptPanel(addTextField(TAV), FlowLayout.CENTER, 0);
    acceptPanel(addTextField(NOME), FlowLayout.CENTER, 1);
    acceptPanel(addTextField(ORA), FlowLayout.CENTER, 2);
    acceptPanel(addTextField(NUM), FlowLayout.CENTER, 3);
    acceptPanel(addTextField(TEL), FlowLayout.CENTER, 4);
    acceptPanel(addRadioButton(MENU), FlowLayout.CENTER, 5);
    acceptPanel(addTextField(MENUFISSO), FlowLayout.CENTER, 6);
    map.get(MENUFISSO).setVisible(false);
    center.add(panelAggregator.aggregateAll());

    this.getContentPane().add(center, BorderLayout.CENTER);
    this.getContentPane().add(okButton, BorderLayout.SOUTH);
  }

  private void setHandlers() {
    ((JRadioButton) map.get(MENU)).addActionListener(e -> {
        if (((JRadioButton) map.get(MENU)).isSelected()) {
          map.get(MENUFISSO).setVisible(true);
          ReservationForm.this.validate();
        } else {
          map.get(MENUFISSO).setVisible(false);
          ReservationForm.this.validate();
        }
      });

    this.okButton.addActionListener(e -> { ReservationForm.this.setVisible(false); }); 
  } 
  
  private JTextField addTextField(final String string) {
    final JTextField t = new JTextField(20);
    t.setName(string);
    map.put(string, t);
    return t;
  }

  private JRadioButton addRadioButton(final String string) {
    final JRadioButton b = new JRadioButton();
    b.setSelected(false);
    b.setName(string);
    map.put(string, b);
    return b;
  }

  private void acceptPanel(final JComponent component, final int orientation, final int pos) {
    final JPanel panel = new JPanel(new FlowLayout(orientation));
    panel.add(component);
    panelAggregator.accept(pos, panel);
  }

  @Override
  public Integer getTable() throws NumberFormatException {
    if (((JTextField) map.get(NUM)).getText().isEmpty()) {
      throw new NumberFormatException();
    }
    return Integer.parseInt(((JTextField) map.get(TAV)).getText());
  }

  @Override
  public String getName() throws NullPointerException {
    return ((JTextField)map.get(NOME)).getText();
  }

  @Override
  public String getH() throws NullPointerException {
    return ((JTextField) map.get(ORA)).getText();
  }

  @Override
  public String getTel() throws NullPointerException {
    return ((JTextField)map.get(TEL)).getText();
  }

  @Override
  public String getNum() throws NumberFormatException {
    if (((JTextField) map.get(NUM)).getText().isEmpty()) {
      throw new NumberFormatException();
    }
    return ((JTextField) map.get(NUM)).getText();
  }

  @Override
  public String getMenu() {
    return ((JTextField)map.get(MENUFISSO)).getText();
  }

  //RIDURRE CON UNA LAMBDA
  @Override
  public void disableAll() {
    for (final String s: map.keySet()) {
      map.get(s).setEnabled(false);
    }
  }

  @Override
  public void enableAll() {
    for (final String s: map.keySet()) {
      map.get(s).setEnabled(true);
    }
    map.get(TAV).setEnabled(false);
  }

  @Override
  public void setTable(final int srt) {
    ((JTextField) map.get(TAV)).setText("" + srt);
  }

  @Override
  public void setName(final String srt) {
    ((JTextField) map.get(NOME)).setText(srt);
  }

  @Override
  public void setH(final String srt) {
    ((JTextField) map.get(ORA)).setText(srt);
  }

  @Override
  public void setTel(final String srt) {
    ((JTextField) map.get(TEL)).setText(srt);
  }

  @Override
  public void setNum(final int srt) {
    ((JTextField) map.get(NUM)).setText("" + srt);
  }

  @Override
  public void setMenu(final String srt) {
    ((JTextField) map.get(MENUFISSO)).setText(srt);
    ((JRadioButton) map.get(MENU)).setSelected(true);
    map.get(MENUFISSO).setVisible(true);
  }
}
