package it.unibo.tavernproj.controller;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.view.IView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * @author Eleonora Guidi
 * 
 * modify by @author Giulia Lucchi
 *
 */

//HO USATO IL PATTERN SINGLETON

public class Controller implements IController{
  
	private static final Controller SINGLETON = new Controller();  
	
	
	private final Set<IView> view = new HashSet<>();
	private IModel model = new Model();
	private String[] files= new String[2000];
	private ObjectOutput out;
	private ObjectInput  in;
	private ObjectOutput outMap;
	private ObjectInput  inMap; 
	



	private Controller(){};
	
	
	/*usato nel test junit per caricare un modello a piacere*/
	public void setModel(IModel model){
	  this.model = model;
	}
	
	public static Controller getController(){
	  return SINGLETON;
	}

	@Override
	public void addTable(Integer table, String date) {
		for (final IView v: view){	
			v.addTable(table, date);
		}
	}

	@Override
	public void removeTable(final int number) {
		// TODO Auto-generated method stub
		
	}
	
	
	 public void loadTables(String date) {
      if (!model.isEmpty()){
	      for (Integer i: model.getTableRes(date).keySet()){
    	    for (final IView v: view){  
    	      v.addTable(i, date);
    	    }
  	     }
	    }	
    }


  @Override
	  public Map<Integer,IReservation> getRes(final String date) {	
	   if (model.getRes(date).isEmpty()){
	     return new HashMap<>();
	   }
     return model.getTableRes(date);
	  } 
	

	@Override
	public IReservation getReservation(int table, String date) {
		for (IReservation r: model.getRes(date)){
			if (r.getTable() == table){
				return r;
			}
		}
		throw new NumberFormatException();
	}

	@Override
	public void addView(final IView v) {
		v.attachViewObserver(this);
		view.add(v);
	}

	@Override
	public void removeView(final IView v) {
		view.remove(v);	
	}


	/*
	 * Save and Load
	 * @author Giulia Lucchi
	 * 
	 */
	
	@Override
	public void saveModel(){
		//int i = 0;
//		try {
//			outMap = new ObjectOutputStream(new FileOutputStream("map"));
//			outMap.writeObject(label.getIcon());
//			outMap.close();
//			
//		} catch (Exception e1) {
//		// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		//for(String date : this.model.getMap().keySet()){
			//files[i]=date;
			try{
				out = new ObjectOutputStream(new FileOutputStream("map.txt"));//date));
				/*Map<Integer, IReservation> temp = this.model.getMap().get(date);
				
				for(Integer table : temp.keySet()){
					out.writeObject(temp.get(table));
				}
				*/
				out.writeObject(this.model.getMap());
				
				//out.writeObject(1);
				out.close();
			}catch (Exception e){
				
			}
			//i++;
		//}		
		
	}
	
	/*per caricare il modello da file system all'accensione*/
	 @Override
	  public void setModel(){	   
	    //Map<String, Map <Integer, IReservation>> map = new HashMap<>();
	    for(String date : files){
	        try{
	          in = new ObjectInputStream(new FileInputStream("map.txt"));//date));
	          //IReservation r = (IReservation) in.readObject();
	          //while(!r.equals(1)){
	            /*Map<Integer, IReservation> temp = new HashMap<>();
	            temp.put(r.getTable(), r);
	            map.put(date, temp);
	            r = (IReservation) in.readObject();*/
	            //this.model.setModel((Map<String, Map <Integer, IReservation>>)in.readObject());
	            model.setModel((Map<String, Map <Integer, IReservation>>) in.readObject());
	          //}
	          in.close();
	        }catch(Exception e){
	          //this.model = new Model();
	        }
	    }
	    
	  }
	//SALVARE TUTTO IL MODELLO SU FILESYSTEM (QUINDI LA MAPPA PRINCIPALE) E RISETTARLO AL CARICAMENTO

	
	@Override
	public Icon loadMap(JLabel label){
		Icon map=null;
		try{
			inMap = new ObjectInputStream(new FileInputStream("mappa"));
			map = (Icon) inMap.readObject();
			inMap.close();
		}catch(Exception e){
			
		}
		
		return map;
	}




	
}

