package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.IReservation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Chooser extends JFrame{
  
  private final JLabel lDat = new JLabel("Data (formato gg-mm-aaa): ");
  private final JTextField dat = new JTextField(20);
  private final JLabel lName = new JLabel("Nome: ");
  private final JTextField name = new JTextField(20);
  private final JLabel lTav = new JLabel("Tavolo: ");
  private final JTextField tav = new JTextField(20);
  private boolean choosedByDate = false;
  private boolean removed = false;
  private int table;
  private JButton ok = new JButton("OK");
  
  private final JPanel res = new JPanel(new GridBagLayout());
  
  private String date;
  
  private final IController controller;
  
  public Chooser(IController controller){
    this.setSize(500, 400);
    
    this.controller = controller;
    
    JButton bDate = new JButton("Scegli per data");
    bDate.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0) {
        choosedByDate = true;
        JFrame frame = new JFrame("Calendar");
        Calendar calendar = new Calendar(frame);
        while (!calendar.getPickedDate().equals("Error") && !calendar.isRight()) {
          //FARE una finestra al posto del messaggio stampato
          System.out.println("Selezionare una data utile");
          calendar = new Calendar(frame);
        }
        if (!calendar.getPickedDate().equals("Error")) {
          date = calendar.getPickedDate();
          if (controller.getReservation(date).size() == 0){
            //FARE FORM CHE ESCE FUORI
            System.out.print("Nessuna prenotazione per la data selezionata.");
          }else{
            loadReservation(date);
            lTav.setVisible(true);
            tav.setVisible(true);
            ok.setVisible(true);
          }
        }
        
      }
      
    });
    
    
    JButton bPerson = new JButton("Scegli per nome");
    bPerson.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        Set<String> dates = controller.getDates();
        for (String s: dates){
          loadReservation(s);
        }
        
        lDat.setVisible(true);
        dat.setVisible(true);
        lName.setVisible(true);
        name.setVisible(true);
        ok.setVisible(true);        
      }
      
    }); 
    
    
    JPanel top = new JPanel(new FlowLayout());
    top.add(bDate);
    top.add(bPerson);   
    
    lDat.setVisible(false);
    dat.setVisible(false);
    tav.setVisible(false);
    lTav.setVisible(false);
    name.setVisible(false);
    lName.setVisible(false);
    
    ok.setVisible(false);
    ok.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        Chooser.this.setVisible(false);
        if (choosedByDate) {          
          try{
            table = Integer.parseInt(tav.getText());
          }catch(NumberFormatException e1){
            System.out.print("Il numero del tavolo è sbagliato");
            Chooser.this.setVisible(true);
          }
        }
        else {
          try{
            date = dat.getText();
            table = controller.getReservation(date, name.getText());  
          }catch(IllegalArgumentException e1){
            System.out.print("Il nome o la data inseriti sono sbagliati");
            Chooser.this.setVisible(true);
          }
        }
        
        controller.remove(table, date);
        /*setto la variaabile rimossa solo se è il giorno corrente, 
         * così modifico la view solo se necessario */
        IUtilities utilities = new Utilities();
        if (date.equals(utilities .getCurrentDate())){
          removed = true;  
        }
              
      }      
    });
    
    JPanel up = new JPanel(new FlowLayout());
    up.add(lTav);
    up.add(tav);
    
    JPanel center = new JPanel(new FlowLayout());
    center.add(lDat);
    center.add(dat);
    
    JPanel down = new JPanel(new FlowLayout());
    down.add(lName);
    down.add(name);
    
    JPanel south = new JPanel(new GridBagLayout());
    final GridBagConstraints gap = new GridBagConstraints();
    gap.gridy = 0;
    gap.insets = new Insets(5, 5, 5, 5);
    gap.fill = GridBagConstraints.HORIZONTAL;
   
    south.add(up, gap);
    gap.gridy++;
    south.add(center, gap);
    gap.gridy++;
    south.add(down, gap);
    gap.gridy++;
    south.add(ok, gap);

    
    JPanel main = new JPanel(new BorderLayout());
    main.add(top, BorderLayout.NORTH);
    main.add(res, BorderLayout.CENTER);
    main.add(south, BorderLayout.SOUTH);
    
    this.getContentPane().add(main);
    this.setVisible(true);
  }
  
  private void loadReservation(String date) {
    //scaricare da un file le prentazioni in base alla data

    final GridBagConstraints gap = new GridBagConstraints();
    gap.gridy = 0;
    gap.insets = new Insets(5, 5, 5, 5);
    gap.fill = GridBagConstraints.HORIZONTAL;
    
    for (Integer i: controller.getReservation(date).keySet()){
      res.add(new JLabel(controller.getReservation(date).get(i).toString()), gap); //stampare il set con le prenotazioni nel file di sopra
      gap.gridy++;
    }
    this.validate();
  }
  
  public boolean isBeenRemoved(){
    return this.removed;
  }
  
  public int getTable(){
    return this.table;
  }

}
