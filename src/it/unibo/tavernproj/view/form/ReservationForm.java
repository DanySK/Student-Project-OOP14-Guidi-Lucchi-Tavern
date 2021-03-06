package it.unibo.tavernproj.view.form;

import it.unibo.tavernproj.view.utilities.GUIutilities;
import it.unibo.tavernproj.view.utilities.IGUIutilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.nio.charset.IllegalCharsetNameException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * @author Eleonora Guidi
 *
 */

/*Usato l'esame 01b del 2015 per fare la form!*/
public class ReservationForm extends BasicFrame implements IReservationForm{
  
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
  private static final String MENUFISSO = "Menu a Richiesta / Note";

  private final transient ProgressiveAcceptor<JPanel> panelAggregator = 
      new ProgressiveAcceptorImpl<>();
  private final Map<String, JComponent> map = new HashMap<>(); 
  
  protected final transient IGUIutilities util = new GUIutilities();
  
  /**
   * Builds a new form.
   */
  public ReservationForm() {
    super();
    buildLayout();
    setHandlers();
    this.setVisible(true);
  }

  private void buildLayout() {
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
        final JPanel temp = util.getDefaultPanel(new GridBagLayout());
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
    final JPanel center = util.getDefaultPanel(new GridLayout(0,2));

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
    super.getContentPane().add(center, BorderLayout.CENTER);
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
  } 
  
  private JTextField addTextField(final String string) {
    final JTextField t = new JTextField(20);
    t.setName(string);
    map.put(string, t);
    return t;
  }

  private JRadioButton addRadioButton(final String string) {
    final JRadioButton b = new JRadioButton();
    b.setBackground(Color.white);
    b.setSelected(false);
    b.setName(string);
    map.put(string, b);
    return b;
  }

  private void acceptPanel(final JComponent component, final int orientation, final int pos) {
    final JPanel panel = util.getDefaultPanel(new FlowLayout(orientation));
    panel.add(component);
    panelAggregator.accept(pos, panel);
  }

  @Override
  public Integer getTable() throws NumberFormatException, NullPointerException {   
    if (this.checkNull(TAV) 
        && ((Integer.parseInt(((JTextField)map.get(TAV)).getText())) > 20
        || (Integer.parseInt(((JTextField)map.get(TAV)).getText())) <= 0)) {
      throw new NumberFormatException();
    }    
    return Integer.parseInt(((JTextField) map.get(TAV)).getText());
  }

  @Override
  public String getName() throws NullPointerException, IllegalCharsetNameException {    
    this.checkNull(NOME);
    this.checkString(NOME);
    return ((JTextField)map.get(NOME)).getText();
  }

  @Override
  public Double getH() throws NullPointerException, NumberFormatException {
    if (this.checkNull(ORA) 
        && (Double.parseDouble(((JTextField) map.get(ORA)).getText()) <= 0
        || Double.parseDouble(((JTextField) map.get(ORA)).getText()) > 24)) {
      throw new NumberFormatException();
    }
    return Double.parseDouble(((JTextField) map.get(ORA)).getText());
  }

  @Override
  public String getTel() throws NullPointerException {
    return ((JTextField)map.get(TEL)).getText();
  }

  @Override
  public Integer getNum() throws NumberFormatException, NullPointerException {
    if (this.checkNull(NUM) 
        && (Integer.parseInt(((JTextField) map.get(NUM)).getText()) < 0
        || Integer.parseInt(((JTextField) map.get(NUM)).getText()) > 300)) {
      throw new NumberFormatException();
    }
    return Integer.parseInt(((JTextField) map.get(NUM)).getText());
  }

  @Override
  public String getMenu() throws NullPointerException, IllegalCharsetNameException {
    if (((JTextField)map.get(MENUFISSO)).isVisible()) {
      this.checkNull(MENUFISSO);
      this.checkString(MENUFISSO);
    }
    return ((JTextField)map.get(MENUFISSO)).getText();
  }

  @Override
  public void disableAll() {
    map.keySet().forEach(e -> map.get(e).setEnabled(false));
  }

  @Override
  public void enableAll() {
    map.keySet().forEach(e -> map.get(e).setEnabled(true));
    map.get(TAV).setEnabled(false);
  }  
  
  private boolean checkNull(final String srt) {
    if (((JTextField) map.get(srt)).getText().isEmpty() 
        || ((JTextField) map.get(srt)).getText().equals(" ")) {
      throw new NullPointerException();
    }   
    return true;
  }
  
  private void checkString(final String srt) {
    if ((int)((JTextField) map.get(srt)).getText().charAt(0) < 65
        || (int)((JTextField) map.get(srt)).getText().charAt(0) > 90
        && (int)((JTextField) map.get(srt)).getText().charAt(0) < 97
        || (int)((JTextField) map.get(srt)).getText().charAt(0) > 122) {
      throw new IllegalCharsetNameException(((JTextField) map.get(srt)).getText());
    }    
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
  public void setH(final Double srt) {
    ((JTextField) map.get(ORA)).setText("" + srt);
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
    if (!srt.equals("")) {
      ((JTextField) map.get(MENUFISSO)).setText(srt);
      ((JRadioButton) map.get(MENU)).setSelected(true);
      map.get(MENUFISSO).setVisible(true);
    }
  }
}
