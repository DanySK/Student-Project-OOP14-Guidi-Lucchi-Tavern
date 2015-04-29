package it.unibo.tavernproj.controller;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.view.IView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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
	private final IModel model = new Model();
	private String[] files= new String[2000];
	private ObjectOutput out;
	private ObjectInput  in;
	private ObjectOutput outMap;
	private ObjectInput  inMap; 
	



	private Controller(){};
	
	public static Controller getController(){
	  return SINGLETON;

	}

	@Override
	public void addTable(String table, String date) {
		for (final IView v: view){	
			v.addTable(table, date);
		}
	}

	@Override
	public void removeTable(final int number) {
		// TODO Auto-generated method stub
		
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
	public void setTables(Map<String,Map<Integer,IReservation>> map){
		int i = 0;
//		try {
//			outMap = new ObjectOutputStream(new FileOutputStream("map"));
//			outMap.writeObject(label.getIcon());
//			outMap.close();
//			
//		} catch (Exception e1) {
//		// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		for(String date : map.keySet()){
			files[i]=date;
			try{
				out = new ObjectOutputStream(new FileOutputStream(date));
				Map<Integer, IReservation> temp = map.get(date);
				
				for(Integer table : temp.keySet()){
					out.writeObject(temp.get(table));
				}
				out.writeObject(1);
				out.close();
			}catch (Exception e){
				
			}
			i++;
		}
		
		
	}
	
	
	@Override
	public void getTables(final IModel model){
  for(String date : files){
			try{
				in = new ObjectInputStream(new FileInputStream(date));
				IReservation r = (IReservation) in.readObject();
				while(!r.equals(1)){
				  model.add(date,(IReservation) r);
				  r=(IReservation) in.readObject();
				}
				in.close();
			}catch(Exception e){
				
			}

			
		}
		
	}
	
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

